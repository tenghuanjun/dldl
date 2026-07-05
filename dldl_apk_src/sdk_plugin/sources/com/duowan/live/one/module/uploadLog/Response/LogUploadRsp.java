package com.duowan.live.one.module.uploadLog.Response;

import com.duowan.auk.NoProguard;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LogUploadRsp implements NoProguard {
    private String description;
    private String result;
    private String url;

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String toString() {
        return "LogUploadRangeRsp{result=" + this.result + ", url: " + this.url + ", description: " + this.description + AbstractJsonLexerKt.END_OBJ;
    }
}
