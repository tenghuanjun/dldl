package com.duowan.auk.http.v2.wup;

import com.android.volley.VolleyError;
import com.duowan.taf.jce.JceStruct;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class WupError extends VolleyError {
    public int mCode;
    public String mFuncName;
    public JceStruct mResponse;

    public WupError(String str) {
        super(str);
    }

    public WupError(String str, Throwable th, int i, String str2, JceStruct jceStruct) {
        super(str, th);
        this.mCode = i;
        this.mFuncName = str2;
        this.mResponse = jceStruct;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ZeroErrorMsg] code:");
        sb.append(this.mCode);
        sb.append(", Cause:");
        sb.append(getCause());
        sb.append(", funcName:");
        sb.append(this.mFuncName);
        if (this.mResponse != null) {
            sb.append(", mRsp:");
            sb.append(this.mResponse);
        }
        return sb.toString();
    }
}
