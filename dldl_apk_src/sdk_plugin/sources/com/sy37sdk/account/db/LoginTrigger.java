package com.sy37sdk.account.db;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LoginTrigger {
    private long id;
    private String loginType;
    private String token;
    private long triggerTime;
    private String uid;
    private String uname;

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String str) {
        this.uname = str;
    }

    public String getLoginType() {
        return this.loginType;
    }

    public void setLoginType(String str) {
        this.loginType = str;
    }

    public long getTriggerTime() {
        return this.triggerTime;
    }

    public void setTriggerTime(long j) {
        this.triggerTime = j;
    }

    public String toString() {
        return "LoginTrigger{id=" + this.id + ", uid=" + this.uid + ", uname='" + this.uname + "', loginType='" + this.loginType + "', token='" + this.token + "', triggerTime=" + this.triggerTime + AbstractJsonLexerKt.END_OBJ;
    }
}
