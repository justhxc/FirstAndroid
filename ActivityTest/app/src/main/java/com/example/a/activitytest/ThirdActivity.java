package com.example.a.activitytest;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;

public class ThirdActivity extends BaseActivity {

    private static final String TAG ="tag" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "3Task id is" +getTaskId());
        setContentView(R.layout.activity_third);

    }
}
