package com.huya.hysignal.jce;

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

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WSHistoryMsgReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WSHistoryMsgReq> CREATOR = new Parcelable.Creator<WSHistoryMsgReq>() { // from class: com.huya.hysignal.jce.WSHistoryMsgReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSHistoryMsgReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WSHistoryMsgReq wSHistoryMsgReq = new WSHistoryMsgReq();
            wSHistoryMsgReq.readFrom(jceInputStream);
            return wSHistoryMsgReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSHistoryMsgReq[] newArray(int i) {
            return new WSHistoryMsgReq[i];
        }
    };
    static ArrayList<String> cache_vGroupId;
    public long lUid = 0;
    public ArrayList<String> vGroupId = null;
    public long lLastMsgId = 0;

    public String className() {
        return "HUYA.WSHistoryMsgReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSHistoryMsgReq";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public ArrayList<String> getVGroupId() {
        return this.vGroupId;
    }

    public void setVGroupId(ArrayList<String> arrayList) {
        this.vGroupId = arrayList;
    }

    public long getLLastMsgId() {
        return this.lLastMsgId;
    }

    public void setLLastMsgId(long j) {
        this.lLastMsgId = j;
    }

    public WSHistoryMsgReq() {
        setLUid(0L);
        setVGroupId(this.vGroupId);
        setLLastMsgId(this.lLastMsgId);
    }

    public WSHistoryMsgReq(long j, ArrayList<String> arrayList, long j2) {
        setLUid(j);
        setVGroupId(arrayList);
        setLLastMsgId(j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSHistoryMsgReq wSHistoryMsgReq = (WSHistoryMsgReq) obj;
        return JceUtil.equals(this.lUid, wSHistoryMsgReq.lUid) && JceUtil.equals(this.vGroupId, wSHistoryMsgReq.vGroupId) && JceUtil.equals(this.lLastMsgId, wSHistoryMsgReq.lLastMsgId);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lUid), JceUtil.hashCode(this.vGroupId), JceUtil.hashCode(this.lLastMsgId)});
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
        ArrayList<String> arrayList = this.vGroupId;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
        jceOutputStream.write(this.lLastMsgId, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        if (cache_vGroupId == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vGroupId = arrayList;
            arrayList.add("");
        }
        setVGroupId((ArrayList) jceInputStream.read(cache_vGroupId, 1, false));
        setLLastMsgId(jceInputStream.read(this.lLastMsgId, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display((Collection) this.vGroupId, "vGroupId");
        jceDisplayer.display(this.lLastMsgId, "lLastMsgId");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
