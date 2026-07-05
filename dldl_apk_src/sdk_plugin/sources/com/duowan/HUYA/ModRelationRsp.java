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
public class ModRelationRsp extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<ModRelationRsp> CREATOR = new Parcelable.Creator<ModRelationRsp>() { // from class: com.duowan.HUYA.ModRelationRsp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModRelationRsp createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            ModRelationRsp modRelationRsp = new ModRelationRsp();
            modRelationRsp.readFrom(jceInputStream);
            return modRelationRsp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModRelationRsp[] newArray(int i) {
            return new ModRelationRsp[i];
        }
    };
    public int iNewRelation = 0;
    public String sMessage = "";

    public String className() {
        return "HUYA.ModRelationRsp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ModRelationRsp";
    }

    public int getINewRelation() {
        return this.iNewRelation;
    }

    public void setINewRelation(int i) {
        this.iNewRelation = i;
    }

    public String getSMessage() {
        return this.sMessage;
    }

    public void setSMessage(String str) {
        this.sMessage = str;
    }

    public ModRelationRsp() {
        setINewRelation(0);
        setSMessage(this.sMessage);
    }

    public ModRelationRsp(int i, String str) {
        setINewRelation(i);
        setSMessage(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ModRelationRsp modRelationRsp = (ModRelationRsp) obj;
        return JceUtil.equals(this.iNewRelation, modRelationRsp.iNewRelation) && JceUtil.equals(this.sMessage, modRelationRsp.sMessage);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iNewRelation), JceUtil.hashCode(this.sMessage)});
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
        jceOutputStream.write(this.iNewRelation, 0);
        String str = this.sMessage;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setINewRelation(jceInputStream.read(this.iNewRelation, 0, false));
        setSMessage(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iNewRelation, "iNewRelation");
        jceDisplayer.display(this.sMessage, "sMessage");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
