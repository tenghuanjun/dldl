package com.huya.mtp.hyns.wup;

import com.duowan.taf.jce.JceStruct;
import com.huya.mtp.data.exception.DataException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WupError extends DataException {
    public int mCode;
    public String mFuncName;
    public String mMsg;
    private boolean mNeedPrintRsp;
    public JceStruct mResponse;

    public WupError(String str) {
        super(str);
    }

    public WupError(String str, Throwable th, int i, String str2, JceStruct jceStruct, boolean z) {
        super(str, th);
        this.mMsg = str;
        this.mCode = i;
        this.mFuncName = str2;
        this.mResponse = jceStruct;
        this.mNeedPrintRsp = z;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[WupError] code:");
        sb.append(this.mCode);
        sb.append(", Cause:");
        sb.append(getCause());
        sb.append(", funcName:");
        sb.append(this.mFuncName);
        sb.append(", detailError:");
        sb.append(this.mMsg);
        if (this.mResponse != null && this.mNeedPrintRsp) {
            sb.append(", mRsp:");
            sb.append(this.mResponse);
        }
        return sb.toString();
    }
}
