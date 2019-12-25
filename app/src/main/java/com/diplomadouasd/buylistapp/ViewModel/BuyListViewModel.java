package com.diplomadouasd.buylistapp.ViewModel;
import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.diplomadouasd.buylistapp.Model.AppDataBase;
import com.diplomadouasd.buylistapp.Model.Dao.BuyListDao;
import com.diplomadouasd.buylistapp.Model.Dao.BuyListDetailDao;
import com.diplomadouasd.buylistapp.Model.Entities.BuyList;
import com.diplomadouasd.buylistapp.Model.Entities.BuyListDetail;
import com.diplomadouasd.buylistapp.View.CreateBuyList;
import com.diplomadouasd.buylistapp.View.ViewListCreated;
import java.util.List;

public class BuyListViewModel extends AndroidViewModel {
    private LiveData<List<BuyList>> BuyList;
    private List<BuyList> BuyListExt;
    private LiveData<List<BuyListDetail>> BuyDetailList;
    private BuyListDao db;
    private BuyListDetailDao dbDetail;
    private CreateBuyList context;
    private ViewListCreated viewListCreated;
    public boolean _InCar;

    public BuyListViewModel(@NonNull Application application) {
        super(application);
        db = AppDataBase.getInstance(application).BuyListDao();
        dbDetail = AppDataBase.getInstance(application).BuyListDetailDao();
        BuyList = db.getAllBuyList();
    }

    public LiveData<List<BuyList>> getBuyList() {
        return BuyList;
    }

    public LiveData<List<BuyList>> getBuyListCreated() {
        return db.getAllBuyList();
    }

    public void getLastBuyList(CreateBuyList _context) {
        context = _context;
        new GetAllBuyListAsync(db).execute();
    }

    public LiveData<List<BuyListDetail>> getBuyDetailList(Integer BuyListId) {
        LiveData<List<BuyListDetail>>  result = dbDetail.getAllBuyListDetail(BuyListId);
        return result;
    }

    public void AddNewBuyList(BuyList item)
    {
        new insertAsyncTask(db).execute(item);
    }

    public void UpdateBuyList(BuyList item)
    {
        new updateListAsyncTask(db).execute(item);
    }

    public void UpdateBuyListDetail(BuyListDetail item){
        new updateAsyncTask(dbDetail).execute(item);
    }

    public void AddDetailItems(BuyListDetail item)
    {
        new insertDetailAsyncTask(dbDetail).execute(item);
    }

    public void getDetailByProductId(int Id,CreateBuyList _context){
       context = _context;
       new getDetailByProductIdAsyncTask(dbDetail).execute(Id);
    }

    private class GetAllBuyListAsync extends  AsyncTask<Void,Void,List<BuyList>>{
        private BuyListDao asyncTaskDao;

        public GetAllBuyListAsync(BuyListDao asyncTaskDao) {
            this.asyncTaskDao = asyncTaskDao;
        }

        @Override
        protected List<com.diplomadouasd.buylistapp.Model.Entities.BuyList> doInBackground(Void... voids) {
            return asyncTaskDao.getAllBuyListEx();
        }

        @Override
        protected void onPostExecute(List<com.diplomadouasd.buylistapp.Model.Entities.BuyList> buyLists) {
            super.onPostExecute(buyLists);
            Integer TotalRecord = buyLists.size();
            int BuyListId = TotalRecord == 0 ? 1 : buyLists.get(TotalRecord - 1).getBuyListId() + 1;
            context.SetBuyListId(BuyListId);
        }
    }

    private class getDetailByProductIdAsyncTask extends AsyncTask<Integer,Void,BuyListDetail>
    {
        private BuyListDetailDao asyncTaskDao;

        public getDetailByProductIdAsyncTask(BuyListDetailDao asyncTaskDao) {
            this.asyncTaskDao = asyncTaskDao;
        }

        @Override
        protected void onPostExecute(BuyListDetail buyListDetail) {
            super.onPostExecute(buyListDetail);
            buyListDetail.setInCar(_InCar);
            context.SetItemInCar(buyListDetail);
        }

        @Override
        protected BuyListDetail doInBackground(Integer... Id) {
            return asyncTaskDao.getDetailByProductId(Id[0]);
        }
    }

    private class insertDetailAsyncTask extends AsyncTask<BuyListDetail, Void, Void>
    {
        private BuyListDetailDao asyncTaskDao;

        public insertDetailAsyncTask(BuyListDetailDao asyncTaskDao) {
            this.asyncTaskDao = asyncTaskDao;
        }

        @Override
        protected Void doInBackground(BuyListDetail... Item) {
            asyncTaskDao.AddNewBuyListDetail(Item);
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<BuyList, Void, Void> {

        private BuyListDao asyncTaskDao;

        insertAsyncTask(BuyListDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(BuyList... BuyLists) {
            asyncTaskDao.AddNewBuyList(BuyLists);
            return null;
        }

    }

    private static class updateAsyncTask extends AsyncTask<BuyListDetail, Void, Void> {

        private BuyListDetailDao asyncTaskDao;

        updateAsyncTask(BuyListDetailDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(BuyListDetail... BuyListDetails) {
            asyncTaskDao.UpdateBuyListDetail(BuyListDetails);
            return null;
        }
    }

    private static class updateListAsyncTask extends AsyncTask<BuyList, Void, Void> {
        private BuyListDao asyncTaskDao;
        updateListAsyncTask(BuyListDao dao){
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(BuyList... BuyLists) {
            asyncTaskDao.UpdateBuyList(BuyLists);
            return null;
        }
    }
}