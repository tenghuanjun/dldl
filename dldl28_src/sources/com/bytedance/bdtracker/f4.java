package com.bytedance.bdtracker;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.applog.log.LoggerImpl;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* JADX INFO: loaded from: classes2.dex */
public class f4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile String f251a;

    public static String a(Context context, i1 i1Var) {
        AdvertisingIdClient.Info advertisingIdInfo;
        if (TextUtils.isEmpty(f251a)) {
            synchronized (f4.class) {
                if (!TextUtils.isEmpty(f251a)) {
                    return f251a;
                }
                try {
                    advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                } catch (Throwable th) {
                    if (!(th instanceof ClassNotFoundException) && !(th instanceof NoClassDefFoundError)) {
                        LoggerImpl.global().error("Query Gaid failed", th, new Object[0]);
                    }
                }
                String id = advertisingIdInfo != null ? advertisingIdInfo.getId() : null;
                if (TextUtils.isEmpty(id)) {
                    id = i1Var.f.getString("google_aid", null);
                } else if (!TextUtils.equals(i1Var.f.getString("google_aid", null), id) && !TextUtils.isEmpty(id)) {
                    i1Var.f.edit().putString("google_aid", id).apply();
                }
                f251a = id;
            }
        }
        return f251a;
    }
}
