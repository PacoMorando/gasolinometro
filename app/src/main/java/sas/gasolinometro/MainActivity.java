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
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setDataBinding();
        this.setInputManager();
        this.setContentView(binding.getRoot());
        this.setConsumptionRegistryResView();
        this.binding.fuelLoaded.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_FOCUS)) || (i == EditorInfo.IME_ACTION_NEXT)) {
                    System.out.println("FOCUS pressed " + textView.getText());
                }
                Toast.makeText(getApplicationContext(), "FOCUS pressed " + textView.getText() + " i = " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        this.binding.distance.setOnEditorActionListener((textView, i, keyEvent) -> {
            if ((keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (i == EditorInfo.IME_ACTION_DONE)) {
                System.out.println("Enter pressed " + textView.getText());
            }
            Toast.makeText(getApplicationContext(), "Enter pressed " + textView.getText() + " i = " + i, Toast.LENGTH_SHORT).show();
            this.createRegister();
            return false;
        });
    }

    private void createRegister() {

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
        ConsumptionRegistryRecyclerAdapter consumptionRegistryRecyclerAdapter = new ConsumptionRegistryRecyclerAdapter();
        this.binding.consumptionRegistryResView.setLayoutManager(new LinearLayoutManager(this));
        this.binding.consumptionRegistryResView.setAdapter(consumptionRegistryRecyclerAdapter);
    }

    public void showInput() {
        //imm.showSoftInput(binding.fuelLoaded, InputMethodManager.SHOW_IMPLICIT);
        //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}