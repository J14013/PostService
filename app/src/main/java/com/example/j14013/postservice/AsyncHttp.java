package com.example.j14013.postservice;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by J14013 on 2017/02/10.
 */
public class AsyncHttp extends AsyncTask<String, Integer, Boolean> {
    HttpURLConnection urlConnection = null;
    Boolean flg = false;

    String name;
    double value;

    //コンストラクタ
    public AsyncHttp(String name, double value){
        this.name = name;
        this.value = value;
    }

    @Override
    protected Boolean doInBackground(String... contents) {
        String urlinput = "http://10.250.0.129/upload/post.php";

        try{
            URL url = new URL(urlinput);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            //POST用パラメータ
            String postDataSample = "name="+name+"&text="+value;

            //POSTパラメータ設定
            OutputStream out = urlConnection.getOutputStream();
            out.write(postDataSample.getBytes());
            out.flush();
            out.close();
            urlConnection.getInputStream();
            flg = true;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return flg;
    }
}
