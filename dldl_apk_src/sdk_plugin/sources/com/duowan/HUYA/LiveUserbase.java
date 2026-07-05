package com.duowan.HUYA;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveUserbase extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<LiveUserbase> CREATOR = new Parcelable.Creator<LiveUserbase>() { // from class: com.duowan.HUYA.LiveUserbase.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LiveUserbase createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            LiveUserbase liveUserbase = new LiveUserbase();
            liveUserbase.readFrom(jceInputStream);
            return liveUserbase;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LiveUserbase[] newArray(int i) {
            return new LiveUserbase[i];
        }
    };
    static int cache_eSource;
    static int cache_eType;
    static LiveAppUAEx cache_tUAEx;
    public int eSource = 0;
    public int eType = 0;
    public LiveAppUAEx tUAEx = null;

    public String className() {
        return "HUYA.LiveUserbase";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.LiveUserbase";
    }

    public int getESource() {
        return this.eSource;
    }

    public void setESource(int i) {
        this.eSource = i;
    }

    public int getEType() {
        return this.eType;
    }

    public void setEType(int i) {
        this.eType = i;
    }

    public LiveAppUAEx getTUAEx() {
        return this.tUAEx;
    }

    public void setTUAEx(LiveAppUAEx liveAppUAEx) {
        this.tUAEx = liveAppUAEx;
    }

    public LiveUserbase() {
        setESource(0);
        setEType(this.eType);
        setTUAEx(this.tUAEx);
    }

    public LiveUserbase(int i, int i2, LiveAppUAEx liveAppUAEx) {
        setESource(i);
        setEType(i2);
        setTUAEx(liveAppUAEx);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LiveUserbase liveUserbase = (LiveUserbase) obj;
        return JceUtil.equals(this.eSource, liveUserbase.eSource) && JceUtil.equals(this.eType, liveUserbase.eType) && JceUtil.equals(this.tUAEx, liveUserbase.tUAEx);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.eSource), JceUtil.hashCode(this.eType), JceUtil.hashCode(this.tUAEx)});
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
        jceOutputStream.write(this.eSource, 0);
        jceOutputStream.write(this.eType, 1);
        LiveAppUAEx liveAppUAEx = this.tUAEx;
        if (liveAppUAEx != null) {
            jceOutputStream.write((JceStruct) liveAppUAEx, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setESource(jceInputStream.read(this.eSource, 0, false));
        setEType(jceInputStream.read(this.eType, 1, false));
        if (cache_tUAEx == null) {
            cache_tUAEx = new LiveAppUAEx();
        }
        setTUAEx((LiveAppUAEx) jceInputStream.read((JceStruct) cache_tUAEx, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.eSource, "eSource");
        jceDisplayer.display(this.eType, "eType");
        jceDisplayer.display((JceStruct) this.tUAEx, "tUAEx");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
