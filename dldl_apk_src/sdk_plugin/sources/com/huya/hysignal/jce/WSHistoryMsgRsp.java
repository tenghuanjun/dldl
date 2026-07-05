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
public class WSHistoryMsgRsp extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WSHistoryMsgRsp> CREATOR = new Parcelable.Creator<WSHistoryMsgRsp>() { // from class: com.huya.hysignal.jce.WSHistoryMsgRsp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSHistoryMsgRsp createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WSHistoryMsgRsp wSHistoryMsgRsp = new WSHistoryMsgRsp();
            wSHistoryMsgRsp.readFrom(jceInputStream);
            return wSHistoryMsgRsp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSHistoryMsgRsp[] newArray(int i) {
            return new WSHistoryMsgRsp[i];
        }
    };
    static ArrayList<WSPushMessage> cache_vMsg;
    public ArrayList<WSPushMessage> vMsg = null;
    public long lLastMsgId = 0;

    public String className() {
        return "HUYA.WSHistoryMsgRsp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "WSHistoryMsgRsp";
    }

    public ArrayList<WSPushMessage> getVMsg() {
        return this.vMsg;
    }

    public void setVMsg(ArrayList<WSPushMessage> arrayList) {
        this.vMsg = arrayList;
    }

    public long getLLastMsgId() {
        return this.lLastMsgId;
    }

    public void setLLastMsgId(long j) {
        this.lLastMsgId = j;
    }

    public WSHistoryMsgRsp() {
        setVMsg(null);
        setLLastMsgId(this.lLastMsgId);
    }

    public WSHistoryMsgRsp(ArrayList<WSPushMessage> arrayList, long j) {
        setVMsg(arrayList);
        setLLastMsgId(j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSHistoryMsgRsp wSHistoryMsgRsp = (WSHistoryMsgRsp) obj;
        return JceUtil.equals(this.vMsg, wSHistoryMsgRsp.vMsg) && JceUtil.equals(this.lLastMsgId, wSHistoryMsgRsp.lLastMsgId);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.vMsg), JceUtil.hashCode(this.lLastMsgId)});
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
        ArrayList<WSPushMessage> arrayList = this.vMsg;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
        jceOutputStream.write(this.lLastMsgId, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vMsg == null) {
            cache_vMsg = new ArrayList<>();
            cache_vMsg.add(new WSPushMessage());
        }
        setVMsg((ArrayList) jceInputStream.read(cache_vMsg, 0, false));
        setLLastMsgId(jceInputStream.read(this.lLastMsgId, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection) this.vMsg, "vMsg");
        jceDisplayer.display(this.lLastMsgId, "lLastMsgId");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
