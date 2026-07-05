package com.sqnetwork.voly;

import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NetworkDispatcher extends Thread {
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue<Request<?>> mQueue;
    private volatile boolean mQuit;

    public NetworkDispatcher(BlockingQueue<Request<?>> queue, Network network, Cache cache, ResponseDelivery delivery) {
        super("volley-net");
        this.mQuit = false;
        this.mQueue = queue;
        this.mNetwork = network;
        this.mCache = cache;
        this.mDelivery = delivery;
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    private void addTrafficStatsTag(Request<?> request) {
        if (Build.VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(request.getTrafficStatsTag());
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                processRequest();
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    Thread.currentThread().interrupt();
                    return;
                }
                VolleyLog.e("Ignoring spurious interrupt of NetworkDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }

    private void processRequest() throws InterruptedException {
        Request<?> requestTake = this.mQueue.take();
        requestTake.getRequestStatus().dequeue();
        processRequest(requestTake);
    }

    void processRequest(Request<?> request) {
        logRequestStart(request);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        try {
            request.addMarker("network-queue-take");
            if (request.isCanceled()) {
                request.finish("network-discard-cancelled");
                request.notifyListenerResponseNotUsable();
                return;
            }
            addTrafficStatsTag(request);
            NetworkResponse networkResponsePerformRequest = this.mNetwork.performRequest(request);
            request.addMarker("network-http-complete");
            if (networkResponsePerformRequest.notModified && request.hasHadResponseDelivered()) {
                request.finish("not-modified");
                request.notifyListenerResponseNotUsable();
                return;
            }
            try {
                request.getRequestStatus().parseStart();
                Response<?> networkResponse = request.parseNetworkResponse(networkResponsePerformRequest);
                request.getRequestStatus().parseEnd();
                networkResponse.setNetworkResponse(networkResponsePerformRequest);
                request.addMarker("network-parse-complete");
                if (request.getRequestInterceptors() != null) {
                    for (RequestInterceptor requestInterceptor : request.getRequestInterceptors()) {
                        if (requestInterceptor != null) {
                            networkResponse = requestInterceptor.interceptResponse(request, networkResponsePerformRequest, networkResponse);
                        }
                    }
                }
                if (request.shouldCache() && networkResponse.cacheEntry != null) {
                    this.mCache.put(request.getCacheKey(), networkResponse.cacheEntry);
                    request.addMarker("network-cache-written");
                }
                request.markDelivered();
                logRequestSuccess(request, networkResponsePerformRequest);
                this.mDelivery.postResponse(request, networkResponse);
                request.notifyListenerResponseReceived(networkResponse);
            } catch (Throwable th) {
                request.getRequestStatus().parseEnd();
                throw th;
            }
        } catch (VolleyError e) {
            e.setNetworkTimeMs(SystemClock.elapsedRealtime() - jElapsedRealtime);
            parseAndDeliverNetworkError(request, e);
            request.notifyListenerResponseNotUsable();
        } catch (Exception e2) {
            VolleyLog.e(e2, "Unhandled exception %s", e2.toString());
            VolleyError volleyError = new VolleyError(e2);
            volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - jElapsedRealtime);
            logRequestError(request, volleyError);
            callErrorInterceptor(request, volleyError);
            this.mDelivery.postError(request, volleyError);
            request.notifyListenerResponseNotUsable();
        }
    }

    private void callErrorInterceptor(Request<?> request, VolleyError error) {
        if (request.getRequestInterceptors() != null) {
            for (RequestInterceptor requestInterceptor : request.getRequestInterceptors()) {
                if (requestInterceptor != null) {
                    try {
                        requestInterceptor.interceptError(request, error);
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    private void logRequestStart(Request<?> request) {
        Map<String, String> mapEmptyMap;
        String str;
        if (VolleyLog.DEBUG) {
            StringBuilder sb = new StringBuilder();
            try {
                mapEmptyMap = request.getHeaders();
            } catch (AuthFailureError unused) {
                mapEmptyMap = Collections.emptyMap();
            }
            if (mapEmptyMap != null && !mapEmptyMap.isEmpty()) {
                for (String str2 : mapEmptyMap.keySet()) {
                    sb.append(str2);
                    sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                    sb.append(mapEmptyMap.get(str2));
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            try {
                str = new String(request.getBody());
            } catch (Exception unused2) {
                str = "";
            }
            if (sb.length() == 0 && str.isEmpty()) {
                VolleyLog.d("%s: %s (%s) start", request.getMethodStr().toUpperCase(), request.getUrl(), request.getRequestId());
            } else if (sb.length() == 0) {
                VolleyLog.d("%s: %s (%s) start\n\tBody  : %s", request.getMethodStr().toUpperCase(), request.getUrl(), request.getRequestId(), str);
            } else {
                VolleyLog.d("%s: %s (%s) start\n\tHeader: %s\n\tBody  : %s", request.getMethodStr().toUpperCase(), request.getUrl(), request.getRequestId(), sb.toString(), str);
            }
        }
    }

    private void logRequestSuccess(Request<?> request, NetworkResponse networkResponse) {
        if (VolleyLog.DEBUG) {
            String str = "";
            StringBuilder sb = new StringBuilder();
            Map<String, String> map = networkResponse.headers;
            if (map != null && !map.isEmpty()) {
                for (String str2 : map.keySet()) {
                    sb.append(str2);
                    sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                    sb.append(map.get(str2));
                    sb.append(",");
                    if ("Content-Type".equalsIgnoreCase(str2)) {
                        str = map.get(str2);
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            String printBody = getPrintBody(networkResponse, str);
            if (sb.length() == 0 && printBody.isEmpty()) {
                VolleyLog.i("%s (%s) success(%d)", request.getUrl(), request.getRequestId(), Integer.valueOf(networkResponse.statusCode));
            } else if (sb.length() == 0) {
                VolleyLog.i("%s (%s) success(%d)\n\tBody  : %s", request.getUrl(), request.getRequestId(), Integer.valueOf(networkResponse.statusCode), printBody);
            } else {
                VolleyLog.i("%s (%s) success(%d)\n\tHeader: %s\n\tBody  : %s", request.getUrl(), request.getRequestId(), Integer.valueOf(networkResponse.statusCode), map, printBody);
            }
        }
    }

    private static String getPrintBody(NetworkResponse networkResponse, String contentType) {
        if (networkResponse.data == null) {
            return "";
        }
        if ((contentType == null || contentType.contains("image") || contentType.contains("vnd") || contentType.contains("zip") || contentType.contains("audio") || contentType.contains("video")) ? false : true) {
            try {
                return new String(networkResponse.data);
            } catch (Exception unused) {
            }
        }
        return SimpleComparison.LESS_THAN_OPERATION + contentType + ">(" + networkResponse.data.length + "Bytes)";
    }

    private void logRequestError(Request<?> request, VolleyError error) {
        if (VolleyLog.DEBUG) {
            VolleyLog.e("%s (%s) error\n\t%s", request.getUrl(), request.getRequestId(), error.toString());
        }
    }

    private void parseAndDeliverNetworkError(Request<?> request, VolleyError error) {
        VolleyError networkError = request.parseNetworkError(error);
        logRequestError(request, networkError);
        callErrorInterceptor(request, networkError);
        this.mDelivery.postError(request, networkError);
    }
}
