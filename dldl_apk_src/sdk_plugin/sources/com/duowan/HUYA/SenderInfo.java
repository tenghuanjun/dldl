package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SenderInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static NobleLevelInfo cache_tNobleLevelInfo;
    public long lUid = 0;
    public long lImid = 0;
    public String sNickName = "";
    public int iGender = 0;
    public String sAvatarUrl = "";
    public int iNobleLevel = 0;
    public NobleLevelInfo tNobleLevelInfo = null;

    public String className() {
        return "HUYA.SenderInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SenderInfo";
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public long getLImid() {
        return this.lImid;
    }

    public void setLImid(long j) {
        this.lImid = j;
    }

    public String getSNickName() {
        return this.sNickName;
    }

    public void setSNickName(String str) {
        this.sNickName = str;
    }

    public int getIGender() {
        return this.iGender;
    }

    public void setIGender(int i) {
        this.iGender = i;
    }

    public String getSAvatarUrl() {
        return this.sAvatarUrl;
    }

    public void setSAvatarUrl(String str) {
        this.sAvatarUrl = str;
    }

    public int getINobleLevel() {
        return this.iNobleLevel;
    }

    public void setINobleLevel(int i) {
        this.iNobleLevel = i;
    }

    public NobleLevelInfo getTNobleLevelInfo() {
        return this.tNobleLevelInfo;
    }

    public void setTNobleLevelInfo(NobleLevelInfo nobleLevelInfo) {
        this.tNobleLevelInfo = nobleLevelInfo;
    }

    public SenderInfo() {
        setLUid(0L);
        setLImid(this.lImid);
        setSNickName(this.sNickName);
        setIGender(this.iGender);
        setSAvatarUrl(this.sAvatarUrl);
        setINobleLevel(this.iNobleLevel);
        setTNobleLevelInfo(this.tNobleLevelInfo);
    }

    public SenderInfo(long j, long j2, String str, int i, String str2, int i2, NobleLevelInfo nobleLevelInfo) {
        setLUid(j);
        setLImid(j2);
        setSNickName(str);
        setIGender(i);
        setSAvatarUrl(str2);
        setINobleLevel(i2);
        setTNobleLevelInfo(nobleLevelInfo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SenderInfo senderInfo = (SenderInfo) obj;
        return JceUtil.equals(this.lUid, senderInfo.lUid) && JceUtil.equals(this.lImid, senderInfo.lImid) && JceUtil.equals(this.sNickName, senderInfo.sNickName) && JceUtil.equals(this.iGender, senderInfo.iGender) && JceUtil.equals(this.sAvatarUrl, senderInfo.sAvatarUrl) && JceUtil.equals(this.iNobleLevel, senderInfo.iNobleLevel) && JceUtil.equals(this.tNobleLevelInfo, senderInfo.tNobleLevelInfo);
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
        jceOutputStream.write(this.lImid, 1);
        String str = this.sNickName;
        if (str != null) {
            jceOutputStream.write(str, 2);
        }
        jceOutputStream.write(this.iGender, 3);
        String str2 = this.sAvatarUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 4);
        }
        jceOutputStream.write(this.iNobleLevel, 5);
        NobleLevelInfo nobleLevelInfo = this.tNobleLevelInfo;
        if (nobleLevelInfo != null) {
            jceOutputStream.write((JceStruct) nobleLevelInfo, 6);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLUid(jceInputStream.read(this.lUid, 0, false));
        setLImid(jceInputStream.read(this.lImid, 1, false));
        setSNickName(jceInputStream.readString(2, false));
        setIGender(jceInputStream.read(this.iGender, 3, false));
        setSAvatarUrl(jceInputStream.readString(4, false));
        setINobleLevel(jceInputStream.read(this.iNobleLevel, 5, false));
        if (cache_tNobleLevelInfo == null) {
            cache_tNobleLevelInfo = new NobleLevelInfo();
        }
        setTNobleLevelInfo((NobleLevelInfo) jceInputStream.read((JceStruct) cache_tNobleLevelInfo, 6, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.lImid, "lImid");
        jceDisplayer.display(this.sNickName, "sNickName");
        jceDisplayer.display(this.iGender, "iGender");
        jceDisplayer.display(this.sAvatarUrl, "sAvatarUrl");
        jceDisplayer.display(this.iNobleLevel, "iNobleLevel");
        jceDisplayer.display((JceStruct) this.tNobleLevelInfo, "tNobleLevelInfo");
    }
}
