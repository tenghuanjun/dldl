package com.huya.ciku.apm.tracker;

import com.duowan.auk.util.L;
import com.huya.berry.client.HuyaBerry;
import com.taptap.sdk.kit.internal.p000const.TrackAction;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LivingTracker implements ILiveTracker, ITracker {
    private static final int AUDIO_CNT_INTERVAL = 10000;
    private static final int AUDIO_ENCODE_CNT_INTERVAL = 10000;
    private static final String TAG = "LivingTracker";
    private static final int VIDEO_CNT_INTERVAL = 10000;
    private static final int VIDEO_ENCODE_CNT_INTERVAL = 10000;
    private static LivingTracker smInstance;
    private int mAudioEncodeFrameCnt;
    private int mAudioFrameCnt;
    private int mVideoEncodeFrameCnt;
    private int mVideoFrameCnt;
    private long mVideoCaptureCntLogTime = 0;
    private long mAudioCaptureCntLogTime = 0;
    private long mVideoEncodeCntLogTime = 0;
    private long mAudioEncodeCntLogTime = 0;
    private BeginLivingTracker beginLivingTracker = new BeginLivingTracker();

    @Override // com.huya.ciku.apm.tracker.ITracker
    public String getTag() {
        return TAG;
    }

    public static LivingTracker getInstance() {
        if (smInstance == null) {
            synchronized (LivingTracker.class) {
                if (smInstance == null) {
                    smInstance = new LivingTracker();
                }
            }
        }
        return smInstance;
    }

    private LivingTracker() {
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void start() {
        L.info(TAG, "start");
        this.mVideoCaptureCntLogTime = 0L;
        this.mAudioCaptureCntLogTime = 0L;
        this.mVideoFrameCnt = 0;
        this.mAudioFrameCnt = 0;
        this.mVideoEncodeCntLogTime = 0L;
        this.mAudioEncodeCntLogTime = 0L;
        this.mVideoEncodeFrameCnt = 0;
        this.mAudioEncodeFrameCnt = 0;
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void startLive() {
        L.info(TAG, HuyaBerry.BerryEvent.BERRYEVENT_EVENTTYPE_STARTLIVE);
        this.beginLivingTracker.startLive();
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void beginLiveFail(ErrorCode errorCode) {
        L.info(TAG, "beginLiveFail: { code = " + errorCode.getCode() + ", svrRespCode = " + errorCode.getSvrRespCode() + ", msg = " + errorCode.getMsg());
        this.beginLivingTracker.beginLiveFail(errorCode);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void beginLiveSuccess() {
        L.info(TAG, "beginLiveSuccess");
        this.beginLivingTracker.beginLiveSuccess();
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void startHuyaPush() {
        L.info(TAG, "startHuyaPush");
        this.beginLivingTracker.startHuyaPush();
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void startRtmpPush() {
        L.info(TAG, "startRtmpPush");
        this.beginLivingTracker.startRTMPPush();
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void startConnectVp() {
        L.info(TAG, "startConnectVp");
        this.beginLivingTracker.startConnectVp();
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onVpResult(int i) {
        L.info(TAG, "onVpResult : " + i);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onVpConnect(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("onVpConnect ");
        sb.append(z ? "success" : TrackAction.FAIL);
        L.info(TAG, sb.toString());
        if (z) {
            this.beginLivingTracker.onConnectVpSuccess();
        } else {
            this.beginLivingTracker.onConnectVpFail();
        }
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onVideoPublishReady(int i) {
        L.info(TAG, "onVideoPublishReady " + i);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onAudioPublishReady(int i) {
        L.info(TAG, "onAudioPublishReady " + i);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onHuyaConnect(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("onHuyaConnect ");
        sb.append(z ? "success" : TrackAction.FAIL);
        L.info(TAG, sb.toString());
        this.beginLivingTracker.onHuyaConnect(z);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onHuyaPush(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("onHuyaPush ");
        sb.append(z ? "success" : TrackAction.FAIL);
        L.info(TAG, sb.toString());
        if (z) {
            this.beginLivingTracker.huyaPushSuccess();
        } else {
            this.beginLivingTracker.huyaPushFail();
        }
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onRtmpPushResult(int i) {
        L.info(TAG, "onRtmpPushResult " + i);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onRtmpConnect(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("onRtmpConnect ");
        sb.append(z ? "success" : TrackAction.FAIL);
        L.info(TAG, sb.toString());
        if (z) {
            this.beginLivingTracker.onRtmpConnectSuccess();
        }
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onRtmpPush(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("onRtmpPush ");
        sb.append(z ? "success" : TrackAction.FAIL);
        L.info(TAG, sb.toString());
        if (z) {
            this.beginLivingTracker.rtmpPushSuccess();
        } else {
            this.beginLivingTracker.rtmpPushFail();
        }
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onSwitchPush() {
        L.info(TAG, "onSwitchPush");
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onStartVideoCaptureError(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("onStartVideoCaptureError ");
        sb.append(z ? "success" : TrackAction.FAIL);
        L.info(TAG, sb.toString());
        this.beginLivingTracker.onVideoCaptureResult(z);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onVideoCaptureError(int i) {
        L.info(TAG, "onVideoCaptureError " + i);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onCreateAudioMediaCodecError() {
        L.info(TAG, "onCreateAudioMediaCodecError ");
        this.beginLivingTracker.onAudioCaptureResult(false);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onAudioMediaCodecError() {
        L.info(TAG, "onAudioMediaCodecError ");
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onAudioCaptureError() {
        L.info(TAG, "onAudioCaptureError ");
        this.beginLivingTracker.onAudioCaptureResult(false);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onCreateVideoCodecError() {
        L.info(TAG, "onCreateVideoCodecError ");
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onVideoSendStart() {
        this.beginLivingTracker.onMayBeFirstVideoSendStart();
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onVideoSendEnd(boolean z) {
        this.beginLivingTracker.onMayBeFirstVideoSendEnd(z);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onVideoCaptureResult() {
        int i = this.mVideoFrameCnt + 1;
        this.mVideoFrameCnt = i;
        if (i == 1 && this.mVideoCaptureCntLogTime == 0) {
            L.info(TAG, "first videoCapture success");
        }
        if (System.currentTimeMillis() - this.mVideoCaptureCntLogTime > 10000) {
            this.mVideoFrameCnt = 0;
            this.mVideoCaptureCntLogTime = System.currentTimeMillis();
        }
        this.beginLivingTracker.onVideoCaptureResult(true);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onVideoEncodeResult() {
        int i = this.mVideoEncodeFrameCnt + 1;
        this.mVideoEncodeFrameCnt = i;
        if (i == 1 && this.mVideoEncodeCntLogTime == 0) {
            L.info(TAG, "first videoEncode success");
        }
        if (System.currentTimeMillis() - this.mVideoEncodeCntLogTime > 10000) {
            this.mVideoEncodeFrameCnt = 0;
            this.mVideoEncodeCntLogTime = System.currentTimeMillis();
        }
        this.beginLivingTracker.onVideoEncodeResult(true);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onAudioCaptureResult() {
        int i = this.mAudioFrameCnt + 1;
        this.mAudioFrameCnt = i;
        if (i == 1 && this.mAudioCaptureCntLogTime == 0) {
            L.info(TAG, "first audioCapture success");
        }
        if (System.currentTimeMillis() - this.mAudioCaptureCntLogTime > 10000) {
            this.mAudioFrameCnt = 0;
            this.mAudioCaptureCntLogTime = System.currentTimeMillis();
        }
        this.beginLivingTracker.onAudioCaptureResult(true);
    }

    @Override // com.huya.ciku.apm.tracker.ILiveTracker
    public void onAudioEncodeResult() {
        int i = this.mAudioEncodeFrameCnt + 1;
        this.mAudioEncodeFrameCnt = i;
        if (i == 1 && this.mAudioEncodeCntLogTime == 0) {
            L.info(TAG, "first audioEncode success");
        }
        if (System.currentTimeMillis() - this.mAudioEncodeCntLogTime > 10000) {
            this.mAudioEncodeFrameCnt = 0;
            this.mAudioEncodeCntLogTime = System.currentTimeMillis();
        }
        this.beginLivingTracker.onAudioEncodeResult(true);
    }

    public void stop() {
        L.info(TAG, "onStop");
        this.beginLivingTracker.stop();
    }
}
