package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ChangeLiveInfoReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public UserId tId = null;
    public int iGameId = 0;
    public int iNewGameId = 0;
    public String sLiveDesc = "";

    public String className() {
        return "HUYA.ChangeLiveInfoReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ChangeLiveInfoReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public int getIGameId() {
        return this.iGameId;
    }

    public void setIGameId(int i) {
        this.iGameId = i;
    }

    public int getINewGameId() {
        return this.iNewGameId;
    }

    public void setINewGameId(int i) {
        this.iNewGameId = i;
    }

    public String getSLiveDesc() {
        return this.sLiveDesc;
    }

    public void setSLiveDesc(String str) {
        this.sLiveDesc = str;
    }

    public ChangeLiveInfoReq() {
        setTId(null);
        setIGameId(this.iGameId);
        setINewGameId(this.iNewGameId);
        setSLiveDesc(this.sLiveDesc);
    }

    public ChangeLiveInfoReq(UserId userId, int i, int i2, String str) {
        setTId(userId);
        setIGameId(i);
        setINewGameId(i2);
        setSLiveDesc(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChangeLiveInfoReq changeLiveInfoReq = (ChangeLiveInfoReq) obj;
        return JceUtil.equals(this.tId, changeLiveInfoReq.tId) && JceUtil.equals(this.iGameId, changeLiveInfoReq.iGameId) && JceUtil.equals(this.iNewGameId, changeLiveInfoReq.iNewGameId) && JceUtil.equals(this.sLiveDesc, changeLiveInfoReq.sLiveDesc);
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
        jceOutputStream.write(this.iGameId, 1);
        jceOutputStream.write(this.iNewGameId, 2);
        String str = this.sLiveDesc;
        if (str != null) {
            jceOutputStream.write(str, 3);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setIGameId(jceInputStream.read(this.iGameId, 1, false));
        setINewGameId(jceInputStream.read(this.iNewGameId, 2, false));
        setSLiveDesc(jceInputStream.readString(3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display(this.iNewGameId, "iNewGameId");
        jceDisplayer.display(this.sLiveDesc, "sLiveDesc");
    }
}
