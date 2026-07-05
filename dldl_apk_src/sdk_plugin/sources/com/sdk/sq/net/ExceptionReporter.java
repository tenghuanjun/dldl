package com.sdk.sq.net;

import com.sq.tools.report.exception.IExceptionReporter;
import com.sqnetwork.voly.HostError;
import com.sqnetwork.voly.NetworkResponse;
import com.sqnetwork.voly.ParseError;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.TimeoutError;
import com.sqnetwork.voly.VolleyError;
import com.sqnetwork.voly.VolleyLog;
import com.sqnetwork.voly.toolbox.HttpHeaderParser;
import com.sqnetwork.voly.toolbox.Util;
import java.io.UnsupportedEncodingException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class ExceptionReporter {
    final IExceptionReporter mReporter;

    ExceptionReporter(IExceptionReporter reporter) {
        this.mReporter = reporter;
    }

    void report(Request<?> request, VolleyError error) {
        if (this.mReporter == null || !ReportStrategy.canReportException((ReportStrategy) request.getTag(ReportStrategy.class)) || Util.isInHttpDnsBlacklist(request.getUrl())) {
            return;
        }
        String strBuildData = buildData(request, error);
        ExceptionType type = getType(error);
        try {
            this.mReporter.reportException(error, type.code, type.desc, strBuildData);
        } catch (Exception e) {
            VolleyLog.e(type + "异常上报失败", e);
        }
    }

    private String buildData(Request<?> request, VolleyError error) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("hcode", RequestErrorCode.of(error));
            jSONObject.put("body", parseNetworkResponse(error.networkResponse));
            jSONObject.put("ourl", request.getUrl());
            jSONObject.put("rurl", request.getRealUrl());
            return jSONObject.toString();
        } catch (Exception e) {
            return "build data error " + e;
        }
    }

    private String parseNetworkResponse(NetworkResponse response) {
        if (response == null) {
            return null;
        }
        try {
            return new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException unused) {
            return new String(response.data);
        }
    }

    private ExceptionType getType(VolleyError error) {
        if (error instanceof TimeoutError) {
            return ExceptionType.HTTP_TIME_OUT;
        }
        if (error instanceof HostError) {
            return ExceptionType.HTTP_UNKNOWN_HOST;
        }
        if (error instanceof ParseError) {
            return ExceptionType.HTTP_PARSE_ERROR;
        }
        if (error instanceof SqVerifyError) {
            return ExceptionType.HTTP_VERIFICATION;
        }
        return ExceptionType.HTTP_ERROR;
    }

    public enum ExceptionType {
        HTTP_PARSE_ERROR(14, "网络请求解析失败"),
        HTTP_ERROR(15, "网络请求失败"),
        HTTP_TIME_OUT(16, "网络请求超时"),
        HTTP_VERIFICATION(17, "请求响应校验失败"),
        HTTP_UNKNOWN_HOST(18, "域名解析失败");

        public final int code;
        public final String desc;

        ExceptionType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.desc + "(" + this.code + ")";
        }
    }
}
