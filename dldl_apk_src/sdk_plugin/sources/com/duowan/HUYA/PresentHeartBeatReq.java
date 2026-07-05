package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class PresentHeartBeatReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static int cache_eSource;
    static Map<String, String> cache_mReportMessage;
    static UserId cache_tId;
    public UserId tId = null;
    public long lTid = 0;
    public long lSid = 0;
    public long lShortTid = 0;
    public boolean bIsCameraOpen = false;
    public boolean bIsRoomSecret = false;
    public int eSource = ELiveSource.PC_YY.value();
    public long lLiveId = 0;
    public Map<String, String> mReportMessage = null;

    public String className() {
        return "HUYA.PresentHeartBeatReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PresentHeartBeatReq";
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

    public long getLShortTid() {
        return this.lShortTid;
    }

    public void setLShortTid(long j) {
        this.lShortTid = j;
    }

    public boolean getBIsCameraOpen() {
        return this.bIsCameraOpen;
    }

    public void setBIsCameraOpen(boolean z) {
        this.bIsCameraOpen = z;
    }

    public boolean getBIsRoomSecret() {
        return this.bIsRoomSecret;
    }

    public void setBIsRoomSecret(boolean z) {
        this.bIsRoomSecret = z;
    }

    public int getESource() {
        return this.eSource;
    }

    public void setESource(int i) {
        this.eSource = i;
    }

    public long getLLiveId() {
        return this.lLiveId;
    }

    public void setLLiveId(long j) {
        this.lLiveId = j;
    }

    public Map<String, String> getMReportMessage() {
        return this.mReportMessage;
    }

    public void setMReportMessage(Map<String, String> map) {
        this.mReportMessage = map;
    }

    public PresentHeartBeatReq() {
        setTId(this.tId);
        setLTid(this.lTid);
        setLSid(this.lSid);
        setLShortTid(this.lShortTid);
        setBIsCameraOpen(this.bIsCameraOpen);
        setBIsRoomSecret(this.bIsRoomSecret);
        setESource(this.eSource);
        setLLiveId(this.lLiveId);
        setMReportMessage(this.mReportMessage);
    }

    public PresentHeartBeatReq(UserId userId, long j, long j2, long j3, boolean z, boolean z2, int i, long j4, Map<String, String> map) {
        setTId(userId);
        setLTid(j);
        setLSid(j2);
        setLShortTid(j3);
        setBIsCameraOpen(z);
        setBIsRoomSecret(z2);
        setESource(i);
        setLLiveId(j4);
        setMReportMessage(map);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        PresentHeartBeatReq presentHeartBeatReq = (PresentHeartBeatReq) obj;
        return JceUtil.equals(this.tId, presentHeartBeatReq.tId) && JceUtil.equals(this.lTid, presentHeartBeatReq.lTid) && JceUtil.equals(this.lSid, presentHeartBeatReq.lSid) && JceUtil.equals(this.lShortTid, presentHeartBeatReq.lShortTid) && JceUtil.equals(this.bIsCameraOpen, presentHeartBeatReq.bIsCameraOpen) && JceUtil.equals(this.bIsRoomSecret, presentHeartBeatReq.bIsRoomSecret) && JceUtil.equals(this.eSource, presentHeartBeatReq.eSource) && JceUtil.equals(this.lLiveId, presentHeartBeatReq.lLiveId) && JceUtil.equals(this.mReportMessage, presentHeartBeatReq.mReportMessage);
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
        jceOutputStream.write(this.lShortTid, 3);
        jceOutputStream.write(this.bIsCameraOpen, 4);
        jceOutputStream.write(this.bIsRoomSecret, 5);
        jceOutputStream.write(this.eSource, 7);
        jceOutputStream.write(this.lLiveId, 8);
        Map<String, String> map = this.mReportMessage;
        if (map != null) {
            jceOutputStream.write((Map) map, 9);
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
        setLShortTid(jceInputStream.read(this.lShortTid, 3, false));
        setBIsCameraOpen(jceInputStream.read(this.bIsCameraOpen, 4, false));
        setBIsRoomSecret(jceInputStream.read(this.bIsRoomSecret, 5, false));
        setESource(jceInputStream.read(this.eSource, 7, false));
        setLLiveId(jceInputStream.read(this.lLiveId, 8, false));
        if (cache_mReportMessage == null) {
            HashMap map = new HashMap();
            cache_mReportMessage = map;
            map.put("", "");
        }
        setMReportMessage((Map) jceInputStream.read(cache_mReportMessage, 9, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSid, "lSid");
        jceDisplayer.display(this.lShortTid, "lShortTid");
        jceDisplayer.display(this.bIsCameraOpen, "bIsCameraOpen");
        jceDisplayer.display(this.bIsRoomSecret, "bIsRoomSecret");
        jceDisplayer.display(this.eSource, "eSource");
        jceDisplayer.display(this.lLiveId, "lLiveId");
        jceDisplayer.display((Map) this.mReportMessage, "mReportMessage");
    }
}
