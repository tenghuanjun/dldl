package cn.thinkingdata.android;

import android.content.Context;
import android.content.res.Resources;
import cn.thinkingdata.android.utils.TDLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TDPresetProperties {
    private static final String TAG = "ThinkingAnalytics.TDPresetProperties";
    public static final List<String> disableList = new ArrayList();
    public String appVersion;
    public String bundleId;
    public String carrier;
    public String deviceId;
    public String deviceModel;
    public String deviceType;
    public String disk;
    public int fps;
    public String installTime;
    public boolean isSimulator;
    public String manufacture;
    public String networkType;
    public String os;
    public String osVersion;
    private JSONObject presetProperties;
    public String ram;
    public int screenHeight;
    public int screenWidth;
    public String systemLanguage;
    public double zoneOffset;

    public TDPresetProperties() {
    }

    public TDPresetProperties(JSONObject jSONObject) {
        this.presetProperties = jSONObject;
        if (!disableList.contains("#bundle_id")) {
            this.bundleId = jSONObject.optString("#bundle_id");
        }
        if (!disableList.contains("#carrier")) {
            this.carrier = jSONObject.optString("#carrier");
        }
        if (!disableList.contains("#device_id")) {
            this.deviceId = jSONObject.optString("#device_id");
        }
        if (!disableList.contains("#device_model")) {
            this.deviceModel = jSONObject.optString("#device_model");
        }
        if (!disableList.contains("#manufacturer")) {
            this.manufacture = jSONObject.optString("#manufacturer");
        }
        if (!disableList.contains("#network_type")) {
            this.networkType = jSONObject.optString("#network_type");
        }
        if (!disableList.contains("#os")) {
            this.os = jSONObject.optString("#os");
        }
        if (!disableList.contains("#os_version")) {
            this.osVersion = jSONObject.optString("#os_version");
        }
        if (!disableList.contains("#screen_height")) {
            this.screenHeight = jSONObject.optInt("#screen_height");
        }
        if (!disableList.contains("#screen_width")) {
            this.screenWidth = jSONObject.optInt("#screen_width");
        }
        if (!disableList.contains("#system_language")) {
            this.systemLanguage = jSONObject.optString("#system_language");
        }
        if (!disableList.contains("#zone_offset")) {
            this.zoneOffset = jSONObject.optDouble("#zone_offset");
        }
        if (!disableList.contains("#app_version")) {
            this.appVersion = jSONObject.optString("#app_version");
        }
        if (!disableList.contains("#install_time")) {
            this.installTime = jSONObject.optString("#install_time");
        }
        if (!disableList.contains("#simulator")) {
            this.isSimulator = jSONObject.optBoolean("#simulator");
        }
        if (!disableList.contains("#ram")) {
            this.ram = jSONObject.optString("#ram");
        }
        if (!disableList.contains("#disk")) {
            this.disk = jSONObject.optString("#disk");
        }
        if (!disableList.contains("#fps")) {
            this.fps = jSONObject.optInt("#fps");
        }
        if (disableList.contains("#device_type")) {
            return;
        }
        this.deviceType = jSONObject.optString("#device_type");
    }

    static void initDisableList(Context context) {
        String string;
        synchronized (disableList) {
            if (disableList.isEmpty()) {
                try {
                    Resources resources = context.getResources();
                    disableList.addAll(Arrays.asList(resources.getStringArray(resources.getIdentifier("TDDisPresetProperties", "array", context.getPackageName()))));
                } catch (Exception e) {
                    string = e.toString();
                    TDLog.e(TAG, string);
                } catch (NoClassDefFoundError e2) {
                    string = e2.toString();
                    TDLog.e(TAG, string);
                }
            }
        }
    }

    static void initDisableList(String[] strArr) {
        synchronized (disableList) {
            disableList.clear();
            disableList.addAll(Arrays.asList(strArr));
        }
    }

    public JSONObject toEventPresetProperties() {
        return this.presetProperties;
    }
}
