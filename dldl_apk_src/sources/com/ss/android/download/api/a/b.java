package com.ss.android.download.api.a;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import com.ss.android.download.api.config.i;
import com.ss.android.download.api.config.t;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class b implements i {
    private t a;

    @Override // com.ss.android.download.api.config.i
    public void a(Activity activity, String[] strArr, t tVar) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.a = tVar;
            activity.requestPermissions(strArr, 1);
        } else if (tVar != null) {
            tVar.a();
        }
    }

    @Override // com.ss.android.download.api.config.i
    public boolean a(Context context, String str) {
        return context != null && ContextCompat.checkSelfPermission(context, str) == 0;
    }

    @Override // com.ss.android.download.api.config.i
    public void a(Activity activity, int i, String[] strArr, int[] iArr) {
        t tVar;
        if (iArr.length <= 0 || (tVar = this.a) == null) {
            return;
        }
        if (iArr[0] == -1) {
            tVar.a(strArr[0]);
        } else if (iArr[0] == 0) {
            tVar.a();
        }
    }
}
