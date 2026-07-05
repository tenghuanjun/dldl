package com.huya.statistics.cache;

import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IDiskCacheManager {
    void cleanOldData();

    int getLastTaskId();

    Collection<TaskData> getTaskList(int i, long j);

    void saveTask(TaskData taskData);

    void saveTaskList(List<TaskData> list);

    void setWriteDbEnable(boolean z);

    boolean updateTask(Object[] objArr, boolean z);
}
