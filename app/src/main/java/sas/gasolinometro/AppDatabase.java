package sas.gasolinometro;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {ConsumptionRegister.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract ConsumptionRegistryDAO consumptionRegistryDAO();
}
