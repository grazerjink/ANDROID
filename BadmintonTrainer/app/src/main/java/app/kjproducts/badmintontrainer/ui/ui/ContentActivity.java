package app.kjproducts.badmintontrainer.ui.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;

import app.kjproducts.badmintontrainer.R;

public class ContentActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    private Integer images[] = {R.drawable.anh1, R.drawable.anh2};
    private int currImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        String index = getIntent().getStringExtra("item").toString();
        Snackbar.make(relativeLayout,"Đang cập nhật bài " + index + ". Xin quay lại sau..."  ,Snackbar.LENGTH_SHORT).show();
        initializeImageSwitcher();
        setInitialImage();
        setImageRotateListener();
     }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.translate_open_2,R.anim.translate_close_2);
    }

    private void initializeImageSwitcher() {
        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(ContentActivity.this);
                return imageView;
            }
        });

        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
    }

    private void setImageRotateListener() {
        final Button rotatebutton = (Button) findViewById(R.id.btnRotateImage);
        rotatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                currImage++;
                if (currImage == 2) {
                    currImage = 0;
                }
                setCurrentImage();
            }
        });
    }

    private void setInitialImage() {
        setCurrentImage();
    }

    private void setCurrentImage() {
        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setImageResource(images[currImage]);
    }
}
