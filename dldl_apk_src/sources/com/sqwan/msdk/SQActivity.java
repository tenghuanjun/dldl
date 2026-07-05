package com.sqwan.msdk;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class SQActivity extends Activity {
    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        SQwanCore.getInstance().onStart();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        SQwanCore.getInstance().onRestart();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        SQwanCore.getInstance().onResume();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        SQwanCore.getInstance().onPause();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        SQwanCore.getInstance().onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SQwanCore.getInstance().onDestroy();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return SQwanCore.getInstance().getResources(super.getResources());
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return SQwanCore.getInstance().getAssets(super.getAssets());
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ClassLoader getClassLoader() {
        return SQwanCore.getInstance().getClassLoader(super.getClassLoader());
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        SQwanCore.getInstance().startActivity(intent);
        super.startActivity(intent);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        SQwanCore.getInstance().startActivityForResult(intent, i);
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        SQwanCore.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        SQwanCore.getInstance().onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        SQwanCore.getInstance().onNewIntent(intent);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        SQwanCore.getInstance().onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        SQwanCore.getInstance().onWindowFocusChanged(z);
    }
}
