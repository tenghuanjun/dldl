package com.plugin.standard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface IActivityInterface {
    <T extends View> T findViewById(int i);

    void finish();

    Context getApplicationContext();

    Context getContext();

    Intent getIntent();

    String getPackageName();

    Window getWindow();

    void insertAppContext(Activity activity);

    boolean isFinishing();

    void onActivityResult(int i, int i2, Intent intent);

    void onCreate(Bundle bundle);

    void onDestroy();

    boolean onKeyDown(int i, KeyEvent keyEvent);

    void onPause();

    void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    void onResume();

    void onStart();

    void onStop();

    void setContentView(int i);

    void setRequestedOrientation(int i);

    void setTheme(int i);

    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int i);
}
