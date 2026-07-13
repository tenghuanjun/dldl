package a.a.a.b;

import a.a.a.b.e;
import android.app.Activity;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: GameDurationHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, Long> f69a = new HashMap();
    public static long b = 0;

    public static void a(Activity activity) {
        if (activity == null) {
            a.a.a.g.b.b("GameDurationHelper", "activity is null");
            return;
        }
        String name = activity.getClass().getName();
        if (TextUtils.isEmpty(name)) {
            a.a.a.g.b.b("GameDurationHelper", "pageName is empty:" + name);
            return;
        }
        Long l = f69a.get(name);
        if (l == null) {
            a.a.a.g.b.a("GameDurationHelper", "lastTime is null in pageName:" + name);
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - l.longValue();
        if (jCurrentTimeMillis < 3000) {
            a.a.a.g.b.a("GameDurationHelper", "ignore this time while duration is less than 3s");
            return;
        }
        a.a.a.d.a aVar = new a.a.a.d.a("EVENT_GAME_REPORT_DURATION");
        aVar.c = jCurrentTimeMillis;
        e.c.INSTANCE.f74a.a(aVar);
    }

    public static void b(Activity activity) {
        if (activity == null) {
            a.a.a.g.b.b("GameDurationHelper", "activity is null");
            return;
        }
        String name = activity.getClass().getName();
        if (!TextUtils.isEmpty(name)) {
            f69a.put(name, Long.valueOf(System.currentTimeMillis()));
            return;
        }
        a.a.a.g.b.b("GameDurationHelper", "pageName is empty:" + name);
    }

    public static void a() {
        b = System.currentTimeMillis();
    }
}
