package com.duowan.monitor.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class Field extends JceStruct implements Comparable<Field>, Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sName = "";
    public double fValue = 0.0d;

    public String className() {
        return "monitor.jce.Field";
    }

    public String fullClassName() {
        return "com.duowan.monitor.jce.Field";
    }

    public String getSName() {
        return this.sName;
    }

    public void setSName(String str) {
        this.sName = str;
    }

    public double getFValue() {
        return this.fValue;
    }

    public void setFValue(double d) {
        this.fValue = d;
    }

    public Field() {
        setSName("");
        setFValue(this.fValue);
    }

    public Field(String str, double d) {
        setSName(str);
        setFValue(d);
    }

    @Override // java.lang.Comparable
    public int compareTo(Field field) {
        int[] iArr = {JceUtil.compareTo(this.sName, field.sName), JceUtil.compareTo(this.fValue, field.fValue)};
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
        Field field = (Field) obj;
        return JceUtil.equals(this.sName, field.sName) && JceUtil.equals(this.fValue, field.fValue);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sName), JceUtil.hashCode(this.fValue)});
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
        jceOutputStream.write(this.fValue, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSName(jceInputStream.readString(0, false));
        setFValue(jceInputStream.read(this.fValue, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sName, "sName");
        jceDisplayer.display(this.fValue, "fValue");
    }
}
