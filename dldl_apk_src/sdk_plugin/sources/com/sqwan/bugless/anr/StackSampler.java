package com.sqwan.bugless.anr;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class StackSampler extends AbstractSampler {
    private static final int DEFAULT_MAX_ENTRY_COUNT = 100;
    private static final LinkedHashMap<Long, String> sStackMap = new LinkedHashMap<>();
    private Thread mCurrentThread;
    private int mMaxEntryCount;

    public StackSampler(Thread thread, long sampleIntervalMillis) {
        this(thread, 100, sampleIntervalMillis);
    }

    public StackSampler(Thread thread, int maxEntryCount, long sampleIntervalMillis) {
        super(sampleIntervalMillis);
        this.mMaxEntryCount = 100;
        this.mCurrentThread = thread;
        this.mMaxEntryCount = maxEntryCount;
    }

    public ArrayList<String> getThreadStackEntries(long startTime, long endTime) {
        return new ArrayList<>();
    }

    @Override // com.sqwan.bugless.anr.AbstractSampler
    protected void doSample() {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : this.mCurrentThread.getStackTrace()) {
            sb.append(stackTraceElement.toString());
            sb.append("\r\n");
        }
        synchronized (sStackMap) {
            if (sStackMap.size() == this.mMaxEntryCount && this.mMaxEntryCount > 0) {
                sStackMap.remove(sStackMap.keySet().iterator().next());
            }
            sStackMap.put(Long.valueOf(System.currentTimeMillis()), sb.toString());
        }
    }
}
