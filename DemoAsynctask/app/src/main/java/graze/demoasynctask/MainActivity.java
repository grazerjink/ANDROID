package graze.demoasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    List<Country> countries;
    Button btnNormal;
    Button btnAsyntask;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initData();
        gridView = (GridView) findViewById(R.id.gridview);
        btnAsyntask = (Button) findViewById(R.id.btnAsyntask);
        btnNormal = (Button) findViewById(R.id.btnNormal);


        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Co 15 tam hinh
                //Load trong 15s
                countries = new ArrayList<Country>();
                for (int i = 0; i < 15; i++) {
                    try {
                        Thread.sleep(1000);
                        countries.add(new Country("Việt Nam - " + i + " -", "vietnam"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new MyAdapter(MainActivity.this, R.layout.item_for_grid_view, countries);
                gridView.setAdapter(adapter);
            }
        });

        btnAsyntask.setOnClickListener(new View.OnClickListener() {
            //TODO: Fix bug sao cho execute lai lan 2 ko bao loi
            @Override
            public void onClick(View v) {

                initData();
                //Chi duoc goi 1 lan ma thoi hay ghi nho
                //Mun goi lan hai thi phai new moi asynctask rui goi lai

                new MyAsyncTask().execute("country1","country2","country3","country4","country5","country6","country7","country8",
                        "country9","country10","country11","country12","country13","country14","country15");
            }
        });
    }

    void initData() {
        countries = new ArrayList<>();
        adapter = new MyAdapter(MainActivity.this, R.layout.item_for_grid_view, countries);
        gridView.setAdapter(adapter);
    }

    class MyAsyncTask extends AsyncTask<String, Country, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            for (int i = 0; i < params.length; i++) {
                try {
                    Thread.sleep(1000);
                    //Luu va truyen xuong cho ProgessUpdate
                    Country country = new Country("Việt Nam - " + i + " -", "vietnam");
                    publishProgress(country);
                    //Dung publishProgress de truyen du lieu xuong ProgressUpdate
                    //Co the gui 1 luc nhieu thang nhu gui 1 luc 3 thang
                    //publishProgress(dulieu,dulieu,dulieu)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Country... values) {
            super.onProgressUpdate(values);
            countries.add(values[0]);
            //Cap nhat giao dien UI
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "Dữ liệu đã được cập nhật...", Toast.LENGTH_SHORT).show();
        }
    }
}
