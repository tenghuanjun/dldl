package com.bun.miitmdid.provider.nubia;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.bun.miitmdid.m0;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NubiaIdentityImpl {
    private static final String TAG = "NubiaIdentityImpl";
    private static Uri uri = Uri.parse("content://cn.nubia.identity/identity");

    private static Object generalMethod(Context context, String str, String str2, String str3, Class<?> cls) {
        Bundle bundleCall;
        try {
            int i = Build.VERSION.SDK_INT;
            if (i >= 17) {
                ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(uri);
                if (contentProviderClientAcquireContentProviderClient == null) {
                    m0.d(TAG, "generalMethod: contentResolver is null");
                    return null;
                }
                bundleCall = contentProviderClientAcquireContentProviderClient.call(str, str2, null);
                if (i >= 24) {
                    contentProviderClientAcquireContentProviderClient.close();
                } else {
                    contentProviderClientAcquireContentProviderClient.release();
                }
            } else {
                bundleCall = context.getContentResolver().call(uri, str, str2, (Bundle) null);
            }
            if (bundleCall == null) {
                m0.d(TAG, "generalMethod: bundle is null");
                return null;
            }
            if (bundleCall.getInt("code", -1) == 0) {
                m0.c(TAG, "generalMethod: success");
                if (cls == Boolean.class) {
                    return Boolean.valueOf(bundleCall.getBoolean(str3, false));
                }
                if (cls == String.class) {
                    return bundleCall.getString(str3, "");
                }
                return null;
            }
            m0.d(TAG, "generalMethod: failed:" + bundleCall.getString("message"));
            return null;
        } catch (Exception e) {
            m0.d(TAG, "generalMethod: Exception: " + e.getMessage());
            return null;
        }
    }

    public static String getAAID(Context context, String str) {
        Object objGeneralMethod = generalMethod(context, "getAAID", str, SqTrackCommonKey.id, String.class);
        return objGeneralMethod == null ? "" : (String) objGeneralMethod;
    }

    public static String getOAID(Context context) {
        Object objGeneralMethod = generalMethod(context, "getOAID", null, SqTrackCommonKey.id, String.class);
        return objGeneralMethod == null ? "" : (String) objGeneralMethod;
    }

    public static String getVAID(Context context, String str) {
        Object objGeneralMethod = generalMethod(context, "getVAID", str, SqTrackCommonKey.id, String.class);
        return objGeneralMethod == null ? "" : (String) objGeneralMethod;
    }

    public static boolean isSupported(Context context) {
        Object objGeneralMethod = generalMethod(context, "isSupport", null, "issupport", Boolean.class);
        if (objGeneralMethod == null) {
            return false;
        }
        return ((Boolean) objGeneralMethod).booleanValue();
    }
}
