package com.volcengine.common.innerapi;

import com.volcengine.cloudphone.apiservice.AppGroundSwitchManager;
import com.volcengine.cloudphone.apiservice.AudioService;
import com.volcengine.cloudphone.apiservice.CameraManager;
import com.volcengine.cloudphone.apiservice.FileExchange;
import com.volcengine.cloudphone.apiservice.GameGroundSwitchManager;
import com.volcengine.cloudphone.apiservice.GamePodControlService;
import com.volcengine.cloudphone.apiservice.IClipBoardServiceManager;
import com.volcengine.cloudphone.apiservice.ICommonDataChannel;
import com.volcengine.cloudphone.apiservice.IFileChannelExt;
import com.volcengine.cloudphone.apiservice.IMessageChannel;
import com.volcengine.cloudphone.apiservice.IODeviceManager;
import com.volcengine.cloudphone.apiservice.IProbeNetworkListener;
import com.volcengine.cloudphone.apiservice.LocalInputManager;
import com.volcengine.cloudphone.apiservice.LocationService;
import com.volcengine.cloudphone.apiservice.MultiMediaStreamService;
import com.volcengine.cloudphone.apiservice.PodControlService;
import com.volcengine.cloudphone.apiservice.StreamProfileManager;
import com.volcengine.cloudphone.apiservice.TouchEventService;
import com.volcengine.cloudphone.apiservice.UserService;
import com.volcengine.cloudphone.apiservice.VideoRenderModeManager;
import com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener;
import com.volcengine.cloudphone.apiservice.outinterface.IPlayerListener;
import com.volcengine.cloudphone.apiservice.outinterface.MultiUserService;
import com.volcengine.cloudphone.base.CloudConfig;
import com.volcengine.cloudphone.gamepad.GamePadService;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public interface CloudManager {
    void closeApp(String str);

    void enableAccelSensor(boolean z);

    void enableGravitySensor(boolean z);

    void enableGyroscopeSensor(boolean z);

    void enableLocationService(boolean z);

    void enableMagneticSensor(boolean z);

    void enableMic(boolean z);

    void enableOrientationSensor(boolean z);

    void enableVibrator(boolean z);

    AppGroundSwitchManager getAppGroundSwitchManager();

    AudioService getAudioService();

    CameraManager getCameraManager();

    StreamProfileManager getClarityService();

    IClipBoardServiceManager getClipBoardServiceManager();

    ICommonDataChannel getCommonDataChannel();

    String getDebugData();

    IFileChannelExt getFileChannelExt();

    FileExchange getFileExchange();

    GameGroundSwitchManager getGameGroundSwitchManager();

    GamePadService getGamePadService();

    GamePodControlService getGamePodControlService();

    IODeviceManager getIODeviceManager();

    LocalInputManager getInputMethodManager();

    LocationService getLocationServiceImpl();

    IMessageChannel getMessageChannel();

    MultiMediaStreamService getMultiMediaStreamService();

    MultiUserService getMultiUserService();

    PodControlService getPodControlService();

    int getServiceType();

    int getStatus();

    TouchEventService getTouchEventService();

    UserService getUserService();

    VideoRenderModeManager getVideoRenderModeManager();

    boolean isAudioMuted();

    boolean isInterceptTouch();

    boolean isOpenMic();

    boolean isPlaying();

    boolean isVideoMuted();

    void launchApp(String str);

    void muteAudio(boolean z);

    void muteVideo(boolean z);

    void pause();

    void play(CloudConfig cloudConfig, IPlayerListener iPlayerListener, IGamePlayerListener iGamePlayerListener);

    void probeInterrupt();

    void probeStart(CloudConfig cloudConfig, IProbeNetworkListener iProbeNetworkListener);

    void resetToken(String str, String str2, String str3);

    void restart();

    void resumePlay();

    void rotate(int i);

    int sendKeyEvent(int i);

    int sendKeyEvent(int i, int i2);

    void setInterceptTouchEvent(boolean z);

    void stop(boolean z);

    void turnOffMic();

    void turnOnMic();

    void volumeDown();

    void volumeUp();
}
