package com.ishumei.O000O00000oO;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.ListIterator;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000o0O {
    private static O000O00000o0O O0000O000000oO;

    public static O000O00000o0O O0000O000000oO() {
        if (O0000O000000oO == null) {
            synchronized (O000O00000o0O.class) {
                if (O0000O000000oO == null) {
                    O0000O000000oO = new O000O00000o0O();
                }
            }
        }
        return O0000O000000oO;
    }

    public HashMap<String, String> O000O00000OoO() {
        HashMap<String, String> map = new HashMap<>();
        try {
            String strO000O0000Oo0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd1908cd1bd8a96939b");
            ListIterator<Field> listIterator = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O000O00000OoO(strO000O0000Oo0O).O000O00000o0O().O0000O000000oO().listIterator();
            while (listIterator.hasNext()) {
                Field next = listIterator.next();
                next.setAccessible(true);
                String lowerCase = next.getName().toLowerCase();
                if (com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9d909e8d9bd392909b9a93d38c9a8d969e93d39d8d9e919bd3929e918a999e9c8b8a8d9a8dd3999691989a8d8f8d96918bd39c8f8aa09e9d96d39c8f8aa09e9d96cd").contains(lowerCase)) {
                    map.put(lowerCase, next.get(null).toString());
                }
            }
            Object objO0000O000000oO = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O000O00000OoO(strO000O0000Oo0O).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bac9a8d969e93")).O0000O000000oO();
            if (objO0000O000000oO != null) {
                map.put(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c9a8d969e93a0af"), objO0000O000000oO.toString());
            }
        } catch (Throwable unused) {
        }
        return map;
    }
}
