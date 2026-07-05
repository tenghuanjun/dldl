package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CheckNameValidRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iCode = 0;
    public String sErrMsg = "";

    public String className() {
        return "HUYA.CheckNameValidRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.CheckNameValidRsp";
    }

    public int getICode() {
        return this.iCode;
    }

    public void setICode(int i) {
        this.iCode = i;
    }

    public String getSErrMsg() {
        return this.sErrMsg;
    }

    public void setSErrMsg(String str) {
        this.sErrMsg = str;
    }

    public CheckNameValidRsp() {
        setICode(0);
        setSErrMsg(this.sErrMsg);
    }

    public CheckNameValidRsp(int i, String str) {
        setICode(i);
        setSErrMsg(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CheckNameValidRsp checkNameValidRsp = (CheckNameValidRsp) obj;
        return JceUtil.equals(this.iCode, checkNameValidRsp.iCode) && JceUtil.equals(this.sErrMsg, checkNameValidRsp.sErrMsg);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iCode), JceUtil.hashCode(this.sErrMsg)});
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
        jceOutputStream.write(this.iCode, 0);
        String str = this.sErrMsg;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setICode(jceInputStream.read(this.iCode, 0, false));
        setSErrMsg(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iCode, "iCode");
        jceDisplayer.display(this.sErrMsg, "sErrMsg");
    }
}
