package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SuperFansInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lSFExpiredTS = 0;
    public int iSFFlag = 0;

    public String className() {
        return "HUYA.SuperFansInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SuperFansInfo";
    }

    public long getLSFExpiredTS() {
        return this.lSFExpiredTS;
    }

    public void setLSFExpiredTS(long j) {
        this.lSFExpiredTS = j;
    }

    public int getISFFlag() {
        return this.iSFFlag;
    }

    public void setISFFlag(int i) {
        this.iSFFlag = i;
    }

    public SuperFansInfo() {
        setLSFExpiredTS(0L);
        setISFFlag(this.iSFFlag);
    }

    public SuperFansInfo(long j, int i) {
        setLSFExpiredTS(j);
        setISFFlag(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SuperFansInfo superFansInfo = (SuperFansInfo) obj;
        return JceUtil.equals(this.lSFExpiredTS, superFansInfo.lSFExpiredTS) && JceUtil.equals(this.iSFFlag, superFansInfo.iSFFlag);
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
        jceOutputStream.write(this.lSFExpiredTS, 0);
        jceOutputStream.write(this.iSFFlag, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLSFExpiredTS(jceInputStream.read(this.lSFExpiredTS, 0, false));
        setISFFlag(jceInputStream.read(this.iSFFlag, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lSFExpiredTS, "lSFExpiredTS");
        jceDisplayer.display(this.iSFFlag, "iSFFlag");
    }
}
