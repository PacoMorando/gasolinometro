package sas.gasolinometro;

import java.util.ArrayList;

public class ConsumptionRegister {
    private float lastLoad;
    private float vehicleKms;
    private String registerDate;
    private String registerHour;
    private float consumption;
    private float kmsTraveled;

    public ConsumptionRegister(float lastLoad, float vehicleKms) {
        this.lastLoad = lastLoad;
        this.vehicleKms = vehicleKms;
    }

    public String getLastLoad() {
        return String.valueOf(this.lastLoad);
    }

    public String getVehicleKms() {
        return String.valueOf(this.vehicleKms);
    }

    public String getRegisterDate() {
        return "31/10/2023";
    }

    public String getRegisterHour() {
        return "lun 07:04 p.m.";
    }

    public String getConsumption() {
        return "27.81";
    }

    public String getKmsTraveled() {
        return "250.1";
    }
}