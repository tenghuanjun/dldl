package com.sqwan.common.mod.advertise;

import android.content.Context;
import com.sqwan.common.mod.IModBase;
import com.sqwan.msdk.api.SQResultListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IAdvertiseMod extends IModBase {
    void init(Context context);

    void showAdvertiseReward(Context context, String str, SQResultListener sQResultListener);
}
