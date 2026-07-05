package com.ishumei.O000O00000oO;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.ishumei.O000O00000OoO.O000O00000OoO;
import com.sqwan.bugless.core.Constant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O00000OoO {
    private static volatile O000O00000OoO O000O0000O0oO;
    public int O0000O000000oO = 0;
    public int O000O00000OoO = 0;
    private Context O000O00000o0O;
    private Object O000O00000oO;

    O000O00000OoO() {
        this.O000O00000o0O = null;
        this.O000O00000oO = null;
        Context context = com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO;
        this.O000O00000o0O = context;
        if (context != null) {
            try {
                this.O000O00000oO = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O000O00000o0O).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8baf9e9c949e989ab29e919e989a8d")).O0000O000000oO();
            } catch (Exception unused) {
            }
        }
    }

    public static O000O00000OoO O0000O000000oO() {
        if (O000O0000O0oO == null) {
            synchronized (O000O00000OoO.class) {
                if (O000O0000O0oO == null) {
                    O000O0000O0oO = new O000O00000OoO();
                }
            }
        }
        return O000O0000O0oO;
    }

    public synchronized Map<String, Object> O0000O000000oO(Map<String, O000O00000OoO.O000O0000O0oO> map, int i, int i2) {
        Object obj;
        List list;
        HashMap map2;
        int i3;
        this.O0000O000000oO = 0;
        this.O000O00000OoO = 0;
        HashMap map3 = new HashMap();
        ArrayList arrayList = new ArrayList();
        HashMap map4 = new HashMap();
        HashMap map5 = new HashMap();
        if (this.O000O00000o0O == null) {
            return map3;
        }
        if (map != null) {
            for (Map.Entry<String, O000O00000OoO.O000O0000O0oO> entry : map.entrySet()) {
                map5.put(entry.getValue().O000O00000OoO(), entry.getKey());
            }
        }
        if (this.O000O00000oO == null) {
            throw new Exception();
        }
        Object obj2 = this.O000O00000oO;
        int i4 = 1;
        List list2 = (List) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(obj2).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8bb6918c8b9e93939a9baf9e9c949e989a8c")).O0000O000000oO(0);
        Collections.sort(list2, new Comparator<PackageInfo>() { // from class: com.ishumei.O000O00000oO.O000O00000OoO.1
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: O0000O000000oO, reason: merged with bridge method [inline-methods] */
            public int compare(PackageInfo packageInfo, PackageInfo packageInfo2) {
                try {
                    Object objO0000O000000oO = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(packageInfo).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8f93969c9e8b969091b6919990"));
                    Object objO0000O000000oO2 = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(packageInfo2).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8f93969c9e8b969091b6919990"));
                    int iIntValue = ((Integer) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(objO0000O000000oO).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("99939e988c"))).intValue();
                    int iIntValue2 = ((Integer) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(objO0000O000000oO2).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("99939e988c"))).intValue();
                    Long lValueOf = Long.valueOf(-packageInfo.firstInstallTime);
                    Long lValueOf2 = Long.valueOf(-packageInfo2.firstInstallTime);
                    boolean z = (iIntValue & 1) == 0 || (iIntValue & 128) == 0;
                    boolean z2 = (iIntValue2 & 1) > 0 || (iIntValue2 & 128) > 0;
                    if (z && !z2) {
                        return 1;
                    }
                    if (z || !z2) {
                        return lValueOf.compareTo(lValueOf2);
                    }
                    return -1;
                } catch (Exception e) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
                    return 0;
                }
            }
        });
        int i5 = 0;
        while (i5 < list2.size()) {
            Object obj3 = list2.get(i5);
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(obj3).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8f9e9c949e989ab19e929a"));
            Object objO0000O000000oO = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(obj3).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8f93969c9e8b969091b6919990"));
            int iIntValue = ((Integer) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(objO0000O000000oO).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("99939e988c"))).intValue();
            int i6 = ((iIntValue & 1) > 0 || (i3 = iIntValue & 128) > 0 || i3 != 0) ? 0 : 1;
            if (map5.containsKey(str)) {
                map4.put((String) map5.get(str), Integer.valueOf(i4));
            } else {
                if (i6 != 0 || this.O0000O000000oO < i) {
                    if (i6 != i4 || this.O000O00000OoO < i2) {
                        net.vidageek.O0000O000000oO.O000O0000O0oO.O0000O000000oO.O000O00000o0O o000O00000o0OO0000O000000oO = new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(objO0000O000000oO).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("93909e9bb39e9d9a93"));
                        Object[] objArr = new Object[i4];
                        objArr[0] = obj2;
                        String str2 = (String) o000O00000o0OO0000O000000oO.O0000O000000oO(objArr);
                        HashMap map6 = map5;
                        obj = obj2;
                        long jLongValue = ((Long) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(obj3).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("99968d8c8bb6918c8b9e9393ab96929a"))).longValue();
                        String str3 = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(obj3).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("899a8d8c969091b19e929a"));
                        list = list2;
                        int iIntValue2 = ((Integer) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(obj3).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("899a8d8c969091bc909b9a"))).intValue();
                        long jLongValue2 = ((Long) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(obj3).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("939e8c8baa8f9b9e8b9aab96929a"))).longValue();
                        StringBuilder sb = new StringBuilder();
                        map2 = map6;
                        sb.append("");
                        sb.append(jLongValue);
                        sb.append(",");
                        sb.append(str);
                        sb.append(",");
                        sb.append(str2);
                        sb.append(",");
                        sb.append(i6);
                        sb.append(",");
                        sb.append(iIntValue2);
                        sb.append(",");
                        sb.append(str3);
                        sb.append(",");
                        sb.append(jLongValue2);
                        arrayList.add(sb.toString());
                        if (i6 == 1) {
                            this.O000O00000OoO++;
                        } else {
                            this.O0000O000000oO++;
                        }
                    }
                    i5++;
                    obj2 = obj;
                    map5 = map2;
                    list2 = list;
                    i4 = 1;
                }
                map2 = map5;
                obj = obj2;
                list = list2;
                i5++;
                obj2 = obj;
                map5 = map2;
                list2 = list;
                i4 = 1;
            }
            map2 = map5;
            obj = obj2;
            list = list2;
            i5++;
            obj2 = obj;
            map5 = map2;
            list2 = list;
            i4 = 1;
        }
        map3.put(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8f8c"), arrayList);
        map3.put(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8897968b9a9e8f8f8c"), map4);
        return map3;
    }

    public String O000O00000OoO() {
        if (this.O000O00000o0O == null) {
            return "";
        }
        try {
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O000O00000o0O.getPackageManager().getPackageInfo(O000O00000o0O(), 0)).O000O00000OoO().O0000O000000oO(Constant.PKG_VERSION_NAME);
            return str == null ? "" : str;
        } catch (Exception unused) {
            return "";
        }
    }

    public String O000O00000o0O() {
        String str;
        if (this.O000O00000o0O == null) {
            return "";
        }
        try {
            str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O000O00000o0O).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("989a8baf9e9c949e989ab19e929a")).O0000O000000oO();
        } catch (Exception unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public String O000O00000oO() {
        try {
            if (this.O000O00000o0O == null) {
                return "";
            }
            if (this.O000O00000oO == null) {
                throw new Exception();
            }
            String str = (String) new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(new net.vidageek.O0000O000000oO.O000O00000OoO.O000O00000o0O().O0000O000000oO(this.O000O00000oO).O0000O000000oO().O0000O000000oO("getPackageInfo").O0000O000000oO(com.ishumei.O000O00000OoO.O000O00000oO.O0000O000000oO.getPackageName(), 0)).O000O00000OoO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9e8f8f93969c9e8b969091b6919990"))).O0000O000000oO().O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("93909e9bb39e9d9a93")).O0000O000000oO(this.O000O00000oO);
            return str == null ? "" : str;
        } catch (Exception unused) {
            return "";
        }
    }
}
