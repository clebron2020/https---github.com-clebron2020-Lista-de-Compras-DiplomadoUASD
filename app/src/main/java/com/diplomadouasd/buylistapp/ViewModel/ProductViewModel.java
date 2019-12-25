package com.diplomadouasd.buylistapp.ViewModel;
import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.diplomadouasd.buylistapp.Model.AppDataBase;
import com.diplomadouasd.buylistapp.Model.Dao.ProductCategoryDao;
import com.diplomadouasd.buylistapp.Model.Dao.ProductDao;
import com.diplomadouasd.buylistapp.Model.Entities.BuyListDetail;
import com.diplomadouasd.buylistapp.Model.Entities.Product;
import com.diplomadouasd.buylistapp.Model.Entities.ProductCategory;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private LiveData<List<Product>> productLiveData;
    private ProductDao db;
    public ProductViewModel(@NonNull Application application) {
        super(application);
        db = AppDataBase.getInstance(application).ProductDao();
        productLiveData = db.getAllProducts();
    }

    public LiveData<List<Product>> GetProductList(boolean FromBuyList,Integer BuyListId){
        return FromBuyList?db.getAllProductsByBuyList(BuyListId):db.getAllProducts();
    }

    public void AddNewProduct(Product item)
    {
        new ProductViewModel.insertAsyncTask(db).execute(item);
    }

    public void UpdateProduct(Product item)
    {
        new ProductViewModel.updateAsyncTask(db).execute(item);
    }

    public void DeleteProduct(Product item)
    {
        new ProductViewModel.deleteAsyncTask(db).execute(item);
    }

    private static class insertAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao asyncTaskDao;

        insertAsyncTask(ProductDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Product... Products) {
            asyncTaskDao.AddNewProduct(Products);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao asyncTaskDao;

        deleteAsyncTask(ProductDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Product... Products) {
            asyncTaskDao.DeleteProduct(Products);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao asyncTaskDao;

        updateAsyncTask(ProductDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Product... Products) {
            asyncTaskDao.UpdateProduct(Products);
            return null;
        }
    }
}
