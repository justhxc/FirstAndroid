package com.example.a.lbstest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView cLocation;
    TextView sLocation;
    Button singleLocation;
    Button continueLocation;
    //        单次定位
    private AMapLocationClient locationClientSingle = null;
    //        连续定位
    private AMapLocationClient locationClientContinue = null;

    private int continueCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singleLocation = findViewById(R.id.single_location);
        sLocation = findViewById(R.id.single_position);
        continueLocation = findViewById(R.id.continue_location);
        cLocation = findViewById(R.id.continue_position);

        singleLocation.setOnClickListener(this);
        continueLocation.setOnClickListener(this);

        initPermissions();

    }

    /**
     * 动态权限申请
     */
    private void initPermissions() {

        List<String> permissionList = new ArrayList<>();
//        高德SDK在真机(LG-G6 8.0)上拒绝了定位权限也能获取定位，但模拟器上拒绝了就不能定位，
//        也就是说真机上不需要任何权限申请就能定位手机，可怕
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);

        }
//        这俩权限好像不需要也能定位,
//        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE)
//                != PackageManager.PERMISSION_GRANTED){
//            permissionList.add(Manifest.permission.READ_PHONE_STATE);
//
//        }
//
//        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED){
//            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        }
        if (!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions,1);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case 1:
                if (grantResults.length > 0){
                    for (int result : grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "有权限未授权，定位可能失败", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.single_location:
                if (singleLocation.getText().equals("单次定位") ){
                    startSingleLocation();
                    singleLocation.setText("停止定位");
                } else {
                    stopSingleLocation();
                    singleLocation.setText("单次定位");
                }
                break;
            case R.id.continue_location:
                if (continueLocation.getText().equals("连续定位") ){
                    startContinueLocation();
                    continueLocation.setText("停止定位");
                } else {
                    stopContinueLocation();
                    continueLocation.setText("连续定位");
                }
                break;
            default:
                break;


        }
    }

    /**
     * 单次客户端的定位监听
     */
    AMapLocationListener locationSingleListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            long callBackTime = System.currentTimeMillis();
            StringBuffer sb = new StringBuffer();
            sb.append("单次定位完成\n");
            sb.append("回调时间: " + Utils.formatUTC(callBackTime, null) + "\n");
            if(null == location){
                sb.append("定位失败：location is null!!!!!!!");
            } else {
                sb.append(Utils.getLocationStr(location));
            }
            sLocation.setText(sb.toString());
        }
    };
    /**
     * 连续客户端的定位监听
     */
    AMapLocationListener locationContinueListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            continueCount ++;
            long callBackTime = System.currentTimeMillis();
            StringBuffer sb = new StringBuffer();
            sb.append("持续定位完成 " + continueCount +  "\n");
            sb.append("回调时间: " + Utils.formatUTC(callBackTime, null) + "\n");
            if(null == location){
                sb.append("定位失败：location is null!!!!!!!");
            } else {
                sb.append(Utils.getLocationStr(location));
            }

            cLocation.setText(sb.toString());
        }
    };
    /**
     * 开启关闭连续定位
     */
    private void startContinueLocation() {
        if(locationClientContinue == null){
            locationClientContinue = new AMapLocationClient(this.getApplicationContext());
        }
        AMapLocationClientOption locationClientOption = new AMapLocationClientOption();
        // 单次定位开关 默认连续
        locationClientOption.setOnceLocation(true);
        // 获取地址信息
        locationClientOption.setNeedAddress(true);
        locationClientOption.setLocationCacheEnable(false);
        locationClientContinue.setLocationOption(locationClientOption);
        locationClientContinue.setLocationListener(locationContinueListener);
        locationClientContinue.startLocation();
    }

    private void stopContinueLocation() {
        if(locationClientContinue != null){
            locationClientContinue.stopLocation();
        }
    }

    /**
     * 开启关闭单次定位
     */
    private void startSingleLocation() {
        if(locationClientSingle == null){
            locationClientSingle = new AMapLocationClient(this.getApplicationContext());
        }

        //连续的定位方式  默认连续
        AMapLocationClientOption locationClientOption = new AMapLocationClientOption();
        // 地址信息
        locationClientOption.setNeedAddress(true);
        locationClientSingle.setLocationOption(locationClientOption);
        locationClientSingle.setLocationListener(locationSingleListener);
        locationClientSingle.startLocation();
    }
    private void stopSingleLocation() {
    }

    /**
     * 关闭定位客户端
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationClientSingle != null){
            locationClientSingle.onDestroy();
            locationClientSingle = null;
        }
        if (locationClientContinue != null){
            locationClientContinue.onDestroy();
            locationClientContinue = null;
        }
    }
}
