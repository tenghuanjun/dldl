package com.sq.diagnostic.assistant.tcping;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TcpingResult {
    public static final int TCPING_SUCCESS_CODE = 0;
    public int code;
    public Exception exception;
    public String host;
    public String ip;
    public long latency;
    public int port;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TCPing ");
        sb.append(this.host);
        sb.append("(");
        sb.append(this.ip);
        sb.append(":");
        sb.append(this.port);
        sb.append(")");
        if (this.code == 0) {
            sb.append(" time=");
            sb.append(this.latency);
            sb.append("ms");
        } else {
            sb.append(this.exception.getLocalizedMessage());
        }
        return sb.toString();
    }
}
