package com.huyaudb;

import com.huyaudbunify.inter.IIHuyaAuthCoreCallBack;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaAuthCore {
    private static volatile HuyaAuthCore mHuyaAuthCore;
    private IIHuyaAuthCoreCallBack mAuthCorecallBack;

    public native void init();

    public native void receiveNet(byte[] bArr, int i, int i2, int i3);

    public native byte[] sendMsg(long j, byte[] bArr);

    public native void unInit();

    static {
        System.loadLibrary("udbauthunify");
    }

    public static HuyaAuthCore getInstance() {
        if (mHuyaAuthCore == null) {
            synchronized (HuyaAuthCore.class) {
                if (mHuyaAuthCore == null) {
                    mHuyaAuthCore = new HuyaAuthCore();
                }
            }
        }
        return mHuyaAuthCore;
    }

    public void setAuthCorecallBack(IIHuyaAuthCoreCallBack iIHuyaAuthCoreCallBack) {
        this.mAuthCorecallBack = iIHuyaAuthCoreCallBack;
    }

    public void sendNet(long j, int i, byte[] bArr) {
        IIHuyaAuthCoreCallBack iIHuyaAuthCoreCallBack = this.mAuthCorecallBack;
        if (iIHuyaAuthCoreCallBack != null) {
            iIHuyaAuthCoreCallBack.sendNet(j, i, bArr);
        }
    }

    public void receiveMsg(long j, byte[] bArr) {
        IIHuyaAuthCoreCallBack iIHuyaAuthCoreCallBack = this.mAuthCorecallBack;
        if (iIHuyaAuthCoreCallBack != null) {
            iIHuyaAuthCoreCallBack.receiveMsg(j, bArr);
        }
    }

    public void log(byte[] bArr) {
        IIHuyaAuthCoreCallBack iIHuyaAuthCoreCallBack = this.mAuthCorecallBack;
        if (iIHuyaAuthCoreCallBack != null) {
            iIHuyaAuthCoreCallBack.log(new String(bArr));
        }
    }
}
