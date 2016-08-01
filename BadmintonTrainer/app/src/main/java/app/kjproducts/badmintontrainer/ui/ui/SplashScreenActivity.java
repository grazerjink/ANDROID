package app.kjproducts.badmintontrainer.ui.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import app.kjproducts.badmintontrainer.R;

public class SplashScreenActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3500;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.fade_in_splash);
        animation.reset();
        relativeLayout.clearAnimation();
        relativeLayout.startAnimation(animation);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent secondSplashIntent = new Intent(SplashScreenActivity.this, SecondSplashActivity.class);
                SplashScreenActivity.this.startActivity(secondSplashIntent);
                overridePendingTransition(R.anim.fade_in_splash, R.anim.fade_out_activity);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void onBackPressed() {
        //No do anything
    }
}
