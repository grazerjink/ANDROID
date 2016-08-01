package app.kjproducts.badmintontrainer.ui.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import app.kjproducts.badmintontrainer.R;
import app.kjproducts.badmintontrainer.ui.modules.LessonPagerAdapter;

public class LessonPagerActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    LessonPagerAdapter lessonPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_pager);
        viewPager = (ViewPager) findViewById(R.id.lessonPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        lessonPagerAdapter = new LessonPagerAdapter(getFragmentManager());


        viewPager.setAdapter(lessonPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        LessonPagerActivity.super.onBackPressed();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        dialog.dismiss();
                        break;
                }
            }
        };

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thoát ứng dụng")
                .setMessage("Bạn chắc chắn muốn thoát chứ ?")
                .setPositiveButton("THOÁT", dialogClickListener)
                .setNegativeButton("KHÔNG", dialogClickListener);

        final AlertDialog dialogMain = builder.create();
        dialogMain.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                dialogMain.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(244, 43, 36));
                dialogMain.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.rgb(244, 43, 36));
            }
        });
        dialogMain.show();
    }
}
