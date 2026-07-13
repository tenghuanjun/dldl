package com.volcengine.c;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.downloader.core.DownloadConfig;
import com.bytedance.downloader.core.DownloadDispatcher;
import com.bytedance.downloader.core.DownloadRequest;
import com.bytedance.downloader.core.DownloadResponse;
import com.bytedance.downloader.core.IDownloadCallback;
import com.lzy.okgo.model.Priority;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.SDKContext;
import com.volcengine.common.contant.CommonConstants;
import com.volcengine.common.contant.CommonErrorCode;
import com.volcengine.common.innerapi.ConfigService;
import com.volcengine.common.innerapi.MonitorService;
import com.volcengine.common.innerapi.PluginService;
import com.volcengine.common.util.CompatConsumer;
import com.volcengine.j.d;
import com.volcengine.j.e;
import com.volcengine.j.m;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a implements IDownloadCallback, com.volcengine.a.a, ConfigService.ConfigObserver {
    private static final boolean i = "vivo".equalsIgnoreCase(Build.BRAND);
    private static final String[] j = {"vegame.volccdn.com"};
    private static final String[] k = {"120.240.117.132", "183.56.135.202", "14.22.21.139", "59.36.213.86", "183.2.170.86", "36.156.119.191", "183.131.160.86", "42.81.156.223"};
    private static final String[] l = {"file-channel.tos-cn-beijing.volces.com"};
    private static final String[] m = {"221.194.187.74", "221.194.187.75", "36.110.132.17", "36.110.132.15", "111.62.105.135", "111.62.105.136"};
    private volatile DownloadDispatcher b;
    private final Map<String, Integer> d = new HashMap();
    private final AtomicReference<CompatConsumer<DownloadResponse>> e = new AtomicReference<>();
    private final AtomicReference<e<DownloadResponse, Integer, String>> f = new AtomicReference<>();
    private final AtomicReference<d<DownloadResponse, Integer>> g = new AtomicReference<>();
    private final AtomicReference<d<DownloadResponse, String>> h = new AtomicReference<>();
    private final com.volcengine.a.b a = new com.volcengine.a.b(SDKContext.getContext(), SDKContext.getExecutorsService().getIOExecutor(), this);
    private final List<String> c = Arrays.asList(j);

    /* JADX INFO: renamed from: com.volcengine.c.a$a, reason: collision with other inner class name */
    class C0201a extends TimerTask {
        final /* synthetic */ DownloadRequest a;

        C0201a(DownloadRequest downloadRequest) {
            this.a = downloadRequest;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            a.this.b(this.a);
        }
    }

    public a() {
        SDKContext.getConfigService().register(ConfigService.download_config, this);
    }

    private void a(final DownloadResponse downloadResponse) {
        AcLog.v(PluginService.TAG_PLUGIN, "onDownloadCompleted: " + downloadResponse.getFileName());
        final CompatConsumer<DownloadResponse> compatConsumer = this.e.get();
        if (compatConsumer != null) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                compatConsumer.accept(downloadResponse);
            } else {
                SDKContext.getExecutorsService().executeMain(new Runnable() { // from class: com.volcengine.c.a$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        compatConsumer.accept(downloadResponse);
                    }
                });
            }
        }
    }

    private void a(final DownloadResponse downloadResponse, final int i2) {
        AcLog.v(PluginService.TAG_PLUGIN, "onDownloadProgress: " + i2);
        final d<DownloadResponse, Integer> dVar = this.g.get();
        if (dVar != null) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                dVar.a(downloadResponse, Integer.valueOf(i2));
            } else {
                SDKContext.getExecutorsService().executeMain(new Runnable() { // from class: com.volcengine.c.a$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        a.a(dVar, downloadResponse, i2);
                    }
                });
            }
        }
    }

    private void a(final DownloadResponse downloadResponse, final int i2, final String str) {
        AcLog.e(PluginService.TAG_PLUGIN, "onDownloadFailed: " + downloadResponse + ", " + ("errorCode: " + i2 + " errorMsg: " + str));
        final e<DownloadResponse, Integer, String> eVar = this.f.get();
        if (eVar != null) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                eVar.a(downloadResponse, Integer.valueOf(i2), str);
            } else {
                SDKContext.getExecutorsService().executeMain(new Runnable() { // from class: com.volcengine.c.a$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        a.a(eVar, downloadResponse, i2, str);
                    }
                });
            }
        }
    }

    private void a(final DownloadResponse downloadResponse, final String str) {
        final d<DownloadResponse, String> dVar = this.h.get();
        if (dVar != null) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                dVar.a(downloadResponse, str);
            } else {
                SDKContext.getExecutorsService().executeMain(new Runnable() { // from class: com.volcengine.c.a$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        dVar.a(downloadResponse, str);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(d dVar, DownloadResponse downloadResponse, int i2) {
        dVar.a(downloadResponse, Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(e eVar, DownloadResponse downloadResponse, int i2, String str) {
        eVar.a(downloadResponse, Integer.valueOf(i2), str);
    }

    private boolean a(String str) {
        try {
            for (String str2 : this.c) {
                if (str != null && str.contains(str2)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            AcLog.e("DownloadService", e.getMessage());
            return false;
        }
    }

    private boolean a(JSONObject jSONObject) {
        return jSONObject.optBoolean("ignore_certification_verify", false) || Build.VERSION.SDK_INT <= 22;
    }

    private synchronized DownloadDispatcher b() {
        JSONArray jSONArrayOptJSONArray;
        if (this.b == null) {
            DownloadConfig.Builder builder = new DownloadConfig.Builder();
            JSONObject configJson = SDKContext.getConfigService().getConfigJson(ConfigService.download_config);
            try {
                if (configJson.has("ip_map")) {
                    JSONObject jSONObjectOptJSONObject = configJson.optJSONObject("ip_map");
                    if (jSONObjectOptJSONObject != null) {
                        Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            if (!TextUtils.isEmpty(next)) {
                                String strOptString = jSONObjectOptJSONObject.optString(next);
                                if (!TextUtils.isEmpty(strOptString)) {
                                    for (String str : strOptString.split(";")) {
                                        builder.addDnsResolver(next, str);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (String str2 : j) {
                        for (String str3 : k) {
                            builder.addDnsResolver(str2, str3);
                        }
                    }
                    for (String str4 : l) {
                        for (String str5 : m) {
                            builder.addDnsResolver(str4, str5);
                        }
                    }
                }
                if (configJson.has("plugin_host_list") && (jSONArrayOptJSONArray = configJson.optJSONArray("plugin_host_list")) != null) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        this.c.add(jSONArrayOptJSONArray.get(i2).toString());
                    }
                }
            } catch (Exception e) {
                AcLog.e("DownloadService", e.getMessage());
            }
            this.b = new DownloadDispatcher(builder.maxTask(configJson.optInt("max_task", 3)).maxChunk(configJson.optInt("max_chunk", Runtime.getRuntime().availableProcessors())).bandwidthLimit(configJson.optInt("bandwidth_limit", 0)).retryCount(configJson.optInt("retry_count", 5)).retryInterval(configJson.optInt("retry_interval", Priority.UI_NORMAL)).retryMode(configJson.optInt("retry_mode", 0)).cacheExpiredTime(configJson.optInt("cache_expired_time", 900000)).connectTimeout(configJson.optInt("connect_timeout", 10000)).readTimeout(configJson.optInt("read_timeout", 15000)).dnsSelectStrategy(configJson.optInt("dns_select_strategy", 0)).forceFlushWhenWriteFile(configJson.optBoolean("force_flush", false)).disappearWhenTaskIsDone(true).overwriteExistTask(true).supportBreakpointResume(true).supportFileVerification(true).useFileStreamWriteFile(configJson.optBoolean("use_file_stream", i)).logger(false).ignoreCertificateVerify(a(configJson)).executor(SDKContext.getExecutorsService().getIOExecutor()).cacheDirectory(SDKContext.getContext().getExternalCacheDir()).build(), this);
        }
        return this.b;
    }

    private void b(DownloadResponse downloadResponse) {
        MonitorService monitorService;
        String str;
        try {
            HashMap map = new HashMap();
            map.put(CommonConstants.KEY_PLUGIN_FILE_NAME, downloadResponse.getFileName());
            for (Object obj : downloadResponse.getExtras().keySet()) {
                if (obj instanceof String) {
                    map.put((String) obj, downloadResponse.getExtras().get(obj));
                }
            }
            if (a(downloadResponse.getUrl())) {
                monitorService = SDKContext.getMonitorService();
                str = CommonConstants.event_downloadPluginPrepared;
            } else {
                monitorService = SDKContext.getMonitorService();
                str = CommonConstants.event_downloadFilePrepared;
            }
            monitorService.reportCategory(str, map);
        } catch (Exception e) {
            AcLog.e("DownloadService", e.getMessage());
        }
    }

    private void b(DownloadResponse downloadResponse, int i2, String str) {
        MonitorService monitorService;
        String str2;
        try {
            com.volcengine.i.b bVar = new com.volcengine.i.b();
            Pair<Integer, String> pair = CommonErrorCode.ERROR_DOWNLOAD_PLUGIN_FAILED;
            com.volcengine.i.b bVarA = bVar.a(((Integer) pair.first).intValue()).a((String) pair.second).b(i2).c(str).b(CommonConstants.VALUE_LEVEL_WARNING).a(CommonConstants.KEY_PLUGIN_FILE_NAME, downloadResponse.getFileName());
            try {
                for (Object obj : downloadResponse.getExtras().keySet()) {
                    if (obj instanceof String) {
                        bVarA.a((String) obj, downloadResponse.getExtras().get(obj));
                    }
                }
                Map<String, String> mapC = this.a.c();
                for (String str3 : mapC.keySet()) {
                    bVarA.a(str3, mapC.get(str3));
                }
            } catch (Exception e) {
                AcLog.e("DownloadService", e.getMessage());
            }
            if (a(downloadResponse.getUrl())) {
                monitorService = SDKContext.getMonitorService();
                str2 = CommonConstants.event_downloadPluginFailed;
            } else {
                monitorService = SDKContext.getMonitorService();
                str2 = CommonConstants.event_downloadFileFailed;
            }
            monitorService.reportCategory(str2, bVarA.a());
        } catch (Exception e2) {
            AcLog.e("DownloadService", e2.getMessage());
        }
    }

    private void c(DownloadResponse downloadResponse) {
        MonitorService monitorService;
        String str;
        try {
            HashMap map = new HashMap();
            map.put(CommonConstants.KEY_PLUGIN_FILE_NAME, downloadResponse.getFileName());
            for (Object obj : downloadResponse.getExtras().keySet()) {
                if (obj instanceof String) {
                    map.put((String) obj, downloadResponse.getExtras().get(obj));
                }
            }
            if (a(downloadResponse.getUrl())) {
                monitorService = SDKContext.getMonitorService();
                str = CommonConstants.event_downloadPluginStarted;
            } else {
                monitorService = SDKContext.getMonitorService();
                str = CommonConstants.event_downloadFileStarted;
            }
            monitorService.reportCategory(str, map);
        } catch (Exception e) {
            AcLog.e("DownloadService", e.getMessage());
        }
    }

    private void c(DownloadResponse downloadResponse, int i2, String str) {
        MonitorService monitorService;
        String str2;
        AcLog.w(PluginService.TAG_PLUGIN, "onDownloadWarning: " + downloadResponse + ", " + ("errorCode: " + i2 + " errorMsg: " + str));
        try {
            com.volcengine.i.b bVarA = new com.volcengine.i.b().b(i2).c(str).b(CommonConstants.VALUE_LEVEL_WARNING).a(CommonConstants.KEY_PLUGIN_FILE_NAME, downloadResponse.getFileName());
            for (Object obj : downloadResponse.getExtras().keySet()) {
                if (obj instanceof String) {
                    bVarA.a((String) obj, downloadResponse.getExtras().get(obj));
                }
            }
            if (a(downloadResponse.getUrl())) {
                monitorService = SDKContext.getMonitorService();
                str2 = CommonConstants.event_downloadPluginWarning;
            } else {
                monitorService = SDKContext.getMonitorService();
                str2 = CommonConstants.event_downloadFileWarning;
            }
            monitorService.reportCategory(str2, bVarA.a());
        } catch (Exception e) {
            AcLog.e("DownloadService", e.getMessage());
        }
    }

    private void d(DownloadResponse downloadResponse) {
        MonitorService monitorService;
        String str;
        try {
            HashMap map = new HashMap();
            map.put(CommonConstants.KEY_PLUGIN_FILE_NAME, downloadResponse.getFileName());
            for (Object obj : downloadResponse.getExtras().keySet()) {
                if (obj instanceof String) {
                    map.put((String) obj, downloadResponse.getExtras().get(obj));
                }
            }
            if (a(downloadResponse.getUrl())) {
                monitorService = SDKContext.getMonitorService();
                str = CommonConstants.event_downloadPluginSucceed;
            } else {
                monitorService = SDKContext.getMonitorService();
                str = CommonConstants.event_downloadFileSucceed;
            }
            monitorService.reportCategory(str, map);
        } catch (Exception e) {
            AcLog.e("DownloadService", e.getMessage());
        }
    }

    private void e(DownloadResponse downloadResponse) {
        Context context = SDKContext.getContext();
        if (Build.VERSION.SDK_INT >= 23 && SDKContext.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") == 0) {
            downloadResponse.getExtras().put("is_network_connected", Boolean.toString(m.j(context)));
        }
        downloadResponse.getExtras().put("download_url", downloadResponse.getUrl());
        this.a.d(downloadResponse.getExtras());
    }

    public void a() {
        this.a.a();
    }

    public void a(DownloadRequest downloadRequest) {
        DownloadDispatcher downloadDispatcherB = b();
        boolean zExistTask = downloadDispatcherB.existTask(downloadRequest);
        if (!zExistTask) {
            downloadDispatcherB.removeTask(downloadRequest);
        }
        AcLog.v(PluginService.TAG_PLUGIN, "cancel: " + downloadRequest.getFileName() + ", contain:" + zExistTask);
    }

    public void a(CompatConsumer<DownloadResponse> compatConsumer) {
        this.e.set(compatConsumer);
    }

    public void a(d<DownloadResponse, Integer> dVar) {
        this.g.set(dVar);
    }

    public void a(e<DownloadResponse, Integer, String> eVar) {
        this.f.set(eVar);
    }

    @Override // com.volcengine.a.a
    public void a(Map<String, String> map) {
        MonitorService monitorService;
        String str;
        HashMap map2 = new HashMap();
        for (String str2 : map.keySet()) {
            String str3 = map.get(str2);
            AcLog.e(PluginService.TAG_PLUGIN, "onDiagnosis: extra - " + str2 + " - " + str3);
            map2.put(str2, str3);
        }
        if (map.containsKey("download_url") && a(map.get("download_url"))) {
            monitorService = SDKContext.getMonitorService();
            str = CommonConstants.event_downloadPluginDiagnosis;
        } else {
            monitorService = SDKContext.getMonitorService();
            str = CommonConstants.event_downloadFileDiagnosis;
        }
        monitorService.reportCategory(str, map2);
    }

    public List<String> b(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            String host = URI.create(str).getHost();
            JSONObject jSONObjectOptJSONObject = SDKContext.getConfigService().getConfigJson(ConfigService.download_config).optJSONObject("ip_map");
            if (jSONObjectOptJSONObject != null && !TextUtils.isEmpty(host)) {
                String strOptString = jSONObjectOptJSONObject.optString(host);
                if (!TextUtils.isEmpty(strOptString)) {
                    Collections.addAll(arrayList, strOptString.split(";"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void b(DownloadRequest downloadRequest) {
        DownloadDispatcher downloadDispatcherB = b();
        boolean zExistTask = downloadDispatcherB.existTask(downloadRequest);
        if (!zExistTask) {
            downloadDispatcherB.addTask(downloadRequest);
        }
        AcLog.v(PluginService.TAG_PLUGIN, "downloadFile: " + downloadRequest.getFileName() + ", contain:" + zExistTask);
    }

    public void b(d<DownloadResponse, String> dVar) {
        this.h.set(dVar);
    }

    public void onDownloadCancelled(DownloadResponse downloadResponse) {
        a(downloadResponse, "onDownloadCancelled");
    }

    public void onDownloadCompleted(DownloadResponse downloadResponse) {
        d(downloadResponse);
        a(downloadResponse);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x004d, code lost:
    
        if (r5.d.get(r1).intValue() < 3) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onDownloadFailed(com.bytedance.downloader.core.DownloadResponse r6, int r7, java.lang.String r8) {
        /*
            r5 = this;
            r0 = -5
            if (r7 != r0) goto L89
            com.bytedance.downloader.core.DownloadRequest$Builder r0 = new com.bytedance.downloader.core.DownloadRequest$Builder
            r0.<init>()
            java.lang.String r1 = r6.getUrl()
            com.bytedance.downloader.core.DownloadRequest$Builder r0 = r0.url(r1)
            java.lang.String r1 = r6.getMd5()
            com.bytedance.downloader.core.DownloadRequest$Builder r0 = r0.md5(r1)
            java.util.List r1 = r6.getHostIpList()
            com.bytedance.downloader.core.DownloadRequest$Builder r0 = r0.hostIpList(r1)
            java.lang.String r1 = r6.getFileName()
            com.bytedance.downloader.core.DownloadRequest$Builder r0 = r0.fileName(r1)
            java.lang.String r1 = r6.getSavePath()
            com.bytedance.downloader.core.DownloadRequest$Builder r0 = r0.savePath(r1)
            com.bytedance.downloader.core.DownloadRequest r0 = r0.build()
            java.lang.String r1 = r0.toString()
            java.util.Map<java.lang.String, java.lang.Integer> r2 = r5.d
            boolean r2 = r2.containsKey(r1)
            if (r2 == 0) goto L50
            java.util.Map<java.lang.String, java.lang.Integer> r2 = r5.d
            java.lang.Object r2 = r2.get(r1)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r3 = 3
            if (r2 >= r3) goto L89
            goto L5a
        L50:
            java.util.Map<java.lang.String, java.lang.Integer> r2 = r5.d
            r3 = 0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2.put(r1, r3)
        L5a:
            java.util.Map<java.lang.String, java.lang.Integer> r2 = r5.d
            java.lang.Object r2 = r2.get(r1)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.util.Map<java.lang.String, java.lang.Integer> r3 = r5.d
            int r2 = r2 + 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3.put(r1, r2)
            java.util.Timer r1 = new java.util.Timer
            r1.<init>()
            com.volcengine.c.a$a r2 = new com.volcengine.c.a$a
            r2.<init>(r0)
            r3 = 1000(0x3e8, double:4.94E-321)
            r1.schedule(r2, r3)
            r5.c(r6, r7, r8)
            java.lang.String r7 = "onDownloadFailed"
            r5.a(r6, r7)
            goto L8f
        L89:
            r5.b(r6, r7, r8)
            r5.a(r6, r7, r8)
        L8f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.volcengine.c.a.onDownloadFailed(com.bytedance.downloader.core.DownloadResponse, int, java.lang.String):void");
    }

    public void onDownloadPrepared(DownloadResponse downloadResponse) {
        b(downloadResponse);
        a(downloadResponse, "onDownloadPrepared");
    }

    public void onDownloadProgress(DownloadResponse downloadResponse, int i2) {
        a(downloadResponse, i2);
    }

    public void onDownloadRemoved(DownloadResponse downloadResponse) {
        a(downloadResponse, "onDownloadRemoved");
    }

    public void onDownloadSpeed(DownloadResponse downloadResponse, long j2) {
        a(downloadResponse, "onDownloadSpeed");
    }

    public void onDownloadStarted(DownloadResponse downloadResponse) {
        c(downloadResponse);
        a(downloadResponse, "onDownloadStarted");
    }

    public void onDownloadUpdated(DownloadResponse downloadResponse) {
        a(downloadResponse, "onDownloadUpdated");
    }

    public void onDownloadWarning(DownloadResponse downloadResponse, int i2, String str) {
        if (i2 != -12 && i2 != -10 && i2 != -5 && i2 != 0) {
            try {
                e(downloadResponse);
            } catch (Exception e) {
                AcLog.e("DownloadService", e.getMessage());
            }
        }
        c(downloadResponse, i2, str);
        a(downloadResponse, "onDownloadWarning");
    }

    @Override // com.volcengine.common.innerapi.ConfigService.ConfigObserver
    public void onReceiveConfig(String str, String str2) {
        AcLog.v("DownloadService", "onReceiveConfig: configName = [" + str + "], config = [" + str2 + "]");
        if (ConfigService.download_config.equals(str)) {
            b();
            SDKContext.getConfigService().unregister(ConfigService.download_config, this);
        }
    }
}
