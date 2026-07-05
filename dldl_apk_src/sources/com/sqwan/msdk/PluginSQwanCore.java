package com.sqwan.msdk;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import com.host.PluginExActivityHandler;
import com.mobile.auth.BuildConfig;
import com.parameters.performfeatureconfig.PerformFeatureType;
import com.parameters.share.ShareMessage;
import com.plugin.core.Plugin;
import com.plugin.core.loader.ApkClassLoader;
import com.plugin.core.tool.PluginLog;
import com.plugin.sdk.BasePluginInterface;
import com.sqwan.msdk.api.InitBean;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.api.SQPushTransmitMessageListener;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.api.SQSdkApi;
import com.sqwan.msdk.api.SQSdkInterface;
import com.sqwan.msdk.api.tool.IScreenshotListener;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PluginSQwanCore extends BasePluginInterface implements SQSdkApi {
    public static final String INFO_BALANCE = "balance";
    public static final String INFO_PARTYNAME = "partyName";
    public static final String INFO_ROLEID = "roleId";
    public static final String INFO_ROLELEVEL = "roleLevel";
    public static final String INFO_ROLENAME = "roleName";
    public static final String INFO_ROLE_TIME_CREATE = "roleCTime";
    public static final String INFO_ROLE_TIME_LEVEL = "roleLevelMTime";
    public static final String INFO_SERVERID = "serverId";
    public static final String INFO_SERVERNAME = "serverName";
    public static final String INFO_SERVERTIME = "serverTime";
    public static final String INFO_VIPLEVEL = "vipLevel";
    public static final int LOG_LEVEL_DEBUG = 0;
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_WARN = 2;
    private Context mContext;
    private SQSdkInterface mSdk;
    private Resources pluginRes;
    private final String SQRESULT_LISTENER_CLASS = "com.sqwan.msdk.api.SQResultListener";
    private HashMap<String, Class> mReflectClassMap = new HashMap<>();
    private final String TAG = "SdkProxy";
    private final String SDK_CLASS = "com.sqwan.msdk.SQwanCoreImpl";
    private final String SDK_GET_METHOD = "getInstance";

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void closeMic() {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void closeSpeaker() {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void creatRole(Context context, String str) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void enableSpeakerOn(boolean z) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void forbidMemberVoice(int i, boolean z) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public int getMicLevel() {
        return 0;
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public int getSpeakerVolume() {
        return 0;
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void initCore(Context context, String str, SQResultListener sQResultListener) {
    }

    @Override // com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public boolean isSupportPlugin() {
        return true;
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void joinNationalRoom(String str, int i, int i2) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void joinTeamRoom(String str, int i) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onJoinRoomListener(Context context, SQResultListener sQResultListener) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onMemberVoiceListener(Context context, SQResultListener sQResultListener) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onQuitRoomListener(Context context, SQResultListener sQResultListener) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onStatusUpdateListener(Context context, SQResultListener sQResultListener) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onWindowFocusChanged(boolean z) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void openMic() {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void openSpeaker() {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void poll() {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void quitRoom(String str, int i) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void setContext(Context context) {
    }

    public void setDebug(Boolean bool) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void setMicLevel(int i) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void setServerInfo(String str) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void setSpeakerVolume(int i) {
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void shareToWX(Context context, String str, String str2, String str3, String str4, int i, SQResultListener sQResultListener) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void speechInit(Context context, SQResultListener sQResultListener) {
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public boolean testMic() {
        return false;
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void init(Context context, String str, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            this.mContext = context;
            getSQwanCoreImplClass().getMethod("init", Context.class, String.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, this.mContext, str, listenerWrapper(sQResultListener));
            if (this.pluginRes != null) {
                Configuration configuration = this.pluginRes.getConfiguration();
                configuration.orientation = BusinessUtils.getScreenOrientation(context);
                this.pluginRes.getConfiguration().setTo(configuration);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void submitStatisticsInfo(String str, String str2) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("submitStatisticsInfo", String.class, String.class).invoke(sQwanCoreImpl, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void track(String str, String str2, HashMap<String, String> map) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("track", String.class, String.class, HashMap.class).invoke(sQwanCoreImpl, str, str2, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public SQSdkInterface getPlatform(Context context, InitBean initBean, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return null;
        }
        try {
            return (SQSdkInterface) getSQwanCoreImplClass().getMethod("getPlatform", Context.class, InitBean.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, context, initBean, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void login(Context context, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("login", Context.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, context, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void changeAccount(Context context, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("changeAccount", Context.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, context, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void setSwitchAccountListener(SQResultListener sQResultListener) {
        voidListenerMethodCall("setSwitchAccountListener", sQResultListener);
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void setBackToGameLoginListener(SQResultListener sQResultListener) {
        voidListenerMethodCall("setBackToGameLoginListener", sQResultListener);
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void showSQWebDialog(String str) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("showSQWebDialog", String.class).invoke(sQwanCoreImpl, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void showSQPersonalDialog(Context context) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("showSQPersonalDialog", Context.class).invoke(sQwanCoreImpl, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void logout(Context context, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("logout", Context.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, context, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void showExitDailog(Context context, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("showExitDailog", Context.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, context, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void pay(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, float f, int i2, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("pay", Context.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Integer.TYPE, Float.TYPE, Integer.TYPE, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, context, str, str2, str3, str4, str5, str6, str7, str8, Integer.valueOf(i), Float.valueOf(f), Integer.valueOf(i2), listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void submitRoleInfo(HashMap<String, String> map) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("submitRoleInfo", HashMap.class).invoke(sQwanCoreImpl, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void creatRoleInfo(HashMap<String, String> map) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("creatRoleInfo", HashMap.class).invoke(sQwanCoreImpl, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void upgradeRoleInfo(HashMap<String, String> map) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("upgradeRoleInfo", HashMap.class).invoke(sQwanCoreImpl, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onStart() {
        voidMethodCall("onStart");
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onResume() {
        voidMethodCall("onResume");
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onPause() {
        voidMethodCall("onPause");
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onStop() {
        voidMethodCall("onStop");
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onDestroy() {
        voidMethodCall("onDestroy");
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onRestart() {
        voidMethodCall("onRestart");
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onActivityResult(int i, int i2, Intent intent) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("onActivityResult", Integer.TYPE, Integer.TYPE, Intent.class).invoke(sQwanCoreImpl, Integer.valueOf(i), Integer.valueOf(i2), intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onNewIntent(Intent intent) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("onNewIntent", Intent.class).invoke(sQwanCoreImpl, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.IExtensionInterface
    public void performFeatureBBS() {
        voidMethodCall("performFeatureBBS");
    }

    @Override // com.sqwan.msdk.api.tool.IExtensionInterface
    public void performFeatureVPlayer() {
        voidMethodCall("performFeatureVPlayer");
    }

    @Override // com.sqwan.msdk.api.tool.IExtensionInterface
    public void performFeature(Context context, String str, Object obj, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("performFeature", Context.class, String.class, Object.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, context, str, obj, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.Tool
    public void setScreenshotListener(final IScreenshotListener iScreenshotListener) {
        ApkClassLoader apkClassLoader;
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null || (apkClassLoader = getApkClassLoader()) == null) {
            return;
        }
        try {
            Class cls = getClass("com.sqwan.msdk.api.tool.IScreenshotListener");
            getSQwanCoreImplClass().getMethod("setScreenshotListener", cls).invoke(sQwanCoreImpl, Proxy.newProxyInstance(apkClassLoader, new Class[]{cls}, new InvocationHandler() { // from class: com.sqwan.msdk.PluginSQwanCore.1
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    return iScreenshotListener.createScreenshot();
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public Resources getResources(Resources resources) {
        if (!isSupportPlugin() || PluginLoader.getInstance().get() == null || PluginLoader.getInstance().get().getResource() == null) {
            return resources;
        }
        if (this.pluginRes == null) {
            Resources resource = PluginLoader.getInstance().get().getResource();
            this.pluginRes = resource;
            Configuration configuration = resource.getConfiguration();
            configuration.orientation = BusinessUtils.getScreenOrientation(this.pluginRes.getAssets());
            this.pluginRes.getConfiguration().setTo(configuration);
        }
        return this.pluginRes;
    }

    @Override // com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public AssetManager getAssets(AssetManager assetManager) {
        Resources resource;
        return (!isSupportPlugin() || PluginLoader.getInstance().get() == null || PluginLoader.getInstance().get().getResource() == null || (resource = PluginLoader.getInstance().get().getResource()) == null) ? assetManager : resource.getAssets();
    }

    @Override // com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public ClassLoader getClassLoader(ClassLoader classLoader) {
        return (!isSupportPlugin() || PluginLoader.getInstance().get() == null || PluginLoader.getInstance().get().getClassLoader() == null) ? classLoader : PluginLoader.getInstance().get().getClassLoader();
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void reportMDev(String str) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("reportMDev", String.class).invoke(sQwanCoreImpl, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public void startActivity(Intent intent) {
        PluginExActivityHandler.getInstance().handlerActivityIntent(this.mContext, intent, PluginLoader.getInstance().get().getClassLoader());
    }

    @Override // com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public void startActivityForResult(Intent intent, int i) {
        PluginExActivityHandler.getInstance().handlerActivityIntent(this.mContext, intent, PluginLoader.getInstance().get().getClassLoader());
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("onRequestPermissionsResult", Integer.TYPE, String[].class, int[].class).invoke(sQwanCoreImpl, Integer.valueOf(i), strArr, iArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendLog(String str) {
        Log.i("sqsdk_m", str);
    }

    public static void sendLogNoDebug(String str) {
        Log.i("sqsdk_m", str);
    }

    public static void sendLog(String str, int i) {
        Log.i("sqsdk_m", str);
    }

    public static void sendLogBase4CP(String str) {
        Log.i("sqsdk_m", str);
    }

    public static void sendLogPlat4CP(String str) {
        Log.i("sqsdk_m", str);
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public SQAppConfig getAppConfig() {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return null;
        }
        try {
            Class cls = getClass("com.sqwan.msdk.api.ISQAppConfig");
            Object objInvoke = getSQwanCoreImplClass().getMethod("getAppConfig", new Class[0]).invoke(sQwanCoreImpl, new Object[0]);
            if (objInvoke == null) {
                return null;
            }
            return new SQAppConfig((String) cls.getMethod("getGameid", new Class[0]).invoke(objInvoke, new Object[0]), (String) cls.getMethod("getPartner", new Class[0]).invoke(objInvoke, new Object[0]), (String) cls.getMethod("getRefer", new Class[0]).invoke(objInvoke, new Object[0]));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Class getClass(String str) {
        if (this.mReflectClassMap.get(str) != null) {
            return this.mReflectClassMap.get(str);
        }
        ApkClassLoader apkClassLoader = getApkClassLoader();
        if (apkClassLoader == null) {
            return null;
        }
        try {
            Class clsLoadClass = apkClassLoader.loadClass(str);
            this.mReflectClassMap.put(str, clsLoadClass);
            return clsLoadClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object listenerWrapper(final SQResultListener sQResultListener) {
        ApkClassLoader apkClassLoader = getApkClassLoader();
        if (apkClassLoader == null) {
            return null;
        }
        return Proxy.newProxyInstance(apkClassLoader, new Class[]{getClass("com.sqwan.msdk.api.SQResultListener")}, new InvocationHandler() { // from class: com.sqwan.msdk.PluginSQwanCore.2
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                if (method.getName().equals("onSuccess")) {
                    sQResultListener.onSuccess((Bundle) objArr[0]);
                    return null;
                }
                sQResultListener.onFailture(((Integer) objArr[0]).intValue(), (String) objArr[1]);
                return null;
            }
        });
    }

    private void voidMethodCall(String str) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod(str, new Class[0]).invoke(sQwanCoreImpl, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void voidListenerMethodCall(String str, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod(str, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void share(String str, SQResultListener sQResultListener) {
        share(str, "", sQResultListener);
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void share(String str, String str2, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod(PerformFeatureType.TYPE_SHARE, String.class, String.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, str, str2, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void share(ShareMessage shareMessage, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod(PerformFeatureType.TYPE_SHARE, ShareMessage.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, shareMessage, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void log(int i, String str) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod(BuildConfig.FLAVOR_type, Integer.TYPE, String.class).invoke(sQwanCoreImpl, Integer.valueOf(i), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void showUAgreement(Context context) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("showUAgreement", Context.class).invoke(sQwanCoreImpl, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public boolean isSupportLiveVideo() {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return false;
        }
        try {
            Method method = getSQwanCoreImplClass().getMethod("isSupportLiveVideo", new Class[0]);
            method.setAccessible(true);
            return ((Boolean) method.invoke(sQwanCoreImpl, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void joinLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("joinLiveshowRoom", Map.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, map, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void leaveLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("leaveLiveshowRoom", Map.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, map, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void setLiveshowDestroyCallback(SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("setLiveshowDestroyCallback", getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void setLiveshowVoiceChangeCallback(SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("setLiveshowVoiceChangeCallback", getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveshow
    public void performLiveshowFeature(Map<String, String> map, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("performLiveshowFeature", Map.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, map, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public boolean isSupportLiveRadio() {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return false;
        }
        try {
            Method method = getSQwanCoreImplClass().getMethod("isSupportLiveRadio", new Class[0]);
            method.setAccessible(true);
            return ((Boolean) method.invoke(sQwanCoreImpl, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public void joinLiveRadioRoom(Map<String, String> map, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("joinLiveRadioRoom", Map.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, map, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public void leaveLiveRadioRoom(Map<String, String> map, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("leaveLiveRadioRoom", Map.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, map, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public void setLiveRadioDestroyCallback(SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("setLiveRadioDestroyCallback", getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public void setLiveRadioVoiceChangeCallback(SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("setLiveRadioVoiceChangeCallback", getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.tool.ILiveRadio
    public void performLiveRadioFeature(Map<String, String> map, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("performLiveRadioFeature", Map.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, map, listenerWrapper(sQResultListener));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkInterface
    public void printLog(int i, String str, String str2) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            getSQwanCoreImplClass().getMethod("printLog", Integer.TYPE, String.class, String.class).invoke(sQwanCoreImpl, Integer.valueOf(i), str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ApkClassLoader getApkClassLoader() {
        Plugin plugin = PluginLoader.getInstance().get();
        if (plugin == null) {
            PluginLog.e("PluginLoader.getInstance().get() 类还没有初始化", new Exception());
            return null;
        }
        ApkClassLoader apkClassLoader = plugin.mClassLoader;
        if (apkClassLoader == null) {
            PluginLog.e("PluginLoader.getInstance().get().mClassLoader 还没有初始化", new Exception());
        }
        return apkClassLoader;
    }

    private Class getSQwanCoreImplClass() {
        Class cls = getClass("com.sqwan.msdk.SQwanCoreImpl");
        if (cls == null) {
            PluginLog.e("sdk 核心类加载失败", new Exception("com.sqwan.msdk.SQwanCoreImpl类加载失败"));
        }
        return cls;
    }

    private Object getSQwanCoreImpl() {
        try {
            Class sQwanCoreImplClass = getSQwanCoreImplClass();
            if (sQwanCoreImplClass != null) {
                return sQwanCoreImplClass.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            }
        } catch (Exception e) {
            PluginLog.e("sdk 核心类实例加载异常", e);
        }
        return null;
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void setSQPushTransmitMessageListener(SQPushTransmitMessageListener sQPushTransmitMessageListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            sQwanCoreImpl.getClass().getMethod("setSQPushTransmitMessageListener", SQPushTransmitMessageListener.class).invoke(sQwanCoreImpl, sQPushTransmitMessageListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sqwan.msdk.api.SQSdkApi
    public void showAdReward(Context context, String str, SQResultListener sQResultListener) {
        Object sQwanCoreImpl = getSQwanCoreImpl();
        if (sQwanCoreImpl == null) {
            return;
        }
        try {
            sQwanCoreImpl.getClass().getMethod("showAdReward", Context.class, String.class, getClass("com.sqwan.msdk.api.SQResultListener")).invoke(sQwanCoreImpl, context, str, sQResultListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
