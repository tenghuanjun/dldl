package com.huya.ciku.apm.collector;

import com.duowan.monitor.core.OnStatusChangeListener;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseCollector implements OnStatusChangeListener {
    protected boolean mEnabled;
    protected boolean mStarted;

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStart() {
        this.mStarted = true;
    }

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStop() {
        this.mStarted = false;
    }

    @Override // com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        this.mEnabled = jSONObject != null ? jSONObject.optBoolean("enabled") : false;
    }

    protected boolean isEnabled() {
        return this.mEnabled && this.mStarted;
    }
}
