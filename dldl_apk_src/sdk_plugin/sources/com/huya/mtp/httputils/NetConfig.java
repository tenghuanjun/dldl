package com.huya.mtp.httputils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NetConfig {
    private int concentLengthGzip;
    private boolean isEncryption;
    private int readTimeOut;

    private NetConfig(boolean z, int i, int i2) {
        this.isEncryption = z;
        this.concentLengthGzip = i;
        this.readTimeOut = i2;
    }

    public int getConcentLengthGzip() {
        return this.concentLengthGzip;
    }

    public int getReadTimeOut() {
        return this.readTimeOut;
    }

    public boolean isEncryption() {
        return this.isEncryption;
    }

    public static class Builder {
        public static final int LENGTH_MAX_FOR_GZIP = 1024;
        int concentLengthGzip = 1024;
        boolean isEncryption;
        int readTimeOut;

        public boolean isEncryption() {
            return this.isEncryption;
        }

        public Builder setEncryption(boolean z) {
            this.isEncryption = z;
            return this;
        }

        public int getConcentLengthGzip() {
            return this.concentLengthGzip;
        }

        public Builder setConcentLengthGzip(int i) {
            this.concentLengthGzip = i;
            return this;
        }

        public int getReadTimeOut() {
            return this.readTimeOut;
        }

        public Builder setReadTimeOut(int i) {
            this.readTimeOut = i;
            return this;
        }

        public NetConfig build() {
            return new NetConfig(this.isEncryption, this.concentLengthGzip, this.readTimeOut);
        }
    }
}
