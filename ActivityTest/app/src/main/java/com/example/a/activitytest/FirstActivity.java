package com.example.a.activitytest;

         import android.support.v7.app.AppCompatActivity;
         import android.os.Bundle;
         import android.view.Menu;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.first_layout);
        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this,"Toast",Toast.LENGTH_SHORT).show();
            }
        });

    }

}
