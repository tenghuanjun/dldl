package com.huyaudbunify.util;

import com.huyaudbunify.account.SdkComType;
import com.huyaudbunify.bean.LoginData;
import com.huyaudbunify.bean.ThirdLoginOption;
import com.huyaudbunify.bean.UserAction;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaAccountSaveUtils {
    private static volatile HuyaAccountSaveUtils mHuyaAccountSaveUtils;
    private SdkComType type = SdkComType.HYTPYE_HY;
    private SdkComType defaultType = SdkComType.HYTPYE_HY;
    private long uid = 0;
    private String passport = "";
    private String mobileMask = "";
    private boolean isShowHuya = false;
    private int thirdLgnOpenType = 0;
    private String thirdLgnToken = "";
    private ThirdLoginOption thirdLgnOption = null;
    private LoginData loginData = null;
    private String deviceId = "";
    private boolean isLogin = false;
    private String guid = "";
    private String huyaua = "";
    private String channel = "";
    private String appGroup = "";
    private int mTerminalType = 1;
    private String lcid = "0";
    private String sdid = "";
    private String contrycode = "";
    private String servantName = "";
    private Boolean misHome = false;
    private UserAction userAction = null;

    public int getTerminalType() {
        return this.mTerminalType;
    }

    public void setTerminalType(int i) {
        this.mTerminalType = i;
    }

    public String getLcid() {
        return this.lcid;
    }

    public void setLcid(String str) {
        this.lcid = str;
    }

    public String getSdid() {
        return this.sdid;
    }

    public void setSdid(String str) {
        this.sdid = str;
    }

    public String getContrycode() {
        return this.contrycode;
    }

    public void setContrycode(String str) {
        this.contrycode = str;
    }

    public String getServantName() {
        return this.servantName;
    }

    public void setServantName(String str) {
        this.servantName = str;
    }

    public Boolean isHome() {
        return this.misHome;
    }

    public void setHome(Boolean bool) {
        this.misHome = bool;
    }

    public UserAction getUserAction() {
        return this.userAction;
    }

    public void setUserAction(UserAction userAction) {
        this.userAction = userAction;
    }

    public boolean isLogin() {
        return this.isLogin;
    }

    public void setLogin(boolean z) {
        this.isLogin = z;
    }

    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String str) {
        this.guid = str;
    }

    public String getHuyaua() {
        return this.huyaua;
    }

    public void setHuyaua(String str) {
        this.huyaua = str;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public static HuyaAccountSaveUtils getInstance() {
        if (mHuyaAccountSaveUtils == null) {
            synchronized (HuyaAccountSaveUtils.class) {
                if (mHuyaAccountSaveUtils == null) {
                    mHuyaAccountSaveUtils = new HuyaAccountSaveUtils();
                }
            }
        }
        return mHuyaAccountSaveUtils;
    }

    public LoginData getLoginData() {
        return this.loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public SdkComType getType() {
        return this.type;
    }

    public void setType(SdkComType sdkComType) {
        this.type = sdkComType;
    }

    public SdkComType getDefaultType() {
        return this.defaultType;
    }

    public void setDefaultType(SdkComType sdkComType) {
        this.defaultType = sdkComType;
    }

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public String getPassport() {
        return this.passport;
    }

    public void setPassport(String str) {
        this.passport = str;
    }

    public String getMobileMask() {
        return this.mobileMask;
    }

    public void setMobileMask(String str) {
        this.mobileMask = str;
    }

    public boolean isShowHuya() {
        return this.isShowHuya;
    }

    public void setShowHuya(boolean z) {
        this.isShowHuya = z;
    }

    public int getThirdLgnOpenType() {
        return this.thirdLgnOpenType;
    }

    public void setThirdLgnOpenType(int i) {
        this.thirdLgnOpenType = i;
    }

    public String getThirdLgnToken() {
        return this.thirdLgnToken;
    }

    public void setThirdLgnToken(String str) {
        this.thirdLgnToken = str;
    }

    public ThirdLoginOption getThirdLgnOption() {
        return this.thirdLgnOption;
    }

    public void setThirdLgnOption(ThirdLoginOption thirdLoginOption) {
        this.thirdLgnOption = thirdLoginOption;
    }

    public String getAppGroup() {
        return this.appGroup;
    }

    public void setAppGroup(String str) {
        this.appGroup = str;
    }
}
