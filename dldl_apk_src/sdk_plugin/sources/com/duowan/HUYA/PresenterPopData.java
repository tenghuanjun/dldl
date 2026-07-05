package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class PresenterPopData extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static ArrayList<PopupButtonInfo> cache_vButtonInfo;
    public long lId = 0;
    public String sContent = "";
    public String sExtenalLink = "";
    public long lStartTime = 0;
    public long lEndTime = 0;
    public long iPopInterval = 0;
    public long lNowTime = 0;
    public String sTitle = "";
    public ArrayList<PopupButtonInfo> vButtonInfo = null;

    public String className() {
        return "HUYA.PresenterPopData";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.PresenterPopData";
    }

    public long getLId() {
        return this.lId;
    }

    public void setLId(long j) {
        this.lId = j;
    }

    public String getSContent() {
        return this.sContent;
    }

    public void setSContent(String str) {
        this.sContent = str;
    }

    public String getSExtenalLink() {
        return this.sExtenalLink;
    }

    public void setSExtenalLink(String str) {
        this.sExtenalLink = str;
    }

    public long getLStartTime() {
        return this.lStartTime;
    }

    public void setLStartTime(long j) {
        this.lStartTime = j;
    }

    public long getLEndTime() {
        return this.lEndTime;
    }

    public void setLEndTime(long j) {
        this.lEndTime = j;
    }

    public long getIPopInterval() {
        return this.iPopInterval;
    }

    public void setIPopInterval(long j) {
        this.iPopInterval = j;
    }

    public long getLNowTime() {
        return this.lNowTime;
    }

    public void setLNowTime(long j) {
        this.lNowTime = j;
    }

    public String getSTitle() {
        return this.sTitle;
    }

    public void setSTitle(String str) {
        this.sTitle = str;
    }

    public ArrayList<PopupButtonInfo> getVButtonInfo() {
        return this.vButtonInfo;
    }

    public void setVButtonInfo(ArrayList<PopupButtonInfo> arrayList) {
        this.vButtonInfo = arrayList;
    }

    public PresenterPopData() {
        setLId(0L);
        setSContent(this.sContent);
        setSExtenalLink(this.sExtenalLink);
        setLStartTime(this.lStartTime);
        setLEndTime(this.lEndTime);
        setIPopInterval(this.iPopInterval);
        setLNowTime(this.lNowTime);
        setSTitle(this.sTitle);
        setVButtonInfo(this.vButtonInfo);
    }

    public PresenterPopData(long j, String str, String str2, long j2, long j3, long j4, long j5, String str3, ArrayList<PopupButtonInfo> arrayList) {
        setLId(j);
        setSContent(str);
        setSExtenalLink(str2);
        setLStartTime(j2);
        setLEndTime(j3);
        setIPopInterval(j4);
        setLNowTime(j5);
        setSTitle(str3);
        setVButtonInfo(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PresenterPopData presenterPopData = (PresenterPopData) obj;
        return JceUtil.equals(this.lId, presenterPopData.lId) && JceUtil.equals(this.sContent, presenterPopData.sContent) && JceUtil.equals(this.sExtenalLink, presenterPopData.sExtenalLink) && JceUtil.equals(this.lStartTime, presenterPopData.lStartTime) && JceUtil.equals(this.lEndTime, presenterPopData.lEndTime) && JceUtil.equals(this.iPopInterval, presenterPopData.iPopInterval) && JceUtil.equals(this.lNowTime, presenterPopData.lNowTime) && JceUtil.equals(this.sTitle, presenterPopData.sTitle) && JceUtil.equals(this.vButtonInfo, presenterPopData.vButtonInfo);
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
        jceOutputStream.write(this.lId, 0);
        String str = this.sContent;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        String str2 = this.sExtenalLink;
        if (str2 != null) {
            jceOutputStream.write(str2, 2);
        }
        jceOutputStream.write(this.lStartTime, 3);
        jceOutputStream.write(this.lEndTime, 4);
        jceOutputStream.write(this.iPopInterval, 5);
        jceOutputStream.write(this.lNowTime, 6);
        String str3 = this.sTitle;
        if (str3 != null) {
            jceOutputStream.write(str3, 7);
        }
        ArrayList<PopupButtonInfo> arrayList = this.vButtonInfo;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 8);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLId(jceInputStream.read(this.lId, 0, false));
        setSContent(jceInputStream.readString(1, false));
        setSExtenalLink(jceInputStream.readString(2, false));
        setLStartTime(jceInputStream.read(this.lStartTime, 3, false));
        setLEndTime(jceInputStream.read(this.lEndTime, 4, false));
        setIPopInterval(jceInputStream.read(this.iPopInterval, 5, false));
        setLNowTime(jceInputStream.read(this.lNowTime, 6, false));
        setSTitle(jceInputStream.readString(7, false));
        if (cache_vButtonInfo == null) {
            cache_vButtonInfo = new ArrayList<>();
            cache_vButtonInfo.add(new PopupButtonInfo());
        }
        setVButtonInfo((ArrayList) jceInputStream.read(cache_vButtonInfo, 8, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lId, "lId");
        jceDisplayer.display(this.sContent, "sContent");
        jceDisplayer.display(this.sExtenalLink, "sExtenalLink");
        jceDisplayer.display(this.lStartTime, "lStartTime");
        jceDisplayer.display(this.lEndTime, "lEndTime");
        jceDisplayer.display(this.iPopInterval, "iPopInterval");
        jceDisplayer.display(this.lNowTime, "lNowTime");
        jceDisplayer.display(this.sTitle, "sTitle");
        jceDisplayer.display((Collection) this.vButtonInfo, "vButtonInfo");
    }
}
