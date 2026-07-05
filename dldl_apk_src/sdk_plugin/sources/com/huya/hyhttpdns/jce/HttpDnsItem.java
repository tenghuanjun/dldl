package com.huya.hyhttpdns.jce;

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

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsItem extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<HttpDnsItem> CREATOR = new Parcelable.Creator<HttpDnsItem>() { // from class: com.huya.hyhttpdns.jce.HttpDnsItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HttpDnsItem createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            HttpDnsItem httpDnsItem = new HttpDnsItem();
            httpDnsItem.readFrom(jceInputStream);
            return httpDnsItem;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HttpDnsItem[] newArray(int i) {
            return new HttpDnsItem[i];
        }
    };
    static ArrayList<String> cache_vIp;
    static ArrayList<String> cache_vIpv6;
    public ArrayList<String> vIp = null;
    public long iExpireTime = 0;
    public ArrayList<String> vIpv6 = null;

    public String className() {
        return "HUYA.HttpDnsItem";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.HttpDnsItem";
    }

    public ArrayList<String> getVIp() {
        return this.vIp;
    }

    public void setVIp(ArrayList<String> arrayList) {
        this.vIp = arrayList;
    }

    public long getIExpireTime() {
        return this.iExpireTime;
    }

    public void setIExpireTime(long j) {
        this.iExpireTime = j;
    }

    public ArrayList<String> getVIpv6() {
        return this.vIpv6;
    }

    public void setVIpv6(ArrayList<String> arrayList) {
        this.vIpv6 = arrayList;
    }

    public HttpDnsItem() {
        setVIp(null);
        setIExpireTime(this.iExpireTime);
        setVIpv6(this.vIpv6);
    }

    public HttpDnsItem(ArrayList<String> arrayList, long j, ArrayList<String> arrayList2) {
        setVIp(arrayList);
        setIExpireTime(j);
        setVIpv6(arrayList2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HttpDnsItem httpDnsItem = (HttpDnsItem) obj;
        return JceUtil.equals(this.vIp, httpDnsItem.vIp) && JceUtil.equals(this.iExpireTime, httpDnsItem.iExpireTime) && JceUtil.equals(this.vIpv6, httpDnsItem.vIpv6);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.vIp), JceUtil.hashCode(this.iExpireTime), JceUtil.hashCode(this.vIpv6)});
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
        ArrayList<String> arrayList = this.vIp;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
        jceOutputStream.write(this.iExpireTime, 1);
        ArrayList<String> arrayList2 = this.vIpv6;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vIp == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vIp = arrayList;
            arrayList.add("");
        }
        setVIp((ArrayList) jceInputStream.read(cache_vIp, 0, false));
        setIExpireTime(jceInputStream.read(this.iExpireTime, 1, false));
        if (cache_vIpv6 == null) {
            ArrayList<String> arrayList2 = new ArrayList<>();
            cache_vIpv6 = arrayList2;
            arrayList2.add("");
        }
        setVIpv6((ArrayList) jceInputStream.read(cache_vIpv6, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection) this.vIp, "vIp");
        jceDisplayer.display(this.iExpireTime, "iExpireTime");
        jceDisplayer.display((Collection) this.vIpv6, "vIpv6");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
