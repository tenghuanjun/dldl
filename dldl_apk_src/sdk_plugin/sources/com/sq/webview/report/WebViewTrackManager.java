package com.sq.webview.report;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.webkit.WebView;
import com.sq.webview.report.WebErrorReporter;
import com.sq.webview.util.WebUtils;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WebViewTrackManager {
    private static final String CONTRACT_ERROR_CAUSE = "errorCause";
    private static final String CONTRACT_ERROR_CODE = "errorCode";
    private static final String CONTRACT_LOAD_CONSUMING = "loadConsuming";
    private static final String CONTRACT_PAGE_WHITE_SCREEN_RATE = "whiteScreenRate";
    private static final String CONTRACT_WEB_HOST = "webHost";
    private static final String CONTRACT_WEB_PATH = "webPath";
    private static final String CONTRACT_WEB_TITLE = "webTitle";
    private static final String CONTRACT_WEB_URL = "webUrl";
    public static final String SDK_WEB_BUILD_RESP_FAIL_LIST = "sdk_web_build_resp_fail_list";
    public static final String SDK_WEB_DOWNLOAD_LOCAL_H5_RES_FAIL = "sdk_web_download_local_h5_res_fail";
    public static final String SDK_WEB_DOWNLOAD_LOCAL_H5_RES_RETRY = "sdk_web_download_local_h5_res_retry";
    public static final String SDK_WEB_DOWNLOAD_LOCAL_H5_RES_START = "sdk_web_download_local_h5_res_start";
    public static final String SDK_WEB_DOWNLOAD_LOCAL_H5_RES_SUCCESS = "sdk_web_download_local_h5_res_success";
    public static final String SDK_WEB_DOWNLOAD_MANIFEST = "sdk_web_download_manifest";
    public static final String SDK_WEB_DOWNLOAD_MANIFEST_FAIL = "sdk_web_download_manifest_fail";
    public static final String SDK_WEB_DOWNLOAD_MANIFEST_SUCCESS = "sdk_web_download_manifest_success";
    public static final String SDK_WEB_GET_CONFIG_LIST_FAIL = "sdk_web_get_config_list_fail";
    public static final String SDK_WEB_GET_CONFIG_LIST_SUCCESS = "sdk_web_get_config_list_success";
    public static final String SDK_WEB_GET_SINGLE_CONFIG_FAIL = "sdk_web_get_single_config_fail";
    public static final String SDK_WEB_GET_SINGLE_CONFIG_SUCCESS = "sdk_web_get_single_config_success";
    private static final String SDK_WEB_HTTP_CODE_ERROR = "sdk_web_http_code_error";
    private static final String SDK_WEB_LOAD_FAIL = "sdk_web_load_fail";
    private static final String SDK_WEB_LOAD_FINISH = "sdk_web_load_finish";
    public static final String SDK_WEB_LOAD_PAGE_ONLINE = "sdk_web_load_page_online";
    private static final String SDK_WEB_LOAD_WHITE_SCREEN_ERROR = "sdk_web_load_white_screen_error";
    public static final String SDK_WEB_MAIN_SWITCH_CLOSE = "sdk_web_main_switch_close";
    public static final String SDK_WEB_NO_FETCH_LIST = "sdk_web_no_fetch_list";
    private static final String SDK_WEB_SSL_ERROR = "sdk_web_ssl_error";
    public static final String SDK_WEB_VERIFY_RES_FAIL = "sdk_web_verify_res_fail";
    public static final String SDK_WEB_VERIFY_RES_SUCCESS = "sdk_web_verify_res_success";
    private Executor mExecutor;
    private final WebErrorReporter mWebErrorReporter;
    private final WebEventReporter mWebEventReporter;

    public WebViewTrackManager(WebErrorReporter webErrorReporter, WebEventReporter webEventReporter) {
        this.mWebErrorReporter = webErrorReporter;
        this.mWebEventReporter = webEventReporter;
    }

    public void setExecutor(Executor executor) {
        this.mExecutor = executor;
    }

    public void trackWebLoadFinish(WebView webView, String url, long loadConsuming) {
        HashMap<String, Object> map = new HashMap<>();
        addCommonParams(webView, url, map);
        map.put(CONTRACT_LOAD_CONSUMING, String.valueOf(loadConsuming));
        WebEventReporter webEventReporter = this.mWebEventReporter;
        if (webEventReporter == null) {
            return;
        }
        webEventReporter.report(SDK_WEB_LOAD_FINISH, map);
    }

    public void trackWebLoadFail(WebView webView, String url, int errorCode, String description) {
        HashMap<String, Object> map = new HashMap<>();
        addCommonParams(webView, url, map);
        map.put(CONTRACT_ERROR_CODE, String.valueOf(errorCode));
        map.put(CONTRACT_ERROR_CAUSE, String.valueOf(description));
        WebEventReporter webEventReporter = this.mWebEventReporter;
        if (webEventReporter != null) {
            webEventReporter.report(SDK_WEB_LOAD_FAIL, map);
        }
        WebErrorReporter webErrorReporter = this.mWebErrorReporter;
        if (webErrorReporter != null) {
            webErrorReporter.report(WebErrorReporter.ExceptionType.WEB_LOAD_FAIL, map);
        }
    }

    public void trackWebLoadWhiteScreenError(final WebView webView, String url) {
        final HashMap<String, Object> map = new HashMap<>();
        addCommonParams(webView, url, map);
        webView.postDelayed(new Runnable() { // from class: com.sq.webview.report.-$$Lambda$WebViewTrackManager$K9EUDFEQjTtXwe9TvxxYyj81T38
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$trackWebLoadWhiteScreenError$1$WebViewTrackManager(webView, map);
            }
        }, 100L);
    }

    public /* synthetic */ void lambda$trackWebLoadWhiteScreenError$1$WebViewTrackManager(final WebView webView, final HashMap map) {
        try {
            final Bitmap bitmapCreateBitmap = Bitmap.createBitmap(webView.getWidth(), webView.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.translate(-webView.getScrollX(), -webView.getScrollY());
            webView.draw(canvas);
            this.mExecutor.execute(new Runnable() { // from class: com.sq.webview.report.-$$Lambda$WebViewTrackManager$W9SLKBa3D_B0BO6rhuo5YW5PRHk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$null$0$WebViewTrackManager(bitmapCreateBitmap, map);
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public /* synthetic */ void lambda$null$0$WebViewTrackManager(final Bitmap bitmap, final HashMap map) {
        boolean zIsRecycled;
        Bitmap bitmapCreateScaledBitmap = null;
        try {
            bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 10, bitmap.getHeight() / 10, true);
            float bitmapWhiteScreenRate = WebUtils.getBitmapWhiteScreenRate(bitmapCreateScaledBitmap);
            if (bitmapWhiteScreenRate < 0.95f) {
                if (bitmapCreateScaledBitmap != null) {
                    if (zIsRecycled) {
                        return;
                    } else {
                        return;
                    }
                }
                return;
            }
            map.put(CONTRACT_PAGE_WHITE_SCREEN_RATE, String.valueOf(bitmapWhiteScreenRate));
            if (this.mWebEventReporter != null) {
                this.mWebEventReporter.report(SDK_WEB_LOAD_WHITE_SCREEN_ERROR, map);
            }
            if (this.mWebErrorReporter != null) {
                this.mWebErrorReporter.report(WebErrorReporter.ExceptionType.WEB_LOAD_WHITE_SCREEN_ERROR, map);
            }
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            if (bitmapCreateScaledBitmap == null || bitmapCreateScaledBitmap.isRecycled()) {
                return;
            }
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
                if (bitmapCreateScaledBitmap == null || bitmapCreateScaledBitmap.isRecycled()) {
                    return;
                }
            } finally {
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                }
                if (bitmapCreateScaledBitmap != null && !bitmapCreateScaledBitmap.isRecycled()) {
                    bitmapCreateScaledBitmap.recycle();
                }
            }
        }
        bitmapCreateScaledBitmap.recycle();
    }

    public void trackWebLoadSslError(WebView webView, String url, int errorCode) {
        HashMap<String, Object> map = new HashMap<>();
        addCommonParams(webView, url, map);
        map.put(CONTRACT_ERROR_CODE, String.valueOf(errorCode));
        WebEventReporter webEventReporter = this.mWebEventReporter;
        if (webEventReporter != null) {
            webEventReporter.report(SDK_WEB_SSL_ERROR, map);
        }
        WebErrorReporter webErrorReporter = this.mWebErrorReporter;
        if (webErrorReporter != null) {
            webErrorReporter.report(WebErrorReporter.ExceptionType.WEB_LOAD_SSL_ERROR, map);
        }
    }

    public void trackWebHttpCodeError(WebView webView, String url, int errorCode) {
        if (url.toLowerCase().endsWith("favicon.ico")) {
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        addCommonParams(webView, url, map);
        map.put(CONTRACT_ERROR_CODE, String.valueOf(errorCode));
        WebEventReporter webEventReporter = this.mWebEventReporter;
        if (webEventReporter != null) {
            webEventReporter.report(SDK_WEB_HTTP_CODE_ERROR, map);
        }
        WebErrorReporter webErrorReporter = this.mWebErrorReporter;
        if (webErrorReporter != null) {
            webErrorReporter.report(WebErrorReporter.ExceptionType.WEB_LOAD_HTTP_CODE_ERROR, map);
        }
    }

    private void addCommonParams(WebView webView, String url, HashMap<String, Object> map) {
        map.put(CONTRACT_WEB_URL, url);
        map.put(CONTRACT_WEB_TITLE, webView.getTitle());
        if (url == null) {
            return;
        }
        Uri uri = Uri.parse(url);
        map.put(CONTRACT_WEB_HOST, uri.getHost());
        map.put(CONTRACT_WEB_PATH, uri.getPath());
    }
}
