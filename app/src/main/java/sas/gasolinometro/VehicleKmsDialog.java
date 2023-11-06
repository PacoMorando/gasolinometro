package sas.gasolinometro;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class VehicleKmsDialog extends AppCompatDialogFragment {
    private final TextView vehicleKms;
    private final TextInputEditText currentVehicleKms;
    private final RegistryController registryController;

    //TODO Refactor by adding an interface to prevent a long parameter list
    public VehicleKmsDialog(TextView vehicleKms, TextInputEditText currentVehicleKms, RegistryController registryController) {
        this.vehicleKms = vehicleKms;
        this.currentVehicleKms = currentVehicleKms;
        this.registryController = registryController;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.vehicle_kms_dialog, null);
        TextInputEditText setVehicleKms = view.findViewById(R.id.set_vehicle_kms);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity());
        builder.setNeutralButton(getString(R.string.dialog_neutral), (dialogInterface, i) -> {
                })
                .setPositiveButton(R.string.dialog_ok, (dialogInterface, i) -> {
                    System.out.println(setVehicleKms.getText());
                    this.setVehicleKms(String.valueOf(setVehicleKms.getText()));
                    this.setCurrentVehicleInputHelper();
                })
                .setView(view);
        return builder.create();
    }

    private void setVehicleKms(String text) {
        this.vehicleKms.setText(text + getString(R.string.length_unit));
        this.registryController.setPreviousVehicleKms(Float.valueOf(text));
    }

    private void setCurrentVehicleInputHelper() { //TODO Refactor by adding an interface to prevent repeat this code
        if (this.registryController.getPreviousVehicleKms() >= 1000) {
            this.currentVehicleKms.setText(String.valueOf((int) this.registryController.getPreviousVehicleKms() / 1000));
            this.currentVehicleKms.setSelection(this.currentVehicleKms.getText().length());
        } else {
            this.currentVehicleKms.getText().clear();
        }
    }
}
