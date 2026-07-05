package com.getui.gtc.a;

import android.text.TextUtils;
import com.getui.gtc.base.util.ScheduleQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    private static List<b> a = new ArrayList();
    private static AtomicBoolean b = new AtomicBoolean(false);

    static {
        a.add(new c());
        a.add(new d());
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.contains("|")) {
            str.replace("|", "$");
        }
        return str;
    }

    public static void a() {
        if (b.getAndSet(true)) {
            return;
        }
        Iterator<b> it = a.iterator();
        while (it.hasNext()) {
            ScheduleQueue.getInstance().addSchedule(it.next(), com.igexin.push.config.c.i);
        }
    }
}
