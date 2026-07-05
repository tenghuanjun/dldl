package com.sdk.sq.net;

import com.sq.tools.report.event.IEventReporter;
import com.sqnetwork.voly.ParseError;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.RequestStatus;
import com.sqnetwork.voly.VolleyError;
import com.sqnetwork.voly.VolleyLog;
import com.sqnetwork.voly.toolbox.NetworkStatus;
import com.sqnetwork.voly.toolbox.Util;
import com.sqwan.common.track.SqTrackNetKey;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class EventReporter {
    private static final String EVENT_REQUEST_FINISH = "sdk_request_finish";
    private static final String KEY_ERROR_CODE = "error_code";
    private static final String KEY_MSG = "msg";
    private static final String KEY_MSG_TITLE = "msg_title";
    private final IEventReporter mReporter;

    EventReporter(IEventReporter reporter) {
        this.mReporter = reporter;
    }

    void report(Request<?> request) {
        VolleyError volleyError;
        if (request == null || this.mReporter == null || !ReportStrategy.canReportEvent((ReportStrategy) request.getTag(ReportStrategy.class)) || Util.isInHttpDnsBlacklist(request.getUrl())) {
            return;
        }
        RequestStatus requestStatus = request.getRequestStatus();
        int i = 0;
        while (i < requestStatus.networkStatuses.size()) {
            NetworkStatus networkStatus = requestStatus.networkStatuses.get(i);
            HashMap map = new HashMap();
            addNetworkStatusParams(map, networkStatus);
            int i2 = i + 1;
            map.put(SqTrackNetKey.currentRequestCount, Integer.valueOf(i2));
            map.put("is_last_request", Boolean.valueOf(i == requestStatus.networkStatuses.size() - 1));
            map.put("total_cost", Long.valueOf(requestStatus.totalCost()));
            map.put("request_id", request.getRequestId());
            map.put("parse_cost", Long.valueOf(requestStatus.parseCost()));
            map.put("wait_cost", Long.valueOf(requestStatus.queueCost()));
            if (Util.verifyAsIpAddress(requestStatus.domain())) {
                map.put("domain", "");
            } else {
                map.put("domain", requestStatus.domain());
            }
            if (networkStatus.error == null && requestStatus.response != null && (volleyError = requestStatus.response.error) != null) {
                map.put("msg", volleyError.toString());
                map.put(KEY_MSG_TITLE, volleyError.getClass().getSimpleName());
                if (volleyError instanceof ParseError) {
                    map.put(KEY_ERROR_CODE, Integer.valueOf(RequestErrorCode.ERROR_PARSE));
                } else {
                    map.put(KEY_ERROR_CODE, Integer.valueOf(networkStatus.httpStatus));
                }
            }
            try {
                this.mReporter.report(EVENT_REQUEST_FINISH, map);
            } catch (Exception e) {
                VolleyLog.e("sdk_request_finish事件上报失败", e);
            }
            i = i2;
        }
        try {
            this.mReporter.flush();
        } catch (Exception unused) {
        }
    }

    private void addNetworkStatusParams(Map<String, Object> params, NetworkStatus networkStatus) {
        params.put("uri", networkStatus.path());
        params.put("protocol", networkStatus.scheme());
        params.put("method", networkStatus.method());
        params.put(SqTrackNetKey.code, Integer.valueOf(networkStatus.httpStatus));
        params.put("cost", Long.valueOf(networkStatus.callCost()));
        params.put("dns_cost", Long.valueOf(networkStatus.dnsCost()));
        params.put("ssl_cost", Long.valueOf(networkStatus.sslCost()));
        params.put("tcp_cost", Long.valueOf(networkStatus.connectCost()));
        params.put("write_cost", Long.valueOf(networkStatus.requestCost()));
        params.put("read_cost", Long.valueOf(networkStatus.responseCost()));
        params.put(SqTrackNetKey.dnsOccur, Boolean.valueOf(networkStatus.useLocalDns));
        params.put("server_ip", networkStatus.serverIp());
        params.put("device_active_ip", networkStatus.activeIp);
        params.put(SqTrackNetKey.isSuccess, Boolean.valueOf(networkStatus.isSuccess()));
        params.put("has_json_body", Boolean.valueOf(networkStatus.hasJsonBody()));
        if (networkStatus.isSuccess()) {
            params.put("msg", "请求成功");
            params.put(KEY_ERROR_CODE, 0);
            params.put(KEY_MSG_TITLE, "请求成功");
        } else {
            Exception exc = networkStatus.error;
            params.put("msg", exc == null ? "NullError" : exc.toString());
            params.put(KEY_ERROR_CODE, Integer.valueOf(errorCodeOf(networkStatus)));
            params.put(KEY_MSG_TITLE, exc != null ? exc.getClass().getSimpleName() : "NullError");
        }
    }

    private int errorCodeOf(NetworkStatus networkStatus) {
        if (networkStatus.isSuccess()) {
            return 0;
        }
        Exception exc = networkStatus.error;
        if (exc == null) {
            return networkStatus.httpStatus;
        }
        return RequestErrorCode.of(exc);
    }
}
