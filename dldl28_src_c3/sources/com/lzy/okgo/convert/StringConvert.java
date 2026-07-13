package com.lzy.okgo.convert;

import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class StringConvert implements Converter<String> {
    @Override // com.lzy.okgo.convert.Converter
    public String convertResponse(Response response) throws Throwable {
        ResponseBody responseBodyBody = response.body();
        if (responseBodyBody == null) {
            return null;
        }
        return responseBodyBody.string();
    }
}
