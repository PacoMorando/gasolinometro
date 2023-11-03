package sas.gasolinometro;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;

public class RegistryController {
    protected float lastVehicleKms = 0.0f;
    private final ArrayList<ConsumptionRegister> consumptionRegistries;
    private ConsumptionRegistryDAO consumptionRegistryDAO;

    public RegistryController(Context applicationContext) {
        AppDatabase dataBase = Room.databaseBuilder(applicationContext, AppDatabase.class, "consumption-registry").build();
        this.consumptionRegistryDAO = dataBase.consumptionRegistryDAO();
        this.consumptionRegistries = new ArrayList<>();
        //this.setMockConsumptionRegistries();
    }

    public void createRegister(float fuelLoaded, float currentVehicleKms) {
        this.consumptionRegistries.add(new ConsumptionRegister(fuelLoaded, currentVehicleKms, this.lastVehicleKms));
        this.consumptionRegistryDAO.insert(new ConsumptionRegister(fuelLoaded, currentVehicleKms, this.lastVehicleKms));
        this.lastVehicleKms = currentVehicleKms;
    }

    public ArrayList<ConsumptionRegister> getConsumptionRegistries() {
        return consumptionRegistries;
    }

    private void setMockConsumptionRegistries() {
        this.consumptionRegistries.add(new ConsumptionRegister(9.097f, 250.2f, lastVehicleKms));
        this.lastVehicleKms = 250.2f;
        this.consumptionRegistries.add(new ConsumptionRegister(8.087f, 501.2f, lastVehicleKms));
        this.lastVehicleKms = 501.2f;
        this.consumptionRegistries.add(new ConsumptionRegister(8.187f, 788.2f, lastVehicleKms));
        this.lastVehicleKms = 788.2f;
        this.consumptionRegistries.add(new ConsumptionRegister(8.287f, 1050.2f, lastVehicleKms));
        this.lastVehicleKms = 1050.2f;
        this.consumptionRegistries.add(new ConsumptionRegister(8.387f, 1305.2f, lastVehicleKms));
        this.lastVehicleKms = 1305.2f;
    }
}
