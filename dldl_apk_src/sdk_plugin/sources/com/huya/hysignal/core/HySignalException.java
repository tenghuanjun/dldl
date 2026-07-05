package com.huya.hysignal.core;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HySignalException extends Exception {
    private int mErrorCode;
    private int mErrorType;

    public HySignalException(int i, int i2) {
        this.mErrorType = i;
        this.mErrorCode = i2;
    }

    public int getErrorType() {
        return this.mErrorType;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "HySignalException{ErrorType=" + this.mErrorType + ", ErrorCode=" + this.mErrorCode + AbstractJsonLexerKt.END_OBJ;
    }
}
