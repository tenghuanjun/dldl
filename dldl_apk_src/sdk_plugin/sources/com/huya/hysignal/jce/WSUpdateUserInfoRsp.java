package com.huya.hysignal.jce;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WSUpdateUserInfoRsp extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WSUpdateUserInfoRsp> CREATOR = new Parcelable.Creator<WSUpdateUserInfoRsp>() { // from class: com.huya.hysignal.jce.WSUpdateUserInfoRsp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSUpdateUserInfoRsp createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WSUpdateUserInfoRsp wSUpdateUserInfoRsp = new WSUpdateUserInfoRsp();
            wSUpdateUserInfoRsp.readFrom(jceInputStream);
            return wSUpdateUserInfoRsp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSUpdateUserInfoRsp[] newArray(int i) {
            return new WSUpdateUserInfoRsp[i];
        }
    };
    public int iResCode = 0;

    public String className() {
        return "HUYA.WSUpdateUserInfoRsp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSUpdateUserInfoRsp";
    }

    public int getIResCode() {
        return this.iResCode;
    }

    public void setIResCode(int i) {
        this.iResCode = i;
    }

    public WSUpdateUserInfoRsp() {
        setIResCode(0);
    }

    public WSUpdateUserInfoRsp(int i) {
        setIResCode(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.iResCode, ((WSUpdateUserInfoRsp) obj).iResCode);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iResCode)});
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
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIResCode(jceInputStream.read(this.iResCode, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display(this.iResCode, "iResCode");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
