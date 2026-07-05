package com.sqwan.msdk.api;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import com.sqwan.msdk.api.tool.IExtensionInterface;
import com.sqwan.msdk.api.tool.Tool;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface SQSdkInterface extends Tool, IExtensionInterface {
    void changeAccount(Context context, SQResultListener sQResultListener);

    void closeMic();

    void closeSpeaker();

    void creatRole(Context context, String str);

    void creatRoleInfo(HashMap<String, String> map);

    void enableSpeakerOn(boolean z);

    void forbidMemberVoice(int i, boolean z);

    int getMicLevel();

    int getSpeakerVolume();

    void joinNationalRoom(String str, int i, int i2);

    void joinTeamRoom(String str, int i);

    void login(Context context, SQResultListener sQResultListener);

    void logout(Context context, SQResultListener sQResultListener);

    void onActivityResult(int i, int i2, Intent intent);

    void onConfigurationChanged(Configuration configuration);

    void onDestroy();

    void onJoinRoomListener(Context context, SQResultListener sQResultListener);

    void onMemberVoiceListener(Context context, SQResultListener sQResultListener);

    void onNewIntent(Intent intent);

    void onPause();

    void onQuitRoomListener(Context context, SQResultListener sQResultListener);

    void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    void onRestart();

    void onResume();

    void onStart();

    void onStatusUpdateListener(Context context, SQResultListener sQResultListener);

    void onStop();

    void onWindowFocusChanged(boolean z);

    void openMic();

    void openSpeaker();

    void pay(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, float f, int i2, SQResultListener sQResultListener);

    void poll();

    void printLog(int i, String str, String str2);

    void quitRoom(String str, int i);

    void setBackToGameLoginListener(SQResultListener sQResultListener);

    void setContext(Context context);

    void setMicLevel(int i);

    void setServerInfo(String str);

    void setSpeakerVolume(int i);

    void setSwitchAccountListener(SQResultListener sQResultListener);

    void showExitDailog(Context context, SQResultListener sQResultListener);

    void showSQPersonalDialog(Context context);

    void showSQWebDialog(String str);

    void showUAgreement(Context context);

    void speechInit(Context context, SQResultListener sQResultListener);

    void submitRoleInfo(HashMap<String, String> map);

    boolean testMic();

    void upgradeRoleInfo(HashMap<String, String> map);
}
