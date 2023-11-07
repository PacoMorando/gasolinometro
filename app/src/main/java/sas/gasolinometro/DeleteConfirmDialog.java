package sas.gasolinometro;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DeleteConfirmDialog extends AppCompatDialogFragment {
    private final RegisterDeleter registerDeleter;
    private final ConsumptionRegistryRecyclerAdapter.ViewHolder holder;
    public DeleteConfirmDialog(RegisterDeleter registerDeleter, ConsumptionRegistryRecyclerAdapter.ViewHolder holder) {
        this.registerDeleter = registerDeleter;
        this.holder = holder;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity());
        builder.setTitle("Do you want to delete this register?")
                .setMessage("Date:" + this.holder.getDate() + "\n" +
                            "Consumption " + this.holder.getConsumption() + "\n" +
                             this.holder.getVehicleKms())
                .setNeutralButton(getString(R.string.dialog_neutral), (dialogInterface, i) -> {
                })
                .setPositiveButton(R.string.dialog_ok, (dialogInterface, i) -> {
                    this.registerDeleter.deleteRegister(holder.getAdapterPosition());
                });
        return builder.create();
    }

    public interface RegisterDeleter{
        void deleteRegister(int position);
    }
}
