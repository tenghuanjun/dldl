package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WSUserInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lUid = 0;
    public boolean bAnonymous = true;
    public String sGuid = "";
    public String sToken = "";
    public long lTid = 0;
    public long lSid = 0;
    public long lGroupId = 0;
    public long lGroupType = 0;
    public String sAppId = "";

    public String className() {
        return "HUYA.WSUserInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSUserInfo";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public boolean getBAnonymous() {
        return this.bAnonymous;
    }

    public void setBAnonymous(boolean z) {
        this.bAnonymous = z;
    }

    public String getSGuid() {
        return this.sGuid;
    }

    public void setSGuid(String str) {
        this.sGuid = str;
    }

    public String getSToken() {
        return this.sToken;
    }

    public void setSToken(String str) {
        this.sToken = str;
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

    public long getLGroupId() {
        return this.lGroupId;
    }

    public void setLGroupId(long j) {
        this.lGroupId = j;
    }

    public long getLGroupType() {
        return this.lGroupType;
    }

    public void setLGroupType(long j) {
        this.lGroupType = j;
    }

    public String getSAppId() {
        return this.sAppId;
    }

    public void setSAppId(String str) {
        this.sAppId = str;
    }

    public WSUserInfo() {
        setLUid(0L);
        setBAnonymous(this.bAnonymous);
        setSGuid(this.sGuid);
        setSToken(this.sToken);
        setLTid(this.lTid);
        setLSid(this.lSid);
        setLGroupId(this.lGroupId);
        setLGroupType(this.lGroupType);
        setSAppId(this.sAppId);
    }

    public WSUserInfo(long j, boolean z, String str, String str2, long j2, long j3, long j4, long j5, String str3) {
        setLUid(j);
        setBAnonymous(z);
        setSGuid(str);
        setSToken(str2);
        setLTid(j2);
        setLSid(j3);
        setLGroupId(j4);
        setLGroupType(j5);
        setSAppId(str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSUserInfo wSUserInfo = (WSUserInfo) obj;
        return JceUtil.equals(this.lUid, wSUserInfo.lUid) && JceUtil.equals(this.bAnonymous, wSUserInfo.bAnonymous) && JceUtil.equals(this.sGuid, wSUserInfo.sGuid) && JceUtil.equals(this.sToken, wSUserInfo.sToken) && JceUtil.equals(this.lTid, wSUserInfo.lTid) && JceUtil.equals(this.lSid, wSUserInfo.lSid) && JceUtil.equals(this.lGroupId, wSUserInfo.lGroupId) && JceUtil.equals(this.lGroupType, wSUserInfo.lGroupType) && JceUtil.equals(this.sAppId, wSUserInfo.sAppId);
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
        jceOutputStream.write(this.lUid, 0);
        jceOutputStream.write(this.bAnonymous, 1);
        String str = this.sGuid;
        if (str != null) {
            jceOutputStream.write(str, 2);
        }
        String str2 = this.sToken;
        if (str2 != null) {
            jceOutputStream.write(str2, 3);
        }
        jceOutputStream.write(this.lTid, 4);
        jceOutputStream.write(this.lSid, 5);
        jceOutputStream.write(this.lGroupId, 6);
        jceOutputStream.write(this.lGroupType, 7);
        String str3 = this.sAppId;
        if (str3 != null) {
            jceOutputStream.write(str3, 8);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setBAnonymous(jceInputStream.read(this.bAnonymous, 1, false));
        setSGuid(jceInputStream.readString(2, false));
        setSToken(jceInputStream.readString(3, false));
        setLTid(jceInputStream.read(this.lTid, 4, false));
        setLSid(jceInputStream.read(this.lSid, 5, false));
        setLGroupId(jceInputStream.read(this.lGroupId, 6, false));
        setLGroupType(jceInputStream.read(this.lGroupType, 7, false));
        setSAppId(jceInputStream.readString(8, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.bAnonymous, "bAnonymous");
        jceDisplayer.display(this.sGuid, "sGuid");
        jceDisplayer.display(this.sToken, "sToken");
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSid, "lSid");
        jceDisplayer.display(this.lGroupId, "lGroupId");
        jceDisplayer.display(this.lGroupType, "lGroupType");
        jceDisplayer.display(this.sAppId, "sAppId");
    }
}
