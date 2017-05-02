package kj.sgusupport.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import kj.sgusupport.login.LoginActivity;
import kj.sgusupport.main.MainActivity;

/**
 * Created by KJ on 28/09/2016.
 */

public class LoginMethod {

    LoginActivity loginActivity;

    public LoginMethod(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public void hienKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    loginActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public void kiemTraMaSV(final EditText editText, final TextInputLayout textInputLayout) {
        if (editText.getText().toString().length() <= 0) {
            textInputLayout.setError("Chưa nhập mã số sinh viên");
        } else if (editText.getText().toString().length() != 10) {
            textInputLayout.setError("Nhập sai mã số sinh viên");
        } else if (editText.getText().toString().length() == 10) {
            if (!editText.getText().toString().substring(0, 3).equals("311")) {
                textInputLayout.setError("Nhập sai cú pháp mã sinh viên");
            } else {
                textInputLayout.setError(null);
                Intent main = new Intent(loginActivity, MainActivity.class);
                loginActivity.startActivity(main);
            }
        }
    }

    public String layInfoSV() {

        return null;
    }
}
