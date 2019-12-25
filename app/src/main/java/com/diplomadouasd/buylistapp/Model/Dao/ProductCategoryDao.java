package com.diplomadouasd.buylistapp.Model.Dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.diplomadouasd.buylistapp.Model.Entities.ProductCategory;
import java.util.List;
@Dao
public interface ProductCategoryDao
{
    @Query("SELECT * FROM ProductCategory")
    LiveData<List<ProductCategory>> getAllProductCategory();

    @Query("SELECT * FROM ProductCategory")
    List<ProductCategory> getCategories();

    @Insert
    void AddNewProductCategory(ProductCategory... item);
    @Delete
    void DeleteProductCategory(ProductCategory... item);
    @Update
    void UpdateProductCategory(ProductCategory... item);
}
