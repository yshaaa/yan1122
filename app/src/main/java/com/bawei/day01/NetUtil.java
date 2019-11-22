package com.bawei.day01;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class NetUtil {
    private NetUtil(){

    }
    private static class NetUrl{
        private static NetUtil netUtil=new NetUtil();
    }
    public static NetUtil getInstance(){
        return NetUrl.netUtil;
    }

    public String io2String(InputStream inputStream){
        byte[] bytes=new byte[1024];
        int len=-1;
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        String json=null;
        try {
            while((len=inputStream.read(bytes))!=-1){
                byteArrayOutputStream.write(bytes,0,len);
            }
            byte[] bytes1 = byteArrayOutputStream.toByteArray();
             json = new String(bytes1);
        }catch (IOException i){
            i.printStackTrace();
        }
        return json;
    }
    public Bitmap io2Bitmap(InputStream inputStream){
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }

    @SuppressLint("StaticFieldLeak")
    public void doGet(final String httpurl,final MyCallBack myCallBack){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                HttpURLConnection httpURLConnection=null;
                InputStream inputStream=null;
                String json="";
                try {
                    URL url = new URL(httpurl);
                     httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    if(httpURLConnection.getResponseCode()==200){
                         inputStream = httpURLConnection.getInputStream();
                         json = io2String(inputStream);
                    }else{
                        Log.e("tag","请求失败");
                    }

                }catch (IOException i){
                    i.printStackTrace();
                }finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        }catch (IOException i){
                            i.printStackTrace();
                        }
                    }
                }
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                myCallBack.onget(s);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    public void doGetphono(final String httpurl,final MyCallBack myCallBack){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                HttpURLConnection httpURLConnection=null;
                InputStream inputStream=null;
                Bitmap json=null;
                try {
                    URL url = new URL(httpurl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    if(httpURLConnection.getResponseCode()==200){
                        inputStream = httpURLConnection.getInputStream();
                        json = io2Bitmap(inputStream);
                    }else{
                        Log.e("tag","请求失败");
                    }

                }catch (IOException i){
                    i.printStackTrace();
                }finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        }catch (IOException i){
                            i.printStackTrace();
                        }
                    }
                }
                return json;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                myCallBack.ongetPhono(bitmap);
            }
        }.execute();
    }


    public boolean hasNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo!=null&&activeNetworkInfo.isAvailable()){
            return true;
        }else {
            return false;
        }
    }

    public boolean WIFI(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo!=null&&activeNetworkInfo.isAvailable()&&activeNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI){
            return true;
        }else {
            return false;
        }
    }

    public boolean Phono(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo!=null&&activeNetworkInfo.isAvailable()&&activeNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
            return true;
        }else {
            return false;
        }
    }






    public interface MyCallBack{
        void onget(String json);
        void ongetPhono(Bitmap bitmap);
    }

}
