package com.youme.voiceengine;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class api {
    public static final int YOUME_RTC_BGM_TO_MIC = 1;
    public static final int YOUME_RTC_BGM_TO_SPEAKER = 0;
    public static String m_strCallbackName;

    public static native int clearMagicVoiceInfo();

    public static native int getBackgroundMusicVolume();

    public static native long getBgmDuration();

    public static native long getBgmProgress();

    public static native int getChannelUserList(String str, int i, boolean z);

    public static native int getEffectVolume();

    public static native boolean getMagicVoiceEffectEnabled();

    public static native int getMicVolume();

    public static native int getMicbypassVolume();

    public static native boolean getMicrophoneMute();

    public static native String getSdkInfo();

    public static native float getSoundtouchPitchSemiTones();

    public static native boolean getSpeakerMute();

    public static native boolean getUseMobileNetworkEnabled();

    public static native int getUserRole();

    public static native int getVolume();

    public static native int init(String str, String str2, int i, String str3);

    public static native boolean isBackgroundMusicPlaying();

    public static native boolean isInChannel(String str);

    public static native boolean isInited();

    public static native boolean isJoined();

    public static native int joinChannelMultiMode(String str, String str2, int i);

    public static native int joinChannelMultiMode(String str, String str2, int i, boolean z);

    public static native int joinChannelSingleMode(String str, String str2, int i);

    public static native int joinChannelSingleMode(String str, String str2, int i, boolean z);

    public static native int kickOtherFromChannel(String str, String str2, int i);

    public static native int leaveChannelAll();

    public static native int leaveChannelMultiMode(String str);

    public static native int pauseAllEffects();

    public static native int pauseBackgroundMusic();

    public static native int pauseChannel();

    public static native int pauseEffect(int i);

    public static native int playBackgroundMusic(String str, boolean z);

    public static native int playEffect(String str, int i);

    public static native int releaseGrabMic(String str);

    public static native int reportAudio();

    public static native int requestGrabMic(String str, int i, boolean z, String str2);

    public static native int requestInviteMic(String str, String str2, String str3);

    public static native int requestRestApi(String str, String str2);

    public static native int responseInviteMic(String str, boolean z, String str2);

    public static native int resumeAllEffects();

    public static native int resumeBackgroundMusic();

    public static native int resumeChannel();

    public static native int resumeEffect(int i);

    public static native int sendMessage(String str, String str2);

    public static native int setAudioEffectType(int i);

    public static native void setAutoSendStatus(boolean z);

    public static native int setBackgroundMusicVolume(int i);

    public static native void setBgmProgress(long j);

    public static native int setChannelAudioMode(int i);

    public static native int setEffectVolume(int i);

    public static native int setExitCommModeWhenHeadsetPlugin(boolean z);

    public static native int setExternalSoundCardMode(boolean z);

    public static native int setFarendVoiceLevelCallback(int i);

    public static native int setForceDisableAEC(boolean z);

    public static native int setForceDisableAGC(boolean z);

    public static native int setGrabMicOption(String str, int i, int i2, int i3, int i4);

    public static native int setHeadsetMonitorOn(boolean z);

    public static native int setHeadsetMonitorOn(boolean z, boolean z2);

    public static native int setInviteMicOption(String str, int i, int i2);

    public static native int setListenOtherVoice(String str, boolean z);

    public static native int setMagicVoiceAdjust(double d, double d2);

    public static native int setMagicVoiceEnable(boolean z);

    public static native int setMagicVoiceInfo(String str);

    public static native int setMicLevelCallback(int i);

    public static native void setMicVolume(int i);

    public static native int setMicbypassVolume(int i);

    public static native void setMicrophoneMute(boolean z);

    public static native int setOtherMicMute(String str, boolean z);

    public static native int setOtherSpeakerMute(String str, boolean z);

    public static native int setOutputToSpeaker(boolean z);

    public static native void setPlayingTimeMs(int i);

    public static native void setRecordingTimeMs(int i);

    public static native int setReleaseMicWhenMute(boolean z);

    public static native int setReportBufferTime(int i);

    public static native int setReverbEnabled(boolean z);

    public static native void setServerRegion(int i, String str, boolean z);

    public static native int setSoundtouchPitchSemiTones(float f);

    public static native void setSpeakerMute(boolean z);

    public static native void setToken(String str);

    public static native void setTokenV3(String str, long j);

    public static native void setUseMobileNetworkEnabled(boolean z);

    public static native int setUserRole(int i);

    public static native int setVadCallbackEnabled(boolean z);

    public static native void setVolume(int i);

    public static native int setWhiteUserList(String str, String str2);

    public static native int speakToChannel(String str);

    public static native int startGrabMicAction(String str, String str2);

    public static native int stopAllEffects();

    public static native int stopBackgroundMusic();

    public static native int stopEffect(int i);

    public static native int stopGrabMicAction(String str, String str2);

    public static native int stopInviteMic();

    public static native int unInit();

    public static void SetCallback(YouMeCallBackInterface youMeCallBackInterface) {
        IYouMeEventCallback.callBack = youMeCallBackInterface;
    }

    public static void setPcmCallbackEnable(YouMeCallBackInterfacePcm youMeCallBackInterfacePcm, int i, boolean z, int i2, int i3) {
        Log.d("Api", "setPcmCallbackEnable");
        IYouMeEventCallback.mCallbackPcm = youMeCallBackInterfacePcm;
        if (youMeCallBackInterfacePcm == null) {
            NativeEngine.setPcmCallbackEnable(0, false, i2, i3);
        } else {
            NativeEngine.setPcmCallbackEnable(i, z, i2, i3);
        }
    }

    public static void setPcmCallbackEnableForUnity3D(YouMeCallBackInterfacePcmForUnity youMeCallBackInterfacePcmForUnity, int i, boolean z, int i2, int i3) {
        IYouMeEventCallback.mCallbackPcmForUntiy = youMeCallBackInterfacePcmForUnity;
        NativeEngine.setPcmCallbackEnable(i, z, i2, i3);
    }

    public static boolean releaseMicSync() {
        AudioRecorder.OnAudioRecorderTmp(0);
        return true;
    }

    public static boolean resumeMicSync() {
        AudioRecorder.OnAudioRecorderTmp(1);
        return true;
    }
}
