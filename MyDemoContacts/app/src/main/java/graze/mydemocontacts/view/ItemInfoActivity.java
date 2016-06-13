package graze.mydemocontacts.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import graze.mydemocontacts.R;

public class ItemInfoActivity extends AppCompatActivity {

    TextView tvPhone;
    TextView tvEmail;
    ArrayList<String> phone;
    ArrayList<String> email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvEmail = (TextView) findViewById(R.id.tvPhone);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        phone = new ArrayList<>();
        email = new ArrayList<>();
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                phone = bundle.getStringArrayList("phone");
                email = bundle.getStringArrayList("email");
                tvEmail.setText(email.get(0).toString());
                tvPhone.setText(phone.get(0).toString());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info_contacts,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
