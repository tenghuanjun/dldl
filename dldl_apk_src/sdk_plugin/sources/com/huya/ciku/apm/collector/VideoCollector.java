package com.huya.ciku.apm.collector;

import android.os.SystemClock;
import com.duowan.monitor.jce.EUnit;
import com.duowan.monitor.utility.MonitorThread;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.ciku.apm.model.MediaSample;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VideoCollector extends CycleCollector {
    protected static final int DEFAULT_COLLECT_TIME = 2000;
    public static final String METRIC_ANIMATION_PROCESS_TIME = "animationpapertime";
    public static final String METRIC_AUDIO_EMPTY_PACKET = "audionullpackagerate";
    public static final String METRIC_AUDIO_FRAME_RATE = "samplerate";
    public static final String METRIC_BEAUTY_PROCESS_TIME = "beautytime";
    public static final String METRIC_BIG_EYE_PROCESS_TIME = "bigeyetime";
    public static final String METRIC_END_LIVE = "endlive";
    public static final String METRIC_INTERNET_LAG = "netcutdown";
    public static final String METRIC_LINK_MIC_SUCCESS_COUNT = "linkmicsuccesscount";
    public static final String METRIC_LINK_MIC_TOTAL_COUNT = "linkmictotalcount";
    public static final String METRIC_LIVE_INTERRUPT = "netshutdown";
    public static final String METRIC_OPEN_LIVE = "openlive";
    public static final String METRIC_PK_SUCCESS_COUNT = "pksuccesscount";
    public static final String METRIC_PK_TOTAL_COUNT = "pktotalcount";
    public static final String METRIC_VIDEO_EMPTY_PACKET = "videonullpackagerate";
    public static final String METRIC_VIDEO_FRAME_RATE = "framerate";
    public static final String METRIC_VIDEO_LINK_PROCESS_TIME = "linkmictime";
    private boolean enableCollectAudioFrame;
    private boolean enableCollectVideoFrame;
    private Object mLock;
    private MediaSample mReportSample;
    private MediaSample mSample;
    private long mStartTime;

    private long getResult(long j, long j2) {
        return j2;
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public void onStart() {
    }

    public VideoCollector() {
        super(2000L);
        this.mStartTime = 0L;
        this.mLock = new Object();
    }

    public void start() {
        if (this.mStopped) {
            this.mStopped = false;
            update();
        }
        initMediaSample();
        this.mStartTime = SystemClock.elapsedRealtime();
    }

    public void stop() {
        super.onStop();
        this.enableCollectAudioFrame = false;
        this.enableCollectVideoFrame = false;
        if (!this.mStopped) {
            this.mStopped = true;
            update();
        }
        MediaSample mediaSample = this.mSample;
        if (mediaSample != null) {
            mediaSample.reset();
            this.mSample = null;
        }
    }

    public void collectVideoFrameRate() {
        if (isReportEnable()) {
            this.enableCollectVideoFrame = true;
            this.mSample.increaseVideoFrameRate();
        }
    }

    public void collectAudioFrameRate() {
        initMediaSample();
        if (isReportEnable()) {
            this.enableCollectAudioFrame = true;
            this.mSample.increaseAudioFrameRate();
        }
    }

    public void tickAudioEmptyPacket() {
        if (isReportEnable()) {
            this.mSample.tickAudioEmptyPacket();
        }
    }

    public void tickVideoEmptyPacket() {
        if (isReportEnable()) {
            this.mSample.tickVideoEmptyPacket();
        }
    }

    private void initMediaSample() {
        if (this.mSample == null) {
            this.mSample = new MediaSample();
        }
        if (this.mReportSample == null) {
            this.mReportSample = new MediaSample();
        }
    }

    public void accumulateBeautyProcessTime(long j) {
        if (isReportEnable()) {
            this.mSample.beautyProcessTime += j;
        }
    }

    public void accumulateBigEyeProcessTime(long j) {
        if (isReportEnable()) {
            this.mSample.bigEyeProcessTime += j;
        }
    }

    public void accumulateAnimationProcessTime(long j) {
        if (isReportEnable()) {
            this.mSample.animationProcessTime += j;
        }
    }

    public void accumulateVideoLinkProcessTime(long j) {
        if (isReportEnable()) {
            this.mSample.videoLinkProcessTime += j;
        }
    }

    public void reportTotalStartLive(boolean z) {
        MonitorCenter.getInstance().request(METRIC_OPEN_LIVE, z ? 1.0d : 0.0d, EUnit.EUnit_Count);
    }

    public void reportTotalEndLive(boolean z) {
        MonitorThread.postDelayed(new Runnable() { // from class: com.huya.ciku.apm.collector.VideoCollector.1
            @Override // java.lang.Runnable
            public void run() {
                MonitorCenter.getInstance().request(VideoCollector.METRIC_END_LIVE, 1.0d, EUnit.EUnit_Count);
            }
        }, getInterval());
    }

    public void reportInternetLag(boolean z) {
        if (isReportEnable()) {
            this.mSample.internetLag = 1;
        }
    }

    public void reportLiveInterrupt(boolean z, String str) {
        if (isReportEnable()) {
            this.mSample.liveInterrupt = 1;
        }
    }

    public void reportPKTotalCount(boolean z) {
        if (isReportEnable()) {
            this.mSample.pkTotalCount = 1;
        }
    }

    public void reportPKSuccessTotalCount(boolean z) {
        if (isReportEnable()) {
            this.mSample.pkSuccessCount = 1;
        }
    }

    public void reportLinkMicTotalCount(boolean z) {
        if (isReportEnable()) {
            this.mSample.linkMicTotalCount = 1;
        }
    }

    public void reportLinkMicSuccessCount(boolean z) {
        if (isReportEnable()) {
            this.mSample.linkMicSuccessCount = 1;
        }
    }

    public boolean isLiving() {
        return !this.mStopped && isEnabled();
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector
    void doCollect() {
        synchronized (this.mLock) {
            if (this.mReportSample != null) {
                this.mReportSample.clone(this.mSample);
            }
            if (this.mSample != null) {
                this.mSample.reset();
            }
            reportSample(this.mReportSample);
            if (this.mReportSample != null) {
                this.mReportSample.reset();
            }
            this.mStartTime = SystemClock.elapsedRealtime();
        }
    }

    private void reportSample(MediaSample mediaSample) {
        if (!isEnabled() || mediaSample == null) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.mStartTime;
        if (this.enableCollectVideoFrame) {
            MonitorCenter.getInstance().request(METRIC_VIDEO_FRAME_RATE, getResult(jElapsedRealtime, mediaSample.getVideoFrameRate()), EUnit.EUnit_CountPerSecond);
            MonitorCenter.getInstance().request(METRIC_VIDEO_EMPTY_PACKET, mediaSample.getVideoFrameRate() > 0 ? 0.0d : 1.0d, EUnit.EUnit_Seconds);
        }
        if (this.enableCollectAudioFrame) {
            MonitorCenter.getInstance().request(METRIC_AUDIO_FRAME_RATE, getResult(jElapsedRealtime, mediaSample.getAudioFrameRate()), EUnit.EUnit_CountPerSecond);
            MonitorCenter.getInstance().request(METRIC_AUDIO_EMPTY_PACKET, mediaSample.getAudioFrameRate() <= 0 ? 1.0d : 0.0d, EUnit.EUnit_Seconds);
        }
        MonitorCenter.getInstance().request(METRIC_BEAUTY_PROCESS_TIME, getResult(jElapsedRealtime, mediaSample.getBeautyProcessTime()), EUnit.EUnit_Milliseconds);
        MonitorCenter.getInstance().request(METRIC_BIG_EYE_PROCESS_TIME, getResult(jElapsedRealtime, mediaSample.bigEyeProcessTime), EUnit.EUnit_Milliseconds);
        MonitorCenter.getInstance().request(METRIC_ANIMATION_PROCESS_TIME, getResult(jElapsedRealtime, mediaSample.animationProcessTime), EUnit.EUnit_Milliseconds);
        MonitorCenter.getInstance().request(METRIC_VIDEO_LINK_PROCESS_TIME, getResult(jElapsedRealtime, mediaSample.videoLinkProcessTime), EUnit.EUnit_Milliseconds);
        reportTotalStartLive(false);
        MonitorCenter.getInstance().request(METRIC_END_LIVE, mediaSample.totalEndLive, EUnit.EUnit_Count);
        MonitorCenter.getInstance().request(METRIC_INTERNET_LAG, mediaSample.internetLag, EUnit.EUnit_Count);
        MonitorCenter.getInstance().request(METRIC_LIVE_INTERRUPT, mediaSample.liveInterrupt, EUnit.EUnit_Count);
        MonitorCenter.getInstance().request(METRIC_PK_TOTAL_COUNT, mediaSample.pkTotalCount, EUnit.EUnit_Count);
        MonitorCenter.getInstance().request(METRIC_LINK_MIC_SUCCESS_COUNT, mediaSample.linkMicSuccessCount, EUnit.EUnit_Count);
        MonitorCenter.getInstance().request(METRIC_LINK_MIC_TOTAL_COUNT, mediaSample.linkMicTotalCount, EUnit.EUnit_Count);
        MonitorCenter.getInstance().request(METRIC_PK_SUCCESS_COUNT, mediaSample.pkSuccessCount, EUnit.EUnit_Count);
    }

    private boolean isReportEnable() {
        return isEnabled() && this.mSample != null;
    }
}
