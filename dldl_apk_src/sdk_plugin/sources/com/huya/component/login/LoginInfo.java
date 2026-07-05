package com.huya.component.login;

import com.duowan.auk.NoProguard;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginInfo implements NoProguard {
    public static final int TYPE_3RD = 255;
    public static final int TYPE_CREDIT_LOGIN = 2;
    public static final int TYPE_H5_DATA = 1;
    public static final int TYPE_ONE_KEY = 4;
    public static final int TYPE_PASSPORT = 0;
    public static final int TYPE_SMS = 3;
    public String account;
    public String password;
    public int strategy_type;
    public int thirdType;
    public String token;
    public int type;
    public long uid;

    public enum LoginType {
        NO_LOGIN(-1),
        TYPE_YY(20),
        TYPE_QQ(4),
        TYPE_WEI_BO(5),
        TYPE_WE_CHAT(3);

        public String appKey;
        public int value;

        LoginType(int i) {
            this.value = i;
        }
    }

    public String toString() {
        return "LoginInfo{account='" + this.account + "', token='" + this.token + "', type=" + this.type + ", uid=" + this.uid + ", thirdType=" + this.thirdType + ", strategy_type=" + this.strategy_type + AbstractJsonLexerKt.END_OBJ;
    }
}
