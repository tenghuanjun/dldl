package com.huya.statistics.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.duowan.taf.jce.JceInputStream;
import com.huya.mtp.httputils.NetConfig;
import com.huya.mtp.httputils.NetworkUtil;
import com.huya.statistics.cache.ICacheManager;
import com.huya.statistics.cache.TaskCacheManager;
import com.huya.statistics.cache.TaskData;
import com.huya.statistics.jce.DataInfo;
import com.huya.statistics.jce.SDKReport;
import com.huya.statistics.log.SLog;
import com.huya.statistics.util.StatisticsThread;
import com.huya.statistics.util.Util;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TaskManager {
    private static final int DELAY_LIST_MAX_SIZE = 100;
    private static final int MAX_REPORT_COUNT = 5;
    private static final int MAX_REPORT_ITEM = 400;
    private static final long initTime = System.currentTimeMillis() - 1;
    private static volatile boolean isInDelayMode = false;
    private ICacheManager cacheManager;
    private Context context;
    private NetConfig mNetConfig;
    private String url;
    private DataInfo header = new DataInfo();
    private String TAG = TaskManager.class.getSimpleName();
    private long interval = OAIDHelper.TIMEOUT;
    private int failedCount = 0;
    private volatile boolean updateDbFailed = false;
    private ArrayList<TaskData> delayList = new ArrayList<>(100);
    private Runnable mWorkRunnable = new Runnable() { // from class: com.huya.statistics.core.TaskManager.1
        @Override // java.lang.Runnable
        public void run() {
            Collection<TaskData> memoryCacheTasks;
            if (NetworkUtil.isNetworkAvailable(TaskManager.this.context)) {
                int i = 0;
                while (true) {
                    if (i > 5 || (memoryCacheTasks = TaskManager.this.cacheManager.getMemoryCacheTasks(400)) == null || memoryCacheTasks.size() <= 0) {
                        break;
                    }
                    if (!TaskManager.this.doWork(memoryCacheTasks, false)) {
                        TaskManager.access$308(TaskManager.this);
                        break;
                    } else {
                        StatisticsContent.addRepTimes();
                        TaskManager.this.failedCount = 0;
                        i++;
                    }
                }
            } else {
                TaskManager.access$308(TaskManager.this);
            }
            StatisticsThread.executorWorkTask(TaskManager.this.mWorkRunnable, TaskManager.this.interval * ((long) (TaskManager.this.failedCount + 1)));
        }
    };
    private Runnable pauseReportRunnable = new Runnable() { // from class: com.huya.statistics.core.TaskManager.2
        @Override // java.lang.Runnable
        public void run() {
            boolean unused = TaskManager.isInDelayMode = false;
            TaskManager.this.clearDelayList();
        }
    };

    static /* synthetic */ int access$308(TaskManager taskManager) {
        int i = taskManager.failedCount;
        taskManager.failedCount = i + 1;
        return i;
    }

    public TaskManager(Context context, String str, boolean z) {
        this.cacheManager = new TaskCacheManager(context);
        this.context = context;
        this.url = str;
        StatisticsThread.executorWorkTask(this.mWorkRunnable, this.interval);
        handleHistoryData();
        initNetworkReceiver();
        this.mNetConfig = new NetConfig.Builder().setEncryption(z).setReadTimeOut(60000).setConcentLengthGzip(10240).build();
    }

    public void addTask(final StatisticsContent statisticsContent) {
        StatisticsThread.executorMakeTask(new Runnable() { // from class: com.huya.statistics.core.TaskManager.3
            @Override // java.lang.Runnable
            public void run() {
                TaskData taskData = new TaskData();
                DataInfo dataInfo = new DataInfo(statisticsContent.getRaw());
                taskData.setContent(dataInfo.toByteArray());
                taskData.setUuid(Util.uuid2Bytes(statisticsContent.getUUId()));
                taskData.setCreateTime(System.currentTimeMillis());
                taskData.setDataInfo(dataInfo);
                if (!TaskManager.isInDelayMode) {
                    TaskManager.this.cacheManager.saveTask(taskData);
                    return;
                }
                TaskManager.this.delayList.add(taskData);
                if (TaskManager.this.delayList.size() >= 100) {
                    TaskManager.this.clearDelayList();
                }
            }
        });
    }

    public void pauseReport(final long j) {
        SLog.debug(this.TAG, "pauseReport" + j, new Object[0]);
        StatisticsThread.executorMakeTask(new Runnable() { // from class: com.huya.statistics.core.TaskManager.4
            @Override // java.lang.Runnable
            public void run() {
                StatisticsThread.removeMakeTask(TaskManager.this.pauseReportRunnable);
                if (j > 0) {
                    boolean unused = TaskManager.isInDelayMode = true;
                    StatisticsThread.executorMakeTask(TaskManager.this.pauseReportRunnable, j);
                } else {
                    boolean unused2 = TaskManager.isInDelayMode = false;
                    TaskManager.this.clearDelayList();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDelayList() {
        if (this.delayList.size() == 0) {
            return;
        }
        this.cacheManager.saveTaskList(this.delayList);
        this.delayList.clear();
        SLog.debug(this.TAG, "clearDelayList", new Object[0]);
    }

    public void realTimeReport(boolean z) {
        if (z) {
            this.cacheManager.setWriteDbEnable(false);
            setInterval(1000L);
        } else if (this.interval == 1000) {
            this.cacheManager.setWriteDbEnable(true);
            setInterval(OAIDHelper.TIMEOUT);
        }
    }

    public DataInfo getHeader() {
        return this.header;
    }

    public void setHeader(DataInfo dataInfo) {
        this.header = dataInfo;
    }

    private void setInterval(long j) {
        this.interval = j;
        StatisticsThread.removeWorkTask(this.mWorkRunnable);
        StatisticsThread.executorWorkTask(this.mWorkRunnable, j);
    }

    private void handleHistoryData() {
        if (Util.isMainProcess(this.context)) {
            new Thread(new Runnable() { // from class: com.huya.statistics.core.TaskManager.5
                @Override // java.lang.Runnable
                public void run() {
                    Collection<TaskData> ddCacheTasks;
                    try {
                        Thread.sleep(2000L);
                        TaskManager.this.cacheManager.cleanOldData();
                        while (!TaskManager.this.updateDbFailed && (ddCacheTasks = TaskManager.this.cacheManager.getDdCacheTasks(400, TaskManager.initTime)) != null && ddCacheTasks.size() != 0) {
                            if (!TaskManager.this.doWork(ddCacheTasks, true)) {
                                Thread.sleep(OAIDHelper.TIMEOUT);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, this.TAG).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean doWork(Collection<TaskData> collection, boolean z) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (collection != null && collection.size() > 0) {
            Iterator<TaskData> it = collection.iterator();
            ArrayList<DataInfo> arrayList = new ArrayList<>();
            int size = collection.size();
            Object[] objArr = new Object[size];
            for (int i = 0; i < size; i++) {
                TaskData next = it.next();
                DataInfo dataInfo = next.getDataInfo();
                if (next.getDataInfo() == null) {
                    JceInputStream jceInputStream = new JceInputStream(next.getContent());
                    DataInfo dataInfo2 = new DataInfo();
                    dataInfo2.readFrom(jceInputStream);
                    dataInfo = dataInfo2;
                }
                arrayList.add(dataInfo);
                objArr[i] = next.getUuid();
            }
            SDKReport sDKReport = new SDKReport();
            sDKReport.setVBody(arrayList);
            sDKReport.setTHeader(this.header);
            if (NetworkUtil.post(this.url, sDKReport.toByteArray(), 1, this.mNetConfig) != null) {
                this.updateDbFailed = this.cacheManager.updateDbTask(objArr, true);
                if (!z) {
                    this.cacheManager.removeMemTask(size);
                }
                SLog.warn(this.TAG, "do work success", new Object[0]);
                SLog.info(this.TAG, "CostTime:" + (System.currentTimeMillis() - jCurrentTimeMillis) + " size" + arrayList.size(), new Object[0]);
                return true;
            }
            this.updateDbFailed = this.cacheManager.updateDbTask(objArr, false);
            SLog.warn(this.TAG, "do work failed", new Object[0]);
        }
        return false;
    }

    private void initNetworkReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.context.registerReceiver(new BroadcastReceiver() { // from class: com.huya.statistics.core.TaskManager.6
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (!NetworkUtil.isNetworkAvailable(TaskManager.this.context) || TaskManager.this.failedCount <= 0) {
                    return;
                }
                StatisticsThread.removeWorkTask(TaskManager.this.mWorkRunnable);
                TaskManager.this.failedCount = 0;
                StatisticsThread.executorWorkTask(TaskManager.this.mWorkRunnable, TaskManager.this.interval);
            }
        }, intentFilter);
    }
}
