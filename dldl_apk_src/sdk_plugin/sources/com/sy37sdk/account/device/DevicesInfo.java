package com.sy37sdk.account.device;

import android.content.Context;
import com.sq.tool.sqtools.detector.Detector;
import com.sq.tool.sqtools.detector.DevicesFingerprint;
import com.sq.tool.sqtools.detector.callback.DeviceCollectCallback;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sq.tool.sqtools.detector.inter.IPrivateAgreement;
import com.sq.tool.sqtools.detector.sp.SPConfigInfo;
import com.sq.tools.manager.SensitiveInfoManager;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.dev.ImeiLogic;
import com.sqwan.common.dev.MacLogic;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.BaseSQwanCore;
import com.sy37sdk.account.AccountCache;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DevicesInfo {
    public static final int DEVICE_LOGIN_FAIL = 2;
    public static final int DEVICE_LOGIN_SUCC = 1;
    public static Map<String, String> deviceInfoMap = new HashMap();
    private static boolean activeDone = false;
    public static int loginType = 1;

    public static void setDeviceInfo(String str, String str2) {
        deviceInfoMap.put(str, str2);
    }

    public static void setDeviceInfoFromJson(String str) {
        if (deviceInfoMap == null) {
            deviceInfoMap = new HashMap();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            deviceInfoMap.put(SqTrackCommonKey.boot_time, getJsonValue(jSONObject, SqTrackCommonKey.boot_time));
            deviceInfoMap.put(SqTrackCommonKey.screen_brightness, getJsonValue(jSONObject, SqTrackCommonKey.screen_brightness));
            deviceInfoMap.put(SqTrackCommonKey.cpu_count, getJsonValue(jSONObject, SqTrackCommonKey.cpu_count));
            deviceInfoMap.put(SqTrackCommonKey.is_first_launch, getJsonValue(jSONObject, SqTrackCommonKey.is_first_launch));
            deviceInfoMap.put(SqTrackCommonKey.input_method_list, getJsonValue(jSONObject, SqTrackCommonKey.input_method_list));
            deviceInfoMap.put(SqTrackCommonKey.ram_total, getJsonValue(jSONObject, SqTrackCommonKey.ram_total));
            deviceInfoMap.put(SqTrackCommonKey.display_metrics, getJsonValue(jSONObject, SqTrackCommonKey.display_metrics));
            deviceInfoMap.put(SqTrackCommonKey.sensor_list, getJsonValue(jSONObject, SqTrackCommonKey.sensor_list));
            deviceInfoMap.put(SqTrackCommonKey.enable_adb, getJsonValue(jSONObject, SqTrackCommonKey.enable_adb));
            deviceInfoMap.put("country", getJsonValue(jSONObject, "country"));
            deviceInfoMap.put(SqTrackCommonKey.is_wifi_proxy, getJsonValue(jSONObject, SqTrackCommonKey.is_wifi_proxy));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setDeviceInfoFromMap(Map<String, String> map) {
        if (deviceInfoMap == null) {
            deviceInfoMap = new HashMap();
        }
        try {
            deviceInfoMap.put("phone_model", getMapValue(map, SqConstants.MODE));
            deviceInfoMap.put("os", getMapValue(map, "os"));
            deviceInfoMap.put(SqTrackCommonKey.os_desc, getMapValue(map, SqTrackCommonKey.os_desc));
            deviceInfoMap.put("os_over", getMapValue(map, "over"));
            deviceInfoMap.put("brand", getMapValue(map, "brand"));
            deviceInfoMap.put("apk_name", getMapValue(map, SqConstants.DPGN));
            deviceInfoMap.put("network_type", getMapValue(map, SqConstants.NWK));
            deviceInfoMap.put(SqTrackCommonKey.battery_level, getMapValue(map, SqTrackCommonKey.battery_level));
            deviceInfoMap.put(SqTrackCommonKey.battery_status, getMapValue(map, SqTrackCommonKey.battery_status));
            deviceInfoMap.put("ssid", getMapValue(map, "ssid"));
            deviceInfoMap.put(SqTrackCommonKey.bssid, getMapValue(map, SqTrackCommonKey.bssid));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setBaseDeviceInfo(Context context) {
        if (deviceInfoMap == null) {
            deviceInfoMap = new HashMap();
        }
        try {
            String androidId = SensitiveInfoManager.getInstance().getAndroidId(context);
            String oaid = DeviceUtils.getOaid(context);
            String value = "";
            String value2 = ImeiLogic.getInstance(context).getFromCache() == null ? "" : ImeiLogic.getInstance(context).getFromCache().getValue();
            if (MacLogic.getInstance(context).getFromCache() != null) {
                value = MacLogic.getInstance(context).getFromCache().getValue();
            }
            deviceInfoMap.put(SqTrackCommonKey.d_android_id, androidId);
            deviceInfoMap.put(SqTrackCommonKey.d_oaid, oaid);
            deviceInfoMap.put(SqTrackCommonKey.d_mac, value);
            deviceInfoMap.put(SqTrackCommonKey.d_imei, value2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initDevices(final Context context) {
        LogUtil.i("调用initDevices");
        Detector.build().setPrivateAgreement(new IPrivateAgreement() { // from class: com.sy37sdk.account.device.DevicesInfo.3
            @Override // com.sq.tool.sqtools.detector.inter.IPrivateAgreement
            public boolean getPrivateAgreement() {
                return true;
            }
        }).setCollectCallback(new DeviceCollectCallback() { // from class: com.sy37sdk.account.device.DevicesInfo.2
            @Override // com.sq.tool.sqtools.detector.callback.DeviceCollectCallback
            public void onFail() {
            }

            @Override // com.sq.tool.sqtools.detector.callback.DeviceCollectCallback
            public void onSuccess(Map<String, String> map) {
                LogUtil.i("DEVICE_INFO回调onSuccess");
                map.put("token", DevicesFingerprint.getDevToken(context));
                if (DevicesInfo.getActive()) {
                    LogUtil.i("loginType-->" + DevicesInfo.loginType);
                    int i = DevicesInfo.loginType;
                    if (i == 1) {
                        map.put(BaseSQwanCore.LOGIN_KEY_USERID, AccountCache.getUserid(context));
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DEVICE_INFO_LOGIN, map);
                        LogUtil.i("DEVICE_INFO_LOGIN");
                        return;
                    } else if (i == 2) {
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DEVICE_INFO_LOGIN_FAIL, map);
                        LogUtil.i("DEVICE_INFO_LOGIN_FAIL");
                        return;
                    } else {
                        map.put(BaseSQwanCore.LOGIN_KEY_USERID, AccountCache.getUserid(context));
                        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DEVICE_INFO_LOGIN, map);
                        LogUtil.i("default:DEVICE_INFO_LOGIN");
                        return;
                    }
                }
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DEVICE_INFO_ACTIVE, map);
                LogUtil.i("DEVICE_INFO_ACTIVE");
                DevicesInfo.setActive(true);
            }
        }).setSPConfigInfo(new SPConfigInfo() { // from class: com.sy37sdk.account.device.DevicesInfo.1
            @Override // com.sq.tool.sqtools.detector.sp.SPConfigInfo
            public Map<String, String> getMap() {
                return DevicesInfo.deviceInfoMap;
            }
        }).setHttpClient(new DevicesHttpClient()).init(context);
    }

    public static void doDeviceCollect() {
        Detector.getInstance().doDeviceCollect();
    }

    public static void doDeviceCollect(int i) {
        loginType = i;
        Detector.getInstance().doDeviceCollect();
    }

    public static void initAndDoDeviceCollect(Context context) {
        setBaseDeviceInfo(context);
        initDevices(context);
        Detector.getInstance().doDeviceCollect();
    }

    public static void setActive(boolean z) {
        activeDone = z;
    }

    public static boolean getActive() {
        return activeDone;
    }

    public static String getMapValue(Map<String, String> map, String str) {
        try {
            String str2 = map.get(str);
            return str2 == null ? "" : str2;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getJsonValue(JSONObject jSONObject, String str) {
        try {
            String strValueOf = String.valueOf(jSONObject.opt(str));
            return strValueOf.isEmpty() ? "" : strValueOf;
        } catch (Exception unused) {
            return "";
        }
    }
}
