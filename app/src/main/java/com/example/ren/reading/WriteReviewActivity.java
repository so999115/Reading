package com.example.ren.reading;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteReviewActivity extends AppCompatActivity {

    private static String TAG = "phptest_WriteReviewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        final EditText etDate = (EditText) findViewById(R.id.date);
        final EditText etTitle = (EditText) findViewById(R.id.title);
        final EditText etContent= (EditText) findViewById(R.id.content);
        //final EditText etEmail = (EditText) findViewById(R.id.user_email);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        //출력 포멧 YMD
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");

        Intent intent1 = getIntent();
        etTitle.setText(intent1.getStringExtra("bookname"));

        etDate.setText(simpleDateFormat.format(date));



        Button button = (Button)findViewById(R.id.ok_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = GlobalApplication.gMemberInfo.getMemberEmailInfo();
                //String email = etEmail.getText().toString();
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();

                InsertData task = new InsertData();
                task.execute(email, title, content);

                //etEmail.setText("");
                etTitle.setText("");
                etContent.setText("");

                Toast.makeText(getApplicationContext(), "서평이 등록되었습니다.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(WriteReviewActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });
    }

    class InsertData extends AsyncTask<String, Void, String> { //AsyncTask : 백그라운드 스레드에서 실행되는 비동기 클래스
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() { //doInBackground 메소드가 실행되기 전에 실행되는 메소드
            super.onPreExecute();

            progressDialog = ProgressDialog.show(WriteReviewActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) { //doInBackground 메소드 후에 실행되는 메소드, 백그라운드 메소드의 반환값을 인자로 받아 그 결과를 화면에 반영
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String email = (String)params[0];
            String title = (String)params[1];
            String content = (String)params[2];

            String serverURL = "http://so999115.cafe24.com/php/review.php";
            String postParameters = "email=" +email + "&title=" + title + "&content=" + content;


            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("content-type", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString();

            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }
}
