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
public class RelationItem extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<RelationItem> CREATOR = new Parcelable.Creator<RelationItem>() { // from class: com.duowan.HUYA.RelationItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RelationItem createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            RelationItem relationItem = new RelationItem();
            relationItem.readFrom(jceInputStream);
            return relationItem;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RelationItem[] newArray(int i) {
            return new RelationItem[i];
        }
    };
    public long lUid = 0;
    public int iRelation = 0;
    public int iSubscribeToTime = 0;
    public int iSubscribeFromTime = 0;
    public int iBlackTime = 0;
    public int iModifyTime = 0;

    public String className() {
        return "HUYA.RelationItem";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.RelationItem";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public int getIRelation() {
        return this.iRelation;
    }

    public void setIRelation(int i) {
        this.iRelation = i;
    }

    public int getISubscribeToTime() {
        return this.iSubscribeToTime;
    }

    public void setISubscribeToTime(int i) {
        this.iSubscribeToTime = i;
    }

    public int getISubscribeFromTime() {
        return this.iSubscribeFromTime;
    }

    public void setISubscribeFromTime(int i) {
        this.iSubscribeFromTime = i;
    }

    public int getIBlackTime() {
        return this.iBlackTime;
    }

    public void setIBlackTime(int i) {
        this.iBlackTime = i;
    }

    public int getIModifyTime() {
        return this.iModifyTime;
    }

    public void setIModifyTime(int i) {
        this.iModifyTime = i;
    }

    public RelationItem() {
        setLUid(0L);
        setIRelation(this.iRelation);
        setISubscribeToTime(this.iSubscribeToTime);
        setISubscribeFromTime(this.iSubscribeFromTime);
        setIBlackTime(this.iBlackTime);
        setIModifyTime(this.iModifyTime);
    }

    public RelationItem(long j, int i, int i2, int i3, int i4, int i5) {
        setLUid(j);
        setIRelation(i);
        setISubscribeToTime(i2);
        setISubscribeFromTime(i3);
        setIBlackTime(i4);
        setIModifyTime(i5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RelationItem relationItem = (RelationItem) obj;
        return JceUtil.equals(this.lUid, relationItem.lUid) && JceUtil.equals(this.iRelation, relationItem.iRelation) && JceUtil.equals(this.iSubscribeToTime, relationItem.iSubscribeToTime) && JceUtil.equals(this.iSubscribeFromTime, relationItem.iSubscribeFromTime) && JceUtil.equals(this.iBlackTime, relationItem.iBlackTime) && JceUtil.equals(this.iModifyTime, relationItem.iModifyTime);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lUid), JceUtil.hashCode(this.iRelation), JceUtil.hashCode(this.iSubscribeToTime), JceUtil.hashCode(this.iSubscribeFromTime), JceUtil.hashCode(this.iBlackTime), JceUtil.hashCode(this.iModifyTime)});
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
        jceOutputStream.write(this.lUid, 0);
        jceOutputStream.write(this.iRelation, 1);
        jceOutputStream.write(this.iSubscribeToTime, 2);
        jceOutputStream.write(this.iSubscribeFromTime, 3);
        jceOutputStream.write(this.iBlackTime, 4);
        jceOutputStream.write(this.iModifyTime, 5);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setIRelation(jceInputStream.read(this.iRelation, 1, false));
        setISubscribeToTime(jceInputStream.read(this.iSubscribeToTime, 2, false));
        setISubscribeFromTime(jceInputStream.read(this.iSubscribeFromTime, 3, false));
        setIBlackTime(jceInputStream.read(this.iBlackTime, 4, false));
        setIModifyTime(jceInputStream.read(this.iModifyTime, 5, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.iRelation, "iRelation");
        jceDisplayer.display(this.iSubscribeToTime, "iSubscribeToTime");
        jceDisplayer.display(this.iSubscribeFromTime, "iSubscribeFromTime");
        jceDisplayer.display(this.iBlackTime, "iBlackTime");
        jceDisplayer.display(this.iModifyTime, "iModifyTime");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
