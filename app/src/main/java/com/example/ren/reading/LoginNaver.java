package com.example.ren.reading;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import com.nhn.android.naverlogin.OAuthLoginHandler;
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton;

/**
 * Created by Ren on 2017-10-26.
 */

public class LoginNaver {

    private static String OAUTH_CLIENT_ID="0BWQ8Fm1aKk7tkeMHkAu";
    private static String OAUTH_CLIENT_SECRET="YCx2m0QVMF";
    private static String OAUTH_CLIENT_NAME="네이버 아이디로 로그인";

    private static LoginNaver uniqueInstance;

    private static OAuthLogin mOAuthLoginInstance;
    private OAuthLoginButton mOAuthLoginButton;
    private Activity mActivity;

    private LoginNaver() {
    }

    public static LoginNaver getInstance() {
        if (uniqueInstance == null) {
            synchronized (LoginNaver.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new LoginNaver();
                }
            }
        }
        return uniqueInstance;
    }

    public void init(Activity activity, OAuthLoginButton button) {
        OAuthLoginDefine.DEVELOPER_VERSION = true;
        mActivity = activity;
        mOAuthLoginInstance = OAuthLogin.getInstance();
        mOAuthLoginInstance.init(activity, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);

        mOAuthLoginButton = button;
        mOAuthLoginButton.setOAuthLoginHandler(mOAuthLoginHandler);
    }

    public void login() {
        mOAuthLoginInstance.startOauthLoginActivity(mActivity, mOAuthLoginHandler);
    }

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if(success) {
                String accessToken = mOAuthLoginInstance.getAccessToken(mActivity);
                String refreshToken = mOAuthLoginInstance.getRefreshToken(mActivity);
                long expiresAt = mOAuthLoginInstance.getExpiresAt(mActivity);
                String tokenType = mOAuthLoginInstance.getTokenType(mActivity);
/*
                if (OAuthLoginDefine.DEVELOPER_VERSION) {
                    Toast.makeText(mActivity, "accessToken : " + accessToken, Toast.LENGTH_SHORT).show();
                    Toast.makeText(mActivity, "refreshToken : " + refreshToken, Toast.LENGTH_SHORT).show();
                    Toast.makeText(mActivity, "Expires : " + String.valueOf(expiresAt), Toast.LENGTH_SHORT).show();
                    Toast.makeText(mActivity, "TokenType : " + tokenType, Toast.LENGTH_SHORT).show();
                    Toast.makeText(mActivity, "AuthState : " + mOAuthLoginInstance.getState(mActivity).toString(), Toast.LENGTH_SHORT).show();
                }
*/
                //requestApi(accessToken, "https://openapi.naver.com/v1/nid/me");
                HttpUtils.getInstance().requestNaverMemberInfo(accessToken, new StartActivityImpl() {
                    @Override
                    public void redirect(String type, String id, String pwd, String email, String nick) {
                        setMemberInfo(type, id, pwd, email, nick);
                        //startMainActivity();
                        startStartActivity();
                    }
                });

            } else {
                String errorCode = mOAuthLoginInstance.getLastErrorCode(mActivity).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(mActivity);
                Toast.makeText(mActivity, "errorCode : "+errorCode+", errorDesc : "+errorDesc, Toast.LENGTH_SHORT).show();
            }

        }
    };

    private void requestApi(String accessToken, String url) {
        //String a = mOAuthLoginInstance.requestApi(mActivity, accessToken, url);
        //Log.d("asdf", a);
    }

    private void setMemberInfo (String type, String id, String pwd, String email, String nick) {
        GlobalApplication.gMemberInfo.setUserInfo(type, id, pwd, email, nick);
    }
/*
    private void startMainActivity() {
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mActivity.startActivity(intent);
        mActivity.finish();
    }
*/
    private void startStartActivity() {
        Intent intent = new Intent(mActivity, StartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mActivity.startActivity(intent);
        mActivity.finish();
    }
    private void startLoginActivity(Activity activity) {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    public void logout(Activity activity) {
        mOAuthLoginInstance.logout(activity);
        //사용자 정보 삭제
        GlobalApplication.gMemberInfo.logOut();
        //로그인 화면으로 이동
        startLoginActivity(activity);
    }
}
