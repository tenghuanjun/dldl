package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ZhuShouGameNameInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sTitle = "";
    public String sDesc = "";
    public int iActionType = 0;
    public String sActionUrl = "";
    public String sCallType = "";
    public int iLoginStatus = 0;
    public int iTips = 0;
    public int iType = 0;
    public int iGameId = 0;
    public String sChineseName = "";
    public int iGameType = 0;
    public int iScreenType = 0;
    public int iCodecType = 0;

    public String className() {
        return "HUYA.ZhuShouGameNameInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.ZhuShouGameNameInfo";
    }

    public String getSTitle() {
        return this.sTitle;
    }

    public void setSTitle(String str) {
        this.sTitle = str;
    }

    public String getSDesc() {
        return this.sDesc;
    }

    public void setSDesc(String str) {
        this.sDesc = str;
    }

    public int getIActionType() {
        return this.iActionType;
    }

    public void setIActionType(int i) {
        this.iActionType = i;
    }

    public String getSActionUrl() {
        return this.sActionUrl;
    }

    public void setSActionUrl(String str) {
        this.sActionUrl = str;
    }

    public String getSCallType() {
        return this.sCallType;
    }

    public void setSCallType(String str) {
        this.sCallType = str;
    }

    public int getILoginStatus() {
        return this.iLoginStatus;
    }

    public void setILoginStatus(int i) {
        this.iLoginStatus = i;
    }

    public int getITips() {
        return this.iTips;
    }

    public void setITips(int i) {
        this.iTips = i;
    }

    public int getIType() {
        return this.iType;
    }

    public void setIType(int i) {
        this.iType = i;
    }

    public int getIGameId() {
        return this.iGameId;
    }

    public void setIGameId(int i) {
        this.iGameId = i;
    }

    public String getSChineseName() {
        return this.sChineseName;
    }

    public void setSChineseName(String str) {
        this.sChineseName = str;
    }

    public int getIGameType() {
        return this.iGameType;
    }

    public void setIGameType(int i) {
        this.iGameType = i;
    }

    public int getIScreenType() {
        return this.iScreenType;
    }

    public void setIScreenType(int i) {
        this.iScreenType = i;
    }

    public int getICodecType() {
        return this.iCodecType;
    }

    public void setICodecType(int i) {
        this.iCodecType = i;
    }

    public ZhuShouGameNameInfo() {
        setSTitle("");
        setSDesc(this.sDesc);
        setIActionType(this.iActionType);
        setSActionUrl(this.sActionUrl);
        setSCallType(this.sCallType);
        setILoginStatus(this.iLoginStatus);
        setITips(this.iTips);
        setIType(this.iType);
        setIGameId(this.iGameId);
        setSChineseName(this.sChineseName);
        setIGameType(this.iGameType);
        setIScreenType(this.iScreenType);
        setICodecType(this.iCodecType);
    }

    public ZhuShouGameNameInfo(String str, String str2, int i, String str3, String str4, int i2, int i3, int i4, int i5, String str5, int i6, int i7, int i8) {
        setSTitle(str);
        setSDesc(str2);
        setIActionType(i);
        setSActionUrl(str3);
        setSCallType(str4);
        setILoginStatus(i2);
        setITips(i3);
        setIType(i4);
        setIGameId(i5);
        setSChineseName(str5);
        setIGameType(i6);
        setIScreenType(i7);
        setICodecType(i8);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ZhuShouGameNameInfo zhuShouGameNameInfo = (ZhuShouGameNameInfo) obj;
        return JceUtil.equals(this.sTitle, zhuShouGameNameInfo.sTitle) && JceUtil.equals(this.sDesc, zhuShouGameNameInfo.sDesc) && JceUtil.equals(this.iActionType, zhuShouGameNameInfo.iActionType) && JceUtil.equals(this.sActionUrl, zhuShouGameNameInfo.sActionUrl) && JceUtil.equals(this.sCallType, zhuShouGameNameInfo.sCallType) && JceUtil.equals(this.iLoginStatus, zhuShouGameNameInfo.iLoginStatus) && JceUtil.equals(this.iTips, zhuShouGameNameInfo.iTips) && JceUtil.equals(this.iType, zhuShouGameNameInfo.iType) && JceUtil.equals(this.iGameId, zhuShouGameNameInfo.iGameId) && JceUtil.equals(this.sChineseName, zhuShouGameNameInfo.sChineseName) && JceUtil.equals(this.iGameType, zhuShouGameNameInfo.iGameType) && JceUtil.equals(this.iScreenType, zhuShouGameNameInfo.iScreenType) && JceUtil.equals(this.iCodecType, zhuShouGameNameInfo.iCodecType);
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
        String str = this.sTitle;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        String str2 = this.sDesc;
        if (str2 != null) {
            jceOutputStream.write(str2, 1);
        }
        jceOutputStream.write(this.iActionType, 2);
        String str3 = this.sActionUrl;
        if (str3 != null) {
            jceOutputStream.write(str3, 3);
        }
        String str4 = this.sCallType;
        if (str4 != null) {
            jceOutputStream.write(str4, 4);
        }
        jceOutputStream.write(this.iLoginStatus, 5);
        jceOutputStream.write(this.iTips, 6);
        jceOutputStream.write(this.iType, 7);
        jceOutputStream.write(this.iGameId, 8);
        String str5 = this.sChineseName;
        if (str5 != null) {
            jceOutputStream.write(str5, 9);
        }
        jceOutputStream.write(this.iGameType, 10);
        jceOutputStream.write(this.iScreenType, 11);
        jceOutputStream.write(this.iCodecType, 12);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSTitle(jceInputStream.readString(0, false));
        setSDesc(jceInputStream.readString(1, false));
        setIActionType(jceInputStream.read(this.iActionType, 2, false));
        setSActionUrl(jceInputStream.readString(3, false));
        setSCallType(jceInputStream.readString(4, false));
        setILoginStatus(jceInputStream.read(this.iLoginStatus, 5, false));
        setITips(jceInputStream.read(this.iTips, 6, false));
        setIType(jceInputStream.read(this.iType, 7, false));
        setIGameId(jceInputStream.read(this.iGameId, 8, false));
        setSChineseName(jceInputStream.readString(9, false));
        setIGameType(jceInputStream.read(this.iGameType, 10, false));
        setIScreenType(jceInputStream.read(this.iScreenType, 11, false));
        setICodecType(jceInputStream.read(this.iCodecType, 12, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sTitle, "sTitle");
        jceDisplayer.display(this.sDesc, "sDesc");
        jceDisplayer.display(this.iActionType, "iActionType");
        jceDisplayer.display(this.sActionUrl, "sActionUrl");
        jceDisplayer.display(this.sCallType, "sCallType");
        jceDisplayer.display(this.iLoginStatus, "iLoginStatus");
        jceDisplayer.display(this.iTips, "iTips");
        jceDisplayer.display(this.iType, "iType");
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display(this.sChineseName, "sChineseName");
        jceDisplayer.display(this.iGameType, "iGameType");
        jceDisplayer.display(this.iScreenType, "iScreenType");
        jceDisplayer.display(this.iCodecType, "iCodecType");
    }
}
