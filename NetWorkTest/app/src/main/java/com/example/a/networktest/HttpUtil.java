package com.example.a.networktest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
    /**普通方法发送Http请求*/
    public static void sendHttpRequest(final String address
            , final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    //拼接响应信息
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if(listener != null){
                        //回调onFinish函数返回响应字符串对象
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e){
                    if (listener != null){
                        //错误回调onError函数返回错误对象
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    /**OkHttp发送Http请求*/
    public static void sendOkhttpRequest(String address,okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        //m没有使用execute而使用enqueue,后者开启新的线程
        client.newCall(request).enqueue(callback);
    }
}
