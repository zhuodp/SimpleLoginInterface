package com.example.a74021.lab7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Button OK;
    public Button clear;
    public Button quit;
    private EditText pw;
    private EditText pw2;
    private boolean tag=true;
    public void readAccount(){
        SharedPreferences sp=getSharedPreferences("info",MODE_PRIVATE);
        String  password1=sp.getString("pw1","");
        String password2=sp.getString("pw2","");
        //显示账户密码
        pw.setText(password1);
        pw2.setText(password2);

        if(!password2.equals("")){
            pw.setHint("password");
            pw.setText("");
            pw2.setVisibility(View.INVISIBLE);
            tag=false;
        }
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OK=(Button)findViewById(R.id.BtnOK);
        clear= (Button)findViewById(R.id.BtnClear);
        quit = (Button)findViewById(R.id.BtnQuit);
        pw =(EditText)findViewById(R.id.editText1);
        pw2= (EditText)findViewById(R.id.editText2);

        //读取账户和密码
        readAccount();

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1=pw.getText().toString();
                String p2=pw2.getText().toString();
                //创建sharedPreference对象（“文件名”，“访问权限”）
                SharedPreferences sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);

                //获取编辑内容接口
                SharedPreferences.Editor ed=sharedPreferences.edit();

                //保存账户密码到sharepreference中
                ed.putString("pw1",p1);
                ed.putString("pw2",p2);
                //commit
                ed.commit();

                if (!p1.equals("")&&p2.equals("")){
                    Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!p1.equals(p2)){
                    Toast.makeText(getApplicationContext(),"密码不正确",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(p1.equals(p2))
                {
                    Intent intent=new Intent(MainActivity.this,FileEditorActivity.class);
                    startActivity(intent);
                }
            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tag==true) {
                    pw.setText("");
                    pw2.setText("");
                }
                else
                {
                    pw.setText("");
                }
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    MainActivity.this.finish();
                }
                catch (Exception e)
                {}
            }
        });
    }
}
