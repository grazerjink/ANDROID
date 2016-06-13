package graze.mydemocontacts.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import graze.mydemocontacts.ContactAdapter;
import graze.mydemocontacts.R;
import graze.mydemocontacts.model.Contact;
import graze.mydemocontacts.model.Email;
import graze.mydemocontacts.model.Phone;

public class ListContactsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, Serializable {

    //List<String> name;
    ListView listView;
    SearchView searchView;
    ContactAdapter adapter;

    private static final String TAG = "CONTACT";
    List<Contact> contacts;
    List<String> displayNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listview);
        initData();
        adapter = new ContactAdapter(this, R.layout.item_list_contacts, contacts);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putLong("_ID", contacts.get(position).get_id());
                Intent intentRequest = new Intent(ListContactsActivity.this, ItemInfoActivity.class);
                intentRequest.putExtra("mypackage", bundle);
                startActivity(intentRequest);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_contacts, menu);
        MenuItem itemSearch = menu.findItem(R.id.mnSearch);
        searchView = (SearchView) itemSearch.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mnInsert) {
            return true;
        } else if (id == R.id.mnRefesh) {
            return true;
        } else if (id == R.id.mnSearch) {
            setVisible(true);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context_list_contact, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    /*void initData(){
        name = new ArrayList<>();
        name.add("Anh1");
        name.add("Anh2");
        name.add("Anh3");
        name.add("Anh4");
        name.add("Anh5");
        name.add("Anh6");
        name.add("Anh7");
        name.add("Anh8");
        name.add("Anh9");
        name.add("Anh10");
        name.add("Anh11");
        name.add("Anh12");
        name.add("Anh13");
        name.add("Anh14");
        name.add("Anh15");
        name.add("Anh16");
    }*/

    void initData() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI
                , new String[]{ContactsContract.RawContacts._ID,
                        ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY}, null, null, null, null);
        contacts = new ArrayList<>();
        displayNames = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact();
                    contact.set_id(cursor.getLong(cursor.getColumnIndex(ContactsContract.RawContacts._ID)));
                    contact.setDisplayName(cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts.DISPLAY_NAME_PRIMARY)));

                    contacts.add(contact);
                    displayNames.add(contact.getDisplayName());
                } while (cursor.moveToNext());
            }
        }
    }

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
        //adapter.getFilter().filter(newText);
        return true;
    }
}
