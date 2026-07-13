package com.volcengine.androidcloud.common.log;

/* JADX INFO: loaded from: classes3.dex */
public class AcStubTarget extends AcLogTarget {
    public AcStubTarget(boolean z) {
        super(z);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void d(String str, String str2) {
        if (!this.mActive || this.mLogger == null) {
            return;
        }
        this.mLogger.onDebug(str, str2);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void e(String str, String str2) {
        if (!this.mActive || this.mLogger == null) {
            return;
        }
        this.mLogger.onError(str, str2);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void e(String str, String str2, Throwable th) {
        if (!this.mActive || this.mLogger == null) {
            return;
        }
        this.mLogger.onError(str, str2, th);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void f(String str, String str2) {
        if (!this.mActive || this.mLogger == null) {
            return;
        }
        this.mLogger.onVerbose(str, str2);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void i(String str, String str2) {
        if (!this.mActive || this.mLogger == null) {
            return;
        }
        this.mLogger.onInfo(str, str2);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void v(String str, String str2) {
        if (!this.mActive || this.mLogger == null) {
            return;
        }
        this.mLogger.onVerbose(str, str2);
    }

    @Override // com.volcengine.androidcloud.common.log.AcLogTarget
    public void w(String str, String str2) {
        if (!this.mActive || this.mLogger == null) {
            return;
        }
        this.mLogger.onWarn(str, str2);
    }
}
