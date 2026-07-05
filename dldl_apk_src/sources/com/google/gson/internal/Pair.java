package com.google.gson.internal;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public final class Pair<FIRST, SECOND> {
    public final FIRST first;
    public final SECOND second;

    public Pair(FIRST first, SECOND second) {
        this.first = first;
        this.second = second;
    }

    public int hashCode() {
        FIRST first = this.first;
        int iHashCode = (first != null ? first.hashCode() : 0) * 17;
        SECOND second = this.second;
        return iHashCode + ((second != null ? second.hashCode() : 0) * 17);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return equal(this.first, pair.first) && equal(this.second, pair.second);
    }

    private static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public String toString() {
        return String.format("{%s,%s}", this.first, this.second);
    }
}
