package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetInfoFromVGRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static byte[] cache_sMsg;
    public long lUid = 0;
    public long ServerId = 0;
    public byte[] sMsg = null;
    public long iUri = 0;
    public String sProtoName = "";

    public String className() {
        return "HUYA.GetInfoFromVGRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetInfoFromVGRsp";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public long getServerId() {
        return this.ServerId;
    }

    public void setServerId(long j) {
        this.ServerId = j;
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

    public GetInfoFromVGRsp() {
        setLUid(0L);
        setServerId(this.ServerId);
        setSMsg(this.sMsg);
        setIUri(this.iUri);
        setSProtoName(this.sProtoName);
    }

    public GetInfoFromVGRsp(long j, long j2, byte[] bArr, long j3, String str) {
        setLUid(j);
        setServerId(j2);
        setSMsg(bArr);
        setIUri(j3);
        setSProtoName(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetInfoFromVGRsp getInfoFromVGRsp = (GetInfoFromVGRsp) obj;
        return JceUtil.equals(this.lUid, getInfoFromVGRsp.lUid) && JceUtil.equals(this.ServerId, getInfoFromVGRsp.ServerId) && JceUtil.equals(this.sMsg, getInfoFromVGRsp.sMsg) && JceUtil.equals(this.iUri, getInfoFromVGRsp.iUri) && JceUtil.equals(this.sProtoName, getInfoFromVGRsp.sProtoName);
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
        jceOutputStream.write(this.ServerId, 1);
        byte[] bArr = this.sMsg;
        if (bArr != null) {
            jceOutputStream.write(bArr, 2);
        }
        jceOutputStream.write(this.iUri, 3);
        String str = this.sProtoName;
        if (str != null) {
            jceOutputStream.write(str, 4);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setServerId(jceInputStream.read(this.ServerId, 1, false));
        if (cache_sMsg == null) {
            cache_sMsg = new byte[]{0};
        }
        setSMsg(jceInputStream.read(cache_sMsg, 2, false));
        setIUri(jceInputStream.read(this.iUri, 3, false));
        setSProtoName(jceInputStream.readString(4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.ServerId, "ServerId");
        jceDisplayer.display(this.sMsg, "sMsg");
        jceDisplayer.display(this.iUri, "iUri");
        jceDisplayer.display(this.sProtoName, "sProtoName");
    }
}
