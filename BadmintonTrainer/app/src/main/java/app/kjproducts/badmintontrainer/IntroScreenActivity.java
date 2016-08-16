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

public class IntroScreenActivity extends AppCompatActivity {

    private final int TIME_SHOW = 3500;
    RelativeLayout firstIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro_screen);

        firstIntro = (RelativeLayout) findViewById(R.id.introFirst);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_effect);
        animation.reset();
        firstIntro.clearAnimation();
        firstIntro.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroScreenActivity.this, SecondIntroScreenActivity.class);
                IntroScreenActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.fade_in_effect, R.anim.fade_out_effect);
                IntroScreenActivity.this.finish();
            }
        }, TIME_SHOW);
    }

    @Override
    public void onBackPressed() {
        //Nothing to do, you have to wait this activity is finished
    }
}
