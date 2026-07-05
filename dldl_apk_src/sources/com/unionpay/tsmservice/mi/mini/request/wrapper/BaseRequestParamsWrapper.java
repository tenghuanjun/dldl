package com.unionpay.tsmservice.mi.mini.request.wrapper;

import android.text.TextUtils;
import com.unionpay.tsmservice.mi.mini.request.RequestParams;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public abstract class BaseRequestParamsWrapper {
    protected RequestParams target;

    public BaseRequestParamsWrapper(RequestParams requestParams) {
        this.target = requestParams;
    }

    public abstract JSONObject getRequestJSONObject();

    public JSONObject getReserveJSONObject() {
        RequestParams requestParams = this.target;
        if (requestParams == null) {
            throw new IllegalArgumentException("target is null");
        }
        String reserve = requestParams.getReserve();
        return !TextUtils.isEmpty(reserve) ? new JSONObject(reserve) : new JSONObject();
    }

    public RequestParams getTarget() {
        return this.target;
    }

    public abstract boolean isParamsValid();
}
