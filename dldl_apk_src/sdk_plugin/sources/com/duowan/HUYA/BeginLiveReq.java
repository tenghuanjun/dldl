package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class BeginLiveReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Map<String, String> cache_mMiscInfo;
    static UserId cache_tId;
    public UserId tId = null;
    public int iPopupFlags = 0;
    public int iBandWidth = 0;
    public int iResolution = 0;
    public int iPresentType = 0;
    public int iGameId = 0;
    public int iIsCdnSupport = 0;
    public long lTopSid = 0;
    public long lSubSid = 0;
    public int iNewGameId = 0;
    public String sNickName = "";
    public String sLiveDesc = "";
    public int iShortChannel = 0;
    public int iBitRate = 0;
    public int iFrameRate = 0;
    public int iSourceType = 0;
    public int iCodecType = 0;
    public int iScreenType = 0;
    public long lLiveCompatibleFlag = 0;
    public Map<String, String> mMiscInfo = null;
    public String sGPSInfo = "";
    public long lMultiStreamFlag = 0;

    public String className() {
        return "HUYA.BeginLiveReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.BeginLiveReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public int getIPopupFlags() {
        return this.iPopupFlags;
    }

    public void setIPopupFlags(int i) {
        this.iPopupFlags = i;
    }

    public int getIBandWidth() {
        return this.iBandWidth;
    }

    public void setIBandWidth(int i) {
        this.iBandWidth = i;
    }

    public int getIResolution() {
        return this.iResolution;
    }

    public void setIResolution(int i) {
        this.iResolution = i;
    }

    public int getIPresentType() {
        return this.iPresentType;
    }

    public void setIPresentType(int i) {
        this.iPresentType = i;
    }

    public int getIGameId() {
        return this.iGameId;
    }

    public void setIGameId(int i) {
        this.iGameId = i;
    }

    public int getIIsCdnSupport() {
        return this.iIsCdnSupport;
    }

    public void setIIsCdnSupport(int i) {
        this.iIsCdnSupport = i;
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

    public int getINewGameId() {
        return this.iNewGameId;
    }

    public void setINewGameId(int i) {
        this.iNewGameId = i;
    }

    public String getSNickName() {
        return this.sNickName;
    }

    public void setSNickName(String str) {
        this.sNickName = str;
    }

    public String getSLiveDesc() {
        return this.sLiveDesc;
    }

    public void setSLiveDesc(String str) {
        this.sLiveDesc = str;
    }

    public int getIShortChannel() {
        return this.iShortChannel;
    }

    public void setIShortChannel(int i) {
        this.iShortChannel = i;
    }

    public int getIBitRate() {
        return this.iBitRate;
    }

    public void setIBitRate(int i) {
        this.iBitRate = i;
    }

    public int getIFrameRate() {
        return this.iFrameRate;
    }

    public void setIFrameRate(int i) {
        this.iFrameRate = i;
    }

    public int getISourceType() {
        return this.iSourceType;
    }

    public void setISourceType(int i) {
        this.iSourceType = i;
    }

    public int getICodecType() {
        return this.iCodecType;
    }

    public void setICodecType(int i) {
        this.iCodecType = i;
    }

    public int getIScreenType() {
        return this.iScreenType;
    }

    public void setIScreenType(int i) {
        this.iScreenType = i;
    }

    public long getLLiveCompatibleFlag() {
        return this.lLiveCompatibleFlag;
    }

    public void setLLiveCompatibleFlag(long j) {
        this.lLiveCompatibleFlag = j;
    }

    public Map<String, String> getMMiscInfo() {
        return this.mMiscInfo;
    }

    public void setMMiscInfo(Map<String, String> map) {
        this.mMiscInfo = map;
    }

    public String getSGPSInfo() {
        return this.sGPSInfo;
    }

    public void setSGPSInfo(String str) {
        this.sGPSInfo = str;
    }

    public long getLMultiStreamFlag() {
        return this.lMultiStreamFlag;
    }

    public void setLMultiStreamFlag(long j) {
        this.lMultiStreamFlag = j;
    }

    public BeginLiveReq() {
        setTId(null);
        setIPopupFlags(this.iPopupFlags);
        setIBandWidth(this.iBandWidth);
        setIResolution(this.iResolution);
        setIPresentType(this.iPresentType);
        setIGameId(this.iGameId);
        setIIsCdnSupport(this.iIsCdnSupport);
        setLTopSid(this.lTopSid);
        setLSubSid(this.lSubSid);
        setINewGameId(this.iNewGameId);
        setSNickName(this.sNickName);
        setSLiveDesc(this.sLiveDesc);
        setIShortChannel(this.iShortChannel);
        setIBitRate(this.iBitRate);
        setIFrameRate(this.iFrameRate);
        setISourceType(this.iSourceType);
        setICodecType(this.iCodecType);
        setIScreenType(this.iScreenType);
        setLLiveCompatibleFlag(this.lLiveCompatibleFlag);
        setMMiscInfo(this.mMiscInfo);
        setSGPSInfo(this.sGPSInfo);
        setLMultiStreamFlag(this.lMultiStreamFlag);
    }

    public BeginLiveReq(UserId userId, int i, int i2, int i3, int i4, int i5, int i6, long j, long j2, int i7, String str, String str2, int i8, int i9, int i10, int i11, int i12, int i13, long j3, Map<String, String> map, String str3, long j4) {
        setTId(userId);
        setIPopupFlags(i);
        setIBandWidth(i2);
        setIResolution(i3);
        setIPresentType(i4);
        setIGameId(i5);
        setIIsCdnSupport(i6);
        setLTopSid(j);
        setLSubSid(j2);
        setINewGameId(i7);
        setSNickName(str);
        setSLiveDesc(str2);
        setIShortChannel(i8);
        setIBitRate(i9);
        setIFrameRate(i10);
        setISourceType(i11);
        setICodecType(i12);
        setIScreenType(i13);
        setLLiveCompatibleFlag(j3);
        setMMiscInfo(map);
        setSGPSInfo(str3);
        setLMultiStreamFlag(j4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BeginLiveReq beginLiveReq = (BeginLiveReq) obj;
        return JceUtil.equals(this.tId, beginLiveReq.tId) && JceUtil.equals(this.iPopupFlags, beginLiveReq.iPopupFlags) && JceUtil.equals(this.iBandWidth, beginLiveReq.iBandWidth) && JceUtil.equals(this.iResolution, beginLiveReq.iResolution) && JceUtil.equals(this.iPresentType, beginLiveReq.iPresentType) && JceUtil.equals(this.iGameId, beginLiveReq.iGameId) && JceUtil.equals(this.iIsCdnSupport, beginLiveReq.iIsCdnSupport) && JceUtil.equals(this.lTopSid, beginLiveReq.lTopSid) && JceUtil.equals(this.lSubSid, beginLiveReq.lSubSid) && JceUtil.equals(this.iNewGameId, beginLiveReq.iNewGameId) && JceUtil.equals(this.sNickName, beginLiveReq.sNickName) && JceUtil.equals(this.sLiveDesc, beginLiveReq.sLiveDesc) && JceUtil.equals(this.iShortChannel, beginLiveReq.iShortChannel) && JceUtil.equals(this.iBitRate, beginLiveReq.iBitRate) && JceUtil.equals(this.iFrameRate, beginLiveReq.iFrameRate) && JceUtil.equals(this.iSourceType, beginLiveReq.iSourceType) && JceUtil.equals(this.iCodecType, beginLiveReq.iCodecType) && JceUtil.equals(this.iScreenType, beginLiveReq.iScreenType) && JceUtil.equals(this.lLiveCompatibleFlag, beginLiveReq.lLiveCompatibleFlag) && JceUtil.equals(this.mMiscInfo, beginLiveReq.mMiscInfo) && JceUtil.equals(this.sGPSInfo, beginLiveReq.sGPSInfo) && JceUtil.equals(this.lMultiStreamFlag, beginLiveReq.lMultiStreamFlag);
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
        jceOutputStream.write(this.iPopupFlags, 1);
        jceOutputStream.write(this.iBandWidth, 2);
        jceOutputStream.write(this.iResolution, 3);
        jceOutputStream.write(this.iPresentType, 4);
        jceOutputStream.write(this.iGameId, 5);
        jceOutputStream.write(this.iIsCdnSupport, 6);
        jceOutputStream.write(this.lTopSid, 7);
        jceOutputStream.write(this.lSubSid, 8);
        jceOutputStream.write(this.iNewGameId, 9);
        String str = this.sNickName;
        if (str != null) {
            jceOutputStream.write(str, 10);
        }
        String str2 = this.sLiveDesc;
        if (str2 != null) {
            jceOutputStream.write(str2, 11);
        }
        jceOutputStream.write(this.iShortChannel, 12);
        jceOutputStream.write(this.iBitRate, 13);
        jceOutputStream.write(this.iFrameRate, 14);
        jceOutputStream.write(this.iSourceType, 15);
        jceOutputStream.write(this.iCodecType, 16);
        jceOutputStream.write(this.iScreenType, 17);
        jceOutputStream.write(this.lLiveCompatibleFlag, 18);
        Map<String, String> map = this.mMiscInfo;
        if (map != null) {
            jceOutputStream.write((Map) map, 19);
        }
        String str3 = this.sGPSInfo;
        if (str3 != null) {
            jceOutputStream.write(str3, 20);
        }
        jceOutputStream.write(this.lMultiStreamFlag, 21);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setIPopupFlags(jceInputStream.read(this.iPopupFlags, 1, false));
        setIBandWidth(jceInputStream.read(this.iBandWidth, 2, false));
        setIResolution(jceInputStream.read(this.iResolution, 3, false));
        setIPresentType(jceInputStream.read(this.iPresentType, 4, false));
        setIGameId(jceInputStream.read(this.iGameId, 5, false));
        setIIsCdnSupport(jceInputStream.read(this.iIsCdnSupport, 6, false));
        setLTopSid(jceInputStream.read(this.lTopSid, 7, false));
        setLSubSid(jceInputStream.read(this.lSubSid, 8, false));
        setINewGameId(jceInputStream.read(this.iNewGameId, 9, false));
        setSNickName(jceInputStream.readString(10, false));
        setSLiveDesc(jceInputStream.readString(11, false));
        setIShortChannel(jceInputStream.read(this.iShortChannel, 12, false));
        setIBitRate(jceInputStream.read(this.iBitRate, 13, false));
        setIFrameRate(jceInputStream.read(this.iFrameRate, 14, false));
        setISourceType(jceInputStream.read(this.iSourceType, 15, false));
        setICodecType(jceInputStream.read(this.iCodecType, 16, false));
        setIScreenType(jceInputStream.read(this.iScreenType, 17, false));
        setLLiveCompatibleFlag(jceInputStream.read(this.lLiveCompatibleFlag, 18, false));
        if (cache_mMiscInfo == null) {
            HashMap map = new HashMap();
            cache_mMiscInfo = map;
            map.put("", "");
        }
        setMMiscInfo((Map) jceInputStream.read(cache_mMiscInfo, 19, false));
        setSGPSInfo(jceInputStream.readString(20, false));
        setLMultiStreamFlag(jceInputStream.read(this.lMultiStreamFlag, 21, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.iPopupFlags, "iPopupFlags");
        jceDisplayer.display(this.iBandWidth, "iBandWidth");
        jceDisplayer.display(this.iResolution, "iResolution");
        jceDisplayer.display(this.iPresentType, "iPresentType");
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display(this.iIsCdnSupport, "iIsCdnSupport");
        jceDisplayer.display(this.lTopSid, "lTopSid");
        jceDisplayer.display(this.lSubSid, "lSubSid");
        jceDisplayer.display(this.iNewGameId, "iNewGameId");
        jceDisplayer.display(this.sNickName, "sNickName");
        jceDisplayer.display(this.sLiveDesc, "sLiveDesc");
        jceDisplayer.display(this.iShortChannel, "iShortChannel");
        jceDisplayer.display(this.iBitRate, "iBitRate");
        jceDisplayer.display(this.iFrameRate, "iFrameRate");
        jceDisplayer.display(this.iSourceType, "iSourceType");
        jceDisplayer.display(this.iCodecType, "iCodecType");
        jceDisplayer.display(this.iScreenType, "iScreenType");
        jceDisplayer.display(this.lLiveCompatibleFlag, "lLiveCompatibleFlag");
        jceDisplayer.display((Map) this.mMiscInfo, "mMiscInfo");
        jceDisplayer.display(this.sGPSInfo, "sGPSInfo");
        jceDisplayer.display(this.lMultiStreamFlag, "lMultiStreamFlag");
    }
}
