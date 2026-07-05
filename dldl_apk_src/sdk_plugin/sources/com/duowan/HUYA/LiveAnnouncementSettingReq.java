package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class LiveAnnouncementSettingReq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static UserId cache_tId;
    public UserId tId = null;
    public String sLiveAnnouncement = "";

    public String className() {
        return "HUYA.LiveAnnouncementSettingReq";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.LiveAnnouncementSettingReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public String getSLiveAnnouncement() {
        return this.sLiveAnnouncement;
    }

    public void setSLiveAnnouncement(String str) {
        this.sLiveAnnouncement = str;
    }

    public LiveAnnouncementSettingReq() {
        setTId(null);
        setSLiveAnnouncement(this.sLiveAnnouncement);
    }

    public LiveAnnouncementSettingReq(UserId userId, String str) {
        setTId(userId);
        setSLiveAnnouncement(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LiveAnnouncementSettingReq liveAnnouncementSettingReq = (LiveAnnouncementSettingReq) obj;
        return JceUtil.equals(this.tId, liveAnnouncementSettingReq.tId) && JceUtil.equals(this.sLiveAnnouncement, liveAnnouncementSettingReq.sLiveAnnouncement);
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
        UserId userId = this.tId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 0);
        }
        String str = this.sLiveAnnouncement;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setSLiveAnnouncement(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.sLiveAnnouncement, "sLiveAnnouncement");
    }
}
