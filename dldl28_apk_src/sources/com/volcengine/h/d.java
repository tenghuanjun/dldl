package com.volcengine.h;

import android.database.ContentObserver;
import android.net.Uri;
import com.volcengine.androidcloud.common.log.AcLog;

/* JADX INFO: loaded from: classes3.dex */
public class d extends ContentObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f1142a;
    private volatile boolean b;

    public d(c cVar) {
        super(cVar);
        this.b = false;
        this.f1142a = cVar;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        if (this.f1142a.a()) {
            AcLog.d(com.volcengine.j.b.g(), "Observer receive a change");
            if (this.b) {
                return;
            }
            this.b = true;
            AcLog.d(com.volcengine.j.b.g(), "Observer start to raise signal");
            this.f1142a.sendEmptyMessage(1);
        }
    }
}
