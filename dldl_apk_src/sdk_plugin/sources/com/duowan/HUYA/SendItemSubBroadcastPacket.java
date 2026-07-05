package com.duowan.HUYA;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SendItemSubBroadcastPacket extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<SendItemSubBroadcastPacket> CREATOR = new Parcelable.Creator<SendItemSubBroadcastPacket>() { // from class: com.duowan.HUYA.SendItemSubBroadcastPacket.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SendItemSubBroadcastPacket createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            SendItemSubBroadcastPacket sendItemSubBroadcastPacket = new SendItemSubBroadcastPacket();
            sendItemSubBroadcastPacket.readFrom(jceInputStream);
            return sendItemSubBroadcastPacket;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SendItemSubBroadcastPacket[] newArray(int i) {
            return new SendItemSubBroadcastPacket[i];
        }
    };
    static StreamerNode cache_streamerInfo;
    static ItemEffectInfo cache_tEffectInfo;
    static NobleLevelInfo cache_tNobleLevel;
    static UserIdentityInfo cache_userInfo;
    static ArrayList<Long> cache_vExUid;
    public int iItemType = 0;
    public String strPayId = "";
    public int iItemCount = 0;
    public long lPresenterUid = 0;
    public long lSenderUid = 0;
    public String sPresenterNick = "";
    public String sSenderNick = "";
    public String sSendContent = "";
    public int iItemCountByGroup = 0;
    public int iItemGroup = 0;
    public int iSuperPupleLevel = 0;
    public int iComboScore = 0;
    public int iDisplayInfo = 0;
    public int iEffectType = 0;
    public String iSenderIcon = "";
    public String iPresenterIcon = "";
    public int iTemplateType = 0;
    public String sExpand = "";
    public boolean bBusi = false;
    public int iColorEffectType = 0;
    public String sPropsName = "";
    public short iAccpet = 0;
    public short iEventType = 0;
    public UserIdentityInfo userInfo = null;
    public long lRoomId = 0;
    public long lHomeOwnerUid = 0;
    public StreamerNode streamerInfo = null;
    public int iPayType = -1;
    public int iNobleLevel = 0;
    public NobleLevelInfo tNobleLevel = null;
    public ItemEffectInfo tEffectInfo = null;
    public ArrayList<Long> vExUid = null;
    public int iComboStatus = 0;
    public int iPidColorType = 0;

    public String className() {
        return "HUYA.SendItemSubBroadcastPacket";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SendItemSubBroadcastPacket";
    }

    public int getIItemType() {
        return this.iItemType;
    }

    public void setIItemType(int i) {
        this.iItemType = i;
    }

    public String getStrPayId() {
        return this.strPayId;
    }

    public void setStrPayId(String str) {
        this.strPayId = str;
    }

    public int getIItemCount() {
        return this.iItemCount;
    }

    public void setIItemCount(int i) {
        this.iItemCount = i;
    }

    public long getLPresenterUid() {
        return this.lPresenterUid;
    }

    public void setLPresenterUid(long j) {
        this.lPresenterUid = j;
    }

    public long getLSenderUid() {
        return this.lSenderUid;
    }

    public void setLSenderUid(long j) {
        this.lSenderUid = j;
    }

    public String getSPresenterNick() {
        return this.sPresenterNick;
    }

    public void setSPresenterNick(String str) {
        this.sPresenterNick = str;
    }

    public String getSSenderNick() {
        return this.sSenderNick;
    }

    public void setSSenderNick(String str) {
        this.sSenderNick = str;
    }

    public String getSSendContent() {
        return this.sSendContent;
    }

    public void setSSendContent(String str) {
        this.sSendContent = str;
    }

    public int getIItemCountByGroup() {
        return this.iItemCountByGroup;
    }

    public void setIItemCountByGroup(int i) {
        this.iItemCountByGroup = i;
    }

    public int getIItemGroup() {
        return this.iItemGroup;
    }

    public void setIItemGroup(int i) {
        this.iItemGroup = i;
    }

    public int getISuperPupleLevel() {
        return this.iSuperPupleLevel;
    }

    public void setISuperPupleLevel(int i) {
        this.iSuperPupleLevel = i;
    }

    public int getIComboScore() {
        return this.iComboScore;
    }

    public void setIComboScore(int i) {
        this.iComboScore = i;
    }

    public int getIDisplayInfo() {
        return this.iDisplayInfo;
    }

    public void setIDisplayInfo(int i) {
        this.iDisplayInfo = i;
    }

    public int getIEffectType() {
        return this.iEffectType;
    }

    public void setIEffectType(int i) {
        this.iEffectType = i;
    }

    public String getISenderIcon() {
        return this.iSenderIcon;
    }

    public void setISenderIcon(String str) {
        this.iSenderIcon = str;
    }

    public String getIPresenterIcon() {
        return this.iPresenterIcon;
    }

    public void setIPresenterIcon(String str) {
        this.iPresenterIcon = str;
    }

    public int getITemplateType() {
        return this.iTemplateType;
    }

    public void setITemplateType(int i) {
        this.iTemplateType = i;
    }

    public String getSExpand() {
        return this.sExpand;
    }

    public void setSExpand(String str) {
        this.sExpand = str;
    }

    public boolean getBBusi() {
        return this.bBusi;
    }

    public void setBBusi(boolean z) {
        this.bBusi = z;
    }

    public int getIColorEffectType() {
        return this.iColorEffectType;
    }

    public void setIColorEffectType(int i) {
        this.iColorEffectType = i;
    }

    public String getSPropsName() {
        return this.sPropsName;
    }

    public void setSPropsName(String str) {
        this.sPropsName = str;
    }

    public short getIAccpet() {
        return this.iAccpet;
    }

    public void setIAccpet(short s) {
        this.iAccpet = s;
    }

    public short getIEventType() {
        return this.iEventType;
    }

    public void setIEventType(short s) {
        this.iEventType = s;
    }

    public UserIdentityInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserIdentityInfo userIdentityInfo) {
        this.userInfo = userIdentityInfo;
    }

    public long getLRoomId() {
        return this.lRoomId;
    }

    public void setLRoomId(long j) {
        this.lRoomId = j;
    }

    public long getLHomeOwnerUid() {
        return this.lHomeOwnerUid;
    }

    public void setLHomeOwnerUid(long j) {
        this.lHomeOwnerUid = j;
    }

    public StreamerNode getStreamerInfo() {
        return this.streamerInfo;
    }

    public void setStreamerInfo(StreamerNode streamerNode) {
        this.streamerInfo = streamerNode;
    }

    public int getIPayType() {
        return this.iPayType;
    }

    public void setIPayType(int i) {
        this.iPayType = i;
    }

    public int getINobleLevel() {
        return this.iNobleLevel;
    }

    public void setINobleLevel(int i) {
        this.iNobleLevel = i;
    }

    public NobleLevelInfo getTNobleLevel() {
        return this.tNobleLevel;
    }

    public void setTNobleLevel(NobleLevelInfo nobleLevelInfo) {
        this.tNobleLevel = nobleLevelInfo;
    }

    public ItemEffectInfo getTEffectInfo() {
        return this.tEffectInfo;
    }

    public void setTEffectInfo(ItemEffectInfo itemEffectInfo) {
        this.tEffectInfo = itemEffectInfo;
    }

    public ArrayList<Long> getVExUid() {
        return this.vExUid;
    }

    public void setVExUid(ArrayList<Long> arrayList) {
        this.vExUid = arrayList;
    }

    public int getIComboStatus() {
        return this.iComboStatus;
    }

    public void setIComboStatus(int i) {
        this.iComboStatus = i;
    }

    public int getIPidColorType() {
        return this.iPidColorType;
    }

    public void setIPidColorType(int i) {
        this.iPidColorType = i;
    }

    public SendItemSubBroadcastPacket() {
        setIItemType(0);
        setStrPayId(this.strPayId);
        setIItemCount(this.iItemCount);
        setLPresenterUid(this.lPresenterUid);
        setLSenderUid(this.lSenderUid);
        setSPresenterNick(this.sPresenterNick);
        setSSenderNick(this.sSenderNick);
        setSSendContent(this.sSendContent);
        setIItemCountByGroup(this.iItemCountByGroup);
        setIItemGroup(this.iItemGroup);
        setISuperPupleLevel(this.iSuperPupleLevel);
        setIComboScore(this.iComboScore);
        setIDisplayInfo(this.iDisplayInfo);
        setIEffectType(this.iEffectType);
        setISenderIcon(this.iSenderIcon);
        setIPresenterIcon(this.iPresenterIcon);
        setITemplateType(this.iTemplateType);
        setSExpand(this.sExpand);
        setBBusi(this.bBusi);
        setIColorEffectType(this.iColorEffectType);
        setSPropsName(this.sPropsName);
        setIAccpet(this.iAccpet);
        setIEventType(this.iEventType);
        setUserInfo(this.userInfo);
        setLRoomId(this.lRoomId);
        setLHomeOwnerUid(this.lHomeOwnerUid);
        setStreamerInfo(this.streamerInfo);
        setIPayType(this.iPayType);
        setINobleLevel(this.iNobleLevel);
        setTNobleLevel(this.tNobleLevel);
        setTEffectInfo(this.tEffectInfo);
        setVExUid(this.vExUid);
        setIComboStatus(this.iComboStatus);
        setIPidColorType(this.iPidColorType);
    }

    public SendItemSubBroadcastPacket(int i, String str, int i2, long j, long j2, String str2, String str3, String str4, int i3, int i4, int i5, int i6, int i7, int i8, String str5, String str6, int i9, String str7, boolean z, int i10, String str8, short s, short s2, UserIdentityInfo userIdentityInfo, long j3, long j4, StreamerNode streamerNode, int i11, int i12, NobleLevelInfo nobleLevelInfo, ItemEffectInfo itemEffectInfo, ArrayList<Long> arrayList, int i13, int i14) {
        setIItemType(i);
        setStrPayId(str);
        setIItemCount(i2);
        setLPresenterUid(j);
        setLSenderUid(j2);
        setSPresenterNick(str2);
        setSSenderNick(str3);
        setSSendContent(str4);
        setIItemCountByGroup(i3);
        setIItemGroup(i4);
        setISuperPupleLevel(i5);
        setIComboScore(i6);
        setIDisplayInfo(i7);
        setIEffectType(i8);
        setISenderIcon(str5);
        setIPresenterIcon(str6);
        setITemplateType(i9);
        setSExpand(str7);
        setBBusi(z);
        setIColorEffectType(i10);
        setSPropsName(str8);
        setIAccpet(s);
        setIEventType(s2);
        setUserInfo(userIdentityInfo);
        setLRoomId(j3);
        setLHomeOwnerUid(j4);
        setStreamerInfo(streamerNode);
        setIPayType(i11);
        setINobleLevel(i12);
        setTNobleLevel(nobleLevelInfo);
        setTEffectInfo(itemEffectInfo);
        setVExUid(arrayList);
        setIComboStatus(i13);
        setIPidColorType(i14);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SendItemSubBroadcastPacket sendItemSubBroadcastPacket = (SendItemSubBroadcastPacket) obj;
        return JceUtil.equals(this.iItemType, sendItemSubBroadcastPacket.iItemType) && JceUtil.equals(this.strPayId, sendItemSubBroadcastPacket.strPayId) && JceUtil.equals(this.iItemCount, sendItemSubBroadcastPacket.iItemCount) && JceUtil.equals(this.lPresenterUid, sendItemSubBroadcastPacket.lPresenterUid) && JceUtil.equals(this.lSenderUid, sendItemSubBroadcastPacket.lSenderUid) && JceUtil.equals(this.sPresenterNick, sendItemSubBroadcastPacket.sPresenterNick) && JceUtil.equals(this.sSenderNick, sendItemSubBroadcastPacket.sSenderNick) && JceUtil.equals(this.sSendContent, sendItemSubBroadcastPacket.sSendContent) && JceUtil.equals(this.iItemCountByGroup, sendItemSubBroadcastPacket.iItemCountByGroup) && JceUtil.equals(this.iItemGroup, sendItemSubBroadcastPacket.iItemGroup) && JceUtil.equals(this.iSuperPupleLevel, sendItemSubBroadcastPacket.iSuperPupleLevel) && JceUtil.equals(this.iComboScore, sendItemSubBroadcastPacket.iComboScore) && JceUtil.equals(this.iDisplayInfo, sendItemSubBroadcastPacket.iDisplayInfo) && JceUtil.equals(this.iEffectType, sendItemSubBroadcastPacket.iEffectType) && JceUtil.equals(this.iSenderIcon, sendItemSubBroadcastPacket.iSenderIcon) && JceUtil.equals(this.iPresenterIcon, sendItemSubBroadcastPacket.iPresenterIcon) && JceUtil.equals(this.iTemplateType, sendItemSubBroadcastPacket.iTemplateType) && JceUtil.equals(this.sExpand, sendItemSubBroadcastPacket.sExpand) && JceUtil.equals(this.bBusi, sendItemSubBroadcastPacket.bBusi) && JceUtil.equals(this.iColorEffectType, sendItemSubBroadcastPacket.iColorEffectType) && JceUtil.equals(this.sPropsName, sendItemSubBroadcastPacket.sPropsName) && JceUtil.equals(this.iAccpet, sendItemSubBroadcastPacket.iAccpet) && JceUtil.equals(this.iEventType, sendItemSubBroadcastPacket.iEventType) && JceUtil.equals(this.userInfo, sendItemSubBroadcastPacket.userInfo) && JceUtil.equals(this.lRoomId, sendItemSubBroadcastPacket.lRoomId) && JceUtil.equals(this.lHomeOwnerUid, sendItemSubBroadcastPacket.lHomeOwnerUid) && JceUtil.equals(this.streamerInfo, sendItemSubBroadcastPacket.streamerInfo) && JceUtil.equals(this.iPayType, sendItemSubBroadcastPacket.iPayType) && JceUtil.equals(this.iNobleLevel, sendItemSubBroadcastPacket.iNobleLevel) && JceUtil.equals(this.tNobleLevel, sendItemSubBroadcastPacket.tNobleLevel) && JceUtil.equals(this.tEffectInfo, sendItemSubBroadcastPacket.tEffectInfo) && JceUtil.equals(this.vExUid, sendItemSubBroadcastPacket.vExUid) && JceUtil.equals(this.iComboStatus, sendItemSubBroadcastPacket.iComboStatus) && JceUtil.equals(this.iPidColorType, sendItemSubBroadcastPacket.iPidColorType);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iItemType), JceUtil.hashCode(this.strPayId), JceUtil.hashCode(this.iItemCount), JceUtil.hashCode(this.lPresenterUid), JceUtil.hashCode(this.lSenderUid), JceUtil.hashCode(this.sPresenterNick), JceUtil.hashCode(this.sSenderNick), JceUtil.hashCode(this.sSendContent), JceUtil.hashCode(this.iItemCountByGroup), JceUtil.hashCode(this.iItemGroup), JceUtil.hashCode(this.iSuperPupleLevel), JceUtil.hashCode(this.iComboScore), JceUtil.hashCode(this.iDisplayInfo), JceUtil.hashCode(this.iEffectType), JceUtil.hashCode(this.iSenderIcon), JceUtil.hashCode(this.iPresenterIcon), JceUtil.hashCode(this.iTemplateType), JceUtil.hashCode(this.sExpand), JceUtil.hashCode(this.bBusi), JceUtil.hashCode(this.iColorEffectType), JceUtil.hashCode(this.sPropsName), JceUtil.hashCode(this.iAccpet), JceUtil.hashCode(this.iEventType), JceUtil.hashCode(this.userInfo), JceUtil.hashCode(this.lRoomId), JceUtil.hashCode(this.lHomeOwnerUid), JceUtil.hashCode(this.streamerInfo), JceUtil.hashCode(this.iPayType), JceUtil.hashCode(this.iNobleLevel), JceUtil.hashCode(this.tNobleLevel), JceUtil.hashCode(this.tEffectInfo), JceUtil.hashCode(this.vExUid), JceUtil.hashCode(this.iComboStatus), JceUtil.hashCode(this.iPidColorType)});
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
        jceOutputStream.write(this.iItemType, 0);
        String str = this.strPayId;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
        jceOutputStream.write(this.iItemCount, 2);
        jceOutputStream.write(this.lPresenterUid, 3);
        jceOutputStream.write(this.lSenderUid, 4);
        String str2 = this.sPresenterNick;
        if (str2 != null) {
            jceOutputStream.write(str2, 5);
        }
        String str3 = this.sSenderNick;
        if (str3 != null) {
            jceOutputStream.write(str3, 6);
        }
        String str4 = this.sSendContent;
        if (str4 != null) {
            jceOutputStream.write(str4, 7);
        }
        jceOutputStream.write(this.iItemCountByGroup, 8);
        jceOutputStream.write(this.iItemGroup, 9);
        jceOutputStream.write(this.iSuperPupleLevel, 10);
        jceOutputStream.write(this.iComboScore, 11);
        jceOutputStream.write(this.iDisplayInfo, 12);
        jceOutputStream.write(this.iEffectType, 13);
        String str5 = this.iSenderIcon;
        if (str5 != null) {
            jceOutputStream.write(str5, 14);
        }
        String str6 = this.iPresenterIcon;
        if (str6 != null) {
            jceOutputStream.write(str6, 15);
        }
        jceOutputStream.write(this.iTemplateType, 16);
        String str7 = this.sExpand;
        if (str7 != null) {
            jceOutputStream.write(str7, 17);
        }
        jceOutputStream.write(this.bBusi, 18);
        jceOutputStream.write(this.iColorEffectType, 19);
        String str8 = this.sPropsName;
        if (str8 != null) {
            jceOutputStream.write(str8, 20);
        }
        jceOutputStream.write(this.iAccpet, 21);
        jceOutputStream.write(this.iEventType, 22);
        UserIdentityInfo userIdentityInfo = this.userInfo;
        if (userIdentityInfo != null) {
            jceOutputStream.write((JceStruct) userIdentityInfo, 23);
        }
        jceOutputStream.write(this.lRoomId, 24);
        jceOutputStream.write(this.lHomeOwnerUid, 25);
        StreamerNode streamerNode = this.streamerInfo;
        if (streamerNode != null) {
            jceOutputStream.write((JceStruct) streamerNode, 26);
        }
        jceOutputStream.write(this.iPayType, 27);
        jceOutputStream.write(this.iNobleLevel, 28);
        NobleLevelInfo nobleLevelInfo = this.tNobleLevel;
        if (nobleLevelInfo != null) {
            jceOutputStream.write((JceStruct) nobleLevelInfo, 29);
        }
        ItemEffectInfo itemEffectInfo = this.tEffectInfo;
        if (itemEffectInfo != null) {
            jceOutputStream.write((JceStruct) itemEffectInfo, 30);
        }
        ArrayList<Long> arrayList = this.vExUid;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 31);
        }
        jceOutputStream.write(this.iComboStatus, 32);
        jceOutputStream.write(this.iPidColorType, 33);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIItemType(jceInputStream.read(this.iItemType, 0, false));
        setStrPayId(jceInputStream.readString(1, false));
        setIItemCount(jceInputStream.read(this.iItemCount, 2, false));
        setLPresenterUid(jceInputStream.read(this.lPresenterUid, 3, false));
        setLSenderUid(jceInputStream.read(this.lSenderUid, 4, false));
        setSPresenterNick(jceInputStream.readString(5, false));
        setSSenderNick(jceInputStream.readString(6, false));
        setSSendContent(jceInputStream.readString(7, false));
        setIItemCountByGroup(jceInputStream.read(this.iItemCountByGroup, 8, false));
        setIItemGroup(jceInputStream.read(this.iItemGroup, 9, false));
        setISuperPupleLevel(jceInputStream.read(this.iSuperPupleLevel, 10, false));
        setIComboScore(jceInputStream.read(this.iComboScore, 11, false));
        setIDisplayInfo(jceInputStream.read(this.iDisplayInfo, 12, false));
        setIEffectType(jceInputStream.read(this.iEffectType, 13, false));
        setISenderIcon(jceInputStream.readString(14, false));
        setIPresenterIcon(jceInputStream.readString(15, false));
        setITemplateType(jceInputStream.read(this.iTemplateType, 16, false));
        setSExpand(jceInputStream.readString(17, false));
        setBBusi(jceInputStream.read(this.bBusi, 18, false));
        setIColorEffectType(jceInputStream.read(this.iColorEffectType, 19, false));
        setSPropsName(jceInputStream.readString(20, false));
        setIAccpet(jceInputStream.read(this.iAccpet, 21, false));
        setIEventType(jceInputStream.read(this.iEventType, 22, false));
        if (cache_userInfo == null) {
            cache_userInfo = new UserIdentityInfo();
        }
        setUserInfo((UserIdentityInfo) jceInputStream.read((JceStruct) cache_userInfo, 23, false));
        setLRoomId(jceInputStream.read(this.lRoomId, 24, false));
        setLHomeOwnerUid(jceInputStream.read(this.lHomeOwnerUid, 25, false));
        if (cache_streamerInfo == null) {
            cache_streamerInfo = new StreamerNode();
        }
        setStreamerInfo((StreamerNode) jceInputStream.read((JceStruct) cache_streamerInfo, 26, false));
        setIPayType(jceInputStream.read(this.iPayType, 27, false));
        setINobleLevel(jceInputStream.read(this.iNobleLevel, 28, false));
        if (cache_tNobleLevel == null) {
            cache_tNobleLevel = new NobleLevelInfo();
        }
        setTNobleLevel((NobleLevelInfo) jceInputStream.read((JceStruct) cache_tNobleLevel, 29, false));
        if (cache_tEffectInfo == null) {
            cache_tEffectInfo = new ItemEffectInfo();
        }
        setTEffectInfo((ItemEffectInfo) jceInputStream.read((JceStruct) cache_tEffectInfo, 30, false));
        if (cache_vExUid == null) {
            cache_vExUid = new ArrayList<>();
            cache_vExUid.add(0L);
        }
        setVExUid((ArrayList) jceInputStream.read(cache_vExUid, 31, false));
        setIComboStatus(jceInputStream.read(this.iComboStatus, 32, false));
        setIPidColorType(jceInputStream.read(this.iPidColorType, 33, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.iItemType, "iItemType");
        jceDisplayer.display(this.strPayId, "strPayId");
        jceDisplayer.display(this.iItemCount, "iItemCount");
        jceDisplayer.display(this.lPresenterUid, "lPresenterUid");
        jceDisplayer.display(this.lSenderUid, "lSenderUid");
        jceDisplayer.display(this.sPresenterNick, "sPresenterNick");
        jceDisplayer.display(this.sSenderNick, "sSenderNick");
        jceDisplayer.display(this.sSendContent, "sSendContent");
        jceDisplayer.display(this.iItemCountByGroup, "iItemCountByGroup");
        jceDisplayer.display(this.iItemGroup, "iItemGroup");
        jceDisplayer.display(this.iSuperPupleLevel, "iSuperPupleLevel");
        jceDisplayer.display(this.iComboScore, "iComboScore");
        jceDisplayer.display(this.iDisplayInfo, "iDisplayInfo");
        jceDisplayer.display(this.iEffectType, "iEffectType");
        jceDisplayer.display(this.iSenderIcon, "iSenderIcon");
        jceDisplayer.display(this.iPresenterIcon, "iPresenterIcon");
        jceDisplayer.display(this.iTemplateType, "iTemplateType");
        jceDisplayer.display(this.sExpand, "sExpand");
        jceDisplayer.display(this.bBusi, "bBusi");
        jceDisplayer.display(this.iColorEffectType, "iColorEffectType");
        jceDisplayer.display(this.sPropsName, "sPropsName");
        jceDisplayer.display(this.iAccpet, "iAccpet");
        jceDisplayer.display(this.iEventType, "iEventType");
        jceDisplayer.display((JceStruct) this.userInfo, "userInfo");
        jceDisplayer.display(this.lRoomId, "lRoomId");
        jceDisplayer.display(this.lHomeOwnerUid, "lHomeOwnerUid");
        jceDisplayer.display((JceStruct) this.streamerInfo, "streamerInfo");
        jceDisplayer.display(this.iPayType, "iPayType");
        jceDisplayer.display(this.iNobleLevel, "iNobleLevel");
        jceDisplayer.display((JceStruct) this.tNobleLevel, "tNobleLevel");
        jceDisplayer.display((JceStruct) this.tEffectInfo, "tEffectInfo");
        jceDisplayer.display((Collection) this.vExUid, "vExUid");
        jceDisplayer.display(this.iComboStatus, "iComboStatus");
        jceDisplayer.display(this.iPidColorType, "iPidColorType");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
