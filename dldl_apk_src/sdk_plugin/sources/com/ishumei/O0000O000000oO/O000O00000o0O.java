package com.ishumei.O0000O000000oO;

import android.os.Build;
import com.ishumei.O000O00000oO.O00O0000o00O;
import com.ishumei.O000O00000oO.O00O0000o0OO;
import com.ishumei.smantifraud.SmAntiFraud;
import com.sqwan.msdk.api.IMUrl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000o0O implements O000O00000oO {
    private static final String O0000O000000oO = O000O00000o0O.class.getCanonicalName();
    private static String O000O00000OoO;
    private static String O000O00000o0O;
    private static String O000O00000oO;
    private static String O000O0000O0oO;
    private static String O000O0000OOoO;
    private static String O000O0000Oo0O;
    private static String O000O0000OoO;
    private static String O000O00oOoOoO;
    private static String O00O0000OooO;
    private static String O00O0000o00O;
    private static String O00O0000o0O;
    private static String O00O0000o0OO;
    private static String O00O0000oO;
    private static String O00O0000oO0O;
    private static String O00O0000oOO;
    private static String O00O000O00oO;
    private static String O00O000O0OOO;
    private static String O00O000O0o0O;
    private static O000O00000o0O O00O000O0oO;
    private static String O00O00oOooOO;

    static {
        try {
            O000O00000OoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8d8b868f9a");
            O000O00000o0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("908c");
            O000O00000oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8b");
            O000O0000O0oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("908c899a8d");
            O000O0000OOoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c9b94899a8d");
            O000O0000Oo0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8f899a8d");
            O000O0000OoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8f919e929a");
            O00O0000OooO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e9b969b");
            O00O0000o00O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c868c");
            O00O0000o0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c928c");
            O00O0000o0OO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c90918b9e9c8b");
            O00O0000oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c9e9393b39098");
            O00O0000oO0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("96929a96");
            O00O0000oOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c92969b");
            O00O00oOooOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9996919e919c9ab6919990");
            O00O000O00oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c92969b8c8b9e8b");
            O00O000O0OOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("919a8b");
            O000O00oOoOoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9d90908b");
            O00O000O0o0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8fb69b");
        } catch (Exception unused) {
        }
        O00O000O0oO = null;
    }

    public static O000O00000o0O O0000O000000oO() {
        if (O00O000O0oO == null) {
            synchronized (O000O00000o0O.class) {
                if (O00O000O0oO == null) {
                    O00O000O0oO = new O000O00000o0O();
                }
            }
        }
        return O00O000O0oO;
    }

    @Override // com.ishumei.O0000O000000oO.O000O00000oO
    public Map<String, Object> O0000O000000oO(int i) {
        String str;
        Object obj;
        HashMap map = new HashMap();
        try {
            Set<String> notCollect = SmAntiFraud.option.getNotCollect();
            if (notCollect == null) {
                notCollect = Collections.emptySet();
            }
            map.put(O000O00000OoO, com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9996919e919c9a"));
            map.put(O000O00000o0O, IMUrl.OS);
            map.put(O000O0000O0oO, Build.VERSION.RELEASE);
            map.put(O00O000O0o0O, SmAntiFraud.option.getAppId());
            map.put(O000O0000OOoO, "2.8.4");
            map.put(O000O00000oO, Long.valueOf(System.currentTimeMillis()));
            map.put(O000O0000OoO, com.ishumei.O000O00000oO.O000O00000OoO.O0000O000000oO().O000O00000o0O());
            map.put(O000O0000Oo0O, com.ishumei.O000O00000oO.O000O00000OoO.O0000O000000oO().O000O00000OoO());
            if (!notCollect.contains(O00O0000oO0O)) {
                map.put(O00O0000oO0O, O00O0000o0OO.O0000O000000oO().O000O00000o0O());
            }
            map.put(O00O0000OooO, O00O0000o00O.O0000O000000oO().O000O00000OoO());
            map.put(O00O0000o00O, com.ishumei.O000O00000oO.O000O00000o0O.O0000O000000oO().O000O00000OoO());
            HashMap map2 = new HashMap();
            if (!notCollect.contains(O00O0000o0O)) {
                try {
                    map2.put(O00O0000o0O, com.ishumei.O000O00000oO.O000O00000oO.O0000O000000oO().O000O00000oO());
                } catch (Exception e) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000OoO(O0000O000000oO, "get message failed:" + e.getMessage());
                }
            }
            if (!notCollect.contains(O00O0000o0OO)) {
                try {
                    map2.put(O00O0000o0OO, com.ishumei.O000O00000oO.O000O00000oO.O0000O000000oO().O000O00000o0O());
                } catch (Exception e2) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000OoO(O0000O000000oO, "get contact failed:" + e2.getMessage());
                }
            }
            if (!notCollect.contains(O00O0000oO)) {
                try {
                    map2.put(O00O0000oO, com.ishumei.O000O00000oO.O000O00000oO.O0000O000000oO().O000O00000OoO());
                } catch (Exception e3) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000OoO(O0000O000000oO, "get call failed:" + e3.getMessage());
                }
            }
            map.put(O00O00oOooOO, map2);
            map.put(O00O0000oOO, O000O0000OoO.O0000O000000oO().O000O00000o0O());
            map.put(O00O000O00oO, O000O0000OoO.O0000O000000oO().O000O00000OoO());
            if (!notCollect.contains(O00O000O0OOO)) {
                List<String> listO000O0000OoO = com.ishumei.O000O00000oO.O000O0000Oo0O.O0000O000000oO().O000O0000OoO();
                if (listO000O0000OoO == null) {
                    str = O00O000O0OOO;
                    obj = AbstractJsonLexerKt.NULL;
                } else if ((i & 1) == 1) {
                    ArrayList arrayList = new ArrayList(listO000O0000OoO.size());
                    Iterator<String> it = listO000O0000OoO.iterator();
                    while (it.hasNext()) {
                        arrayList.add(com.ishumei.O000O0000OOoO.O000O0000OoO.O000O0000OOoO(it.next()));
                    }
                    map.put(O00O000O0OOO, arrayList);
                } else {
                    str = O00O000O0OOO;
                    obj = listO000O0000OoO;
                }
                map.put(str, obj);
            }
            map.put(O000O00oOoOoO, Long.valueOf(O00O0000o00O.O0000O000000oO().O000O00000o0O()));
            return map;
        } catch (Exception e4) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO(O0000O000000oO, "finance collect failed: " + e4);
            return map;
        }
    }
}
