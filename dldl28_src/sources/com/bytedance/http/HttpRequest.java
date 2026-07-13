package com.bytedance.http;

import com.bytedance.http.HttpHeaders;
import com.bytedance.http.HttpUrl;
import com.bytedance.http.b.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class HttpRequest {
    private final byte[] mBody;
    private final Map mCookies;
    private final Map mExtras;
    private final HttpHeaders mHeaders;
    private final List mHosts;
    private final HttpMethod mMethod;
    private final List mPathSegments;
    private final HttpUrl mUrl;

    public static final class Builder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final String SCHEME_HTTP = "http";
        private static final String SCHEME_HTTPS = "https";
        private static final String SCHEME_WS = "ws";
        private static final String SCHEME_WSS = "wss";
        private byte[] mBody;
        private final Map mCookies;
        private String mCurrentHost;
        private final Map mExtras;
        private final HttpHeaders.Builder mHeaders;
        private final List mHosts;
        private HttpMethod mMethod;
        private final List mPathSegments;
        private int mPort;
        private final Map mQueryNamesAndValues;
        private String mScheme;

        public Builder() {
            this.mMethod = HttpMethod.GET;
            this.mScheme = SCHEME_HTTPS;
            this.mPort = -1;
            this.mHosts = new ArrayList();
            this.mPathSegments = new ArrayList();
            this.mQueryNamesAndValues = new HashMap();
            this.mCookies = new HashMap();
            this.mExtras = new HashMap();
            this.mHeaders = new HttpHeaders.Builder();
        }

        Builder(HttpRequest httpRequest) {
            this.mMethod = HttpMethod.GET;
            this.mScheme = SCHEME_HTTPS;
            this.mPort = -1;
            ArrayList arrayList = new ArrayList();
            this.mHosts = arrayList;
            ArrayList arrayList2 = new ArrayList();
            this.mPathSegments = arrayList2;
            HashMap map = new HashMap();
            this.mQueryNamesAndValues = map;
            this.mCookies = new HashMap();
            HashMap map2 = new HashMap();
            this.mExtras = map2;
            this.mMethod = httpRequest.mMethod;
            this.mScheme = httpRequest.mUrl.scheme();
            this.mPort = httpRequest.mUrl.port();
            this.mCurrentHost = httpRequest.mUrl.host();
            arrayList.addAll(httpRequest.hosts());
            arrayList2.addAll(httpRequest.mUrl.pathSegments());
            map.putAll(httpRequest.url().queryNamesAndValue());
            map2.putAll(httpRequest.mExtras);
            this.mHeaders = new HttpHeaders.Builder();
            for (String str : httpRequest.mHeaders.names()) {
                this.mHeaders.add(str, httpRequest.mHeaders.get(str));
            }
            this.mBody = httpRequest.mBody;
        }

        public final Builder addCookie(String str, String str2) {
            if (g.c(str) && g.c(str2)) {
                this.mCookies.put(str, str2);
            }
            return this;
        }

        public final Builder addExtra(String str, String str2) {
            if (g.c(str) && g.c(str2)) {
                this.mExtras.put(str, str2);
            }
            if (g.c(str) && !g.c(str2)) {
                this.mExtras.remove(str);
            }
            return this;
        }

        public final Builder addHeader(String str, String str2) {
            if (g.c(str) && g.c(str2)) {
                if (this.mHeaders.get(str) != null) {
                    this.mHeaders.removeAll(str);
                }
                this.mHeaders.add(str, str2);
            }
            return this;
        }

        public final Builder addHost(String str) {
            if (g.c(str)) {
                this.mHosts.add(str);
            }
            return this;
        }

        public final Builder addPathSegment(String str) {
            if (g.c(str)) {
                this.mPathSegments.add(str);
            }
            return this;
        }

        public final Builder addQueryParameter(String str, String str2) {
            if (g.c(str) && g.c(str2)) {
                this.mQueryNamesAndValues.put(str, str2);
            }
            return this;
        }

        public final Builder body(String str) {
            this.mBody = str.getBytes();
            return this;
        }

        public final Builder body(byte[] bArr) {
            this.mBody = bArr;
            return this;
        }

        public final HttpRequest build() {
            if (this.mCurrentHost == null) {
                Iterator it = this.mHosts.iterator();
                if (it.hasNext()) {
                    this.mCurrentHost = (String) it.next();
                }
            }
            return new HttpRequest(this);
        }

        public final Builder extras(Map map) {
            this.mExtras.putAll(map);
            return this;
        }

        public final Builder get() {
            this.mMethod = HttpMethod.GET;
            return this;
        }

        public final Builder host(String str) {
            this.mCurrentHost = str;
            return addHost(str);
        }

        public final Builder http() {
            this.mScheme = SCHEME_HTTP;
            return this;
        }

        public final Builder https() {
            this.mScheme = SCHEME_HTTPS;
            return this;
        }

        public final Builder port(int i) {
            this.mPort = i;
            return this;
        }

        public final Builder post() {
            this.mMethod = HttpMethod.POST;
            return this;
        }

        public final Builder removeHeader(String str) {
            this.mHeaders.removeAll(str);
            return this;
        }

        public final Builder ws() {
            this.mScheme = SCHEME_WS;
            return this;
        }

        public final Builder wss() {
            this.mScheme = SCHEME_WSS;
            return this;
        }
    }

    HttpRequest(Builder builder) {
        HashMap map = new HashMap();
        this.mCookies = map;
        ArrayList arrayList = new ArrayList();
        this.mHosts = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.mPathSegments = arrayList2;
        HashMap map2 = new HashMap();
        this.mExtras = map2;
        this.mMethod = builder.mMethod;
        this.mHeaders = builder.mHeaders.build();
        this.mBody = builder.mBody;
        arrayList.addAll(builder.mHosts);
        arrayList2.addAll(builder.mPathSegments);
        map.putAll(builder.mCookies);
        map2.putAll(builder.mExtras);
        this.mUrl = new HttpUrl.Builder().scheme(builder.mScheme).port(builder.mPort).host(builder.mCurrentHost).pathSegment(builder.mPathSegments).queryParameter(builder.mQueryNamesAndValues).build();
    }

    private String getPathSegments() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.mPathSegments.size(); i++) {
            sb.append("/");
            sb.append((String) this.mPathSegments.get(i));
        }
        return sb.toString();
    }

    public byte[] body() {
        return this.mBody;
    }

    public Map cookies() {
        return this.mCookies;
    }

    public Map extras() {
        return this.mExtras;
    }

    public HttpHeaders headers() {
        return this.mHeaders;
    }

    public List hosts() {
        return this.mHosts;
    }

    public boolean isHttps() {
        return this.mUrl.isHttps();
    }

    public String masterHost() {
        return this.mHosts.size() > 0 ? (String) this.mHosts.get(0) : "";
    }

    public HttpMethod method() {
        return this.mMethod;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public List pathSegments() {
        return this.mPathSegments;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String pathSegments = getPathSegments();
        sb.append(g.c(pathSegments) ? this.mMethod.toString() + StringUtils.SPACE + pathSegments + " HTTP/1.1\n" : this.mMethod.toString() + " / HTTP/1.1\n");
        if (hosts().size() > 0) {
            sb.append("Host: " + ((String) hosts().get(0)) + StringUtils.LF);
        }
        for (String str : this.mHeaders.names()) {
            sb.append(str + ": " + this.mHeaders.get(str) + StringUtils.LF);
        }
        byte[] bArr = this.mBody;
        if (bArr != null && bArr.length > 0) {
            sb.append("\r\n");
            sb.append(new String(this.mBody));
        }
        return sb.toString();
    }

    public HttpUrl url() {
        return this.mUrl;
    }
}
