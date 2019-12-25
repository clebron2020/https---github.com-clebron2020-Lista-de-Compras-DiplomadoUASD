package com.diplomadouasd.buylistapp.ViewModel;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.diplomadouasd.buylistapp.Model.AppDataBase;
import com.diplomadouasd.buylistapp.Model.Dao.SuperMarketDao;
import com.diplomadouasd.buylistapp.Model.Entities.SuperMarket;
import com.diplomadouasd.buylistapp.View.CreateBuyList;

import java.util.List;

public class SuperMarketViewModel extends AndroidViewModel {
    private LiveData<List<SuperMarket>> SuperMarketList;
    private MutableLiveData<SuperMarket> superMarketItem;
    private SuperMarketDao db;
    CreateBuyList context;
    public SuperMarketViewModel(@NonNull Application application) {
        super(application);
        db = AppDataBase.getInstance(application).SuperMarketDao();
        SuperMarketList = db.getAllSuperMarket();
        superMarketItem = new MutableLiveData<>();
    }

    public void UpdateSuperMarketUI(SuperMarket item){
        superMarketItem.setValue(item);
    }

    public LiveData<SuperMarket> getSuperMarketItem(){
        return superMarketItem;
    }


    public void getAllSuperMarket(CreateBuyList context){
        this.context= context;
        new getAllSuperMarketAsyncTask(db).execute();
    }

    public LiveData<List<SuperMarket>> getSuperMarketList() {
        return SuperMarketList;
    }

    public void AddNewSuperMarket(SuperMarket item)
    {
        new insertAsyncTask(db).execute(item);
    }

    public void UpdateSuperMarket(SuperMarket item)
    {
        new updateAsyncTask(db).execute(item);
    }

    public void DeleteSuperMarket(SuperMarket item)
    {
        new deleteAsyncTask(db).execute(item);
    }


    private class getAllSuperMarketAsyncTask extends  AsyncTask<Void,Void,List<SuperMarket>>{
        private SuperMarketDao asyncTaskDao;

        public getAllSuperMarketAsyncTask(SuperMarketDao asyncTaskDao) {
            this.asyncTaskDao = asyncTaskDao;
        }

        @Override
        protected void onPostExecute(List<SuperMarket> superMarkets) {
            super.onPostExecute(superMarkets);
            context.SetSuperMarketList(superMarkets);
        }

        @Override
        protected List<SuperMarket> doInBackground(Void... voids) {
            return this.asyncTaskDao.getAllSuperMarketExt();
        }
    }

    private static class insertAsyncTask extends AsyncTask<SuperMarket, Void, Void> {

        private SuperMarketDao asyncTaskDao;

        insertAsyncTask(SuperMarketDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(SuperMarket... SuperMarkets) {
            asyncTaskDao.AddNewSuperMarket(SuperMarkets);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<SuperMarket, Void, Void> {

        private SuperMarketDao asyncTaskDao;

        deleteAsyncTask(SuperMarketDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(SuperMarket... SuperMarkets) {
            asyncTaskDao.DeleteSuperMarket(SuperMarkets);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<SuperMarket, Void, Void> {

        private SuperMarketDao asyncTaskDao;

        updateAsyncTask(SuperMarketDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(SuperMarket... SuperMarkets) {
            asyncTaskDao.UpdateSuperMarket(SuperMarkets);
            return null;
        }
    }
}