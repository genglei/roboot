package com.hehe.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hehe.R;
import com.hehe.utils.Util;
import com.tbruyelle.rxpermissions3.RxPermissions;

import io.reactivex.rxjava3.functions.Consumer;


public class SplashShowActivity extends AppCompatActivity {
    private boolean isOnPause;
    private final String TAG = SplashShowActivity.class.getSimpleName();
    Handler handler = new Handler();
    Runnable runnable = new Runnable() { // from class: com.higgs.deliveryrobot.ui.SplashShowActivity.1
        @Override // java.lang.Runnable
        public void run() {
            SplashShowActivity splashShowActivity = SplashShowActivity.this;
            splashShowActivity.startActivity(new Intent(splashShowActivity, RobotMainActivity.class));
            SplashShowActivity.this.handler.removeCallbacks(this);
        }
    };

    static /* synthetic */ String access$000(SplashShowActivity splashShowActivity) {
        return splashShowActivity.TAG;
    }

    static /* synthetic */ boolean access$100(SplashShowActivity splashShowActivity) {
        return splashShowActivity.isOnPause;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        Util.NavigationBarStatusBar(this, true);
        initPremission();
    }

    private void initPremission() {
        new RxPermissions(this).request( "android.permission.WRITE_EXTERNAL_STORAGE")
                .subscribe(new Consumer<Boolean>(){
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean.booleanValue()) {
                            Log.e(SplashShowActivity.access$000(SplashShowActivity.this), "accept: 未获取到相应的权限");
                        }
                    }
                });
//        new RxPermissions(this).request("android.permission.READ_PHONE_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.WRITE_SETTINGS")
//          .subscribe(new Consumer<Boolean>(){
//              @Override
//              public void accept(Boolean aBoolean) throws Exception {
//                  if (!aBoolean.booleanValue()) {
//                      Log.e(SplashShowActivity.access$000(SplashShowActivity.this), "accept: 未获取到相应的权限");
//                  }
//              }
//          });
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Log.e(this.TAG, "onResume: SplashActivity");
        if (this.isOnPause) {
            this.handler.postDelayed(this.runnable, 1000L);
        } else {
            this.handler.postDelayed(new Runnable() { // from class: com.higgs.deliveryrobot.ui.SplashShowActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    if (!SplashShowActivity.access$100(SplashShowActivity.this)) {
                        SplashShowActivity.this.handler.post(SplashShowActivity.this.runnable);
                    }
                    SplashShowActivity.this.handler.removeCallbacks(this);
                }
            }, 1000L);
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.isOnPause = true;
    }

    @Override
    // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.runnable);
        finish();
    }
}
