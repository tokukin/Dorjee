package com.example.tokukin.dorjee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class DetalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal);

        Intent intent = getIntent();
        Stars.DataBean star = (Stars.DataBean) intent.getSerializableExtra("star");
        TextView textabs = findViewById(R.id.text_detail_abs);
        textabs.setText(star.getTag());

        TextView textbbs = findViewById(R.id.text_detail_bbs);
        textbbs.setText(star.getDesc());
        ImageView imgdetail = findViewById(R.id.img_star_detail);
        Glide.with(DetalActivity.this).load(star.getImage_url()).placeholder(R.drawable.logo).error(R.drawable.logo).transform(new GlideRoundTransform(DetalActivity.this,0)).into(imgdetail);



        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(star.getAbs());


//        Toast.makeText(this, name,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
