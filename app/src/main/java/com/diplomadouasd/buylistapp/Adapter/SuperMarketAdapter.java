package com.diplomadouasd.buylistapp.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.diplomadouasd.buylistapp.Model.Entities.SuperMarket;
import com.diplomadouasd.buylistapp.R;
import java.util.ArrayList;
import java.util.List;

public class SuperMarketAdapter extends BaseAdapter  implements Filterable {
    Context context;
    List<SuperMarket> dataList;
    List<SuperMarket> tempdataList;
    public SuperMarketAdapter(Context context, List<SuperMarket> dataList) {
       this.context = context;
       this.dataList = dataList;
       this.tempdataList =dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public SuperMarket getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getSuperMarketId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.supermarketlist_row_item,parent,false);
        SuperMarket currentRecord = getItem(position);
        TextView tvSuperMarketName= v.findViewById(R.id.tvSuperMarketName);
        tvSuperMarketName.setText(currentRecord.getName());
        ImageView btnFavorite = v.findViewById(R.id.btnFavorite);
        btnFavorite.setImageResource(currentRecord.getIsFavorite()?R.drawable.ic_star_black_24dp:R.drawable.ic_star_border_black_24dp);
        ImageView btnEdit = v.findViewById(R.id.btnEdit);
        SetOnclickBtnFavorite(btnFavorite,currentRecord);
        SetOnclickBtnEdit(btnEdit,currentRecord);
        return v;
    }

    private void SetOnclickBtnFavorite(View v,final SuperMarket item){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setIsFavorite(!item.getIsFavorite());
                ((com.diplomadouasd.buylistapp.View.SuperMarket)context).superMarketViewModel.UpdateSuperMarket(item);
            }
        });
    }

    private void SetOnclickBtnEdit(View v,final SuperMarket item){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((com.diplomadouasd.buylistapp.View.SuperMarket)context).superMarketViewModel.UpdateSuperMarketUI(item);
            }
        });
    }

    @Override
    public Filter getFilter() {
        return new CountryFilter();
    }

    public  class CountryFilter extends  Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length()==0) {
                results.values = tempdataList;
                results.count = tempdataList.size();
            } else {

                ArrayList<SuperMarket> Suggestions = new ArrayList<>();
                for(SuperMarket item:tempdataList ){

                    if (item.getName().toUpperCase().contains(constraint.toString().toUpperCase())){
                        Suggestions.add(item);
                    }
                }

                results.values = Suggestions;
                results.count = Suggestions.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataList = (ArrayList<SuperMarket>) results.values;
            notifyDataSetChanged();
        }
    }
}
