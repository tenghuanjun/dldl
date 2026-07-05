package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class BadgeReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tUserId;
    public UserId tUserId = null;
    public long lBadgeId = 0;
    public long lToUid = 0;

    public String className() {
        return "HUYA.BadgeReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.BadgeReq";
    }

    public UserId getTUserId() {
        return this.tUserId;
    }

    public void setTUserId(UserId userId) {
        this.tUserId = userId;
    }

    public long getLBadgeId() {
        return this.lBadgeId;
    }

    public void setLBadgeId(long j) {
        this.lBadgeId = j;
    }

    public long getLToUid() {
        return this.lToUid;
    }

    public void setLToUid(long j) {
        this.lToUid = j;
    }

    public BadgeReq() {
        setTUserId(null);
        setLBadgeId(this.lBadgeId);
        setLToUid(this.lToUid);
    }

    public BadgeReq(UserId userId, long j, long j2) {
        setTUserId(userId);
        setLBadgeId(j);
        setLToUid(j2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BadgeReq badgeReq = (BadgeReq) obj;
        return JceUtil.equals(this.tUserId, badgeReq.tUserId) && JceUtil.equals(this.lBadgeId, badgeReq.lBadgeId) && JceUtil.equals(this.lToUid, badgeReq.lToUid);
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
        jceOutputStream.write(this.lBadgeId, 1);
        jceOutputStream.write(this.lToUid, 2);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tUserId == null) {
            cache_tUserId = new UserId();
        }
        setTUserId((UserId) jceInputStream.read((JceStruct) cache_tUserId, 0, false));
        setLBadgeId(jceInputStream.read(this.lBadgeId, 1, false));
        setLToUid(jceInputStream.read(this.lToUid, 2, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tUserId, "tUserId");
        jceDisplayer.display(this.lBadgeId, "lBadgeId");
        jceDisplayer.display(this.lToUid, "lToUid");
    }
}
