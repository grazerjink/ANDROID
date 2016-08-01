package app.kjproducts.badmintontrainer.ui.modules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.kjproducts.badmintontrainer.R;
import app.kjproducts.badmintontrainer.ui.ui.ContentActivity;

/**
 * Created by graze on 30/07/2016.
 */
public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.MyViewHolder> {
    Context mContext;
    int mLayoutRes;
    List<String> lessons;

    public LessonAdapter(Context mContext, int mLayoutRes, List<String> lessons) {
        this.mContext = mContext;
        this.mLayoutRes = mLayoutRes;
        this.lessons = lessons;
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
        holder.tvLesson.setText(lessons.get(position).toString());
        holder.cardView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvLesson;
        CardView cardView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            tvLesson = (TextView) itemView.findViewById(R.id.tvLesson);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent comingSoon = new Intent(itemView.getContext(), ContentActivity.class);
                    comingSoon.putExtra("item",(getAdapterPosition()+1)+"");
                    mContext.startActivity(comingSoon);
                    ((Activity)mContext).overridePendingTransition(R.anim.translate_open,R.anim.translate_close);
                }
            });
        }
    }
}


