package com.huya.hysignal.jce;

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
public class WSUpdateUserExpsReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<WSUpdateUserExpsReq> CREATOR = new Parcelable.Creator<WSUpdateUserExpsReq>() { // from class: com.huya.hysignal.jce.WSUpdateUserExpsReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSUpdateUserExpsReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            WSUpdateUserExpsReq wSUpdateUserExpsReq = new WSUpdateUserExpsReq();
            wSUpdateUserExpsReq.readFrom(jceInputStream);
            return wSUpdateUserExpsReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WSUpdateUserExpsReq[] newArray(int i) {
            return new WSUpdateUserExpsReq[i];
        }
    };
    static Map<String, String> cache_mExps;
    public Map<String, String> mExps = null;

    public String className() {
        return "HUYA.WSUpdateUserExpsReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSUpdateUserExpsReq";
    }

    public Map<String, String> getMExps() {
        return this.mExps;
    }

    public void setMExps(Map<String, String> map) {
        this.mExps = map;
    }

    public WSUpdateUserExpsReq() {
        setMExps(null);
    }

    public WSUpdateUserExpsReq(Map<String, String> map) {
        setMExps(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.mExps, ((WSUpdateUserExpsReq) obj).mExps);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.mExps)});
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
        Map<String, String> map = this.mExps;
        if (map != null) {
            jceOutputStream.write((Map) map, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_mExps == null) {
            HashMap map = new HashMap();
            cache_mExps = map;
            map.put("", "");
        }
        setMExps((Map) jceInputStream.read(cache_mExps, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((Map) this.mExps, "mExps");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
