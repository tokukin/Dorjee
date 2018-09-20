package com.example.tokukin.dorjee;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class Splash extends Activity {

    private WeakReference<Activity> sCurrentActivityWeakRef;
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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler=new Handler();
        new Thread("等待3s"){
            public void run(){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Splash.this, "欢迎", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Splash.this,HelloActivity.class);
                        startActivity(intent);
                    }
                });

            }
        }.start();


    }

//    public void jumpmain(View view) {
//        startActivity(new Intent(Splash.this,LoginActivity.class));
//    }
}
