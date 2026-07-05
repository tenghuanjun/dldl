package com.unionpay.tsmservice.mi.mini.request.wrapper;

import com.unionpay.tsmservice.mi.mini.request.QueryVendorPayStatusRequestParams;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class QueryVendorPayStatusRequestParamsWrapper extends BaseRequestParamsWrapper {
    public QueryVendorPayStatusRequestParamsWrapper(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams) {
        super(queryVendorPayStatusRequestParams);
    }

    @Override // com.unionpay.tsmservice.mi.mini.request.wrapper.BaseRequestParamsWrapper
    public JSONObject getRequestJSONObject() {
        return new JSONObject();
    }

    @Override // com.unionpay.tsmservice.mi.mini.request.wrapper.BaseRequestParamsWrapper
    public boolean isParamsValid() {
        return this.target != null;
    }
}
