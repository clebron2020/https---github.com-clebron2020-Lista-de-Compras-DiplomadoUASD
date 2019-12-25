package com.diplomadouasd.buylistapp.Adapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.diplomadouasd.buylistapp.Model.Entities.Header;
import com.diplomadouasd.buylistapp.Model.Entities.ItemRow;
import com.diplomadouasd.buylistapp.Model.Entities.ProductGrouping;
import com.diplomadouasd.buylistapp.R;
import com.diplomadouasd.buylistapp.Utility.BitMapUtility;
import java.util.List;
public class ProductAdapter extends BaseAdapter {
        //implements Filterable {
    Context context;
    List<ProductGrouping> dataList;
    List<ProductGrouping> tempdataList;
    Boolean _IsProductList;

    public ProductAdapter(Context context, List<ProductGrouping> dataList,Boolean IsProductList) {
        this.context = context;
        this.dataList = dataList;
        this.tempdataList = dataList;
        this._IsProductList = IsProductList;
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
            v = LayoutInflater.from(context).inflate(R.layout.row_item_list,parent,false);
            TextView tvRowItem = v.findViewById(R.id.tvRowItem);
            ImageView btnAddItem = v.findViewById(R.id.btnAddItem);
            ImageView btnEdit = v.findViewById(R.id.btnEdit);
            ImageView btnSystem = v.findViewById(R.id.btnSystem);

            byte[] ImageProduct = ((ItemRow)currentItem.getItemRow()).getItemImage();
            if (ImageProduct!=null) {
                Bitmap img = BitMapUtility.getImage(ImageProduct);
                btnEdit.setImageBitmap(img);
            }

            Boolean IsGenerateBySystem = ((ItemRow)currentItem.getItemRow()).getItemIsSystem();
            btnSystem.setVisibility(IsGenerateBySystem?View.VISIBLE:View.GONE);
            btnAddItem.setVisibility(_IsProductList?View.GONE:View.VISIBLE);
            tvRowItem.setText(((ItemRow)currentItem.getItemRow()).getItemDescription());
            Boolean Exist = ((ItemRow)currentItem.getItemRow()).getExist();

            if (!_IsProductList && Exist){
                btnAddItem.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check_blue_24dp));
            }
            else
               SetOnClickBtnAddItem(btnAddItem,((ItemRow)currentItem.getItemRow()).getItemId(),position);
            SetOnclickBtnEdit(btnEdit,((ItemRow)currentItem.getItemRow()).getItemId());
        }

        return v;
    }

    private void SetOnClickBtnAddItem(View v, final  int itemId,final int Position){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.diplomadouasd.buylistapp.View.Products Target = ((com.diplomadouasd.buylistapp.View.Products)context);
                Drawable ImageAdd = context.getResources().getDrawable(R.drawable.ic_add_black_24dp);
                Drawable ImageCheck = context.getResources().getDrawable(R.drawable.ic_check_black_24dp);
                Object Tag = v.getTag();

                if (Tag==null){
                    Target.AddItemInBuyList(itemId);
                    Tag = false;
                }

                Boolean IsAdded = (Boolean) Tag;
                ((ImageView)v).setImageDrawable(IsAdded?ImageAdd:ImageCheck);
                v.setTag(!IsAdded);
                if (IsAdded)
                    Target.DeleteItemInBuyList(itemId);
            }
        });
    }

    private void SetOnclickBtnEdit(View v,final Integer ItemId){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               ((com.diplomadouasd.buylistapp.View.Products)context).ShowForm(null);
            }
        });
    }
}
