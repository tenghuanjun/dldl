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
public class AppSrcInfo extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<AppSrcInfo> CREATOR = new Parcelable.Creator<AppSrcInfo>() { // from class: com.huya.hysignal.jce.AppSrcInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppSrcInfo createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            AppSrcInfo appSrcInfo = new AppSrcInfo();
            appSrcInfo.readFrom(jceInputStream);
            return appSrcInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppSrcInfo[] newArray(int i) {
            return new AppSrcInfo[i];
        }
    };
    public String sAppId = "";
    public String sCountry = "";
    public String sLang = "";

    public String className() {
        return "HUYA.AppSrcInfo";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.AppSrcInfo";
    }

    public String getSAppId() {
        return this.sAppId;
    }

    public void setSAppId(String str) {
        this.sAppId = str;
    }

    public String getSCountry() {
        return this.sCountry;
    }

    public void setSCountry(String str) {
        this.sCountry = str;
    }

    public String getSLang() {
        return this.sLang;
    }

    public void setSLang(String str) {
        this.sLang = str;
    }

    public AppSrcInfo() {
        setSAppId("");
        setSCountry(this.sCountry);
        setSLang(this.sLang);
    }

    public AppSrcInfo(String str, String str2, String str3) {
        setSAppId(str);
        setSCountry(str2);
        setSLang(str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppSrcInfo appSrcInfo = (AppSrcInfo) obj;
        return JceUtil.equals(this.sAppId, appSrcInfo.sAppId) && JceUtil.equals(this.sCountry, appSrcInfo.sCountry) && JceUtil.equals(this.sLang, appSrcInfo.sLang);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sAppId), JceUtil.hashCode(this.sCountry), JceUtil.hashCode(this.sLang)});
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
        String str = this.sAppId;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sCountry;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
        String str3 = this.sLang;
        if (str3 != null) {
            jceOutputStream.write(str3, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSAppId(jceInputStream.readString(0, false));
        setSCountry(jceInputStream.readString(1, false));
        setSLang(jceInputStream.readString(2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sAppId, "sAppId");
        jceDisplayer.display(this.sCountry, "sCountry");
        jceDisplayer.display(this.sLang, "sLang");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
