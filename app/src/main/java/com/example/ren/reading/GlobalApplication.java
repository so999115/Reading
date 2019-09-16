package com.example.ren.reading;

import android.annotation.SuppressLint;
import android.app.Application;

/**
 * Created by Ren on 2017-10-26.
 */

public class GlobalApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static volatile GlobalApplication instance = null;
    public static MemberInfo gMemberInfo;

    /**
     * singleton 애플리케이션 객체를 얻는다.
     * @return singleton 애플리케이션 객체
     */
    public static GlobalApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    /**
     * 이미지 로더, 이미지 캐시, 요청 큐를 초기화한다.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        gMemberInfo = new MemberInfo();
        LoginKakao.getInstance().init(this);
    }
    /**
     * 애플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
