package com.bytedance.downloader.core;

import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
final class n extends TimerTask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ Timer f361a;
    private /* synthetic */ m b;

    n(m mVar, Timer timer) {
        this.b = mVar;
        this.f361a = timer;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() throws Throwable {
        this.f361a.cancel();
        this.b.i();
    }
}
