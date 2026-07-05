package com.huya.security.hydeviceid;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyDeviceNetInfo {
    public String iccid;
    public String multiCardInfo;
    public int netCarry;
    public Map<String, String> netMap = new HashMap();
    public String networkOperatorName;
    public String simOperatorName;
    public String telephoneNumber;
    public String voiceMailNumber;
    public Object wifiList;

    public Map<String, String> getNetMap() {
        return this.netMap;
    }

    public void setNetMap(Map<String, String> map) {
        this.netMap = map;
    }

    public int getNetCarry() {
        return this.netCarry;
    }

    public void setNetCarry(int i) {
        this.netCarry = i;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setIccid(String str) {
        this.iccid = str;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public void setTelephoneNumber(String str) {
        this.telephoneNumber = str;
    }

    public String getSimOperatorName() {
        return this.simOperatorName;
    }

    public void setSimOperatorName(String str) {
        this.simOperatorName = str;
    }

    public String getNetworkOperatorName() {
        return this.networkOperatorName;
    }

    public String getVoiceMailNumber() {
        return this.voiceMailNumber;
    }

    public void setVoiceMailNumber(String str) {
        this.voiceMailNumber = str;
    }

    public void setNetworkOperatorName(String str) {
        this.networkOperatorName = str;
    }

    public String getMultiCardInfo() {
        return this.multiCardInfo;
    }

    public void setMultiCardInfo(String str) {
        this.multiCardInfo = str;
    }

    public Object getWifiList() {
        return this.wifiList;
    }

    public void setWifiList(Object obj) {
        this.wifiList = obj;
    }
}
