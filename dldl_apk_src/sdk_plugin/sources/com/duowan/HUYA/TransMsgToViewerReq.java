package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class TransMsgToViewerReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    static TransMsg cache_tTransMsg;
    public long lChannelId;
    public long lSubchannelId;
    public UserId tId;
    public TransMsg tTransMsg;

    public String className() {
        return "HUYA.TransMsgToViewerReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.TransMsgToViewerReq";
    }

    public TransMsgToViewerReq() {
        this.tId = null;
        this.tTransMsg = null;
        this.lChannelId = 0L;
        this.lSubchannelId = 0L;
    }

    public TransMsgToViewerReq(UserId userId, TransMsg transMsg, long j, long j2) {
        this.tId = null;
        this.tTransMsg = null;
        this.lChannelId = 0L;
        this.lSubchannelId = 0L;
        this.tId = userId;
        this.tTransMsg = transMsg;
        this.lChannelId = j;
        this.lSubchannelId = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TransMsgToViewerReq transMsgToViewerReq = (TransMsgToViewerReq) obj;
        return JceUtil.equals(this.tId, transMsgToViewerReq.tId) && JceUtil.equals(this.tTransMsg, transMsgToViewerReq.tTransMsg) && JceUtil.equals(this.lChannelId, transMsgToViewerReq.lChannelId) && JceUtil.equals(this.lSubchannelId, transMsgToViewerReq.lSubchannelId);
    }

    public int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void writeTo(JceOutputStream jceOutputStream) {
        UserId userId = this.tId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 0);
        }
        TransMsg transMsg = this.tTransMsg;
        if (transMsg != null) {
            jceOutputStream.write((JceStruct) transMsg, 1);
        }
        jceOutputStream.write(this.lChannelId, 2);
        jceOutputStream.write(this.lSubchannelId, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        this.tId = (UserId) jceInputStream.read((JceStruct) cache_tId, 0, false);
        if (cache_tTransMsg == null) {
            cache_tTransMsg = new TransMsg();
        }
        this.tTransMsg = (TransMsg) jceInputStream.read((JceStruct) cache_tTransMsg, 1, false);
        this.lChannelId = jceInputStream.read(this.lChannelId, 2, false);
        this.lSubchannelId = jceInputStream.read(this.lSubchannelId, 3, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display((JceStruct) this.tTransMsg, "tTransMsg");
        jceDisplayer.display(this.lChannelId, "lChannelId");
        jceDisplayer.display(this.lSubchannelId, "lSubchannelId");
    }
}
