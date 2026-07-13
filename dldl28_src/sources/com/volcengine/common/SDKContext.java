package com.volcengine.common;

import android.content.Context;
import android.text.TextUtils;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.androidcloud.common.pod.Rotation;
import com.volcengine.common.config.SDKSwitchSettingImpl;
import com.volcengine.common.extensions.api.gamepad.GamePadExtension;
import com.volcengine.common.innerapi.AppStateService;
import com.volcengine.common.innerapi.ConfigService;
import com.volcengine.common.innerapi.DownloadService;
import com.volcengine.common.innerapi.ExecutorsService;
import com.volcengine.common.innerapi.HttpService;
import com.volcengine.common.innerapi.IJsonConverter;
import com.volcengine.common.innerapi.MonitorService;
import com.volcengine.common.innerapi.PluginService;
import com.volcengine.common.innerapi.SDKSwitchSetting;
import com.volcengine.j.c;
import com.volcengine.j.f;
import com.volcengine.j.g;
import com.volcengine.j.l;
import com.volcengine.j.m;
import com.volcengine.j.o;
import java.util.Map;
import java.util.Objects;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class SDKContext {
    private static final String TAG = "SDKContext";
    private final com.volcengine.j.a mAppStateService;
    private Context mContext;
    private boolean mDebug;
    private volatile DownloadService mDownloadService;
    private final com.volcengine.d.a mExecutorsService;
    private volatile com.volcengine.e.a mGamePadExtension;
    private volatile boolean mHasInited;
    private volatile HttpService mHttpService;
    private IJsonConverter mIJsonConverter;
    private volatile com.volcengine.common.sdkmonitor.b mMonitorService;
    private String mPluginConfigVersion;
    private volatile SDKSwitchSetting mSDKSwitchSetting;
    private String mSdkVersion;
    private final com.volcengine.f.b mServerTimeHolder;
    private final o mSystemProperties;

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final SDKContext f1113a = new SDKContext();
    }

    private SDKContext() {
        this.mDebug = false;
        this.mSdkVersion = "";
        this.mPluginConfigVersion = "";
        this.mExecutorsService = com.volcengine.d.a.c();
        this.mAppStateService = new com.volcengine.j.a();
        this.mServerTimeHolder = new com.volcengine.f.b();
        this.mSystemProperties = new o();
    }

    public static void checkInitState() {
        if (!b.f1113a.mHasInited) {
            throw new IllegalStateException("sdk has not been initialized");
        }
    }

    public static int checkSelfPermission(Context context, String str) {
        return f.a(context, str);
    }

    public static boolean checkSimulator() {
        return com.volcengine.common.util.a.c(getContext());
    }

    public static String getAccountId() {
        return c.d();
    }

    public static AppStateService getAppStateService() {
        return b.f1113a.mAppStateService;
    }

    public static int getAppVersionCode() {
        return c.a(getContext());
    }

    public static String getBoolean(String str, String str2) {
        return b.f1113a.mSystemProperties.a(str, str2);
    }

    public static boolean getBoolean(String str, boolean z) {
        return b.f1113a.mSystemProperties.a(str, z);
    }

    public static ConfigService getConfigService() {
        return com.volcengine.common.config.a.b();
    }

    public static Context getContext() {
        Object objA = b.f1113a.mContext;
        if (objA == null) {
            objA = g.a();
        }
        return (Context) Objects.requireNonNull(objA, "sdk external error: have you called init()?");
    }

    public static String getDid() {
        return c.f();
    }

    public static Rotation getDisplayRotation() {
        return c.b(getContext());
    }

    public static DownloadService getDownloadService() {
        DownloadService bVar = b.f1113a.mDownloadService;
        if (bVar == null) {
            synchronized (com.volcengine.c.b.class) {
                bVar = b.f1113a.mDownloadService;
                if (bVar == null) {
                    bVar = new com.volcengine.c.b();
                    b.f1113a.mDownloadService = bVar;
                }
            }
        }
        return bVar;
    }

    public static ExecutorsService getExecutorsService() {
        return b.f1113a.mExecutorsService;
    }

    public static GamePadExtension getGamePadExtension() {
        com.volcengine.e.a aVar = b.f1113a.mGamePadExtension;
        if (aVar == null) {
            synchronized (com.volcengine.e.a.class) {
                aVar = b.f1113a.mGamePadExtension;
                if (aVar == null) {
                    aVar = new com.volcengine.e.a();
                    b.f1113a.mGamePadExtension = aVar;
                }
            }
        }
        return aVar;
    }

    public static String getHostAbi() {
        return l.a();
    }

    public static HttpService getHttpService() {
        HttpService aVar = b.f1113a.mHttpService;
        if (aVar == null) {
            synchronized (com.volcengine.f.a.class) {
                aVar = b.f1113a.mHttpService;
                if (aVar == null) {
                    aVar = new com.volcengine.f.a();
                    b.f1113a.mHttpService = aVar;
                }
            }
        }
        return aVar;
    }

    public static String getIid() {
        return c.g();
    }

    public static int getInt(String str, int i) {
        return b.f1113a.mSystemProperties.a(str, i);
    }

    public static IJsonConverter getJsonConverter() {
        return (IJsonConverter) Objects.requireNonNull(b.f1113a.mIJsonConverter, "sdk external error: have you called init()?");
    }

    public static MonitorService getMonitorService() {
        com.volcengine.common.sdkmonitor.b bVar = b.f1113a.mMonitorService;
        if (bVar == null) {
            synchronized (com.volcengine.common.sdkmonitor.b.class) {
                bVar = b.f1113a.mMonitorService;
                if (bVar == null) {
                    bVar = new com.volcengine.common.sdkmonitor.b();
                    b.f1113a.mMonitorService = bVar;
                }
            }
        }
        return bVar;
    }

    public static String getNetworkType() {
        return m.i(getContext());
    }

    public static String getPluginConfigVersion() {
        return b.f1113a.mPluginConfigVersion;
    }

    public static PluginService getPluginService() {
        return com.volcengine.common.plugin.c.c();
    }

    public static SDKSwitchSetting getSDKSwitchSetting() {
        SDKSwitchSetting sDKSwitchSettingImpl = b.f1113a.mSDKSwitchSetting;
        if (sDKSwitchSettingImpl == null) {
            synchronized (SDKSwitchSetting.class) {
                sDKSwitchSettingImpl = b.f1113a.mSDKSwitchSetting;
                if (sDKSwitchSettingImpl == null) {
                    sDKSwitchSettingImpl = new SDKSwitchSettingImpl();
                    b.f1113a.mSDKSwitchSetting = sDKSwitchSettingImpl;
                }
            }
        }
        return sDKSwitchSettingImpl;
    }

    public static String getSdkVersion() {
        return b.f1113a.mSdkVersion;
    }

    public static long getServiceTime(boolean z) {
        return b.f1113a.mServerTimeHolder.a(z);
    }

    public static String getUUId() {
        return c.h();
    }

    public static boolean hasPermission(Context context, String str) {
        return f.b(context, str);
    }

    public static void init(Context context) {
        if (b.f1113a.mHasInited) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        b.f1113a.mContext = applicationContext;
        b.f1113a.initJsonConvertor();
        com.volcengine.i.a.c();
        b.f1113a.mAppStateService.a(applicationContext);
        b.f1113a.mHasInited = true;
    }

    private void initJsonConvertor() {
        try {
            if (c.a("com.google.gson.Gson")) {
                AcLog.d(TAG, "gson is present");
                this.mIJsonConverter = new com.volcengine.g.b();
            } else if (c.a("com.fasterxml.jackson.databind.ObjectMapper")) {
                AcLog.d(TAG, "jackson is present");
                this.mIJsonConverter = new com.volcengine.g.c();
            } else if (c.a("com.alibaba.fastjson.JSON")) {
                AcLog.d(TAG, "fastJson is present");
                this.mIJsonConverter = new com.volcengine.g.a();
            }
        } catch (Throwable th) {
            throw new IllegalArgumentException("please Depend on one of gson jackson or fastJson ", th);
        }
    }

    public static boolean isAppForeground() {
        return b.f1113a.mAppStateService.isAppForeground();
    }

    public static boolean isBoe() {
        return c.i();
    }

    public static boolean isDebug() {
        return b.f1113a.mDebug;
    }

    public static boolean isEmptyConfig(String str) {
        return TextUtils.isEmpty(str) || "{}".equals(str) || HttpUrl.PATH_SEGMENT_ENCODE_SET_URI.equals(str) || "[{}]".equals(str);
    }

    public static boolean isInited() {
        return b.f1113a.mHasInited;
    }

    public static void setDebug(boolean z) {
        b.f1113a.mDebug = z;
    }

    public static void setPluginConfigVersion(String str) {
        b.f1113a.mPluginConfigVersion = str;
    }

    public static void setSdkVersion(String str) {
        b.f1113a.mSdkVersion = str;
    }

    public static void updateServiceTime(Map<String, String> map) {
        b.f1113a.mServerTimeHolder.a(map);
    }
}
