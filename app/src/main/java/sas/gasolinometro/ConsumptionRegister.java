package sas.gasolinometro;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "consumption_registry")
public class ConsumptionRegister {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private float gasLoaded;
    private float previousVehicleKms;
    private float currentVehicleKms;
    private Date date;
    private float kmsTraveled;
    private float consumption;

    public ConsumptionRegister() {
    }

    private ConsumptionRegister(float gasLoaded, float currentVehicleKms, float previousVehicleKms) {
        this.gasLoaded = gasLoaded;
        this.previousVehicleKms = previousVehicleKms;
        this.currentVehicleKms = currentVehicleKms;
        this.date = new Date();
        this.kmsTraveled = this.currentVehicleKms - previousVehicleKms;
        this.consumption = (this.currentVehicleKms - previousVehicleKms) / this.gasLoaded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGasLoaded() {
        return gasLoaded;
    }

    public void setGasLoaded(float gasLoaded) {
        this.gasLoaded = gasLoaded;
    }

    public float getPreviousVehicleKms() {
        return previousVehicleKms;
    }

    public void setPreviousVehicleKms(float previousVehicleKms) {
        this.previousVehicleKms = previousVehicleKms;
    }

    public float getCurrentVehicleKms() {
        return currentVehicleKms;
    }

    public void setCurrentVehicleKms(float currentVehicleKms) {
        this.currentVehicleKms = currentVehicleKms;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getKmsTraveled() {
        return kmsTraveled;
    }

    public void setKmsTraveled(float kmsTraveled) {
        this.kmsTraveled = kmsTraveled;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public static class Builder {

        private float gasLoaded;
        private float previousVehicleKms;
        private float currentVehicleKms;

        public Builder setGasLoaded(float gasLoaded) {
            this.gasLoaded = gasLoaded;
            return this;
        }

        public Builder setPreviousVehicleKms(float previousVehicleKms) {
            this.previousVehicleKms = previousVehicleKms;
            return this;
        }

        public Builder setCurrentVehicleKms(float currentVehicleKms) {
            this.currentVehicleKms = currentVehicleKms;
            return this;
        }

        public ConsumptionRegister build() {
            return new ConsumptionRegister(this.gasLoaded, this.currentVehicleKms, this.previousVehicleKms);
        }
    }
}