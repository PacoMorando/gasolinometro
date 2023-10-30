package sas.gasolinometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import sas.gasolinometro.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public String textMessage = "testBinding TEXTO PARA VER SI JALA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //this.textMessage = getResources().getDisplayMetrics().toString() + getResources() + " DATA BINDING";
        binding.textDef.setText(getResources().getDisplayMetrics().toString() + getResources());/**/
    }

    public String getMessage(){
        return "Mensaje de funcion";
    }
}