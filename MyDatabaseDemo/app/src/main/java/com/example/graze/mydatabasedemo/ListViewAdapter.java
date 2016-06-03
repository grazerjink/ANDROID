package com.example.graze.mydatabasedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by graze on 25/05/2016.
 */
public class ListViewAdapter extends ArrayAdapter {

    Context mContext;
    int mLayoutRes;
    List<Country> mCountries;

    public ListViewAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        mContext = context;
        mLayoutRes = resource;
        mCountries = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mLayoutRes,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.nameEn = (TextView) convertView.findViewById(R.id.tvNameEn);
            viewHolder.nameVi = (TextView) convertView.findViewById(R.id.tvNameVi);
            viewHolder.flag = (ImageView) convertView.findViewById(R.id.ivFlag);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nameEn.setText(mCountries.get(position).getNameEn());
        viewHolder.nameVi.setText(mCountries.get(position).getNameVi());
        int id = mContext.getResources().getIdentifier(mCountries.get(position).getFlag(),"drawable",mContext.getPackageName());
        viewHolder.flag.setImageResource(id);
        return convertView;
    }
}

class ViewHolder {
    TextView nameEn;
    TextView nameVi;
    ImageView flag;
}
