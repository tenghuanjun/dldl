package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class MessageNotice extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static BulletFormat cache_tBulletFormat;
    static ContentFormat cache_tFormat;
    static SenderInfo cache_tUserInfo;
    static ArrayList<UidNickName> cache_vAtSomeone;
    static ArrayList<DecorationInfo> cache_vBulletPrefix;
    static ArrayList<DecorationInfo> cache_vDecorationPrefix;
    static ArrayList<DecorationInfo> cache_vDecorationSuffix;
    public SenderInfo tUserInfo = null;
    public long lTid = 0;
    public long lSid = 0;
    public String sContent = "";
    public int iShowMode = 0;
    public ContentFormat tFormat = null;
    public BulletFormat tBulletFormat = null;
    public int iTermType = 0;
    public ArrayList<DecorationInfo> vDecorationPrefix = null;
    public ArrayList<DecorationInfo> vDecorationSuffix = null;
    public ArrayList<UidNickName> vAtSomeone = null;
    public long lPid = 0;
    public ArrayList<DecorationInfo> vBulletPrefix = null;
    public String sIconUrl = "";
    public int iType = 0;

    public String className() {
        return "HUYA.MessageNotice";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.MessageNotice";
    }

    public SenderInfo getTUserInfo() {
        return this.tUserInfo;
    }

    public void setTUserInfo(SenderInfo senderInfo) {
        this.tUserInfo = senderInfo;
    }

    public long getLTid() {
        return this.lTid;
    }

    public void setLTid(long j) {
        this.lTid = j;
    }

    public long getLSid() {
        return this.lSid;
    }

    public void setLSid(long j) {
        this.lSid = j;
    }

    public String getSContent() {
        return this.sContent;
    }

    public void setSContent(String str) {
        this.sContent = str;
    }

    public int getIShowMode() {
        return this.iShowMode;
    }

    public void setIShowMode(int i) {
        this.iShowMode = i;
    }

    public ContentFormat getTFormat() {
        return this.tFormat;
    }

    public void setTFormat(ContentFormat contentFormat) {
        this.tFormat = contentFormat;
    }

    public BulletFormat getTBulletFormat() {
        return this.tBulletFormat;
    }

    public void setTBulletFormat(BulletFormat bulletFormat) {
        this.tBulletFormat = bulletFormat;
    }

    public int getITermType() {
        return this.iTermType;
    }

    public void setITermType(int i) {
        this.iTermType = i;
    }

    public ArrayList<DecorationInfo> getVDecorationPrefix() {
        return this.vDecorationPrefix;
    }

    public void setVDecorationPrefix(ArrayList<DecorationInfo> arrayList) {
        this.vDecorationPrefix = arrayList;
    }

    public ArrayList<DecorationInfo> getVDecorationSuffix() {
        return this.vDecorationSuffix;
    }

    public void setVDecorationSuffix(ArrayList<DecorationInfo> arrayList) {
        this.vDecorationSuffix = arrayList;
    }

    public ArrayList<UidNickName> getVAtSomeone() {
        return this.vAtSomeone;
    }

    public void setVAtSomeone(ArrayList<UidNickName> arrayList) {
        this.vAtSomeone = arrayList;
    }

    public long getLPid() {
        return this.lPid;
    }

    public void setLPid(long j) {
        this.lPid = j;
    }

    public ArrayList<DecorationInfo> getVBulletPrefix() {
        return this.vBulletPrefix;
    }

    public void setVBulletPrefix(ArrayList<DecorationInfo> arrayList) {
        this.vBulletPrefix = arrayList;
    }

    public String getSIconUrl() {
        return this.sIconUrl;
    }

    public void setSIconUrl(String str) {
        this.sIconUrl = str;
    }

    public int getIType() {
        return this.iType;
    }

    public void setIType(int i) {
        this.iType = i;
    }

    public MessageNotice() {
        setTUserInfo(null);
        setLTid(this.lTid);
        setLSid(this.lSid);
        setSContent(this.sContent);
        setIShowMode(this.iShowMode);
        setTFormat(this.tFormat);
        setTBulletFormat(this.tBulletFormat);
        setITermType(this.iTermType);
        setVDecorationPrefix(this.vDecorationPrefix);
        setVDecorationSuffix(this.vDecorationSuffix);
        setVAtSomeone(this.vAtSomeone);
        setLPid(this.lPid);
        setVBulletPrefix(this.vBulletPrefix);
        setSIconUrl(this.sIconUrl);
        setIType(this.iType);
    }

    public MessageNotice(SenderInfo senderInfo, long j, long j2, String str, int i, ContentFormat contentFormat, BulletFormat bulletFormat, int i2, ArrayList<DecorationInfo> arrayList, ArrayList<DecorationInfo> arrayList2, ArrayList<UidNickName> arrayList3, long j3, ArrayList<DecorationInfo> arrayList4, String str2, int i3) {
        setTUserInfo(senderInfo);
        setLTid(j);
        setLSid(j2);
        setSContent(str);
        setIShowMode(i);
        setTFormat(contentFormat);
        setTBulletFormat(bulletFormat);
        setITermType(i2);
        setVDecorationPrefix(arrayList);
        setVDecorationSuffix(arrayList2);
        setVAtSomeone(arrayList3);
        setLPid(j3);
        setVBulletPrefix(arrayList4);
        setSIconUrl(str2);
        setIType(i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageNotice messageNotice = (MessageNotice) obj;
        return JceUtil.equals(this.tUserInfo, messageNotice.tUserInfo) && JceUtil.equals(this.lTid, messageNotice.lTid) && JceUtil.equals(this.lSid, messageNotice.lSid) && JceUtil.equals(this.sContent, messageNotice.sContent) && JceUtil.equals(this.iShowMode, messageNotice.iShowMode) && JceUtil.equals(this.tFormat, messageNotice.tFormat) && JceUtil.equals(this.tBulletFormat, messageNotice.tBulletFormat) && JceUtil.equals(this.iTermType, messageNotice.iTermType) && JceUtil.equals(this.vDecorationPrefix, messageNotice.vDecorationPrefix) && JceUtil.equals(this.vDecorationSuffix, messageNotice.vDecorationSuffix) && JceUtil.equals(this.vAtSomeone, messageNotice.vAtSomeone) && JceUtil.equals(this.lPid, messageNotice.lPid) && JceUtil.equals(this.vBulletPrefix, messageNotice.vBulletPrefix) && JceUtil.equals(this.sIconUrl, messageNotice.sIconUrl) && JceUtil.equals(this.iType, messageNotice.iType);
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
        SenderInfo senderInfo = this.tUserInfo;
        if (senderInfo != null) {
            jceOutputStream.write((JceStruct) senderInfo, 0);
        }
        jceOutputStream.write(this.lTid, 1);
        jceOutputStream.write(this.lSid, 2);
        String str = this.sContent;
        if (str != null) {
            jceOutputStream.write(str, 3);
        }
        jceOutputStream.write(this.iShowMode, 4);
        ContentFormat contentFormat = this.tFormat;
        if (contentFormat != null) {
            jceOutputStream.write((JceStruct) contentFormat, 5);
        }
        BulletFormat bulletFormat = this.tBulletFormat;
        if (bulletFormat != null) {
            jceOutputStream.write((JceStruct) bulletFormat, 6);
        }
        jceOutputStream.write(this.iTermType, 7);
        ArrayList<DecorationInfo> arrayList = this.vDecorationPrefix;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 8);
        }
        ArrayList<DecorationInfo> arrayList2 = this.vDecorationSuffix;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 9);
        }
        ArrayList<UidNickName> arrayList3 = this.vAtSomeone;
        if (arrayList3 != null) {
            jceOutputStream.write((Collection) arrayList3, 10);
        }
        jceOutputStream.write(this.lPid, 11);
        ArrayList<DecorationInfo> arrayList4 = this.vBulletPrefix;
        if (arrayList4 != null) {
            jceOutputStream.write((Collection) arrayList4, 12);
        }
        String str2 = this.sIconUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 13);
        }
        jceOutputStream.write(this.iType, 14);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tUserInfo == null) {
            cache_tUserInfo = new SenderInfo();
        }
        setTUserInfo((SenderInfo) jceInputStream.read((JceStruct) cache_tUserInfo, 0, false));
        setLTid(jceInputStream.read(this.lTid, 1, false));
        setLSid(jceInputStream.read(this.lSid, 2, false));
        setSContent(jceInputStream.readString(3, false));
        setIShowMode(jceInputStream.read(this.iShowMode, 4, false));
        if (cache_tFormat == null) {
            cache_tFormat = new ContentFormat();
        }
        setTFormat((ContentFormat) jceInputStream.read((JceStruct) cache_tFormat, 5, false));
        if (cache_tBulletFormat == null) {
            cache_tBulletFormat = new BulletFormat();
        }
        setTBulletFormat((BulletFormat) jceInputStream.read((JceStruct) cache_tBulletFormat, 6, false));
        setITermType(jceInputStream.read(this.iTermType, 7, false));
        if (cache_vDecorationPrefix == null) {
            cache_vDecorationPrefix = new ArrayList<>();
            cache_vDecorationPrefix.add(new DecorationInfo());
        }
        setVDecorationPrefix((ArrayList) jceInputStream.read(cache_vDecorationPrefix, 8, false));
        if (cache_vDecorationSuffix == null) {
            cache_vDecorationSuffix = new ArrayList<>();
            cache_vDecorationSuffix.add(new DecorationInfo());
        }
        setVDecorationSuffix((ArrayList) jceInputStream.read(cache_vDecorationSuffix, 9, false));
        if (cache_vAtSomeone == null) {
            cache_vAtSomeone = new ArrayList<>();
            cache_vAtSomeone.add(new UidNickName());
        }
        setVAtSomeone((ArrayList) jceInputStream.read(cache_vAtSomeone, 10, false));
        setLPid(jceInputStream.read(this.lPid, 11, false));
        if (cache_vBulletPrefix == null) {
            cache_vBulletPrefix = new ArrayList<>();
            cache_vBulletPrefix.add(new DecorationInfo());
        }
        setVBulletPrefix((ArrayList) jceInputStream.read(cache_vBulletPrefix, 12, false));
        setSIconUrl(jceInputStream.readString(13, false));
        setIType(jceInputStream.read(this.iType, 14, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tUserInfo, "tUserInfo");
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSid, "lSid");
        jceDisplayer.display(this.sContent, "sContent");
        jceDisplayer.display(this.iShowMode, "iShowMode");
        jceDisplayer.display((JceStruct) this.tFormat, "tFormat");
        jceDisplayer.display((JceStruct) this.tBulletFormat, "tBulletFormat");
        jceDisplayer.display(this.iTermType, "iTermType");
        jceDisplayer.display((Collection) this.vDecorationPrefix, "vDecorationPrefix");
        jceDisplayer.display((Collection) this.vDecorationSuffix, "vDecorationSuffix");
        jceDisplayer.display((Collection) this.vAtSomeone, "vAtSomeone");
        jceDisplayer.display(this.lPid, "lPid");
        jceDisplayer.display((Collection) this.vBulletPrefix, "vBulletPrefix");
        jceDisplayer.display(this.sIconUrl, "sIconUrl");
        jceDisplayer.display(this.iType, "iType");
    }
}
