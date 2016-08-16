package app.kjproducts.badmintontrainer;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.model.ParentListItem;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.kjproducts.badmintontrainer.adapter.ExerciseExpandableRecyclerAdapter;
import app.kjproducts.badmintontrainer.database.dao.ChildDAO;
import app.kjproducts.badmintontrainer.database.dao.ExerciseDAO;
import app.kjproducts.badmintontrainer.list.ListExercise;
import app.kjproducts.badmintontrainer.model.Child;
import app.kjproducts.badmintontrainer.model.Exercise;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public static String PATH;
    ChildDAO childDAO;
    ExerciseDAO exerciseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        PATH = getFilesDir().getAbsolutePath() + "/badminton";
        recyclerView = (RecyclerView) findViewById(R.id.recExercise);

        initDB();
        childDAO = new ChildDAO(this, PATH);
        exerciseDAO = new ExerciseDAO(this, PATH);

        List<ListExercise> mListTong = new ArrayList<>();
        //LOAD DATA LEN
        List<Exercise> exercises = exerciseDAO.getAll();
        List<Child> childList = childDAO.getAll();

        //SET TIEU DE CHO PARENT
        for (Exercise exercise : exercises) {
            ListExercise eachParentItem = new ListExercise(exercise.getId_index(), exercise.getTitle());
            mListTong.add(eachParentItem);
        }

        //SET TIEU DE CHO CON CUA PARENT
        final List<ParentListItem> parentListItems = new ArrayList<>();
        for (ListExercise mEachItem : mListTong) {
            List<Child> childItemList = new ArrayList<>();
            for (Child child : childList) {
                if (mEachItem.getId_index() == child.getId_child()) {
                    childItemList.add(new Child(child.getId_child(), child.getChildImage(), child.getChildName(), child.getId_detail()));
                }
            }
            mEachItem.setChildItemList(childItemList);
            parentListItems.add(mEachItem);
        }

        final ExpandableRecyclerAdapter adapter = new ExerciseExpandableRecyclerAdapter(this, parentListItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.giveIdea) {
            Intent intent = new Intent(this,GiveIdeaActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.listIdea) {
            Intent intent = new Intent(this,IdeaActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void initDB() {
        File file = new File(PATH);
        if (!file.exists()) {
            AssetManager assetManager = getAssets();
            try {
                BufferedInputStream bis = new BufferedInputStream(assetManager.open("badminton"));
                FileOutputStream fos = openFileOutput("badminton", Context.MODE_PRIVATE);

                byte[] buffer = new byte[512];
                int i = 0;
                while ((i = bis.read(buffer)) != -1) {
                    fos.write(buffer, 0, i);
                }

                bis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
