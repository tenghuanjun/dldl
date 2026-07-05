package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WSDeRegisterReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iDeRegisterType = 0;

    public String className() {
        return "HUYA.WSDeRegisterReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSDeRegisterReq";
    }

    public int getIDeRegisterType() {
        return this.iDeRegisterType;
    }

    public void setIDeRegisterType(int i) {
        this.iDeRegisterType = i;
    }

    public WSDeRegisterReq() {
        setIDeRegisterType(0);
    }

    public WSDeRegisterReq(int i) {
        setIDeRegisterType(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.iDeRegisterType, ((WSDeRegisterReq) obj).iDeRegisterType);
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
        jceOutputStream.write(this.iDeRegisterType, 0);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIDeRegisterType(jceInputStream.read(this.iDeRegisterType, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display(this.iDeRegisterType, "iDeRegisterType");
    }
}
