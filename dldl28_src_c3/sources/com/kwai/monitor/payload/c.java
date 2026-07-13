package com.kwai.monitor.payload;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class c<A, B> {
    public final A a;
    public final B b;

    public c(A a, B b) {
        this.a = a;
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
        A a = this.a;
        if (a == null) {
            if (cVar.a != null) {
                return false;
            }
        } else if (!a.equals(cVar.a)) {
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
        A a = this.a;
        int iHashCode = ((a == null ? 0 : a.hashCode()) + 31) * 31;
        B b = this.b;
        return iHashCode + (b != null ? b.hashCode() : 0);
    }
}
