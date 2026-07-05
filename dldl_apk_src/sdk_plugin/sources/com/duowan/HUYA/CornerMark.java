package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class CornerMark extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iPos;
    public String sAction;
    public String sIcon;
    public String sText;
    public String sWebCSSClass;

    public String className() {
        return "HUYA.CornerMark";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.CornerMark";
    }

    public CornerMark() {
        this.iPos = 0;
        this.sIcon = "";
        this.sText = "";
        this.sWebCSSClass = "";
        this.sAction = "";
    }

    public CornerMark(int i, String str, String str2, String str3, String str4) {
        this.iPos = 0;
        this.sIcon = "";
        this.sText = "";
        this.sWebCSSClass = "";
        this.sAction = "";
        this.iPos = i;
        this.sIcon = str;
        this.sText = str2;
        this.sWebCSSClass = str3;
        this.sAction = str4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CornerMark cornerMark = (CornerMark) obj;
        return JceUtil.equals(this.iPos, cornerMark.iPos) && JceUtil.equals(this.sIcon, cornerMark.sIcon) && JceUtil.equals(this.sText, cornerMark.sText) && JceUtil.equals(this.sWebCSSClass, cornerMark.sWebCSSClass) && JceUtil.equals(this.sAction, cornerMark.sAction);
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
        jceOutputStream.write(this.iPos, 0);
        String str = this.sIcon;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sText;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        String str3 = this.sWebCSSClass;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
        String str4 = this.sAction;
        if (str4 != null) {
            jceOutputStream.write(str4, 4);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        this.iPos = jceInputStream.read(this.iPos, 0, false);
        this.sIcon = jceInputStream.readString(1, false);
        this.sText = jceInputStream.readString(2, false);
        this.sWebCSSClass = jceInputStream.readString(3, false);
        this.sAction = jceInputStream.readString(4, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iPos, "iPos");
        jceDisplayer.display(this.sIcon, "sIcon");
        jceDisplayer.display(this.sText, "sText");
        jceDisplayer.display(this.sWebCSSClass, "sWebCSSClass");
        jceDisplayer.display(this.sAction, "sAction");
    }
}
