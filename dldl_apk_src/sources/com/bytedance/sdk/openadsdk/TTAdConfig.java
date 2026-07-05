package com.bytedance.sdk.openadsdk;

import com.bytedance.sdk.openadsdk.CSJConfig;
import com.bytedance.sdk.openadsdk.live.ITTLiveTokenInjectionAuth;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class TTAdConfig extends CSJConfig {
    private ITTLiveTokenInjectionAuth a;

    private TTAdConfig(CSJConfig.a aVar) {
        super(aVar);
    }

    public ITTLiveTokenInjectionAuth getInjectionAuth() {
        return this.a;
    }

    public void setInjectionAuth(ITTLiveTokenInjectionAuth iTTLiveTokenInjectionAuth) {
        this.a = iTTLiveTokenInjectionAuth;
    }

    public static class Builder {
        private ITTLiveTokenInjectionAuth a;
        private CSJConfig.a b = new CSJConfig.a();

        public Builder injectionAuth(ITTLiveTokenInjectionAuth iTTLiveTokenInjectionAuth) {
            this.a = iTTLiveTokenInjectionAuth;
            return this;
        }

        public Builder appId(String str) {
            this.b.a(str);
            return this;
        }

        public Builder appName(String str) {
            this.b.b(str);
            return this;
        }

        public Builder paid(boolean z) {
            this.b.a(z);
            return this;
        }

        public Builder keywords(String str) {
            this.b.c(str);
            return this;
        }

        public Builder data(String str) {
            this.b.d(str);
            return this;
        }

        public Builder titleBarTheme(int i) {
            this.b.a(i);
            return this;
        }

        public Builder allowShowNotify(boolean z) {
            this.b.b(z);
            return this;
        }

        public Builder debug(boolean z) {
            this.b.c(z);
            return this;
        }

        public Builder directDownloadNetworkType(int... iArr) {
            this.b.a(iArr);
            return this;
        }

        public Builder supportMultiProcess(boolean z) {
            this.b.d(z);
            return this;
        }

        public Builder customController(TTCustomController tTCustomController) {
            this.b.a(tTCustomController);
            return this;
        }

        public Builder themeStatus(int i) {
            this.b.b(i);
            return this;
        }

        public Builder setPluginUpdateConfig(int i) {
            this.b.c(i);
            return this;
        }

        public Builder setAgeGroup(int i) {
            this.b.d(i);
            return this;
        }

        public Builder addExtra(String str, Object obj) {
            this.b.a(str, obj);
            return this;
        }

        public TTAdConfig build() {
            TTAdConfig tTAdConfig = new TTAdConfig(this.b);
            tTAdConfig.setInjectionAuth(this.a);
            return tTAdConfig;
        }
    }
}
