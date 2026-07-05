package com.duowan.HUYA;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SubscriberExtraInfo extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<SubscriberExtraInfo> CREATOR = new Parcelable.Creator<SubscriberExtraInfo>() { // from class: com.duowan.HUYA.SubscriberExtraInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriberExtraInfo createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            SubscriberExtraInfo subscriberExtraInfo = new SubscriberExtraInfo();
            subscriberExtraInfo.readFrom(jceInputStream);
            return subscriberExtraInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriberExtraInfo[] newArray(int i) {
            return new SubscriberExtraInfo[i];
        }
    };
    static ArrayList<SubscriberAcSkillInfo> cache_vAcSkill;
    static ArrayList<SubscriberAcTag> cache_vAcTags;
    public ArrayList<SubscriberAcSkillInfo> vAcSkill = null;
    public int iAcOrderCount = 0;
    public int iAcStar = 0;
    public String sAcMasterPageUrl = "";
    public int iAcSortScore = 0;
    public ArrayList<SubscriberAcTag> vAcTags = null;

    public String className() {
        return "HUYA.SubscriberExtraInfo";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SubscriberExtraInfo";
    }

    public ArrayList<SubscriberAcSkillInfo> getVAcSkill() {
        return this.vAcSkill;
    }

    public void setVAcSkill(ArrayList<SubscriberAcSkillInfo> arrayList) {
        this.vAcSkill = arrayList;
    }

    public int getIAcOrderCount() {
        return this.iAcOrderCount;
    }

    public void setIAcOrderCount(int i) {
        this.iAcOrderCount = i;
    }

    public int getIAcStar() {
        return this.iAcStar;
    }

    public void setIAcStar(int i) {
        this.iAcStar = i;
    }

    public String getSAcMasterPageUrl() {
        return this.sAcMasterPageUrl;
    }

    public void setSAcMasterPageUrl(String str) {
        this.sAcMasterPageUrl = str;
    }

    public int getIAcSortScore() {
        return this.iAcSortScore;
    }

    public void setIAcSortScore(int i) {
        this.iAcSortScore = i;
    }

    public ArrayList<SubscriberAcTag> getVAcTags() {
        return this.vAcTags;
    }

    public void setVAcTags(ArrayList<SubscriberAcTag> arrayList) {
        this.vAcTags = arrayList;
    }

    public SubscriberExtraInfo() {
        setVAcSkill(null);
        setIAcOrderCount(this.iAcOrderCount);
        setIAcStar(this.iAcStar);
        setSAcMasterPageUrl(this.sAcMasterPageUrl);
        setIAcSortScore(this.iAcSortScore);
        setVAcTags(this.vAcTags);
    }

    public SubscriberExtraInfo(ArrayList<SubscriberAcSkillInfo> arrayList, int i, int i2, String str, int i3, ArrayList<SubscriberAcTag> arrayList2) {
        setVAcSkill(arrayList);
        setIAcOrderCount(i);
        setIAcStar(i2);
        setSAcMasterPageUrl(str);
        setIAcSortScore(i3);
        setVAcTags(arrayList2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubscriberExtraInfo subscriberExtraInfo = (SubscriberExtraInfo) obj;
        return JceUtil.equals(this.vAcSkill, subscriberExtraInfo.vAcSkill) && JceUtil.equals(this.iAcOrderCount, subscriberExtraInfo.iAcOrderCount) && JceUtil.equals(this.iAcStar, subscriberExtraInfo.iAcStar) && JceUtil.equals(this.sAcMasterPageUrl, subscriberExtraInfo.sAcMasterPageUrl) && JceUtil.equals(this.iAcSortScore, subscriberExtraInfo.iAcSortScore) && JceUtil.equals(this.vAcTags, subscriberExtraInfo.vAcTags);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.vAcSkill), JceUtil.hashCode(this.iAcOrderCount), JceUtil.hashCode(this.iAcStar), JceUtil.hashCode(this.sAcMasterPageUrl), JceUtil.hashCode(this.iAcSortScore), JceUtil.hashCode(this.vAcTags)});
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
        ArrayList<SubscriberAcSkillInfo> arrayList = this.vAcSkill;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
        jceOutputStream.write(this.iAcOrderCount, 1);
        jceOutputStream.write(this.iAcStar, 2);
        String str = this.sAcMasterPageUrl;
        if (str != null) {
            jceOutputStream.write(str, 3);
        }
        jceOutputStream.write(this.iAcSortScore, 4);
        ArrayList<SubscriberAcTag> arrayList2 = this.vAcTags;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 5);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vAcSkill == null) {
            cache_vAcSkill = new ArrayList<>();
            cache_vAcSkill.add(new SubscriberAcSkillInfo());
        }
        setVAcSkill((ArrayList) jceInputStream.read(cache_vAcSkill, 0, false));
        setIAcOrderCount(jceInputStream.read(this.iAcOrderCount, 1, false));
        setIAcStar(jceInputStream.read(this.iAcStar, 2, false));
        setSAcMasterPageUrl(jceInputStream.readString(3, false));
        setIAcSortScore(jceInputStream.read(this.iAcSortScore, 4, false));
        if (cache_vAcTags == null) {
            cache_vAcTags = new ArrayList<>();
            cache_vAcTags.add(new SubscriberAcTag());
        }
        setVAcTags((ArrayList) jceInputStream.read(cache_vAcTags, 5, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection) this.vAcSkill, "vAcSkill");
        jceDisplayer.display(this.iAcOrderCount, "iAcOrderCount");
        jceDisplayer.display(this.iAcStar, "iAcStar");
        jceDisplayer.display(this.sAcMasterPageUrl, "sAcMasterPageUrl");
        jceDisplayer.display(this.iAcSortScore, "iAcSortScore");
        jceDisplayer.display((Collection) this.vAcTags, "vAcTags");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
