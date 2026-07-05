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
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SubScribeListUserRecItemRsp extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<SubScribeListUserRecItemRsp> CREATOR = new Parcelable.Creator<SubScribeListUserRecItemRsp>() { // from class: com.duowan.HUYA.SubScribeListUserRecItemRsp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubScribeListUserRecItemRsp createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            SubScribeListUserRecItemRsp subScribeListUserRecItemRsp = new SubScribeListUserRecItemRsp();
            subScribeListUserRecItemRsp.readFrom(jceInputStream);
            return subScribeListUserRecItemRsp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SubScribeListUserRecItemRsp[] newArray(int i) {
            return new SubScribeListUserRecItemRsp[i];
        }
    };
    static Map<Long, SubscriberExtraInfo> cache_mExtraInfo;
    static ArrayList<UserRecItem> cache_vLiveSubscribers;
    static ArrayList<SubscriberStat> cache_vUnLiveSubscribers;
    public ArrayList<UserRecItem> vLiveSubscribers = null;
    public ArrayList<SubscriberStat> vUnLiveSubscribers = null;
    public int iTotalUnlive = 0;
    public Map<Long, SubscriberExtraInfo> mExtraInfo = null;

    public String className() {
        return "HUYA.SubScribeListUserRecItemRsp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.SubScribeListUserRecItemRsp";
    }

    public ArrayList<UserRecItem> getVLiveSubscribers() {
        return this.vLiveSubscribers;
    }

    public void setVLiveSubscribers(ArrayList<UserRecItem> arrayList) {
        this.vLiveSubscribers = arrayList;
    }

    public ArrayList<SubscriberStat> getVUnLiveSubscribers() {
        return this.vUnLiveSubscribers;
    }

    public void setVUnLiveSubscribers(ArrayList<SubscriberStat> arrayList) {
        this.vUnLiveSubscribers = arrayList;
    }

    public int getITotalUnlive() {
        return this.iTotalUnlive;
    }

    public void setITotalUnlive(int i) {
        this.iTotalUnlive = i;
    }

    public Map<Long, SubscriberExtraInfo> getMExtraInfo() {
        return this.mExtraInfo;
    }

    public void setMExtraInfo(Map<Long, SubscriberExtraInfo> map) {
        this.mExtraInfo = map;
    }

    public SubScribeListUserRecItemRsp() {
        setVLiveSubscribers(null);
        setVUnLiveSubscribers(this.vUnLiveSubscribers);
        setITotalUnlive(this.iTotalUnlive);
        setMExtraInfo(this.mExtraInfo);
    }

    public SubScribeListUserRecItemRsp(ArrayList<UserRecItem> arrayList, ArrayList<SubscriberStat> arrayList2, int i, Map<Long, SubscriberExtraInfo> map) {
        setVLiveSubscribers(arrayList);
        setVUnLiveSubscribers(arrayList2);
        setITotalUnlive(i);
        setMExtraInfo(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubScribeListUserRecItemRsp subScribeListUserRecItemRsp = (SubScribeListUserRecItemRsp) obj;
        return JceUtil.equals(this.vLiveSubscribers, subScribeListUserRecItemRsp.vLiveSubscribers) && JceUtil.equals(this.vUnLiveSubscribers, subScribeListUserRecItemRsp.vUnLiveSubscribers) && JceUtil.equals(this.iTotalUnlive, subScribeListUserRecItemRsp.iTotalUnlive) && JceUtil.equals(this.mExtraInfo, subScribeListUserRecItemRsp.mExtraInfo);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.vLiveSubscribers), JceUtil.hashCode(this.vUnLiveSubscribers), JceUtil.hashCode(this.iTotalUnlive), JceUtil.hashCode(this.mExtraInfo)});
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
        ArrayList<UserRecItem> arrayList = this.vLiveSubscribers;
        if (arrayList != null) {
            jceOutputStream.write((Collection) arrayList, 0);
        }
        ArrayList<SubscriberStat> arrayList2 = this.vUnLiveSubscribers;
        if (arrayList2 != null) {
            jceOutputStream.write((Collection) arrayList2, 1);
        }
        jceOutputStream.write(this.iTotalUnlive, 2);
        Map<Long, SubscriberExtraInfo> map = this.mExtraInfo;
        if (map != null) {
            jceOutputStream.write((Map) map, 3);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_vLiveSubscribers == null) {
            cache_vLiveSubscribers = new ArrayList<>();
            cache_vLiveSubscribers.add(new UserRecItem());
        }
        setVLiveSubscribers((ArrayList) jceInputStream.read(cache_vLiveSubscribers, 0, false));
        if (cache_vUnLiveSubscribers == null) {
            cache_vUnLiveSubscribers = new ArrayList<>();
            cache_vUnLiveSubscribers.add(new SubscriberStat());
        }
        setVUnLiveSubscribers((ArrayList) jceInputStream.read(cache_vUnLiveSubscribers, 1, false));
        setITotalUnlive(jceInputStream.read(this.iTotalUnlive, 2, false));
        if (cache_mExtraInfo == null) {
            cache_mExtraInfo = new HashMap();
            cache_mExtraInfo.put(0L, new SubscriberExtraInfo());
        }
        setMExtraInfo((Map) jceInputStream.read(cache_mExtraInfo, 3, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((Collection) this.vLiveSubscribers, "vLiveSubscribers");
        jceDisplayer.display((Collection) this.vUnLiveSubscribers, "vUnLiveSubscribers");
        jceDisplayer.display(this.iTotalUnlive, "iTotalUnlive");
        jceDisplayer.display((Map) this.mExtraInfo, "mExtraInfo");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
