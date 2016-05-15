package com.example.graze.mydemofragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by graze on 15/05/2016.
 */
public class MyFragment extends BaseFragment {
    @Override
    protected String getTagName() {
        return "fragment_1";
    }

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.edtContent)
    EditText edtContent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.layout_demo_fragment,container,false);
        ButterKnife.bind(this, root);
        //tvTitle = (TextView) root.findViewById(R.id.tvTitle);
        //btnNext = (Button) root.findViewById(R.id.btnNext);
        //edtContent = (EditText) root.findViewById(R.id.edtContent);

        Bundle bundle = getArguments();
        tvTitle.setText(bundle.getString("title"));

        btnNext.setOnClickListener(v -> {
            MyFragment fragment2 = new MyFragment();
            Bundle argument = new Bundle();
            argument.putString("title",edtContent.getText().toString());
            fragment2.setArguments(argument);
            ((MainFragmentActivity)getActivity()).pushFragment(fragment2,true);
        });
        return root;
    }
}
