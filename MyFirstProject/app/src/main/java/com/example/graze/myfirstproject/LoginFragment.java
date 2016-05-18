package com.example.graze.myfirstproject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.TextAppearanceSpan;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

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
    @BindView(R.id.btnSignup)
    Button btnSignup;
    @BindView(R.id.cbShow)
    CheckBox cbShow;

    public String getTagName(){
        return "loginFragment";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_fragment_layout,container,false);
        ButterKnife.bind(this,root);
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
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        return root;
    }

    public void login(){
        if(!validate()) {
            loginFailed();
            return;
        }

        btnSignup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),R.style.AppTheme_RedPink);
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

    public void loginSucceed(){
        btnSignup.setEnabled(true);
        //Chuyen qa trang noi dung
        Intent intent = new Intent(getActivity(),ContentMainActivity.class);
        startActivity(intent);
    }

    public void loginFailed(){
        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_LONG).show();
        btnSignup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
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

        //Save by SharedReference
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SharedRef", Context.MODE_PRIVATE);
        if(email.equals(sharedPreferences.getString("email",""))
                && password.equals(sharedPreferences.getString("pass",""))){
            valid = true;
        } else {
            valid = false;
        }
        return valid;
    }
}
