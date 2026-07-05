package com.huya.ciku.apm.model;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class QoSData {
    public static final String METRIC_NAME_QOS = "QoS";
    public int bitrate;
    public String correlationId;
    public String destinationIp;
    public String msg;
    public String phoneNumber;
    public String reqType;
    public int respCode;
    public String sourceIp;
    public String speedupType;

    public QoSData reqType(String str) {
        this.reqType = str;
        return this;
    }

    public QoSData respCode(int i) {
        this.respCode = i;
        return this;
    }

    public QoSData msg(String str) {
        this.msg = str;
        return this;
    }

    public QoSData correlationId(String str) {
        this.correlationId = str;
        return this;
    }

    public QoSData phoneNumber(String str) {
        this.phoneNumber = str;
        return this;
    }

    public QoSData speedupType(String str) {
        this.speedupType = str;
        return this;
    }

    public QoSData sourceIp(String str) {
        this.sourceIp = str;
        return this;
    }

    public QoSData destinationIp(String str) {
        this.destinationIp = str;
        return this;
    }

    public QoSData bitrate(int i) {
        this.bitrate = i;
        return this;
    }
}
