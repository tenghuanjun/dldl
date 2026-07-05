package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetLivingInfoRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static BeginLiveNotice cache_tNotice;
    static StreamSettingNotice cache_tStreamSettingNotice;
    public int bIsLiving = 0;
    public BeginLiveNotice tNotice = null;
    public StreamSettingNotice tStreamSettingNotice = null;
    public int bIsSelfLiving = 0;

    public String className() {
        return "HUYA.GetLivingInfoRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetLivingInfoRsp";
    }

    @Override // com.duowan.taf.jce.JceStruct
    public String toString() {
        return "GetLivingInfoRsp:{ Notice:\r\n" + this.tNotice.toString() + "\r\nStreamSettingNotice:" + this.tStreamSettingNotice.toString() + " IsLiving:" + this.bIsLiving + " IsSelfLiving:" + this.bIsSelfLiving + " }\r\n";
    }

    public int getBIsLiving() {
        return this.bIsLiving;
    }

    public void setBIsLiving(int i) {
        this.bIsLiving = i;
    }

    public BeginLiveNotice getTNotice() {
        return this.tNotice;
    }

    public void setTNotice(BeginLiveNotice beginLiveNotice) {
        this.tNotice = beginLiveNotice;
    }

    public StreamSettingNotice getTStreamSettingNotice() {
        return this.tStreamSettingNotice;
    }

    public void setTStreamSettingNotice(StreamSettingNotice streamSettingNotice) {
        this.tStreamSettingNotice = streamSettingNotice;
    }

    public int getBIsSelfLiving() {
        return this.bIsSelfLiving;
    }

    public void setBIsSelfLiving(int i) {
        this.bIsSelfLiving = i;
    }

    public GetLivingInfoRsp() {
        setBIsLiving(0);
        setTNotice(this.tNotice);
        setTStreamSettingNotice(this.tStreamSettingNotice);
        setBIsSelfLiving(this.bIsSelfLiving);
    }

    public GetLivingInfoRsp(int i, BeginLiveNotice beginLiveNotice, StreamSettingNotice streamSettingNotice, int i2) {
        setBIsLiving(i);
        setTNotice(beginLiveNotice);
        setTStreamSettingNotice(streamSettingNotice);
        setBIsSelfLiving(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetLivingInfoRsp getLivingInfoRsp = (GetLivingInfoRsp) obj;
        return JceUtil.equals(this.bIsLiving, getLivingInfoRsp.bIsLiving) && JceUtil.equals(this.tNotice, getLivingInfoRsp.tNotice) && JceUtil.equals(this.tStreamSettingNotice, getLivingInfoRsp.tStreamSettingNotice) && JceUtil.equals(this.bIsSelfLiving, getLivingInfoRsp.bIsSelfLiving);
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
        jceOutputStream.write(this.bIsLiving, 0);
        BeginLiveNotice beginLiveNotice = this.tNotice;
        if (beginLiveNotice != null) {
            jceOutputStream.write((JceStruct) beginLiveNotice, 1);
        }
        StreamSettingNotice streamSettingNotice = this.tStreamSettingNotice;
        if (streamSettingNotice != null) {
            jceOutputStream.write((JceStruct) streamSettingNotice, 2);
        }
        jceOutputStream.write(this.bIsSelfLiving, 3);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setBIsLiving(jceInputStream.read(this.bIsLiving, 0, false));
        if (cache_tNotice == null) {
            cache_tNotice = new BeginLiveNotice();
        }
        setTNotice((BeginLiveNotice) jceInputStream.read((JceStruct) cache_tNotice, 1, false));
        if (cache_tStreamSettingNotice == null) {
            cache_tStreamSettingNotice = new StreamSettingNotice();
        }
        setTStreamSettingNotice((StreamSettingNotice) jceInputStream.read((JceStruct) cache_tStreamSettingNotice, 2, false));
        setBIsSelfLiving(jceInputStream.read(this.bIsSelfLiving, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.bIsLiving, "bIsLiving");
        jceDisplayer.display((JceStruct) this.tNotice, "tNotice");
        jceDisplayer.display((JceStruct) this.tStreamSettingNotice, "tStreamSettingNotice");
        jceDisplayer.display(this.bIsSelfLiving, "bIsSelfLiving");
    }
}
