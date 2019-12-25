package com.diplomadouasd.buylistapp.View;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.diplomadouasd.buylistapp.Adapter.ProductAdapter;
import com.diplomadouasd.buylistapp.Model.Entities.Header;
import com.diplomadouasd.buylistapp.Model.Entities.ItemRow;
import com.diplomadouasd.buylistapp.Model.Entities.Product;
import com.diplomadouasd.buylistapp.Model.Entities.ProductCategory;
import com.diplomadouasd.buylistapp.Model.Entities.ProductGrouping;
import com.diplomadouasd.buylistapp.R;
import com.diplomadouasd.buylistapp.Utility.Utilities;
import com.diplomadouasd.buylistapp.ViewModel.ProductCategoryViewModel;
import com.diplomadouasd.buylistapp.ViewModel.ProductViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import java.util.ArrayList;
import java.util.List;

public class Products extends BaseActivity {
    ProductAdapter adapter;
    FloatingActionButton fabCreateProduct;
    ListView lvProducts;
    MaterialBetterSpinner spProductCategory;
    List<ProductCategory> productCategoryList = new ArrayList<>();
    ArrayList<String> productCategoryListArray = new ArrayList<>();
    ArrayAdapter<String> categoryArrayAdapter;
    public ProductViewModel productViewModel;
    public ProductCategoryViewModel productCategoryViewModel;
    TextInputEditText txtProductDesc;
    MaterialButton btnAdd;
    Utilities utils;
    Context context =this;
    List<Product> SelectedProductList;
    List<Product> AllProducts;
    private Gson oJSONSerializer = new Gson();
    private Boolean IsProduct;
    private Boolean FromByList;
    private Integer BuyListId;

    private Product FindProductById(int ProductId){
        Product result = null;
        for (Product p: AllProducts) {
            if (p.getProductId()==ProductId){
               result = new Product(p.getProductId(), p.getProductCatId(),p.getDescription());
               break;
            }
        }
        return result;
    }

    public void AddItemInBuyList(int itemId){
        Product  item =  FindProductById(itemId);
        SelectedProductList.add(item);
    }

    public void DeleteItemInBuyList(int itemId){
        if (SelectedProductList.size()>0) {
            for (int x = 0; x <= SelectedProductList.size(); x++) {
                if (SelectedProductList.get(x).getProductId() == itemId) {
                    SelectedProductList.remove(x);
                    break;
                }
            }
        }
    }

    public void SetCategoryList(List<ProductCategory> _productCategoryList){
        productCategoryList = _productCategoryList;
        for (ProductCategory p : productCategoryList)
            productCategoryListArray.add(p.getDescription());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        InitializeView();

        FromByList = getIntent().getBooleanExtra("FromByList",false);
        BuyListId = getIntent().getIntExtra("BuyListId",-1);
        SelectedProductList = new ArrayList<>();
        productCategoryViewModel = ViewModelProviders.of(this).get(ProductCategoryViewModel.class);
        productCategoryViewModel.getCategoriesAsync(this);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.GetProductList(FromByList,BuyListId).observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                AllProducts = products;
                Integer CountCat = 0;
                List<ProductGrouping> productGroupings = new ArrayList<>();
                for (ProductCategory pc:productCategoryList)
                {
                    ProductGrouping itemHeader = new ProductGrouping(new Header(pc.getDescription()));
                    productGroupings.add(itemHeader);
                    for (Product p:products)
                    {
                        if (pc.getProductCatId()==p.getProductCatId()){
                            ProductGrouping itemRow = new ProductGrouping(new ItemRow(p.getProductId(), p.getDescription(),p.getImageProduct(),p.getGenerateBySystem(),p.getExist() > 0));
                            productGroupings.add(itemRow);
                        }
                    }
                }

                IsProduct =getIntent().getBooleanExtra("IsProductList",true);
                adapter = new ProductAdapter(Products.this,productGroupings,IsProduct);
                lvProducts.setAdapter(adapter);
                TextView tvTotal = findViewById(R.id.tvTotal);
                tvTotal.setText(adapter.getCount()+ " Items");
            }
        });

        fabCreateProduct = findViewById(R.id.fabCreateProduct);
        Drawable myDrawableButton = getResources().getDrawable(R.drawable.ic_add_circle_black_24dp);
        fabCreateProduct.setImageDrawable(myDrawableButton);
        fabCreateProduct.setOnClickListener(new View.OnClickListener() {
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
                //adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    protected void InitializeView() {
        lvProducts = findViewById(R.id.lvProducts);
        txtProductDesc = findViewById(R.id.txtSuperMarketName);
        btnAdd = findViewById(R.id.btnAdd);
        actionBar.setTitle("Lista de productos");
        utils = new Utilities(this);
        categoryArrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,productCategoryListArray);
        SetEvents();
    }

    public void ShowForm(final com.diplomadouasd.buylistapp.Model.Entities.Product itemProduct){
        String MsgDlg = "Crear Producto";
        final View viewForm = myLayouInflater(R.layout.frm_product);
        spProductCategory = viewForm.findViewById(R.id.spProductCategoryList);
        spProductCategory.setAdapter(categoryArrayAdapter);

        final TextInputEditText  txtProductDesc = viewForm.findViewById(R.id.txtProductDesc);
        if (itemProduct!= null) {
            txtProductDesc.setText(itemProduct.getDescription());
            spProductCategory.setText(itemProduct.getProductCategoryDesc());
            MsgDlg = "Editar Producto";
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
                String Name = txtProductDesc.getText().toString().trim();
                Boolean hasError = false;
                String ProductCategorySelected = spProductCategory.getText().toString();

                if (TextUtils.isEmpty(ProductCategorySelected))
                {
                    spProductCategory.setError("Campo requerido");
                    spProductCategory.requestFocus();
                    hasError = true;
                }

                if (TextUtils.isEmpty(Name))
                {
                    txtProductDesc.setError("Campo requerido");
                    txtProductDesc.requestFocus();
                    hasError = true;
                }

                ProductCategory itemCat = new ProductCategory();

                //Buscar la categoria en el array
                for (ProductCategory p:productCategoryList) {
                    if (p.getDescription().toUpperCase().contains(ProductCategorySelected.toUpperCase()))
                    {
                        itemCat = p;
                        break;
                    }
                }

                if (!hasError) {

                    com.diplomadouasd.buylistapp.Model.Entities.Product item = new com.diplomadouasd.buylistapp.Model.Entities.Product
                            (
                                    itemCat.getProductCatId(),
                                    txtProductDesc.getText().toString(),
                                    null
                                    ,false);
                    if (itemProduct==null) {
                        productViewModel.AddNewProduct(item);
                    }
                    else {
                        itemProduct.setProductCatId(itemCat.getProductCatId());
                        itemProduct.setDescription(txtProductDesc.getText().toString());
                        productViewModel.UpdateProduct(itemProduct);
                    }

                    utils.CurrentDialog.dismiss();
                }
            }
        });

        utils.ModalPopup(MsgDlg,"",viewForm,null,null);
    }

    @Override
    protected void SetEvents() {
        lvProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final com.diplomadouasd.buylistapp.Model.Entities.ProductGrouping Item = adapter.getItem(position);
                if (!((ItemRow) Item.getItemRow()).getItemIsSystem()) {
                    utils.DialogConfirm("", "Esta seguro de eliminar el producto?", Utilities.DialogType.Warning, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //productViewModel.DeleteProduct();
                        }
                    }, null);
                }
                    return true;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent();
        if (SelectedProductList.size() > 0) {
            String SelectedProductListJson = oJSONSerializer.toJson(SelectedProductList);
            intent.putExtra("SelectedProductListJson", SelectedProductListJson);
            setResult(RESULT_OK, intent);
        } else
            setResult(RESULT_CANCELED,intent);

        return super.onSupportNavigateUp();
    }
}
