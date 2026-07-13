package com.bytedance.hume.readapk;

/* JADX INFO: loaded from: classes2.dex */
public final class c<A, B> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final A f402a;
    private final B b;

    private c(A a2, B b) {
        this.f402a = a2;
        this.b = b;
    }

    public static <A, B> c<A, B> a(A a2, B b) {
        return new c<>(a2, b);
    }

    public A a() {
        return this.f402a;
    }

    public B b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        A a2 = this.f402a;
        if (a2 == null) {
            if (cVar.f402a != null) {
                return false;
            }
        } else if (!a2.equals(cVar.f402a)) {
            return false;
        }
        B b = this.b;
        B b2 = cVar.b;
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
        A a2 = this.f402a;
        int iHashCode = ((a2 == null ? 0 : a2.hashCode()) + 31) * 31;
        B b = this.b;
        return iHashCode + (b != null ? b.hashCode() : 0);
    }
}
