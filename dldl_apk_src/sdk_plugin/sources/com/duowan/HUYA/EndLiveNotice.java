package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class EndLiveNotice extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lPresenterUid = 0;
    public int iReason = 0;
    public long lLiveId = 0;
    public String sReason = "";

    public String className() {
        return "HUYA.EndLiveNotice";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.EndLiveNotice";
    }

    public long getLPresenterUid() {
        return this.lPresenterUid;
    }

    public void setLPresenterUid(long j) {
        this.lPresenterUid = j;
    }

    public int getIReason() {
        return this.iReason;
    }

    public void setIReason(int i) {
        this.iReason = i;
    }

    public long getLLiveId() {
        return this.lLiveId;
    }

    public void setLLiveId(long j) {
        this.lLiveId = j;
    }

    public String getSReason() {
        return this.sReason;
    }

    public void setSReason(String str) {
        this.sReason = str;
    }

    public EndLiveNotice() {
        setLPresenterUid(0L);
        setIReason(this.iReason);
        setLLiveId(this.lLiveId);
        setSReason(this.sReason);
    }

    public EndLiveNotice(long j, int i, long j2, String str) {
        setLPresenterUid(j);
        setIReason(i);
        setLLiveId(j2);
        setSReason(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EndLiveNotice endLiveNotice = (EndLiveNotice) obj;
        return JceUtil.equals(this.lPresenterUid, endLiveNotice.lPresenterUid) && JceUtil.equals(this.iReason, endLiveNotice.iReason) && JceUtil.equals(this.lLiveId, endLiveNotice.lLiveId) && JceUtil.equals(this.sReason, endLiveNotice.sReason);
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
        jceOutputStream.write(this.lPresenterUid, 0);
        jceOutputStream.write(this.iReason, 1);
        jceOutputStream.write(this.lLiveId, 2);
        String str = this.sReason;
        if (str != null) {
            jceOutputStream.write(str, 3);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLPresenterUid(jceInputStream.read(this.lPresenterUid, 0, false));
        setIReason(jceInputStream.read(this.iReason, 1, false));
        setLLiveId(jceInputStream.read(this.lLiveId, 2, false));
        setSReason(jceInputStream.readString(3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lPresenterUid, "lPresenterUid");
        jceDisplayer.display(this.iReason, "iReason");
        jceDisplayer.display(this.lLiveId, "lLiveId");
        jceDisplayer.display(this.sReason, "sReason");
    }
}
