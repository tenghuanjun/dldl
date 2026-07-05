package com.huya.mtp.hyns.miniprogram.socket;

import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ProxySignalProtoMapper {
    private static final HashMap<Integer, Class> sCommandToClassMap = new HashMap<>();
    private static final HashMap<Class, Integer> sClassToCommandMap = new HashMap<>();

    static {
        register(1, Object.class);
    }

    public static void register(int i, Class cls) {
        sCommandToClassMap.put(Integer.valueOf(i), cls);
        sClassToCommandMap.put(cls, Integer.valueOf(i));
    }

    public static boolean isCommandExist(String str) {
        return sCommandToClassMap.containsKey(str);
    }

    public static String classToCommand(Class cls) {
        Integer num = sClassToCommandMap.get(cls);
        return num == null ? "" : num.toString();
    }

    public static Class commandToClass(int i) {
        return sCommandToClassMap.get(Integer.valueOf(i));
    }
}
