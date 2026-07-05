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
public class WSRegisterGroupRsp extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WSRegisterGroupRsp> CREATOR = new Parcelable.Creator<WSRegisterGroupRsp>() { // from class: com.huya.hysignal.jce.WSRegisterGroupRsp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSRegisterGroupRsp createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WSRegisterGroupRsp wSRegisterGroupRsp = new WSRegisterGroupRsp();
            wSRegisterGroupRsp.readFrom(jceInputStream);
            return wSRegisterGroupRsp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSRegisterGroupRsp[] newArray(int i) {
            return new WSRegisterGroupRsp[i];
        }
    };
    static ArrayList<String> cache_vSupportP2PGroupId;
    public int iResCode = 0;
    public ArrayList<String> vSupportP2PGroupId = null;

    public String className() {
        return "HUYA.WSRegisterGroupRsp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSRegisterGroupRsp";
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

    public WSRegisterGroupRsp() {
        setIResCode(0);
        setVSupportP2PGroupId(this.vSupportP2PGroupId);
    }

    public WSRegisterGroupRsp(int i, ArrayList<String> arrayList) {
        setIResCode(i);
        setVSupportP2PGroupId(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSRegisterGroupRsp wSRegisterGroupRsp = (WSRegisterGroupRsp) obj;
        return JceUtil.equals(this.iResCode, wSRegisterGroupRsp.iResCode) && JceUtil.equals(this.vSupportP2PGroupId, wSRegisterGroupRsp.vSupportP2PGroupId);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iResCode), JceUtil.hashCode(this.vSupportP2PGroupId)});
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
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iResCode, "iResCode");
        jceDisplayer.display((Collection) this.vSupportP2PGroupId, "vSupportP2PGroupId");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
