package com.volcengine.common.innerapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface HttpService {

    public interface Callback {
        void onResponse(Response response);
    }

    public static class Response {
        private final int code;
        private final String message;
        private final String requestBody;
        private final Map<String, String> requestHeaders;
        private final String responseBody;
        private final Map<String, String> responseExtras;
        private final Map<String, String> responseHeaders;

        public Response(int i, String str, Map<String, String> map, Map<String, String> map2, String str2, Map<String, String> map3, byte[] bArr) {
            this.code = i;
            this.message = str;
            this.responseHeaders = new HashMap(map);
            this.responseExtras = new HashMap(map2);
            this.responseBody = str2;
            this.requestHeaders = new HashMap(map3);
            this.requestBody = new String(bArr);
        }

        public int code() {
            return this.code;
        }

        public String message() {
            return this.message;
        }

        public String requestBody() {
            return this.requestBody;
        }

        public Map<String, String> requestHeaders() {
            return this.requestHeaders;
        }

        public String responseBody() {
            return this.responseBody;
        }

        public Map<String, String> responseExtras() {
            return this.responseExtras;
        }

        public Map<String, String> responseHeaders() {
            return this.responseHeaders;
        }
    }

    void cancelAll();

    void get(List<String> list, List<String> list2, Map<String, String> map, Map<String, String> map2, Callback callback);

    void init(int i);

    void post(List<String> list, List<String> list2, Map<String, String> map, Map<String, String> map2, String str, Callback callback);

    void setCommonHeader(Map<String, String> map);
}
