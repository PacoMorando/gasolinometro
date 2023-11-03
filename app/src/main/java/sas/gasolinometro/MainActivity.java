package sas.gasolinometro;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import sas.gasolinometro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RegisterView registerView;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setDataBinding();
        this.registerView = new RegisterView(this.binding.fuelLoaded, this.binding.distance, getApplicationContext());
        this.setInputManager();
        this.setContentView(binding.getRoot());
        this.setConsumptionRegistryResView();
    }


    private void setDataBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        this.binding.setMainActivity(this);
        binding.fuelLoaded.requestFocus();
    }

    private void setInputManager() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void setConsumptionRegistryResView() {
        //this.registerView.setConsumptionRegistryResView();
        this.binding.consumptionRegistryResView.setLayoutManager(new LinearLayoutManager(this));
        this.binding.consumptionRegistryResView.setAdapter(this.registerView.getConsumptionRegistryRecyclerAdapter());
    }

    public void showInput() {
        //imm.showSoftInput(binding.fuelLoaded, InputMethodManager.SHOW_IMPLICIT);
        //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}