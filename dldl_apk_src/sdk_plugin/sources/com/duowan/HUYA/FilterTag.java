package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class FilterTag extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iLBS;
    public int iTime;
    public String sAction;
    public String sId;
    public String sName;
    public String sParentTagId;

    public String className() {
        return "HUYA.FilterTag";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.FilterTag";
    }

    public FilterTag() {
        this.sId = "";
        this.sName = "";
        this.sParentTagId = "";
        this.iLBS = 0;
        this.sAction = "";
        this.iTime = 0;
    }

    public FilterTag(String str, String str2, String str3, int i, String str4, int i2) {
        this.sId = "";
        this.sName = "";
        this.sParentTagId = "";
        this.iLBS = 0;
        this.sAction = "";
        this.iTime = 0;
        this.sId = str;
        this.sName = str2;
        this.sParentTagId = str3;
        this.iLBS = i;
        this.sAction = str4;
        this.iTime = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FilterTag filterTag = (FilterTag) obj;
        return JceUtil.equals(this.sId, filterTag.sId) && JceUtil.equals(this.sName, filterTag.sName) && JceUtil.equals(this.sParentTagId, filterTag.sParentTagId) && JceUtil.equals(this.iLBS, filterTag.iLBS) && JceUtil.equals(this.sAction, filterTag.sAction) && JceUtil.equals(this.iTime, filterTag.iTime);
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
        String str = this.sId;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sName;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
        String str3 = this.sParentTagId;
        if (str3 != null) {
            jceOutputStream.write(str3, 2);
        }
        jceOutputStream.write(this.iLBS, 3);
        String str4 = this.sAction;
        if (str4 != null) {
            jceOutputStream.write(str4, 4);
        }
        jceOutputStream.write(this.iTime, 5);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        this.sId = jceInputStream.readString(0, false);
        this.sName = jceInputStream.readString(1, false);
        this.sParentTagId = jceInputStream.readString(2, false);
        this.iLBS = jceInputStream.read(this.iLBS, 3, false);
        this.sAction = jceInputStream.readString(4, false);
        this.iTime = jceInputStream.read(this.iTime, 5, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sId, "sId");
        jceDisplayer.display(this.sName, "sName");
        jceDisplayer.display(this.sParentTagId, "sParentTagId");
        jceDisplayer.display(this.iLBS, "iLBS");
        jceDisplayer.display(this.sAction, "sAction");
        jceDisplayer.display(this.iTime, "iTime");
    }
}
