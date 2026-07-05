package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetMobilePresenterChannelRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ChannelPair cache_tChannelPair;
    public ChannelPair tChannelPair = null;

    public String className() {
        return "HUYA.GetMobilePresenterChannelRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetMobilePresenterChannelRsp";
    }

    public ChannelPair getTChannelPair() {
        return this.tChannelPair;
    }

    public void setTChannelPair(ChannelPair channelPair) {
        this.tChannelPair = channelPair;
    }

    public GetMobilePresenterChannelRsp() {
        setTChannelPair(null);
    }

    public GetMobilePresenterChannelRsp(ChannelPair channelPair) {
        setTChannelPair(channelPair);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.tChannelPair, ((GetMobilePresenterChannelRsp) obj).tChannelPair);
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
        ChannelPair channelPair = this.tChannelPair;
        if (channelPair != null) {
            jceOutputStream.write((JceStruct) channelPair, 0);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tChannelPair == null) {
            cache_tChannelPair = new ChannelPair();
        }
        setTChannelPair((ChannelPair) jceInputStream.read((JceStruct) cache_tChannelPair, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display((JceStruct) this.tChannelPair, "tChannelPair");
    }
}
