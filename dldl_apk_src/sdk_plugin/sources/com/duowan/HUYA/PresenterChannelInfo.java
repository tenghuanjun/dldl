package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class PresenterChannelInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lYYId = 0;
    public long lTid = 0;
    public long lSid = 0;
    public int iSourceType = 0;
    public int iScreenType = 0;
    public long lUid = 0;
    public int iGameId = 0;

    public String className() {
        return "HUYA.PresenterChannelInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PresenterChannelInfo";
    }

    public long getLYYId() {
        return this.lYYId;
    }

    public void setLYYId(long j) {
        this.lYYId = j;
    }

    public long getLTid() {
        return this.lTid;
    }

    public void setLTid(long j) {
        this.lTid = j;
    }

    public long getLSid() {
        return this.lSid;
    }

    public void setLSid(long j) {
        this.lSid = j;
    }

    public int getISourceType() {
        return this.iSourceType;
    }

    public void setISourceType(int i) {
        this.iSourceType = i;
    }

    public int getIScreenType() {
        return this.iScreenType;
    }

    public void setIScreenType(int i) {
        this.iScreenType = i;
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public int getIGameId() {
        return this.iGameId;
    }

    public void setIGameId(int i) {
        this.iGameId = i;
    }

    public PresenterChannelInfo() {
        setLYYId(0L);
        setLTid(this.lTid);
        setLSid(this.lSid);
        setISourceType(this.iSourceType);
        setIScreenType(this.iScreenType);
        setLUid(this.lUid);
        setIGameId(this.iGameId);
    }

    public PresenterChannelInfo(long j, long j2, long j3, int i, int i2, long j4, int i3) {
        setLYYId(j);
        setLTid(j2);
        setLSid(j3);
        setISourceType(i);
        setIScreenType(i2);
        setLUid(j4);
        setIGameId(i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PresenterChannelInfo presenterChannelInfo = (PresenterChannelInfo) obj;
        return JceUtil.equals(this.lYYId, presenterChannelInfo.lYYId) && JceUtil.equals(this.lTid, presenterChannelInfo.lTid) && JceUtil.equals(this.lSid, presenterChannelInfo.lSid) && JceUtil.equals(this.iSourceType, presenterChannelInfo.iSourceType) && JceUtil.equals(this.iScreenType, presenterChannelInfo.iScreenType) && JceUtil.equals(this.lUid, presenterChannelInfo.lUid) && JceUtil.equals(this.iGameId, presenterChannelInfo.iGameId);
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
        jceOutputStream.write(this.lYYId, 0);
        jceOutputStream.write(this.lTid, 1);
        jceOutputStream.write(this.lSid, 3);
        jceOutputStream.write(this.iSourceType, 4);
        jceOutputStream.write(this.iScreenType, 5);
        jceOutputStream.write(this.lUid, 6);
        jceOutputStream.write(this.iGameId, 7);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLYYId(jceInputStream.read(this.lYYId, 0, false));
        setLTid(jceInputStream.read(this.lTid, 1, false));
        setLSid(jceInputStream.read(this.lSid, 3, false));
        setISourceType(jceInputStream.read(this.iSourceType, 4, false));
        setIScreenType(jceInputStream.read(this.iScreenType, 5, false));
        setLUid(jceInputStream.read(this.lUid, 6, false));
        setIGameId(jceInputStream.read(this.iGameId, 7, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lYYId, "lYYId");
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSid, "lSid");
        jceDisplayer.display(this.iSourceType, "iSourceType");
        jceDisplayer.display(this.iScreenType, "iScreenType");
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.iGameId, "iGameId");
    }
}
