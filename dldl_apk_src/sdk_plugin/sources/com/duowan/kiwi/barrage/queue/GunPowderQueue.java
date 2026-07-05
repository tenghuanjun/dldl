package com.duowan.kiwi.barrage.queue;

import com.duowan.ark.ArkUtils;
import com.duowan.kiwi.barrage.GunPowder;
import com.duowan.kiwi.barrage.config.BarrageConfig;
import com.duowan.kiwi.barrage.config.BarrageLog;
import com.duowan.kiwi.barrage.report.BarrageLost;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GunPowderQueue {
    private static final int DISCARD_SIZE = 10;
    public static final int KCACHE_NUMBER_PER_LINE = 20;
    public static final int KCLEAN = 5;
    public static final int KLIMIT = 400;
    public static final int KREMOVE = 20;
    public static final int KSINGLE_MAX = 20;
    public static final int KSINGLE_REMOVE = 10;
    private static final int MAX_LINES = 22;
    private static final String TAG = "[Barrage]queue";
    private int mQueueMaxNumb = 400;
    private int mDiscardNum = 0;
    private ReentrantLock mLock = new ReentrantLock();
    private long mPollCount = 1;
    private Deque<GunPowder> mShells = new LinkedList();
    private Deque<GunPowder> mSingleLineShells = new LinkedList();

    public void setLimited(int i) {
        if (i > 0) {
            this.mDiscardNum = (22 / i) - 1;
        }
        BarrageLog.info("[Barrage]queue", "maxLine: %d, mDiscardNum:%d", Integer.valueOf(i), Integer.valueOf(this.mDiscardNum));
        int i2 = i * 20;
        this.mQueueMaxNumb = i2;
        if (i2 > 400) {
            this.mQueueMaxNumb = 400;
        }
        BarrageLog.info("[Barrage]queue", "maxLine:%d, mQueueMaxNumb:%d ", Integer.valueOf(i), Integer.valueOf(this.mQueueMaxNumb));
    }

    public void offer(GunPowder gunPowder) {
        if (this.mLock.tryLock()) {
            if (gunPowder.mExplosive == 4) {
                offerSingle(gunPowder);
                this.mLock.unlock();
                return;
            }
            if (gunPowder.mNeedClean) {
                while (this.mShells.size() > 5) {
                    this.mShells.pollLast();
                }
            }
            if (this.mShells.size() >= this.mQueueMaxNumb) {
                BarrageLost.recordLostBarrage();
                int i = 0;
                for (int i2 = 0; i2 < this.mShells.size() && i < 20 && 2 > this.mShells.peekFirst().mExplosive; i2++) {
                    this.mShells.pollFirst();
                    i++;
                }
                if (this.mShells.size() >= this.mQueueMaxNumb) {
                    for (int i3 = 0; i3 < 20; i3++) {
                        this.mShells.pollFirst();
                    }
                }
            }
            if (2 <= gunPowder.mExplosive) {
                this.mShells.offerFirst(gunPowder);
            } else {
                this.mShells.offer(gunPowder);
            }
            this.mLock.unlock();
            return;
        }
        if (this.mLock.tryLock()) {
            if (gunPowder.mExplosive == 4) {
                offerSingle(gunPowder);
                this.mLock.unlock();
            } else {
                if (2 <= gunPowder.mExplosive) {
                    this.mShells.offerFirst(gunPowder);
                }
                this.mLock.unlock();
            }
        }
    }

    private void offerSingle(GunPowder gunPowder) {
        if (this.mSingleLineShells.size() > 20) {
            for (int i = 0; i < 10; i++) {
                this.mSingleLineShells.pollLast();
            }
        }
        this.mSingleLineShells.offer(gunPowder);
    }

    public GunPowder poll(int i) {
        if (i % 2 == 0) {
            if (this.mLock.tryLock()) {
                GunPowder gunPowderPoll = this.mSingleLineShells.poll();
                this.mLock.unlock();
                if (gunPowderPoll != null) {
                    return gunPowderPoll;
                }
            } else {
                BarrageLog.debug("[Barrage]queue", "tryLock failed, lineindex:%s", Integer.valueOf(i));
            }
        }
        return poll();
    }

    public GunPowder poll() {
        return poll(false);
    }

    public GunPowder poll(boolean z) {
        GunPowder gunPowderPollLast;
        GunPowder gunPowder = null;
        if (this.mLock.tryLock()) {
            try {
                if (this.mPollCount % 8 == 0 || z) {
                    gunPowderPollLast = this.mShells.pollLast();
                } else {
                    gunPowderPollLast = this.mShells.poll();
                }
                gunPowder = gunPowderPollLast;
            } catch (NoSuchElementException e) {
                int size = this.mShells.size();
                BarrageLog.error("ShellQueue", "[poll] throws NoSuchElementException, mLock=%d, mGunPowderQueue.size=%d, sizeBeforeCrash=%d", Integer.valueOf(this.mLock.getHoldCount()), Integer.valueOf(this.mShells.size()), Integer.valueOf(size));
                ArkUtils.crashIfDebug(e, "[poll] throws NoSuchElementException, mLock=%d, mGunPowderQueue.size=%d, sizeBeforeCrash=%d", Integer.valueOf(this.mLock.getHoldCount()), Integer.valueOf(this.mShells.size()), Integer.valueOf(size));
            }
            if (gunPowder != null) {
                if (this.mDiscardNum > 0 && this.mShells.size() > 10) {
                    for (int i = 0; i < this.mDiscardNum; i++) {
                        this.mShells.pollLast();
                    }
                }
                this.mPollCount++;
                BarrageLog.debug("[Barrage]queue", "nickName: %s, text: %s, size = %d", gunPowder.mNickName, gunPowder.mPowder, Integer.valueOf(this.mShells.size()));
            }
            this.mLock.unlock();
        }
        return gunPowder;
    }

    public float adjustShowDuration(float f) {
        int size = this.mShells.size() - BarrageConfig.ACTIVATE_ACC_SIZE;
        if (size <= 0) {
            return f;
        }
        if (size > BarrageConfig.ACC_DENOMINATOR) {
            size = BarrageConfig.ACC_DENOMINATOR;
        }
        return f * (1.0f - (BarrageConfig.ACCLERATE * (size / BarrageConfig.ACC_DENOMINATOR)));
    }

    public void clear() {
        for (int i = 0; i < 100; i++) {
            if (this.mLock.tryLock()) {
                this.mShells.clear();
                this.mSingleLineShells.clear();
                this.mLock.unlock();
                BarrageLog.debug("[Barrage]queue", "mGunPowderQueue.clear, get lock times : %d", Integer.valueOf(i));
                return;
            }
        }
        BarrageLog.error("[Barrage]queue", "clear error, has not got lock");
    }

    public int size() {
        if (!this.mLock.tryLock()) {
            return -1;
        }
        int size = this.mShells.size();
        this.mLock.unlock();
        return size;
    }
}
