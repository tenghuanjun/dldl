package com.volcengine.h;

import android.content.Context;
import android.os.Handler;
import com.volcengine.androidcloud.common.log.AcLog;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a extends c {
    public a(Context context, Handler.Callback callback) {
        super(context, callback);
        try {
            this.b.registerContentObserver(a(context), true, new d(this));
        } catch (Exception e) {
            AcLog.e("multi-process", e.getMessage());
        }
    }
}
