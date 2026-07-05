package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WSMsgItem extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static byte[] cache_sMsg;
    public long iUri = 0;
    public byte[] sMsg = null;
    public long lMsgId = 0;

    public String className() {
        return "HUYA.WSMsgItem";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSMsgItem";
    }

    public long getIUri() {
        return this.iUri;
    }

    public void setIUri(long j) {
        this.iUri = j;
    }

    public byte[] getSMsg() {
        return this.sMsg;
    }

    public void setSMsg(byte[] bArr) {
        this.sMsg = bArr;
    }

    public long getLMsgId() {
        return this.lMsgId;
    }

    public void setLMsgId(long j) {
        this.lMsgId = j;
    }

    public WSMsgItem() {
        setIUri(0L);
        setSMsg(this.sMsg);
        setLMsgId(this.lMsgId);
    }

    public WSMsgItem(long j, byte[] bArr, long j2) {
        setIUri(j);
        setSMsg(bArr);
        setLMsgId(j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSMsgItem wSMsgItem = (WSMsgItem) obj;
        return JceUtil.equals(this.iUri, wSMsgItem.iUri) && JceUtil.equals(this.sMsg, wSMsgItem.sMsg) && JceUtil.equals(this.lMsgId, wSMsgItem.lMsgId);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iUri), JceUtil.hashCode(this.sMsg), JceUtil.hashCode(this.lMsgId)});
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
        jceOutputStream.write(this.iUri, 0);
        byte[] bArr = this.sMsg;
        if (bArr != null) {
            jceOutputStream.write(bArr, 1);
        }
        jceOutputStream.write(this.lMsgId, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIUri(jceInputStream.read(this.iUri, 0, false));
        if (cache_sMsg == null) {
            cache_sMsg = new byte[]{0};
        }
        setSMsg(jceInputStream.read(cache_sMsg, 1, false));
        setLMsgId(jceInputStream.read(this.lMsgId, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iUri, "iUri");
        jceDisplayer.display(this.sMsg, "sMsg");
        jceDisplayer.display(this.lMsgId, "lMsgId");
    }
}
