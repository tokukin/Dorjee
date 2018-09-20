package com.example.tokukin.dorjee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText editText;
    private EditText editText2;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText = findViewById(R.id.edittext_user);
        editText2 = findViewById(R.id.edittext_pwd);
        textView = findViewById(R.id.text1);
    }

    public void login(View TextView){
//        textView.setText("hello:"+editText.getText().toString());
        String user = editText.getText().toString();
        String pwd = editText2.getText().toString();
        if(user.equals("l") && pwd.equals("1")){

            startActivity(new Intent(this,HomeActivity.class));

        }else {
            Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
        }

//        Toast.makeText(this,"user:"+user+";pwd:"+pwd,Toast.LENGTH_SHORT).show();
    }

    public void reg(View view) {
        Toast.makeText(this,"未开放注册",Toast.LENGTH_SHORT).show();
    }
}
