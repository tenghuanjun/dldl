package com.duowan.auk.ui;

import android.app.ActionBar;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import com.duowan.auk.app.ArkReport;
import com.duowan.auk.ui.annotation.IAActivity;
import com.duowan.auk.ui.utils.UILog;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArkPActivity extends PreferenceActivity implements LifecycleOwner {
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.preference.PreferenceActivity
    public void onBuildHeaders(List<PreferenceActivity.Header> list) {
        boolean zStackBottom;
        IAActivity iAActivity = (IAActivity) getClass().getAnnotation(IAActivity.class);
        if (iAActivity != null) {
            zStackBottom = iAActivity.stackBottom();
            loadHeadersFromResource(iAActivity.value(), list);
        } else {
            zStackBottom = false;
        }
        ActionBar actionBar = getActionBar();
        if (actionBar == null || !zStackBottom) {
            return;
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        UILog.lifecycle("onCreate", this);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        UILog.lifecycle("onRestart", this);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        UILog.lifecycle("onStart", this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        ArkReport.resume(this);
        UILog.lifecycle("onResume", this);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        ArkReport.pause(this);
        UILog.lifecycle("onPause", this);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        UILog.lifecycle("onStop", this);
    }

    @Override // android.preference.PreferenceActivity, android.app.ListActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        UILog.lifecycle("onDestroy", this);
    }

    @Override // android.arch.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }
}
