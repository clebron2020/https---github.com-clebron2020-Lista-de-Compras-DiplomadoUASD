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
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.diplomadouasd.buylistapp.Adapter.ProductCategoryAdapter;
import com.diplomadouasd.buylistapp.R;
import com.diplomadouasd.buylistapp.Utility.Utilities;
import com.diplomadouasd.buylistapp.ViewModel.ProductCategoryViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import java.util.List;

public class ProductCategory extends BaseActivity {
    ProductCategoryAdapter adapter;
    GridView lvProductCategory;
    FloatingActionButton fabCreateProductCategory;
    public ProductCategoryViewModel productCategoryViewModel;
    TextInputEditText txtProductCategory;
    MaterialButton btnAdd;
    Utilities utils;
    Context context =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);
        InitializeView();
        fabCreateProductCategory = findViewById(R.id.fabCreateProductCategory);
        Drawable myDrawableButton = getResources().getDrawable(R.drawable.ic_add_circle_black_24dp);
        fabCreateProductCategory.setImageDrawable(myDrawableButton);
        fabCreateProductCategory.setOnClickListener(new View.OnClickListener() {
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
        lvProductCategory = findViewById(R.id.lvProductCategory);
        txtProductCategory = findViewById(R.id.txtSuperMarketName);
        btnAdd = findViewById(R.id.btnAdd);
        actionBar.setTitle("Lista de Categorias de productos");
        productCategoryViewModel = ViewModelProviders.of(this).get(ProductCategoryViewModel.class);
        productCategoryViewModel.getProductCategoryList().observe(this, new Observer<List<com.diplomadouasd.buylistapp.Model.Entities.ProductCategory>>() {
            @Override
            public void onChanged(List<com.diplomadouasd.buylistapp.Model.Entities.ProductCategory> productCategories) {
                adapter = new ProductCategoryAdapter(context,productCategories);
                lvProductCategory.setAdapter(adapter);
                TextView tvTotal = findViewById(R.id.tvTotal);
                tvTotal.setText(adapter.getCount()+ " Items");
            }
        });

        utils = new Utilities(this);
        SetEvents();
    }

    public void ShowForm(final com.diplomadouasd.buylistapp.Model.Entities.ProductCategory itemProductCategory){
        String MsgDlg = "Crear Categoria de productos";
        final View viewForm = myLayouInflater(R.layout.frm_product_category);
        final TextInputEditText  txtProductCategory = viewForm.findViewById(R.id.txtProductCategory);
        if (itemProductCategory!= null) {
            txtProductCategory.setText(itemProductCategory.getDescription());
            MsgDlg = "Editar Categoria de productos";
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
                String Name = txtProductCategory.getText().toString().trim();
                Boolean hasError = false;
                if (TextUtils.isEmpty(Name))
                {
                    txtProductCategory.setError("Campo requerido");
                    txtProductCategory.requestFocus();
                    hasError = true;
                }

                if (!hasError) {
                    com.diplomadouasd.buylistapp.Model.Entities.ProductCategory item = new com.diplomadouasd.buylistapp.Model.Entities.ProductCategory(txtProductCategory.getText().toString());
                    if (itemProductCategory==null)
                        productCategoryViewModel.AddNewProductCategory(item);
                    else {
                        itemProductCategory.setDescription(txtProductCategory.getText().toString());
                        productCategoryViewModel.UpdateProductCategory(itemProductCategory);
                    }

                    utils.CurrentDialog.dismiss();
                }
            }
        });

        utils.ModalPopup(MsgDlg,"",viewForm,null,null);
    }

    @Override
    protected void SetEvents() {
        lvProductCategory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final com.diplomadouasd.buylistapp.Model.Entities.ProductCategory Item = adapter.getItem(position);
                utils.DialogConfirm("", "Esta seguro de eliminar la categoria seleccionada?", Utilities.DialogType.Warning, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        productCategoryViewModel.DeleteProductCategory(Item);
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
