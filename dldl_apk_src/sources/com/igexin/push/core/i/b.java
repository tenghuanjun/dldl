package com.igexin.push.core.i;

import android.content.Intent;
import com.igexin.push.core.e;
import com.igexin.sdk.PushActivity;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b {
    private static b b;
    private Map<Long, a> a = new HashMap();

    private b() {
    }

    public static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    private void b(a aVar) {
        if (aVar != null) {
            if (aVar != null) {
                this.a.put(aVar.a(), aVar);
            }
            Intent intent = new Intent(e.i, (Class<?>) PushActivity.class);
            intent.putExtra("activityid", aVar.a());
            intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            e.i.startActivity(intent);
        }
    }

    private void c(a aVar) {
        if (aVar != null) {
            a(aVar);
        }
    }

    private void d(a aVar) {
        if (aVar != null) {
            this.a.put(aVar.a(), aVar);
        }
    }

    public final a a(Long l) {
        return this.a.get(l);
    }

    public final void a(a aVar) {
        if (aVar != null) {
            this.a.remove(aVar.a());
        }
    }
}
