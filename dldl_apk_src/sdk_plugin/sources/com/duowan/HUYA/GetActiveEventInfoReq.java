package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GetActiveEventInfoReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public int iCurGameID;
    public int iOrderType;
    public int iType;
    public long lUid;
    public UserId tId;

    public String className() {
        return "HUYA.GetActiveEventInfoReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetActiveEventInfoReq";
    }

    public GetActiveEventInfoReq() {
        this.tId = null;
        this.lUid = 0L;
        this.iType = 0;
        this.iOrderType = 0;
        this.iCurGameID = 0;
    }

    public GetActiveEventInfoReq(UserId userId, long j, int i, int i2, int i3) {
        this.tId = null;
        this.lUid = 0L;
        this.iType = 0;
        this.iOrderType = 0;
        this.iCurGameID = 0;
        this.tId = userId;
        this.lUid = j;
        this.iType = i;
        this.iOrderType = i2;
        this.iCurGameID = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetActiveEventInfoReq getActiveEventInfoReq = (GetActiveEventInfoReq) obj;
        return JceUtil.equals(this.tId, getActiveEventInfoReq.tId) && JceUtil.equals(this.lUid, getActiveEventInfoReq.lUid) && JceUtil.equals(this.iType, getActiveEventInfoReq.iType) && JceUtil.equals(this.iOrderType, getActiveEventInfoReq.iOrderType) && JceUtil.equals(this.iCurGameID, getActiveEventInfoReq.iCurGameID);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tId), JceUtil.hashCode(this.lUid), JceUtil.hashCode(this.iType), JceUtil.hashCode(this.iOrderType), JceUtil.hashCode(this.iCurGameID)});
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
        jceOutputStream.write(this.iType, 2);
        jceOutputStream.write(this.iOrderType, 3);
        jceOutputStream.write(this.iCurGameID, 4);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        this.tId = (UserId) jceInputStream.read((JceStruct) cache_tId, 0, false);
        this.lUid = jceInputStream.read(this.lUid, 1, false);
        this.iType = jceInputStream.read(this.iType, 2, false);
        this.iOrderType = jceInputStream.read(this.iOrderType, 3, false);
        this.iCurGameID = jceInputStream.read(this.iCurGameID, 4, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.iType, "iType");
        jceDisplayer.display(this.iOrderType, "iOrderType");
        jceDisplayer.display(this.iCurGameID, "iCurGameID");
    }
}
