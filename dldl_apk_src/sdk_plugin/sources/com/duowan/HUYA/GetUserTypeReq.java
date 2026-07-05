package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetUserTypeReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public UserId tId = null;
    public long lUid = 0;
    public long lPresenterUid = 0;
    public long lTid = 0;
    public long lSubcid = 0;

    public String className() {
        return "HUYA.GetUserTypeReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetUserTypeReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public long getLPresenterUid() {
        return this.lPresenterUid;
    }

    public void setLPresenterUid(long j) {
        this.lPresenterUid = j;
    }

    public long getLTid() {
        return this.lTid;
    }

    public void setLTid(long j) {
        this.lTid = j;
    }

    public long getLSubcid() {
        return this.lSubcid;
    }

    public void setLSubcid(long j) {
        this.lSubcid = j;
    }

    public GetUserTypeReq() {
        setTId(null);
        setLUid(this.lUid);
        setLPresenterUid(this.lPresenterUid);
        setLTid(this.lTid);
        setLSubcid(this.lSubcid);
    }

    public GetUserTypeReq(UserId userId, long j, long j2, long j3, long j4) {
        setTId(userId);
        setLUid(j);
        setLPresenterUid(j2);
        setLTid(j3);
        setLSubcid(j4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetUserTypeReq getUserTypeReq = (GetUserTypeReq) obj;
        return JceUtil.equals(this.tId, getUserTypeReq.tId) && JceUtil.equals(this.lUid, getUserTypeReq.lUid) && JceUtil.equals(this.lPresenterUid, getUserTypeReq.lPresenterUid) && JceUtil.equals(this.lTid, getUserTypeReq.lTid) && JceUtil.equals(this.lSubcid, getUserTypeReq.lSubcid);
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
        jceOutputStream.write(this.lUid, 1);
        jceOutputStream.write(this.lPresenterUid, 2);
        jceOutputStream.write(this.lTid, 3);
        jceOutputStream.write(this.lSubcid, 4);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setLUid(jceInputStream.read(this.lUid, 1, false));
        setLPresenterUid(jceInputStream.read(this.lPresenterUid, 2, false));
        setLTid(jceInputStream.read(this.lTid, 3, false));
        setLSubcid(jceInputStream.read(this.lSubcid, 4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.lPresenterUid, "lPresenterUid");
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSubcid, "lSubcid");
    }
}
