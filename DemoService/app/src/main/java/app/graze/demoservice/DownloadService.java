package app.graze.demoservice;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by graze on 02/07/2016.
 */
public class DownloadService extends IntentService {

    private int ketquaxuly = Activity.RESULT_CANCELED;
    public static final String PATH = "Ten duong dan";
    public static final String FILE_NAME = "Ten tap tin";
    public static final String RESULT = "Ket qua";
    public static final String SAVED_FILE_PATH = "duongdanluufile";


    public DownloadService() {
        super("Download Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String path = intent.getStringExtra(PATH);
        String filename = intent.getStringExtra(FILE_NAME);

        File file = new File(Environment.getExternalStorageDirectory(), filename);
        if (file.exists()) {
            file.delete();
        }
        try {
            URL url = new URL(path);
            InputStream stream = url.openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            FileOutputStream fos = new FileOutputStream(file.getPath()); //lay duong dan luu vao bo nho SD Card
            int next = -1;
            while ((next = reader.read()) != -1) {
                fos.write(next);
            }

            ketquaxuly = Activity.RESULT_OK;
            fos.close();
            reader.close();
            stream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendBroadCastReceiver(path, ketquaxuly);
    }

    public void sendBroadCastReceiver(String path, int result) {
        Intent intent = new Intent("app.graze.demoservice");
        intent.putExtra(SAVED_FILE_PATH, path);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
