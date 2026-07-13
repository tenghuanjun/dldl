package com.volcengine.f;

import android.os.Build;
import android.text.TextUtils;
import com.bytedance.dns.DnsResolver;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.bytedance.http.Call;
import com.bytedance.http.Callback;
import com.bytedance.http.HttpDispatcher;
import com.bytedance.http.HttpRequest;
import com.bytedance.http.HttpResponse;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.SDKContext;
import com.volcengine.common.contant.CommonConstants;
import com.volcengine.common.innerapi.ConfigService;
import com.volcengine.common.innerapi.ExecutorsService;
import com.volcengine.common.innerapi.HttpService;
import com.volcengine.common.innerapi.MonitorService;
import com.volcengine.common.innerapi.PluginService;
import com.volcengine.f.a;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class a implements HttpService, com.volcengine.a.a, ConfigService.ConfigObserver {
    private static final String[] f = {"vegame.volcengineapi.com", "acep.volcengineapi.com"};
    private static final String[] g = {"163.179.228.105", "183.240.178.65", "58.216.15.106", "111.7.89.204", "125.64.129.238"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile HttpDispatcher f1134a;
    private HttpDispatcher.Builder b;
    private final Queue<Call> d = new ConcurrentLinkedQueue();
    private Map<String, String> e = new HashMap();
    private final com.volcengine.a.b c = new com.volcengine.a.b(SDKContext.getContext(), SDKContext.getExecutorsService().getIOExecutor(), this);

    /* JADX INFO: renamed from: com.volcengine.f.a$a, reason: collision with other inner class name */
    class C0448a implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ HttpService.Callback f1135a;

        C0448a(HttpService.Callback callback) {
            this.f1135a = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a(HttpService.Callback callback, Call call, HttpResponse httpResponse) {
            callback.onResponse(new HttpService.Response(call.isCancelled() ? -2 : httpResponse.code(), httpResponse.message() != null ? httpResponse.message() : "", httpResponse.headers() != null ? httpResponse.headers().toMap() : Collections.emptyMap(), httpResponse.extras() != null ? httpResponse.extras() : Collections.emptyMap(), httpResponse.body() != null ? httpResponse.body() : "", httpResponse.request() != null ? httpResponse.request().headers().toMap() : Collections.emptyMap(), (httpResponse.request() == null || httpResponse.request().body() == null) ? new byte[0] : httpResponse.request().body()));
        }

        @Override // com.bytedance.http.Callback
        public void onDiagnosis(Call call, HttpResponse httpResponse) {
            a.this.c.d(httpResponse.extras());
        }

        @Override // com.bytedance.http.Callback
        public void onResponse(final Call call, final HttpResponse httpResponse) {
            ExecutorsService executorsService;
            Runnable runnable;
            MonitorService monitorService;
            String str;
            AcLog.v("HttpService", httpResponse.toString());
            try {
                try {
                    a.this.a(httpResponse);
                    HashMap map = new HashMap();
                    for (Object obj : httpResponse.extras().keySet()) {
                        if (obj instanceof String) {
                            Object obj2 = httpResponse.extras().get(obj);
                            AcLog.e(PluginService.TAG_PLUGIN, "onDiagnosis: extra - " + obj + " - " + obj2);
                            map.put((String) obj, obj2);
                        }
                    }
                    if (httpResponse.code() != 200) {
                        map.put(CommonConstants.KEY_ORIGIN_ERR_CODE, Integer.valueOf(httpResponse.code()));
                        map.put(CommonConstants.KEY_ORIGIN_ERR_MSG, httpResponse.message());
                        monitorService = SDKContext.getMonitorService();
                        str = CommonConstants.event_netServiceFailed;
                    } else {
                        monitorService = SDKContext.getMonitorService();
                        str = CommonConstants.event_netServiceSucceed;
                    }
                    monitorService.reportCategory(str, map);
                    SDKContext.getMonitorService().reportOnlyEvent(CommonConstants.event_postOnResponse);
                    a.this.d.remove(call);
                    executorsService = SDKContext.getExecutorsService();
                    final HttpService.Callback callback = this.f1135a;
                    runnable = new Runnable() { // from class: com.volcengine.f.a$a$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            a.C0448a.a(callback, call, httpResponse);
                        }
                    };
                } catch (Exception e) {
                    AcLog.e("HttpService", e.getMessage());
                    a.this.d.remove(call);
                    executorsService = SDKContext.getExecutorsService();
                    final HttpService.Callback callback2 = this.f1135a;
                    runnable = new Runnable() { // from class: com.volcengine.f.a$a$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            a.C0448a.a(callback2, call, httpResponse);
                        }
                    };
                }
                executorsService.executeMain(runnable);
            } catch (Throwable th) {
                a.this.d.remove(call);
                ExecutorsService executorsService2 = SDKContext.getExecutorsService();
                final HttpService.Callback callback3 = this.f1135a;
                executorsService2.executeMain(new Runnable() { // from class: com.volcengine.f.a$a$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        a.C0448a.a(callback3, call, httpResponse);
                    }
                });
                throw th;
            }
        }
    }

    class b implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ HttpService.Callback f1136a;

        b(HttpService.Callback callback) {
            this.f1136a = callback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a(HttpService.Callback callback, Call call, HttpResponse httpResponse) {
            callback.onResponse(new HttpService.Response(call.isCancelled() ? -2 : httpResponse.code(), httpResponse.message() != null ? httpResponse.message() : "", httpResponse.headers() != null ? httpResponse.headers().toMap() : Collections.emptyMap(), httpResponse.extras() != null ? httpResponse.extras() : Collections.emptyMap(), httpResponse.body() != null ? httpResponse.body() : "", httpResponse.request() != null ? httpResponse.request().headers().toMap() : Collections.emptyMap(), (httpResponse.request() == null || httpResponse.request().body() == null) ? new byte[0] : httpResponse.request().body()));
        }

        @Override // com.bytedance.http.Callback
        public void onDiagnosis(Call call, HttpResponse httpResponse) {
            a.this.c.d(httpResponse.extras());
        }

        @Override // com.bytedance.http.Callback
        public void onResponse(final Call call, final HttpResponse httpResponse) {
            ExecutorsService executorsService;
            Runnable runnable;
            MonitorService monitorService;
            String str;
            try {
                try {
                    a.this.a(httpResponse);
                    HashMap map = new HashMap();
                    for (Object obj : httpResponse.extras().keySet()) {
                        if (obj instanceof String) {
                            Object obj2 = httpResponse.extras().get(obj);
                            AcLog.e(PluginService.TAG_PLUGIN, "onDiagnosis: extra - " + obj + " - " + obj2);
                            map.put((String) obj, obj2);
                        }
                    }
                    if (httpResponse.code() != 200) {
                        map.put(CommonConstants.KEY_ORIGIN_ERR_CODE, Integer.valueOf(httpResponse.code()));
                        map.put(CommonConstants.KEY_ORIGIN_ERR_MSG, httpResponse.message());
                        monitorService = SDKContext.getMonitorService();
                        str = CommonConstants.event_netServiceFailed;
                    } else {
                        monitorService = SDKContext.getMonitorService();
                        str = CommonConstants.event_netServiceSucceed;
                    }
                    monitorService.reportCategory(str, map);
                    a.this.d.remove(call);
                    executorsService = SDKContext.getExecutorsService();
                    final HttpService.Callback callback = this.f1136a;
                    runnable = new Runnable() { // from class: com.volcengine.f.a$b$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            a.b.a(callback, call, httpResponse);
                        }
                    };
                } catch (Exception e) {
                    AcLog.e("HttpService", e.getMessage());
                    a.this.d.remove(call);
                    executorsService = SDKContext.getExecutorsService();
                    final HttpService.Callback callback2 = this.f1136a;
                    runnable = new Runnable() { // from class: com.volcengine.f.a$b$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            a.b.a(callback2, call, httpResponse);
                        }
                    };
                }
                executorsService.executeMain(runnable);
            } catch (Throwable th) {
                a.this.d.remove(call);
                ExecutorsService executorsService2 = SDKContext.getExecutorsService();
                final HttpService.Callback callback3 = this.f1136a;
                executorsService2.executeMain(new Runnable() { // from class: com.volcengine.f.a$b$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        a.b.a(callback3, call, httpResponse);
                    }
                });
                throw th;
            }
        }
    }

    public a() {
        SDKContext.getConfigService().register(ConfigService.network_config, this);
    }

    public static String a(HttpRequest httpRequest) {
        return MessageFormat.format("\nurl={0}, \nheader={1}, \nbody={2}", httpRequest.url().toString(), httpRequest.headers().toString(), httpRequest.body());
    }

    private void a() {
        com.volcengine.a.b bVar = this.c;
        if (bVar != null) {
            bVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i) {
        DnsResolver dnsResolverResolver;
        String str;
        if (i == 1) {
            dnsResolverResolver = c().resolver();
            str = "vegame.volcengineapi.com";
        } else {
            dnsResolverResolver = c().resolver();
            str = "acep.volcengineapi.com";
        }
        dnsResolverResolver.m6356lambda$refreshAsync$0$combytedancednsDnsResolver(str);
    }

    public static void a(HttpRequest httpRequest, HttpResponse httpResponse) {
        AcLog.e("HttpService", "onDiagnosis: request = " + a(httpRequest) + "\nresponse = " + b(httpResponse));
        Map<String, Object> mapA = com.volcengine.j.c.a(httpResponse.code(), httpResponse.body());
        mapA.put("url", httpRequest.url().toString());
        SDKContext.getMonitorService().reportCategory(CommonConstants.event_networkDiagnose, mapA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HttpResponse httpResponse) {
        try {
            if (httpResponse.extras() != null) {
                httpResponse.extras().putAll(this.c.c());
            }
        } catch (Exception e) {
            AcLog.e("HttpService", e.getMessage());
        }
    }

    private boolean a(JSONObject jSONObject) {
        return jSONObject.optBoolean("ignore_certification_verify", false) || Build.VERSION.SDK_INT <= 22;
    }

    public static String b(HttpResponse httpResponse) {
        int iCode = httpResponse.code();
        return MessageFormat.format("code={0}, msg={1}, body={2}", Integer.valueOf(iCode), httpResponse.message(), httpResponse.body());
    }

    private void b() {
        try {
            if (this.f1134a != null) {
                this.f1134a.dispatcher().cancelAll();
            }
            while (!this.d.isEmpty()) {
                Call callPoll = this.d.poll();
                if (callPoll != null) {
                    callPoll.cancel();
                }
            }
        } catch (Exception e) {
            AcLog.e("HttpService", e.getMessage());
        }
    }

    private synchronized HttpDispatcher c() {
        if (this.f1134a == null) {
            JSONObject configJson = SDKContext.getConfigService().getConfigJson(ConfigService.network_config);
            HttpDispatcher.Builder builder = new HttpDispatcher.Builder();
            this.b = builder;
            HttpDispatcher.Builder builderRetryCount = builder.logger(false).ignoreCertificateVerify(a(configJson)).addInterceptor(new c(configJson.optBoolean("use_short_connection", false))).executor(SDKContext.getExecutorsService().getIOExecutor()).retryCount(configJson.optInt("retry_count", 2));
            long jOptLong = configJson.optLong("retry_interval", 1000L);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            this.f1134a = builderRetryCount.retryInterval(jOptLong, timeUnit).retryMode(configJson.optInt("retry_mode", 0)).connectTimeout(configJson.optLong("connect_timeout", 10000L), timeUnit).readTimeout(configJson.optLong("read_timeout", 15000L), timeUnit).cacheExpiredTime(configJson.optLong("cache_expired_time", MonitorCommonConstants.SECOND_STOP_INTERVAL), timeUnit).dnsSelectStrategy(configJson.optInt("dns_select_strategy", 0)).build();
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
                                    this.f1134a.resolver().addHttpCloud(next, Arrays.asList(strOptString.split(";")));
                                }
                            }
                        }
                    }
                } else {
                    for (String str : f) {
                        this.f1134a.resolver().addHttpCloud(str, Arrays.asList(g));
                    }
                }
            } catch (Exception e) {
                AcLog.e("HttpService", e.getMessage());
            }
        }
        return this.f1134a;
    }

    @Override // com.volcengine.a.a
    public void a(Map<String, String> map) {
        HashMap map2 = new HashMap();
        for (String str : map.keySet()) {
            String str2 = map.get(str);
            AcLog.e(PluginService.TAG_PLUGIN, "onDiagnosis: extra - " + str + " - " + str2);
            map2.put(str, str2);
        }
        SDKContext.getMonitorService().reportCategory(CommonConstants.event_netServiceDiagnosis, map2);
    }

    @Override // com.volcengine.common.innerapi.HttpService
    public void cancelAll() {
        b();
        a();
    }

    @Override // com.volcengine.common.innerapi.HttpService
    public void get(List<String> list, List<String> list2, Map<String, String> map, Map<String, String> map2, HttpService.Callback callback) {
        HttpRequest.Builder builder = new HttpRequest.Builder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            builder.addHost(it.next());
        }
        Iterator<String> it2 = list2.iterator();
        while (it2.hasNext()) {
            builder.addPathSegment(it2.next());
        }
        for (String str : map.keySet()) {
            builder.addQueryParameter(str, (String) Objects.requireNonNull(map.get(str)));
        }
        for (String str2 : map2.keySet()) {
            builder.addHeader(str2, (String) Objects.requireNonNull(map2.get(str2)));
        }
        for (Map.Entry<String, String> entry : this.e.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        HttpRequest httpRequestBuild = builder.https().get().build();
        AcLog.v("HttpService", httpRequestBuild.toString());
        Call callNewCall = c().newCall(httpRequestBuild);
        this.d.offer(callNewCall);
        callNewCall.enqueue(new b(callback));
    }

    @Override // com.volcengine.common.innerapi.HttpService
    public void init(final int i) {
        SDKContext.getExecutorsService().getIOExecutor().execute(new Runnable() { // from class: com.volcengine.f.a$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.a(i);
            }
        });
    }

    @Override // com.volcengine.common.innerapi.ConfigService.ConfigObserver
    public void onReceiveConfig(String str, String str2) {
        AcLog.v("HttpService", "onReceiveConfig: configName = [" + str + "], config = [" + str2 + "]");
        if (ConfigService.network_config.equals(str)) {
            SDKContext.getConfigService().unregister(ConfigService.network_config, this);
            c();
        }
    }

    @Override // com.volcengine.common.innerapi.HttpService
    public void post(List<String> list, List<String> list2, Map<String, String> map, Map<String, String> map2, String str, HttpService.Callback callback) {
        HttpRequest.Builder builder = new HttpRequest.Builder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            builder.addHost(it.next());
        }
        Iterator<String> it2 = list2.iterator();
        while (it2.hasNext()) {
            builder.addPathSegment(it2.next());
        }
        HashMap map3 = new HashMap();
        for (String str2 : map.keySet()) {
            String str3 = (String) Objects.requireNonNull(map.get(str2));
            map3.put(str2, str3);
            builder.addQueryParameter(str2, str3);
        }
        for (String str4 : map2.keySet()) {
            builder.addHeader(str4, (String) Objects.requireNonNull(map2.get(str4)));
        }
        for (Map.Entry<String, String> entry : this.e.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        builder.body(str);
        HttpRequest httpRequestBuild = builder.https().post().build();
        AcLog.v("HttpService", httpRequestBuild.toString());
        Call callNewCall = c().newCall(httpRequestBuild);
        this.d.offer(callNewCall);
        SDKContext.getMonitorService().reportCategory(CommonConstants.event_postRequest, map3);
        callNewCall.enqueue(new C0448a(callback));
    }

    @Override // com.volcengine.common.innerapi.HttpService
    public void setCommonHeader(Map<String, String> map) {
        this.e = new HashMap(map);
    }
}
