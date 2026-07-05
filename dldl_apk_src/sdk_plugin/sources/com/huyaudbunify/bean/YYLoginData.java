package com.huyaudbunify.bean;

import android.util.Base64;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class YYLoginData {
    long yyuid = 0;
    long yyid = 0;
    String credit = "";
    String mobileMask = "";
    String emailMask = "";
    String ticket = "";
    String yycookie = "";
    String webcookie = "";
    String passport = "";

    public String getPassport() {
        return this.passport;
    }

    public void setPassport(String str) {
        this.passport = str;
    }

    public String getWebCookieOri() {
        return new String(Base64.decode(this.webcookie, 0));
    }

    public String getYYCookieOri() {
        return new String(Base64.decode(this.yycookie, 0));
    }

    public String getTicketOri() {
        return new String(Base64.decode(this.ticket, 0));
    }

    public String getCreditOri() {
        return new String(Base64.decode(this.credit, 0));
    }

    public long getYyuid() {
        return this.yyuid;
    }

    public void setYyuid(long j) {
        this.yyuid = j;
    }

    public long getYyid() {
        return this.yyid;
    }

    public void setYyid(long j) {
        this.yyid = j;
    }

    public String getCredit() {
        return this.credit;
    }

    public void setCredit(String str) {
        this.credit = str;
    }

    public String getMobileMask() {
        return this.mobileMask;
    }

    public void setMobileMask(String str) {
        this.mobileMask = str;
    }

    public String getEmailMask() {
        return this.emailMask;
    }

    public void setEmailMask(String str) {
        this.emailMask = str;
    }

    public String getTicket() {
        return this.ticket;
    }

    public void setTicket(String str) {
        this.ticket = str;
    }

    public String getYycookie() {
        return this.yycookie;
    }

    public void setYycookie(String str) {
        this.yycookie = str;
    }

    public String getWebcookie() {
        return this.webcookie;
    }

    public void setWebcookie(String str) {
        this.webcookie = str;
    }
}
