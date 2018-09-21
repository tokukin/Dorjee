package com.example.tokukin.dorjee;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;

public class HelloActivity extends AppCompatActivity {
    private List<Person> persons;
    private ListView listView;
    private LayoutInflater layoutInflater2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        listView = (ListView) findViewById(R.id.list_view);
        layoutInflater2 = LayoutInflater.from(this);
        initPersonList();

        /*
        new Thread() {
            public void run() {
                try {
                    //todo 从网络下载数据

                } catch (Exception e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //todo 数据加载完进行列表输出

                    }
                });
            }
        }.start();*/

    }






    private void initPersonList() {





        Person person = new Person();
        person.setAge(25);
        person.setName("张三");

        Person person2 = new Person();
        person2.setAge(39);
        person2.setName("李四");

        Person person3 = new Person();
        person3.setAge(21);
        person3.setName("王五");

        persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                persons.add(person);
            } else if (i % 3 == 1) {
                persons.add(person2);
            } else {
                persons.add(person3);
            }
        }
    }

    public void showbaidu(View view) {
        Intent intent = new Intent();
        intent.putExtra("url", "http://www.baidu.com");
        intent.setClass(HelloActivity.this, WebActivity.class);
        startActivity(intent);

    }

    public void showListP(View view){
//        反序列化，用于网络侧获取到数据的本地使用
//        IndexAd indexAd = new Gson().fromJson("{\n" +
//                "\t\"customer\":\"china_mobile\",\n" +
//                "\t\"customerPhoto\":\"http://img1.mydrivers.com/img/20180628/b87e57ce5cf54208a18abf140c53b575.jpg\"\n" +
//                "\n" +
//                "}",IndexAd.class);
//
//        Toast.makeText(this,"customer是:"+indexAd.getCustomer()+",图片:"+indexAd.getCustomerPhoto(),Toast.LENGTH_SHORT).show();
//        序列化，用于向网络提交数据
//        new Gson().toJson(indexAd);

        SimpleRepo.getJson("http://laotian.cn/ad.json", new SimpleRepo.JsonCallbackOnUI() {
            @Override
            public void onSuccess(String json) {
                Toast.makeText(HelloActivity.this,json,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailed() {
                Toast.makeText(HelloActivity.this,"网络请求失败",Toast.LENGTH_SHORT).show();
            }
        });



    }



    private class PersonAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return persons.size();
        }


        @Override
        public Object getItem(int position) {
            return persons.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater2.inflate(R.layout.layout_person, parent, false);
            }
            Person person = persons.get(position);

            TextView name = (TextView) convertView.findViewById(R.id.textname);
            TextView age = (TextView) convertView.findViewById(R.id.textage);
            name.setText(person.getName());
            age.setText("第" + position + "项姓名是：" + person.getName() + ",年龄是：" + person.getAge());
            return convertView;
        }
    }

    public void showListPerson(View view) {
        listView.setAdapter(new PersonAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person person = persons.get(position);
//                Toast.makeText(HelloActivity.this, "点击了:" + position + "，名称是：" + person.getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.putExtra("guy", person);
                intent.setClass(HelloActivity.this, DetalActivity.class);
                startActivity(intent);

//                startActivity(new Intent(HelloActivity.this, DetalActivity.class));
            }
        });


    }





}
