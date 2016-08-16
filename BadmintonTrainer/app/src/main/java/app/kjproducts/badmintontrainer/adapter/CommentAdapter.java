package app.kjproducts.badmintontrainer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.kjproducts.badmintontrainer.R;
import app.kjproducts.badmintontrainer.model.Comment;

/**
 * Created by KJ on 16/08/2016.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {
    Context mContext;
    int mLayoutRes;
    List<Comment> comments;

    public CommentAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
        mContext = context;
        mLayoutRes = resource;
        comments = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentViewHolder commentViewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(mLayoutRes, parent, false);
            commentViewHolder = new CommentViewHolder();
            commentViewHolder.tvUser = (TextView) convertView.findViewById(R.id.tvUser);
            commentViewHolder.tvComment = (TextView) convertView.findViewById(R.id.tvComment);
            convertView.setTag(commentViewHolder);
        } else {
            commentViewHolder = (CommentViewHolder) convertView.getTag();
        }
        commentViewHolder.tvUser.setText(comments.get(position).getUser().toString());
        commentViewHolder.tvComment.setText(comments.get(position).getComment().toString());
        return convertView;
    }

    class CommentViewHolder {
        TextView tvUser;
        TextView tvComment;
    }
}
