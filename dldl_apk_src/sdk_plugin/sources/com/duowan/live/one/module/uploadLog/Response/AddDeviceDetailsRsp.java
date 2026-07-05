package com.duowan.live.one.module.uploadLog.Response;

import com.duowan.auk.NoProguard;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AddDeviceDetailsRsp implements NoProguard {
    private String description;
    private String result;

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String toString() {
        return "AddDeviceDetailsRsp { result =" + this.result + ", description: " + this.description + AbstractJsonLexerKt.END_OBJ;
    }
}
