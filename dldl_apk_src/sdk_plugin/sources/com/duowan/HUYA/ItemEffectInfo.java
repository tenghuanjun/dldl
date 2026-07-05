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
public class ItemEffectInfo extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<ItemEffectInfo> CREATOR = new Parcelable.Creator<ItemEffectInfo>() { // from class: com.duowan.HUYA.ItemEffectInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ItemEffectInfo createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            ItemEffectInfo itemEffectInfo = new ItemEffectInfo();
            itemEffectInfo.readFrom(jceInputStream);
            return itemEffectInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ItemEffectInfo[] newArray(int i) {
            return new ItemEffectInfo[i];
        }
    };
    public int iPriceLevel = 0;
    public int iStreamDuration = 0;
    public int iShowType = 0;

    public String className() {
        return "HUYA.ItemEffectInfo";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ItemEffectInfo";
    }

    public int getIPriceLevel() {
        return this.iPriceLevel;
    }

    public void setIPriceLevel(int i) {
        this.iPriceLevel = i;
    }

    public int getIStreamDuration() {
        return this.iStreamDuration;
    }

    public void setIStreamDuration(int i) {
        this.iStreamDuration = i;
    }

    public int getIShowType() {
        return this.iShowType;
    }

    public void setIShowType(int i) {
        this.iShowType = i;
    }

    public ItemEffectInfo() {
        setIPriceLevel(0);
        setIStreamDuration(this.iStreamDuration);
        setIShowType(this.iShowType);
    }

    public ItemEffectInfo(int i, int i2, int i3) {
        setIPriceLevel(i);
        setIStreamDuration(i2);
        setIShowType(i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ItemEffectInfo itemEffectInfo = (ItemEffectInfo) obj;
        return JceUtil.equals(this.iPriceLevel, itemEffectInfo.iPriceLevel) && JceUtil.equals(this.iStreamDuration, itemEffectInfo.iStreamDuration) && JceUtil.equals(this.iShowType, itemEffectInfo.iShowType);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iPriceLevel), JceUtil.hashCode(this.iStreamDuration), JceUtil.hashCode(this.iShowType)});
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
        jceOutputStream.write(this.iPriceLevel, 0);
        jceOutputStream.write(this.iStreamDuration, 1);
        jceOutputStream.write(this.iShowType, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIPriceLevel(jceInputStream.read(this.iPriceLevel, 0, false));
        setIStreamDuration(jceInputStream.read(this.iStreamDuration, 1, false));
        setIShowType(jceInputStream.read(this.iShowType, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iPriceLevel, "iPriceLevel");
        jceDisplayer.display(this.iStreamDuration, "iStreamDuration");
        jceDisplayer.display(this.iShowType, "iShowType");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
