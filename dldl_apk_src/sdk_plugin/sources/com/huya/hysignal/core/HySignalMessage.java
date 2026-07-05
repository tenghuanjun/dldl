package com.huya.hysignal.core;

import com.huya.mtp.utils.Utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Deprecated
public final class HySignalMessage {
    int iUri = 0;
    long msgId = 0;
    byte[] sMsg = null;
    String sGroupId = "";
    boolean isFromQuery = false;
    boolean isFromP2p = false;
    String sourceLinkChannel = Utils.ChinaOperator.UNKNOWN;

    public String getSourceLongLinkChannel() {
        return this.sourceLinkChannel;
    }

    public void setSourceLongLinkChannel(String str) {
        this.sourceLinkChannel = str;
    }

    public int getIUri() {
        return this.iUri;
    }

    void setIUri(int i) {
        this.iUri = i;
    }

    public long getMsgId() {
        return this.msgId;
    }

    public void setMsgId(long j) {
        this.msgId = j;
    }

    public byte[] getSMsg() {
        return this.sMsg;
    }

    void setSMsg(byte[] bArr) {
        this.sMsg = bArr;
    }

    public String getSGroupId() {
        return this.sGroupId;
    }

    void setSGroupId(String str) {
        this.sGroupId = str;
    }

    public boolean isFromQuery() {
        return this.isFromQuery;
    }

    public void setFromQuery(boolean z) {
        this.isFromQuery = z;
    }

    public boolean isFromP2p() {
        return this.isFromP2p;
    }

    public void setFromP2p(boolean z) {
        this.isFromP2p = z;
    }

    public HySignalMessage(int i, byte[] bArr, String str, long j, boolean z, String str2) {
        setIUri(i);
        setSMsg(bArr);
        setSGroupId(str);
        setMsgId(j);
        setFromQuery(z);
        setSourceLongLinkChannel(str2);
    }
}
