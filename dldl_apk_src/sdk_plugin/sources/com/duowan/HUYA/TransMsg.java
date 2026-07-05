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
public class TransMsg extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<TransMsg> CREATOR = new Parcelable.Creator<TransMsg>() { // from class: com.duowan.HUYA.TransMsg.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransMsg createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            TransMsg transMsg = new TransMsg();
            transMsg.readFrom(jceInputStream);
            return transMsg;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransMsg[] newArray(int i) {
            return new TransMsg[i];
        }
    };
    static byte[] cache_vContents;
    public String sBuffer = "";
    public int iType = 0;
    public byte[] vContents = null;

    public String className() {
        return "HUYA.TransMsg";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.TransMsg";
    }

    public String getSBuffer() {
        return this.sBuffer;
    }

    public void setSBuffer(String str) {
        this.sBuffer = str;
    }

    public int getIType() {
        return this.iType;
    }

    public void setIType(int i) {
        this.iType = i;
    }

    public byte[] getVContents() {
        return this.vContents;
    }

    public void setVContents(byte[] bArr) {
        this.vContents = bArr;
    }

    public TransMsg() {
        setSBuffer("");
        setIType(this.iType);
        setVContents(this.vContents);
    }

    public TransMsg(String str, int i, byte[] bArr) {
        setSBuffer(str);
        setIType(i);
        setVContents(bArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TransMsg transMsg = (TransMsg) obj;
        return JceUtil.equals(this.sBuffer, transMsg.sBuffer) && JceUtil.equals(this.iType, transMsg.iType) && JceUtil.equals(this.vContents, transMsg.vContents);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sBuffer), JceUtil.hashCode(this.iType), JceUtil.hashCode(this.vContents)});
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
        String str = this.sBuffer;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        jceOutputStream.write(this.iType, 1);
        byte[] bArr = this.vContents;
        if (bArr != null) {
            jceOutputStream.write(bArr, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSBuffer(jceInputStream.readString(0, false));
        setIType(jceInputStream.read(this.iType, 1, false));
        if (cache_vContents == null) {
            cache_vContents = new byte[]{0};
        }
        setVContents(jceInputStream.read(cache_vContents, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sBuffer, "sBuffer");
        jceDisplayer.display(this.iType, "iType");
        jceDisplayer.display(this.vContents, "vContents");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
