package com.huya.hysignal.jce;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WSTimeSyncReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WSTimeSyncReq> CREATOR = new Parcelable.Creator<WSTimeSyncReq>() { // from class: com.huya.hysignal.jce.WSTimeSyncReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSTimeSyncReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WSTimeSyncReq wSTimeSyncReq = new WSTimeSyncReq();
            wSTimeSyncReq.readFrom(jceInputStream);
            return wSTimeSyncReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSTimeSyncReq[] newArray(int i) {
            return new WSTimeSyncReq[i];
        }
    };
    public String sGuid = "";
    public long lClientTime = 0;

    public String className() {
        return "HUYA.WSTimeSyncReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSTimeSyncReq";
    }

    public String getSGuid() {
        return this.sGuid;
    }

    public void setSGuid(String str) {
        this.sGuid = str;
    }

    public long getLClientTime() {
        return this.lClientTime;
    }

    public void setLClientTime(long j) {
        this.lClientTime = j;
    }

    public WSTimeSyncReq() {
        setSGuid("");
        setLClientTime(this.lClientTime);
    }

    public WSTimeSyncReq(String str, long j) {
        setSGuid(str);
        setLClientTime(j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSTimeSyncReq wSTimeSyncReq = (WSTimeSyncReq) obj;
        return JceUtil.equals(this.sGuid, wSTimeSyncReq.sGuid) && JceUtil.equals(this.lClientTime, wSTimeSyncReq.lClientTime);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sGuid), JceUtil.hashCode(this.lClientTime)});
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
        String str = this.sGuid;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        jceOutputStream.write(this.lClientTime, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSGuid(jceInputStream.readString(0, false));
        setLClientTime(jceInputStream.read(this.lClientTime, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sGuid, "sGuid");
        jceDisplayer.display(this.lClientTime, "lClientTime");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
