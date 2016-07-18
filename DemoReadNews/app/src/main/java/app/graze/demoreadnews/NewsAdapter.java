package app.graze.demoreadnews;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by graze on 18/07/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    Context mContext;
    int mLayoutRes;
    List<News> newsList;
    Activity activity;

    public NewsAdapter(Activity activity, Context mContext, int mLayoutRes, List<News> newsList) {
        this.activity = activity;
        this.mContext = mContext;
        this.mLayoutRes = mLayoutRes;
        this.newsList = newsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Infate
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(mLayoutRes, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Thuc hien gi gi do
        holder.tvTitle.setText(newsList.get(position).getTitle().toString());
        holder.tvDescription.setText(newsList.get(position).getDescription().toString());
        holder.tvPubDate.setText(newsList.get(position).getPubDate().toString());
        Picasso.with(mContext)
                .load(newsList.get(position).getImgLink())
                .resize(90, 90)
                .centerCrop()
                .error(R.drawable.none)
                .into(holder.imageView);
        holder.cardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void clear(){
        newsList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<News> newsest){
        newsList.addAll(newsest);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvPubDate;
        TextView tvDescription;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvContent);
            tvPubDate = (TextView) itemView.findViewById(R.id.tvDate);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

            //Tao action tai ViewHolder lun vi chua CardView
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = newsList.get(getAdapterPosition()).getLinkDetail().toString();
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                    CustomTabsIntent tabsIntent = builder.build();
                    tabsIntent.launchUrl(activity,Uri.parse(url));
                }
            });
        }
    }
}