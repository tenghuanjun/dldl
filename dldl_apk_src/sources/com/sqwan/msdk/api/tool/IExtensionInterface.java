package com.sqwan.msdk.api.tool;

import android.content.Context;
import com.sqwan.msdk.api.SQResultListener;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface IExtensionInterface {
    void performFeature(Context context, String str, Object obj, SQResultListener sQResultListener);

    void performFeatureBBS();

    void performFeatureVPlayer();
}
