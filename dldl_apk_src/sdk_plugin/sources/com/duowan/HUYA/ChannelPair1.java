package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ChannelPair1 extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lTid = 0;
    public long lSid = 0;

    public String className() {
        return "HUYA.ChannelPair";
    }

    public String fullClassName() {
        return "ChannelPair";
    }

    public long getLTid() {
        return this.lTid;
    }

    public void setLTid(long j) {
        this.lTid = j;
    }

    public long getLSid() {
        return this.lSid;
    }

    public void setLSid(long j) {
        this.lSid = j;
    }

    public ChannelPair1() {
        setLTid(0L);
        setLSid(this.lSid);
    }

    public ChannelPair1(long j, long j2) {
        setLTid(j);
        setLSid(j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChannelPair1 channelPair1 = (ChannelPair1) obj;
        return JceUtil.equals(this.lTid, channelPair1.lTid) && JceUtil.equals(this.lSid, channelPair1.lSid);
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
        jceOutputStream.write(this.lTid, 0);
        jceOutputStream.write(this.lSid, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLTid(jceInputStream.read(this.lTid, 0, false));
        setLSid(jceInputStream.read(this.lSid, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSid, "lSid");
    }
}
