package com.example.graze.myfirstproject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by graze on 18/05/2016.
 */
public class SignupFragment extends Fragment {
    public String getTagName(){
        return "signupFragment";
    }

    @BindView(R.id.btnCreate)
    Button btnCreate;
    @BindView(R.id.edtEmailSignup)
    EditText edtEmail;
    @BindView(R.id.edtPasswordSignup)
    EditText edtPass;
    @BindView(R.id.edtUsername)
    EditText edtUsername;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.signup_fragment_layout,container,false);
        ButterKnife.bind(this,root);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        return root;
    }

    public void signup(){
        if(!validate()) {
            signupFailed();
            return;
        }
        btnCreate.setEnabled(true);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),R.style.AppTheme_RedPink);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                signupSucceed();
                progressDialog.dismiss();
            }
        }, 2500);
    }

    public void signupSucceed(){
        btnCreate.setEnabled(true);
        //Luu lai du lieu
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SharedRef", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",edtUsername.getText().toString());
        editor.putString("email",edtEmail.getText().toString());
        editor.putString("pass",edtPass.getText().toString());
        editor.apply();
        Intent intent = new Intent(getActivity(),ContentMainActivity.class);
        startActivity(intent);
    }

    public void signupFailed(){
        Toast.makeText(getActivity(),"Sign Up failed", Toast.LENGTH_LONG).show();
        btnCreate.setEnabled(true);
    }

    public boolean validate(){
        String username = edtUsername.getText().toString();
        String pass = edtPass.getText().toString();
        String email = edtEmail.getText().toString();
        boolean valid = true;

        if(username.isEmpty() || username.length() < 4 || username.length() > 10){
            edtUsername.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            edtUsername.setError(null);
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmail.setError("Enter a valid email address");
            valid = false;
        } else {
            edtEmail.setError(null);
        }

        if(pass.isEmpty() || pass.length() > 10 || pass.length() < 4) {
            edtPass.setError("Between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            edtPass.setError(null);
        }

        return valid;
    }
}
