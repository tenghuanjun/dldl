package com.huya.component.login;

import android.text.TextUtils;
import com.duowan.auk.NoProguard;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@DatabaseTable(tableName = "account_V2.0")
public class LoginAccount implements NoProguard {
    public static final int TYPE_3RD = 255;
    public static final int TYPE_H5_DATA = 1;
    public static final int TYPE_NO_LOGIN = -1;
    public static final int TYPE_PASSPORT = 0;

    @DatabaseField(id = true)
    public String account;

    @DatabaseField(canBeNull = false)
    public long lastLoginTime;

    @DatabaseField(canBeNull = false)
    public String password;

    @DatabaseField(canBeNull = true)
    public String token;

    @DatabaseField(canBeNull = false)
    public int type = -1;

    @DatabaseField(canBeNull = false)
    public long uid;

    public long H5Uid() {
        if (this.type == 1) {
            return parseLong(this.account, 0L);
        }
        return 0L;
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
