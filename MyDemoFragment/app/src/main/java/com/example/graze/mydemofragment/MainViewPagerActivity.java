package com.example.graze.mydemofragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_pager);
        ButterKnife.bind(this);

        viewPager.setAdapter(new MyViewPagerAdapter(getFragmentManager()));
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    MyFragment frag1 = new MyFragment();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("title","First Fragment");
                    frag1.setArguments(bundle1);
                    return frag1;
                case 1:
                    MyFragment frag2 = new MyFragment();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("title","Second Fragment");
                    frag2.setArguments(bundle2);
                    return frag2;
                case 2:
                    MyFragment frag3 = new MyFragment();
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("title","Third Fragment");
                    frag3.setArguments(bundle3);
                    return frag3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
