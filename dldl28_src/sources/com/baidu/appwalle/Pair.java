package com.baidu.appwalle;

/* JADX INFO: loaded from: classes2.dex */
final class Pair<A, B> {
    private final A mFirst;
    private final B mSecond;

    private Pair(A first, B second) {
        this.mFirst = first;
        this.mSecond = second;
    }

    public static <A, B> Pair<A, B> of(A first, B second) {
        return new Pair<>(first, second);
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
            return pair.mSecond == null;
        }
        return b.equals(pair.mSecond);
    }
}
