package graze.mydemoanimation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    Button btnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btnSecond = (Button) findViewById(R.id.btnGotoSecond);
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent second = new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(second);
                overridePendingTransition(R.anim.translate_activities_in_left,R.anim.translate_activities_in_left);
            }
        });

    }
}
