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
public class SubscriberAcTag extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<SubscriberAcTag> CREATOR = new Parcelable.Creator<SubscriberAcTag>() { // from class: com.duowan.HUYA.SubscriberAcTag.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriberAcTag createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            SubscriberAcTag subscriberAcTag = new SubscriberAcTag();
            subscriberAcTag.readFrom(jceInputStream);
            return subscriberAcTag;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriberAcTag[] newArray(int i) {
            return new SubscriberAcTag[i];
        }
    };
    public int iTagId = 0;
    public String sName = "";
    public String sIcon = "";
    public String sDesc = "";
    public int iType = 0;

    public String className() {
        return "HUYA.SubscriberAcTag";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SubscriberAcTag";
    }

    public int getITagId() {
        return this.iTagId;
    }

    public void setITagId(int i) {
        this.iTagId = i;
    }

    public String getSName() {
        return this.sName;
    }

    public void setSName(String str) {
        this.sName = str;
    }

    public String getSIcon() {
        return this.sIcon;
    }

    public void setSIcon(String str) {
        this.sIcon = str;
    }

    public String getSDesc() {
        return this.sDesc;
    }

    public void setSDesc(String str) {
        this.sDesc = str;
    }

    public int getIType() {
        return this.iType;
    }

    public void setIType(int i) {
        this.iType = i;
    }

    public SubscriberAcTag() {
        setITagId(0);
        setSName(this.sName);
        setSIcon(this.sIcon);
        setSDesc(this.sDesc);
        setIType(this.iType);
    }

    public SubscriberAcTag(int i, String str, String str2, String str3, int i2) {
        setITagId(i);
        setSName(str);
        setSIcon(str2);
        setSDesc(str3);
        setIType(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubscriberAcTag subscriberAcTag = (SubscriberAcTag) obj;
        return JceUtil.equals(this.iTagId, subscriberAcTag.iTagId) && JceUtil.equals(this.sName, subscriberAcTag.sName) && JceUtil.equals(this.sIcon, subscriberAcTag.sIcon) && JceUtil.equals(this.sDesc, subscriberAcTag.sDesc) && JceUtil.equals(this.iType, subscriberAcTag.iType);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iTagId), JceUtil.hashCode(this.sName), JceUtil.hashCode(this.sIcon), JceUtil.hashCode(this.sDesc), JceUtil.hashCode(this.iType)});
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
        jceOutputStream.write(this.iTagId, 0);
        String str = this.sName;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sIcon;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        String str3 = this.sDesc;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
        jceOutputStream.write(this.iType, 4);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setITagId(jceInputStream.read(this.iTagId, 0, false));
        setSName(jceInputStream.readString(1, false));
        setSIcon(jceInputStream.readString(2, false));
        setSDesc(jceInputStream.readString(3, false));
        setIType(jceInputStream.read(this.iType, 4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iTagId, "iTagId");
        jceDisplayer.display(this.sName, "sName");
        jceDisplayer.display(this.sIcon, "sIcon");
        jceDisplayer.display(this.sDesc, "sDesc");
        jceDisplayer.display(this.iType, "iType");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
