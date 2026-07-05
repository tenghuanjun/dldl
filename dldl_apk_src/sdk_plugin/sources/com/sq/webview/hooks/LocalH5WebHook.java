package com.sq.webview.hooks;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.sq.webview.SimpleWebHook;
import com.sq.webview.local.ConfigItem;
import com.sq.webview.local.GlobalConfig;
import com.sq.webview.local.LocalH5Manager;
import com.sq.webview.report.WebErrorReporter;
import com.sq.webview.report.WebEventReporter;
import com.sq.webview.report.WebViewTrackManager;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LocalH5WebHook extends SimpleWebHook {
    private final Map<String, String> mBuildFailRespMap = new HashMap();
    private boolean mPageFinish = false;
    private final WebErrorReporter mWebErrorReporter;
    private final WebEventReporter mWebEventReporter;

    public LocalH5WebHook(WebEventReporter webEventReporter, WebErrorReporter webErrorReporter) {
        this.mWebErrorReporter = webErrorReporter;
        this.mWebEventReporter = webEventReporter;
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageStarted(WebView webView, String url, Bitmap favicon) {
        super.onPageStarted(webView, url, favicon);
        this.mPageFinish = false;
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
        this.mPageFinish = true;
        if (this.mBuildFailRespMap.isEmpty()) {
            return;
        }
        HashMap map = new HashMap();
        map.put("build_fail_data", buildFailRespData());
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_BUILD_RESP_FAIL_LIST, map);
    }

    @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        WebResourceResponse webResourceResponseFetchResource = fetchResource(request);
        return webResourceResponseFetchResource != null ? webResourceResponseFetchResource : super.shouldInterceptRequest(view, request);
    }

    private WebResourceResponse fetchResource(WebResourceRequest webResourceRequest) {
        ConfigItem next;
        boolean zBooleanValue;
        Boolean bool;
        String string = webResourceRequest.getUrl().toString();
        Uri uri = Uri.parse(string);
        GlobalConfig globalConfig = LocalH5Manager.getInstance().getGlobalConfig();
        if (globalConfig == null) {
            return null;
        }
        List<ConfigItem> list = globalConfig.getList();
        HashMap<String, Boolean> updateCheckMap = LocalH5Manager.getInstance().getUpdateCheckMap();
        Iterator<ConfigItem> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                zBooleanValue = false;
                break;
            }
            next = it.next();
            if (string.startsWith(next.getUrl())) {
                zBooleanValue = next.getSwitch().booleanValue();
                break;
            }
        }
        if (!((next == null || (bool = updateCheckMap.get(next.getUrl())) == null || !bool.booleanValue()) ? zBooleanValue : false)) {
            return null;
        }
        File file = new File(LocalH5Manager.getInstance().getRootPath() + File.separator + uri.getHost() + uri.getPath());
        if (file.exists()) {
            try {
                return new WebResourceResponse(getMimeType(file.getAbsolutePath()), "UTF-8", new FileInputStream(file));
            } catch (Exception e) {
                this.mBuildFailRespMap.put(string, e.getMessage());
            }
        }
        return null;
    }

    private String getMimeType(String filePath) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(filePath));
    }

    private String buildFailRespData() {
        JSONArray jSONArray = new JSONArray();
        try {
            for (Map.Entry<String, String> entry : this.mBuildFailRespMap.entrySet()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(entry.getKey(), entry.getValue());
                jSONArray.put(jSONObject);
            }
            this.mBuildFailRespMap.clear();
            return jSONArray.toString();
        } catch (JSONException e) {
            return e.getMessage();
        }
    }
}
