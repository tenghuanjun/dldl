package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GetUserTypeRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static IsMutedRsp cache_tIsMutedRsp;
    public long lUid = 0;
    public long lPresenterUid = 0;
    public int iType = 0;
    public IsMutedRsp tIsMutedRsp = null;
    public int iGHManagerType = 0;
    public String sManagerDecorationUrl = "";

    public String className() {
        return "HUYA.GetUserTypeRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetUserTypeRsp";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public long getLPresenterUid() {
        return this.lPresenterUid;
    }

    public void setLPresenterUid(long j) {
        this.lPresenterUid = j;
    }

    public int getIType() {
        return this.iType;
    }

    public void setIType(int i) {
        this.iType = i;
    }

    public IsMutedRsp getTIsMutedRsp() {
        return this.tIsMutedRsp;
    }

    public void setTIsMutedRsp(IsMutedRsp isMutedRsp) {
        this.tIsMutedRsp = isMutedRsp;
    }

    public int getIGHManagerType() {
        return this.iGHManagerType;
    }

    public void setIGHManagerType(int i) {
        this.iGHManagerType = i;
    }

    public String getSManagerDecorationUrl() {
        return this.sManagerDecorationUrl;
    }

    public void setSManagerDecorationUrl(String str) {
        this.sManagerDecorationUrl = str;
    }

    public GetUserTypeRsp() {
        setLUid(0L);
        setLPresenterUid(this.lPresenterUid);
        setIType(this.iType);
        setTIsMutedRsp(this.tIsMutedRsp);
        setIGHManagerType(this.iGHManagerType);
        setSManagerDecorationUrl(this.sManagerDecorationUrl);
    }

    public GetUserTypeRsp(long j, long j2, int i, IsMutedRsp isMutedRsp, int i2, String str) {
        setLUid(j);
        setLPresenterUid(j2);
        setIType(i);
        setTIsMutedRsp(isMutedRsp);
        setIGHManagerType(i2);
        setSManagerDecorationUrl(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetUserTypeRsp getUserTypeRsp = (GetUserTypeRsp) obj;
        return JceUtil.equals(this.lUid, getUserTypeRsp.lUid) && JceUtil.equals(this.lPresenterUid, getUserTypeRsp.lPresenterUid) && JceUtil.equals(this.iType, getUserTypeRsp.iType) && JceUtil.equals(this.tIsMutedRsp, getUserTypeRsp.tIsMutedRsp) && JceUtil.equals(this.iGHManagerType, getUserTypeRsp.iGHManagerType) && JceUtil.equals(this.sManagerDecorationUrl, getUserTypeRsp.sManagerDecorationUrl);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lUid), JceUtil.hashCode(this.lPresenterUid), JceUtil.hashCode(this.iType), JceUtil.hashCode(this.tIsMutedRsp), JceUtil.hashCode(this.iGHManagerType), JceUtil.hashCode(this.sManagerDecorationUrl)});
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
        jceOutputStream.write(this.lPresenterUid, 1);
        jceOutputStream.write(this.iType, 3);
        IsMutedRsp isMutedRsp = this.tIsMutedRsp;
        if (isMutedRsp != null) {
            jceOutputStream.write((JceStruct) isMutedRsp, 4);
        }
        jceOutputStream.write(this.iGHManagerType, 5);
        String str = this.sManagerDecorationUrl;
        if (str != null) {
            jceOutputStream.write(str, 6);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setLPresenterUid(jceInputStream.read(this.lPresenterUid, 1, false));
        setIType(jceInputStream.read(this.iType, 3, false));
        if (cache_tIsMutedRsp == null) {
            cache_tIsMutedRsp = new IsMutedRsp();
        }
        setTIsMutedRsp((IsMutedRsp) jceInputStream.read((JceStruct) cache_tIsMutedRsp, 4, false));
        setIGHManagerType(jceInputStream.read(this.iGHManagerType, 5, false));
        setSManagerDecorationUrl(jceInputStream.readString(6, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.lPresenterUid, "lPresenterUid");
        jceDisplayer.display(this.iType, "iType");
        jceDisplayer.display((JceStruct) this.tIsMutedRsp, "tIsMutedRsp");
        jceDisplayer.display(this.iGHManagerType, "iGHManagerType");
        jceDisplayer.display(this.sManagerDecorationUrl, "sManagerDecorationUrl");
    }
}
