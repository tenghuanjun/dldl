package com.huya.statistics.cache;

import android.content.Context;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TaskCacheManager implements ICacheManager {
    private IDiskCacheManager diskCacheManager;
    LinkedList<TaskData> memoryCache = new LinkedList<>();

    public TaskCacheManager(Context context) {
        this.diskCacheManager = new TaskDataDBManager(context);
    }

    @Override // com.huya.statistics.cache.ICacheManager
    public boolean saveTask(TaskData taskData) {
        this.diskCacheManager.saveTask(taskData);
        synchronized (this.memoryCache) {
            this.memoryCache.add(taskData);
        }
        return true;
    }

    @Override // com.huya.statistics.cache.ICacheManager
    public boolean saveTaskList(List<TaskData> list) {
        this.diskCacheManager.saveTaskList(list);
        synchronized (this.memoryCache) {
            this.memoryCache.addAll(list);
        }
        return true;
    }

    @Override // com.huya.statistics.cache.ICacheManager
    public Collection<TaskData> getMemoryCacheTasks(int i) {
        LinkedList linkedList;
        synchronized (this.memoryCache) {
            if (i > this.memoryCache.size()) {
                i = this.memoryCache.size();
            }
            linkedList = new LinkedList(this.memoryCache.subList(0, i));
        }
        return linkedList;
    }

    @Override // com.huya.statistics.cache.ICacheManager
    public Collection<TaskData> getDdCacheTasks(int i, long j) {
        return this.diskCacheManager.getTaskList(i, j);
    }

    @Override // com.huya.statistics.cache.ICacheManager
    public void cleanOldData() {
        this.diskCacheManager.cleanOldData();
    }

    @Override // com.huya.statistics.cache.ICacheManager
    public boolean removeMemTask(int i) {
        synchronized (this.memoryCache) {
            if (i > this.memoryCache.size()) {
                i = this.memoryCache.size();
            }
            this.memoryCache.subList(0, i).clear();
        }
        return true;
    }

    @Override // com.huya.statistics.cache.ICacheManager
    public boolean updateDbTask(Object[] objArr, boolean z) {
        return this.diskCacheManager.updateTask(objArr, z);
    }

    @Override // com.huya.statistics.cache.ICacheManager
    public int getDbLastTaskId() {
        return this.diskCacheManager.getLastTaskId();
    }

    @Override // com.huya.statistics.cache.ICacheManager
    public void setWriteDbEnable(boolean z) {
        this.diskCacheManager.setWriteDbEnable(z);
    }
}
