package com.huya.live.service;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ServiceHelper {
    public static void createService(Class cls, Class<? extends AbsService> cls2) {
        try {
            AbsService absServiceNewInstance = cls2.newInstance();
            absServiceNewInstance.onCreate();
            ServiceCenter.instance().addService(cls.getName(), absServiceNewInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        }
    }
}
