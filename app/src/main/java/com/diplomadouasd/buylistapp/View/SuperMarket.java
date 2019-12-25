package com.diplomadouasd.buylistapp.View;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.diplomadouasd.buylistapp.Adapter.SuperMarketAdapter;
import com.diplomadouasd.buylistapp.R;
import com.diplomadouasd.buylistapp.Utility.Utilities;
import com.diplomadouasd.buylistapp.ViewModel.SuperMarketViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import java.util.List;

public class SuperMarket extends BaseActivity {
    SuperMarketAdapter adapter;
    GridView lvSuperMarket;
    FloatingActionButton fabCreateSuperMarket;
    public SuperMarketViewModel superMarketViewModel;
    TextInputEditText txtSuperMarketName;
    MaterialButton btnAdd;
    Utilities utils;
    Context context =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_market);
        InitializeView();
        fabCreateSuperMarket = findViewById(R.id.fabCreateSuperMarket);
        Drawable myDrawableButton = getResources().getDrawable(R.drawable.ic_add_circle_black_24dp);
        fabCreateSuperMarket.setImageDrawable(myDrawableButton);
        fabCreateSuperMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowForm(null);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActivityMenuRes = R.menu.crud_menu;
        super.onCreateOptionsMenu(menu);
        MenuItem SearchItem = menu.findItem(R.id.menu_search);
            SearchView searchView = (SearchView)SearchItem.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return true;
                }
            });

     return true;
    }

    @Override
    protected void InitializeView() {
        lvSuperMarket = findViewById(R.id.lvSuperMarket);
        txtSuperMarketName = findViewById(R.id.txtSuperMarketName);
        btnAdd = findViewById(R.id.btnAdd);
        actionBar.setTitle("Lista de Super Mercados");
        superMarketViewModel = ViewModelProviders.of(this).get(SuperMarketViewModel.class);
        superMarketViewModel.getSuperMarketList().observe(this, new Observer<List<com.diplomadouasd.buylistapp.Model.Entities.SuperMarket>>() {
            @Override
            public void onChanged(List<com.diplomadouasd.buylistapp.Model.Entities.SuperMarket> superMarkets) {
                adapter = new SuperMarketAdapter(context,superMarkets);
                lvSuperMarket.setAdapter(adapter);
                TextView tvTotal = findViewById(R.id.tvTotal);
                tvTotal.setText(adapter.getCount()+ " Items");
            }
        });

        superMarketViewModel.getSuperMarketItem().observe(this, new Observer<com.diplomadouasd.buylistapp.Model.Entities.SuperMarket>() {
            @Override
            public void onChanged(com.diplomadouasd.buylistapp.Model.Entities.SuperMarket superMarket) {
                if (superMarket!=null)
                   ShowForm(superMarket);
            }
        });

        utils = new Utilities(this);
        SetEvents();
    }

    public void ShowForm(final com.diplomadouasd.buylistapp.Model.Entities.SuperMarket itemSuperMarket){
        String MsgDlg = "Crear Super Mercado";
        final View viewForm = myLayouInflater(R.layout.frm_super_market);
        final TextInputEditText  txtSuperMarketName = viewForm.findViewById(R.id.txtSuperMarketName);
        if (itemSuperMarket!= null) {
            txtSuperMarketName.setText(itemSuperMarket.getName());
            MsgDlg = "Editar Super Mercado";
        }

        viewForm.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.CurrentDialog.cancel();
            }
        });

        viewForm.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = txtSuperMarketName.getText().toString().trim();
                Boolean hasError = false;
                if (TextUtils.isEmpty(Name))
                {
                    txtSuperMarketName.setError("Campo requerido");
                    txtSuperMarketName.requestFocus();
                    hasError = true;
                }

                if (!hasError) {
                    com.diplomadouasd.buylistapp.Model.Entities.SuperMarket item = new com.diplomadouasd.buylistapp.Model.Entities.SuperMarket(txtSuperMarketName.getText().toString(),false);
                    if (itemSuperMarket==null)
                        superMarketViewModel.AddNewSuperMarket(item);
                    else {
                        itemSuperMarket.setName(txtSuperMarketName.getText().toString());
                        superMarketViewModel.UpdateSuperMarket(itemSuperMarket);
                    }

                    utils.CurrentDialog.dismiss();
                }
            }
        });
                
        utils.ModalPopup(MsgDlg,"",viewForm,null,null);
    }

    @Override
    protected void SetEvents() {
        lvSuperMarket.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final com.diplomadouasd.buylistapp.Model.Entities.SuperMarket Item = adapter.getItem(position);
                utils.DialogConfirm("", "Esta seguro de eliminar el super mercado?", Utilities.DialogType.Warning, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                          superMarketViewModel.DeleteSuperMarket(Item);
                    }
                },null);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int Id = item.getItemId();
        switch (Id)
        {
            case R.id.menu_add:

                break;
            case R.id.menu_products:
                break;
            case R.id.menu_productsCategory:
                break;
            case R.id.menu_settings:
                break;
        }
        return true;
    }
}
