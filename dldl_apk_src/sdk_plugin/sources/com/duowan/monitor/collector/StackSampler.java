package com.duowan.monitor.collector;

import com.snail.antifake.deviceid.ShellAdbUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class StackSampler extends CycleCollector {
    private static final int DEFAULT_INTERVAL = 1000;
    private static final int DEFAULT_MAX_ENTRY_COUNT = 10;
    private static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US);
    private Thread mCurrentThread;
    private final LinkedHashMap<Long, String> sStackMap;

    StackSampler(Thread thread) {
        super(1000L);
        this.sStackMap = new LinkedHashMap<>();
        this.mCurrentThread = thread;
    }

    ArrayList<String> getThreadStackEntries(long j, long j2) {
        ArrayList<String> arrayList = new ArrayList<>();
        synchronized (this.sStackMap) {
            for (Long l : this.sStackMap.keySet()) {
                if (j < l.longValue() && l.longValue() < j2) {
                    arrayList.add(TIME_FORMATTER.format(l) + "\n\n" + this.sStackMap.get(l));
                }
            }
        }
        return arrayList;
    }

    @Override // com.duowan.monitor.collector.CycleCollector
    public void doCollect() {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : this.mCurrentThread.getStackTrace()) {
            sb.append(stackTraceElement.toString());
            sb.append(ShellAdbUtils.COMMAND_LINE_END);
        }
        synchronized (this.sStackMap) {
            if (this.sStackMap.size() == 10) {
                this.sStackMap.remove(this.sStackMap.keySet().iterator().next());
            }
            this.sStackMap.put(Long.valueOf(System.currentTimeMillis()), sb.toString());
        }
    }
}
