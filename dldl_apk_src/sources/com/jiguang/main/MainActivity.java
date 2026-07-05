package com.jiguang.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.google.gson.JsonObject;
import com.google.zxing.util.Constant;
import com.igexin.assist.sdk.AssistPushConsts;
import com.igexin.push.config.c;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.jiguang.h5.BatteryListener;
import com.jiguang.h5.CutoutUtil;
import com.jiguang.h5.PermissionDialog;
import com.jiguang.h5.SdkConst;
import com.jiguang.h5.SdkMgr;
import com.jiguang.h5.SignalListener;
import com.parameters.performfeatureconfig.PerformFeatureType;
import com.sqwan.msdk.SQwanCore;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.api.tool.IScreenshotListener;
import java.io.File;
import layaair.game.IMarket.IPlugin;
import layaair.game.IMarket.IPluginRuntimeProxy;
import layaair.game.Market.GameEngine;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class MainActivity extends Activity {
    private static final String CAMERA_PERMISSION = "android.permission.CAMERA";
    public static final int CHECK_NET = 111;
    public static String TAG = SdkMgr.TAG;
    public static MainActivity ma;
    private Bitmap mScreenCaptureBitmap;
    public long m_beginTime;
    public boolean m_is37SDKOk;
    public boolean m_isLayaOk;
    public boolean m_isNetOk;
    public boolean m_isOncreateAgain;
    AlertDialog m_netDlg;
    public enStep m_step;
    public PermissionDialog permissionDialog;
    private IPlugin mPlugin = null;
    private IPluginRuntimeProxy mProxy = null;
    String appkey = "CR.wdPyFoanb6Thv8sJ5rjNDMEeI3@X1";
    Handler m_handler = new Handler();
    private Object mLock = new Object();

    public enum enStep {
        enStepNone,
        enStep1,
        enStep2,
        over
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        ma = this;
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        getWindow().setFlags(1024, 1024);
        getWindow().setFlags(128, 128);
        SdkMgr.Con = this;
        this.m_isOncreateAgain = false;
        if (SdkMgr.Mgr != null) {
            this.m_isOncreateAgain = true;
        }
        SdkMgr.Mgr = new SdkMgr();
        SdkMgr.s_batteryListener = new BatteryListener(this);
        SdkMgr.s_batteryListener.register();
        SdkMgr.s_signalListener = new SignalListener(this);
        this.m_step = enStep.enStepNone;
        this.m_is37SDKOk = false;
        this.m_isLayaOk = false;
        this.m_isNetOk = false;
        this.m_beginTime = System.currentTimeMillis();
        if (this.m_isOncreateAgain) {
            this.m_is37SDKOk = true;
        }
        driver();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        onWindowFocusChanged(true);
        SQwanCore.getInstance().onStart();
        CutoutUtil.setDislay2Cutout();
        clearOldVersionCache();
        SdkMgr.IMEI();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        SQwanCore.getInstance().onRestart();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        boolean z = this.m_step == enStep.over && !SdkMgr.m_firstOnCall;
        if (this.m_isLayaOk) {
            this.mPlugin.game_plugin_onPause();
            if (this.m_step == enStep.over && !z) {
                SdkMgr.Call("pushPause", null);
            }
        }
        SQwanCore.getInstance().onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        boolean z = this.m_step == enStep.over && !SdkMgr.m_firstOnCall;
        if (this.m_isLayaOk) {
            this.mPlugin.game_plugin_onResume();
            if (this.m_step == enStep.over && !z) {
                SdkMgr.Call("pushResume", null);
            }
        }
        SQwanCore.getInstance().onResume();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        SQwanCore.getInstance().onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.m_isLayaOk) {
            this.mPlugin.game_plugin_onDestory();
        }
        SQwanCore.getInstance().onDestroy();
        if (SdkMgr.s_batteryListener != null) {
            SdkMgr.s_batteryListener.unregister();
            SdkMgr.s_batteryListener = null;
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        SQwanCore.getInstance().onActivityResult(i, i2, intent);
        if (!this.m_isNetOk && i == 111) {
            checkNet();
        }
        if (i == 11002 && i2 == -1) {
            SdkMgr.onScanCodeFinish(intent.getExtras().getString(Constant.INTENT_EXTRA_KEY_QR_SCAN));
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        SQwanCore.getInstance().onRequestPermissionsResult(i, strArr, iArr);
        PermissionDialog permissionDialog = this.permissionDialog;
        if (permissionDialog != null) {
            permissionDialog.dismiss();
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        SQwanCore.getInstance().onNewIntent(intent);
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return SQwanCore.getInstance().getResources(super.getResources());
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ClassLoader getClassLoader() {
        return SQwanCore.getInstance().getClassLoader(super.getClassLoader());
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        SQwanCore.getInstance().startActivity(intent);
        super.startActivity(intent);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        SQwanCore.getInstance().startActivityForResult(intent, i);
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        SQwanCore.getInstance().onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        SQwanCore.getInstance().onWindowFocusChanged(z);
    }

    void driver() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        switch (this.m_step) {
            case enStepNone:
                this.m_step = enStep.enStep1;
                startStep1();
                break;
            case enStep1:
                if (this.m_isNetOk && this.m_is37SDKOk && jCurrentTimeMillis - this.m_beginTime > 1000) {
                    this.m_step = enStep.enStep2;
                    startStep2();
                }
                break;
            case enStep2:
                if (this.m_isLayaOk && this.m_is37SDKOk) {
                    this.m_step = enStep.over;
                    stepOver();
                    return;
                }
                break;
            default:
                return;
        }
        this.m_handler.postDelayed(new Runnable() { // from class: com.jiguang.main.MainActivity.1
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.driver();
            }
        }, 500L);
    }

    void startStep1() {
        SQwanCore.getInstance().setDebug(false);
        try {
            SQwanCore.sendLog(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        init37Sdk();
        SQwanCore.getInstance().setSwitchAccountListener(new SQResultListener() { // from class: com.jiguang.main.MainActivity.2
            @Override // com.sqwan.msdk.api.SQResultListener
            public void onSuccess(final Bundle bundle) {
                MainActivity.this.m_handler.postDelayed(new Runnable() { // from class: com.jiguang.main.MainActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SdkMgr.Token = bundle.getString(AssistPushConsts.MSG_TYPE_TOKEN);
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.addProperty(AssistPushConsts.MSG_TYPE_TOKEN, SdkMgr.Token);
                        jsonObject.addProperty("gid", bundle.getString("gid"));
                        jsonObject.addProperty(PushConsts.KEY_SERVICE_PIT, bundle.getString(PushConsts.KEY_SERVICE_PIT));
                        SdkMgr.Call("pushSwitchAccount", jsonObject);
                        SdkMgr.m_firstOnCall = false;
                    }
                }, c.j);
            }

            @Override // com.sqwan.msdk.api.SQResultListener
            public void onFailture(int i, String str) {
                SdkMgr.Logd("37sdk悬浮窗切换账号失败:" + str);
            }
        });
        SQwanCore.getInstance().setBackToGameLoginListener(new SQResultListener() { // from class: com.jiguang.main.MainActivity.3
            @Override // com.sqwan.msdk.api.SQResultListener
            public void onFailture(int i, String str) {
            }

            @Override // com.sqwan.msdk.api.SQResultListener
            public void onSuccess(Bundle bundle) {
                MainActivity.this.m_handler.postDelayed(new Runnable() { // from class: com.jiguang.main.MainActivity.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SdkMgr.Call("pushBackToGameLogin", null);
                        SdkMgr.m_firstOnCall = false;
                    }
                }, c.j);
            }
        });
        SQwanCore.getInstance().setScreenshotListener(new IScreenshotListener() { // from class: com.jiguang.main.MainActivity.4
            @Override // com.sqwan.msdk.api.tool.IScreenshotListener
            public Bitmap createScreenshot() {
                synchronized (MainActivity.this.mLock) {
                    MainActivity.this.m_handler.postDelayed(new Runnable() { // from class: com.jiguang.main.MainActivity.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SdkMgr.Call("captureScreenListen", null);
                        }
                    }, 100L);
                    try {
                        MainActivity.this.mLock.wait();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
                return MainActivity.this.mScreenCaptureBitmap;
            }
        });
        SQwanCore.getInstance().performFeature(this, PerformFeatureType.TYPE_AUTHRESULTCHECK, null, new SQResultListener() { // from class: com.jiguang.main.MainActivity.5
            @Override // com.sqwan.msdk.api.SQResultListener
            public void onSuccess(Bundle bundle) {
                SdkMgr.Logd("实名认证回调成功");
            }

            @Override // com.sqwan.msdk.api.SQResultListener
            public void onFailture(int i, String str) {
                SdkMgr.Call("gameOffline", null);
            }
        });
        checkNet();
    }

    public void captureScreenFinish(Bitmap bitmap) {
        this.mScreenCaptureBitmap = bitmap;
        synchronized (this.mLock) {
            this.mLock.notifyAll();
        }
    }

    void init37Sdk() {
        SQwanCore.getInstance().init(this, this.appkey, new SQResultListener() { // from class: com.jiguang.main.MainActivity.6
            @Override // com.sqwan.msdk.api.SQResultListener
            public void onSuccess(Bundle bundle) {
                SdkMgr.Logd("37sdk初始化完成");
                MainActivity.this.m_handler.postDelayed(new Runnable() { // from class: com.jiguang.main.MainActivity.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity.this.m_is37SDKOk = true;
                    }
                }, c.j);
            }

            @Override // com.sqwan.msdk.api.SQResultListener
            public void onFailture(int i, String str) {
                SdkMgr.Loge("37sdk初始化失败:" + i + " " + str);
                Toast.makeText(MainActivity.this, "网络异常", 1).show();
                MainActivity.this.m_handler.postDelayed(new Runnable() { // from class: com.jiguang.main.MainActivity.6.2
                    @Override // java.lang.Runnable
                    public void run() {
                        MainActivity.this.init37Sdk();
                    }
                }, 3000L);
            }
        });
    }

    void startStep2() {
        this.mProxy = new RuntimeProxy(this);
        this.mPlugin = new GameEngine(this);
        this.mPlugin.game_plugin_set_runtime_proxy(this.mProxy);
        this.mPlugin.game_plugin_set_option("localize", SdkConst.IsLocal + "");
        this.mPlugin.game_plugin_set_option("gameUrl", SdkConst.Url);
        int downloadThreadNum = SdkMgr.getDownloadThreadNum();
        Log.d(SdkMgr.TAG, "下载线程数量：" + downloadThreadNum);
        this.mPlugin.game_plugin_init(downloadThreadNum);
        setContentView(this.mPlugin.game_plugin_get_view());
        this.m_isLayaOk = true;
    }

    void stepOver() {
        SdkMgr.Init();
        PushManager.getInstance().initialize(getApplicationContext());
        SQwanCore.getInstance().performFeature(this, "age_appropriate_icon", null, new SQResultListener() { // from class: com.jiguang.main.MainActivity.7
            @Override // com.sqwan.msdk.api.SQResultListener
            public void onSuccess(Bundle bundle) {
                String string = bundle.getString("age_appropriate_icon");
                SdkMgr.Logd("适龄提醒url:" + string);
                SdkMgr.rightAgeURl = string;
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("url", string);
                SdkMgr.Call("rightAgeImgUrl", jsonObject);
            }

            @Override // com.sqwan.msdk.api.SQResultListener
            public void onFailture(int i, String str) {
                SdkMgr.Logd("获取适龄提醒icon失败，msg: " + str);
            }
        });
    }

    void checkNet() {
        AlertDialog alertDialog = this.m_netDlg;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.m_netDlg.dismiss();
            this.m_netDlg = null;
        }
        if (!isOpenNetwork()) {
            settingNetwork(this);
        } else {
            this.m_isNetOk = true;
        }
    }

    public boolean isOpenNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void settingNetwork(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("找不到网络").setMessage("是否对网络进行设置?");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() { // from class: com.jiguang.main.MainActivity.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent;
                if (MainActivity.this.isOpenNetwork()) {
                    Toast.makeText(context.getApplicationContext(), "当前有可用网络", 1).show();
                    MainActivity.this.m_isNetOk = true;
                    return;
                }
                try {
                    if (Integer.valueOf(Build.VERSION.SDK).intValue() > 10) {
                        intent = new Intent("android.settings.WIRELESS_SETTINGS");
                    } else {
                        intent = new Intent();
                        intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.WirelessSettings"));
                        intent.setAction("android.intent.action.VIEW");
                    }
                    ((Activity) context).startActivityForResult(intent, 111);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() { // from class: com.jiguang.main.MainActivity.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                Process.killProcess(Process.myPid());
            }
        });
        this.m_netDlg = builder.create();
        this.m_netDlg.setCanceledOnTouchOutside(false);
        this.m_netDlg.show();
    }

    private void clearOldVersionCache() {
        int i;
        try {
            i = ma.getPackageManager().getPackageInfo(ma.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
            i = 0;
        }
        String packageName = getPackageName();
        SharedPreferences sharedPreferences = getSharedPreferences(packageName + "_cache", 0);
        int i2 = sharedPreferences.getInt(TTDownloadField.TT_VERSION_CODE, 0);
        if (i2 != 0 && i2 < i) {
            Log.d("===yjw deleteFile", "versionCode=" + i + ";old=" + i2);
            File file = new File("/sdcard/Android/data/{pkg}".replace("{pkg}", packageName));
            File file2 = new File("/data/data/{pkg}".replace("{pkg}", packageName));
            deleteFile(file);
            deleteFile(file2);
        }
        sharedPreferences.edit().putInt(TTDownloadField.TT_VERSION_CODE, i).apply();
    }

    private static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                if (checkNoDelete(file.getAbsolutePath()).booleanValue()) {
                    return;
                }
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                    if (checkNoDelete(file.getAbsolutePath()).booleanValue()) {
                        return;
                    }
                    file.delete();
                    return;
                }
                for (File file2 : fileArrListFiles) {
                    deleteFile(file2);
                }
                if (checkNoDelete(file.getAbsolutePath()).booleanValue()) {
                    return;
                }
                file.delete();
            }
        }
    }

    private static Boolean checkNoDelete(String str) {
        return Boolean.valueOf(str.indexOf("/app_plugin") != -1);
    }
}
