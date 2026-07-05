package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class OpenRtmpAddrRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sRtmpAddr = "";
    public String sRtmpKey = "";
    public String sErrorMsg = "";

    public String className() {
        return "HUYA.OpenRtmpAddrRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.OpenRtmpAddrRsp";
    }

    public String getSRtmpAddr() {
        return this.sRtmpAddr;
    }

    public void setSRtmpAddr(String str) {
        this.sRtmpAddr = str;
    }

    public String getSRtmpKey() {
        return this.sRtmpKey;
    }

    public void setSRtmpKey(String str) {
        this.sRtmpKey = str;
    }

    public String getSErrorMsg() {
        return this.sErrorMsg;
    }

    public void setSErrorMsg(String str) {
        this.sErrorMsg = str;
    }

    public OpenRtmpAddrRsp() {
        setSRtmpAddr("");
        setSRtmpKey(this.sRtmpKey);
        setSErrorMsg(this.sErrorMsg);
    }

    public OpenRtmpAddrRsp(String str, String str2, String str3) {
        setSRtmpAddr(str);
        setSRtmpKey(str2);
        setSErrorMsg(str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OpenRtmpAddrRsp openRtmpAddrRsp = (OpenRtmpAddrRsp) obj;
        return JceUtil.equals(this.sRtmpAddr, openRtmpAddrRsp.sRtmpAddr) && JceUtil.equals(this.sRtmpKey, openRtmpAddrRsp.sRtmpKey) && JceUtil.equals(this.sErrorMsg, openRtmpAddrRsp.sErrorMsg);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sRtmpAddr), JceUtil.hashCode(this.sRtmpKey), JceUtil.hashCode(this.sErrorMsg)});
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
        String str = this.sRtmpAddr;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sRtmpKey;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
        String str3 = this.sErrorMsg;
        if (str3 != null) {
            jceOutputStream.write(str3, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSRtmpAddr(jceInputStream.readString(0, false));
        setSRtmpKey(jceInputStream.readString(1, false));
        setSErrorMsg(jceInputStream.readString(2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sRtmpAddr, "sRtmpAddr");
        jceDisplayer.display(this.sRtmpKey, "sRtmpKey");
        jceDisplayer.display(this.sErrorMsg, "sErrorMsg");
    }
}
