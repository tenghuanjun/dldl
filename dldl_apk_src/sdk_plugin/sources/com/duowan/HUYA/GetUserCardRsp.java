package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GetUserCardRsp extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static FansInfoDetail cache_tFansInfo;
    static GuardInfo cache_tGuardInfo;
    static NobleInfo cache_tNobleInfo;
    static UserBase cache_tUsrBase;
    public UserBase tUsrBase = null;
    public NobleInfo tNobleInfo = null;
    public GuardInfo tGuardInfo = null;
    public FansInfoDetail tFansInfo = null;
    public int iSubscribeStatus = 0;
    public int iSubscribedCount = 0;
    public String sLogoDecoUrl = "";
    public int iAge = 0;
    public String sUserPageUrl = "";
    public int iDiyId = 0;

    public String className() {
        return "HUYA.GetUserCardRsp";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GetUserCardRsp";
    }

    public UserBase getTUsrBase() {
        return this.tUsrBase;
    }

    public void setTUsrBase(UserBase userBase) {
        this.tUsrBase = userBase;
    }

    public NobleInfo getTNobleInfo() {
        return this.tNobleInfo;
    }

    public void setTNobleInfo(NobleInfo nobleInfo) {
        this.tNobleInfo = nobleInfo;
    }

    public GuardInfo getTGuardInfo() {
        return this.tGuardInfo;
    }

    public void setTGuardInfo(GuardInfo guardInfo) {
        this.tGuardInfo = guardInfo;
    }

    public FansInfoDetail getTFansInfo() {
        return this.tFansInfo;
    }

    public void setTFansInfo(FansInfoDetail fansInfoDetail) {
        this.tFansInfo = fansInfoDetail;
    }

    public int getISubscribeStatus() {
        return this.iSubscribeStatus;
    }

    public void setISubscribeStatus(int i) {
        this.iSubscribeStatus = i;
    }

    public int getISubscribedCount() {
        return this.iSubscribedCount;
    }

    public void setISubscribedCount(int i) {
        this.iSubscribedCount = i;
    }

    public String getSLogoDecoUrl() {
        return this.sLogoDecoUrl;
    }

    public void setSLogoDecoUrl(String str) {
        this.sLogoDecoUrl = str;
    }

    public int getIAge() {
        return this.iAge;
    }

    public void setIAge(int i) {
        this.iAge = i;
    }

    public String getSUserPageUrl() {
        return this.sUserPageUrl;
    }

    public void setSUserPageUrl(String str) {
        this.sUserPageUrl = str;
    }

    public int getIDiyId() {
        return this.iDiyId;
    }

    public void setIDiyId(int i) {
        this.iDiyId = i;
    }

    public GetUserCardRsp() {
        setTUsrBase(null);
        setTNobleInfo(this.tNobleInfo);
        setTGuardInfo(this.tGuardInfo);
        setTFansInfo(this.tFansInfo);
        setISubscribeStatus(this.iSubscribeStatus);
        setISubscribedCount(this.iSubscribedCount);
        setSLogoDecoUrl(this.sLogoDecoUrl);
        setIAge(this.iAge);
        setSUserPageUrl(this.sUserPageUrl);
        setIDiyId(this.iDiyId);
    }

    public GetUserCardRsp(UserBase userBase, NobleInfo nobleInfo, GuardInfo guardInfo, FansInfoDetail fansInfoDetail, int i, int i2, String str, int i3, String str2, int i4) {
        setTUsrBase(userBase);
        setTNobleInfo(nobleInfo);
        setTGuardInfo(guardInfo);
        setTFansInfo(fansInfoDetail);
        setISubscribeStatus(i);
        setISubscribedCount(i2);
        setSLogoDecoUrl(str);
        setIAge(i3);
        setSUserPageUrl(str2);
        setIDiyId(i4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GetUserCardRsp getUserCardRsp = (GetUserCardRsp) obj;
        return JceUtil.equals(this.tUsrBase, getUserCardRsp.tUsrBase) && JceUtil.equals(this.tNobleInfo, getUserCardRsp.tNobleInfo) && JceUtil.equals(this.tGuardInfo, getUserCardRsp.tGuardInfo) && JceUtil.equals(this.tFansInfo, getUserCardRsp.tFansInfo) && JceUtil.equals(this.iSubscribeStatus, getUserCardRsp.iSubscribeStatus) && JceUtil.equals(this.iSubscribedCount, getUserCardRsp.iSubscribedCount) && JceUtil.equals(this.sLogoDecoUrl, getUserCardRsp.sLogoDecoUrl) && JceUtil.equals(this.iAge, getUserCardRsp.iAge) && JceUtil.equals(this.sUserPageUrl, getUserCardRsp.sUserPageUrl) && JceUtil.equals(this.iDiyId, getUserCardRsp.iDiyId);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tUsrBase), JceUtil.hashCode(this.tNobleInfo), JceUtil.hashCode(this.tGuardInfo), JceUtil.hashCode(this.tFansInfo), JceUtil.hashCode(this.iSubscribeStatus), JceUtil.hashCode(this.iSubscribedCount), JceUtil.hashCode(this.sLogoDecoUrl), JceUtil.hashCode(this.iAge), JceUtil.hashCode(this.sUserPageUrl), JceUtil.hashCode(this.iDiyId)});
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
        UserBase userBase = this.tUsrBase;
        if (userBase != null) {
            jceOutputStream.write((JceStruct) userBase, 0);
        }
        NobleInfo nobleInfo = this.tNobleInfo;
        if (nobleInfo != null) {
            jceOutputStream.write((JceStruct) nobleInfo, 1);
        }
        GuardInfo guardInfo = this.tGuardInfo;
        if (guardInfo != null) {
            jceOutputStream.write((JceStruct) guardInfo, 2);
        }
        FansInfoDetail fansInfoDetail = this.tFansInfo;
        if (fansInfoDetail != null) {
            jceOutputStream.write((JceStruct) fansInfoDetail, 3);
        }
        jceOutputStream.write(this.iSubscribeStatus, 4);
        jceOutputStream.write(this.iSubscribedCount, 5);
        String str = this.sLogoDecoUrl;
        if (str != null) {
            jceOutputStream.write(str, 6);
        }
        jceOutputStream.write(this.iAge, 7);
        String str2 = this.sUserPageUrl;
        if (str2 != null) {
            jceOutputStream.write(str2, 8);
        }
        jceOutputStream.write(this.iDiyId, 9);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tUsrBase == null) {
            cache_tUsrBase = new UserBase();
        }
        setTUsrBase((UserBase) jceInputStream.read((JceStruct) cache_tUsrBase, 0, false));
        if (cache_tNobleInfo == null) {
            cache_tNobleInfo = new NobleInfo();
        }
        setTNobleInfo((NobleInfo) jceInputStream.read((JceStruct) cache_tNobleInfo, 1, false));
        if (cache_tGuardInfo == null) {
            cache_tGuardInfo = new GuardInfo();
        }
        setTGuardInfo((GuardInfo) jceInputStream.read((JceStruct) cache_tGuardInfo, 2, false));
        if (cache_tFansInfo == null) {
            cache_tFansInfo = new FansInfoDetail();
        }
        setTFansInfo((FansInfoDetail) jceInputStream.read((JceStruct) cache_tFansInfo, 3, false));
        setISubscribeStatus(jceInputStream.read(this.iSubscribeStatus, 4, false));
        setISubscribedCount(jceInputStream.read(this.iSubscribedCount, 5, false));
        setSLogoDecoUrl(jceInputStream.readString(6, false));
        setIAge(jceInputStream.read(this.iAge, 7, false));
        setSUserPageUrl(jceInputStream.readString(8, false));
        setIDiyId(jceInputStream.read(this.iDiyId, 9, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tUsrBase, "tUsrBase");
        jceDisplayer.display((JceStruct) this.tNobleInfo, "tNobleInfo");
        jceDisplayer.display((JceStruct) this.tGuardInfo, "tGuardInfo");
        jceDisplayer.display((JceStruct) this.tFansInfo, "tFansInfo");
        jceDisplayer.display(this.iSubscribeStatus, "iSubscribeStatus");
        jceDisplayer.display(this.iSubscribedCount, "iSubscribedCount");
        jceDisplayer.display(this.sLogoDecoUrl, "sLogoDecoUrl");
        jceDisplayer.display(this.iAge, "iAge");
        jceDisplayer.display(this.sUserPageUrl, "sUserPageUrl");
        jceDisplayer.display(this.iDiyId, "iDiyId");
    }
}
