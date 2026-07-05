package com.duowan.live.one.module.uploadLog.Response;

import com.duowan.auk.NoProguard;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class AddFeedBackRsp implements NoProguard {
    private String description;
    private String fbId;
    private String isRequireLog;
    private long logBeginTime;
    private long logEndTime;
    private long maxFileSize;
    private String result;
    private String serverTime;
    private String status;

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public long getLogBeginTime() {
        return this.logBeginTime;
    }

    public void setLogBeginTime(long j) {
        this.logBeginTime = j;
    }

    public String getIsRequireLog() {
        return this.isRequireLog;
    }

    public void setIsRequireLog(String str) {
        this.isRequireLog = str;
    }

    public String getFbId() {
        return this.fbId;
    }

    public void setFbId(String str) {
        this.fbId = str;
    }

    public long getLogEndTime() {
        return this.logEndTime;
    }

    public void setLogEndTime(long j) {
        this.logEndTime = j;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getServerTime() {
        return this.serverTime;
    }

    public void setServerTime(String str) {
        this.serverTime = str;
    }

    public long getMaxFileSize() {
        return this.maxFileSize;
    }

    public void setMaxFileSize(long j) {
        this.maxFileSize = j;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        return "NewAddQuestionRsp{status=" + this.status + ", result='" + this.result + "', fbId=" + this.fbId + ", isRequireLog=" + this.isRequireLog + ", maxFileSize=" + this.maxFileSize + ", description=" + this.description + ", logBeginTime=" + this.logBeginTime + ", logEndTime=" + this.logEndTime + AbstractJsonLexerKt.END_OBJ;
    }
}
