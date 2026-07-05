package com.huya.hyhttpdns.jce;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class QueryHttpDnsRsp extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<QueryHttpDnsRsp> CREATOR = new Parcelable.Creator<QueryHttpDnsRsp>() { // from class: com.huya.hyhttpdns.jce.QueryHttpDnsRsp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public QueryHttpDnsRsp createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            QueryHttpDnsRsp queryHttpDnsRsp = new QueryHttpDnsRsp();
            queryHttpDnsRsp.readFrom(jceInputStream);
            return queryHttpDnsRsp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public QueryHttpDnsRsp[] newArray(int i) {
            return new QueryHttpDnsRsp[i];
        }
    };
    static Map<String, HttpDnsItem> cache_mDomain2Ip;
    public Map<String, HttpDnsItem> mDomain2Ip = null;

    public String className() {
        return "HUYA.QueryHttpDnsRsp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "QueryHttpDnsRsp";
    }

    public Map<String, HttpDnsItem> getMDomain2Ip() {
        return this.mDomain2Ip;
    }

    public void setMDomain2Ip(Map<String, HttpDnsItem> map) {
        this.mDomain2Ip = map;
    }

    public QueryHttpDnsRsp() {
        setMDomain2Ip(null);
    }

    public QueryHttpDnsRsp(Map<String, HttpDnsItem> map) {
        setMDomain2Ip(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.mDomain2Ip, ((QueryHttpDnsRsp) obj).mDomain2Ip);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.mDomain2Ip)});
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
        Map<String, HttpDnsItem> map = this.mDomain2Ip;
        if (map != null) {
            jceOutputStream.write((Map) map, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_mDomain2Ip == null) {
            cache_mDomain2Ip = new HashMap();
            cache_mDomain2Ip.put("", new HttpDnsItem());
        }
        setMDomain2Ip((Map) jceInputStream.read(cache_mDomain2Ip, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Map) this.mDomain2Ip, "mDomain2Ip");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
