package com.bytedance.applog;

import android.accounts.Account;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.bytedance.applog.alink.IALinkListener;
import com.bytedance.applog.event.EventBuilder;
import com.bytedance.applog.event.IEventHandler;
import com.bytedance.applog.exposure.ViewExposureManager;
import com.bytedance.applog.network.INetworkClient;
import com.bytedance.applog.oneid.IDBindCallback;
import com.bytedance.applog.profile.UserProfileCallback;
import com.bytedance.bdtracker.a1;
import com.bytedance.bdtracker.d;
import com.bytedance.bdtracker.f;
import com.bytedance.bdtracker.n0;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class AppLog {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final IAppLogInstance f179a = newInstance();
    public static volatile boolean b = false;

    public static void activateALink(Uri uri) {
        f179a.activateALink(uri);
    }

    public static void addDataObserver(IDataObserver iDataObserver) {
        f179a.addDataObserver(iDataObserver);
    }

    public static void addEventObserver(IEventObserver iEventObserver) {
        f179a.addEventObserver(iEventObserver);
    }

    public static String addNetCommonParams(Context context, String str, boolean z, Level level) {
        return f179a.addNetCommonParams(context, str, z, level);
    }

    public static void addSessionHook(ISessionObserver iSessionObserver) {
        f179a.addSessionHook(iSessionObserver);
    }

    public static void bind(Map<String, String> map, IDBindCallback iDBindCallback) {
        f179a.bind(map, iDBindCallback);
    }

    public static void clearDb() {
        f179a.clearDb();
    }

    public static void flush() {
        f179a.flush();
    }

    public static <T> T getAbConfig(String str, T t) {
        return (T) f179a.getAbConfig(str, t);
    }

    public static String getAbSdkVersion() {
        return f179a.getAbSdkVersion();
    }

    public static IActiveCustomParamsCallback getActiveCustomParams() {
        return f179a.getActiveCustomParams();
    }

    @Deprecated
    public static String getAid() {
        return f179a.getAid();
    }

    public static JSONObject getAllAbTestConfigs() {
        return f179a.getAllAbTestConfigs();
    }

    public static f getAppContext() {
        return f179a.getAppContext();
    }

    public static String getAppId() {
        return f179a.getAppId();
    }

    public static String getClientUdid() {
        return f179a.getClientUdid();
    }

    public static Context getContext() {
        return f179a.getContext();
    }

    public static String getDid() {
        return f179a.getDid();
    }

    public static boolean getEncryptAndCompress() {
        return f179a.getEncryptAndCompress();
    }

    public static JSONObject getHeader() {
        return f179a.getHeader();
    }

    public static IHeaderCustomTimelyCallback getHeaderCustomCallback() {
        return f179a.getHeaderCustomCallback();
    }

    public static <T> T getHeaderValue(String str, T t, Class<T> cls) {
        return (T) f179a.getHeaderValue(str, t, cls);
    }

    public static String getIid() {
        return f179a.getIid();
    }

    public static InitConfig getInitConfig() {
        return f179a.getInitConfig();
    }

    public static IAppLogInstance getInstance() {
        return f179a;
    }

    public static INetworkClient getNetClient() {
        return f179a.getNetClient();
    }

    public static String getOpenUdid() {
        return f179a.getOpenUdid();
    }

    public static Map<String, String> getRequestHeader() {
        return f179a.getRequestHeader();
    }

    public static String getSdkVersion() {
        return f179a.getSdkVersion();
    }

    public static String getSessionId() {
        return f179a.getSessionId();
    }

    public static String getSsid() {
        return f179a.getSsid();
    }

    public static void getSsidGroup(Map<String, String> map) {
        f179a.getSsidGroup(map);
    }

    public static String getUdid() {
        return f179a.getUdid();
    }

    public static String getUserID() {
        return f179a.getUserID();
    }

    public static String getUserUniqueID() {
        return f179a.getUserUniqueID();
    }

    public static ViewExposureManager getViewExposureManager() {
        return f179a.getViewExposureManager();
    }

    public static JSONObject getViewProperties(View view) {
        return f179a.getViewProperties(view);
    }

    public static boolean hasStarted() {
        return f179a.hasStarted();
    }

    public static void ignoreAutoTrackClick(View view) {
        f179a.ignoreAutoTrackClick(view);
    }

    public static void ignoreAutoTrackClickByViewType(Class<?>... clsArr) {
        f179a.ignoreAutoTrackClickByViewType(clsArr);
    }

    public static void ignoreAutoTrackPage(Class<?>... clsArr) {
        f179a.ignoreAutoTrackPage(clsArr);
    }

    public static void init(Context context, InitConfig initConfig) {
        synchronized (AppLog.class) {
            if (n0.a(b, "Default AppLog is initialized, please create another instance by `AppLog.newInstance()`")) {
                return;
            }
            b = true;
            if (TextUtils.isEmpty(initConfig.getSpName())) {
                initConfig.setSpName("applog_stats");
            }
            f179a.init(context, initConfig);
        }
    }

    public static void init(Context context, InitConfig initConfig, Activity activity) {
        synchronized (AppLog.class) {
            if (n0.a(b, "Default AppLog is initialized, please create another instance by `new AppLogInstance()`")) {
                return;
            }
            b = true;
            if (TextUtils.isEmpty(initConfig.getSpName())) {
                initConfig.setSpName("applog_stats");
            }
            f179a.init(context, initConfig, activity);
        }
    }

    public static void initH5Bridge(View view, String str) {
        f179a.initH5Bridge(view, str);
    }

    public static void initWebViewBridge(View view, String str) {
        f179a.initWebViewBridge(view, str);
    }

    public static boolean isAutoTrackClickIgnored(View view) {
        return f179a.isAutoTrackClickIgnored(view);
    }

    public static boolean isAutoTrackPageIgnored(Class<?> cls) {
        return f179a.isAutoTrackPageIgnored(cls);
    }

    public static boolean isH5BridgeEnable() {
        return f179a.isH5BridgeEnable();
    }

    public static boolean isH5CollectEnable() {
        return f179a.isH5CollectEnable();
    }

    public static boolean isNewUser() {
        return f179a.isNewUser();
    }

    public static boolean isPrivacyMode() {
        return f179a.isPrivacyMode();
    }

    public static boolean manualActivate() {
        return f179a.manualActivate();
    }

    public static EventBuilder newEvent(String str) {
        return f179a.newEvent(str);
    }

    public static IAppLogInstance newInstance() {
        return new d();
    }

    public static void onActivityPause() {
        f179a.onActivityPause();
    }

    public static void onActivityResumed(Activity activity, int i) {
        f179a.onActivityResumed(activity, i);
    }

    public static void onEventV3(String str) {
        f179a.onEventV3(str);
    }

    public static void onEventV3(String str, Bundle bundle) {
        f179a.onEventV3(str, bundle);
    }

    public static void onEventV3(String str, Bundle bundle, int i) {
        f179a.onEventV3(str, bundle, i);
    }

    public static void onEventV3(String str, JSONObject jSONObject) {
        f179a.onEventV3(str, jSONObject);
    }

    public static void onEventV3(String str, JSONObject jSONObject, int i) {
        f179a.onEventV3(str, jSONObject, i);
    }

    public static void onMiscEvent(String str, JSONObject jSONObject) {
        f179a.onMiscEvent(str, jSONObject);
    }

    public static void onPause(Context context) {
        f179a.onPause(context);
    }

    public static void onResume(Context context) {
        f179a.onResume(context);
    }

    public static void pauseDurationEvent(String str) {
        f179a.pauseDurationEvent(str);
    }

    public static void profileAppend(JSONObject jSONObject) {
        f179a.profileAppend(jSONObject);
    }

    public static void profileIncrement(JSONObject jSONObject) {
        f179a.profileIncrement(jSONObject);
    }

    public static void profileSet(JSONObject jSONObject) {
        f179a.profileSet(jSONObject);
    }

    public static void profileSetOnce(JSONObject jSONObject) {
        f179a.profileSetOnce(jSONObject);
    }

    public static void profileUnset(String str) {
        f179a.profileUnset(str);
    }

    public static void pullAbTestConfigs() {
        f179a.pullAbTestConfigs();
    }

    public static void pullAbTestConfigs(int i, IPullAbTestConfigCallback iPullAbTestConfigCallback) {
        f179a.pullAbTestConfigs(i, iPullAbTestConfigCallback);
    }

    public static void putCommonParams(Context context, Map<String, String> map, boolean z, Level level) {
        f179a.putCommonParams(context, map, z, level);
    }

    public static void registerHeaderCustomCallback(IHeaderCustomTimelyCallback iHeaderCustomTimelyCallback) {
        f179a.registerHeaderCustomCallback(iHeaderCustomTimelyCallback);
    }

    public static void removeAllDataObserver() {
        f179a.removeAllDataObserver();
    }

    public static void removeDataObserver(IDataObserver iDataObserver) {
        f179a.removeDataObserver(iDataObserver);
    }

    public static void removeEventObserver(IEventObserver iEventObserver) {
        f179a.removeEventObserver(iEventObserver);
    }

    public static void removeHeaderInfo(String str) {
        f179a.removeHeaderInfo(str);
    }

    public static void removeOaidObserver(IOaidObserver iOaidObserver) {
        f179a.removeOaidObserver(iOaidObserver);
    }

    public static void removeSessionHook(ISessionObserver iSessionObserver) {
        f179a.removeSessionHook(iSessionObserver);
    }

    @Deprecated
    public static boolean reportPhoneDetailInfo() {
        return f179a.reportPhoneDetailInfo();
    }

    public static void resumeDurationEvent(String str) {
        f179a.resumeDurationEvent(str);
    }

    public static void setALinkListener(IALinkListener iALinkListener) {
        f179a.setALinkListener(iALinkListener);
    }

    public static void setAccount(Account account) {
        f179a.setAccount(account);
    }

    public static void setActiveCustomParams(IActiveCustomParamsCallback iActiveCustomParamsCallback) {
        f179a.setActiveCustomParams(iActiveCustomParamsCallback);
    }

    public static void setAppContext(f fVar) {
        f179a.setAppContext(fVar);
    }

    public static void setAppLanguageAndRegion(String str, String str2) {
        f179a.setAppLanguageAndRegion(str, str2);
    }

    public static void setAppTrack(JSONObject jSONObject) {
        f179a.setAppTrack(jSONObject);
    }

    public static void setClipboardEnabled(boolean z) {
        f179a.setClipboardEnabled(z);
    }

    public static void setDevToolsEnable(boolean z) {
        a1.b = Boolean.valueOf(z);
    }

    public static void setEncryptAndCompress(boolean z) {
        f179a.setEncryptAndCompress(z);
    }

    public static void setEventFilterByClient(List<String> list, boolean z) {
        f179a.setEventFilterByClient(list, z);
    }

    public static void setEventHandler(IEventHandler iEventHandler) {
        f179a.setEventHandler(iEventHandler);
    }

    public static void setExternalAbVersion(String str) {
        f179a.setExternalAbVersion(str);
    }

    public static void setExtraParams(IExtraParams iExtraParams) {
        f179a.setExtraParams(iExtraParams);
    }

    @Deprecated
    public static void setForbidReportPhoneDetailInfo(boolean z) {
        f179a.setForbidReportPhoneDetailInfo(z);
    }

    public static void setGPSLocation(float f, float f2, String str) {
        f179a.setGPSLocation(f, f2, str);
    }

    public static void setGoogleAid(String str) {
        f179a.setGoogleAid(str);
    }

    public static void setHeaderInfo(String str, Object obj) {
        f179a.setHeaderInfo(str, obj);
    }

    public static void setHeaderInfo(HashMap<String, Object> map) {
        f179a.setHeaderInfo(map);
    }

    public static void setOaidObserver(IOaidObserver iOaidObserver) {
        f179a.setOaidObserver(iOaidObserver);
    }

    public static void setPrivacyMode(boolean z) {
        f179a.setPrivacyMode(z);
    }

    public static void setPullAbTestConfigsThrottleMills(Long l) {
        f179a.setPullAbTestConfigsThrottleMills(l);
    }

    public static void setRangersEventVerifyEnable(boolean z, String str) {
        f179a.setRangersEventVerifyEnable(z, str);
    }

    public static void setTouchPoint(String str) {
        f179a.setTouchPoint(str);
    }

    public static void setTracerData(JSONObject jSONObject) {
        f179a.setTracerData(jSONObject);
    }

    public static void setUriRuntime(UriConfig uriConfig) {
        f179a.setUriRuntime(uriConfig);
    }

    public static void setUserAgent(String str) {
        f179a.setUserAgent(str);
    }

    public static void setUserID(long j) {
        f179a.setUserID(j);
    }

    public static void setUserUniqueID(String str) {
        f179a.setUserUniqueID(str);
    }

    public static void setUserUniqueID(String str, String str2) {
        f179a.setUserUniqueID(str, str2);
    }

    public static void setViewId(Dialog dialog, String str) {
        f179a.setViewId(dialog, str);
    }

    public static void setViewId(View view, String str) {
        f179a.setViewId(view, str);
    }

    public static void setViewId(Object obj, String str) {
        f179a.setViewId(obj, str);
    }

    public static void setViewProperties(View view, JSONObject jSONObject) {
        f179a.setViewProperties(view, jSONObject);
    }

    public static void start() {
        f179a.start();
    }

    public static void startDurationEvent(String str) {
        f179a.startDurationEvent(str);
    }

    public static void startSimulator(String str) {
        f179a.startSimulator(str);
    }

    public static void stopDurationEvent(String str, JSONObject jSONObject) {
        f179a.stopDurationEvent(str, jSONObject);
    }

    public static void trackClick(View view) {
        f179a.trackClick(view);
    }

    public static void trackClick(View view, JSONObject jSONObject) {
        f179a.trackClick(view, jSONObject);
    }

    public static void trackPage(Activity activity) {
        f179a.trackPage(activity);
    }

    public static void trackPage(Activity activity, JSONObject jSONObject) {
        f179a.trackPage(activity, jSONObject);
    }

    public static void trackPage(Object obj) {
        f179a.trackPage(obj);
    }

    public static void trackPage(Object obj, JSONObject jSONObject) {
        f179a.trackPage(obj, jSONObject);
    }

    public static void userProfileSetOnce(JSONObject jSONObject, UserProfileCallback userProfileCallback) {
        f179a.userProfileSetOnce(jSONObject, userProfileCallback);
    }

    public static void userProfileSync(JSONObject jSONObject, UserProfileCallback userProfileCallback) {
        f179a.userProfileSync(jSONObject, userProfileCallback);
    }
}
