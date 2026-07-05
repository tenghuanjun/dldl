package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class UserHeartBeatReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static int cache_eLineType;
    static UserId cache_tId;
    public UserId tId = null;
    public long lTid = 0;
    public long lSid = 0;
    public long lPid = 0;
    public boolean bWatchVideo = false;
    public int eLineType = 0;
    public int iFps = 0;
    public int iAttendee = 0;
    public int iBandwidth = 0;
    public int iLastHeartElapseTime = 0;

    public String className() {
        return "HUYA.UserHeartBeatReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserHeartBeatReq";
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

    public int getELineType() {
        return this.eLineType;
    }

    public void setELineType(int i) {
        this.eLineType = i;
    }

    public int getIFps() {
        return this.iFps;
    }

    public void setIFps(int i) {
        this.iFps = i;
    }

    public int getIAttendee() {
        return this.iAttendee;
    }

    public void setIAttendee(int i) {
        this.iAttendee = i;
    }

    public int getIBandwidth() {
        return this.iBandwidth;
    }

    public void setIBandwidth(int i) {
        this.iBandwidth = i;
    }

    public int getILastHeartElapseTime() {
        return this.iLastHeartElapseTime;
    }

    public void setILastHeartElapseTime(int i) {
        this.iLastHeartElapseTime = i;
    }

    public UserHeartBeatReq() {
        setTId(null);
        setLTid(this.lTid);
        setLSid(this.lSid);
        setLPid(this.lPid);
        setBWatchVideo(this.bWatchVideo);
        setELineType(this.eLineType);
        setIFps(this.iFps);
        setIAttendee(this.iAttendee);
        setIBandwidth(this.iBandwidth);
        setILastHeartElapseTime(this.iLastHeartElapseTime);
    }

    public UserHeartBeatReq(UserId userId, long j, long j2, long j3, boolean z, int i, int i2, int i3, int i4, int i5) {
        setTId(userId);
        setLTid(j);
        setLSid(j2);
        setLPid(j3);
        setBWatchVideo(z);
        setELineType(i);
        setIFps(i2);
        setIAttendee(i3);
        setIBandwidth(i4);
        setILastHeartElapseTime(i5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserHeartBeatReq userHeartBeatReq = (UserHeartBeatReq) obj;
        return JceUtil.equals(this.tId, userHeartBeatReq.tId) && JceUtil.equals(this.lTid, userHeartBeatReq.lTid) && JceUtil.equals(this.lSid, userHeartBeatReq.lSid) && JceUtil.equals(this.lPid, userHeartBeatReq.lPid) && JceUtil.equals(this.bWatchVideo, userHeartBeatReq.bWatchVideo) && JceUtil.equals(this.eLineType, userHeartBeatReq.eLineType) && JceUtil.equals(this.iFps, userHeartBeatReq.iFps) && JceUtil.equals(this.iAttendee, userHeartBeatReq.iAttendee) && JceUtil.equals(this.iBandwidth, userHeartBeatReq.iBandwidth) && JceUtil.equals(this.iLastHeartElapseTime, userHeartBeatReq.iLastHeartElapseTime);
    }

    public int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
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
        jceOutputStream.write(this.lPid, 4);
        jceOutputStream.write(this.bWatchVideo, 5);
        jceOutputStream.write(this.eLineType, 6);
        jceOutputStream.write(this.iFps, 7);
        jceOutputStream.write(this.iAttendee, 8);
        jceOutputStream.write(this.iBandwidth, 9);
        jceOutputStream.write(this.iLastHeartElapseTime, 10);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setLTid(jceInputStream.read(this.lTid, 1, false));
        setLSid(jceInputStream.read(this.lSid, 2, false));
        setLPid(jceInputStream.read(this.lPid, 4, false));
        setBWatchVideo(jceInputStream.read(this.bWatchVideo, 5, false));
        setELineType(jceInputStream.read(this.eLineType, 6, false));
        setIFps(jceInputStream.read(this.iFps, 7, false));
        setIAttendee(jceInputStream.read(this.iAttendee, 8, false));
        setIBandwidth(jceInputStream.read(this.iBandwidth, 9, false));
        setILastHeartElapseTime(jceInputStream.read(this.iLastHeartElapseTime, 10, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSid, "lSid");
        jceDisplayer.display(this.lPid, "lPid");
        jceDisplayer.display(this.bWatchVideo, "bWatchVideo");
        jceDisplayer.display(this.eLineType, "eLineType");
        jceDisplayer.display(this.iFps, "iFps");
        jceDisplayer.display(this.iAttendee, "iAttendee");
        jceDisplayer.display(this.iBandwidth, "iBandwidth");
        jceDisplayer.display(this.iLastHeartElapseTime, "iLastHeartElapseTime");
    }
}
