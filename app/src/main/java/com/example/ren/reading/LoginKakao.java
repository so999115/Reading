package com.example.ren.reading;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.callback.UnLinkResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ren on 2017-10-26.
 */

public class LoginKakao {

    private ISessionCallback callback;
    private Session session;

    private static LoginKakao uniqueInstance;

    private LoginKakao() {
    }

    public static LoginKakao getInstance() {
        if (uniqueInstance == null) {
            synchronized (LoginKakao.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new LoginKakao();
                }
            }
        }
        return uniqueInstance;
    }

    public void init(Context context) {

        Log.d("kakao login", "key hash : " + getKeyHash(context));

        try {
            KakaoSDK.init(adapter);
        } catch (KakaoSDK.AlreadyInitializedException e) {
            Logger.e(e);
        }
    }

    public void setSession(Activity activity) {
        if (callback == null && session == null) {
            callback = new SessionCallback(activity);
            session = Session.getCurrentSession();
            session.addCallback(callback);
            session.checkAndImplicitOpen();
        }
    }

    private final KakaoAdapter adapter = new KakaoAdapter() {
        /**
         * Session Config에 대해서는 default값들이 존재한다.
         * 필요한 상황에서만 override해서 사용하면 됨.
         * @return Session의 설정값.
         */
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_TALK};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Context getApplicationContext() {
                    return GlobalApplication.getGlobalApplicationContext();
                }
            };
        }
    };

    private class SessionCallback implements ISessionCallback {
        Activity activity;

        public SessionCallback(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onSessionOpened() {
            Logger.i("++ MySessionStatusCallback::onSessionOpened()");
            requestMe(activity);
        }

        @Override
        public void onSessionOpenFailed(final KakaoException exception) {
            Logger.i("++ onSessionClosed()");
            if(exception != null) {
                Toast.makeText(GlobalApplication.getGlobalApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                Logger.e(exception);
            }
        }
    }
    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     * @param activity
     */
    protected void requestMe(final Activity activity) {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                Logger.d("UserProfile : " + userProfile);
                //사용자 로그인 체크
                HttpUtils.getInstance().kakaoLogin(Long.toString(userProfile.getId()), userProfile.getEmail(), userProfile.getNickname(), new StartActivityImpl() {
                    @Override
                    public void redirect(String type, String id, String pwd, String email, String nick) {
                        setMemberInfo(type, id, pwd, email, nick);
                        //startMainActivity(activity);
                        startStartActivity(activity);
                    }
                });
            }

            @Override
            public void onNotSignedUp() {
                Logger.d("UserProfile : not signed up");
                //requestSignUp();
            }
        });
    }

    private void setMemberInfo (String type, String id, String pwd, String email, String nick) {
        GlobalApplication.gMemberInfo.setUserInfo(type, id, pwd, email, nick);
    }
/*
    private void startMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
        activity.finish();
    }
*/
    private void startStartActivity(Activity activity) {
        Intent intent = new Intent(activity, StartActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    private void startLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
        activity.finish();
    }

    public void logout(final Activity activity) {
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {
                //사용자 정보 삭제
                GlobalApplication.gMemberInfo.logOut();
                //로그인 화면으로 이동
                startLoginActivity(activity);
            }
        });
    }

    protected boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        return Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data);
    }


    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w("kakao login", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }
}
