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
public class SubscriberAcSkillInfo extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<SubscriberAcSkillInfo> CREATOR = new Parcelable.Creator<SubscriberAcSkillInfo>() { // from class: com.duowan.HUYA.SubscriberAcSkillInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriberAcSkillInfo createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            SubscriberAcSkillInfo subscriberAcSkillInfo = new SubscriberAcSkillInfo();
            subscriberAcSkillInfo.readFrom(jceInputStream);
            return subscriberAcSkillInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriberAcSkillInfo[] newArray(int i) {
            return new SubscriberAcSkillInfo[i];
        }
    };
    public int iId = 0;
    public String sName = "";
    public String sIcon = "";

    public String className() {
        return "HUYA.SubscriberAcSkillInfo";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SubscriberAcSkillInfo";
    }

    public int getIId() {
        return this.iId;
    }

    public void setIId(int i) {
        this.iId = i;
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

    public SubscriberAcSkillInfo() {
        setIId(0);
        setSName(this.sName);
        setSIcon(this.sIcon);
    }

    public SubscriberAcSkillInfo(int i, String str, String str2) {
        setIId(i);
        setSName(str);
        setSIcon(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubscriberAcSkillInfo subscriberAcSkillInfo = (SubscriberAcSkillInfo) obj;
        return JceUtil.equals(this.iId, subscriberAcSkillInfo.iId) && JceUtil.equals(this.sName, subscriberAcSkillInfo.sName) && JceUtil.equals(this.sIcon, subscriberAcSkillInfo.sIcon);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iId), JceUtil.hashCode(this.sName), JceUtil.hashCode(this.sIcon)});
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
        jceOutputStream.write(this.iId, 0);
        String str = this.sName;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sIcon;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIId(jceInputStream.read(this.iId, 0, false));
        setSName(jceInputStream.readString(1, false));
        setSIcon(jceInputStream.readString(2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iId, "iId");
        jceDisplayer.display(this.sName, "sName");
        jceDisplayer.display(this.sIcon, "sIcon");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
