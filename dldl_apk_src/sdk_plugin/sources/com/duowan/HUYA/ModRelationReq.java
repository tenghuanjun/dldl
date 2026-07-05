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
public class ModRelationReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<ModRelationReq> CREATOR = new Parcelable.Creator<ModRelationReq>() { // from class: com.duowan.HUYA.ModRelationReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModRelationReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            ModRelationReq modRelationReq = new ModRelationReq();
            modRelationReq.readFrom(jceInputStream);
            return modRelationReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ModRelationReq[] newArray(int i) {
            return new ModRelationReq[i];
        }
    };
    static UserId cache_tId;
    public UserId tId = null;
    public long lUid = 0;
    public int iOp = 0;
    public String sSource = "";

    public String className() {
        return "HUYA.ModRelationReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ModRelationReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public int getIOp() {
        return this.iOp;
    }

    public void setIOp(int i) {
        this.iOp = i;
    }

    public String getSSource() {
        return this.sSource;
    }

    public void setSSource(String str) {
        this.sSource = str;
    }

    public ModRelationReq() {
        setTId(null);
        setLUid(this.lUid);
        setIOp(this.iOp);
        setSSource(this.sSource);
    }

    public ModRelationReq(UserId userId, long j, int i, String str) {
        setTId(userId);
        setLUid(j);
        setIOp(i);
        setSSource(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ModRelationReq modRelationReq = (ModRelationReq) obj;
        return JceUtil.equals(this.tId, modRelationReq.tId) && JceUtil.equals(this.lUid, modRelationReq.lUid) && JceUtil.equals(this.iOp, modRelationReq.iOp) && JceUtil.equals(this.sSource, modRelationReq.sSource);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tId), JceUtil.hashCode(this.lUid), JceUtil.hashCode(this.iOp), JceUtil.hashCode(this.sSource)});
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
        UserId userId = this.tId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 0);
        }
        jceOutputStream.write(this.lUid, 1);
        jceOutputStream.write(this.iOp, 2);
        String str = this.sSource;
        if (str != null) {
            jceOutputStream.write(str, 3);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setLUid(jceInputStream.read(this.lUid, 1, false));
        setIOp(jceInputStream.read(this.iOp, 2, false));
        setSSource(jceInputStream.readString(3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.iOp, "iOp");
        jceDisplayer.display(this.sSource, "sSource");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
