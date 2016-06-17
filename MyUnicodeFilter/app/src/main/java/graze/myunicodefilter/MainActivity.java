package graze.myunicodefilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AutoCompleteTextView atcpSearch;
    SearchView searchView;
    MyAdapter adapter;
    List<String> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        listView = (ListView) findViewById(R.id.listView);
        atcpSearch = (AutoCompleteTextView) findViewById(R.id.atcpSearch);
        adapter = new MyAdapter(this, R.layout.item_list_layout, countries);
        listView.setAdapter(adapter);
        atcpSearch.setAdapter(adapter);
    }

    void initData() {
        countries = new ArrayList<>();
        countries.add("Việt Nam");
        countries.add("Lào");
        countries.add("Ấn Độ");
        countries.add("Trung Quốc");
        countries.add("Pháp");
        countries.add("Nhật Bản");
        countries.add("Hàn Quốc");
        countries.add("Mỹ");
        countries.add("Đông Timo");
        countries.add("Thái Lan");
        countries.add("Campuchia");
        countries.add("Úc");
        countries.add("Nga");
        countries.add("Canada");
        countries.add("Ý");
        countries.add("Anh Quốc");
        countries.add("Mêxicô");
        countries.add("Ai Cập");
        countries.add("Ả Rập");
        countries.add("IRắc");
        countries.add("Braxin");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        MenuItem menuItem = menu.findItem(R.id.mnSearch);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Tìm kiếm tại đây.....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.getFilter().filter("");
                    listView.clearTextFilter();
                } else {
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
