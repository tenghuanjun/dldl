package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WSPushMessage extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static byte[] cache_sMsg;
    public int ePushType = 0;
    public long iUri = 0;
    public byte[] sMsg = null;
    public int iProtocolType = 0;
    public String sGroupId = "";
    public long lMsgId = 0;
    public int iMsgTag = 0;

    public String className() {
        return "HUYA.WSPushMessage";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSPushMessage";
    }

    public int getEPushType() {
        return this.ePushType;
    }

    public void setEPushType(int i) {
        this.ePushType = i;
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

    public int getIProtocolType() {
        return this.iProtocolType;
    }

    public void setIProtocolType(int i) {
        this.iProtocolType = i;
    }

    public String getSGroupId() {
        return this.sGroupId;
    }

    public void setSGroupId(String str) {
        this.sGroupId = str;
    }

    public long getLMsgId() {
        return this.lMsgId;
    }

    public void setLMsgId(long j) {
        this.lMsgId = j;
    }

    public int getIMsgTag() {
        return this.iMsgTag;
    }

    public void setIMsgTag(int i) {
        this.iMsgTag = i;
    }

    public WSPushMessage() {
        setEPushType(0);
        setIUri(this.iUri);
        setSMsg(this.sMsg);
        setIProtocolType(this.iProtocolType);
        setSGroupId(this.sGroupId);
        setLMsgId(this.lMsgId);
        setIMsgTag(this.iMsgTag);
    }

    public WSPushMessage(int i, long j, byte[] bArr, int i2, String str, long j2, int i3) {
        setEPushType(i);
        setIUri(j);
        setSMsg(bArr);
        setIProtocolType(i2);
        setSGroupId(str);
        setLMsgId(j2);
        setIMsgTag(i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSPushMessage wSPushMessage = (WSPushMessage) obj;
        return JceUtil.equals(this.ePushType, wSPushMessage.ePushType) && JceUtil.equals(this.iUri, wSPushMessage.iUri) && JceUtil.equals(this.sMsg, wSPushMessage.sMsg) && JceUtil.equals(this.iProtocolType, wSPushMessage.iProtocolType) && JceUtil.equals(this.sGroupId, wSPushMessage.sGroupId) && JceUtil.equals(this.lMsgId, wSPushMessage.lMsgId) && JceUtil.equals(this.iMsgTag, wSPushMessage.iMsgTag);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.ePushType), JceUtil.hashCode(this.iUri), JceUtil.hashCode(this.sMsg), JceUtil.hashCode(this.iProtocolType), JceUtil.hashCode(this.sGroupId), JceUtil.hashCode(this.lMsgId), JceUtil.hashCode(this.iMsgTag)});
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
        jceOutputStream.write(this.ePushType, 0);
        jceOutputStream.write(this.iUri, 1);
        byte[] bArr = this.sMsg;
        if (bArr != null) {
            jceOutputStream.write(bArr, 2);
        }
        jceOutputStream.write(this.iProtocolType, 3);
        String str = this.sGroupId;
        if (str != null) {
            jceOutputStream.write(str, 4);
        }
        jceOutputStream.write(this.lMsgId, 5);
        jceOutputStream.write(this.iMsgTag, 6);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setEPushType(jceInputStream.read(this.ePushType, 0, false));
        setIUri(jceInputStream.read(this.iUri, 1, false));
        if (cache_sMsg == null) {
            cache_sMsg = new byte[]{0};
        }
        setSMsg(jceInputStream.read(cache_sMsg, 2, false));
        setIProtocolType(jceInputStream.read(this.iProtocolType, 3, false));
        setSGroupId(jceInputStream.readString(4, false));
        setLMsgId(jceInputStream.read(this.lMsgId, 5, false));
        setIMsgTag(jceInputStream.read(this.iMsgTag, 6, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.ePushType, "ePushType");
        jceDisplayer.display(this.iUri, "iUri");
        jceDisplayer.display(this.sMsg, "sMsg");
        jceDisplayer.display(this.iProtocolType, "iProtocolType");
        jceDisplayer.display(this.sGroupId, "sGroupId");
        jceDisplayer.display(this.lMsgId, "lMsgId");
        jceDisplayer.display(this.iMsgTag, "iMsgTag");
    }
}
