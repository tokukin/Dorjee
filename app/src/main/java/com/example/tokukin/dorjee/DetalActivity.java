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
        Person person = (Person) intent.getSerializableExtra("guy");

        TextView textview = findViewById(R.id.text_detal_name);
        TextView textview2 = findViewById(R.id.text_detal_age);
        textview.setText("姓名："+person.getName());
        textview2.setText("年龄："+person.getAge()+"");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(person.getName()+"的详情");


//        Toast.makeText(this, name,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
