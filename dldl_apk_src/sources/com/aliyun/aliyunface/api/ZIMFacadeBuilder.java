package com.aliyun.aliyunface.api;

import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ZIMFacadeBuilder {
    private static ZIMFacade s_instance;

    public static synchronized ZIMFacade create(Context context) {
        if (context == null) {
            throw new RuntimeException("context Can't be null");
        }
        if (s_instance == null) {
            s_instance = new ZIMFacade(context);
        }
        return s_instance;
    }
}
