package com.huya.statistics.cache;

import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ICacheManager {
    void cleanOldData();

    int getDbLastTaskId();

    Collection<TaskData> getDdCacheTasks(int i, long j);

    Collection<TaskData> getMemoryCacheTasks(int i);

    boolean removeMemTask(int i);

    boolean saveTask(TaskData taskData);

    boolean saveTaskList(List<TaskData> list);

    void setWriteDbEnable(boolean z);

    boolean updateDbTask(Object[] objArr, boolean z);
}
