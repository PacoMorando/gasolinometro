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
}