package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class PresenterNotifyPopupReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public UserId tId = null;
    public long lSid = 0;
    public long lSubSid = 0;
    public int iLiveType = 0;
    public int iFromType = 0;
    public int iProceesId = 0;

    public String className() {
        return "HUYA.PresenterNotifyPopupReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PresenterNotifyPopupReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public long getLSid() {
        return this.lSid;
    }

    public void setLSid(long j) {
        this.lSid = j;
    }

    public long getLSubSid() {
        return this.lSubSid;
    }

    public void setLSubSid(long j) {
        this.lSubSid = j;
    }

    public int getILiveType() {
        return this.iLiveType;
    }

    public void setILiveType(int i) {
        this.iLiveType = i;
    }

    public int getIFromType() {
        return this.iFromType;
    }

    public void setIFromType(int i) {
        this.iFromType = i;
    }

    public int getIProceesId() {
        return this.iProceesId;
    }

    public void setIProceesId(int i) {
        this.iProceesId = i;
    }

    public PresenterNotifyPopupReq() {
        setTId(null);
        setLSid(this.lSid);
        setLSubSid(this.lSubSid);
        setILiveType(this.iLiveType);
        setIFromType(this.iFromType);
        setIProceesId(this.iProceesId);
    }

    public PresenterNotifyPopupReq(UserId userId, long j, long j2, int i, int i2, int i3) {
        setTId(userId);
        setLSid(j);
        setLSubSid(j2);
        setILiveType(i);
        setIFromType(i2);
        setIProceesId(i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PresenterNotifyPopupReq presenterNotifyPopupReq = (PresenterNotifyPopupReq) obj;
        return JceUtil.equals(this.tId, presenterNotifyPopupReq.tId) && JceUtil.equals(this.lSid, presenterNotifyPopupReq.lSid) && JceUtil.equals(this.lSubSid, presenterNotifyPopupReq.lSubSid) && JceUtil.equals(this.iLiveType, presenterNotifyPopupReq.iLiveType) && JceUtil.equals(this.iFromType, presenterNotifyPopupReq.iFromType) && JceUtil.equals(this.iProceesId, presenterNotifyPopupReq.iProceesId);
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
        jceOutputStream.write(this.lSid, 1);
        jceOutputStream.write(this.lSubSid, 2);
        jceOutputStream.write(this.iLiveType, 3);
        jceOutputStream.write(this.iFromType, 4);
        jceOutputStream.write(this.iProceesId, 5);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setLSid(jceInputStream.read(this.lSid, 1, false));
        setLSubSid(jceInputStream.read(this.lSubSid, 2, false));
        setILiveType(jceInputStream.read(this.iLiveType, 3, false));
        setIFromType(jceInputStream.read(this.iFromType, 4, false));
        setIProceesId(jceInputStream.read(this.iProceesId, 5, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.lSid, "lSid");
        jceDisplayer.display(this.lSubSid, "lSubSid");
        jceDisplayer.display(this.iLiveType, "iLiveType");
        jceDisplayer.display(this.iFromType, "iFromType");
        jceDisplayer.display(this.iProceesId, "iProceesId");
    }
}
