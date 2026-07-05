package com.aliyun.aliyunface.log;

import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.igexin.push.core.b;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class RecordBase {
    public String magic = "-";
    public String logTime = "-";
    public String clientId = "-";
    public String clientVersion = "-";
    public String logVersion = "-";
    public String deviceId = "-";
    public String sessionId = "-";
    public String userId = "-";
    public String actionId = "-";
    public String abTestId = "-";
    public String refer = "-";
    public String appId = "-";
    public String pageStartTime = "-";
    public String xPath = "-";
    public String entityId = "-";
    public String actionName = "-";
    public String logLevel = "-";
    public String bizType = "-";
    public String logType = "-";
    public String extParam1 = "-";
    public String extParma2 = "-";
    public String extParam3 = "-";
    public String extParam4 = "-";
    public String pageId = "-";
    public String prevViewId = "-";
    public String viewId = "-";
    public String curAid = "-";
    public String curAToken = "-";
    public String sampleRatio = "-";
    public String phoneType = "-";
    public String osVersion = "-";
    public String netType = "-";
    public String internalVersion = "-";
    public String channel = "-";
    public String language = "-";
    public String hotPatchVersion = "-";
    public String coreNum = "-";
    public String maxFreq = "-";
    public String totalMem = "-";
    public String baseExt = "-";
    public String enableSPM = "-";
    public String screenMetrix = "-";

    public String getMagic() {
        return this.magic;
    }

    public void setMagic(String str) {
        this.magic = str;
    }

    public String getLogTime() {
        return this.logTime;
    }

    public void setLogTime(String str) {
        this.logTime = str;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }

    public String getLogVersion() {
        return this.logVersion;
    }

    public void setLogVersion(String str) {
        this.logVersion = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getActionId() {
        return this.actionId;
    }

    public void setActionId(String str) {
        this.actionId = str;
    }

    public String getAbTestId() {
        return this.abTestId;
    }

    public void setAbTestId(String str) {
        this.abTestId = str;
    }

    public String getRefer() {
        return this.refer;
    }

    public void setRefer(String str) {
        this.refer = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getPageStartTime() {
        return this.pageStartTime;
    }

    public void setPageStartTime(String str) {
        this.pageStartTime = str;
    }

    public String getxPath() {
        return this.xPath;
    }

    public void setxPath(String str) {
        this.xPath = str;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public void setEntityId(String str) {
        this.entityId = str;
    }

    public String getActionName() {
        return this.actionName;
    }

    public void setActionName(String str) {
        this.actionName = str;
    }

    public String getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(String str) {
        this.logLevel = str;
    }

    public String getBizType() {
        return this.bizType;
    }

    public void setBizType(String str) {
        this.bizType = str;
    }

    public String getLogType() {
        return this.logType;
    }

    public void setLogType(String str) {
        this.logType = str;
    }

    public String getExtParam1() {
        return this.extParam1;
    }

    public void setExtParam1(String str) {
        this.extParam1 = str;
    }

    public String getExtParma2() {
        return this.extParma2;
    }

    public void setExtParma2(String str) {
        this.extParma2 = str;
    }

    public String getExtParam3() {
        return this.extParam3;
    }

    public void setExtParam3(String str) {
        this.extParam3 = str;
    }

    public String getExtParam4() {
        return this.extParam4;
    }

    public void setExtParam4(String str) {
        this.extParam4 = str;
    }

    public String getPageId() {
        return this.pageId;
    }

    public void setPageId(String str) {
        this.pageId = str;
    }

    public String getPrevViewId() {
        return this.prevViewId;
    }

    public void setPrevViewId(String str) {
        this.prevViewId = str;
    }

    public String getViewId() {
        return this.viewId;
    }

    public void setViewId(String str) {
        this.viewId = str;
    }

    public String getCurAid() {
        return this.curAid;
    }

    public void setCurAid(String str) {
        this.curAid = str;
    }

    public String getCurAToken() {
        return this.curAToken;
    }

    public void setCurAToken(String str) {
        this.curAToken = str;
    }

    public String getSampleRatio() {
        return this.sampleRatio;
    }

    public void setSampleRatio(String str) {
        this.sampleRatio = str;
    }

    public String getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(String str) {
        this.phoneType = str;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public String getNetType() {
        return this.netType;
    }

    public void setNetType(String str) {
        this.netType = str;
    }

    public String getInternalVersion() {
        return this.internalVersion;
    }

    public void setInternalVersion(String str) {
        this.internalVersion = str;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getHotPatchVersion() {
        return this.hotPatchVersion;
    }

    public void setHotPatchVersion(String str) {
        this.hotPatchVersion = str;
    }

    public String getCoreNum() {
        return this.coreNum;
    }

    public void setCoreNum(String str) {
        this.coreNum = str;
    }

    public String getMaxFreq() {
        return this.maxFreq;
    }

    public void setMaxFreq(String str) {
        this.maxFreq = str;
    }

    public String getTotalMem() {
        return this.totalMem;
    }

    public void setTotalMem(String str) {
        this.totalMem = str;
    }

    public String getBaseExt() {
        return this.baseExt;
    }

    public void setBaseExt(String str) {
        this.baseExt = str;
    }

    public String getEnableSPM() {
        return this.enableSPM;
    }

    public void setEnableSPM(String str) {
        this.enableSPM = str;
    }

    public String getScreenMetrix() {
        return this.screenMetrix;
    }

    public void setScreenMetrix(String str) {
        this.screenMetrix = str;
    }

    public String toString() {
        String str;
        Field declaredField;
        StringBuilder sb = new StringBuilder();
        String[] strArr = {"magic", "logTime", "clientId", "clientVersion", "logVersion", "deviceId", "sessionId", "userId", "actionId", "abTestId", TTDownloadField.TT_REFER, "appId", "pageStartTime", "xPath", "entityId", "actionName", "logLevel", "bizType", "logType", "extParam1", "extParma2", "extParam3", "extParam4", MediationConstant.KEY_USE_POLICY_PAGE_ID, "prevViewId", "viewId", "curAid", "curAToken", "sampleRatio", "phoneType", "osVersion", "netType", "internalVersion", "channel", "language", "hotPatchVersion", "coreNum", "maxFreq", "totalMem", "baseExt", "enableSPM", "screenMetrix"};
        for (int i = 0; i < 42; i++) {
            try {
                declaredField = RecordBase.class.getDeclaredField(strArr[i]);
                declaredField.setAccessible(true);
            } catch (Exception e) {
                e = e;
            }
            try {
                str = (String) declaredField.get(this);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                str = "-";
            }
            sb.append(str);
            if (i != 41) {
                sb.append(b.aj);
            }
        }
        sb.append("$$");
        return sb.toString();
    }
}
