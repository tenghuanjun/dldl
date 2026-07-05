package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class NobleLevelInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iNobleLevel = 0;
    public int iAttrType = 0;

    public String className() {
        return "HUYA.NobleLevelInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.NobleLevelInfo";
    }

    public int getINobleLevel() {
        return this.iNobleLevel;
    }

    public void setINobleLevel(int i) {
        this.iNobleLevel = i;
    }

    public int getIAttrType() {
        return this.iAttrType;
    }

    public void setIAttrType(int i) {
        this.iAttrType = i;
    }

    public NobleLevelInfo() {
        setINobleLevel(0);
        setIAttrType(this.iAttrType);
    }

    public NobleLevelInfo(int i, int i2) {
        setINobleLevel(i);
        setIAttrType(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NobleLevelInfo nobleLevelInfo = (NobleLevelInfo) obj;
        return JceUtil.equals(this.iNobleLevel, nobleLevelInfo.iNobleLevel) && JceUtil.equals(this.iAttrType, nobleLevelInfo.iAttrType);
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
        jceOutputStream.write(this.iNobleLevel, 0);
        jceOutputStream.write(this.iAttrType, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setINobleLevel(jceInputStream.read(this.iNobleLevel, 0, false));
        setIAttrType(jceInputStream.read(this.iAttrType, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iNobleLevel, "iNobleLevel");
        jceDisplayer.display(this.iAttrType, "iAttrType");
    }
}
