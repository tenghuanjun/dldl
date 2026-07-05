package com.unionpay.tsmservice.mini.result.wrapper;

import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Base64;
import com.unionpay.tsmservice.mini.ITsmCallback;
import com.unionpay.tsmservice.mini.result.QueryVendorPayStatusResult;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class QueryVendorPayStatusResultCallbackWrapper extends BaseResultCallbackWrapper {
    public QueryVendorPayStatusResultCallbackWrapper(int i, ITsmCallback iTsmCallback) {
        super(i, iTsmCallback);
    }

    private void getCallbackResultData(Bundle bundle) {
        Parcel parcelObtain = Parcel.obtain();
        String string = bundle.getString("result");
        if (!TextUtils.isEmpty(string)) {
            byte[] bArrDecode = Base64.decode(string, 0);
            if (bArrDecode != null && bArrDecode.length != 0) {
                parcelObtain.unmarshall(bArrDecode, 0, bArrDecode.length);
                parcelObtain.setDataPosition(0);
            }
            if (parcelObtain.dataSize() == 0) {
                bundle.putString("errorCode", "010001");
            } else {
                QueryVendorPayStatusResult queryVendorPayStatusResult = (QueryVendorPayStatusResult) parcelObtain.readParcelable(QueryVendorPayStatusResult.class.getClassLoader());
                bundle.putString("errorCode", "10000");
                bundle.putParcelable("result", queryVendorPayStatusResult);
            }
        }
        parcelObtain.recycle();
    }

    @Override // com.unionpay.tsmservice.mini.result.wrapper.BaseResultCallbackWrapper
    protected Bundle convertResult(Bundle bundle) {
        new QueryVendorPayStatusResult().setQueryVendorPayStatusResult(bundle);
        getCallbackResultData(bundle);
        return bundle;
    }
}
