package com.bytedance.bdtracker;

import com.bytedance.applog.oneid.IDBindCallback;
import com.bytedance.applog.oneid.IDBindResult;

/* JADX INFO: loaded from: classes2.dex */
public final class v2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ IDBindCallback f331a;
    public final /* synthetic */ IDBindResult b;

    public v2(IDBindCallback iDBindCallback, IDBindResult iDBindResult) {
        this.f331a = iDBindCallback;
        this.b = iDBindResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f331a.onSuccess(this.b);
    }
}
