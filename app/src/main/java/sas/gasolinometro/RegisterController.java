package sas.gasolinometro;

import java.util.ArrayList;

public class RegisterController {

    protected float lastVehicleKms = 0.0f;
    private final ArrayList<ConsumptionRegister> consumptionRegistries;
    private final ConsumptionRegistryRecyclerAdapter consumptionRegistryRecyclerAdapter;

    public RegisterController() {
        this.consumptionRegistries = new ArrayList<>();
        this.consumptionRegistryRecyclerAdapter = new ConsumptionRegistryRecyclerAdapter(this.consumptionRegistries);
        this.setMockConsumptionRegistries();
    }

    public void createRegister(float fuelLoaded, float distance) {
        this.consumptionRegistries.add(new ConsumptionRegister(fuelLoaded, distance, lastVehicleKms));
        this.lastVehicleKms = distance;
        this.consumptionRegistryRecyclerAdapter.notifyDataSetChanged();
    }

    public ConsumptionRegistryRecyclerAdapter getConsumptionRegistryRecyclerAdapter (){
        return this.consumptionRegistryRecyclerAdapter;
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
