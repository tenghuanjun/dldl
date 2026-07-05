package com.sy37sdk.account.uagree;

import android.content.Context;
import android.text.TextUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SpUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UAgreeCacheHelper {
    private static final String SP_KEY_HISTORY_VERSION = "sp_key_history_version";
    private static final String SP_KEY_URL_INTERIM = "sp_key_url_interim";
    private static final String SP_KEY_URL_POLICY = "sp_key_url_policy";
    private static final String SP_KEY_URL_PROTOCOL = "sp_key_url_protocol";

    public static int getVersion(Context context, String str) {
        Map<String, Integer> versionFromSP = getVersionFromSP(context);
        if (versionFromSP.containsKey(str.toLowerCase())) {
            return versionFromSP.get(str.toLowerCase()).intValue();
        }
        return 0;
    }

    public static void setVersion(Context context, String str, int i) {
        Map<String, Integer> versionFromSP = getVersionFromSP(context);
        versionFromSP.put(str.toLowerCase(), Integer.valueOf(i));
        saveVersionToSP(context, versionFromSP);
    }

    private static Map<String, Integer> getVersionFromSP(Context context) {
        HashMap map = new HashMap();
        try {
            LogUtil.i("尝试读取用户协议版本string类型");
            String string = SpUtils.get(context).getString(SP_KEY_HISTORY_VERSION);
            LogUtil.i("尝试读取用户协议版本string类型成功，" + string);
            if (!TextUtils.isEmpty(string)) {
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        map.put(next, Integer.valueOf(jSONObject.getInt(next)));
                    }
                } catch (JSONException e) {
                    LogUtil.e("读取用户协议历史版本出错！");
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            LogUtil.i("读取用户协议版本string类型出错，尝试读取int类型");
            try {
                LogUtil.i("尝试读取用户协议版本int类型");
                int i = SpUtils.get(context).getInt(SP_KEY_HISTORY_VERSION);
                LogUtil.i("读取用户协议版本int类型成功 " + i);
                map.put("permission", Integer.valueOf(i));
            } catch (Exception e3) {
                e3.printStackTrace();
                LogUtil.i("读取用户协议版本int类型也失败 ");
            }
        }
        return map;
    }

    private static void saveVersionToSP(Context context, Map<String, Integer> map) {
        JSONObject jSONObject = new JSONObject(map);
        LogUtil.i("version json --> " + jSONObject.toString());
        SpUtils.get(context).put(SP_KEY_HISTORY_VERSION, jSONObject.toString());
    }

    public static void setUrlProtocol(Context context, String str) {
        SpUtils.get(context).put(SP_KEY_URL_PROTOCOL, str);
    }

    public static String getUrlProtocol(Context context) {
        return SpUtils.get(context).getString(SP_KEY_URL_PROTOCOL, "");
    }

    public static void setUrlPolicy(Context context, String str) {
        SpUtils.get(context).put(SP_KEY_URL_POLICY, str);
    }

    public static String getUrlPolicy(Context context) {
        return SpUtils.get(context).getString(SP_KEY_URL_POLICY, "");
    }

    public static void setUrlInterim(Context context, String str) {
        SpUtils.get(context).put(SP_KEY_URL_INTERIM, str);
    }

    public static String getUrlInterim(Context context) {
        return SpUtils.get(context).getString(SP_KEY_URL_INTERIM, "");
    }
}
