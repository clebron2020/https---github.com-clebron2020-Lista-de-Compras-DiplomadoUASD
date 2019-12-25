package com.diplomadouasd.buylistapp.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.diplomadouasd.buylistapp.Model.Entities.BuyList;
import com.diplomadouasd.buylistapp.R;
import com.diplomadouasd.buylistapp.View.BaseActivity;
import com.diplomadouasd.buylistapp.View.CreateBuyList;
import com.diplomadouasd.buylistapp.View.ListDetail;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class BuyListAdapter extends BaseAdapter
{
    Context context;
    List<BuyList> dataList;
    List<BuyList> tempdataList;

    public BuyListAdapter(Context context, List<BuyList> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.tempdataList =dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public BuyList getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  Long.parseLong(String.valueOf(getItem(position).getBuyListId()));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.buylist_row_item,parent,false);
        final BuyList currentRecord = getItem(position);
        ImageButton btnViewDetail = v.findViewById(R.id.btnViewDetail);
        TextView tvTotalAmountPaid= v.findViewById(R.id.tvTotalAmountPaid);
        TextView tvCreateDate= v.findViewById(R.id.tvCreateDate);
        TextView tvSuperMarketName= v.findViewById(R.id.tvSuperMarketName);
        tvTotalAmountPaid.setText("Total Pagado $:"  + currentRecord.getBuyListTotal());
        tvCreateDate.setText(currentRecord.getBuyListCreattionDate().toString());
        tvSuperMarketName.setText("SuperMercado :" + currentRecord.getSuperMarketName());
        btnViewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CreateBuyList.class);
                intent.putExtra("BuyListId",currentRecord.getBuyListId());
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return v;
    }
}
