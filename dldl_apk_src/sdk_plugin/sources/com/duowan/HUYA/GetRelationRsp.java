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
public class GetRelationRsp extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<GetRelationRsp> CREATOR = new Parcelable.Creator<GetRelationRsp>() { // from class: com.duowan.HUYA.GetRelationRsp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetRelationRsp createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            GetRelationRsp getRelationRsp = new GetRelationRsp();
            getRelationRsp.readFrom(jceInputStream);
            return getRelationRsp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetRelationRsp[] newArray(int i) {
            return new GetRelationRsp[i];
        }
    };
    static RelationItem cache_tItem;
    public RelationItem tItem = null;

    public String className() {
        return "HUYA.GetRelationRsp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetRelationRsp";
    }

    public RelationItem getTItem() {
        return this.tItem;
    }

    public void setTItem(RelationItem relationItem) {
        this.tItem = relationItem;
    }

    public GetRelationRsp() {
        setTItem(null);
    }

    public GetRelationRsp(RelationItem relationItem) {
        setTItem(relationItem);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.tItem, ((GetRelationRsp) obj).tItem);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tItem)});
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
        RelationItem relationItem = this.tItem;
        if (relationItem != null) {
            jceOutputStream.write((JceStruct) relationItem, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tItem == null) {
            cache_tItem = new RelationItem();
        }
        setTItem((RelationItem) jceInputStream.read((JceStruct) cache_tItem, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((JceStruct) this.tItem, "tItem");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
