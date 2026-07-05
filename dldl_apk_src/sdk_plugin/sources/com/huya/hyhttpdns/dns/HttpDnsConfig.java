package com.huya.hyhttpdns.dns;

import android.content.Context;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsConfig {
    final String appSrc;
    final HttpDnsReportListener httpDnsreportListener;
    final String mCacheDir;
    final Context mContext;
    final HttpDnsLog mHttpDnsLog;
    final HttpDnsUserInfo mHttpDnsUserInfo;
    final boolean mIsOverSea;
    final boolean mIsTestEnv;
    final String mUserSetHost;
    final String mUserSetTestUrl;
    final String ua;

    public HttpDnsConfig(Builder builder) {
        this.mContext = builder.context;
        this.mIsTestEnv = builder.mIsTestEnv;
        this.mIsOverSea = builder.mIsOverSea;
        this.mCacheDir = builder.mCacheDir;
        this.mHttpDnsUserInfo = builder.mHttpDnsUserInfo;
        this.mHttpDnsLog = builder.mHttpDnsLog;
        this.httpDnsreportListener = builder.httpDnsreportListener;
        this.ua = builder.ua;
        this.appSrc = builder.appSrc;
        this.mUserSetTestUrl = builder.userSetTestUrl;
        this.mUserSetHost = builder.mUserSetHost;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean isTestEnv() {
        return this.mIsTestEnv;
    }

    public boolean ismIsOverSea() {
        return this.mIsOverSea;
    }

    public String getCacheDir() {
        return this.mCacheDir;
    }

    public HttpDnsUserInfo getHyHttpDnsReqParam() {
        return this.mHttpDnsUserInfo;
    }

    public HttpDnsLog getHttpDnsLog() {
        return this.mHttpDnsLog;
    }

    public HttpDnsUserInfo getmHttpDnsUserInfo() {
        return this.mHttpDnsUserInfo;
    }

    public static class Builder {
        Context context;
        boolean mIsTestEnv = false;
        boolean mIsOverSea = false;
        String mCacheDir = null;
        HttpDnsUserInfo mHttpDnsUserInfo = null;
        String[] mBackupIps = null;
        Map mBackupDnsMap = null;
        HttpDnsLog mHttpDnsLog = null;
        HttpDnsReportListener httpDnsreportListener = null;
        String ua = "";
        String appSrc = "";
        String userSetTestUrl = null;
        String mUserSetHost = null;

        public Builder(Context context) {
            if (context == null) {
                throw new NullPointerException("context must be not null");
            }
            this.context = context;
        }

        public Builder setTestEnv(boolean z) {
            this.mIsTestEnv = z;
            return this;
        }

        public Builder isOverSea(boolean z) {
            this.mIsOverSea = z;
            return this;
        }

        @Deprecated
        public Builder setCacheDir(String str) {
            this.mCacheDir = str;
            return this;
        }

        public Builder setUserInfo(HttpDnsUserInfo httpDnsUserInfo) {
            this.mHttpDnsUserInfo = httpDnsUserInfo;
            return this;
        }

        public Builder setHttpDnsLog(HttpDnsLog httpDnsLog) {
            this.mHttpDnsLog = httpDnsLog;
            return this;
        }

        public Builder setReportListener(HttpDnsReportListener httpDnsReportListener) {
            this.httpDnsreportListener = httpDnsReportListener;
            return this;
        }

        public Builder setUa(String str) {
            this.ua = str;
            return this;
        }

        public Builder setAppSrc(String str) {
            this.appSrc = str;
            return this;
        }

        public Builder setTestUrl(String str) {
            this.userSetTestUrl = str;
            return this;
        }

        public Builder setCustomHost(String str) {
            this.mUserSetHost = str;
            return this;
        }

        public HttpDnsConfig build() {
            return new HttpDnsConfig(this);
        }
    }
}
