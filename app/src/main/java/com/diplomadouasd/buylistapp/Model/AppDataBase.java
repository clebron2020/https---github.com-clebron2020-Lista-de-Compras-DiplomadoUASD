package com.diplomadouasd.buylistapp.Model;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.diplomadouasd.buylistapp.Model.Dao.BuyListDao;
import com.diplomadouasd.buylistapp.Model.Dao.BuyListDetailDao;
import com.diplomadouasd.buylistapp.Model.Dao.ProductCategoryDao;
import com.diplomadouasd.buylistapp.Model.Dao.ProductDao;
import com.diplomadouasd.buylistapp.Model.Dao.SuperMarketDao;
import com.diplomadouasd.buylistapp.Model.Entities.BuyList;
import com.diplomadouasd.buylistapp.Model.Entities.BuyListDetail;
import com.diplomadouasd.buylistapp.Model.Entities.Product;
import com.diplomadouasd.buylistapp.Model.Entities.ProductCategory;
import com.diplomadouasd.buylistapp.Model.Entities.SuperMarket;

@Database(entities = {BuyList.class, BuyListDetail.class, SuperMarket.class, Product.class, ProductCategory.class}, version = 1, exportSchema = false)
public abstract class AppDataBase  extends RoomDatabase
{
    private static volatile AppDataBase INSTANCE;
    public abstract BuyListDao BuyListDao();
    public abstract BuyListDetailDao BuyListDetailDao();
    public abstract SuperMarketDao SuperMarketDao();
    public abstract ProductCategoryDao ProductCategoryDao();
    public abstract ProductDao ProductDao();

    public static AppDataBase getInstance(final Context context){
        if (INSTANCE==null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"BuyListApp.db").build();

        return
                INSTANCE;
    }
}

