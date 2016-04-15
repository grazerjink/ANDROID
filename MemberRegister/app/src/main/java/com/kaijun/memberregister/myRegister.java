package com.kaijun.memberregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class myRegister extends AppCompatActivity {

    EditText edtUser;
    EditText edtPass;
    EditText edtComfirm;
    EditText edtEmail;
    Button btnCont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_register);

        edtUser = (EditText) findViewById(R.id.edtUsr);
        edtPass = (EditText) findViewById(R.id.edtPasswd);
        edtComfirm = (EditText) findViewById(R.id.edtComfirm);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnCont = (Button) findViewById(R.id.btnCont);

        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = edtUser.getText().toString();
                String str2 = edtPass.getText().toString();
                String str3 = edtComfirm.getText().toString();
                String str4 = edtEmail.getText().toString();

                if (str1.equals("")) {
                    Toast.makeText(myRegister.this, "Chưa nhập Username", Toast.LENGTH_SHORT).show();
                } else if (str2.equals("")) {
                    Toast.makeText(myRegister.this, "Chưa nhập Password", Toast.LENGTH_SHORT).show();
                } else if (str3.equals("")) {
                    Toast.makeText(myRegister.this, "Chưa nhập Confirm Password", Toast.LENGTH_SHORT).show();
                } else if (!str3.equals(str2)) {
                    Toast.makeText(myRegister.this, "Mật khẩu xác nhận không đúng", Toast.LENGTH_SHORT).show();
                } else if (str4.equals("")) {
                    Toast.makeText(myRegister.this, "Chưa nhập Email", Toast.LENGTH_SHORT).show();
                } else if (!str4.contains("@")) {
                    Toast.makeText(myRegister.this, "Email sai cú pháp", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(myRegister.this, "Đã đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent goToSign = new Intent(myRegister.this, mySign.class);
                    startActivity(goToSign);
                }
            }
        });

    }
}
