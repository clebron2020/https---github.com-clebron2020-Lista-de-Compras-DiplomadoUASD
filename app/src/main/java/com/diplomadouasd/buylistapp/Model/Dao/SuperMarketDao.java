package com.diplomadouasd.buylistapp.Model.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.diplomadouasd.buylistapp.Model.Entities.SuperMarket;

import java.util.List;
@Dao
public interface SuperMarketDao {
    @Query("SELECT * FROM SuperMarket order by IsFavorite DESC")
    LiveData<List<SuperMarket>> getAllSuperMarket();
    @Query("SELECT * FROM SuperMarket where SuperMarketId=:id")
    LiveData<SuperMarket> getSuperMarket(int id);

    @Query("SELECT * FROM SuperMarket order by IsFavorite DESC")
    List<SuperMarket> getAllSuperMarketExt();

    @Insert
    void AddNewSuperMarket(SuperMarket... item);
    @Delete
    void DeleteSuperMarket(SuperMarket... item);
    @Update
    void UpdateSuperMarket(SuperMarket... item);
}
