package com.alipay.deviceid.module.x;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class ac implements ah, ai {
    @Override // com.alipay.deviceid.module.x.ai
    public final Object a(Object obj) {
        return ((Enum) obj).name();
    }

    @Override // com.alipay.deviceid.module.x.ah
    public final Object a(Object obj, Type type) {
        return Enum.valueOf((Class) type, obj.toString());
    }

    @Override // com.alipay.deviceid.module.x.ah, com.alipay.deviceid.module.x.ai
    public final boolean a(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }
}
