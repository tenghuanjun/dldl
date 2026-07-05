package com.jiguang.h5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.alipay.sdk.packet.e;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.util.Constant;
import com.igexin.assist.sdk.AssistPushConsts;
import com.igexin.sdk.PushConsts;
import com.jiguang.main.MainActivity;
import com.mobile.auth.BuildConfig;
import com.parameters.performfeatureconfig.PerformFeatureType;
import com.sqwan.msdk.PluginSQwanCore;
import com.sqwan.msdk.SQwanCore;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.api.SQResultListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import kotlin.UByte;
import layaair.game.browser.ExportJavaFunction;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class SdkMgr extends Handler {
    private static String m_uniqueUUID;
    public static BatteryListener s_batteryListener;
    public static SignalListener s_signalListener;
    public static String TAG = SdkMgr.class.toString();
    public static Context Con = null;
    public static SdkMgr Mgr = null;
    public static String Token = null;
    static Gson m_gson = new Gson();
    static JsonParser m_parse = new JsonParser();
    static List<String> m_callTemp = new ArrayList();
    static List<String> m_oncallTemp = new ArrayList();
    public static boolean m_firstOnCall = false;
    public static String rightAgeURl = "";
    static String m_packageName = null;
    static boolean pushCidOnce = false;
    static boolean listenCidOk = false;
    static int m_getMaxCpuFreq = -1;
    static int m_getCpuCores = -1;
    static int showType = 0;

    public static void Logd(String str) {
        Log.d(TAG, str);
    }

    public static void Loge(String str) {
        Log.e(TAG, str);
    }

    public static void Init() {
        for (int i = 0; i < m_oncallTemp.size(); i++) {
            OnCallInternal(m_oncallTemp.get(i));
        }
        m_oncallTemp.clear();
        for (int i2 = 0; i2 < m_callTemp.size(); i2++) {
            CallInternal(m_callTemp.get(i2));
        }
        m_callTemp.clear();
    }

    public static void OnShowAlert(final String str) {
        Mgr.postDelayed(new Runnable() { // from class: com.jiguang.h5.SdkMgr.1
            @Override // java.lang.Runnable
            public void run() {
                JsonObject asJsonObject = SdkMgr.m_parse.parse(str).getAsJsonObject();
                AlertDialog.Builder builder = new AlertDialog.Builder(SdkMgr.Con);
                builder.setTitle(asJsonObject.get("title").getAsString()).setMessage(asJsonObject.get("cxt").getAsString());
                builder.setPositiveButton(asJsonObject.get("btn1").getAsString(), new DialogInterface.OnClickListener() { // from class: com.jiguang.h5.SdkMgr.1.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ExportJavaFunction.CallBackToJS(SdkMgr.class, "OnShowAlert", "0");
                    }
                });
                if (asJsonObject.has("btn2")) {
                    builder.setNegativeButton(asJsonObject.get("btn2").getAsString(), new DialogInterface.OnClickListener() { // from class: com.jiguang.h5.SdkMgr.1.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ExportJavaFunction.CallBackToJS(SdkMgr.class, "OnShowAlert", "1");
                        }
                    });
                }
                AlertDialog alertDialogCreate = builder.create();
                alertDialogCreate.setCanceledOnTouchOutside(false);
                alertDialogCreate.show();
            }
        }, 100L);
    }

    public static void OnExit(String str) {
        Process.killProcess(Process.myPid());
    }

    public static void OnCall(String str) {
        if (Mgr == null) {
            return;
        }
        m_firstOnCall = true;
        Message message = new Message();
        message.obj = str;
        Mgr.sendMessage(message);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        String str = (String) message.obj;
        if (MainActivity.ma == null || MainActivity.ma.m_step != MainActivity.enStep.over) {
            m_oncallTemp.add(str);
        } else {
            OnCallInternal(str);
        }
    }

    public static void Call(String str, Object obj) {
        JsonElement jsonTree;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("cmd", str);
        if (obj == null) {
            jsonTree = new JsonObject();
        } else if (obj instanceof JsonElement) {
            jsonTree = (JsonElement) obj;
        } else {
            jsonTree = m_gson.toJsonTree(obj);
        }
        jsonObject.add("cxt", jsonTree);
        String json = m_gson.toJson((JsonElement) jsonObject);
        if (MainActivity.ma == null || MainActivity.ma.m_step != MainActivity.enStep.over) {
            m_callTemp.add(json);
        } else if (!m_firstOnCall) {
            Loge("首次被as调用前就调用as");
        } else {
            CallInternal(json);
        }
    }

    static void CallInternal(final String str) {
        Mgr.postDelayed(new Runnable() { // from class: com.jiguang.h5.SdkMgr.2
            @Override // java.lang.Runnable
            public void run() {
                SdkMgr.Logd("原生调用回as：" + str);
                ExportJavaFunction.CallBackToJS(SdkMgr.class, "OnCall", str);
            }
        }, 200L);
    }

    static void OnCallInternal(String str) {
        m_firstOnCall = true;
        Logd("原生被as调用：" + str);
        JsonObject asJsonObject = m_parse.parse(str).getAsJsonObject();
        String asString = asJsonObject.get("cmd").getAsString();
        JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("cxt");
        if (asString.equalsIgnoreCase("login")) {
            onLogin(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("changeAccount")) {
            onChangeAccount(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("pay")) {
            onPay(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("creatRoleInfo")) {
            onCreatRoleInfo(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("submitRoleInfo")) {
            onSubmitRoleInfo(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("upgradeRoleInfo")) {
            onUpgradeRoleInfo(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("getAppConfig")) {
            onGetAppConfig(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("pushSwitchAccount")) {
            Logd("as注册了悬浮窗点切换账号监听");
            return;
        }
        if (asString.equalsIgnoreCase("pushBackToGameLogin")) {
            Logd("as注册了悬浮窗点登陆监听");
            return;
        }
        if (asString.equalsIgnoreCase("pushShowExitDailog")) {
            Logd("as注册了退出弹窗的回调");
            return;
        }
        if (asString.equalsIgnoreCase("pushGetuiClientId")) {
            Logd("as注册了个推初始化回调");
            listenCidOk = true;
            CheckCid();
            return;
        }
        if (asString.equalsIgnoreCase("pushPause")) {
            Logd("as注册了游戏切到后台的回调");
            return;
        }
        if (asString.equalsIgnoreCase("pushResume")) {
            Logd("as注册了游戏切回前台的回调");
            return;
        }
        if (asString.equalsIgnoreCase("getSign")) {
            onGetSign(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("getIMEI")) {
            onGetIMEI(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("showSQPersonal")) {
            SQwanCore.getInstance().showSQPersonalDialog(Con);
            return;
        }
        if (asString.equalsIgnoreCase("showExitDailog")) {
            onShowExitDailog(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("logd")) {
            Logd(asJsonObject2.get(BuildConfig.FLAVOR_type).getAsString());
            return;
        }
        if (asString.equalsIgnoreCase("loge")) {
            Loge(asJsonObject2.get(BuildConfig.FLAVOR_type).getAsString());
            return;
        }
        if (asString.equalsIgnoreCase("showWebView")) {
            SQwanCore.getInstance().showSQWebDialog(asJsonObject2.get("url").getAsString());
            return;
        }
        if (asString.equalsIgnoreCase("setShare")) {
            onSetShare(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("showUAgreement")) {
            SQwanCore.getInstance().showUAgreement(MainActivity.ma);
            return;
        }
        if (asString.equalsIgnoreCase(TTDownloadField.TT_OPEN_URL)) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(asJsonObject2.get("url").getAsString()));
            MainActivity.ma.startActivity(intent);
            return;
        }
        if (asString.equalsIgnoreCase("setClip")) {
            onSetClip(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("getSignalInfo")) {
            onGetSignalInfo(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("performFeatureBBS")) {
            SQwanCore.getInstance().performFeatureBBS();
            return;
        }
        if (asString.equalsIgnoreCase("performFeatureVPlayer")) {
            SQwanCore.getInstance().performFeatureVPlayer();
            return;
        }
        if (asString.equalsIgnoreCase("performFeature")) {
            onPerformFeature(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("captureScreenListen")) {
            Logd("as注册了截图监听");
            return;
        }
        if (asString.equalsIgnoreCase("gameOffline")) {
            Logd("as注册了gameOffline监听");
            return;
        }
        if (asString.equalsIgnoreCase("rightAgeImgUrl")) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("url", rightAgeURl);
            Call("rightAgeImgUrl", jsonObject);
            Logd("as注册了rightAgeImgUrl监听");
            return;
        }
        if (asString.equalsIgnoreCase("showRightAge")) {
            Logd("打开适龄地址");
            SQwanCore.getInstance().performFeature(MainActivity.ma, PerformFeatureType.TYPE_SHOW_AGE_APPROPRIATE, null, null);
            return;
        }
        if (asString.equalsIgnoreCase("captureScreenFinish")) {
            captureScreenFinish(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("scanCode")) {
            onScanCode(asJsonObject2);
            return;
        }
        if (asString.equalsIgnoreCase("sendedAppOreo")) {
            setSendAppOreo();
            return;
        }
        Loge("as的调用没有处理，cmd：" + asString);
    }

    static String getAppVer() {
        int i = 0;
        try {
            i = Con.getPackageManager().getPackageInfo(Con.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return "" + i;
    }

    static void onLogin(JsonObject jsonObject) {
        SQwanCore.getInstance().login(Con, new SQResultListener() { // from class: com.jiguang.h5.SdkMgr.3
            @Override // com.sqwan.msdk.api.SQResultListener
            public void onSuccess(Bundle bundle) {
                SdkMgr.Token = bundle.getString(AssistPushConsts.MSG_TYPE_TOKEN);
                JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("ret", (Boolean) true);
                jsonObject2.addProperty(AssistPushConsts.MSG_TYPE_TOKEN, SdkMgr.Token);
                jsonObject2.addProperty("gid", bundle.getString("gid"));
                jsonObject2.addProperty(PushConsts.KEY_SERVICE_PIT, bundle.getString(PushConsts.KEY_SERVICE_PIT));
                jsonObject2.addProperty("isReplace", Boolean.valueOf(SdkConst.IsReplace));
                jsonObject2.addProperty("appVer", SdkMgr.getAppVer());
                SdkMgr.Call("login", jsonObject2);
            }

            @Override // com.sqwan.msdk.api.SQResultListener
            public void onFailture(int i, String str) {
                SdkMgr.Logd("37sdk登陆失败:" + str);
                if (MainActivity.ma.m_isOncreateAgain && i == 204) {
                    SdkMgr.Call("pushBackToGameLogin", null);
                } else if (str.equals("取消登录")) {
                    JsonObject jsonObject2 = new JsonObject();
                    jsonObject2.addProperty("ret", (Boolean) false);
                    SdkMgr.Call("login", jsonObject2);
                }
            }
        });
    }

    static void onChangeAccount(JsonObject jsonObject) {
        SQwanCore.getInstance().changeAccount(Con, new SQResultListener() { // from class: com.jiguang.h5.SdkMgr.4
            @Override // com.sqwan.msdk.api.SQResultListener
            public void onSuccess(Bundle bundle) {
                SdkMgr.Token = bundle.getString(AssistPushConsts.MSG_TYPE_TOKEN);
                JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("ret", (Boolean) true);
                jsonObject2.addProperty(AssistPushConsts.MSG_TYPE_TOKEN, SdkMgr.Token);
                jsonObject2.addProperty("gid", bundle.getString("gid"));
                jsonObject2.addProperty(PushConsts.KEY_SERVICE_PIT, bundle.getString(PushConsts.KEY_SERVICE_PIT));
                jsonObject2.addProperty("isReplace", Boolean.valueOf(SdkConst.IsReplace));
                jsonObject2.addProperty("appVer", SdkMgr.getAppVer());
                SdkMgr.Call("changeAccount", jsonObject2);
            }

            @Override // com.sqwan.msdk.api.SQResultListener
            public void onFailture(int i, String str) {
                SdkMgr.Logd("37sdk切换账号失败:" + str);
                JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("ret", (Boolean) false);
                SdkMgr.Call("changeAccount", jsonObject2);
            }
        });
    }

    static void onPay(JsonObject jsonObject) {
        SQwanCore.getInstance().pay(Con, jsonObject.get("doid").getAsString(), jsonObject.get("dpt").getAsString(), jsonObject.get("dcn").getAsString(), jsonObject.get("dsid").getAsString(), jsonObject.get("dsname").getAsString(), jsonObject.get("dext").getAsString(), jsonObject.get("drid").getAsString(), jsonObject.get("drname").getAsString(), jsonObject.get("drlevel").getAsInt(), jsonObject.get("dmoney").getAsFloat(), jsonObject.get("dradio").getAsInt(), new SQResultListener() { // from class: com.jiguang.h5.SdkMgr.5
            @Override // com.sqwan.msdk.api.SQResultListener
            public void onSuccess(Bundle bundle) {
                SdkMgr.Logd("37sdk发起充值请求成功");
            }

            @Override // com.sqwan.msdk.api.SQResultListener
            public void onFailture(int i, String str) {
                SdkMgr.Logd("37sdk发起充值请求失败：" + str);
            }
        });
    }

    static void onCreatRoleInfo(JsonObject jsonObject) {
        HashMap<String, String> map = new HashMap<>();
        map.put(PluginSQwanCore.INFO_SERVERID, jsonObject.get(PluginSQwanCore.INFO_SERVERID).getAsString());
        map.put(PluginSQwanCore.INFO_SERVERNAME, jsonObject.get(PluginSQwanCore.INFO_SERVERNAME).getAsString());
        map.put(PluginSQwanCore.INFO_ROLEID, jsonObject.get(PluginSQwanCore.INFO_ROLEID).getAsString());
        map.put(PluginSQwanCore.INFO_ROLENAME, jsonObject.get(PluginSQwanCore.INFO_ROLENAME).getAsString());
        map.put(PluginSQwanCore.INFO_ROLELEVEL, jsonObject.get(PluginSQwanCore.INFO_ROLELEVEL).getAsString());
        map.put(PluginSQwanCore.INFO_BALANCE, jsonObject.get(PluginSQwanCore.INFO_BALANCE).getAsString());
        map.put(PluginSQwanCore.INFO_PARTYNAME, jsonObject.get(PluginSQwanCore.INFO_PARTYNAME).getAsString());
        map.put(PluginSQwanCore.INFO_VIPLEVEL, jsonObject.get(PluginSQwanCore.INFO_VIPLEVEL).getAsString());
        map.put(PluginSQwanCore.INFO_ROLE_TIME_CREATE, jsonObject.get(PluginSQwanCore.INFO_ROLE_TIME_CREATE).getAsString());
        map.put(PluginSQwanCore.INFO_ROLE_TIME_LEVEL, jsonObject.get(PluginSQwanCore.INFO_ROLE_TIME_LEVEL).getAsString());
        SQwanCore.getInstance().creatRoleInfo(map);
    }

    static void onSubmitRoleInfo(JsonObject jsonObject) {
        HashMap<String, String> map = new HashMap<>();
        map.put(PluginSQwanCore.INFO_SERVERID, jsonObject.get(PluginSQwanCore.INFO_SERVERID).getAsString());
        map.put(PluginSQwanCore.INFO_SERVERNAME, jsonObject.get(PluginSQwanCore.INFO_SERVERNAME).getAsString());
        map.put(PluginSQwanCore.INFO_ROLEID, jsonObject.get(PluginSQwanCore.INFO_ROLEID).getAsString());
        map.put(PluginSQwanCore.INFO_ROLENAME, jsonObject.get(PluginSQwanCore.INFO_ROLENAME).getAsString());
        map.put(PluginSQwanCore.INFO_ROLELEVEL, jsonObject.get(PluginSQwanCore.INFO_ROLELEVEL).getAsString());
        map.put(PluginSQwanCore.INFO_BALANCE, jsonObject.get(PluginSQwanCore.INFO_BALANCE).getAsString());
        map.put(PluginSQwanCore.INFO_PARTYNAME, jsonObject.get(PluginSQwanCore.INFO_PARTYNAME).getAsString());
        map.put(PluginSQwanCore.INFO_VIPLEVEL, jsonObject.get(PluginSQwanCore.INFO_VIPLEVEL).getAsString());
        if (jsonObject.has(PluginSQwanCore.INFO_ROLE_TIME_CREATE)) {
            map.put(PluginSQwanCore.INFO_ROLE_TIME_CREATE, jsonObject.get(PluginSQwanCore.INFO_ROLE_TIME_CREATE).getAsString());
        }
        if (jsonObject.has(PluginSQwanCore.INFO_ROLE_TIME_LEVEL)) {
            map.put(PluginSQwanCore.INFO_ROLE_TIME_LEVEL, jsonObject.get(PluginSQwanCore.INFO_ROLE_TIME_LEVEL).getAsString());
        }
        if (jsonObject.has("openTime")) {
            map.put(PluginSQwanCore.INFO_SERVERTIME, jsonObject.get("openTime").getAsString());
        }
        SQwanCore.getInstance().submitRoleInfo(map);
    }

    static void onUpgradeRoleInfo(JsonObject jsonObject) {
        HashMap<String, String> map = new HashMap<>();
        map.put(PluginSQwanCore.INFO_SERVERID, jsonObject.get(PluginSQwanCore.INFO_SERVERID).getAsString());
        map.put(PluginSQwanCore.INFO_SERVERNAME, jsonObject.get(PluginSQwanCore.INFO_SERVERNAME).getAsString());
        map.put(PluginSQwanCore.INFO_ROLEID, jsonObject.get(PluginSQwanCore.INFO_ROLEID).getAsString());
        map.put(PluginSQwanCore.INFO_ROLENAME, jsonObject.get(PluginSQwanCore.INFO_ROLENAME).getAsString());
        map.put(PluginSQwanCore.INFO_ROLELEVEL, jsonObject.get(PluginSQwanCore.INFO_ROLELEVEL).getAsString());
        map.put(PluginSQwanCore.INFO_BALANCE, jsonObject.get(PluginSQwanCore.INFO_BALANCE).getAsString());
        map.put(PluginSQwanCore.INFO_PARTYNAME, jsonObject.get(PluginSQwanCore.INFO_PARTYNAME).getAsString());
        map.put(PluginSQwanCore.INFO_VIPLEVEL, jsonObject.get(PluginSQwanCore.INFO_VIPLEVEL).getAsString());
        map.put(PluginSQwanCore.INFO_ROLE_TIME_CREATE, jsonObject.get(PluginSQwanCore.INFO_ROLE_TIME_CREATE).getAsString());
        map.put(PluginSQwanCore.INFO_ROLE_TIME_LEVEL, jsonObject.get(PluginSQwanCore.INFO_ROLE_TIME_LEVEL).getAsString());
        SQwanCore.getInstance().upgradeRoleInfo(map);
    }

    static void onGetAppConfig(JsonObject jsonObject) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        SQAppConfig appConfig = SQwanCore.getInstance().getAppConfig();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("ret", (Boolean) true);
        jsonObject2.addProperty("gid", appConfig.getGameid());
        jsonObject2.addProperty(PushConsts.KEY_SERVICE_PIT, appConfig.getPartner());
        jsonObject2.addProperty(TTDownloadField.TT_REFER, appConfig.getRefer());
        jsonObject2.addProperty("isReplace", Boolean.valueOf(SdkConst.IsReplace));
        jsonObject2.addProperty("cpuFreq", Integer.valueOf(getMaxCpuFreq()));
        jsonObject2.addProperty("cpuCores", Integer.valueOf(getCpuCores()));
        jsonObject2.addProperty("canScanCode", (Boolean) true);
        jsonObject2.addProperty("packageName", GetPackageName());
        jsonObject2.addProperty("isOnSdCard", (Boolean) false);
        Environment.getExternalStorageState().equals("mounted");
        jsonObject2.addProperty("availableSize", Long.valueOf(GetAvailableSize(Environment.getDataDirectory().getPath())));
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        jsonObject2.addProperty("debugTime", Long.valueOf(jCurrentTimeMillis2));
        Log.d(TAG, "onGetAppConfig耗时:" + jCurrentTimeMillis2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) Con).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.widthPixels > 0 && displayMetrics.heightPixels > 0) {
            jsonObject2.addProperty("width", Integer.valueOf(displayMetrics.widthPixels));
            jsonObject2.addProperty("height", Integer.valueOf(displayMetrics.heightPixels));
        }
        Call("getAppConfig", jsonObject2);
    }

    static String GetPackageName() {
        String str = m_packageName;
        if (str != null) {
            return str;
        }
        m_packageName = "";
        int iMyPid = Process.myPid();
        Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) Con.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningAppProcesses().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.pid == iMyPid) {
                m_packageName = next.processName;
                break;
            }
        }
        return m_packageName;
    }

    @SuppressLint({"NewApi"})
    static long GetAvailableSize(String str) {
        long blockSize;
        long availableBlocks;
        StatFs statFs = new StatFs(str);
        if (Build.VERSION.SDK_INT >= 18) {
            blockSize = statFs.getBlockSizeLong();
            statFs.getBlockCountLong();
            availableBlocks = statFs.getAvailableBlocksLong();
        } else {
            blockSize = statFs.getBlockSize();
            statFs.getBlockCount();
            availableBlocks = statFs.getAvailableBlocks();
        }
        return (availableBlocks * blockSize) / 1024;
    }

    static void onGetSign(JsonObject jsonObject) {
        if (Token == null) {
            Loge("请求签名的时候发现token为空");
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("ret", (Boolean) false);
            Call("getSign", jsonObject2);
            return;
        }
        SQAppConfig appConfig = SQwanCore.getInstance().getAppConfig();
        String str = appConfig.getGameid() + appConfig.getPartner() + Token + jsonObject.get("os").getAsString() + jsonObject.get("time").getAsString() + IMEI() + "test";
        JsonObject jsonObject3 = new JsonObject();
        jsonObject3.addProperty("ret", (Boolean) true);
        jsonObject3.addProperty("sign", Md5(str));
        Call("getSign", jsonObject3);
    }

    public static String Md5(String str) {
        try {
            String str2 = "";
            for (byte b : MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"))) {
                String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str2 = str2 + hexString;
            }
            return str2;
        } catch (UnsupportedEncodingException unused) {
            Loge("Encode Error");
            return "";
        } catch (NoSuchAlgorithmException unused2) {
            Loge("Md5 Error");
            return "";
        }
    }

    @SuppressLint({"MissingPermission"})
    public static String IMEI() {
        if (m_uniqueUUID == null) {
            getUniquePsuedoID();
        }
        return m_uniqueUUID;
    }

    private static boolean sendedAppOreo() {
        MainActivity mainActivity = MainActivity.ma;
        String string = mainActivity.getSharedPreferences(mainActivity.getPackageName() + "_cache", 0).getString("sendedAppOreo", null);
        return (string == null || string.length() == 0) ? false : true;
    }

    private static void setSendAppOreo() {
        MainActivity mainActivity = MainActivity.ma;
        mainActivity.getSharedPreferences(mainActivity.getPackageName() + "_cache", 0).edit().putString("sendedAppOreo", "sendedAppOreo").apply();
    }

    private static void getUniquePsuedoID() {
        MainActivity mainActivity = MainActivity.ma;
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences(mainActivity.getPackageName() + "_cache", 0);
        String string = sharedPreferences.getString("uniqueUUID", null);
        if (string != null) {
            m_uniqueUUID = string;
        } else {
            m_uniqueUUID = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10);
            sharedPreferences.edit().putString("uniqueUUID", m_uniqueUUID).apply();
        }
        if (sendedAppOreo()) {
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("myImei", m_uniqueUUID);
        Call("sendAppOreo", jsonObject);
    }

    static void onGetIMEI(JsonObject jsonObject) {
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("IMEI", IMEI());
        Call("getIMEI", jsonObject2);
    }

    static void onShowExitDailog(JsonObject jsonObject) {
        SQwanCore.getInstance().showExitDailog(Con, new SQResultListener() { // from class: com.jiguang.h5.SdkMgr.6
            @Override // com.sqwan.msdk.api.SQResultListener
            public void onFailture(int i, String str) {
            }

            @Override // com.sqwan.msdk.api.SQResultListener
            public void onSuccess(Bundle bundle) {
                SdkMgr.Call("pushShowExitDailog", null);
                Process.killProcess(Process.myPid());
            }
        });
    }

    public static void CheckCid() {
        if (CustomGetuiIntentService.s_cid == null || !listenCidOk || pushCidOnce) {
            return;
        }
        pushCidOnce = true;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(PushConsts.KEY_CLIENT_ID, CustomGetuiIntentService.s_cid);
        Call("pushGetuiClientId", jsonObject);
    }

    public static int getMaxCpuFreq() {
        int i = m_getMaxCpuFreq;
        if (i != -1) {
            return i;
        }
        try {
            String str = "";
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
            m_getMaxCpuFreq = Integer.parseInt(str.trim());
        } catch (Exception e) {
            e.printStackTrace();
            Loge("获取不到cpu最大主频");
            m_getMaxCpuFreq = 1000000;
        }
        return m_getMaxCpuFreq;
    }

    public static int getCpuCores() {
        int i = m_getCpuCores;
        if (i != -1) {
            return i;
        }
        try {
            m_getCpuCores = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() { // from class: com.jiguang.h5.SdkMgr.1CpuFilter
                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return Pattern.matches("cpu[0-9]", file.getName());
                }
            }).length;
        } catch (Exception e) {
            Loge("获取不到核心数");
            e.printStackTrace();
            m_getCpuCores = 4;
        }
        return m_getCpuCores;
    }

    public static int getDownloadThreadNum() {
        int maxCpuFreq = getMaxCpuFreq();
        int cpuCores = getCpuCores();
        if (maxCpuFreq >= 2000000 && cpuCores >= 4) {
            return 6;
        }
        if (maxCpuFreq < 1800000 || cpuCores < 8) {
            return (maxCpuFreq < 1800000 || cpuCores < 4) ? 4 : 5;
        }
        return 6;
    }

    @SuppressLint({"NewApi"})
    static void onSetClip(JsonObject jsonObject) {
        ((ClipboardManager) MainActivity.ma.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Label", jsonObject.get("content").getAsString()));
    }

    public static void onGetSignalInfo(JsonObject jsonObject) {
        int battery = BatteryListener.getBattery();
        int signal = SignalListener.getSignal();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("battery", Integer.valueOf(battery));
        jsonObject2.addProperty("network", Integer.valueOf(SignalListener.getNetworkState()));
        jsonObject2.addProperty("signal", Integer.valueOf(signal));
        Logd("获取手机信号电量等信息：" + m_gson.toJson((JsonElement) jsonObject2));
        Call("getSignalInfo", jsonObject2);
    }

    public static void onPerformFeature(JsonObject jsonObject) {
        HashMap map = new HashMap();
        Iterator<Map.Entry<String, JsonElement>> it = jsonObject.entrySet().iterator();
        String str = null;
        while (it.hasNext()) {
            String key = it.next().getKey();
            String asString = jsonObject.get(key).getAsString();
            if (key.equals("subtype")) {
                str = asString;
            } else {
                map.put(key, asString);
            }
        }
        SQwanCore.getInstance().performFeature(Con, str, map, new SQResultListener() { // from class: com.jiguang.h5.SdkMgr.7
            @Override // com.sqwan.msdk.api.SQResultListener
            public void onSuccess(Bundle bundle) {
                JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("ret", (Boolean) true);
                SdkMgr.Call("performFeature", jsonObject2);
            }

            @Override // com.sqwan.msdk.api.SQResultListener
            public void onFailture(int i, String str2) {
                JsonObject jsonObject2 = new JsonObject();
                jsonObject2.addProperty("ret", (Boolean) false);
                SdkMgr.Call("performFeature", jsonObject2);
            }
        });
    }

    static void captureScreenFinish(JsonObject jsonObject) {
        try {
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(new FileInputStream(jsonObject.get("svpath").getAsString()));
            Logd("读取到本地图片");
            MainActivity.ma.captureScreenFinish(bitmapDecodeStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            MainActivity.ma.captureScreenFinish(null);
        }
    }

    static void onScanCode(JsonObject jsonObject) {
        if (ActivityCompat.checkSelfPermission(MainActivity.ma, PermissionUtils.PERMISSION_CAMERA) != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.ma, PermissionUtils.PERMISSION_CAMERA)) {
                showType = 0;
            }
            Toast.makeText(MainActivity.ma, "请至权限中心打开本应用的相机访问权限", 0).show();
            if (showType == 0) {
                if (MainActivity.ma.permissionDialog == null) {
                    MainActivity.ma.permissionDialog = new PermissionDialog(MainActivity.ma, "需要申请相机权限，用于实现扫码登录，拒绝或取消授权不影响使用其他服务。");
                }
                MainActivity.ma.permissionDialog.show();
                showType = 1;
            }
            ActivityCompat.requestPermissions(MainActivity.ma, new String[]{PermissionUtils.PERMISSION_CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        if (ActivityCompat.checkSelfPermission(MainActivity.ma, PermissionUtils.PERMISSION_READ_EXTERNAL_STORAGE) != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.ma, PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(MainActivity.ma, "请至权限中心打开本应用的文件读写权限", 0).show();
            }
            ActivityCompat.requestPermissions(MainActivity.ma, new String[]{PermissionUtils.PERMISSION_READ_EXTERNAL_STORAGE}, Constant.REQ_PERM_EXTERNAL_STORAGE);
        } else {
            MainActivity.ma.startActivityForResult(new Intent(MainActivity.ma, (Class<?>) CaptureActivity.class), Constant.REQ_QR_CODE);
        }
    }

    public static void onScanCodeFinish(String str) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(e.k, str);
        Call("scanCode", jsonObject);
    }

    static void onSetShare(JsonObject jsonObject) {
        Logd("set分享");
        SQwanCore.getInstance().share(jsonObject.get("shareId").getAsString(), new SQResultListener() { // from class: com.jiguang.h5.SdkMgr.8
            @Override // com.sqwan.msdk.api.SQResultListener
            public void onSuccess(Bundle bundle) {
                Toast.makeText(MainActivity.ma, "分享成功", 1).show();
                Log.i("MainActivity", "分享onSuccess");
            }

            @Override // com.sqwan.msdk.api.SQResultListener
            public void onFailture(int i, String str) {
                Log.i("MainActivity", "分享onFailture");
            }
        });
    }
}
