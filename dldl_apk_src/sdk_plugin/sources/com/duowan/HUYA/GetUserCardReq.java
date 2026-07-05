package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GetUserCardReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public UserId tId = null;
    public long lPid = 0;
    public long lReqUid = 0;

    public String className() {
        return "HUYA.GetUserCardReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetUserCardReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public long getLPid() {
        return this.lPid;
    }

    public void setLPid(long j) {
        this.lPid = j;
    }

    public long getLReqUid() {
        return this.lReqUid;
    }

    public void setLReqUid(long j) {
        this.lReqUid = j;
    }

    public GetUserCardReq() {
        setTId(null);
        setLPid(this.lPid);
        setLReqUid(this.lReqUid);
    }

    public GetUserCardReq(UserId userId, long j, long j2) {
        setTId(userId);
        setLPid(j);
        setLReqUid(j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetUserCardReq getUserCardReq = (GetUserCardReq) obj;
        return JceUtil.equals(this.tId, getUserCardReq.tId) && JceUtil.equals(this.lPid, getUserCardReq.lPid) && JceUtil.equals(this.lReqUid, getUserCardReq.lReqUid);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tId), JceUtil.hashCode(this.lPid), JceUtil.hashCode(this.lReqUid)});
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
        jceOutputStream.write(this.lPid, 1);
        jceOutputStream.write(this.lReqUid, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setLPid(jceInputStream.read(this.lPid, 1, false));
        setLReqUid(jceInputStream.read(this.lReqUid, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.lPid, "lPid");
        jceDisplayer.display(this.lReqUid, "lReqUid");
    }
}
