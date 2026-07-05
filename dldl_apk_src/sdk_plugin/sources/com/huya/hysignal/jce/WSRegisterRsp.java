package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WSRegisterRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iResCode = 0;
    public long lRequestId = 0;
    public String sMessage = "";
    public String sBCConnHost = "";

    public String className() {
        return "HUYA.WSRegisterRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSRegisterRsp";
    }

    public int getIResCode() {
        return this.iResCode;
    }

    public void setIResCode(int i) {
        this.iResCode = i;
    }

    public long getLRequestId() {
        return this.lRequestId;
    }

    public void setLRequestId(long j) {
        this.lRequestId = j;
    }

    public String getSMessage() {
        return this.sMessage;
    }

    public void setSMessage(String str) {
        this.sMessage = str;
    }

    public String getSBCConnHost() {
        return this.sBCConnHost;
    }

    public void setSBCConnHost(String str) {
        this.sBCConnHost = str;
    }

    public WSRegisterRsp() {
        setIResCode(0);
        setLRequestId(this.lRequestId);
        setSMessage(this.sMessage);
        setSBCConnHost(this.sBCConnHost);
    }

    public WSRegisterRsp(int i, long j, String str, String str2) {
        setIResCode(i);
        setLRequestId(j);
        setSMessage(str);
        setSBCConnHost(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSRegisterRsp wSRegisterRsp = (WSRegisterRsp) obj;
        return JceUtil.equals(this.iResCode, wSRegisterRsp.iResCode) && JceUtil.equals(this.lRequestId, wSRegisterRsp.lRequestId) && JceUtil.equals(this.sMessage, wSRegisterRsp.sMessage) && JceUtil.equals(this.sBCConnHost, wSRegisterRsp.sBCConnHost);
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
        jceOutputStream.write(this.iResCode, 0);
        jceOutputStream.write(this.lRequestId, 1);
        String str = this.sMessage;
        if (str != null) {
            jceOutputStream.write(str, 2);
        }
        String str2 = this.sBCConnHost;
        if (str2 != null) {
            jceOutputStream.write(str2, 3);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIResCode(jceInputStream.read(this.iResCode, 0, false));
        setLRequestId(jceInputStream.read(this.lRequestId, 1, false));
        setSMessage(jceInputStream.readString(2, false));
        setSBCConnHost(jceInputStream.readString(3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iResCode, "iResCode");
        jceDisplayer.display(this.lRequestId, "lRequestId");
        jceDisplayer.display(this.sMessage, "sMessage");
        jceDisplayer.display(this.sBCConnHost, "sBCConnHost");
    }
}
