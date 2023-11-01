package sas.gasolinometro;

public class ConsumptionRegister {
    private float lastLoad;
    private float lastVehicleKms;
    private float currentVehicleKms;
    private String registerDate;
    private String registerHour;
    private float consumption;
    private float kmsTraveled;

    public ConsumptionRegister(float lastLoad, float currentVehicleKms, float lastVehicleKms) {
        this.lastLoad = lastLoad;
        this.lastVehicleKms = lastVehicleKms;
        this.currentVehicleKms = currentVehicleKms;
        this.consumption = (this.currentVehicleKms - this.lastVehicleKms) / this.lastLoad;
    }

    public String getLastLoad() {
        return String.valueOf(this.lastLoad);
    }

    public String getCurrentVehicleKms() {
        return "Total: " + String.valueOf(this.currentVehicleKms) + " kms";
    }

    public String getRegisterDate() {
        return "31/10/2023";//TODO fatla programar
    }

    public String getRegisterHour() {
        return "lun 07:04 p.m."; //TODO falta programar
    }

    public String getConsumption() {
        return String.valueOf(this.consumption);
    }

    public String getKmsTraveled() {
        return String.valueOf(this.currentVehicleKms - this.lastVehicleKms);
    }
}