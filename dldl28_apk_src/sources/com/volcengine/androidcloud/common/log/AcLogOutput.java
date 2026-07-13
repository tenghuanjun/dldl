package com.volcengine.androidcloud.common.log;

import com.volcengine.androidcloud.common.log.AcLog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class AcLogOutput {
    private boolean mFormat;
    private final AcLogStrategy mStrategy;
    private int mTargets;
    private final HashMap<AcTargetType, AcLogTarget> mTargetsMap = new HashMap<>();
    private String mReplace = "";

    public AcLogOutput(AcLogStrategy acLogStrategy, int i, boolean z) {
        this.mStrategy = acLogStrategy;
        this.mTargets = i;
        this.mFormat = z;
        for (AcTargetType acTargetType : AcTargetType.values()) {
            this.mTargetsMap.put(acTargetType, AcTargetFactory.getTarget(acTargetType, this.mTargets));
        }
    }

    private String format(AcLog.Level level, String str, String str2) {
        return this.mFormat ? String.format(Locale.getDefault(), "[%s][PID:%d][%s][%s] %s", getCurrentTime(), Long.valueOf(Thread.currentThread().getId()), level.identify(), str, str2) : str2;
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date());
    }

    private boolean isNotBlank(String str) {
        return (str == null || str.length() == 0) ? false : true;
    }

    public void d(String str, String str2) {
        AcLogStrategy acLogStrategy = this.mStrategy;
        AcLog.Level level = AcLog.Level.DEBUG;
        if (acLogStrategy.condition(level, str)) {
            if (isNotBlank(this.mReplace)) {
                str2 = String.format("%s\t%s", str, str2);
            }
            if (isNotBlank(this.mReplace)) {
                str = this.mReplace;
            }
            String str3 = format(level, str, str2);
            if (str3 == null || str3.length() <= 0) {
                return;
            }
            for (AcTargetType acTargetType : AcTargetType.values()) {
                ((AcLogTarget) Objects.requireNonNull(this.mTargetsMap.get(acTargetType))).d(str, str3);
            }
        }
    }

    public void e(String str, String str2) {
        AcLogStrategy acLogStrategy = this.mStrategy;
        AcLog.Level level = AcLog.Level.ERROR;
        if (acLogStrategy.condition(level, str)) {
            if (isNotBlank(this.mReplace)) {
                str2 = String.format("%s\t%s", str, str2);
            }
            if (isNotBlank(this.mReplace)) {
                str = this.mReplace;
            }
            String str3 = format(level, str, str2);
            if (str3 == null || str3.length() <= 0) {
                return;
            }
            for (AcTargetType acTargetType : AcTargetType.values()) {
                ((AcLogTarget) Objects.requireNonNull(this.mTargetsMap.get(acTargetType))).e(str, str3);
            }
        }
    }

    public void e(String str, String str2, Throwable th) {
        AcLogStrategy acLogStrategy = this.mStrategy;
        AcLog.Level level = AcLog.Level.ERROR;
        if (acLogStrategy.condition(level, str)) {
            int i = 0;
            if (isNotBlank(this.mReplace)) {
                str2 = String.format("%s\t%s", str, str2);
            }
            if (isNotBlank(this.mReplace)) {
                str = this.mReplace;
            }
            String str3 = format(level, str, str2);
            if (str3 == null || str3.length() <= 0) {
                return;
            }
            if (th != null) {
                AcTargetType[] acTargetTypeArrValues = AcTargetType.values();
                int length = acTargetTypeArrValues.length;
                while (i < length) {
                    ((AcLogTarget) Objects.requireNonNull(this.mTargetsMap.get(acTargetTypeArrValues[i]))).e(str, str3, th);
                    i++;
                }
                return;
            }
            AcTargetType[] acTargetTypeArrValues2 = AcTargetType.values();
            int length2 = acTargetTypeArrValues2.length;
            while (i < length2) {
                ((AcLogTarget) Objects.requireNonNull(this.mTargetsMap.get(acTargetTypeArrValues2[i]))).e(str, str3);
                i++;
            }
        }
    }

    public void f(String str, String str2) {
        AcLogStrategy acLogStrategy = this.mStrategy;
        AcLog.Level level = AcLog.Level.FREQUENCY;
        if (acLogStrategy.condition(level, str)) {
            if (isNotBlank(this.mReplace)) {
                str2 = String.format("%s\t%s", str, str2);
            }
            if (isNotBlank(this.mReplace)) {
                str = this.mReplace;
            }
            String str3 = format(level, str, str2);
            if (str3 == null || str3.length() <= 0) {
                return;
            }
            for (AcTargetType acTargetType : AcTargetType.values()) {
                ((AcLogTarget) Objects.requireNonNull(this.mTargetsMap.get(acTargetType))).f(str, str3);
            }
        }
    }

    public void i(String str, String str2) {
        AcLogStrategy acLogStrategy = this.mStrategy;
        AcLog.Level level = AcLog.Level.INFO;
        if (acLogStrategy.condition(level, str)) {
            if (isNotBlank(this.mReplace)) {
                str2 = String.format("%s\t%s", str, str2);
            }
            if (isNotBlank(this.mReplace)) {
                str = this.mReplace;
            }
            String str3 = format(level, str, str2);
            if (str3 == null || str3.length() <= 0) {
                return;
            }
            for (AcTargetType acTargetType : AcTargetType.values()) {
                ((AcLogTarget) Objects.requireNonNull(this.mTargetsMap.get(acTargetType))).i(str, str3);
            }
        }
    }

    public void setFormat(boolean z) {
        this.mFormat = z;
    }

    public void setLogger(AcTargetType acTargetType, AcLog.ILogger iLogger) {
        int iIntValue;
        ((AcLogTarget) Objects.requireNonNull(this.mTargetsMap.get(acTargetType))).setLogger(iLogger);
        if (iLogger != null) {
            iIntValue = acTargetType.getValue().intValue() | this.mTargets;
        } else {
            iIntValue = (~acTargetType.getValue().intValue()) & this.mTargets;
        }
        setTargets(iIntValue);
    }

    public void setReplace(String str) {
        this.mReplace = str;
    }

    public void setTargets(int i) {
        this.mTargets = i;
        for (AcTargetType acTargetType : AcTargetType.values()) {
            ((AcLogTarget) Objects.requireNonNull(this.mTargetsMap.get(acTargetType))).setActive(acTargetType.isActive(i));
        }
    }

    public void v(String str, String str2) {
        AcLogStrategy acLogStrategy = this.mStrategy;
        AcLog.Level level = AcLog.Level.VERBOSE;
        if (acLogStrategy.condition(level, str)) {
            if (isNotBlank(this.mReplace)) {
                str2 = String.format("%s\t%s", str, str2);
            }
            if (isNotBlank(this.mReplace)) {
                str = this.mReplace;
            }
            String str3 = format(level, str, str2);
            if (str3 == null || str3.length() <= 0) {
                return;
            }
            for (AcTargetType acTargetType : AcTargetType.values()) {
                ((AcLogTarget) Objects.requireNonNull(this.mTargetsMap.get(acTargetType))).v(str, str3);
            }
        }
    }

    public void w(String str, String str2) {
        AcLogStrategy acLogStrategy = this.mStrategy;
        AcLog.Level level = AcLog.Level.WARN;
        if (acLogStrategy.condition(level, str)) {
            if (isNotBlank(this.mReplace)) {
                str2 = String.format("%s\t%s", str, str2);
            }
            if (isNotBlank(this.mReplace)) {
                str = this.mReplace;
            }
            String str3 = format(level, str, str2);
            if (str3 == null || str3.length() <= 0) {
                return;
            }
            for (AcTargetType acTargetType : AcTargetType.values()) {
                ((AcLogTarget) Objects.requireNonNull(this.mTargetsMap.get(acTargetType))).w(str, str3);
            }
        }
    }
}
