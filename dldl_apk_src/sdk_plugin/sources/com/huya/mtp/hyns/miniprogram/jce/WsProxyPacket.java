package com.huya.mtp.hyns.miniprogram.jce;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WsProxyPacket extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WsProxyPacket> CREATOR = new Parcelable.Creator<WsProxyPacket>() { // from class: com.huya.mtp.hyns.miniprogram.jce.WsProxyPacket.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WsProxyPacket createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WsProxyPacket wsProxyPacket = new WsProxyPacket();
            wsProxyPacket.readFrom(jceInputStream);
            return wsProxyPacket;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WsProxyPacket[] newArray(int i) {
            return new WsProxyPacket[i];
        }
    };
    static byte[] cache_vData;
    public int iCommand = 0;
    public byte[] vData = null;

    public String className() {
        return "HUYA.WsProxyPacket";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.huya.mtp.hyns.miniprogram.jce.WsProxyPacket";
    }

    public int getICommand() {
        return this.iCommand;
    }

    public void setICommand(int i) {
        this.iCommand = i;
    }

    public byte[] getVData() {
        return this.vData;
    }

    public void setVData(byte[] bArr) {
        this.vData = bArr;
    }

    public WsProxyPacket() {
        setICommand(0);
        setVData(this.vData);
    }

    public WsProxyPacket(int i, byte[] bArr) {
        setICommand(i);
        setVData(bArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WsProxyPacket wsProxyPacket = (WsProxyPacket) obj;
        return JceUtil.equals(this.iCommand, wsProxyPacket.iCommand) && JceUtil.equals(this.vData, wsProxyPacket.vData);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iCommand), JceUtil.hashCode(this.vData)});
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
        jceOutputStream.write(this.iCommand, 0);
        byte[] bArr = this.vData;
        if (bArr != null) {
            jceOutputStream.write(bArr, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setICommand(jceInputStream.read(this.iCommand, 0, false));
        if (cache_vData == null) {
            cache_vData = new byte[]{0};
        }
        setVData(jceInputStream.read(cache_vData, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iCommand, "iCommand");
        jceDisplayer.display(this.vData, "vData");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
