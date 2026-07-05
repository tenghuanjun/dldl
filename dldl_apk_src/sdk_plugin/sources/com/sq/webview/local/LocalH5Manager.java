package com.sq.webview.local;

import android.app.Application;
import android.net.Uri;
import android.text.TextUtils;
import com.sq.webview.net.IRequest;
import com.sq.webview.report.WebErrorReporter;
import com.sq.webview.report.WebEventReporter;
import com.sq.webview.report.WebViewTrackManager;
import com.sq.webview.util.FileUtils;
import com.sq.webview.util.SpUtil;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.util.ZipUtil;
import com.sy37sdk.account.db.LoginTriggerTable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LocalH5Manager {
    private static final String CONFIG_LIST_URL = "http://app-liefer.37.com.cn/app/webview/config/list";
    private static final String TAG = "LocalH5Manager";
    private static LocalH5Manager sInstance;
    private Application mContext;
    private ExecutorService mExecutorService;
    private String mGid;
    private GlobalConfig mGlobalConfig;
    private OkHttpClient mOkHttpClient;
    private String mPid;
    private IRequest mRequestProxy;
    private String mSversion;
    private WebErrorReporter mWebErrorReporter;
    private WebEventReporter mWebEventReporter;
    private String rootPath;
    private final HashMap<String, Boolean> updateCheckResultMap = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    interface ConfigListCallback {
        void onData(GlobalConfig config);
    }

    private LocalH5Manager() {
    }

    public static LocalH5Manager getInstance() {
        if (sInstance == null) {
            sInstance = new LocalH5Manager();
        }
        return sInstance;
    }

    public LocalH5Manager setPid(String pid) {
        this.mPid = pid;
        return this;
    }

    public LocalH5Manager setGid(String gid) {
        this.mGid = gid;
        return this;
    }

    public LocalH5Manager setSversion(String sversion) {
        this.mSversion = sversion;
        return this;
    }

    public LocalH5Manager setRequestProxy(IRequest requestProxy) {
        this.mRequestProxy = requestProxy;
        return this;
    }

    public LocalH5Manager setEventReporter(WebEventReporter eventReporter) {
        this.mWebEventReporter = eventReporter;
        return this;
    }

    public LocalH5Manager setErrorReporter(WebErrorReporter errorReporter) {
        this.mWebErrorReporter = errorReporter;
        return this;
    }

    public void init(Application context) {
        this.mExecutorService = Executors.newFixedThreadPool(10);
        this.mContext = context;
        this.rootPath = context.getCacheDir().getPath();
        this.mOkHttpClient = new OkHttpClient.Builder().connectTimeout(2L, TimeUnit.SECONDS).readTimeout(3L, TimeUnit.SECONDS).writeTimeout(3L, TimeUnit.SECONDS).build();
        WebLogUtil.i("cachePath " + this.rootPath);
        getConfigList(new ConfigListCallback() { // from class: com.sq.webview.local.-$$Lambda$LocalH5Manager$W-RXlYVxVhJY-DaDNZf-J0X8oN4
            @Override // com.sq.webview.local.LocalH5Manager.ConfigListCallback
            public final void onData(GlobalConfig globalConfig) {
                this.f$0.lambda$init$0$LocalH5Manager(globalConfig);
            }
        });
    }

    public /* synthetic */ void lambda$init$0$LocalH5Manager(GlobalConfig config) {
        this.mGlobalConfig = config;
        if (config.getMain_switch()) {
            WebLogUtil.i(TAG, "getConfigList success $it");
            innerCheck(config);
        } else {
            this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_MAIN_SWITCH_CLOSE, null);
        }
    }

    private void innerCheck(GlobalConfig globalConfig) {
        for (ConfigItem configItem : globalConfig.getList()) {
            if (configItem.getSwitch().booleanValue() && configItem.getCache().booleanValue()) {
                if (checkUpdate(configItem)) {
                    trackUpdateRes("init_update", configItem.getPackage_url());
                    updateRes(configItem);
                } else {
                    WebLogUtil.i(TAG, "hash 一致，不需要下载更新");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRes(final ConfigItem item) {
        WebLogUtil.i(TAG, "hash 不一致，需要下载更新");
        final String tempPath = getTempPath(item.getUrl());
        this.mExecutorService.execute(new Runnable() { // from class: com.sq.webview.local.LocalH5Manager.1
            @Override // java.lang.Runnable
            public void run() {
                FileUtils.deleteFile(new File(tempPath));
                File fileDownloadResZip = LocalH5Manager.this.downloadResZip(item.getPackage_url(), item.getUrl());
                JSONArray jSONArrayDownloadManifest = LocalH5Manager.this.downloadManifest(item.getList_url(), item.getUrl());
                if (fileDownloadResZip != null) {
                    LocalH5Manager.this.processVerify(fileDownloadResZip, item, jSONArrayDownloadManifest);
                    return;
                }
                LocalH5Manager.this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_DOWNLOAD_LOCAL_H5_RES_RETRY, null);
                File fileDownloadResZip2 = LocalH5Manager.this.downloadResZip(item.getPackage_url(), item.getUrl());
                if (fileDownloadResZip2 != null) {
                    LocalH5Manager.this.processVerify(fileDownloadResZip2, item, jSONArrayDownloadManifest);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONArray downloadManifest(String url, String businessUrl) {
        Response responseExecute;
        WebLogUtil.i("DownloadMsg", "success");
        trackDownloadManifest(url);
        try {
            try {
                responseExecute = this.mOkHttpClient.newCall(new Request.Builder().url(url).build()).execute();
            } catch (Exception e) {
                trackDownloadManifestFail(url, "未知错误 " + e.getMessage());
            }
        } catch (SocketTimeoutException e2) {
            trackDownloadManifestFail(url, "网络超时 " + e2.getMessage());
        }
        if (responseExecute.isSuccessful()) {
            try {
                Uri uri = Uri.parse(businessUrl);
                String path = uri.getPath();
                String str = getRootPath() + File.separator + uri.getHost() + File.separator + path + "manifest.json";
                saveFile(responseExecute, str);
                String str2 = FileUtils.read(str);
                WebLogUtil.i(TAG, "downloadManifest success");
                JSONArray jSONArray = new JSONArray(str2);
                trackDownloadManifestSuccess(url);
                return jSONArray;
            } catch (Exception e3) {
                trackDownloadManifestFail(url, "download fail, exception is " + e3.getMessage());
                return new JSONArray();
            }
        }
        trackDownloadManifestFail(url, "http code is " + responseExecute.code());
        WebLogUtil.i(TAG, "downloadManifest error");
        return new JSONArray();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File downloadResZip(String url, String businessUrl) {
        Timepiece timepieceStart = Timepiece.start();
        try {
            Response responseExecute = this.mOkHttpClient.newCall(new Request.Builder().url(url).build()).execute();
            if (responseExecute.isSuccessful()) {
                try {
                    Uri uri = Uri.parse(businessUrl);
                    String path = uri.getPath();
                    String host = uri.getHost();
                    String str = this.rootPath + File.separator + "temp" + File.separator + host + path + File.separator + "package.zip";
                    String str2 = getRootPath() + File.separator + host + File.separator + path;
                    saveFile(responseExecute, str);
                    WebLogUtil.i(TAG, "downloadByOkhttp success ");
                    ZipUtil.unzip(str, str2, "package");
                    FileUtils.deleteFile(new File(str));
                    trackDownloadResSuccess(timepieceStart.stop(), url);
                    return new File(str2);
                } catch (SocketTimeoutException e) {
                    trackDownloadResFail(timepieceStart.stop(), url, "网络超时 " + e.getMessage());
                    return null;
                } catch (Exception e2) {
                    trackDownloadResFail(timepieceStart.stop(), url, "download fail, exception is " + e2.getMessage());
                    return null;
                }
            }
            trackDownloadResFail(timepieceStart.stop(), url, "http code is " + responseExecute.code());
            WebLogUtil.e(TAG, "downloadByOkhttp error");
            return null;
        } catch (SocketTimeoutException e3) {
            trackDownloadResFail(timepieceStart.stop(), url, "网络超时 " + e3.getMessage());
            return null;
        } catch (Exception e4) {
            trackDownloadResFail(timepieceStart.stop(), url, "未知错误 " + e4.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processVerify(File file, ConfigItem item, JSONArray json) {
        try {
            SpUtil.putShareString(item.getUrl(), "", this.mContext);
            verify(file, item.getUrl(), json);
            SpUtil.putShareString(item.getUrl(), item.getHash(), this.mContext);
            trackVerifySuccess(item.getUrl());
        } catch (Md5NoMatchException e) {
            trackVerifyFail(item.getUrl(), e.getMsg());
            FileUtils.deleteFile(file);
        } catch (Exception e2) {
            trackVerifyFail(item.getUrl(), e2.getMessage());
            FileUtils.deleteFile(file);
        }
    }

    private void verify(File file, String businessUrl, JSONArray jsonArray) throws Md5NoMatchException {
        Uri uri = Uri.parse(businessUrl);
        String path = uri.getPath();
        String str = getRootPath() + File.separator + uri.getHost() + path;
        WebLogUtil.i(TAG, "start verify files");
        verifyFileRes(file, str, jsonArray);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0065, code lost:
    
        if (r5 != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0067, code lost:
    
        r3 = r3.getPath().replace(r13, "");
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0075, code lost:
    
        if (r3.equals("manifest.json") == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0092, code lost:
    
        throw new com.sq.webview.local.Md5NoMatchException("检查更新列表文件不通过，file = " + r3 + "localMd5 :  " + r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x009c, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void verifyFileRes(java.io.File r12, java.lang.String r13, org.json.JSONArray r14) throws com.sq.webview.local.Md5NoMatchException {
        /*
            r11 = this;
            java.io.File[] r12 = r12.listFiles()
            int r0 = r12.length
            r1 = 0
            r2 = 0
        L7:
            if (r2 >= r0) goto La0
            r3 = r12[r2]
            boolean r4 = r3.isFile()
            if (r4 == 0) goto L93
            java.lang.String r4 = r3.getPath()
            java.lang.String r4 = com.sq.webview.util.Md5Utils.getMD5(r4)
            r5 = 0
        L1a:
            int r6 = r14.length()
            java.lang.String r7 = "localMd5 :  "
            java.lang.String r8 = "检查更新列表文件不通过，file = "
            java.lang.String r9 = ""
            if (r5 >= r6) goto L64
            org.json.JSONObject r6 = r14.optJSONObject(r5)
            java.lang.String r10 = r3.getPath()
            java.lang.String r10 = r10.replace(r13, r9)
            org.json.JSONObject r6 = r6.optJSONObject(r10)
            if (r6 == 0) goto L61
            java.lang.String r5 = "md5"
            java.lang.String r5 = r6.optString(r5)
            boolean r5 = android.text.TextUtils.equals(r4, r5)
            if (r5 == 0) goto L46
            r5 = 1
            goto L65
        L46:
            com.sq.webview.local.Md5NoMatchException r12 = new com.sq.webview.local.Md5NoMatchException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r8)
            r13.append(r10)
            r13.append(r7)
            r13.append(r4)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L61:
            int r5 = r5 + 1
            goto L1a
        L64:
            r5 = 0
        L65:
            if (r5 != 0) goto L9c
            java.lang.String r3 = r3.getPath()
            java.lang.String r3 = r3.replace(r13, r9)
            java.lang.String r5 = "manifest.json"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L78
            goto L9c
        L78:
            com.sq.webview.local.Md5NoMatchException r12 = new com.sq.webview.local.Md5NoMatchException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r8)
            r13.append(r3)
            r13.append(r7)
            r13.append(r4)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L93:
            boolean r4 = r3.isDirectory()
            if (r4 == 0) goto L9c
            r11.verifyFileRes(r3, r13, r14)
        L9c:
            int r2 = r2 + 1
            goto L7
        La0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.webview.local.LocalH5Manager.verifyFileRes(java.io.File, java.lang.String, org.json.JSONArray):void");
    }

    private void saveFile(Response response, String path) throws IOException {
        if (!new File(path).exists()) {
            FileUtils.createFile(path);
        }
        InputStream inputStreamByteStream = response.body().byteStream();
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStreamByteStream.read(bArr);
            if (i != -1) {
                fileOutputStream.write(bArr, 0, i);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStreamByteStream.close();
                return;
            }
        }
    }

    private String getTempPath(String url) {
        Uri uri = Uri.parse(url);
        String path = uri.getPath();
        return this.rootPath + File.separator + "temp" + File.separator + uri.getHost() + path + File.separator + "package.zip";
    }

    public String getRootPath() {
        return this.rootPath + File.separator + "local_h5_res";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkUpdate(ConfigItem remoteConfig) {
        return !TextUtils.equals(remoteConfig.getHash(), SpUtil.getShareString(remoteConfig.getUrl(), this.mContext));
    }

    private void getConfigList(final ConfigListCallback callback) {
        HashMap map = new HashMap();
        map.put("gid", this.mGid);
        map.put("pid", this.mPid);
        map.put("sversion", this.mSversion);
        this.mRequestProxy.getRequest(CONFIG_LIST_URL, map, new IRequest.RequestCallback<JSONObject>() { // from class: com.sq.webview.local.LocalH5Manager.2
            @Override // com.sq.webview.net.IRequest.RequestCallback
            public void onSuccess(JSONObject data) {
                LocalH5Manager.this.trackRequestListSuccess();
                callback.onData(GlobalConfig.parse(data));
            }

            @Override // com.sq.webview.net.IRequest.RequestCallback
            public void onError(int code, String msg) {
                LocalH5Manager.this.trackRequestListFail("httpCode : " + code + " error : " + msg);
            }
        });
    }

    public void getSinglePageConfig(String url, final ConfigCallback callback) {
        this.updateCheckResultMap.clear();
        GlobalConfig globalConfig = this.mGlobalConfig;
        if (globalConfig == null) {
            callback.onConfigGet(false);
            trackLoadPageOnline("getSinglePageConfig 获取全局配置未完成");
            WebLogUtil.e(TAG, "getSinglePageConfig 获取全局配置未完成");
            return;
        }
        if (!globalConfig.getMain_switch()) {
            callback.onConfigGet(false);
            trackLoadPageOnline("getSinglePageConfig main_switch 关闭");
            WebLogUtil.i(TAG, "getSinglePageConfig main_switch 关闭");
            return;
        }
        final String url2 = null;
        for (ConfigItem configItem : this.mGlobalConfig.getList()) {
            if (url.startsWith(configItem.getUrl())) {
                url2 = configItem.getUrl();
            }
        }
        if (TextUtils.isEmpty(url2)) {
            callback.onConfigGet(false);
            trackLoadPageOnline("GetSinglePageConfig fail, not match. Url is $url");
            WebLogUtil.w("GetSinglePageConfig fail, not match. Url is $url");
        } else {
            final Timepiece timepieceStart = Timepiece.start();
            HashMap map = new HashMap();
            map.put("gid", this.mGid);
            map.put("pid", this.mPid);
            map.put("sversion", this.mSversion);
            this.mRequestProxy.getRequest(CONFIG_LIST_URL, map, new IRequest.RequestCallback<JSONObject>() { // from class: com.sq.webview.local.LocalH5Manager.3
                @Override // com.sq.webview.net.IRequest.RequestCallback
                public void onSuccess(JSONObject jsonObject) {
                    LocalH5Manager.this.trackRequestSingleConfigSuc(timepieceStart.stop());
                    LocalH5Manager.this.mGlobalConfig = GlobalConfig.parse(jsonObject);
                    for (ConfigItem configItem2 : LocalH5Manager.this.mGlobalConfig.getList()) {
                        LocalH5Manager.this.updateCheckResultMap.put(configItem2.getUrl(), Boolean.valueOf(LocalH5Manager.this.checkUpdate(configItem2)));
                    }
                    ConfigItem configItemFind = find(LocalH5Manager.this.mGlobalConfig.getList(), url2);
                    if (configItemFind != null) {
                        if (configItemFind.getSwitch().booleanValue()) {
                            if (LocalH5Manager.this.checkUpdate(configItemFind)) {
                                callback.onConfigGet(false);
                                LocalH5Manager.this.trackLoadPageOnline("资源更新，先走线上");
                                LocalH5Manager.this.trackUpdateRes("open_webpage_update", configItemFind.getPackage_url());
                                LocalH5Manager.this.updateRes(configItemFind);
                                return;
                            }
                            callback.onConfigGet(true);
                            WebLogUtil.i(LocalH5Manager.TAG, "hash 一致，不需要下载更新");
                            return;
                        }
                        callback.onConfigGet(false);
                        LocalH5Manager.this.trackLoadPageOnline("业务开关关闭，走线上");
                        return;
                    }
                    callback.onConfigGet(false);
                    LocalH5Manager.this.trackLoadPageOnline("业务开关关闭，走线上");
                }

                @Override // com.sq.webview.net.IRequest.RequestCallback
                public void onError(int code, String msg) {
                    LocalH5Manager.this.trackRequestSingleConfigFail(msg);
                    callback.onConfigGet(false);
                }

                private ConfigItem find(List<ConfigItem> list, String url3) {
                    for (ConfigItem configItem2 : list) {
                        if (TextUtils.equals(configItem2.getUrl(), url3)) {
                            return configItem2;
                        }
                    }
                    return null;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackUpdateRes(String triggerTime, String downloadUrl) {
        HashMap map = new HashMap();
        map.put(LoginTriggerTable.TRIGGER_TIME, triggerTime);
        map.put("download_url", downloadUrl);
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_DOWNLOAD_LOCAL_H5_RES_START, map);
    }

    private void trackDownloadResSuccess(long cost, String downloadUrl) {
        HashMap map = new HashMap();
        map.put("cost", Long.valueOf(cost));
        map.put("download_url", downloadUrl);
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_DOWNLOAD_LOCAL_H5_RES_SUCCESS, map);
    }

    private void trackDownloadResFail(long cost, String downloadUrl, String msg) {
        HashMap map = new HashMap();
        map.put("cost", Long.valueOf(cost));
        map.put("download_url", downloadUrl);
        map.put("fail_reason", msg);
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_DOWNLOAD_LOCAL_H5_RES_FAIL, map);
        this.mWebErrorReporter.report(WebErrorReporter.ExceptionType.WEB_DOWNLOAD_LOCAL_RES_FAIL, map);
    }

    private void trackDownloadManifest(String downloadUrl) {
        HashMap map = new HashMap();
        map.put("download_url", downloadUrl);
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_DOWNLOAD_MANIFEST, map);
    }

    private void trackDownloadManifestSuccess(String downloadUrl) {
        HashMap map = new HashMap();
        map.put("download_url", downloadUrl);
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_DOWNLOAD_MANIFEST_SUCCESS, map);
    }

    private void trackDownloadManifestFail(String downloadUrl, String msg) {
        HashMap map = new HashMap();
        map.put("download_url", downloadUrl);
        map.put("fail_reason", msg);
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_DOWNLOAD_MANIFEST_FAIL, map);
        this.mWebErrorReporter.report(WebErrorReporter.ExceptionType.WEB_DOWNLOAD_MANIFEST_FAIL, map);
    }

    private void trackVerifySuccess(String url) {
        HashMap map = new HashMap();
        map.put("url", url);
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_VERIFY_RES_SUCCESS, map);
    }

    private void trackVerifyFail(String url, String msg) {
        HashMap map = new HashMap();
        map.put("fail_reason", msg);
        map.put("url", url);
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_VERIFY_RES_FAIL, map);
        this.mWebErrorReporter.report(WebErrorReporter.ExceptionType.WEB_VERIFY_RES_FAIL, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackRequestListSuccess() {
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_GET_CONFIG_LIST_SUCCESS, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackRequestListFail(String msg) {
        HashMap map = new HashMap();
        map.put("fail_reason", msg);
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_GET_CONFIG_LIST_FAIL, map);
        this.mWebErrorReporter.report(WebErrorReporter.ExceptionType.WEB_GET_CONFIG_LIST_FAIL, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackRequestSingleConfigSuc(long cost) {
        HashMap map = new HashMap();
        map.put("cost", Long.valueOf(cost));
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_GET_SINGLE_CONFIG_SUCCESS, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackRequestSingleConfigFail(String msg) {
        HashMap map = new HashMap();
        map.put("fail_reason", msg);
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_GET_SINGLE_CONFIG_FAIL, map);
        this.mWebErrorReporter.report(WebErrorReporter.ExceptionType.WEB_GET_SINGLE_CONFIG_FAIL, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackLoadPageOnline(String msg) {
        HashMap map = new HashMap();
        map.put("reason", msg);
        this.mWebEventReporter.report(WebViewTrackManager.SDK_WEB_LOAD_PAGE_ONLINE, map);
    }

    public GlobalConfig getGlobalConfig() {
        return this.mGlobalConfig;
    }

    public HashMap<String, Boolean> getUpdateCheckMap() {
        return this.updateCheckResultMap;
    }
}
