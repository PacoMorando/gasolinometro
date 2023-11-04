package sas.gasolinometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import sas.gasolinometro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setDataBinding();
        this.setContentView(binding.getRoot());
        RegistryView registryView = new RegistryView(binding, getApplicationContext());
        registryView.setConsumptionRegistryResView();
        this.setInputManager();
        //this.setConsumptionRegistryResView();
    }


    private void setDataBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        this.binding.setMainActivity(this);
        //binding.fuelLoaded.requestFocus();
    }

    private void setInputManager() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void setConsumptionRegistryResView() {
        //this.registerView.setConsumptionRegistryResView();
        /*this.binding.consumptionRegistryResView.setLayoutManager(new LinearLayoutManager(this));
        this.binding.consumptionRegistryResView.setAdapter(this.registerView.getConsumptionRegistryRecyclerAdapter());*/
    }

    public void showInput() {
        //imm.showSoftInput(binding.fuelLoaded, InputMethodManager.SHOW_IMPLICIT);
        //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}