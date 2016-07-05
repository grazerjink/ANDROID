package app.graze.demonotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void KhoiTaoNotification(View v) {
        final NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        final Notification.Builder notifyBuilder = new Notification.Builder(this);
        notifyBuilder.setContentTitle("DEMO NOTIFY IN ANDROID");
        notifyBuilder.setContentText("This is the first demo about Notification");
        notifyBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notifyBuilder.setAutoCancel(true);

        Intent intent = new Intent(this, HienThiNotification.class);
        //Khai bao 1 PendingIntent
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
        //Click vao se bat ung dung cua chung ta
        notifyBuilder.setContentIntent(pIntent); //Can 1 cai PendingIntent

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i;
                for(i = 0; i<= 100; i+=10) {
                    notifyBuilder.setProgress(100, i, false);
                    notifyManager.notify(1, notifyBuilder.build());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                notifyBuilder.setProgress(0, 0, false);
                notifyBuilder.setContentText("Download Completed");
                notifyManager.notify(1, notifyBuilder.build());
            }
        }
        ).start();
        notifyManager.notify(1, notifyBuilder.build());
    }
}
