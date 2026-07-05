package com.huya.mtp.nsdt.adr;

import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSDTPlanTask {
    String[] ips;
    int[] ports;
    String request_body;
    int task_type;
    int tcp_timeout;
    int timeSpanSeconds;

    private NSDTPlanTask(String[] strArr, int[] iArr, int i, int i2, int i3, String str) {
        this.ips = strArr;
        this.ports = iArr;
        this.timeSpanSeconds = i;
        this.task_type = i2;
        this.tcp_timeout = i3;
        this.request_body = str;
    }

    public String[] getIps() {
        return this.ips;
    }

    public int[] getPorts() {
        return this.ports;
    }

    public int getTimeSpanSeconds() {
        return this.timeSpanSeconds;
    }

    public int getTask_type() {
        return this.task_type;
    }

    public int getTcp_timeout() {
        return this.tcp_timeout;
    }

    public String getRequest_body() {
        return this.request_body;
    }

    public String toString() {
        return "NSDTPlanTask{ips=" + Arrays.toString(this.ips) + ", ports=" + Arrays.toString(this.ports) + ", timeSpanSeconds=" + this.timeSpanSeconds + ", task_type=" + this.task_type + ", tcp_timeout=" + this.tcp_timeout + ", request_body='" + this.request_body + '\'' + AbstractJsonLexerKt.END_OBJ;
    }

    public static class Builder {
        String[] ips;
        int[] ports;
        String request_body;
        int task_type;
        int tcp_timeout;
        int timeSpanSeconds;

        public Builder(String[] strArr, int[] iArr, int i, int i2, int i3, String str) {
            this.ips = strArr;
            this.ports = iArr;
            this.timeSpanSeconds = i;
            this.task_type = i2;
            this.tcp_timeout = i3;
            this.request_body = str;
        }

        public void setIps(String[] strArr) {
            this.ips = strArr;
        }

        public void setPorts(int[] iArr) {
            this.ports = iArr;
        }

        public void setTimeSpanSeconds(int i) {
            this.timeSpanSeconds = i;
        }

        public void setTask_type(int i) {
            this.task_type = i;
        }

        public void setTcp_timeout(int i) {
            this.tcp_timeout = i;
        }

        public void setRequest_body(String str) {
            this.request_body = str;
        }

        public NSDTPlanTask build() {
            return new NSDTPlanTask(this.ips, this.ports, this.timeSpanSeconds, this.task_type, this.tcp_timeout, this.request_body);
        }
    }
}
