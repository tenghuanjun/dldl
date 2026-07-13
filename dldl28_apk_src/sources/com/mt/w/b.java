package com.mt.w;

/* JADX INFO: loaded from: classes3.dex */
public final class b<A, B> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A f838a;
    public final B b;

    public b(A a2, B b) {
        this.f838a = a2;
        this.b = b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        A a2 = this.f838a;
        if (a2 == null) {
            if (bVar.f838a != null) {
                return false;
            }
        } else if (!a2.equals(bVar.f838a)) {
            return false;
        }
        B b = this.b;
        B b2 = bVar.b;
        if (b == null) {
            if (b2 != null) {
                return false;
            }
        } else if (!b.equals(b2)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        A a2 = this.f838a;
        int iHashCode = ((a2 == null ? 0 : a2.hashCode()) + 31) * 31;
        B b = this.b;
        return iHashCode + (b != null ? b.hashCode() : 0);
    }
}
