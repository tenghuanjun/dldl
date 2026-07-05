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
public class Subscriber extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<Subscriber> CREATOR = new Parcelable.Creator<Subscriber>() { // from class: com.duowan.HUYA.Subscriber.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Subscriber createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            Subscriber subscriber = new Subscriber();
            subscriber.readFrom(jceInputStream);
            return subscriber;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Subscriber[] newArray(int i) {
            return new Subscriber[i];
        }
    };
    public int iType = 0;
    public String sKey = "";
    public long lSubscribeTime = 0;

    public String className() {
        return "HUYA.Subscriber";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.Subscriber";
    }

    public int getIType() {
        return this.iType;
    }

    public void setIType(int i) {
        this.iType = i;
    }

    public String getSKey() {
        return this.sKey;
    }

    public void setSKey(String str) {
        this.sKey = str;
    }

    public long getLSubscribeTime() {
        return this.lSubscribeTime;
    }

    public void setLSubscribeTime(long j) {
        this.lSubscribeTime = j;
    }

    public Subscriber() {
        setIType(0);
        setSKey(this.sKey);
        setLSubscribeTime(this.lSubscribeTime);
    }

    public Subscriber(int i, String str, long j) {
        setIType(i);
        setSKey(str);
        setLSubscribeTime(j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Subscriber subscriber = (Subscriber) obj;
        return JceUtil.equals(this.iType, subscriber.iType) && JceUtil.equals(this.sKey, subscriber.sKey) && JceUtil.equals(this.lSubscribeTime, subscriber.lSubscribeTime);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iType), JceUtil.hashCode(this.sKey), JceUtil.hashCode(this.lSubscribeTime)});
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
        jceOutputStream.write(this.iType, 0);
        String str = this.sKey;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        jceOutputStream.write(this.lSubscribeTime, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIType(jceInputStream.read(this.iType, 0, false));
        setSKey(jceInputStream.readString(1, false));
        setLSubscribeTime(jceInputStream.read(this.lSubscribeTime, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iType, "iType");
        jceDisplayer.display(this.sKey, "sKey");
        jceDisplayer.display(this.lSubscribeTime, "lSubscribeTime");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
