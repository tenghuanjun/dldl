package com.tencent.mars.stn;

import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class TaskProfile {
    public String cgi;
    public int channelSelect;
    public int cmdId;
    public int dyntimeStatus;
    public long endTaskTime;
    public int errCode;
    public int errType;
    public ConnectProfile[] historyNetLinkers;
    public long startTaskTime;
    public int taskId;

    public static class ConnectProfile {
        public int connErrCode;
        public long connTime;
        public long disconnErrCode;
        public long disconnErrType;
        public long disconnTime;
        public long dnsEndTime;
        public long dnsTime;
        public String host;
        public String ip;
        public int ipType;
        public int port;
        public long startTime;
        public int tryIPCount;

        public String toString() {
            return "ConnectProfile{startTime=" + this.startTime + ", dnsTime=" + this.dnsTime + ", dnsEndTime=" + this.dnsEndTime + ", connTime=" + this.connTime + ", connErrCode=" + this.connErrCode + ", tryIPCount=" + this.tryIPCount + ", ip='" + this.ip + "', port=" + this.port + ", host='" + this.host + "', ipType=" + this.ipType + ", disconnTime=" + this.disconnTime + ", disconnErrType=" + this.disconnErrType + ", disconnErrCode=" + this.disconnErrCode + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public String toString() {
        return "TaskProfile{taskId=" + this.taskId + ", cmdId=" + this.cmdId + ", cgi='" + this.cgi + "', startTaskTime=" + this.startTaskTime + ", endTaskTime=" + this.endTaskTime + ", dyntimeStatus=" + this.dyntimeStatus + ", errCode=" + this.errCode + ", errType=" + this.errType + ", channelSelect=" + this.channelSelect + ", historyNetLinkers=" + Arrays.toString(this.historyNetLinkers) + AbstractJsonLexerKt.END_OBJ;
    }
}
