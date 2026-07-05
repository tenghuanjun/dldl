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
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WSSyncGroupReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WSSyncGroupReq> CREATOR = new Parcelable.Creator<WSSyncGroupReq>() { // from class: com.huya.hysignal.jce.WSSyncGroupReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSSyncGroupReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WSSyncGroupReq wSSyncGroupReq = new WSSyncGroupReq();
            wSSyncGroupReq.readFrom(jceInputStream);
            return wSSyncGroupReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSSyncGroupReq[] newArray(int i) {
            return new WSSyncGroupReq[i];
        }
    };
    static Map<String, String> cache_mGroupId2Token;
    static ArrayList<String> cache_vGroupId;
    public ArrayList<String> vGroupId = null;
    public Map<String, String> mGroupId2Token = null;

    public String className() {
        return "HUYA.WSSyncGroupReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSSyncGroupReq";
    }

    public ArrayList<String> getVGroupId() {
        return this.vGroupId;
    }

    public void setVGroupId(ArrayList<String> arrayList) {
        this.vGroupId = arrayList;
    }

    public Map<String, String> getMGroupId2Token() {
        return this.mGroupId2Token;
    }

    public void setMGroupId2Token(Map<String, String> map) {
        this.mGroupId2Token = map;
    }

    public WSSyncGroupReq() {
        setVGroupId(null);
        setMGroupId2Token(this.mGroupId2Token);
    }

    public WSSyncGroupReq(ArrayList<String> arrayList, Map<String, String> map) {
        setVGroupId(arrayList);
        setMGroupId2Token(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSSyncGroupReq wSSyncGroupReq = (WSSyncGroupReq) obj;
        return JceUtil.equals(this.vGroupId, wSSyncGroupReq.vGroupId) && JceUtil.equals(this.mGroupId2Token, wSSyncGroupReq.mGroupId2Token);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.vGroupId), JceUtil.hashCode(this.mGroupId2Token)});
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
        Map<String, String> map = this.mGroupId2Token;
        if (map != null) {
            jceOutputStream.write((Map) map, 1);
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
        if (cache_mGroupId2Token == null) {
            HashMap map = new HashMap();
            cache_mGroupId2Token = map;
            map.put("", "");
        }
        setMGroupId2Token((Map) jceInputStream.read(cache_mGroupId2Token, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection) this.vGroupId, "vGroupId");
        jceDisplayer.display((Map) this.mGroupId2Token, "mGroupId2Token");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
