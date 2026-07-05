package com.sqnetwork.voly;

import android.os.SystemClock;
import com.sqnetwork.voly.toolbox.NetworkStatus;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RequestStatus {
    public final long createTime;
    public long dequeueTime;
    public long enqueueTime;
    public long finishTime;
    public final List<NetworkStatus> networkStatuses = new ArrayList();
    public long parseEndTime;
    public long parseStartTime;
    public final Request<?> request;
    public Response<?> response;
    public final HttpUrl url;

    RequestStatus(Request<?> request) {
        this.request = request;
        if (request.getUrl() == null) {
            this.url = null;
        } else {
            this.url = HttpUrl.parse(request.getUrl());
        }
        this.createTime = now();
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
        HttpUrl httpUrl = this.url;
        return httpUrl == null ? "" : httpUrl.host();
    }

    public String method() {
        return this.request.getMethodStr();
    }

    public int httpStatus() {
        Response<?> response = this.response;
        if (response == null || response.getNetworkResponse() == null) {
            return -1;
        }
        return this.response.getNetworkResponse().statusCode;
    }

    public boolean isSuccess() {
        Response<?> response = this.response;
        return response != null && response.isSuccess();
    }

    public void enqueue() {
        this.enqueueTime = now();
    }

    public void dequeue() {
        this.dequeueTime = now();
    }

    public void finish() {
        this.finishTime = now();
    }

    public void parseStart() {
        this.parseStartTime = now();
    }

    public void parseEnd() {
        this.parseEndTime = now();
    }

    public long queueCost() {
        long j;
        long jNow = this.dequeueTime;
        if (jNow != 0) {
            j = this.createTime;
        } else {
            jNow = now();
            j = this.createTime;
        }
        return jNow - j;
    }

    public long parseCost() {
        long j = this.parseEndTime;
        if (j != 0) {
            long j2 = this.parseStartTime;
            if (j2 != 0) {
                return j - j2;
            }
        }
        if (this.parseStartTime != 0) {
            return now() - this.parseStartTime;
        }
        return 0L;
    }

    public long totalCost() {
        long j;
        long jNow = this.finishTime;
        if (jNow != 0) {
            j = this.createTime;
        } else {
            jNow = now();
            j = this.createTime;
        }
        return jNow - j;
    }

    public NetworkStatus currentCallStatus() {
        if (this.networkStatuses.isEmpty()) {
            return null;
        }
        return this.networkStatuses.get(r0.size() - 1);
    }

    static long now() {
        return SystemClock.elapsedRealtime();
    }
}
