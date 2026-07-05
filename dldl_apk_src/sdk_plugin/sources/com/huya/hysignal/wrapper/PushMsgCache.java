package com.huya.hysignal.wrapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class PushMsgCache {
    private int currentIndex = 0;
    private int mMaxListSize;
    private long mMaxSetSize;
    private List<HashSet<Long>> mMsgIdList;

    PushMsgCache(int i, long j) {
        this.mMsgIdList = new ArrayList(this.mMaxListSize);
        this.mMaxListSize = i;
        this.mMaxSetSize = j / 3;
        for (int i2 = 0; i2 < this.mMaxListSize; i2++) {
            this.mMsgIdList.add(new HashSet<>());
        }
    }

    synchronized void updateMaxCacheCount(long j) {
        this.mMaxSetSize = j;
    }

    synchronized boolean cacheIfNeed(long j) {
        if (j == 0) {
            return true;
        }
        boolean z = false;
        for (int i = 0; i < this.mMaxListSize; i++) {
            if (this.mMsgIdList.get((this.currentIndex + i) % this.mMaxListSize).contains(Long.valueOf(j))) {
                return false;
            }
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.mMaxListSize) {
                break;
            }
            HashSet<Long> hashSet = this.mMsgIdList.get((this.currentIndex + i2) % this.mMaxListSize);
            if (hashSet.size() <= this.mMaxSetSize) {
                hashSet.add(Long.valueOf(j));
                this.currentIndex = (this.currentIndex + i2) % this.mMaxListSize;
                z = true;
                break;
            }
            i2++;
        }
        if (!z) {
            int i3 = (this.currentIndex + 1) % this.mMaxListSize;
            this.currentIndex = i3;
            HashSet<Long> hashSet2 = this.mMsgIdList.get(i3);
            hashSet2.clear();
            hashSet2.add(Long.valueOf(j));
        }
        return true;
    }
}
