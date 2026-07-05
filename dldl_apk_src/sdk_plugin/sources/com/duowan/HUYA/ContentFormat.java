package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ContentFormat extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iFontColor = -1;
    public int iFontSize = 4;
    public int iPopupStyle = 0;
    public int iNickNameFontColor = -1;

    public String className() {
        return "HUYA.ContentFormat";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ContentFormat";
    }

    public int getIFontColor() {
        return this.iFontColor;
    }

    public void setIFontColor(int i) {
        this.iFontColor = i;
    }

    public int getIFontSize() {
        return this.iFontSize;
    }

    public void setIFontSize(int i) {
        this.iFontSize = i;
    }

    public int getIPopupStyle() {
        return this.iPopupStyle;
    }

    public void setIPopupStyle(int i) {
        this.iPopupStyle = i;
    }

    public int getINickNameFontColor() {
        return this.iNickNameFontColor;
    }

    public void setINickNameFontColor(int i) {
        this.iNickNameFontColor = i;
    }

    public ContentFormat() {
        setIFontColor(-1);
        setIFontSize(this.iFontSize);
        setIPopupStyle(this.iPopupStyle);
        setINickNameFontColor(this.iNickNameFontColor);
    }

    public ContentFormat(int i, int i2, int i3, int i4) {
        setIFontColor(i);
        setIFontSize(i2);
        setIPopupStyle(i3);
        setINickNameFontColor(i4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ContentFormat contentFormat = (ContentFormat) obj;
        return JceUtil.equals(this.iFontColor, contentFormat.iFontColor) && JceUtil.equals(this.iFontSize, contentFormat.iFontSize) && JceUtil.equals(this.iPopupStyle, contentFormat.iPopupStyle) && JceUtil.equals(this.iNickNameFontColor, contentFormat.iNickNameFontColor);
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
        jceOutputStream.write(this.iFontColor, 0);
        jceOutputStream.write(this.iFontSize, 1);
        jceOutputStream.write(this.iPopupStyle, 2);
        jceOutputStream.write(this.iNickNameFontColor, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIFontColor(jceInputStream.read(this.iFontColor, 0, false));
        setIFontSize(jceInputStream.read(this.iFontSize, 1, false));
        setIPopupStyle(jceInputStream.read(this.iPopupStyle, 2, false));
        setINickNameFontColor(jceInputStream.read(this.iNickNameFontColor, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iFontColor, "iFontColor");
        jceDisplayer.display(this.iFontSize, "iFontSize");
        jceDisplayer.display(this.iPopupStyle, "iPopupStyle");
        jceDisplayer.display(this.iNickNameFontColor, "iNickNameFontColor");
    }
}
