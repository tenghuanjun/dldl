package com.duowan.HUYA;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BulletBorderGroundFormat extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<BulletBorderGroundFormat> CREATOR = new Parcelable.Creator<BulletBorderGroundFormat>() { // from class: com.duowan.HUYA.BulletBorderGroundFormat.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BulletBorderGroundFormat createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            BulletBorderGroundFormat bulletBorderGroundFormat = new BulletBorderGroundFormat();
            bulletBorderGroundFormat.readFrom(jceInputStream);
            return bulletBorderGroundFormat;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BulletBorderGroundFormat[] newArray(int i) {
            return new BulletBorderGroundFormat[i];
        }
    };
    public int iEnableUse = 0;
    public int iBorderThickness = 0;
    public int iBorderColour = -1;
    public int iBorderDiaphaneity = 100;
    public int iGroundColour = -1;
    public int iGroundColourDiaphaneity = 100;
    public String sAvatarDecorationUrl = "";
    public int iFontColor = -1;

    public String className() {
        return "HUYA.BulletBorderGroundFormat";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.BulletBorderGroundFormat";
    }

    public int getIEnableUse() {
        return this.iEnableUse;
    }

    public void setIEnableUse(int i) {
        this.iEnableUse = i;
    }

    public int getIBorderThickness() {
        return this.iBorderThickness;
    }

    public void setIBorderThickness(int i) {
        this.iBorderThickness = i;
    }

    public int getIBorderColour() {
        return this.iBorderColour;
    }

    public void setIBorderColour(int i) {
        this.iBorderColour = i;
    }

    public int getIBorderDiaphaneity() {
        return this.iBorderDiaphaneity;
    }

    public void setIBorderDiaphaneity(int i) {
        this.iBorderDiaphaneity = i;
    }

    public int getIGroundColour() {
        return this.iGroundColour;
    }

    public void setIGroundColour(int i) {
        this.iGroundColour = i;
    }

    public int getIGroundColourDiaphaneity() {
        return this.iGroundColourDiaphaneity;
    }

    public void setIGroundColourDiaphaneity(int i) {
        this.iGroundColourDiaphaneity = i;
    }

    public String getSAvatarDecorationUrl() {
        return this.sAvatarDecorationUrl;
    }

    public void setSAvatarDecorationUrl(String str) {
        this.sAvatarDecorationUrl = str;
    }

    public int getIFontColor() {
        return this.iFontColor;
    }

    public void setIFontColor(int i) {
        this.iFontColor = i;
    }

    public BulletBorderGroundFormat() {
        setIEnableUse(0);
        setIBorderThickness(this.iBorderThickness);
        setIBorderColour(this.iBorderColour);
        setIBorderDiaphaneity(this.iBorderDiaphaneity);
        setIGroundColour(this.iGroundColour);
        setIGroundColourDiaphaneity(this.iGroundColourDiaphaneity);
        setSAvatarDecorationUrl(this.sAvatarDecorationUrl);
        setIFontColor(this.iFontColor);
    }

    public BulletBorderGroundFormat(int i, int i2, int i3, int i4, int i5, int i6, String str, int i7) {
        setIEnableUse(i);
        setIBorderThickness(i2);
        setIBorderColour(i3);
        setIBorderDiaphaneity(i4);
        setIGroundColour(i5);
        setIGroundColourDiaphaneity(i6);
        setSAvatarDecorationUrl(str);
        setIFontColor(i7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BulletBorderGroundFormat bulletBorderGroundFormat = (BulletBorderGroundFormat) obj;
        return JceUtil.equals(this.iEnableUse, bulletBorderGroundFormat.iEnableUse) && JceUtil.equals(this.iBorderThickness, bulletBorderGroundFormat.iBorderThickness) && JceUtil.equals(this.iBorderColour, bulletBorderGroundFormat.iBorderColour) && JceUtil.equals(this.iBorderDiaphaneity, bulletBorderGroundFormat.iBorderDiaphaneity) && JceUtil.equals(this.iGroundColour, bulletBorderGroundFormat.iGroundColour) && JceUtil.equals(this.iGroundColourDiaphaneity, bulletBorderGroundFormat.iGroundColourDiaphaneity) && JceUtil.equals(this.sAvatarDecorationUrl, bulletBorderGroundFormat.sAvatarDecorationUrl) && JceUtil.equals(this.iFontColor, bulletBorderGroundFormat.iFontColor);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iEnableUse), JceUtil.hashCode(this.iBorderThickness), JceUtil.hashCode(this.iBorderColour), JceUtil.hashCode(this.iBorderDiaphaneity), JceUtil.hashCode(this.iGroundColour), JceUtil.hashCode(this.iGroundColourDiaphaneity), JceUtil.hashCode(this.sAvatarDecorationUrl), JceUtil.hashCode(this.iFontColor)});
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
        jceOutputStream.write(this.iEnableUse, 0);
        jceOutputStream.write(this.iBorderThickness, 1);
        jceOutputStream.write(this.iBorderColour, 2);
        jceOutputStream.write(this.iBorderDiaphaneity, 3);
        jceOutputStream.write(this.iGroundColour, 4);
        jceOutputStream.write(this.iGroundColourDiaphaneity, 5);
        String str = this.sAvatarDecorationUrl;
        if (str != null) {
            jceOutputStream.write(str, 6);
        }
        jceOutputStream.write(this.iFontColor, 7);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIEnableUse(jceInputStream.read(this.iEnableUse, 0, false));
        setIBorderThickness(jceInputStream.read(this.iBorderThickness, 1, false));
        setIBorderColour(jceInputStream.read(this.iBorderColour, 2, false));
        setIBorderDiaphaneity(jceInputStream.read(this.iBorderDiaphaneity, 3, false));
        setIGroundColour(jceInputStream.read(this.iGroundColour, 4, false));
        setIGroundColourDiaphaneity(jceInputStream.read(this.iGroundColourDiaphaneity, 5, false));
        setSAvatarDecorationUrl(jceInputStream.readString(6, false));
        setIFontColor(jceInputStream.read(this.iFontColor, 7, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iEnableUse, "iEnableUse");
        jceDisplayer.display(this.iBorderThickness, "iBorderThickness");
        jceDisplayer.display(this.iBorderColour, "iBorderColour");
        jceDisplayer.display(this.iBorderDiaphaneity, "iBorderDiaphaneity");
        jceDisplayer.display(this.iGroundColour, "iGroundColour");
        jceDisplayer.display(this.iGroundColourDiaphaneity, "iGroundColourDiaphaneity");
        jceDisplayer.display(this.sAvatarDecorationUrl, "sAvatarDecorationUrl");
        jceDisplayer.display(this.iFontColor, "iFontColor");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
