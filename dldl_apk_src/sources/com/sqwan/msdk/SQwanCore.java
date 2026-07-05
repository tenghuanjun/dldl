package com.sqwan.msdk;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.parameters.share.ShareMessage;
import com.sqwan.msdk.api.SQAppConfig;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.api.tool.IScreenshotListener;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class SQwanCore extends PluginSQwanCore {
    private static volatile SQwanCore sInstance;

    public static SQwanCore getInstance() {
        if (sInstance == null) {
            synchronized (SQwanCore.class) {
                if (sInstance == null) {
                    sInstance = new SQwanCore();
                }
            }
        }
        return sInstance;
    }

    private SQwanCore() {
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkApi
    public void init(Context context, String str, SQResultListener sQResultListener) {
        super.init(context, str, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkApi
    public void initCore(Context context, String str, SQResultListener sQResultListener) {
        super.initCore(context, str, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkApi
    public void track(String str, String str2, HashMap<String, String> map) {
        super.track(str, str2, map);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void login(Context context, SQResultListener sQResultListener) {
        super.login(context, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void changeAccount(Context context, SQResultListener sQResultListener) {
        super.changeAccount(context, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void setSwitchAccountListener(SQResultListener sQResultListener) {
        super.setSwitchAccountListener(sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void setBackToGameLoginListener(SQResultListener sQResultListener) {
        super.setBackToGameLoginListener(sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void showSQWebDialog(String str) {
        super.showSQWebDialog(str);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void showSQPersonalDialog(Context context) {
        super.showSQPersonalDialog(context);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void logout(Context context, SQResultListener sQResultListener) {
        super.logout(context, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void showExitDailog(Context context, SQResultListener sQResultListener) {
        super.showExitDailog(context, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void pay(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, float f, int i2, SQResultListener sQResultListener) {
        super.pay(context, str, str2, str3, str4, str5, str6, str7, str8, i, f, i2, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void submitRoleInfo(HashMap<String, String> map) {
        super.submitRoleInfo(map);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void creatRoleInfo(HashMap<String, String> map) {
        super.creatRoleInfo(map);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void upgradeRoleInfo(HashMap<String, String> map) {
        super.upgradeRoleInfo(map);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void creatRole(Context context, String str) {
        super.creatRole(context, str);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void speechInit(Context context, SQResultListener sQResultListener) {
        super.speechInit(context, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void setServerInfo(String str) {
        super.setServerInfo(str);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void poll() {
        super.poll();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void joinNationalRoom(String str, int i, int i2) {
        super.joinNationalRoom(str, i, i2);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void joinTeamRoom(String str, int i) {
        super.joinTeamRoom(str, i);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void quitRoom(String str, int i) {
        super.quitRoom(str, i);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void openMic() {
        super.openMic();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void closeMic() {
        super.closeMic();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void openSpeaker() {
        super.openSpeaker();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void closeSpeaker() {
        super.closeSpeaker();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void setMicLevel(int i) {
        super.setMicLevel(i);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public int getMicLevel() {
        return super.getMicLevel();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void setSpeakerVolume(int i) {
        super.setSpeakerVolume(i);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public int getSpeakerVolume() {
        return super.getSpeakerVolume();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public boolean testMic() {
        return super.testMic();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void enableSpeakerOn(boolean z) {
        super.enableSpeakerOn(z);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void forbidMemberVoice(int i, boolean z) {
        super.forbidMemberVoice(i, z);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onJoinRoomListener(Context context, SQResultListener sQResultListener) {
        super.onJoinRoomListener(context, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onQuitRoomListener(Context context, SQResultListener sQResultListener) {
        super.onQuitRoomListener(context, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onMemberVoiceListener(Context context, SQResultListener sQResultListener) {
        super.onMemberVoiceListener(context, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onStatusUpdateListener(Context context, SQResultListener sQResultListener) {
        super.onStatusUpdateListener(context, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onStart() {
        super.onStart();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onResume() {
        super.onResume();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onPause() {
        super.onPause();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onStop() {
        super.onStop();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onRestart() {
        super.onRestart();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void setContext(Context context) {
        super.setContext(context);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.IExtensionInterface
    public void performFeatureBBS() {
        super.performFeatureBBS();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.IExtensionInterface
    public void performFeatureVPlayer() {
        super.performFeatureVPlayer();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.IExtensionInterface
    public void performFeature(Context context, String str, Object obj, SQResultListener sQResultListener) {
        super.performFeature(context, str, obj, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.Tool
    public void setScreenshotListener(IScreenshotListener iScreenshotListener) {
        super.setScreenshotListener(iScreenshotListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public Resources getResources(Resources resources) {
        return super.getResources(resources);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public AssetManager getAssets(AssetManager assetManager) {
        return super.getAssets(assetManager);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public ClassLoader getClassLoader(ClassLoader classLoader) {
        return super.getClassLoader(classLoader);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkApi
    public void reportMDev(String str) {
        super.reportMDev(str);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(intent, i);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore
    public void setDebug(Boolean bool) {
        super.setDebug(bool);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkApi
    public SQAppConfig getAppConfig() {
        return super.getAppConfig();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.plugin.sdk.BasePluginInterface, com.sqwan.msdk.api.SQSdkApi
    public boolean isSupportPlugin() {
        return super.isSupportPlugin();
    }

    @Override // com.sqwan.msdk.PluginSQwanCore
    public Class getClass(String str) {
        return super.getClass(str);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkApi
    public void share(String str, SQResultListener sQResultListener) {
        super.share(str, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkApi
    public void share(String str, String str2, SQResultListener sQResultListener) {
        super.share(str, str2, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkApi
    public void share(ShareMessage shareMessage, SQResultListener sQResultListener) {
        super.share(shareMessage, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void showUAgreement(Context context) {
        super.showUAgreement(context);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.SQSdkInterface
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.ILiveshow
    public void joinLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener) {
        super.joinLiveshowRoom(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.ILiveshow
    public void leaveLiveshowRoom(Map<String, String> map, SQResultListener sQResultListener) {
        super.leaveLiveshowRoom(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.ILiveshow
    public void setLiveshowDestroyCallback(SQResultListener sQResultListener) {
        super.setLiveshowDestroyCallback(sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.ILiveshow
    public void setLiveshowVoiceChangeCallback(SQResultListener sQResultListener) {
        super.setLiveshowVoiceChangeCallback(sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.ILiveshow
    public void performLiveshowFeature(Map<String, String> map, SQResultListener sQResultListener) {
        super.performLiveshowFeature(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.ILiveRadio
    public void joinLiveRadioRoom(Map<String, String> map, SQResultListener sQResultListener) {
        super.joinLiveRadioRoom(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.ILiveRadio
    public void leaveLiveRadioRoom(Map<String, String> map, SQResultListener sQResultListener) {
        super.leaveLiveRadioRoom(map, sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.ILiveRadio
    public void setLiveRadioDestroyCallback(SQResultListener sQResultListener) {
        super.setLiveRadioDestroyCallback(sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.ILiveRadio
    public void setLiveRadioVoiceChangeCallback(SQResultListener sQResultListener) {
        super.setLiveRadioVoiceChangeCallback(sQResultListener);
    }

    @Override // com.sqwan.msdk.PluginSQwanCore, com.sqwan.msdk.api.tool.ILiveRadio
    public void performLiveRadioFeature(Map<String, String> map, SQResultListener sQResultListener) {
        super.performLiveRadioFeature(map, sQResultListener);
    }
}
