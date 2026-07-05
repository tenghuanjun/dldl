package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResLoginThird {
    String gender;
    ResponseHeander header;
    String imageUrl;
    LoginData loginData;
    List<StrategyDetail> loginStrategy = new ArrayList();
    String nickName;
    String openId;
    String refreshToken;

    public List<StrategyDetail> getLoginStrategy() {
        return this.loginStrategy;
    }

    public void setLoginStrategy(List<StrategyDetail> list) {
        this.loginStrategy = list;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public LoginData getLoginData() {
        return this.loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public ResponseHeander getHeader() {
        return this.header;
    }

    public void setHeader(ResponseHeander responseHeander) {
        this.header = responseHeander;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String str) {
        this.openId = str;
    }
}
