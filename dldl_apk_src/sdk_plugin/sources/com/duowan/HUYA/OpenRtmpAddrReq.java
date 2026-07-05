package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class OpenRtmpAddrReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Map<String, String> cache_mMiscInfo;
    static UserId cache_tUserId;
    public String sLiveDesc = "";
    public int iGameId = 0;
    public UserId tUserId = null;
    public int iBps = 0;
    public long lLiveFlag = 0;
    public int iReadOnly = 0;
    public Map<String, String> mMiscInfo = null;

    public String className() {
        return "HUYA.OpenRtmpAddrReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.OpenRtmpAddrReq";
    }

    public String getSLiveDesc() {
        return this.sLiveDesc;
    }

    public void setSLiveDesc(String str) {
        this.sLiveDesc = str;
    }

    public int getIGameId() {
        return this.iGameId;
    }

    public void setIGameId(int i) {
        this.iGameId = i;
    }

    public UserId getTUserId() {
        return this.tUserId;
    }

    public void setTUserId(UserId userId) {
        this.tUserId = userId;
    }

    public int getIBps() {
        return this.iBps;
    }

    public void setIBps(int i) {
        this.iBps = i;
    }

    public long getLLiveFlag() {
        return this.lLiveFlag;
    }

    public void setLLiveFlag(long j) {
        this.lLiveFlag = j;
    }

    public int getIReadOnly() {
        return this.iReadOnly;
    }

    public void setIReadOnly(int i) {
        this.iReadOnly = i;
    }

    public Map<String, String> getMMiscInfo() {
        return this.mMiscInfo;
    }

    public void setMMiscInfo(Map<String, String> map) {
        this.mMiscInfo = map;
    }

    public OpenRtmpAddrReq() {
        setSLiveDesc("");
        setIGameId(this.iGameId);
        setTUserId(this.tUserId);
        setIBps(this.iBps);
        setLLiveFlag(this.lLiveFlag);
        setIReadOnly(this.iReadOnly);
        setMMiscInfo(this.mMiscInfo);
    }

    public OpenRtmpAddrReq(String str, int i, UserId userId, int i2, long j, int i3, Map<String, String> map) {
        setSLiveDesc(str);
        setIGameId(i);
        setTUserId(userId);
        setIBps(i2);
        setLLiveFlag(j);
        setIReadOnly(i3);
        setMMiscInfo(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OpenRtmpAddrReq openRtmpAddrReq = (OpenRtmpAddrReq) obj;
        return JceUtil.equals(this.sLiveDesc, openRtmpAddrReq.sLiveDesc) && JceUtil.equals(this.iGameId, openRtmpAddrReq.iGameId) && JceUtil.equals(this.tUserId, openRtmpAddrReq.tUserId) && JceUtil.equals(this.iBps, openRtmpAddrReq.iBps) && JceUtil.equals(this.lLiveFlag, openRtmpAddrReq.lLiveFlag) && JceUtil.equals(this.iReadOnly, openRtmpAddrReq.iReadOnly) && JceUtil.equals(this.mMiscInfo, openRtmpAddrReq.mMiscInfo);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sLiveDesc), JceUtil.hashCode(this.iGameId), JceUtil.hashCode(this.tUserId), JceUtil.hashCode(this.iBps), JceUtil.hashCode(this.lLiveFlag), JceUtil.hashCode(this.iReadOnly), JceUtil.hashCode(this.mMiscInfo)});
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
        String str = this.sLiveDesc;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        jceOutputStream.write(this.iGameId, 1);
        UserId userId = this.tUserId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 2);
        }
        jceOutputStream.write(this.iBps, 3);
        jceOutputStream.write(this.lLiveFlag, 4);
        jceOutputStream.write(this.iReadOnly, 5);
        Map<String, String> map = this.mMiscInfo;
        if (map != null) {
            jceOutputStream.write((Map) map, 6);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSLiveDesc(jceInputStream.readString(0, false));
        setIGameId(jceInputStream.read(this.iGameId, 1, false));
        if (cache_tUserId == null) {
            cache_tUserId = new UserId();
        }
        setTUserId((UserId) jceInputStream.read((JceStruct) cache_tUserId, 2, false));
        setIBps(jceInputStream.read(this.iBps, 3, false));
        setLLiveFlag(jceInputStream.read(this.lLiveFlag, 4, false));
        setIReadOnly(jceInputStream.read(this.iReadOnly, 5, false));
        if (cache_mMiscInfo == null) {
            HashMap map = new HashMap();
            cache_mMiscInfo = map;
            map.put("", "");
        }
        setMMiscInfo((Map) jceInputStream.read(cache_mMiscInfo, 6, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sLiveDesc, "sLiveDesc");
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display((JceStruct) this.tUserId, "tUserId");
        jceDisplayer.display(this.iBps, "iBps");
        jceDisplayer.display(this.lLiveFlag, "lLiveFlag");
        jceDisplayer.display(this.iReadOnly, "iReadOnly");
        jceDisplayer.display((Map) this.mMiscInfo, "mMiscInfo");
    }
}
