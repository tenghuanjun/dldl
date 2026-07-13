package com.bytedance.http;

import com.tencent.connect.common.Constants;

/* JADX INFO: loaded from: classes2.dex */
public enum HttpMethod {
    GET(Constants.HTTP_GET),
    POST(Constants.HTTP_POST);

    private final String method;

    HttpMethod(String str) {
        this.method = str;
    }

    public final String getValue() {
        return this.method;
    }
}
