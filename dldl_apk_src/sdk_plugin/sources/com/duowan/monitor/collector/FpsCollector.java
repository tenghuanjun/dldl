package com.duowan.monitor.collector;

import android.os.Build;
import android.view.Choreographer;
import com.duowan.monitor.MonitorSDK;
import com.duowan.monitor.jce.EUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FpsCollector extends CycleCollector {
    private static final int DEFAULT_INTERVAL = 60000;
    private OnFpsListener mFpsListener;
    private FpsSimple mFpsSimple;

    private interface OnFpsListener {
        void onCompleted(double d);
    }

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnConfigListener
    public /* bridge */ /* synthetic */ void onConfig(JSONObject jSONObject) {
        super.onConfig(jSONObject);
    }

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public /* bridge */ /* synthetic */ void onStart() {
        super.onStart();
    }

    public FpsCollector() {
        super(60000L);
        this.mFpsListener = new OnFpsListener() { // from class: com.duowan.monitor.collector.FpsCollector.1
            @Override // com.duowan.monitor.collector.FpsCollector.OnFpsListener
            public void onCompleted(double d) {
                MonitorSDK.request(MonitorSDK.createMetric("performance", "fps", d, EUnit.EUnit_CountPerSecond));
            }
        };
        if (Build.VERSION.SDK_INT >= 16) {
            this.mFpsSimple = new FpsSimple(this.mFpsListener);
        }
    }

    @Override // com.duowan.monitor.collector.CycleCollector
    public void doCollect() {
        FpsSimple fpsSimple = this.mFpsSimple;
        if (fpsSimple != null) {
            fpsSimple.start();
        }
    }

    @Override // com.duowan.monitor.collector.CycleCollector, com.duowan.monitor.core.OnStatusChangeListener
    public void onStop() {
        super.onStop();
        FpsSimple fpsSimple = this.mFpsSimple;
        if (fpsSimple != null) {
            fpsSimple.stop();
        }
    }

    private static class FpsSimple {
        private List<Long> mDataSet;
        private OnFpsListener mListener;
        private volatile boolean mQuit = true;
        private long mStartSampleTimeInNs = 0;
        private float mDeviceRefreshRateInMs = 16.6f;
        private final long mSampleTimeInMs = 928;
        private Choreographer.FrameCallback mCallbackImp = new Choreographer.FrameCallback() { // from class: com.duowan.monitor.collector.FpsCollector.FpsSimple.1
            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                FpsSimple.this.doFrameImp(j);
            }
        };

        FpsSimple(OnFpsListener onFpsListener) {
            if (onFpsListener == null) {
                throw new NullPointerException("listener can't be null");
            }
            this.mListener = onFpsListener;
            this.mDataSet = new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void doFrameImp(long j) {
            if (this.mQuit) {
                clear();
                return;
            }
            if (this.mStartSampleTimeInNs == 0) {
                this.mStartSampleTimeInNs = j;
            }
            if (isFinishedWithSample(j)) {
                OnFpsListener onFpsListener = this.mListener;
                List<Long> list = this.mDataSet;
                onFpsListener.onCompleted(calculateMetric(list, getDroppedSet(list)));
                this.mDataSet.clear();
                this.mStartSampleTimeInNs = 0L;
                stop();
                return;
            }
            this.mDataSet.add(Long.valueOf(j));
            Choreographer.getInstance().postFrameCallback(this.mCallbackImp);
        }

        private boolean isFinishedWithSample(long j) {
            return j - this.mStartSampleTimeInNs > getSampleTimeInNs();
        }

        private void clear() {
            this.mDataSet.clear();
        }

        private long getSampleTimeInNs() {
            return TimeUnit.NANOSECONDS.convert(928L, TimeUnit.MILLISECONDS);
        }

        List<Integer> getDroppedSet(List<Long> list) {
            ArrayList arrayList = new ArrayList();
            long jLongValue = -1;
            for (Long l : list) {
                if (jLongValue == -1) {
                    jLongValue = l.longValue();
                } else {
                    int iDroppedCount = droppedCount(jLongValue, l.longValue(), this.mDeviceRefreshRateInMs);
                    if (iDroppedCount > 0) {
                        arrayList.add(Integer.valueOf(iDroppedCount));
                    }
                    jLongValue = l.longValue();
                }
            }
            return arrayList;
        }

        int droppedCount(long j, long j2, float f) {
            long jConvert = TimeUnit.MILLISECONDS.convert(j2 - j, TimeUnit.NANOSECONDS);
            long jRound = Math.round(f);
            if (jConvert > jRound) {
                return (int) (jConvert / jRound);
            }
            return 0;
        }

        private long calculateMetric(List<Long> list, List<Integer> list2) {
            int iIntValue = 0;
            long numberOfFramesInSet = getNumberOfFramesInSet(list.get(list.size() - 1).longValue() - list.get(0).longValue());
            Iterator<Integer> it = list2.iterator();
            while (it.hasNext()) {
                iIntValue += it.next().intValue();
            }
            if (numberOfFramesInSet == 0) {
                return 0L;
            }
            return Math.round((60 / numberOfFramesInSet) * (numberOfFramesInSet - ((long) iIntValue)));
        }

        private long getNumberOfFramesInSet(long j) {
            return Math.round(TimeUnit.MILLISECONDS.convert(j, TimeUnit.NANOSECONDS) / this.mDeviceRefreshRateInMs);
        }

        synchronized void start() {
            if (this.mQuit) {
                this.mQuit = false;
                Choreographer.getInstance().postFrameCallback(this.mCallbackImp);
            }
        }

        synchronized void stop() {
            if (!this.mQuit) {
                this.mQuit = true;
                Choreographer.getInstance().removeFrameCallback(this.mCallbackImp);
            }
        }
    }
}
