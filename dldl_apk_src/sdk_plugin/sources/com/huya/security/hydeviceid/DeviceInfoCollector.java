package com.huya.security.hydeviceid;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.util.Log;
import com.huya.statistics.core.StatisticsContent;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.taptap.sdk.db.constant.Common;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DeviceInfoCollector {
    public static JSONObject collectInfo = new JSONObject();
    public static String appid = "";
    public static String mid = "";
    public static String guid = "";

    public static void collectMemInfo() throws JSONException {
        Context context = NativeBridge.getContext();
        long internalToatalSpace = HyDeviceUtil.getInternalToatalSpace(context);
        long availableInternalToatalSpace = HyDeviceUtil.getAvailableInternalToatalSpace(context);
        long externalMemorySize = HyDeviceUtil.getExternalMemorySize(context);
        long availableExternalMemorySize = HyDeviceUtil.getAvailableExternalMemorySize(context);
        long totalMemory = HyDeviceUtil.getTotalMemory(context);
        collectInfo.put("internaltotal", internalToatalSpace);
        collectInfo.put("internalavail", availableInternalToatalSpace);
        collectInfo.put("externaltotal", externalMemorySize);
        collectInfo.put("externalavail", availableExternalMemorySize);
        collectInfo.put("memory", totalMemory);
    }

    public static void collectWifiInfo() throws JSONException {
        String netInfo = HyDeviceUtil.getNetInfo(NativeBridge.getContext());
        String macFromHardware = HyDeviceUtil.getMacFromHardware();
        collectInfo.put("netinfo", new JSONObject(netInfo));
        collectInfo.put("mac", macFromHardware);
    }

    public static void collectScreenInfo() throws JSONException {
        HyDeviceUtil.getScreen(NativeBridge.getContext());
        collectInfo.put("screenwidth", HyDeviceUtil.nW);
        collectInfo.put("screenheight", HyDeviceUtil.nH);
    }

    public static void collectSensor() throws JSONException {
        collectInfo.put("sensor", new JSONObject(HyDeviceUtil.getSensor(NativeBridge.getContext())));
    }

    public static void collectAppInfo() throws JSONException {
        String guid2 = getGuid();
        String mid2 = getMid();
        collectInfo.put("appid", getAppId());
        collectInfo.put(StatisticsContent.MID, mid2);
        collectInfo.put(StatisticsContent.GUID, guid2);
    }

    public static String getGuid() {
        try {
            if (guid != null && !guid.isEmpty()) {
                return guid;
            }
            String str = (String) Class.forName("com.duowan.biz.wup.WupHelper").getDeclaredMethod("getGuid", new Class[0]).invoke(new Object[0], new Object[0]);
            guid = str;
            return str;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getMid() {
        String str = mid;
        if (str != null && !str.isEmpty()) {
            return mid;
        }
        String string = Settings.Secure.getString(NativeBridge.getContext().getContentResolver(), "android_id");
        mid = string;
        return string;
    }

    public static String getMetaDataValue(Context context, String str) {
        int i;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                return "";
            }
            String string = applicationInfo.metaData.getString(str);
            return (string != null || (i = applicationInfo.metaData.getInt(str, -1)) == -1) ? string : String.valueOf(i);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getAppId() {
        if (!appid.isEmpty()) {
            return appid;
        }
        Context context = NativeBridge.getContext();
        try {
            appid = getMetaDataValue(context, "HY_APPID");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = appid;
        if (str == null || str.isEmpty()) {
            appid = context.getPackageName();
        }
        return appid;
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void collectVersion() throws JSONException {
        collectInfo.put(Common.Predefined.SUB_APP_VERSION, getVersionName(NativeBridge.getContext()));
        collectInfo.put("data_version", BuildConfig.VERSION_NAME);
    }

    public static void collectBuildInfo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("HARDWARE", Build.HARDWARE);
        jSONObject.put("BOOTLOADER", Build.BOOTLOADER);
        jSONObject.put("MANUFACTURER", Build.MANUFACTURER);
        jSONObject.put("DEVICE", Build.DEVICE);
        collectInfo.put("build", jSONObject);
    }

    public static void collectBattery() throws JSONException {
        Context context = NativeBridge.getContext();
        JSONObject jSONObject = new JSONObject();
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = intentRegisterReceiver.getIntExtra("voltage", 99999);
        int intExtra2 = intentRegisterReceiver.getIntExtra("temperature", 99999);
        jSONObject.put("voltage", intExtra);
        jSONObject.put("temperature", intExtra2);
        collectInfo.put("battery", jSONObject);
    }

    public static void collectOaid() throws Exception {
        final Class clsLoadClass = releaseDex().loadClass("com.huya.security.oaid.OaidHelper");
        clsLoadClass.getDeclaredMethods()[0].invoke(null, NativeBridge.getContext(), new Runnable() { // from class: com.huya.security.hydeviceid.DeviceInfoCollector.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String string = clsLoadClass.getDeclaredField("oaid").get(null).toString();
                    String string2 = clsLoadClass.getDeclaredField("error").get(null).toString();
                    NativeBridge.setOaid(string);
                    Log.i("oaid", string + string2);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e2) {
                    e2.printStackTrace();
                }
            }
        });
    }

    public static DexClassLoader releaseDex() throws IOException {
        Context context = NativeBridge.getContext();
        String strConcat = context.getDir("hydevice", 2).getAbsolutePath().concat("/oaid.dex");
        String absolutePath = context.getCacheDir().getAbsolutePath();
        new File(strConcat).exists();
        FileOutputStream fileOutputStream = new FileOutputStream(strConcat);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(context.getResources().getAssets().open("oaid.dex"));
        while (bufferedInputStream.available() > 0) {
            byte[] bArr = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(bArr);
            fileOutputStream.write(bArr);
        }
        fileOutputStream.close();
        bufferedInputStream.close();
        return new DexClassLoader(strConcat, absolutePath, null, Context.class.getClassLoader());
    }

    public static void collectPhoneAccount() throws JSONException {
        Context context = NativeBridge.getContext();
        if (context.checkPermission("android.permission.READ_PHONE_STATE", Process.myPid(), Process.myUid()) == 0 && Build.VERSION.SDK_INT >= 23) {
            List<PhoneAccountHandle> callCapablePhoneAccounts = ((TelecomManager) context.getSystemService(TelecomManager.class)).getCallCapablePhoneAccounts();
            JSONArray jSONArray = new JSONArray();
            for (PhoneAccountHandle phoneAccountHandle : callCapablePhoneAccounts) {
                PhoneAccount phoneAccount = ((TelecomManager) context.getSystemService(TelecomManager.class)).getPhoneAccount(phoneAccountHandle);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("uri", phoneAccount.getAddress());
                jSONObject.put(StatisticsContent.EVENT_LABEL, phoneAccount.getLabel());
                jSONObject.put("desp", phoneAccount.getShortDescription());
                jSONObject.put("enable", phoneAccount.isEnabled());
                jSONObject.put(SqTrackCommonKey.id, phoneAccountHandle.getId());
                jSONObject.put("component", phoneAccountHandle.getComponentName().toString());
                jSONArray.put(jSONObject);
            }
            collectInfo.put("phoneAccount", jSONArray);
        }
    }

    public static void collect() {
        try {
            collectMemInfo();
            collectWifiInfo();
            collectScreenInfo();
            collectSensor();
            collectAppInfo();
            collectVersion();
            collectBuildInfo();
            collectBattery();
            collectOaid();
            collectPhoneAccount();
            NativeBridge.setCollectInfo(collectInfo.toString());
        } catch (Exception e) {
            LogBridge.write(6, LogBridge.getStackTraceAsString(e));
        }
    }
}
