package com.example.graze.demolistview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.util.List;

import my.country.flag.Country;

/**
 * Created by graze on 14/05/2016.
 */
public class MyCustomAdapter extends BaseAdapter{
    Context context;
    int layoutRes;
    List<Country> countries;

    public MyCustomAdapter(Context context, int layoutRes, List<Country> countries) {
        this.context = context;
        this.layoutRes = layoutRes;
        this.countries = countries;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layoutRes,parent,false);

            viewHolder = new ViewHolder();
            viewHolder.viName = (TextView) convertView.findViewById(R.id.twNameVi);
            viewHolder.enName = (TextView) convertView.findViewById(R.id.twNameEn);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.ivRegion);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.enName.setText(countries.get(position).getEnName());
        viewHolder.viName.setText(countries.get(position).getViName());
       //in t id = convertView.getResources().getIdentifier("drawable/" + countries.get(position).getEnName().toLowerCase(), null, null);
        int id=context.getResources().getIdentifier(countries.get(position).getEnName().toLowerCase(), "drawable", context.getPackageName());
        viewHolder.image.setImageResource(id);

        return convertView;
    }

    class ViewHolder {
        TextView enName;
        TextView viName;
        ImageView image;
    }
}
