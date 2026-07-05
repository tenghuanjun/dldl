package com.sq.tools.report.event.thinkingdata;

import android.content.Context;
import android.text.TextUtils;
import cn.thinkingdata.android.TDConfig;
import cn.thinkingdata.android.ThinkingAnalyticsSDK;
import com.sq.tool.logger.SQLog;
import com.sq.tools.report.event.IEventReporter;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ThinkingDataReporter implements IEventReporter {
    private static final String TAG = "【Report TD】";
    protected String mAppId;
    private boolean mEnable;
    protected String mServerUrl;
    private ThinkingAnalyticsSDK mTA;

    @Override // com.sq.tools.report.event.IEventReporter
    public void setUserConsent(Map<String, Boolean> consent) {
    }

    public ThinkingDataReporter() {
        this(null, null);
    }

    public ThinkingDataReporter(String appId, String serverUrl) {
        this.mAppId = appId;
        this.mServerUrl = serverUrl;
        try {
            ThinkingAnalyticsSDK.class.getName();
            this.mEnable = true;
        } catch (Throwable unused) {
            SQLog.e("【Report TD】SDK不包含数数模块, 不支持数数上报");
        }
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void init(Context context) {
        if (this.mEnable) {
            String str = this.mAppId;
            String str2 = this.mServerUrl;
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                this.mEnable = false;
                SQLog.w("【Report TD】缺少数数配置, 无法使用数数上报");
            } else {
                ThinkingAnalyticsSDK thinkingAnalyticsSDKSharedInstance = ThinkingAnalyticsSDK.sharedInstance(TDConfig.getInstance(context, str, str2));
                this.mTA = thinkingAnalyticsSDKSharedInstance;
                onDefaultConfig(thinkingAnalyticsSDKSharedInstance);
            }
        }
    }

    protected void onDefaultConfig(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_INSTALL);
        arrayList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_START);
        arrayList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_END);
        arrayList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_VIEW_SCREEN);
        arrayList.add(ThinkingAnalyticsSDK.AutoTrackEventType.APP_CRASH);
        thinkingAnalyticsSDK.enableAutoTrack(arrayList);
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void setAccountId(String uid) {
        if (!this.mEnable || this.mTA == null) {
            return;
        }
        if (uid == null || uid.isEmpty()) {
            this.mTA.logout();
        } else {
            this.mTA.login(uid);
        }
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void setSuperProperties(Map<String, Object> properties) {
        ThinkingAnalyticsSDK thinkingAnalyticsSDK;
        if (!this.mEnable || (thinkingAnalyticsSDK = this.mTA) == null) {
            return;
        }
        thinkingAnalyticsSDK.setSuperProperties(mapToJson(properties));
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void setDynamicSuperPropertiesTracker(final IEventReporter.DynamicSuperPropertiesTracker tracker) {
        ThinkingAnalyticsSDK thinkingAnalyticsSDK;
        if (!this.mEnable || (thinkingAnalyticsSDK = this.mTA) == null) {
            return;
        }
        if (tracker == null) {
            thinkingAnalyticsSDK.setDynamicSuperPropertiesTracker(null);
        } else {
            thinkingAnalyticsSDK.setDynamicSuperPropertiesTracker(new ThinkingAnalyticsSDK.DynamicSuperPropertiesTracker() { // from class: com.sq.tools.report.event.thinkingdata.-$$Lambda$ThinkingDataReporter$0n503FEbF_95BXkhG-NobPGtVfI
                @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.DynamicSuperPropertiesTracker
                public final JSONObject getDynamicSuperProperties() {
                    return ThinkingDataReporter.mapToJson(tracker.getDynamicSuperProperties());
                }
            });
        }
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void report(String event, Map<String, Object> properties) {
        ThinkingAnalyticsSDK thinkingAnalyticsSDK;
        if (!this.mEnable || (thinkingAnalyticsSDK = this.mTA) == null) {
            return;
        }
        thinkingAnalyticsSDK.track(event, mapToJson(properties));
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void flush() {
        ThinkingAnalyticsSDK thinkingAnalyticsSDK;
        if (!this.mEnable || (thinkingAnalyticsSDK = this.mTA) == null) {
            return;
        }
        thinkingAnalyticsSDK.flush();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject mapToJson(Map<String, ?> map) {
        if (map == null || map.isEmpty()) {
            return new JSONObject();
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            try {
                jSONObject.put(str, map.get(str));
            } catch (JSONException e) {
                SQLog.e(TAG, e);
            }
        }
        return jSONObject;
    }
}
