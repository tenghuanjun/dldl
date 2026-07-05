package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqLoginThird {
    String appTerminalType;
    List<String> bizAppids = new ArrayList();
    String bizData;
    String channel;
    int improve;
    boolean isAuthLogin;
    String oauthType;
    String oauthUrl;
    String openId;
    int openType;
    String thirdAppkey;
    String token;
    String tokenSecret;
    String udbcode;
    String userAction;

    public String getUserAction() {
        return this.userAction;
    }

    public void setUserAction(String str) {
        this.userAction = str;
    }

    public String getUdbcode() {
        return this.udbcode;
    }

    public void setUdbcode(String str) {
        this.udbcode = str;
    }

    public String getAppTerminalType() {
        return this.appTerminalType;
    }

    public void setAppTerminalType(String str) {
        this.appTerminalType = str;
    }

    public String getBizData() {
        return this.bizData;
    }

    public void setBizData(String str) {
        this.bizData = str;
    }

    public int getImprove() {
        return this.improve;
    }

    public void setImprove(int i) {
        this.improve = i;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String str) {
        this.openId = str;
    }

    public int getOpenType() {
        return this.openType;
    }

    public void setOpenType(int i) {
        this.openType = i;
    }

    public List<String> getBizAppids() {
        return this.bizAppids;
    }

    public void setBizAppids(List<String> list) {
        this.bizAppids = list;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getOauthUrl() {
        return this.oauthUrl;
    }

    public void setOauthUrl(String str) {
        this.oauthUrl = str;
    }

    public String getTokenSecret() {
        return this.tokenSecret;
    }

    public void setTokenSecret(String str) {
        this.tokenSecret = str;
    }

    public String getThirdAppkey() {
        return this.thirdAppkey;
    }

    public void setThirdAppkey(String str) {
        this.thirdAppkey = str;
    }

    public String getOauthType() {
        return this.oauthType;
    }

    public void setOauthType(String str) {
        this.oauthType = str;
    }

    public void setAuthLogin(boolean z) {
        this.isAuthLogin = z;
    }

    public boolean isAuthLogin() {
        return this.isAuthLogin;
    }
}
