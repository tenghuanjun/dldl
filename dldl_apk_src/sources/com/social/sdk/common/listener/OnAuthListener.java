package com.social.sdk.common.listener;

import android.os.Bundle;
import com.social.sdk.platform.PlatformType;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface OnAuthListener {
    void onCancel(PlatformType platformType);

    void onFailure(PlatformType platformType, String str);

    void onSuccess(PlatformType platformType, Bundle bundle);
}
