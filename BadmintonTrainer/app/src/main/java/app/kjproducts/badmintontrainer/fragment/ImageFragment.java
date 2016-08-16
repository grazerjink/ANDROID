package app.kjproducts.badmintontrainer.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.kjproducts.badmintontrainer.R;
import app.kjproducts.badmintontrainer.model.Detail;

/**
 * Created by KJ on 16/08/2016.
 */
public class ImageFragment extends Fragment {

    String imgLink;

    public static ImageFragment newInstance(String image) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString("imageLink", image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgLink = getArguments().getString("imageLink", null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imgChange);
        Picasso.with(getActivity()).load("file:///android_asset/detail_image/"+imgLink)
                .error(R.drawable.error_image).into(imageView);
        return view;
    }
}
