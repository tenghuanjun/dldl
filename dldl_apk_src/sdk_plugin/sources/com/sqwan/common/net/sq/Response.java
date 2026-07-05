package com.sqwan.common.net.sq;

import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Response {
    public static final int STATE_FAIL = 0;
    public static final int STATE_OK = 1;
    private String data;
    private Map<String, String> headers;
    private String jsonStr;
    private String message;
    private String reqId;
    private int state = 0;
    private int statusCode;

    public void setState(int i) {
        this.state = i;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getMessage() {
        return this.message;
    }

    public int getState() {
        return this.state;
    }

    public String getJsonStr() {
        return this.jsonStr;
    }

    public void setJsonStr(String str) {
        this.jsonStr = str;
    }

    public void setData(String str) {
        this.data = str;
    }

    public String getData() {
        return this.data;
    }

    public String getReqId() {
        return this.reqId;
    }

    public void setReqId(String str) {
        this.reqId = str;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public String toString() {
        return "Response{state=" + this.state + ", message='" + this.message + "', data='" + this.data + "', reqId='" + this.reqId + "', statusCode=" + this.statusCode + ", headers=" + this.headers + AbstractJsonLexerKt.END_OBJ;
    }
}
