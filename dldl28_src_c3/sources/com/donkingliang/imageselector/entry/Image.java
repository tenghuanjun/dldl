package com.donkingliang.imageselector.entry;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class Image implements Parcelable {
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() { // from class: com.donkingliang.imageselector.entry.Image.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Image createFromParcel(Parcel parcel) {
            return new Image(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Image[] newArray(int i) {
            return new Image[i];
        }
    };
    private String mimeType;
    private String name;
    private String path;
    private long time;
    private Uri uri;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Image(String str, long j, String str2, String str3, Uri uri) {
        this.path = str;
        this.time = j;
        this.name = str2;
        this.mimeType = str3;
        this.uri = uri;
    }

    public Uri getUri() {
        return this.uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String str) {
        this.mimeType = str;
    }

    public boolean isGif() {
        return "image/gif".equals(this.mimeType);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.path);
        parcel.writeLong(this.time);
        parcel.writeString(this.name);
        parcel.writeString(this.mimeType);
        parcel.writeParcelable(this.uri, i);
    }

    protected Image(Parcel parcel) {
        this.path = parcel.readString();
        this.time = parcel.readLong();
        this.name = parcel.readString();
        this.mimeType = parcel.readString();
        this.uri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }
}
