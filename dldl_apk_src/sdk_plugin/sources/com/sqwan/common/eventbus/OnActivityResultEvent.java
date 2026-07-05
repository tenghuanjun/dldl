package com.sqwan.common.eventbus;

import android.content.Intent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class OnActivityResultEvent {
    private Intent intent;
    private int requestCode;
    private int resultCode;

    public OnActivityResultEvent(int i, int i2, Intent intent) {
        this.requestCode = i;
        this.resultCode = i2;
        this.intent = intent;
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public Intent getIntent() {
        return this.intent;
    }
}
