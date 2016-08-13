package app.kjproducts.apiservice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.kjproducts.apiservice.model.User;
import app.kjproducts.apiservice.network.APIServiceBuilder;
import app.kjproducts.apiservice.network.DemoAPIService;
import app.kjproducts.apiservice.network.NetworkRequest;
import app.kjproducts.apiservice.utils.APIKeySharePreference;
import rx.Subscription;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;
    Button btnLoginWithFB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPass);
        btnLoginWithFB = (Button) findViewById(R.id.btnLoginWithFB);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }

    Subscription subscription;
    DemoAPIService demoAPIService;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    public void doLoginWithFB(View v) {

    }

    public void doLogin(View v) {
        String apiKey = APIKeySharePreference.getKey(this);
        demoAPIService = (DemoAPIService)
                APIServiceBuilder.buildAPIService(this,Constants.DOMAIN);
        if (apiKey.isEmpty()) {
            subscription = NetworkRequest.performAsyncRequest(this, demoAPIService.getAPIKey(),
                    data -> {
                        String newAPIKey = data.getApiKey();
                        if (newAPIKey != null && !newAPIKey.isEmpty()) {
                            APIKeySharePreference.saveAPIKey(this, newAPIKey);
                            data.setStatus(true);
                        } else {
                            data.setStatus(false);
                        }
                        return data;
                    },
                    next -> {
                        if (next.getStatus()) {
                            Snackbar.make(btnLogin, "Lấy API key thành công !", Snackbar.LENGTH_SHORT).show();
                            login();
                        } else {
                            Snackbar.make(btnLogin, "Không lấy được API key !", Snackbar.LENGTH_SHORT).show();
                        }

                    });
        } else {
            login();
        }
    }


    public void login() {
        Log.d(TAG, "Login");
        // TODO: Implement your own authentication logic here.

        User user = new User();
        user.setApi(APIKeySharePreference.getKey(this));
        user.setPass(edtPassword.getText().toString());
        user.setUserName(edtEmail.getText().toString());
        subscription = NetworkRequest.
                performAsyncRequest(this, demoAPIService.login("application/json",user),
                        data -> {
                            if(data.getStatus()){

                            }
                            return data;
                        },
                        next -> {
                            if (next.getStatus()) {
                                //di tiep toi man hinh mainActivity
                                Intent intent = new Intent(this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                //xuat thong bao
                                Snackbar.make(btnLogin, next.getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        });

    }
}