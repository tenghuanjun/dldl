package com.duowan.live.common.packer.walle;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
final class Pair<A, B> {
    private final A f;
    private final B s;

    private Pair(A a, B b) {
        this.f = a;
        this.s = b;
    }

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    public A getFirst() {
        return this.f;
    }

    public B getSecond() {
        return this.s;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pair pair = (Pair) obj;
        A a = this.f;
        if (a == null ? pair.f != null : !a.equals(pair.f)) {
            return false;
        }
        B b = this.s;
        B b2 = pair.s;
        return b != null ? b.equals(b2) : b2 == null;
    }

    public int hashCode() {
        A a = this.f;
        int iHashCode = (a != null ? a.hashCode() : 0) * 31;
        B b = this.s;
        return iHashCode + (b != null ? b.hashCode() : 0);
    }
}
