package com.ishumei.O000O00000oO;

import android.content.Context;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O00O0000o0O {
    private static O00O0000o0O O0000O000000oO;

    public static O00O0000o0O O0000O000000oO() {
        if (O0000O000000oO == null) {
            synchronized (O00O0000o0O.class) {
                if (O0000O000000oO == null) {
                    O0000O000000oO = new O00O0000o0O();
                }
            }
        }
        return O0000O000000oO;
    }

    public String O0000O000000oO(String str) {
        try {
            Class<?> clsLoadClass = Context.class.getClassLoader().loadClass(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd1908cd1ac868c8b9a92af8d908f9a8d8b969a8c"));
            Method method = clsLoadClass.getMethod(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8b"), String.class);
            method.setAccessible(true);
            String str2 = (String) method.invoke(clsLoadClass, str);
            return str2 == null ? "" : str2;
        } catch (Exception unused) {
            return "";
        }
    }

    public HashMap<String, String> O000O00000OoO() {
        HashMap<String, String> map = new HashMap<>();
        try {
            Class<?> clsLoadClass = Context.class.getClassLoader().loadClass(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd1908cd1ac868c8b9a92af8d908f9a8d8b969a8c"));
            Method method = clsLoadClass.getMethod(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8b"), String.class);
            method.setAccessible(true);
            String[] strArr = {com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8d90d19b9a9d8a98989e9d939a"), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8d90d18c9a8d969e939190"), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8d90d19d90908bd18c9a8d969e939190"), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("988c92d1919a8b88908d94d18b868f9a"), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("988c92d18c9692d18c8b9e8b9a"), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8f9a8d8c968c8bd18c868cd19c908a918b8d86"), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8f9a8d8c968c8bd18c868cd1939e91988a9e989a"), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c868cd18a8c9dd18c8b9e8b9a")};
            for (int i = 0; i < 8; i++) {
                String str = strArr[i];
                String str2 = (String) method.invoke(clsLoadClass, str);
                if (str2 != null && !str2.isEmpty()) {
                    map.put(str, str2);
                }
            }
        } catch (Exception e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
        }
        return map;
    }

    public String O000O00000o0O() {
        ArrayList arrayList = new ArrayList();
        try {
            Class<?> clsLoadClass = Context.class.getClassLoader().loadClass(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e919b8d90969bd1908cd1ac868c8b9a92af8d908f9a8d8b969a8c"));
            Method method = clsLoadClass.getMethod(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8b"), String.class);
            method.setAccessible(true);
            String[] strArr = {com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("919a8bd19b918cce"), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("919a8bd19b918ccd"), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("919a8bd19b918ccc"), com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("919a8bd19b918ccb")};
            for (int i = 0; i < 4; i++) {
                String str = (String) method.invoke(clsLoadClass, strArr[i]);
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                }
            }
            return TextUtils.join(",", arrayList);
        } catch (Exception e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
            return "";
        }
    }
}
