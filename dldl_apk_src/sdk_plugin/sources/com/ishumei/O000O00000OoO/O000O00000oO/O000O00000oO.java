package com.ishumei.O000O00000OoO.O000O00000oO;

import com.huya.statistics.core.StatisticsContent;
import com.ishumei.O000O00000oO.O000O0000Oo0O;
import com.ishumei.O000O00000oO.O00O0000o0O;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000oO {
    private com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO O0000O000000oO;

    private static class O0000O000000oO {
        private static final O000O00000oO O0000O000000oO = new O000O00000oO();
    }

    private O000O00000oO() {
        if (com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO == null) {
            return;
        }
        this.O0000O000000oO = new com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO();
    }

    public static O000O00000oO O0000O000000oO() {
        return O0000O000000oO.O0000O000000oO;
    }

    public synchronized void O0000O000000oO(String str, String str2) {
        if (str2.contains("/v3/profile/android")) {
            str2 = "1";
        } else if (str2.contains("/v3/cloudconf")) {
            str2 = "2";
        }
        this.O0000O000000oO.O0000O000000oO(str, str2, O000O0000Oo0O.O0000O000000oO().O000O0000Oo0O(), O00O0000o0O.O0000O000000oO().O000O00000o0O());
    }

    public synchronized Map<String, String> O000O00000OoO() {
        HashMap map = new HashMap();
        O000O00000OoO o000O00000OoOO0000O000000oO = this.O0000O000000oO.O0000O000000oO();
        if (o000O00000OoOO0000O000000oO == null) {
            return map;
        }
        map.put("ex", o000O00000OoOO0000O000000oO.O000O00000OoO());
        map.put(StatisticsContent.NET, o000O00000OoOO0000O000000oO.O000O00000oO());
        map.put("t", o000O00000OoOO0000O000000oO.O000O00000o0O());
        map.put("url", o000O00000OoOO0000O000000oO.O000O0000O0oO());
        map.put("dns", o000O00000OoOO0000O000000oO.O0000O000000oO());
        return map;
    }
}
