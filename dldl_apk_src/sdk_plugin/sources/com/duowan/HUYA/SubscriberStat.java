package com.duowan.HUYA;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SubscriberStat extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<SubscriberStat> CREATOR = new Parcelable.Creator<SubscriberStat>() { // from class: com.duowan.HUYA.SubscriberStat.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriberStat createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            SubscriberStat subscriberStat = new SubscriberStat();
            subscriberStat.readFrom(jceInputStream);
            return subscriberStat;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubscriberStat[] newArray(int i) {
            return new SubscriberStat[i];
        }
    };
    static GameLiveInfo cache_tLive;
    static LiveScheduleInfo cache_tSchedule;
    static UserProfile cache_tUserProfile;
    public UserProfile tUserProfile = null;
    public long lSubscribeTime = 0;
    public boolean bLiving = true;
    public GameLiveInfo tLive = null;
    public boolean bSubscribedTo = true;
    public int iRelation = 0;
    public String sSchedule = "";
    public LiveScheduleInfo tSchedule = null;

    public String className() {
        return "HUYA.SubscriberStat";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SubscriberStat";
    }

    public UserProfile getTUserProfile() {
        return this.tUserProfile;
    }

    public void setTUserProfile(UserProfile userProfile) {
        this.tUserProfile = userProfile;
    }

    public long getLSubscribeTime() {
        return this.lSubscribeTime;
    }

    public void setLSubscribeTime(long j) {
        this.lSubscribeTime = j;
    }

    public boolean getBLiving() {
        return this.bLiving;
    }

    public void setBLiving(boolean z) {
        this.bLiving = z;
    }

    public GameLiveInfo getTLive() {
        return this.tLive;
    }

    public void setTLive(GameLiveInfo gameLiveInfo) {
        this.tLive = gameLiveInfo;
    }

    public boolean getBSubscribedTo() {
        return this.bSubscribedTo;
    }

    public void setBSubscribedTo(boolean z) {
        this.bSubscribedTo = z;
    }

    public int getIRelation() {
        return this.iRelation;
    }

    public void setIRelation(int i) {
        this.iRelation = i;
    }

    public String getSSchedule() {
        return this.sSchedule;
    }

    public void setSSchedule(String str) {
        this.sSchedule = str;
    }

    public LiveScheduleInfo getTSchedule() {
        return this.tSchedule;
    }

    public void setTSchedule(LiveScheduleInfo liveScheduleInfo) {
        this.tSchedule = liveScheduleInfo;
    }

    public SubscriberStat() {
        setTUserProfile(null);
        setLSubscribeTime(this.lSubscribeTime);
        setBLiving(this.bLiving);
        setTLive(this.tLive);
        setBSubscribedTo(this.bSubscribedTo);
        setIRelation(this.iRelation);
        setSSchedule(this.sSchedule);
        setTSchedule(this.tSchedule);
    }

    public SubscriberStat(UserProfile userProfile, long j, boolean z, GameLiveInfo gameLiveInfo, boolean z2, int i, String str, LiveScheduleInfo liveScheduleInfo) {
        setTUserProfile(userProfile);
        setLSubscribeTime(j);
        setBLiving(z);
        setTLive(gameLiveInfo);
        setBSubscribedTo(z2);
        setIRelation(i);
        setSSchedule(str);
        setTSchedule(liveScheduleInfo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubscriberStat subscriberStat = (SubscriberStat) obj;
        return JceUtil.equals(this.tUserProfile, subscriberStat.tUserProfile) && JceUtil.equals(this.lSubscribeTime, subscriberStat.lSubscribeTime) && JceUtil.equals(this.bLiving, subscriberStat.bLiving) && JceUtil.equals(this.tLive, subscriberStat.tLive) && JceUtil.equals(this.bSubscribedTo, subscriberStat.bSubscribedTo) && JceUtil.equals(this.iRelation, subscriberStat.iRelation) && JceUtil.equals(this.sSchedule, subscriberStat.sSchedule) && JceUtil.equals(this.tSchedule, subscriberStat.tSchedule);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tUserProfile), JceUtil.hashCode(this.lSubscribeTime), JceUtil.hashCode(this.bLiving), JceUtil.hashCode(this.tLive), JceUtil.hashCode(this.bSubscribedTo), JceUtil.hashCode(this.iRelation), JceUtil.hashCode(this.sSchedule), JceUtil.hashCode(this.tSchedule)});
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
        UserProfile userProfile = this.tUserProfile;
        if (userProfile != null) {
            jceOutputStream.write((JceStruct) userProfile, 0);
        }
        jceOutputStream.write(this.lSubscribeTime, 1);
        jceOutputStream.write(this.bLiving, 2);
        GameLiveInfo gameLiveInfo = this.tLive;
        if (gameLiveInfo != null) {
            jceOutputStream.write((JceStruct) gameLiveInfo, 3);
        }
        jceOutputStream.write(this.bSubscribedTo, 5);
        jceOutputStream.write(this.iRelation, 7);
        String str = this.sSchedule;
        if (str != null) {
            jceOutputStream.write(str, 8);
        }
        LiveScheduleInfo liveScheduleInfo = this.tSchedule;
        if (liveScheduleInfo != null) {
            jceOutputStream.write((JceStruct) liveScheduleInfo, 9);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tUserProfile == null) {
            cache_tUserProfile = new UserProfile();
        }
        setTUserProfile((UserProfile) jceInputStream.read((JceStruct) cache_tUserProfile, 0, false));
        setLSubscribeTime(jceInputStream.read(this.lSubscribeTime, 1, false));
        setBLiving(jceInputStream.read(this.bLiving, 2, false));
        if (cache_tLive == null) {
            cache_tLive = new GameLiveInfo();
        }
        setTLive((GameLiveInfo) jceInputStream.read((JceStruct) cache_tLive, 3, false));
        setBSubscribedTo(jceInputStream.read(this.bSubscribedTo, 5, false));
        setIRelation(jceInputStream.read(this.iRelation, 7, false));
        setSSchedule(jceInputStream.readString(8, false));
        if (cache_tSchedule == null) {
            cache_tSchedule = new LiveScheduleInfo();
        }
        setTSchedule((LiveScheduleInfo) jceInputStream.read((JceStruct) cache_tSchedule, 9, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tUserProfile, "tUserProfile");
        jceDisplayer.display(this.lSubscribeTime, "lSubscribeTime");
        jceDisplayer.display(this.bLiving, "bLiving");
        jceDisplayer.display((JceStruct) this.tLive, "tLive");
        jceDisplayer.display(this.bSubscribedTo, "bSubscribedTo");
        jceDisplayer.display(this.iRelation, "iRelation");
        jceDisplayer.display(this.sSchedule, "sSchedule");
        jceDisplayer.display((JceStruct) this.tSchedule, "tSchedule");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
