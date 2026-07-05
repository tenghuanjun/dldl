package com.duowan.auk.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.view.MenuItem;
import com.duowan.auk.app.ArkReport;
import com.duowan.auk.ui.annotation.IAActivity;
import com.duowan.auk.ui.annotation.IAHelper;
import com.duowan.auk.ui.utils.UILog;
import com.duowan.auk.ui.widget.IGetLayoutId;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArkActivity extends Activity implements IGetLayoutId, LifecycleOwner {
    private static final String BASE_CLASS_NAME = ArkActivity.class.getName();
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Override // com.duowan.auk.ui.widget.IGetLayoutId
    public int getLayoutId() {
        return 0;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        boolean zStackBottom;
        super.onCreate(bundle);
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        UILog.lifecycle("onCreate", this);
        IAActivity iAActivity = (IAActivity) getClass().getAnnotation(IAActivity.class);
        if (iAActivity != null) {
            setContentView(iAActivity.value());
            zStackBottom = iAActivity.stackBottom();
            IAHelper.init(this, BASE_CLASS_NAME);
        } else {
            int layoutId = getLayoutId();
            if (layoutId != 0) {
                setContentView(layoutId);
                IAHelper.init(this, BASE_CLASS_NAME);
            }
            zStackBottom = false;
        }
        ActionBar actionBar = getActionBar();
        if (actionBar == null || zStackBottom) {
            return;
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
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

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        UILog.lifecycle("onStop", this);
    }

    @Override // android.app.Activity
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
