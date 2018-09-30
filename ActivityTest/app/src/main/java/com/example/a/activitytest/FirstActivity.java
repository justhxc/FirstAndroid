package com.example.a.activitytest;

         import android.content.Intent;
         import android.support.annotation.Nullable;
         import android.support.v7.app.AppCompatActivity;
         import android.os.Bundle;
         import android.view.Menu;
         import android.view.MenuItem;
         import android.view.View;
         import android.widget.Button;
         import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show();
                break;
            case R.id.quick_item:
                finish();
                break;
            default:
        }

        return true;
    }
    /** 获取返回的数据*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String returnData = data.getStringExtra("info2");
                    Toast.makeText(this,returnData,Toast.LENGTH_SHORT).show();}
            break;
            default:
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        设置布局
        setContentView(R.layout.first_layout);
//        button点击
        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this,"Toast",Toast.LENGTH_SHORT).show();
//               隐式启动intent
                Intent intent = new Intent("com.example.activitytest.ACTION_START");
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
//              显式启动intent
//              Intent intent = new Intent(FirstActivity.this,SecondActivity.class);

//                数据传递
                String data="Info from firstActivity.";
                intent.putExtra("info",data);
//                startActivity(intent);//普通启动
                startActivityForResult(intent,1);//启动并获取下个活动的返回
            }
        });

    }

}
