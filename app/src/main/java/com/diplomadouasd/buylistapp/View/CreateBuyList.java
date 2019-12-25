package com.diplomadouasd.buylistapp.View;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.diplomadouasd.buylistapp.Adapter.BuyListProductAdapter;
import com.diplomadouasd.buylistapp.Adapter.BuyListProductAdapterCar;
import com.diplomadouasd.buylistapp.Model.Entities.BuyList;
import com.diplomadouasd.buylistapp.Model.Entities.BuyListDetail;
import com.diplomadouasd.buylistapp.Model.Entities.Header;
import com.diplomadouasd.buylistapp.Model.Entities.HeaderInCar;
import com.diplomadouasd.buylistapp.Model.Entities.HeaderInList;
import com.diplomadouasd.buylistapp.Model.Entities.ItemRow;
import com.diplomadouasd.buylistapp.Model.Entities.ItemRowInCar;
import com.diplomadouasd.buylistapp.Model.Entities.Product;
import com.diplomadouasd.buylistapp.Model.Entities.ProductCategory;
import com.diplomadouasd.buylistapp.Model.Entities.ProductGrouping;
import com.diplomadouasd.buylistapp.Model.Entities.SuperMarket;
import com.diplomadouasd.buylistapp.R;
import com.diplomadouasd.buylistapp.Utility.Utilities;
import com.diplomadouasd.buylistapp.ViewModel.BuyListViewModel;
import com.diplomadouasd.buylistapp.ViewModel.ProductCategoryViewModel;
import com.diplomadouasd.buylistapp.ViewModel.SuperMarketViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateBuyList extends BaseActivity {
    ImageView btnCancelList;
    ImageView btnAdd;
    BuyListProductAdapter adapter;
    BuyListProductAdapterCar adapterCar;
    Utilities utils;
    GridView lvProducts;
    ArrayList<String> SuperMarketArrayList = new ArrayList<>();
    ArrayList<SuperMarket> SuperMarketList = new ArrayList<>();
    ArrayAdapter<String> supermarketArrayAdapter;
    SuperMarketViewModel superMarketViewModel;
    BuyListViewModel buyListViewModel;
    MaterialBetterSpinner spSupermarkets;
    public ProductCategoryViewModel productCategoryViewModel;
    private Gson oJSONSerializer = new Gson();
    public Integer BuyListId = null;
    TextView txtTotalProducts;
    TextView txtTotalInCar;
    TextView txtTotalPagar;
    Boolean HasCreated;

    List<ProductGrouping> productGroupings = new ArrayList<>();
    List<com.diplomadouasd.buylistapp.Model.Entities.ProductCategory> productCategoryList = new ArrayList<>();
    public void SetCategoryList(List<com.diplomadouasd.buylistapp.Model.Entities.ProductCategory> _productCategoryList){
        productCategoryList = _productCategoryList;
    }

    public void  SetBuyListId(int Id){
        BuyListId = Id;
        String SuperMarketName = spSupermarkets.getText().toString();
        int SuperMarketIdSelected = GetSuperMarketIdbyName(SuperMarketName);
        //Crear la lista
        buyListViewModel.AddNewBuyList(new BuyList(BuyListId, new Date(), SuperMarketIdSelected, false));
        GetBuyListDetail(BuyListId);
        HasCreated =true;
    }

    public void SetSuperMarketList(List<SuperMarket> superMarkets){

        SuperMarketList.addAll(superMarkets);
        for (SuperMarket p : superMarkets)
            SuperMarketArrayList.add(p.getName());

        supermarketArrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,SuperMarketArrayList);
        spSupermarkets.setAdapter(supermarketArrayAdapter);
//        supermarketArrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_buy_list);
        InitializeView();
        SetEvents();
        BuyListId = getIntent().getIntExtra("BuyListId",-1);
        HasCreated = (BuyListId > 0);
        superMarketViewModel = ViewModelProviders.of(this).get(SuperMarketViewModel.class);
        superMarketViewModel.getAllSuperMarket(this);
        productCategoryViewModel = ViewModelProviders.of(this).get(ProductCategoryViewModel.class);
        productCategoryViewModel.getCategoriesAsync(this);
        buyListViewModel = ViewModelProviders.of(this).get(BuyListViewModel.class);

        if (!HasCreated) {
            buyListViewModel.getLastBuyList(this);
        } else {
            GetBuyListDetail(BuyListId);
        }
    }

    public void GetBuyListDetail(final Integer BuyListId){
        buyListViewModel.getBuyDetailList(BuyListId).observe(this, new Observer<List<BuyListDetail>>() {
            @Override
            public void onChanged(List<BuyListDetail> buyListDetails) {
                if (buyListDetails.size() > 0) {
                    productGroupings = new ArrayList<>();
                    Integer CountProductInList = 0;
                    Integer CountProductInCar = 0;
                    Double TotalAmountToPay  = 0.00;
                    int CurrentProductCatId;
                    List<BuyListDetail> ProductsInList = new ArrayList<>();
                    List<BuyListDetail> ProductsInCar = new ArrayList<>();

                    //Productos en la lista
                    for (BuyListDetail p : buyListDetails){
                        if (!p.getInCar()) {
                            ProductsInList.add(p);
                            CountProductInList++;
                        }
                    }

                    //Productos en el carrito
                    for (BuyListDetail p : buyListDetails){
                        if (p.getInCar()) {
                            ProductsInCar.add(p);
                            TotalAmountToPay+=p.getQuantity() * p.getUnitPrice();
                            CountProductInCar++;
                        }
                    }

                    ProductGrouping itemHeaderInList = new ProductGrouping(new HeaderInList("Productos"));
                    productGroupings.add(itemHeaderInList);

                    Boolean IsChangeHeader = true;
                    if (ProductsInList.size() > 0) {
                        ProductGrouping itemHeader = null;
                        BuyListDetail FirstRecord = ProductsInList.get(0);
                        CurrentProductCatId = FirstRecord.getProductCatId();
                        //Crear la primera categoria
                        itemHeader = new ProductGrouping(new Header(FirstRecord.getProductCatDesc()));
                        productGroupings.add(itemHeader);
                        for (BuyListDetail p : ProductsInList) {
                            int NextCatId = p.getProductCatId();
                            if (CurrentProductCatId != NextCatId) {
                                itemHeader = new ProductGrouping(new Header(p.getProductCatDesc()));
                                productGroupings.add(itemHeader);
                                CurrentProductCatId = p.getProductCatId();
                            }

                            ProductGrouping itemRow = new ProductGrouping(new ItemRow(p.getProductId(), p.getProductDesc(),null,p.getGenerateBySystem(),true));
                            productGroupings.add(itemRow);
                        }
                    }

                    ProductGrouping itemHeaderInCar = new ProductGrouping(new HeaderInCar("Carrito"));
                    productGroupings.add(itemHeaderInCar);
                    if (ProductsInCar.size() > 0) {
                        ProductGrouping itemHeader = null;
                        BuyListDetail FirstRecord = ProductsInCar.get(0);
                        CurrentProductCatId = FirstRecord.getProductCatId();
                        //Crear la primera categoria
                        itemHeader = new ProductGrouping(new Header(FirstRecord.getProductCatDesc()));
                        productGroupings.add(itemHeader);

                        for (BuyListDetail p : ProductsInCar)
                        {
                            int NextCatId = p.getProductCatId();
                            if (CurrentProductCatId != NextCatId) {
                                itemHeader = new ProductGrouping(new Header(p.getProductCatDesc()));
                                productGroupings.add(itemHeader);
                                CurrentProductCatId = p.getProductCatId();
                            }
                            ProductGrouping itemRow = new ProductGrouping(new ItemRowInCar(p.getProductId(), p.getProductDesc(),p.getQuantity(),p.getUnitPrice()));
                            productGroupings.add(itemRow);
                        }
                    }

                    adapter = new BuyListProductAdapter(CreateBuyList.this, productGroupings,buyListViewModel);
                    lvProducts.setAdapter(adapter);
                    txtTotalProducts.setText(CountProductInList + " Productos en la lista");
                    txtTotalInCar.setText(CountProductInCar + " en el carrito");
                    txtTotalPagar.setText("Total a pagar : $" + TotalAmountToPay);
                    buyListViewModel.UpdateBuyList(new BuyList(BuyListId,TotalAmountToPay));
                }
            }
        });
    }

    private ProductCategory GetProductCategoryById(int CategoryId){
        ProductCategory result = null;
        for (ProductCategory pc : productCategoryList) {
             if (pc.getProductCatId() == CategoryId){
                 result = pc;
                 break;
             }
        }
        return
             result;
    }

    @Override
    protected void InitializeView() {
        btnCancelList = findViewById(R.id.btnCancelList);
        txtTotalProducts = findViewById(R.id.txtTotalProducts);
        txtTotalInCar= findViewById(R.id.txtTotalInCar);
        txtTotalPagar = findViewById(R.id.txtTotalPagar);
        utils = new Utilities(this);
        getSupportActionBar().setTitle("Lista de Compras");
        btnAdd = findViewById(R.id.btnAdd);
        lvProducts = findViewById(R.id.lvProducts);
        spSupermarkets = findViewById(R.id.spSupermarkets);
    }

    @Override
    protected void SetEvents() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SupermarketSelected = spSupermarkets.getText().toString();
                if (TextUtils.isEmpty(SupermarketSelected))
                {
                    utils.MessageBox("Advertencia", "Debe Elegir el Supermercado");
                    return;
                }
                Intent intent = new Intent(getApplicationContext(),Products.class);
                intent.putExtra("IsProductList",false);
                intent.putExtra("FromByList",true);
                intent.putExtra("BuyListId",BuyListId);
                startActivityForResult(intent,1);
            }
    });

    btnCancelList.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            utils.DialogConfirm("Pregunta", "Esta Seguro de querer cancelar la lista actual?", Utilities.DialogType.Warning, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onSupportNavigateUp();
                }
            },null);
        }
      });

    lvProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            utils.DialogConfirm("Advertencia", "Esta seguro de querer eliminar este producto de la lista?", Utilities.DialogType.Warning, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ProductGrouping item = productGroupings.get(position);
                    if (item.getItemRow() instanceof ItemRowInCar){
                        buyListViewModel._InCar = false;
                        buyListViewModel.getDetailByProductId(((ItemRowInCar) item.getItemRow()).getItemId(),CreateBuyList.this);
                    }
                }
            },null);

            return false;
        }
    });
    }

    public void SetItemInCar(BuyListDetail Item)
    {
        if (Item.getInCar())
            ShowForm(Item);
        else
            buyListViewModel.UpdateBuyListDetail(Item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK)
        {
            //Guardar los productos seleccionados
            String SelectedProductListJson = data.getStringExtra("SelectedProductListJson");
            Type type = new TypeToken<List<Product>>() {}.getType();
            List<Product> SelectedProducts = oJSONSerializer.fromJson(SelectedProductListJson, type);
            for (Product p : SelectedProducts) {
                BuyListDetail item = new BuyListDetail(BuyListId, p.getProductId(), 0, Double.valueOf(0),false);
                buyListViewModel.AddDetailItems(item);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private int GetSuperMarketIdbyName(String Name){
       int result = -1;
        for (SuperMarket s:SuperMarketList) {
           if (s.getName().toUpperCase().contains(Name.toUpperCase()))
           {
               result = s.getSuperMarketId();
               break;
           }
        }

       return
               result;
    }

    public void ShowForm(final BuyListDetail itemProduct){
        String MsgDlg = "Añadiendo al Carrito";
        final View viewForm = myLayouInflater(R.layout.frm_product_incar);
        final TextView txtQty = viewForm.findViewById(R.id.txtQTY);
        final TextView txtUnitePrice = viewForm.findViewById(R.id.txtUnitPrice);

        viewForm.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
                utils.CurrentDialog.cancel();
            }
        });

        viewForm.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String QTY = txtQty.getText().toString().trim();
                String UnitePrice = txtUnitePrice.getText().toString().trim();
                Boolean hasError = false;

                if (TextUtils.isEmpty(QTY))
                {
                    txtQty.setError("Campo requerido");
                    txtQty.requestFocus();
                    hasError = true;
                }

                if (TextUtils.isEmpty(UnitePrice))
                {
                    txtUnitePrice.setError("Campo requerido");
                    txtUnitePrice.requestFocus();
                    hasError = true;
                }

                if (!hasError){
                itemProduct.setQuantity(Integer.valueOf(QTY));
                itemProduct.setUnitPrice(Double.valueOf(UnitePrice));
                itemProduct.setInCar(true);
                buyListViewModel.UpdateBuyListDetail(itemProduct);
                utils.CurrentDialog.dismiss();
                }
            }
        });

        utils.ModalPopup(MsgDlg,"",viewForm,null,null);
    }
}
