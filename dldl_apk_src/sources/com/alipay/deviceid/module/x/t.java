package com.alipay.deviceid.module.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class t {
    private File a;
    private q b;

    /* JADX INFO: renamed from: com.alipay.deviceid.module.x.t$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        public AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                t.this.a();
            } catch (Exception e) {
                u.a(e);
            }
        }
    }

    public t(String str, q qVar) {
        this.a = null;
        this.b = null;
        this.a = new File(str);
        this.b = qVar;
    }

    private static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "id");
            jSONObject.put("error", str);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    final synchronized void a() {
        if (this.a == null) {
            return;
        }
        if (this.a.exists() && this.a.isDirectory() && this.a.list().length != 0) {
            ArrayList arrayList = new ArrayList();
            for (String str : this.a.list()) {
                arrayList.add(str);
            }
            Collections.sort(arrayList);
            String str2 = (String) arrayList.get(arrayList.size() - 1);
            int size = arrayList.size();
            if (str2.equals(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log")) {
                if (arrayList.size() < 2) {
                    return;
                }
                str2 = (String) arrayList.get(arrayList.size() - 2);
                size--;
            }
            if (!this.b.a(a(f.a(this.a.getAbsolutePath(), str2)))) {
                size--;
            }
            for (int i = 0; i < size; i++) {
                new File(this.a, (String) arrayList.get(i)).delete();
            }
        }
    }
}
