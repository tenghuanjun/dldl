package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class LocationPos extends JceStruct implements Comparable<LocationPos>, Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public double lat;
    public double lng;

    public String className() {
        return "HUYA.LocationPos";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.LocationPos";
    }

    public LocationPos() {
        this.lat = -1.0d;
        this.lng = -1.0d;
    }

    public LocationPos(double d, double d2) {
        this.lat = -1.0d;
        this.lng = -1.0d;
        this.lat = d;
        this.lng = d2;
    }

    @Override // java.lang.Comparable
    public int compareTo(LocationPos locationPos) {
        int[] iArr = {JceUtil.compareTo(this.lat, locationPos.lat), JceUtil.compareTo(this.lng, locationPos.lng)};
        for (int i = 0; i < 2; i++) {
            if (iArr[i] != 0) {
                return iArr[i];
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LocationPos locationPos = (LocationPos) obj;
        return JceUtil.equals(this.lat, locationPos.lat) && JceUtil.equals(this.lng, locationPos.lng);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lat), JceUtil.hashCode(this.lng)});
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
        jceOutputStream.write(this.lat, 0);
        jceOutputStream.write(this.lng, 1);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        this.lat = jceInputStream.read(this.lat, 0, false);
        this.lng = jceInputStream.read(this.lng, 1, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lat, "lat");
        jceDisplayer.display(this.lng, "lng");
    }
}
