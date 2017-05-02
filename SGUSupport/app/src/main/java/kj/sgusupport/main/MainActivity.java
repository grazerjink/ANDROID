package kj.sgusupport.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import kj.sgusupport.Menu;
import kj.sgusupport.R;
import kj.sgusupport.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences ref;
    private RecyclerView recyclerView;
    private List<Menu> menuList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        setSupportActionBar(toolbar);
        ref = getSharedPreferences("SharedRef", MODE_PRIVATE);
        danhSachDM();
        MyAdapter myAdapter = new MyAdapter(this, R.layout.danh_sach_chuc_nang, menuList);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == 1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String tmp = ref.getString(LoginActivity.MASV, "");
        if (tmp == null || tmp.equals("")) {
            Intent login = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(login);
        }
    }

    void anhXa() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
    }

    void danhSachDM() {
        menuList = new ArrayList<>();
        menuList.add(new Menu("Xem TKB", "ic_tkb"));
        menuList.add(new Menu("Xem điểm", "ic_diemthi"));
        menuList.add(new Menu("Xem lịch thi", "ic_lichthi"));
        menuList.add(new Menu("Chia sẻ đề thi", "ic_dethi"));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
