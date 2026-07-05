package com.ishumei.O000O00000OoO.O0000O000000oO;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.ishumei.O000O0000OOoO.O000O00000oO;
import com.ishumei.O000O0000OOoO.O000O0000OoO;
import com.ishumei.smantifraud.SmAntiFraud;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O0000O000000oO {
    private AtomicBoolean O0000O000000oO;
    private Map<String, String> O000O00000OoO;
    private Runnable O000O00000o0O;

    /* JADX INFO: renamed from: com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO$O0000O000000oO, reason: collision with other inner class name */
    private static class C0041O0000O000000oO {
        private static final O0000O000000oO O0000O000000oO = new O0000O000000oO();
    }

    private O0000O000000oO() {
        this.O000O00000o0O = new Runnable() { // from class: com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO.1
            @Override // java.lang.Runnable
            public void run() {
                O000O00000oO.O000O00000OoO("IpCacheManager", "refreshAllCache start...");
                try {
                    SmAntiFraud.SmOption smOption = SmAntiFraud.option;
                    for (String str : O0000O000000oO.this.O0000O000000oO(smOption.getUrl(), smOption.getConfUrl(), smOption.getContactUrl(), smOption.getTraceUrl())) {
                        try {
                            String strO0000O000000oO = O0000O000000oO.this.O0000O000000oO(str);
                            O000O00000oO.O000O00000OoO("IpCacheManager", "refreshAllCache lookup: " + str + ", ip: " + strO0000O000000oO);
                            if (!TextUtils.isEmpty(strO0000O000000oO)) {
                                O0000O000000oO.this.O0000O000000oO(str, strO0000O000000oO);
                            }
                        } catch (Exception unused) {
                        }
                    }
                    O000O00000oO.O000O00000OoO("IpCacheManager", "refreshAllCache end...");
                } catch (Throwable unused2) {
                }
            }
        };
        this.O000O00000OoO = new ConcurrentHashMap();
        this.O0000O000000oO = new AtomicBoolean(false);
    }

    public static O0000O000000oO O0000O000000oO() {
        return C0041O0000O000000oO.O0000O000000oO;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String O0000O000000oO(String str) {
        try {
            InetAddress byName = InetAddress.getByName(str);
            if (byName == null) {
                return null;
            }
            String hostAddress = byName.getHostAddress();
            O000O00000oO.O000O00000OoO("IpCacheManager", "lookup host: " + str + ", ip: " + hostAddress);
            if (TextUtils.isEmpty(hostAddress)) {
                return null;
            }
            return hostAddress;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Set<String> O0000O000000oO(String... strArr) {
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            String strO000O0000OoO = O000O0000OoO.O000O0000OoO(str);
            if (!TextUtils.isEmpty(strO000O0000OoO) && !O000O0000OoO.O00O0000OooO(strO000O0000OoO)) {
                hashSet.add(strO000O0000OoO);
            }
        }
        Map<String, String> map = this.O000O00000OoO;
        if (map != null && map.size() > 0) {
            hashSet.addAll(this.O000O00000OoO.keySet());
        }
        return hashSet;
    }

    private void O000O00000OoO(String str, String str2) {
        Context context = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
        if (context == null) {
            O000O00000oO.O000O00000oO("IpCacheManager", "Context is null, can't save to sp.");
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.shumei", 0).edit();
        editorEdit.putString(str, str2);
        editorEdit.putLong(str + "_SUFFIX_TIME", System.currentTimeMillis());
        editorEdit.apply();
    }

    private Map<String, String> O000O00000oO() {
        HashMap map = new HashMap();
        Context context = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
        if (context == null) {
            O000O00000oO.O000O00000oO("IpCacheManager", "Context is null, can't load from sp.");
            return map;
        }
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, ?> entry : context.getSharedPreferences("com.shumei", 0).getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (!key.equals("deviceid")) {
                if (key.endsWith("_SUFFIX_TIME")) {
                    if ((value instanceof Long) && System.currentTimeMillis() - ((Long) value).longValue() > 604800000) {
                        hashSet.add(key.substring(0, key.indexOf("_SUFFIX_TIME")));
                    }
                } else if (value instanceof String) {
                    map.put(key, (String) value);
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            map.remove((String) it.next());
        }
        return map;
    }

    public String O0000O000000oO(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO().O000O00000o0O().O0000O000000oO()) {
            Log.i("IpCacheManager", "IP cache disable, return null.");
            return null;
        }
        if (!this.O0000O000000oO.get()) {
            O000O00000OoO();
        }
        if (O000O0000OoO.O00O0000OooO(str)) {
            return null;
        }
        if (z) {
            return O0000O000000oO(str);
        }
        String str2 = this.O000O00000OoO.get(str);
        return TextUtils.isEmpty(str2) ? O0000O000000oO(str) : str2;
    }

    public synchronized void O0000O000000oO(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (O000O0000OoO.O00O0000OooO(str)) {
                return;
            }
            this.O000O00000OoO.put(str, str2);
            O000O00000OoO(str, str2);
        }
    }

    public void O000O00000OoO() {
        if (this.O0000O000000oO.compareAndSet(false, true)) {
            this.O000O00000OoO.putAll(O000O00000oO());
            com.ishumei.O000O00000o0O.O0000O000000oO.O000O00000OoO().O0000O000000oO(this.O000O00000o0O, 1);
        }
    }

    public Map<String, String> O000O00000o0O() {
        return new HashMap(this.O000O00000OoO);
    }
}
