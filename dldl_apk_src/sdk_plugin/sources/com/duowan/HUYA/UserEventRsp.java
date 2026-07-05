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
public class UserEventRsp extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<UserEventRsp> CREATOR = new Parcelable.Creator<UserEventRsp>() { // from class: com.duowan.HUYA.UserEventRsp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserEventRsp createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            UserEventRsp userEventRsp = new UserEventRsp();
            userEventRsp.readFrom(jceInputStream);
            return userEventRsp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserEventRsp[] newArray(int i) {
            return new UserEventRsp[i];
        }
    };
    public long lTid = 0;
    public long lSid = 0;
    public int iUserHeartBeatInterval = 60;
    public int iPresentHeartBeatInterval = 60;

    public String className() {
        return "HUYA.UserEventRsp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserEventRsp";
    }

    public long getLTid() {
        return this.lTid;
    }

    public void setLTid(long j) {
        this.lTid = j;
    }

    public long getLSid() {
        return this.lSid;
    }

    public void setLSid(long j) {
        this.lSid = j;
    }

    public int getIUserHeartBeatInterval() {
        return this.iUserHeartBeatInterval;
    }

    public void setIUserHeartBeatInterval(int i) {
        this.iUserHeartBeatInterval = i;
    }

    public int getIPresentHeartBeatInterval() {
        return this.iPresentHeartBeatInterval;
    }

    public void setIPresentHeartBeatInterval(int i) {
        this.iPresentHeartBeatInterval = i;
    }

    public UserEventRsp() {
        setLTid(0L);
        setLSid(this.lSid);
        setIUserHeartBeatInterval(this.iUserHeartBeatInterval);
        setIPresentHeartBeatInterval(this.iPresentHeartBeatInterval);
    }

    public UserEventRsp(long j, long j2, int i, int i2) {
        setLTid(j);
        setLSid(j2);
        setIUserHeartBeatInterval(i);
        setIPresentHeartBeatInterval(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserEventRsp userEventRsp = (UserEventRsp) obj;
        return JceUtil.equals(this.lTid, userEventRsp.lTid) && JceUtil.equals(this.lSid, userEventRsp.lSid) && JceUtil.equals(this.iUserHeartBeatInterval, userEventRsp.iUserHeartBeatInterval) && JceUtil.equals(this.iPresentHeartBeatInterval, userEventRsp.iPresentHeartBeatInterval);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lTid), JceUtil.hashCode(this.lSid), JceUtil.hashCode(this.iUserHeartBeatInterval), JceUtil.hashCode(this.iPresentHeartBeatInterval)});
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
        jceOutputStream.write(this.lTid, 0);
        jceOutputStream.write(this.lSid, 1);
        jceOutputStream.write(this.iUserHeartBeatInterval, 2);
        jceOutputStream.write(this.iPresentHeartBeatInterval, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLTid(jceInputStream.read(this.lTid, 0, false));
        setLSid(jceInputStream.read(this.lSid, 1, false));
        setIUserHeartBeatInterval(jceInputStream.read(this.iUserHeartBeatInterval, 2, false));
        setIPresentHeartBeatInterval(jceInputStream.read(this.iPresentHeartBeatInterval, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSid, "lSid");
        jceDisplayer.display(this.iUserHeartBeatInterval, "iUserHeartBeatInterval");
        jceDisplayer.display(this.iPresentHeartBeatInterval, "iPresentHeartBeatInterval");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
