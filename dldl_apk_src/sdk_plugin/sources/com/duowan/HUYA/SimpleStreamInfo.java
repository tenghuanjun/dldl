package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SimpleStreamInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<String> cache_vFlvIPList;
    static ArrayList<MultiStreamInfo> cache_vMultiStreamInfo;
    public int iBitRate;
    public int iDefaultBitRate;
    public int iIsP2PSupport;
    public int iLineIndex;
    public long lFreeFlag;
    public String sFlvAntiCode;
    public String sFlvUrl;
    public String sFlvUrlSuffix;
    public String sP2pAntiCode;
    public String sP2pUrl;
    public String sP2pUrlSuffix;
    public String sStreamName;
    public ArrayList<String> vFlvIPList;
    public ArrayList<MultiStreamInfo> vMultiStreamInfo;

    public String className() {
        return "HUYA.SimpleStreamInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SimpleStreamInfo";
    }

    public SimpleStreamInfo() {
        this.sStreamName = "";
        this.sFlvUrl = "";
        this.sFlvUrlSuffix = "";
        this.sFlvAntiCode = "";
        this.iLineIndex = 0;
        this.vFlvIPList = null;
        this.iIsP2PSupport = 0;
        this.sP2pUrl = "";
        this.sP2pUrlSuffix = "";
        this.sP2pAntiCode = "";
        this.vMultiStreamInfo = null;
        this.iBitRate = 0;
        this.iDefaultBitRate = 0;
        this.lFreeFlag = 0L;
    }

    public SimpleStreamInfo(String str, String str2, String str3, String str4, int i, ArrayList<String> arrayList, int i2, String str5, String str6, String str7, ArrayList<MultiStreamInfo> arrayList2, int i3, int i4, long j) {
        this.sStreamName = "";
        this.sFlvUrl = "";
        this.sFlvUrlSuffix = "";
        this.sFlvAntiCode = "";
        this.iLineIndex = 0;
        this.vFlvIPList = null;
        this.iIsP2PSupport = 0;
        this.sP2pUrl = "";
        this.sP2pUrlSuffix = "";
        this.sP2pAntiCode = "";
        this.vMultiStreamInfo = null;
        this.iBitRate = 0;
        this.iDefaultBitRate = 0;
        this.lFreeFlag = 0L;
        this.sStreamName = str;
        this.sFlvUrl = str2;
        this.sFlvUrlSuffix = str3;
        this.sFlvAntiCode = str4;
        this.iLineIndex = i;
        this.vFlvIPList = arrayList;
        this.iIsP2PSupport = i2;
        this.sP2pUrl = str5;
        this.sP2pUrlSuffix = str6;
        this.sP2pAntiCode = str7;
        this.vMultiStreamInfo = arrayList2;
        this.iBitRate = i3;
        this.iDefaultBitRate = i4;
        this.lFreeFlag = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SimpleStreamInfo simpleStreamInfo = (SimpleStreamInfo) obj;
        return JceUtil.equals(this.sStreamName, simpleStreamInfo.sStreamName) && JceUtil.equals(this.sFlvUrl, simpleStreamInfo.sFlvUrl) && JceUtil.equals(this.sFlvUrlSuffix, simpleStreamInfo.sFlvUrlSuffix) && JceUtil.equals(this.sFlvAntiCode, simpleStreamInfo.sFlvAntiCode) && JceUtil.equals(this.iLineIndex, simpleStreamInfo.iLineIndex) && JceUtil.equals(this.vFlvIPList, simpleStreamInfo.vFlvIPList) && JceUtil.equals(this.iIsP2PSupport, simpleStreamInfo.iIsP2PSupport) && JceUtil.equals(this.sP2pUrl, simpleStreamInfo.sP2pUrl) && JceUtil.equals(this.sP2pUrlSuffix, simpleStreamInfo.sP2pUrlSuffix) && JceUtil.equals(this.sP2pAntiCode, simpleStreamInfo.sP2pAntiCode) && JceUtil.equals(this.vMultiStreamInfo, simpleStreamInfo.vMultiStreamInfo) && JceUtil.equals(this.iBitRate, simpleStreamInfo.iBitRate) && JceUtil.equals(this.iDefaultBitRate, simpleStreamInfo.iDefaultBitRate) && JceUtil.equals(this.lFreeFlag, simpleStreamInfo.lFreeFlag);
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
        String str = this.sStreamName;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sFlvUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
        String str3 = this.sFlvUrlSuffix;
        if (str3 != null) {
            jceOutputStream.write(str3, 2);
        }
        String str4 = this.sFlvAntiCode;
        if (str4 != null) {
            jceOutputStream.write(str4, 3);
        }
        jceOutputStream.write(this.iLineIndex, 4);
        ArrayList<String> arrayList = this.vFlvIPList;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 5);
        }
        jceOutputStream.write(this.iIsP2PSupport, 6);
        String str5 = this.sP2pUrl;
        if (str5 != null) {
            jceOutputStream.write(str5, 7);
        }
        String str6 = this.sP2pUrlSuffix;
        if (str6 != null) {
            jceOutputStream.write(str6, 8);
        }
        String str7 = this.sP2pAntiCode;
        if (str7 != null) {
            jceOutputStream.write(str7, 9);
        }
        ArrayList<MultiStreamInfo> arrayList2 = this.vMultiStreamInfo;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 10);
        }
        jceOutputStream.write(this.iBitRate, 11);
        jceOutputStream.write(this.iDefaultBitRate, 12);
        jceOutputStream.write(this.lFreeFlag, 13);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        this.sStreamName = jceInputStream.readString(0, false);
        this.sFlvUrl = jceInputStream.readString(1, false);
        this.sFlvUrlSuffix = jceInputStream.readString(2, false);
        this.sFlvAntiCode = jceInputStream.readString(3, false);
        this.iLineIndex = jceInputStream.read(this.iLineIndex, 4, false);
        if (cache_vFlvIPList == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vFlvIPList = arrayList;
            arrayList.add("");
        }
        this.vFlvIPList = (ArrayList) jceInputStream.read(cache_vFlvIPList, 5, false);
        this.iIsP2PSupport = jceInputStream.read(this.iIsP2PSupport, 6, false);
        this.sP2pUrl = jceInputStream.readString(7, false);
        this.sP2pUrlSuffix = jceInputStream.readString(8, false);
        this.sP2pAntiCode = jceInputStream.readString(9, false);
        if (cache_vMultiStreamInfo == null) {
            cache_vMultiStreamInfo = new ArrayList<>();
            cache_vMultiStreamInfo.add(new MultiStreamInfo());
        }
        this.vMultiStreamInfo = (ArrayList) jceInputStream.read(cache_vMultiStreamInfo, 10, false);
        this.iBitRate = jceInputStream.read(this.iBitRate, 11, false);
        this.iDefaultBitRate = jceInputStream.read(this.iDefaultBitRate, 12, false);
        this.lFreeFlag = jceInputStream.read(this.lFreeFlag, 13, false);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sStreamName, "sStreamName");
        jceDisplayer.display(this.sFlvUrl, "sFlvUrl");
        jceDisplayer.display(this.sFlvUrlSuffix, "sFlvUrlSuffix");
        jceDisplayer.display(this.sFlvAntiCode, "sFlvAntiCode");
        jceDisplayer.display(this.iLineIndex, "iLineIndex");
        jceDisplayer.display((Collection) this.vFlvIPList, "vFlvIPList");
        jceDisplayer.display(this.iIsP2PSupport, "iIsP2PSupport");
        jceDisplayer.display(this.sP2pUrl, "sP2pUrl");
        jceDisplayer.display(this.sP2pUrlSuffix, "sP2pUrlSuffix");
        jceDisplayer.display(this.sP2pAntiCode, "sP2pAntiCode");
        jceDisplayer.display((Collection) this.vMultiStreamInfo, "vMultiStreamInfo");
        jceDisplayer.display(this.iBitRate, "iBitRate");
        jceDisplayer.display(this.iDefaultBitRate, "iDefaultBitRate");
        jceDisplayer.display(this.lFreeFlag, "lFreeFlag");
    }
}
