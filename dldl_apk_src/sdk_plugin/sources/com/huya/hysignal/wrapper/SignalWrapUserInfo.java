package com.huya.hysignal.wrapper;

import com.tencent.bugly.Bugly;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SignalWrapUserInfo {
    public static final int DEFAULT_ERR_TOKEN_TYPE = -1;
    public static final int DEFAULT_ERR_UID = -1;
    public Boolean isLogin;
    public String token;
    public int tokenType;
    public long uid;

    private SignalWrapUserInfo(Builder builder) {
        this.isLogin = builder.isLogin;
        this.uid = builder.uid;
        this.token = builder.token;
        this.tokenType = builder.tokenType;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("isLogin: ");
        Boolean bool = this.isLogin;
        if (bool == null) {
            str = AbstractJsonLexerKt.NULL;
        } else {
            str = bool.booleanValue() ? "true" : Bugly.SDK_IS_DEV;
        }
        sb.append(str);
        sb.append(", uid: ");
        sb.append(this.uid);
        sb.append(", token: ");
        sb.append(this.token);
        sb.append(", tokenType: ");
        sb.append(this.tokenType);
        return sb.toString();
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
            this.token = str;
            return this;
        }

        public Builder setTokenType(int i) {
            this.tokenType = i;
            return this;
        }

        public Builder setUid(long j) {
            this.uid = j;
            return this;
        }

        public SignalWrapUserInfo build() {
            return new SignalWrapUserInfo(this);
        }

        SignalWrapUserInfo defaultBuild() {
            setToken("");
            setTokenType(0);
            setUid(0L);
            setLogin(false);
            return new SignalWrapUserInfo(this);
        }
    }
}
