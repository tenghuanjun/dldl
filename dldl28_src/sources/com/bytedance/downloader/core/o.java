package com.bytedance.downloader.core;

import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
final class o extends TimerTask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ Timer f362a;
    private /* synthetic */ m b;

    o(m mVar, Timer timer) {
        this.b = mVar;
        this.f362a = timer;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        this.f362a.cancel();
        this.b.a();
    }
}
