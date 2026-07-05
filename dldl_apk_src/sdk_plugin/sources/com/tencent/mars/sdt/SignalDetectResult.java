package com.tencent.mars.sdt;

import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class SignalDetectResult {
    public ResultDetail[] details;

    public static class ResultDetail {
        public long connTime;
        public String detectIP;
        public int detectType;
        public String dnsDomain;
        public String dnsIP1;
        public String dnsIP2;
        public int errorCode;
        public int httpStatusCode;
        public String localDns;
        public int networkType;
        public int pingCheckCount;
        public String pingLossRate;
        public int port;
        public int rtt;
        public String rttStr;

        public String toString() {
            return "ResultDetail{detectType=" + this.detectType + ", errorCode=" + this.errorCode + ", networkType=" + this.networkType + ", detectIP='" + this.detectIP + "', connTime=" + this.connTime + ", port=" + this.port + ", rtt=" + this.rtt + ", rttStr='" + this.rttStr + "', httpStatusCode=" + this.httpStatusCode + ", pingCheckCount=" + this.pingCheckCount + ", pingLossRate='" + this.pingLossRate + "', dnsDomain='" + this.dnsDomain + "', localDns='" + this.localDns + "', dnsIP1='" + this.dnsIP1 + "', dnsIP2='" + this.dnsIP2 + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public String toString() {
        return "SignalDetectResult{details=" + Arrays.toString(this.details) + AbstractJsonLexerKt.END_OBJ;
    }
}
