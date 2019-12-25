package com.diplomadouasd.buylistapp.View;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import com.diplomadouasd.buylistapp.Adapter.BuyListAdapter;
import com.diplomadouasd.buylistapp.Model.Entities.BuyList;
import com.diplomadouasd.buylistapp.R;
import com.diplomadouasd.buylistapp.ViewModel.BuyListViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewListCreated extends BaseActivity {
    BuyListAdapter adapter;
    ListView lvBuyListCreated;
    List<BuyList> buyLists = new ArrayList<>();
    BuyListViewModel buyListAppViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_created);
        InitializeView();
        buyListAppViewModel = ViewModelProviders.of(this).get(BuyListViewModel.class);
        buyListAppViewModel.getBuyListCreated().observe(this, new Observer<List<BuyList>>() {
            @Override
            public void onChanged(List<BuyList> buyLists) {
                adapter = new BuyListAdapter(getApplicationContext(),buyLists);
                lvBuyListCreated.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void InitializeView() {
        lvBuyListCreated = findViewById(R.id.lvBuyListCreated);
    }

    @Override
    protected void SetEvents() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
