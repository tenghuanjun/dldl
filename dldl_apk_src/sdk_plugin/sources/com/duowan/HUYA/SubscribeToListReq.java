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
public class SubscribeToListReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<SubscribeToListReq> CREATOR = new Parcelable.Creator<SubscribeToListReq>() { // from class: com.duowan.HUYA.SubscribeToListReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscribeToListReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            SubscribeToListReq subscribeToListReq = new SubscribeToListReq();
            subscribeToListReq.readFrom(jceInputStream);
            return subscribeToListReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscribeToListReq[] newArray(int i) {
            return new SubscribeToListReq[i];
        }
    };
    static Subscriber cache_tFrom;
    static UserId cache_tId;
    public UserId tId = null;
    public Subscriber tFrom = null;
    public int iFreeFlowFlag = 0;

    public String className() {
        return "HUYA.SubscribeToListReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SubscribeToListReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public Subscriber getTFrom() {
        return this.tFrom;
    }

    public void setTFrom(Subscriber subscriber) {
        this.tFrom = subscriber;
    }

    public int getIFreeFlowFlag() {
        return this.iFreeFlowFlag;
    }

    public void setIFreeFlowFlag(int i) {
        this.iFreeFlowFlag = i;
    }

    public SubscribeToListReq() {
        setTId(null);
        setTFrom(this.tFrom);
        setIFreeFlowFlag(this.iFreeFlowFlag);
    }

    public SubscribeToListReq(UserId userId, Subscriber subscriber, int i) {
        setTId(userId);
        setTFrom(subscriber);
        setIFreeFlowFlag(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubscribeToListReq subscribeToListReq = (SubscribeToListReq) obj;
        return JceUtil.equals(this.tId, subscribeToListReq.tId) && JceUtil.equals(this.tFrom, subscribeToListReq.tFrom) && JceUtil.equals(this.iFreeFlowFlag, subscribeToListReq.iFreeFlowFlag);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tId), JceUtil.hashCode(this.tFrom), JceUtil.hashCode(this.iFreeFlowFlag)});
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
        UserId userId = this.tId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 0);
        }
        Subscriber subscriber = this.tFrom;
        if (subscriber != null) {
            jceOutputStream.write((JceStruct) subscriber, 1);
        }
        jceOutputStream.write(this.iFreeFlowFlag, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        if (cache_tFrom == null) {
            cache_tFrom = new Subscriber();
        }
        setTFrom((Subscriber) jceInputStream.read((JceStruct) cache_tFrom, 1, false));
        setIFreeFlowFlag(jceInputStream.read(this.iFreeFlowFlag, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display((JceStruct) this.tFrom, "tFrom");
        jceDisplayer.display(this.iFreeFlowFlag, "iFreeFlowFlag");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
