package com.huyaudbunify.account;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum SdkComType {
    HYTPYE_MAJOR_YY(1),
    HYTPYE_MAJOR_HY(2),
    HYTPYE_HY(3);

    private int mType;

    SdkComType(int i) {
        this.mType = i;
    }

    public boolean isMajorYY() {
        return this.mType == 1;
    }

    public boolean isMajorHY() {
        return this.mType == 2;
    }

    public boolean isHY() {
        return this.mType == 3;
    }

    public int getType() {
        return this.mType;
    }

    public static SdkComType valueOf(int i) {
        if (i == 1) {
            return HYTPYE_MAJOR_YY;
        }
        if (i == 2) {
            return HYTPYE_MAJOR_HY;
        }
        if (i == 3) {
            return HYTPYE_HY;
        }
        return HYTPYE_MAJOR_YY;
    }
}
