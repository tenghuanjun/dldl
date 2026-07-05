package com.getui.gtc.dyc;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.http.Call;
import com.getui.gtc.base.http.Response;
import com.getui.gtc.dyc.b.b;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class g {

    /* JADX INFO: renamed from: com.getui.gtc.dyc.g$1, reason: invalid class name */
    class AnonymousClass1 implements Call.Callback {
        final /* synthetic */ c a;
        final /* synthetic */ d c;
        final /* synthetic */ b d;

        @Override // com.getui.gtc.base.http.Call.Callback
        public void onFailure(Call call, Exception exc) {
            c cVar = this.a;
            if (cVar != null) {
                cVar.a(exc);
            }
        }

        @Override // com.getui.gtc.base.http.Call.Callback
        public void onResponse(Call call, Response response) {
            try {
                h hVarA = this.c.a(this.d, response);
                if (this.a != null) {
                    this.a.a(hVarA);
                }
            } catch (Throwable th) {
                com.getui.gtc.dyc.a.a.a.c(th);
                c cVar = this.a;
                if (cVar != null) {
                    cVar.a(th);
                }
            }
        }
    }

    static class a {
        private static g a = new g(null);
    }

    public interface c {
        void a(h hVar);

        void a(Throwable th);
    }

    private g() {
        a(GtcProvider.context());
    }

    /* synthetic */ g(AnonymousClass1 anonymousClass1) {
        this();
    }

    static g a() {
        return a.a;
    }

    private void a(Context context) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            com.getui.gtc.dyc.a.a.a.c(e);
            applicationInfo = null;
        }
        if (applicationInfo == null || applicationInfo.metaData == null) {
            return;
        }
        String string = applicationInfo.metaData.getString("DYC_P");
        if (!TextUtils.isEmpty(string)) {
            d.a = string;
        }
        String string2 = applicationInfo.metaData.getString("DYC_K");
        if (TextUtils.isEmpty(string2)) {
            return;
        }
        d.c = string2;
    }

    public h a(b bVar) throws Exception {
        return new d().a(bVar);
    }
}
