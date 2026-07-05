package com.huya.component.login;

import android.text.TextUtils;
import com.duowan.auk.NoProguard;
import com.huya.component.login.api.IAccount;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Account implements IAccount, NoProguard {
    public static final int TYPE_3RD = 255;
    public static final int TYPE_H5_DATA = 1;
    public static final int TYPE_NO_LOGIN = -1;
    public static final int TYPE_PASSPORT = 0;
    public String account;
    public long lastLoginTime;
    public String password;
    public String token;
    public int type = -1;
    public long uid;

    public long H5Uid() {
        if (this.type == 1) {
            return parseLong(this.account, 0L);
        }
        return 0L;
    }

    @Override // com.huya.component.login.api.IAccount
    public void setAccountInfo(String str, String str2, String str3, int i, long j, long j2) {
        this.account = str;
        this.password = str2;
        this.token = str3;
        this.type = i;
        this.lastLoginTime = j;
        this.uid = j2;
    }

    public int hashCode() {
        String str = this.account;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.password;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (obj == null || TextUtils.isEmpty(this.account)) {
            return false;
        }
        return this.account.equals(((Account) obj).account);
    }

    public String toString() {
        return "Account{account='" + this.account + "', password='" + this.password + "', token='" + this.token + "', type=" + this.type + ", lastLoginTime=" + this.lastLoginTime + ", uid=" + this.uid + AbstractJsonLexerKt.END_OBJ;
    }

    private long parseLong(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return j;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }
}
