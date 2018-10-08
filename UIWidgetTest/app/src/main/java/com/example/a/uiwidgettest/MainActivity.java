package com.example.a.uiwidgettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import static com.example.a.uiwidgettest.R.id.edit_text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private ImageView imageView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        Button button2 =  (Button) findViewById(R.id.button2);
        Button button3 =  (Button) findViewById(R.id.proDia);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            //点击按钮后的操作
//            }
//        });
//        实现接口注册监听器的方法
        button .setOnClickListener(this);
        button2 .setOnClickListener(this);
        button3 .setOnClickListener(this);
        editText=findViewById(R.id.edit_text);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progress_bar);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button:
                String inputText = editText.getText().toString();
                Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
                imageView.setImageResource(R.drawable.img_2);
                if(progressBar.getVisibility() == View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(0);
                }
                else if (progressBar.getProgress()<90)
                    progressBar.setProgress(progressBar.getProgress()+10);
                else
                    progressBar.setVisibility(View.GONE);
                break;
//                对话框
            case R.id.button2:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("this is a Dialog");
                dialog.setMessage("Something you should know");
                dialog.setCancelable(false);
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            case  R.id.proDia:
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setCancelable(true);
                progressDialog.setTitle("ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                break;
            default:
                    break;
        }
    }
}
