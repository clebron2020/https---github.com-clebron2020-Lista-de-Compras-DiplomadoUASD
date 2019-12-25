package com.diplomadouasd.buylistapp.Model.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import com.diplomadouasd.buylistapp.Model.Entities.BuyList;
import java.util.List;

@Dao
public interface BuyListDao
{
    @Query("SELECT " +
            "b.BuyListId, " +
            "b.BuyList_CreattionDate, " +
            "b.BuyList_SuperMarketId, " +
            "s.Name as SuperMarket_Name, " +
            "b.BuyList_Total, " +
            "b.Finaly FROM BuyList b join SuperMarket s on b.BuyList_SuperMarketId = s.SuperMarketId")
    LiveData<List<BuyList>> getAllBuyList();

    @Query("SELECT " +
            "b.BuyListId, " +
            "b.BuyList_CreattionDate, " +
            "b.BuyList_SuperMarketId, " +
            "b.BuyList_Total," +
            "b.Finaly FROM BuyList b")
    List<BuyList> getAllBuyListEx();
    @Insert
    void AddNewBuyList(BuyList... item);
    @Delete
    void DeleteBuyList(BuyList... item);
    @Update
    void UpdateBuyList(BuyList... item);
}

