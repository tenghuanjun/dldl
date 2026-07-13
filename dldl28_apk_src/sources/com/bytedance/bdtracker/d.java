package com.bytedance.bdtracker;

import android.accounts.Account;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import com.bytedance.applog.IActiveCustomParamsCallback;
import com.bytedance.applog.IAppLogInstance;
import com.bytedance.applog.IDataObserver;
import com.bytedance.applog.IEventObserver;
import com.bytedance.applog.IExtraParams;
import com.bytedance.applog.IHeaderCustomTimelyCallback;
import com.bytedance.applog.IOaidObserver;
import com.bytedance.applog.IPullAbTestConfigCallback;
import com.bytedance.applog.ISessionObserver;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.Level;
import com.bytedance.applog.R;
import com.bytedance.applog.UriConfig;
import com.bytedance.applog.alink.IALinkListener;
import com.bytedance.applog.convert.BuildConfig;
import com.bytedance.applog.event.EventBuilder;
import com.bytedance.applog.event.IEventHandler;
import com.bytedance.applog.exception.AppCrashType;
import com.bytedance.applog.exposure.ViewExposureManager;
import com.bytedance.applog.log.EventBus;
import com.bytedance.applog.log.IAppLogLogger;
import com.bytedance.applog.log.ILogProcessor;
import com.bytedance.applog.log.LogProcessorHolder;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.applog.network.INetworkClient;
import com.bytedance.applog.oneid.IDBindCallback;
import com.bytedance.applog.profile.UserProfileCallback;
import com.bytedance.applog.simulate.SimulateLaunchActivity;
import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import com.volcengine.common.contant.CommonConstants;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class d implements IAppLogInstance {
    public static final List<d> J = new CopyOnWriteArrayList();
    public static final AtomicInteger K = new AtomicInteger(0);
    public IActiveCustomParamsCallback A;
    public volatile r B;
    public IEventHandler C;
    public final IAppLogLogger D;
    public final g3 j;
    public final e3 k;
    public volatile i1 o;
    public volatile k1 p;
    public volatile c0 q;
    public volatile v r;
    public volatile ViewExposureManager s;
    public volatile INetworkClient t;
    public volatile IHeaderCustomTimelyCallback v;
    public volatile s0 w;
    public v0 y;
    public IALinkListener z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConcurrentHashMap<String, JSONObject> f231a = new ConcurrentHashMap<>();
    public final x0 b = new x0();
    public final w0 c = new w0();
    public final n1 d = new n1();
    public final c1 e = new c1();
    public final Set<Integer> f = new HashSet();
    public final Set<String> g = new HashSet();
    public final Set<Class<?>> h = new HashSet();
    public final Map<String, i0> i = new ConcurrentHashMap();
    public int l = 0;
    public String m = "";
    public volatile Application n = null;
    public volatile boolean u = false;
    public volatile boolean x = false;
    public volatile boolean E = true;
    public long F = 0;
    public volatile boolean G = false;
    public final h4<String> H = new h4<>();
    public final h4<String> I = new h4<>();

    public class a implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f232a;

        public a(boolean z) {
            this.f232a = z;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject.put(CommonConstants.key_appId, d.this.m);
                jSONObject2.put("接口加密开关", this.f232a);
                jSONObject.put("config", jSONObject2);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public class b implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f233a;

        public b(boolean z) {
            this.f233a = z;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject.put(CommonConstants.key_appId, d.this.m);
                jSONObject2.put("禁止采集详细信息开关", this.f233a);
                jSONObject.put("config", jSONObject2);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public class c implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f234a;

        public c(boolean z) {
            this.f234a = z;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject.put(CommonConstants.key_appId, d.this.m);
                jSONObject2.put("剪切板开关", this.f234a);
                jSONObject.put("config", jSONObject2);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    /* JADX INFO: renamed from: com.bytedance.bdtracker.d$d, reason: collision with other inner class name */
    public class C0150d implements EventBus.DataFetcher {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f235a;

        public C0150d(boolean z) {
            this.f235a = z;
        }

        @Override // com.bytedance.applog.log.EventBus.DataFetcher
        public Object fetch() {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject.put(CommonConstants.key_appId, d.this.m);
                jSONObject2.put("隐私模式开关", this.f235a);
                jSONObject.put("config", jSONObject2);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }
    }

    public d() {
        K.incrementAndGet();
        this.D = new LoggerImpl();
        this.j = new g3(this);
        this.k = new e3(this);
        J.add(this);
    }

    public final void a(Object obj, JSONObject jSONObject) {
        boolean z;
        if (this.r == null || obj == null) {
            return;
        }
        q3 q3Var = new q3("bav2b_page", true);
        JSONObject jSONObject2 = new JSONObject();
        String name = obj.getClass().getName();
        Iterator<Class<?>> it = k4.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (it.next().isInstance(obj)) {
                Activity activity = null;
                try {
                    activity = (Activity) obj.getClass().getMethod("getActivity", null).invoke(obj, null);
                } catch (Throwable unused) {
                }
                if (activity != null) {
                    name = activity.getClass().getName() + ":" + name;
                }
                z = true;
            }
        }
        try {
            jSONObject2.put("page_key", name);
            jSONObject2.put("is_fragment", z);
            jSONObject2.put("duration", 1000L);
            jSONObject2.put("page_title", k4.c(obj));
            jSONObject2.put("page_path", k4.b(obj));
            jSONObject2.put("is_custom", true);
            n0.b(jSONObject, jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        q3Var.o = jSONObject2;
        receive(q3Var);
    }

    public final void a(String str, String str2, long j) {
        z1 monitor = getMonitor();
        if (monitor == null) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        str.hashCode();
        g2 e2Var = !str.equals("sdk_init") ? !str.equals("api_usage") ? null : new e2(str2, jElapsedRealtime - j) : new n2(jElapsedRealtime - j);
        if (e2Var != null) {
            ((d2) monitor).a(e2Var);
        }
    }

    public final boolean a() {
        return n0.a((Object) this.p, "Please initialize first");
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void activateALink(Uri uri) {
        JSONObject jSONObject;
        if (b()) {
            return;
        }
        i iVar = this.q.B;
        iVar.a();
        if (uri != null) {
            iVar.h = uri.toString();
        }
        d dVar = iVar.c.d;
        Intrinsics.checkExpressionValueIsNotNull(dVar, "mEngine.appLog");
        dVar.D.debug(3, "Activate deep link with url: {}...", iVar.h);
        Handler handler = iVar.b;
        if (handler != null) {
            try {
                jSONObject = new JSONObject();
                if (uri != null) {
                    String scheme = uri.getScheme();
                    if (Intrinsics.areEqual(scheme, "http") || Intrinsics.areEqual(scheme, "https")) {
                        jSONObject.put("tr_token", uri.getLastPathSegment());
                    }
                    for (String str : uri.getQueryParameterNames()) {
                        jSONObject.put(str, uri.getQueryParameter(str));
                    }
                }
            } catch (Throwable unused) {
                jSONObject = null;
            }
            k kVar = (k) o.f299a.a(jSONObject, k.class);
            String strD = kVar != null ? kVar.d() : null;
            if (strD == null || strD.length() == 0) {
                return;
            }
            iVar.e = 0;
            handler.sendMessage(handler.obtainMessage(1, kVar));
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public synchronized void addDataObserver(IDataObserver iDataObserver) {
        if (this.y == null) {
            this.y = new v0();
        }
        this.y.a(iDataObserver);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void addEventObserver(IEventObserver iEventObserver) {
        this.c.a(iEventObserver);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String addNetCommonParams(Context context, String str, boolean z, Level level) {
        return this.j.a(this.p != null ? this.p.e() : null, str, z, level);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void addSessionHook(ISessionObserver iSessionObserver) {
        this.b.a(iSessionObserver);
    }

    public final boolean b() {
        return n0.a((Object) this.q, "Please initialize first");
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void bind(Map<String, String> map, IDBindCallback iDBindCallback) {
        if (b()) {
            return;
        }
        c0 c0Var = this.q;
        if (map == null) {
            c0Var.d.D.warn("BindID identities is null", new Object[0]);
        } else {
            c0Var.E.a(map, iDBindCallback);
        }
    }

    public n1 c() {
        return this.d;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void clearDb() {
        if (this.q == null) {
            new j0().initCause(new AssertionError("clearDb before init")).printStackTrace();
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.D.debug("Start to clear db data...", new Object[0]);
        this.q.c().a();
        this.D.debug("Db data cleared", new Object[0]);
        a("api_usage", "clearDb", jElapsedRealtime);
    }

    public boolean d() {
        return this.G;
    }

    public final void e() {
        h4<String> h4Var = this.H;
        if (!h4Var.b || n0.b(h4Var, this.o.g())) {
            return;
        }
        if (this.I.b) {
            this.p.b(this.H.f259a, this.I.f259a);
        } else {
            this.p.h(this.H.f259a);
        }
        this.p.g("");
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void flush() {
        if (b()) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.q.a((String[]) null, true);
        a("api_usage", "flush", jElapsedRealtime);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public IALinkListener getALinkListener() {
        return this.z;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public <T> T getAbConfig(String str, T t) {
        if (a()) {
            return null;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        k1 k1Var = this.p;
        JSONObject jSONObjectOptJSONObject = k1Var.c.a().optJSONObject(str);
        if (jSONObjectOptJSONObject != null) {
            String strOptString = jSONObjectOptJSONObject.optString("vid");
            Object objOpt = jSONObjectOptJSONObject.opt("val");
            k1Var.a(strOptString);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ab_sdk_version", strOptString);
                k1Var.i.onEventV3("abtest_exposure", jSONObject, 0);
            } catch (Throwable th) {
                k1Var.i.D.error(Collections.singletonList("DeviceManager"), "JSON handle failed", th, new Object[0]);
            }
            Object obj = objOpt != null ? objOpt : null;
            if (obj != null) {
                t = (T) obj;
            }
        }
        a("api_usage", "getAbConfig", jElapsedRealtime);
        return t;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getAbSdkVersion() {
        if (a()) {
            return null;
        }
        return this.p.a();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public IActiveCustomParamsCallback getActiveCustomParams() {
        return this.A;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    @Deprecated
    public String getAid() {
        return this.m;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public JSONObject getAllAbTestConfigs() {
        return this.q == null ? new JSONObject() : this.q.e.a();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public f getAppContext() {
        return null;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getAppId() {
        return this.m;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getClientUdid() {
        return a() ? "" : this.p.d.optString("clientudid", "");
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public Context getContext() {
        return this.n;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getDeepLinkUrl() {
        if (this.q != null) {
            return this.q.B.h;
        }
        return null;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getDid() {
        return a() ? "" : this.p.c();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public boolean getEncryptAndCompress() {
        return this.E;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public s0 getEventFilterByClient() {
        return this.w;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public IEventHandler getEventHandler() {
        return this.C;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public JSONObject getHeader() {
        if (a()) {
            return null;
        }
        return this.p.e();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public IHeaderCustomTimelyCallback getHeaderCustomCallback() {
        return this.v;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public <T> T getHeaderValue(String str, T t, Class<T> cls) {
        if (a()) {
            return null;
        }
        return (T) this.p.a(str, t, cls);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getIid() {
        return a() ? "" : this.p.f();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public InitConfig getInitConfig() {
        if (this.o != null) {
            return this.o.c;
        }
        return null;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public int getLaunchFrom() {
        return this.l;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public z1 getMonitor() {
        if (b()) {
            return null;
        }
        return this.q.q;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public INetworkClient getNetClient() {
        if (this.t != null) {
            return this.t;
        }
        if (getInitConfig() != null && getInitConfig().getNetworkClient() != null) {
            return getInitConfig().getNetworkClient();
        }
        synchronized (this) {
            if (this.t == null) {
                this.t = new q2(this.k);
            }
        }
        return this.t;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getOpenUdid() {
        return a() ? "" : this.p.h();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public Map<String, String> getRequestHeader() {
        if (this.o == null) {
            return Collections.emptyMap();
        }
        String string = this.o.f.getString("device_token", "");
        HashMap map = new HashMap();
        map.put("x-tt-dt", string != null ? string : "");
        return map;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getSdkVersion() {
        return BuildConfig.VERSION_NAME;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getSessionId() {
        return this.q != null ? this.q.d() : "";
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getSsid() {
        return a() ? "" : this.p.j();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void getSsidGroup(Map<String, String> map) {
        String did = getDid();
        if (!TextUtils.isEmpty(did)) {
            map.put(MonitorConstants.KEY_DEVICE_ID, did);
        }
        String iid = getIid();
        if (!TextUtils.isEmpty(iid)) {
            map.put("install_id", iid);
        }
        String openUdid = getOpenUdid();
        if (!TextUtils.isEmpty(openUdid)) {
            map.put("openudid", openUdid);
        }
        String clientUdid = getClientUdid();
        if (TextUtils.isEmpty(clientUdid)) {
            return;
        }
        map.put("clientudid", clientUdid);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getUdid() {
        return a() ? "" : this.p.k();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getUserID() {
        if (b()) {
            return null;
        }
        return String.valueOf(this.q.n.f256a);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public String getUserUniqueID() {
        return a() ? "" : this.p.l();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public ViewExposureManager getViewExposureManager() {
        return this.s;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public JSONObject getViewProperties(View view) {
        if (view != null) {
            return this.f231a.get(n0.b(view));
        }
        return null;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public boolean hasStarted() {
        return this.u;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void ignoreAutoTrackClick(View view) {
        if (view == null) {
            return;
        }
        this.g.add(n0.b(view));
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void ignoreAutoTrackClickByViewType(Class<?>... clsArr) {
        if (clsArr == null) {
            return;
        }
        this.h.addAll(Arrays.asList(clsArr));
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void ignoreAutoTrackPage(Class<?>... clsArr) {
        if (clsArr == null) {
            return;
        }
        for (Class<?> cls : clsArr) {
            if (cls != null) {
                Iterator<Class<?>> it = k4.c.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (cls.isAssignableFrom(it.next())) {
                            break;
                        }
                    } else {
                        Iterator<Class<?>> it2 = k4.d.iterator();
                        while (it2.hasNext()) {
                            if (cls.isAssignableFrom(it2.next())) {
                            }
                        }
                        this.D.warn("{} is not a page class", cls);
                    }
                }
                String canonicalName = cls.getCanonicalName();
                if (!TextUtils.isEmpty(canonicalName)) {
                    this.f.add(Integer.valueOf(canonicalName.hashCode()));
                }
            }
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void init(Context context, InitConfig initConfig) {
        String str;
        ILogProcessor y0Var;
        synchronized (d.class) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (n0.c(initConfig.getAid())) {
                Log.e("AppLog", "Init failed. App id must not be empty!");
                return;
            }
            if (n0.c(initConfig.getChannel())) {
                Log.e("AppLog", "Channel must not be empty!");
                return;
            }
            if (com.bytedance.bdtracker.b.b(initConfig.getAid())) {
                Log.e("AppLog", "The app id: " + initConfig.getAid() + " has initialized already");
                return;
            }
            this.D.setAppId(initConfig.getAid());
            this.m = initConfig.getAid();
            this.n = (Application) context.getApplicationContext();
            if (this.n != null) {
                try {
                    this.G = (this.n.getApplicationInfo().flags & 2) != 0;
                } catch (Throwable unused) {
                }
                if (!this.G) {
                    a1.f206a = false;
                }
            }
            if (initConfig.isLogEnable()) {
                if (initConfig.getLogger() != null) {
                    str = this.m;
                    y0Var = new z0(initConfig.getLogger());
                } else {
                    str = this.m;
                    y0Var = new y0(this);
                }
                LogProcessorHolder.setProcessor(str, y0Var);
            }
            this.D.info("AppLog init begin...", new Object[0]);
            if (!initConfig.isMonitorEnabled() && !b2.a(initConfig) && initConfig.getUriConfig() == null) {
                initConfig.setMonitorEnabled(true);
            }
            a1.a("init_begin", (EventBus.DataFetcher) new e(this, initConfig));
            initMetaSec(context);
            if (TextUtils.isEmpty(initConfig.getSpName())) {
                initConfig.setSpName(com.bytedance.bdtracker.b.a(this, "applog_stats"));
            }
            this.o = new i1(this, this.n, initConfig);
            this.p = new k1(this, this.n, this.o);
            e();
            this.q = new c0(this, this.o, this.p, this.e);
            this.r = v.a(this.n);
            this.s = new ViewExposureManager(this);
            if (AppCrashType.hasJavaCrashType(initConfig.getTrackCrashType())) {
                k0.a();
            }
            this.l = 1;
            this.u = initConfig.autoStart();
            String str2 = this.m;
            if (!a1.a() && !n0.c("init_end")) {
                EventBus.global.get(new Object[0]).emit(a1.a("init_end"), str2);
            }
            this.D.info("AppLog init end", new Object[0]);
            if (n0.a(SimulateLaunchActivity.entryAppId, this.m)) {
                h3.a(this);
            }
            this.o.k();
            a("sdk_init", null, jElapsedRealtime);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void init(Context context, InitConfig initConfig, Activity activity) {
        init(context, initConfig);
        if (this.r == null || activity == null) {
            return;
        }
        this.r.onActivityCreated(activity, null);
        this.r.onActivityResumed(activity);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void initH5Bridge(View view, String str) {
        Class<?> clsB = n0.b("com.bytedance.applog.tracker.WebViewUtil");
        if (clsB == null) {
            this.D.warn("No WebViewUtil class, and will not initialize h5 bridge", new Object[0]);
            return;
        }
        try {
            Method declaredMethod = clsB.getDeclaredMethod("injectWebViewBridges", View.class, String.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(null, view, str);
        } catch (Throwable th) {
            this.D.error("Initialize h5 bridge failed", th, new Object[0]);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void initMetaSec(Context context) {
        if (getInitConfig() == null || getInitConfig().isMetaSecEnabled()) {
            Class<?> clsB = n0.b("com.bytedance.applog.metasec.AppLogSecHelper");
            if (clsB == null) {
                this.D.debug("No AppLogSecHelper class, and will not init", new Object[0]);
                return;
            }
            try {
                Method declaredMethod = clsB.getDeclaredMethod("init", IAppLogInstance.class, Context.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(null, this, context);
            } catch (Throwable th) {
                this.D.error("Initialize AppLogSecHelper failed", th, new Object[0]);
            }
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void initWebViewBridge(View view, String str) {
        Class<?> clsB = n0.b("com.bytedance.applog.tracker.WebViewUtil");
        if (clsB != null) {
            try {
                clsB.getMethod("injectWebViewBridges", View.class, String.class).invoke(null, view, str);
            } catch (Throwable th) {
                this.D.error("Init webview bridge failed", th, new Object[0]);
            }
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public boolean isAutoTrackClickIgnored(View view) {
        if (view == null) {
            return false;
        }
        if (this.g.contains(n0.b(view))) {
            return true;
        }
        Iterator<Class<?>> it = this.h.iterator();
        while (it.hasNext()) {
            if (it.next().isInstance(view)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public boolean isAutoTrackPageIgnored(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        String canonicalName = cls.getCanonicalName();
        if (TextUtils.isEmpty(canonicalName)) {
            return false;
        }
        return this.f.contains(Integer.valueOf(canonicalName.hashCode()));
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public boolean isBavEnabled() {
        return this.q != null && this.q.g();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public boolean isH5BridgeEnable() {
        return getInitConfig() != null && getInitConfig().isH5BridgeEnable();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public boolean isH5CollectEnable() {
        return getInitConfig() != null && getInitConfig().isH5CollectEnable();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public boolean isNewUser() {
        if (a()) {
            return false;
        }
        return this.p.e;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public boolean isPrivacyMode() {
        return this.x;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public boolean manualActivate() {
        if (b()) {
            return false;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean zA = this.q.a(false);
        a("api_usage", "manualActivate", jElapsedRealtime);
        return zA;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public EventBuilder newEvent(String str) {
        return new EventBuilder(this).setEvent(str);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void onActivityPause() {
        if (this.r != null) {
            this.r.onActivityPaused(null);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void onActivityResumed(Activity activity, int i) {
        if (this.r != null) {
            this.r.a(activity, i);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void onEventV3(String str) {
        onEventV3(str, (JSONObject) null, 0);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void onEventV3(String str, Bundle bundle) {
        onEventV3(str, bundle, 0);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void onEventV3(String str, JSONObject jSONObject) {
        onEventV3(str, jSONObject, 0);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void onMiscEvent(String str, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str) || jSONObject == null || jSONObject.length() <= 0) {
            this.D.warn("call onMiscEvent with invalid params", new Object[0]);
            return;
        }
        this.D.debug(Arrays.asList("customEvent", "miscEvent"), "logType:{} params:{} ", str, jSONObject.toString());
        try {
            jSONObject.put(MonitorCommonConstants.KEY_LOG_TYPE, str);
            receive(new m3("log_data", jSONObject));
        } catch (Throwable th) {
            this.D.error("call onMiscEvent error", th, new Object[0]);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void onPause(Context context) {
        if (context instanceof Activity) {
            onActivityPause();
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void onResume(Context context) {
        if (context instanceof Activity) {
            onActivityResumed((Activity) context, context.hashCode());
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void pauseDurationEvent(String str) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (n0.a(TextUtils.isEmpty(str), "Event name must not empty!")) {
            return;
        }
        i0 i0Var = this.i.get(str);
        if (n0.a((Object) i0Var, "No duration event with name: " + str)) {
            return;
        }
        i0Var.a(jElapsedRealtime);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void profileAppend(JSONObject jSONObject) {
        if (b() || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        try {
            if (!n0.a(jSONObject, (Class<?>[]) new Class[]{String.class, Integer.class}, (Class<?>[]) new Class[]{String.class})) {
                this.D.warn("only support String、Int、String Array！", new Object[0]);
                return;
            }
        } catch (Throwable th) {
            this.D.error("JSON handle failed", th, new Object[0]);
        }
        s4.a(this.D, jSONObject);
        this.q.b(jSONObject);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void profileIncrement(JSONObject jSONObject) {
        if (b() || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        try {
            if (!n0.a(jSONObject, (Class<?>[]) new Class[]{Integer.class}, (Class<?>[]) null)) {
                this.D.warn("only support Int param", new Object[0]);
                return;
            }
        } catch (Throwable th) {
            this.D.error("JSON handle failed", th, new Object[0]);
        }
        s4.a(this.D, jSONObject);
        this.q.c(jSONObject);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void profileSet(JSONObject jSONObject) {
        if (b() || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        s4.a(this.D, jSONObject);
        this.q.d(jSONObject);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void profileSetOnce(JSONObject jSONObject) {
        if (b() || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        s4.a(this.D, jSONObject);
        this.q.e(jSONObject);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void profileUnset(String str) {
        if (b()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str, "");
        } catch (Throwable th) {
            this.D.error("JSON handle failed", th, new Object[0]);
        }
        s4.a(this.D, jSONObject);
        this.q.f(jSONObject);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void pullAbTestConfigs() {
        pullAbTestConfigs(-1, null);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void pullAbTestConfigs(int i, IPullAbTestConfigCallback iPullAbTestConfigCallback) {
        if (this.q == null) {
            new j0().initCause(new AssertionError("Please initialize first")).printStackTrace();
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jAbs = this.q.f223a - Math.abs(jCurrentTimeMillis - this.F);
        if (jAbs < 0) {
            this.F = jCurrentTimeMillis;
            Handler handler = this.q.p;
            handler.sendMessage(handler.obtainMessage(18, i, -1, iPullAbTestConfigCallback));
        } else if (iPullAbTestConfigCallback != null) {
            iPullAbTestConfigCallback.onThrottle(jAbs);
        } else {
            this.D.warn("Pull ABTest config too frequently", new Object[0]);
        }
        a("api_usage", "pullAbTestConfigs", jElapsedRealtime);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void putCommonParams(Context context, Map<String, String> map, boolean z, Level level) {
        this.j.a(this.p != null ? this.p.e() : null, z, map, level);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void receive(j3 j3Var) {
        if (j3Var == null) {
            return;
        }
        j3Var.m = this.m;
        if (this.q == null) {
            this.e.a(j3Var);
        } else {
            this.q.a(j3Var);
        }
        a1.a("event_receive", j3Var);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void registerHeaderCustomCallback(IHeaderCustomTimelyCallback iHeaderCustomTimelyCallback) {
        this.v = iHeaderCustomTimelyCallback;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void removeAllDataObserver() {
        v0 v0Var = this.y;
        if (v0Var != null) {
            v0Var.f330a.clear();
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void removeDataObserver(IDataObserver iDataObserver) {
        v0 v0Var = this.y;
        if (v0Var != null) {
            v0Var.b(iDataObserver);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void removeEventObserver(IEventObserver iEventObserver) {
        this.c.b(iEventObserver);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void removeHeaderInfo(String str) {
        if (a()) {
            return;
        }
        this.p.d(str);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void removeOaidObserver(IOaidObserver iOaidObserver) {
        x4.b(iOaidObserver);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void removeSessionHook(ISessionObserver iSessionObserver) {
        this.b.b(iSessionObserver);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public boolean reportPhoneDetailInfo() {
        return this.p != null && this.p.r();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void resumeDurationEvent(String str) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (n0.a(TextUtils.isEmpty(str), "Event name must not empty!")) {
            return;
        }
        i0 i0Var = this.i.get(str);
        if (n0.a((Object) i0Var, "No duration event with name: " + str)) {
            return;
        }
        i0Var.b(jElapsedRealtime);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setALinkListener(IALinkListener iALinkListener) {
        this.z = iALinkListener;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setAccount(Account account) {
        if (a()) {
            return;
        }
        n1 n1VarC = this.p.i.c();
        if (!(n1VarC.f295a instanceof c4)) {
            n1VarC.b = account;
            return;
        }
        i3 i3Var = ((c4) n1VarC.f295a).c;
        if (i3Var != null) {
            i3Var.a(account);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setActiveCustomParams(IActiveCustomParamsCallback iActiveCustomParamsCallback) {
        this.A = iActiveCustomParamsCallback;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setAppContext(f fVar) {
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setAppLanguageAndRegion(String str, String str2) {
        boolean z;
        if (b()) {
            return;
        }
        c0 c0Var = this.q;
        k1 k1Var = c0Var.i;
        boolean z2 = true;
        if (k1Var.a("app_language", (Object) str)) {
            com.bytedance.bdtracker.a.a(k1Var.c.f, "app_language", str);
            z = true;
        } else {
            z = false;
        }
        k1 k1Var2 = c0Var.i;
        if (k1Var2.a("app_region", (Object) str2)) {
            com.bytedance.bdtracker.a.a(k1Var2.c.f, "app_region", str2);
        } else {
            z2 = false;
        }
        if (z || z2) {
            c0Var.a(c0Var.k);
            c0Var.a(c0Var.f);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setAppTrack(JSONObject jSONObject) {
        if (jSONObject == null || a()) {
            return;
        }
        k1 k1Var = this.p;
        if (k1Var.a("app_track", jSONObject)) {
            i1 i1Var = k1Var.c;
            com.bytedance.bdtracker.a.a(i1Var.d, "app_track", jSONObject.toString());
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setClipboardEnabled(boolean z) {
        if (b()) {
            return;
        }
        this.q.B.f260a = z;
        a1.a("update_config", (EventBus.DataFetcher) new c(z));
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setEncryptAndCompress(boolean z) {
        this.E = z;
        if (n0.d(this.m)) {
            a1.a("update_config", (EventBus.DataFetcher) new a(z));
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setEventFilterByClient(List<String> list, boolean z) {
        s0 u0Var = null;
        if (list != null && !list.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (String str : list) {
                if (!TextUtils.isEmpty(str)) {
                    hashSet.add(str);
                }
            }
            if (!hashSet.isEmpty()) {
                u0Var = z ? new u0(hashSet, null) : new t0(hashSet, null);
            }
        }
        this.w = u0Var;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setEventHandler(IEventHandler iEventHandler) {
        this.C = iEventHandler;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setExternalAbVersion(String str) {
        if (a()) {
            return;
        }
        this.p.f(str);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setExtraParams(IExtraParams iExtraParams) {
        this.j.f253a = iExtraParams;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setForbidReportPhoneDetailInfo(boolean z) {
        if (a()) {
            return;
        }
        k1 k1Var = this.p;
        k1Var.k = z;
        if (!k1Var.r()) {
            k1Var.a("sim_serial_number", (Object) null);
        }
        a1.a("update_config", (EventBus.DataFetcher) new b(z));
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setGPSLocation(float f, float f2, String str) {
        if (this.p == null) {
            this.D.warn("Please initialize first", new Object[0]);
        } else {
            this.B = new r(f, f2, str);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setGoogleAid(String str) {
        if (a()) {
            return;
        }
        k1 k1Var = this.p;
        if (k1Var.a("google_aid", (Object) str)) {
            com.bytedance.bdtracker.a.a(k1Var.c.f, "google_aid", str);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setHeaderInfo(String str, Object obj) {
        if (a() || TextUtils.isEmpty(str)) {
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put(str, obj);
        s4.a(this.D, map);
        this.p.a(map);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setLaunchFrom(int i) {
        this.l = i;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setOaidObserver(IOaidObserver iOaidObserver) {
        x4.a(iOaidObserver);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setPrivacyMode(boolean z) {
        this.x = z;
        if (n0.d(this.m)) {
            a1.a("update_config", (EventBus.DataFetcher) new C0150d(z));
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setPullAbTestConfigsThrottleMills(Long l) {
        if (this.q != null) {
            this.q.a(l);
        } else {
            new j0().initCause(new AssertionError("Please initialize first")).printStackTrace();
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setRangersEventVerifyEnable(boolean z, String str) {
        if (b()) {
            return;
        }
        c0 c0Var = this.q;
        c0Var.j.removeMessages(15);
        c0Var.j.obtainMessage(15, new Object[]{Boolean.valueOf(z), str}).sendToTarget();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setTouchPoint(String str) {
        setHeaderInfo("touch_point", str);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setTracerData(JSONObject jSONObject) {
        if (a()) {
            return;
        }
        this.p.a("tracer_data", jSONObject);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setUriRuntime(UriConfig uriConfig) {
        if (b()) {
            return;
        }
        c0 c0Var = this.q;
        c0Var.o = uriConfig;
        c0Var.a(c0Var.k);
        if (c0Var.e.c.isAutoActive()) {
            c0Var.a(true);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setUserAgent(String str) {
        if (a()) {
            return;
        }
        k1 k1Var = this.p;
        if (k1Var.a("user_agent", (Object) str)) {
            com.bytedance.bdtracker.a.a(k1Var.c.f, "user_agent", str);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setUserID(long j) {
        if (b()) {
            return;
        }
        this.q.n.f256a = j;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.applog.IAppLogInstance
    public void setUserUniqueID(String str) {
        if (this.p != null) {
            setUserUniqueID(str, this.p.m());
            return;
        }
        h4<String> h4Var = this.H;
        h4Var.f259a = str;
        h4Var.b = true;
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setViewId(Dialog dialog, String str) {
        if (dialog == null || dialog.getWindow() == null) {
            return;
        }
        dialog.getWindow().getDecorView().setTag(R.id.applog_tag_view_id, str);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setViewId(View view, String str) {
        if (view == null) {
            return;
        }
        view.setTag(R.id.applog_tag_view_id, str);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setViewId(Object obj, String str) {
        if (obj == null) {
            return;
        }
        if (!n0.a(obj, "android.support.v7.app.AlertDialog", "androidx.appcompat.app.AlertDialog")) {
            this.D.warn("Only support AlertDialog view", new Object[0]);
            return;
        }
        try {
            Window window = (Window) obj.getClass().getMethod("getWindow", null).invoke(obj, null);
            if (window != null) {
                window.getDecorView().setTag(R.id.applog_tag_view_id, str);
            }
        } catch (NoSuchMethodException e) {
            this.D.error("Not found getWindow method in alertDialog", e, new Object[0]);
        } catch (Throwable th) {
            this.D.error("Cannot set viewId for alertDialog", th, new Object[0]);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setViewProperties(View view, JSONObject jSONObject) {
        if (view == null || jSONObject == null) {
            return;
        }
        this.f231a.put(n0.b(view), jSONObject);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void start() {
        if (this.u) {
            return;
        }
        this.u = true;
        c0 c0Var = this.q;
        if (c0Var.r) {
            return;
        }
        c0Var.r = true;
        c0Var.p.sendEmptyMessage(1);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void startDurationEvent(String str) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (n0.a(TextUtils.isEmpty(str), "Event name must not empty!")) {
            return;
        }
        i0 i0Var = this.i.get(str);
        if (i0Var == null) {
            i0Var = new i0(this.D, str);
            this.i.put(str, i0Var);
        }
        i0Var.c(jElapsedRealtime);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void startSimulator(String str) {
        if (b()) {
            return;
        }
        c0 c0Var = this.q;
        a0 a0Var = c0Var.s;
        if (a0Var != null) {
            a0Var.d = true;
        }
        Class<?> clsB = n0.b("com.bytedance.applog.picker.DomSender");
        if (clsB != null) {
            try {
                c0Var.s = (a0) clsB.getConstructor(c0.class, String.class).newInstance(c0Var, str);
                c0Var.j.sendMessage(c0Var.j.obtainMessage(9, c0Var.s));
            } catch (Throwable th) {
                c0Var.d.D.error("Start simulator failed.", th, new Object[0]);
            }
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void stopDurationEvent(String str, JSONObject jSONObject) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (n0.a(TextUtils.isEmpty(str), "Event name must not empty!")) {
            return;
        }
        i0 i0Var = this.i.get(str);
        if (n0.a((Object) i0Var, "No duration event with name: " + str)) {
            return;
        }
        long j = 0;
        if (jElapsedRealtime <= 0) {
            IAppLogLogger iAppLogLogger = i0Var.f261a;
            if (iAppLogLogger != null) {
                iAppLogLogger.warn(4, "End at illegal time: " + jElapsedRealtime, new Object[0]);
            }
        } else {
            i0Var.a(jElapsedRealtime);
            IAppLogLogger iAppLogLogger2 = i0Var.f261a;
            if (iAppLogLogger2 != null) {
                iAppLogLogger2.debug(4, "[DurationEvent:{}] End[ at:{} and duration is {}ms", i0Var.b, Long.valueOf(jElapsedRealtime), Long.valueOf(i0Var.d));
            }
            j = i0Var.d;
        }
        JSONObject jSONObject2 = new JSONObject();
        n0.b(jSONObject, jSONObject2);
        try {
            jSONObject2.put("$event_duration", j);
        } catch (Throwable th) {
            this.D.error("JSON handle failed", th, new Object[0]);
        }
        receive(new q3(str, jSONObject2));
        this.i.remove(str);
    }

    public String toString() {
        StringBuilder sbA = com.bytedance.bdtracker.a.a("AppLogInstance{id:");
        sbA.append(K.get());
        sbA.append(";appId:");
        sbA.append(this.m);
        sbA.append("}@");
        sbA.append(hashCode());
        return sbA.toString();
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void trackClick(View view) {
        trackClick(view, null);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void trackClick(View view, JSONObject jSONObject) {
        l3 l3VarA = n0.a(view, false);
        if (l3VarA != null && jSONObject != null) {
            l3VarA.o = jSONObject;
        }
        receive(l3VarA);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void trackPage(Activity activity) {
        trackPage(activity, (JSONObject) null);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void trackPage(Activity activity, JSONObject jSONObject) {
        a(activity, jSONObject);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void trackPage(Object obj) {
        trackPage(obj, (JSONObject) null);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void trackPage(Object obj, JSONObject jSONObject) {
        a(obj, jSONObject);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void userProfileSetOnce(JSONObject jSONObject, UserProfileCallback userProfileCallback) {
        if (b()) {
            return;
        }
        c0 c0Var = this.q;
        if (c0Var.j != null) {
            a3.a(c0Var, 0, jSONObject, userProfileCallback, c0Var.j, false);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void userProfileSync(JSONObject jSONObject, UserProfileCallback userProfileCallback) {
        if (b()) {
            return;
        }
        c0 c0Var = this.q;
        if (c0Var.j != null) {
            a3.a(c0Var, 1, jSONObject, userProfileCallback, c0Var.j, false);
        }
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void onEventV3(String str, Bundle bundle, int i) {
        JSONObject jSONObject = null;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        for (String str2 : bundle.keySet()) {
                            jSONObject2.put(str2, bundle.get(str2));
                        }
                        jSONObject = jSONObject2;
                    } catch (Throwable th) {
                        th = th;
                        jSONObject = jSONObject2;
                        this.D.error("Parse event params failed", th, new Object[0]);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        onEventV3(str, jSONObject, i);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void setHeaderInfo(HashMap<String, Object> map) {
        if (a()) {
            return;
        }
        s4.a(this.D, map);
        this.p.a(map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.applog.IAppLogInstance
    public void setUserUniqueID(String str, String str2) {
        if (this.p == null) {
            h4<String> h4Var = this.H;
            h4Var.f259a = str;
            h4Var.b = true;
            h4<String> h4Var2 = this.I;
            h4Var2.f259a = str2;
            h4Var2.b = true;
            return;
        }
        if (b()) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        c0 c0Var = this.q;
        if (!n0.a(str, c0Var.i.l())) {
            c0Var.a((String[]) null, false);
            ArrayList arrayList = new ArrayList();
            long jCurrentTimeMillis = System.currentTimeMillis();
            t3 t3VarA = v.a();
            boolean zD = n0.d(c0Var.n.a());
            if (zD && t3VarA != null) {
                t3VarA = (t3) t3VarA.m6355clone();
                t3VarA.m = c0Var.d.m;
                long j = jCurrentTimeMillis - t3VarA.c;
                t3VarA.a(jCurrentTimeMillis);
                if (j < 0) {
                    j = 0;
                }
                t3VarA.s = j;
                t3VarA.B = c0Var.n.b();
                c0Var.n.a(c0Var.d, t3VarA);
                arrayList.add(t3VarA);
            }
            c0Var.a(str, str2);
            if (zD && t3VarA != null) {
                t3 t3Var = (t3) t3VarA.m6355clone();
                t3Var.a(jCurrentTimeMillis + 1);
                t3Var.s = -1L;
                c0Var.n.a(c0Var.d, t3Var, arrayList, true).v = c0Var.n.b();
                c0Var.n.a(c0Var.d, t3Var);
                arrayList.add(t3Var);
            }
            if (!arrayList.isEmpty()) {
                c0Var.c().c.a(arrayList);
            }
            c0Var.a(c0Var.l);
        }
        a("api_usage", "setUserUniqueID", jElapsedRealtime);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void onEventV3(String str, JSONObject jSONObject, int i) {
        if (TextUtils.isEmpty(str)) {
            this.D.error("event name is empty", new Object[0]);
            return;
        }
        this.D.debug(Arrays.asList("customEvent", "eventV3"), "event:{} type:{} params:{} ", str, Integer.valueOf(i), jSONObject != null ? jSONObject.toString() : null);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        s4.a(this.D, str, jSONObject);
        receive(new q3(this.m, str, false, jSONObject != null ? jSONObject.toString() : null, i));
        z1 monitor = getMonitor();
        if (monitor == null) {
            return;
        }
        long jElapsedRealtime2 = SystemClock.elapsedRealtime();
        k2 k2Var = new k2();
        k2Var.f276a = "onEventV3";
        k2Var.b = jElapsedRealtime2 - jElapsedRealtime;
        ((d2) monitor).a(k2Var);
    }

    @Override // com.bytedance.applog.IAppLogInstance
    public void receive(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        for (String str : strArr) {
        }
        if (this.q == null) {
            this.e.a(strArr);
            return;
        }
        c0 c0Var = this.q;
        c0Var.p.removeMessages(4);
        c0Var.p.obtainMessage(4, strArr).sendToTarget();
    }
}
