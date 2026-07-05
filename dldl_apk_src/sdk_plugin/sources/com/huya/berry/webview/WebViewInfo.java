package com.huya.berry.webview;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebViewInfo implements Parcelable {
    public static final Parcelable.Creator<WebViewInfo> CREATOR = new Parcelable.Creator<WebViewInfo>() { // from class: com.huya.berry.webview.WebViewInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WebViewInfo createFromParcel(Parcel parcel) {
            return new WebViewInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WebViewInfo[] newArray(int i) {
            return new WebViewInfo[i];
        }
    };
    public float height;
    public boolean isBindLogin;
    public boolean isBusiurlBindLogin;
    public boolean isDynamicTitle;
    public String mJumpUrl;
    public String mTitle;
    public String mUrl;
    public float width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WebViewInfo() {
    }

    protected WebViewInfo(Parcel parcel) {
        this.mUrl = parcel.readString();
        this.mTitle = parcel.readString();
        this.mJumpUrl = parcel.readString();
        this.isBindLogin = parcel.readByte() != 0;
        this.isBusiurlBindLogin = parcel.readByte() != 0;
        this.isDynamicTitle = parcel.readByte() != 0;
        this.width = parcel.readFloat();
        this.height = parcel.readFloat();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mJumpUrl);
        parcel.writeByte(this.isBindLogin ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isBusiurlBindLogin ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isDynamicTitle ? (byte) 1 : (byte) 0);
        parcel.writeFloat(this.width);
        parcel.writeFloat(this.height);
    }
}
