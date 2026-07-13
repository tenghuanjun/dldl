package com.bytedance.http;

import com.bytedance.http.HttpHeaders;
import com.bytedance.http.b.g;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class HttpResponse {
    private final String mBody;
    private final int mCode;
    private final Map mExtras;
    private final HttpHeaders mHeaders;
    private final String mMessage;
    private final HttpRequest mRequest;

    public static class Builder {
        private String mBody;
        private int mCode;
        private final Map mExtras;
        private final HttpHeaders.Builder mHeaders;
        private String mMessage;
        private HttpRequest mRequest;

        public Builder() {
            this.mCode = -1;
            this.mMessage = "";
            this.mBody = "";
            this.mExtras = new HashMap();
            this.mHeaders = new HttpHeaders.Builder();
        }

        Builder(HttpResponse httpResponse) {
            this.mCode = -1;
            this.mMessage = "";
            this.mBody = "";
            HashMap map = new HashMap();
            this.mExtras = map;
            this.mRequest = httpResponse.mRequest;
            this.mCode = httpResponse.mCode;
            this.mMessage = httpResponse.mMessage;
            this.mBody = httpResponse.mBody;
            this.mHeaders = httpResponse.mHeaders.newBuilder();
            map.putAll(httpResponse.mExtras);
        }

        public Builder addExtra(String str, String str2) {
            if (g.c(str) && g.c(str2)) {
                this.mExtras.put(str, str2);
            }
            if (g.c(str) && !g.c(str2)) {
                this.mExtras.remove(str);
            }
            return this;
        }

        public Builder addHeader(String str, String str2) {
            if (g.c(str) && g.c(str2)) {
                this.mHeaders.add(str, str2);
            }
            return this;
        }

        public Builder body(String str) {
            this.mBody = str;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(this);
        }

        public Builder code(int i) {
            this.mCode = i;
            return this;
        }

        public Builder extras(Map map) {
            this.mExtras.putAll(map);
            return this;
        }

        public Builder message(String str) {
            this.mMessage = str;
            return this;
        }

        public Builder request(HttpRequest httpRequest) {
            this.mRequest = httpRequest;
            return this;
        }
    }

    HttpResponse(Builder builder) {
        HashMap map = new HashMap();
        this.mExtras = map;
        this.mRequest = builder.mRequest;
        this.mCode = builder.mCode;
        this.mMessage = builder.mMessage;
        this.mBody = builder.mBody;
        this.mHeaders = builder.mHeaders.build();
        map.putAll(builder.mExtras);
    }

    public String body() {
        return this.mBody;
    }

    public int code() {
        return this.mCode;
    }

    public Map extras() {
        return this.mExtras;
    }

    public HttpHeaders headers() {
        return this.mHeaders;
    }

    public String message() {
        return this.mMessage;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public HttpRequest request() {
        return this.mRequest;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HTTP/1.1 ");
        sb.append(this.mCode);
        sb.append(StringUtils.SPACE);
        sb.append(this.mMessage);
        sb.append(StringUtils.LF);
        for (String str : this.mHeaders.names()) {
            sb.append(str);
            sb.append(": ");
            sb.append(this.mHeaders.get(str));
            sb.append(StringUtils.LF);
        }
        sb.append("\r\n");
        sb.append(this.mBody);
        return sb.toString();
    }
}
