package a.a.a.c;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONObject;

/* JADX INFO: compiled from: DetectManager.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f83a;
    public final /* synthetic */ Application b;
    public final /* synthetic */ a.a.a.a c;

    public e(String str, Application application, a.a.a.a aVar) {
        this.f83a = str;
        this.b = application;
        this.c = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        a.a.a.c.m.b bVar;
        try {
            if (a.a.a.b.d.a()) {
                String str = this.f83a;
                if (TextUtils.isEmpty(str)) {
                    d.d("Manager", "config is empty");
                    return;
                }
                if (TextUtils.isEmpty(str)) {
                    bVar = null;
                } else {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        bVar = new a.a.a.c.m.b();
                        bVar.a(bVar, jSONObject);
                    } catch (Exception e) {
                        d.d("Manager", Log.getStackTraceString(e));
                        bVar = null;
                    }
                }
                if (bVar != null) {
                    a.a.a.b.d.f70a = bVar.f93a;
                    d.a("Manager", "config:" + bVar.toString());
                }
                if (bVar != null && (bVar.f93a & 1) != 0) {
                    if (a.a.a.b.d.a(bVar)) {
                        d.a("Manager", "logicDuplicated");
                    } else {
                        if (a.a.a.b.d.a(this.b.getApplicationContext(), bVar)) {
                            return;
                        }
                        a.a.a.b.d.a(this.b, bVar, this.c);
                    }
                }
            }
        } catch (Throwable th) {
            d.b("Manager", Log.getStackTraceString(th));
        }
    }
}
