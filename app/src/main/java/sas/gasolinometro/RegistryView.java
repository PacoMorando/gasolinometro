package sas.gasolinometro;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import sas.gasolinometro.databinding.ActivityMainBinding;

public class RegistryView implements ViewUpdater {
    private final ActivityMainBinding binding;
    private final RegistryController registryController;
    private final ConsumptionRegistryRecyclerAdapter consumptionRegistryRecyclerAdapter;
    private final Context context;
    private final LinearLayoutManager layoutManager;


    public RegistryView(ActivityMainBinding binding) {
        this.registryController = new RegistryController(binding.getMainActivity().getApplicationContext());
        this.consumptionRegistryRecyclerAdapter = new ConsumptionRegistryRecyclerAdapter(this, this.registryController);
        this.layoutManager = new LinearLayoutManager(binding.getMainActivity().getApplicationContext());
        this.context = binding.getMainActivity().getApplicationContext();
        this.binding = binding;
        this.setViews();
    }

    private void setViews() {
        this.setConsumptionRegistryResView();
        this.binding.setKmButton.setOnClickListener(
                view -> new VehicleKmsDialog(this, this.registryController).show(binding.getMainActivity().getSupportFragmentManager(), "setVehicleKms")
        );
        this.binding.currentVehicleKms.setOnEditorActionListener((textView, i, keyEvent) -> {
            this.createRegister();
            this.updateViews();
            return false;
        });
        this.updateViews();
    }

    private void setConsumptionRegistryResView() {
        this.binding.consumptionRegistryResView.setLayoutManager(this.layoutManager);
        this.binding.consumptionRegistryResView.setAdapter(this.consumptionRegistryRecyclerAdapter);
        this.binding.consumptionRegistryResView.scrollToPosition(0);
    }

    private void createRegister() {
        if (this.isRegisterValid()) {
            this.registryController.createRegister(this.formattedValue(this.binding.fuelLoaded.getText().toString()), this.formattedValue(this.binding.currentVehicleKms.getText().toString()));
            this.registryController.setPreviousVehicleKms(this.formattedValue(this.binding.currentVehicleKms.getText().toString()));
            this.binding.vehicleKms.setText(this.registryController.getPreviousVehicleKms() + context.getString(R.string.length_unit));
            this.consumptionRegistryRecyclerAdapter.notifyDataSetChanged();
            this.clearForm();
        }
    }

    private boolean isRegisterValid() {
        if (String.valueOf(this.binding.fuelLoaded.getText()).equals("")) {
            Toast.makeText(this.context, this.context.getString(R.string.gas_loaded_missing), Toast.LENGTH_SHORT).show();
            this.binding.fuelLoaded.requestFocus();
            return false;
        }
        if (String.valueOf(this.binding.currentVehicleKms.getText()).equals("")) {
            Toast.makeText(this.context, this.context.getString(R.string.vehicle_length_missing), Toast.LENGTH_SHORT).show();
            this.binding.currentVehicleKms.requestFocus();//TODO Programar que el teclado no se esconda
            return false;
        }
        return true;
    }

    private void clearForm() {
        this.binding.fuelLoaded.requestFocus();
        this.binding.fuelLoaded.getText().clear();
        this.setCurrentVehicleInputHelper();
    }

    private void setCurrentVehicleInputHelper() {
        if (this.registryController.getPreviousVehicleKms() >= 1000) {
            this.binding.currentVehicleKms.setText(String.valueOf((int) this.registryController.getPreviousVehicleKms() / 1000));
            this.binding.currentVehicleKms.setSelection(this.binding.currentVehicleKms.getText().length());
        } else {
            this.binding.currentVehicleKms.getText().clear();
        }
    }

    @Override
    public void updateViews() {
        this.binding.vehicleKms.setText(this.registryController.getPreviousVehicleKms() + context.getString(R.string.length_unit));
        this.binding.nextLoad.setText(this.registryController.getNextLoad() + "km");
        this.setCurrentVehicleInputHelper();
    }

    private float formattedValue(String value) {
        return (float) Math.round(Float.parseFloat(value) * 1000) / 1000;
    }
}
