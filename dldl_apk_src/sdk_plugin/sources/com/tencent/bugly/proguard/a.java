package com.tencent.bugly.proguard;

import android.content.Context;
import android.text.TextUtils;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class a {
    private static Proxy e;
    protected HashMap<String, HashMap<String, byte[]>> a = new HashMap<>();
    protected String b;
    i c;
    private HashMap<String, Object> d;

    public static aj a(int i) {
        if (i == 1) {
            return new ai();
        }
        if (i == 3) {
            return new ah();
        }
        return null;
    }

    a() {
        new HashMap();
        this.d = new HashMap<>();
        this.b = "GBK";
        this.c = new i();
    }

    public static void a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            e = null;
        } else {
            e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i));
        }
    }

    public static void a(InetAddress inetAddress, int i) {
        if (inetAddress == null) {
            e = null;
        } else {
            e = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(inetAddress, i));
        }
    }

    public void a(String str) {
        this.b = str;
    }

    public static Proxy b() {
        return e;
    }

    public static at a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        at atVar = new at();
        atVar.a = userInfoBean.e;
        atVar.e = userInfoBean.j;
        atVar.d = userInfoBean.c;
        atVar.c = userInfoBean.d;
        atVar.g = com.tencent.bugly.crashreport.common.info.a.b().i();
        atVar.h = userInfoBean.o == 1;
        int i = userInfoBean.b;
        if (i == 1) {
            atVar.b = (byte) 1;
        } else if (i == 2) {
            atVar.b = (byte) 4;
        } else if (i == 3) {
            atVar.b = (byte) 2;
        } else if (i == 4) {
            atVar.b = (byte) 3;
        } else {
            if (userInfoBean.b < 10 || userInfoBean.b >= 20) {
                x.e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.b));
                return null;
            }
            atVar.b = (byte) userInfoBean.b;
        }
        atVar.f = new HashMap();
        if (userInfoBean.p >= 0) {
            Map<String, String> map = atVar.f;
            StringBuilder sb = new StringBuilder();
            sb.append(userInfoBean.p);
            map.put("C01", sb.toString());
        }
        if (userInfoBean.q >= 0) {
            Map<String, String> map2 = atVar.f;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(userInfoBean.q);
            map2.put("C02", sb2.toString());
        }
        if (userInfoBean.r != null && userInfoBean.r.size() > 0) {
            for (Map.Entry<String, String> entry : userInfoBean.r.entrySet()) {
                atVar.f.put("C03_" + entry.getKey(), entry.getValue());
            }
        }
        if (userInfoBean.s != null && userInfoBean.s.size() > 0) {
            for (Map.Entry<String, String> entry2 : userInfoBean.s.entrySet()) {
                atVar.f.put("C04_" + entry2.getKey(), entry2.getValue());
            }
        }
        Map<String, String> map3 = atVar.f;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(!userInfoBean.l);
        map3.put("A36", sb3.toString());
        Map<String, String> map4 = atVar.f;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(userInfoBean.g);
        map4.put("F02", sb4.toString());
        Map<String, String> map5 = atVar.f;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(userInfoBean.h);
        map5.put("F03", sb5.toString());
        atVar.f.put("F04", userInfoBean.j);
        Map<String, String> map6 = atVar.f;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(userInfoBean.i);
        map6.put("F05", sb6.toString());
        atVar.f.put("F06", userInfoBean.m);
        Map<String, String> map7 = atVar.f;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(userInfoBean.k);
        map7.put("F10", sb7.toString());
        x.c("summary type %d vm:%d", Byte.valueOf(atVar.b), Integer.valueOf(atVar.f.size()));
        return atVar;
    }

    public static String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            String str = "map";
            if (i < arrayList.size()) {
                String str2 = arrayList.get(i);
                if (str2.equals("java.lang.Integer") || str2.equals("int")) {
                    str = "int32";
                } else if (str2.equals("java.lang.Boolean") || str2.equals("boolean")) {
                    str = "bool";
                } else if (str2.equals("java.lang.Byte") || str2.equals("byte")) {
                    str = "char";
                } else if (str2.equals("java.lang.Double") || str2.equals("double")) {
                    str = "double";
                } else if (str2.equals("java.lang.Float") || str2.equals("float")) {
                    str = "float";
                } else if (str2.equals("java.lang.Long") || str2.equals("long")) {
                    str = "int64";
                } else if (str2.equals("java.lang.Short") || str2.equals("short")) {
                    str = "short";
                } else {
                    if (str2.equals("java.lang.Character")) {
                        throw new IllegalArgumentException("can not support java.lang.Character");
                    }
                    if (str2.equals("java.lang.String")) {
                        str = "string";
                    } else if (str2.equals("java.util.List")) {
                        str = "list";
                    } else if (!str2.equals("java.util.Map")) {
                        str = str2;
                    }
                }
                arrayList.set(i, str);
                i++;
            } else {
                Collections.reverse(arrayList);
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    String str3 = arrayList.get(i2);
                    if (str3.equals("list")) {
                        int i3 = i2 - 1;
                        arrayList.set(i3, SimpleComparison.LESS_THAN_OPERATION + arrayList.get(i3));
                        arrayList.set(0, arrayList.get(0) + SimpleComparison.GREATER_THAN_OPERATION);
                    } else if (str3.equals("map")) {
                        int i4 = i2 - 1;
                        arrayList.set(i4, SimpleComparison.LESS_THAN_OPERATION + arrayList.get(i4) + ",");
                        arrayList.set(0, arrayList.get(0) + SimpleComparison.GREATER_THAN_OPERATION);
                    } else if (str3.equals("Array")) {
                        int i5 = i2 - 1;
                        arrayList.set(i5, SimpleComparison.LESS_THAN_OPERATION + arrayList.get(i5));
                        arrayList.set(0, arrayList.get(0) + SimpleComparison.GREATER_THAN_OPERATION);
                    }
                }
                Collections.reverse(arrayList);
                Iterator<String> it = arrayList.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(it.next());
                }
                return stringBuffer.toString();
            }
        }
    }

    public <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        }
        j jVar = new j();
        jVar.a(this.b);
        jVar.a(t, 0);
        byte[] bArrA = l.a(jVar.a());
        HashMap<String, byte[]> map = new HashMap<>(1);
        ArrayList<String> arrayList = new ArrayList<>(1);
        a(arrayList, t);
        map.put(a(arrayList), bArrA);
        this.d.remove(str);
        this.a.put(str, map);
    }

    public static au a(List<UserInfoBean> list, int i) {
        com.tencent.bugly.crashreport.common.info.a aVarB;
        if (list == null || list.size() == 0 || (aVarB = com.tencent.bugly.crashreport.common.info.a.b()) == null) {
            return null;
        }
        aVarB.t();
        au auVar = new au();
        auVar.b = aVarB.d;
        auVar.c = aVarB.h();
        ArrayList<at> arrayList = new ArrayList<>();
        Iterator<UserInfoBean> it = list.iterator();
        while (it.hasNext()) {
            at atVarA = a(it.next());
            if (atVarA != null) {
                arrayList.add(atVarA);
            }
        }
        auVar.d = arrayList;
        auVar.e = new HashMap();
        auVar.e.put("A7", aVarB.f);
        auVar.e.put("A6", aVarB.s());
        auVar.e.put("A5", aVarB.r());
        Map<String, String> map = auVar.e;
        StringBuilder sb = new StringBuilder();
        sb.append(aVarB.p());
        map.put("A2", sb.toString());
        Map<String, String> map2 = auVar.e;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(aVarB.p());
        map2.put("A1", sb2.toString());
        auVar.e.put("A24", aVarB.h);
        Map<String, String> map3 = auVar.e;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(aVarB.q());
        map3.put("A17", sb3.toString());
        auVar.e.put("A15", aVarB.w());
        Map<String, String> map4 = auVar.e;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(aVarB.x());
        map4.put("A13", sb4.toString());
        auVar.e.put("F08", aVarB.v);
        auVar.e.put("F09", aVarB.w);
        Map<String, String> mapG = aVarB.G();
        if (mapG != null && mapG.size() > 0) {
            for (Map.Entry<String, String> entry : mapG.entrySet()) {
                auVar.e.put("C04_" + entry.getKey(), entry.getValue());
            }
        }
        if (i == 1) {
            auVar.a = (byte) 1;
        } else {
            if (i != 2) {
                x.e("unknown up type %d ", Integer.valueOf(i));
                return null;
            }
            auVar.a = (byte) 2;
        }
        return auVar;
    }

    public static <T extends k> T a(byte[] bArr, Class<T> cls) {
        if (bArr != null && bArr.length > 0) {
            try {
                T tNewInstance = cls.newInstance();
                i iVar = new i(bArr);
                iVar.a("utf-8");
                tNewInstance.a(iVar);
                return tNewInstance;
            } catch (Throwable th) {
                if (!x.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static ap a(Context context, int i, byte[] bArr) {
        com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
        StrategyBean strategyBeanC = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (aVarB == null || strategyBeanC == null) {
            x.e("Can not create request pkg for parameters is invalid.", new Object[0]);
            return null;
        }
        try {
            ap apVar = new ap();
            synchronized (aVarB) {
                apVar.a = 1;
                apVar.b = aVarB.f();
                apVar.c = aVarB.c;
                apVar.d = aVarB.j;
                apVar.e = aVarB.l;
                aVarB.getClass();
                apVar.f = "3.0.0";
                apVar.g = i;
                apVar.h = bArr == null ? "".getBytes() : bArr;
                apVar.i = aVarB.g;
                apVar.j = aVarB.h;
                apVar.k = new HashMap();
                apVar.l = aVarB.e();
                apVar.m = strategyBeanC.p;
                apVar.o = aVarB.h();
                apVar.p = com.tencent.bugly.crashreport.common.info.b.c(context);
                apVar.q = System.currentTimeMillis();
                apVar.r = aVarB.k();
                apVar.s = aVarB.j();
                apVar.t = aVarB.m();
                apVar.u = aVarB.l();
                apVar.v = aVarB.n();
                apVar.w = apVar.p;
                aVarB.getClass();
                apVar.n = "com.tencent.bugly";
                apVar.k.put("A26", aVarB.y());
                apVar.k.put("A60", aVarB.z());
                apVar.k.put("A61", aVarB.A());
                Map<String, String> map = apVar.k;
                StringBuilder sb = new StringBuilder();
                sb.append(aVarB.R());
                map.put("A62", sb.toString());
                Map<String, String> map2 = apVar.k;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(aVarB.S());
                map2.put("A63", sb2.toString());
                Map<String, String> map3 = apVar.k;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(aVarB.z);
                map3.put("F11", sb3.toString());
                Map<String, String> map4 = apVar.k;
                StringBuilder sb4 = new StringBuilder();
                sb4.append(aVarB.y);
                map4.put("F12", sb4.toString());
                apVar.k.put("G1", aVarB.u());
                apVar.k.put("A64", aVarB.T());
                if (aVarB.B) {
                    apVar.k.put("G2", aVarB.L());
                    apVar.k.put("G3", aVarB.M());
                    apVar.k.put("G4", aVarB.N());
                    apVar.k.put("G5", aVarB.O());
                    apVar.k.put("G6", aVarB.P());
                    apVar.k.put("G7", Long.toString(aVarB.Q()));
                }
                apVar.k.put("D3", aVarB.k);
                if (com.tencent.bugly.b.b != null) {
                    for (com.tencent.bugly.a aVar : com.tencent.bugly.b.b) {
                        if (aVar.versionKey != null && aVar.version != null) {
                            apVar.k.put(aVar.versionKey, aVar.version);
                        }
                    }
                }
                apVar.k.put("G15", z.b("G15", ""));
                apVar.k.put("D4", z.b("D4", "0"));
            }
            u uVarA = u.a();
            if (uVarA != null && !uVarA.a && bArr != null) {
                apVar.h = z.a(apVar.h, 2, 1, strategyBeanC.u);
                if (apVar.h == null) {
                    x.e("reqPkg sbuffer error!", new Object[0]);
                    return null;
                }
            }
            Map<String, String> mapF = aVarB.F();
            if (mapF != null) {
                for (Map.Entry<String, String> entry : mapF.entrySet()) {
                    apVar.k.put(entry.getKey(), entry.getValue());
                }
            }
            return apVar;
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private void a(ArrayList<String> arrayList, Object obj) {
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            }
            if (Array.getLength(obj) > 0) {
                arrayList.add("java.util.List");
                a(arrayList, Array.get(obj, 0));
                return;
            } else {
                arrayList.add("Array");
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        }
        if (obj instanceof List) {
            arrayList.add("java.util.List");
            List list = (List) obj;
            if (list.size() > 0) {
                a(arrayList, list.get(0));
                return;
            } else {
                arrayList.add("?");
                return;
            }
        }
        if (obj instanceof Map) {
            arrayList.add("java.util.Map");
            Map map = (Map) obj;
            if (map.size() > 0) {
                Object next = map.keySet().iterator().next();
                Object obj2 = map.get(next);
                arrayList.add(next.getClass().getName());
                a(arrayList, obj2);
                return;
            }
            arrayList.add("?");
            arrayList.add("?");
            return;
        }
        arrayList.add(obj.getClass().getName());
    }

    public byte[] a() {
        j jVar = new j(0);
        jVar.a(this.b);
        jVar.a((Map) this.a, 0);
        return l.a(jVar.a());
    }

    public void a(byte[] bArr) {
        this.c.a(bArr);
        this.c.a(this.b);
        HashMap map = new HashMap(1);
        HashMap map2 = new HashMap(1);
        map2.put("", new byte[0]);
        map.put("", map2);
        this.a = this.c.a((Map) map, 0, false);
    }

    public static byte[] a(Object obj) {
        try {
            d dVar = new d();
            dVar.c();
            dVar.a("utf-8");
            dVar.b(1);
            dVar.b("RqdServer");
            dVar.c("sync");
            dVar.a("detail", obj);
            return dVar.a();
        } catch (Throwable th) {
            if (x.b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static aq a(byte[] bArr, boolean z) {
        if (bArr != null) {
            try {
                d dVar = new d();
                dVar.c();
                dVar.a("utf-8");
                dVar.a(bArr);
                Object objB = dVar.b("detail", new aq());
                aq aqVar = aq.class.isInstance(objB) ? (aq) aq.class.cast(objB) : null;
                if (!z && aqVar != null && aqVar.c != null && aqVar.c.length > 0) {
                    x.c("resp buf %d", Integer.valueOf(aqVar.c.length));
                    aqVar.c = z.b(aqVar.c, 2, 1, StrategyBean.d);
                    if (aqVar.c == null) {
                        x.e("resp sbuffer error!", new Object[0]);
                        return null;
                    }
                }
                return aqVar;
            } catch (Throwable th) {
                if (!x.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] a(k kVar) {
        try {
            j jVar = new j();
            jVar.a("utf-8");
            kVar.a(jVar);
            return jVar.b();
        } catch (Throwable th) {
            if (x.b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
