package graze.mydemocontacts.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import graze.mydemocontacts.R;
import graze.mydemocontacts.model.Contact;
import graze.mydemocontacts.model.Email;
import graze.mydemocontacts.model.Phone;

public class ItemInfoActivity extends AppCompatActivity {

    TextView tvPhone;
    TextView tvEmail;
    TextView tvContactName;
    Long _ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvContactName = (TextView) findViewById(R.id.tvContactName);
        showInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info_contacts, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    void showInfo() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("mypackage");
        _ID = bundle.getLong("_ID");
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
//                        null, ContactsContract.Data._ID + "= ?",
                null, ContactsContract.Data.RAW_CONTACT_ID + "= ?",
                new String[]{_ID + ""},
                null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    tvContactName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY)));
                    //kiem tra mime_type --> record thuoc loai nao
                    String mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
                    switch (mimeType) {
                        case ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE:
                            Phone phone = new Phone();
                            phone.setNumber(cursor.getString(
                                    cursor.getColumnIndex(
                                            ContactsContract.CommonDataKinds.Phone.NUMBER)));
                            phone.setType(cursor.getInt(
                                    cursor.getColumnIndex(
                                            ContactsContract.CommonDataKinds.Phone.TYPE)));
                            //TODO: chuyen type tu int --> string
                            tvPhone.setText(phone.getNumber() + "");
                            break;
                        case ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE:
                            //TODO: get thong tin email
                            Email email = new Email();
                            email.setAddress(cursor.getString
                                    (cursor.getColumnIndex(
                                            ContactsContract.CommonDataKinds.Email.ADDRESS)));
                            email.setType(cursor.getInt(
                                    cursor.getColumnIndex(
                                            ContactsContract.CommonDataKinds.Email.TYPE)));
                            tvEmail.setText(email.getAddress() + "");
                            break;
                    }
                    //TODO: startActivity(detail contact)
                } while (cursor.moveToNext());
            }
        }
    }
}
