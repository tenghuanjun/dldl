package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class BeginLiveRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Map<Long, Long> cache_mpMultiStream;
    static ArrayList<PopupButtonInfo> cache_vButtonInfo;
    static ArrayList<String> cache_vSwitchStreamUrl;
    public String sMesssage = "";
    public int iStreamType = 0;
    public String sUpStreamAddress = "";
    public int iHeartbeatInterval = 0;
    public int iIsMultiStream = 0;
    public int iRespCode = 13;
    public ArrayList<String> vSwitchStreamUrl = null;
    public long lLiveId = 0;
    public long lMultiStreamFlag = 0;
    public Map<Long, Long> mpMultiStream = null;
    public long lChannelId = 0;
    public String sTitle = "";
    public ArrayList<PopupButtonInfo> vButtonInfo = null;

    public String className() {
        return "HUYA.BeginLiveRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.BeginLiveRsp";
    }

    public String getSMesssage() {
        return this.sMesssage;
    }

    public void setSMesssage(String str) {
        this.sMesssage = str;
    }

    public int getIStreamType() {
        return this.iStreamType;
    }

    public void setIStreamType(int i) {
        this.iStreamType = i;
    }

    public String getSUpStreamAddress() {
        return this.sUpStreamAddress;
    }

    public void setSUpStreamAddress(String str) {
        this.sUpStreamAddress = str;
    }

    public int getIHeartbeatInterval() {
        return this.iHeartbeatInterval;
    }

    public void setIHeartbeatInterval(int i) {
        this.iHeartbeatInterval = i;
    }

    public int getIIsMultiStream() {
        return this.iIsMultiStream;
    }

    public void setIIsMultiStream(int i) {
        this.iIsMultiStream = i;
    }

    public int getIRespCode() {
        return this.iRespCode;
    }

    public void setIRespCode(int i) {
        this.iRespCode = i;
    }

    public ArrayList<String> getVSwitchStreamUrl() {
        return this.vSwitchStreamUrl;
    }

    public void setVSwitchStreamUrl(ArrayList<String> arrayList) {
        this.vSwitchStreamUrl = arrayList;
    }

    public long getLLiveId() {
        return this.lLiveId;
    }

    public void setLLiveId(long j) {
        this.lLiveId = j;
    }

    public long getLMultiStreamFlag() {
        return this.lMultiStreamFlag;
    }

    public void setLMultiStreamFlag(long j) {
        this.lMultiStreamFlag = j;
    }

    public Map<Long, Long> getMpMultiStream() {
        return this.mpMultiStream;
    }

    public void setMpMultiStream(Map<Long, Long> map) {
        this.mpMultiStream = map;
    }

    public long getLChannelId() {
        return this.lChannelId;
    }

    public void setLChannelId(long j) {
        this.lChannelId = j;
    }

    public String getSTitle() {
        return this.sTitle;
    }

    public void setSTitle(String str) {
        this.sTitle = str;
    }

    public ArrayList<PopupButtonInfo> getVButtonInfo() {
        return this.vButtonInfo;
    }

    public void setVButtonInfo(ArrayList<PopupButtonInfo> arrayList) {
        this.vButtonInfo = arrayList;
    }

    public BeginLiveRsp() {
        setSMesssage("");
        setIStreamType(this.iStreamType);
        setSUpStreamAddress(this.sUpStreamAddress);
        setIHeartbeatInterval(this.iHeartbeatInterval);
        setIIsMultiStream(this.iIsMultiStream);
        setIRespCode(this.iRespCode);
        setVSwitchStreamUrl(this.vSwitchStreamUrl);
        setLLiveId(this.lLiveId);
        setLMultiStreamFlag(this.lMultiStreamFlag);
        setMpMultiStream(this.mpMultiStream);
        setLChannelId(this.lChannelId);
        setSTitle(this.sTitle);
        setVButtonInfo(this.vButtonInfo);
    }

    public BeginLiveRsp(String str, int i, String str2, int i2, int i3, int i4, ArrayList<String> arrayList, long j, long j2, Map<Long, Long> map, long j3, String str3, ArrayList<PopupButtonInfo> arrayList2) {
        setSMesssage(str);
        setIStreamType(i);
        setSUpStreamAddress(str2);
        setIHeartbeatInterval(i2);
        setIIsMultiStream(i3);
        setIRespCode(i4);
        setVSwitchStreamUrl(arrayList);
        setLLiveId(j);
        setLMultiStreamFlag(j2);
        setMpMultiStream(map);
        setLChannelId(j3);
        setSTitle(str3);
        setVButtonInfo(arrayList2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BeginLiveRsp beginLiveRsp = (BeginLiveRsp) obj;
        return JceUtil.equals(this.sMesssage, beginLiveRsp.sMesssage) && JceUtil.equals(this.iStreamType, beginLiveRsp.iStreamType) && JceUtil.equals(this.sUpStreamAddress, beginLiveRsp.sUpStreamAddress) && JceUtil.equals(this.iHeartbeatInterval, beginLiveRsp.iHeartbeatInterval) && JceUtil.equals(this.iIsMultiStream, beginLiveRsp.iIsMultiStream) && JceUtil.equals(this.iRespCode, beginLiveRsp.iRespCode) && JceUtil.equals(this.vSwitchStreamUrl, beginLiveRsp.vSwitchStreamUrl) && JceUtil.equals(this.lLiveId, beginLiveRsp.lLiveId) && JceUtil.equals(this.lMultiStreamFlag, beginLiveRsp.lMultiStreamFlag) && JceUtil.equals(this.mpMultiStream, beginLiveRsp.mpMultiStream) && JceUtil.equals(this.lChannelId, beginLiveRsp.lChannelId) && JceUtil.equals(this.sTitle, beginLiveRsp.sTitle) && JceUtil.equals(this.vButtonInfo, beginLiveRsp.vButtonInfo);
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
        String str = this.sMesssage;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        jceOutputStream.write(this.iStreamType, 1);
        String str2 = this.sUpStreamAddress;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        jceOutputStream.write(this.iHeartbeatInterval, 3);
        jceOutputStream.write(this.iIsMultiStream, 4);
        jceOutputStream.write(this.iRespCode, 5);
        ArrayList<String> arrayList = this.vSwitchStreamUrl;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 6);
        }
        jceOutputStream.write(this.lLiveId, 7);
        jceOutputStream.write(this.lMultiStreamFlag, 8);
        Map<Long, Long> map = this.mpMultiStream;
        if (map != null) {
            jceOutputStream.write((Map) map, 9);
        }
        jceOutputStream.write(this.lChannelId, 10);
        String str3 = this.sTitle;
        if (str3 != null) {
            jceOutputStream.write(str3, 11);
        }
        ArrayList<PopupButtonInfo> arrayList2 = this.vButtonInfo;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 12);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSMesssage(jceInputStream.readString(0, false));
        setIStreamType(jceInputStream.read(this.iStreamType, 1, false));
        setSUpStreamAddress(jceInputStream.readString(2, false));
        setIHeartbeatInterval(jceInputStream.read(this.iHeartbeatInterval, 3, false));
        setIIsMultiStream(jceInputStream.read(this.iIsMultiStream, 4, false));
        setIRespCode(jceInputStream.read(this.iRespCode, 5, false));
        if (cache_vSwitchStreamUrl == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vSwitchStreamUrl = arrayList;
            arrayList.add("");
        }
        setVSwitchStreamUrl((ArrayList) jceInputStream.read(cache_vSwitchStreamUrl, 6, false));
        setLLiveId(jceInputStream.read(this.lLiveId, 7, false));
        setLMultiStreamFlag(jceInputStream.read(this.lMultiStreamFlag, 8, false));
        if (cache_mpMultiStream == null) {
            cache_mpMultiStream = new HashMap();
            cache_mpMultiStream.put(0L, 0L);
        }
        setMpMultiStream((Map) jceInputStream.read(cache_mpMultiStream, 9, false));
        setLChannelId(jceInputStream.read(this.lChannelId, 10, false));
        setSTitle(jceInputStream.readString(11, false));
        if (cache_vButtonInfo == null) {
            cache_vButtonInfo = new ArrayList<>();
            cache_vButtonInfo.add(new PopupButtonInfo());
        }
        setVButtonInfo((ArrayList) jceInputStream.read(cache_vButtonInfo, 12, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sMesssage, "sMesssage");
        jceDisplayer.display(this.iStreamType, "iStreamType");
        jceDisplayer.display(this.sUpStreamAddress, "sUpStreamAddress");
        jceDisplayer.display(this.iHeartbeatInterval, "iHeartbeatInterval");
        jceDisplayer.display(this.iIsMultiStream, "iIsMultiStream");
        jceDisplayer.display(this.iRespCode, "iRespCode");
        jceDisplayer.display((Collection) this.vSwitchStreamUrl, "vSwitchStreamUrl");
        jceDisplayer.display(this.lLiveId, "lLiveId");
        jceDisplayer.display(this.lMultiStreamFlag, "lMultiStreamFlag");
        jceDisplayer.display((Map) this.mpMultiStream, "mpMultiStream");
        jceDisplayer.display(this.lChannelId, "lChannelId");
        jceDisplayer.display(this.sTitle, "sTitle");
        jceDisplayer.display((Collection) this.vButtonInfo, "vButtonInfo");
    }
}
