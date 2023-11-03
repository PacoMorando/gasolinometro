package sas.gasolinometro;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RegisterView {
    private final EditText fuelLoaded;
    private final EditText distance;
    private final Context applicationContext;
    private final RegisterController registerController;



    public RegisterView(EditText fuelLoaded, EditText distance, Context applicationContext) {
        this.registerController = new RegisterController();
        this.fuelLoaded = fuelLoaded;
        this.distance = distance;
        this.applicationContext = applicationContext;
        this.setForm();
    }

    private void setForm() {
        this.distance.setOnEditorActionListener((textView, i, keyEvent) -> {
            this.createRegister();
            return false;
        });
    }

    private void createRegister() {
        if (this.isRegisterValid()) {
            this.registerController.createRegister(this.formattedValue(this.fuelLoaded.getText().toString()), this.formattedValue(this.distance.getText().toString()));
            this.clearForm();
        }
    }

    private void clearForm() {
        Toast.makeText(this.applicationContext, "GAS " + this.formattedValue(this.fuelLoaded.getText().toString()) + "L. / " + this.formattedValue(this.distance.getText().toString()) + "kms", Toast.LENGTH_SHORT).show();
        this.fuelLoaded.requestFocus();
        this.fuelLoaded.getText().clear();
        this.distance.getText().clear();
    }

    private boolean isRegisterValid() {
        if (String.valueOf(this.fuelLoaded.getText()).equals("")) {
            Toast.makeText(this.applicationContext, "Te faltó la gasolina ", Toast.LENGTH_SHORT).show();
            this.fuelLoaded.requestFocus();
            return false;
        }
        if (String.valueOf(this.distance.getText()).equals("")) {
            Toast.makeText(this.applicationContext, "Te faltó el kilometraje ", Toast.LENGTH_SHORT).show();
            this.distance.requestFocus();//TODO Programar que el teclado no se esconda
            return false;
        }
        return true;
    }

    private float formattedValue(String value) {
        return (float) Math.round(Float.parseFloat(value) * 1000) / 1000;
    }

    public ConsumptionRegistryRecyclerAdapter getConsumptionRegistryRecyclerAdapter() {
        return this.registerController.getConsumptionRegistryRecyclerAdapter();
    }

    public void setConsumptionRegistryResView() {}
}
