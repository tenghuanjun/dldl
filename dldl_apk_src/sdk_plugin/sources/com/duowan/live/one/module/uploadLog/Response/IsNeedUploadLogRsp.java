package com.duowan.live.one.module.uploadLog.Response;

import com.duowan.auk.NoProguard;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class IsNeedUploadLogRsp implements NoProguard {
    private static final String NEED = "1";
    private FeedbackData[] feedback;
    private String isRequireLog;
    private long maxFileSize;
    private String serverTime;

    public static class FeedbackData implements NoProguard {
        String fbId;
        String fileType;
        String isRequireSupplementary;
        long logBeginTime;
        long logEndTime;
        String logNameList;

        public String getFbId() {
            return this.fbId;
        }

        public void setFbId(String str) {
            this.fbId = str;
        }

        public String getFileType() {
            return this.fileType;
        }

        public void setFileType(String str) {
            this.fileType = str;
        }

        public String getIsRequireSupplementary() {
            return this.isRequireSupplementary;
        }

        public boolean isRequireSupplementary() {
            return "1".equals(this.isRequireSupplementary);
        }

        public void setIsRequireSupplementary(String str) {
            this.isRequireSupplementary = str;
        }

        public long getLogBeginTime() {
            return this.logBeginTime;
        }

        public void setLogBeginTime(long j) {
            this.logBeginTime = j;
        }

        public long getLogEndTime() {
            return this.logEndTime;
        }

        public void setLogEndTime(long j) {
            this.logEndTime = j;
        }

        public String getLogNameList() {
            return this.logNameList;
        }

        public void setLogNameList(String str) {
            this.logNameList = str;
        }

        public String toString() {
            return "FeedbackData{fbId =" + this.fbId + ", isRequireSupplementary: " + this.isRequireSupplementary + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public FeedbackData[] getFeedback() {
        return this.feedback;
    }

    public void setFeedback(FeedbackData[] feedbackDataArr) {
        this.feedback = feedbackDataArr;
    }

    public String getIsRequireLog() {
        return this.isRequireLog;
    }

    public void setIsRequireLog(String str) {
        this.isRequireLog = str;
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

    public String toString() {
        return "LogUploadRangeRsp{feedbackDatas =" + this.feedback + ", isRequireLog: " + this.isRequireLog + AbstractJsonLexerKt.END_OBJ;
    }
}
