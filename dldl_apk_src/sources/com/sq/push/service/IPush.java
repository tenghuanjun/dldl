package com.sq.push.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface IPush {

    public interface TokenListener {
        void onReceiveToken(String token);
    }

    public interface TransmitMessageListener {
        void onReceiveTransmitMessage(String json);
    }

    TokenListener getTokenListener();

    TransmitMessageListener getTransmitMessageListener();

    void init(Context context);

    void onCreate(Activity activity, Bundle bundle);

    void onNewIntent(Activity activity, Intent intent);

    void sendFeedback(Context context, Intent intent);

    void sendFeedback(Context context, Map<String, String> params);

    void setTokenListener(TokenListener listener);

    void setTransmitMessageListener(TransmitMessageListener listener);
}
