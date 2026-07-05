package com.sq.tool.network;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.sdk.sq.net.gateway.GateWayUtils;
import com.sqwan.common.util.SQContextWrapper;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GateWayManager {
    private static final String DEFAULT_KEY = "soC2GAr8jN2fsbry";
    private static final String DEFAULT_VERSION = "";
    public static String SP_KEY_FIXED_KEY = "sq_gate_way_key";
    public static String SP_KEY_VERSION = "sq_x_version";
    private static final String SQ_PREFS = "sq_prefs";
    private static final HashSet<String> sWhiteList;

    static {
        HashSet<String> hashSet = new HashSet<>();
        sWhiteList = hashSet;
        hashSet.add("https://sdk-apix-secure.37.com.cn/server-info-service/get-url");
    }

    public static void saveKey(String str) {
        SharedPreferences.Editor editorEdit = SQContextWrapper.getApplicationContext().getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(SP_KEY_FIXED_KEY, str);
        editorEdit.apply();
    }

    public static String getKey() {
        return SQContextWrapper.getApplicationContext().getSharedPreferences(SQ_PREFS, 0).getString(SP_KEY_FIXED_KEY, DEFAULT_KEY);
    }

    public static void saveXVersion(String str) {
        SharedPreferences.Editor editorEdit = SQContextWrapper.getApplicationContext().getSharedPreferences(SQ_PREFS, 0).edit();
        editorEdit.putString(SP_KEY_VERSION, str);
        editorEdit.apply();
    }

    public static String getXVersion() {
        return SQContextWrapper.getApplicationContext().getSharedPreferences(SQ_PREFS, 0).getString(SP_KEY_VERSION, "");
    }

    public static void updateWhiteList(Map<String, String> map) {
        sWhiteList.clear();
        for (String str : map.values()) {
            if (str.contains("-secure")) {
                sWhiteList.add(str);
            }
        }
    }

    public static void addWhiteListUrl(String str) {
        if (str.contains("-secure")) {
            sWhiteList.add(str);
        }
    }

    public static HashSet<String> getWhiteList() {
        return sWhiteList;
    }

    public static void updateKeySet(JSONObject jSONObject) {
        String strFindSecureKey = findSecureKey(jSONObject);
        try {
            if (TextUtils.isEmpty(strFindSecureKey)) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject(strFindSecureKey);
            String strOptString = jSONObject2.optString("X-Request-AppKey");
            String strOptString2 = jSONObject2.optString("X-Request-AppSecret");
            String strOptString3 = jSONObject2.optString("X-Request-Version");
            saveKey(GateWayUtils.getFixedKey(strOptString, strOptString2));
            saveXVersion(strOptString3);
        } catch (JSONException e) {
            e.printStackTrace();
            saveKey(DEFAULT_KEY);
            saveXVersion("");
        }
    }

    private static String findSecureKey(JSONObject jSONObject) {
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("api_infos");
            if (jSONArrayOptJSONArray == null) {
                return null;
            }
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if ("x_secure_key".equals(jSONObjectOptJSONObject.optString("api_key"))) {
                    return jSONObjectOptJSONObject.optString("api_info");
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
