package com.example.ren.reading;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Ren on 2017. 10. 9..
 */
//취소버튼을 두번 누르면 어플리케이션을 종료시키는 소스코드

public class BackBtnCloseHandler {
    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackBtnCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime+2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if(System.currentTimeMillis() <= backKeyPressedTime+2000) {
            activity.moveTaskToBack(true);
            activity.finish();
            activity.finishAffinity();
            android.os.Process.killProcess(android.os.Process.myPid());
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}

