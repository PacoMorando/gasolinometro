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
        this.binding.setMainActivity(this);
        setContentView(binding.getRoot());
    }

}