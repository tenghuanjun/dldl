package cn.thinkingdata.android;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import cn.thinkingdata.android.aop.push.TAPushUtils;
import cn.thinkingdata.android.utils.TDLog;
import cn.thinkingdata.android.utils.r;
import com.sqwan.common.track.SqTrackNetKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ThinkingAnalyticsSDK implements e {
    static final String TAG = "ThinkingAnalyticsSDK";
    private boolean mAutoTrack;
    private AutoTrackEventListener mAutoTrackEventListener;
    private List<AutoTrackEventType> mAutoTrackEventTypeList;
    private List<Integer> mAutoTrackIgnoredActivities;
    private JSONObject mAutoTrackStartProperties;
    private cn.thinkingdata.android.utils.f mAutoTrackStartTime;
    public cn.thinkingdata.android.utils.b mCalibratedTimeManager;
    TDConfig mConfig;
    private DynamicSuperPropertiesTracker mDynamicSuperPropertiesTracker;
    private final boolean mEnableTrackOldData;
    private String mLastScreenUrl;
    private o mLifecycleCallbacks;
    protected final cn.thinkingdata.android.b mMessages;
    private cn.thinkingdata.android.q.b mStorageManager;
    private final k mSystemInformation;
    private boolean mTrackCrash;
    private boolean mTrackFragmentAppViewScreen;
    final Map<String, d> mTrackTimer;
    private final p mUserOperationHandler;
    private static final Map<Context, Map<String, ThinkingAnalyticsSDK>> sInstanceMap = new HashMap();
    private static final Map<Context, List<String>> sAppFirstInstallationMap = new HashMap();
    private boolean isFromSubProcess = false;
    private boolean mIgnoreAppViewInExtPackage = false;
    private List<Class> mIgnoredViewTypeList = new ArrayList();
    private final JSONObject mAutoTrackEventProperties = new JSONObject();

    public interface AutoTrackEventListener {
        JSONObject eventCallback(AutoTrackEventType autoTrackEventType, JSONObject jSONObject);
    }

    public enum AutoTrackEventType {
        APP_START("ta_app_start"),
        APP_END("ta_app_end"),
        APP_CLICK("ta_app_click"),
        APP_VIEW_SCREEN("ta_app_view"),
        APP_CRASH("ta_app_crash"),
        APP_INSTALL("ta_app_install");

        private final String eventName;

        AutoTrackEventType(String str) {
            this.eventName = str;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public static AutoTrackEventType autoTrackEventTypeFromEventName(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            byte b = -1;
            switch (str.hashCode()) {
                case -1123498325:
                    if (str.equals("ta_app_install")) {
                        b = 5;
                    }
                    break;
                case -78288232:
                    if (str.equals("ta_app_click")) {
                        b = 2;
                    }
                    break;
                case -78116681:
                    if (str.equals("ta_app_crash")) {
                        b = 4;
                    }
                    break;
                case -63280782:
                    if (str.equals("ta_app_start")) {
                        b = 0;
                    }
                    break;
                case 1014444523:
                    if (str.equals("ta_app_end")) {
                        b = 1;
                    }
                    break;
                case 1383510933:
                    if (str.equals("ta_app_view")) {
                        b = 3;
                    }
                    break;
            }
            if (b == 0) {
                return APP_START;
            }
            if (b == 1) {
                return APP_END;
            }
            if (b == 2) {
                return APP_CLICK;
            }
            if (b == 3) {
                return APP_VIEW_SCREEN;
            }
            if (b == 4) {
                return APP_CRASH;
            }
            if (b != 5) {
                return null;
            }
            return APP_INSTALL;
        }

        String getEventName() {
            return this.eventName;
        }
    }

    public interface DynamicSuperPropertiesTracker {
        JSONObject getDynamicSuperProperties();
    }

    public enum TATrackStatus {
        PAUSE,
        STOP,
        SAVE_ONLY,
        NORMAL
    }

    public enum ThinkingdataNetworkType {
        NETWORKTYPE_DEFAULT,
        NETWORKTYPE_WIFI,
        NETWORKTYPE_ALL
    }

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[TATrackStatus.values().length];
            a = iArr;
            try {
                iArr[TATrackStatus.PAUSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[TATrackStatus.STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[TATrackStatus.SAVE_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[TATrackStatus.NORMAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public interface b {
        void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK);
    }

    ThinkingAnalyticsSDK(TDConfig tDConfig, boolean... zArr) {
        this.mConfig = tDConfig;
        if (!TDPresetProperties.disableList.contains("#fps")) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            r.e();
        }
        this.mCalibratedTimeManager = new cn.thinkingdata.android.utils.b(tDConfig);
        this.mUserOperationHandler = new p(this, tDConfig);
        if (zArr.length > 0 && zArr[0]) {
            this.mEnableTrackOldData = false;
            this.mTrackTimer = new HashMap();
            this.mSystemInformation = k.a(tDConfig.mContext, tDConfig.getDefaultTimeZone());
            this.mMessages = getDataHandleInstance(tDConfig.mContext);
            return;
        }
        this.mEnableTrackOldData = tDConfig.trackOldData() && !isOldDataTracked();
        this.mStorageManager = new cn.thinkingdata.android.q.b(tDConfig.mContext, tDConfig.getName());
        this.mSystemInformation = k.a(tDConfig.mContext, tDConfig.getDefaultTimeZone());
        cn.thinkingdata.android.b dataHandleInstance = getDataHandleInstance(tDConfig.mContext);
        this.mMessages = dataHandleInstance;
        dataHandleInstance.a(getToken(), this.mStorageManager.g());
        if (tDConfig.mEnableEncrypt) {
            cn.thinkingdata.android.encrypt.e.a(tDConfig.getName(), tDConfig);
        }
        if (this.mEnableTrackOldData) {
            this.mMessages.c(tDConfig.getName());
        }
        this.mTrackTimer = new HashMap();
        this.mAutoTrackIgnoredActivities = new ArrayList();
        this.mAutoTrackEventTypeList = new ArrayList();
        this.mLifecycleCallbacks = new o(this, this.mConfig.getMainProcessName());
        if (Build.VERSION.SDK_INT >= 14) {
            ((Application) tDConfig.mContext.getApplicationContext()).registerActivityLifecycleCallbacks(this.mLifecycleCallbacks);
        }
        if (!tDConfig.isNormal() || r.d()) {
            enableTrackLog(true);
        }
        cn.thinkingdata.android.r.f.a(tDConfig.mContext);
        if (tDConfig.isEnableMutiprocess() && r.f(tDConfig.mContext)) {
            TDReceiver.a(tDConfig.mContext);
        }
        TAPushUtils.clearPushEvent(this);
        TDLog.i(TAG, String.format("Thinking Analytics SDK %s instance initialized successfully with mode: %s, APP ID ends with: %s, server url: %s, device ID: %s", TDConfig.VERSION, tDConfig.getMode().name(), r.a(tDConfig.mToken, 4), tDConfig.getServerUrl(), getDeviceId()));
    }

    static void addInstance(ThinkingAnalyticsSDK thinkingAnalyticsSDK, Context context, String str) {
        synchronized (sInstanceMap) {
            Map<String, ThinkingAnalyticsSDK> map = sInstanceMap.get(context);
            if (map == null) {
                map = new HashMap<>();
                sInstanceMap.put(context, map);
            }
            map.put(str, thinkingAnalyticsSDK);
        }
    }

    public static void allInstances(b bVar) {
        synchronized (sInstanceMap) {
            Iterator<Map<String, ThinkingAnalyticsSDK>> it = sInstanceMap.values().iterator();
            while (it.hasNext()) {
                Iterator<ThinkingAnalyticsSDK> it2 = it.next().values().iterator();
                while (it2.hasNext()) {
                    bVar.process(it2.next());
                }
            }
        }
    }

    public static void calibrateTime(long j) {
        cn.thinkingdata.android.utils.b.a(j);
    }

    public static void calibrateTimeWithNtp(String... strArr) {
        cn.thinkingdata.android.utils.b.a(strArr);
    }

    public static void enableTrackLog(boolean z) {
        TDLog.setEnableLog(z);
    }

    public static cn.thinkingdata.android.utils.e getCalibratedTime() {
        return cn.thinkingdata.android.utils.b.b();
    }

    private String getIdentifyID() {
        return this.mStorageManager.e();
    }

    static Map<String, ThinkingAnalyticsSDK> getInstanceMap(Context context) {
        return sInstanceMap.get(context);
    }

    public static String getLocalRegion() {
        return Locale.getDefault().getCountry();
    }

    private static boolean isOldDataTracked() {
        synchronized (sInstanceMap) {
            if (sInstanceMap.size() > 0) {
                Iterator<Map<String, ThinkingAnalyticsSDK>> it = sInstanceMap.values().iterator();
                while (it.hasNext()) {
                    Iterator<ThinkingAnalyticsSDK> it2 = it.next().values().iterator();
                    while (it2.hasNext()) {
                        if (it2.next().mEnableTrackOldData) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    private JSONObject obtainDefaultEventProperties(String str) {
        d dVar;
        JSONObject dynamicSuperProperties;
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObject = new JSONObject();
        try {
            r.a(new JSONObject(this.mSystemInformation.d()), jSONObject, this.mConfig.getDefaultTimeZone());
            if (!TextUtils.isEmpty(this.mSystemInformation.b())) {
                jSONObject.put("#app_version", this.mSystemInformation.b());
            }
            if (!TDPresetProperties.disableList.contains("#fps")) {
                jSONObject.put("#fps", r.a());
            }
            r.a(getSuperProperties(), jSONObject, this.mConfig.getDefaultTimeZone());
            if (!this.isFromSubProcess && (jSONObjectOptJSONObject = getAutoTrackProperties().optJSONObject(str)) != null) {
                r.a(jSONObjectOptJSONObject, jSONObject, this.mConfig.getDefaultTimeZone());
            }
            try {
                if (this.mDynamicSuperPropertiesTracker != null && (dynamicSuperProperties = this.mDynamicSuperPropertiesTracker.getDynamicSuperProperties()) != null && cn.thinkingdata.android.utils.h.a(dynamicSuperProperties)) {
                    r.a(dynamicSuperProperties, jSONObject, this.mConfig.getDefaultTimeZone());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!this.isFromSubProcess) {
                synchronized (this.mTrackTimer) {
                    dVar = this.mTrackTimer.get(str);
                    this.mTrackTimer.remove(str);
                }
                if (dVar != null) {
                    try {
                        Double dValueOf = Double.valueOf(dVar.b());
                        if (dValueOf.doubleValue() > 0.0d && !TDPresetProperties.disableList.contains("#duration")) {
                            jSONObject.put("#duration", dValueOf);
                        }
                        Double dValueOf2 = Double.valueOf(dVar.a());
                        if (dValueOf2.doubleValue() > 0.0d && !str.equals("ta_app_end") && !TDPresetProperties.disableList.contains("#background_duration")) {
                            jSONObject.put("#background_duration", dValueOf2);
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (!TDPresetProperties.disableList.contains("#network_type")) {
                jSONObject.put("#network_type", this.mSystemInformation.c());
            }
            if (!TDPresetProperties.disableList.contains("#ram")) {
                jSONObject.put("#ram", this.mSystemInformation.b(this.mConfig.mContext));
            }
            if (!TDPresetProperties.disableList.contains("#disk")) {
                jSONObject.put("#disk", this.mSystemInformation.a(this.mConfig.mContext, false));
            }
            if (!TDPresetProperties.disableList.contains("#device_type")) {
                jSONObject.put("#device_type", r.c(this.mConfig.mContext));
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public static void setCustomerLibInfo(String str, String str2) {
        k.a(str, str2);
    }

    public static ThinkingAnalyticsSDK sharedInstance(Context context, String str) {
        return sharedInstance(context, str, null, false);
    }

    public static ThinkingAnalyticsSDK sharedInstance(Context context, String str, String str2) {
        return sharedInstance(context, str, str2, true);
    }

    public static ThinkingAnalyticsSDK sharedInstance(Context context, String str, String str2, boolean z) {
        String str3;
        if (context == null) {
            str3 = "App context is required to get SDK instance.";
        } else if (TextUtils.isEmpty(str)) {
            str3 = "APP ID is required to get SDK instance.";
        } else {
            try {
                TDConfig tDConfig = TDConfig.getInstance(context, str, str2);
                tDConfig.setTrackOldData(z);
                return sharedInstance(tDConfig);
            } catch (IllegalArgumentException unused) {
                str3 = "Cannot get valid TDConfig instance. Returning null";
            }
        }
        TDLog.w(TAG, str3);
        return null;
    }

    public static ThinkingAnalyticsSDK sharedInstance(TDConfig tDConfig) {
        ThinkingAnalyticsSDK thinkingAnalyticsSDK;
        if (tDConfig == null) {
            TDLog.w(TAG, "Cannot initial SDK instance with null config instance.");
            return null;
        }
        synchronized (sInstanceMap) {
            Map<String, ThinkingAnalyticsSDK> map = sInstanceMap.get(tDConfig.mContext);
            if (map == null) {
                map = new HashMap<>();
                sInstanceMap.put(tDConfig.mContext, map);
                k kVarA = k.a(tDConfig.mContext, tDConfig.getDefaultTimeZone());
                long jE = kVarA.e();
                long jLongValue = cn.thinkingdata.android.q.e.a(tDConfig.mContext).b().longValue();
                boolean z = jLongValue > 0 && jE <= jLongValue;
                if (!z) {
                    cn.thinkingdata.android.q.e.a(tDConfig.mContext).a(Long.valueOf(jE));
                }
                boolean zG = kVarA.g();
                if (!z && zG) {
                    sAppFirstInstallationMap.put(tDConfig.mContext, new LinkedList());
                }
            }
            thinkingAnalyticsSDK = map.get(tDConfig.getName());
            if (thinkingAnalyticsSDK == null) {
                if (r.f(tDConfig.mContext)) {
                    thinkingAnalyticsSDK = new ThinkingAnalyticsSDK(tDConfig, new boolean[0]);
                    if (sAppFirstInstallationMap.containsKey(tDConfig.mContext)) {
                        sAppFirstInstallationMap.get(tDConfig.mContext).add(tDConfig.getName());
                    }
                } else {
                    thinkingAnalyticsSDK = new j(tDConfig);
                }
                map.put(tDConfig.getName(), thinkingAnalyticsSDK);
            }
        }
        return thinkingAnalyticsSDK;
    }

    private void track(String str, JSONObject jSONObject, cn.thinkingdata.android.utils.f fVar) {
        track(str, jSONObject, fVar, true);
    }

    private void track(String str, JSONObject jSONObject, cn.thinkingdata.android.utils.f fVar, boolean z) {
        track(str, jSONObject, fVar, z, null, null);
    }

    void appBecomeActive() {
        d value;
        synchronized (this.mTrackTimer) {
            try {
                try {
                    for (Map.Entry<String, d> entry : this.mTrackTimer.entrySet()) {
                        if (entry != null && (value = entry.getValue()) != null) {
                            long jC = (value.c() + SystemClock.elapsedRealtime()) - value.e();
                            value.d(SystemClock.elapsedRealtime());
                            value.b(jC);
                        }
                    }
                } catch (Exception e) {
                    TDLog.i(TAG, "appBecomeActive error:" + e.getMessage());
                }
            } finally {
                flush();
            }
        }
    }

    void appEnterBackground() {
        d value;
        synchronized (this.mTrackTimer) {
            try {
                for (Map.Entry<String, d> entry : this.mTrackTimer.entrySet()) {
                    if (entry != null && !"ta_app_end".equals(entry.getKey().toString()) && (value = entry.getValue()) != null) {
                        value.c((value.d() + SystemClock.elapsedRealtime()) - value.e());
                        value.d(SystemClock.elapsedRealtime());
                    }
                }
            } catch (Exception e) {
                TDLog.i(TAG, "appEnterBackground error:" + e.getMessage());
            }
        }
    }

    void autoTrack(String str, JSONObject jSONObject) {
        if (hasDisabled()) {
            return;
        }
        track(str, jSONObject, this.mCalibratedTimeManager.a(), false);
    }

    public void clearSuperProperties() {
        if (hasDisabled()) {
            return;
        }
        this.mStorageManager.c();
    }

    /* JADX INFO: renamed from: createLightInstance, reason: merged with bridge method [inline-methods] */
    public ThinkingAnalyticsSDK m7createLightInstance() {
        return new f(this.mConfig);
    }

    public void enableAutoTrack(List<AutoTrackEventType> list) {
        if (hasDisabled()) {
            return;
        }
        this.mAutoTrack = true;
        if (list == null || list.size() == 0) {
            return;
        }
        if (list.contains(AutoTrackEventType.APP_INSTALL)) {
            synchronized (sInstanceMap) {
                if (sAppFirstInstallationMap.containsKey(this.mConfig.mContext) && sAppFirstInstallationMap.get(this.mConfig.mContext).contains(getToken())) {
                    track("ta_app_install");
                    flush();
                    sAppFirstInstallationMap.get(this.mConfig.mContext).remove(getToken());
                }
            }
        }
        if (list.contains(AutoTrackEventType.APP_CRASH)) {
            this.mTrackCrash = true;
            l lVarB = l.b(this.mConfig.mContext);
            if (lVarB != null) {
                lVarB.a();
            }
        }
        if (!this.mAutoTrackEventTypeList.contains(AutoTrackEventType.APP_END) && list.contains(AutoTrackEventType.APP_END)) {
            timeEvent("ta_app_end");
            this.mLifecycleCallbacks.a(true);
        }
        synchronized (this) {
            this.mAutoTrackStartTime = this.mCalibratedTimeManager.a();
            this.mAutoTrackStartProperties = obtainDefaultEventProperties("ta_app_start");
        }
        this.mAutoTrackEventTypeList.clear();
        this.mAutoTrackEventTypeList.addAll(list);
        if (this.mAutoTrackEventTypeList.contains(AutoTrackEventType.APP_START)) {
            this.mLifecycleCallbacks.b();
        }
    }

    public void enableAutoTrack(List<AutoTrackEventType> list, AutoTrackEventListener autoTrackEventListener) {
        this.mAutoTrackEventListener = autoTrackEventListener;
        enableAutoTrack(list);
    }

    public void enableAutoTrack(List<AutoTrackEventType> list, JSONObject jSONObject) {
        setAutoTrackProperties(list, jSONObject);
        enableAutoTrack(list);
    }

    public void enableThirdPartySharing(int i) {
        cn.thinkingdata.android.r.f.a().a("/thingkingdata/third/party").b("enableThirdPartySharing").a("type", i).a("instance", this).a("loginId", getLoginId()).f();
    }

    public void enableThirdPartySharing(int i, Object obj) {
        cn.thinkingdata.android.r.f.a().a("/thingkingdata/third/party").b("enableThirdPartySharingWithParams").a("type", i).a("instance", this).a("loginId", getLoginId()).a(SqTrackNetKey.params, obj).f();
    }

    @Deprecated
    public void enableTracking(boolean z) {
        TDLog.d(TAG, "enableTracking: " + z);
        if (isEnabled() && !z) {
            flush();
        }
        this.mStorageManager.a(z);
    }

    public void flush() {
        if (hasDisabled()) {
            return;
        }
        this.mMessages.b(getToken());
    }

    public List<AutoTrackEventType> getAutoTrackEventTypeList() {
        return this.mAutoTrackEventTypeList;
    }

    public JSONObject getAutoTrackProperties() {
        return this.mAutoTrackEventProperties;
    }

    synchronized JSONObject getAutoTrackStartProperties() {
        JSONObject jSONObject;
        jSONObject = this.mAutoTrackStartProperties;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        return jSONObject;
    }

    synchronized cn.thinkingdata.android.utils.f getAutoTrackStartTime() {
        return this.mAutoTrackStartTime;
    }

    protected cn.thinkingdata.android.b getDataHandleInstance(Context context) {
        return cn.thinkingdata.android.b.b(context);
    }

    public String getDeviceId() {
        if (this.mSystemInformation.d().containsKey("#device_id")) {
            return (String) this.mSystemInformation.d().get("#device_id");
        }
        return null;
    }

    public String getDistinctId() {
        String identifyID = getIdentifyID();
        return identifyID == null ? getRandomID() : identifyID;
    }

    DynamicSuperPropertiesTracker getDynamicSuperPropertiesTracker() {
        return this.mDynamicSuperPropertiesTracker;
    }

    List<Class> getIgnoredViewTypeList() {
        if (this.mIgnoredViewTypeList == null) {
            this.mIgnoredViewTypeList = new ArrayList();
        }
        return this.mIgnoredViewTypeList;
    }

    String getLoginId() {
        return this.mStorageManager.a(this.mEnableTrackOldData, this.mConfig.mContext);
    }

    public TDPresetProperties getPresetProperties() {
        JSONObject jSONObjectA = k.e(this.mConfig.mContext).a();
        String strC = k.e(this.mConfig.mContext).c();
        double dDoubleValue = this.mCalibratedTimeManager.a().a().doubleValue();
        try {
            if (!TDPresetProperties.disableList.contains("#network_type")) {
                jSONObjectA.put("#network_type", strC);
            }
            if (!TDPresetProperties.disableList.contains("#zone_offset")) {
                jSONObjectA.put("#zone_offset", dDoubleValue);
            }
            if (!TDPresetProperties.disableList.contains("#ram")) {
                jSONObjectA.put("#ram", this.mSystemInformation.b(this.mConfig.mContext));
            }
            if (!TDPresetProperties.disableList.contains("#disk")) {
                jSONObjectA.put("#disk", this.mSystemInformation.a(this.mConfig.mContext, false));
            }
            if (!TDPresetProperties.disableList.contains("#fps")) {
                jSONObjectA.put("#fps", r.a());
            }
            if (!TDPresetProperties.disableList.contains("#device_type")) {
                jSONObjectA.put("#device_type", r.c(this.mConfig.mContext));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new TDPresetProperties(jSONObjectA);
    }

    String getRandomID() {
        return cn.thinkingdata.android.q.e.a(this.mConfig.mContext).e();
    }

    public JSONObject getSuperProperties() {
        return this.mStorageManager.h();
    }

    public String getTimeString(Date date) {
        return this.mCalibratedTimeManager.a(date, this.mConfig.getDefaultTimeZone()).b();
    }

    public String getToken() {
        return this.mConfig.getName();
    }

    boolean hasDisabled() {
        return !isEnabled() || hasOptOut();
    }

    public boolean hasOptOut() {
        return this.mStorageManager.f();
    }

    public void identify(String str) {
        if (hasDisabled()) {
            return;
        }
        this.mStorageManager.b(str, this.mConfig.shouldThrowException());
    }

    public void ignoreAppViewEventInExtPackage() {
        this.mIgnoreAppViewInExtPackage = true;
    }

    public void ignoreAutoTrackActivities(List<Class<?>> list) {
        if (hasDisabled() || list == null || list.size() == 0) {
            return;
        }
        if (this.mAutoTrackIgnoredActivities == null) {
            this.mAutoTrackIgnoredActivities = new ArrayList();
        }
        for (Class<?> cls : list) {
            if (cls != null && !this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(cls.hashCode()))) {
                this.mAutoTrackIgnoredActivities.add(Integer.valueOf(cls.hashCode()));
            }
        }
    }

    public void ignoreAutoTrackActivity(Class<?> cls) {
        if (hasDisabled() || cls == null) {
            return;
        }
        if (this.mAutoTrackIgnoredActivities == null) {
            this.mAutoTrackIgnoredActivities = new ArrayList();
        }
        if (this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(cls.hashCode()))) {
            return;
        }
        this.mAutoTrackIgnoredActivities.add(Integer.valueOf(cls.hashCode()));
    }

    public void ignoreView(View view) {
        if (hasDisabled() || view == null) {
            return;
        }
        r.a(getToken(), view, R.id.thinking_analytics_tag_view_ignored, "1");
    }

    public void ignoreViewType(Class cls) {
        if (hasDisabled() || cls == null) {
            return;
        }
        if (this.mIgnoredViewTypeList == null) {
            this.mIgnoredViewTypeList = new ArrayList();
        }
        if (this.mIgnoredViewTypeList.contains(cls)) {
            return;
        }
        this.mIgnoredViewTypeList.add(cls);
    }

    boolean isActivityAutoTrackAppClickIgnored(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        List<Integer> list = this.mAutoTrackIgnoredActivities;
        if (list != null && list.contains(Integer.valueOf(cls.hashCode()))) {
            return true;
        }
        ThinkingDataIgnoreTrackAppViewScreenAndAppClick thinkingDataIgnoreTrackAppViewScreenAndAppClick = (ThinkingDataIgnoreTrackAppViewScreenAndAppClick) cls.getAnnotation(ThinkingDataIgnoreTrackAppViewScreenAndAppClick.class);
        if (thinkingDataIgnoreTrackAppViewScreenAndAppClick != null && (TextUtils.isEmpty(thinkingDataIgnoreTrackAppViewScreenAndAppClick.appId()) || getToken().equals(thinkingDataIgnoreTrackAppViewScreenAndAppClick.appId()))) {
            return true;
        }
        ThinkingDataIgnoreTrackAppClick thinkingDataIgnoreTrackAppClick = (ThinkingDataIgnoreTrackAppClick) cls.getAnnotation(ThinkingDataIgnoreTrackAppClick.class);
        if (thinkingDataIgnoreTrackAppClick != null) {
            return TextUtils.isEmpty(thinkingDataIgnoreTrackAppClick.appId()) || getToken().equals(thinkingDataIgnoreTrackAppClick.appId());
        }
        return false;
    }

    boolean isActivityAutoTrackAppViewScreenIgnored(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        List<Integer> list = this.mAutoTrackIgnoredActivities;
        if (list != null && list.contains(Integer.valueOf(cls.hashCode()))) {
            return true;
        }
        ThinkingDataIgnoreTrackAppViewScreenAndAppClick thinkingDataIgnoreTrackAppViewScreenAndAppClick = (ThinkingDataIgnoreTrackAppViewScreenAndAppClick) cls.getAnnotation(ThinkingDataIgnoreTrackAppViewScreenAndAppClick.class);
        if (thinkingDataIgnoreTrackAppViewScreenAndAppClick != null && (TextUtils.isEmpty(thinkingDataIgnoreTrackAppViewScreenAndAppClick.appId()) || getToken().equals(thinkingDataIgnoreTrackAppViewScreenAndAppClick.appId()))) {
            return true;
        }
        ThinkingDataIgnoreTrackAppViewScreen thinkingDataIgnoreTrackAppViewScreen = (ThinkingDataIgnoreTrackAppViewScreen) cls.getAnnotation(ThinkingDataIgnoreTrackAppViewScreen.class);
        return thinkingDataIgnoreTrackAppViewScreen != null && (TextUtils.isEmpty(thinkingDataIgnoreTrackAppViewScreen.appId()) || getToken().equals(thinkingDataIgnoreTrackAppViewScreen.appId()));
    }

    boolean isAutoTrackEnabled() {
        if (hasDisabled()) {
            return false;
        }
        return this.mAutoTrack;
    }

    boolean isAutoTrackEventTypeIgnored(AutoTrackEventType autoTrackEventType) {
        return (autoTrackEventType == null || this.mAutoTrackEventTypeList.contains(autoTrackEventType)) ? false : true;
    }

    public boolean isEnabled() {
        return this.mStorageManager.d();
    }

    boolean isIgnoreAppViewInExtPackage() {
        return this.mIgnoreAppViewInExtPackage;
    }

    boolean isTrackFragmentAppViewScreenEnabled() {
        return this.mTrackFragmentAppViewScreen;
    }

    public void login(String str) {
        if (hasDisabled()) {
            return;
        }
        this.mStorageManager.a(str, this.mConfig.shouldThrowException());
    }

    public void logout() {
        if (hasDisabled()) {
            return;
        }
        this.mStorageManager.b(this.mEnableTrackOldData, this.mConfig.mContext);
    }

    @Deprecated
    public void optInTracking() {
        TDLog.d(TAG, "optInTracking...");
        this.mStorageManager.b(false);
        this.mMessages.b(getToken());
    }

    @Deprecated
    public void optOutTracking() {
        TDLog.d(TAG, "optOutTracking...");
        this.mStorageManager.b(true);
        this.mMessages.a(getToken());
        synchronized (this.mTrackTimer) {
            this.mTrackTimer.clear();
        }
        this.mStorageManager.a();
        this.mStorageManager.b();
        this.mStorageManager.c();
    }

    @Deprecated
    public void optOutTrackingAndDeleteUser() {
        cn.thinkingdata.android.a aVar = new cn.thinkingdata.android.a(this, cn.thinkingdata.android.utils.m.USER_DEL, null, this.mCalibratedTimeManager.a());
        aVar.b();
        trackInternal(aVar);
        optOutTracking();
    }

    public void setAutoTrackProperties(List<AutoTrackEventType> list, JSONObject jSONObject) {
        if (hasDisabled()) {
            return;
        }
        if (jSONObject != null) {
            try {
                if (cn.thinkingdata.android.utils.h.a(jSONObject)) {
                    JSONObject jSONObject2 = new JSONObject();
                    for (AutoTrackEventType autoTrackEventType : list) {
                        JSONObject jSONObject3 = new JSONObject();
                        r.a(jSONObject, jSONObject3, this.mConfig.getDefaultTimeZone());
                        jSONObject2.put(autoTrackEventType.getEventName(), jSONObject3);
                    }
                    synchronized (this.mAutoTrackEventProperties) {
                        r.b(jSONObject2, this.mAutoTrackEventProperties, this.mConfig.getDefaultTimeZone());
                    }
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (this.mConfig.shouldThrowException()) {
            throw new n("Set autoTrackEvent properties failed. Please refer to the SDK debug log for details.");
        }
    }

    public void setDynamicSuperPropertiesTracker(DynamicSuperPropertiesTracker dynamicSuperPropertiesTracker) {
        if (hasDisabled()) {
            return;
        }
        this.mDynamicSuperPropertiesTracker = dynamicSuperPropertiesTracker;
    }

    public void setFromSubProcess(boolean z) {
        this.isFromSubProcess = z;
    }

    public void setJsBridge(WebView webView) {
        if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(new TDWebAppInterface(this, this.mSystemInformation.d()), "ThinkingData_APP_JS_Bridge");
        } else {
            TDLog.d(TAG, "SetJsBridge failed due to parameter webView is null");
            if (this.mConfig.shouldThrowException()) {
                throw new n("webView cannot be null for setJsBridge");
            }
        }
    }

    public void setJsBridgeForX5WebView(Object obj) {
        if (obj == null) {
            TDLog.d(TAG, "SetJsBridge failed due to parameter webView is null");
            return;
        }
        try {
            obj.getClass().getMethod("addJavascriptInterface", Object.class, String.class).invoke(obj, new TDWebAppInterface(this, this.mSystemInformation.d()), "ThinkingData_APP_JS_Bridge");
        } catch (Exception e) {
            TDLog.w(TAG, "setJsBridgeForX5WebView failed: " + e.toString());
        }
    }

    public void setNetworkType(ThinkingdataNetworkType thinkingdataNetworkType) {
        if (hasDisabled()) {
            return;
        }
        this.mConfig.setNetworkType(thinkingdataNetworkType);
    }

    public void setSuperProperties(JSONObject jSONObject) {
        if (hasDisabled()) {
            return;
        }
        this.mStorageManager.a(jSONObject, this.mConfig.getDefaultTimeZone(), this.mConfig.shouldThrowException());
    }

    public void setTrackStatus(TATrackStatus tATrackStatus) {
        int i = a.a[tATrackStatus.ordinal()];
        if (i == 1) {
            this.mStorageManager.b(false);
            this.mStorageManager.c(false);
            this.mMessages.a(getToken(), false);
            enableTracking(false);
            return;
        }
        if (i == 2) {
            this.mStorageManager.a(true);
            this.mStorageManager.c(false);
            this.mMessages.a(getToken(), false);
            optOutTracking();
            return;
        }
        if (i == 3) {
            this.mStorageManager.a(true);
            this.mStorageManager.b(false);
            this.mStorageManager.c(true);
            this.mMessages.a(getToken(), true);
            return;
        }
        if (i != 4) {
            return;
        }
        this.mStorageManager.a(true);
        this.mStorageManager.b(false);
        this.mStorageManager.c(false);
        this.mMessages.a(getToken(), false);
        flush();
    }

    public void setViewID(Dialog dialog, String str) {
        if (hasDisabled() || dialog == null) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str) || dialog.getWindow() == null) {
                return;
            }
            r.a(getToken(), dialog.getWindow().getDecorView(), R.id.thinking_analytics_tag_view_id, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setViewID(View view, String str) {
        if (hasDisabled() || view == null || TextUtils.isEmpty(str)) {
            return;
        }
        r.a(getToken(), view, R.id.thinking_analytics_tag_view_id, str);
    }

    public void setViewProperties(View view, JSONObject jSONObject) {
        if (hasDisabled() || view == null || jSONObject == null) {
            return;
        }
        r.a(getToken(), view, R.id.thinking_analytics_tag_view_properties, jSONObject);
    }

    boolean shouldTrackCrash() {
        if (hasDisabled()) {
            return false;
        }
        return this.mTrackCrash;
    }

    public void timeEvent(String str) {
        if (hasDisabled()) {
            return;
        }
        try {
            if (cn.thinkingdata.android.utils.h.a(str)) {
                TDLog.w(TAG, "timeEvent event name[" + str + "] is not valid");
            }
            synchronized (this.mTrackTimer) {
                this.mTrackTimer.put(str, new d(TimeUnit.SECONDS));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void track(ThinkingAnalyticsEvent thinkingAnalyticsEvent) {
        if (hasDisabled()) {
            return;
        }
        if (thinkingAnalyticsEvent == null) {
            TDLog.w(TAG, "Ignoring empty event...");
            return;
        }
        cn.thinkingdata.android.utils.f fVarA = thinkingAnalyticsEvent.getEventTime() != null ? this.mCalibratedTimeManager.a(thinkingAnalyticsEvent.getEventTime(), thinkingAnalyticsEvent.getTimeZone()) : this.mCalibratedTimeManager.a();
        HashMap map = new HashMap();
        if (TextUtils.isEmpty(thinkingAnalyticsEvent.getExtraField())) {
            TDLog.w(TAG, "Invalid ExtraFields. Ignoring...");
        } else {
            map.put(thinkingAnalyticsEvent.getExtraField(), ((thinkingAnalyticsEvent instanceof TDFirstEvent) && thinkingAnalyticsEvent.getExtraValue() == null) ? getDeviceId() : thinkingAnalyticsEvent.getExtraValue());
        }
        track(thinkingAnalyticsEvent.getEventName(), thinkingAnalyticsEvent.getProperties(), fVarA, true, map, thinkingAnalyticsEvent.getDataType());
    }

    public void track(String str) {
        if (hasDisabled()) {
            return;
        }
        track(str, (JSONObject) null, this.mCalibratedTimeManager.a());
    }

    public void track(String str, JSONObject jSONObject) {
        if (hasDisabled()) {
            return;
        }
        track(str, jSONObject, this.mCalibratedTimeManager.a());
    }

    void track(String str, JSONObject jSONObject, cn.thinkingdata.android.utils.f fVar, boolean z, Map<String, String> map, cn.thinkingdata.android.utils.m mVar) {
        AutoTrackEventType autoTrackEventTypeAutoTrackEventTypeFromEventName;
        if (this.mConfig.isDisabledEvent(str)) {
            TDLog.d(TAG, "Ignoring disabled event [" + str + "]");
            return;
        }
        if (z) {
            try {
                if (cn.thinkingdata.android.utils.h.a(str)) {
                    TDLog.w(TAG, "Event name[" + str + "] is invalid. Event name must be string that starts with English letter, and contains letter, number, and '_'. The max length of the event name is 50.");
                    if (this.mConfig.shouldThrowException()) {
                        throw new n("Invalid event name: " + str);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        if (z && !cn.thinkingdata.android.utils.h.a(jSONObject)) {
            TDLog.w(TAG, "The data contains invalid key or value: " + jSONObject.toString());
            if (this.mConfig.shouldThrowException()) {
                throw new n("Invalid properties. Please refer to SDK debug log for detail reasons.");
            }
        }
        JSONObject jSONObjectObtainDefaultEventProperties = obtainDefaultEventProperties(str);
        if (jSONObject != null) {
            r.a(jSONObject, jSONObjectObtainDefaultEventProperties, this.mConfig.getDefaultTimeZone());
        }
        if (!this.isFromSubProcess && (autoTrackEventTypeAutoTrackEventTypeFromEventName = AutoTrackEventType.autoTrackEventTypeFromEventName(str)) != null) {
            if (this.mAutoTrackEventListener != null) {
                JSONObject jSONObjectEventCallback = this.mAutoTrackEventListener.eventCallback(autoTrackEventTypeAutoTrackEventTypeFromEventName, jSONObjectObtainDefaultEventProperties);
                if (jSONObjectEventCallback != null) {
                    r.a(jSONObjectEventCallback, jSONObjectObtainDefaultEventProperties, this.mConfig.getDefaultTimeZone());
                }
            } else {
                TDLog.i(TAG, "No mAutoTrackEventListener");
            }
        }
        if (mVar == null) {
            mVar = cn.thinkingdata.android.utils.m.TRACK;
        }
        cn.thinkingdata.android.a aVar = new cn.thinkingdata.android.a(this, mVar, jSONObjectObtainDefaultEventProperties, fVar);
        aVar.a = str;
        if (map != null) {
            aVar.a(map);
        }
        setFromSubProcess(false);
        trackInternal(aVar);
    }

    public void track(String str, JSONObject jSONObject, Date date) {
        if (hasDisabled()) {
            return;
        }
        track(str, jSONObject, this.mCalibratedTimeManager.a(date, null));
    }

    public void track(String str, JSONObject jSONObject, Date date, TimeZone timeZone) {
        if (hasDisabled()) {
            return;
        }
        track(str, jSONObject, this.mCalibratedTimeManager.a(date, timeZone));
    }

    void trackAppCrashAndEndEvent(JSONObject jSONObject) {
        this.mLifecycleCallbacks.a(jSONObject);
    }

    public void trackAppInstall() {
        if (hasDisabled()) {
            return;
        }
        enableAutoTrack(new ArrayList(Collections.singletonList(AutoTrackEventType.APP_INSTALL)));
    }

    public void trackFragmentAppViewScreen() {
        if (hasDisabled()) {
            return;
        }
        this.mTrackFragmentAppViewScreen = true;
    }

    void trackInternal(cn.thinkingdata.android.a aVar) {
        if (this.mConfig.isDebugOnly() || this.mConfig.isDebug()) {
            this.mMessages.b(aVar);
        } else if (aVar.h) {
            this.mMessages.c(aVar);
        } else {
            this.mMessages.a(aVar);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void trackViewScreen(Activity activity) {
        if (hasDisabled() || activity == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TDPresetProperties.disableList.contains("#screen_name")) {
                jSONObject.put("#screen_name", activity.getClass().getCanonicalName());
            }
            r.a(jSONObject, activity);
            if (!(activity instanceof ScreenAutoTracker)) {
                autoTrack("ta_app_view", jSONObject);
                return;
            }
            ScreenAutoTracker screenAutoTracker = (ScreenAutoTracker) activity;
            String screenUrl = screenAutoTracker.getScreenUrl();
            JSONObject trackProperties = screenAutoTracker.getTrackProperties();
            if (trackProperties != null) {
                r.a(trackProperties, jSONObject, this.mConfig.getDefaultTimeZone());
            }
            trackViewScreenInternal(screenUrl, jSONObject);
        } catch (Exception e) {
            TDLog.i(TAG, "trackViewScreen:" + e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void trackViewScreen(Fragment fragment) {
        if (hasDisabled() || fragment == 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            Object canonicalName = fragment.getClass().getCanonicalName();
            String strA = r.a(fragment, getToken());
            Activity activity = fragment.getActivity();
            if (activity != null) {
                if (TextUtils.isEmpty(strA)) {
                    strA = r.a(activity);
                }
                canonicalName = String.format(Locale.CHINA, "%s|%s", activity.getClass().getCanonicalName(), canonicalName);
            }
            if (!TextUtils.isEmpty(strA) && !TDPresetProperties.disableList.contains("#title")) {
                jSONObject.put("#title", strA);
            }
            if (!TDPresetProperties.disableList.contains("#screen_name")) {
                jSONObject.put("#screen_name", canonicalName);
            }
            if (!(fragment instanceof ScreenAutoTracker)) {
                autoTrack("ta_app_view", jSONObject);
                return;
            }
            ScreenAutoTracker screenAutoTracker = (ScreenAutoTracker) fragment;
            String screenUrl = screenAutoTracker.getScreenUrl();
            JSONObject trackProperties = screenAutoTracker.getTrackProperties();
            if (trackProperties != null) {
                r.a(trackProperties, jSONObject, this.mConfig.getDefaultTimeZone());
            }
            trackViewScreenInternal(screenUrl, jSONObject);
        } catch (Exception e) {
            TDLog.i(TAG, "trackViewScreen:" + e);
        }
    }

    public void trackViewScreen(Object obj) {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3;
        if (hasDisabled() || obj == null) {
            return;
        }
        Activity activity = null;
        try {
            cls = Class.forName("android.support.v4.app.Fragment");
        } catch (Exception unused) {
            cls = null;
        }
        try {
            cls2 = Class.forName("android.app.Fragment");
        } catch (Exception unused2) {
            cls2 = null;
        }
        try {
            cls3 = Class.forName("androidx.fragment.app.Fragment");
        } catch (Exception unused3) {
            cls3 = null;
        }
        if ((cls == null || !cls.isInstance(obj)) && ((cls2 == null || !cls2.isInstance(obj)) && (cls3 == null || !cls3.isInstance(obj)))) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            Object canonicalName = obj.getClass().getCanonicalName();
            String strA = r.a(obj, getToken());
            try {
                activity = (Activity) obj.getClass().getMethod("getActivity", new Class[0]).invoke(obj, new Object[0]);
            } catch (Exception unused4) {
            }
            if (activity != null) {
                if (TextUtils.isEmpty(strA)) {
                    strA = r.a(activity);
                }
                canonicalName = String.format(Locale.CHINA, "%s|%s", activity.getClass().getCanonicalName(), canonicalName);
            }
            if (!TextUtils.isEmpty(strA) && !TDPresetProperties.disableList.contains("#title")) {
                jSONObject.put("#title", strA);
            }
            if (!TDPresetProperties.disableList.contains("#screen_name")) {
                jSONObject.put("#screen_name", canonicalName);
            }
            if (!(obj instanceof ScreenAutoTracker)) {
                autoTrack("ta_app_view", jSONObject);
                return;
            }
            ScreenAutoTracker screenAutoTracker = (ScreenAutoTracker) obj;
            String screenUrl = screenAutoTracker.getScreenUrl();
            JSONObject trackProperties = screenAutoTracker.getTrackProperties();
            if (trackProperties != null) {
                r.a(trackProperties, jSONObject, this.mConfig.getDefaultTimeZone());
            }
            trackViewScreenInternal(screenUrl, jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void trackViewScreenInternal(String str, JSONObject jSONObject) {
        if (hasDisabled()) {
            return;
        }
        try {
            if (TextUtils.isEmpty(str) && jSONObject == null) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            if (!TextUtils.isEmpty(this.mLastScreenUrl) && !TDPresetProperties.disableList.contains("#referrer")) {
                jSONObject2.put("#referrer", this.mLastScreenUrl);
            }
            if (!TDPresetProperties.disableList.contains("#url")) {
                jSONObject2.put("#url", str);
            }
            this.mLastScreenUrl = str;
            if (jSONObject != null) {
                r.a(jSONObject, jSONObject2, this.mConfig.getDefaultTimeZone());
            }
            autoTrack("ta_app_view", jSONObject2);
        } catch (JSONException e) {
            TDLog.i(TAG, "trackViewScreen:" + e);
        }
    }

    public void unsetSuperProperty(String str) {
        if (hasDisabled()) {
            return;
        }
        this.mStorageManager.a(str);
    }

    public void user_add(String str, Number number) {
        this.mUserOperationHandler.a(str, number);
    }

    public void user_add(JSONObject jSONObject) {
        this.mUserOperationHandler.a(jSONObject, (Date) null);
    }

    public void user_add(JSONObject jSONObject, Date date) {
        this.mUserOperationHandler.a(jSONObject, date);
    }

    public void user_append(JSONObject jSONObject) {
        this.mUserOperationHandler.b(jSONObject, null);
    }

    public void user_append(JSONObject jSONObject, Date date) {
        this.mUserOperationHandler.b(jSONObject, date);
    }

    public void user_delete() {
        this.mUserOperationHandler.a((Date) null);
    }

    public void user_delete(Date date) {
        this.mUserOperationHandler.a(date);
    }

    void user_operations(cn.thinkingdata.android.utils.m mVar, JSONObject jSONObject, Date date) {
        this.mUserOperationHandler.a(mVar, jSONObject, date);
    }

    public void user_set(JSONObject jSONObject) {
        this.mUserOperationHandler.c(jSONObject, null);
    }

    public void user_set(JSONObject jSONObject, Date date) {
        this.mUserOperationHandler.c(jSONObject, date);
    }

    public void user_setOnce(JSONObject jSONObject) {
        this.mUserOperationHandler.d(jSONObject, null);
    }

    public void user_setOnce(JSONObject jSONObject, Date date) {
        this.mUserOperationHandler.d(jSONObject, date);
    }

    public void user_uniqAppend(JSONObject jSONObject) {
        this.mUserOperationHandler.e(jSONObject, null);
    }

    public void user_uniqAppend(JSONObject jSONObject, Date date) {
        this.mUserOperationHandler.e(jSONObject, date);
    }

    public void user_unset(JSONObject jSONObject, Date date) {
        this.mUserOperationHandler.f(jSONObject, date);
    }

    public void user_unset(String... strArr) {
        this.mUserOperationHandler.a(strArr);
    }
}
