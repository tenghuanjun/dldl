package com.alipay.deviceid.module.x;

import android.content.Context;
import com.alipay.zoloz.toyger.blob.BlobManager;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class bw {
    public static synchronized String a(Context context, String str) {
        return cb.a(context, "alipay_device_id_storage", "apdidtoekn" + str);
    }

    public static synchronized void a() {
    }

    public static synchronized void a(Context context, String str, String str2) {
        c(context, BlobManager.BLOB_ELEM_IMAGE_HASHCODE + str, str2);
    }

    public static synchronized void b(Context context, String str, String str2) {
        c(context, "apdidtoekn" + str, str2);
    }

    private static void c(Context context, String str, String str2) {
        cb.a(context, "alipay_device_id_storage", str, str2);
    }
}
