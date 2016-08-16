package app.kjproducts.badmintontrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.kjproducts.badmintontrainer.database.dao.CommentDAO;
import app.kjproducts.badmintontrainer.model.Comment;

public class GiveIdeaActivity extends AppCompatActivity {

    EditText edtUser;
    EditText edtComment;
    Button btnSend;

    CommentDAO commentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_idea);
        commentDAO = new CommentDAO(this,MainActivity.PATH);

        edtUser = (EditText) findViewById(R.id.edtUserName);
        edtComment = (EditText) findViewById(R.id.tareComment);
        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtUser.getText().toString();
                String cmt = edtComment.getText().toString();
                Comment comment = new Comment(user,cmt);
                commentDAO.insert(comment);
                Toast.makeText(GiveIdeaActivity.this, "Đã gửi ý kiến thành công !", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
