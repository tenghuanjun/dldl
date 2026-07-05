package com.duowan.auk.http;

import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.duowan.auk.http.HttpClient;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class PostTask extends HttpTask {
    private byte[] mBody;
    private String mBodyContentType;
    private Map<String, String> mBodyParams;

    PostTask(String str, HttpClient.RequestParams requestParams, HttpClient.HttpHandler httpHandler) {
        super(1, str, requestParams, httpHandler);
        this.mBodyContentType = requestParams.getBodyContentType();
        this.mBodyParams = requestParams.getBodyParams();
        this.mBody = requestParams.getBody();
    }

    @Override // com.android.volley.Request
    public String getBodyContentType() {
        return TextUtils.isEmpty(this.mBodyContentType) ? super.getBodyContentType() : this.mBodyContentType;
    }

    @Override // com.android.volley.Request
    public byte[] getBody() throws AuthFailureError {
        byte[] bArr = this.mBody;
        return bArr == null ? super.getBody() : bArr;
    }

    @Override // com.android.volley.Request
    protected Map<String, String> getParams() throws AuthFailureError {
        return this.mBodyParams;
    }
}
