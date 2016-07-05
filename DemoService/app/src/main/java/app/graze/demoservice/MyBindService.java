package app.graze.demoservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by graze on 05/07/2016.
 */
public class MyBindService extends Service {

    private Binder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public double TinhHieu2So(double a, double b) {
        return a/b;
    }

    public class LocalBinder extends Binder {
        MyBindService getService(){
            return MyBindService.this;
        }
    }
}
