package com.diplomadouasd.buylistapp.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.diplomadouasd.buylistapp.Model.Entities.Header;
import com.diplomadouasd.buylistapp.Model.Entities.ItemRow;
import com.diplomadouasd.buylistapp.Model.Entities.ProductGrouping;
import com.diplomadouasd.buylistapp.R;
import com.diplomadouasd.buylistapp.ViewModel.BuyListViewModel;
import java.util.List;
public class BuyListProductAdapterCar extends BaseAdapter {
    //implements Filterable {
    final Context context;
    List<ProductGrouping> dataList;
    final BuyListViewModel buyListViewModel;
    public BuyListProductAdapterCar(Context context, List<ProductGrouping> dataList, BuyListViewModel _buyListViewModel) {
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
        View v;
        ProductGrouping currentItem = getItem(position);
        if (currentItem.getItemRow() instanceof Header)
        {
            v = LayoutInflater.from(context).inflate(R.layout.header_row_list,parent,false);
            TextView tvHeader = v.findViewById(R.id.tvHeader);
            tvHeader.setText(((Header)currentItem.getItemRow()).get_HeaderName());
        } else
        {
            v = LayoutInflater.from(context).inflate(R.layout.row_item_buylistincar,parent,false);
            TextView tvRowItem = v.findViewById(R.id.tvRowItem);
            tvRowItem.setText(((ItemRow)currentItem.getItemRow()).getItemDescription());
            TextView txtQty  = v.findViewById(R.id.txtQty);
            TextView txtPrice  = v.findViewById(R.id.txtPrice);
            TextView txtTotal  = v.findViewById(R.id.txtTotal);
            int Qty =  ((ItemRow)currentItem.getItemRow()).getItemQTY();
            Double Price = ((ItemRow)currentItem.getItemRow()).getItemPrice();
            txtQty.setText(""+Qty);
            Double Total = Qty * Price;
            txtPrice.setText(Price.toString());
            txtTotal.setText(Total.toString());
        }

        return v;
    }
}