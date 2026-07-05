package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetPresenterVeriInfoRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iVerified = 0;
    public String sVerifiedAvatar = "";
    public String sPresenterName = "";
    public String sVerifyUrl = "";
    public int iIsForceVerify = 0;

    public String className() {
        return "HUYA.GetPresenterVeriInfoRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetPresenterVeriInfoRsp";
    }

    public int getIVerified() {
        return this.iVerified;
    }

    public void setIVerified(int i) {
        this.iVerified = i;
    }

    public String getSVerifiedAvatar() {
        return this.sVerifiedAvatar;
    }

    public void setSVerifiedAvatar(String str) {
        this.sVerifiedAvatar = str;
    }

    public String getSPresenterName() {
        return this.sPresenterName;
    }

    public void setSPresenterName(String str) {
        this.sPresenterName = str;
    }

    public String getSVerifyUrl() {
        return this.sVerifyUrl;
    }

    public void setSVerifyUrl(String str) {
        this.sVerifyUrl = str;
    }

    public int getIIsForceVerify() {
        return this.iIsForceVerify;
    }

    public void setIIsForceVerify(int i) {
        this.iIsForceVerify = i;
    }

    public GetPresenterVeriInfoRsp() {
        setIVerified(0);
        setSVerifiedAvatar(this.sVerifiedAvatar);
        setSPresenterName(this.sPresenterName);
        setSVerifyUrl(this.sVerifyUrl);
        setIIsForceVerify(this.iIsForceVerify);
    }

    public GetPresenterVeriInfoRsp(int i, String str, String str2, String str3, int i2) {
        setIVerified(i);
        setSVerifiedAvatar(str);
        setSPresenterName(str2);
        setSVerifyUrl(str3);
        setIIsForceVerify(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetPresenterVeriInfoRsp getPresenterVeriInfoRsp = (GetPresenterVeriInfoRsp) obj;
        return JceUtil.equals(this.iVerified, getPresenterVeriInfoRsp.iVerified) && JceUtil.equals(this.sVerifiedAvatar, getPresenterVeriInfoRsp.sVerifiedAvatar) && JceUtil.equals(this.sPresenterName, getPresenterVeriInfoRsp.sPresenterName) && JceUtil.equals(this.sVerifyUrl, getPresenterVeriInfoRsp.sVerifyUrl) && JceUtil.equals(this.iIsForceVerify, getPresenterVeriInfoRsp.iIsForceVerify);
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
        jceOutputStream.write(this.iVerified, 0);
        String str = this.sVerifiedAvatar;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sPresenterName;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        String str3 = this.sVerifyUrl;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
        jceOutputStream.write(this.iIsForceVerify, 4);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIVerified(jceInputStream.read(this.iVerified, 0, false));
        setSVerifiedAvatar(jceInputStream.readString(1, false));
        setSPresenterName(jceInputStream.readString(2, false));
        setSVerifyUrl(jceInputStream.readString(3, false));
        setIIsForceVerify(jceInputStream.read(this.iIsForceVerify, 4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iVerified, "iVerified");
        jceDisplayer.display(this.sVerifiedAvatar, "sVerifiedAvatar");
        jceDisplayer.display(this.sPresenterName, "sPresenterName");
        jceDisplayer.display(this.sVerifyUrl, "sVerifyUrl");
        jceDisplayer.display(this.iIsForceVerify, "iIsForceVerify");
    }
}
