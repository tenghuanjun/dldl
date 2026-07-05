package com.sq.webview.util;

import android.os.SystemClock;
import java.net.InetAddress;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NetworkStatus {
    public long callEndTime;
    public long callStartTime;
    public InetAddress connectAddress;
    public long connectEndTime;
    public long connectStartTime;
    public long dnsEndTime;
    public long dnsStartTime;
    public Exception error;
    public int httpStatus = -1;
    public final HttpUrl originUrl;
    public long requestBodyEndTime;
    public long requestBodyStartTime;
    public long requestHeadEndTime;
    public long requestHeadStartTime;
    public long responseBodyEndTime;
    public long responseBodyStartTime;
    public long responseHeadEndTime;
    public long responseHeadStartTime;
    public long sslEndTime;
    public long sslStartTime;
    public HttpUrl url;
    public boolean useLocalDns;

    public NetworkStatus(HttpUrl originUrl) {
        this.originUrl = originUrl;
        this.url = originUrl;
    }

    public String path() {
        HttpUrl httpUrl = this.url;
        return httpUrl == null ? "" : httpUrl.encodedPath();
    }

    public String scheme() {
        HttpUrl httpUrl = this.url;
        return httpUrl == null ? "" : httpUrl.scheme();
    }

    public String domain() {
        InetAddress inetAddress = this.connectAddress;
        return inetAddress == null ? "" : inetAddress.getHostName();
    }

    public String serverIp() {
        InetAddress inetAddress = this.connectAddress;
        String hostAddress = inetAddress == null ? "" : inetAddress.getHostAddress();
        return hostAddress == null ? "" : hostAddress;
    }

    public boolean isSuccess() {
        int i = this.httpStatus;
        return (i >= 200 && i <= 299) || this.httpStatus == 304;
    }

    public void callStart() {
        if (this.callStartTime == 0) {
            this.callStartTime = now();
        }
    }

    public void dnsStart() {
        if (this.dnsStartTime == 0) {
            this.dnsStartTime = now();
        }
    }

    public void dnsEnd() {
        this.dnsEndTime = now();
    }

    public void connectStart() {
        if (this.connectStartTime == 0) {
            this.connectStartTime = now();
        }
    }

    public void secureConnectStart() {
        if (this.sslStartTime == 0) {
            this.sslStartTime = now();
        }
    }

    public void secureConnectEnd() {
        this.sslEndTime = now();
    }

    public void connectEnd() {
        this.connectEndTime = now();
    }

    public void requestHeadersStart() {
        if (this.requestHeadStartTime == 0) {
            this.requestHeadStartTime = now();
        }
    }

    public void requestHeadersEnd() {
        this.requestHeadEndTime = now();
    }

    public void requestBodyStart() {
        if (this.requestBodyStartTime == 0) {
            this.requestBodyStartTime = now();
        }
    }

    public void requestBodyEnd() {
        this.requestBodyEndTime = now();
    }

    public void responseHeadersStart() {
        if (this.responseHeadStartTime == 0) {
            this.responseHeadStartTime = now();
        }
    }

    public void responseHeadersEnd() {
        this.responseHeadEndTime = now();
    }

    public void responseBodyStart() {
        if (this.responseBodyStartTime == 0) {
            this.responseBodyStartTime = now();
        }
    }

    public void responseBodyEnd() {
        this.responseBodyEndTime = now();
    }

    public void callEnd() {
        this.callEndTime = now();
    }

    public long callCost() {
        long j = this.callEndTime;
        if (j != 0) {
            long j2 = this.callStartTime;
            if (j2 != 0) {
                return j - j2;
            }
        }
        if (this.callStartTime != 0) {
            return now() - this.callStartTime;
        }
        return -1L;
    }

    public long dnsCost() {
        long j = this.dnsEndTime;
        if (j != 0) {
            long j2 = this.dnsStartTime;
            if (j2 != 0) {
                return j - j2;
            }
        }
        return 0L;
    }

    public long sslCost() {
        long j = this.sslEndTime;
        if (j != 0) {
            long j2 = this.sslStartTime;
            if (j2 != 0) {
                return j - j2;
            }
        }
        return 0L;
    }

    public long connectCost() {
        long j = this.connectEndTime;
        if (j != 0) {
            long j2 = this.connectStartTime;
            if (j2 != 0) {
                return j - j2;
            }
        }
        return 0L;
    }

    public long requestHeadCost() {
        long j = this.requestHeadEndTime;
        if (j != 0) {
            long j2 = this.requestHeadStartTime;
            if (j2 != 0) {
                return j - j2;
            }
        }
        return 0L;
    }

    public long requestBodyCost() {
        long j = this.requestBodyEndTime;
        if (j != 0) {
            long j2 = this.requestBodyStartTime;
            if (j2 != 0) {
                return j - j2;
            }
        }
        return 0L;
    }

    public long requestCost() {
        return requestHeadCost() + requestBodyCost();
    }

    public long responseHeadCost() {
        long j = this.responseHeadEndTime;
        if (j != 0) {
            long j2 = this.responseHeadStartTime;
            if (j2 != 0) {
                return j - j2;
            }
        }
        return 0L;
    }

    public long responseBodyCost() {
        long j = this.responseBodyEndTime;
        if (j != 0) {
            long j2 = this.responseBodyStartTime;
            if (j2 != 0) {
                return j - j2;
            }
        }
        return 0L;
    }

    public long responseCost() {
        return responseHeadCost() + responseBodyCost();
    }

    static long now() {
        return SystemClock.elapsedRealtime();
    }
}
