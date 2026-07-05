package com.duowan.auk.http.v2.wup;

import com.duowan.auk.util.L;
import com.duowan.jce.wup.UniPacket;
import com.duowan.taf.jce.JceStruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class WupUtil {
    private static final String TAG = "WupUtil";

    public static UniPacket create(String str, String str2, String str3, JceStruct jceStruct, Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(str3, jceStruct);
        return create(str, str2, map);
    }

    public static UniPacket create(String str, String str2, Map<String, Object> map) {
        UniPacket uniPacket = new UniPacket();
        uniPacket.useVersion3();
        uniPacket.setServantName(str);
        uniPacket.setFuncName(str2);
        if (map != null) {
            for (String str3 : map.keySet()) {
                uniPacket.put(str3, map.get(str3));
            }
        }
        return uniPacket;
    }

    public static <T> T getObject(UniPacket uniPacket, String str, T t) {
        try {
            return (T) terminate(uniPacket.getByClass(str, t));
        } catch (Exception e) {
            L.error(TAG, (Throwable) e);
            return null;
        }
    }

    public static int getCode(UniPacket uniPacket, String str) {
        return ((Integer) uniPacket.get(str, 0, 0)).intValue();
    }

    private static <T> T terminate(T t) throws IllegalAccessException, InstantiationException {
        if (t == null) {
            return null;
        }
        return (T) realTerminate(t);
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static <T> T realTerminate(T r8) throws java.lang.IllegalAccessException, java.lang.InstantiationException {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.auk.http.v2.wup.WupUtil.realTerminate(java.lang.Object):java.lang.Object");
    }

    private static boolean userType(Class cls) {
        return (cls == null || cls.isArray() || Collection.class.isAssignableFrom(cls) || cls.equals(Byte.class) || Map.class.isAssignableFrom(cls) || cls.equals(Integer.class) || cls.equals(Long.class) || cls.equals(Boolean.class) || cls.equals(Double.class) || cls.equals(Float.class) || cls.equals(String.class)) ? false : true;
    }
}
