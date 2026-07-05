package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetMobileGameListReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public UserId tId = null;
    public int iAllGameFlag = 0;

    public String className() {
        return "HUYA.GetMobileGameListReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetMobileGameListReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public int getIAllGameFlag() {
        return this.iAllGameFlag;
    }

    public void setIAllGameFlag(int i) {
        this.iAllGameFlag = i;
    }

    public GetMobileGameListReq() {
        setTId(null);
        setIAllGameFlag(this.iAllGameFlag);
    }

    public GetMobileGameListReq(UserId userId, int i) {
        setTId(userId);
        setIAllGameFlag(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetMobileGameListReq getMobileGameListReq = (GetMobileGameListReq) obj;
        return JceUtil.equals(this.tId, getMobileGameListReq.tId) && JceUtil.equals(this.iAllGameFlag, getMobileGameListReq.iAllGameFlag);
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
        jceOutputStream.write(this.iAllGameFlag, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setIAllGameFlag(jceInputStream.read(this.iAllGameFlag, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.iAllGameFlag, "iAllGameFlag");
    }
}
