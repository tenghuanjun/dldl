package com.bytedance.applog.convert.hume.readapk;

/* JADX INFO: loaded from: classes2.dex */
public final class Pair<A, B> {
    private final A mFirst;
    private final B mSecond;

    private Pair(A a2, B b) {
        this.mFirst = a2;
        this.mSecond = b;
    }

    public static <A, B> Pair<A, B> of(A a2, B b) {
        return new Pair<>(a2, b);
    }

    public A getFirst() {
        return this.mFirst;
    }

    public B getSecond() {
        return this.mSecond;
    }

    public int hashCode() {
        A a2 = this.mFirst;
        int iHashCode = ((a2 == null ? 0 : a2.hashCode()) + 31) * 31;
        B b = this.mSecond;
        return iHashCode + (b != null ? b.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pair pair = (Pair) obj;
        A a2 = this.mFirst;
        if (a2 == null) {
            if (pair.mFirst != null) {
                return false;
            }
        } else if (!a2.equals(pair.mFirst)) {
            return false;
        }
        B b = this.mSecond;
        if (b == null) {
            if (pair.mSecond != null) {
                return false;
            }
        } else if (!b.equals(pair.mSecond)) {
            return false;
        }
        return true;
    }
}
