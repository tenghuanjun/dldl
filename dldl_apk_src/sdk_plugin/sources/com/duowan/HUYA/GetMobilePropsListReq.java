package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetMobilePropsListReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tUserId;
    public UserId tUserId = null;
    public String sMd5 = "";
    public int iTemplateType = 64;
    public String sVersion = "";
    public int iAppId = 0;
    public long lPresenterUid = 0;
    public long lSid = 0;
    public long lSubSid = 0;
    public int iGameId = 0;

    public String className() {
        return "HUYA.GetMobilePropsListReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetMobilePropsListReq";
    }

    public UserId getTUserId() {
        return this.tUserId;
    }

    public void setTUserId(UserId userId) {
        this.tUserId = userId;
    }

    public String getSMd5() {
        return this.sMd5;
    }

    public void setSMd5(String str) {
        this.sMd5 = str;
    }

    public int getITemplateType() {
        return this.iTemplateType;
    }

    public void setITemplateType(int i) {
        this.iTemplateType = i;
    }

    public String getSVersion() {
        return this.sVersion;
    }

    public void setSVersion(String str) {
        this.sVersion = str;
    }

    public int getIAppId() {
        return this.iAppId;
    }

    public void setIAppId(int i) {
        this.iAppId = i;
    }

    public long getLPresenterUid() {
        return this.lPresenterUid;
    }

    public void setLPresenterUid(long j) {
        this.lPresenterUid = j;
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

    public int getIGameId() {
        return this.iGameId;
    }

    public void setIGameId(int i) {
        this.iGameId = i;
    }

    public GetMobilePropsListReq() {
        setTUserId(null);
        setSMd5(this.sMd5);
        setITemplateType(this.iTemplateType);
        setSVersion(this.sVersion);
        setIAppId(this.iAppId);
        setLPresenterUid(this.lPresenterUid);
        setLSid(this.lSid);
        setLSubSid(this.lSubSid);
        setIGameId(this.iGameId);
    }

    public GetMobilePropsListReq(UserId userId, String str, int i, String str2, int i2, long j, long j2, long j3, int i3) {
        setTUserId(userId);
        setSMd5(str);
        setITemplateType(i);
        setSVersion(str2);
        setIAppId(i2);
        setLPresenterUid(j);
        setLSid(j2);
        setLSubSid(j3);
        setIGameId(i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetMobilePropsListReq getMobilePropsListReq = (GetMobilePropsListReq) obj;
        return JceUtil.equals(this.tUserId, getMobilePropsListReq.tUserId) && JceUtil.equals(this.sMd5, getMobilePropsListReq.sMd5) && JceUtil.equals(this.iTemplateType, getMobilePropsListReq.iTemplateType) && JceUtil.equals(this.sVersion, getMobilePropsListReq.sVersion) && JceUtil.equals(this.iAppId, getMobilePropsListReq.iAppId) && JceUtil.equals(this.lPresenterUid, getMobilePropsListReq.lPresenterUid) && JceUtil.equals(this.lSid, getMobilePropsListReq.lSid) && JceUtil.equals(this.lSubSid, getMobilePropsListReq.lSubSid) && JceUtil.equals(this.iGameId, getMobilePropsListReq.iGameId);
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
        UserId userId = this.tUserId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 1);
        }
        String str = this.sMd5;
        if (str != null) {
            jceOutputStream.write(str, 2);
        }
        jceOutputStream.write(this.iTemplateType, 3);
        String str2 = this.sVersion;
        if (str2 != null) {
            jceOutputStream.write(str2, 4);
        }
        jceOutputStream.write(this.iAppId, 5);
        jceOutputStream.write(this.lPresenterUid, 6);
        jceOutputStream.write(this.lSid, 7);
        jceOutputStream.write(this.lSubSid, 8);
        jceOutputStream.write(this.iGameId, 9);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tUserId == null) {
            cache_tUserId = new UserId();
        }
        setTUserId((UserId) jceInputStream.read((JceStruct) cache_tUserId, 1, false));
        setSMd5(jceInputStream.readString(2, false));
        setITemplateType(jceInputStream.read(this.iTemplateType, 3, false));
        setSVersion(jceInputStream.readString(4, false));
        setIAppId(jceInputStream.read(this.iAppId, 5, false));
        setLPresenterUid(jceInputStream.read(this.lPresenterUid, 6, false));
        setLSid(jceInputStream.read(this.lSid, 7, false));
        setLSubSid(jceInputStream.read(this.lSubSid, 8, false));
        setIGameId(jceInputStream.read(this.iGameId, 9, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tUserId, "tUserId");
        jceDisplayer.display(this.sMd5, "sMd5");
        jceDisplayer.display(this.iTemplateType, "iTemplateType");
        jceDisplayer.display(this.sVersion, "sVersion");
        jceDisplayer.display(this.iAppId, "iAppId");
        jceDisplayer.display(this.lPresenterUid, "lPresenterUid");
        jceDisplayer.display(this.lSid, "lSid");
        jceDisplayer.display(this.lSubSid, "lSubSid");
        jceDisplayer.display(this.iGameId, "iGameId");
    }
}
