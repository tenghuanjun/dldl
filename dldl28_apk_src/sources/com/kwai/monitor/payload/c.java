package com.kwai.monitor.payload;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c<A, B> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A f496a;
    public final B b;

    public c(A a2, B b) {
        this.f496a = a2;
        this.b = b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        A a2 = this.f496a;
        if (a2 == null) {
            if (cVar.f496a != null) {
                return false;
            }
        } else if (!a2.equals(cVar.f496a)) {
            return false;
        }
        B b = this.b;
        if (b == null) {
            if (cVar.b != null) {
                return false;
            }
        } else if (!b.equals(cVar.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        A a2 = this.f496a;
        int iHashCode = ((a2 == null ? 0 : a2.hashCode()) + 31) * 31;
        B b = this.b;
        return iHashCode + (b != null ? b.hashCode() : 0);
    }
}
