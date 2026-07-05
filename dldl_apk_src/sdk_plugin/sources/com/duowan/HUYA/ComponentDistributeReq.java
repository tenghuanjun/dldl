package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ComponentDistributeReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public int iTerminalType = 0;
    public String sVer = "";
    public UserId tId = null;
    public long lGamid = 0;

    public String className() {
        return "HUYA.ComponentDistributeReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ComponentDistributeReq";
    }

    public int getITerminalType() {
        return this.iTerminalType;
    }

    public void setITerminalType(int i) {
        this.iTerminalType = i;
    }

    public String getSVer() {
        return this.sVer;
    }

    public void setSVer(String str) {
        this.sVer = str;
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public long getLGamid() {
        return this.lGamid;
    }

    public void setLGamid(long j) {
        this.lGamid = j;
    }

    public ComponentDistributeReq() {
        setITerminalType(0);
        setSVer(this.sVer);
        setTId(this.tId);
        setLGamid(this.lGamid);
    }

    public ComponentDistributeReq(int i, String str, UserId userId, long j) {
        setITerminalType(i);
        setSVer(str);
        setTId(userId);
        setLGamid(j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ComponentDistributeReq componentDistributeReq = (ComponentDistributeReq) obj;
        return JceUtil.equals(this.iTerminalType, componentDistributeReq.iTerminalType) && JceUtil.equals(this.sVer, componentDistributeReq.sVer) && JceUtil.equals(this.tId, componentDistributeReq.tId) && JceUtil.equals(this.lGamid, componentDistributeReq.lGamid);
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
        jceOutputStream.write(this.iTerminalType, 0);
        String str = this.sVer;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        UserId userId = this.tId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 2);
        }
        jceOutputStream.write(this.lGamid, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setITerminalType(jceInputStream.read(this.iTerminalType, 0, false));
        setSVer(jceInputStream.readString(1, false));
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 2, false));
        setLGamid(jceInputStream.read(this.lGamid, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iTerminalType, "iTerminalType");
        jceDisplayer.display(this.sVer, "sVer");
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.lGamid, "lGamid");
    }
}
