package com.huya.mtp.data.exception;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class JsonParseException extends ParseException {
    public final String mResponse;

    public JsonParseException(String str) {
        this.mResponse = str;
    }

    public JsonParseException(String str, String str2) {
        super(str2);
        this.mResponse = str;
    }

    public JsonParseException(String str, String str2, Throwable th) {
        super(str2, th);
        this.mResponse = str;
    }

    public JsonParseException(String str, Throwable th) {
        super(th);
        this.mResponse = str;
    }
}
