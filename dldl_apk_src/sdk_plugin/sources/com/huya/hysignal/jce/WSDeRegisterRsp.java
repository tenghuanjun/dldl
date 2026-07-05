package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WSDeRegisterRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iResCode = 0;

    public String className() {
        return "HUYA.WSDeRegisterRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSDeRegisterRsp";
    }

    public int getIResCode() {
        return this.iResCode;
    }

    public void setIResCode(int i) {
        this.iResCode = i;
    }

    public WSDeRegisterRsp() {
        setIResCode(0);
    }

    public WSDeRegisterRsp(int i) {
        setIResCode(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.iResCode, ((WSDeRegisterRsp) obj).iResCode);
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
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIResCode(jceInputStream.read(this.iResCode, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display(this.iResCode, "iResCode");
    }
}
