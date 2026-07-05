package com.plugin.standard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class BaseActivity implements IActivityInterface {
    private Activity mAppActivity;

    @Override // com.plugin.standard.IActivityInterface
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.plugin.standard.IActivityInterface
    public void onCreate(Bundle bundle) {
    }

    @Override // com.plugin.standard.IActivityInterface
    public void onDestroy() {
    }

    @Override // com.plugin.standard.IActivityInterface
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    @Override // com.plugin.standard.IActivityInterface
    public void onPause() {
    }

    @Override // com.plugin.standard.IActivityInterface
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    @Override // com.plugin.standard.IActivityInterface
    public void onResume() {
    }

    @Override // com.plugin.standard.IActivityInterface
    public void onStart() {
    }

    @Override // com.plugin.standard.IActivityInterface
    public void onStop() {
    }

    @Override // com.plugin.standard.IActivityInterface
    public void insertAppContext(Activity activity) {
        this.mAppActivity = activity;
    }

    @Override // com.plugin.standard.IActivityInterface
    public void startActivity(Intent intent) {
        this.mAppActivity.startActivity(intent);
    }

    @Override // com.plugin.standard.IActivityInterface
    public void setTheme(int i) {
        this.mAppActivity.setTheme(i);
    }

    @Override // com.plugin.standard.IActivityInterface
    public void setRequestedOrientation(int i) {
        this.mAppActivity.setRequestedOrientation(i);
    }

    @Override // com.plugin.standard.IActivityInterface
    public <T extends View> T findViewById(int i) {
        return (T) this.mAppActivity.findViewById(i);
    }

    @Override // com.plugin.standard.IActivityInterface
    public void setContentView(int i) {
        this.mAppActivity.setContentView(i);
    }

    @Override // com.plugin.standard.IActivityInterface
    public Intent getIntent() {
        return this.mAppActivity.getIntent();
    }

    @Override // com.plugin.standard.IActivityInterface
    public Window getWindow() {
        return this.mAppActivity.getWindow();
    }

    @Override // com.plugin.standard.IActivityInterface
    public Activity getContext() {
        return this.mAppActivity;
    }

    @Override // com.plugin.standard.IActivityInterface
    public void finish() {
        this.mAppActivity.finish();
    }

    @Override // com.plugin.standard.IActivityInterface
    public boolean isFinishing() {
        return this.mAppActivity.isFinishing();
    }

    @Override // com.plugin.standard.IActivityInterface
    public void startActivityForResult(Intent intent, int i) {
        this.mAppActivity.startActivityForResult(intent, i);
    }

    @Override // com.plugin.standard.IActivityInterface
    public String getPackageName() {
        return this.mAppActivity.getPackageName();
    }

    @Override // com.plugin.standard.IActivityInterface
    public Context getApplicationContext() {
        return this.mAppActivity.getApplicationContext();
    }
}
