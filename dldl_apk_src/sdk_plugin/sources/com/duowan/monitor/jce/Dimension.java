package com.duowan.monitor.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class Dimension extends JceStruct implements Comparable<Dimension>, Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sName = "";
    public String sValue = "";

    public String className() {
        return "monitor.jce.Dimension";
    }

    public String fullClassName() {
        return "com.duowan.monitor.jce.Dimension";
    }

    public String getSName() {
        return this.sName;
    }

    public void setSName(String str) {
        this.sName = str;
    }

    public String getSValue() {
        return this.sValue;
    }

    public void setSValue(String str) {
        this.sValue = str;
    }

    public Dimension() {
        setSName("");
        setSValue(this.sValue);
    }

    public Dimension(String str, String str2) {
        setSName(str);
        setSValue(str2);
    }

    @Override // java.lang.Comparable
    public int compareTo(Dimension dimension) {
        int[] iArr = {JceUtil.compareTo(this.sName, dimension.sName), JceUtil.compareTo(this.sValue, dimension.sValue)};
        for (int i = 0; i < 2; i++) {
            if (iArr[i] != 0) {
                return iArr[i];
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Dimension dimension = (Dimension) obj;
        return JceUtil.equals(this.sName, dimension.sName) && JceUtil.equals(this.sValue, dimension.sValue);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sName), JceUtil.hashCode(this.sValue)});
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
        String str = this.sName;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sValue;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSName(jceInputStream.readString(0, false));
        setSValue(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sName, "sName");
        jceDisplayer.display(this.sValue, "sValue");
    }
}
