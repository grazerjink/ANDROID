package com.example.graze.mydemoexplorer;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainExplorerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.textPath)
    TextView tvPath;
    @BindView(R.id.listView)
    ListView listView;

    String mPath;

    List<String> mFileNames;
    List<String> mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_explorer);

        ButterKnife.bind(this);

        listView.setOnItemClickListener(this);
        //Lay duong dan root toi thu muc trong External
        mPath = Environment.getExternalStorageDirectory().getPath();
        getDir(mPath);
    }

    //ham lay duong dan root
    public void getDir(String pathDir) {
        tvPath.setText(pathDir);

        mFileNames = new ArrayList<>();
        mFilePath = new ArrayList<>();

        File dir = new File(pathDir);

        mFileNames.add("../");
        mFilePath.add(dir.getParent()); //Lay duong dan cua cha

        if(dir.isDirectory()) {
            File[] files = dir.listFiles();
            for(int i = 0 ; i < files.length ; i++) {
                mFileNames.add(files[i].getName());
                mFilePath.add(files[i].getAbsolutePath());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mFileNames);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        File file = new File(mFilePath.get(position));
        if(file.isDirectory() && !file.isHidden() && file.canRead()){
            getDir(file.getAbsolutePath());
        } else {
            //xem image
//            if (file.getAbsolutePath().contains(".jpg")) {
//                //lay danh sach file hinh anh
//                String[] files = file.getParentFile().list(new ExtensionsNameFilter(ExtensionsNameFilter.IMAGE_FILTER));
//                Intent intent = new Intent(this, ActivityGalleryScreen.class);
//                intent.putExtra("listImage", files);
//                intent.putExtra("parent", file.getParent() + "/");
//                startActivity(intent);
//            } else
//                Toast.makeText(this, file.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
