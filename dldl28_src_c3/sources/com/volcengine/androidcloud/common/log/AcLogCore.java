package com.volcengine.androidcloud.common.log;

import com.volcengine.androidcloud.common.log.AcLog;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class AcLogCore {
    private static final boolean DEFAULT_DEBUG = true;
    private static final long DEFAULT_END_TIME = -1;
    private static final boolean DEFAULT_FORMAT = false;
    private static final long DEFAULT_START_TIME = -1;
    private static final int DEFAULT_TARGETS = 2;
    private final Boolean[] mDebug;
    private final AcLogOutput mOutput;
    private final AcLogStrategy mStrategy;
    private static final AcLog.Level DEFAULT_LEVEL = AcLog.Level.VERBOSE;
    private static final Set<String> DEFAULT_TAGS = Collections.singleton("all");

    public AcLogCore() {
        Boolean[] boolArr = new Boolean[AcLogConfigSource.values().length];
        this.mDebug = boolArr;
        boolArr[AcLogConfigSource.CompileConfig.ordinal()] = Boolean.TRUE;
        AcLogStrategy acLogStrategy = new AcLogStrategy(getDebug(), DEFAULT_LEVEL, -1L, -1L, DEFAULT_TAGS);
        this.mStrategy = acLogStrategy;
        AcLogOutput acLogOutput = new AcLogOutput(acLogStrategy, 2, false);
        this.mOutput = acLogOutput;
        acLogOutput.setLogger(AcTargetType.LoggingTarget, getLoggingTarget());
    }

    private boolean getDebug() {
        boolean zBooleanValue = true;
        int i = 0;
        while (true) {
            try {
                Boolean[] boolArr = this.mDebug;
                if (i >= boolArr.length) {
                    break;
                }
                Boolean bool = boolArr[i];
                if (bool != null) {
                    zBooleanValue = bool.booleanValue();
                }
                i++;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return zBooleanValue;
    }

    private AcLog.ILogger getLoggingTarget() {
        AcLog.ILogger iLogger;
        try {
            if ("Dalvik".equals(System.getProperties().getProperty("java.vm.name"))) {
                iLogger = (AcLog.ILogger) AcLogcatLogger.class.newInstance();
            }
            return iLogger == null ? new AcLoggingLogger() : iLogger;
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                return new AcLoggingLogger();
            } finally {
                new AcLoggingLogger();
            }
        }
    }

    public AcLogOutput getOutput() {
        return this.mOutput;
    }

    public void setDebug(AcLogConfigSource acLogConfigSource, boolean z) {
        this.mDebug[acLogConfigSource.ordinal()] = Boolean.valueOf(z);
        this.mStrategy.setDebug(getDebug());
    }

    public void setFormat(boolean z) {
        this.mOutput.setFormat(z);
    }

    public void setLevel(AcLog.Level level) {
        this.mStrategy.setLevel(level);
    }

    public void setLogger(AcTargetType acTargetType, AcLog.ILogger iLogger) {
        this.mOutput.setLogger(acTargetType, iLogger);
    }

    public void setRange(long j, long j2) {
        this.mStrategy.setRange(j, j2);
    }

    public void setReplace(String str) {
        this.mOutput.setReplace(str);
    }

    public void setTags(Set<String> set) {
        this.mStrategy.setTags(set);
    }

    public void setTargets(int i) {
        this.mOutput.setTargets(i);
    }
}
