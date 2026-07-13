package com.volcengine.androidcloud.common.log;

import com.volcengine.androidcloud.common.log.AcLog;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class AcLogStrategy {
    private boolean mDebug;
    private long mEndTime;
    private long mStartTime;
    private final Set<String> mTags = new HashSet();
    private AcLog.Level mLevel = AcLog.Level.DEBUG;
    private boolean mAllTags = false;

    public AcLogStrategy(boolean z, AcLog.Level level, long j, long j2, Set<String> set) {
        setDebug(z);
        setLevel(level);
        setRange(j, j2);
        setTags(set);
    }

    public boolean condition(AcLog.Level level, String str) {
        if (!this.mDebug || level.ordinal() < this.mLevel.ordinal()) {
            return false;
        }
        if (this.mStartTime > 0 && System.currentTimeMillis() < this.mStartTime) {
            return false;
        }
        if ((this.mEndTime > 0 && System.currentTimeMillis() > this.mEndTime) || str == null || str.length() == 0) {
            return false;
        }
        return this.mAllTags || this.mTags.contains(str);
    }

    public void setDebug(boolean z) {
        this.mDebug = z;
    }

    public void setLevel(AcLog.Level level) {
        this.mLevel = level;
    }

    public void setRange(long j, long j2) {
        this.mStartTime = j;
        this.mEndTime = j2;
    }

    public void setTags(Set<String> set) {
        this.mTags.clear();
        this.mTags.addAll(set);
        this.mAllTags = this.mTags.contains("all");
    }
}
