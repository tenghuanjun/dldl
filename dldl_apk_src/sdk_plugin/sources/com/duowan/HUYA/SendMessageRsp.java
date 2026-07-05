package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SendMessageRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static MessageNotice cache_tNotice;
    public int iStatus = 0;
    public MessageNotice tNotice = null;

    public String className() {
        return "HUYA.SendMessageRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SendMessageRsp";
    }

    public int getIStatus() {
        return this.iStatus;
    }

    public void setIStatus(int i) {
        this.iStatus = i;
    }

    public MessageNotice getTNotice() {
        return this.tNotice;
    }

    public void setTNotice(MessageNotice messageNotice) {
        this.tNotice = messageNotice;
    }

    public SendMessageRsp() {
        setIStatus(0);
        setTNotice(this.tNotice);
    }

    public SendMessageRsp(int i, MessageNotice messageNotice) {
        setIStatus(i);
        setTNotice(messageNotice);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SendMessageRsp sendMessageRsp = (SendMessageRsp) obj;
        return JceUtil.equals(this.iStatus, sendMessageRsp.iStatus) && JceUtil.equals(this.tNotice, sendMessageRsp.tNotice);
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
        jceOutputStream.write(this.iStatus, 0);
        MessageNotice messageNotice = this.tNotice;
        if (messageNotice != null) {
            jceOutputStream.write((JceStruct) messageNotice, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIStatus(jceInputStream.read(this.iStatus, 0, false));
        if (cache_tNotice == null) {
            cache_tNotice = new MessageNotice();
        }
        setTNotice((MessageNotice) jceInputStream.read((JceStruct) cache_tNotice, 1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iStatus, "iStatus");
        jceDisplayer.display((JceStruct) this.tNotice, "tNotice");
    }
}
