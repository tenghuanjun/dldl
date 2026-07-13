package com.bytedance.framwork.core.sdklib.model;

/* JADX INFO: loaded from: classes2.dex */
public class LocalLog {
    public String aid;
    public long createTime;
    public String data;
    public long id;
    public String type;
    public String type2;

    public LocalLog() {
    }

    public LocalLog(long j, String str) {
        this.id = j;
        this.data = str;
    }

    public LocalLog(long j, String str, String str2) {
        this.id = j;
        this.aid = str;
        this.data = str2;
    }

    public LocalLog(String str, String str2, String str3, String str4, long j) {
        this.aid = str;
        this.type = str2;
        this.type2 = str3;
        this.data = str4;
        this.createTime = j;
    }

    public static LocalLog newLocalLog(String str) {
        return new LocalLog().setType(str);
    }

    public LocalLog setAid(String str) {
        this.aid = str;
        return this;
    }

    public LocalLog setData(String str) {
        this.data = str;
        return this;
    }

    public LocalLog setId(long j) {
        this.id = j;
        return this;
    }

    public LocalLog setTimestamp(long j) {
        this.createTime = j;
        return this;
    }

    public LocalLog setType(String str) {
        this.type = str;
        return this;
    }

    public LocalLog setType2(String str) {
        this.type2 = str;
        return this;
    }

    public String toString() {
        return "LocalLog{id=" + this.id + ", aid=" + this.aid + ", type='" + this.type + "', type2='" + this.type2 + "', data='" + this.data + "', createTime=" + this.createTime + '}';
    }
}
