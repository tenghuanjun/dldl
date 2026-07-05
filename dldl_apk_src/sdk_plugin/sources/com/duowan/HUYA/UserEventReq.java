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
public class UserEventReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<UserEventReq> CREATOR = new Parcelable.Creator<UserEventReq>() { // from class: com.duowan.HUYA.UserEventReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserEventReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            UserEventReq userEventReq = new UserEventReq();
            userEventReq.readFrom(jceInputStream);
            return userEventReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserEventReq[] newArray(int i) {
            return new UserEventReq[i];
        }
    };
    static int cache_eOp;
    static int cache_eSource;
    static int cache_eTemplateType;
    static UserId cache_tId;
    public UserId tId = null;
    public long lTid = 0;
    public long lSid = 0;
    public int eOp = 0;
    public String sChan = "";
    public int eSource = 0;
    public long lPid = 0;
    public boolean bWatchVideo = false;
    public boolean bAnonymous = false;
    public int eTemplateType = TemplateType.PRIMARY.value();
    public String sTraceSource = "";

    public String className() {
        return "HUYA.UserEventReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserEventReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public long getLTid() {
        return this.lTid;
    }

    public void setLTid(long j) {
        this.lTid = j;
    }

    public long getLSid() {
        return this.lSid;
    }

    public void setLSid(long j) {
        this.lSid = j;
    }

    public int getEOp() {
        return this.eOp;
    }

    public void setEOp(int i) {
        this.eOp = i;
    }

    public String getSChan() {
        return this.sChan;
    }

    public void setSChan(String str) {
        this.sChan = str;
    }

    public int getESource() {
        return this.eSource;
    }

    public void setESource(int i) {
        this.eSource = i;
    }

    public long getLPid() {
        return this.lPid;
    }

    public void setLPid(long j) {
        this.lPid = j;
    }

    public boolean getBWatchVideo() {
        return this.bWatchVideo;
    }

    public void setBWatchVideo(boolean z) {
        this.bWatchVideo = z;
    }

    public boolean getBAnonymous() {
        return this.bAnonymous;
    }

    public void setBAnonymous(boolean z) {
        this.bAnonymous = z;
    }

    public int getETemplateType() {
        return this.eTemplateType;
    }

    public void setETemplateType(int i) {
        this.eTemplateType = i;
    }

    public String getSTraceSource() {
        return this.sTraceSource;
    }

    public void setSTraceSource(String str) {
        this.sTraceSource = str;
    }

    public UserEventReq() {
        setTId(this.tId);
        setLTid(this.lTid);
        setLSid(this.lSid);
        setEOp(this.eOp);
        setSChan(this.sChan);
        setESource(this.eSource);
        setLPid(this.lPid);
        setBWatchVideo(this.bWatchVideo);
        setBAnonymous(this.bAnonymous);
        setETemplateType(this.eTemplateType);
        setSTraceSource(this.sTraceSource);
    }

    public UserEventReq(UserId userId, long j, long j2, int i, String str, int i2, long j3, boolean z, boolean z2, int i3, String str2) {
        setTId(userId);
        setLTid(j);
        setLSid(j2);
        setEOp(i);
        setSChan(str);
        setESource(i2);
        setLPid(j3);
        setBWatchVideo(z);
        setBAnonymous(z2);
        setETemplateType(i3);
        setSTraceSource(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserEventReq userEventReq = (UserEventReq) obj;
        return JceUtil.equals(this.tId, userEventReq.tId) && JceUtil.equals(this.lTid, userEventReq.lTid) && JceUtil.equals(this.lSid, userEventReq.lSid) && JceUtil.equals(this.eOp, userEventReq.eOp) && JceUtil.equals(this.sChan, userEventReq.sChan) && JceUtil.equals(this.eSource, userEventReq.eSource) && JceUtil.equals(this.lPid, userEventReq.lPid) && JceUtil.equals(this.bWatchVideo, userEventReq.bWatchVideo) && JceUtil.equals(this.bAnonymous, userEventReq.bAnonymous) && JceUtil.equals(this.eTemplateType, userEventReq.eTemplateType) && JceUtil.equals(this.sTraceSource, userEventReq.sTraceSource);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tId), JceUtil.hashCode(this.lTid), JceUtil.hashCode(this.lSid), JceUtil.hashCode(this.eOp), JceUtil.hashCode(this.sChan), JceUtil.hashCode(this.eSource), JceUtil.hashCode(this.lPid), JceUtil.hashCode(this.bWatchVideo), JceUtil.hashCode(this.bAnonymous), JceUtil.hashCode(this.eTemplateType), JceUtil.hashCode(this.sTraceSource)});
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
        jceOutputStream.write(this.lTid, 1);
        jceOutputStream.write(this.lSid, 2);
        jceOutputStream.write(this.eOp, 4);
        String str = this.sChan;
        if (str != null) {
            jceOutputStream.write(str, 5);
        }
        jceOutputStream.write(this.eSource, 6);
        jceOutputStream.write(this.lPid, 7);
        jceOutputStream.write(this.bWatchVideo, 8);
        jceOutputStream.write(this.bAnonymous, 9);
        jceOutputStream.write(this.eTemplateType, 10);
        String str2 = this.sTraceSource;
        if (str2 != null) {
            jceOutputStream.write(str2, 11);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setLTid(jceInputStream.read(this.lTid, 1, false));
        setLSid(jceInputStream.read(this.lSid, 2, false));
        setEOp(jceInputStream.read(this.eOp, 4, false));
        setSChan(jceInputStream.readString(5, false));
        setESource(jceInputStream.read(this.eSource, 6, false));
        setLPid(jceInputStream.read(this.lPid, 7, false));
        setBWatchVideo(jceInputStream.read(this.bWatchVideo, 8, false));
        setBAnonymous(jceInputStream.read(this.bAnonymous, 9, false));
        setETemplateType(jceInputStream.read(this.eTemplateType, 10, false));
        setSTraceSource(jceInputStream.readString(11, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSid, "lSid");
        jceDisplayer.display(this.eOp, "eOp");
        jceDisplayer.display(this.sChan, "sChan");
        jceDisplayer.display(this.eSource, "eSource");
        jceDisplayer.display(this.lPid, "lPid");
        jceDisplayer.display(this.bWatchVideo, "bWatchVideo");
        jceDisplayer.display(this.bAnonymous, "bAnonymous");
        jceDisplayer.display(this.eTemplateType, "eTemplateType");
        jceDisplayer.display(this.sTraceSource, "sTraceSource");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
