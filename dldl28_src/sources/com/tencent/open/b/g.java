package com.tencent.open.b;

import android.content.Context;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public class g {
    public static void a(String str) {
    }

    public static void a(String str, List<Serializable> list) {
    }

    static void a() {
        Context contextA = com.tencent.open.utils.g.a();
        if (contextA == null) {
            return;
        }
        contextA.deleteDatabase("sdk_report.db");
    }

    public static List<Serializable> b(String str) {
        return Collections.synchronizedList(new ArrayList());
    }
}
