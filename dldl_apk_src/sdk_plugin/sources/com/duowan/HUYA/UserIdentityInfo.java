package com.duowan.HUYA;

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

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class UserIdentityInfo extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<UserIdentityInfo> CREATOR = new Parcelable.Creator<UserIdentityInfo>() { // from class: com.duowan.HUYA.UserIdentityInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserIdentityInfo createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            UserIdentityInfo userIdentityInfo = new UserIdentityInfo();
            userIdentityInfo.readFrom(jceInputStream);
            return userIdentityInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserIdentityInfo[] newArray(int i) {
            return new UserIdentityInfo[i];
        }
    };
    static ArrayList<DecorationInfo> cache_vDecorationPrefix;
    static ArrayList<DecorationInfo> cache_vDecorationSuffix;
    public ArrayList<DecorationInfo> vDecorationPrefix = null;
    public ArrayList<DecorationInfo> vDecorationSuffix = null;

    public String className() {
        return "HUYA.UserIdentityInfo";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserIdentityInfo";
    }

    public ArrayList<DecorationInfo> getVDecorationPrefix() {
        return this.vDecorationPrefix;
    }

    public void setVDecorationPrefix(ArrayList<DecorationInfo> arrayList) {
        this.vDecorationPrefix = arrayList;
    }

    public ArrayList<DecorationInfo> getVDecorationSuffix() {
        return this.vDecorationSuffix;
    }

    public void setVDecorationSuffix(ArrayList<DecorationInfo> arrayList) {
        this.vDecorationSuffix = arrayList;
    }

    public UserIdentityInfo() {
        setVDecorationPrefix(null);
        setVDecorationSuffix(this.vDecorationSuffix);
    }

    public UserIdentityInfo(ArrayList<DecorationInfo> arrayList, ArrayList<DecorationInfo> arrayList2) {
        setVDecorationPrefix(arrayList);
        setVDecorationSuffix(arrayList2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserIdentityInfo userIdentityInfo = (UserIdentityInfo) obj;
        return JceUtil.equals(this.vDecorationPrefix, userIdentityInfo.vDecorationPrefix) && JceUtil.equals(this.vDecorationSuffix, userIdentityInfo.vDecorationSuffix);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.vDecorationPrefix), JceUtil.hashCode(this.vDecorationSuffix)});
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
        ArrayList<DecorationInfo> arrayList = this.vDecorationPrefix;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
        ArrayList<DecorationInfo> arrayList2 = this.vDecorationSuffix;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vDecorationPrefix == null) {
            cache_vDecorationPrefix = new ArrayList<>();
            cache_vDecorationPrefix.add(new DecorationInfo());
        }
        setVDecorationPrefix((ArrayList) jceInputStream.read(cache_vDecorationPrefix, 0, false));
        if (cache_vDecorationSuffix == null) {
            cache_vDecorationSuffix = new ArrayList<>();
            cache_vDecorationSuffix.add(new DecorationInfo());
        }
        setVDecorationSuffix((ArrayList) jceInputStream.read(cache_vDecorationSuffix, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection) this.vDecorationPrefix, "vDecorationPrefix");
        jceDisplayer.display((Collection) this.vDecorationSuffix, "vDecorationSuffix");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
