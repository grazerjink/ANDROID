package app.graze.demoservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by graze on 02/07/2016.
 */
public class MusicService extends Service {

    MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(MusicService.this, "Service created", Toast.LENGTH_SHORT).show();
        mp = MediaPlayer.create(this,R.raw.marvingaye);
        mp.setLooping(false);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(MusicService.this, "Music is playing...!", Toast.LENGTH_SHORT).show();
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(MusicService.this, "Stopped playing music!", Toast.LENGTH_SHORT).show();
        mp.stop();
    }
}
