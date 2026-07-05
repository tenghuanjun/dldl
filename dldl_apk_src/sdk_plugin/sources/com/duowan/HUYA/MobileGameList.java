package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class MobileGameList extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public String sGameName = "";
    public int iGameId = 0;
    public int iScreen = 0;
    public String sDesc = "";
    public String sPacketName = "";
    public String sIosUrl = "";
    public String sMultiPacketName = "";
    public String sShortName = "";
    public String sPicUrl = "";

    public String className() {
        return "HUYA.MobileGameList";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.MobileGameList";
    }

    public String getSGameName() {
        return this.sGameName;
    }

    public void setSGameName(String str) {
        this.sGameName = str;
    }

    public int getIGameId() {
        return this.iGameId;
    }

    public void setIGameId(int i) {
        this.iGameId = i;
    }

    public int getIScreen() {
        return this.iScreen;
    }

    public void setIScreen(int i) {
        this.iScreen = i;
    }

    public String getSDesc() {
        return this.sDesc;
    }

    public void setSDesc(String str) {
        this.sDesc = str;
    }

    public String getSPacketName() {
        return this.sPacketName;
    }

    public void setSPacketName(String str) {
        this.sPacketName = str;
    }

    public String getSIosUrl() {
        return this.sIosUrl;
    }

    public void setSIosUrl(String str) {
        this.sIosUrl = str;
    }

    public String getSMultiPacketName() {
        return this.sMultiPacketName;
    }

    public void setSMultiPacketName(String str) {
        this.sMultiPacketName = str;
    }

    public String getSShortName() {
        return this.sShortName;
    }

    public void setSShortName(String str) {
        this.sShortName = str;
    }

    public String getSPicUrl() {
        return this.sPicUrl;
    }

    public void setSPicUrl(String str) {
        this.sPicUrl = str;
    }

    public MobileGameList() {
        setSGameName("");
        setIGameId(this.iGameId);
        setIScreen(this.iScreen);
        setSDesc(this.sDesc);
        setSPacketName(this.sPacketName);
        setSIosUrl(this.sIosUrl);
        setSMultiPacketName(this.sMultiPacketName);
        setSShortName(this.sShortName);
        setSPicUrl(this.sPicUrl);
    }

    public MobileGameList(String str, int i, int i2, String str2, String str3, String str4, String str5, String str6, String str7) {
        setSGameName(str);
        setIGameId(i);
        setIScreen(i2);
        setSDesc(str2);
        setSPacketName(str3);
        setSIosUrl(str4);
        setSMultiPacketName(str5);
        setSShortName(str6);
        setSPicUrl(str7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MobileGameList mobileGameList = (MobileGameList) obj;
        return JceUtil.equals(this.sGameName, mobileGameList.sGameName) && JceUtil.equals(this.iGameId, mobileGameList.iGameId) && JceUtil.equals(this.iScreen, mobileGameList.iScreen) && JceUtil.equals(this.sDesc, mobileGameList.sDesc) && JceUtil.equals(this.sPacketName, mobileGameList.sPacketName) && JceUtil.equals(this.sIosUrl, mobileGameList.sIosUrl) && JceUtil.equals(this.sMultiPacketName, mobileGameList.sMultiPacketName) && JceUtil.equals(this.sShortName, mobileGameList.sShortName) && JceUtil.equals(this.sPicUrl, mobileGameList.sPicUrl);
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
        String str = this.sGameName;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        jceOutputStream.write(this.iGameId, 1);
        jceOutputStream.write(this.iScreen, 2);
        String str2 = this.sDesc;
        if (str2 != null) {
            jceOutputStream.write(str2, 3);
        }
        String str3 = this.sPacketName;
        if (str3 != null) {
            jceOutputStream.write(str3, 5);
        }
        String str4 = this.sIosUrl;
        if (str4 != null) {
            jceOutputStream.write(str4, 6);
        }
        String str5 = this.sMultiPacketName;
        if (str5 != null) {
            jceOutputStream.write(str5, 7);
        }
        String str6 = this.sShortName;
        if (str6 != null) {
            jceOutputStream.write(str6, 8);
        }
        String str7 = this.sPicUrl;
        if (str7 != null) {
            jceOutputStream.write(str7, 9);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSGameName(jceInputStream.readString(0, false));
        setIGameId(jceInputStream.read(this.iGameId, 1, false));
        setIScreen(jceInputStream.read(this.iScreen, 2, false));
        setSDesc(jceInputStream.readString(3, false));
        setSPacketName(jceInputStream.readString(5, false));
        setSIosUrl(jceInputStream.readString(6, false));
        setSMultiPacketName(jceInputStream.readString(7, false));
        setSShortName(jceInputStream.readString(8, false));
        setSPicUrl(jceInputStream.readString(9, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sGameName, "sGameName");
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display(this.iScreen, "iScreen");
        jceDisplayer.display(this.sDesc, "sDesc");
        jceDisplayer.display(this.sPacketName, "sPacketName");
        jceDisplayer.display(this.sIosUrl, "sIosUrl");
        jceDisplayer.display(this.sMultiPacketName, "sMultiPacketName");
        jceDisplayer.display(this.sShortName, "sShortName");
        jceDisplayer.display(this.sPicUrl, "sPicUrl");
    }
}
