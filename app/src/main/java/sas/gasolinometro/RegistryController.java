package sas.gasolinometro;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;

public class RegistryController {
    protected float previousVehicleKms;
    private final ArrayList<ConsumptionRegister> consumptionRegistries;
    private final ConsumptionRegistryDAO consumptionRegistryDAO;

    public RegistryController(Context applicationContext) {
        AppDatabase dataBase = Room.databaseBuilder(applicationContext, AppDatabase.class, "gasolinometro").allowMainThreadQueries().build();
        this.consumptionRegistryDAO = dataBase.consumptionRegistryDAO();
        this.consumptionRegistries = (ArrayList<ConsumptionRegister>) this.consumptionRegistryDAO.getAll();
        this.setPreviousVehicleKms();
    }

    private void setPreviousVehicleKms() {
        if (this.consumptionRegistryDAO.getLatestUser() != null) {
            this.previousVehicleKms = this.consumptionRegistryDAO.getLatestUser().getCurrentVehicleKms();
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
        this.consumptionRegistries.add(0, consumptionRegister);
        this.consumptionRegistryDAO.insert(consumptionRegister);
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
}
