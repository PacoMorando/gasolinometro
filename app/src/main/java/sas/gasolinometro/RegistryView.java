package sas.gasolinometro;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import sas.gasolinometro.databinding.ActivityMainBinding;

public class RegistryView {
    private final ActivityMainBinding binding;
    private final RegistryController registryController;
    private final ConsumptionRegistryRecyclerAdapter consumptionRegistryRecyclerAdapter;
    private final Context context;
    private final LinearLayoutManager layoutManager;


    public RegistryView(ActivityMainBinding binding) {
        this.registryController = new RegistryController(binding.getMainActivity().getApplicationContext());
       this.consumptionRegistryRecyclerAdapter = new ConsumptionRegistryRecyclerAdapter(this.registryController.getConsumptionRegistries());
        this.layoutManager = new LinearLayoutManager(binding.getMainActivity().getApplicationContext());
        this.context = binding.getMainActivity().getApplicationContext();
        this.binding = binding;
        this.setForm();
        this.setVehicleKms();
        this.setConsumptionRegistryResView();
    }

    private void setVehicleKms() {
        this.binding.vehicleKms.setText(this.registryController.getPreviousVehicleKms() + context.getString(R.string.length_unit));
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
            this.registryController.setPreviousVehicleKms(this.formattedValue(this.binding.currentVehicleKms.getText().toString()));
            this.binding.vehicleKms.setText(this.registryController.getPreviousVehicleKms() + context.getString(R.string.length_unit));
            this.consumptionRegistryRecyclerAdapter.notifyDataSetChanged();
            this.clearForm();
        }
    }

    private void clearForm() {
        this.binding.fuelLoaded.requestFocus();
        this.binding.fuelLoaded.getText().clear();
        this.binding.currentVehicleKms.getText().clear();
    }

    private boolean isRegisterValid() {
        if (String.valueOf(this.binding.fuelLoaded.getText()).equals("")) {
            Toast.makeText(this.context, "Te faltó la gasolina ", Toast.LENGTH_SHORT).show();
            this.binding.fuelLoaded.requestFocus();
            return false;
        }
        if (String.valueOf(this.binding.currentVehicleKms.getText()).equals("")) {
            Toast.makeText(this.context, "Te faltó el kilometraje ", Toast.LENGTH_SHORT).show();
            this.binding.currentVehicleKms.requestFocus();//TODO Programar que el teclado no se esconda
            return false;
        }
        return true;
    }

    private float formattedValue(String value) {
        return (float) Math.round(Float.parseFloat(value) * 1000) / 1000;
    }

    private void setConsumptionRegistryResView() {
       this.layoutManager.setReverseLayout(true);
        //this.layoutManager.scrollToPosition(0);
        //this.layoutManager.scrollToPosition(this.registryController.getConsumptionRegistries().size()-1);
        this.binding.consumptionRegistryResView.setLayoutManager(this.layoutManager);
        this.binding.consumptionRegistryResView.setAdapter(this.consumptionRegistryRecyclerAdapter);
        this.binding.consumptionRegistryResView.scrollToPosition(this.registryController.getConsumptionRegistries().size()-1);
    }
}
