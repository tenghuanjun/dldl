package com.alipay.deviceid.module.x;

import java.lang.reflect.Type;
import java.util.Date;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class ab implements ah, ai {
    @Override // com.alipay.deviceid.module.x.ai
    public final Object a(Object obj) {
        return Long.valueOf(((Date) obj).getTime());
    }

    @Override // com.alipay.deviceid.module.x.ah
    public final Object a(Object obj, Type type) {
        return new Date(((Long) obj).longValue());
    }

    @Override // com.alipay.deviceid.module.x.ah, com.alipay.deviceid.module.x.ai
    public final boolean a(Class<?> cls) {
        return Date.class.isAssignableFrom(cls);
    }
}
