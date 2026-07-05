package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WSVerifyHuyaTokenRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iValidate = 0;

    public String className() {
        return "HUYA.WSVerifyHuyaTokenRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSVerifyHuyaTokenRsp";
    }

    public int getIValidate() {
        return this.iValidate;
    }

    public void setIValidate(int i) {
        this.iValidate = i;
    }

    public WSVerifyHuyaTokenRsp() {
        setIValidate(0);
    }

    public WSVerifyHuyaTokenRsp(int i) {
        setIValidate(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.iValidate, ((WSVerifyHuyaTokenRsp) obj).iValidate);
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
        jceOutputStream.write(this.iValidate, 0);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIValidate(jceInputStream.read(this.iValidate, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display(this.iValidate, "iValidate");
    }
}
