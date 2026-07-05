package com.huya.berry.sdklivelist;

import android.os.Parcel;
import android.os.Parcelable;
import com.duowan.HUYA.ComponentItem;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ComponentInfo implements Parcelable, Comparable<ComponentInfo> {
    public static final Parcelable.Creator<ComponentInfo> CREATOR = new Parcelable.Creator<ComponentInfo>() { // from class: com.huya.berry.sdklivelist.ComponentInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ComponentInfo createFromParcel(Parcel parcel) {
            return new ComponentInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ComponentInfo[] newArray(int i) {
            return new ComponentInfo[i];
        }
    };
    public boolean bRedIcon;
    public int heavy;
    public int iComID;
    public int iLoginStatus;
    public String sIconUrl;
    public String sTitle;
    public String sUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ComponentInfo(ComponentItem componentItem) {
        this.heavy = 0;
        this.sTitle = "";
        this.sUrl = "";
        this.bRedIcon = true;
        this.iLoginStatus = 0;
        this.sIconUrl = "";
        this.iComID = 0;
        this.heavy = componentItem.heavy;
        this.sTitle = componentItem.sTitle;
        this.sUrl = componentItem.sUrl;
        this.bRedIcon = componentItem.bRedIcon;
        this.iLoginStatus = componentItem.iLoginStatus;
        this.sIconUrl = componentItem.sIconUrl;
        this.iComID = componentItem.iComID;
    }

    public String toString() {
        return "ComponentInfo{heavy=" + this.heavy + ", sTitle='" + this.sTitle + "', sUrl='" + this.sUrl + "', bRedIcon=" + this.bRedIcon + ", iLoginStatus=" + this.iLoginStatus + ", sIconUrl='" + this.sIconUrl + "', iComID=" + this.iComID + AbstractJsonLexerKt.END_OBJ;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.heavy);
        parcel.writeString(this.sTitle);
        parcel.writeString(this.sUrl);
        parcel.writeByte(this.bRedIcon ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.iLoginStatus);
        parcel.writeString(this.sIconUrl);
        parcel.writeInt(this.iComID);
    }

    protected ComponentInfo(Parcel parcel) {
        this.heavy = 0;
        this.sTitle = "";
        this.sUrl = "";
        this.bRedIcon = true;
        this.iLoginStatus = 0;
        this.sIconUrl = "";
        this.iComID = 0;
        this.heavy = parcel.readInt();
        this.sTitle = parcel.readString();
        this.sUrl = parcel.readString();
        this.bRedIcon = parcel.readByte() != 0;
        this.iLoginStatus = parcel.readInt();
        this.sIconUrl = parcel.readString();
        this.iComID = parcel.readInt();
    }

    @Override // java.lang.Comparable
    public int compareTo(ComponentInfo componentInfo) {
        int i = this.heavy;
        int i2 = componentInfo.heavy;
        if (i < i2) {
            return 1;
        }
        if (i > i2) {
            return -1;
        }
        int i3 = this.iComID;
        int i4 = componentInfo.iComID;
        if (i3 == i4) {
            return 0;
        }
        return i3 < i4 ? -1 : 1;
    }
}
