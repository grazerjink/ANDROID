package graze.demoasynctask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HOCVIEN on 6/24/2016.
 */
public class MyAdapter extends ArrayAdapter {
    Context mContext;
    int mResource;
    List<Country> mCountries;

    public MyAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mCountries = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mResource,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(mCountries.get(position).getName().toString());

        return convertView;
    }

    class ViewHolder{
        TextView tvName;
    }
}
