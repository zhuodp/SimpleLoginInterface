package com.example.a74021.lab7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
/**
 * Created by 74021 on 2017/12/14.
 */
public class FileEditorActivity extends AppCompatActivity{
    public Button save;
    public Button load;
    public Button clear2;
    private EditText et;

    @Override
    protected void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.edit_activity);

        save=(Button)findViewById(R.id.BtnSave);
        load=(Button)findViewById(R.id.BtnLoad);
        clear2=(Button)findViewById(R.id.BtnClear2);
        et=(EditText)findViewById(R.id.eT);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try(FileOutputStream fileOutputStream=openFileOutput("myFile.txt",MODE_PRIVATE)){
                    String str = "";
                    str = et.getText().toString();
                    fileOutputStream.write(str.getBytes());
                    Toast.makeText(FileEditorActivity.this, "已保存", Toast.LENGTH_SHORT).show();
                    Log.i("TAG", "Successfully saved file.");
                    return ;

                } catch (IOException ex) {
                    Log.e("TAG", "Fail to save file.");
                }
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try (FileInputStream fileInputStream = openFileInput("myFile.txt")) {
                    byte[] contents = new byte[fileInputStream.available()];
                    fileInputStream.read(contents);
                    et.setText(new String(contents));
                    Toast.makeText(FileEditorActivity.this, "载入成功", Toast.LENGTH_SHORT).show();
                    return ;
                } catch (IOException ex) {
                    Log.e("TAG", "Fail to read file.");
                    Toast.makeText(FileEditorActivity.this, "装入失败", Toast.LENGTH_SHORT).show();
                    return ;
                }
            }
        });


        clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
            }
        });

    }
}