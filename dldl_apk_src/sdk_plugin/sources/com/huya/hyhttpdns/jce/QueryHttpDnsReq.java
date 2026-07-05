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
public class QueryHttpDnsReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<QueryHttpDnsReq> CREATOR = new Parcelable.Creator<QueryHttpDnsReq>() { // from class: com.huya.hyhttpdns.jce.QueryHttpDnsReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public QueryHttpDnsReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            QueryHttpDnsReq queryHttpDnsReq = new QueryHttpDnsReq();
            queryHttpDnsReq.readFrom(jceInputStream);
            return queryHttpDnsReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public QueryHttpDnsReq[] newArray(int i) {
            return new QueryHttpDnsReq[i];
        }
    };
    static ArrayList<String> cache_vDomain;
    public long lUid = 0;
    public String sUA = "";
    public ArrayList<String> vDomain = null;
    public String sAppSrc = "";

    public String className() {
        return "HUYA.QueryHttpDnsReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.QueryHttpDnsReq";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public String getSUA() {
        return this.sUA;
    }

    public void setSUA(String str) {
        this.sUA = str;
    }

    public ArrayList<String> getVDomain() {
        return this.vDomain;
    }

    public void setVDomain(ArrayList<String> arrayList) {
        this.vDomain = arrayList;
    }

    public String getSAppSrc() {
        return this.sAppSrc;
    }

    public void setSAppSrc(String str) {
        this.sAppSrc = str;
    }

    public QueryHttpDnsReq() {
        setLUid(0L);
        setSUA(this.sUA);
        setVDomain(this.vDomain);
        setSAppSrc(this.sAppSrc);
    }

    public QueryHttpDnsReq(long j, String str, ArrayList<String> arrayList, String str2) {
        setLUid(j);
        setSUA(str);
        setVDomain(arrayList);
        setSAppSrc(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        QueryHttpDnsReq queryHttpDnsReq = (QueryHttpDnsReq) obj;
        return JceUtil.equals(this.lUid, queryHttpDnsReq.lUid) && JceUtil.equals(this.sUA, queryHttpDnsReq.sUA) && JceUtil.equals(this.vDomain, queryHttpDnsReq.vDomain) && JceUtil.equals(this.sAppSrc, queryHttpDnsReq.sAppSrc);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lUid), JceUtil.hashCode(this.sUA), JceUtil.hashCode(this.vDomain), JceUtil.hashCode(this.sAppSrc)});
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
        String str = this.sUA;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        ArrayList<String> arrayList = this.vDomain;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 2);
        }
        String str2 = this.sAppSrc;
        if (str2 != null) {
            jceOutputStream.write(str2, 3);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setSUA(jceInputStream.readString(1, false));
        if (cache_vDomain == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vDomain = arrayList;
            arrayList.add("");
        }
        setVDomain((ArrayList) jceInputStream.read(cache_vDomain, 2, false));
        setSAppSrc(jceInputStream.readString(3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.sUA, "sUA");
        jceDisplayer.display((Collection) this.vDomain, "vDomain");
        jceDisplayer.display(this.sAppSrc, "sAppSrc");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
