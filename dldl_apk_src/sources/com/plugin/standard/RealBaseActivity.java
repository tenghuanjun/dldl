package com.plugin.standard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class RealBaseActivity extends Activity implements IActivityInterface {
    @Override // com.plugin.standard.IActivityInterface
    public Context getContext() {
        return this;
    }

    @Override // com.plugin.standard.IActivityInterface
    public void insertAppContext(Activity activity) {
    }

    @Override // android.app.Activity, com.plugin.standard.IActivityInterface
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Activity, com.plugin.standard.IActivityInterface
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity, com.plugin.standard.IActivityInterface
    public void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity, com.plugin.standard.IActivityInterface
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity, com.plugin.standard.IActivityInterface
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity, com.plugin.standard.IActivityInterface
    public void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity, com.plugin.standard.IActivityInterface
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, com.plugin.standard.IActivityInterface
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }
}
