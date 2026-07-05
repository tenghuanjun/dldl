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
public class GamePartyHeartbeatReq extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<GamePartyHeartbeatReq> CREATOR = new Parcelable.Creator<GamePartyHeartbeatReq>() { // from class: com.duowan.HUYA.GamePartyHeartbeatReq.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GamePartyHeartbeatReq createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            GamePartyHeartbeatReq gamePartyHeartbeatReq = new GamePartyHeartbeatReq();
            gamePartyHeartbeatReq.readFrom(jceInputStream);
            return gamePartyHeartbeatReq;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GamePartyHeartbeatReq[] newArray(int i) {
            return new GamePartyHeartbeatReq[i];
        }
    };
    static UserId cache_tId;
    public UserId tId = null;
    public String sContent = "";

    public String className() {
        return "HUYA.GamePartyHeartbeatReq";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GamePartyHeartbeatReq";
    }

    public UserId getTId() {
        return this.tId;
    }

    public void setTId(UserId userId) {
        this.tId = userId;
    }

    public String getSContent() {
        return this.sContent;
    }

    public void setSContent(String str) {
        this.sContent = str;
    }

    public GamePartyHeartbeatReq() {
        setTId(null);
        setSContent(this.sContent);
    }

    public GamePartyHeartbeatReq(UserId userId, String str) {
        setTId(userId);
        setSContent(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GamePartyHeartbeatReq gamePartyHeartbeatReq = (GamePartyHeartbeatReq) obj;
        return JceUtil.equals(this.tId, gamePartyHeartbeatReq.tId) && JceUtil.equals(this.sContent, gamePartyHeartbeatReq.sContent);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.tId), JceUtil.hashCode(this.sContent)});
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
        UserId userId = this.tId;
        if (userId != null) {
            jceOutputStream.write((JceStruct) userId, 0);
        }
        String str = this.sContent;
        if (str != null) {
            jceOutputStream.write(str, 1);
        }
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        if (cache_tId == null) {
            cache_tId = new UserId();
        }
        setTId((UserId) jceInputStream.read((JceStruct) cache_tId, 0, false));
        setSContent(jceInputStream.readString(1, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        JceDisplayer jceDisplayer = new JceDisplayer(sb, i);
        jceDisplayer.display((JceStruct) this.tId, "tId");
        jceDisplayer.display(this.sContent, "sContent");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
