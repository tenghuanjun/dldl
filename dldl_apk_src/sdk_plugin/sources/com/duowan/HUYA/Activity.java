package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class Activity extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iType = 0;
    public String sKey = "";

    public String className() {
        return "HUYA.Activity";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.Activity";
    }

    public int getIType() {
        return this.iType;
    }

    public void setIType(int i) {
        this.iType = i;
    }

    public String getSKey() {
        return this.sKey;
    }

    public void setSKey(String str) {
        this.sKey = str;
    }

    public Activity() {
        setIType(0);
        setSKey(this.sKey);
    }

    public Activity(int i, String str) {
        setIType(i);
        setSKey(str);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Activity activity = (Activity) obj;
        return JceUtil.equals(this.iType, activity.iType) && JceUtil.equals(this.sKey, activity.sKey);
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
        jceOutputStream.write(this.iType, 0);
        String str = this.sKey;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIType(jceInputStream.read(this.iType, 0, false));
        setSKey(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iType, "iType");
        jceDisplayer.display(this.sKey, "sKey");
    }
}
