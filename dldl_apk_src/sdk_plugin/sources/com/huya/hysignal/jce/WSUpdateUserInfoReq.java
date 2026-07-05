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
public class WSUpdateUserInfoReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WSUpdateUserInfoReq> CREATOR = new Parcelable.Creator<WSUpdateUserInfoReq>() { // from class: com.huya.hysignal.jce.WSUpdateUserInfoReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSUpdateUserInfoReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WSUpdateUserInfoReq wSUpdateUserInfoReq = new WSUpdateUserInfoReq();
            wSUpdateUserInfoReq.readFrom(jceInputStream);
            return wSUpdateUserInfoReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSUpdateUserInfoReq[] newArray(int i) {
            return new WSUpdateUserInfoReq[i];
        }
    };
    public String sAppSrc = "";
    public String sGuid = "";
    public int iReportMsgIdRatio = 0;
    public int iSupportAck = 0;

    public String className() {
        return "HUYA.WSUpdateUserInfoReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSUpdateUserInfoReq";
    }

    public String getSAppSrc() {
        return this.sAppSrc;
    }

    public void setSAppSrc(String str) {
        this.sAppSrc = str;
    }

    public String getSGuid() {
        return this.sGuid;
    }

    public void setSGuid(String str) {
        this.sGuid = str;
    }

    public int getIReportMsgIdRatio() {
        return this.iReportMsgIdRatio;
    }

    public void setIReportMsgIdRatio(int i) {
        this.iReportMsgIdRatio = i;
    }

    public int getISupportAck() {
        return this.iSupportAck;
    }

    public void setISupportAck(int i) {
        this.iSupportAck = i;
    }

    public WSUpdateUserInfoReq() {
        setSAppSrc("");
        setSGuid(this.sGuid);
        setIReportMsgIdRatio(this.iReportMsgIdRatio);
        setISupportAck(this.iSupportAck);
    }

    public WSUpdateUserInfoReq(String str, String str2, int i, int i2) {
        setSAppSrc(str);
        setSGuid(str2);
        setIReportMsgIdRatio(i);
        setISupportAck(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSUpdateUserInfoReq wSUpdateUserInfoReq = (WSUpdateUserInfoReq) obj;
        return JceUtil.equals(this.sAppSrc, wSUpdateUserInfoReq.sAppSrc) && JceUtil.equals(this.sGuid, wSUpdateUserInfoReq.sGuid) && JceUtil.equals(this.iReportMsgIdRatio, wSUpdateUserInfoReq.iReportMsgIdRatio) && JceUtil.equals(this.iSupportAck, wSUpdateUserInfoReq.iSupportAck);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sAppSrc), JceUtil.hashCode(this.sGuid), JceUtil.hashCode(this.iReportMsgIdRatio), JceUtil.hashCode(this.iSupportAck)});
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
        String str = this.sAppSrc;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sGuid;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
        jceOutputStream.write(this.iReportMsgIdRatio, 2);
        jceOutputStream.write(this.iSupportAck, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSAppSrc(jceInputStream.readString(0, false));
        setSGuid(jceInputStream.readString(1, false));
        setIReportMsgIdRatio(jceInputStream.read(this.iReportMsgIdRatio, 2, false));
        setISupportAck(jceInputStream.read(this.iSupportAck, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sAppSrc, "sAppSrc");
        jceDisplayer.display(this.sGuid, "sGuid");
        jceDisplayer.display(this.iReportMsgIdRatio, "iReportMsgIdRatio");
        jceDisplayer.display(this.iSupportAck, "iSupportAck");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
