package com.volcengine.h;

import android.database.ContentObserver;
import android.net.Uri;
import com.volcengine.androidcloud.common.log.AcLog;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class d extends ContentObserver {
    private final c a;
    private volatile boolean b;

    public d(c cVar) {
        super(cVar);
        this.b = false;
        this.a = cVar;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        if (this.a.a()) {
            AcLog.d(com.volcengine.j.b.g(), "Observer receive a change");
            if (this.b) {
                return;
            }
            this.b = true;
            AcLog.d(com.volcengine.j.b.g(), "Observer start to raise signal");
            this.a.sendEmptyMessage(1);
        }
    }
}
