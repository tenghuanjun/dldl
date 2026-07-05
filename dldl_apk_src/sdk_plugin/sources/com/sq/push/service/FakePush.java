package com.sq.push.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.sq.push.service.IPush;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class FakePush implements IPush {
    public IPush.TokenListener getTokenListener() {
        return null;
    }

    public IPush.TransmitMessageListener getTransmitMessageListener() {
        return null;
    }

    public void init(Context context) {
    }

    public void onCreate(Activity activity, Bundle bundle) {
    }

    public void onNewIntent(Activity activity, Intent intent) {
    }

    public void sendFeedback(Context context, Intent intent) {
    }

    public void sendFeedback(Context context, Map<String, String> params) {
    }

    public void setTokenListener(IPush.TokenListener listener) {
    }

    public void setTransmitMessageListener(IPush.TransmitMessageListener listener) {
    }

    FakePush() {
    }
}
