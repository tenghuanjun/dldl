package com.unionpay.tsmservice.mini.result;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class QueryVendorPayStatusResult extends BaseResult {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.unionpay.tsmservice.mini.result.QueryVendorPayStatusResult.1
        @Override // android.os.Parcelable.Creator
        public final QueryVendorPayStatusResult createFromParcel(Parcel parcel) {
            return new QueryVendorPayStatusResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final QueryVendorPayStatusResult[] newArray(int i) {
            return new QueryVendorPayStatusResult[i];
        }
    };
    private Bundle mStatus;

    public QueryVendorPayStatusResult() {
    }

    public QueryVendorPayStatusResult(Parcel parcel) {
        this.mStatus = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getQueryVendorPayStatusResult() {
        return this.mStatus;
    }

    @Override // com.unionpay.tsmservice.mini.result.BaseResult
    public void initWithJSONObject(JSONObject jSONObject) {
    }

    public void setQueryVendorPayStatusResult(Bundle bundle) {
        this.mStatus = bundle;
    }

    @Override // com.unionpay.tsmservice.mini.result.BaseResult
    public JSONObject toJSONObject() {
        return new JSONObject();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mStatus);
    }
}
