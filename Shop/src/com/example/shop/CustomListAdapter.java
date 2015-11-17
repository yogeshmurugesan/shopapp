package com.example.shop;

import java.util.List;
 
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
 
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.shop.model.MobileList;
 
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<MobileList> mobileItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
 
    public CustomListAdapter(Activity activity, List<MobileList> mobileItems) {
        this.activity = activity;
        this.mobileItems = mobileItems;
    }
 
    @Override
    public int getCount() {
        return mobileItems.size();
    }
 
    @Override
    public Object getItem(int location) {
        return mobileItems.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.row_listview, null);
 
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.ml_name);
        TextView model = (TextView) convertView.findViewById(R.id.ml_model);
        TextView price = (TextView) convertView.findViewById(R.id.ml_price);
 
        MobileList m = mobileItems.get(position);
        
        thumbNail.setImageUrl(m.getImgurl(), imageLoader);
        thumbNail.setDefaultImageResId(R.drawable.loading6);
        name.setText(m.getName());
        model.setText(m.getModel());
        price.setText(m.getPrice());
        
        return convertView;
    }
 
}
