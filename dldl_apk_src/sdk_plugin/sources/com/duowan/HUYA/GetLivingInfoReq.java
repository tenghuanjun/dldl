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
public class GetLivingInfoReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<GetLivingInfoReq> CREATOR = new Parcelable.Creator<GetLivingInfoReq>() { // from class: com.duowan.HUYA.GetLivingInfoReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetLivingInfoReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            GetLivingInfoReq getLivingInfoReq = new GetLivingInfoReq();
            getLivingInfoReq.readFrom(jceInputStream);
            return getLivingInfoReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetLivingInfoReq[] newArray(int i) {
            return new GetLivingInfoReq[i];
        }
    };
    static UserId cache_tId;
    public UserId tId = null;
    public long lTopSid = 0;
    public long lSubSid = 0;
    public long lPresenterUid = 0;
    public String sTraceSource = "";
    public String sPassword = "";
    public long iRoomId = 0;

    public String className() {
        return "HUYA.GetLivingInfoReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetLivingInfoReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public long getLTopSid() {
        return this.lTopSid;
    }

    public void setLTopSid(long j) {
        this.lTopSid = j;
    }

    public long getLSubSid() {
        return this.lSubSid;
    }

    public void setLSubSid(long j) {
        this.lSubSid = j;
    }

    public long getLPresenterUid() {
        return this.lPresenterUid;
    }

    public void setLPresenterUid(long j) {
        this.lPresenterUid = j;
    }

    public String getSTraceSource() {
        return this.sTraceSource;
    }

    public void setSTraceSource(String str) {
        this.sTraceSource = str;
    }

    public String getSPassword() {
        return this.sPassword;
    }

    public void setSPassword(String str) {
        this.sPassword = str;
    }

    public long getIRoomId() {
        return this.iRoomId;
    }

    public void setIRoomId(long j) {
        this.iRoomId = j;
    }

    public GetLivingInfoReq() {
        setTId(null);
        setLTopSid(this.lTopSid);
        setLSubSid(this.lSubSid);
        setLPresenterUid(this.lPresenterUid);
        setSTraceSource(this.sTraceSource);
        setSPassword(this.sPassword);
        setIRoomId(this.iRoomId);
    }

    public GetLivingInfoReq(UserId userId, long j, long j2, long j3, String str, String str2, long j4) {
        setTId(userId);
        setLTopSid(j);
        setLSubSid(j2);
        setLPresenterUid(j3);
        setSTraceSource(str);
        setSPassword(str2);
        setIRoomId(j4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetLivingInfoReq getLivingInfoReq = (GetLivingInfoReq) obj;
        return JceUtil.equals(this.tId, getLivingInfoReq.tId) && JceUtil.equals(this.lTopSid, getLivingInfoReq.lTopSid) && JceUtil.equals(this.lSubSid, getLivingInfoReq.lSubSid) && JceUtil.equals(this.lPresenterUid, getLivingInfoReq.lPresenterUid) && JceUtil.equals(this.sTraceSource, getLivingInfoReq.sTraceSource) && JceUtil.equals(this.sPassword, getLivingInfoReq.sPassword) && JceUtil.equals(this.iRoomId, getLivingInfoReq.iRoomId);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tId), JceUtil.hashCode(this.lTopSid), JceUtil.hashCode(this.lSubSid), JceUtil.hashCode(this.lPresenterUid), JceUtil.hashCode(this.sTraceSource), JceUtil.hashCode(this.sPassword), JceUtil.hashCode(this.iRoomId)});
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
        jceOutputStream.write(this.lTopSid, 1);
        jceOutputStream.write(this.lSubSid, 2);
        jceOutputStream.write(this.lPresenterUid, 3);
        String str = this.sTraceSource;
        if (str != null) {
            jceOutputStream.write(str, 4);
        }
        String str2 = this.sPassword;
        if (str2 != null) {
            jceOutputStream.write(str2, 5);
        }
        jceOutputStream.write(this.iRoomId, 6);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setLTopSid(jceInputStream.read(this.lTopSid, 1, false));
        setLSubSid(jceInputStream.read(this.lSubSid, 2, false));
        setLPresenterUid(jceInputStream.read(this.lPresenterUid, 3, false));
        setSTraceSource(jceInputStream.readString(4, false));
        setSPassword(jceInputStream.readString(5, false));
        setIRoomId(jceInputStream.read(this.iRoomId, 6, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.lTopSid, "lTopSid");
        jceDisplayer.display(this.lSubSid, "lSubSid");
        jceDisplayer.display(this.lPresenterUid, "lPresenterUid");
        jceDisplayer.display(this.sTraceSource, "sTraceSource");
        jceDisplayer.display(this.sPassword, "sPassword");
        jceDisplayer.display(this.iRoomId, "iRoomId");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
