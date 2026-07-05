package com.duowan.monitor.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class UserId extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lUid = 0;
    public String sGuid = "";
    public String sToken = "";
    public String sHuYaUA = "";

    public String className() {
        return "monitor.jce.UserId";
    }

    public String fullClassName() {
        return "com.duowan.monitor.jce.UserId";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
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

    public String getSHuYaUA() {
        return this.sHuYaUA;
    }

    public void setSHuYaUA(String str) {
        this.sHuYaUA = str;
    }

    public UserId() {
        setLUid(0L);
        setSGuid(this.sGuid);
        setSToken(this.sToken);
        setSHuYaUA(this.sHuYaUA);
    }

    public UserId(long j, String str, String str2, String str3) {
        setLUid(j);
        setSGuid(str);
        setSToken(str2);
        setSHuYaUA(str3);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        UserId userId = (UserId) obj;
        return JceUtil.equals(this.lUid, userId.lUid) && JceUtil.equals(this.sGuid, userId.sGuid) && JceUtil.equals(this.sToken, userId.sToken) && JceUtil.equals(this.sHuYaUA, userId.sHuYaUA);
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
        String str = this.sGuid;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sToken;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        String str3 = this.sHuYaUA;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setSGuid(jceInputStream.readString(1, false));
        setSToken(jceInputStream.readString(2, false));
        setSHuYaUA(jceInputStream.readString(3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.sGuid, "sGuid");
        jceDisplayer.display(this.sToken, "sToken");
        jceDisplayer.display(this.sHuYaUA, "sHuYaUA");
    }
}
