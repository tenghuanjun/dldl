package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WSMsgAckRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iRet = 0;

    public String className() {
        return "HUYA.WSMsgAckRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSMsgAckRsp";
    }

    public int getIRet() {
        return this.iRet;
    }

    public void setIRet(int i) {
        this.iRet = i;
    }

    public WSMsgAckRsp() {
        setIRet(0);
    }

    public WSMsgAckRsp(int i) {
        setIRet(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.iRet, ((WSMsgAckRsp) obj).iRet);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iRet)});
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
        jceOutputStream.write(this.iRet, 0);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIRet(jceInputStream.read(this.iRet, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display(this.iRet, "iRet");
    }
}
