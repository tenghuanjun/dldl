package com.bytedance.dr.impl;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.dr.OaidApi;
import com.volcengine.common.contant.CommonConstants;

/* JADX INFO: loaded from: classes2.dex */
public final class i implements OaidApi {
    @Override // com.bytedance.dr.OaidApi
    public String getName() {
        return "Nubia";
    }

    @Override // com.bytedance.dr.OaidApi
    public OaidApi.a getOaid(Context context) {
        try {
            ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(Uri.parse("content://cn.nubia.identity/identity"));
            if (contentProviderClientAcquireContentProviderClient == null) {
                return null;
            }
            Bundle bundleCall = contentProviderClientAcquireContentProviderClient.call("getOAID", null, null);
            int i = Build.VERSION.SDK_INT;
            contentProviderClientAcquireContentProviderClient.release();
            if (bundleCall == null) {
                return null;
            }
            if (bundleCall.getInt("code", -1) == 0) {
                OaidApi.a aVar = new OaidApi.a();
                aVar.f366a = bundleCall.getString("id");
                return aVar;
            }
            String string = bundleCall.getString(CommonConstants.KEY_MESSAGE);
            if (!TextUtils.isEmpty(string)) {
                LoggerImpl.global().error(1, string, new Object[0]);
            }
            return null;
        } catch (Exception e) {
            LoggerImpl.global().error(1, "getOaid failed", e, new Object[0]);
            return null;
        }
    }

    @Override // com.bytedance.dr.OaidApi
    public boolean support(Context context) {
        return Build.VERSION.SDK_INT > 28;
    }
}
