package sas.gasolinometro;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
public class ConsumptionRegister {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "gas_loaded")
    private final float gasLoaded;
    @ColumnInfo(name = "previous_vehicle_kms")
    public float previousVehicleKms;
    @ColumnInfo(name = "current_vehicle_kms")
    public float currentVehicleKms;
    @ColumnInfo(name = "date")
    public Date date;
    private final float kmsTraveled;
    private final float consumption;

    public ConsumptionRegister(float gasLoaded, float currentVehicleKms, float lastVehicleKms) {
        this.date = new Date();
        this.gasLoaded = gasLoaded;
        this.currentVehicleKms = currentVehicleKms;
        this.kmsTraveled = this.currentVehicleKms - lastVehicleKms;
        this.consumption = (this.currentVehicleKms - lastVehicleKms) / this.gasLoaded;
    }

    public float getGasLoaded() {
        return this.gasLoaded;
    }

    public float getCurrentVehicleKms() {
        return this.currentVehicleKms;
    }

    public float getConsumption() {
        return this.consumption;
    }

    public float getKmsTraveled() {
        return this.kmsTraveled;
    }

    public Date getDate() {
        return this.date;
    }
}