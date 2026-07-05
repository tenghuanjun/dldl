package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FansInfoDetail extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static FansInfo cache_tInfo;
    public FansInfo tInfo = null;
    public String sBadgeName = "";

    public String className() {
        return "HUYA.FansInfoDetail";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.FansInfoDetail";
    }

    public FansInfo getTInfo() {
        return this.tInfo;
    }

    public void setTInfo(FansInfo fansInfo) {
        this.tInfo = fansInfo;
    }

    public String getSBadgeName() {
        return this.sBadgeName;
    }

    public void setSBadgeName(String str) {
        this.sBadgeName = str;
    }

    public FansInfoDetail() {
        setTInfo(null);
        setSBadgeName(this.sBadgeName);
    }

    public FansInfoDetail(FansInfo fansInfo, String str) {
        setTInfo(fansInfo);
        setSBadgeName(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FansInfoDetail fansInfoDetail = (FansInfoDetail) obj;
        return JceUtil.equals(this.tInfo, fansInfoDetail.tInfo) && JceUtil.equals(this.sBadgeName, fansInfoDetail.sBadgeName);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tInfo), JceUtil.hashCode(this.sBadgeName)});
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
        FansInfo fansInfo = this.tInfo;
        if (fansInfo != null) {
            jceOutputStream.write((JceStruct) fansInfo, 0);
        }
        String str = this.sBadgeName;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tInfo == null) {
            cache_tInfo = new FansInfo();
        }
        setTInfo((FansInfo) jceInputStream.read((JceStruct) cache_tInfo, 0, false));
        setSBadgeName(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tInfo, "tInfo");
        jceDisplayer.display(this.sBadgeName, "sBadgeName");
    }
}
