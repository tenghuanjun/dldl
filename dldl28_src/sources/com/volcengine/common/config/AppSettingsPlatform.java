package com.volcengine.common.config;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import com.bytedance.http.Call;
import com.bytedance.http.Callback;
import com.bytedance.http.HttpDispatcher;
import com.bytedance.http.HttpRequest;
import com.bytedance.http.HttpResponse;
import com.lzy.okgo.model.HttpHeaders;
import com.tencent.connect.common.Constants;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.androidcloud.common.pod.PodInfo;
import com.volcengine.common.SDKContext;
import com.volcengine.common.config.b;
import com.volcengine.common.contant.CommonConstants;
import com.volcengine.common.contant.CommonErrorCode;
import com.volcengine.common.innerapi.ConfigService;
import com.volcengine.common.innerapi.MonitorService;
import com.volcengine.j.c;
import com.volcengine.j.j;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes3.dex */
class AppSettingsPlatform implements b {
    private static final String[] e = {"27.128.209.229", "42.81.24.101"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HttpDispatcher f1114a;
    private final String b;
    private final boolean c;
    private final int d;

    static class BaseResponse {
        public Data data;
        public String message;

        BaseResponse() {
        }

        public String toString() {
            return "BaseResponse{data=" + this.data + ", message='" + this.message + "'}";
        }
    }

    static class Data {
        public String ctx_infos;
        public Map<String, Object> settings;
        public Map<String, Object> vid_info;

        Data() {
        }

        public String toString() {
            return "Data{settings=" + this.settings + ", vid_info=" + this.vid_info + ", ctx_infos='" + this.ctx_infos + "'}";
        }
    }

    class a implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ HttpRequest f1115a;
        final /* synthetic */ b.a b;

        a(AppSettingsPlatform appSettingsPlatform, HttpRequest httpRequest, b.a aVar) {
            this.f1115a = httpRequest;
            this.b = aVar;
        }

        @Override // com.bytedance.http.Callback
        public void onDiagnosis(Call call, HttpResponse httpResponse) {
            com.volcengine.f.a.a(this.f1115a, httpResponse);
        }

        @Override // com.bytedance.http.Callback
        public void onResponse(Call call, HttpResponse httpResponse) {
            int iCode = httpResponse.code();
            String strBody = httpResponse.body();
            if (200 != iCode) {
                MonitorService monitorService = SDKContext.getMonitorService();
                Pair<Integer, String> pair = CommonErrorCode.ERROR_REQUEST_PLUGIN_CONFIG_FAILED;
                monitorService.reportCategory(CommonConstants.event_requestConfigFailed, c.a(((Integer) pair.first).intValue(), (String) pair.second, iCode, strBody));
                this.b.a(iCode, strBody);
                return;
            }
            SDKContext.getMonitorService().reportCategory(CommonConstants.event_requestConfigSucceed, Collections.singletonMap(CommonConstants.KEY_RESPONSE, strBody));
            SDKContext.updateServiceTime(httpResponse.headers().toMap());
            Data data = ((BaseResponse) SDKContext.getJsonConverter().fromJson(strBody, BaseResponse.class)).data;
            this.b.a(0, data == null ? null : j.a(data.settings));
        }
    }

    public AppSettingsPlatform(int i) {
        int i2 = 0;
        HttpDispatcher.Builder builderRetryMode = new HttpDispatcher.Builder().retryCount(2).retryMode(0);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        HttpDispatcher.Builder builderExecutor = builderRetryMode.retryInterval(1000L, timeUnit).connectTimeout(10000L, timeUnit).readTimeout(15000L, timeUnit).cacheExpiredTime(MonitorCommonConstants.SECOND_STOP_INTERVAL, timeUnit).dnsSelectStrategy(0).executor(SDKContext.getExecutorsService().getIOExecutor());
        String[] strArr = e;
        int length = strArr.length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            builderExecutor.addBackupIp("vegameapi.volces.com", strArr[i2]);
            i2++;
        }
        this.f1114a = builderExecutor.build();
        boolean zIsBoe = SDKContext.isBoe();
        this.c = zIsBoe;
        this.b = zIsBoe ? "is.snssdk.com.boe-gateway.byted.org" : "vegameapi.volces.com";
        this.d = i;
    }

    private Map<String, Object> b() {
        HashMap map = new HashMap();
        map.put("aid", 202);
        map.put("iid", SDKContext.getIid());
        map.put(MonitorConstants.KEY_DEVICE_ID, SDKContext.getDid());
        map.put("device_platform", PodInfo.GAME_TYPE_ANDROID);
        map.put("version_code", Integer.valueOf(SDKContext.getAppVersionCode()));
        map.put("caller_name", "vesdk");
        map.put("channel", c());
        Locale locale = Locale.getDefault();
        map.put("region", locale.getCountry());
        map.put(IjkMediaMeta.IJKM_KEY_LANGUAGE, locale.getLanguage());
        map.put("os_version", Build.VERSION.RELEASE);
        map.put("os_api", Integer.valueOf(Build.VERSION.SDK_INT));
        map.put("device_type", Build.MODEL);
        map.put("device_brand", Build.BRAND);
        map.put("host_abi", SDKContext.getHostAbi());
        map.put(Constants.JumpUrlConstants.URL_KEY_SDK_VERSION, SDKContext.getSdkVersion());
        map.put("account_id", SDKContext.getAccountId());
        map.put("service_type", Integer.valueOf(this.d));
        return map;
    }

    private String c() {
        String strE = c.e();
        return TextUtils.isEmpty(strE) ? "Default" : strE;
    }

    @Override // com.volcengine.common.config.b
    public List<String> a() {
        return Arrays.asList(ConfigService.logger_config, ConfigService.daemon_config, "plugin_config", ConfigService.key_code_config, ConfigService.network_config, ConfigService.download_config, ConfigService.engine_config, "monitor_config", ConfigService.switch_config, ConfigService.file_channel_config, ConfigService.sensor_config);
    }

    @Override // com.volcengine.common.config.b
    public void a(b.a aVar) {
        HttpRequest.Builder builder = new HttpRequest.Builder().addHost(this.b).addPathSegment("service/settings/v3/").addHeader(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/json").get();
        if (this.c) {
            builder.http();
        } else {
            builder.https();
        }
        for (Map.Entry<String, Object> entry : b().entrySet()) {
            builder.addQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
        }
        HttpRequest httpRequestBuild = builder.build();
        this.f1114a.newCall(httpRequestBuild).enqueue(new a(this, httpRequestBuild, aVar));
    }

    @Override // com.volcengine.common.config.b
    public void a(ConfigService configService, int i, String str) {
        JSONObject jSONObject;
        List<String> listA = a();
        if (i != 0) {
            Iterator<String> it = listA.iterator();
            while (it.hasNext()) {
                configService.dispatchConfig(it.next(), null);
            }
            return;
        }
        if (TextUtils.isEmpty(str)) {
            jSONObject = new JSONObject();
        } else {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e2) {
                AcLog.e("AppSettingsPlatform", "dispatchResponse: " + Log.getStackTraceString(e2));
                jSONObject = new JSONObject();
            }
        }
        for (String str2 : listA) {
            String strOptString = jSONObject.optString(str2);
            try {
                configService.storeConfig(str2, strOptString);
                configService.dispatchConfig(str2, strOptString);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }
}
