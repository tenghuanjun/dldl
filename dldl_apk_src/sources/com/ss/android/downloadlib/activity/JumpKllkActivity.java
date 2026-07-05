package com.ss.android.downloadlib.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.e.c;
import com.ss.android.downloadlib.g.h;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class JumpKllkActivity extends TTDelegateActivity {
    @Override // com.ss.android.downloadlib.activity.TTDelegateActivity
    protected void a() {
        Intent intent = getIntent();
        if (getIntent() == null) {
            c.a().a("handleIntent is null");
            com.ss.android.socialbase.appdownloader.c.a((Activity) this);
            return;
        }
        String stringExtra = intent.getStringExtra(com.igexin.push.core.d.c.c);
        long longExtra = intent.getLongExtra("id", 0L);
        if (TextUtils.isEmpty(stringExtra) || longExtra == 0) {
            c.a().a("getPackage or id is null");
            com.ss.android.socialbase.appdownloader.c.a((Activity) this);
        }
        boolean booleanExtra = intent.getBooleanExtra("dl", false);
        String stringExtra2 = intent.getStringExtra("bk");
        if ((!TextUtils.isEmpty(stringExtra2)) & booleanExtra) {
            h.a((Context) this, stringExtra, longExtra, stringExtra2, true);
            com.ss.android.socialbase.appdownloader.c.a((Activity) this);
            return;
        }
        int iOptInt = k.j().optInt("ab", 0);
        h.a(this, stringExtra, longExtra, iOptInt == 1);
        if (iOptInt != 1) {
            com.ss.android.socialbase.appdownloader.c.a((Activity) this);
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        com.ss.android.socialbase.appdownloader.c.a((Activity) this);
    }
}
