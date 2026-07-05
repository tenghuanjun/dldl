package com.igexin.sdk;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import com.igexin.sdk.main.PushCoreLoader;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PushActivity extends Activity {
    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (PushCoreLoader.getInstance().getPushCore() != null) {
            PushCoreLoader.getInstance().getPushCore().onActivityConfigurationChanged(this, configuration);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        if (PushCoreLoader.getInstance().getPushCore() != null) {
            return PushCoreLoader.getInstance().getPushCore().onActivityCreateOptionsMenu(this, menu);
        }
        return true;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (PushCoreLoader.getInstance().getPushCore() != null) {
            PushCoreLoader.getInstance().getPushCore().onActivityDestroy(this);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (PushCoreLoader.getInstance().getPushCore() == null || !PushCoreLoader.getInstance().getPushCore().onActivityKeyDown(this, i, keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (PushCoreLoader.getInstance().getPushCore() != null) {
            PushCoreLoader.getInstance().getPushCore().onActivityNewIntent(this, intent);
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (PushCoreLoader.getInstance().getPushCore() != null) {
            PushCoreLoader.getInstance().getPushCore().onActivityPause(this);
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (PushCoreLoader.getInstance().getPushCore() != null) {
            PushCoreLoader.getInstance().getPushCore().onActivityRestart(this);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (PushCoreLoader.getInstance().getPushCore() != null) {
            PushCoreLoader.getInstance().getPushCore().onActivityResume(this);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        if (PushCoreLoader.getInstance().getPushCore() != null) {
            PushCoreLoader.getInstance().getPushCore().onActivityStart(this, getIntent());
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (PushCoreLoader.getInstance().getPushCore() != null) {
            PushCoreLoader.getInstance().getPushCore().onActivityStop(this);
        }
    }
}
