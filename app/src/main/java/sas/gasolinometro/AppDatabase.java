package sas.gasolinometro;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {ConsumptionRegister.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ConsumptionRegistryDAO consumptionRegistryDAO();
}
