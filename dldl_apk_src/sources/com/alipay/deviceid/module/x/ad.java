package com.alipay.deviceid.module.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class ad {
    static List<ah> a;

    static {
        ArrayList arrayList = new ArrayList();
        a = arrayList;
        arrayList.add(new ak());
        a.add(new ac());
        a.add(new ab());
        a.add(new ag());
        a.add(new aj());
        a.add(new aa());
        a.add(new z());
        a.add(new af());
    }

    public static final <T> T a(Object obj, Type type) {
        T t;
        for (ah ahVar : a) {
            if (ahVar.a(al.a(type)) && (t = (T) ahVar.a(obj, type)) != null) {
                return t;
            }
        }
        return null;
    }

    public static final Object a(String str, Type type) {
        Object xVar;
        if (str == null || str.length() == 0) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.startsWith("[") && strTrim.endsWith("]")) {
            xVar = new w(strTrim);
        } else {
            if (!strTrim.startsWith("{") || !strTrim.endsWith(com.alipay.sdk.util.i.d)) {
                return a((Object) strTrim, type);
            }
            xVar = new x(strTrim);
        }
        return a(xVar, type);
    }
}
