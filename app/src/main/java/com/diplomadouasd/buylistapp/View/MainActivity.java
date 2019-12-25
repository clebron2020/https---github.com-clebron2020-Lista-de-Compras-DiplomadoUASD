package com.diplomadouasd.buylistapp.View;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.diplomadouasd.buylistapp.Adapter.ProductCategoryAdapter;
import com.diplomadouasd.buylistapp.R;
import com.diplomadouasd.buylistapp.Utility.ConfigApp;
import com.diplomadouasd.buylistapp.ViewModel.ProductCategoryViewModel;
import com.diplomadouasd.buylistapp.ViewModel.ProductViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends BaseActivity {
    ConfigApp configApp;
    FloatingActionButton fabCreateList;
    MaterialButton btnSuperMarket,btnCategoriaProductos,btnProducts,btnMyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CoordinatorLayout relativeLayout = findViewById(R.id.LinearLayoutMain);
        relativeLayout.setBackground(getResources().getDrawable(R.drawable.createlistback));
        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        ProductCategoryViewModel productCategoryViewModel= ViewModelProviders.of(this).get(ProductCategoryViewModel.class);
        configApp = new ConfigApp(productCategoryViewModel,productViewModel,getApplicationContext());
        productCategoryViewModel.getProductCategoryList().observe(this, new Observer<List<com.diplomadouasd.buylistapp.Model.Entities.ProductCategory>>() {
            @Override
            public void onChanged(List<com.diplomadouasd.buylistapp.Model.Entities.ProductCategory> productCategories) {
                 if (productCategories.size()==0){
                     configApp.CreateProductCategories();
                     configApp.CreateProducts();
                 }
            }
        });

        fabCreateList = findViewById(R.id.fabCreateList);
        Drawable myDrawableButton = getResources().getDrawable(R.drawable.ic_add_circle_black_24dp);
        fabCreateList.setImageDrawable(myDrawableButton);
        InitializeView();
    }

    @Override
    protected void InitializeView() {
      actionBar.setTitle("Lista de Compras");
      btnSuperMarket = findViewById(R.id.btnSuperMarket);
      btnCategoriaProductos= findViewById(R.id.btnCategoriaProductos);
      btnProducts= findViewById(R.id.btnProducts);
      btnMyList= findViewById(R.id.btnMyList);
      SetEvents();
    }

    @Override
    protected void SetEvents() {
        fabCreateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToActivity(CreateBuyList.class);
            }
        });

        btnSuperMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToActivity(SuperMarket.class);
            }
        });

        btnCategoriaProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToActivity(ProductCategory.class);
            }
        });

        btnProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToActivity(Products.class);
            }
        });


        btnMyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToActivity(ViewListCreated.class);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.ActivityMenuRes = R.menu.main_menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int Id = item.getItemId();
        switch (Id)
        {
            case R.id.menu_About:
                GoToActivity(About.class);
                break;
            case R.id.menu_products:
                GoToActivity(Products.class);
                break;
            case R.id.menu_productsCategory:
                GoToActivity(ProductCategory.class);
                break;
            case R.id.menu_supermarket:
                GoToActivity(SuperMarket.class);
                break;
            case R.id.menu_settings:
                GoToActivity(Settings.class);
                break;
            case R.id.menu_createlist:
                GoToActivity(CreateBuyList.class);
                break;
            case R.id.menu_myList:
                GoToActivity(ViewListCreated.class);
                break;
        }
        return true;
    }
}
