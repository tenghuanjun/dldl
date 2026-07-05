package com.sqwan.common.base;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.sq.tool.network.VolleyErrorUtil;
import com.sqnetwork.voly.VolleyError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SqError implements Parcelable {
    public static final Parcelable.Creator<SqError> CREATOR = new Parcelable.Creator<SqError>() { // from class: com.sqwan.common.base.SqError.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SqError createFromParcel(Parcel parcel) {
            return new SqError(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SqError[] newArray(int i) {
            return new SqError[i];
        }
    };
    private static final String HTTP_ERROR = "http request error";
    private static final String PARSE_ERROR = "http data parse error";
    public final int code;
    public final String msg;
    public final int originCode;
    public final String originMsg;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SqError(int i, String str) {
        this(i, str, -1, null);
    }

    public SqError(int i, String str, int i2, String str2) {
        this.code = i;
        this.msg = str;
        this.originCode = i2;
        this.originMsg = str2;
    }

    public SqError(SqError sqError) {
        this(sqError.code, sqError.msg, sqError.originCode, sqError.originMsg);
    }

    public static SqError serverError(int i, int i2, String str) {
        return new SqError(i, str, i2, str);
    }

    public static SqError httpError(int i, VolleyError volleyError) {
        return new SqError(i, HTTP_ERROR, VolleyErrorUtil.errorCode(volleyError), VolleyErrorUtil.simpleErrorMsg(volleyError));
    }

    public static SqError parseError(int i, Exception exc) {
        return new SqError(i, PARSE_ERROR, -1, exc != null ? exc.getMessage() : null);
    }

    public int getModule() {
        return (Math.abs(this.code) % 1000) / 100;
    }

    public String toString() {
        if (TextUtils.isEmpty(this.originMsg)) {
            return this.msg + "(" + this.code + "/" + this.originCode + ")";
        }
        return this.msg + "(" + this.code + "/" + this.originCode + "), [" + this.originMsg + "]";
    }

    protected SqError(Parcel parcel) {
        this.code = parcel.readInt();
        String string = parcel.readString();
        this.msg = string == null ? "unknown(p)" : string;
        this.originCode = parcel.readInt();
        this.originMsg = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeString(this.msg);
        parcel.writeInt(this.originCode);
        parcel.writeString(this.originMsg);
    }
}
