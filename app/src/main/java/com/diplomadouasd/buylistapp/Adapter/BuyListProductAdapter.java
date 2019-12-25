package com.diplomadouasd.buylistapp.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.diplomadouasd.buylistapp.Model.Entities.Header;
import com.diplomadouasd.buylistapp.Model.Entities.HeaderInCar;
import com.diplomadouasd.buylistapp.Model.Entities.HeaderInList;
import com.diplomadouasd.buylistapp.Model.Entities.ItemRow;
import com.diplomadouasd.buylistapp.Model.Entities.ItemRowInCar;
import com.diplomadouasd.buylistapp.Model.Entities.Product;
import com.diplomadouasd.buylistapp.Model.Entities.ProductGrouping;
import com.diplomadouasd.buylistapp.R;
import com.diplomadouasd.buylistapp.View.CreateBuyList;
import com.diplomadouasd.buylistapp.ViewModel.BuyListViewModel;
import com.google.android.material.checkbox.MaterialCheckBox;
import java.util.List;

public class BuyListProductAdapter extends BaseAdapter {
        //implements Filterable {
    Context context;
    List<ProductGrouping> dataList;
    BuyListViewModel buyListViewModel;
    public BuyListProductAdapter(Context context, List<ProductGrouping> dataList,BuyListViewModel _buyListViewModel) {
        this.context = context;
        this.dataList = dataList;
        this.buyListViewModel = _buyListViewModel;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public ProductGrouping getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
                //Integer.parseInt(String.valueOf(getItem(position).getProductId()));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        ProductGrouping currentItem = getItem(position);

        if (currentItem.getItemRow() instanceof Header)
        {
            v = LayoutInflater.from(context).inflate(R.layout.header_row_list,parent,false);
            TextView tvHeader = v.findViewById(R.id.tvHeader);
            tvHeader.setText(((Header)currentItem.getItemRow()).get_HeaderName());
        }
        else if (currentItem.getItemRow() instanceof HeaderInCar)
        {
            v = LayoutInflater.from(context).inflate(R.layout.header_row_incar_list,parent,false);
        }
        else if (currentItem.getItemRow() instanceof HeaderInList)
        {
            v = LayoutInflater.from(context).inflate(R.layout.header_row_in_list,parent,false);
        } else if (currentItem.getItemRow() instanceof ItemRow)
        {
            v = LayoutInflater.from(context).inflate(R.layout.row_item_buylist,parent,false);
            TextView tvRowItem = v.findViewById(R.id.tvRowItem);
            MaterialCheckBox btnAddItem = v.findViewById(R.id.btnAddItem);
            tvRowItem.setText(((ItemRow)currentItem.getItemRow()).getItemDescription());
            SetOnClickBtnAddItem(btnAddItem,((ItemRow)currentItem.getItemRow()).getItemId());
        }
        else if (currentItem.getItemRow() instanceof ItemRowInCar)
        {
            v = LayoutInflater.from(context).inflate(R.layout.row_item_buylistincar,parent,false);
            TextView tvRowItem = v.findViewById(R.id.tvRowItemInCar);
            tvRowItem.setText(((ItemRowInCar)currentItem.getItemRow()).getItemDescription());
            TextView txtQty  = v.findViewById(R.id.txtQty);
            TextView txtPrice  = v.findViewById(R.id.txtPrice);
            TextView txtTotal  = v.findViewById(R.id.txtTotal);
            Integer QTY  =((ItemRowInCar)currentItem.getItemRow()).getItemQTY();
            Double Price  =((ItemRowInCar)currentItem.getItemRow()).getItemPrice();
            if (QTY!=null)
              txtQty.setText(QTY.toString());

            if (Price!=null)
                txtPrice.setText(Price.toString());

            if (QTY!=null && Price!=null){
                Double Total = QTY * Price;
                txtPrice.setText(Price.toString());
                txtTotal.setText(Total.toString());
            }
        }

        return v;
    }

    private void SetOnClickBtnAddItem(View v, final int itemId){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyListViewModel._InCar = true;
                buyListViewModel.getDetailByProductId(itemId,(CreateBuyList)context);
            }
        });
    }

    private void SetOnclickBtnEdit(View v,final Product item){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((com.diplomadouasd.buylistapp.View.Products)context).ShowForm(item);
            }
        });
    }
}
