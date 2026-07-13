package com.bytedance.framwork.core.sdklib;

import android.content.Context;
import com.bytedance.framwork.core.sdklib.config.MonitorConfigure;
import com.bytedance.framwork.core.sdklib.model.LocalLog;
import com.bytedance.framwork.core.sdklib.net.DefaultLogSendImpl;
import com.bytedance.framwork.core.sdklib.net.MonitorLogSender;
import com.bytedance.framwork.core.sdklib.thread.AsyncEventManager;
import com.bytedance.framwork.core.sdklib.util.JsonUtil;
import com.bytedance.framwork.core.sdklib.util.ListUtils;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class LogReportManager implements AsyncEventManager.IMonitorTimeTask {
    private static final String TAG = "LogReportManager";
    private String mAid;
    private Context mContext;
    private JSONObject mHeaderInfo;
    private LogStoreManager mLogStoreManager;
    private volatile boolean mCollectLogSwitch = true;
    private long mLastPackTime = 0;
    private int mInterval = 120;
    private int mReportCount = 100;

    public LogReportManager(Context context, String str) {
        this.mContext = context;
        this.mLogStoreManager = LogStoreManager.getInstance(context);
        this.mAid = str;
    }

    private int deleteUploadedLogs(long j) {
        return this.mLogStoreManager.deleteLogs(this.mAid, j);
    }

    private List<LocalLog> getLogSampled(int i) {
        return this.mLogStoreManager.getLogsLimit(Integer.parseInt(this.mAid), i);
    }

    private long getLogSampledCount() {
        return this.mLogStoreManager.getLogCount(this.mAid);
    }

    private boolean sendLog(JSONArray jSONArray, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (JsonUtil.isEmpty(jSONArray)) {
                return false;
            }
            jSONObject.put("data", jSONArray);
            JSONObject jSONObject2 = this.mHeaderInfo;
            if (jSONObject2 == null) {
                return true;
            }
            jSONObject.put("header", jSONObject2);
            return MonitorLogSender.send(this.mAid, jSONObject.toString());
        } catch (Throwable unused) {
            return false;
        }
    }

    private void setHeaderInfo(JSONObject jSONObject) {
        this.mHeaderInfo = jSONObject;
    }

    private void setReportCount(int i) {
        if (i <= 0) {
            return;
        }
        this.mReportCount = i;
    }

    private void setReportInterval(int i) {
        if (i <= 0) {
            return;
        }
        this.mInterval = i;
    }

    public void init() {
        AsyncEventManager.getInstance().addTimeTask(this);
        String str = this.mAid;
        MonitorLogSender.setImpl(str, new DefaultLogSendImpl(this.mContext, str));
    }

    @Override // com.bytedance.framwork.core.sdklib.thread.AsyncEventManager.IMonitorTimeTask
    public void onTimeEvent(long j) {
        packAndSendLog(false);
    }

    public void packAndSendLog(boolean z) {
        if (this.mCollectLogSwitch) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long logSampledCount = getLogSampledCount();
            if (logSampledCount <= 0) {
                return;
            }
            if (z || logSampledCount > this.mReportCount || (jCurrentTimeMillis - this.mLastPackTime) / 1000 > this.mInterval) {
                this.mLastPackTime = jCurrentTimeMillis;
                reportOneAidLog();
            }
        }
    }

    public void reportOneAidLog() {
        List<LocalLog> logSampled = getLogSampled(100);
        if (ListUtils.isEmpty(logSampled)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            LinkedList linkedList = new LinkedList();
            long j = -1;
            for (LocalLog localLog : logSampled) {
                long j2 = localLog.id;
                if (j2 > j) {
                    j = j2;
                }
                String str = localLog.data;
                linkedList.add(localLog);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    jSONObject.put("log_id", j2);
                    jSONObject.put("d_s_t", System.currentTimeMillis());
                    jSONArray.put(jSONObject);
                } catch (Throwable unused) {
                }
            }
            if (sendLog(jSONArray, false)) {
                deleteUploadedLogs(j);
            }
        } catch (Throwable unused2) {
        }
    }

    public void setCollectLogSwitch(boolean z) {
        this.mCollectLogSwitch = z;
    }

    public void updateConfig() {
        setReportInterval(MonitorConfigure.getReportInterval(this.mAid));
        setReportCount(MonitorConfigure.getReportCount(this.mAid));
        setHeaderInfo(MonitorConfigure.getReportJsonHeaderInfo(this.mAid));
    }
}
