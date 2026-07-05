package com.huya.statistics.cache;

import com.huya.statistics.jce.DataInfo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TaskData {
    private byte[] content;
    private long createTime;
    private DataInfo dataInfo;
    private long id;
    private int isSuccess;
    private long lastSendTime;
    private int sendCount;
    private byte[] uuid;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(byte[] bArr) {
        this.content = bArr;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public long getLastSendTime() {
        return this.lastSendTime;
    }

    public void setLastSendTime(long j) {
        this.lastSendTime = j;
    }

    public int getSendCount() {
        return this.sendCount;
    }

    public void setSendCount(int i) {
        this.sendCount = i;
    }

    public int getIsSuccess() {
        return this.isSuccess;
    }

    public void setIsSuccess(int i) {
        this.isSuccess = i;
    }

    public byte[] getUuid() {
        return this.uuid;
    }

    public void setUuid(byte[] bArr) {
        this.uuid = bArr;
    }

    public DataInfo getDataInfo() {
        return this.dataInfo;
    }

    public void setDataInfo(DataInfo dataInfo) {
        this.dataInfo = dataInfo;
    }
}
