package com.sq.tool.network;

import com.sdk.sq.net.SqRequestCallback;
import com.sqnetwork.voly.VolleyError;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class SqHttpCallback<Data> extends SqRequestCallback<Data> {
    private static final String KEY_DATA = "data";
    private static final String KEY_ERROR_CODE = "state";
    private static final String KEY_ERROR_MSG = "msg";
    private static final int STATE_OK = 1;
    private JSONObject mResp;

    public static abstract class SimpleSqHttpCallback<Data> extends SqHttpCallback<Data> {
        @Override // com.sq.tool.network.SqHttpCallback
        public void onFailure(int i, String str, VolleyError volleyError) {
        }

        @Override // com.sdk.sq.net.SqRequestCallback
        public void onResponseStateError(int i, int i2, String str, String str2) {
        }
    }

    @Override // com.sdk.sq.net.SqRequestCallback
    protected String getDataKey() {
        return "data";
    }

    @Override // com.sdk.sq.net.SqRequestCallback
    protected String getMsgKey() {
        return "msg";
    }

    @Override // com.sdk.sq.net.SqRequestCallback
    protected int getOkState() {
        return 1;
    }

    @Override // com.sdk.sq.net.SqRequestCallback
    protected String getStateKey() {
        return KEY_ERROR_CODE;
    }

    public abstract void onFailure(int i, String str, VolleyError volleyError);

    public abstract void onSuccess(Data data);

    public SqHttpCallback() {
    }

    public SqHttpCallback(Class<Data> cls) {
        super(cls);
    }

    @Override // com.sdk.sq.net.SqRequestCallback, com.sdk.sq.net.RequestBuilder.RequestCallback
    public void onSuccess(int i, Map<String, String> map, JSONObject jSONObject) {
        this.mResp = jSONObject;
        super.onSuccess(i, map, jSONObject);
    }

    @Override // com.sdk.sq.net.SqRequestCallback
    public void onRequestSuccess(int i, Data data) {
        onSuccess(data);
    }

    @Override // com.sdk.sq.net.SqRequestCallback
    public void onRequestError(int i, VolleyError volleyError) {
        onFailure(i, VolleyErrorUtil.simpleErrorMsg(volleyError), volleyError);
    }

    public static String wrapStr(int i, String str) {
        return "state=" + i + "(" + str + ")";
    }

    protected void setResp(JSONObject jSONObject) {
        this.mResp = jSONObject;
    }

    @Override // com.sdk.sq.net.SqRequestCallback
    public JSONObject getResponse() {
        return this.mResp;
    }

    @Override // com.sdk.sq.net.SqRequestCallback
    public String getResponseStr() {
        JSONObject jSONObject = this.mResp;
        return jSONObject == null ? "" : jSONObject.toString();
    }
}
