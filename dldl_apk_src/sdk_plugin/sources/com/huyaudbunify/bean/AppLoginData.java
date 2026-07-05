package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AppLoginData {
    List<Biztoken> biztoken_vec = new ArrayList();
    Cookie cookie;
    String cred;
    String emailMask;
    public long hyOpenId;
    long hyid;
    int isHuya;
    String mobileMask;
    String passport;
    int regOrigin;
    long status;
    long subUid;
    public Map<String, String> thirdParams;
    long timestamp;
    long uid;
    public String userId;
    public int userIdState;

    public int getUserIdState() {
        return this.userIdState;
    }

    public void setUserIdState(int i) {
        this.userIdState = i;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public int getRegOrigin() {
        return this.regOrigin;
    }

    public void setRegOrigin(int i) {
        this.regOrigin = i;
    }

    public List<Biztoken> getBiztoken_vec() {
        return this.biztoken_vec;
    }

    public void setBiztoken_vec(List<Biztoken> list) {
        this.biztoken_vec = list;
    }

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public long getHyid() {
        return this.hyid;
    }

    public void setHyid(long j) {
        this.hyid = j;
    }

    public String getPassport() {
        return this.passport;
    }

    public void setPassport(String str) {
        this.passport = str;
    }

    public String getCred() {
        return this.cred;
    }

    public void setCred(String str) {
        this.cred = str;
    }

    public Cookie getCookie() {
        return this.cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
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

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public long getSubUid() {
        return this.subUid;
    }

    public void setSubUid(long j) {
        this.subUid = j;
    }

    public int getIsHuya() {
        return this.isHuya;
    }

    public void setIsHuya(int i) {
        this.isHuya = i;
    }

    public long getStatus() {
        return this.status;
    }

    public void setStatus(long j) {
        this.status = j;
    }

    public long getHyOpenId() {
        return this.hyOpenId;
    }

    public void setHyOpenId(long j) {
        this.hyOpenId = j;
    }

    public Map<String, String> getThirdParams() {
        return this.thirdParams;
    }

    public void setThirdParams(Map<String, String> map) {
        this.thirdParams = map;
    }
}
