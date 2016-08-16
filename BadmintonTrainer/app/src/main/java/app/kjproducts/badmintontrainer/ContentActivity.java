package app.kjproducts.badmintontrainer;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import app.kjproducts.badmintontrainer.adapter.DepthPageTransformer;
import app.kjproducts.badmintontrainer.adapter.FragmentAdapter;
import app.kjproducts.badmintontrainer.database.dao.DetailDAO;
import app.kjproducts.badmintontrainer.model.Child;
import app.kjproducts.badmintontrainer.model.Detail;
import me.relex.circleindicator.CircleIndicator;

public class ContentActivity extends AppCompatActivity {
    DetailDAO detailDAO;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tvTitle;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_content);
        tv1 = (TextView) findViewById(R.id.mergeText1);
        tv2 = (TextView) findViewById(R.id.mergeText2);
        tv3 = (TextView) findViewById(R.id.mergeText3);
        tv4 = (TextView) findViewById(R.id.mergeText4);
        viewPager = (ViewPager) findViewById(R.id.pageImage);
        tvTitle = (TextView) findViewById(R.id.tvContentTilte);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        detailDAO = new DetailDAO(this, MainActivity.PATH);

        Intent intent = getIntent();
        long id_detail = intent.getLongExtra("id_detail", 0);
        String title = intent.getStringExtra("title");

        tvTitle.setText(title);

        List<Detail> detailList = detailDAO.getAll();

        List<String> content = new ArrayList<>();
        List<String> imageLink = new ArrayList<>();
        List<Integer> tong = new ArrayList<>();
        for (Detail detail : detailList) {
            if (detail.getId_detail() == id_detail) {
                tong.add((int) detail.getId());
                content.add(detail.getContent());
                imageLink.add(detail.getImage());
            }
        }

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager(), imageLink);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());

        indicator.setViewPager(viewPager);

        int i = tong.size();
        switch (i) {
            case 1:
                tv1.setText(content.get(0).toString());
                tv2.setVisibility(View.INVISIBLE);
                tv3.setVisibility(View.INVISIBLE);
                tv4.setVisibility(View.INVISIBLE);
                break;
            case 2:
                tv1.setText(content.get(0).toString());
                tv2.setText(content.get(1).toString());
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.INVISIBLE);
                tv4.setVisibility(View.INVISIBLE);
                break;
            case 3:
                tv1.setText(content.get(0).toString());
                tv2.setText(content.get(1).toString());
                tv3.setText(content.get(2).toString());
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                tv4.setVisibility(View.INVISIBLE);
                break;
            case 4:
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                tv4.setVisibility(View.VISIBLE);
                tv1.setText(content.get(0).toString());
                tv2.setText(content.get(1).toString());
                tv3.setText(content.get(2).toString());
                tv4.setText(content.get(3).toString());
                break;
        }
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
            Intent intent = new Intent(this, GiveIdeaActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.listIdea) {
            Intent intent = new Intent(this, IdeaActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
