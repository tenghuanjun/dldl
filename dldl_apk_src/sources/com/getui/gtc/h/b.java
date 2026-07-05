package com.getui.gtc.h;

import com.getui.gtc.base.http.Request;
import com.getui.gtc.base.http.Response;
import com.getui.gtc.e.c;
import com.getui.gtc.entity.a;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b {
    public static void a(a.C0046a c0046a, String str) throws Exception {
        if (a(c0046a)) {
            throw new RuntimeException("The download request is unusual, too many times in a short time");
        }
        Response responseExecute = d.a.newCall(new Request.Builder().url(c0046a.h).method("GET").build()).execute();
        if (responseExecute.body() == null) {
            throw new RuntimeException("can not save file, body is null");
        }
        responseExecute.body().file(new File(str));
    }

    private static boolean a(a.C0046a c0046a) {
        int iC = c.a.a.b.c(c0046a.a);
        long jB = c.a.a.b.b(c0046a.a);
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z = true;
        if (iC >= 10) {
            iC = -1;
        } else if (iC >= 0) {
            if (jCurrentTimeMillis - jB > 3600000) {
                iC = 0;
            } else {
                iC++;
                jCurrentTimeMillis = jB;
            }
            z = false;
        } else if (jCurrentTimeMillis - jB > 3600000) {
            iC = 0;
        } else {
            jCurrentTimeMillis = jB;
        }
        c.a.a.b.a(c0046a.a, jCurrentTimeMillis, iC);
        return z;
    }
}
