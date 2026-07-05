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
public class LiveLaunchRsp extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<LiveLaunchRsp> CREATOR = new Parcelable.Creator<LiveLaunchRsp>() { // from class: com.duowan.HUYA.LiveLaunchRsp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LiveLaunchRsp createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            LiveLaunchRsp liveLaunchRsp = new LiveLaunchRsp();
            liveLaunchRsp.readFrom(jceInputStream);
            return liveLaunchRsp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LiveLaunchRsp[] newArray(int i) {
            return new LiveLaunchRsp[i];
        }
    };
    static int cache_eAccess;
    static ArrayList<LiveProxyValue> cache_vProxyList;
    public String sGuid = "";
    public int iTime = 0;
    public ArrayList<LiveProxyValue> vProxyList = null;
    public int eAccess = 0;
    public String sClientIp = "";

    public String className() {
        return "HUYA.LiveLaunchRsp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.LiveLaunchRsp";
    }

    public String getSGuid() {
        return this.sGuid;
    }

    public void setSGuid(String str) {
        this.sGuid = str;
    }

    public int getITime() {
        return this.iTime;
    }

    public void setITime(int i) {
        this.iTime = i;
    }

    public ArrayList<LiveProxyValue> getVProxyList() {
        return this.vProxyList;
    }

    public void setVProxyList(ArrayList<LiveProxyValue> arrayList) {
        this.vProxyList = arrayList;
    }

    public int getEAccess() {
        return this.eAccess;
    }

    public void setEAccess(int i) {
        this.eAccess = i;
    }

    public String getSClientIp() {
        return this.sClientIp;
    }

    public void setSClientIp(String str) {
        this.sClientIp = str;
    }

    public LiveLaunchRsp() {
        setSGuid("");
        setITime(this.iTime);
        setVProxyList(this.vProxyList);
        setEAccess(this.eAccess);
        setSClientIp(this.sClientIp);
    }

    public LiveLaunchRsp(String str, int i, ArrayList<LiveProxyValue> arrayList, int i2, String str2) {
        setSGuid(str);
        setITime(i);
        setVProxyList(arrayList);
        setEAccess(i2);
        setSClientIp(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LiveLaunchRsp liveLaunchRsp = (LiveLaunchRsp) obj;
        return JceUtil.equals(this.sGuid, liveLaunchRsp.sGuid) && JceUtil.equals(this.iTime, liveLaunchRsp.iTime) && JceUtil.equals(this.vProxyList, liveLaunchRsp.vProxyList) && JceUtil.equals(this.eAccess, liveLaunchRsp.eAccess) && JceUtil.equals(this.sClientIp, liveLaunchRsp.sClientIp);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sGuid), JceUtil.hashCode(this.iTime), JceUtil.hashCode(this.vProxyList), JceUtil.hashCode(this.eAccess), JceUtil.hashCode(this.sClientIp)});
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
        String str = this.sGuid;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        jceOutputStream.write(this.iTime, 1);
        ArrayList<LiveProxyValue> arrayList = this.vProxyList;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 2);
        }
        jceOutputStream.write(this.eAccess, 3);
        String str2 = this.sClientIp;
        if (str2 != null) {
            jceOutputStream.write(str2, 4);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSGuid(jceInputStream.readString(0, false));
        setITime(jceInputStream.read(this.iTime, 1, false));
        if (cache_vProxyList == null) {
            cache_vProxyList = new ArrayList<>();
            cache_vProxyList.add(new LiveProxyValue());
        }
        setVProxyList((ArrayList) jceInputStream.read(cache_vProxyList, 2, false));
        setEAccess(jceInputStream.read(this.eAccess, 3, false));
        setSClientIp(jceInputStream.readString(4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sGuid, "sGuid");
        jceDisplayer.display(this.iTime, "iTime");
        jceDisplayer.display((Collection) this.vProxyList, "vProxyList");
        jceDisplayer.display(this.eAccess, "eAccess");
        jceDisplayer.display(this.sClientIp, "sClientIp");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
