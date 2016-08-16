package app.kjproducts.badmintontrainer.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.bignerdranch.expandablerecyclerview.model.ParentListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import app.kjproducts.badmintontrainer.ContentActivity;
import app.kjproducts.badmintontrainer.R;
import app.kjproducts.badmintontrainer.list.ListExercise;
import app.kjproducts.badmintontrainer.model.Child;

/**
 * Created by KJ on 13/08/2016.
 */
public class ExerciseExpandableRecyclerAdapter extends ExpandableRecyclerAdapter<ExerciseExpandableRecyclerAdapter.ParentListExercise, ExerciseExpandableRecyclerAdapter.ChildListExercise> {


    /**
     * Primary constructor. Sets up {@link #mParentItemList} and {@link #mItemList}.
     * <p/>
     * Changes to {@link #mParentItemList} should be made through add/remove methods in
     * {@link ExpandableRecyclerAdapter}
     *
     * @param parentItemList List of all {@link ParentListItem} objects to be
     * displayed in the RecyclerView that this
     * adapter is linked to
     */

    private LayoutInflater mInflater;
    Context context;

    public ExerciseExpandableRecyclerAdapter(Context context, List<ParentListItem> itemList) {
        super(itemList);
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ParentListExercise onCreateParentViewHolder(ViewGroup parentViewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.list_parent_item, parentViewGroup, false);
        return new ParentListExercise(view);
    }

    @Override
    public ChildListExercise onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.list_children_item, childViewGroup, false);
        return new ChildListExercise(view);
    }

    @Override
    public void onBindParentViewHolder(ParentListExercise parentViewHolder, int position, ParentListItem parentListItem) {
        ListExercise listExercise = (ListExercise) parentListItem;
        parentViewHolder.tvTitle.setText(listExercise.getTitle());
    }

    @Override
    public void onBindChildViewHolder(ChildListExercise childViewHolder, int parentPosition, int childPosition, Object childListItem) {
        Child child = (Child) childListItem;
        Picasso.with(context).load("file:///android_asset/child_image/"+child.getChildImage()).error(R.drawable.imgdefault).into(childViewHolder.imgLabel);
        childViewHolder.tvContent.setText(child.getChildName());
    }

    class ParentListExercise extends ParentViewHolder {

        /**
         * Default constructor.
         *
         * @param itemView The {@link View} being hosted in this ViewHolder
         */

        CardView cardView;
        TextView tvTitle;
        ImageView imgArrow;

        public ParentListExercise(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            imgArrow = (ImageView) itemView.findViewById(R.id.imgArrow);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            final Animation animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_effect_for_arrow);
            animation.reset();
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isExpanded()) {
                        collapseView();
                        imgArrow.setBackgroundResource(R.drawable.hide_arrow);
                        imgArrow.startAnimation(animation);
                    } else {
                        expandView();
                        imgArrow.setBackgroundResource(R.drawable.open_arrow);
                        imgArrow.startAnimation(animation);
                    }
                }
            });

        }

        @Override
        public boolean shouldItemViewClickToggleExpansion() {
            return false;
        }

    }

    class ChildListExercise extends ChildViewHolder {
        /**
         * Default constructor.
         *
         * @param itemView The {@link View} being hosted in this ViewHolder
         */

        TextView tvContent;
        LinearLayout container;
        ImageView imgLabel;

        public ChildListExercise(final View itemView) {
            super(itemView);

            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            imgLabel = (ImageView) itemView.findViewById(R.id.imgLabel);
            container = (LinearLayout) itemView.findViewById(R.id.linearContainer);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Child child = (Child) getChildListItem();
                    Intent intent = new Intent(context, ContentActivity.class);
                    intent.putExtra("id_detail",child.getId_detail());
                    intent.putExtra("title",child.getChildName());
                    context.startActivity(intent);
                }
            });
        }
    }
}
