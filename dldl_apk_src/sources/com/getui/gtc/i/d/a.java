package com.getui.gtc.i.d;

import com.getui.gtc.dim.DimManager;
import com.getui.gtc.f.b;
import com.getui.gtc.f.d;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {

    /* JADX INFO: renamed from: com.getui.gtc.i.d.a$a, reason: collision with other inner class name */
    public static class C0048a {
        private static final a a = new a(0);
    }

    private a() {
        a(b.a(new d() { // from class: com.getui.gtc.i.d.a.1
            @Override // com.getui.gtc.f.d
            public final void a(String str) {
            }

            @Override // com.getui.gtc.f.d
            public final void a(Map<String, String> map, Map<String, String> map2) {
                a.a(map2);
            }
        }));
    }

    /* synthetic */ a(byte b) {
        this();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:129:0x01ea. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<java.lang.String> a(java.lang.String r2) {
        /*
            Method dump skipped, instruction units count: 916
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.i.d.a.a(java.lang.String):java.util.List");
    }

    static void a(Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        try {
            if (map.containsKey("sdk.gtc.dim.ram.valid.time")) {
                for (String str : map.get("sdk.gtc.dim.ram.valid.time").split(com.igexin.push.core.b.aj)) {
                    if (str.contains(":")) {
                        String[] strArrSplit = str.split(":");
                        List<String> listA = a(strArrSplit[0]);
                        long j = Long.parseLong(strArrSplit[1]);
                        Iterator<String> it = listA.iterator();
                        while (it.hasNext()) {
                            DimManager.getInstance().set("dim-2-2-1-1", it.next(), String.valueOf(j * 1000));
                        }
                    } else {
                        DimManager.getInstance().set("dim-2-2-1-1", "dim-2-2-1-1", String.valueOf(Long.parseLong(str) * 1000));
                    }
                }
            }
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.a(th);
        }
        try {
            if (map.containsKey("sdk.gtc.dim.storage.valid.time")) {
                for (String str2 : map.get("sdk.gtc.dim.storage.valid.time").split(com.igexin.push.core.b.aj)) {
                    if (str2.contains(":")) {
                        String[] strArrSplit2 = str2.split(":");
                        List<String> listA2 = a(strArrSplit2[0]);
                        long j2 = Long.parseLong(strArrSplit2[1]);
                        Iterator<String> it2 = listA2.iterator();
                        while (it2.hasNext()) {
                            DimManager.getInstance().set("dim-2-2-2-1", it2.next(), String.valueOf(j2 * 1000));
                        }
                    } else {
                        DimManager.getInstance().set("dim-2-2-2-1", "dim-2-2-2-1", String.valueOf(Long.parseLong(str2) * 1000));
                    }
                }
            }
        } catch (Throwable th2) {
            com.getui.gtc.i.c.a.a(th2);
        }
        try {
            if (map.containsKey("sdk.gtc.dim.sys.call.enable")) {
                for (String str3 : map.get("sdk.gtc.dim.sys.call.enable").split(com.igexin.push.core.b.aj)) {
                    if (str3.contains(":")) {
                        String[] strArrSplit3 = str3.split(":");
                        List<String> listA3 = a(strArrSplit3[0]);
                        int i = Integer.parseInt(strArrSplit3[1]);
                        Iterator<String> it3 = listA3.iterator();
                        while (it3.hasNext()) {
                            DimManager.getInstance().set("dim-2-2-3-1", it3.next(), String.valueOf(i));
                        }
                    } else {
                        DimManager.getInstance().set("dim-2-2-3-1", "dim-2-2-3-1", String.valueOf(Integer.parseInt(str3)));
                    }
                }
            }
        } catch (Throwable th3) {
            com.getui.gtc.i.c.a.a(th3);
        }
        try {
            if (map.containsKey("sdk.gtc.dim.rom.map.extension")) {
                DimManager.getInstance().set("dim-2-2-4-1", "dim-2-2-4-1", map.get("sdk.gtc.dim.rom.map.extension"));
            }
        } catch (Throwable th4) {
            com.getui.gtc.i.c.a.a(th4);
        }
        try {
            if (map.containsKey("sdk.gtc.dim.al.tech_policy")) {
                DimManager.getInstance().set("dim-2-2-5-1", "dim-2-2-5-1", map.get("sdk.gtc.dim.al.tech_policy"));
            }
        } catch (Throwable th5) {
            com.getui.gtc.i.c.a.a(th5);
        }
        try {
            if (map.containsKey("sdk.gtc.dim.complex.policy")) {
                for (String str4 : map.get("sdk.gtc.dim.complex.policy").split(com.igexin.push.core.b.aj)) {
                    String[] strArrSplit4 = str4.split("#");
                    List<String> listA4 = a(strArrSplit4[0]);
                    String[] strArrSplit5 = strArrSplit4[1].split(":");
                    StringBuilder sb = new StringBuilder();
                    for (String str5 : strArrSplit5) {
                        Iterator<String> it4 = a(str5).iterator();
                        while (it4.hasNext()) {
                            sb.append(it4.next());
                            sb.append(com.igexin.push.core.b.aj);
                        }
                    }
                    Iterator<String> it5 = listA4.iterator();
                    while (it5.hasNext()) {
                        DimManager.getInstance().set("dim-2-2-6-1", it5.next(), sb.toString());
                    }
                }
            }
        } catch (Throwable th6) {
            com.getui.gtc.i.c.a.a(th6);
        }
    }
}
