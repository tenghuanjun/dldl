package com.igexin.push.core.c;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.igexin.b.a.d.f;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.b.p;
import com.igexin.push.core.e;
import com.igexin.push.e.a.c;
import com.igexin.push.f.n;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public static a a = null;
    private static final String b = "BIDataManager";

    private static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    private void b() {
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        if (arrayList.isEmpty()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", "reportapplist");
            jSONObject.put("session_last", e.w);
            JSONArray jSONArray = new JSONArray();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("appid", arrayList.get(i).c);
                jSONObject2.put("name", arrayList.get(i).a);
                jSONObject2.put("version", arrayList.get(i).b);
                jSONObject2.put(TTDownloadField.TT_VERSION_NAME, arrayList.get(i).d);
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("applist", jSONArray);
        } catch (Exception unused) {
        }
        com.igexin.b.a.b.e.a().a((f) new c(new com.igexin.push.core.h.a(SDKUrlConfig.getBiUploadServiceUrl(), jSONObject.toString().getBytes())), false, true);
        com.igexin.b.a.c.a.a("reportapplist", new Object[0]);
    }

    private String c() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        a(arrayList2);
        Iterator<p> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().c);
        }
        return arrayList.toString();
    }

    public final void a(List<p> list) {
        Comparator<p> comparator = new Comparator<p>() { // from class: com.igexin.push.core.c.a.1
            private static int a(p pVar, p pVar2) {
                if (pVar.c.equals(pVar2.c)) {
                    return 0;
                }
                return pVar.c.compareTo(pVar2.c);
            }

            @Override // java.util.Comparator
            public final /* synthetic */ int compare(p pVar, p pVar2) {
                p pVar3 = pVar;
                p pVar4 = pVar2;
                if (pVar3.c.equals(pVar4.c)) {
                    return 0;
                }
                return pVar3.c.compareTo(pVar4.c);
            }
        };
        PackageManager packageManager = e.i.getPackageManager();
        List<PackageInfo> listA = n.a();
        for (int i = 0; i < listA.size(); i++) {
            try {
                PackageInfo packageInfo = listA.get(i);
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                if ((applicationInfo.flags & 1) <= 0) {
                    p pVar = new p();
                    pVar.a = applicationInfo.loadLabel(packageManager).toString();
                    pVar.c = applicationInfo.packageName;
                    pVar.b = String.valueOf(packageInfo.versionCode);
                    pVar.d = packageInfo.versionName;
                    list.add(pVar);
                }
            } catch (Exception unused) {
            }
        }
        Collections.sort(list, comparator);
    }
}
