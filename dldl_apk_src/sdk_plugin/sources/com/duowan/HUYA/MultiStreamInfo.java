package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MultiStreamInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sDisplayName = "";
    public int iBitRate = 0;
    public int iCodecType = 0;
    public int iCompatibleFlag = 0;
    public int iHEVCBitRate = -1;

    public String className() {
        return "HUYA.MultiStreamInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.MultiStreamInfo";
    }

    public String getSDisplayName() {
        return this.sDisplayName;
    }

    public void setSDisplayName(String str) {
        this.sDisplayName = str;
    }

    public int getIBitRate() {
        return this.iBitRate;
    }

    public void setIBitRate(int i) {
        this.iBitRate = i;
    }

    public int getICodecType() {
        return this.iCodecType;
    }

    public void setICodecType(int i) {
        this.iCodecType = i;
    }

    public int getICompatibleFlag() {
        return this.iCompatibleFlag;
    }

    public void setICompatibleFlag(int i) {
        this.iCompatibleFlag = i;
    }

    public int getIHEVCBitRate() {
        return this.iHEVCBitRate;
    }

    public void setIHEVCBitRate(int i) {
        this.iHEVCBitRate = i;
    }

    public MultiStreamInfo() {
        setSDisplayName("");
        setIBitRate(this.iBitRate);
        setICodecType(this.iCodecType);
        setICompatibleFlag(this.iCompatibleFlag);
        setIHEVCBitRate(this.iHEVCBitRate);
    }

    public MultiStreamInfo(String str, int i, int i2, int i3, int i4) {
        setSDisplayName(str);
        setIBitRate(i);
        setICodecType(i2);
        setICompatibleFlag(i3);
        setIHEVCBitRate(i4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MultiStreamInfo multiStreamInfo = (MultiStreamInfo) obj;
        return JceUtil.equals(this.sDisplayName, multiStreamInfo.sDisplayName) && JceUtil.equals(this.iBitRate, multiStreamInfo.iBitRate) && JceUtil.equals(this.iCodecType, multiStreamInfo.iCodecType) && JceUtil.equals(this.iCompatibleFlag, multiStreamInfo.iCompatibleFlag) && JceUtil.equals(this.iHEVCBitRate, multiStreamInfo.iHEVCBitRate);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sDisplayName), JceUtil.hashCode(this.iBitRate), JceUtil.hashCode(this.iCodecType), JceUtil.hashCode(this.iCompatibleFlag), JceUtil.hashCode(this.iHEVCBitRate)});
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
        String str = this.sDisplayName;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        jceOutputStream.write(this.iBitRate, 1);
        jceOutputStream.write(this.iCodecType, 2);
        jceOutputStream.write(this.iCompatibleFlag, 3);
        jceOutputStream.write(this.iHEVCBitRate, 4);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSDisplayName(jceInputStream.readString(0, false));
        setIBitRate(jceInputStream.read(this.iBitRate, 1, false));
        setICodecType(jceInputStream.read(this.iCodecType, 2, false));
        setICompatibleFlag(jceInputStream.read(this.iCompatibleFlag, 3, false));
        setIHEVCBitRate(jceInputStream.read(this.iHEVCBitRate, 4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sDisplayName, "sDisplayName");
        jceDisplayer.display(this.iBitRate, "iBitRate");
        jceDisplayer.display(this.iCodecType, "iCodecType");
        jceDisplayer.display(this.iCompatibleFlag, "iCompatibleFlag");
        jceDisplayer.display(this.iHEVCBitRate, "iHEVCBitRate");
    }
}
