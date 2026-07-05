package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetPresenterSignInviteReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public UserId tId = null;
    public int iTermType = 0;

    public String className() {
        return "HUYA.GetPresenterSignInviteReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetPresenterSignInviteReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public int getITermType() {
        return this.iTermType;
    }

    public void setITermType(int i) {
        this.iTermType = i;
    }

    public GetPresenterSignInviteReq() {
        setTId(null);
        setITermType(this.iTermType);
    }

    public GetPresenterSignInviteReq(UserId userId, int i) {
        setTId(userId);
        setITermType(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetPresenterSignInviteReq getPresenterSignInviteReq = (GetPresenterSignInviteReq) obj;
        return JceUtil.equals(this.tId, getPresenterSignInviteReq.tId) && JceUtil.equals(this.iTermType, getPresenterSignInviteReq.iTermType);
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
        jceOutputStream.write(this.iTermType, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setITermType(jceInputStream.read(this.iTermType, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.iTermType, "iTermType");
    }
}
