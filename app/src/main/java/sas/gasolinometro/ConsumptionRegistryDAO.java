package sas.gasolinometro;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ConsumptionRegistryDAO {
    @Query("SELECT * FROM consumption_registry ORDER BY date DESC")
    List<ConsumptionRegister> getAll();

    @Insert
    void insert(ConsumptionRegister consumptionRegister);

    @Query("SELECT * FROM consumption_registry ORDER BY date DESC LIMIT 1")
    ConsumptionRegister getLatestRegister();

    @Delete
    void deleteRegister(ConsumptionRegister consumptionRegister);

}