package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ZhuShouLiveingGameListReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public UserId tId = null;
    public String sMd5 = "";

    public String className() {
        return "HUYA.ZhuShouLiveingGameListReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ZhuShouLiveingGameListReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public String getSMd5() {
        return this.sMd5;
    }

    public void setSMd5(String str) {
        this.sMd5 = str;
    }

    public ZhuShouLiveingGameListReq() {
        setTId(null);
        setSMd5(this.sMd5);
    }

    public ZhuShouLiveingGameListReq(UserId userId, String str) {
        setTId(userId);
        setSMd5(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ZhuShouLiveingGameListReq zhuShouLiveingGameListReq = (ZhuShouLiveingGameListReq) obj;
        return JceUtil.equals(this.tId, zhuShouLiveingGameListReq.tId) && JceUtil.equals(this.sMd5, zhuShouLiveingGameListReq.sMd5);
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
        String str = this.sMd5;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setSMd5(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.sMd5, "sMd5");
    }
}
