package com.ishumei.O0000O000000oO;

import com.ishumei.O000O00000oO.O00O0000OooO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O0000O0oO implements O000O00000oO {

    private static class O0000O000000oO {
        private static final O000O0000O0oO O0000O000000oO = new O000O0000O0oO();
    }

    private O000O0000O0oO() {
    }

    public static O000O0000O0oO O0000O000000oO() {
        return O0000O000000oO.O0000O000000oO;
    }

    @Override // com.ishumei.O0000O000000oO.O000O00000oO
    public Map<String, Object> O0000O000000oO(int i) {
        return null;
    }

    public Map<String, Object> O0000O000000oO(int i, List<O00O0000OooO.O000O0000O0oO> list, String str) {
        Map<String, Object> mapO0000O000000oO = O000O00000OoO.O0000O000000oO().O0000O000000oO(i);
        mapO0000O000000oO.put("rtype", "sensor");
        if (list != null && list.size() != 0) {
            ArrayList arrayList = new ArrayList();
            for (O00O0000OooO.O000O0000O0oO o000O0000O0oO : list) {
                HashMap map = new HashMap();
                Map<Long, Object> mapO0000O000000oO2 = o000O0000O0oO.O0000O000000oO(str);
                if (mapO0000O000000oO2 != null) {
                    map.put("stype", o000O0000O0oO.O000O0000OOoO());
                    ArrayList arrayList2 = new ArrayList();
                    for (Map.Entry<Long, Object> entry : mapO0000O000000oO2.entrySet()) {
                        Long key = entry.getKey();
                        Object value = entry.getValue();
                        HashMap map2 = new HashMap();
                        map2.put("v", value);
                        map2.put("t", key);
                        arrayList2.add(map2);
                    }
                    map.put("value", arrayList2);
                    arrayList.add(map);
                }
            }
            mapO0000O000000oO.put("sensor", arrayList);
            mapO0000O000000oO.put("t", Long.valueOf(System.currentTimeMillis()));
        }
        return mapO0000O000000oO;
    }
}
