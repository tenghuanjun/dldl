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
public class LiveScheduleInfo extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<LiveScheduleInfo> CREATOR = new Parcelable.Creator<LiveScheduleInfo>() { // from class: com.duowan.HUYA.LiveScheduleInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LiveScheduleInfo createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            LiveScheduleInfo liveScheduleInfo = new LiveScheduleInfo();
            liveScheduleInfo.readFrom(jceInputStream);
            return liveScheduleInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LiveScheduleInfo[] newArray(int i) {
            return new LiveScheduleInfo[i];
        }
    };
    public long lPresenterId = 0;
    public String sSchedule = "";
    public String sDescription = "";
    public long lModifyTime = 0;

    public String className() {
        return "HUYA.LiveScheduleInfo";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.LiveScheduleInfo";
    }

    public long getLPresenterId() {
        return this.lPresenterId;
    }

    public void setLPresenterId(long j) {
        this.lPresenterId = j;
    }

    public String getSSchedule() {
        return this.sSchedule;
    }

    public void setSSchedule(String str) {
        this.sSchedule = str;
    }

    public String getSDescription() {
        return this.sDescription;
    }

    public void setSDescription(String str) {
        this.sDescription = str;
    }

    public long getLModifyTime() {
        return this.lModifyTime;
    }

    public void setLModifyTime(long j) {
        this.lModifyTime = j;
    }

    public LiveScheduleInfo() {
        setLPresenterId(0L);
        setSSchedule(this.sSchedule);
        setSDescription(this.sDescription);
        setLModifyTime(this.lModifyTime);
    }

    public LiveScheduleInfo(long j, String str, String str2, long j2) {
        setLPresenterId(j);
        setSSchedule(str);
        setSDescription(str2);
        setLModifyTime(j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LiveScheduleInfo liveScheduleInfo = (LiveScheduleInfo) obj;
        return JceUtil.equals(this.lPresenterId, liveScheduleInfo.lPresenterId) && JceUtil.equals(this.sSchedule, liveScheduleInfo.sSchedule) && JceUtil.equals(this.sDescription, liveScheduleInfo.sDescription) && JceUtil.equals(this.lModifyTime, liveScheduleInfo.lModifyTime);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lPresenterId), JceUtil.hashCode(this.sSchedule), JceUtil.hashCode(this.sDescription), JceUtil.hashCode(this.lModifyTime)});
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
        jceOutputStream.write(this.lPresenterId, 0);
        String str = this.sSchedule;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sDescription;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        jceOutputStream.write(this.lModifyTime, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLPresenterId(jceInputStream.read(this.lPresenterId, 0, false));
        setSSchedule(jceInputStream.readString(1, false));
        setSDescription(jceInputStream.readString(2, false));
        setLModifyTime(jceInputStream.read(this.lModifyTime, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lPresenterId, "lPresenterId");
        jceDisplayer.display(this.sSchedule, "sSchedule");
        jceDisplayer.display(this.sDescription, "sDescription");
        jceDisplayer.display(this.lModifyTime, "lModifyTime");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
