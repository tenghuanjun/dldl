package com.getui.gtc.g;

import android.text.TextUtils;
import com.getui.gtc.api.SdkInfo;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.util.ScheduleQueue;
import com.getui.gtc.e.c;
import com.getui.gtc.entity.a;
import com.getui.gtc.f.b;
import com.getui.gtc.f.d;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public final class c {
    String a;
    String b;
    volatile Map<String, a.C0046a> c;

    public static class a {
        private static c a = new c(0);
    }

    private c() {
        this.c = new HashMap();
        try {
            this.a = GtcProvider.context().getFilesDir().getAbsolutePath();
            File file = new File(this.a);
            if (!file.exists()) {
                file.mkdirs();
            }
            this.b = this.a + File.separator + "libs";
            File file2 = new File(this.b);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            HashSet<String> hashSet = new HashSet(c.a.a.a.k);
            com.getui.gtc.i.c.a.a("start remove: ".concat(String.valueOf(hashSet)));
            for (String str : hashSet) {
                if (a(str)) {
                    c.a.a.a.e(str);
                }
            }
            com.getui.gtc.dyc.b.a.a(GtcProvider.context(), new d() { // from class: com.getui.gtc.g.c.1
                @Override // com.getui.gtc.f.d
                public final void a(String str2) {
                }

                @Override // com.getui.gtc.f.d
                public final void a(Map<String, String> map, Map<String, String> map2) {
                    if (map == null || map2 == null) {
                        return;
                    }
                    com.getui.gtc.entity.a aVarA = com.getui.gtc.entity.a.a(map2);
                    com.getui.gtc.entity.a aVarA2 = com.getui.gtc.entity.a.a(map);
                    if (aVarA2 != null) {
                        int size = aVarA2.a.size();
                        HashSet hashSet2 = new HashSet();
                        for (int i = 0; i < size; i++) {
                            a.C0046a c0046aA = aVarA2.a(i);
                            if (aVarA == null || aVarA.b(c0046aA.a) == null || !aVarA.b(c0046aA.a).b.equalsIgnoreCase(c0046aA.b) || !aVarA.b(c0046aA.a).c.equalsIgnoreCase(c0046aA.c)) {
                                hashSet2.add(c0046aA.c);
                            }
                        }
                        com.getui.gtc.i.c.a.a("wait remove: ".concat(String.valueOf(hashSet2)));
                        c.a.a.a.a(hashSet2);
                    }
                    if (aVarA != null) {
                        int size2 = aVarA.a.size();
                        for (int i2 = 0; i2 < size2; i2++) {
                            a.C0046a c0046aA2 = aVarA.a(i2);
                            if (!c.this.a(c0046aA2)) {
                                try {
                                    com.getui.gtc.h.b.a(c0046aA2, c.this.b + File.separator + c0046aA2.c);
                                } catch (Exception e) {
                                    com.getui.gtc.i.c.a.a(e);
                                }
                            }
                        }
                    }
                }
            }.c);
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
    }

    /* synthetic */ c(byte b) {
        this();
    }

    private void b(SdkInfo sdkInfo) {
        List<a.C0046a> stubs = sdkInfo.getStubs();
        for (int i = 0; i < stubs.size(); i++) {
            final a.C0046a c0046a = stubs.get(i);
            if (!b(c0046a)) {
                this.c.put(c0046a.d, c0046a);
                if (c0046a.j) {
                    b.a(GtcProvider.context(), null, null, c0046a.d, sdkInfo.getAppid(), sdkInfo.getCid(), new com.getui.gtc.g.a.b() { // from class: com.getui.gtc.g.c.2
                        @Override // com.getui.gtc.g.a.b
                        public final void a(boolean z) {
                            if (z) {
                                return;
                            }
                            c.this.c.remove(c0046a.d);
                        }
                    });
                } else {
                    try {
                        b.a(GtcProvider.context(), c0046a.d, sdkInfo.getAppid(), sdkInfo.getCid());
                    } catch (Throwable th) {
                        this.c.remove(c0046a.d);
                        com.getui.gtc.i.c.a.b("local ext failed: ".concat(String.valueOf(th)));
                    }
                }
            }
        }
    }

    private boolean b(a.C0046a c0046a) {
        if (this.c == null) {
            return false;
        }
        return this.c.containsKey(c0046a.d);
    }

    private com.getui.gtc.entity.a c(final SdkInfo sdkInfo) {
        Map<String, String> mapA = com.getui.gtc.f.b.a(sdkInfo, new b.a() { // from class: com.getui.gtc.g.c.3
            @Override // com.getui.gtc.f.b.a
            public final void a(Map<String, String> map) {
                c.this.a(sdkInfo, com.getui.gtc.entity.a.a(map));
            }
        });
        if (mapA == null) {
            return null;
        }
        return com.getui.gtc.entity.a.a(mapA);
    }

    final String a(SdkInfo sdkInfo, a.C0046a c0046a) {
        com.getui.gtc.entity.a aVarA;
        a.C0046a c0046aB;
        File file = new File(this.b + File.separator + c0046a.c);
        if (a(c0046a)) {
            return file.getAbsolutePath();
        }
        try {
            Map<String, String> mapA = com.getui.gtc.dyc.b.a.a(GtcProvider.context(), sdkInfo.getModuleName());
            if (mapA == null || (aVarA = com.getui.gtc.entity.a.a(mapA)) == null || (c0046aB = aVarA.b(c0046a.a)) == null || !c0046aB.e.equals(c0046a.e)) {
                return null;
            }
            com.getui.gtc.h.b.a(c0046a, file.getAbsolutePath());
            if (com.getui.gtc.i.b.a.a(file.getAbsolutePath()).equals(c0046a.e)) {
                return file.getAbsolutePath();
            }
            com.getui.gtc.i.b.a.a(file);
            throw new Exception("The net ext save failed or has a wrong checksum");
        } catch (Exception e) {
            com.getui.gtc.i.c.a.a(e);
            return null;
        }
    }

    public final void a(SdkInfo sdkInfo) {
        b(sdkInfo);
        a(sdkInfo, c(sdkInfo));
    }

    final void a(final SdkInfo sdkInfo, com.getui.gtc.entity.a aVar) {
        if (aVar == null) {
            return;
        }
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 0; i < aVar.a.size(); i++) {
            final a.C0046a c0046aA = aVar.a(i);
            final long jA = c.a.a.b.a(c0046aA.a);
            if (!b(c0046aA)) {
                if ((c0046aA.g <= 0 || jA <= 0 || System.currentTimeMillis() - jA <= c0046aA.g) && (jA <= 0 || !c0046aA.i)) {
                    this.c.put(c0046aA.d, c0046aA);
                    ScheduleQueue.getInstance().addSchedule(new Runnable() { // from class: com.getui.gtc.g.c.4
                        @Override // java.lang.Runnable
                        public final void run() throws Throwable {
                            String strA = c.this.a(sdkInfo, c0046aA);
                            if (strA == null) {
                                c.this.c.remove(c0046aA.d);
                                return;
                            }
                            File file = new File(strA);
                            final File file2 = new File(strA + com.getui.gtc.c.a.b);
                            com.getui.gtc.i.a.a.a(file, file2, c0046aA.f);
                            try {
                                if (c0046aA.j) {
                                    b.a(GtcProvider.context(), file2.getAbsolutePath(), c.this.a, c0046aA.d, sdkInfo.getAppid(), sdkInfo.getCid(), new com.getui.gtc.g.a.b() { // from class: com.getui.gtc.g.c.4.1
                                        @Override // com.getui.gtc.g.a.b
                                        public final void a(boolean z) {
                                            com.getui.gtc.i.b.a.a(file2);
                                            if (!z) {
                                                c.this.c.remove(c0046aA.d);
                                            } else if (jA == 0) {
                                                c.a.a.b.a(c0046aA.a, System.currentTimeMillis());
                                            }
                                        }
                                    });
                                    return;
                                }
                                b.a(GtcProvider.context(), file2.getAbsolutePath(), c.this.a, c0046aA.d, sdkInfo.getAppid(), sdkInfo.getCid());
                                if (jA == 0) {
                                    c.a.a.b.a(c0046aA.a, System.currentTimeMillis());
                                }
                            } catch (Throwable th) {
                                c.this.c.remove(c0046aA.d);
                                com.getui.gtc.i.c.a.b("net ext filed: ".concat(String.valueOf(th)));
                            } finally {
                                com.getui.gtc.i.b.a.a(file2);
                            }
                        }
                    });
                } else {
                    arrayList.add(Integer.valueOf(i));
                }
            }
        }
        for (Integer num : arrayList) {
            a(aVar.a(num.intValue()).c);
            aVar.c(num.intValue());
        }
    }

    final boolean a(a.C0046a c0046a) {
        File file = new File(this.b + File.separator + c0046a.c);
        if (file.exists() && file.isFile()) {
            return com.getui.gtc.i.b.a.a(file.getAbsolutePath()).equals(c0046a.e);
        }
        return false;
    }

    public final boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            com.getui.gtc.i.b.a.a(this.a, str);
            String strA = com.getui.gtc.g.a.a(str);
            if (TextUtils.isEmpty(strA)) {
                return true;
            }
            com.getui.gtc.i.b.a.a(new File(this.a + "/" + strA));
            return true;
        } catch (Exception e) {
            com.getui.gtc.i.c.a.b(e);
            return false;
        }
    }
}
