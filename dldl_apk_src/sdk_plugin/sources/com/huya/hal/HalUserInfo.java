package com.huya.hal;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HalUserInfo {
    final Boolean isLogin;
    final String token;
    final int tokenType;
    final long uid;

    private HalUserInfo(Builder builder) {
        this.isLogin = builder.isLogin;
        this.uid = builder.uid;
        this.token = builder.token;
        this.tokenType = builder.tokenType;
    }

    public String toString() {
        return "HalUserInfo{isLogin= " + this.isLogin + ", uid= " + this.uid + ", token= '" + this.token + "', tokenType= " + this.tokenType + " }";
    }

    public String readableString() {
        return "   {已经登陆 = " + this.isLogin + ",\n   uid = " + this.uid + ",\n   token = '" + this.token + ",\n   token类型 = " + this.tokenType + "}";
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
            if (str == null) {
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

        public HalUserInfo build() {
            return new HalUserInfo(this);
        }

        HalUserInfo defaultUserInfo() {
            setLogin(false);
            setToken("");
            setTokenType(0);
            setUid(0L);
            return new HalUserInfo(this);
        }
    }
}
