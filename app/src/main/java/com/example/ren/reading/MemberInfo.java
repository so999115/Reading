package com.example.ren.reading;

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;

/**
 * Created by Ren on 2017-10-26.
 */

public class MemberInfo {
    private String mType;
    private String mId;
    private String mPwd;
    private String mEmail;
    private String mNick;
    private boolean mLoginYn;

    public MemberInfo() {
        init();
    }

    public MemberInfo(String type, String id, String pwd, String email, String nick) {
        setUserInfo(type, id, pwd, email, nick);
    }

    private void init() {
        mType = "";
        mId = "";
        mPwd = "";
        mEmail = "";
        mNick = "";
        mLoginYn = false;
    }

    public void setUserInfo(String type, String id, String pwd, String email, String nick) {
        mType = type;
        mId = id;
        mPwd = pwd;
        mEmail = email;
        mNick = nick;
        mLoginYn = true;
        setLogin();
    }

    private void setLogin() {
        SharedPreferences pref = GlobalApplication.getGlobalApplicationContext().getSharedPreferences("REN_READING", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("LOGIN_TYPE", mType);
        editor.apply();
    }

    public boolean isLogin() {
        return mLoginYn;
    }

    public boolean isKakao() {
        return mType.equals("k");
    }

    public void logOut() {
        init();
        setLogin();
    }

    public String getMemberInfo() {
        return "로그인 타입 : " + (mType.equals("k")?"카카오":"네이버") +
                "\n아이디 : " + mId +
                "\n이메일 : " + mEmail +
                "\n닉네임 : " + mNick;
    }

    public String getMemberIDInfo() {
        return"ID:"+mNick+"("+ (mType.equals("k")?"카카오":"네이버")+")";
    }


    public String getMemberEmailInfo() {
        return mEmail;
    }
}