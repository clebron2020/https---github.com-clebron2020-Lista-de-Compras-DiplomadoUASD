package com.diplomadouasd.buylistapp.Model.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.diplomadouasd.buylistapp.Model.Entities.Product;
import java.util.List;
@Dao
public interface ProductDao {
    @Query("SELECT p.productId," +
            "p.GenerateBySystem," +
            "p.ProductCatId," +
            "p.Description," +
            "pc.Description as ProductCategoryDesc," +
            "p.ImageProduct,  " +
            "Exist "+
            "FROM Product p join ProductCategory pc on p.ProductCatId = pc.ProductCatId " +
            "order by p.ProductCatId asc")
    LiveData<List<Product>> getAllProducts();
    @Query("SELECT * FROM Product where ProductId=:id")
    LiveData<Product> getProduct(int id);

    @Query("SELECT p.productId," +
            "p.GenerateBySystem," +
            "p.ProductCatId," +
            "p.Description," +
            "pc.Description as ProductCategoryDesc," +
            "p.ImageProduct,  " +
            "ifnull(bd.ProductId,0) as Exist "+
            "FROM Product p join ProductCategory pc on p.ProductCatId = pc.ProductCatId " +
            "left join buylistdetail bd on bd.ProductId == p.productId and bd.BuyListId=:Id "+
            "order by p.ProductCatId asc")
    LiveData<List<Product>> getAllProductsByBuyList(Integer Id);

    @Insert
    void AddNewProduct(Product... item);
    @Delete
    void DeleteProduct(Product... item);
    @Update
    void UpdateProduct(Product... item);
}

