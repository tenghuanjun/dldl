package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class UserBase extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lUid = 0;
    public String sNickName = "";
    public String sAvatarUrl = "";
    public int iGender = 0;
    public long lYYId = 0;
    public int iCertified = 0;
    public int iSubscribedCount = 0;
    public int iSubscribeToCount = 0;
    public int iUserLevel = 0;
    public long lUserExp = 0;
    public int iBirthday = 0;
    public String sSign = "";
    public String sArea = "";
    public String sLocation = "";
    public String sRegisterTime = "";
    public int iFreezeTime = 0;
    public int iBindPhone = 0;
    public String sHuyaId = "";

    public String className() {
        return "HUYA.UserBase";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.UserBase";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public String getSNickName() {
        return this.sNickName;
    }

    public void setSNickName(String str) {
        this.sNickName = str;
    }

    public String getSAvatarUrl() {
        return this.sAvatarUrl;
    }

    public void setSAvatarUrl(String str) {
        this.sAvatarUrl = str;
    }

    public int getIGender() {
        return this.iGender;
    }

    public void setIGender(int i) {
        this.iGender = i;
    }

    public long getLYYId() {
        return this.lYYId;
    }

    public void setLYYId(long j) {
        this.lYYId = j;
    }

    public int getICertified() {
        return this.iCertified;
    }

    public void setICertified(int i) {
        this.iCertified = i;
    }

    public int getISubscribedCount() {
        return this.iSubscribedCount;
    }

    public void setISubscribedCount(int i) {
        this.iSubscribedCount = i;
    }

    public int getISubscribeToCount() {
        return this.iSubscribeToCount;
    }

    public void setISubscribeToCount(int i) {
        this.iSubscribeToCount = i;
    }

    public int getIUserLevel() {
        return this.iUserLevel;
    }

    public void setIUserLevel(int i) {
        this.iUserLevel = i;
    }

    public long getLUserExp() {
        return this.lUserExp;
    }

    public void setLUserExp(long j) {
        this.lUserExp = j;
    }

    public int getIBirthday() {
        return this.iBirthday;
    }

    public void setIBirthday(int i) {
        this.iBirthday = i;
    }

    public String getSSign() {
        return this.sSign;
    }

    public void setSSign(String str) {
        this.sSign = str;
    }

    public String getSArea() {
        return this.sArea;
    }

    public void setSArea(String str) {
        this.sArea = str;
    }

    public String getSLocation() {
        return this.sLocation;
    }

    public void setSLocation(String str) {
        this.sLocation = str;
    }

    public String getSRegisterTime() {
        return this.sRegisterTime;
    }

    public void setSRegisterTime(String str) {
        this.sRegisterTime = str;
    }

    public int getIFreezeTime() {
        return this.iFreezeTime;
    }

    public void setIFreezeTime(int i) {
        this.iFreezeTime = i;
    }

    public int getIBindPhone() {
        return this.iBindPhone;
    }

    public void setIBindPhone(int i) {
        this.iBindPhone = i;
    }

    public String getSHuyaId() {
        return this.sHuyaId;
    }

    public void setSHuyaId(String str) {
        this.sHuyaId = str;
    }

    public UserBase() {
        setLUid(0L);
        setSNickName(this.sNickName);
        setSAvatarUrl(this.sAvatarUrl);
        setIGender(this.iGender);
        setLYYId(this.lYYId);
        setICertified(this.iCertified);
        setISubscribedCount(this.iSubscribedCount);
        setISubscribeToCount(this.iSubscribeToCount);
        setIUserLevel(this.iUserLevel);
        setLUserExp(this.lUserExp);
        setIBirthday(this.iBirthday);
        setSSign(this.sSign);
        setSArea(this.sArea);
        setSLocation(this.sLocation);
        setSRegisterTime(this.sRegisterTime);
        setIFreezeTime(this.iFreezeTime);
        setIBindPhone(this.iBindPhone);
        setSHuyaId(this.sHuyaId);
    }

    public UserBase(long j, String str, String str2, int i, long j2, int i2, int i3, int i4, int i5, long j3, int i6, String str3, String str4, String str5, String str6, int i7, int i8, String str7) {
        setLUid(j);
        setSNickName(str);
        setSAvatarUrl(str2);
        setIGender(i);
        setLYYId(j2);
        setICertified(i2);
        setISubscribedCount(i3);
        setISubscribeToCount(i4);
        setIUserLevel(i5);
        setLUserExp(j3);
        setIBirthday(i6);
        setSSign(str3);
        setSArea(str4);
        setSLocation(str5);
        setSRegisterTime(str6);
        setIFreezeTime(i7);
        setIBindPhone(i8);
        setSHuyaId(str7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserBase userBase = (UserBase) obj;
        return JceUtil.equals(this.lUid, userBase.lUid) && JceUtil.equals(this.sNickName, userBase.sNickName) && JceUtil.equals(this.sAvatarUrl, userBase.sAvatarUrl) && JceUtil.equals(this.iGender, userBase.iGender) && JceUtil.equals(this.lYYId, userBase.lYYId) && JceUtil.equals(this.iCertified, userBase.iCertified) && JceUtil.equals(this.iSubscribedCount, userBase.iSubscribedCount) && JceUtil.equals(this.iSubscribeToCount, userBase.iSubscribeToCount) && JceUtil.equals(this.iUserLevel, userBase.iUserLevel) && JceUtil.equals(this.lUserExp, userBase.lUserExp) && JceUtil.equals(this.iBirthday, userBase.iBirthday) && JceUtil.equals(this.sSign, userBase.sSign) && JceUtil.equals(this.sArea, userBase.sArea) && JceUtil.equals(this.sLocation, userBase.sLocation) && JceUtil.equals(this.sRegisterTime, userBase.sRegisterTime) && JceUtil.equals(this.iFreezeTime, userBase.iFreezeTime) && JceUtil.equals(this.iBindPhone, userBase.iBindPhone) && JceUtil.equals(this.sHuyaId, userBase.sHuyaId);
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
        jceOutputStream.write(this.lUid, 0);
        String str = this.sNickName;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sAvatarUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        jceOutputStream.write(this.iGender, 3);
        jceOutputStream.write(this.lYYId, 4);
        jceOutputStream.write(this.iCertified, 5);
        jceOutputStream.write(this.iSubscribedCount, 6);
        jceOutputStream.write(this.iSubscribeToCount, 7);
        jceOutputStream.write(this.iUserLevel, 8);
        jceOutputStream.write(this.lUserExp, 9);
        jceOutputStream.write(this.iBirthday, 10);
        String str3 = this.sSign;
        if (str3 != null) {
            jceOutputStream.write(str3, 11);
        }
        String str4 = this.sArea;
        if (str4 != null) {
            jceOutputStream.write(str4, 12);
        }
        String str5 = this.sLocation;
        if (str5 != null) {
            jceOutputStream.write(str5, 13);
        }
        String str6 = this.sRegisterTime;
        if (str6 != null) {
            jceOutputStream.write(str6, 14);
        }
        jceOutputStream.write(this.iFreezeTime, 15);
        jceOutputStream.write(this.iBindPhone, 16);
        String str7 = this.sHuyaId;
        if (str7 != null) {
            jceOutputStream.write(str7, 17);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setSNickName(jceInputStream.readString(1, false));
        setSAvatarUrl(jceInputStream.readString(2, false));
        setIGender(jceInputStream.read(this.iGender, 3, false));
        setLYYId(jceInputStream.read(this.lYYId, 4, false));
        setICertified(jceInputStream.read(this.iCertified, 5, false));
        setISubscribedCount(jceInputStream.read(this.iSubscribedCount, 6, false));
        setISubscribeToCount(jceInputStream.read(this.iSubscribeToCount, 7, false));
        setIUserLevel(jceInputStream.read(this.iUserLevel, 8, false));
        setLUserExp(jceInputStream.read(this.lUserExp, 9, false));
        setIBirthday(jceInputStream.read(this.iBirthday, 10, false));
        setSSign(jceInputStream.readString(11, false));
        setSArea(jceInputStream.readString(12, false));
        setSLocation(jceInputStream.readString(13, false));
        setSRegisterTime(jceInputStream.readString(14, false));
        setIFreezeTime(jceInputStream.read(this.iFreezeTime, 15, false));
        setIBindPhone(jceInputStream.read(this.iBindPhone, 16, false));
        setSHuyaId(jceInputStream.readString(17, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.sNickName, "sNickName");
        jceDisplayer.display(this.sAvatarUrl, "sAvatarUrl");
        jceDisplayer.display(this.iGender, "iGender");
        jceDisplayer.display(this.lYYId, "lYYId");
        jceDisplayer.display(this.iCertified, "iCertified");
        jceDisplayer.display(this.iSubscribedCount, "iSubscribedCount");
        jceDisplayer.display(this.iSubscribeToCount, "iSubscribeToCount");
        jceDisplayer.display(this.iUserLevel, "iUserLevel");
        jceDisplayer.display(this.lUserExp, "lUserExp");
        jceDisplayer.display(this.iBirthday, "iBirthday");
        jceDisplayer.display(this.sSign, "sSign");
        jceDisplayer.display(this.sArea, "sArea");
        jceDisplayer.display(this.sLocation, "sLocation");
        jceDisplayer.display(this.sRegisterTime, "sRegisterTime");
        jceDisplayer.display(this.iFreezeTime, "iFreezeTime");
        jceDisplayer.display(this.iBindPhone, "iBindPhone");
        jceDisplayer.display(this.sHuyaId, "sHuyaId");
    }
}
