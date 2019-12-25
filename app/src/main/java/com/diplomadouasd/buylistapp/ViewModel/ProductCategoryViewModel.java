package com.diplomadouasd.buylistapp.ViewModel;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.diplomadouasd.buylistapp.Model.AppDataBase;
import com.diplomadouasd.buylistapp.Model.Dao.ProductCategoryDao;
import com.diplomadouasd.buylistapp.Model.Entities.BuyList;
import com.diplomadouasd.buylistapp.Model.Entities.ProductCategory;
import com.diplomadouasd.buylistapp.View.CreateBuyList;
import com.diplomadouasd.buylistapp.View.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryViewModel extends AndroidViewModel
{
    private LiveData<List<com.diplomadouasd.buylistapp.Model.Entities.ProductCategory>> ProductCategory;
    private ProductCategoryDao db;
    private ArrayList<ProductCategory> productCategoriesList;
    Products _context;
    CreateBuyList _context2;

    public ProductCategoryViewModel(@NonNull Application application) {
        super(application);
        db = AppDataBase.getInstance(application).ProductCategoryDao();
        ProductCategory = db.getAllProductCategory();
    }

    public LiveData<List<ProductCategory>> getProductCategoryList() {
        return ProductCategory;
    }

    public void getCategoriesAsync(Products vProducts){
         _context = vProducts;
         new getProductsCategoriesAsyncTask(db).execute();
    }

    public void getCategoriesAsync(CreateBuyList vBuyList){
        _context2 = vBuyList;
        new getProductsCategoriesAsyncTask2(db).execute();
    }

    public void AddNewProductCategory(ProductCategory item)
    {
        new insertAsyncTask(db).execute(item);
    }

    public void UpdateProductCategory(ProductCategory item)
    {
        new updateAsyncTask(db).execute(item);
    }

    public void DeleteProductCategory(ProductCategory item)
    {
        new deleteAsyncTask(db).execute(item);
    }

    private static class insertAsyncTask extends AsyncTask<ProductCategory, Void, Void> {

        private ProductCategoryDao asyncTaskDao;

        insertAsyncTask(ProductCategoryDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(ProductCategory... SuperMarkets) {
            asyncTaskDao.AddNewProductCategory(SuperMarkets);
            return null;
        }
    }

    private class getProductsCategoriesAsyncTask extends AsyncTask<Void,Void,List<ProductCategory>>
    {
        private ProductCategoryDao asyncTaskDao;

        getProductsCategoriesAsyncTask(ProductCategoryDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected java.util.List<com.diplomadouasd.buylistapp.Model.Entities.ProductCategory> doInBackground(Void... voids) {
            return asyncTaskDao.getCategories();
        }

        @Override
        protected void onPostExecute(java.util.List<com.diplomadouasd.buylistapp.Model.Entities.ProductCategory> productCategories) {
            super.onPostExecute(productCategories);
            _context.SetCategoryList(productCategories);
        }
    }

    private class getProductsCategoriesAsyncTask2 extends AsyncTask<Void,Void,List<ProductCategory>>
    {
        private ProductCategoryDao asyncTaskDao;

        getProductsCategoriesAsyncTask2(ProductCategoryDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected java.util.List<com.diplomadouasd.buylistapp.Model.Entities.ProductCategory> doInBackground(Void... voids) {
            return asyncTaskDao.getCategories();
        }

        @Override
        protected void onPostExecute(java.util.List<com.diplomadouasd.buylistapp.Model.Entities.ProductCategory> productCategories) {
            super.onPostExecute(productCategories);
            _context2.SetCategoryList(productCategories);
        }
    }

    private static class deleteAsyncTask extends AsyncTask<ProductCategory, Void, Void> {

        private ProductCategoryDao asyncTaskDao;

        deleteAsyncTask(ProductCategoryDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(ProductCategory... ProductCategorys) {
            asyncTaskDao.DeleteProductCategory(ProductCategorys);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<ProductCategory, Void, Void> {

        private ProductCategoryDao asyncTaskDao;

        updateAsyncTask(ProductCategoryDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(ProductCategory... ProductCategorys) {
            asyncTaskDao.UpdateProductCategory(ProductCategorys);
            return null;
        }
    }
}
