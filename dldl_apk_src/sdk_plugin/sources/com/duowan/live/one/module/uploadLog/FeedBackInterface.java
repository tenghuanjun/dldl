package com.duowan.live.one.module.uploadLog;

import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FeedBackInterface {

    public static class IsNeedUploadLog {
    }

    public static class AddFeedBack {
        public String mDetail;
        public String mFeedBackType;
        public String mSsid;
        public long startTime;

        public AddFeedBack(String str, String str2, String str3) {
            this.mFeedBackType = str;
            this.mDetail = str2;
            this.mSsid = str3;
        }

        public AddFeedBack(String str, String str2) {
            this.mFeedBackType = str;
            this.mDetail = str2;
        }

        public AddFeedBack(String str, String str2, String str3, long j) {
            this.mFeedBackType = str;
            this.mDetail = str2;
            this.mSsid = str3;
            this.startTime = j;
        }
    }

    public static class UploadLog {
        public String mFbId;
        public boolean mIsReload;
        public long mLogBeginTime;
        public long mLogEndTime;
        public long mMaxFileSize;

        public UploadLog(String str, long j, long j2, long j3, boolean z) {
            this.mFbId = str;
            this.mLogBeginTime = j;
            this.mLogEndTime = j2;
            this.mMaxFileSize = j3;
            this.mIsReload = z;
        }

        public UploadLog(String str, long j, long j2, long j3) {
            this(str, j, j2, j3, false);
        }
    }

    public static class AddDeviceDetails {
        public String mFbId;
        public String mIsReload;
        public long mLogBeginTime;
        public long mLogEndTime;
        public long mMaxFileSize;

        public AddDeviceDetails(String str, long j, long j2, long j3, String str2) {
            this.mFbId = str;
            this.mLogBeginTime = j;
            this.mLogEndTime = j2;
            this.mMaxFileSize = j3;
            this.mIsReload = str2;
        }
    }

    public static class UploadBeautyBitmap {
        public String mFbId;
        public List<String> mFilePaths;
        public long mMaxFileSize;

        public UploadBeautyBitmap(String str, long j, List<String> list) {
            this.mFbId = str;
            this.mMaxFileSize = j;
            this.mFilePaths = list;
        }
    }
}
