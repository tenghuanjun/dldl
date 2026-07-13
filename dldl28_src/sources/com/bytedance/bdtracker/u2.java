package com.bytedance.bdtracker;

import com.bytedance.applog.oneid.IDBindCallback;

/* JADX INFO: loaded from: classes2.dex */
public final class u2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ IDBindCallback f327a;
    public final /* synthetic */ int b;
    public final /* synthetic */ String c;

    public u2(IDBindCallback iDBindCallback, int i, String str) {
        this.f327a = iDBindCallback;
        this.b = i;
        this.c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f327a.onFail(this.b, this.c);
    }
}
