package com.volcengine.cloudgame;

import android.content.Context;
import android.util.Pair;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.androidcloud.common.log.AcLogConfigSource;
import com.volcengine.androidcloud.common.log.AcTargetType;
import com.volcengine.cloudgame.VeGameEngine;
import com.volcengine.cloudphone.apiservice.AudioService;
import com.volcengine.cloudphone.apiservice.CameraManager;
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
import com.volcengine.cloudphone.apiservice.PodControlService;
import com.volcengine.cloudphone.apiservice.StreamProfileManager;
import com.volcengine.cloudphone.apiservice.outinterface.ICloudCoreManagerStatusListener;
import com.volcengine.cloudphone.apiservice.outinterface.IGamePlayerListener;
import com.volcengine.cloudphone.apiservice.outinterface.InitListener;
import com.volcengine.cloudphone.apiservice.outinterface.MultiUserService;
import com.volcengine.cloudphone.gamepad.GamePadService;
import com.volcengine.common.InitHelper;
import com.volcengine.common.SDKContext;
import com.volcengine.common.contant.CommonConstants;
import com.volcengine.common.innerapi.CloudManager;
import com.volcengine.common.innerapi.ExecutorsService;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class VeGameEngine {
    public static final int IDLE = 1;
    private CloudManager mImpl;
    private InitHelper mInitHelper;
    private final String TAG = "VeGameEngine";
    private final List<ICloudCoreManagerStatusListener> mCloudCoreManagerStatusListeners = new CopyOnWriteArrayList();
    private Boolean mSupportMultiProcess = false;
    private volatile boolean mIsSuccess = false;

    private static class InstanceHolder {
        static VeGameEngine instance = new VeGameEngine();

        private InstanceHolder() {
        }
    }

    public static VeGameEngine getInstance() {
        return InstanceHolder.instance;
    }

    public void prepare(Context context) {
        init(context);
    }

    @Deprecated
    public void init(Context context) {
        if (this.mInitHelper != null) {
            return;
        }
        this.mInitHelper = new InitHelper(context, 1, BuildConfig.PLUGIN_CONFIG_VERSION, "1.32.20-alpha", this.mSupportMultiProcess.booleanValue(), new InitListener() { // from class: com.volcengine.cloudgame.VeGameEngine.1
            @Override // com.volcengine.cloudphone.apiservice.outinterface.InitListener
            public void initSuccess() {
                VeGameEngine.this.onInitSucceed();
            }

            @Override // com.volcengine.cloudphone.apiservice.outinterface.InitListener
            public void initFail(int i, String str) {
                AcLog.d("VeGameEngine", "first download fail");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onInitSucceed() {
        CloudManager cloudManager;
        if (this.mImpl == null && (cloudManager = (CloudManager) SDKContext.getPluginService().loadClass(CommonConstants.CloudManagerImpl, 1)) != null) {
            initCloudManager(cloudManager);
            for (ICloudCoreManagerStatusListener iCloudCoreManagerStatusListener : this.mCloudCoreManagerStatusListeners) {
                iCloudCoreManagerStatusListener.onInitialed();
                iCloudCoreManagerStatusListener.onPrepared();
            }
            this.mCloudCoreManagerStatusListeners.clear();
            this.mIsSuccess = true;
        }
    }

    private void initCloudManager(CloudManager cloudManager) {
        this.mImpl = cloudManager;
        AcLog.d("VeGameEngine", "init success");
        SDKContext.getMonitorService().reportOnlyEvent(CommonConstants.event_initSuccess);
    }

    public int getStatus() {
        if (isAddCore()) {
            return this.mImpl.getStatus();
        }
        return 1;
    }

    public String getDeviceId() {
        return SDKContext.getDid();
    }

    public void rotate(int i) {
        if (isAddCore()) {
            this.mImpl.rotate(i);
        }
    }

    public boolean isPlaying() {
        if (isAddCore()) {
            return this.mImpl.isPlaying();
        }
        return false;
    }

    public void resetToken(String str, String str2, String str3) {
        if (isAddCore()) {
            this.mImpl.resetToken(str, str2, str3);
        }
    }

    public String getServiceDeviceId() {
        return SDKContext.getUUId();
    }

    public void start(GamePlayConfig gamePlayConfig, IGamePlayerListener iGamePlayerListener) {
        SDKContext.checkInitState();
        SDKContext.getMonitorService().reportCategory(CommonConstants.event_startByUser, Collections.singletonMap(CommonConstants.key_gameId, gamePlayConfig.getCloudConfig().getGameId()));
        if (this.mImpl != null) {
            AcLog.d("VeGameEngine", "game play");
            this.mImpl.play(gamePlayConfig.getCloudConfig(), null, iGamePlayerListener);
        } else {
            this.mInitHelper.setPlayListener(new AnonymousClass2(gamePlayConfig, iGamePlayerListener));
            if (this.mInitHelper.getInitStatus() == 2) {
                this.mInitHelper.retryInit();
            }
        }
    }

    /* JADX INFO: renamed from: com.volcengine.cloudgame.VeGameEngine$2, reason: invalid class name */
    class AnonymousClass2 implements InitHelper.StartCallBack {
        final long startTime = System.currentTimeMillis();
        final /* synthetic */ GamePlayConfig val$config;
        final /* synthetic */ IGamePlayerListener val$playerListener;

        AnonymousClass2(GamePlayConfig gamePlayConfig, IGamePlayerListener iGamePlayerListener) {
            this.val$config = gamePlayConfig;
            this.val$playerListener = iGamePlayerListener;
        }

        @Override // com.volcengine.common.InitHelper.StartCallBack
        public void onPrepareStart() {
            AcLog.d("VeGameEngine", "game play");
            SDKContext.getMonitorService().reportCategory(CommonConstants.event_startDelay, Collections.singletonMap(CommonConstants.key_initDelayTime, Long.valueOf(System.currentTimeMillis() - this.startTime)));
            if (VeGameEngine.this.mImpl != null) {
                VeGameEngine.this.mImpl.play(this.val$config.getCloudConfig(), null, this.val$playerListener);
            } else {
                AcLog.e("VeGameEngine", "mImpl is null");
            }
        }

        @Override // com.volcengine.common.InitHelper.StartCallBack
        public void onFail(final int i, final String str) {
            ExecutorsService executorsService = SDKContext.getExecutorsService();
            final IGamePlayerListener iGamePlayerListener = this.val$playerListener;
            executorsService.executeMain(new Runnable() { // from class: com.volcengine.cloudgame.VeGameEngine$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    VeGameEngine.AnonymousClass2.lambda$onFail$0(i, str, iGamePlayerListener);
                }
            });
        }

        static /* synthetic */ void lambda$onFail$0(int i, String str, IGamePlayerListener iGamePlayerListener) {
            Pair pairCreate = Pair.create(Integer.valueOf(i), str);
            SDKContext.getMonitorService().reportError(CommonConstants.event_initFail, ((Integer) pairCreate.first).intValue(), (String) pairCreate.second, -1, "", "error");
            iGamePlayerListener.onError(((Integer) pairCreate.first).intValue(), (String) pairCreate.second);
        }
    }

    public void resume() {
        if (isAddCore()) {
            this.mImpl.resumePlay();
        }
    }

    public void pause() {
        if (isAddCore()) {
            this.mImpl.pause();
        }
    }

    public void stop() {
        InitHelper initHelper = this.mInitHelper;
        if (initHelper != null) {
            initHelper.clearPlayerListener();
        }
        if (isAddCore()) {
            this.mImpl.stop(true);
        }
    }

    public void restart() {
        if (isAddCore()) {
            this.mImpl.restart();
        }
    }

    public void muteAudio(boolean z) {
        if (isAddCore()) {
            this.mImpl.muteAudio(z);
        }
    }

    public boolean isAudioMuted() {
        if (isAddCore()) {
            return this.mImpl.isAudioMuted();
        }
        return false;
    }

    @Deprecated
    public boolean isOpenMic() {
        if (isAddCore()) {
            return this.mImpl.isOpenMic();
        }
        return false;
    }

    public void sendKeyEvent(int i) {
        if (isAddCore()) {
            this.mImpl.sendKeyEvent(i);
        }
    }

    public PodControlService getPodControlService() {
        if (isAddCore()) {
            return this.mImpl.getPodControlService();
        }
        return null;
    }

    public String getDebugData() {
        StringBuilder sb = new StringBuilder();
        sb.append(SDKContext.getMonitorService().getCommonExtra().toString());
        sb.append("\n");
        if (isAddCore()) {
            sb.append(this.mImpl.getDebugData());
        }
        return sb.toString();
    }

    public GameGroundSwitchManager getGameGroundSwitchManager() {
        if (isAddCore()) {
            return this.mImpl.getGameGroundSwitchManager();
        }
        return null;
    }

    @Deprecated
    public void enableMic(boolean z) {
        if (isAddCore()) {
            this.mImpl.enableMic(z);
        }
    }

    @Deprecated
    public boolean isEnableMic() {
        if (isAddCore()) {
            return this.mImpl.isOpenMic();
        }
        return false;
    }

    public void enableAccelSensor(boolean z) {
        if (isAddCore()) {
            this.mImpl.enableAccelSensor(z);
        }
    }

    public void enableGyroscopeSensor(boolean z) {
        if (isAddCore()) {
            this.mImpl.enableGyroscopeSensor(z);
        }
    }

    public void enableGravitySensor(boolean z) {
        if (isAddCore()) {
            this.mImpl.enableGravitySensor(z);
        }
    }

    public void enableOrientationSensor(boolean z) {
        if (isAddCore()) {
            this.mImpl.enableOrientationSensor(z);
        }
    }

    public void enableMagneticSensor(boolean z) {
        if (isAddCore()) {
            this.mImpl.enableMagneticSensor(z);
        }
    }

    public void enableVibrator(boolean z) {
        if (isAddCore()) {
            this.mImpl.enableVibrator(z);
        }
    }

    public void enableLocationService(boolean z) {
        if (isAddCore()) {
            this.mImpl.enableLocationService(z);
        }
    }

    public GamePadService getGamePadService() {
        if (isAddCore()) {
            return this.mImpl.getGamePadService();
        }
        return null;
    }

    public ICommonDataChannel getCommonDataChannel() {
        if (isAddCore()) {
            return this.mImpl.getCommonDataChannel();
        }
        return null;
    }

    public IMessageChannel getMessageChannel() {
        if (isAddCore()) {
            return this.mImpl.getMessageChannel();
        }
        return null;
    }

    public IFileChannelExt getFileChannelExt() {
        if (isAddCore()) {
            return this.mImpl.getFileChannelExt();
        }
        return null;
    }

    public StreamProfileManager getClarityService() {
        if (isAddCore()) {
            return this.mImpl.getClarityService();
        }
        return null;
    }

    public CameraManager getCameraManager() {
        if (isAddCore()) {
            return this.mImpl.getCameraManager();
        }
        return null;
    }

    public AudioService getAudioService() {
        if (isAddCore()) {
            return this.mImpl.getAudioService();
        }
        return null;
    }

    public LocationService getLocationService() {
        if (isAddCore()) {
            return this.mImpl.getLocationServiceImpl();
        }
        return null;
    }

    public LocalInputManager getLocalInputManager() {
        if (isAddCore()) {
            return this.mImpl.getInputMethodManager();
        }
        return null;
    }

    public IODeviceManager getIODeviceManager() {
        if (isAddCore()) {
            return this.mImpl.getIODeviceManager();
        }
        return null;
    }

    public GamePodControlService getGamePodControlService() {
        if (isAddCore()) {
            return this.mImpl.getGamePodControlService();
        }
        return null;
    }

    public void addCloudCoreManagerListener(ICloudCoreManagerStatusListener iCloudCoreManagerStatusListener) {
        if (this.mIsSuccess) {
            iCloudCoreManagerStatusListener.onInitialed();
            iCloudCoreManagerStatusListener.onPrepared();
        } else {
            this.mCloudCoreManagerStatusListeners.add(iCloudCoreManagerStatusListener);
        }
    }

    public void removeCloudCoreManagerListener(ICloudCoreManagerStatusListener iCloudCoreManagerStatusListener) {
        this.mCloudCoreManagerStatusListeners.remove(iCloudCoreManagerStatusListener);
    }

    public IClipBoardServiceManager getClipBoardServiceManager() {
        if (isAddCore()) {
            return this.mImpl.getClipBoardServiceManager();
        }
        return null;
    }

    public MultiUserService getMultiUserService() {
        if (isAddCore()) {
            return this.mImpl.getMultiUserService();
        }
        return null;
    }

    public void volumeUp() {
        if (isAddCore()) {
            this.mImpl.volumeUp();
        }
    }

    public void volumeDown() {
        if (isAddCore()) {
            this.mImpl.volumeDown();
        }
    }

    public static void setDebug(boolean z) {
        int iIntValue;
        SDKContext.setDebug(z);
        AcLog.setDebug(AcLogConfigSource.CompileConfig, z);
        if (z) {
            iIntValue = AcTargetType.LocalStorageTarget.getValue().intValue() | AcTargetType.LoggingTarget.getValue().intValue();
        } else {
            iIntValue = AcTargetType.LocalStorageTarget.getValue().intValue();
        }
        AcLog.setTargets(iIntValue);
    }

    public void setInterceptTouchEvent(boolean z) {
        if (isAddCore()) {
            this.mImpl.setInterceptTouchEvent(z);
        }
    }

    public boolean isInterceptTouch() {
        if (isAddCore()) {
            return this.mImpl.isInterceptTouch();
        }
        return false;
    }

    public void probeStart(final GamePlayConfig gamePlayConfig, final IProbeNetworkListener iProbeNetworkListener) {
        if (isAddCore()) {
            this.mImpl.probeStart(gamePlayConfig.getCloudConfig(), iProbeNetworkListener);
        } else {
            addCloudCoreManagerListener(new ICloudCoreManagerStatusListener() { // from class: com.volcengine.cloudgame.VeGameEngine.3
                @Override // com.volcengine.cloudphone.apiservice.outinterface.ICloudCoreManagerStatusListener
                public void onInitialed() {
                }

                @Override // com.volcengine.cloudphone.apiservice.outinterface.ICloudCoreManagerStatusListener
                public void onPrepared() {
                    VeGameEngine.this.mImpl.probeStart(gamePlayConfig.getCloudConfig(), iProbeNetworkListener);
                }
            });
        }
    }

    public void probeInterrupt() {
        if (isAddCore()) {
            this.mImpl.probeInterrupt();
        }
    }

    public static String getSDKVersion() {
        return "1.32.20-alpha";
    }

    public static String getBuildTime() {
        return BuildConfig.BUILD_TIME;
    }

    private boolean isAddCore() {
        return this.mImpl != null;
    }
}
