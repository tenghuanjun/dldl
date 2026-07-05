package com.huya.mtp.hyns.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UserId extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<UserId> CREATOR = new Parcelable.Creator<UserId>() { // from class: com.huya.mtp.hyns.api.UserId.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserId createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            UserId userId = new UserId();
            userId.readFrom(jceInputStream);
            return userId;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserId[] newArray(int i) {
            return new UserId[i];
        }
    };
    public long lUid = 0;
    public String sGuid = "";
    public String sToken = "";
    public String sHuYaUA = "";
    public String sCookie = "";
    public int iTokenType = 0;
    public String sDeviceInfo = "";

    public String className() {
        return "HUYA.UserId";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserId";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public String getSGuid() {
        return this.sGuid;
    }

    public void setSGuid(String str) {
        this.sGuid = str;
    }

    public String getSToken() {
        return this.sToken;
    }

    public void setSToken(String str) {
        this.sToken = str;
    }

    public String getSHuYaUA() {
        return this.sHuYaUA;
    }

    public void setSHuYaUA(String str) {
        this.sHuYaUA = str;
    }

    public String getSCookie() {
        return this.sCookie;
    }

    public void setSCookie(String str) {
        this.sCookie = str;
    }

    public int getITokenType() {
        return this.iTokenType;
    }

    public void setITokenType(int i) {
        this.iTokenType = i;
    }

    public String getSDeviceInfo() {
        return this.sDeviceInfo;
    }

    public void setSDeviceInfo(String str) {
        this.sDeviceInfo = str;
    }

    public UserId() {
        setLUid(0L);
        setSGuid(this.sGuid);
        setSToken(this.sToken);
        setSHuYaUA(this.sHuYaUA);
        setSCookie(this.sCookie);
        setITokenType(this.iTokenType);
        setSDeviceInfo(this.sDeviceInfo);
    }

    public UserId(long j, String str, String str2, String str3, String str4, int i, String str5) {
        setLUid(j);
        setSGuid(str);
        setSToken(str2);
        setSHuYaUA(str3);
        setSCookie(str4);
        setITokenType(i);
        setSDeviceInfo(str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserId userId = (UserId) obj;
        return JceUtil.equals(this.lUid, userId.lUid) && JceUtil.equals(this.sGuid, userId.sGuid) && JceUtil.equals(this.sToken, userId.sToken) && JceUtil.equals(this.sHuYaUA, userId.sHuYaUA) && JceUtil.equals(this.sCookie, userId.sCookie) && JceUtil.equals(this.iTokenType, userId.iTokenType) && JceUtil.equals(this.sDeviceInfo, userId.sDeviceInfo);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lUid), JceUtil.hashCode(this.sGuid), JceUtil.hashCode(this.sToken), JceUtil.hashCode(this.sHuYaUA), JceUtil.hashCode(this.sCookie), JceUtil.hashCode(this.iTokenType), JceUtil.hashCode(this.sDeviceInfo)});
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
        jceOutputStream.write(this.lUid, 0);
        String str = this.sGuid;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sToken;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        String str3 = this.sHuYaUA;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
        String str4 = this.sCookie;
        if (str4 != null) {
            jceOutputStream.write(str4, 4);
        }
        jceOutputStream.write(this.iTokenType, 5);
        String str5 = this.sDeviceInfo;
        if (str5 != null) {
            jceOutputStream.write(str5, 6);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setSGuid(jceInputStream.readString(1, false));
        setSToken(jceInputStream.readString(2, false));
        setSHuYaUA(jceInputStream.readString(3, false));
        setSCookie(jceInputStream.readString(4, false));
        setITokenType(jceInputStream.read(this.iTokenType, 5, false));
        setSDeviceInfo(jceInputStream.readString(6, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.sGuid, "sGuid");
        jceDisplayer.display(this.sToken, "sToken");
        jceDisplayer.display(this.sHuYaUA, "sHuYaUA");
        jceDisplayer.display(this.sCookie, "sCookie");
        jceDisplayer.display(this.iTokenType, "iTokenType");
        jceDisplayer.display(this.sDeviceInfo, "sDeviceInfo");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
