package com.sq.track;

import android.content.Context;
import android.text.TextUtils;
import cn.thinkingdata.android.ThinkingAnalyticsSDK;
import com.sqwan.common.dev.DevLogic;
import com.sqwan.common.dev.ImeiLogic;
import com.sqwan.common.dev.MacLogic;
import com.sqwan.common.track.SqTrackUtil;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.config.ConfigManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public enum ThinkingDataManager {
    INSTANCE;

    private static final String TAG = "ThinkingDataManager";
    private final HashMap<String, HashMap<String, String>> cache = new HashMap<>();
    private Context context;
    private ThinkingAnalyticsSDK sdkInstance;

    ThinkingDataManager() {
    }

    public static ThinkingDataManager getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        LogUtil.i(TAG, "数数进行初始化");
        this.context = context;
        ThinkingAnalyticsSDK thinkingAnalyticsSDKSharedInstance = ThinkingAnalyticsSDK.sharedInstance(context, "8de79ea48b924c6eabbfae0ae868f1a9", "https://ta.shan-yu-tech.com");
        this.sdkInstance = thinkingAnalyticsSDKSharedInstance;
        thinkingAnalyticsSDKSharedInstance.setDynamicSuperPropertiesTracker(new ThinkingAnalyticsSDK.DynamicSuperPropertiesTracker() { // from class: com.sq.track.ThinkingDataManager.1
            @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.DynamicSuperPropertiesTracker
            public JSONObject getDynamicSuperProperties() {
                return ThinkingDataManager.this.getDynamicSuperPropertiesTracker();
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

    public void track(String str, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.sdkInstance == null) {
            cacheEvent(str, map);
            return;
        }
        LogUtil.i(TAG, "数数埋点, 事件名:" + str);
        if (map == null) {
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

    private void cacheEvent(String str, HashMap<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        LogUtil.i(TAG, "数数初始化前,缓存事件: " + str);
        this.cache.put(str, map);
    }

    private void pushCacheEvents() {
        if (this.cache.isEmpty() || this.sdkInstance == null) {
            return;
        }
        LogUtil.i(TAG, "上报数数缓存事件");
        for (Map.Entry<String, HashMap<String, String>> entry : this.cache.entrySet()) {
            if (!TextUtils.isEmpty(entry.getKey())) {
                track(entry.getKey(), entry.getValue());
            }
        }
        this.cache.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getDynamicSuperPropertiesTracker() {
        JSONObject jSONObject = new JSONObject();
        try {
            SQAppConfig sQAppConfig = ConfigManager.getInstance(this.context).getSQAppConfig();
            jSONObject.put("gid", sQAppConfig.getGameid());
            jSONObject.put("pid", sQAppConfig.getPartner());
            jSONObject.put("refer", sQAppConfig.getRefer());
            jSONObject.put("sversion", VersionUtil.getSdkVersion());
            String value = DevLogic.getInstance(this.context).getFromCache() == null ? "" : DevLogic.getInstance(this.context).getFromCache().getValue();
            String value2 = ImeiLogic.getInstance(this.context).getFromCache() == null ? "" : ImeiLogic.getInstance(this.context).getFromCache().getValue();
            String value3 = MacLogic.getInstance(this.context).getFromCache() == null ? "" : MacLogic.getInstance(this.context).getFromCache().getValue();
            jSONObject.put("dev", value);
            jSONObject.put("imei", value2);
            jSONObject.put("mac", value3);
            jSONObject.put("oaid", DeviceUtils.getOaid(this.context));
            jSONObject.put("gwversion", "4.6.7");
            jSONObject.put("version", SqTrackUtil.getVersionCode(this.context) + "");
            if (SqTrackUtil.getLogined(this.context)) {
                jSONObject.put("uid", SqTrackUtil.getUserid(this.context));
                jSONObject.put("uname", SqTrackUtil.getUsername(this.context));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
