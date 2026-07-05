package com.bytedance.sdk.openadsdk.api.plugin;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.sdk.openadsdk.AdConfig;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTCustomController;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.mobile.auth.gatewayauth.Constant;
import com.parameters.performfeatureconfig.PerformFeatureKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class c {
    private static SharedPreferences b;
    private static final List<Pair<String, JSONObject>> c = new CopyOnWriteArrayList();
    static final Map<String, String> a = new HashMap();

    public static void a(int i, String str, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(MediationConstant.EXTRA_DURATION, Long.valueOf(j));
            jSONObject.putOpt("code", Integer.valueOf(i));
            jSONObject.putOpt(com.igexin.push.core.b.Z, str);
            jSONObject.putOpt("load_record", b.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        e("plugin_load_failed", jSONObject);
    }

    public static void a(String str, JSONObject jSONObject) {
        d("zeus_" + str, jSONObject);
    }

    public static void a() {
        if (c.size() <= 0) {
            return;
        }
        try {
            for (Pair<String, JSONObject> pair : c) {
                if (pair != null) {
                    d((String) pair.first, (JSONObject) pair.second);
                }
            }
            c.clear();
        } catch (Exception unused) {
        }
    }

    private static void d(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        TTAdManager adManager = TTAdSdk.getAdManager();
        if (adManager != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("action", 1);
            bundle.putString("event_name", str);
            bundle.putString("event_extra", jSONObject.toString());
            adManager.getExtra(Bundle.class, bundle);
            return;
        }
        e(str, jSONObject);
    }

    private static void e(final String str, final JSONObject jSONObject) {
        com.bytedance.sdk.openadsdk.e.a.a().b(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.c.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                ArrayList arrayList = new ArrayList();
                arrayList.add(c.f(str, jSONObject));
                c.b(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static JSONObject f(String str, JSONObject jSONObject) {
        String str2 = "6.3.1.0";
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("os_api", Build.VERSION.SDK_INT);
            jSONObject.put("support_abi", Arrays.toString(Build.VERSION.SDK_INT >= 21 ? Build.SUPPORTED_ABIS : new String[]{Build.CPU_ABI, Build.CPU_ABI2}));
            jSONObject2.put("ad_sdk_version", "6.3.1.0");
            String strA = e.a("com.byted.pangle");
            if (!TextUtils.isEmpty(strA)) {
                str2 = strA;
            }
            jSONObject2.put(PluginConstants.KEY_PLUGIN_VERSION, str2);
            jSONObject2.put("timestamp", System.currentTimeMillis() / 1000);
            jSONObject2.put("is_plugin", true);
            jSONObject.put("appid", a.get("appid"));
            jSONObject2.put("event_extra", jSONObject != null ? jSONObject.toString() : "");
            jSONObject2.put("type", str);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("model", Build.MODEL);
            jSONObject3.put(Constant.LOGIN_ACTIVITY_VENDOR_KEY, Build.MANUFACTURER);
            jSONObject3.put("imei", a.get("imei"));
            jSONObject3.put(PerformFeatureKey.KEY_OAID, a.get(PerformFeatureKey.KEY_OAID));
            jSONObject2.put("device_info", jSONObject3);
        } catch (JSONException unused) {
        }
        return jSONObject2;
    }

    public static void b(String str, JSONObject jSONObject) {
        c.add(new Pair<>(str, jSONObject));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(List<JSONObject> list) throws Throwable {
        if (list == null) {
            return;
        }
        if (b == null) {
            b = TTAppContextHolder.getContext().getSharedPreferences("tt_sdk_settings_other", 0);
        }
        String str = String.format("https://%s%s", b.getString("url_stats", "api-access.pangolin-sdk-toutiao.com"), "/api/ad/union/sdk/stats/batch/");
        JSONObject jSONObject = new JSONObject();
        try {
            if (c.size() > 0) {
                for (Pair<String, JSONObject> pair : c) {
                    list.add(f((String) pair.first, (JSONObject) pair.second));
                }
                c.clear();
            }
            jSONObject.put("stats_list", new JSONArray((Collection) list));
        } catch (Exception unused) {
        }
        com.bytedance.sdk.openadsdk.api.plugin.b.c.a().a(true, str, com.bytedance.sdk.openadsdk.api.plugin.c.b.a(jSONObject).toString().getBytes());
    }

    public static void a(AdConfig adConfig) {
        String str;
        if (adConfig == null) {
            return;
        }
        a.put("appid", adConfig.getAppId());
        int pluginUpdateConfig = adConfig.getPluginUpdateConfig();
        Map<String, String> map = a;
        if (pluginUpdateConfig != 0) {
            str = pluginUpdateConfig + "";
        } else {
            str = "2";
        }
        map.put("plugin_update_conf", str);
        TTCustomController customController = adConfig.getCustomController();
        if (customController != null) {
            try {
                a.put(PerformFeatureKey.KEY_OAID, customController.getDevOaid());
                a.put("imei", customController.getDevImei());
            } catch (Exception unused) {
            }
        }
    }
}
