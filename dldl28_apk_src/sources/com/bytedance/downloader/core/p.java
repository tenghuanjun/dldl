package com.bytedance.downloader.core;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class p {
    private static /* synthetic */ boolean c = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f363a;
    private final List b = new ArrayList();

    public p(int i) {
        if (!c && i <= 0) {
            throw new AssertionError();
        }
        this.f363a = i;
    }

    public final int a() {
        return this.b.size();
    }

    public final boolean a(m mVar) {
        if (this.b.contains(mVar) || a() >= this.f363a) {
            return false;
        }
        this.b.add(mVar);
        return true;
    }

    public final int b() {
        return this.f363a;
    }

    public final boolean b(m mVar) {
        if (!this.b.contains(mVar)) {
            return false;
        }
        this.b.remove(mVar);
        return true;
    }
}
