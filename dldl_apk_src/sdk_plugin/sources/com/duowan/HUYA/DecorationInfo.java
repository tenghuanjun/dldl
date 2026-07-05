package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class DecorationInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static byte[] cache_vData;
    public int iAppId = 0;
    public int iViewType = 0;
    public byte[] vData = null;

    public String className() {
        return "HUYA.PendantInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PendantInfo";
    }

    public int getIAppId() {
        return this.iAppId;
    }

    public void setIAppId(int i) {
        this.iAppId = i;
    }

    public int getIViewType() {
        return this.iViewType;
    }

    public void setIViewType(int i) {
        this.iViewType = i;
    }

    public byte[] getVData() {
        return this.vData;
    }

    public void setVData(byte[] bArr) {
        this.vData = bArr;
    }

    public DecorationInfo() {
        setIAppId(0);
        setIViewType(this.iViewType);
        setVData(this.vData);
    }

    public DecorationInfo(int i, int i2, byte[] bArr) {
        setIAppId(i);
        setIViewType(i2);
        setVData(bArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DecorationInfo decorationInfo = (DecorationInfo) obj;
        return JceUtil.equals(this.iAppId, decorationInfo.iAppId) && JceUtil.equals(this.iViewType, decorationInfo.iViewType) && JceUtil.equals(this.vData, decorationInfo.vData);
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
        jceOutputStream.write(this.iAppId, 0);
        jceOutputStream.write(this.iViewType, 1);
        byte[] bArr = this.vData;
        if (bArr != null) {
            jceOutputStream.write(bArr, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIAppId(jceInputStream.read(this.iAppId, 0, false));
        setIViewType(jceInputStream.read(this.iViewType, 1, false));
        if (cache_vData == null) {
            cache_vData = new byte[]{0};
        }
        setVData(jceInputStream.read(cache_vData, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iAppId, "iAppId");
        jceDisplayer.display(this.iViewType, "iViewType");
        jceDisplayer.display(this.vData, "vData");
    }
}
