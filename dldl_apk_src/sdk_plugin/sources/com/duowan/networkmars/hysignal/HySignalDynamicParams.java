package com.duowan.networkmars.hysignal;

import android.text.TextUtils;
import com.duowan.auk.util.L;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HySignalDynamicParams {
    private static final String KEY_CHANNEL = "adr_game_sdk_mars_channel";
    private static final String KEY_LIMITFLOW = "adr_game_sdk_mars_limitFlow";
    private static final String KEY_LIMITFREQUENCY = "adr_game_sdk_mars_limitFrequency";
    private static final String KEY_NETWORKSTATUSSENSITIVE = "adr_game_sdk_mars_networkStatusSensitive";
    private static final String KEY_RETRYCOUNT = "adr_game_sdk_mars_retryCount";
    private static final String KEY_TOTALTIMEOUT = "adr_game_sdk_mars_totalTimeout";
    private static final String TAG = "HySignalDynamicParams";
    private static volatile HySignalDynamicParams mInstance;
    private String channelJson;
    private String limitFlowJson;
    private String limitFrequencyJson;
    private String networkStatusSensitiveJson;
    private String retryCountJson;
    private String totalTimeoutJson;

    private HySignalDynamicParams() {
    }

    public static HySignalDynamicParams getInstance() {
        if (mInstance == null) {
            synchronized (HySignalDynamicParams.class) {
                if (mInstance == null) {
                    mInstance = new HySignalDynamicParams();
                }
            }
        }
        return mInstance;
    }

    public void init(Map<String, String> map) {
        this.channelJson = map.get(KEY_CHANNEL);
        this.retryCountJson = map.get(KEY_RETRYCOUNT);
        this.limitFlowJson = map.get(KEY_LIMITFLOW);
        this.limitFrequencyJson = map.get(KEY_LIMITFREQUENCY);
        this.totalTimeoutJson = map.get(KEY_TOTALTIMEOUT);
        this.networkStatusSensitiveJson = map.get(KEY_NETWORKSTATUSSENSITIVE);
    }

    public int getChannel(String str) {
        if (TextUtils.isEmpty(str) || !isJSON(this.channelJson)) {
            return 3;
        }
        return parseJSON(this.channelJson, str, 3);
    }

    public int getRetryCount(String str) {
        if (TextUtils.isEmpty(str) || !isJSON(this.retryCountJson)) {
            return 1;
        }
        return parseJSON(this.retryCountJson, str, 1);
    }

    public boolean getLimitFlow(String str) {
        if (TextUtils.isEmpty(str) || !isJSON(this.limitFlowJson)) {
            return true;
        }
        return parseJSON(this.limitFlowJson, str, true);
    }

    public boolean getLimitFrequency(String str) {
        if (TextUtils.isEmpty(str) || !isJSON(this.limitFrequencyJson)) {
            return true;
        }
        return parseJSON(this.limitFrequencyJson, str, true);
    }

    public int getTotalTimeout(String str) {
        if (TextUtils.isEmpty(str) || !isJSON(this.totalTimeoutJson)) {
            return 0;
        }
        return parseJSON(this.totalTimeoutJson, str, 0);
    }

    public boolean getNetworkStatusSensitive(String str) {
        if (TextUtils.isEmpty(str) || !isJSON(this.networkStatusSensitiveJson)) {
            return false;
        }
        return parseJSON(this.networkStatusSensitiveJson, str, false);
    }

    private boolean isJSON(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            new JSONObject(str);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int parseJSON(String str, String str2, int i) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("default");
            if (!TextUtils.isEmpty(strOptString)) {
                i = Integer.parseInt(strOptString);
            }
            String strOptString2 = jSONObject.optString(str2);
            return !TextUtils.isEmpty(strOptString2) ? Integer.parseInt(strOptString2) : i;
        } catch (Throwable th) {
            L.error(TAG, "parseJSON int error" + th);
            return i;
        }
    }

    private boolean parseJSON(String str, String str2, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("default");
            if (!TextUtils.isEmpty(strOptString)) {
                z = Boolean.parseBoolean(strOptString);
            }
            String strOptString2 = jSONObject.optString(str2);
            return !TextUtils.isEmpty(strOptString2) ? Boolean.parseBoolean(strOptString2) : z;
        } catch (Throwable th) {
            L.error(TAG, "parseJSON boolean error" + th);
            return z;
        }
    }
}
