package com.example.graze.demolistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.country.flag.Country;

public class DemoListViewActivity extends AppCompatActivity {

    //@BindView(R.id.listview)
    ListView listView;

    List<Country> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list_view);

        //ButterKnife.bind(this);
        initData();
        listView = (ListView) findViewById(R.id.listview);
        MyCustomAdapter adapter = new MyCustomAdapter(this, R.layout.demo_list_item_view, countries);
        listView.setAdapter(adapter);
    }

    void initData(){
            countries = new ArrayList<>();
            countries.add(new Country("Việt Nam","Vietnam"));
            countries.add(new Country("Bra-xin","Brazil"));
            countries.add(new Country("Canada","Canada"));
            countries.add(new Country("Pháp","France"));
            countries.add(new Country("Đức","Germany"));
            countries.add(new Country("Iran","Iran"));
            countries.add(new Country("Nhật Bản","Japan"));
            countries.add(new Country("Hàn Quốc","Korea"));
            countries.add(new Country("Nga","Russia"));
            countries.add(new Country("Trung Quốc","China"));
            countries.add(new Country("Nước Ý","Italy"));
            countries.add(new Country("Ấn Độ","India"));
            countries.add(new Country("Argentina","Argentina"));
            countries.add(new Country("Bermuda","Bermuda"));
            countries.add(new Country("Austrailia","Austria"));
            countries.add(new Country("Nga","Russia"));
    }
}
