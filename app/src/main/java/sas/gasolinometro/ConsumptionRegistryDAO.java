package sas.gasolinometro;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ConsumptionRegistryDAO {
    @Query("SELECT * FROM consumption_register")
    List<ConsumptionRegister> getAll();

    @Insert
    void insert(ConsumptionRegister consumptionRegister);

}
