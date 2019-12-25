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
import com.diplomadouasd.buylistapp.Model.Entities.ProductCategory;
import com.diplomadouasd.buylistapp.R;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryAdapter extends BaseAdapter  implements Filterable {
    Context context;
    List<ProductCategory> dataList;
    List<ProductCategory> tempdataList;
    public ProductCategoryAdapter(Context context, List<ProductCategory> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.tempdataList =dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public ProductCategory getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getProductCatId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.product_category_row_item,parent,false);
        ProductCategory currentRecord = getItem(position);
        TextView tvSuperMarketName= v.findViewById(R.id.tvCategoryName);
        tvSuperMarketName.setText(currentRecord.getDescription());
        ImageView btnEdit = v.findViewById(R.id.btnEdit);
        SetOnclickBtnEdit(btnEdit,currentRecord);
        return v;
    }

    private void SetOnclickBtnEdit(View v,final ProductCategory item){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((com.diplomadouasd.buylistapp.View.ProductCategory)context).ShowForm(item);
            }
        });
    }

    @Override
    public Filter getFilter() {
        return new ProductCategoryAdapter.ProductCategoryFilter();
    }

    public  class ProductCategoryFilter extends  Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length()==0) {
                results.values = tempdataList;
                results.count = tempdataList.size();
            } else {

                ArrayList<ProductCategory> Suggestions = new ArrayList<>();
                for(ProductCategory item:tempdataList ){

                    if (item.getDescription().toUpperCase().contains(constraint.toString().toUpperCase())){
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
            dataList = (ArrayList<ProductCategory>) results.values;
            notifyDataSetChanged();
        }
    }
}
