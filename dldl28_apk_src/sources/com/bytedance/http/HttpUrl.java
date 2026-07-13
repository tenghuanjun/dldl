package com.bytedance.http;

import com.bytedance.http.b.g;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes2.dex */
public class HttpUrl {
    private final Builder mBuilder;

    public static final class Builder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private String mHost;
        private String mScheme;
        private int mPort = -1;
        private final List mPathSegments = new ArrayList();
        private final Map mQueryNamesAndValue = new HashMap();

        public final HttpUrl build() {
            return new HttpUrl(this);
        }

        public final Builder host(String str) {
            if (str == null) {
                throw new NullPointerException("host == null");
            }
            if (str.length() != 0) {
                this.mHost = str;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + str);
        }

        public final Builder pathSegment(List list) {
            this.mPathSegments.addAll(list);
            return this;
        }

        public final Builder port(int i) {
            if (i < 0 || i > 65535) {
                i = -1;
            }
            this.mPort = i;
            return this;
        }

        public final Builder queryParameter(Map map) {
            this.mQueryNamesAndValue.putAll(map);
            return this;
        }

        public final Builder scheme(String str) {
            if (str == null) {
                throw new NullPointerException("scheme is null");
            }
            String str2 = "http";
            if (!str.equalsIgnoreCase("http")) {
                str2 = "https";
                if (!str.equalsIgnoreCase("https")) {
                    str2 = "ws";
                    if (!str.equalsIgnoreCase("ws")) {
                        str2 = "wss";
                        if (!str.equalsIgnoreCase("wss")) {
                            throw new IllegalArgumentException("unexpected scheme: " + str);
                        }
                    }
                }
            }
            this.mScheme = str2;
            return this;
        }
    }

    HttpUrl(Builder builder) {
        this.mBuilder = builder;
    }

    private static int defaultPort(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    private int effectivePort() {
        return this.mBuilder.mPort != -1 ? this.mBuilder.mPort : defaultPort(this.mBuilder.mScheme);
    }

    private static void namesAndValuesToQueryString(StringBuilder sb, Map map) {
        boolean z = true;
        for (String str : map.keySet()) {
            String str2 = (String) map.get(str);
            if (str2 != null) {
                if (z) {
                    z = false;
                } else {
                    sb.append(Typography.amp);
                }
                sb.append(str);
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    private static void pathSegmentsToString(StringBuilder sb, List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            sb.append('/');
            sb.append((String) list.get(i));
        }
    }

    public String host() {
        return this.mBuilder.mHost;
    }

    public boolean isHttps() {
        return "https".equalsIgnoreCase(this.mBuilder.mScheme);
    }

    public boolean isIpDirect() {
        return g.a(this.mBuilder.mHost);
    }

    public boolean isWSS() {
        return "wss".equalsIgnoreCase(this.mBuilder.mScheme);
    }

    public List pathSegments() {
        return this.mBuilder.mPathSegments;
    }

    public int port() {
        return this.mBuilder.mPort;
    }

    public Map queryNamesAndValue() {
        return this.mBuilder.mQueryNamesAndValue;
    }

    public String scheme() {
        return this.mBuilder.mScheme;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mBuilder.mScheme);
        sb.append("://");
        if (this.mBuilder.mHost.indexOf(58) != -1) {
            sb.append('[');
            sb.append(this.mBuilder.mHost);
            sb.append(']');
        } else {
            sb.append(this.mBuilder.mHost);
        }
        int iEffectivePort = effectivePort();
        if (iEffectivePort != defaultPort(this.mBuilder.mScheme)) {
            sb.append(':');
            sb.append(iEffectivePort);
        }
        pathSegmentsToString(sb, this.mBuilder.mPathSegments);
        if (this.mBuilder.mQueryNamesAndValue.size() > 0) {
            sb.append('?');
            namesAndValuesToQueryString(sb, this.mBuilder.mQueryNamesAndValue);
        }
        return sb.toString();
    }

    public URI uri() {
        return new URI(toString());
    }
}
