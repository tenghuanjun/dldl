package com.unionpay.tsmservice.mini.result.wrapper;

import android.os.Bundle;
import com.unionpay.tsmservice.mini.ITsmCallback;
import org.json.JSONException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public abstract class BaseResultCallbackWrapper extends ITsmCallback.Stub {
    protected int interfaceId;
    protected ITsmCallback target;

    public BaseResultCallbackWrapper(int i, ITsmCallback iTsmCallback) {
        this.target = iTsmCallback;
        this.interfaceId = i;
    }

    protected abstract Bundle convertResult(Bundle bundle);

    @Override // com.unionpay.tsmservice.mini.ITsmCallback
    public void onError(String str, String str2) {
        ITsmCallback iTsmCallback = this.target;
        if (iTsmCallback != null) {
            iTsmCallback.onError(str, str2);
        }
    }

    @Override // com.unionpay.tsmservice.mini.ITsmCallback
    public void onResult(Bundle bundle) {
        try {
            convertResult(bundle);
            bundle.remove("interfaceId");
        } catch (JSONException e) {
            e.printStackTrace();
            bundle.putString("errorCode", this.interfaceId + "00001");
        }
        if ("10000".equalsIgnoreCase(bundle.getString("errorCode"))) {
            ITsmCallback iTsmCallback = this.target;
            if (iTsmCallback != null) {
                iTsmCallback.onResult(bundle);
                return;
            }
            return;
        }
        ITsmCallback iTsmCallback2 = this.target;
        if (iTsmCallback2 != null) {
            iTsmCallback2.onError(bundle.getString("errorCode"), bundle.getString("errorDesc"));
        }
    }
}
