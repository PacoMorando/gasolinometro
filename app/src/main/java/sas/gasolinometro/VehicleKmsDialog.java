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
    private final ViewUpdater viewUpdater;
    private final RegistryController registryController;

    public VehicleKmsDialog(ViewUpdater viewUpdater, RegistryController registryController) {
        this.viewUpdater = viewUpdater;
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
                })
                .setView(view);
        return builder.create();
    }

    private void setVehicleKms(String text) {
        this.registryController.setPreviousVehicleKms(Float.valueOf(text));
        this.viewUpdater.updateViews();
    }
}
