package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetInfoFromVGReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static byte[] cache_sMsg;
    static UserId cache_tId;
    static ArrayList<String> cache_vStreamName;
    public long lUid = 0;
    public int iServiceType = 0;
    public int iClientType = 0;
    public long iClientIp = 0;
    public int iTerminalType = 0;
    public int iRequestVPType = 0;
    public byte[] sMsg = null;
    public long iUri = 0;
    public String sProtoName = "";
    public long lSid = 0;
    public ArrayList<String> vStreamName = null;
    public UserId tId = null;

    public String className() {
        return "HUYA.GetInfoFromVGReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetInfoFromVGReq";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public int getIServiceType() {
        return this.iServiceType;
    }

    public void setIServiceType(int i) {
        this.iServiceType = i;
    }

    public int getIClientType() {
        return this.iClientType;
    }

    public void setIClientType(int i) {
        this.iClientType = i;
    }

    public long getIClientIp() {
        return this.iClientIp;
    }

    public void setIClientIp(long j) {
        this.iClientIp = j;
    }

    public int getITerminalType() {
        return this.iTerminalType;
    }

    public void setITerminalType(int i) {
        this.iTerminalType = i;
    }

    public int getIRequestVPType() {
        return this.iRequestVPType;
    }

    public void setIRequestVPType(int i) {
        this.iRequestVPType = i;
    }

    public byte[] getSMsg() {
        return this.sMsg;
    }

    public void setSMsg(byte[] bArr) {
        this.sMsg = bArr;
    }

    public long getIUri() {
        return this.iUri;
    }

    public void setIUri(long j) {
        this.iUri = j;
    }

    public String getSProtoName() {
        return this.sProtoName;
    }

    public void setSProtoName(String str) {
        this.sProtoName = str;
    }

    public long getLSid() {
        return this.lSid;
    }

    public void setLSid(long j) {
        this.lSid = j;
    }

    public ArrayList<String> getVStreamName() {
        return this.vStreamName;
    }

    public void setVStreamName(ArrayList<String> arrayList) {
        this.vStreamName = arrayList;
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public GetInfoFromVGReq() {
        setLUid(0L);
        setIServiceType(this.iServiceType);
        setIClientType(this.iClientType);
        setIClientIp(this.iClientIp);
        setITerminalType(this.iTerminalType);
        setIRequestVPType(this.iRequestVPType);
        setSMsg(this.sMsg);
        setIUri(this.iUri);
        setSProtoName(this.sProtoName);
        setLSid(this.lSid);
        setVStreamName(this.vStreamName);
        setTId(this.tId);
    }

    public GetInfoFromVGReq(long j, int i, int i2, long j2, int i3, int i4, byte[] bArr, long j3, String str, long j4, ArrayList<String> arrayList, UserId userId) {
        setLUid(j);
        setIServiceType(i);
        setIClientType(i2);
        setIClientIp(j2);
        setITerminalType(i3);
        setIRequestVPType(i4);
        setSMsg(bArr);
        setIUri(j3);
        setSProtoName(str);
        setLSid(j4);
        setVStreamName(arrayList);
        setTId(userId);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetInfoFromVGReq getInfoFromVGReq = (GetInfoFromVGReq) obj;
        return JceUtil.equals(this.lUid, getInfoFromVGReq.lUid) && JceUtil.equals(this.iServiceType, getInfoFromVGReq.iServiceType) && JceUtil.equals(this.iClientType, getInfoFromVGReq.iClientType) && JceUtil.equals(this.iClientIp, getInfoFromVGReq.iClientIp) && JceUtil.equals(this.iTerminalType, getInfoFromVGReq.iTerminalType) && JceUtil.equals(this.iRequestVPType, getInfoFromVGReq.iRequestVPType) && JceUtil.equals(this.sMsg, getInfoFromVGReq.sMsg) && JceUtil.equals(this.iUri, getInfoFromVGReq.iUri) && JceUtil.equals(this.sProtoName, getInfoFromVGReq.sProtoName) && JceUtil.equals(this.lSid, getInfoFromVGReq.lSid) && JceUtil.equals(this.vStreamName, getInfoFromVGReq.vStreamName) && JceUtil.equals(this.tId, getInfoFromVGReq.tId);
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
        jceOutputStream.write(this.lUid, 0);
        jceOutputStream.write(this.iServiceType, 1);
        jceOutputStream.write(this.iClientType, 2);
        jceOutputStream.write(this.iClientIp, 3);
        jceOutputStream.write(this.iTerminalType, 4);
        jceOutputStream.write(this.iRequestVPType, 5);
        byte[] bArr = this.sMsg;
        if (bArr != null) {
            jceOutputStream.write(bArr, 6);
        }
        jceOutputStream.write(this.iUri, 7);
        String str = this.sProtoName;
        if (str != null) {
            jceOutputStream.write(str, 8);
        }
        jceOutputStream.write(this.lSid, 9);
        ArrayList<String> arrayList = this.vStreamName;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 10);
        }
        UserId userId = this.tId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 11);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setIServiceType(jceInputStream.read(this.iServiceType, 1, false));
        setIClientType(jceInputStream.read(this.iClientType, 2, false));
        setIClientIp(jceInputStream.read(this.iClientIp, 3, false));
        setITerminalType(jceInputStream.read(this.iTerminalType, 4, false));
        setIRequestVPType(jceInputStream.read(this.iRequestVPType, 5, false));
        if (cache_sMsg == null) {
            cache_sMsg = new byte[]{0};
        }
        setSMsg(jceInputStream.read(cache_sMsg, 6, false));
        setIUri(jceInputStream.read(this.iUri, 7, false));
        setSProtoName(jceInputStream.readString(8, false));
        setLSid(jceInputStream.read(this.lSid, 9, false));
        if (cache_vStreamName == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vStreamName = arrayList;
            arrayList.add("");
        }
        setVStreamName((ArrayList) jceInputStream.read(cache_vStreamName, 10, false));
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 11, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.iServiceType, "iServiceType");
        jceDisplayer.display(this.iClientType, "iClientType");
        jceDisplayer.display(this.iClientIp, "iClientIp");
        jceDisplayer.display(this.iTerminalType, "iTerminalType");
        jceDisplayer.display(this.iRequestVPType, "iRequestVPType");
        jceDisplayer.display(this.sMsg, "sMsg");
        jceDisplayer.display(this.iUri, "iUri");
        jceDisplayer.display(this.sProtoName, "sProtoName");
        jceDisplayer.display(this.lSid, "lSid");
        jceDisplayer.display((Collection) this.vStreamName, "vStreamName");
        jceDisplayer.display((JceStruct) this.tId, "tId");
    }
}
