package com.volcengine.androidcloud.common.log;

import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.androidcloud.common.log.AcLogCache;

/* JADX INFO: loaded from: classes3.dex */
public class AcStubCacheTarget extends AcLogTarget implements AcLogCache.Callback {
    private static final int MAX_QUEUE_SIZE = 256;
    private final AcLogCache mLogCache;

    /* JADX INFO: renamed from: com.volcengine.androidcloud.common.log.AcStubCacheTarget$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$volcengine$androidcloud$common$log$AcLog$Level;

        static {
            int[] iArr = new int[AcLog.Level.values().length];
            $SwitchMap$com$volcengine$androidcloud$common$log$AcLog$Level = iArr;
            try {
                iArr[AcLog.Level.VERBOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$volcengine$androidcloud$common$log$AcLog$Level[AcLog.Level.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$volcengine$androidcloud$common$log$AcLog$Level[AcLog.Level.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$volcengine$androidcloud$common$log$AcLog$Level[AcLog.Level.WARN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$volcengine$androidcloud$common$log$AcLog$Level[AcLog.Level.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    protected AcStubCacheTarget(boolean z) {
        super(z);
        this.mLogCache = new AcLogCache(256, this);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void d(String str, String str2) {
        if (this.mActive) {
            if (this.mLogger == null || this.mLogCache.size() != 0) {
                this.mLogCache.onDebug(str, str2);
            } else {
                this.mLogger.onDebug(str, str2);
            }
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void e(String str, String str2) {
        if (this.mActive) {
            if (this.mLogger == null || this.mLogCache.size() != 0) {
                this.mLogCache.onError(str, str2);
            } else {
                this.mLogger.onError(str, str2);
            }
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void e(String str, String str2, Throwable th) {
        if (this.mActive) {
            if (this.mLogger == null || this.mLogCache.size() != 0) {
                this.mLogCache.onError(str, str2, th);
            } else {
                this.mLogger.onError(str, str2, th);
            }
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void f(String str, String str2) {
        if (this.mActive) {
            if (this.mLogger == null || this.mLogCache.size() != 0) {
                this.mLogCache.onVerbose(str, str2);
            } else {
                this.mLogger.onVerbose(str, str2);
            }
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void i(String str, String str2) {
        if (this.mActive) {
            if (this.mLogger == null || this.mLogCache.size() != 0) {
                this.mLogCache.onInfo(str, str2);
            } else {
                this.mLogger.onInfo(str, str2);
            }
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogCache.Callback
    public void onLog(AcLogInfo acLogInfo) {
        if (this.mLogger != null) {
            int i = AnonymousClass1.$SwitchMap$com$volcengine$androidcloud$common$log$AcLog$Level[acLogInfo.level().ordinal()];
            if (i == 1) {
                this.mLogger.onVerbose(acLogInfo.tag(), acLogInfo.message());
                return;
            }
            if (i == 2) {
                this.mLogger.onDebug(acLogInfo.tag(), acLogInfo.message());
                return;
            }
            if (i == 3) {
                this.mLogger.onInfo(acLogInfo.tag(), acLogInfo.message());
                return;
            }
            if (i == 4) {
                this.mLogger.onWarn(acLogInfo.tag(), acLogInfo.message());
            } else {
                if (i != 5) {
                    return;
                }
                if (acLogInfo.throwable() != null) {
                    this.mLogger.onError(acLogInfo.tag(), acLogInfo.message(), acLogInfo.throwable());
                } else {
                    this.mLogger.onError(acLogInfo.tag(), acLogInfo.message());
                }
            }
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void setLogger(AcLog.ILogger iLogger) {
        super.setLogger(iLogger);
        if (iLogger != null) {
            this.mLogCache.startLooper();
        } else {
            this.mLogCache.stopLooper();
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void v(String str, String str2) {
        if (this.mActive) {
            if (this.mLogger == null || this.mLogCache.size() != 0) {
                this.mLogCache.onVerbose(str, str2);
            } else {
                this.mLogger.onVerbose(str, str2);
            }
        }
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void w(String str, String str2) {
        if (this.mActive) {
            if (this.mLogger == null || this.mLogCache.size() != 0) {
                this.mLogCache.onWarn(str, str2);
            } else {
                this.mLogger.onWarn(str, str2);
            }
        }
    }
}
