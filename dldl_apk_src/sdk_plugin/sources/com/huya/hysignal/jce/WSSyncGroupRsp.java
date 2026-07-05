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
public class WSSyncGroupRsp extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WSSyncGroupRsp> CREATOR = new Parcelable.Creator<WSSyncGroupRsp>() { // from class: com.huya.hysignal.jce.WSSyncGroupRsp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSSyncGroupRsp createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WSSyncGroupRsp wSSyncGroupRsp = new WSSyncGroupRsp();
            wSSyncGroupRsp.readFrom(jceInputStream);
            return wSSyncGroupRsp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSSyncGroupRsp[] newArray(int i) {
            return new WSSyncGroupRsp[i];
        }
    };
    static ArrayList<String> cache_vRegisterGroupId;
    static ArrayList<String> cache_vSupportP2PGroupId;
    static ArrayList<String> cache_vVerifyFailGroupId;
    public int iResCode = 0;
    public ArrayList<String> vSupportP2PGroupId = null;
    public ArrayList<String> vRegisterGroupId = null;
    public ArrayList<String> vVerifyFailGroupId = null;

    public String className() {
        return "HUYA.WSSyncGroupRsp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSSyncGroupRsp";
    }

    public int getIResCode() {
        return this.iResCode;
    }

    public void setIResCode(int i) {
        this.iResCode = i;
    }

    public ArrayList<String> getVSupportP2PGroupId() {
        return this.vSupportP2PGroupId;
    }

    public void setVSupportP2PGroupId(ArrayList<String> arrayList) {
        this.vSupportP2PGroupId = arrayList;
    }

    public ArrayList<String> getVRegisterGroupId() {
        return this.vRegisterGroupId;
    }

    public void setVRegisterGroupId(ArrayList<String> arrayList) {
        this.vRegisterGroupId = arrayList;
    }

    public ArrayList<String> getVVerifyFailGroupId() {
        return this.vVerifyFailGroupId;
    }

    public void setVVerifyFailGroupId(ArrayList<String> arrayList) {
        this.vVerifyFailGroupId = arrayList;
    }

    public WSSyncGroupRsp() {
        setIResCode(0);
        setVSupportP2PGroupId(this.vSupportP2PGroupId);
        setVRegisterGroupId(this.vRegisterGroupId);
        setVVerifyFailGroupId(this.vVerifyFailGroupId);
    }

    public WSSyncGroupRsp(int i, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        setIResCode(i);
        setVSupportP2PGroupId(arrayList);
        setVRegisterGroupId(arrayList2);
        setVVerifyFailGroupId(arrayList3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSSyncGroupRsp wSSyncGroupRsp = (WSSyncGroupRsp) obj;
        return JceUtil.equals(this.iResCode, wSSyncGroupRsp.iResCode) && JceUtil.equals(this.vSupportP2PGroupId, wSSyncGroupRsp.vSupportP2PGroupId) && JceUtil.equals(this.vRegisterGroupId, wSSyncGroupRsp.vRegisterGroupId) && JceUtil.equals(this.vVerifyFailGroupId, wSSyncGroupRsp.vVerifyFailGroupId);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iResCode), JceUtil.hashCode(this.vSupportP2PGroupId), JceUtil.hashCode(this.vRegisterGroupId), JceUtil.hashCode(this.vVerifyFailGroupId)});
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
        jceOutputStream.write(this.iResCode, 0);
        ArrayList<String> arrayList = this.vSupportP2PGroupId;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 1);
        }
        ArrayList<String> arrayList2 = this.vRegisterGroupId;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 2);
        }
        ArrayList<String> arrayList3 = this.vVerifyFailGroupId;
        if (arrayList3 != null) {
            jceOutputStream.write((Collection) arrayList3, 3);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIResCode(jceInputStream.read(this.iResCode, 0, false));
        if (cache_vSupportP2PGroupId == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vSupportP2PGroupId = arrayList;
            arrayList.add("");
        }
        setVSupportP2PGroupId((ArrayList) jceInputStream.read(cache_vSupportP2PGroupId, 1, false));
        if (cache_vRegisterGroupId == null) {
            ArrayList<String> arrayList2 = new ArrayList<>();
            cache_vRegisterGroupId = arrayList2;
            arrayList2.add("");
        }
        setVRegisterGroupId((ArrayList) jceInputStream.read(cache_vRegisterGroupId, 2, false));
        if (cache_vVerifyFailGroupId == null) {
            ArrayList<String> arrayList3 = new ArrayList<>();
            cache_vVerifyFailGroupId = arrayList3;
            arrayList3.add("");
        }
        setVVerifyFailGroupId((ArrayList) jceInputStream.read(cache_vVerifyFailGroupId, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iResCode, "iResCode");
        jceDisplayer.display((Collection) this.vSupportP2PGroupId, "vSupportP2PGroupId");
        jceDisplayer.display((Collection) this.vRegisterGroupId, "vRegisterGroupId");
        jceDisplayer.display((Collection) this.vVerifyFailGroupId, "vVerifyFailGroupId");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
