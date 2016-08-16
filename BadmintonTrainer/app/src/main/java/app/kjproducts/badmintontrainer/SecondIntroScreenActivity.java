package app.kjproducts.badmintontrainer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class SecondIntroScreenActivity extends AppCompatActivity {
    private final int TIME_SHOW = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second_intro_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SecondIntroScreenActivity.this, MainActivity.class);
                SecondIntroScreenActivity.this.startActivity(intent);
                SecondIntroScreenActivity.this.finish();
            }
        }, TIME_SHOW);
    }

    @Override
    public void onBackPressed() {
        //Nothing to do, you have to wait this activity is finished
    }
}
