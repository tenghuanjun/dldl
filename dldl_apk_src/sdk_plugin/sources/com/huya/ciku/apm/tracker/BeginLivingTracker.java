package com.huya.ciku.apm.tracker;

import android.os.Handler;
import android.os.Looper;
import com.duowan.auk.util.L;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.ciku.apm.constant.BeginLiveConstant;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class BeginLivingTracker implements ITracker {
    private static final int ACTION_CONNECT_HUYA_VP_FAIL = 5;
    private static final int ACTION_CONNECT_HUYA_VP_SUCCESS = 4;
    public static final int ACTION_END_BEGIN_LIVE_WUP = 1;
    private static final int ACTION_FIRST_PUSH_SUCCESS = 8;
    private static final int ACTION_PUSH_CONNECT_SUCCESS = 6;
    private static final int ACTION_PUSH_FAIL = 7;
    public static final int ACTION_START_BEGIN_LIVE = 0;
    private static final int ACTION_START_CONNECT_HUYA_VP = 3;
    private static final int ACTION_START_PUSH = 2;
    private static final int BEGIN_LIVE_TIME_1 = 5000;
    private static final int BEGIN_LIVE_TIME_2 = 10000;
    private static final int BEGIN_LIVE_TIME_3 = 60000;
    private static final int PUSH_HUYA_TYPE = 1;
    private static final int PUSH_RTMP_TYPE = 2;
    private static final String TAG = "BeginLivingTracker";
    private static final int UNDEFINED = -1;
    private long mStartBeginLiveTime;
    private int pushType;
    private boolean started;
    private int state = -1;
    private int extraCode = 0;
    private boolean firstVideoSend = false;
    private boolean videoCaptureSuccess = false;
    private boolean audioCaptureSuccess = false;
    private boolean videoEncodeSuccess = false;
    private boolean audioEncodeSuccess = false;
    private final Runnable mBeginLiveTimeoutRunnable = new Runnable() { // from class: com.huya.ciku.apm.tracker.BeginLivingTracker.1
        @Override // java.lang.Runnable
        public void run() {
            L.info(BeginLivingTracker.TAG, "beginLiveTimeOut-------, time = 60000");
            if (BeginLivingTracker.this.state == 3) {
                BeginLivingTracker.this.report(BeginLiveConstant.ERR_HUYA_VP_TIME_OUT);
            } else if (BeginLivingTracker.this.state == 5) {
                BeginLivingTracker.this.report(BeginLiveConstant.ERR_HUYA_VP_TRY_TIME_OUT);
            } else if (BeginLivingTracker.this.state == 4) {
                BeginLivingTracker.this.report(BeginLiveConstant.ERR_HUYA_LINK_TIME_OUT);
            } else if (BeginLivingTracker.this.state == 6) {
                if (BeginLivingTracker.this.firstVideoSend) {
                    if (BeginLivingTracker.this.pushType == 1) {
                        BeginLivingTracker.this.report(BeginLiveConstant.ERR_HUYA_PUSH_TIME_OUT);
                    } else {
                        BeginLivingTracker.this.report(BeginLiveConstant.ERR_RTMP_PUSH_TIME_OUT);
                    }
                } else if (!BeginLivingTracker.this.audioCaptureSuccess) {
                    BeginLivingTracker.this.report(BeginLiveConstant.ERR_AUDIO_COLLECTION_FAIL);
                } else if (!BeginLivingTracker.this.videoCaptureSuccess) {
                    BeginLivingTracker.this.report(BeginLiveConstant.ERR_VIDEO_COLLECTION_FAIL);
                } else if (!BeginLivingTracker.this.videoEncodeSuccess) {
                    BeginLivingTracker.this.report(BeginLiveConstant.ERR_VIDEO_ENCODER_FAIL);
                } else if (!BeginLivingTracker.this.audioEncodeSuccess) {
                    BeginLivingTracker.this.report(BeginLiveConstant.ERR_AUDIO_OTHER_FAIL);
                } else if (BeginLivingTracker.this.pushType == 1) {
                    BeginLivingTracker.this.report(BeginLiveConstant.ERR_HUYA_PUSH_TIME_OUT);
                } else {
                    BeginLivingTracker.this.report(BeginLiveConstant.ERR_RTMP_PUSH_TIME_OUT);
                }
            } else if (BeginLivingTracker.this.state == 2) {
                if (BeginLivingTracker.this.pushType == 2) {
                    BeginLivingTracker.this.report(BeginLiveConstant.ERR_RTMP_CONNECT_TIME_OUT);
                } else {
                    BeginLivingTracker.this.report(BeginLiveConstant.ERR_HUYA_LINK_TIME_OUT);
                }
            } else if (BeginLivingTracker.this.state == 0) {
                BeginLivingTracker.this.report(BeginLiveConstant.ERR_START_LIVE_TIMEOUT);
            } else if (BeginLivingTracker.this.state == 7) {
                if (BeginLivingTracker.this.pushType == 2) {
                    BeginLivingTracker.this.report(BeginLiveConstant.ERR_RTMP_PUSH_FAIL);
                } else {
                    BeginLivingTracker.this.report(BeginLiveConstant.ERR_HUYA_PUSH_FAIL);
                }
            }
            BeginLivingTracker.this.state = -1;
        }
    };
    private MonitorCenter monitorCenter = MonitorCenter.getInstance();
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override // com.huya.ciku.apm.tracker.ITracker
    public String getTag() {
        return TAG;
    }

    BeginLivingTracker() {
    }

    void stop() {
        this.handler.removeCallbacks(this.mBeginLiveTimeoutRunnable);
        this.state = -1;
        this.started = false;
    }

    void startLive() {
        if (!this.started) {
            this.state = 0;
            this.pushType = 0;
            this.mStartBeginLiveTime = System.currentTimeMillis();
            this.handler.removeCallbacks(this.mBeginLiveTimeoutRunnable);
            this.handler.postDelayed(this.mBeginLiveTimeoutRunnable, 60000L);
            this.firstVideoSend = false;
            this.started = true;
            this.videoCaptureSuccess = false;
            this.audioCaptureSuccess = false;
            this.videoEncodeSuccess = false;
            this.audioEncodeSuccess = false;
            return;
        }
        L.info(TAG, "startLive but not the first startLive");
    }

    void beginLiveFail(ErrorCode errorCode) {
        if (checkState()) {
            this.handler.removeCallbacks(this.mBeginLiveTimeoutRunnable);
            if (errorCode.equals(ErrorCode.ERR_LIVE_CONFIG_INVALID)) {
                report(BeginLiveConstant.ERR_LIVE_CONFIG_INVALID);
            } else if (errorCode.equals(ErrorCode.ERR_TICKET_INVALID)) {
                report(BeginLiveConstant.ERR_TICKET_EMPTY, errorCode.getSvrRespCode(), errorCode.getMsg());
            } else if (errorCode.equals(ErrorCode.ERR_START_LIVE_FAIL)) {
                report(BeginLiveConstant.ERR_START_LIVE_FAIL, errorCode.getSvrRespCode(), errorCode.getMsg());
            } else if (errorCode.equals(ErrorCode.ERR_START_LIVE_TIMEOUT)) {
                report(BeginLiveConstant.ERR_START_LIVE_TIMEOUT, errorCode.getSvrRespCode(), errorCode.getMsg());
            } else if (errorCode.equals(ErrorCode.ERR_NO_STREAM_NAME)) {
                report(BeginLiveConstant.ERR_NO_STREAM_NAME, errorCode.getSvrRespCode(), errorCode.getMsg());
            }
            this.state = -1;
        }
    }

    void beginLiveSuccess() {
        this.state = 1;
    }

    void onVideoCaptureResult(boolean z) {
        if (checkState()) {
            this.videoCaptureSuccess = z;
        }
    }

    void onAudioCaptureResult(boolean z) {
        if (checkState()) {
            this.audioCaptureSuccess = z;
        }
    }

    void onVideoEncodeResult(boolean z) {
        if (checkState()) {
            this.videoEncodeSuccess = z;
        }
    }

    void onAudioEncodeResult(boolean z) {
        if (checkState()) {
            this.audioEncodeSuccess = z;
        }
    }

    void startHuyaPush() {
        if (checkState()) {
            this.pushType = 1;
            this.state = 2;
        }
    }

    void startRTMPPush() {
        if (checkState()) {
            this.pushType = 2;
            this.state = 2;
        }
    }

    void startConnectVp() {
        if (checkState()) {
            this.state = 3;
        }
    }

    void onConnectVpSuccess() {
        if (checkState()) {
            this.state = 4;
        }
    }

    void onConnectVpFail() {
        if (checkState()) {
            this.state = 5;
        }
    }

    void rtmpPushFail() {
        if (checkState()) {
            this.state = 7;
        }
    }

    void huyaPushFail() {
        if (checkState()) {
            this.state = 7;
        }
    }

    void huyaPushSuccess() {
        int iCode;
        if (checkState()) {
            this.state = -1;
            this.handler.removeCallbacks(this.mBeginLiveTimeoutRunnable);
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mStartBeginLiveTime;
            L.info(TAG, "huyaPushSuccess -------------, time = " + jCurrentTimeMillis);
            if (jCurrentTimeMillis < OAIDHelper.TIMEOUT) {
                iCode = BeginLiveConstant.SUCCESS.code();
            } else if (jCurrentTimeMillis < 10000) {
                iCode = BeginLiveConstant.SUCCESS_TEN_S.code();
            } else {
                iCode = jCurrentTimeMillis < 60000 ? BeginLiveConstant.SUCCESS_MINUTE.code() : 0;
            }
            this.monitorCenter.reportBeginLive(iCode, 0, "");
            this.state = 8;
        }
    }

    void rtmpPushSuccess() {
        if (checkState()) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mStartBeginLiveTime;
            this.handler.removeCallbacks(this.mBeginLiveTimeoutRunnable);
            L.info(TAG, "rtmpPushSuccess -------------, time = " + jCurrentTimeMillis);
            int iCode = 0;
            if (jCurrentTimeMillis < OAIDHelper.TIMEOUT) {
                iCode = BeginLiveConstant.SUCCESS.code();
            } else if (jCurrentTimeMillis < 10000) {
                iCode = BeginLiveConstant.SUCCESS_TEN_S.code();
            } else if (jCurrentTimeMillis < 60000) {
                iCode = BeginLiveConstant.SUCCESS_MINUTE.code();
            }
            this.monitorCenter.reportBeginLive(iCode, 1, "");
            this.state = 8;
        }
    }

    void onHuyaConnect(boolean z) {
        if (checkState()) {
            this.state = 6;
        }
    }

    void onRtmpConnectSuccess() {
        if (checkState()) {
            this.state = 6;
        }
    }

    void onMayBeFirstVideoSendStart() {
        if (checkState() && this.state == 6 && !this.firstVideoSend) {
            L.info(TAG, "onFirstVideoSendStart");
        }
    }

    void onMayBeFirstVideoSendEnd(boolean z) {
        if (checkState() && this.state == 6 && !this.firstVideoSend) {
            L.info(TAG, "onFirstVideoSendEnd " + z);
            this.firstVideoSend = true;
        }
    }

    private boolean checkState() {
        int i;
        return (!this.started || (i = this.state) == -1 || i == 8) ? false : true;
    }

    private void report(BeginLiveConstant beginLiveConstant, int i, String str) {
        L.info(TAG, "report code = " + beginLiveConstant.code() + " secondCode = " + i + " msg = " + str);
        this.monitorCenter.reportBeginLive(beginLiveConstant.code(), i, str);
    }

    private void report(BeginLiveConstant beginLiveConstant, int i) {
        L.info(TAG, "report code = " + beginLiveConstant.code() + " secondCode = " + i + " msg = " + beginLiveConstant.msg());
        this.monitorCenter.reportBeginLive(beginLiveConstant.code(), i, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void report(BeginLiveConstant beginLiveConstant) {
        L.info(TAG, "report code = " + beginLiveConstant.code() + " msg = " + beginLiveConstant.msg());
        this.monitorCenter.reportBeginLive(beginLiveConstant.code(), 0, "");
    }
}
