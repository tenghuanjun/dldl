package com.huya.ciku.apm.collector;

import com.duowan.auk.util.L;
import com.duowan.monitor.jce.EUnit;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.ciku.apm.provider.IAudioStatisticsProvider;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AudioCollector extends CycleCollector {
    private static final String METRIC_AUDIO_KIT_LONG_TIME_COUNT = "audio.audiokitlongtimecount";
    private static final String METRIC_AUDIO_KIT_TIME = "audio.audiokittime";
    private static final String METRIC_AUDIO_LONG_TIME_COUNT = "audio.longtimecount";
    private static final String METRIC_AUDIO_TOTAL_TIME = "audio.totaltime";
    private static final String METRIC_AUIDO_CAPTURE = "audio.capture";
    private WeakReference<IAudioStatisticsProvider> mAudioStatisticsProvider;

    @Override // com.huya.ciku.apm.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public void onStart() {
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public void onStop() {
    }

    public AudioCollector() {
        super(3000L);
        this.mAudioStatisticsProvider = new WeakReference<>(null);
        L.info(this, "new AudioCollector ");
        setEnabled(true);
    }

    public void start() {
        if (this.mStopped) {
            this.mStopped = false;
            update();
            L.info(this, "start");
        }
    }

    public void stop() {
        if (this.mStopped) {
            return;
        }
        this.mStopped = true;
        update();
        L.info(this, "stop");
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector
    void doCollect() {
        int audioTotalTime;
        int audioKitTime;
        int audioLongTimeCount;
        int audioKitLongTimeCount;
        WeakReference<IAudioStatisticsProvider> weakReference = this.mAudioStatisticsProvider;
        int audioFrameRate = 0;
        if (weakReference == null || weakReference.get() == null) {
            audioTotalTime = 0;
            audioKitTime = 0;
            audioLongTimeCount = 0;
            audioKitLongTimeCount = 0;
        } else {
            audioFrameRate = this.mAudioStatisticsProvider.get().getAudioFrameRate();
            audioTotalTime = this.mAudioStatisticsProvider.get().getAudioTotalTime();
            audioKitTime = this.mAudioStatisticsProvider.get().getAudioKitTime();
            audioLongTimeCount = this.mAudioStatisticsProvider.get().getAudioLongTimeCount();
            audioKitLongTimeCount = this.mAudioStatisticsProvider.get().getAudioKitLongTimeCount();
        }
        MonitorCenter.getInstance().request(METRIC_AUIDO_CAPTURE, audioFrameRate, EUnit.EUnit_CountPerSecond);
        MonitorCenter.getInstance().request(METRIC_AUDIO_TOTAL_TIME, audioTotalTime, EUnit.EUnit_Milliseconds);
        MonitorCenter.getInstance().request(METRIC_AUDIO_KIT_TIME, audioKitTime, EUnit.EUnit_Milliseconds);
        MonitorCenter.getInstance().request(METRIC_AUDIO_LONG_TIME_COUNT, audioLongTimeCount, EUnit.EUnit_Count);
        MonitorCenter.getInstance().request(METRIC_AUDIO_KIT_LONG_TIME_COUNT, audioKitLongTimeCount, EUnit.EUnit_Count);
    }

    public void setAudioStatisticsProvider(IAudioStatisticsProvider iAudioStatisticsProvider) {
        this.mAudioStatisticsProvider = new WeakReference<>(iAudioStatisticsProvider);
    }

    @Override // com.huya.ciku.apm.collector.CycleCollector, com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        super.onConfig(jSONObject);
        L.info("onConfig:" + jSONObject);
    }
}
