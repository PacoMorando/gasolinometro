package sas.gasolinometro;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;

public class RegistryController {
    protected float previousVehicleKms;
    private final ArrayList<ConsumptionRegister> consumptionRegistries;
    private final ConsumptionRegistryDAO consumptionRegistryDAO;

    public RegistryController(Context applicationContext) {
        AppDatabase dataBase = Room.databaseBuilder(applicationContext, AppDatabase.class, "gasolinometro.db")
                .allowMainThreadQueries()
                .build();
        this.consumptionRegistryDAO = dataBase.consumptionRegistryDAO();
        this.consumptionRegistries = (ArrayList<ConsumptionRegister>) this.consumptionRegistryDAO.getAll();
        this.setPreviousVehicleKms();
    }

    public void setPreviousVehicleKms() {
        if (this.consumptionRegistryDAO.getLatestRegister() != null) {
            this.previousVehicleKms = this.consumptionRegistryDAO.getLatestRegister().getCurrentVehicleKms();
        } else {
            this.previousVehicleKms = 0.0f;
        }
    }

    public void createRegister(float gasLoaded, float currentVehicleKms) {
        ConsumptionRegister consumptionRegister = new ConsumptionRegister.Builder()
                .setGasLoaded(gasLoaded)
                .setCurrentVehicleKms(currentVehicleKms)
                .setPreviousVehicleKms(this.previousVehicleKms)
                .build();
        this.consumptionRegistryDAO.insert(consumptionRegister);
        this.consumptionRegistries.add(0, this.consumptionRegistryDAO.getLatestRegister());
        this.previousVehicleKms = currentVehicleKms;
    }

    public float getPreviousVehicleKms() {
        return this.previousVehicleKms;
    }

    public ArrayList<ConsumptionRegister> getConsumptionRegistries() {
        return consumptionRegistries;
    }

    public void setPreviousVehicleKms(Float previousVehicleKms) {
        this.previousVehicleKms = previousVehicleKms;
    }

    public void deleteRegister(ConsumptionRegister consumptionRegister) {
        this.consumptionRegistryDAO.deleteRegister(consumptionRegister);
    }

    public int getNextLoad() {
        return this.calculateNextLoad();
    }

    private int calculateNextLoad() {
        int tankCapacity = 10;//TODO "10" is the tank capacity, refactor pending when tank capacity becomes editable
        int averageSize = 3;//TODO "3" is for calculate an average taking more than the last consumption registry, to prevent variability of the consumption average. Refactor pending when average size becomes editable
        float consumptionAverage = this.calculateConsumptionAverage(averageSize);
        return (int) (this.previousVehicleKms + (consumptionAverage * tankCapacity));
    }

    private float calculateConsumptionAverage(int averageSize) {
        float consumptionAverage = 0;
        if (this.consumptionRegistries.size() < averageSize) {
            if (this.consumptionRegistryDAO.getLatestRegister() == null) {
                return consumptionAverage;
            }
            return (float) Math.floor(this.consumptionRegistryDAO.getLatestRegister().getConsumption());
        }
        for (int i = 0; i < averageSize; i++) {
            consumptionAverage += this.consumptionRegistries.get(i).getConsumption();
        }
        consumptionAverage = (float) Math.floor(consumptionAverage / averageSize);//TODO Math.floor is for give a factor in the average, to prevent variability of the consumption average
        return consumptionAverage;
    }
}