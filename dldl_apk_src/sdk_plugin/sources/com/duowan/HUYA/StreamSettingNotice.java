package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class StreamSettingNotice extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lPresenterUid = 0;
    public int iBitRate = 0;
    public int iResolution = 0;
    public int iFrameRate = 0;
    public long lLiveId = 0;
    public String sDisplayName = "";

    public String className() {
        return "HUYA.StreamSettingNotice";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.StreamSettingNotice";
    }

    @Override // com.duowan.taf.jce.JceStruct
    public String toString() {
        return "StreamSettingNotice:{PresenterUid:" + this.lPresenterUid + " BitRate:" + this.iBitRate + " Resolution:" + this.iResolution + " FrameRate:" + this.iFrameRate + " LiveId:" + this.lLiveId + " DisplayName:" + this.sDisplayName + "}\r\n";
    }

    public long getLPresenterUid() {
        return this.lPresenterUid;
    }

    public void setLPresenterUid(long j) {
        this.lPresenterUid = j;
    }

    public int getIBitRate() {
        return this.iBitRate;
    }

    public void setIBitRate(int i) {
        this.iBitRate = i;
    }

    public int getIResolution() {
        return this.iResolution;
    }

    public void setIResolution(int i) {
        this.iResolution = i;
    }

    public int getIFrameRate() {
        return this.iFrameRate;
    }

    public void setIFrameRate(int i) {
        this.iFrameRate = i;
    }

    public long getLLiveId() {
        return this.lLiveId;
    }

    public void setLLiveId(long j) {
        this.lLiveId = j;
    }

    public String getSDisplayName() {
        return this.sDisplayName;
    }

    public void setSDisplayName(String str) {
        this.sDisplayName = str;
    }

    public StreamSettingNotice() {
        setLPresenterUid(0L);
        setIBitRate(this.iBitRate);
        setIResolution(this.iResolution);
        setIFrameRate(this.iFrameRate);
        setLLiveId(this.lLiveId);
        setSDisplayName(this.sDisplayName);
    }

    public StreamSettingNotice(long j, int i, int i2, int i3, long j2, String str) {
        setLPresenterUid(j);
        setIBitRate(i);
        setIResolution(i2);
        setIFrameRate(i3);
        setLLiveId(j2);
        setSDisplayName(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StreamSettingNotice streamSettingNotice = (StreamSettingNotice) obj;
        return JceUtil.equals(this.lPresenterUid, streamSettingNotice.lPresenterUid) && JceUtil.equals(this.iBitRate, streamSettingNotice.iBitRate) && JceUtil.equals(this.iResolution, streamSettingNotice.iResolution) && JceUtil.equals(this.iFrameRate, streamSettingNotice.iFrameRate) && JceUtil.equals(this.lLiveId, streamSettingNotice.lLiveId) && JceUtil.equals(this.sDisplayName, streamSettingNotice.sDisplayName);
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
        jceOutputStream.write(this.lPresenterUid, 0);
        jceOutputStream.write(this.iBitRate, 1);
        jceOutputStream.write(this.iResolution, 2);
        jceOutputStream.write(this.iFrameRate, 3);
        jceOutputStream.write(this.lLiveId, 4);
        String str = this.sDisplayName;
        if (str != null) {
            jceOutputStream.write(str, 5);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLPresenterUid(jceInputStream.read(this.lPresenterUid, 0, false));
        setIBitRate(jceInputStream.read(this.iBitRate, 1, false));
        setIResolution(jceInputStream.read(this.iResolution, 2, false));
        setIFrameRate(jceInputStream.read(this.iFrameRate, 3, false));
        setLLiveId(jceInputStream.read(this.lLiveId, 4, false));
        setSDisplayName(jceInputStream.readString(5, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lPresenterUid, "lPresenterUid");
        jceDisplayer.display(this.iBitRate, "iBitRate");
        jceDisplayer.display(this.iResolution, "iResolution");
        jceDisplayer.display(this.iFrameRate, "iFrameRate");
        jceDisplayer.display(this.lLiveId, "lLiveId");
        jceDisplayer.display(this.sDisplayName, "sDisplayName");
    }
}
