package com.plugin.core;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.plugin.core.loader.ApkClassLoader;
import com.plugin.standard.IActivityInterface;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class ProxyPluginActivity extends Activity {
    private final String TAG = getClass().getSimpleName();
    private IActivityInterface pluginActivity;

    @Override // android.content.ContextWrapper, android.content.Context
    public ApkClassLoader getClassLoader() {
        return null;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return super.getResources();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null && !TextUtils.isEmpty(intent.getStringExtra(TTDownloadField.TT_ACTIVITY))) {
            try {
                IActivityInterface iActivityInterface = (IActivityInterface) getClassLoader().getInterface(IActivityInterface.class, intent.getStringExtra(TTDownloadField.TT_ACTIVITY));
                this.pluginActivity = iActivityInterface;
                iActivityInterface.insertAppContext(this);
                this.pluginActivity.onCreate(new Bundle());
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(this.TAG, "intent 中没带插件activity信息");
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        IActivityInterface iActivityInterface = this.pluginActivity;
        if (iActivityInterface != null) {
            iActivityInterface.onStart();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        IActivityInterface iActivityInterface = this.pluginActivity;
        if (iActivityInterface != null) {
            iActivityInterface.onResume();
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        IActivityInterface iActivityInterface = this.pluginActivity;
        if (iActivityInterface != null) {
            iActivityInterface.onPause();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        IActivityInterface iActivityInterface = this.pluginActivity;
        if (iActivityInterface != null) {
            return iActivityInterface.onKeyDown(i, keyEvent);
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        IActivityInterface iActivityInterface = this.pluginActivity;
        if (iActivityInterface != null) {
            iActivityInterface.onActivityResult(i, i2, intent);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        IActivityInterface iActivityInterface = this.pluginActivity;
        if (iActivityInterface != null) {
            iActivityInterface.onDestroy();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        IActivityInterface iActivityInterface = this.pluginActivity;
        if (iActivityInterface != null) {
            iActivityInterface.onRequestPermissionsResult(i, strArr, iArr);
        }
    }
}
