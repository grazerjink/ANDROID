package com.example.graze.myfirstproject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by graze on 18/05/2016.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.edtEmailLogin)
    EditText edtEmail;
    @BindView(R.id.edtPasswordLogin)
    EditText edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.cbShow)
    CheckBox cbShow;

    public String getTagName() {
        return "loginFragment";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment_layout, container, false);
        ButterKnife.bind(this, root);
        cbShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    //Show password
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //Hide password
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        return root;
    }

    public void login() {
        if (!validate()) {
            loginFailed();
            return;
        }
        btnLogin.setEnabled(true);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        loginSucceed();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 2500);
    }

    public void loginSucceed() {
        btnLogin.setEnabled(true);
        Intent intent = new Intent(getActivity(), ContentMainActivity.class);
        startActivity(intent);
    }

    public void loginFailed() {
        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_LONG).show();
        btnLogin.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Enter a valid email address");
            valid = false;
        } else {
            edtEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            edtPassword.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            edtPassword.setError(null);
        }

        //Kiem tra trong file co dung email va pass khong ?
        //tien hanh doc file
        String content = readFile();
        //Content co du lieu tien hanh cat tung dong mot ra
        String[] tokens = content.split("\n");
        for (String token : tokens) {
            //Doc moi dong rui tien hanh cat tung thanh phan trong moi dong ra...
            String[] tokens_2 = token.split("\t");
            //So sanh cai nguoi dung nhap vs du lieu trong file
            if (!tokens_2[1].equals(edtEmail.getText().toString()) && !tokens_2[2].equals(edtPassword.getText().toString()))
                valid = false;
            else {
                //Luu lai du lieu
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SharedRef", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", tokens_2[0]);
                editor.putString("email",tokens_2[1]);
                editor.putString("pass", tokens_2[2]);
                editor.apply();
            }
        }
        return valid;
    }

    public String readFile() {
        try {
            File dir = getActivity().getFilesDir();
            FileReader reader = new FileReader(dir.getAbsoluteFile() + "/account");
            char[] buffer = new char[256];
            int len = 0;
            String content = "";
            while ((len = reader.read(buffer)) != -1) {
                content += new String(buffer, 0, len);
            }
            reader.close();
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
