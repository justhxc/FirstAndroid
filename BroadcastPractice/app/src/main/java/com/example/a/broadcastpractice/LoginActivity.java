package com.example.a.broadcastpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        rememberPass = findViewById(R.id.remember_pass);
        login = findViewById(R.id.login);
        Boolean isRemember = pref.getBoolean("remember_pass",false);
        if (isRemember){
            //将账号密码设置到文本框
            String account = pref.getString("account","");
            String passwd = pref.getString("passwd","");
            accountEdit.setText(account);
            passwordEdit.setText(passwd);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String passwd = passwordEdit.getText().toString();
                if (account.equals("admin") && passwd.equals("123")){
                    //保存账号密码
                    editor = pref.edit();
                    if (rememberPass.isChecked()){
                        editor.putBoolean("remember_pass",true);
                        editor.putString("account",account);
                        editor.putString("passwd",passwd);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
