package com.sqwan.liveshow.im;

import android.os.Bundle;
import com.sqwan.msdk.api.SQResultListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class ILiveshowImCallback extends YouMeIMCallbackWrapper implements SQResultListener {
    public abstract void joinRoom(boolean z);

    public abstract void leaveRoom(boolean z);

    public void onFailture(int i, String str) {
    }

    public void onSuccess(Bundle bundle) {
    }
}
