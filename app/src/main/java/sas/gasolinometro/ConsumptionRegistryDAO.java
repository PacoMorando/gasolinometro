package sas.gasolinometro;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface ConsumptionRegistryDAO {
    @Query("SELECT * FROM ConsumptionRegister")
    List<ConsumptionRegister> getAll();

    @Insert
    void insert(ConsumptionRegister consumptionRegister);

}
