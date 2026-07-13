package com.volcengine.common.sdkmonitor;

import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import com.tencent.connect.common.Constants;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.cloudphone.base.CloudConfig;
import com.volcengine.common.SDKContext;
import com.volcengine.common.contant.CommonConstants;
import com.volcengine.common.innerapi.ConfigService;
import com.volcengine.common.innerapi.ISDKMonitor;
import com.volcengine.common.innerapi.MonitorService;
import com.volcengine.common.innerapi.PluginService;
import com.volcengine.j.j;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class b implements MonitorService, ConfigService.ConfigObserver, PluginService.ILoadResultListener {
    private long d;
    private final String c = "isid_" + UUID.randomUUID().toString();
    private String e = "sid_" + UUID.randomUUID().toString();
    private final Map<String, Object> g = new ConcurrentHashMap();
    private final Set<String> f = new HashSet();
    private final SparseBooleanArray h = new SparseBooleanArray();
    private final SparseLongArray i = new SparseLongArray();
    private final a j = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<String> f1127a = new HashSet();
    private final SparseIntArray b = new SparseIntArray();

    public b() {
        SDKContext.getConfigService().register("monitor_config", this);
        SDKContext.getPluginService().addLoadResultListener(this);
    }

    private void a(String str, Object obj) {
        if (obj != null && str != null) {
            this.g.put(str, obj);
            return;
        }
        AcLog.w("MonitorService", "addCommonExtra: key=" + str + ", value=" + obj);
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void addLowPriorityEvents(String... strArr) {
        if (strArr != null) {
            this.f1127a.addAll(Arrays.asList(strArr));
        }
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void addMonitor(ISDKMonitor iSDKMonitor) {
        this.j.a(iSDKMonitor);
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void addSessionExtra(String str, String str2) {
        if (str2 != null && str != null) {
            this.g.put(str, str2);
            this.f.add(str);
            return;
        }
        AcLog.w("MonitorService", "addSessionExtra: key=" + str + ", value=" + str2);
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void beginSession(int i, CloudConfig cloudConfig) {
        String str;
        this.d = System.currentTimeMillis();
        if (i == 1) {
            addSessionExtra("customGameId", cloudConfig.getCustomGameId());
            addSessionExtra("userId", cloudConfig.getUserId());
            addSessionExtra(CommonConstants.key_gameId, cloudConfig.getGameId());
            addSessionExtra("planId", cloudConfig.getConfigurationCode());
            addSessionExtra("productId", cloudConfig.getProductId());
            addSessionExtra("roundId", cloudConfig.getRoundId());
            str = CommonConstants.GAME_TYPE;
        } else {
            if (i != 2) {
                return;
            }
            addSessionExtra("userId", cloudConfig.getUserId());
            addSessionExtra("planId", cloudConfig.getConfigurationCode());
            addSessionExtra("productId", cloudConfig.getProductId());
            addSessionExtra("roundId", cloudConfig.getRoundId());
            addSessionExtra(CommonConstants.key_appId, cloudConfig.getPhoneAppId());
            str = CommonConstants.PHONE_TYPE;
        }
        a("volcProduct", str);
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public boolean checkReportStateInSession(String str) {
        int iHashCode = str.hashCode();
        boolean z = this.h.get(iHashCode, false);
        if (!z) {
            this.h.put(iHashCode, true);
        }
        return z;
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public boolean checkReportStateInTime(String str, int i) {
        int iHashCode = str.hashCode();
        long j = this.i.get(iHashCode, -1L);
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (jUptimeMillis - j <= ((long) i) * 1000) {
            return true;
        }
        this.i.put(iHashCode, jUptimeMillis);
        return false;
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void endSession(int i, boolean z) {
        Iterator<String> it = this.f.iterator();
        while (it.hasNext()) {
            this.g.remove(it.next());
        }
        this.f.clear();
        if (z) {
            String str = "sid_" + UUID.randomUUID().toString();
            this.e = str;
            this.g.put(CommonConstants.key_SessionId, str);
        }
        this.d = 0L;
        this.h.clear();
        this.i.clear();
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public Map<String, Object> getCommonExtra() {
        return this.g;
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public String getCurrentSessionId() {
        return this.e;
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public long getPlayElapsedTime() {
        return System.currentTimeMillis() - this.d;
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public long getPlayTime() {
        return this.d;
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void init(int i) {
        HashMap map = new HashMap();
        map.put(MonitorConstants.KEY_DEVICE_ID, SDKContext.getDid());
        map.put(MonitorConstants.HOST_APP_ID, "101");
        map.put(Constants.JumpUrlConstants.URL_KEY_SDK_VERSION, SDKContext.getSdkVersion());
        map.put("sdk_version_name", SDKContext.getSdkVersion());
        a(CommonConstants.key_SDKVersion, SDKContext.getSdkVersion());
        a(CommonConstants.key_OS, CommonConstants.VALUE_OS);
        a(CommonConstants.key_OSVersion, Build.VERSION.RELEASE);
        a(CommonConstants.key_DID, SDKContext.getDid());
        a(CommonConstants.key_UUID, SDKContext.getUUId());
        a(CommonConstants.key_initSessionId, this.c);
        a(CommonConstants.key_accountId, SDKContext.getAccountId());
        a(CommonConstants.key_SessionId, this.e);
        this.j.a(i, map);
        com.volcengine.i.a.d();
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public boolean isLowPriorityEvent(String str) {
        return this.f1127a.contains(str);
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void onEvent(String str, JSONObject jSONObject) {
        this.j.a(str, jSONObject);
    }

    @Override // com.volcengine.common.innerapi.PluginService.ILoadResultListener
    public void onLoadFailed(int i, String str) {
    }

    @Override // com.volcengine.common.innerapi.PluginService.ILoadResultListener
    public void onLoadSuccess() {
        SDKContext.getPluginService().removeLoadResultListener(this);
        JSONObject configJson = SDKContext.getConfigService().getConfigJson("monitor_config");
        this.j.a(configJson.optInt("provider", 0));
        JSONArray jSONArrayOptJSONArray = configJson.optJSONArray("low_priority");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                try {
                    String string = jSONArrayOptJSONArray.getString(i);
                    if (!TextUtils.isEmpty(string)) {
                        this.f1127a.add(string);
                    }
                } catch (JSONException e) {
                    AcLog.w("MonitorService", "onLoadSuccess: low_priority: " + Log.getStackTraceString(e));
                }
            }
        }
        JSONObject jSONObjectOptJSONObject = configJson.optJSONObject("cyclic_event");
        if (jSONObjectOptJSONObject != null) {
            Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                try {
                    this.b.put(next.hashCode(), jSONObjectOptJSONObject.getInt(next));
                } catch (JSONException e2) {
                    AcLog.w("MonitorService", "onLoadSuccess: cyclic_event: " + Log.getStackTraceString(e2));
                }
            }
        }
    }

    @Override // com.volcengine.common.innerapi.ConfigService.ConfigObserver
    public void onReceiveConfig(String str, String str2) {
        AcLog.v("MonitorService", "onReceiveConfig: configName = [" + str + "], config = [" + str2 + "]");
        if ("monitor_config".equals(str)) {
            ConfigService configService = SDKContext.getConfigService();
            configService.unregister("monitor_config", this);
            SDKContext.getAppStateService().reportAppState(configService.getConfigJson(str).optBoolean(MonitorService.APP_STATE_REPORT, false));
        }
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void removeMonitor(ISDKMonitor iSDKMonitor) {
        this.j.b(iSDKMonitor);
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void reportCategory(String str, int i, String str2) {
        HashMap map = new HashMap();
        map.put("code", Integer.valueOf(i));
        map.put(CommonConstants.KEY_MESSAGE, str2);
        reportCommon(str, map, Collections.emptyMap(), Collections.emptyMap());
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void reportCategory(String str, Map<String, Object> map) {
        reportCommon(str, map, Collections.emptyMap(), Collections.emptyMap());
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void reportCommon(String str, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3) {
        int iIndexOfKey = this.b.indexOfKey(str.hashCode());
        if (iIndexOfKey >= 0) {
            int iValueAt = this.b.valueAt(iIndexOfKey);
            if (iValueAt == -1) {
                if (checkReportStateInSession(str)) {
                    AcLog.w("MonitorService", "reportCommon: block[-1] " + str);
                    return;
                }
            } else if (iValueAt > 0 && checkReportStateInTime(str, iValueAt)) {
                AcLog.w("MonitorService", "reportCommon: block[" + iValueAt + "] " + str);
                return;
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - this.d;
        HashMap map4 = new HashMap(this.g);
        map4.putAll(map3);
        map4.put("ts", Long.valueOf(jCurrentTimeMillis));
        map4.put(CommonConstants.key_durationFromStart, Long.valueOf(j));
        AcLog.d("Event", "event : " + str + ", category : " + map + ", metric : " + map2 + ", extra : " + map4);
        this.j.a(str, j.b(map), j.b(map2), j.b(map4));
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void reportError(String str, int i, String str2, int i2, String str3) {
        HashMap map = new HashMap();
        map.put(CommonConstants.KEY_ERROR_CODE, Integer.valueOf(i));
        map.put(CommonConstants.KEY_ERR_MSG, str2);
        map.put(CommonConstants.KEY_ORIGIN_ERR_CODE, Integer.valueOf(i2));
        map.put(CommonConstants.KEY_ORIGIN_ERR_MSG, str3);
        reportCommon(str, map, Collections.emptyMap(), Collections.emptyMap());
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void reportError(String str, int i, String str2, int i2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put(CommonConstants.KEY_ERROR_CODE, Integer.valueOf(i));
        map.put(CommonConstants.KEY_ERR_MSG, str2);
        map.put(CommonConstants.KEY_ORIGIN_ERR_CODE, Integer.valueOf(i2));
        map.put(CommonConstants.KEY_ORIGIN_ERR_MSG, str3);
        map.put("level", str4);
        reportCommon(str, map, Collections.emptyMap(), Collections.emptyMap());
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void reportMetric(String str, Map<String, Object> map) {
        reportCommon(str, Collections.emptyMap(), map, Collections.emptyMap());
    }

    @Override // com.volcengine.common.innerapi.MonitorService
    public void reportOnlyEvent(String str) {
        reportCategory(str, Collections.singletonMap(CommonConstants.M_NORMAl_STATE, ""));
    }
}
