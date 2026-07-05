package com.sqwan.common.web;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ExitAnimParams implements Parcelable {
    public static final Parcelable.Creator<ExitAnimParams> CREATOR = new Parcelable.Creator<ExitAnimParams>() { // from class: com.sqwan.common.web.ExitAnimParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExitAnimParams createFromParcel(Parcel parcel) {
            return new ExitAnimParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExitAnimParams[] newArray(int i) {
            return new ExitAnimParams[i];
        }
    };
    public int height;
    public int width;
    public int x;
    public int y;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ExitAnimParams(int i, int i2, int i3, int i4) {
        this.width = i;
        this.height = i2;
        this.x = i3;
        this.y = i4;
    }

    protected ExitAnimParams(Parcel parcel) {
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.x = parcel.readInt();
        this.y = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeInt(this.x);
        parcel.writeInt(this.y);
    }
}
