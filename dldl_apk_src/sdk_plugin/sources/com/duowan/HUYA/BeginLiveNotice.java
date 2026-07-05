package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BeginLiveNotice extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static Map<String, String> cache_mMiscInfo;
    static ArrayList<String> cache_vCdnList;
    static ArrayList<MultiStreamInfo> cache_vMultiStreamInfo;
    static ArrayList<StreamInfo> cache_vStreamInfo;
    public long lPresenterUid = 0;
    public int iGameId = 0;
    public String sGameName = "";
    public int iRandomRange = 0;
    public int iStreamType = 0;
    public ArrayList<StreamInfo> vStreamInfo = null;
    public ArrayList<String> vCdnList = null;
    public long lLiveId = 0;
    public int iPCDefaultBitRate = 0;
    public int iWebDefaultBitRate = 0;
    public int iMobileDefaultBitRate = 0;
    public long lMultiStreamFlag = 0;
    public String sNick = "";
    public long lYYId = 0;
    public long lAttendeeCount = 0;
    public int iCodecType = 0;
    public int iScreenType = 0;
    public ArrayList<MultiStreamInfo> vMultiStreamInfo = null;
    public String sLiveDesc = "";
    public long lLiveCompatibleFlag = 0;
    public String sAvatarUrl = "";
    public int iSourceType = 0;
    public String sSubchannelName = "";
    public String sVideoCaptureUrl = "";
    public int iStartTime = 0;
    public long lChannelId = 0;
    public long lSubChannelId = 0;
    public String sLocation = "";
    public int iCdnPolicyLevel = 0;
    public int iGameType = 0;
    public Map<String, String> mMiscInfo = null;
    public int iShortChannel = 0;
    public int iRoomId = 0;
    public int bIsRoomSecret = 0;
    public int iHashPolicy = 0;
    public long lSignChannel = 0;
    public int iMobileWifiDefaultBitRate = 0;
    public int iEnableAutoBitRate = 0;

    public String className() {
        return "HUYA.BeginLiveNotice";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.BeginLiveNotice";
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

    public int getIRandomRange() {
        return this.iRandomRange;
    }

    public void setIRandomRange(int i) {
        this.iRandomRange = i;
    }

    public int getIStreamType() {
        return this.iStreamType;
    }

    public void setIStreamType(int i) {
        this.iStreamType = i;
    }

    public ArrayList<StreamInfo> getVStreamInfo() {
        return this.vStreamInfo;
    }

    public void setVStreamInfo(ArrayList<StreamInfo> arrayList) {
        this.vStreamInfo = arrayList;
    }

    public ArrayList<String> getVCdnList() {
        return this.vCdnList;
    }

    public void setVCdnList(ArrayList<String> arrayList) {
        this.vCdnList = arrayList;
    }

    public long getLLiveId() {
        return this.lLiveId;
    }

    public void setLLiveId(long j) {
        this.lLiveId = j;
    }

    public int getIPCDefaultBitRate() {
        return this.iPCDefaultBitRate;
    }

    public void setIPCDefaultBitRate(int i) {
        this.iPCDefaultBitRate = i;
    }

    public int getIWebDefaultBitRate() {
        return this.iWebDefaultBitRate;
    }

    public void setIWebDefaultBitRate(int i) {
        this.iWebDefaultBitRate = i;
    }

    public int getIMobileDefaultBitRate() {
        return this.iMobileDefaultBitRate;
    }

    public void setIMobileDefaultBitRate(int i) {
        this.iMobileDefaultBitRate = i;
    }

    public long getLMultiStreamFlag() {
        return this.lMultiStreamFlag;
    }

    public void setLMultiStreamFlag(long j) {
        this.lMultiStreamFlag = j;
    }

    public String getSNick() {
        return this.sNick;
    }

    public void setSNick(String str) {
        this.sNick = str;
    }

    public long getLYYId() {
        return this.lYYId;
    }

    public void setLYYId(long j) {
        this.lYYId = j;
    }

    public long getLAttendeeCount() {
        return this.lAttendeeCount;
    }

    public void setLAttendeeCount(long j) {
        this.lAttendeeCount = j;
    }

    public int getICodecType() {
        return this.iCodecType;
    }

    public void setICodecType(int i) {
        this.iCodecType = i;
    }

    public int getIScreenType() {
        return this.iScreenType;
    }

    public void setIScreenType(int i) {
        this.iScreenType = i;
    }

    public ArrayList<MultiStreamInfo> getVMultiStreamInfo() {
        return this.vMultiStreamInfo;
    }

    public void setVMultiStreamInfo(ArrayList<MultiStreamInfo> arrayList) {
        this.vMultiStreamInfo = arrayList;
    }

    public String getSLiveDesc() {
        return this.sLiveDesc;
    }

    public void setSLiveDesc(String str) {
        this.sLiveDesc = str;
    }

    public long getLLiveCompatibleFlag() {
        return this.lLiveCompatibleFlag;
    }

    public void setLLiveCompatibleFlag(long j) {
        this.lLiveCompatibleFlag = j;
    }

    public String getSAvatarUrl() {
        return this.sAvatarUrl;
    }

    public void setSAvatarUrl(String str) {
        this.sAvatarUrl = str;
    }

    public int getISourceType() {
        return this.iSourceType;
    }

    public void setISourceType(int i) {
        this.iSourceType = i;
    }

    public String getSSubchannelName() {
        return this.sSubchannelName;
    }

    public void setSSubchannelName(String str) {
        this.sSubchannelName = str;
    }

    public String getSVideoCaptureUrl() {
        return this.sVideoCaptureUrl;
    }

    public void setSVideoCaptureUrl(String str) {
        this.sVideoCaptureUrl = str;
    }

    public int getIStartTime() {
        return this.iStartTime;
    }

    public void setIStartTime(int i) {
        this.iStartTime = i;
    }

    public long getLChannelId() {
        return this.lChannelId;
    }

    public void setLChannelId(long j) {
        this.lChannelId = j;
    }

    public long getLSubChannelId() {
        return this.lSubChannelId;
    }

    public void setLSubChannelId(long j) {
        this.lSubChannelId = j;
    }

    public String getSLocation() {
        return this.sLocation;
    }

    public void setSLocation(String str) {
        this.sLocation = str;
    }

    public int getICdnPolicyLevel() {
        return this.iCdnPolicyLevel;
    }

    public void setICdnPolicyLevel(int i) {
        this.iCdnPolicyLevel = i;
    }

    public int getIGameType() {
        return this.iGameType;
    }

    public void setIGameType(int i) {
        this.iGameType = i;
    }

    public Map<String, String> getMMiscInfo() {
        return this.mMiscInfo;
    }

    public void setMMiscInfo(Map<String, String> map) {
        this.mMiscInfo = map;
    }

    public int getIShortChannel() {
        return this.iShortChannel;
    }

    public void setIShortChannel(int i) {
        this.iShortChannel = i;
    }

    public int getIRoomId() {
        return this.iRoomId;
    }

    public void setIRoomId(int i) {
        this.iRoomId = i;
    }

    public int getBIsRoomSecret() {
        return this.bIsRoomSecret;
    }

    public void setBIsRoomSecret(int i) {
        this.bIsRoomSecret = i;
    }

    public int getIHashPolicy() {
        return this.iHashPolicy;
    }

    public void setIHashPolicy(int i) {
        this.iHashPolicy = i;
    }

    public long getLSignChannel() {
        return this.lSignChannel;
    }

    public void setLSignChannel(long j) {
        this.lSignChannel = j;
    }

    public int getIMobileWifiDefaultBitRate() {
        return this.iMobileWifiDefaultBitRate;
    }

    public void setIMobileWifiDefaultBitRate(int i) {
        this.iMobileWifiDefaultBitRate = i;
    }

    public int getIEnableAutoBitRate() {
        return this.iEnableAutoBitRate;
    }

    public void setIEnableAutoBitRate(int i) {
        this.iEnableAutoBitRate = i;
    }

    public BeginLiveNotice() {
        setLPresenterUid(0L);
        setIGameId(this.iGameId);
        setSGameName(this.sGameName);
        setIRandomRange(this.iRandomRange);
        setIStreamType(this.iStreamType);
        setVStreamInfo(this.vStreamInfo);
        setVCdnList(this.vCdnList);
        setLLiveId(this.lLiveId);
        setIPCDefaultBitRate(this.iPCDefaultBitRate);
        setIWebDefaultBitRate(this.iWebDefaultBitRate);
        setIMobileDefaultBitRate(this.iMobileDefaultBitRate);
        setLMultiStreamFlag(this.lMultiStreamFlag);
        setSNick(this.sNick);
        setLYYId(this.lYYId);
        setLAttendeeCount(this.lAttendeeCount);
        setICodecType(this.iCodecType);
        setIScreenType(this.iScreenType);
        setVMultiStreamInfo(this.vMultiStreamInfo);
        setSLiveDesc(this.sLiveDesc);
        setLLiveCompatibleFlag(this.lLiveCompatibleFlag);
        setSAvatarUrl(this.sAvatarUrl);
        setISourceType(this.iSourceType);
        setSSubchannelName(this.sSubchannelName);
        setSVideoCaptureUrl(this.sVideoCaptureUrl);
        setIStartTime(this.iStartTime);
        setLChannelId(this.lChannelId);
        setLSubChannelId(this.lSubChannelId);
        setSLocation(this.sLocation);
        setICdnPolicyLevel(this.iCdnPolicyLevel);
        setIGameType(this.iGameType);
        setMMiscInfo(this.mMiscInfo);
        setIShortChannel(this.iShortChannel);
        setIRoomId(this.iRoomId);
        setBIsRoomSecret(this.bIsRoomSecret);
        setIHashPolicy(this.iHashPolicy);
        setLSignChannel(this.lSignChannel);
        setIMobileWifiDefaultBitRate(this.iMobileWifiDefaultBitRate);
        setIEnableAutoBitRate(this.iEnableAutoBitRate);
    }

    public BeginLiveNotice(long j, int i, String str, int i2, int i3, ArrayList<StreamInfo> arrayList, ArrayList<String> arrayList2, long j2, int i4, int i5, int i6, long j3, String str2, long j4, long j5, int i7, int i8, ArrayList<MultiStreamInfo> arrayList3, String str3, long j6, String str4, int i9, String str5, String str6, int i10, long j7, long j8, String str7, int i11, int i12, Map<String, String> map, int i13, int i14, int i15, int i16, long j9, int i17, int i18) {
        setLPresenterUid(j);
        setIGameId(i);
        setSGameName(str);
        setIRandomRange(i2);
        setIStreamType(i3);
        setVStreamInfo(arrayList);
        setVCdnList(arrayList2);
        setLLiveId(j2);
        setIPCDefaultBitRate(i4);
        setIWebDefaultBitRate(i5);
        setIMobileDefaultBitRate(i6);
        setLMultiStreamFlag(j3);
        setSNick(str2);
        setLYYId(j4);
        setLAttendeeCount(j5);
        setICodecType(i7);
        setIScreenType(i8);
        setVMultiStreamInfo(arrayList3);
        setSLiveDesc(str3);
        setLLiveCompatibleFlag(j6);
        setSAvatarUrl(str4);
        setISourceType(i9);
        setSSubchannelName(str5);
        setSVideoCaptureUrl(str6);
        setIStartTime(i10);
        setLChannelId(j7);
        setLSubChannelId(j8);
        setSLocation(str7);
        setICdnPolicyLevel(i11);
        setIGameType(i12);
        setMMiscInfo(map);
        setIShortChannel(i13);
        setIRoomId(i14);
        setBIsRoomSecret(i15);
        setIHashPolicy(i16);
        setLSignChannel(j9);
        setIMobileWifiDefaultBitRate(i17);
        setIEnableAutoBitRate(i18);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BeginLiveNotice beginLiveNotice = (BeginLiveNotice) obj;
        return JceUtil.equals(this.lPresenterUid, beginLiveNotice.lPresenterUid) && JceUtil.equals(this.iGameId, beginLiveNotice.iGameId) && JceUtil.equals(this.sGameName, beginLiveNotice.sGameName) && JceUtil.equals(this.iRandomRange, beginLiveNotice.iRandomRange) && JceUtil.equals(this.iStreamType, beginLiveNotice.iStreamType) && JceUtil.equals(this.vStreamInfo, beginLiveNotice.vStreamInfo) && JceUtil.equals(this.vCdnList, beginLiveNotice.vCdnList) && JceUtil.equals(this.lLiveId, beginLiveNotice.lLiveId) && JceUtil.equals(this.iPCDefaultBitRate, beginLiveNotice.iPCDefaultBitRate) && JceUtil.equals(this.iWebDefaultBitRate, beginLiveNotice.iWebDefaultBitRate) && JceUtil.equals(this.iMobileDefaultBitRate, beginLiveNotice.iMobileDefaultBitRate) && JceUtil.equals(this.lMultiStreamFlag, beginLiveNotice.lMultiStreamFlag) && JceUtil.equals(this.sNick, beginLiveNotice.sNick) && JceUtil.equals(this.lYYId, beginLiveNotice.lYYId) && JceUtil.equals(this.lAttendeeCount, beginLiveNotice.lAttendeeCount) && JceUtil.equals(this.iCodecType, beginLiveNotice.iCodecType) && JceUtil.equals(this.iScreenType, beginLiveNotice.iScreenType) && JceUtil.equals(this.vMultiStreamInfo, beginLiveNotice.vMultiStreamInfo) && JceUtil.equals(this.sLiveDesc, beginLiveNotice.sLiveDesc) && JceUtil.equals(this.lLiveCompatibleFlag, beginLiveNotice.lLiveCompatibleFlag) && JceUtil.equals(this.sAvatarUrl, beginLiveNotice.sAvatarUrl) && JceUtil.equals(this.iSourceType, beginLiveNotice.iSourceType) && JceUtil.equals(this.sSubchannelName, beginLiveNotice.sSubchannelName) && JceUtil.equals(this.sVideoCaptureUrl, beginLiveNotice.sVideoCaptureUrl) && JceUtil.equals(this.iStartTime, beginLiveNotice.iStartTime) && JceUtil.equals(this.lChannelId, beginLiveNotice.lChannelId) && JceUtil.equals(this.lSubChannelId, beginLiveNotice.lSubChannelId) && JceUtil.equals(this.sLocation, beginLiveNotice.sLocation) && JceUtil.equals(this.iCdnPolicyLevel, beginLiveNotice.iCdnPolicyLevel) && JceUtil.equals(this.iGameType, beginLiveNotice.iGameType) && JceUtil.equals(this.mMiscInfo, beginLiveNotice.mMiscInfo) && JceUtil.equals(this.iShortChannel, beginLiveNotice.iShortChannel) && JceUtil.equals(this.iRoomId, beginLiveNotice.iRoomId) && JceUtil.equals(this.bIsRoomSecret, beginLiveNotice.bIsRoomSecret) && JceUtil.equals(this.iHashPolicy, beginLiveNotice.iHashPolicy) && JceUtil.equals(this.lSignChannel, beginLiveNotice.lSignChannel) && JceUtil.equals(this.iMobileWifiDefaultBitRate, beginLiveNotice.iMobileWifiDefaultBitRate) && JceUtil.equals(this.iEnableAutoBitRate, beginLiveNotice.iEnableAutoBitRate);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.lPresenterUid), JceUtil.hashCode(this.iGameId), JceUtil.hashCode(this.sGameName), JceUtil.hashCode(this.iRandomRange), JceUtil.hashCode(this.iStreamType), JceUtil.hashCode(this.vStreamInfo), JceUtil.hashCode(this.vCdnList), JceUtil.hashCode(this.lLiveId), JceUtil.hashCode(this.iPCDefaultBitRate), JceUtil.hashCode(this.iWebDefaultBitRate), JceUtil.hashCode(this.iMobileDefaultBitRate), JceUtil.hashCode(this.lMultiStreamFlag), JceUtil.hashCode(this.sNick), JceUtil.hashCode(this.lYYId), JceUtil.hashCode(this.lAttendeeCount), JceUtil.hashCode(this.iCodecType), JceUtil.hashCode(this.iScreenType), JceUtil.hashCode(this.vMultiStreamInfo), JceUtil.hashCode(this.sLiveDesc), JceUtil.hashCode(this.lLiveCompatibleFlag), JceUtil.hashCode(this.sAvatarUrl), JceUtil.hashCode(this.iSourceType), JceUtil.hashCode(this.sSubchannelName), JceUtil.hashCode(this.sVideoCaptureUrl), JceUtil.hashCode(this.iStartTime), JceUtil.hashCode(this.lChannelId), JceUtil.hashCode(this.lSubChannelId), JceUtil.hashCode(this.sLocation), JceUtil.hashCode(this.iCdnPolicyLevel), JceUtil.hashCode(this.iGameType), JceUtil.hashCode(this.mMiscInfo), JceUtil.hashCode(this.iShortChannel), JceUtil.hashCode(this.iRoomId), JceUtil.hashCode(this.bIsRoomSecret), JceUtil.hashCode(this.iHashPolicy), JceUtil.hashCode(this.lSignChannel), JceUtil.hashCode(this.iMobileWifiDefaultBitRate), JceUtil.hashCode(this.iEnableAutoBitRate)});
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
        jceOutputStream.write(this.iRandomRange, 3);
        jceOutputStream.write(this.iStreamType, 4);
        ArrayList<StreamInfo> arrayList = this.vStreamInfo;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 5);
        }
        ArrayList<String> arrayList2 = this.vCdnList;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 6);
        }
        jceOutputStream.write(this.lLiveId, 7);
        jceOutputStream.write(this.iPCDefaultBitRate, 8);
        jceOutputStream.write(this.iWebDefaultBitRate, 9);
        jceOutputStream.write(this.iMobileDefaultBitRate, 10);
        jceOutputStream.write(this.lMultiStreamFlag, 11);
        String str2 = this.sNick;
        if (str2 != null) {
            jceOutputStream.write(str2, 12);
        }
        jceOutputStream.write(this.lYYId, 13);
        jceOutputStream.write(this.lAttendeeCount, 14);
        jceOutputStream.write(this.iCodecType, 15);
        jceOutputStream.write(this.iScreenType, 16);
        ArrayList<MultiStreamInfo> arrayList3 = this.vMultiStreamInfo;
        if (arrayList3 != null) {
            jceOutputStream.write((Collection) arrayList3, 17);
        }
        String str3 = this.sLiveDesc;
        if (str3 != null) {
            jceOutputStream.write(str3, 18);
        }
        jceOutputStream.write(this.lLiveCompatibleFlag, 19);
        String str4 = this.sAvatarUrl;
        if (str4 != null) {
            jceOutputStream.write(str4, 20);
        }
        jceOutputStream.write(this.iSourceType, 21);
        String str5 = this.sSubchannelName;
        if (str5 != null) {
            jceOutputStream.write(str5, 22);
        }
        String str6 = this.sVideoCaptureUrl;
        if (str6 != null) {
            jceOutputStream.write(str6, 23);
        }
        jceOutputStream.write(this.iStartTime, 24);
        jceOutputStream.write(this.lChannelId, 25);
        jceOutputStream.write(this.lSubChannelId, 26);
        String str7 = this.sLocation;
        if (str7 != null) {
            jceOutputStream.write(str7, 27);
        }
        jceOutputStream.write(this.iCdnPolicyLevel, 28);
        jceOutputStream.write(this.iGameType, 29);
        Map<String, String> map = this.mMiscInfo;
        if (map != null) {
            jceOutputStream.write((Map) map, 30);
        }
        jceOutputStream.write(this.iShortChannel, 31);
        jceOutputStream.write(this.iRoomId, 32);
        jceOutputStream.write(this.bIsRoomSecret, 33);
        jceOutputStream.write(this.iHashPolicy, 34);
        jceOutputStream.write(this.lSignChannel, 35);
        jceOutputStream.write(this.iMobileWifiDefaultBitRate, 36);
        jceOutputStream.write(this.iEnableAutoBitRate, 37);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLPresenterUid(jceInputStream.read(this.lPresenterUid, 0, false));
        setIGameId(jceInputStream.read(this.iGameId, 1, false));
        setSGameName(jceInputStream.readString(2, false));
        setIRandomRange(jceInputStream.read(this.iRandomRange, 3, false));
        setIStreamType(jceInputStream.read(this.iStreamType, 4, false));
        if (cache_vStreamInfo == null) {
            cache_vStreamInfo = new ArrayList<>();
            cache_vStreamInfo.add(new StreamInfo());
        }
        setVStreamInfo((ArrayList) jceInputStream.read(cache_vStreamInfo, 5, false));
        if (cache_vCdnList == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            cache_vCdnList = arrayList;
            arrayList.add("");
        }
        setVCdnList((ArrayList) jceInputStream.read(cache_vCdnList, 6, false));
        setLLiveId(jceInputStream.read(this.lLiveId, 7, false));
        setIPCDefaultBitRate(jceInputStream.read(this.iPCDefaultBitRate, 8, false));
        setIWebDefaultBitRate(jceInputStream.read(this.iWebDefaultBitRate, 9, false));
        setIMobileDefaultBitRate(jceInputStream.read(this.iMobileDefaultBitRate, 10, false));
        setLMultiStreamFlag(jceInputStream.read(this.lMultiStreamFlag, 11, false));
        setSNick(jceInputStream.readString(12, false));
        setLYYId(jceInputStream.read(this.lYYId, 13, false));
        setLAttendeeCount(jceInputStream.read(this.lAttendeeCount, 14, false));
        setICodecType(jceInputStream.read(this.iCodecType, 15, false));
        setIScreenType(jceInputStream.read(this.iScreenType, 16, false));
        if (cache_vMultiStreamInfo == null) {
            cache_vMultiStreamInfo = new ArrayList<>();
            cache_vMultiStreamInfo.add(new MultiStreamInfo());
        }
        setVMultiStreamInfo((ArrayList) jceInputStream.read(cache_vMultiStreamInfo, 17, false));
        setSLiveDesc(jceInputStream.readString(18, false));
        setLLiveCompatibleFlag(jceInputStream.read(this.lLiveCompatibleFlag, 19, false));
        setSAvatarUrl(jceInputStream.readString(20, false));
        setISourceType(jceInputStream.read(this.iSourceType, 21, false));
        setSSubchannelName(jceInputStream.readString(22, false));
        setSVideoCaptureUrl(jceInputStream.readString(23, false));
        setIStartTime(jceInputStream.read(this.iStartTime, 24, false));
        setLChannelId(jceInputStream.read(this.lChannelId, 25, false));
        setLSubChannelId(jceInputStream.read(this.lSubChannelId, 26, false));
        setSLocation(jceInputStream.readString(27, false));
        setICdnPolicyLevel(jceInputStream.read(this.iCdnPolicyLevel, 28, false));
        setIGameType(jceInputStream.read(this.iGameType, 29, false));
        if (cache_mMiscInfo == null) {
            HashMap map = new HashMap();
            cache_mMiscInfo = map;
            map.put("", "");
        }
        setMMiscInfo((Map) jceInputStream.read(cache_mMiscInfo, 30, false));
        setIShortChannel(jceInputStream.read(this.iShortChannel, 31, false));
        setIRoomId(jceInputStream.read(this.iRoomId, 32, false));
        setBIsRoomSecret(jceInputStream.read(this.bIsRoomSecret, 33, false));
        setIHashPolicy(jceInputStream.read(this.iHashPolicy, 34, false));
        setLSignChannel(jceInputStream.read(this.lSignChannel, 35, false));
        setIMobileWifiDefaultBitRate(jceInputStream.read(this.iMobileWifiDefaultBitRate, 36, false));
        setIEnableAutoBitRate(jceInputStream.read(this.iEnableAutoBitRate, 37, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lPresenterUid, "lPresenterUid");
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display(this.sGameName, "sGameName");
        jceDisplayer.display(this.iRandomRange, "iRandomRange");
        jceDisplayer.display(this.iStreamType, "iStreamType");
        jceDisplayer.display((Collection) this.vStreamInfo, "vStreamInfo");
        jceDisplayer.display((Collection) this.vCdnList, "vCdnList");
        jceDisplayer.display(this.lLiveId, "lLiveId");
        jceDisplayer.display(this.iPCDefaultBitRate, "iPCDefaultBitRate");
        jceDisplayer.display(this.iWebDefaultBitRate, "iWebDefaultBitRate");
        jceDisplayer.display(this.iMobileDefaultBitRate, "iMobileDefaultBitRate");
        jceDisplayer.display(this.lMultiStreamFlag, "lMultiStreamFlag");
        jceDisplayer.display(this.sNick, "sNick");
        jceDisplayer.display(this.lYYId, "lYYId");
        jceDisplayer.display(this.lAttendeeCount, "lAttendeeCount");
        jceDisplayer.display(this.iCodecType, "iCodecType");
        jceDisplayer.display(this.iScreenType, "iScreenType");
        jceDisplayer.display((Collection) this.vMultiStreamInfo, "vMultiStreamInfo");
        jceDisplayer.display(this.sLiveDesc, "sLiveDesc");
        jceDisplayer.display(this.lLiveCompatibleFlag, "lLiveCompatibleFlag");
        jceDisplayer.display(this.sAvatarUrl, "sAvatarUrl");
        jceDisplayer.display(this.iSourceType, "iSourceType");
        jceDisplayer.display(this.sSubchannelName, "sSubchannelName");
        jceDisplayer.display(this.sVideoCaptureUrl, "sVideoCaptureUrl");
        jceDisplayer.display(this.iStartTime, "iStartTime");
        jceDisplayer.display(this.lChannelId, "lChannelId");
        jceDisplayer.display(this.lSubChannelId, "lSubChannelId");
        jceDisplayer.display(this.sLocation, "sLocation");
        jceDisplayer.display(this.iCdnPolicyLevel, "iCdnPolicyLevel");
        jceDisplayer.display(this.iGameType, "iGameType");
        jceDisplayer.display((Map) this.mMiscInfo, "mMiscInfo");
        jceDisplayer.display(this.iShortChannel, "iShortChannel");
        jceDisplayer.display(this.iRoomId, "iRoomId");
        jceDisplayer.display(this.bIsRoomSecret, "bIsRoomSecret");
        jceDisplayer.display(this.iHashPolicy, "iHashPolicy");
        jceDisplayer.display(this.lSignChannel, "lSignChannel");
        jceDisplayer.display(this.iMobileWifiDefaultBitRate, "iMobileWifiDefaultBitRate");
        jceDisplayer.display(this.iEnableAutoBitRate, "iEnableAutoBitRate");
    }
}
