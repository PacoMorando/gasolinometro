package sas.gasolinometro;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;

public class RegistryController {
    protected float previousVehicleKms = 0.0f;
    private final ArrayList<ConsumptionRegister> consumptionRegistries;
    private final ConsumptionRegistryDAO consumptionRegistryDAO;

    public RegistryController(Context applicationContext) {
        AppDatabase dataBase = Room.databaseBuilder(applicationContext, AppDatabase.class, "consumption-registry").allowMainThreadQueries().build();
        this.consumptionRegistryDAO = dataBase.consumptionRegistryDAO();
        this.consumptionRegistries = (ArrayList<ConsumptionRegister>) this.consumptionRegistryDAO.getAll();
    }

    public void createRegister(float gasLoaded, float currentVehicleKms) {
        ConsumptionRegister consumptionRegister = new ConsumptionRegister.Builder()
                .setGasLoaded(gasLoaded)
                .setCurrentVehicleKms(currentVehicleKms)
                .setPreviousVehicleKms(this.previousVehicleKms)
                .build();
        this.consumptionRegistries.add(consumptionRegister);
        this.consumptionRegistryDAO.insert(consumptionRegister);
        this.previousVehicleKms = currentVehicleKms;
    }

    public ArrayList<ConsumptionRegister> getConsumptionRegistries() {
        return consumptionRegistries;
    }

}
