package com.lzy.okgo.callback;

import com.lzy.okgo.convert.StringConvert;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public abstract class StringCallback extends AbsCallback<String> {
    private StringConvert convert = new StringConvert();

    @Override // com.lzy.okgo.convert.Converter
    public String convertResponse(Response response) throws Throwable {
        String strConvertResponse = this.convert.convertResponse(response);
        response.close();
        return strConvertResponse;
    }
}
