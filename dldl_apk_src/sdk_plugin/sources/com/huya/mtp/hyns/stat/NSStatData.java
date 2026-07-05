package com.huya.mtp.hyns.stat;

import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSStatData {
    public String authority;
    public DataType dataType;
    public String errorInfo;
    public String path;
    public String reportId;
    public int requestSize;
    public int responseSize;
    public int responseTime;
    public int retCode;
    public Scheme scheme;
    public int success;
    public long suspendTime;

    public enum DataType {
        wup("wup"),
        json("json");

        private String tag;

        DataType(String str) {
            this.tag = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.tag;
        }
    }

    public enum Scheme {
        yy("yy"),
        huya(NSStatUtil.DEFAULT_APP),
        http(IDataSource.SCHEME_HTTP_TAG),
        https(IDataSource.SCHEME_HTTPS_TAG),
        hysignal("hysignal");

        private String tag;

        Scheme(String str) {
            this.tag = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.tag;
        }
    }

    public void clear() {
        this.scheme = null;
        this.authority = null;
        this.path = null;
        this.dataType = null;
        this.success = 0;
        this.retCode = 0;
        this.responseTime = 0;
        this.responseSize = 0;
        this.requestSize = 0;
        this.errorInfo = null;
        this.reportId = null;
    }
}
