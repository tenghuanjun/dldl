package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SwitchToGameTemplateReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public long lSid;
    public long lTid;
    public UserId tId;

    public String className() {
        return "HUYA.SwitchToGameTemplateReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SwitchToGameTemplateReq";
    }

    public SwitchToGameTemplateReq() {
        this.tId = null;
        this.lTid = 0L;
        this.lSid = 0L;
    }

    public SwitchToGameTemplateReq(UserId userId, long j, long j2) {
        this.tId = null;
        this.lTid = 0L;
        this.lSid = 0L;
        this.tId = userId;
        this.lTid = j;
        this.lSid = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SwitchToGameTemplateReq switchToGameTemplateReq = (SwitchToGameTemplateReq) obj;
        return JceUtil.equals(this.tId, switchToGameTemplateReq.tId) && JceUtil.equals(this.lTid, switchToGameTemplateReq.lTid) && JceUtil.equals(this.lSid, switchToGameTemplateReq.lSid);
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
        jceOutputStream.write(this.lTid, 1);
        jceOutputStream.write(this.lSid, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        this.tId = (UserId) jceInputStream.read((JceStruct) cache_tId, 0, false);
        this.lTid = jceInputStream.read(this.lTid, 1, false);
        this.lSid = jceInputStream.read(this.lSid, 2, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSid, "lSid");
    }
}
