package com.huya.component.user.api.data;

import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.huya.mtp.utils.Base64;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PresenterChannelInfo extends JceStruct {
    private int iAuth;
    private long lSid;
    private long lTid;

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
    }

    public PresenterChannelInfo(int i, long j, long j2) {
        this.iAuth = 0;
        this.lTid = 0L;
        this.lSid = 0L;
        this.iAuth = i;
        this.lTid = j;
        this.lSid = j2;
    }

    public int getiAuth() {
        return this.iAuth;
    }

    public void setiAuth(int i) {
        this.iAuth = i;
    }

    public long getlTid() {
        return this.lTid;
    }

    public void setlTid(long j) {
        this.lTid = j;
    }

    public long getlSid() {
        return this.lSid;
    }

    public void setlSid(long j) {
        this.lSid = j;
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void writeTo(JceOutputStream jceOutputStream) {
        jceOutputStream.write(this.iAuth, 0);
        jceOutputStream.write(this.lTid, 1);
        jceOutputStream.write(this.lSid, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        this.iAuth = jceInputStream.read(this.iAuth, 0, false);
        this.lTid = jceInputStream.read(this.lTid, 1, false);
        this.lSid = jceInputStream.read(this.lSid, 2, false);
    }

    public String toBase64String() {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        return Base64.encodeToString(jceOutputStream.toByteArray());
    }

    public void fromBase64String(String str) {
        readFrom(new JceInputStream(Base64.decode(str.getBytes())));
    }
}
