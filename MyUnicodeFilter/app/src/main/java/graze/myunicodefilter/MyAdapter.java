package graze.myunicodefilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by graze on 17/06/2016.
 */
public class MyAdapter extends ArrayAdapter {

    Context mContext;
    int mResource;
    List<String> mCountry;
    List<String> mCountryOriginal;

    public MyAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mCountry = objects;
        mCountryOriginal = new ArrayList<>(mCountry);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvCountry = (TextView) convertView.findViewById(R.id.tvCountry);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvCountry.setText(mCountry.get(position).toString());
        return convertView;
    }

    class ViewHolder {
        TextView tvCountry;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults results = new FilterResults();
                ConvertString convert = new ConvertString();
                List<String> temp = new ArrayList<>();
//                for (String sub : mCountryOriginal) {
//                    if (convert.convertString(sub).toUpperCase().contains(constraint.toString().toUpperCase()))
//                        temp.add(sub);
//                }
//                results.values = temp;
//                results.count = temp.size();
//                return results;
                if (constraint != null) {
                    temp.clear();
                    for (String item : mCountryOriginal) {
                        if (convert.convertString(item).toUpperCase().contains(constraint.toString().toUpperCase()))
                            temp.add(item);
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = temp;
                    filterResults.count = temp.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                /*mCountry.clear();
                mCountry.addAll((ArrayList<String>) results.values);
                notifyDataSetChanged();*/
                ArrayList<String> filteredList = (ArrayList<String>) results.values;
                if (results != null && results.count > 0) {
                    mCountry.clear();
                    for (String c : filteredList) {
                        mCountry.add(c);
                    }
                }
                notifyDataSetChanged();
            }
        };
    }

    class ConvertString {
        private char[] charA = {'à', 'á', 'ạ', 'ả', 'ã', 'â', 'ầ', 'ấ', 'ậ', 'ẩ', 'ẫ', 'ă', 'ằ', 'ắ', 'ặ', 'ẳ', 'ẵ'};// a,// ă,// â
        private char[] charE = {'ê', 'ề', 'ế', 'ệ', 'ể', 'ễ', 'è', 'é', 'ẹ', 'ẻ', 'ẽ'};
        private char[] charI = {'ì', 'í', 'ị', 'ỉ', 'ĩ'};
        private char[] charO = {'ò', 'ó', 'ọ', 'ỏ', 'õ', 'ô', 'ồ', 'ố', 'ộ', 'ổ', 'ỗ', 'ơ', 'ờ', 'ớ', 'ợ', 'ở', 'ỡ'};// ơ
        private char[] charU = {'ù', 'ú', 'ụ', 'ủ', 'ũ', 'ư', 'ừ', 'ứ', 'ự', 'ử', 'ữ'};
        private char[] charY = {'ỳ', 'ý', 'ỵ', 'ỷ', 'ỹ'};
        private char[] charD = {'đ', ' '};

        String charact = String.valueOf(charA, 0, charA.length)
                + String.valueOf(charE, 0, charE.length)
                + String.valueOf(charI, 0, charI.length)
                + String.valueOf(charO, 0, charO.length)
                + String.valueOf(charU, 0, charU.length)
                + String.valueOf(charY, 0, charY.length)
                + String.valueOf(charD, 0, charD.length);

        private char GetAlterChar(char pC) {
            if ((int) pC == 32) {
                return ' ';
            }

            char tam = pC;// Character.toLowerCase(pC);

            int i = 0;
            while (i < charact.length() && charact.charAt(i) != tam) {
                i++;
            }
            if (i < 0 || i > 67)
                return pC;

            if (i == 66) {
                return 'd';
            }
            if (i >= 0 && i <= 16) {
                return 'a';
            }
            if (i >= 17 && i <= 27) {
                return 'e';
            }
            if (i >= 28 && i <= 32) {
                return 'i';
            }
            if (i >= 33 && i <= 49) {
                return 'o';
            }
            if (i >= 50 && i <= 60) {
                return 'u';
            }
            if (i >= 61 && i <= 65) {
                return 'y';
            }
            return pC;
        }

        public String convertString(String pStr) {
            String convertString = pStr.toLowerCase();
            Character[] returnString = new Character[convertString.length()];
            for (int i = 0; i < convertString.length(); i++) {
                char temp = convertString.charAt(i);
                if ((int) temp < 97 || temp > 122) {
                    char tam1 = this.GetAlterChar(temp);
                    if ((int) temp != 32)
                        convertString = convertString.replace(temp, tam1);
                }
            }
            return convertString;
        }
    }
}
