package com.duowan.ark.thread;

import com.duowan.ark.thread.ParamBuild;
import com.duowan.ark.thread.pool.LogUtil;
import com.duowan.ark.thread.pool.Pools;
import com.duowan.ark.thread.pool.ScheduledExecutor;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ExecutorCenter {
    private static final String TAG = "ExecutorCenter";
    private static final String msDefaultKey = "ExecutorCenter_Default";
    private static ExecutorCenter msInstance;
    private Map<String, ScheduledExecutor> mExecutors;

    public static ExecutorCenter getInstance() {
        if (msInstance == null) {
            synchronized (ExecutorCenter.class) {
                if (msInstance == null) {
                    msInstance = new ExecutorCenter();
                }
            }
        }
        return msInstance;
    }

    private ExecutorCenter() {
        HashMap map = new HashMap();
        this.mExecutors = map;
        map.put(msDefaultKey, Pools.newScheduledThreadPoolExecutor(ParamBuild.CPU_COUNT + 1, Integer.MAX_VALUE, msDefaultKey));
    }

    public static void setLogger(LogUtil.Logger logger) {
        LogUtil.setLog(logger);
    }

    public void addExecutor(ParamBuild.Params params) {
        ScheduledExecutor scheduledExecutorNewScheduledThreadPoolExecutor;
        if (params == null) {
            throw new NullPointerException("ParamBuild.Params may not be null");
        }
        if (params.getType() == null) {
            LogUtil.info(TAG, "addExecutor null == type, use Default one");
            return;
        }
        if (this.mExecutors.containsKey(params.getType())) {
            LogUtil.info(TAG, "Executor has already added ！！ type = " + params.getType());
            return;
        }
        if (params.hasLooper()) {
            scheduledExecutorNewScheduledThreadPoolExecutor = Pools.newScheduledExecutor(params.getType(), params.getPriority(), params.getCount());
        } else {
            scheduledExecutorNewScheduledThreadPoolExecutor = Pools.newScheduledThreadPoolExecutor(params.getCount(), params.getMaxCount(), params.getType());
        }
        this.mExecutors.put(params.getType(), scheduledExecutorNewScheduledThreadPoolExecutor);
        LogUtil.info(TAG, "addExecutor success , type = " + params.getType());
    }

    public ScheduledExecutor getExecutor(String str) {
        return realGetExecutor(str);
    }

    public void post(PriorityRunnable priorityRunnable) {
        if (priorityRunnable == null) {
            LogUtil.info(TAG, "runnable null!!!!");
        } else {
            realGetExecutor(priorityRunnable.getKey()).execute(priorityRunnable);
        }
    }

    public void postDelay(PriorityRunnable priorityRunnable, long j) {
        if (priorityRunnable == null) {
            LogUtil.info(TAG, "runnable null!!!!");
        } else {
            realGetExecutor(priorityRunnable.getKey()).execute(priorityRunnable, j);
        }
    }

    private ScheduledExecutor realGetExecutor(String str) {
        ScheduledExecutor scheduledExecutor = str != null ? this.mExecutors.get(str) : null;
        return scheduledExecutor == null ? this.mExecutors.get(msDefaultKey) : scheduledExecutor;
    }
}
