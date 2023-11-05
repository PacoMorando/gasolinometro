package sas.gasolinometro;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import sas.gasolinometro.databinding.ActivityMainBinding;

public class RegistryView {
    private final ActivityMainBinding binding;
    private final Context applicationContext;
    private final RegistryController registryController;
    private final ConsumptionRegistryRecyclerAdapter consumptionRegistryRecyclerAdapter;


    public RegistryView(ActivityMainBinding binding, Context applicationContext) {
        this.registryController = new RegistryController(applicationContext);
        this.consumptionRegistryRecyclerAdapter = new ConsumptionRegistryRecyclerAdapter(this.registryController.getConsumptionRegistries());
        this.binding = binding;
        this.applicationContext = applicationContext;
        this.setForm();
        this.setVehicleKms();
    }

    private void setVehicleKms() {
        this.binding.vehicleKms.setText("Vehicle kms = " + this.registryController.getPreviousVehicleKms() + "km");
        this.binding.vehicleKms.setOnClickListener(
                view -> new VehicleKmsDialog(this.binding.vehicleKms, this.registryController).show(binding.getMainActivity().getSupportFragmentManager(), "setVehicleKms")
        );
    }

    private void setForm() {
        this.binding.currentVehicleKms.setOnEditorActionListener((textView, i, keyEvent) -> {
            this.createRegister();
            return false;
        });
    }

    private void createRegister() {
        if (this.isRegisterValid()) {
            this.registryController.createRegister(this.formattedValue(this.binding.fuelLoaded.getText().toString()), this.formattedValue(this.binding.currentVehicleKms.getText().toString()));
            this.consumptionRegistryRecyclerAdapter.notifyDataSetChanged();
            this.clearForm();
        }
    }

    private void clearForm() {
        Toast.makeText(this.applicationContext, "GAS " + this.formattedValue(this.binding.fuelLoaded.getText().toString()) + "L. / " + this.formattedValue(this.binding.currentVehicleKms.getText().toString()) + "kms", Toast.LENGTH_SHORT).show();
        this.binding.fuelLoaded.requestFocus();
        this.binding.fuelLoaded.getText().clear();
        this.binding.currentVehicleKms.getText().clear();
    }

    private boolean isRegisterValid() {
        if (String.valueOf(this.binding.fuelLoaded.getText()).equals("")) {
            Toast.makeText(this.applicationContext, "Te faltó la gasolina ", Toast.LENGTH_SHORT).show();
            this.binding.fuelLoaded.requestFocus();
            return false;
        }
        if (String.valueOf(this.binding.currentVehicleKms.getText()).equals("")) {
            Toast.makeText(this.applicationContext, "Te faltó el kilometraje ", Toast.LENGTH_SHORT).show();
            this.binding.currentVehicleKms.requestFocus();//TODO Programar que el teclado no se esconda
            return false;
        }
        return true;
    }

    private float formattedValue(String value) {
        return (float) Math.round(Float.parseFloat(value) * 1000) / 1000;
    }

    public void setConsumptionRegistryResView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.applicationContext);
        this.binding.consumptionRegistryResView.setLayoutManager(layoutManager);
        this.binding.consumptionRegistryResView.setAdapter(this.consumptionRegistryRecyclerAdapter);
    }
}
