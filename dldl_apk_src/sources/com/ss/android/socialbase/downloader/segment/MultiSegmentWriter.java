package com.ss.android.socialbase.downloader.segment;

import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.thread.IDownloadRunnableCallback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
class MultiSegmentWriter {
    private static final boolean DEBUG = false;
    private static final int MIN_CACHE_BYTES = 65536;
    private static final int MIN_CACHE_TIME_MS = 100;
    private static final int MIN_SYNC_STEP_BYTE = 65536;
    private static final long MIN_SYNC_TIME_MS = 500;
    private static final String TAG = "MultiSegmentWriter";
    private final IDownloadRunnableCallback callback;
    private final DownloadInfo downloadInfo;
    private BaseException exception;
    private final boolean hasSyncStrategy;
    private final boolean isMonitorRw;
    private final IBufferPool pool;
    private final DownloadSetting setting;
    private final long syncIntervalMsBg;
    private final long syncIntervalMsFg;
    private long syncTimeNs;
    private final List<SegmentOutput> outputs = new LinkedList();
    private final List<SegmentOutput> doneOutputs = new ArrayList();
    private volatile boolean threadDirty = false;
    private volatile boolean paused = false;
    private volatile boolean canceled = false;
    private volatile long lastSyncBytes = 0;
    private volatile long lastSyncTimestamp = 0;
    private final IDownloadCache downloadCache = DownloadComponentManager.getDownloadCache();
    private final AppStatusManager appStatusManager = AppStatusManager.getInstance();

    private boolean isNeedSync(long j, long j2) {
        return j > 65536 && j2 > 500;
    }

    MultiSegmentWriter(DownloadInfo downloadInfo, IDownloadRunnableCallback iDownloadRunnableCallback, IBufferPool iBufferPool) {
        this.downloadInfo = downloadInfo;
        this.callback = iDownloadRunnableCallback;
        this.pool = iBufferPool;
        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(downloadInfo.getId());
        this.setting = downloadSettingObtain;
        boolean z = downloadSettingObtain.optInt(DownloadSettingKeys.SYNC_STRATEGY, 0) == 1;
        this.hasSyncStrategy = z;
        if (z) {
            long jOptInt = this.setting.optInt(DownloadSettingKeys.SYNC_INTERVAL_MS_FG, 5000);
            long jOptInt2 = this.setting.optInt(DownloadSettingKeys.SYNC_INTERVAL_MS_BG, 1000);
            this.syncIntervalMsFg = Math.max(jOptInt, 500L);
            this.syncIntervalMsBg = Math.max(jOptInt2, 500L);
        } else {
            this.syncIntervalMsFg = 0L;
            this.syncIntervalMsBg = 0L;
        }
        this.isMonitorRw = this.setting.optInt(DownloadSettingKeys.MONITOR_RW) == 1;
    }

    void assignOutput(SegmentOutput segmentOutput) {
        synchronized (this) {
            this.outputs.add(segmentOutput);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00b8, code lost:
    
        if (r13 <= 0) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00bb, code lost:
    
        r3.onProgress(r13);
     */
    /* JADX WARN: Removed duplicated region for block: B:129:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01f3 A[Catch: all -> 0x03d7, TryCatch #26 {all -> 0x03d7, blocks: (B:158:0x01ef, B:160:0x01f3, B:163:0x01f9, B:165:0x01ff, B:166:0x0202, B:167:0x0218, B:204:0x029c, B:205:0x029e, B:244:0x0325, B:246:0x032f, B:248:0x0333, B:285:0x03b5, B:287:0x03bb, B:288:0x03be, B:289:0x03d6), top: B:361:0x0027, inners: #27 }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01ff A[Catch: all -> 0x03d7, TryCatch #26 {all -> 0x03d7, blocks: (B:158:0x01ef, B:160:0x01f3, B:163:0x01f9, B:165:0x01ff, B:166:0x0202, B:167:0x0218, B:204:0x029c, B:205:0x029e, B:244:0x0325, B:246:0x032f, B:248:0x0333, B:285:0x03b5, B:287:0x03bb, B:288:0x03be, B:289:0x03d6), top: B:361:0x0027, inners: #27 }] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x032f A[Catch: all -> 0x03d7, TryCatch #26 {all -> 0x03d7, blocks: (B:158:0x01ef, B:160:0x01f3, B:163:0x01f9, B:165:0x01ff, B:166:0x0202, B:167:0x0218, B:204:0x029c, B:205:0x029e, B:244:0x0325, B:246:0x032f, B:248:0x0333, B:285:0x03b5, B:287:0x03bb, B:288:0x03be, B:289:0x03d6), top: B:361:0x0027, inners: #27 }] */
    /* JADX WARN: Removed duplicated region for block: B:287:0x03bb A[Catch: all -> 0x03d7, TryCatch #26 {all -> 0x03d7, blocks: (B:158:0x01ef, B:160:0x01f3, B:163:0x01f9, B:165:0x01ff, B:166:0x0202, B:167:0x0218, B:204:0x029c, B:205:0x029e, B:244:0x0325, B:246:0x032f, B:248:0x0333, B:285:0x03b5, B:287:0x03bb, B:288:0x03be, B:289:0x03d6), top: B:361:0x0027, inners: #27 }] */
    /* JADX WARN: Removed duplicated region for block: B:300:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0229 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:343:0x02ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:345:0x0362 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:347:0x00e3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:368:0x0415 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:372:0x03f5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0248 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:378:0x02ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void loopAndWrite(com.ss.android.socialbase.downloader.segment.IInput r31) throws com.ss.android.socialbase.downloader.exception.BaseException {
        /*
            Method dump skipped, instruction units count: 1131
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.downloader.segment.MultiSegmentWriter.loopAndWrite(com.ss.android.socialbase.downloader.segment.IInput):void");
    }

    private void checkAndSync(long j, boolean z) throws IOException {
        long j2 = j - this.lastSyncTimestamp;
        if (this.hasSyncStrategy) {
            if (j2 > (this.appStatusManager.isAppForeground() ? this.syncIntervalMsFg : this.syncIntervalMsBg)) {
                flushAndSync();
                this.lastSyncTimestamp = j;
                return;
            }
            return;
        }
        long curBytes = this.downloadInfo.getCurBytes() - this.lastSyncBytes;
        if (z || isNeedSync(curBytes, j2)) {
            flushAndSync();
            this.lastSyncTimestamp = j;
        }
    }

    public long getLastSyncBytes() {
        return this.lastSyncBytes;
    }

    private void outputDone(IOutput iOutput) {
        synchronized (this) {
            this.doneOutputs.add((SegmentOutput) iOutput);
        }
    }

    private void flushAndSync() throws IOException {
        boolean z = this.isMonitorRw;
        long jNanoTime = z ? System.nanoTime() : 0L;
        DownloadInfo downloadInfo = this.downloadInfo;
        IDownloadCache iDownloadCache = this.downloadCache;
        List<SegmentOutput> list = this.outputs;
        List<SegmentOutput> list2 = this.doneOutputs;
        Map<Long, Segment> segmentMap = iDownloadCache.getSegmentMap(downloadInfo.getId());
        if (segmentMap == null) {
            segmentMap = new HashMap<>(4);
        }
        boolean z2 = false;
        synchronized (this) {
            flush(list);
            try {
                sync(list);
                z2 = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
            updateSegmentToMap(list, segmentMap);
            if (list2.size() > 0) {
                close(list2);
                list.removeAll(list2);
                list2.clear();
            }
        }
        if (z2) {
            downloadInfo.updateRealDownloadTime(true);
            iDownloadCache.updateSegments(downloadInfo.getId(), segmentMap);
            iDownloadCache.updateDownloadInfo(downloadInfo);
            this.lastSyncBytes = downloadInfo.getCurBytes();
        }
        if (z) {
            this.syncTimeNs += System.nanoTime() - jNanoTime;
        }
    }

    private void flush(List<SegmentOutput> list) throws IOException {
        Iterator<SegmentOutput> it = list.iterator();
        while (it.hasNext()) {
            it.next().flush();
        }
    }

    private void sync(List<SegmentOutput> list) throws IOException {
        Iterator<SegmentOutput> it = list.iterator();
        while (it.hasNext()) {
            it.next().sync();
        }
    }

    private void close(List<SegmentOutput> list) {
        Iterator<SegmentOutput> it = list.iterator();
        while (it.hasNext()) {
            it.next().close();
        }
    }

    private void updateSegmentToMap(List<SegmentOutput> list, Map<Long, Segment> map) {
        Iterator<SegmentOutput> it = list.iterator();
        while (it.hasNext()) {
            Segment segment = it.next().getSegment();
            Segment segment2 = map.get(Long.valueOf(segment.getStartOffset()));
            if (segment2 == null) {
                map.put(Long.valueOf(segment.getStartOffset()), new Segment(segment));
            } else {
                segment2.setCurrentOffset(segment.getCurrentOffset());
                segment2.setEndOffset(segment.getEndOffset());
            }
        }
    }

    public void cancel() {
        this.canceled = true;
        this.threadDirty = true;
    }

    public void pause() {
        this.paused = true;
        this.threadDirty = true;
    }
}
