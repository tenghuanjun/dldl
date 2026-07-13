package com.donkingliang.imageselector.entry;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class RequestConfig implements Parcelable {
    public static final Parcelable.Creator<RequestConfig> CREATOR = new Parcelable.Creator<RequestConfig>() { // from class: com.donkingliang.imageselector.entry.RequestConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestConfig createFromParcel(Parcel parcel) {
            return new RequestConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestConfig[] newArray(int i) {
            return new RequestConfig[i];
        }
    };
    public boolean canPreview;
    public float cropRatio;
    public boolean isCrop;
    public boolean isSingle;
    public int maxSelectCount;
    public boolean onlyTakePhoto;
    public int requestCode;
    public ArrayList<String> selected;
    public boolean useCamera;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isCrop ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.useCamera ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.onlyTakePhoto ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isSingle ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.canPreview ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.maxSelectCount);
        parcel.writeStringList(this.selected);
        parcel.writeFloat(this.cropRatio);
        parcel.writeInt(this.requestCode);
    }

    public RequestConfig() {
        this.isCrop = false;
        this.useCamera = true;
        this.onlyTakePhoto = false;
        this.isSingle = false;
        this.canPreview = true;
        this.cropRatio = 1.0f;
    }

    protected RequestConfig(Parcel parcel) {
        this.isCrop = false;
        this.useCamera = true;
        this.onlyTakePhoto = false;
        this.isSingle = false;
        this.canPreview = true;
        this.cropRatio = 1.0f;
        this.isCrop = parcel.readByte() != 0;
        this.useCamera = parcel.readByte() != 0;
        this.onlyTakePhoto = parcel.readByte() != 0;
        this.isSingle = parcel.readByte() != 0;
        this.canPreview = parcel.readByte() != 0;
        this.maxSelectCount = parcel.readInt();
        this.selected = parcel.createStringArrayList();
        this.cropRatio = parcel.readFloat();
        this.requestCode = parcel.readInt();
    }
}
