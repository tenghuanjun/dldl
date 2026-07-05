package com.huya.hysignal.jce;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import com.huya.mtp.hyns.stat.NSStatReporter;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebSocketCommand extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WebSocketCommand> CREATOR = new Parcelable.Creator<WebSocketCommand>() { // from class: com.huya.hysignal.jce.WebSocketCommand.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WebSocketCommand createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WebSocketCommand webSocketCommand = new WebSocketCommand();
            webSocketCommand.readFrom(jceInputStream);
            return webSocketCommand;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WebSocketCommand[] newArray(int i) {
            return new WebSocketCommand[i];
        }
    };
    static byte[] cache_vData;
    public int iCmdType = 0;
    public byte[] vData = null;
    public long lRequestId = 0;
    public String traceId = "";
    public int iEncryptType = 0;
    public long lTime = 0;

    public String className() {
        return "HUYA.WebSocketCommand";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WebSocketCommand";
    }

    public int getICmdType() {
        return this.iCmdType;
    }

    public void setICmdType(int i) {
        this.iCmdType = i;
    }

    public byte[] getVData() {
        return this.vData;
    }

    public void setVData(byte[] bArr) {
        this.vData = bArr;
    }

    public long getLRequestId() {
        return this.lRequestId;
    }

    public void setLRequestId(long j) {
        this.lRequestId = j;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String str) {
        this.traceId = str;
    }

    public int getIEncryptType() {
        return this.iEncryptType;
    }

    public void setIEncryptType(int i) {
        this.iEncryptType = i;
    }

    public long getLTime() {
        return this.lTime;
    }

    public void setLTime(long j) {
        this.lTime = j;
    }

    public WebSocketCommand() {
        setICmdType(0);
        setVData(this.vData);
        setLRequestId(this.lRequestId);
        setTraceId(this.traceId);
        setIEncryptType(this.iEncryptType);
        setLTime(this.lTime);
    }

    public WebSocketCommand(int i, byte[] bArr, long j, String str, int i2, long j2) {
        setICmdType(i);
        setVData(bArr);
        setLRequestId(j);
        setTraceId(str);
        setIEncryptType(i2);
        setLTime(j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WebSocketCommand webSocketCommand = (WebSocketCommand) obj;
        return JceUtil.equals(this.iCmdType, webSocketCommand.iCmdType) && JceUtil.equals(this.vData, webSocketCommand.vData) && JceUtil.equals(this.lRequestId, webSocketCommand.lRequestId) && JceUtil.equals(this.traceId, webSocketCommand.traceId) && JceUtil.equals(this.iEncryptType, webSocketCommand.iEncryptType) && JceUtil.equals(this.lTime, webSocketCommand.lTime);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iCmdType), JceUtil.hashCode(this.vData), JceUtil.hashCode(this.lRequestId), JceUtil.hashCode(this.traceId), JceUtil.hashCode(this.iEncryptType), JceUtil.hashCode(this.lTime)});
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
        jceOutputStream.write(this.iCmdType, 0);
        byte[] bArr = this.vData;
        if (bArr != null) {
            jceOutputStream.write(bArr, 1);
        }
        jceOutputStream.write(this.lRequestId, 2);
        String str = this.traceId;
        if (str != null) {
            jceOutputStream.write(str, 3);
        }
        jceOutputStream.write(this.iEncryptType, 4);
        jceOutputStream.write(this.lTime, 5);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setICmdType(jceInputStream.read(this.iCmdType, 0, false));
        if (cache_vData == null) {
            cache_vData = new byte[]{0};
        }
        setVData(jceInputStream.read(cache_vData, 1, false));
        setLRequestId(jceInputStream.read(this.lRequestId, 2, false));
        setTraceId(jceInputStream.readString(3, false));
        setIEncryptType(jceInputStream.read(this.iEncryptType, 4, false));
        setLTime(jceInputStream.read(this.lTime, 5, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iCmdType, "iCmdType");
        jceDisplayer.display(this.vData, "vData");
        jceDisplayer.display(this.lRequestId, "lRequestId");
        jceDisplayer.display(this.traceId, NSStatReporter.NS_TRACE_ID);
        jceDisplayer.display(this.iEncryptType, "iEncryptType");
        jceDisplayer.display(this.lTime, "lTime");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
