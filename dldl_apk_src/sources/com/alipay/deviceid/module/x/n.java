package com.alipay.deviceid.module.x;

import android.content.Context;
import com.alipay.deviceid.module.rpc.report.open.model.ReportRequest;
import com.alipay.deviceid.module.rpc.report.open.model.ReportResult;
import java2jni_do_not_delete_this.java2jni_do_not_delete_this_library_deviceid_1607;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class n {
    static {
        java2jni_do_not_delete_this_library_deviceid_1607.loadLibrary();
    }

    public static native ReportRequest a(Context context, p pVar);

    public static native o a(ReportResult reportResult);
}
