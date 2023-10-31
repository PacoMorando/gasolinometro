package sas.gasolinometro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import sas.gasolinometro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setDataBinding();
        this.setInputManager();
        this.setContentView(binding.getRoot());
        ConsumptionRegistryRecyclerAdapter consumptionRegistryRecyclerAdapter = new ConsumptionRegistryRecyclerAdapter();
        this.binding.consumptionRegistryResView.setLayoutManager(new LinearLayoutManager(this));
        this.binding.consumptionRegistryResView.setAdapter(consumptionRegistryRecyclerAdapter);

    }

    private void setInputManager() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void setDataBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        this.binding.setMainActivity(this);
        binding.fuelLoaded.requestFocus();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public void showInput() {
        //imm.showSoftInput(binding.fuelLoaded, InputMethodManager.SHOW_IMPLICIT);
        //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}