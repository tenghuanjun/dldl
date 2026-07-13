package com.bytedance.framwork.core.sdklib;

import android.content.Context;
import com.bytedance.framwork.core.sdklib.model.LocalLog;
import com.bytedance.framwork.core.sdklib.thread.AsyncEventManager;
import com.bytedance.framwork.core.sdklib.util.ListUtils;
import java.util.LinkedList;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MonitorManager implements AsyncEventManager.IMonitorTimeTask {
    private static final int MAX_QUEUE_SIZE = 2000;
    private static final String TAG = "MonitorManager";
    private String mAid;
    private LogStoreManager mLogStoreManager;
    private final LinkedList<LocalLog> mPendingQueue = new LinkedList<>();
    private final int WAIT_INSERT_DB_LOG_SIZE = 5;
    private long mLastInsertDBTime = 0;
    private final int WAIT_INSERT_DB_TIME = 120000;
    private boolean mReportLogSwitch = true;

    public MonitorManager(Context context, String str) {
        this.mLogStoreManager = LogStoreManager.getInstance(context);
        this.mAid = str;
    }

    protected void enqueue(LocalLog localLog) {
        if (this.mPendingQueue.size() >= 2000) {
            this.mPendingQueue.poll();
        }
        this.mPendingQueue.add(localLog);
    }

    public void init() {
        AsyncEventManager.getInstance().addTimeTask(this);
    }

    public void logSend(String str, String str2, JSONObject jSONObject) {
        if (!this.mReportLogSwitch || jSONObject == null) {
            return;
        }
        enqueue(new LocalLog(this.mAid, str, str2, jSONObject.toString(), System.currentTimeMillis()));
    }

    @Override // com.bytedance.framwork.core.sdklib.thread.AsyncEventManager.IMonitorTimeTask
    public void onTimeEvent(long j) {
        if (this.mReportLogSwitch) {
            processPendingQueue(j, false);
        }
    }

    public boolean processPendingQueue(long j, boolean z) {
        LinkedList linkedList;
        int size = this.mPendingQueue.size();
        if (size <= 0) {
            return false;
        }
        if (!z && size < 5 && j - this.mLastInsertDBTime <= 120000) {
            return false;
        }
        this.mLastInsertDBTime = j;
        synchronized (this.mPendingQueue) {
            linkedList = new LinkedList(this.mPendingQueue);
            this.mPendingQueue.clear();
        }
        if (ListUtils.isEmpty(linkedList)) {
            return true;
        }
        try {
            this.mLogStoreManager.insertLocalLogBatch(this.mAid, linkedList);
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    public void setReportLogSwitch(boolean z) {
        this.mReportLogSwitch = z;
    }
}
