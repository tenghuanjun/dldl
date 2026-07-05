package com.huya.mtp.hyns.volley;

import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.NSCall;
import java.util.Arrays;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(HttpUrlProtocol.class)
public interface HttpUrlApi {
    public static final int API_METHOD_ARGS_LENGTH = 2;

    public enum REQUEST_RULE {
        Body,
        Query,
        Jce_Body
    }

    <R, T> NSCall<T> get(HttpRequest<R> httpRequest, Class<T> cls);

    <R, T> NSCall<T> post(HttpRequest<R> httpRequest, Class<T> cls);

    public static class HttpRequest<Req> {
        Byte[] body;
        Map<String, String> header;
        Map<String, String> params;
        Req req;
        REQUEST_RULE rule = REQUEST_RULE.Body;
        String url;

        public REQUEST_RULE getRule() {
            return this.rule;
        }

        public HttpRequest<Req> setRule(REQUEST_RULE request_rule) {
            this.rule = request_rule;
            return this;
        }

        public String getUrl() {
            return this.url;
        }

        public HttpRequest<Req> setUrl(String str) {
            this.url = str;
            return this;
        }

        public Req getReq() {
            return this.req;
        }

        public HttpRequest<Req> setReq(Req req) {
            this.req = req;
            return this;
        }

        public Map<String, String> getParams() {
            return this.params;
        }

        public HttpRequest<Req> setParams(Map<String, String> map) {
            this.params = map;
            return this;
        }

        public Map<String, String> getHeader() {
            return this.header;
        }

        public HttpRequest<Req> setHeader(Map<String, String> map) {
            this.header = map;
            return this;
        }

        public String toString() {
            return "HttpRequest{url='" + this.url + "', req=" + this.req + ", body=" + Arrays.toString(this.body) + ", params=" + this.params + ", header=" + this.header + ", rule=" + this.rule + AbstractJsonLexerKt.END_OBJ;
        }
    }
}
