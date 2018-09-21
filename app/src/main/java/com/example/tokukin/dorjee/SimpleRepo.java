package com.example.tokukin.dorjee;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SimpleRepo {

    public static interface JsonCallbackOnUI{
        void onSuccess(String json);
        void onFailed();
    }



    /**
     * 获取指定的url内容
     *
     * 必须在子线程调用，收到结果后如果要更改UI,用Handler.post消息到主线程哦
     * @param url
     * @return json内容，网络异常时有可能为null
     */
    public static String getJson(String url){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().get().url(url).build();
        try {
            Response response=okHttpClient.newCall(request).execute();
            if(response.code()>400){
                return null;
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取Json数据，并且在主线程回调callback
     * @param url
     * @param callback 网络请求结果回调，此方法自动在主线程被调用
     * @return
     */
    public static void getJson(final String url, final JsonCallbackOnUI callback){
        new Thread("网络请求"){
            public void run(){
                final String json=getJson(url);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if(json==null){
                            callback.onFailed();
                        }else{
                            callback.onSuccess(json);
                        }
                    }
                });
            }
        }.start();
    }


}
