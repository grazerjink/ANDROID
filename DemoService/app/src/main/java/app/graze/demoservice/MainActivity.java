package app.graze.demoservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//    Button btnStart;
//    Button btnStop;
//    Button btnDownload;
//    TextView tvInfo;
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String path_name = intent.getStringExtra(DownloadService.PATH);
//            int result = intent.getIntExtra(DownloadService.RESULT, 0);
//            if (result == Activity.RESULT_OK) {
//                Toast.makeText(MainActivity.this, "Download Succeed", Toast.LENGTH_SHORT).show();
//                tvInfo.setText("Download Succeed");
//            } else {
//                Toast.makeText(MainActivity.this, "Download Failed", Toast.LENGTH_SHORT).show();
//                tvInfo.setText("Download Failed");
//            }
//        }
//    };


    EditText edtFirst, edtSecond;
    TextView tvKetQua;
    MyBindService myBindService;
    boolean mBound;
    ServiceConnection connectionMyService = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
            myBindService = null;
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBound = true;
            MyBindService.LocalBinder binder = (MyBindService.LocalBinder) service;
            myBindService = binder.getService();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////TODO: DEMO MUSIC SERVICE
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


        ////TODO DEMO DOWNLOAD SERVICE (INTENT SERVICE)
//        tvInfo = (TextView) findViewById(R.id.tvInfo);
//        btnDownload = (Button) findViewById(R.id.btnDownload);
//
//        btnDownload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                goToDownload();
//                tvInfo.setText("Waiting for downloading...!");
//            }
//        });

        edtFirst = (EditText) findViewById(R.id.edtFirst);
        edtSecond = (EditText) findViewById(R.id.edtSecond);
        tvKetQua = (TextView) findViewById(R.id.tvKetQua);
    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        unregisterReceiver(receiver);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        registerReceiver(receiver,new IntentFilter("app.graze.demoservice"));
//    }
//
//    private void goToDownload() {
//        Intent intent = new Intent(this, DownloadService.class);
//        intent.putExtra(DownloadService.PATH, "http://au.vtcgame.vn/vipshop/trang-suc-c5/tui-cheo-yes--8623.html");
//        intent.putExtra(DownloadService.FILE_NAME, "tuiyes.html");
//        startService(intent);
//    }


    @Override
    protected void onStart() {
        super.onStart();
        //Khi Ung dung bat dau
        Intent intent = new Intent(MainActivity.this,MyBindService.class);
        bindService(intent,connectionMyService,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myBindService.unbindService(connectionMyService);
        mBound = false;
    }

    public void Tinh(View v) {
        double a = Double.parseDouble(edtFirst.getText().toString());
        double b = Double.parseDouble(edtSecond.getText().toString());
        tvKetQua.setText(String.valueOf(myBindService.TinhHieu2So(a,b)));
    }
}
