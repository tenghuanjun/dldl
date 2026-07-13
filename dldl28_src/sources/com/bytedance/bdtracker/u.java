package com.bytedance.bdtracker;

import com.bytedance.bdtracker.b;

/* JADX INFO: loaded from: classes2.dex */
public final class u implements b.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ t3 f326a;

    public u(t3 t3Var) {
        this.f326a = t3Var;
    }

    @Override // com.bytedance.bdtracker.b.d
    public boolean a(d dVar) {
        if (b.b.a(dVar) && !dVar.isAutoTrackPageIgnored(this.f326a.E)) {
            return !this.f326a.D || dVar.getInitConfig() == null || dVar.getInitConfig().isAutoTrackFragmentEnabled();
        }
        return false;
    }
}
