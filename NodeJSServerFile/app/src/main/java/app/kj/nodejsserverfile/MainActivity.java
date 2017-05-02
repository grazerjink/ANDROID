package app.kj.nodejsserverfile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private final String CLIENT_SEND_IMAGE = "CLIENT_SEND_IMAGE";
    private final String SERVER_SEND_IMAGE = "SERVER_SEND_IMAGE";
    private final String CLIENT_SEND_REQUEST = "CLIENT_SEND_REQUEST";
    private final String CLIENT_SEND_SOUND = "CLIENT_SEND_SOUND";

    private final int REQUEST_TAKE_PHOTO = 123;
    private final int REQUEST_CHOOSE_PHOTO = 321;

    private Recorder recorder;

    Socket mSocket;
    Emitter.Listener onNewImage;

    {
        try {
            mSocket = IO.socket("http://192.168.1.3:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        onNewImage = new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                handleNewImage(args[0]);
            }
        };
    }

    Button btnChup;
    Button btnGui;
    Button btnChon;
    Button btnRequest, btnGhi, btnDung, btnSend;
    ImageView imgShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSocket();
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendImage();
            }
        });

        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

        btnChup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromServer();
            }
        });

        btnGhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recorder.start();
            }
        });

        btnDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recorder.stop();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSound();
            }
        });
    }

    private void sendImage() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_input_add);
        byte[] bytes = getByteArrayFromBitmap(bitmap);
        mSocket.emit(CLIENT_SEND_IMAGE, bytes);
    }

    private void addControls() {
        btnChon = (Button) findViewById(R.id.btnChon);
        btnChup = (Button) findViewById(R.id.btnChup);
        btnGui = (Button) findViewById(R.id.btnGui);
        btnRequest = (Button) findViewById(R.id.btnRequest);
        btnGhi = (Button) findViewById(R.id.btnGhiRec);
        btnDung = (Button) findViewById(R.id.btnDungSound);
        btnSend = (Button) findViewById(R.id.btnSendSound);
        imgShow = (ImageView) findViewById(R.id.imageView);
        recorder = new Recorder(this);
    }

    private void initSocket() {
        mSocket.connect();
        mSocket.on(SERVER_SEND_IMAGE, onNewImage);
    }

    private void handleNewImage(final Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                byte[] imageByteArray = (byte[]) arg;
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray,0,imageByteArray.length);
                imgShow.setImageBitmap(bitmap);
            }
        });
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    private void choosePicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
    }

    private void getImageFromServer(){
        mSocket.emit(CLIENT_SEND_REQUEST, "abc");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHOOSE_PHOTO && resultCode == RESULT_OK) {

            try {
                Uri imageUri = data.getData();
                InputStream is = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                bitmap = resize(bitmap, 100, 100);
                byte[] bytes = getByteArrayFromBitmap(bitmap);
                mSocket.emit(CLIENT_SEND_IMAGE, bytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            bitmap = resize(bitmap, 100, 100);
            byte[] bytes = getByteArrayFromBitmap(bitmap);
            mSocket.emit(CLIENT_SEND_IMAGE, bytes);
        }
    }

    public byte[] getByteArrayFromBitmap(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > 1) {
                finalWidth = (int) ((float)maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float)maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }

    private byte[] getByteArrayFromLocalFile(String path){
        File file = new File(path);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bytes;
    }

    private void sendSound(){
        // path
       String outputFile = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/khoapham.3gpp";
        byte[] bytes = getByteArrayFromLocalFile(outputFile);
        mSocket.emit(CLIENT_SEND_SOUND,bytes);
    }

}
