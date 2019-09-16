package com.example.ren.reading;


import android.content.ContentValues;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Ren on 2017-10-26.
 */

public class HttpUtils {

    private final static String TAG = "HttpUtils";
    public final static String URL = "http://so999115.cafe24.com/php";
    public final static String RES_RESULT = "RESP_RESULT";
    public final static String RES_RESULT_SUCCESS = "RESP_RESULT_SUCCESS";
    public final static String RES_RESULT_FAIL = "RESP_RESULT_FAIL";

    private static HttpUtils uniqueInstance;

    private AsyncHttpClient mClient;
    private RequestParams mParams;

    private HttpUtils() {
    }

    public static HttpUtils getInstance() {
        if (uniqueInstance == null) {
            synchronized (HttpUtils.class) {
                if (uniqueInstance == null)
                    uniqueInstance = new HttpUtils();
            }
        }
        return uniqueInstance;
    }

    public void kakaoLogin(final String id, final String email, final String nick, final StartActivityImpl callback) {
        mClient = new AsyncHttpClient();
        mParams = new RequestParams();
        mParams.put("type", "k");
        mParams.put("id", id);
        mParams.put("email", email);
        mParams.put("nick", nick);
        mClient.post(getUrl(1000), mParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (isSuccess(responseBody)) {
                    callback.redirect( "k", id, "", email, nick);
                } else {
                    ContentValues ret = getDataFromJson(responseBody);
                    Toast.makeText(GlobalApplication.getGlobalApplicationContext(), ret.getAsString("MSG"), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, new String(responseBody));
            }
        });
    }

    public void requestNaverMemberInfo(String token, final StartActivityImpl callback) {
        mClient = new AsyncHttpClient();
        mClient.addHeader("Authorization", "Bearer " + token);
        mParams = new RequestParams();
        mClient.post(getUrl(1100), mParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                ContentValues ret = getDataFromJson(responseBody);
                if (ret.getAsString("message").equals("success")) {
                    ContentValues mInfo = getDataFromJson(ret.getAsString("response"));
                    naverLogin(mInfo.getAsString("id"), mInfo.getAsString("email"), mInfo.getAsString("nickname"), callback);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, new String(responseBody));
            }
        });
    }

    public void naverLogin(final String id, final String email, final String nick, final StartActivityImpl callback) {
        mClient = new AsyncHttpClient();
        mParams = new RequestParams();
        mParams.put("type", "n");
        mParams.put("id", id);
        mParams.put("email", email);
        mParams.put("nick", nick);
        mClient.post(getUrl(1000), mParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (isSuccess(responseBody)) {
                    callback.redirect( "n", id, "", email, nick);
                } else {
                    ContentValues ret = getDataFromJson(responseBody);
                    Toast.makeText(GlobalApplication.getGlobalApplicationContext(), ret.getAsString("MSG"), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, new String(responseBody));
            }
        });
    }

    private String getUrl(int idx) {
        switch (idx) {
            case 1000:
                return URL + "/login.php";
            case 1100:
                return "https://openapi.naver.com/v1/nid/me";
            default:
                return "";
        }
    }


    /**
     * 서버 응답 메시지 성공여부 반환
     *
     * @return 성공여부
     */
    public boolean isSuccess(byte[] statusByte) {
        boolean ret = false;
        try {
            JSONObject jObj = new JSONObject(new String(statusByte));
            if (jObj != null && jObjGetStr(jObj, RES_RESULT).equals(RES_RESULT_SUCCESS))
                ret = true;
            else
                ret = false;
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }

        return ret;
    }

    /**
     * JSONArray 를 ContentValues 로 변환
     *
     * @return ArrayList<ContentValues>
     */
    public ArrayList<ContentValues> GetDataListFromJson(byte[] bytes, String jsonArrayKey) {
        JSONObject jObj = new JSONObject();

        try {
            jObj = new JSONObject(new String(bytes));
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return getDataListFromJson(jObj, jsonArrayKey);
    }

    /**
     * JSONArray 를 ContentValues 로 변환
     *
     * @return ArrayList<ContentValues>
     */
    public ArrayList<ContentValues> GetDataListFromJson(String jsonString, String jsonArrayKey) {
        JSONObject jObj = new JSONObject();

        try {
            jObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return getDataListFromJson(jObj, jsonArrayKey);
    }


    /**
     * JSONObject 의 JSONArray 첫번째 JSONObject 반환
     * @param jsonObject
     * @param jsonArrayKey
     * @return
     */
    public ContentValues getDataFromJson(JSONObject jsonObject, String jsonArrayKey) {
        try {
            return getDataListFromJson(jsonObject, jsonArrayKey).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * JSONArray 를 ContentValues 로 변환
     * @return ArrayList<ContentValues>
     */
    public ArrayList<ContentValues> getDataListFromJson(JSONObject jsonObject) {
        String jArrayKey = "jArray";
        return getDataListFromJson(jsonObject, jArrayKey);
    }

    /**
     * JSONArray 를 ContentValues 로 변환
     * @return ArrayList<ContentValues>
     */
    public ArrayList<ContentValues> getDataListFromJson(JSONObject jsonObject, String jsonArrayKey) {
        ArrayList<ContentValues> dataList = new ArrayList<>();
        String jArrayKey = "jArray";

        try {

            if (jsonObject != null && !jsonObject.isNull(RES_RESULT)) {

                //서버 반환 값 확인
                String result = jObjGetStr(jsonObject, RES_RESULT);
                if (!result.isEmpty() && result.equals(RES_RESULT_SUCCESS)) {

                    //JSONArray 키가 있을 경우 적용
                    if (jsonArrayKey != null && !jsonArrayKey.isEmpty()) {
                        jArrayKey = jsonArrayKey;
                    }

                    //JSONArray 가 있을 경우 변환
                    if (!jsonObject.isNull(jArrayKey)) {
                        JSONArray jArray = jsonObject.getJSONArray(jArrayKey);
                        dataList = GetDataListFromJArray(jArray);
                    }
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }

        return dataList;
    }

    /**
     * JSONArray 를 ContentValues List 로 반환
     *
     * @return ArrayList<ContentValues>
     */
    public ArrayList<ContentValues> GetDataListFromJArray(JSONArray jArray) {
        ArrayList<ContentValues> dataList = new ArrayList<>();
        ContentValues data;
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject jObj = jArray.optJSONObject(i);
            data = getDataFromJson(jObj);
            dataList.add(data);
        }
        return dataList;
    }

    /**
     * JSONObject 를 ContentValues 로 반환
     *
     * @return ContentValues
     */
    public ContentValues getDataFromJson(byte[] bytes) {
        return getDataFromJson(new String(bytes));
    }

    /**
     * JSONObject 를 ContentValues 로 반환
     *
     * @return ContentValues
     */
    public ContentValues getDataFromJson(String str) {
        ContentValues data = new ContentValues();
        try {
            data = getDataFromJson(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * JSONObject 를 ContentValues 로 반환
     *
     * @return ContentValues
     */
    public ContentValues getDataFromJson(JSONObject jObject) {
        ContentValues data = new ContentValues();
        Iterator<String> iterator = jObject.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = jObjGetStr(jObject, key);
            data.put(key, value);
        }
        return data;
    }

    /**
     * param string 을 ContentValues 로 반환
     * @param params
     * @return
     */
    public ContentValues getDataFromString(String params) {
        ContentValues data = new ContentValues();
        if(params != null && !params.isEmpty()) {
            if (params.contains(",")) {
                String[] dataList = params.split(",");
                for (String str : dataList) {
                    String[] d = str.split("=");
                    if (d.length == 2)
                        data.put(d[0], d[1]);
                    else
                        data.put(d[0], "");
                }
            } else {
                String[] d = params.split("=");
                if (d.length == 2)
                    data.put(d[0], d[1]);
                else
                    data.put(d[0], "");
            }
            //파라미터 JSONObject 로 변환
            //datas = HttpUtils.getInstance().GetDataFromJson(params);
        }
        return data;
    }

    /**
     * JSON Obj getString() null  처리
     *
     * @param jObj JSON Obj
     * @param key  키값
     * @return 키에 해당되는 값
     */
    public String jObjGetStr(JSONObject jObj, String key) {
        String value;
        try {
            if (jObj.has(key)) {
                if (jObj.isNull(key))
                    value = "";
                else
                    value = jObj.getString(key);
            } else {
                value = "";
            }
        } catch (JSONException e) {
            value = "";
        }
        return value;
    }

    /**
     * JSON Obj getString() null  처리
     *
     * @param jObj         JSON Obj
     * @param key          키값
     * @param defaultValue 기본 값
     */
    public String jObjGetStr(JSONObject jObj, String key, String defaultValue) {
        String value;
        try {
            if (jObj.has(key)) {
                if (jObj.isNull(key))
                    value = defaultValue;
                else
                    value = jObj.getString(key);
            } else {
                value = defaultValue;
            }
        } catch (JSONException e) {
            value = "";
        }
        return value;
    }

    /**
     * JSON Obj getInt() null  처리
     *
     * @param jObj JSON Obj
     * @param key  키값
     */
    public int jObjGetInt(JSONObject jObj, String key) {
        int value;
        try {
            if (jObj.has(key)) {
                if (jObj.isNull(key))
                    value = -1;
                else
                    value = jObj.getInt(key);
            } else {
                value = -1;
            }
        } catch (JSONException e) {
            value = -1;
        }
        return value;
    }

    /**
     * JSON Obj getInt() null  처리
     *
     * @param jObj         JSON Obj
     * @param key          키값
     * @param defaultValue 기본 값
     */
    public int jObjGetInt(JSONObject jObj, String key, int defaultValue) {
        int value;
        try {
            if (jObj.has(key)) {
                if (jObj.isNull(key))
                    value = defaultValue;
                else
                    value = jObj.getInt(key);
            } else {
                value = defaultValue;
            }
        } catch (JSONException e) {
            value = -1;
        }
        return value;
    }
}
