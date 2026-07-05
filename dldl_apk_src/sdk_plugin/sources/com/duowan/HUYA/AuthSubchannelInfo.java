package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class AuthSubchannelInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lSubchannelId = 0;
    public String sSubchannelName = "";

    public String className() {
        return "HUYA.AuthSubchannelInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.AuthSubchannelInfo";
    }

    public long getLSubchannelId() {
        return this.lSubchannelId;
    }

    public void setLSubchannelId(long j) {
        this.lSubchannelId = j;
    }

    public String getSSubchannelName() {
        return this.sSubchannelName;
    }

    public void setSSubchannelName(String str) {
        this.sSubchannelName = str;
    }

    public AuthSubchannelInfo() {
        setLSubchannelId(0L);
        setSSubchannelName(this.sSubchannelName);
    }

    public AuthSubchannelInfo(long j, String str) {
        setLSubchannelId(j);
        setSSubchannelName(str);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        AuthSubchannelInfo authSubchannelInfo = (AuthSubchannelInfo) obj;
        return JceUtil.equals(this.lSubchannelId, authSubchannelInfo.lSubchannelId) && JceUtil.equals(this.sSubchannelName, authSubchannelInfo.sSubchannelName);
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
        jceOutputStream.write(this.lSubchannelId, 0);
        String str = this.sSubchannelName;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLSubchannelId(jceInputStream.read(this.lSubchannelId, 0, false));
        setSSubchannelName(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lSubchannelId, "lSubchannelId");
        jceDisplayer.display(this.sSubchannelName, "sSubchannelName");
    }
}
