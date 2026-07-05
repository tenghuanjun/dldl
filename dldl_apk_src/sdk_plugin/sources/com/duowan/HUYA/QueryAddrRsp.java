package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class QueryAddrRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sLiveDesc = "";
    public int iGameId = 0;
    public long lLiveFlag = 0;
    public String sRtmpAddr = "";
    public String sRtmpKey = "";
    public int iLiving = 0;
    public String sErrorMsg = "";

    public String className() {
        return "HUYA.QueryAddrRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.QueryAddrRsp";
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

    public long getLLiveFlag() {
        return this.lLiveFlag;
    }

    public void setLLiveFlag(long j) {
        this.lLiveFlag = j;
    }

    public String getSRtmpAddr() {
        return this.sRtmpAddr;
    }

    public void setSRtmpAddr(String str) {
        this.sRtmpAddr = str;
    }

    public String getSRtmpKey() {
        return this.sRtmpKey;
    }

    public void setSRtmpKey(String str) {
        this.sRtmpKey = str;
    }

    public int getILiving() {
        return this.iLiving;
    }

    public void setILiving(int i) {
        this.iLiving = i;
    }

    public String getSErrorMsg() {
        return this.sErrorMsg;
    }

    public void setSErrorMsg(String str) {
        this.sErrorMsg = str;
    }

    public QueryAddrRsp() {
        setSLiveDesc("");
        setIGameId(this.iGameId);
        setLLiveFlag(this.lLiveFlag);
        setSRtmpAddr(this.sRtmpAddr);
        setSRtmpKey(this.sRtmpKey);
        setILiving(this.iLiving);
        setSErrorMsg(this.sErrorMsg);
    }

    public QueryAddrRsp(String str, int i, long j, String str2, String str3, int i2, String str4) {
        setSLiveDesc(str);
        setIGameId(i);
        setLLiveFlag(j);
        setSRtmpAddr(str2);
        setSRtmpKey(str3);
        setILiving(i2);
        setSErrorMsg(str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        QueryAddrRsp queryAddrRsp = (QueryAddrRsp) obj;
        return JceUtil.equals(this.sLiveDesc, queryAddrRsp.sLiveDesc) && JceUtil.equals(this.iGameId, queryAddrRsp.iGameId) && JceUtil.equals(this.lLiveFlag, queryAddrRsp.lLiveFlag) && JceUtil.equals(this.sRtmpAddr, queryAddrRsp.sRtmpAddr) && JceUtil.equals(this.sRtmpKey, queryAddrRsp.sRtmpKey) && JceUtil.equals(this.iLiving, queryAddrRsp.iLiving) && JceUtil.equals(this.sErrorMsg, queryAddrRsp.sErrorMsg);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sLiveDesc), JceUtil.hashCode(this.iGameId), JceUtil.hashCode(this.lLiveFlag), JceUtil.hashCode(this.sRtmpAddr), JceUtil.hashCode(this.sRtmpKey), JceUtil.hashCode(this.iLiving), JceUtil.hashCode(this.sErrorMsg)});
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
        jceOutputStream.write(this.lLiveFlag, 2);
        String str2 = this.sRtmpAddr;
        if (str2 != null) {
            jceOutputStream.write(str2, 3);
        }
        String str3 = this.sRtmpKey;
        if (str3 != null) {
            jceOutputStream.write(str3, 4);
        }
        jceOutputStream.write(this.iLiving, 5);
        String str4 = this.sErrorMsg;
        if (str4 != null) {
            jceOutputStream.write(str4, 6);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSLiveDesc(jceInputStream.readString(0, false));
        setIGameId(jceInputStream.read(this.iGameId, 1, false));
        setLLiveFlag(jceInputStream.read(this.lLiveFlag, 2, false));
        setSRtmpAddr(jceInputStream.readString(3, false));
        setSRtmpKey(jceInputStream.readString(4, false));
        setILiving(jceInputStream.read(this.iLiving, 5, false));
        setSErrorMsg(jceInputStream.readString(6, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sLiveDesc, "sLiveDesc");
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display(this.lLiveFlag, "lLiveFlag");
        jceDisplayer.display(this.sRtmpAddr, "sRtmpAddr");
        jceDisplayer.display(this.sRtmpKey, "sRtmpKey");
        jceDisplayer.display(this.iLiving, "iLiving");
        jceDisplayer.display(this.sErrorMsg, "sErrorMsg");
    }
}
