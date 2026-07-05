package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class SendMessageReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static BulletFormat cache_tBulletFormat;
    static ContentFormat cache_tFormat;
    static UserId cache_tUserId;
    static ArrayList<UidNickName> cache_vAtSomeone;
    public UserId tUserId = null;
    public long lTid = 0;
    public long lSid = 0;
    public String sContent = "";
    public int iShowMode = 0;
    public ContentFormat tFormat = null;
    public BulletFormat tBulletFormat = null;
    public ArrayList<UidNickName> vAtSomeone = null;
    public long lPid = 0;

    public String className() {
        return "HUYA.SendMessageReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SendMessageReq";
    }

    public UserId getTUserId() {
        return this.tUserId;
    }

    public void setTUserId(UserId userId) {
        this.tUserId = userId;
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

    public SendMessageReq() {
        setTUserId(null);
        setLTid(this.lTid);
        setLSid(this.lSid);
        setSContent(this.sContent);
        setIShowMode(this.iShowMode);
        setTFormat(this.tFormat);
        setTBulletFormat(this.tBulletFormat);
        setVAtSomeone(this.vAtSomeone);
        setLPid(this.lPid);
    }

    public SendMessageReq(UserId userId, long j, long j2, String str, int i, ContentFormat contentFormat, BulletFormat bulletFormat, ArrayList<UidNickName> arrayList, long j3) {
        setTUserId(userId);
        setLTid(j);
        setLSid(j2);
        setSContent(str);
        setIShowMode(i);
        setTFormat(contentFormat);
        setTBulletFormat(bulletFormat);
        setVAtSomeone(arrayList);
        setLPid(j3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SendMessageReq sendMessageReq = (SendMessageReq) obj;
        return JceUtil.equals(this.tUserId, sendMessageReq.tUserId) && JceUtil.equals(this.lTid, sendMessageReq.lTid) && JceUtil.equals(this.lSid, sendMessageReq.lSid) && JceUtil.equals(this.sContent, sendMessageReq.sContent) && JceUtil.equals(this.iShowMode, sendMessageReq.iShowMode) && JceUtil.equals(this.tFormat, sendMessageReq.tFormat) && JceUtil.equals(this.tBulletFormat, sendMessageReq.tBulletFormat) && JceUtil.equals(this.vAtSomeone, sendMessageReq.vAtSomeone) && JceUtil.equals(this.lPid, sendMessageReq.lPid);
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
        UserId userId = this.tUserId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 0);
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
        ArrayList<UidNickName> arrayList = this.vAtSomeone;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 7);
        }
        jceOutputStream.write(this.lPid, 8);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tUserId == null) {
            cache_tUserId = new UserId();
        }
        setTUserId((UserId) jceInputStream.read((JceStruct) cache_tUserId, 0, false));
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
        if (cache_vAtSomeone == null) {
            cache_vAtSomeone = new ArrayList<>();
            cache_vAtSomeone.add(new UidNickName());
        }
        setVAtSomeone((ArrayList) jceInputStream.read(cache_vAtSomeone, 7, false));
        setLPid(jceInputStream.read(this.lPid, 8, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tUserId, "tUserId");
        jceDisplayer.display(this.lTid, "lTid");
        jceDisplayer.display(this.lSid, "lSid");
        jceDisplayer.display(this.sContent, "sContent");
        jceDisplayer.display(this.iShowMode, "iShowMode");
        jceDisplayer.display((JceStruct) this.tFormat, "tFormat");
        jceDisplayer.display((JceStruct) this.tBulletFormat, "tBulletFormat");
        jceDisplayer.display((Collection) this.vAtSomeone, "vAtSomeone");
        jceDisplayer.display(this.lPid, "lPid");
    }
}
