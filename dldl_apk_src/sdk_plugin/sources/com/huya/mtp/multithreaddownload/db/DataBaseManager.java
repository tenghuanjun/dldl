package com.huya.mtp.multithreaddownload.db;

import android.content.Context;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DataBaseManager {
    private static DataBaseManager sDataBaseManager;
    private final ThreadInfoDao mThreadInfoDao;

    public static DataBaseManager getInstance(Context context) {
        if (sDataBaseManager == null) {
            sDataBaseManager = new DataBaseManager(context);
        }
        return sDataBaseManager;
    }

    private DataBaseManager(Context context) {
        this.mThreadInfoDao = new ThreadInfoDao(context);
    }

    public synchronized void insert(ThreadInfo threadInfo) {
        this.mThreadInfoDao.insert(threadInfo);
    }

    public synchronized void delete(String str) {
        this.mThreadInfoDao.delete(str);
    }

    public synchronized void update(String str, int i, long j) {
        this.mThreadInfoDao.update(str, i, j);
    }

    public synchronized List<ThreadInfo> getThreadInfos(String str) {
        return this.mThreadInfoDao.getThreadInfos(str);
    }

    public synchronized boolean existsThread(String str, int i) {
        return this.mThreadInfoDao.existsThread(str, i);
    }

    public synchronized boolean existsTask(String str) {
        return this.mThreadInfoDao.existsTask(str);
    }
}
