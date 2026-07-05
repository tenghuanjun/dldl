package com.alipay.deviceid.module.x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class ae {
    private static List<ai> a;

    static {
        ArrayList arrayList = new ArrayList();
        a = arrayList;
        arrayList.add(new ak());
        a.add(new ac());
        a.add(new ab());
        a.add(new ag());
        a.add(new aa());
        a.add(new z());
        a.add(new af());
    }

    public static String a(Object obj) {
        if (obj == null) {
            return null;
        }
        Object objB = b(obj);
        if (al.a(objB.getClass())) {
            return x.b(objB.toString());
        }
        if (Collection.class.isAssignableFrom(objB.getClass())) {
            return new w((Collection) objB).toString();
        }
        if (Map.class.isAssignableFrom(objB.getClass())) {
            return new x((Map) objB).toString();
        }
        throw new IllegalArgumentException("Unsupported Class : " + objB.getClass());
    }

    public static Object b(Object obj) {
        Object objA;
        if (obj == null) {
            return null;
        }
        for (ai aiVar : a) {
            if (aiVar.a(obj.getClass()) && (objA = aiVar.a(obj)) != null) {
                return objA;
            }
        }
        throw new IllegalArgumentException("Unsupported Class : " + obj.getClass());
    }
}
