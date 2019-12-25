package com.diplomadouasd.buylistapp.Model.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.diplomadouasd.buylistapp.Model.Entities.MetricUnit;
import java.util.List;

@Dao
public interface MetricUnitDao {
    @Query("SELECT * FROM MetricUnit")
    LiveData<List<MetricUnit>> getAllMetricUnit();
    @Insert
    void AddNewMetricUnit(MetricUnit... item);
}
