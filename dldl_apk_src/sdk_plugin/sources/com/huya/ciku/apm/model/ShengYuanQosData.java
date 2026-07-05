package com.huya.ciku.apm.model;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ShengYuanQosData {
    public static final String METRIC_NAME_QOS = "shengyuanqos";
    public String correlationId;
    public String destinationIp;
    public String location;
    public String msg;
    public String phoneNumber;
    public String privateIP;
    public String publicIP;
    public int respCode;
    public long roomId;
    public String scene;
    public String sim;
    public String speedRate;
    public String state;
    public boolean success;
    public String token;
    public int value;

    public ShengYuanQosData state(String str) {
        this.state = str;
        return this;
    }

    public ShengYuanQosData respCode(int i) {
        this.respCode = i;
        return this;
    }

    public ShengYuanQosData msg(String str) {
        this.msg = str;
        return this;
    }

    public ShengYuanQosData correlationId(String str) {
        this.correlationId = str;
        return this;
    }

    public ShengYuanQosData phoneNumber(String str) {
        this.phoneNumber = str;
        return this;
    }

    public ShengYuanQosData sim(String str) {
        this.sim = str;
        return this;
    }

    public ShengYuanQosData privateIP(String str) {
        this.privateIP = str;
        return this;
    }

    public ShengYuanQosData publicIP(String str) {
        this.publicIP = str;
        return this;
    }

    public ShengYuanQosData destinationIp(String str) {
        this.destinationIp = str;
        return this;
    }

    public ShengYuanQosData speedRate(String str) {
        this.speedRate = str;
        return this;
    }

    public ShengYuanQosData token(String str) {
        this.token = str;
        return this;
    }

    public ShengYuanQosData success(boolean z) {
        this.success = z;
        return this;
    }

    public ShengYuanQosData value(int i) {
        this.value = i;
        return this;
    }

    public ShengYuanQosData location(String str) {
        this.location = str;
        return this;
    }

    public ShengYuanQosData scene(String str) {
        this.scene = str;
        return this;
    }

    public ShengYuanQosData roomId(long j) {
        this.roomId = j;
        return this;
    }
}
