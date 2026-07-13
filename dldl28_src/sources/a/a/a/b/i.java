package a.a.a.b;

import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: compiled from: TurboSDK.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements a.a.a.e.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f78a;

    public i(e eVar) {
        this.f78a = eVar;
    }

    @Override // a.a.a.e.d
    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                e.n = new JSONObject(str);
            } catch (Exception e) {
                a.a.a.g.b.a("TurboSDK", "querySwitchMessage  parse error" + e.getMessage());
            }
        }
        e eVar = this.f78a;
        eVar.getClass();
        e.o = true;
        eVar.e();
        if (eVar.b()) {
            eVar.f();
        }
    }

    @Override // a.a.a.e.d
    public void a(a.a.a.d.b bVar) {
        e eVar = this.f78a;
        eVar.getClass();
        e.o = true;
        eVar.e();
        if (eVar.b()) {
            eVar.f();
        }
    }
}
