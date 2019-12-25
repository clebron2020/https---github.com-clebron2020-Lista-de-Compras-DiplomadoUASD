package com.diplomadouasd.buylistapp.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.diplomadouasd.buylistapp.Model.Entities.ProductCategory;
import com.diplomadouasd.buylistapp.R;
import java.util.ArrayList;

public class ProductCategorySpinnerAdapter extends ArrayAdapter<ProductCategory> {

    public ProductCategorySpinnerAdapter(Context context, @NonNull ArrayList<ProductCategory> objects) {
        super(context, 0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return InitView(position, convertView, parent);
    }

    private View InitView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
             convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_category_spinner_item,parent,false);
             ProductCategory currentProductCategory = getItem(position);
             TextView tvProductCategoryDesc = convertView.findViewById(R.id.tvProductCategoryDesc);
             tvProductCategoryDesc.setText(currentProductCategory.getDescription());
          return
                  convertView;
    }
}
