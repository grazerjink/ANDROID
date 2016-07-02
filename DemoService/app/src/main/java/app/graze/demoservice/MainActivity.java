package app.graze.demoservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //    Button btnStart;
//    Button btnStop;
    Button btnDownload;
    TextView tvInfo;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String path_name = intent.getStringExtra(DownloadService.PATH);
            int result = intent.getIntExtra(DownloadService.RESULT, 0);
            if (result == Activity.RESULT_OK) {
                Toast.makeText(MainActivity.this, "Download Succeed", Toast.LENGTH_SHORT).show();
                tvInfo.setText("Download Succeed");
            } else {
                Toast.makeText(MainActivity.this, "Download Failed", Toast.LENGTH_SHORT).show();
                tvInfo.setText("Download Failed");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        btnStart = (Button) findViewById(R.id.btnStart);
//        btnStop = (Button) findViewById(R.id.btnStop);
//
//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent iPlayMusic = new Intent(MainActivity.this,MusicService.class);
//                startService(iPlayMusic);
//            }
//        });
//
//        btnStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent iStopMusic = new Intent(MainActivity.this,MusicService.class);
//                stopService(iStopMusic);
//            }
//        });
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        btnDownload = (Button) findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDownload();
                tvInfo.setText("Waiting for downloading...!");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver,new IntentFilter("app.graze.demoservice"));
    }

    private void goToDownload() {
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra(DownloadService.PATH, "http://au.vtcgame.vn/vipshop/trang-suc-c5/tui-cheo-yes--8623.html");
        intent.putExtra(DownloadService.FILE_NAME, "tuiyes.html");
        startService(intent);
    }
}
