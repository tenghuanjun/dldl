package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SwitchStatusRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iRetCode;
    public String sRetDesc;

    public String className() {
        return "HUYA.SwitchStatusRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SwitchStatusRsp";
    }

    public SwitchStatusRsp() {
        this.iRetCode = 0;
        this.sRetDesc = "";
    }

    public SwitchStatusRsp(int i, String str) {
        this.iRetCode = 0;
        this.sRetDesc = "";
        this.iRetCode = i;
        this.sRetDesc = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SwitchStatusRsp switchStatusRsp = (SwitchStatusRsp) obj;
        return JceUtil.equals(this.iRetCode, switchStatusRsp.iRetCode) && JceUtil.equals(this.sRetDesc, switchStatusRsp.sRetDesc);
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
        jceOutputStream.write(this.iRetCode, 0);
        String str = this.sRetDesc;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        this.iRetCode = jceInputStream.read(this.iRetCode, 0, false);
        this.sRetDesc = jceInputStream.readString(1, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iRetCode, "iRetCode");
        jceDisplayer.display(this.sRetDesc, "sRetDesc");
    }
}
