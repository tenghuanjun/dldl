package com.lzy.okgo.request;

import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.request.base.BodyRequest;
import okhttp3.Request;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class PatchRequest<T> extends BodyRequest<T, PatchRequest<T>> {
    public PatchRequest(String str) {
        super(str);
    }

    @Override // com.lzy.okgo.request.base.Request
    public HttpMethod getMethod() {
        return HttpMethod.PATCH;
    }

    @Override // com.lzy.okgo.request.base.Request
    public Request generateRequest(RequestBody requestBody) {
        return generateRequestBuilder(requestBody).patch(requestBody).url(this.url).tag(this.tag).build();
    }
}
