package com.huya.hysignal.jce;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class WSLaunchReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static WSDeviceInfo cache_tDeviceInfo;
    public long lUid = 0;
    public String sGuid = "";
    public String sUA = "";
    public String sAppSrc = "";
    public WSDeviceInfo tDeviceInfo = null;

    public String className() {
        return "HUYA.WSLaunchReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.WSLaunchReq";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public String getSGuid() {
        return this.sGuid;
    }

    public void setSGuid(String str) {
        this.sGuid = str;
    }

    public String getSUA() {
        return this.sUA;
    }

    public void setSUA(String str) {
        this.sUA = str;
    }

    public String getSAppSrc() {
        return this.sAppSrc;
    }

    public void setSAppSrc(String str) {
        this.sAppSrc = str;
    }

    public WSDeviceInfo getTDeviceInfo() {
        return this.tDeviceInfo;
    }

    public void setTDeviceInfo(WSDeviceInfo wSDeviceInfo) {
        this.tDeviceInfo = wSDeviceInfo;
    }

    public WSLaunchReq() {
        setLUid(0L);
        setSGuid(this.sGuid);
        setSUA(this.sUA);
        setSAppSrc(this.sAppSrc);
        setTDeviceInfo(this.tDeviceInfo);
    }

    public WSLaunchReq(long j, String str, String str2, String str3, WSDeviceInfo wSDeviceInfo) {
        setLUid(j);
        setSGuid(str);
        setSUA(str2);
        setSAppSrc(str3);
        setTDeviceInfo(wSDeviceInfo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WSLaunchReq wSLaunchReq = (WSLaunchReq) obj;
        return JceUtil.equals(this.lUid, wSLaunchReq.lUid) && JceUtil.equals(this.sGuid, wSLaunchReq.sGuid) && JceUtil.equals(this.sUA, wSLaunchReq.sUA) && JceUtil.equals(this.sAppSrc, wSLaunchReq.sAppSrc) && JceUtil.equals(this.tDeviceInfo, wSLaunchReq.tDeviceInfo);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lUid), JceUtil.hashCode(this.sGuid), JceUtil.hashCode(this.sUA), JceUtil.hashCode(this.sAppSrc), JceUtil.hashCode(this.tDeviceInfo)});
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
        String str = this.sGuid;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sUA;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        String str3 = this.sAppSrc;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
        WSDeviceInfo wSDeviceInfo = this.tDeviceInfo;
        if (wSDeviceInfo != null) {
            jceOutputStream.write((JceStruct) wSDeviceInfo, 4);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setSGuid(jceInputStream.readString(1, false));
        setSUA(jceInputStream.readString(2, false));
        setSAppSrc(jceInputStream.readString(3, false));
        if (cache_tDeviceInfo == null) {
            cache_tDeviceInfo = new WSDeviceInfo();
        }
        setTDeviceInfo((WSDeviceInfo) jceInputStream.read((JceStruct) cache_tDeviceInfo, 4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.sGuid, "sGuid");
        jceDisplayer.display(this.sUA, "sUA");
        jceDisplayer.display(this.sAppSrc, "sAppSrc");
        jceDisplayer.display((JceStruct) this.tDeviceInfo, "tDeviceInfo");
    }
}
