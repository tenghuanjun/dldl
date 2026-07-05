package com.ss.android.socialbase.appdownloader.a;

import android.content.Context;
import android.util.Log;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public abstract class a implements e {
    protected final Context a;
    protected final DownloadSetting b;
    protected final String c;

    public a(Context context, DownloadSetting downloadSetting, String str) {
        this.a = context;
        this.b = downloadSetting;
        this.c = str;
    }

    public boolean a() {
        if (this.a == null) {
            return false;
        }
        try {
        } catch (Throwable th) {
            if (Logger.debug()) {
                Log.e("AbsDevicePlan", "check is valid failed!", th);
            }
        }
        return b().resolveActivity(this.a.getPackageManager()) != null;
    }
}
