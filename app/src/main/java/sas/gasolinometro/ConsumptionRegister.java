package sas.gasolinometro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConsumptionRegister {
    private final float lastLoad;
   // private final float lastVehicleKms;

    private final float kmsTraveled;
    private final float currentVehicleKms;
    private final float consumption;
    private final Date date;

    public ConsumptionRegister(float lastLoad, float currentVehicleKms, float previousVehicleKms) { //TODO A ESTE LE VOY A PASAR EL DTO
        this.date = new Date();
        this.lastLoad = lastLoad;
        //this.lastVehicleKms = lastVehicleKms;
        this.currentVehicleKms = currentVehicleKms;
        this.kmsTraveled = this.currentVehicleKms - previousVehicleKms;
        this.consumption = (this.currentVehicleKms - previousVehicleKms) / this.lastLoad;
    }

    public String getLastLoad() {
        return this.lastLoad + "lts.";
    }

    public String getCurrentVehicleKms() {
        return "Total: " + this.currentVehicleKms + " kms";
    }

    public String getRegisterDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
        String formattedDate = dateFormat.format(this.date);
        System.out.println("Fecha: " + formattedDate);
        return formattedDate;//TODO fatla programar
    }

    public String getRegisterHour() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("E hh:mm a", new Locale("es", "ES"));
        String formattedTime = timeFormat.format(this.date);
        System.out.println("Hora: " + formattedTime);
        return formattedTime;
    }

    public String getConsumption() {
        return String.valueOf((float) Math.round(this.consumption * 1000) / 1000);
    }

    public String getKmsTraveled() {
        return String.valueOf((float) Math.round((this.kmsTraveled) * 100) / 100);
        //return String.valueOf((float) Math.round((this.currentVehicleKms - this.lastVehicleKms) * 100) / 100);
    }
}