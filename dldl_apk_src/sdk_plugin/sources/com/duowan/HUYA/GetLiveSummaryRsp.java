package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GetLiveSummaryRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public int iDuration = 0;
    public int iPeakViewer = 0;
    public int iGiftCount = 0;
    public int iNewFans = 0;
    public int iLiveShare = 0;
    public int iVideoShare = 0;
    public String sGameName = "";
    public int iInfluenceValue = 0;
    public String sText = "";
    public String sJumpUrl = "";
    public long lGiftCount = 0;

    public String className() {
        return "HUYA.GetLiveSummaryRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetLiveSummaryRsp";
    }

    public int getIDuration() {
        return this.iDuration;
    }

    public void setIDuration(int i) {
        this.iDuration = i;
    }

    public int getIPeakViewer() {
        return this.iPeakViewer;
    }

    public void setIPeakViewer(int i) {
        this.iPeakViewer = i;
    }

    public int getIGiftCount() {
        return this.iGiftCount;
    }

    public void setIGiftCount(int i) {
        this.iGiftCount = i;
    }

    public int getINewFans() {
        return this.iNewFans;
    }

    public void setINewFans(int i) {
        this.iNewFans = i;
    }

    public int getILiveShare() {
        return this.iLiveShare;
    }

    public void setILiveShare(int i) {
        this.iLiveShare = i;
    }

    public int getIVideoShare() {
        return this.iVideoShare;
    }

    public void setIVideoShare(int i) {
        this.iVideoShare = i;
    }

    public String getSGameName() {
        return this.sGameName;
    }

    public void setSGameName(String str) {
        this.sGameName = str;
    }

    public int getIInfluenceValue() {
        return this.iInfluenceValue;
    }

    public void setIInfluenceValue(int i) {
        this.iInfluenceValue = i;
    }

    public String getSText() {
        return this.sText;
    }

    public void setSText(String str) {
        this.sText = str;
    }

    public String getSJumpUrl() {
        return this.sJumpUrl;
    }

    public void setSJumpUrl(String str) {
        this.sJumpUrl = str;
    }

    public long getLGiftCount() {
        return this.lGiftCount;
    }

    public void setLGiftCount(long j) {
        this.lGiftCount = j;
    }

    public GetLiveSummaryRsp() {
        setIDuration(0);
        setIPeakViewer(this.iPeakViewer);
        setIGiftCount(this.iGiftCount);
        setINewFans(this.iNewFans);
        setILiveShare(this.iLiveShare);
        setIVideoShare(this.iVideoShare);
        setSGameName(this.sGameName);
        setIInfluenceValue(this.iInfluenceValue);
        setSText(this.sText);
        setSJumpUrl(this.sJumpUrl);
        setLGiftCount(this.lGiftCount);
    }

    public GetLiveSummaryRsp(int i, int i2, int i3, int i4, int i5, int i6, String str, int i7, String str2, String str3, long j) {
        setIDuration(i);
        setIPeakViewer(i2);
        setIGiftCount(i3);
        setINewFans(i4);
        setILiveShare(i5);
        setIVideoShare(i6);
        setSGameName(str);
        setIInfluenceValue(i7);
        setSText(str2);
        setSJumpUrl(str3);
        setLGiftCount(j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetLiveSummaryRsp getLiveSummaryRsp = (GetLiveSummaryRsp) obj;
        return JceUtil.equals(this.iDuration, getLiveSummaryRsp.iDuration) && JceUtil.equals(this.iPeakViewer, getLiveSummaryRsp.iPeakViewer) && JceUtil.equals(this.iGiftCount, getLiveSummaryRsp.iGiftCount) && JceUtil.equals(this.iNewFans, getLiveSummaryRsp.iNewFans) && JceUtil.equals(this.iLiveShare, getLiveSummaryRsp.iLiveShare) && JceUtil.equals(this.iVideoShare, getLiveSummaryRsp.iVideoShare) && JceUtil.equals(this.sGameName, getLiveSummaryRsp.sGameName) && JceUtil.equals(this.iInfluenceValue, getLiveSummaryRsp.iInfluenceValue) && JceUtil.equals(this.sText, getLiveSummaryRsp.sText) && JceUtil.equals(this.sJumpUrl, getLiveSummaryRsp.sJumpUrl) && JceUtil.equals(this.lGiftCount, getLiveSummaryRsp.lGiftCount);
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
        jceOutputStream.write(this.iDuration, 0);
        jceOutputStream.write(this.iPeakViewer, 1);
        jceOutputStream.write(this.iGiftCount, 2);
        jceOutputStream.write(this.iNewFans, 3);
        jceOutputStream.write(this.iLiveShare, 4);
        jceOutputStream.write(this.iVideoShare, 5);
        String str = this.sGameName;
        if (str != null) {
            jceOutputStream.write(str, 6);
        }
        jceOutputStream.write(this.iInfluenceValue, 7);
        String str2 = this.sText;
        if (str2 != null) {
            jceOutputStream.write(str2, 8);
        }
        String str3 = this.sJumpUrl;
        if (str3 != null) {
            jceOutputStream.write(str3, 9);
        }
        jceOutputStream.write(this.lGiftCount, 10);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIDuration(jceInputStream.read(this.iDuration, 0, false));
        setIPeakViewer(jceInputStream.read(this.iPeakViewer, 1, false));
        setIGiftCount(jceInputStream.read(this.iGiftCount, 2, false));
        setINewFans(jceInputStream.read(this.iNewFans, 3, false));
        setILiveShare(jceInputStream.read(this.iLiveShare, 4, false));
        setIVideoShare(jceInputStream.read(this.iVideoShare, 5, false));
        setSGameName(jceInputStream.readString(6, false));
        setIInfluenceValue(jceInputStream.read(this.iInfluenceValue, 7, false));
        setSText(jceInputStream.readString(8, false));
        setSJumpUrl(jceInputStream.readString(9, false));
        setLGiftCount(jceInputStream.read(this.lGiftCount, 10, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iDuration, "iDuration");
        jceDisplayer.display(this.iPeakViewer, "iPeakViewer");
        jceDisplayer.display(this.iGiftCount, "iGiftCount");
        jceDisplayer.display(this.iNewFans, "iNewFans");
        jceDisplayer.display(this.iLiveShare, "iLiveShare");
        jceDisplayer.display(this.iVideoShare, "iVideoShare");
        jceDisplayer.display(this.sGameName, "sGameName");
        jceDisplayer.display(this.iInfluenceValue, "iInfluenceValue");
        jceDisplayer.display(this.sText, "sText");
        jceDisplayer.display(this.sJumpUrl, "sJumpUrl");
        jceDisplayer.display(this.lGiftCount, "lGiftCount");
    }
}
