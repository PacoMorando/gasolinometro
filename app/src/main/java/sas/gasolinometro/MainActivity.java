package sas.gasolinometro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        this.binding.setMainActivity(this);
        setContentView(binding.getRoot());
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        binding.fuelLoaded.requestFocus();

        imm.showSoftInput(binding.fuelLoaded, InputMethodManager.SHOW_FORCED);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        System.out.println("ON CREATE");

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("ON RESUME");
        //imm.showSoftInput(binding.fuelLoaded, InputMethodManager.SHOW_IMPLICIT);
        //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}