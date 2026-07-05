package cn.thinkingdata.android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import cn.thinkingdata.android.ThinkingAnalyticsSDK;
import cn.thinkingdata.android.utils.TDLog;
import cn.thinkingdata.android.utils.r;
import com.sqwan.common.route.FunctionRouter;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class o implements Application.ActivityLifecycleCallbacks {
    private final ThinkingAnalyticsSDK c;
    private d e;
    private WeakReference<Activity> f;
    private boolean a = false;
    private final Object b = new Object();
    private volatile Boolean d = true;
    private final List<WeakReference<Activity>> g = new ArrayList();
    private boolean h = false;

    class a extends TimerTask {
        a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (o.this.d.booleanValue()) {
                o.this.d = false;
                JSONObject jSONObject = new JSONObject();
                try {
                    if (!TDPresetProperties.disableList.contains("#resume_from_background")) {
                        jSONObject.put("#resume_from_background", o.this.a);
                    }
                    if (!TDPresetProperties.disableList.contains("#start_reason")) {
                        String strA = o.this.a();
                        if (!strA.equals(new JSONObject().toString())) {
                            jSONObject.put("#start_reason", strA);
                        }
                    }
                } catch (Exception unused) {
                } catch (Throwable th) {
                    o.this.c.autoTrack("ta_app_start", jSONObject);
                    o.this.c.flush();
                    o.this.h = true;
                    throw th;
                }
                o.this.c.autoTrack("ta_app_start", jSONObject);
                o.this.c.flush();
                o.this.h = true;
            }
        }
    }

    o(ThinkingAnalyticsSDK thinkingAnalyticsSDK, String str) {
        this.c = thinkingAnalyticsSDK;
    }

    public static JSONArray a(Object obj) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (!obj.getClass().isArray()) {
            throw new JSONException("Not a primitive array: " + obj.getClass());
        }
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            jSONArray.put(b(Array.get(obj, i)));
        }
        return jSONArray;
    }

    private void a(Activity activity, cn.thinkingdata.android.utils.f fVar) {
        if (this.d.booleanValue() || this.a) {
            if (this.c.isAutoTrackEnabled()) {
                try {
                    if (!this.c.isAutoTrackEventTypeIgnored(ThinkingAnalyticsSDK.AutoTrackEventType.APP_START)) {
                        this.d = false;
                        JSONObject jSONObject = new JSONObject();
                        if (!TDPresetProperties.disableList.contains("#resume_from_background")) {
                            jSONObject.put("#resume_from_background", this.a);
                        }
                        if (!TDPresetProperties.disableList.contains("#start_reason")) {
                            String strA = a();
                            if (!strA.equals(new JSONObject().toString())) {
                                jSONObject.put("#start_reason", strA);
                            }
                        }
                        r.a(jSONObject, activity);
                        if (this.e != null) {
                            double d = Double.parseDouble(this.e.b());
                            if (d > 0.0d && !TDPresetProperties.disableList.contains("#background_duration")) {
                                jSONObject.put("#background_duration", d);
                            }
                        }
                        if (fVar == null) {
                            this.c.autoTrack("ta_app_start", jSONObject);
                        } else if (!this.c.hasDisabled()) {
                            JSONObject autoTrackStartProperties = this.c.getAutoTrackStartProperties();
                            r.a(jSONObject, autoTrackStartProperties, this.c.mConfig.getDefaultTimeZone());
                            cn.thinkingdata.android.a aVar = new cn.thinkingdata.android.a(this.c, cn.thinkingdata.android.utils.m.TRACK, autoTrackStartProperties, fVar);
                            aVar.a = "ta_app_start";
                            this.c.trackInternal(aVar);
                            this.h = true;
                        }
                    }
                    if (fVar == null && !this.c.isAutoTrackEventTypeIgnored(ThinkingAnalyticsSDK.AutoTrackEventType.APP_END)) {
                        this.c.timeEvent("ta_app_end");
                        this.h = true;
                    }
                } catch (Exception e) {
                    TDLog.i("ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks", e);
                }
            }
            try {
                this.c.appBecomeActive();
                this.e = null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private boolean a(Activity activity, boolean z) {
        synchronized (this.b) {
            Iterator<WeakReference<Activity>> it = this.g.iterator();
            while (it.hasNext()) {
                if (it.next().get() == activity) {
                    if (z) {
                        it.remove();
                    }
                    return false;
                }
            }
            return true;
        }
    }

    public static Object b(Object obj) {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject) || obj.equals(JSONObject.NULL)) {
            return obj;
        }
        if (obj instanceof Collection) {
            return new JSONArray((Collection) obj);
        }
        if (obj.getClass().isArray()) {
            return a(obj);
        }
        if (obj instanceof Map) {
            return new JSONObject((Map) obj);
        }
        if (!(obj instanceof Boolean) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Double) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Short) && !(obj instanceof String)) {
            if (obj.getClass().getPackage().getName().startsWith("java.")) {
                return obj.toString();
            }
            return null;
        }
        return obj;
    }

    String a() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        WeakReference<Activity> weakReference = this.f;
        if (weakReference != null) {
            try {
                Intent intent = weakReference.get().getIntent();
                if (intent != null) {
                    String dataString = intent.getDataString();
                    if (!TextUtils.isEmpty(dataString)) {
                        jSONObject.put("url", dataString);
                    }
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        for (String str : extras.keySet()) {
                            Object obj = extras.get(str);
                            Object objB = b(obj);
                            if (objB != null && objB != JSONObject.NULL) {
                                jSONObject2.put(str, b(obj));
                            }
                        }
                        jSONObject.put(FunctionRouter.KEY_DATA, jSONObject2);
                    }
                }
            } catch (Exception unused) {
                return jSONObject.toString();
            }
        }
        return jSONObject.toString();
    }

    void a(JSONObject jSONObject) {
        this.c.autoTrack("ta_app_crash", jSONObject);
        this.c.autoTrack("ta_app_end", new JSONObject());
        this.h = false;
        this.c.flush();
    }

    public void a(boolean z) {
        this.h = z;
    }

    boolean a(Context context) {
        try {
            Resources resources = context.getResources();
            return resources.getBoolean(resources.getIdentifier("TAEnableBackgroundStartEvent", "bool", context.getPackageName()));
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004b A[Catch: all -> 0x004d, DONT_GENERATE, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x000b, B:8:0x0013, B:10:0x001d, B:12:0x0029, B:14:0x0035, B:17:0x0046, B:18:0x004b), top: B:25:0x0003, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void b() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.b
            monitor-enter(r0)
            java.lang.Boolean r1 = r5.d     // Catch: java.lang.Throwable -> L4d
            boolean r1 = r1.booleanValue()     // Catch: java.lang.Throwable -> L4d
            if (r1 == 0) goto L4b
            cn.thinkingdata.android.ThinkingAnalyticsSDK r1 = r5.c     // Catch: java.lang.Throwable -> L4d
            boolean r1 = r1.isAutoTrackEnabled()     // Catch: java.lang.Throwable -> L4d
            if (r1 == 0) goto L4b
            cn.thinkingdata.android.ThinkingAnalyticsSDK r1 = r5.c     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            cn.thinkingdata.android.ThinkingAnalyticsSDK$AutoTrackEventType r2 = cn.thinkingdata.android.ThinkingAnalyticsSDK.AutoTrackEventType.APP_START     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            boolean r1 = r1.isAutoTrackEventTypeIgnored(r2)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            if (r1 != 0) goto L4b
            cn.thinkingdata.android.ThinkingAnalyticsSDK r1 = r5.c     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            cn.thinkingdata.android.TDConfig r1 = r1.mConfig     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            android.content.Context r1 = r1.mContext     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            boolean r1 = cn.thinkingdata.android.utils.r.e(r1)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            if (r1 != 0) goto L35
            cn.thinkingdata.android.ThinkingAnalyticsSDK r1 = r5.c     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            cn.thinkingdata.android.TDConfig r1 = r1.mConfig     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            android.content.Context r1 = r1.mContext     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            boolean r1 = r5.a(r1)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            if (r1 == 0) goto L4b
        L35:
            cn.thinkingdata.android.o$a r1 = new cn.thinkingdata.android.o$a     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            r1.<init>()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            java.util.Timer r2 = new java.util.Timer     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            r2.<init>()     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            r3 = 100
            r2.schedule(r1, r3)     // Catch: java.lang.Exception -> L45 java.lang.Throwable -> L4d
            goto L4b
        L45:
            r1 = move-exception
            java.lang.String r2 = "ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks"
            cn.thinkingdata.android.utils.TDLog.i(r2, r1)     // Catch: java.lang.Throwable -> L4d
        L4b:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4d
            return
        L4d:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L4d
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.o.b():void");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        TDLog.i("ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks", "onActivityCreated");
        this.f = new WeakReference<>(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        synchronized (this.b) {
            if (a(activity, false)) {
                TDLog.i("ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks", "onActivityPaused: the SDK was initialized after the onActivityStart of " + activity);
                this.g.add(new WeakReference<>(activity));
                if (this.g.size() == 1) {
                    a(activity, this.c.getAutoTrackStartTime());
                    this.c.flush();
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        String strUrl;
        ThinkingAnalyticsSDK thinkingAnalyticsSDK;
        synchronized (this.b) {
            if (a(activity, false)) {
                TDLog.i("ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks", "onActivityResumed: the SDK was initialized after the onActivityStart of " + activity);
                this.g.add(new WeakReference<>(activity));
                if (this.g.size() == 1) {
                    a(activity, this.c.getAutoTrackStartTime());
                    this.c.flush();
                }
            }
        }
        try {
            boolean z = !this.c.isActivityAutoTrackAppViewScreenIgnored(activity.getClass());
            if (this.c.isAutoTrackEnabled() && z && !this.c.isAutoTrackEventTypeIgnored(ThinkingAnalyticsSDK.AutoTrackEventType.APP_VIEW_SCREEN)) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (!TDPresetProperties.disableList.contains("#screen_name")) {
                        jSONObject.put("#screen_name", activity.getClass().getCanonicalName());
                    }
                    r.a(jSONObject, activity);
                    if (activity instanceof ScreenAutoTracker) {
                        ScreenAutoTracker screenAutoTracker = (ScreenAutoTracker) activity;
                        strUrl = screenAutoTracker.getScreenUrl();
                        JSONObject trackProperties = screenAutoTracker.getTrackProperties();
                        if (trackProperties == null || !cn.thinkingdata.android.utils.h.a(trackProperties)) {
                            TDLog.d("ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks", "invalid properties: " + trackProperties);
                        } else {
                            r.a(trackProperties, jSONObject, this.c.mConfig.getDefaultTimeZone());
                        }
                        thinkingAnalyticsSDK = this.c;
                    } else {
                        ThinkingDataAutoTrackAppViewScreenUrl thinkingDataAutoTrackAppViewScreenUrl = (ThinkingDataAutoTrackAppViewScreenUrl) activity.getClass().getAnnotation(ThinkingDataAutoTrackAppViewScreenUrl.class);
                        if (thinkingDataAutoTrackAppViewScreenUrl == null || !(TextUtils.isEmpty(thinkingDataAutoTrackAppViewScreenUrl.appId()) || this.c.getToken().equals(thinkingDataAutoTrackAppViewScreenUrl.appId()))) {
                            if (this.c.isIgnoreAppViewInExtPackage()) {
                                return;
                            }
                            this.c.autoTrack("ta_app_view", jSONObject);
                            return;
                        } else {
                            strUrl = thinkingDataAutoTrackAppViewScreenUrl.url();
                            if (TextUtils.isEmpty(strUrl)) {
                                strUrl = activity.getClass().getCanonicalName();
                            }
                            thinkingAnalyticsSDK = this.c;
                        }
                    }
                    thinkingAnalyticsSDK.trackViewScreenInternal(strUrl, jSONObject);
                } catch (Exception e) {
                    TDLog.i("ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks", e);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        TDLog.i("ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks", "onActivityStarted");
        this.f = new WeakReference<>(activity);
        try {
            synchronized (this.b) {
                if (this.g.size() == 0) {
                    a(activity, (cn.thinkingdata.android.utils.f) null);
                }
                if (a(activity, false)) {
                    this.g.add(new WeakReference<>(activity));
                } else {
                    TDLog.w("ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks", "Unexpected state. The activity might not be stopped correctly: " + activity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        ThinkingAnalyticsSDK thinkingAnalyticsSDK;
        String str;
        TDLog.i("ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks", "onActivityStopped");
        try {
            synchronized (this.b) {
                if (a(activity, true)) {
                    TDLog.i("ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks", "onActivityStopped: the SDK might be initialized after the onActivityStart of " + activity);
                    return;
                }
                if (this.g.size() == 0) {
                    this.f = null;
                    if (this.h) {
                        try {
                            this.c.appEnterBackground();
                            this.a = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (this.c.isAutoTrackEnabled()) {
                            JSONObject jSONObject = new JSONObject();
                            if (!this.c.isAutoTrackEventTypeIgnored(ThinkingAnalyticsSDK.AutoTrackEventType.APP_END)) {
                                try {
                                    try {
                                        r.a(jSONObject, activity);
                                        thinkingAnalyticsSDK = this.c;
                                        str = "ta_app_end";
                                    } catch (Exception e2) {
                                        TDLog.i("ThinkingAnalytics.ThinkingDataActivityLifecycleCallbacks", e2);
                                        thinkingAnalyticsSDK = this.c;
                                        str = "ta_app_end";
                                    }
                                    thinkingAnalyticsSDK.autoTrack(str, jSONObject);
                                    this.h = false;
                                } catch (Throwable th) {
                                    this.c.autoTrack("ta_app_end", jSONObject);
                                    this.h = false;
                                    throw th;
                                }
                            }
                        }
                        try {
                            this.e = new d(TimeUnit.SECONDS);
                            this.c.flush();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }
}
