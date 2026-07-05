package com.huya.mtp.utils;

import com.huya.mtp.api.MTPApi;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SegmentLock {
    static final String TAG = "SegmentLock";
    private final int SEGMENT_NUM = 8;
    private final List<Lock> mLockList = new ArrayList();

    public SegmentLock() {
        for (int i = 0; i < 8; i++) {
            this.mLockList.add(new ReentrantLock());
        }
    }

    public void lock(String str) {
        Lock lock = this.mLockList.get(str.hashCode() & 7);
        MTPApi.LOGGER.debug(TAG, "lock key = %s, lock =%s", str, lock);
        lock.lock();
    }

    public void unlock(String str) {
        Lock lock = this.mLockList.get(str.hashCode() & 7);
        MTPApi.LOGGER.debug(TAG, "unlock key = %s, lock =%s", str, lock);
        lock.unlock();
    }
}
