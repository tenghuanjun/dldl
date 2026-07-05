package com.huya.mtp.utils.pack;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Uint8 {
    private long v;

    public Uint8(int i) {
        if (i < 0) {
            this.v = i & 255;
        } else {
            this.v = i;
        }
    }

    public Uint8(String str) {
        this.v = Long.valueOf(str).longValue();
    }

    public static Uint8 toUInt(int i) {
        return new Uint8(i);
    }

    public int toInt() {
        return (int) this.v;
    }

    public byte toByte() {
        return (byte) this.v;
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
        return obj != null && getClass() == obj.getClass() && this.v == ((Uint8) obj).v;
    }
}
