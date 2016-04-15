package com.kaijun.memberregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class mySign extends AppCompatActivity {

    Button btnSignin;
    EditText edtUserName;
    EditText edtPasswd;
    TextView txtvReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sign);
        btnSignin = (Button) findViewById(R.id.btnSign);
        txtvReg = (TextView) findViewById(R.id.txtvReg);
        edtUserName = (EditText) findViewById(R.id.edtUser);
        edtPasswd = (EditText) findViewById(R.id.edtPass);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass = edtPasswd.getText().toString();
                String userName = edtUserName.getText().toString();

                if (userName.equals("admin") && pass.equals("123456")) {
                    Toast.makeText(mySign.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(mySign.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        txtvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToReg = new Intent(mySign.this, myRegister.class);
                startActivity(goToReg);
            }
        });
    }
}
