package com.huya.hysignal.core;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HySignalError {
    private int mErrorCode;
    private int mErrorType;

    public HySignalError(int i, int i2) {
        this.mErrorType = i;
        this.mErrorCode = i2;
    }

    public int getErrType() {
        return this.mErrorType;
    }

    public int getErrCode() {
        return this.mErrorCode;
    }

    public String toString() {
        return "HySignalError{ErrorType=" + this.mErrorType + ", ErrorCode=" + this.mErrorCode + AbstractJsonLexerKt.END_OBJ;
    }
}
