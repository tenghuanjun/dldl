package com.sq.track;

import android.content.Context;
import android.text.TextUtils;
import cn.thinkingdata.android.ThinkingAnalyticsSDK;
import com.sq.tools.utils.HardwareUtils;
import com.sqwan.common.data.cache.SpRequestInfo;
import com.sqwan.common.net.base.RequestUtil;
import com.sqwan.common.track.SqTrackCommonKey;
import com.sqwan.common.track.SqTrackUtil;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.EnvironmentUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.NetWorkUtils;
import com.sqwan.common.util.VersionUtil;
import com.taptap.sdk.db.constant.Common;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum ThinkingDataManager2 {
    INSTANCE;

    private static final String DEBUG_FILE = "track-test";
    private static final String TAG = "ThinkingDataManager2";
    private static final String TA_APP_ID = "f271c0ab7bcf4481b35fa1916850ab19";
    private static final String TA_APP_ID_DEBUG = "debug-appid";
    private static final String TA_SERVER_URL = "https://ta.shan-yu-tech.com";
    private final List<CacheEvent> cacheEvents = new ArrayList();
    private Context mContext;
    private ThinkingAnalyticsSDK sdkInstance;

    ThinkingDataManager2() {
    }

    public static ThinkingDataManager2 getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        LogUtil.i(TAG, "数数进行初始化");
        this.mContext = context;
        ThinkingAnalyticsSDK.enableTrackLog(isDebugModeByFile(context));
        ThinkingAnalyticsSDK thinkingAnalyticsSDKSharedInstance = ThinkingAnalyticsSDK.sharedInstance(context, TA_APP_ID, TA_SERVER_URL);
        this.sdkInstance = thinkingAnalyticsSDKSharedInstance;
        thinkingAnalyticsSDKSharedInstance.setSuperProperties(getSuperProperties());
        this.sdkInstance.setDynamicSuperPropertiesTracker(new ThinkingAnalyticsSDK.DynamicSuperPropertiesTracker() { // from class: com.sq.track.ThinkingDataManager2.1
            @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.DynamicSuperPropertiesTracker
            public JSONObject getDynamicSuperProperties() {
                return ThinkingDataManager2.this.getDynamicSuperPropertiesTracker();
            }
        });
        ArrayList arrayList = new ArrayList();
        arrayList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_INSTALL);
        arrayList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_START);
        arrayList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_END);
        arrayList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_VIEW_SCREEN);
        arrayList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_CLICK);
        arrayList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_CRASH);
        this.sdkInstance.enableAutoTrack(arrayList);
        pushCacheEvents();
    }

    public void track(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.sdkInstance == null) {
            cacheEvent(str, map);
            return;
        }
        if (map == null) {
            LogUtil.i(TAG, "数数埋点, 事件名:" + str + " 无参数");
            this.sdkInstance.track(str);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey())) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
            LogUtil.i(TAG, "数数埋点, 事件名:" + str + " 参数 " + jSONObject.toString());
            this.sdkInstance.track(str, jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUserId(String str) {
        if (this.sdkInstance == null || TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.i(TAG, "设置数数账户ID: " + str);
        this.sdkInstance.login(str);
    }

    public void userSet(String str, String str2) {
        if (this.sdkInstance == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(str, str2);
            LogUtil.i(TAG, "设置数数普通用户属性 " + str + " : " + str2);
            this.sdkInstance.user_set(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void userSetOnce(String str, String str2) {
        if (this.sdkInstance == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(str, str2);
            LogUtil.i(TAG, "设置数数永久用户属性 " + str + " : " + str2);
            this.sdkInstance.user_setOnce(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cacheEvent(String str, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.i(TAG, "数数初始化前,缓存事件: " + str);
        this.cacheEvents.add(new CacheEvent(str, map));
    }

    private void pushCacheEvents() {
        if (this.cacheEvents.isEmpty() || this.sdkInstance == null) {
            return;
        }
        LogUtil.i(TAG, "上报数数缓存事件");
        for (CacheEvent cacheEvent : this.cacheEvents) {
            if (cacheEvent != null && !TextUtils.isEmpty(cacheEvent.getEvent())) {
                track(cacheEvent.getEvent(), cacheEvent.getEventParams());
            }
        }
        this.cacheEvents.clear();
    }

    public void flush() {
        ThinkingAnalyticsSDK thinkingAnalyticsSDK = this.sdkInstance;
        if (thinkingAnalyticsSDK == null) {
            return;
        }
        thinkingAnalyticsSDK.flush();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getDynamicSuperPropertiesTracker() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("network_type", DeviceUtils.getNetWorkType(this.mContext));
            jSONObject.put("carrier", DeviceUtils.getCarrier(this.mContext));
            jSONObject.put("ip", DeviceUtils.getIpAddress(this.mContext));
            jSONObject.put("event_time", System.currentTimeMillis() + "");
            jSONObject.put("mac", DeviceUtils.getMac(this.mContext));
            jSONObject.put("imei", DeviceUtils.getIMEI(this.mContext));
            jSONObject.put("dev", DeviceUtils.getDev(this.mContext));
            jSONObject.put("android_id", DeviceUtils.getAndroidId(this.mContext));
            jSONObject.put("oaid", DeviceUtils.getOaid(this.mContext));
            jSONObject.put("gid", SqTrackUtil.getGameID(this.mContext));
            jSONObject.put("refer", SqTrackUtil.getRefer(this.mContext));
            jSONObject.put("cid", SqTrackUtil.getChannelId(this.mContext));
            jSONObject.put("pid", SqTrackUtil.getPaternerID(this.mContext));
            jSONObject.put("sversion", VersionUtil.getSdkVersion());
            jSONObject.put("gwversion", "4.6.7");
            jSONObject.put("original_sversion", VersionUtil.getOriginalVersion());
            jSONObject.put("plugin_version", VersionUtil.getPluginVersion(this.mContext));
            jSONObject.put("request_liveid", SpRequestInfo.getRequestLiveId(this.mContext));
            jSONObject.put("request_id", RequestUtil.generateRequestId());
            jSONObject.put("scut", SqTrackUtil.getScut(this.mContext));
            jSONObject.put("isProxy", NetWorkUtils.isWifiProxy());
            if (SqTrackUtil.getLogined(this.mContext)) {
                jSONObject.put("uid", SqTrackUtil.getUserid(this.mContext));
                jSONObject.put("uname", SqTrackUtil.getUsername(this.mContext));
                jSONObject.put("role_id", SqTrackUtil.getRoleid(this.mContext));
                jSONObject.put("role_name", SqTrackUtil.getRolename(this.mContext));
                jSONObject.put("role_level", SqTrackUtil.getRolelevel(this.mContext));
                jSONObject.put("vip_level", SqTrackUtil.getVipLevel(this.mContext));
                jSONObject.put("server_id", SqTrackUtil.getServerid(this.mContext));
                jSONObject.put("server_name", SqTrackUtil.getServerName(this.mContext));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public JSONObject getSuperProperties() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("os", DeviceUtils.isHarmony() ? "4" : "1");
            jSONObject.put("os_version", DeviceUtils.getOSVersion());
            jSONObject.put("country", DeviceUtils.getCountry());
            jSONObject.put("country_code", DeviceUtils.getCountryCode());
            jSONObject.put("province", "");
            jSONObject.put("city", "");
            jSONObject.put("phone_brand", DeviceUtils.getBrand());
            jSONObject.put("phone_model", DeviceUtils.getModel());
            Map<String, String> displayMetrics = DeviceUtils.getDisplayMetrics(this.mContext);
            jSONObject.put("pixel", displayMetrics.get("dpi"));
            jSONObject.put("screen_height", displayMetrics.get("height"));
            jSONObject.put("screen_width", displayMetrics.get(Common.Predefined.SUB_WIDTH));
            jSONObject.put("ram", DeviceUtils.getTotalRam() + "");
            jSONObject.put("sd_memory", DeviceUtils.getTotalExternalMemorySize() + "");
            jSONObject.put("cpu_hardware", DeviceUtils.getCpuHardware());
            jSONObject.put("cpu_Ghz", DeviceUtils.getMaxCpuFreq());
            jSONObject.put("cpu_core", DeviceUtils.getCpuCore() + "");
            jSONObject.put("cpu_is_x86", DeviceUtils.getCpuName());
            jSONObject.put("sim", DeviceUtils.getSIM(this.mContext) + "");
            jSONObject.put(SqTrackCommonKey.last_os_update_ts, DeviceUtils.getBootTime() + "");
            jSONObject.put("apk_name", AppUtils.getPackageName(this.mContext));
            jSONObject.put("game_name", AppUtils.getAppName(this.mContext));
            jSONObject.put("install_time", AppUtils.getAppInstallTime(this.mContext));
            jSONObject.put("last_update_time", AppUtils.getAppUpdateTime(this.mContext));
            jSONObject.put("isSimulator", DeviceUtils.isSimulator(this.mContext) + "");
            jSONObject.put("version", SqTrackUtil.getVersionCode(this.mContext) + "");
            jSONObject.put("version_name", SqTrackUtil.getVersionName(this.mContext));
            jSONObject.put("target_version", DeviceUtils.getTargetVersion(this.mContext));
            jSONObject.put("cpu_abi", HardwareUtils.getCpuArch());
            jSONObject.put(SqTrackCommonKey.isRoot, DeviceUtils.isRootDevice());
            jSONObject.put("cpu_info", DeviceUtils.readCpuInfo());
            jSONObject.put("channel_sdk_version", SqTrackUtil.getChannelSdkVersion(this.mContext));
            jSONObject.put("channel_name", SqTrackUtil.getChannelName(this.mContext));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private static boolean isDebugModeByFile(Context context) {
        String str = EnvironmentUtils.getCommonDirPath(context) + File.separator + DEBUG_FILE;
        LogUtil.i(str);
        File file = new File(str);
        return file.exists() && file.isDirectory();
    }
}
