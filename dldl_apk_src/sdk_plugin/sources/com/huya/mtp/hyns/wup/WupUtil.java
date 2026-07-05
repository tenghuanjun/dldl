package com.huya.mtp.hyns.wup;

import com.duowan.jce.wup.UniPacket;
import com.duowan.taf.jce.JceStruct;
import com.huya.mtp.data.exception.ParseException;
import com.huya.mtp.hyns.utils.Reflect;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
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

    public static <T> T getObject(UniPacket uniPacket, String str, T t) throws ParseException {
        if (t == null) {
            return null;
        }
        try {
            return (T) Reflect.on(t.getClass(), uniPacket.getByClass(str, t)).fillNull().get();
        } catch (Exception e) {
            throw new ParseException(e);
        }
    }

    public static int getCode(UniPacket uniPacket, String str) throws ParseException {
        try {
            return ((Integer) uniPacket.get(str, 0, -1)).intValue();
        } catch (Exception e) {
            throw new ParseException("Wup getCode but _newData is null! ", e);
        }
    }
}
