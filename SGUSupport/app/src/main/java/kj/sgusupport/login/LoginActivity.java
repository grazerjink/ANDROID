package kj.sgusupport.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kj.sgusupport.R;

public class LoginActivity extends AppCompatActivity {

    final static public String MASV = "maSV";

    Button btnOK;
    EditText edtMSV;
    TextInputLayout textInputLayout;

    SharedPreferences ref;
    SharedPreferences.Editor editor;
    LoginMethod method = new LoginMethod(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ref = getSharedPreferences("SharedRef", Context.MODE_PRIVATE);
        anhXa();

        edtMSV.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Do whatever you want here
                    method.kiemTraMaSV(edtMSV, textInputLayout);
                    return true;
                }
                return false;
            }
        });
    }

    void doLogin(View v) {
        method.kiemTraMaSV(edtMSV, textInputLayout);
    }

    void anhXa() {
        btnOK = (Button) findViewById(R.id.btnOK);
        edtMSV = (EditText) findViewById(R.id.edtMSV);
        textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        editor = ref.edit();
        editor.putString(MASV, edtMSV.getText().toString());
        editor.commit();
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
