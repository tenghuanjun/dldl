package com.duowan.HUYA;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.taf.jce.JceDisplayer;
import com.duowan.taf.jce.JceInputStream;
import com.duowan.taf.jce.JceOutputStream;
import com.duowan.taf.jce.JceStruct;
import com.duowan.taf.jce.JceUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CreateAddrReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<CreateAddrReq> CREATOR = new Parcelable.Creator<CreateAddrReq>() { // from class: com.duowan.HUYA.CreateAddrReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreateAddrReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            CreateAddrReq createAddrReq = new CreateAddrReq();
            createAddrReq.readFrom(jceInputStream);
            return createAddrReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreateAddrReq[] newArray(int i) {
            return new CreateAddrReq[i];
        }
    };
    static Map<String, String> cache_mMiscInfo;
    static UserId cache_tUserId;
    public String sLiveDesc = "";
    public int iGameId = 0;
    public UserId tUserId = null;
    public long lLiveFlag = 0;
    public Map<String, String> mMiscInfo = null;

    public String className() {
        return "HUYA.CreateAddrReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.CreateAddrReq";
    }

    public String getSLiveDesc() {
        return this.sLiveDesc;
    }

    public void setSLiveDesc(String str) {
        this.sLiveDesc = str;
    }

    public int getIGameId() {
        return this.iGameId;
    }

    public void setIGameId(int i) {
        this.iGameId = i;
    }

    public UserId getTUserId() {
        return this.tUserId;
    }

    public void setTUserId(UserId userId) {
        this.tUserId = userId;
    }

    public long getLLiveFlag() {
        return this.lLiveFlag;
    }

    public void setLLiveFlag(long j) {
        this.lLiveFlag = j;
    }

    public Map<String, String> getMMiscInfo() {
        return this.mMiscInfo;
    }

    public void setMMiscInfo(Map<String, String> map) {
        this.mMiscInfo = map;
    }

    public CreateAddrReq() {
        setSLiveDesc("");
        setIGameId(this.iGameId);
        setTUserId(this.tUserId);
        setLLiveFlag(this.lLiveFlag);
        setMMiscInfo(this.mMiscInfo);
    }

    public CreateAddrReq(String str, int i, UserId userId, long j, Map<String, String> map) {
        setSLiveDesc(str);
        setIGameId(i);
        setTUserId(userId);
        setLLiveFlag(j);
        setMMiscInfo(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CreateAddrReq createAddrReq = (CreateAddrReq) obj;
        return JceUtil.equals(this.sLiveDesc, createAddrReq.sLiveDesc) && JceUtil.equals(this.iGameId, createAddrReq.iGameId) && JceUtil.equals(this.tUserId, createAddrReq.tUserId) && JceUtil.equals(this.lLiveFlag, createAddrReq.lLiveFlag) && JceUtil.equals(this.mMiscInfo, createAddrReq.mMiscInfo);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.sLiveDesc), JceUtil.hashCode(this.iGameId), JceUtil.hashCode(this.tUserId), JceUtil.hashCode(this.lLiveFlag), JceUtil.hashCode(this.mMiscInfo)});
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
        String str = this.sLiveDesc;
        if (str != null) {
            jceOutputStream.write(str, 0);
        }
        jceOutputStream.write(this.iGameId, 1);
        UserId userId = this.tUserId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 2);
        }
        jceOutputStream.write(this.lLiveFlag, 3);
        Map<String, String> map = this.mMiscInfo;
        if (map != null) {
            jceOutputStream.write((Map) map, 4);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setSLiveDesc(jceInputStream.readString(0, false));
        setIGameId(jceInputStream.read(this.iGameId, 1, false));
        if (cache_tUserId == null) {
            cache_tUserId = new UserId();
        }
        setTUserId((UserId) jceInputStream.read((JceStruct) cache_tUserId, 2, false));
        setLLiveFlag(jceInputStream.read(this.lLiveFlag, 3, false));
        if (cache_mMiscInfo == null) {
            HashMap map = new HashMap();
            cache_mMiscInfo = map;
            map.put("", "");
        }
        setMMiscInfo((Map) jceInputStream.read(cache_mMiscInfo, 4, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display(this.sLiveDesc, "sLiveDesc");
        jceDisplayer.display(this.iGameId, "iGameId");
        jceDisplayer.display((JceStruct) this.tUserId, "tUserId");
        jceDisplayer.display(this.lLiveFlag, "lLiveFlag");
        jceDisplayer.display((Map) this.mMiscInfo, "mMiscInfo");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
