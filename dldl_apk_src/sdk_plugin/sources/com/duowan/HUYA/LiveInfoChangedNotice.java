package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveInfoChangedNotice extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public long lPresenterUid = 0;
    public int iGameId = 0;
    public String sGameName = "";
    public long lLiveId = 0;
    public String sLiveDesc = "";

    public String className() {
        return "HUYA.LiveInfoChangedNotice";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.LiveInfoChangedNotice";
    }

    public long getLPresenterUid() {
        return this.lPresenterUid;
    }

    public void setLPresenterUid(long j) {
        this.lPresenterUid = j;
    }

    public int getIGameId() {
        return this.iGameId;
    }

    public void setIGameId(int i) {
        this.iGameId = i;
    }

    public String getSGameName() {
        return this.sGameName;
    }

    public void setSGameName(String str) {
        this.sGameName = str;
    }

    public long getLLiveId() {
        return this.lLiveId;
    }

    public void setLLiveId(long j) {
        this.lLiveId = j;
    }

    public String getSLiveDesc() {
        return this.sLiveDesc;
    }

    public void setSLiveDesc(String str) {
        this.sLiveDesc = str;
    }

    public LiveInfoChangedNotice() {
        setLPresenterUid(0L);
        setIGameId(this.iGameId);
        setSGameName(this.sGameName);
        setLLiveId(this.lLiveId);
        setSLiveDesc(this.sLiveDesc);
    }

    public LiveInfoChangedNotice(long j, int i, String str, long j2, String str2) {
        setLPresenterUid(j);
        setIGameId(i);
        setSGameName(str);
        setLLiveId(j2);
        setSLiveDesc(str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LiveInfoChangedNotice liveInfoChangedNotice = (LiveInfoChangedNotice) obj;
        return JceUtil.equals(this.lPresenterUid, liveInfoChangedNotice.lPresenterUid) && JceUtil.equals(this.iGameId, liveInfoChangedNotice.iGameId) && JceUtil.equals(this.sGameName, liveInfoChangedNotice.sGameName) && JceUtil.equals(this.lLiveId, liveInfoChangedNotice.lLiveId) && JceUtil.equals(this.sLiveDesc, liveInfoChangedNotice.sLiveDesc);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lPresenterUid), JceUtil.hashCode(this.iGameId), JceUtil.hashCode(this.sGameName), JceUtil.hashCode(this.lLiveId), JceUtil.hashCode(this.sLiveDesc)});
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
        jceOutputStream.write(this.lPresenterUid, 0);
        jceOutputStream.write(this.iGameId, 1);
        String str = this.sGameName;
        if (str != null) {
            jceOutputStream.write(str, 2);
        }
        jceOutputStream.write(this.lLiveId, 3);
        String str2 = this.sLiveDesc;
        if (str2 != null) {
            jceOutputStream.write(str2, 4);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLPresenterUid(jceInputStream.read(this.lPresenterUid, 0, false));
        setIGameId(jceInputStream.read(this.iGameId, 1, false));
        setSGameName(jceInputStream.readString(2, false));
        setLLiveId(jceInputStream.read(this.lLiveId, 3, false));
        setSLiveDesc(jceInputStream.readString(4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lPresenterUid, "lPresenterUid");
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display(this.sGameName, "sGameName");
        jceDisplayer.display(this.lLiveId, "lLiveId");
        jceDisplayer.display(this.sLiveDesc, "sLiveDesc");
    }
}
