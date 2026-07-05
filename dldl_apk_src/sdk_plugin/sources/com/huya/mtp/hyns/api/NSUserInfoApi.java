package com.huya.mtp.hyns.api;

import com.huya.mtp.hyns.NSApi;
import com.huya.mtp.hyns.protocol.NSUserInfoProtocol;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@NSApi(NSUserInfoProtocol.class)
public interface NSUserInfoApi {
    void updateAppSrc(String str);

    void updateDynamicConfig(Map<String, String> map);

    void updateIsLogin(boolean z);

    void updateToken(String str);

    void updateTokenType(int i);

    void updateUid(long j);

    @Deprecated
    void updateUserInfo(NSUserInfo nSUserInfo);

    public static class NSUserInfo {
        Boolean isLogin;
        String token;
        int tokenType;
        long uid;

        public Boolean getLogin() {
            return this.isLogin;
        }

        public NSUserInfo setLogin(Boolean bool) {
            this.isLogin = bool;
            return this;
        }

        public NSUserInfo setUid(long j) {
            this.uid = j;
            return this;
        }

        public NSUserInfo setToken(String str) {
            this.token = str;
            return this;
        }

        public NSUserInfo setTokenType(int i) {
            this.tokenType = i;
            return this;
        }

        @Deprecated
        public Boolean isLogin() throws ClassCastException {
            return this.isLogin;
        }

        public long getUid() {
            return this.uid;
        }

        public String getToken() {
            return this.token;
        }

        public int getTokenType() {
            return this.tokenType;
        }

        private NSUserInfo(Builder builder) {
            this.isLogin = builder.isLogin;
            this.uid = builder.uid;
            this.token = builder.token;
            this.tokenType = builder.tokenType;
        }

        public static class Builder {
            Boolean isLogin = null;
            int tokenType = -1;
            long uid = -1;
            String token = null;

            public Builder setLogin(Boolean bool) {
                this.isLogin = bool;
                return this;
            }

            public Builder setToken(String str) {
                if (NSUserInfo.empty(str)) {
                    return this;
                }
                this.token = str;
                return this;
            }

            public Builder setTokenType(int i) {
                this.tokenType = i;
                return this;
            }

            public Builder setUid(long j) {
                if (j < 0) {
                    return this;
                }
                this.uid = j;
                return this;
            }

            public NSUserInfo build() {
                return new NSUserInfo(this);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean empty(String str) {
            return str == null || str.length() <= 0;
        }
    }
}
