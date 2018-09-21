package com.example.tokukin.dorjee;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.lang.ref.WeakReference;
import java.util.List;

public class Splash extends Activity {
    private static int launchTime;

    private WeakReference<Activity> sCurrentActivityWeakRef;
    private String ad;

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Intent intent = new Intent(Splash.this,HelloActivity.class);
//        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        launchTime++;

        final Handler handler = new Handler();
//        等待中加载用户图片
        new Thread("等待3s") {
            public void run() {
                try {
                    if (launchTime == 1) {
                        Thread.sleep(3000);
                    }

                    SimpleRepo.getJson("http://laotian.cn/ad.json", new SimpleRepo.JsonCallbackOnUI() {
                        @Override
                        public void onSuccess(String json) {
                            Log.e("tokukin", json);
                            IndexAd indexAd = new Gson().fromJson(json, IndexAd.class);
                            ad = indexAd.getCustomerPhoto();
                            TextView text_cus_name = findViewById(R.id.text_cus_name);
                            text_cus_name.setText(indexAd.getCustomer());
                            text_cus_name.setVisibility(View.VISIBLE);
                            ImageView imageView = findViewById(R.id.img_1);
                            Glide.with(Splash.this).load(ad).placeholder(R.drawable.icon).error(R.drawable.icon).transform(new GlideRoundTransform(Splash.this, 5)).into(imageView);


                        }

                        @Override
                        public void onFailed() {
                            Toast.makeText(Splash.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    if (launchTime == 1) {
                        Thread.sleep(3000);
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(Splash.this, "欢迎", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Splash.this, StarsActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

            }
        }.start();


    }

}
