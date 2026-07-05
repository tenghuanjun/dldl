package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WSLaunchRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sGuid = "";
    public String sClientIp = "";

    public String className() {
        return "HUYA.WSLaunchRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSLaunchRsp";
    }

    public String getSGuid() {
        return this.sGuid;
    }

    public void setSGuid(String str) {
        this.sGuid = str;
    }

    public String getSClientIp() {
        return this.sClientIp;
    }

    public void setSClientIp(String str) {
        this.sClientIp = str;
    }

    public WSLaunchRsp() {
        setSGuid("");
        setSClientIp(this.sClientIp);
    }

    public WSLaunchRsp(String str, String str2) {
        setSGuid(str);
        setSClientIp(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSLaunchRsp wSLaunchRsp = (WSLaunchRsp) obj;
        return JceUtil.equals(this.sGuid, wSLaunchRsp.sGuid) && JceUtil.equals(this.sClientIp, wSLaunchRsp.sClientIp);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sGuid), JceUtil.hashCode(this.sClientIp)});
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
        String str = this.sGuid;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sClientIp;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSGuid(jceInputStream.readString(0, false));
        setSClientIp(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sGuid, "sGuid");
        jceDisplayer.display(this.sClientIp, "sClientIp");
    }
}
