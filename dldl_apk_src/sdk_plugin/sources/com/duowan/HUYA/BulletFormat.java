package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class BulletFormat extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iFontColor = -1;
    public int iFontSize = 4;
    public int iTextSpeed = 0;
    public int iTransitionType = 1;
    public int iPopupStyle = 0;

    public String className() {
        return "HUYA.BulletFormat";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.BulletFormat";
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

    public int getITextSpeed() {
        return this.iTextSpeed;
    }

    public void setITextSpeed(int i) {
        this.iTextSpeed = i;
    }

    public int getITransitionType() {
        return this.iTransitionType;
    }

    public void setITransitionType(int i) {
        this.iTransitionType = i;
    }

    public int getIPopupStyle() {
        return this.iPopupStyle;
    }

    public void setIPopupStyle(int i) {
        this.iPopupStyle = i;
    }

    public BulletFormat() {
        setIFontColor(-1);
        setIFontSize(this.iFontSize);
        setITextSpeed(this.iTextSpeed);
        setITransitionType(this.iTransitionType);
        setIPopupStyle(this.iPopupStyle);
    }

    public BulletFormat(int i, int i2, int i3, int i4, int i5) {
        setIFontColor(i);
        setIFontSize(i2);
        setITextSpeed(i3);
        setITransitionType(i4);
        setIPopupStyle(i5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BulletFormat bulletFormat = (BulletFormat) obj;
        return JceUtil.equals(this.iFontColor, bulletFormat.iFontColor) && JceUtil.equals(this.iFontSize, bulletFormat.iFontSize) && JceUtil.equals(this.iTextSpeed, bulletFormat.iTextSpeed) && JceUtil.equals(this.iTransitionType, bulletFormat.iTransitionType) && JceUtil.equals(this.iPopupStyle, bulletFormat.iPopupStyle);
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
        jceOutputStream.write(this.iTextSpeed, 2);
        jceOutputStream.write(this.iTransitionType, 3);
        jceOutputStream.write(this.iPopupStyle, 4);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIFontColor(jceInputStream.read(this.iFontColor, 0, false));
        setIFontSize(jceInputStream.read(this.iFontSize, 1, false));
        setITextSpeed(jceInputStream.read(this.iTextSpeed, 2, false));
        setITransitionType(jceInputStream.read(this.iTransitionType, 3, false));
        setIPopupStyle(jceInputStream.read(this.iPopupStyle, 4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iFontColor, "iFontColor");
        jceDisplayer.display(this.iFontSize, "iFontSize");
        jceDisplayer.display(this.iTextSpeed, "iTextSpeed");
        jceDisplayer.display(this.iTransitionType, "iTransitionType");
        jceDisplayer.display(this.iPopupStyle, "iPopupStyle");
    }
}
