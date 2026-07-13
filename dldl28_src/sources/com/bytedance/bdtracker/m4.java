package com.bytedance.bdtracker;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public final class m4 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Context f293a;

    public m4(Context context) {
        this.f293a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        l4.b.b(this.f293a).edit().putBoolean("_install_started_v2", true).apply();
    }
}
