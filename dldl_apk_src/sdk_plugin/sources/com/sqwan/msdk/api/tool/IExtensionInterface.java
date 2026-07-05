package com.sqwan.msdk.api.tool;

import android.content.Context;
import com.sqwan.msdk.api.SQResultListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IExtensionInterface {
    void performFeature(Context context, String str, Object obj, SQResultListener sQResultListener);

    void performFeatureBBS();

    void performFeatureVPlayer();
}
