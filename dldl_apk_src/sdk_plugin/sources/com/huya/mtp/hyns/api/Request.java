package com.huya.mtp.hyns.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Request {
    public static final int Both = 3;

    @Deprecated
    public static final int BothWithoutQuic = 6;
    public static final int CustomWs = 7;
    public static final int Long = 2;
    public static final int PushChannel = 5;
    public static final int Quic = 4;
    public static final int Short = 1;
    public static final int kTaskPriority0 = 0;
    public static final int kTaskPriority1 = 1;
    public static final int kTaskPriority2 = 2;
    public static final int kTaskPriority3 = 3;
    public static final int kTaskPriority4 = 4;
    public static final int kTaskPriority5 = 5;
    public static final int kTaskPriorityHighest = 0;
    public static final int kTaskPriorityLowest = 5;
    public static final int kTaskPriorityNormal = 3;
    public final byte[] body;
    public final String cgi;
    public final int channel;
    public final String channelName;
    public final int cmdId;
    public final boolean encrypt;
    public final boolean limitFlow;
    public final boolean limitFrequency;
    public final boolean networkStatusSensitive;
    public final int priority;
    public final int retryCount;
    public final String shortHost;
    public final int totalTimeout;
    public final String traceId;

    Request(Builder builder) {
        this.cmdId = builder.cmdId;
        this.cgi = builder.cgi;
        this.traceId = builder.traceId;
        this.retryCount = builder.retryCount;
        this.channel = builder.channel;
        this.limitFlow = builder.limitFlow;
        this.limitFrequency = builder.limitFrequency;
        this.networkStatusSensitive = builder.networkStatusSensitive;
        this.priority = builder.priority;
        this.totalTimeout = builder.totalTimeout;
        this.body = builder.body;
        this.shortHost = builder.shortHost;
        this.channelName = builder.channelName;
        this.encrypt = builder.encrypt;
    }

    public int cmdId() {
        return this.cmdId;
    }

    public String cgi() {
        return this.cgi;
    }

    public String traceId() {
        return this.traceId;
    }

    public int retryCount() {
        return this.retryCount;
    }

    public int channel() {
        return this.channel;
    }

    public boolean limitFlow() {
        return this.limitFlow;
    }

    public boolean limitFrequency() {
        return this.limitFrequency;
    }

    public boolean networkStatusSensitive() {
        return this.networkStatusSensitive;
    }

    public int priority() {
        return this.priority;
    }

    public int totalTimeout() {
        return this.totalTimeout;
    }

    public String shortHost() {
        return this.shortHost;
    }

    public byte[] getBody() {
        return this.body;
    }

    public boolean isEncrypt() {
        return this.encrypt;
    }

    public String toString() {
        return "Request{cmdId=" + this.cmdId + ", cgi=" + this.cgi + ", retryCount=" + this.retryCount + ", channel=" + this.channel + ", limitFlow=" + this.limitFlow + ", limitFrequency=" + this.limitFrequency + ", networkStatusSensitive=" + this.networkStatusSensitive + ", priority=" + this.priority + ", totalTimeout=" + this.totalTimeout + ", traceId=" + this.traceId + ", shortHost=" + this.shortHost + "}";
    }

    public static class Builder {
        byte[] body;
        int cmdId;
        String cgi = "/";
        String traceId = "none";
        String shortHost = "";
        int retryCount = -1;
        int channel = 3;
        String channelName = "";
        boolean limitFlow = true;
        boolean limitFrequency = false;
        boolean networkStatusSensitive = false;
        int priority = 3;
        int totalTimeout = 0;
        boolean encrypt = false;

        public Builder cmdId(int i) {
            this.cmdId = i;
            return this;
        }

        public Builder cgi(String str) {
            this.cgi = str;
            return this;
        }

        public Builder retryCount(int i) {
            this.retryCount = i;
            return this;
        }

        public Builder channel(int i) {
            this.channel = i;
            return this;
        }

        public Builder limitFlow(boolean z) {
            this.limitFlow = z;
            return this;
        }

        public Builder limitFrequency(boolean z) {
            this.limitFrequency = z;
            return this;
        }

        public Builder networkStatusSensitive(boolean z) {
            this.networkStatusSensitive = z;
            return this;
        }

        public Builder priority(int i) {
            this.priority = i;
            return this;
        }

        public Builder totalTimeout(int i) {
            this.totalTimeout = i;
            return this;
        }

        public Builder traceId(String str) {
            this.traceId = str;
            return this;
        }

        public Builder body(byte[] bArr) {
            this.body = bArr;
            return this;
        }

        public Builder shortHost(String str) {
            this.shortHost = str;
            return this;
        }

        public Builder channelName(String str) {
            this.channelName = str;
            return this;
        }

        public Builder encrypt(boolean z) {
            this.encrypt = z;
            return this;
        }

        public Builder fromRequest(Request request) {
            this.cmdId = request.cmdId;
            this.cgi = request.cgi;
            this.traceId = request.traceId;
            this.retryCount = request.retryCount;
            this.channel = request.channel;
            this.limitFlow = request.limitFlow;
            this.limitFrequency = request.limitFrequency;
            this.networkStatusSensitive = request.networkStatusSensitive;
            this.priority = request.priority;
            this.totalTimeout = request.totalTimeout;
            this.body = request.body;
            this.shortHost = request.shortHost;
            this.channelName = request.channelName;
            this.encrypt = request.encrypt;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }
}
