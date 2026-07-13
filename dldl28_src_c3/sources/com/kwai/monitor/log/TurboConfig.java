package com.kwai.monitor.log;

import android.content.Context;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class TurboConfig {
    public String mAppId;
    public String mAppName;
    public String mChannel;
    public Context mContext;
    public boolean mEnableDebug;
    public OAIDProxy mOAIDProxy;

    public static class TurboConfigBuilder {
        public String mAppChannel;
        public String mAppId;
        public String mAppName;
        public final Context mContext;
        public boolean mIsDebug = false;
        public OAIDProxy mOAIDProxy;

        public TurboConfigBuilder(Context context) {
            this.mContext = context;
        }

        public static TurboConfigBuilder create(Context context) {
            return new TurboConfigBuilder(context);
        }

        public TurboConfig build() {
            TurboConfig turboConfig = new TurboConfig();
            turboConfig.mContext = this.mContext;
            turboConfig.mAppId = this.mAppId;
            turboConfig.mAppName = this.mAppName;
            turboConfig.mChannel = this.mAppChannel;
            turboConfig.mEnableDebug = this.mIsDebug;
            turboConfig.mOAIDProxy = this.mOAIDProxy;
            return turboConfig;
        }

        public TurboConfigBuilder setAppChannel(String str) {
            this.mAppChannel = str;
            return this;
        }

        public TurboConfigBuilder setAppId(String str) {
            this.mAppId = str;
            return this;
        }

        public TurboConfigBuilder setAppName(String str) {
            this.mAppName = str;
            return this;
        }

        public TurboConfigBuilder setEnableDebug(boolean z) {
            this.mIsDebug = z;
            return this;
        }

        public TurboConfigBuilder setOAIDProxy(OAIDProxy oAIDProxy) {
            this.mOAIDProxy = oAIDProxy;
            return this;
        }
    }
}
