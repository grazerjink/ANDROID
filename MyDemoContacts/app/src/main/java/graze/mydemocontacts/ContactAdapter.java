package graze.mydemocontacts;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import graze.mydemocontacts.model.Contact;

/**
 * Created by graze on 13/06/2016.
 */
public class ContactAdapter extends ArrayAdapter<Contact> {
    Context mContext;
    int mResource;
    List<Contact> mContacts;
    List<Contact> mContactsOriginal;

    public ContactAdapter(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mContacts = objects;
        mContactsOriginal = new ArrayList<>(mContacts);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                //tim kiem item chua tu khoa can tim (constraint)
                FilterResults filterResults = new FilterResults();
                List<Contact> temp = new ArrayList<>();
                Log.d("TAG", "performFiltering: "+constraint);
                for (Contact ct : mContactsOriginal) {

                    if(ct.getDisplayName().toUpperCase().contains(constraint.toString().toUpperCase()))
                        temp.add(ct);
                }
                //Sau khi tim kiem thi tra ket qua cho publicResults
                filterResults.values = temp;
                filterResults.count = temp.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mContacts.clear();
                mContacts.addAll((List<Contact>)results.values);
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.icon = (MaterialLetterIcon) convertView.findViewById(R.id.icName);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tvContactName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        String name = mContext.getString(R.string.contact_name);
        String tmp = mContacts.get(position).getDisplayName().substring(0, 1);
        Log.d("TAG", "getView: " + tmp);
        viewHolder.textView.setText(mContacts.get(position).getDisplayName());
        viewHolder.icon.setLetter(tmp);
        viewHolder.icon.setShapeColor(color);

        return convertView;
    }

    class ViewHolder {
        MaterialLetterIcon icon;
        TextView textView;
    }
}
