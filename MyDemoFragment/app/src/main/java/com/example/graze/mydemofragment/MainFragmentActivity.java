package com.example.graze.mydemofragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainFragmentActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if(count > 1)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        MyFragment fragment = new MyFragment();

        Bundle bundle = new Bundle();
        bundle.putString("title","Fragment 1");
        fragment.setArguments(bundle);

        pushFragment(fragment,true);
    }

    public void pushFragment(BaseFragment fragment, boolean isAddToBackStack) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.container,fragment,fragment.getTagName());
        if (isAddToBackStack)
            fragmentTransaction.addToBackStack(fragment.getTagName());
        fragmentTransaction.commit();
    }
}
