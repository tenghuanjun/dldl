package com.duowan.HUYA;

import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GameLiveInfo extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static int cache_eGender;
    static ArrayList<GameLiveTag> cache_vGameTags;
    static ArrayList<GameLiveTag> cache_vPresenterTags;
    public long lLiveId = 0;
    public long lUid = 0;
    public long lChannelId = 0;
    public int iShortChannel = 0;
    public long lSubchannel = 0;
    public String sSubchannelName = "";
    public int iGameId = 0;
    public String sGameName = "";
    public int iAttendeeCount = 0;
    public int eGender = EGender.MALE.value();
    public int iAid = 0;
    public String sNick = "";
    public String sAvatarUrl = "";
    public int iStartTime = 0;
    public int iEndTime = 0;
    public int iSourceType = 0;
    public boolean bIsCameraOpen = false;
    public boolean bIsRoomSecret = false;
    public String sVideoCaptureUrl = "";
    public int iCdnAttendee = 0;
    public long lYYId = 0;
    public boolean bCertified = false;
    public int iRecType = 0;
    public long lSignChannel = 0;
    public String sLiveDesc = "";
    public int iLevel = 0;
    public String sGameShortName = "";
    public int iGameType = 0;
    public String sPrivateHost = "";
    public int iActivityCount = 0;
    public int iStreamType = 0;
    public int iBitRate = 0;
    public int iResolution = 0;
    public int iFrameRate = 0;
    public int iIsMultiStream = 0;
    public int iExeGameId = 0;
    public long lExp = 0;
    public String sReplayHls = "";
    public long lMultiStreamFlag = 0;
    public int iScreenType = 0;
    public int iChannelType = 0;
    public String sLocation = "";
    public int iCodecType = 0;
    public ArrayList<GameLiveTag> vPresenterTags = null;
    public ArrayList<GameLiveTag> vGameTags = null;
    public long lLiveCompatibleFlag = 0;
    public String sTraceId = "";
    public int iRoomId = 0;

    public String className() {
        return "HUYA.GameLiveInfo";
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GameLiveInfo";
    }

    public long getLLiveId() {
        return this.lLiveId;
    }

    public void setLLiveId(long j) {
        this.lLiveId = j;
    }

    public long getLUid() {
        return this.lUid;
    }

    public void setLUid(long j) {
        this.lUid = j;
    }

    public long getLChannelId() {
        return this.lChannelId;
    }

    public void setLChannelId(long j) {
        this.lChannelId = j;
    }

    public int getIShortChannel() {
        return this.iShortChannel;
    }

    public void setIShortChannel(int i) {
        this.iShortChannel = i;
    }

    public long getLSubchannel() {
        return this.lSubchannel;
    }

    public void setLSubchannel(long j) {
        this.lSubchannel = j;
    }

    public String getSSubchannelName() {
        return this.sSubchannelName;
    }

    public void setSSubchannelName(String str) {
        this.sSubchannelName = str;
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

    public int getIAttendeeCount() {
        return this.iAttendeeCount;
    }

    public void setIAttendeeCount(int i) {
        this.iAttendeeCount = i;
    }

    public int getEGender() {
        return this.eGender;
    }

    public void setEGender(int i) {
        this.eGender = i;
    }

    public int getIAid() {
        return this.iAid;
    }

    public void setIAid(int i) {
        this.iAid = i;
    }

    public String getSNick() {
        return this.sNick;
    }

    public void setSNick(String str) {
        this.sNick = str;
    }

    public String getSAvatarUrl() {
        return this.sAvatarUrl;
    }

    public void setSAvatarUrl(String str) {
        this.sAvatarUrl = str;
    }

    public int getIStartTime() {
        return this.iStartTime;
    }

    public void setIStartTime(int i) {
        this.iStartTime = i;
    }

    public int getIEndTime() {
        return this.iEndTime;
    }

    public void setIEndTime(int i) {
        this.iEndTime = i;
    }

    public int getISourceType() {
        return this.iSourceType;
    }

    public void setISourceType(int i) {
        this.iSourceType = i;
    }

    public boolean getBIsCameraOpen() {
        return this.bIsCameraOpen;
    }

    public void setBIsCameraOpen(boolean z) {
        this.bIsCameraOpen = z;
    }

    public boolean getBIsRoomSecret() {
        return this.bIsRoomSecret;
    }

    public void setBIsRoomSecret(boolean z) {
        this.bIsRoomSecret = z;
    }

    public String getSVideoCaptureUrl() {
        return this.sVideoCaptureUrl;
    }

    public void setSVideoCaptureUrl(String str) {
        this.sVideoCaptureUrl = str;
    }

    public int getICdnAttendee() {
        return this.iCdnAttendee;
    }

    public void setICdnAttendee(int i) {
        this.iCdnAttendee = i;
    }

    public long getLYYId() {
        return this.lYYId;
    }

    public void setLYYId(long j) {
        this.lYYId = j;
    }

    public boolean getBCertified() {
        return this.bCertified;
    }

    public void setBCertified(boolean z) {
        this.bCertified = z;
    }

    public int getIRecType() {
        return this.iRecType;
    }

    public void setIRecType(int i) {
        this.iRecType = i;
    }

    public long getLSignChannel() {
        return this.lSignChannel;
    }

    public void setLSignChannel(long j) {
        this.lSignChannel = j;
    }

    public String getSLiveDesc() {
        return this.sLiveDesc;
    }

    public void setSLiveDesc(String str) {
        this.sLiveDesc = str;
    }

    public int getILevel() {
        return this.iLevel;
    }

    public void setILevel(int i) {
        this.iLevel = i;
    }

    public String getSGameShortName() {
        return this.sGameShortName;
    }

    public void setSGameShortName(String str) {
        this.sGameShortName = str;
    }

    public int getIGameType() {
        return this.iGameType;
    }

    public void setIGameType(int i) {
        this.iGameType = i;
    }

    public String getSPrivateHost() {
        return this.sPrivateHost;
    }

    public void setSPrivateHost(String str) {
        this.sPrivateHost = str;
    }

    public int getIActivityCount() {
        return this.iActivityCount;
    }

    public void setIActivityCount(int i) {
        this.iActivityCount = i;
    }

    public int getIStreamType() {
        return this.iStreamType;
    }

    public void setIStreamType(int i) {
        this.iStreamType = i;
    }

    public int getIBitRate() {
        return this.iBitRate;
    }

    public void setIBitRate(int i) {
        this.iBitRate = i;
    }

    public int getIResolution() {
        return this.iResolution;
    }

    public void setIResolution(int i) {
        this.iResolution = i;
    }

    public int getIFrameRate() {
        return this.iFrameRate;
    }

    public void setIFrameRate(int i) {
        this.iFrameRate = i;
    }

    public int getIIsMultiStream() {
        return this.iIsMultiStream;
    }

    public void setIIsMultiStream(int i) {
        this.iIsMultiStream = i;
    }

    public int getIExeGameId() {
        return this.iExeGameId;
    }

    public void setIExeGameId(int i) {
        this.iExeGameId = i;
    }

    public long getLExp() {
        return this.lExp;
    }

    public void setLExp(long j) {
        this.lExp = j;
    }

    public String getSReplayHls() {
        return this.sReplayHls;
    }

    public void setSReplayHls(String str) {
        this.sReplayHls = str;
    }

    public long getLMultiStreamFlag() {
        return this.lMultiStreamFlag;
    }

    public void setLMultiStreamFlag(long j) {
        this.lMultiStreamFlag = j;
    }

    public int getIScreenType() {
        return this.iScreenType;
    }

    public void setIScreenType(int i) {
        this.iScreenType = i;
    }

    public int getIChannelType() {
        return this.iChannelType;
    }

    public void setIChannelType(int i) {
        this.iChannelType = i;
    }

    public String getSLocation() {
        return this.sLocation;
    }

    public void setSLocation(String str) {
        this.sLocation = str;
    }

    public int getICodecType() {
        return this.iCodecType;
    }

    public void setICodecType(int i) {
        this.iCodecType = i;
    }

    public ArrayList<GameLiveTag> getVPresenterTags() {
        return this.vPresenterTags;
    }

    public void setVPresenterTags(ArrayList<GameLiveTag> arrayList) {
        this.vPresenterTags = arrayList;
    }

    public ArrayList<GameLiveTag> getVGameTags() {
        return this.vGameTags;
    }

    public void setVGameTags(ArrayList<GameLiveTag> arrayList) {
        this.vGameTags = arrayList;
    }

    public long getLLiveCompatibleFlag() {
        return this.lLiveCompatibleFlag;
    }

    public void setLLiveCompatibleFlag(long j) {
        this.lLiveCompatibleFlag = j;
    }

    public String getSTraceId() {
        return this.sTraceId;
    }

    public void setSTraceId(String str) {
        this.sTraceId = str;
    }

    public int getIRoomId() {
        return this.iRoomId;
    }

    public void setIRoomId(int i) {
        this.iRoomId = i;
    }

    public GameLiveInfo() {
        setLLiveId(this.lLiveId);
        setLUid(this.lUid);
        setLChannelId(this.lChannelId);
        setIShortChannel(this.iShortChannel);
        setLSubchannel(this.lSubchannel);
        setSSubchannelName(this.sSubchannelName);
        setIGameId(this.iGameId);
        setSGameName(this.sGameName);
        setIAttendeeCount(this.iAttendeeCount);
        setEGender(this.eGender);
        setIAid(this.iAid);
        setSNick(this.sNick);
        setSAvatarUrl(this.sAvatarUrl);
        setIStartTime(this.iStartTime);
        setIEndTime(this.iEndTime);
        setISourceType(this.iSourceType);
        setBIsCameraOpen(this.bIsCameraOpen);
        setBIsRoomSecret(this.bIsRoomSecret);
        setSVideoCaptureUrl(this.sVideoCaptureUrl);
        setICdnAttendee(this.iCdnAttendee);
        setLYYId(this.lYYId);
        setBCertified(this.bCertified);
        setIRecType(this.iRecType);
        setLSignChannel(this.lSignChannel);
        setSLiveDesc(this.sLiveDesc);
        setILevel(this.iLevel);
        setSGameShortName(this.sGameShortName);
        setIGameType(this.iGameType);
        setSPrivateHost(this.sPrivateHost);
        setIActivityCount(this.iActivityCount);
        setIStreamType(this.iStreamType);
        setIBitRate(this.iBitRate);
        setIResolution(this.iResolution);
        setIFrameRate(this.iFrameRate);
        setIIsMultiStream(this.iIsMultiStream);
        setIExeGameId(this.iExeGameId);
        setLExp(this.lExp);
        setSReplayHls(this.sReplayHls);
        setLMultiStreamFlag(this.lMultiStreamFlag);
        setIScreenType(this.iScreenType);
        setIChannelType(this.iChannelType);
        setSLocation(this.sLocation);
        setICodecType(this.iCodecType);
        setVPresenterTags(this.vPresenterTags);
        setVGameTags(this.vGameTags);
        setLLiveCompatibleFlag(this.lLiveCompatibleFlag);
        setSTraceId(this.sTraceId);
        setIRoomId(this.iRoomId);
    }

    public GameLiveInfo(long j, long j2, long j3, int i, long j4, String str, int i2, String str2, int i3, int i4, int i5, String str3, String str4, int i6, int i7, int i8, boolean z, boolean z2, String str5, int i9, long j5, boolean z3, int i10, long j6, String str6, int i11, String str7, int i12, String str8, int i13, int i14, int i15, int i16, int i17, int i18, int i19, long j7, String str9, long j8, int i20, int i21, String str10, int i22, ArrayList<GameLiveTag> arrayList, ArrayList<GameLiveTag> arrayList2, long j9, String str11, int i23) {
        setLLiveId(j);
        setLUid(j2);
        setLChannelId(j3);
        setIShortChannel(i);
        setLSubchannel(j4);
        setSSubchannelName(str);
        setIGameId(i2);
        setSGameName(str2);
        setIAttendeeCount(i3);
        setEGender(i4);
        setIAid(i5);
        setSNick(str3);
        setSAvatarUrl(str4);
        setIStartTime(i6);
        setIEndTime(i7);
        setISourceType(i8);
        setBIsCameraOpen(z);
        setBIsRoomSecret(z2);
        setSVideoCaptureUrl(str5);
        setICdnAttendee(i9);
        setLYYId(j5);
        setBCertified(z3);
        setIRecType(i10);
        setLSignChannel(j6);
        setSLiveDesc(str6);
        setILevel(i11);
        setSGameShortName(str7);
        setIGameType(i12);
        setSPrivateHost(str8);
        setIActivityCount(i13);
        setIStreamType(i14);
        setIBitRate(i15);
        setIResolution(i16);
        setIFrameRate(i17);
        setIIsMultiStream(i18);
        setIExeGameId(i19);
        setLExp(j7);
        setSReplayHls(str9);
        setLMultiStreamFlag(j8);
        setIScreenType(i20);
        setIChannelType(i21);
        setSLocation(str10);
        setICodecType(i22);
        setVPresenterTags(arrayList);
        setVGameTags(arrayList2);
        setLLiveCompatibleFlag(j9);
        setSTraceId(str11);
        setIRoomId(i23);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GameLiveInfo gameLiveInfo = (GameLiveInfo) obj;
        return JceUtil.equals(this.lLiveId, gameLiveInfo.lLiveId) && JceUtil.equals(this.lUid, gameLiveInfo.lUid) && JceUtil.equals(this.lChannelId, gameLiveInfo.lChannelId) && JceUtil.equals(this.iShortChannel, gameLiveInfo.iShortChannel) && JceUtil.equals(this.lSubchannel, gameLiveInfo.lSubchannel) && JceUtil.equals(this.sSubchannelName, gameLiveInfo.sSubchannelName) && JceUtil.equals(this.iGameId, gameLiveInfo.iGameId) && JceUtil.equals(this.sGameName, gameLiveInfo.sGameName) && JceUtil.equals(this.iAttendeeCount, gameLiveInfo.iAttendeeCount) && JceUtil.equals(this.eGender, gameLiveInfo.eGender) && JceUtil.equals(this.iAid, gameLiveInfo.iAid) && JceUtil.equals(this.sNick, gameLiveInfo.sNick) && JceUtil.equals(this.sAvatarUrl, gameLiveInfo.sAvatarUrl) && JceUtil.equals(this.iStartTime, gameLiveInfo.iStartTime) && JceUtil.equals(this.iEndTime, gameLiveInfo.iEndTime) && JceUtil.equals(this.iSourceType, gameLiveInfo.iSourceType) && JceUtil.equals(this.bIsCameraOpen, gameLiveInfo.bIsCameraOpen) && JceUtil.equals(this.bIsRoomSecret, gameLiveInfo.bIsRoomSecret) && JceUtil.equals(this.sVideoCaptureUrl, gameLiveInfo.sVideoCaptureUrl) && JceUtil.equals(this.iCdnAttendee, gameLiveInfo.iCdnAttendee) && JceUtil.equals(this.lYYId, gameLiveInfo.lYYId) && JceUtil.equals(this.bCertified, gameLiveInfo.bCertified) && JceUtil.equals(this.iRecType, gameLiveInfo.iRecType) && JceUtil.equals(this.lSignChannel, gameLiveInfo.lSignChannel) && JceUtil.equals(this.sLiveDesc, gameLiveInfo.sLiveDesc) && JceUtil.equals(this.iLevel, gameLiveInfo.iLevel) && JceUtil.equals(this.sGameShortName, gameLiveInfo.sGameShortName) && JceUtil.equals(this.iGameType, gameLiveInfo.iGameType) && JceUtil.equals(this.sPrivateHost, gameLiveInfo.sPrivateHost) && JceUtil.equals(this.iActivityCount, gameLiveInfo.iActivityCount) && JceUtil.equals(this.iStreamType, gameLiveInfo.iStreamType) && JceUtil.equals(this.iBitRate, gameLiveInfo.iBitRate) && JceUtil.equals(this.iResolution, gameLiveInfo.iResolution) && JceUtil.equals(this.iFrameRate, gameLiveInfo.iFrameRate) && JceUtil.equals(this.iIsMultiStream, gameLiveInfo.iIsMultiStream) && JceUtil.equals(this.iExeGameId, gameLiveInfo.iExeGameId) && JceUtil.equals(this.lExp, gameLiveInfo.lExp) && JceUtil.equals(this.sReplayHls, gameLiveInfo.sReplayHls) && JceUtil.equals(this.lMultiStreamFlag, gameLiveInfo.lMultiStreamFlag) && JceUtil.equals(this.iScreenType, gameLiveInfo.iScreenType) && JceUtil.equals(this.iChannelType, gameLiveInfo.iChannelType) && JceUtil.equals(this.sLocation, gameLiveInfo.sLocation) && JceUtil.equals(this.iCodecType, gameLiveInfo.iCodecType) && JceUtil.equals(this.vPresenterTags, gameLiveInfo.vPresenterTags) && JceUtil.equals(this.vGameTags, gameLiveInfo.vGameTags) && JceUtil.equals(this.lLiveCompatibleFlag, gameLiveInfo.lLiveCompatibleFlag) && JceUtil.equals(this.sTraceId, gameLiveInfo.sTraceId) && JceUtil.equals(this.iRoomId, gameLiveInfo.iRoomId);
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
        jceOutputStream.write(this.lLiveId, 0);
        jceOutputStream.write(this.lUid, 1);
        jceOutputStream.write(this.lChannelId, 2);
        jceOutputStream.write(this.iShortChannel, 3);
        jceOutputStream.write(this.lSubchannel, 4);
        String str = this.sSubchannelName;
        if (str != null) {
            jceOutputStream.write(str, 5);
        }
        jceOutputStream.write(this.iGameId, 6);
        String str2 = this.sGameName;
        if (str2 != null) {
            jceOutputStream.write(str2, 7);
        }
        jceOutputStream.write(this.iAttendeeCount, 8);
        jceOutputStream.write(this.eGender, 9);
        jceOutputStream.write(this.iAid, 10);
        String str3 = this.sNick;
        if (str3 != null) {
            jceOutputStream.write(str3, 11);
        }
        String str4 = this.sAvatarUrl;
        if (str4 != null) {
            jceOutputStream.write(str4, 12);
        }
        jceOutputStream.write(this.iStartTime, 13);
        jceOutputStream.write(this.iEndTime, 14);
        jceOutputStream.write(this.iSourceType, 15);
        jceOutputStream.write(this.bIsCameraOpen, 16);
        jceOutputStream.write(this.bIsRoomSecret, 17);
        String str5 = this.sVideoCaptureUrl;
        if (str5 != null) {
            jceOutputStream.write(str5, 18);
        }
        jceOutputStream.write(this.iCdnAttendee, 19);
        jceOutputStream.write(this.lYYId, 20);
        jceOutputStream.write(this.bCertified, 21);
        jceOutputStream.write(this.iRecType, 22);
        jceOutputStream.write(this.lSignChannel, 23);
        String str6 = this.sLiveDesc;
        if (str6 != null) {
            jceOutputStream.write(str6, 24);
        }
        jceOutputStream.write(this.iLevel, 25);
        String str7 = this.sGameShortName;
        if (str7 != null) {
            jceOutputStream.write(str7, 26);
        }
        jceOutputStream.write(this.iGameType, 27);
        String str8 = this.sPrivateHost;
        if (str8 != null) {
            jceOutputStream.write(str8, 28);
        }
        jceOutputStream.write(this.iActivityCount, 29);
        jceOutputStream.write(this.iStreamType, 30);
        jceOutputStream.write(this.iBitRate, 31);
        jceOutputStream.write(this.iResolution, 32);
        jceOutputStream.write(this.iFrameRate, 33);
        jceOutputStream.write(this.iIsMultiStream, 34);
        jceOutputStream.write(this.iExeGameId, 35);
        jceOutputStream.write(this.lExp, 36);
        String str9 = this.sReplayHls;
        if (str9 != null) {
            jceOutputStream.write(str9, 37);
        }
        jceOutputStream.write(this.lMultiStreamFlag, 38);
        jceOutputStream.write(this.iScreenType, 39);
        jceOutputStream.write(this.iChannelType, 40);
        String str10 = this.sLocation;
        if (str10 != null) {
            jceOutputStream.write(str10, 41);
        }
        jceOutputStream.write(this.iCodecType, 42);
        ArrayList<GameLiveTag> arrayList = this.vPresenterTags;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 43);
        }
        ArrayList<GameLiveTag> arrayList2 = this.vGameTags;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 44);
        }
        jceOutputStream.write(this.lLiveCompatibleFlag, 45);
        String str11 = this.sTraceId;
        if (str11 != null) {
            jceOutputStream.write(str11, 46);
        }
        jceOutputStream.write(this.iRoomId, 47);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setLLiveId(jceInputStream.read(this.lLiveId, 0, false));
        setLUid(jceInputStream.read(this.lUid, 1, false));
        setLChannelId(jceInputStream.read(this.lChannelId, 2, false));
        setIShortChannel(jceInputStream.read(this.iShortChannel, 3, false));
        setLSubchannel(jceInputStream.read(this.lSubchannel, 4, false));
        setSSubchannelName(jceInputStream.readString(5, false));
        setIGameId(jceInputStream.read(this.iGameId, 6, false));
        setSGameName(jceInputStream.readString(7, false));
        setIAttendeeCount(jceInputStream.read(this.iAttendeeCount, 8, false));
        setEGender(jceInputStream.read(this.eGender, 9, false));
        setIAid(jceInputStream.read(this.iAid, 10, false));
        setSNick(jceInputStream.readString(11, false));
        setSAvatarUrl(jceInputStream.readString(12, false));
        setIStartTime(jceInputStream.read(this.iStartTime, 13, false));
        setIEndTime(jceInputStream.read(this.iEndTime, 14, false));
        setISourceType(jceInputStream.read(this.iSourceType, 15, false));
        setBIsCameraOpen(jceInputStream.read(this.bIsCameraOpen, 16, false));
        setBIsRoomSecret(jceInputStream.read(this.bIsRoomSecret, 17, false));
        setSVideoCaptureUrl(jceInputStream.readString(18, false));
        setICdnAttendee(jceInputStream.read(this.iCdnAttendee, 19, false));
        setLYYId(jceInputStream.read(this.lYYId, 20, false));
        setBCertified(jceInputStream.read(this.bCertified, 21, false));
        setIRecType(jceInputStream.read(this.iRecType, 22, false));
        setLSignChannel(jceInputStream.read(this.lSignChannel, 23, false));
        setSLiveDesc(jceInputStream.readString(24, false));
        setILevel(jceInputStream.read(this.iLevel, 25, false));
        setSGameShortName(jceInputStream.readString(26, false));
        setIGameType(jceInputStream.read(this.iGameType, 27, false));
        setSPrivateHost(jceInputStream.readString(28, false));
        setIActivityCount(jceInputStream.read(this.iActivityCount, 29, false));
        setIStreamType(jceInputStream.read(this.iStreamType, 30, false));
        setIBitRate(jceInputStream.read(this.iBitRate, 31, false));
        setIResolution(jceInputStream.read(this.iResolution, 32, false));
        setIFrameRate(jceInputStream.read(this.iFrameRate, 33, false));
        setIIsMultiStream(jceInputStream.read(this.iIsMultiStream, 34, false));
        setIExeGameId(jceInputStream.read(this.iExeGameId, 35, false));
        setLExp(jceInputStream.read(this.lExp, 36, false));
        setSReplayHls(jceInputStream.readString(37, false));
        setLMultiStreamFlag(jceInputStream.read(this.lMultiStreamFlag, 38, false));
        setIScreenType(jceInputStream.read(this.iScreenType, 39, false));
        setIChannelType(jceInputStream.read(this.iChannelType, 40, false));
        setSLocation(jceInputStream.readString(41, false));
        setICodecType(jceInputStream.read(this.iCodecType, 42, false));
        if (cache_vPresenterTags == null) {
            cache_vPresenterTags = new ArrayList<>();
            cache_vPresenterTags.add(new GameLiveTag());
        }
        setVPresenterTags((ArrayList) jceInputStream.read(cache_vPresenterTags, 43, false));
        if (cache_vGameTags == null) {
            cache_vGameTags = new ArrayList<>();
            cache_vGameTags.add(new GameLiveTag());
        }
        setVGameTags((ArrayList) jceInputStream.read(cache_vGameTags, 44, false));
        setLLiveCompatibleFlag(jceInputStream.read(this.lLiveCompatibleFlag, 45, false));
        setSTraceId(jceInputStream.readString(46, false));
        setIRoomId(jceInputStream.read(this.iRoomId, 47, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.lLiveId, "lLiveId");
        jceDisplayer.display(this.lUid, "lUid");
        jceDisplayer.display(this.lChannelId, "lChannelId");
        jceDisplayer.display(this.iShortChannel, "iShortChannel");
        jceDisplayer.display(this.lSubchannel, "lSubchannel");
        jceDisplayer.display(this.sSubchannelName, "sSubchannelName");
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display(this.sGameName, "sGameName");
        jceDisplayer.display(this.iAttendeeCount, "iAttendeeCount");
        jceDisplayer.display(this.eGender, "eGender");
        jceDisplayer.display(this.iAid, "iAid");
        jceDisplayer.display(this.sNick, "sNick");
        jceDisplayer.display(this.sAvatarUrl, "sAvatarUrl");
        jceDisplayer.display(this.iStartTime, "iStartTime");
        jceDisplayer.display(this.iEndTime, "iEndTime");
        jceDisplayer.display(this.iSourceType, "iSourceType");
        jceDisplayer.display(this.bIsCameraOpen, "bIsCameraOpen");
        jceDisplayer.display(this.bIsRoomSecret, "bIsRoomSecret");
        jceDisplayer.display(this.sVideoCaptureUrl, "sVideoCaptureUrl");
        jceDisplayer.display(this.iCdnAttendee, "iCdnAttendee");
        jceDisplayer.display(this.lYYId, "lYYId");
        jceDisplayer.display(this.bCertified, "bCertified");
        jceDisplayer.display(this.iRecType, "iRecType");
        jceDisplayer.display(this.lSignChannel, "lSignChannel");
        jceDisplayer.display(this.sLiveDesc, "sLiveDesc");
        jceDisplayer.display(this.iLevel, "iLevel");
        jceDisplayer.display(this.sGameShortName, "sGameShortName");
        jceDisplayer.display(this.iGameType, "iGameType");
        jceDisplayer.display(this.sPrivateHost, "sPrivateHost");
        jceDisplayer.display(this.iActivityCount, "iActivityCount");
        jceDisplayer.display(this.iStreamType, "iStreamType");
        jceDisplayer.display(this.iBitRate, "iBitRate");
        jceDisplayer.display(this.iResolution, "iResolution");
        jceDisplayer.display(this.iFrameRate, "iFrameRate");
        jceDisplayer.display(this.iIsMultiStream, "iIsMultiStream");
        jceDisplayer.display(this.iExeGameId, "iExeGameId");
        jceDisplayer.display(this.lExp, "lExp");
        jceDisplayer.display(this.sReplayHls, "sReplayHls");
        jceDisplayer.display(this.lMultiStreamFlag, "lMultiStreamFlag");
        jceDisplayer.display(this.iScreenType, "iScreenType");
        jceDisplayer.display(this.iChannelType, "iChannelType");
        jceDisplayer.display(this.sLocation, "sLocation");
        jceDisplayer.display(this.iCodecType, "iCodecType");
        jceDisplayer.display((Collection) this.vPresenterTags, "vPresenterTags");
        jceDisplayer.display((Collection) this.vGameTags, "vGameTags");
        jceDisplayer.display(this.lLiveCompatibleFlag, "lLiveCompatibleFlag");
        jceDisplayer.display(this.sTraceId, "sTraceId");
        jceDisplayer.display(this.iRoomId, "iRoomId");
    }
}
