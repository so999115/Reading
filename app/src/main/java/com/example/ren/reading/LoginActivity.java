package com.example.ren.reading;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

public class LoginActivity extends AppCompatActivity {

    private RelativeLayout mLayoutLogin;
    private BackBtnCloseHandler backBtnCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        mLayoutLogin = (RelativeLayout) findViewById(R.id.layout_login);

        LoginKakao.getInstance().setSession(this);
        LoginNaver.getInstance().init(this, (OAuthLoginButton) findViewById(R.id.naver_login));

        checkLogin();

        backBtnCloseHandler = new BackBtnCloseHandler(this);
    }

    private void checkLogin() {
        SharedPreferences pref = getSharedPreferences("REN_READING", Activity.MODE_PRIVATE);
        String type = pref.getString("LOGIN_TYPE", "");
        if (type.isEmpty()) {
            mLayoutLogin.setVisibility(View.VISIBLE);
        } else if (type.equals("n")) {
            LoginNaver.getInstance().login();
        }
    }

    @Override
    public void onBackPressed() {
        backBtnCloseHandler.onBackPressed();
    }

    @Override
    protected void onResume() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onResume();
    }

    //for kakao
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //for kakao
        if (LoginKakao.getInstance().onActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
