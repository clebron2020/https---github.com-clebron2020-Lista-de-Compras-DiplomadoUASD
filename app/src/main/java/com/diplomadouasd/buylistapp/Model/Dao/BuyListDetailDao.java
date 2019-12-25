package com.diplomadouasd.buylistapp.Model.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.diplomadouasd.buylistapp.Model.Entities.BuyListDetail;

import java.util.List;

@Dao
public interface BuyListDetailDao {
    @Query("SELECT d.BuyListDetailId," +
            "d.BuyListId," +
            "d.ProductId," +
            "d.Quantity," +
            "d.UnitPrice," +
            "d.Incar," +
            "p.ProductCatId," +
            "p.Description ProductDesc,"+
            "pc.Description ProductCatDesc,"+
            "p.GenerateBySystem GenerateBySystem"+
            " FROM BuyListDetail d join product p on d.ProductId = p.productId join productcategory pc on p.ProductCatId = pc.ProductCatId where d.BuyListId=:Id" +
            " order by p.ProductCatId asc")
    LiveData<List<BuyListDetail>> getAllBuyListDetail(Integer Id);
    @Insert
    void AddNewBuyListDetail(BuyListDetail... item);
    @Delete
    void DeleteBuyListDetail(BuyListDetail... item);
    @Update
    void UpdateBuyListDetail(BuyListDetail... item);

    @Query("SELECT d.BuyListDetailId," +
            "d.BuyListId," +
            "d.ProductId," +
            "d.Quantity," +
            "d.UnitPrice," +
            "d.Incar," +
            "p.ProductCatId," +
            "p.Description ProductDesc,"+
            "pc.Description ProductCatDesc,"+
            "p.GenerateBySystem GenerateBySystem"+
            " FROM BuyListDetail d join product p on d.ProductId = p.productId join productcategory pc on p.ProductCatId = pc.ProductCatId  and p.ProductId =:Id order by p.ProductCatId asc")
    BuyListDetail getDetailByProductId(Integer Id);
}
