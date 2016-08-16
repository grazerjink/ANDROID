package app.kjproducts.badmintontrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import app.kjproducts.badmintontrainer.adapter.CommentAdapter;
import app.kjproducts.badmintontrainer.database.dao.CommentDAO;
import app.kjproducts.badmintontrainer.model.Comment;

public class IdeaActivity extends AppCompatActivity {

    ListView listView;
    CommentDAO commentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea);
        commentDAO = new CommentDAO(this, MainActivity.PATH);
        listView = (ListView) findViewById(R.id.ideaListView);
        List<Comment> commentList = commentDAO.getAll();
        listView.setAdapter(new CommentAdapter(this, R.layout.list_idea_item, commentList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_idea_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.giveIdea) {
            Intent intent = new Intent(this, GiveIdeaActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.refresh) {
            List<Comment> commentList = commentDAO.getAll();
            CommentAdapter adapter = new CommentAdapter(this, R.layout.list_idea_item, commentList);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
