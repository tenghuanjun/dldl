package com.huyaudbunify.bean;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UserOperation {
    double longitude = 0.0d;
    double latitude = 0.0d;
    String ssid = "";
    Map<String, Integer> record = new HashMap();

    public Map<String, Integer> getRecord() {
        return this.record;
    }

    public void setRecord(Map<String, Integer> map) {
        this.record = map;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }
}
