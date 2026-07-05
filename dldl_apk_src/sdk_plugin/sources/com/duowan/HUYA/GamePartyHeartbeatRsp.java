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
public class GamePartyHeartbeatRsp extends JceStruct implements Cloneable, Parcelable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Parcelable.Creator<GamePartyHeartbeatRsp> CREATOR = new Parcelable.Creator<GamePartyHeartbeatRsp>() { // from class: com.duowan.HUYA.GamePartyHeartbeatRsp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GamePartyHeartbeatRsp createFromParcel(Parcel parcel) {
            byte[] bArrCreateByteArray = parcel.createByteArray();
            JceInputStream jceInputStream = new JceInputStream();
            jceInputStream.warp(bArrCreateByteArray);
            GamePartyHeartbeatRsp gamePartyHeartbeatRsp = new GamePartyHeartbeatRsp();
            gamePartyHeartbeatRsp.readFrom(jceInputStream);
            return gamePartyHeartbeatRsp;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GamePartyHeartbeatRsp[] newArray(int i) {
            return new GamePartyHeartbeatRsp[i];
        }
    };
    public int iResultCode = 0;

    public String className() {
        return "HUYA.GamePartyHeartbeatRsp";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String fullClassName() {
        return "com.duowan.HUYA.GamePartyHeartbeatRsp";
    }

    public int getIResultCode() {
        return this.iResultCode;
    }

    public void setIResultCode(int i) {
        this.iResultCode = i;
    }

    public GamePartyHeartbeatRsp() {
        setIResultCode(0);
    }

    public GamePartyHeartbeatRsp(int i) {
        setIResultCode(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return JceUtil.equals(this.iResultCode, ((GamePartyHeartbeatRsp) obj).iResultCode);
    }

    public int hashCode() {
        return Arrays.hashCode(new int[]{JceUtil.hashCode(this.iResultCode)});
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
        jceOutputStream.write(this.iResultCode, 0);
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void readFrom(JceInputStream jceInputStream) {
        setIResultCode(jceInputStream.read(this.iResultCode, 0, false));
    }

    @Override // com.duowan.taf.jce.JceStruct
    public void display(StringBuilder sb, int i) {
        new JceDisplayer(sb, i).display(this.iResultCode, "iResultCode");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JceOutputStream jceOutputStream = new JceOutputStream();
        writeTo(jceOutputStream);
        parcel.writeByteArray(jceOutputStream.toByteArray());
    }
}
