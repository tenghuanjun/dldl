package com.ss.android.socialbase.appdownloader.a;

import android.content.Context;
import android.content.Intent;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class l extends a {
    public l(Context context, DownloadSetting downloadSetting, String str) {
        super(context, downloadSetting, str);
    }

    @Override // com.ss.android.socialbase.appdownloader.a.e
    public Intent b() {
        String strOptString = this.b.optString(com.igexin.push.core.d.c.d);
        String strA = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("ag"), strOptString);
        String strA2 = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("ah"), strOptString);
        String strA3 = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("ai"), strOptString);
        String strA4 = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("aj"), strOptString);
        Intent intent = new Intent();
        intent.putExtra(strA, this.c);
        intent.putExtra(strA2, "*/*");
        intent.putExtra(strA3, true);
        intent.setAction(strA4);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.addFlags(32768);
        return intent;
    }
}
