package com.jiguang.h5;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class SplashActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        requestWindowFeature(1);
        setContentView(getResources().getIdentifier("activity_splash", "layout", getPackageName()));
        new Handler().postDelayed(new Runnable() { // from class: com.jiguang.h5.SplashActivity.1
            @Override // java.lang.Runnable
            public void run() {
                SplashActivity.this.overridePendingTransition(0, 0);
                SplashActivity.this.finish();
            }
        }, 2000L);
    }
}
