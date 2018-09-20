package com.example.tokukin.dorjee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class DetalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        TextView textview = findViewById(R.id.text_detal);
        textview.setText("这是" + name + "的详情页面");

//        Toast.makeText(this, name,Toast.LENGTH_SHORT).show();
    }



}
