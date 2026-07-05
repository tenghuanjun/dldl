package com.huya.mtp.utils.pack;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Uint64 {
    private long v;

    public Uint64(int i) {
        if (i < 0) {
            this.v = Long.valueOf(Integer.toBinaryString(i), 2).longValue();
        } else {
            this.v = i;
        }
    }

    public Uint64(long j) {
        this.v = j;
    }

    public Uint64(String str) {
        this.v = Long.valueOf(str).longValue();
    }

    public static Uint64 toUInt(int i) {
        return new Uint64(i);
    }

    public int toInt() {
        return (int) this.v;
    }

    public long toLong() {
        return this.v;
    }

    public String toString() {
        return Long.toString(this.v);
    }

    public int hashCode() {
        long j = this.v;
        return 31 + ((int) (j ^ (j >>> 32)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.v == ((Uint64) obj).v;
    }
}
