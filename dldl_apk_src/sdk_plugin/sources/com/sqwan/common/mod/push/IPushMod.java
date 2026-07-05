package com.sqwan.common.mod.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.sqwan.common.mod.IModBase;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IPushMod extends IModBase {

    public interface TransmitMessageListenerInternal {
        void onReceiveTransmitMessage(String str);
    }

    void init(Context context);

    void onCreate(Activity activity, Bundle bundle);

    void onNewIntent(Activity activity, Intent intent);

    void sendFeedback(Context context, Map<String, String> map);

    void setTransmitMessageListener(TransmitMessageListenerInternal transmitMessageListenerInternal);

    void setUserInfo(String str, String str2);
}
