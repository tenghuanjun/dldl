package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class AuthChannelInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<AuthSubchannelInfo> cache_vAuthSubchannelInfo;
    public long lChannelId = 0;
    public long lShortChannelId = 0;
    public ArrayList<AuthSubchannelInfo> vAuthSubchannelInfo = null;

    public String className() {
        return "HUYA.AuthChannelInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.AuthChannelInfo";
    }

    public long getLChannelId() {
        return this.lChannelId;
    }

    public void setLChannelId(long j) {
        this.lChannelId = j;
    }

    public long getLShortChannelId() {
        return this.lShortChannelId;
    }

    public void setLShortChannelId(long j) {
        this.lShortChannelId = j;
    }

    public ArrayList<AuthSubchannelInfo> getVAuthSubchannelInfo() {
        return this.vAuthSubchannelInfo;
    }

    public void setVAuthSubchannelInfo(ArrayList<AuthSubchannelInfo> arrayList) {
        this.vAuthSubchannelInfo = arrayList;
    }

    public AuthChannelInfo() {
        setLChannelId(0L);
        setLShortChannelId(this.lShortChannelId);
        setVAuthSubchannelInfo(this.vAuthSubchannelInfo);
    }

    public AuthChannelInfo(long j, long j2, ArrayList<AuthSubchannelInfo> arrayList) {
        setLChannelId(j);
        setLShortChannelId(j2);
        setVAuthSubchannelInfo(arrayList);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        AuthChannelInfo authChannelInfo = (AuthChannelInfo) obj;
        return JceUtil.equals(this.lChannelId, authChannelInfo.lChannelId) && JceUtil.equals(this.lShortChannelId, authChannelInfo.lShortChannelId) && JceUtil.equals(this.vAuthSubchannelInfo, authChannelInfo.vAuthSubchannelInfo);
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
        jceOutputStream.write(this.lChannelId, 0);
        jceOutputStream.write(this.lShortChannelId, 1);
        ArrayList<AuthSubchannelInfo> arrayList = this.vAuthSubchannelInfo;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 2);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLChannelId(jceInputStream.read(this.lChannelId, 0, false));
        setLShortChannelId(jceInputStream.read(this.lShortChannelId, 1, false));
        if (cache_vAuthSubchannelInfo == null) {
            cache_vAuthSubchannelInfo = new ArrayList<>();
            cache_vAuthSubchannelInfo.add(new AuthSubchannelInfo());
        }
        setVAuthSubchannelInfo((ArrayList) jceInputStream.read(cache_vAuthSubchannelInfo, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lChannelId, "lChannelId");
        jceDisplayer.display(this.lShortChannelId, "lShortChannelId");
        jceDisplayer.display((Collection) this.vAuthSubchannelInfo, "vAuthSubchannelInfo");
    }
}
