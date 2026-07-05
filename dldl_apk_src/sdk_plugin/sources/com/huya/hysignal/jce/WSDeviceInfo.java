package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WSDeviceInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sIMEI = "";
    public String sAPN = "";
    public String sNetType = "";
    public String sDeviceId = "";
    public String sMId = "";

    public String className() {
        return "HUYA.WSDeviceInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSDeviceInfo";
    }

    public String getSIMEI() {
        return this.sIMEI;
    }

    public void setSIMEI(String str) {
        this.sIMEI = str;
    }

    public String getSAPN() {
        return this.sAPN;
    }

    public void setSAPN(String str) {
        this.sAPN = str;
    }

    public String getSNetType() {
        return this.sNetType;
    }

    public void setSNetType(String str) {
        this.sNetType = str;
    }

    public String getSDeviceId() {
        return this.sDeviceId;
    }

    public void setSDeviceId(String str) {
        this.sDeviceId = str;
    }

    public String getSMId() {
        return this.sMId;
    }

    public void setSMId(String str) {
        this.sMId = str;
    }

    public WSDeviceInfo() {
        setSIMEI("");
        setSAPN(this.sAPN);
        setSNetType(this.sNetType);
        setSDeviceId(this.sDeviceId);
        setSMId(this.sMId);
    }

    public WSDeviceInfo(String str, String str2, String str3, String str4, String str5) {
        setSIMEI(str);
        setSAPN(str2);
        setSNetType(str3);
        setSDeviceId(str4);
        setSMId(str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSDeviceInfo wSDeviceInfo = (WSDeviceInfo) obj;
        return JceUtil.equals(this.sIMEI, wSDeviceInfo.sIMEI) && JceUtil.equals(this.sAPN, wSDeviceInfo.sAPN) && JceUtil.equals(this.sNetType, wSDeviceInfo.sNetType) && JceUtil.equals(this.sDeviceId, wSDeviceInfo.sDeviceId) && JceUtil.equals(this.sMId, wSDeviceInfo.sMId);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sIMEI), JceUtil.hashCode(this.sAPN), JceUtil.hashCode(this.sNetType), JceUtil.hashCode(this.sDeviceId), JceUtil.hashCode(this.sMId)});
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
        String str = this.sIMEI;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sAPN;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
        String str3 = this.sNetType;
        if (str3 != null) {
            jceOutputStream.write(str3, 2);
        }
        String str4 = this.sDeviceId;
        if (str4 != null) {
            jceOutputStream.write(str4, 3);
        }
        String str5 = this.sMId;
        if (str5 != null) {
            jceOutputStream.write(str5, 4);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSIMEI(jceInputStream.readString(0, false));
        setSAPN(jceInputStream.readString(1, false));
        setSNetType(jceInputStream.readString(2, false));
        setSDeviceId(jceInputStream.readString(3, false));
        setSMId(jceInputStream.readString(4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sIMEI, "sIMEI");
        jceDisplayer.display(this.sAPN, "sAPN");
        jceDisplayer.display(this.sNetType, "sNetType");
        jceDisplayer.display(this.sDeviceId, "sDeviceId");
        jceDisplayer.display(this.sMId, "sMId");
    }
}
