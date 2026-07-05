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
public class WSP2POpenNotify extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WSP2POpenNotify> CREATOR = new Parcelable.Creator<WSP2POpenNotify>() { // from class: com.huya.hysignal.jce.WSP2POpenNotify.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSP2POpenNotify createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WSP2POpenNotify wSP2POpenNotify = new WSP2POpenNotify();
            wSP2POpenNotify.readFrom(jceInputStream);
            return wSP2POpenNotify;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSP2POpenNotify[] newArray(int i) {
            return new WSP2POpenNotify[i];
        }
    };
    static ArrayList<String> cache_vGroupId;
    public ArrayList<String> vGroupId = null;

    public String className() {
        return "HUYA.WSP2POpenNotify";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSP2POpenNotify";
    }

    public ArrayList<String> getVGroupId() {
        return this.vGroupId;
    }

    public void setVGroupId(ArrayList<String> arrayList) {
        this.vGroupId = arrayList;
    }

    public WSP2POpenNotify() {
        setVGroupId(null);
    }

    public WSP2POpenNotify(ArrayList<String> arrayList) {
        setVGroupId(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.vGroupId, ((WSP2POpenNotify) obj).vGroupId);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.vGroupId)});
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
        ArrayList<String> arrayList = this.vGroupId;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vGroupId == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vGroupId = arrayList;
            arrayList.add("");
        }
        setVGroupId((ArrayList) jceInputStream.read(cache_vGroupId, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Collection) this.vGroupId, "vGroupId");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
