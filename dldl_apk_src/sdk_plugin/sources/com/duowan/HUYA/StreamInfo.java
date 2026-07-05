package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class StreamInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<String> cache_vFlvIPList;
    static ArrayList<String> cache_vP2pIPList;
    public String sCdnType = "";
    public int iIsMaster = 0;
    public long lChannelId = 0;
    public long lSubChannelId = 0;
    public long lPresenterUid = 0;
    public String sStreamName = "";
    public String sFlvUrl = "";
    public String sFlvUrlSuffix = "";
    public String sFlvAntiCode = "";
    public String sHlsUrl = "";
    public String sHlsUrlSuffix = "";
    public String sHlsAntiCode = "";
    public int iLineIndex = 0;
    public int iIsMultiStream = 0;
    public int iPCPriorityRate = 0;
    public int iWebPriorityRate = 0;
    public int iMobilePriorityRate = 0;
    public ArrayList<String> vFlvIPList = null;
    public int iIsP2PSupport = 0;
    public String sP2pUrl = "";
    public String sP2pUrlSuffix = "";
    public String sP2pAntiCode = "";
    public long lFreeFlag = 0;
    public int iIsHEVCSupport = 0;
    public ArrayList<String> vP2pIPList = null;

    public String className() {
        return "HUYA.StreamInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.StreamInfo";
    }

    public String getSCdnType() {
        return this.sCdnType;
    }

    public void setSCdnType(String str) {
        this.sCdnType = str;
    }

    public int getIIsMaster() {
        return this.iIsMaster;
    }

    public void setIIsMaster(int i) {
        this.iIsMaster = i;
    }

    public long getLChannelId() {
        return this.lChannelId;
    }

    public void setLChannelId(long j) {
        this.lChannelId = j;
    }

    public long getLSubChannelId() {
        return this.lSubChannelId;
    }

    public void setLSubChannelId(long j) {
        this.lSubChannelId = j;
    }

    public long getLPresenterUid() {
        return this.lPresenterUid;
    }

    public void setLPresenterUid(long j) {
        this.lPresenterUid = j;
    }

    public String getSStreamName() {
        return this.sStreamName;
    }

    public void setSStreamName(String str) {
        this.sStreamName = str;
    }

    public String getSFlvUrl() {
        return this.sFlvUrl;
    }

    public void setSFlvUrl(String str) {
        this.sFlvUrl = str;
    }

    public String getSFlvUrlSuffix() {
        return this.sFlvUrlSuffix;
    }

    public void setSFlvUrlSuffix(String str) {
        this.sFlvUrlSuffix = str;
    }

    public String getSFlvAntiCode() {
        return this.sFlvAntiCode;
    }

    public void setSFlvAntiCode(String str) {
        this.sFlvAntiCode = str;
    }

    public String getSHlsUrl() {
        return this.sHlsUrl;
    }

    public void setSHlsUrl(String str) {
        this.sHlsUrl = str;
    }

    public String getSHlsUrlSuffix() {
        return this.sHlsUrlSuffix;
    }

    public void setSHlsUrlSuffix(String str) {
        this.sHlsUrlSuffix = str;
    }

    public String getSHlsAntiCode() {
        return this.sHlsAntiCode;
    }

    public void setSHlsAntiCode(String str) {
        this.sHlsAntiCode = str;
    }

    public int getILineIndex() {
        return this.iLineIndex;
    }

    public void setILineIndex(int i) {
        this.iLineIndex = i;
    }

    public int getIIsMultiStream() {
        return this.iIsMultiStream;
    }

    public void setIIsMultiStream(int i) {
        this.iIsMultiStream = i;
    }

    public int getIPCPriorityRate() {
        return this.iPCPriorityRate;
    }

    public void setIPCPriorityRate(int i) {
        this.iPCPriorityRate = i;
    }

    public int getIWebPriorityRate() {
        return this.iWebPriorityRate;
    }

    public void setIWebPriorityRate(int i) {
        this.iWebPriorityRate = i;
    }

    public int getIMobilePriorityRate() {
        return this.iMobilePriorityRate;
    }

    public void setIMobilePriorityRate(int i) {
        this.iMobilePriorityRate = i;
    }

    public ArrayList<String> getVFlvIPList() {
        return this.vFlvIPList;
    }

    public void setVFlvIPList(ArrayList<String> arrayList) {
        this.vFlvIPList = arrayList;
    }

    public int getIIsP2PSupport() {
        return this.iIsP2PSupport;
    }

    public void setIIsP2PSupport(int i) {
        this.iIsP2PSupport = i;
    }

    public String getSP2pUrl() {
        return this.sP2pUrl;
    }

    public void setSP2pUrl(String str) {
        this.sP2pUrl = str;
    }

    public String getSP2pUrlSuffix() {
        return this.sP2pUrlSuffix;
    }

    public void setSP2pUrlSuffix(String str) {
        this.sP2pUrlSuffix = str;
    }

    public String getSP2pAntiCode() {
        return this.sP2pAntiCode;
    }

    public void setSP2pAntiCode(String str) {
        this.sP2pAntiCode = str;
    }

    public long getLFreeFlag() {
        return this.lFreeFlag;
    }

    public void setLFreeFlag(long j) {
        this.lFreeFlag = j;
    }

    public int getIIsHEVCSupport() {
        return this.iIsHEVCSupport;
    }

    public void setIIsHEVCSupport(int i) {
        this.iIsHEVCSupport = i;
    }

    public ArrayList<String> getVP2pIPList() {
        return this.vP2pIPList;
    }

    public void setVP2pIPList(ArrayList<String> arrayList) {
        this.vP2pIPList = arrayList;
    }

    public StreamInfo() {
        setSCdnType("");
        setIIsMaster(this.iIsMaster);
        setLChannelId(this.lChannelId);
        setLSubChannelId(this.lSubChannelId);
        setLPresenterUid(this.lPresenterUid);
        setSStreamName(this.sStreamName);
        setSFlvUrl(this.sFlvUrl);
        setSFlvUrlSuffix(this.sFlvUrlSuffix);
        setSFlvAntiCode(this.sFlvAntiCode);
        setSHlsUrl(this.sHlsUrl);
        setSHlsUrlSuffix(this.sHlsUrlSuffix);
        setSHlsAntiCode(this.sHlsAntiCode);
        setILineIndex(this.iLineIndex);
        setIIsMultiStream(this.iIsMultiStream);
        setIPCPriorityRate(this.iPCPriorityRate);
        setIWebPriorityRate(this.iWebPriorityRate);
        setIMobilePriorityRate(this.iMobilePriorityRate);
        setVFlvIPList(this.vFlvIPList);
        setIIsP2PSupport(this.iIsP2PSupport);
        setSP2pUrl(this.sP2pUrl);
        setSP2pUrlSuffix(this.sP2pUrlSuffix);
        setSP2pAntiCode(this.sP2pAntiCode);
        setLFreeFlag(this.lFreeFlag);
        setIIsHEVCSupport(this.iIsHEVCSupport);
        setVP2pIPList(this.vP2pIPList);
    }

    public StreamInfo(String str, int i, long j, long j2, long j3, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2, int i3, int i4, int i5, int i6, ArrayList<String> arrayList, int i7, String str9, String str10, String str11, long j4, int i8, ArrayList<String> arrayList2) {
        setSCdnType(str);
        setIIsMaster(i);
        setLChannelId(j);
        setLSubChannelId(j2);
        setLPresenterUid(j3);
        setSStreamName(str2);
        setSFlvUrl(str3);
        setSFlvUrlSuffix(str4);
        setSFlvAntiCode(str5);
        setSHlsUrl(str6);
        setSHlsUrlSuffix(str7);
        setSHlsAntiCode(str8);
        setILineIndex(i2);
        setIIsMultiStream(i3);
        setIPCPriorityRate(i4);
        setIWebPriorityRate(i5);
        setIMobilePriorityRate(i6);
        setVFlvIPList(arrayList);
        setIIsP2PSupport(i7);
        setSP2pUrl(str9);
        setSP2pUrlSuffix(str10);
        setSP2pAntiCode(str11);
        setLFreeFlag(j4);
        setIIsHEVCSupport(i8);
        setVP2pIPList(arrayList2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StreamInfo streamInfo = (StreamInfo) obj;
        return JceUtil.equals(this.sCdnType, streamInfo.sCdnType) && JceUtil.equals(this.iIsMaster, streamInfo.iIsMaster) && JceUtil.equals(this.lChannelId, streamInfo.lChannelId) && JceUtil.equals(this.lSubChannelId, streamInfo.lSubChannelId) && JceUtil.equals(this.lPresenterUid, streamInfo.lPresenterUid) && JceUtil.equals(this.sStreamName, streamInfo.sStreamName) && JceUtil.equals(this.sFlvUrl, streamInfo.sFlvUrl) && JceUtil.equals(this.sFlvUrlSuffix, streamInfo.sFlvUrlSuffix) && JceUtil.equals(this.sFlvAntiCode, streamInfo.sFlvAntiCode) && JceUtil.equals(this.sHlsUrl, streamInfo.sHlsUrl) && JceUtil.equals(this.sHlsUrlSuffix, streamInfo.sHlsUrlSuffix) && JceUtil.equals(this.sHlsAntiCode, streamInfo.sHlsAntiCode) && JceUtil.equals(this.iLineIndex, streamInfo.iLineIndex) && JceUtil.equals(this.iIsMultiStream, streamInfo.iIsMultiStream) && JceUtil.equals(this.iPCPriorityRate, streamInfo.iPCPriorityRate) && JceUtil.equals(this.iWebPriorityRate, streamInfo.iWebPriorityRate) && JceUtil.equals(this.iMobilePriorityRate, streamInfo.iMobilePriorityRate) && JceUtil.equals(this.vFlvIPList, streamInfo.vFlvIPList) && JceUtil.equals(this.iIsP2PSupport, streamInfo.iIsP2PSupport) && JceUtil.equals(this.sP2pUrl, streamInfo.sP2pUrl) && JceUtil.equals(this.sP2pUrlSuffix, streamInfo.sP2pUrlSuffix) && JceUtil.equals(this.sP2pAntiCode, streamInfo.sP2pAntiCode) && JceUtil.equals(this.lFreeFlag, streamInfo.lFreeFlag) && JceUtil.equals(this.iIsHEVCSupport, streamInfo.iIsHEVCSupport) && JceUtil.equals(this.vP2pIPList, streamInfo.vP2pIPList);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sCdnType), JceUtil.hashCode(this.iIsMaster), JceUtil.hashCode(this.lChannelId), JceUtil.hashCode(this.lSubChannelId), JceUtil.hashCode(this.lPresenterUid), JceUtil.hashCode(this.sStreamName), JceUtil.hashCode(this.sFlvUrl), JceUtil.hashCode(this.sFlvUrlSuffix), JceUtil.hashCode(this.sFlvAntiCode), JceUtil.hashCode(this.sHlsUrl), JceUtil.hashCode(this.sHlsUrlSuffix), JceUtil.hashCode(this.sHlsAntiCode), JceUtil.hashCode(this.iLineIndex), JceUtil.hashCode(this.iIsMultiStream), JceUtil.hashCode(this.iPCPriorityRate), JceUtil.hashCode(this.iWebPriorityRate), JceUtil.hashCode(this.iMobilePriorityRate), JceUtil.hashCode(this.vFlvIPList), JceUtil.hashCode(this.iIsP2PSupport), JceUtil.hashCode(this.sP2pUrl), JceUtil.hashCode(this.sP2pUrlSuffix), JceUtil.hashCode(this.sP2pAntiCode), JceUtil.hashCode(this.lFreeFlag), JceUtil.hashCode(this.iIsHEVCSupport), JceUtil.hashCode(this.vP2pIPList)});
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
        String str = this.sCdnType;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        jceOutputStream.write(this.iIsMaster, 1);
        jceOutputStream.write(this.lChannelId, 2);
        jceOutputStream.write(this.lSubChannelId, 3);
        jceOutputStream.write(this.lPresenterUid, 4);
        String str2 = this.sStreamName;
        if (str2 != null) {
            jceOutputStream.write(str2, 5);
        }
        String str3 = this.sFlvUrl;
        if (str3 != null) {
            jceOutputStream.write(str3, 6);
        }
        String str4 = this.sFlvUrlSuffix;
        if (str4 != null) {
            jceOutputStream.write(str4, 7);
        }
        String str5 = this.sFlvAntiCode;
        if (str5 != null) {
            jceOutputStream.write(str5, 8);
        }
        String str6 = this.sHlsUrl;
        if (str6 != null) {
            jceOutputStream.write(str6, 9);
        }
        String str7 = this.sHlsUrlSuffix;
        if (str7 != null) {
            jceOutputStream.write(str7, 10);
        }
        String str8 = this.sHlsAntiCode;
        if (str8 != null) {
            jceOutputStream.write(str8, 11);
        }
        jceOutputStream.write(this.iLineIndex, 12);
        jceOutputStream.write(this.iIsMultiStream, 13);
        jceOutputStream.write(this.iPCPriorityRate, 14);
        jceOutputStream.write(this.iWebPriorityRate, 15);
        jceOutputStream.write(this.iMobilePriorityRate, 16);
        ArrayList<String> arrayList = this.vFlvIPList;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 17);
        }
        jceOutputStream.write(this.iIsP2PSupport, 18);
        String str9 = this.sP2pUrl;
        if (str9 != null) {
            jceOutputStream.write(str9, 19);
        }
        String str10 = this.sP2pUrlSuffix;
        if (str10 != null) {
            jceOutputStream.write(str10, 20);
        }
        String str11 = this.sP2pAntiCode;
        if (str11 != null) {
            jceOutputStream.write(str11, 21);
        }
        jceOutputStream.write(this.lFreeFlag, 22);
        jceOutputStream.write(this.iIsHEVCSupport, 23);
        ArrayList<String> arrayList2 = this.vP2pIPList;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 24);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSCdnType(jceInputStream.readString(0, false));
        setIIsMaster(jceInputStream.read(this.iIsMaster, 1, false));
        setLChannelId(jceInputStream.read(this.lChannelId, 2, false));
        setLSubChannelId(jceInputStream.read(this.lSubChannelId, 3, false));
        setLPresenterUid(jceInputStream.read(this.lPresenterUid, 4, false));
        setSStreamName(jceInputStream.readString(5, false));
        setSFlvUrl(jceInputStream.readString(6, false));
        setSFlvUrlSuffix(jceInputStream.readString(7, false));
        setSFlvAntiCode(jceInputStream.readString(8, false));
        setSHlsUrl(jceInputStream.readString(9, false));
        setSHlsUrlSuffix(jceInputStream.readString(10, false));
        setSHlsAntiCode(jceInputStream.readString(11, false));
        setILineIndex(jceInputStream.read(this.iLineIndex, 12, false));
        setIIsMultiStream(jceInputStream.read(this.iIsMultiStream, 13, false));
        setIPCPriorityRate(jceInputStream.read(this.iPCPriorityRate, 14, false));
        setIWebPriorityRate(jceInputStream.read(this.iWebPriorityRate, 15, false));
        setIMobilePriorityRate(jceInputStream.read(this.iMobilePriorityRate, 16, false));
        if (cache_vFlvIPList == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vFlvIPList = arrayList;
            arrayList.add("");
        }
        setVFlvIPList((ArrayList) jceInputStream.read(cache_vFlvIPList, 17, false));
        setIIsP2PSupport(jceInputStream.read(this.iIsP2PSupport, 18, false));
        setSP2pUrl(jceInputStream.readString(19, false));
        setSP2pUrlSuffix(jceInputStream.readString(20, false));
        setSP2pAntiCode(jceInputStream.readString(21, false));
        setLFreeFlag(jceInputStream.read(this.lFreeFlag, 22, false));
        setIIsHEVCSupport(jceInputStream.read(this.iIsHEVCSupport, 23, false));
        if (cache_vP2pIPList == null) {
            ArrayList<String> arrayList2 = new ArrayList<>();
            cache_vP2pIPList = arrayList2;
            arrayList2.add("");
        }
        setVP2pIPList((ArrayList) jceInputStream.read(cache_vP2pIPList, 24, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sCdnType, "sCdnType");
        jceDisplayer.display(this.iIsMaster, "iIsMaster");
        jceDisplayer.display(this.lChannelId, "lChannelId");
        jceDisplayer.display(this.lSubChannelId, "lSubChannelId");
        jceDisplayer.display(this.lPresenterUid, "lPresenterUid");
        jceDisplayer.display(this.sStreamName, "sStreamName");
        jceDisplayer.display(this.sFlvUrl, "sFlvUrl");
        jceDisplayer.display(this.sFlvUrlSuffix, "sFlvUrlSuffix");
        jceDisplayer.display(this.sFlvAntiCode, "sFlvAntiCode");
        jceDisplayer.display(this.sHlsUrl, "sHlsUrl");
        jceDisplayer.display(this.sHlsUrlSuffix, "sHlsUrlSuffix");
        jceDisplayer.display(this.sHlsAntiCode, "sHlsAntiCode");
        jceDisplayer.display(this.iLineIndex, "iLineIndex");
        jceDisplayer.display(this.iIsMultiStream, "iIsMultiStream");
        jceDisplayer.display(this.iPCPriorityRate, "iPCPriorityRate");
        jceDisplayer.display(this.iWebPriorityRate, "iWebPriorityRate");
        jceDisplayer.display(this.iMobilePriorityRate, "iMobilePriorityRate");
        jceDisplayer.display((Collection) this.vFlvIPList, "vFlvIPList");
        jceDisplayer.display(this.iIsP2PSupport, "iIsP2PSupport");
        jceDisplayer.display(this.sP2pUrl, "sP2pUrl");
        jceDisplayer.display(this.sP2pUrlSuffix, "sP2pUrlSuffix");
        jceDisplayer.display(this.sP2pAntiCode, "sP2pAntiCode");
        jceDisplayer.display(this.lFreeFlag, "lFreeFlag");
        jceDisplayer.display(this.iIsHEVCSupport, "iIsHEVCSupport");
        jceDisplayer.display((Collection) this.vP2pIPList, "vP2pIPList");
    }
}
