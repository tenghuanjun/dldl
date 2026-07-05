package com.duowan.auk.util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Ver {
    public int mBuild;
    public int mMajor;
    public int mMinor;

    public boolean bigThan(Ver ver) {
        int i = this.mMajor;
        int i2 = ver.mMajor;
        return i > i2 || (i == i2 && this.mMinor > ver.mMinor) || (this.mMajor == ver.mMajor && this.mMinor == ver.mMinor && this.mBuild > ver.mBuild);
    }

    public boolean smallThan(Ver ver) {
        int i = this.mMajor;
        int i2 = ver.mMajor;
        return i < i2 || (i == i2 && this.mMinor < ver.mMinor) || (this.mMajor == ver.mMajor && this.mMinor == ver.mMinor && this.mBuild < ver.mBuild);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Ver)) {
            return false;
        }
        Ver ver = (Ver) obj;
        return this.mMajor == ver.mMajor && this.mMinor == ver.mMinor && this.mBuild == ver.mBuild;
    }

    public int hashCode() {
        int i = this.mMajor;
        return (i * 100) + (i * 10) + this.mBuild;
    }

    public String toString() {
        return String.format("%d.%d.%d", Integer.valueOf(this.mMajor), Integer.valueOf(this.mMinor), Integer.valueOf(this.mBuild));
    }
}
