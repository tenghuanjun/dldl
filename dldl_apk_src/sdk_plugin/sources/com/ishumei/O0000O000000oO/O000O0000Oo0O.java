package com.ishumei.O0000O000000oO;

import android.text.TextUtils;
import com.ishumei.O000O00000OoO.O000O00000OoO;
import com.ishumei.O000O00000oO.O00O0000OooO;
import com.ishumei.O000O0000O0oO.O000O00000OoO;
import com.ishumei.smantifraud.SmAntiFraud;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O0000Oo0O {
    private static Map<String, Integer> O0000O000000oO = new ConcurrentHashMap();
    private com.ishumei.O000O00000o0O.O000O00000OoO O000O00000OoO;
    private O000O00000OoO.AbstractC0045O000O00000OoO O000O00000o0O;
    private O0000O000000oO O000O00000oO;
    private O000O00000OoO.AbstractC0045O000O00000OoO O000O0000O0oO;
    private O0000O000000oO O000O0000OOoO;
    private O000O00000OoO.AbstractC0045O000O00000OoO O000O0000Oo0O;
    private O0000O000000oO O000O0000OoO;
    private O000O00000OoO.AbstractC0045O000O00000OoO O00O0000OooO;
    private com.ishumei.O000O00000o0O.O000O00000OoO O00O0000o00O;

    public static class O0000O000000oO {
        boolean O0000O000000oO;
        int O000O00000OoO;
        String O000O00000o0O;
        O000O00000OoO.AbstractC0045O000O00000OoO<?> O000O00000oO;
        private String O000O0000O0oO = null;
        private String O000O0000OOoO = null;
        private InterfaceC0039O0000O000000oO O000O0000Oo0O;
        private O000O00000OoO O000O0000OoO;

        /* JADX INFO: renamed from: com.ishumei.O0000O000000oO.O000O0000Oo0O$O0000O000000oO$O0000O000000oO, reason: collision with other inner class name */
        public interface InterfaceC0039O0000O000000oO {
            String O0000O000000oO();
        }

        public interface O000O00000OoO {
            String O0000O000000oO();
        }

        O0000O000000oO(InterfaceC0039O0000O000000oO interfaceC0039O0000O000000oO, O000O00000OoO o000O00000OoO, boolean z, int i, O000O00000OoO.AbstractC0045O000O00000OoO<?> abstractC0045O000O00000OoO, String str) {
            this.O000O0000Oo0O = null;
            this.O000O0000OoO = null;
            this.O0000O000000oO = false;
            this.O000O00000OoO = 0;
            this.O000O00000o0O = null;
            this.O000O00000oO = null;
            this.O000O0000Oo0O = interfaceC0039O0000O000000oO;
            this.O000O0000OoO = o000O00000OoO;
            this.O0000O000000oO = z;
            this.O000O00000OoO = i;
            this.O000O00000oO = abstractC0045O000O00000OoO;
            this.O000O00000o0O = str;
        }

        public void O0000O000000oO() {
            O0000O000000oO(false);
        }

        public void O0000O000000oO(boolean z) {
            if (SmAntiFraud.option.isTransport()) {
                new com.ishumei.O000O00000o0O.O000O00000OoO(z, this.O000O00000OoO, false, 0L, false) { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (O0000O000000oO.this.O000O0000Oo0O != null) {
                                O0000O000000oO.this.O000O0000O0oO = O0000O000000oO.this.O000O0000Oo0O.O0000O000000oO();
                            }
                            if (O0000O000000oO.this.O000O0000OoO != null) {
                                O0000O000000oO.this.O000O0000OOoO = O0000O000000oO.this.O000O0000OoO.O0000O000000oO();
                            }
                            com.ishumei.O000O0000O0oO.O0000O000000oO o0000O000000oO = new com.ishumei.O000O0000O0oO.O0000O000000oO();
                            o0000O000000oO.O0000O000000oO();
                            if (O0000O000000oO.this.O000O0000OOoO.startsWith("https://")) {
                                o0000O000000oO.O0000O000000oO(0);
                            } else {
                                o0000O000000oO.O0000O000000oO(1);
                            }
                            o0000O000000oO.O0000O000000oO(O0000O000000oO.this.O000O0000OOoO);
                            new com.ishumei.O000O0000O0oO.O000O00000OoO().O0000O000000oO(o0000O000000oO).O0000O000000oO(O0000O000000oO.this.O000O0000O0oO.getBytes("utf-8"), (Map<String, String>) null, O0000O000000oO.this.O000O00000oO);
                        } catch (Exception unused) {
                        }
                    }
                }.O0000O000000oO();
            }
        }

        public void O000O00000OoO() {
            O0000O000000oO(this.O0000O000000oO);
        }
    }

    private static class O000O00000OoO {
        private static final O000O0000Oo0O O0000O000000oO = new O000O0000Oo0O();
    }

    private O000O0000Oo0O() {
        this.O000O00000OoO = new com.ishumei.O000O00000o0O.O000O00000OoO(true, 1 == true ? 1 : 0) { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (SmAntiFraud.option.isCloudConf()) {
                        com.ishumei.O000O0000O0oO.O0000O000000oO o0000O000000oO = new com.ishumei.O000O0000O0oO.O0000O000000oO();
                        o0000O000000oO.O0000O000000oO();
                        String confUrl = SmAntiFraud.option.getConfUrl();
                        if (confUrl.startsWith("https://")) {
                            o0000O000000oO.O0000O000000oO(0);
                        } else {
                            o0000O000000oO.O0000O000000oO(1);
                        }
                        o0000O000000oO.O0000O000000oO(confUrl);
                        com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO().O0000O000000oO(o0000O000000oO);
                    }
                    O000O0000Oo0O.this.O000O0000OOoO.O000O00000OoO();
                    O000O0000Oo0O.this.O00O0000o00O.O0000O000000oO();
                } catch (Exception unused) {
                }
            }
        };
        int i = 2;
        this.O000O00000o0O = new O000O00000OoO.AbstractC0045O000O00000OoO<Object>(false, i) { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.5
            @Override // com.ishumei.O000O0000O0oO.O000O00000OoO.AbstractC0045O000O00000OoO
            public void O0000O000000oO(String str) {
                String strO0000O000000oO = O000O0000Oo0O.this.O0000O000000oO(str, false);
                O000O0000OoO.O0000O000000oO().O0000O000000oO(strO0000O000000oO);
                synchronized (O000O0000Oo0O.class) {
                    if (SmAntiFraud.getServerIdCallback() != null) {
                        if (TextUtils.isEmpty(strO0000O000000oO)) {
                            SmAntiFraud.getServerIdCallback().onError(-3);
                        } else {
                            SmAntiFraud.getServerIdCallback().onSuccess(strO0000O000000oO);
                        }
                    }
                }
            }

            @Override // com.ishumei.O000O0000O0oO.O000O00000OoO.AbstractC0045O000O00000OoO
            public boolean O0000O000000oO(String str, int i2) {
                boolean zO0000O000000oO = super.O0000O000000oO(str, i2);
                if (zO0000O000000oO) {
                    int i3 = -4;
                    if (i2 == 0 || i2 == 1) {
                        i3 = -1;
                    } else if (i2 == 2 || i2 == 3) {
                        i3 = -2;
                    }
                    if (SmAntiFraud.getServerIdCallback() != null) {
                        SmAntiFraud.getServerIdCallback().onError(i3);
                    }
                }
                return zO0000O000000oO;
            }
        };
        this.O000O00000oO = new O0000O000000oO(new O0000O000000oO.InterfaceC0039O0000O000000oO() { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.6
            @Override // com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO.InterfaceC0039O0000O000000oO
            public String O0000O000000oO() {
                boolean zNeedUsingMD5 = SmAntiFraud.option.needUsingMD5();
                com.ishumei.O000O00000OoO.O000O00000OoO o000O00000OoOO000O00000o0O = com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO().O000O00000o0O();
                int i2 = (zNeedUsingMD5 ? 1 : 0) | (o000O00000OoOO000O00000o0O == null || o000O00000OoOO000O00000o0O.O00O0000o0O() ? 2 : 0);
                O000O0000Oo0O o000O0000Oo0O = O000O0000Oo0O.this;
                return o000O0000Oo0O.O000O00000o0O(o000O0000Oo0O.O0000O000000oO(com.ishumei.O0000O000000oO.O000O00000OoO.O0000O000000oO(), i2), true);
            }
        }, new O0000O000000oO.O000O00000OoO() { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.7
            @Override // com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO.O000O00000OoO
            public String O0000O000000oO() {
                return SmAntiFraud.option.getUrl();
            }
        }, true, 1, this.O000O00000o0O, "core info");
        this.O000O0000O0oO = new O000O00000OoO.AbstractC0045O000O00000OoO<Object>(1 == true ? 1 : 0, i) { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.8
            @Override // com.ishumei.O000O0000O0oO.O000O00000OoO.AbstractC0045O000O00000OoO
            public void O0000O000000oO(String str) {
                O000O0000OoO.O0000O000000oO().O0000O000000oO(O000O0000Oo0O.this.O0000O000000oO(str, true));
            }

            @Override // com.ishumei.O000O0000O0oO.O000O00000OoO.AbstractC0045O000O00000OoO
            public boolean O0000O000000oO(String str, int i2) {
                boolean zO0000O000000oO = super.O0000O000000oO(str, i2);
                if (zO0000O000000oO) {
                    com.ishumei.O000O00000OoO.O000O0000O0oO.O0000O000000oO.O0000O000000oO().O0000O000000oO(new String(this.O000O00000OoO.O000O00000OoO), this.O000O00000OoO.O00O0000o00O);
                }
                return zO0000O000000oO;
            }
        };
        this.O000O0000OOoO = new O0000O000000oO(new O0000O000000oO.InterfaceC0039O0000O000000oO() { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.9
            @Override // com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO.InterfaceC0039O0000O000000oO
            public String O0000O000000oO() {
                com.ishumei.O000O00000OoO.O000O00000OoO o000O00000OoOO000O00000o0O = com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO().O000O00000o0O();
                boolean z = o000O00000OoOO000O00000o0O == null || o000O00000OoOO000O00000o0O.O00O0000o0O();
                int i2 = (SmAntiFraud.option.needUsingMD5() ? 1 : 0) | (z ? 2 : 0);
                O000O0000Oo0O o000O0000Oo0O = O000O0000Oo0O.this;
                return o000O0000Oo0O.O000O00000o0O(o000O0000Oo0O.O0000O000000oO(com.ishumei.O0000O000000oO.O0000O000000oO.O0000O000000oO(), i2), true);
            }
        }, new O0000O000000oO.O000O00000OoO() { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.10
            @Override // com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO.O000O00000OoO
            public String O0000O000000oO() {
                return SmAntiFraud.option.getUrl();
            }
        }, true, 1, this.O000O0000O0oO, "base info");
        this.O000O0000Oo0O = new O000O00000OoO.AbstractC0045O000O00000OoO<Object>(1 == true ? 1 : 0, i) { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.11
            @Override // com.ishumei.O000O0000O0oO.O000O00000OoO.AbstractC0045O000O00000OoO
            public void O0000O000000oO(String str) {
            }
        };
        this.O000O0000OoO = new O0000O000000oO(new O0000O000000oO.InterfaceC0039O0000O000000oO() { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.12
            @Override // com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO.InterfaceC0039O0000O000000oO
            public String O0000O000000oO() {
                O000O0000Oo0O o000O0000Oo0O = O000O0000Oo0O.this;
                return o000O0000Oo0O.O000O00000o0O(o000O0000Oo0O.O0000O000000oO(O000O00000o0O.O0000O000000oO(), 0), false);
            }
        }, new O0000O000000oO.O000O00000OoO() { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.2
            @Override // com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO.O000O00000OoO
            public String O0000O000000oO() {
                return SmAntiFraud.option.getContactUrl();
            }
        }, true, 1, this.O000O0000Oo0O, "finance info");
        this.O00O0000OooO = new O000O00000OoO.AbstractC0045O000O00000OoO<Object>(1 == true ? 1 : 0, i) { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.3
            @Override // com.ishumei.O000O0000O0oO.O000O00000OoO.AbstractC0045O000O00000OoO
            public void O0000O000000oO(String str) {
            }
        };
        this.O00O0000o00O = new com.ishumei.O000O00000o0O.O000O00000OoO(1 == true ? 1 : 0, 5) { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.4
            @Override // java.lang.Runnable
            public void run() {
                O00O0000OooO.O000O0000O0oO o000O0000O0oOO0000O000000oO;
                com.ishumei.O000O00000OoO.O000O00000OoO o000O00000OoOO000O00000o0O = com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO().O000O00000o0O();
                if (o000O00000OoOO000O00000o0O == null) {
                    return;
                }
                List<O000O00000OoO.O000O00000oO> listO000O00000OoO = o000O00000OoOO000O00000o0O.O000O00000OoO();
                final int iO000O00000o0O = o000O00000OoOO000O00000o0O.O000O00000o0O();
                final int iO000O00000oO = o000O00000OoOO000O00000o0O.O000O00000oO();
                if (listO000O00000OoO == null || listO000O00000OoO.size() == 0 || iO000O00000o0O <= 0 || iO000O00000oO < 0) {
                    return;
                }
                final String strValueOf = String.valueOf(System.currentTimeMillis());
                final ArrayList arrayList = new ArrayList();
                for (O000O00000OoO.O000O00000oO o000O00000oO : listO000O00000OoO) {
                    if (o000O00000oO.O000O00000OoO() != 0 && (o000O0000O0oOO0000O000000oO = O00O0000OooO.O0000O000000oO().O0000O000000oO(o000O00000oO.O0000O000000oO())) != null) {
                        int iO000O00000oO2 = o000O00000oO.O000O00000oO();
                        int iO000O00000o0O2 = o000O00000oO.O000O00000o0O();
                        if (iO000O00000oO2 > 0 && iO000O00000o0O2 >= 0) {
                            arrayList.add(o000O0000O0oOO0000O000000oO);
                            o000O0000O0oOO0000O000000oO.O0000O000000oO(iO000O00000oO2, iO000O00000o0O2);
                            o000O0000O0oOO0000O000000oO.O000O00000o0O();
                        }
                    }
                }
                O000O0000Oo0O.O0000O000000oO.put(strValueOf, 0);
                Runnable runnable = new Runnable() { // from class: com.ishumei.O0000O000000oO.O000O0000Oo0O.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Integer numValueOf;
                        try {
                            com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO("SmCollectionManager", "sensor start transport.");
                            String strO000O00000o0O = O000O0000Oo0O.O0000O000000oO().O000O00000o0O(com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Map<?, ?>) O000O0000O0oO.O0000O000000oO().O0000O000000oO((SmAntiFraud.option.needUsingMD5() ? 1 : 0) | (com.ishumei.O000O00000OoO.O0000O000000oO.O0000O000000oO().O000O00000o0O().O00O0000o0O() ? 2 : 0), arrayList, strValueOf)).toString(), true);
                            String url = SmAntiFraud.option.getUrl();
                            com.ishumei.O000O0000O0oO.O0000O000000oO o0000O000000oO = new com.ishumei.O000O0000O0oO.O0000O000000oO();
                            o0000O000000oO.O0000O000000oO();
                            if (url.startsWith("https://")) {
                                o0000O000000oO.O0000O000000oO(0);
                            } else {
                                o0000O000000oO.O0000O000000oO(1);
                            }
                            o0000O000000oO.O0000O000000oO(url);
                            new com.ishumei.O000O0000O0oO.O000O00000OoO().O0000O000000oO(o0000O000000oO).O0000O000000oO(strO000O00000o0O.getBytes("utf-8"), (Map<String, String>) null, O000O0000Oo0O.this.O00O0000OooO);
                            Integer num = (Integer) O000O0000Oo0O.O0000O000000oO.get(strValueOf);
                            numValueOf = Integer.valueOf(num != null ? num.intValue() : 0);
                        } catch (Exception unused) {
                            Integer num2 = (Integer) O000O0000Oo0O.O0000O000000oO.get(strValueOf);
                            numValueOf = Integer.valueOf(num2 != null ? num2.intValue() : 0);
                            if (numValueOf.intValue() >= iO000O00000o0O - 1) {
                                Iterator it = arrayList.iterator();
                                while (it.hasNext()) {
                                    ((O00O0000OooO.O000O0000O0oO) it.next()).O000O00000oO();
                                }
                                return;
                            } else {
                                Iterator it2 = arrayList.iterator();
                                while (it2.hasNext()) {
                                    ((O00O0000OooO.O000O0000O0oO) it2.next()).O000O00000OoO(strValueOf);
                                }
                            }
                        } catch (Throwable th) {
                            Integer num3 = (Integer) O000O0000Oo0O.O0000O000000oO.get(strValueOf);
                            Integer numValueOf2 = Integer.valueOf(num3 != null ? num3.intValue() : 0);
                            if (numValueOf2.intValue() < iO000O00000o0O - 1) {
                                Iterator it3 = arrayList.iterator();
                                while (it3.hasNext()) {
                                    ((O00O0000OooO.O000O0000O0oO) it3.next()).O000O00000OoO(strValueOf);
                                }
                                O000O0000Oo0O.O0000O000000oO.put(strValueOf, Integer.valueOf(numValueOf2.intValue() + 1));
                                com.ishumei.O000O00000o0O.O0000O000000oO.O000O00000OoO().O0000O000000oO(this, 5, false, iO000O00000oO, false);
                            } else {
                                Iterator it4 = arrayList.iterator();
                                while (it4.hasNext()) {
                                    ((O00O0000OooO.O000O0000O0oO) it4.next()).O000O00000oO();
                                }
                            }
                            throw th;
                        }
                        if (numValueOf.intValue() >= iO000O00000o0O - 1) {
                            Iterator it5 = arrayList.iterator();
                            while (it5.hasNext()) {
                                ((O00O0000OooO.O000O0000O0oO) it5.next()).O000O00000oO();
                            }
                        } else {
                            Iterator it6 = arrayList.iterator();
                            while (it6.hasNext()) {
                                ((O00O0000OooO.O000O0000O0oO) it6.next()).O000O00000OoO(strValueOf);
                            }
                            O000O0000Oo0O.O0000O000000oO.put(strValueOf, Integer.valueOf(numValueOf.intValue() + 1));
                            com.ishumei.O000O00000o0O.O0000O000000oO.O000O00000OoO().O0000O000000oO(this, 5, false, iO000O00000oO, false);
                        }
                    }
                };
                try {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((O00O0000OooO.O000O0000O0oO) it.next()).O000O00000OoO(strValueOf);
                    }
                    com.ishumei.O000O00000o0O.O0000O000000oO.O000O00000OoO().O0000O000000oO(runnable, 5, false, iO000O00000oO, false);
                } catch (Exception e) {
                    com.ishumei.O000O0000OOoO.O000O00000oO.O0000O000000oO(e);
                }
            }
        };
    }

    public static O000O0000Oo0O O0000O000000oO() {
        return O000O00000OoO.O0000O000000oO;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003f A[Catch: Exception -> 0x00df, TryCatch #0 {Exception -> 0x00df, blocks: (B:17:0x0038, B:19:0x003f, B:22:0x0046, B:23:0x004b, B:24:0x004c, B:27:0x0054, B:31:0x007c, B:35:0x00c9, B:28:0x005c, B:30:0x0065, B:37:0x00d9, B:38:0x00de), top: B:40:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0054 A[Catch: Exception -> 0x00df, TRY_ENTER, TryCatch #0 {Exception -> 0x00df, blocks: (B:17:0x0038, B:19:0x003f, B:22:0x0046, B:23:0x004b, B:24:0x004c, B:27:0x0054, B:31:0x007c, B:35:0x00c9, B:28:0x005c, B:30:0x0065, B:37:0x00d9, B:38:0x00de), top: B:40:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005c A[Catch: Exception -> 0x00df, TryCatch #0 {Exception -> 0x00df, blocks: (B:17:0x0038, B:19:0x003f, B:22:0x0046, B:23:0x004b, B:24:0x004c, B:27:0x0054, B:31:0x007c, B:35:0x00c9, B:28:0x005c, B:30:0x0065, B:37:0x00d9, B:38:0x00de), top: B:40:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d9 A[Catch: Exception -> 0x00df, TryCatch #0 {Exception -> 0x00df, blocks: (B:17:0x0038, B:19:0x003f, B:22:0x0046, B:23:0x004b, B:24:0x004c, B:27:0x0054, B:31:0x007c, B:35:0x00c9, B:28:0x005c, B:30:0x0065, B:37:0x00d9, B:38:0x00de), top: B:40:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String O0000O000000oO(java.lang.String r8, boolean r9, boolean r10) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.O0000O000000oO.O000O0000Oo0O.O0000O000000oO(java.lang.String, boolean, boolean):java.lang.String");
    }

    private void O0000O000000oO(JSONObject jSONObject) {
        int iOptInt = jSONObject.optInt("c", 0);
        com.ishumei.O000O00000OoO.O000O00000o0O.O0000O000000oO.O0000O000000oO().O0000O000000oO(jSONObject.optInt("t", 0), iOptInt);
    }

    private String O000O00000OoO(String str, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject(str.trim());
            if (z) {
                O0000O000000oO(jSONObject);
            }
            return jSONObject.getString(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9b9a89969c9ab69b"));
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String O000O00000o0O(String str, boolean z) {
        return O0000O000000oO(str, z, false);
    }

    public String O0000O000000oO(O000O00000oO o000O00000oO, int i) {
        try {
            return com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO((Map<?, ?>) o000O00000oO.O0000O000000oO(i)).toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public String O0000O000000oO(String str, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9b9a8b9e9693"));
            String strOptString = jSONObject.optString(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("8c"));
            if (!TextUtils.isEmpty(strOptString)) {
                return O000O00000OoO(com.ishumei.O000O0000OOoO.O000O00000OoO.O0000O000000oO(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("859e8ece929490cf"), com.ishumei.O000O0000OOoO.O000O0000OoO.O000O0000Oo0O(strOptString)), z);
            }
            if (z) {
                O0000O000000oO(jSONObject);
            }
            return jSONObject.optString(com.ishumei.O000O0000OOoO.O000O0000Oo0O.O000O0000Oo0O("9b9a89969c9ab69b"));
        } catch (Exception unused) {
            return "";
        }
    }

    public String O0000O000000oO(boolean z) {
        try {
            return O0000O000000oO(O0000O000000oO(com.ishumei.O0000O000000oO.O000O00000OoO.O0000O000000oO(), 0), true, z ? false : true);
        } catch (Exception unused) {
            return "";
        }
    }

    public void O0000O000000oO(int i) {
        this.O000O00000oO.O000O00000OoO();
    }

    public String O000O00000OoO(boolean z) {
        try {
            return O0000O000000oO(O0000O000000oO(O000O00000o0O.O0000O000000oO(), 0), false, !z);
        } catch (Exception unused) {
            return "";
        }
    }

    public void O000O00000OoO() {
        this.O000O00000OoO.O0000O000000oO();
    }

    public void O000O00000OoO(int i) {
        if (i == 0) {
            this.O000O0000OOoO.O0000O000000oO();
        } else {
            this.O000O0000OOoO.O000O00000OoO();
        }
    }

    public void O000O00000o0O(int i) {
        if (i == 0) {
            this.O000O0000OoO.O0000O000000oO();
        } else {
            this.O000O0000OoO.O000O00000OoO();
        }
    }
}
