package kj.sgusupport.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kj.sgusupport.Menu;
import kj.sgusupport.R;

/**
 * Created by KJ on 05/10/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context mContext;
    List<Menu> menuList;
    int mLayoutRes;

    public MyAdapter(Context mContext, int mLayoutRes, List<Menu> menuList) {
        this.mContext = mContext;
        this.menuList = menuList;
        this.mLayoutRes = mLayoutRes;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgDM;
        TextView tvTenDM;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgDM = (ImageView) itemView.findViewById(R.id.imgIcon);
            tvTenDM = (TextView) itemView.findViewById(R.id.tvName);
        }

        @Override
        public void onClick(View v) {
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(mLayoutRes, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTenDM.setText(menuList.get(position).getTenDM().toString());
        Drawable drawable = mContext.getResources().getDrawable(mContext.getResources()
                .getIdentifier(menuList.get(position).getHinhDM().toString(), "drawable", mContext.getPackageName()));
        holder.imgDM.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
