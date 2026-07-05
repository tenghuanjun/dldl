package com.sq.webview.report;

import com.sq.tools.report.exception.IExceptionReporter;
import com.sq.webview.util.WebLogUtil;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebErrorReporter {
    private final IExceptionReporter mReporter;

    public WebErrorReporter(IExceptionReporter reporter) {
        this.mReporter = reporter;
    }

    public void report(ExceptionType type, Map<String, Object> params) {
        report(new Exception(type.desc), type, params);
    }

    public void report(Exception exception, ExceptionType type, Map<String, Object> params) {
        if (this.mReporter == null) {
            return;
        }
        try {
            this.mReporter.reportException(exception, type.code, type.desc, params != null ? new JSONObject(params).toString() : "");
        } catch (Exception e) {
            WebLogUtil.e(type + "异常上报失败", e);
        }
    }

    public enum ExceptionType {
        WEB_LOAD_FAIL(351, "网页加载失败"),
        WEB_LOAD_WHITE_SCREEN_ERROR(352, "网页加载完成但是页面是白屏"),
        WEB_LOAD_SSL_ERROR(353, "网页加载出现网站证书错误"),
        WEB_LOAD_HTTP_CODE_ERROR(354, "网页加载出现http状态码错误"),
        WEB_DNS_CLOSED_ERROR(357, "网页DNS被关闭"),
        WEB_DNS_UNSUPPORTED_ERROR(358, "网页DNS拦截失败"),
        WEB_DNS_ORIGIN_ERROR(359, "网页DNS降级到原域"),
        WEB_VERIFY_RES_FAIL(360, "离线资源校验失败"),
        WEB_GET_CONFIG_LIST_FAIL(361, "请求配置列表失败"),
        WEB_GET_SINGLE_CONFIG_FAIL(362, "获取单个配置失败"),
        WEB_DOWNLOAD_LOCAL_RES_FAIL(364, "下载离线包资源失败"),
        WEB_DOWNLOAD_MANIFEST_FAIL(365, "下载清单文件资源失败");

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
