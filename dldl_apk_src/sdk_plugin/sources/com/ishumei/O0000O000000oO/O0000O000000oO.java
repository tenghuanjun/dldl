package com.ishumei.O0000O000000oO;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.ishumei.O000O00000OoO.O000O00000OoO;
import com.ishumei.O000O00000oO.O00O0000o00O;
import com.ishumei.O000O00000oO.O00O0000o0O;
import com.ishumei.O000O00000oO.O00O0000o0OO;
import com.ishumei.O000O00000oO.O00O0000oO;
import com.ishumei.smantifraud.BuildConfig;
import com.ishumei.smantifraud.SmAntiFraud;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.msdk.api.IMUrl;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O0000O000000oO implements O000O00000oO {
    private static String O0000O000000oO;
    private static String O000O00000OoO;
    private static String O000O00000o0O;
    private static String O000O00000oO;
    private static String O000O0000O0oO;
    private static String O000O0000OOoO;
    private static String O000O0000Oo0O;
    private static String O000O0000OoO;
    private static String O000O000OOoO;
    private static String O000O000OoOOO;
    private static String O000O000o0O;
    private static String O000O000oO0O;
    private static String O000O000ooo0O;
    private static String O000O00O00oOO;
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
    private static String O00O000O0oO;
    private static String O00O000OO00O;
    private static String O00O000OO0oO;
    private static String O00O000OOO;
    private static String O00O000OOOoO;
    private static String O00O000OOo0O;
    private static String O00O000Oo0O;
    private static String O00O000OoOO;
    private static String O00O000o000O;
    private static String O00O000o00O;
    private static String O00O000oO;
    private static String O00O00O000oO;
    private static String O00O00O00OoO;
    private static String O00O00O00o0O;
    private static String O00O00O00oO;
    private static String O00O00O0O0oO;
    private static String O00O00O0OOoO;
    private static String O00O00O0Oo0O;
    private static String O00O00O0OoO;
    private static String O00O00O0o00O;
    private static String O00O00O0o0O;
    private static String O00O00OO0OO;
    private static String O00O00oOooOO;
    private static String O00oooOoOO;
    private static String O0O00O0o0oO;
    private static String O0O00O0oO;
    private static String O0O00O0oO0O;
    private static String O0O00O0oOOO;
    private static String O0O00O0ooO;
    private static String O0O00OO0oO;
    private static String O0O00OOOoO;
    private static O0000O000000oO O0O00OOo0O;

    static {
        try {
            O0000O000000oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("908c");
            O000O00000OoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8b");
            O000O00000o0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("908c899a8d");
            O000O00000oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c9b94899a8d");
            O000O0000O0oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8f899a8d");
            O000O0000OOoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8f8a8b92");
            O000O0000Oo0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("919e929a");
            O000O0000OoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9d8d9e919b");
            O00O0000OooO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("92909b9a93");
            O00O0000o00O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9d9e919b");
            O00O0000o0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("919a8b88908d94");
            O00O0000o0OO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("908f9a8d9e8b908d");
            O00O0000oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("929e9c");
            O00O0000oO0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c8c969b");
            O00O0000oOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9d8c8c969b");
            O00O00oOooOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("88969996968f");
            O00O000O00oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c92969b");
            O00O000O0OOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("96929a96");
            O000O00oOoOoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("96929a96ce");
            O00O000O0o0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("96929a96cd");
            O00O000O0oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("96928c96");
            O00O000OO00O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("969c9c969b");
            O00O000OO0oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e9b969b");
            O00O000OOOoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9d90908b");
            O00O000OOo0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c9c8d9a9a91");
            O000O000OOoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9d8d9698978b919a8c8c");
            O00O000Oo0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c8f8ab2909b9a93");
            O00O00O0OoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c8f8aa99a919b908d");
            O00O000OoOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c8f8abc908a918b");
            O000O000OoOOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c8f8ab98d9a8e");
            O00O000o000O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8c");
            O00O000o00O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c9a9393");
            O000O000o0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8f8c");
            O00O000oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c868c");
            O000O000oO0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("919a8b");
            O000O000ooo0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8f8d908f8c");
            O00oooOoOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c9a918c908d");
            O00O00O000oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("929a92");
            O00O00O00OoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9a928a");
            O00O00O00o0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8f8d96899e9c86");
            O00O00O00oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8d8b868f9a");
            O000O00O00oOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e9d8b929e9c");
            O00O00O0O0oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e878f908c9a9b");
            O00O00O0OOoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e96919990");
            O00O00O0Oo0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c928c9a8e");
            O00O00O0o00O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8897968b9a9e8f8f");
            O00O00O0o0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8d968c949e8f8f");
            O0O00O0o0oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8d968c949b968d");
            O0O00O0oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9c9c929bca");
            O0O00O0oOOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8fb69b");
            O0O00O0oO0O = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9996939a8c");
            O0O00O0ooO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("96918f8a8b");
            O00O000OOO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8f969b");
            O0O00OOOoO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("968fa09c9e9c979a");
            O00O00OO0OO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("919a8ba09a8d8d908d");
            O0O00OO0oO = com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("999ca08b");
        } catch (Exception unused) {
        }
        O0O00OOo0O = null;
    }

    public static O0000O000000oO O0000O000000oO() {
        if (O0O00OOo0O == null) {
            synchronized (O0000O000000oO.class) {
                if (O0O00OOo0O == null) {
                    O0O00OOo0O = new O0000O000000oO();
                }
            }
        }
        return O0O00OOo0O;
    }

    private Map<String, Object> O0000O000000oO(Map<String, O000O00000OoO.C0042O000O00000OoO> map) {
        int i;
        HashMap map2 = new HashMap();
        if (map == null || map.size() == 0 || com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO == null) {
            return map2;
        }
        PackageManager packageManager = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO.getPackageManager();
        HashMap map3 = new HashMap();
        for (Map.Entry<String, O000O00000OoO.C0042O000O00000OoO> entry : map.entrySet()) {
            String key = entry.getKey();
            O000O00000OoO.C0042O000O00000OoO value = entry.getValue();
            map3.put(value.O000O00000OoO(), key);
            try {
                if (packageManager.getLaunchIntentForPackage(value.O000O00000OoO()) != null) {
                    map2.put(key, 1);
                }
                if (packageManager.getPackageInfo(value.O000O00000OoO(), 0) != null) {
                    map2.put(key, 1);
                }
            } catch (Exception unused) {
            }
            if (new File("/data/app/" + value.O000O00000OoO() + "-1/").exists()) {
                i = 1;
            } else {
                if (new File("/data/app/" + value.O000O00000OoO() + "-2/").exists()) {
                    i = 1;
                }
            }
            map2.put(key, i);
        }
        try {
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(0)) {
                if (map3.containsKey(applicationInfo.packageName)) {
                    map2.put(map3.get(applicationInfo.packageName), 1);
                }
            }
            for (PackageInfo packageInfo : installedPackages) {
                if (map3.containsKey(packageInfo.packageName)) {
                    map2.put(map3.get(packageInfo.packageName), 1);
                }
            }
        } catch (Exception unused2) {
        }
        return map2;
    }

    private static Map<String, Object> O000O00000OoO(Map<String, O000O00000OoO.O000O00000o0O> map) {
        int i;
        HashMap map2 = new HashMap();
        if (map != null && map.size() != 0) {
            for (Map.Entry<String, O000O00000OoO.O000O00000o0O> entry : map.entrySet()) {
                try {
                    String key = entry.getKey();
                    O000O00000OoO.O000O00000o0O value = entry.getValue();
                    if (value.O000O00000o0O() == 0) {
                        if (com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO(value.O000O00000OoO())) {
                            i = 1;
                            map2.put(key, i);
                        }
                    } else if (1 == value.O000O00000o0O() && com.ishumei.O000O0000OOoO.O000O0000OoO.O000O00000OoO(value.O000O00000OoO())) {
                        i = 1;
                        map2.put(key, i);
                    }
                } catch (Exception unused) {
                }
            }
        }
        return map2;
    }

    @Override // com.ishumei.O0000O000000oO.O000O00000oO
    public Map<String, Object> O0000O000000oO(int i) {
        Map<String, O000O00000OoO.O000O00000o0O> mapO00O000O00oO;
        String str;
        String strO000O00000OoO;
        Map<String, O000O00000OoO.C0042O000O00000OoO> mapO00O00oOooOO;
        O0000O000000oO o0000O000000oO;
        String str2;
        String strO000O00000oO;
        String str3;
        String strO000O0000O0oO;
        String str4;
        String strO000O00000o0O;
        String str5;
        String strO0000O000000oO;
        String str6;
        String strO0000O000000oO2;
        String str7;
        String strO000O00000o0O2;
        String str8;
        String str9;
        Object obj;
        String strO00O0000oOO = "";
        com.ishumei.O000O00000OoO.O000O00000OoO o000O00000OoOO000O00000o0O = com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO().O000O00000o0O();
        HashMap map = new HashMap();
        try {
            Set<String> notCollect = SmAntiFraud.option.getNotCollect();
            if (notCollect == null) {
                notCollect = Collections.emptySet();
            }
            Set<String> set = notCollect;
            mapO00O000O00oO = null;
            Set<String> setO000O00oOoOoO = o000O00000OoOO000O00000o0O == null ? null : o000O00000OoOO000O00000o0O.O000O00oOoOoO();
            map.put(O00O00O00oO, SqConstants.PURCHASE_DETAIL_ALL);
            O000O0000OoO.O0000O000000oO().O000O00000oO();
            map.put(O00O000O00oO, O000O0000OoO.O0000O000000oO().O000O00000o0O());
            int i2 = i & 1;
            map.put(O00O00O00o0O, i2 == 1 ? FeedBackConstants.KEY_LOG_MD5 : "none");
            map.put(O00O00O0Oo0O, O000O0000OOoO.O0000O000000oO().O000O00000OoO());
            map.put(O000O0000OOoO, SmAntiFraud.option.getChannel());
            map.put(O0000O000000oO, IMUrl.OS);
            map.put(O000O00000oO, "2.8.4");
            map.put("sdk_flavor", BuildConfig.FLAVOR);
            map.put(O000O00000OoO, Long.valueOf(System.currentTimeMillis()));
            map.put(O000O00000o0O, Build.VERSION.RELEASE);
            map.put(O0O00O0oOOO, SmAntiFraud.option.getAppId());
            map.put(O00O000OOO, Integer.valueOf(Process.myPid()));
            map.put(O00O0000OooO, Build.MODEL);
            if (!set.contains(O000O00O00oOO)) {
                map.put(O000O00O00oOO, com.ishumei.O000O00000oO.O0000O000000oO.O0000O000000oO().O000O00000o0O());
            }
            int iO00O0000OooO = 0;
            if (setO000O00oOoOoO != null && setO000O00oOoOoO.contains("ainfo")) {
                map.put(O00O00O0O0oO, "" + com.ishumei.O000O00000oO.O0000O000000oO.O0000O000000oO().O000O00000oO());
                com.ishumei.O000O00000oO.O0000O000000oO O0000O000000oO2 = com.ishumei.O000O00000oO.O0000O000000oO.O0000O000000oO();
                String str10 = O00O00O0OOoO;
                if (o000O00000OoOO000O00000o0O != null) {
                    strO00O0000oOO = o000O00000OoOO000O00000o0O.O00O0000oOO();
                }
                O0000O000000oO2.O0000O000000oO(map, str10, true, strO00O0000oOO, o000O00000OoOO000O00000o0O != null && o000O00000OoOO000O00000o0O.O000O0000OoO());
            }
            if (!set.contains(O000O000oO0O)) {
                List<String> listO000O0000OoO = com.ishumei.O000O00000oO.O000O0000Oo0O.O0000O000000oO().O000O0000OoO();
                if (listO000O0000OoO == null) {
                    str9 = O000O000oO0O;
                    obj = AbstractJsonLexerKt.NULL;
                } else if (i2 == 1) {
                    ArrayList arrayList = new ArrayList(listO000O0000OoO.size());
                    Iterator<String> it = listO000O0000OoO.iterator();
                    while (it.hasNext()) {
                        arrayList.add(com.ishumei.O000O0000OOoO.O000O0000OoO.O000O0000OOoO(it.next()));
                    }
                    str9 = O000O000oO0O;
                    obj = arrayList;
                } else {
                    map.put(O000O000oO0O, listO000O0000OoO);
                }
                map.put(str9, obj);
            }
            HashMap<String, String> mapO000O00000OoO = O00O0000o0O.O0000O000000oO().O000O00000OoO();
            if (mapO000O00000OoO != null) {
                if (i2 == 1 && (str8 = mapO000O00000OoO.get("ro.serialno")) != null) {
                    mapO000O00000OoO.put("ro.serialno", com.ishumei.O000O0000OOoO.O000O0000OoO.O000O0000OOoO(str8));
                }
                map.put(O000O000ooo0O, mapO000O00000OoO);
            }
            if (setO000O00oOoOoO != null && setO000O00oOoOoO.contains(O00O0000oOO) && !set.contains(O00O0000oOO)) {
                if (i2 == 1) {
                    str7 = O00O0000oOO;
                    strO000O00000o0O2 = com.ishumei.O000O0000OOoO.O000O0000OoO.O000O0000OOoO(com.ishumei.O000O00000oO.O000O0000Oo0O.O0000O000000oO().O000O00000o0O());
                } else {
                    str7 = O00O0000oOO;
                    strO000O00000o0O2 = com.ishumei.O000O00000oO.O000O0000Oo0O.O0000O000000oO().O000O00000o0O();
                }
                map.put(str7, strO000O00000o0O2);
            }
            if (!set.contains(O00O000O0OOO)) {
                if (i2 == 1) {
                    str4 = O00O000O0OOO;
                    strO000O00000o0O = com.ishumei.O000O0000OOoO.O000O0000OoO.O000O0000OOoO(O00O0000o0OO.O0000O000000oO().O000O00000o0O());
                } else {
                    str4 = O00O000O0OOO;
                    strO000O00000o0O = O00O0000o0OO.O0000O000000oO().O000O00000o0O();
                }
                map.put(str4, strO000O00000o0O);
                if (i2 == 1) {
                    str5 = O000O00oOoOoO;
                    strO0000O000000oO = com.ishumei.O000O0000OOoO.O000O0000OoO.O000O0000OOoO(O00O0000o0OO.O0000O000000oO().O0000O000000oO(1));
                } else {
                    str5 = O000O00oOoOoO;
                    strO0000O000000oO = O00O0000o0OO.O0000O000000oO().O0000O000000oO(1);
                }
                map.put(str5, strO0000O000000oO);
                if (i2 == 1) {
                    str6 = O00O000O0o0O;
                    strO0000O000000oO2 = com.ishumei.O000O0000OOoO.O000O0000OoO.O000O0000OOoO(O00O0000o0OO.O0000O000000oO().O0000O000000oO(2));
                } else {
                    str6 = O00O000O0o0O;
                    strO0000O000000oO2 = O00O0000o0OO.O0000O000000oO().O0000O000000oO(2);
                }
                map.put(str6, strO0000O000000oO2);
            }
            if (i2 == 1) {
                str = O00O000OO0oO;
                strO000O00000OoO = com.ishumei.O000O0000OOoO.O000O0000OoO.O000O0000OOoO(O00O0000o00O.O0000O000000oO().O000O00000OoO());
            } else {
                str = O00O000OO0oO;
                strO000O00000OoO = O00O0000o00O.O0000O000000oO().O000O00000OoO();
            }
            map.put(str, strO000O00000OoO);
            if (setO000O00oOoOoO != null && setO000O00oOoOoO.contains(O00O000O0oO) && !set.contains(O00O000O0oO)) {
                if (i2 == 1) {
                    str3 = O00O000O0oO;
                    strO000O0000O0oO = com.ishumei.O000O0000OOoO.O000O0000OoO.O000O0000OOoO(O00O0000o0OO.O0000O000000oO().O000O0000O0oO());
                } else {
                    str3 = O00O000O0oO;
                    strO000O0000O0oO = O00O0000o0OO.O0000O000000oO().O000O0000O0oO();
                }
                map.put(str3, strO000O0000O0oO);
            }
            if (setO000O00oOoOoO != null && setO000O00oOoOoO.contains(O00O0000oO) && !set.contains(O00O0000oO)) {
                if (i2 == 1) {
                    str2 = O00O0000oO;
                    strO000O00000oO = com.ishumei.O000O0000OOoO.O000O0000OoO.O000O0000OOoO(com.ishumei.O000O00000oO.O000O0000Oo0O.O0000O000000oO().O000O00000oO());
                } else {
                    str2 = O00O0000oO;
                    strO000O00000oO = com.ishumei.O000O00000oO.O000O0000Oo0O.O0000O000000oO().O000O00000oO();
                }
                map.put(str2, strO000O00000oO);
            }
            if (setO000O00oOoOoO != null && setO000O00oOoOoO.contains(O000O000o0O) && !set.contains(O000O000o0O)) {
                Map<String, O000O00000OoO.O000O0000O0oO> mapO00O000O0OOO = o000O00000OoOO000O00000o0O == null ? null : o000O00000OoOO000O00000o0O.O00O000O0OOO();
                com.ishumei.O000O00000oO.O000O00000OoO o000O00000OoOO0000O000000oO = com.ishumei.O000O00000oO.O000O00000OoO.O0000O000000oO();
                int iO00O0000o00O = o000O00000OoOO000O00000o0O == null ? 0 : o000O00000OoOO000O00000o0O.O00O0000o00O();
                if (o000O00000OoOO000O00000o0O != null) {
                    iO00O0000OooO = o000O00000OoOO000O00000o0O.O00O0000OooO();
                }
                Map<String, Object> mapO0000O000000oO = o000O00000OoOO0000O000000oO.O0000O000000oO(mapO00O000O0OOO, iO00O0000o00O, iO00O0000OooO);
                map.put(O000O000o0O, mapO0000O000000oO.get(SqConstants.APPS));
                map.put(O00O00O0o00O, mapO0000O000000oO.get("whiteapps"));
            }
            map.put(O00O0000o00O, Build.getRadioVersion());
            if (setO000O00oOoOoO != null && setO000O00oOoOoO.contains(O00O0000oO0O) && !set.contains(O00O0000oO0O)) {
                map.put(O00O0000oO0O, com.ishumei.O000O00000oO.O000O0000Oo0O.O0000O000000oO().O000O00000OoO());
            }
            if (!set.contains(O00O00oOooOO)) {
                map.put(O00O00oOooOO, com.ishumei.O000O00000oO.O000O0000Oo0O.O0000O000000oO().O000O0000O0oO());
            }
            map.put(O00O000OoOO, Integer.valueOf(com.ishumei.O000O00000oO.O000O0000O0oO.O0000O000000oO().O000O00000oO()));
            map.put(O00O000Oo0O, com.ishumei.O000O00000oO.O000O0000O0oO.O0000O000000oO().O000O00000OoO());
            map.put(O000O000OoOOO, Integer.valueOf(com.ishumei.O000O00000oO.O000O0000O0oO.O0000O000000oO().O000O0000O0oO()));
            map.put(O00O00O0OoO, com.ishumei.O000O00000oO.O000O0000O0oO.O0000O000000oO().O000O00000o0O());
            map.put(O00O000OOo0O, O00O0000o00O.O0000O000000oO().O000O00000oO());
            map.put(O000O000OOoO, Integer.valueOf(O00O0000o00O.O0000O000000oO().O000O0000O0oO()));
            map.put(O000O0000O0oO, com.ishumei.O000O00000oO.O000O00000OoO.O0000O000000oO().O000O00000OoO());
            map.put(O00O000OOOoO, Long.valueOf(O00O0000o00O.O0000O000000oO().O000O00000o0O()));
            SmAntiFraud.option.isSynMode();
            map.put(O000O0000Oo0O, com.ishumei.O000O00000oO.O000O00000OoO.O0000O000000oO().O000O00000oO());
            map.put(O000O0000OoO, Build.BRAND);
            if (!set.contains(O00O0000o0O)) {
                map.put(O00O0000o0O, com.ishumei.O000O00000oO.O000O0000Oo0O.O0000O000000oO().O000O0000Oo0O());
            }
            if (!set.contains(O00O0000o0OO)) {
                map.put(O00O0000o0OO, O00O0000o0OO.O0000O000000oO().O000O00000oO());
            }
            map.put(O00O000oO, com.ishumei.O000O00000oO.O000O00000o0O.O0000O000000oO().O000O00000OoO());
            if (!set.contains(O00oooOoOO)) {
                map.put(O00oooOoOO, com.ishumei.O000O00000oO.O000O0000OoO.O0000O000000oO().O000O00000OoO());
            }
            map.put(O00O00O000oO, Long.valueOf(com.ishumei.O000O00000oO.O000O0000O0oO.O0000O000000oO().O000O0000OOoO()));
            if (setO000O00oOoOoO != null && setO000O00oOoOoO.contains(O00O000OO00O) && !set.contains(O00O000OO00O)) {
                map.put(O00O000OO00O, O00O0000o0OO.O0000O000000oO().O000O0000OOoO());
            }
            if (setO000O00oOoOoO != null && setO000O00oOoOoO.contains(O00O000o00O) && !set.contains(O00O000o00O)) {
                map.put(O00O000o00O, O00O0000o0OO.O0000O000000oO().O000O0000OoO());
            }
            if (setO000O00oOoOoO != null && setO000O00oOoOoO.contains(O00O000o000O) && !set.contains(O00O000o000O)) {
                map.put(O00O000o000O, com.ishumei.O000O00000oO.O000O0000Oo0O.O0000O000000oO().O000O0000OOoO());
            }
            if (o000O00000OoOO000O00000o0O == null) {
                o0000O000000oO = this;
                mapO00O00oOooOO = null;
            } else {
                mapO00O00oOooOO = o000O00000OoOO000O00000o0O.O00O00oOooOO();
                o0000O000000oO = this;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            map.put(O00O00O0o0O, o0000O000000oO.O0000O000000oO(mapO00O00oOooOO));
            if (o000O00000OoOO000O00000o0O != null) {
                mapO00O000O00oO = o000O00000OoOO000O00000o0O.O00O000O00oO();
            }
            map.put(O0O00O0o0oO, O000O00000OoO(mapO00O000O00oO));
            map.put(O00O00O00OoO, com.ishumei.O000O00000oO.O000O0000OOoO.O0000O000000oO().O000O00000OoO());
            if (o000O00000OoOO000O00000o0O != null) {
                map.put(O0O00O0oO, o000O00000OoOO000O00000o0O.O00O0000oO0O());
            }
            O00O0000oO.O0000O000000oO().O0000O000000oO(map);
            if (com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO != null) {
                map.put(O0O00O0oO0O, com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO.getFilesDir());
            }
            map.put(O0O00O0ooO, com.ishumei.O000O00000oO.O0000O000000oO.O0000O000000oO().O000O00000OoO());
            com.ishumei.O000O00000oO.O0000O000000oO.O0000O000000oO().O0000O000000oO(map);
            long jO000O00000oO = com.ishumei.O000O0000OOoO.O000O00000o0O.O0000O000000oO().O000O00000oO();
            if (jO000O00000oO != -1) {
                map.put("cost", Long.valueOf(jO000O00000oO));
            }
            map.put(O00O00OO0OO, com.ishumei.O000O00000OoO.O000O00000oO.O000O00000oO.O0000O000000oO().O000O00000OoO());
            map.put(O0O00OOOoO, com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO.O0000O000000oO().O000O00000o0O());
            map.put(O0O00OO0oO, com.ishumei.O000O00000OoO.O000O00000o0O.O0000O000000oO.O0000O000000oO().O000O00000o0O());
            return map;
        } catch (Throwable th2) {
            th = th2;
            map.put("err", Log.getStackTraceString(th));
            return map;
        }
    }
}
