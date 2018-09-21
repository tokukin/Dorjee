package com.example.tokukin.dorjee;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wang.avi.*;

import java.util.List;

public class StarsActivity extends AppCompatActivity {
    private List<Stars.DataBean> stars;
    private ListView listView;
    private LayoutInflater layoutInflater;
    String TAG = "dorjee";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stars);

        listView = (ListView) findViewById(R.id.star_list_view);
        layoutInflater = LayoutInflater.from(this);
        initStarList();

    }

    private void initStarList() {

        final Handler handler = new Handler();
//        等待中加载用户图片
        new Thread("获取列表") {
            public void run() {
                try {

                    SimpleRepo.getJson("http://image.baidu.com/channel/listjson?pn=0&rn=10&tag1=%E6%98%8E%E6%98%9F&tag2=%E5%85%A8%E9%83%A8&ie=utf-8", new SimpleRepo.JsonCallbackOnUI() {
                        @Override
                        public void onSuccess(String json) {
                            Stars stars_all = new Gson().fromJson(json, Stars.class);
                            //todo 获取明星实例

                            stars = stars_all.getData();
                            Stars.DataBean ourteam = new Stars.DataBean();
                            stars.remove(10);
                            ourteam.setAbs("新知团队");
                            ourteam.setTag("第四组天团成员");
                            ourteam.setImage_url("local");
                            ourteam.setDesc("湖北扬天翔、宁夏康彧，新疆宋宇，甘肃李得鑫、陈文焕");
                            stars.add(ourteam);

                        }

                        @Override
                        public void onFailed() {
                            Toast.makeText(StarsActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        ProgressBar progressBar = findViewById(R.id.loading);
                        TextView textView_loading = findViewById(R.id.text_loading);
                        progressBar.setVisibility(View.GONE);
                        textView_loading.setVisibility(View.GONE);

                        showListPerson();
                        Toast.makeText(StarsActivity.this, "网络数据加载完成", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }.start();
    }

    private class StarAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return stars.size();
        }


        @Override
        public Object getItem(int position) {
            return stars.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.layout_star, parent, false);
            }
            Stars.DataBean star = stars.get(position);


            TextView name = (TextView) convertView.findViewById(R.id.starname);
            name.setText(star.getAbs());

            ImageView starimage = convertView.findViewById(R.id.starimg);
            if (star.getImage_url() == "local") {
                starimage.setImageResource(R.drawable.chinamobile);
                starimage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            } else {
                Glide.with(StarsActivity.this).load(star.getImage_url()).placeholder(R.drawable.logo).error(R.drawable.logo).transform(new GlideRoundTransform(StarsActivity.this, 20)).into(starimage);
            }

            return convertView;
        }
    }

    public void showListPerson() {
        listView.setAdapter(new StarsActivity.StarAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stars.DataBean star = stars.get(position);
                Intent intent = new Intent();
                intent.putExtra("star", star);
                intent.setClass(StarsActivity.this, DetalActivity.class);
                startActivity(intent);

//                startActivity(new Intent(HelloActivity.this, DetalActivity.class));
            }
        });


    }


}
