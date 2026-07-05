package com.ss.android.socialbase.downloader.impls;

import com.alipay.security.mobile.module.deviceinfo.e;
import com.igexin.push.config.c;
import com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class DefaultRetryDelayTimeCalculator implements IRetryDelayTimeCalculator {
    @Override // com.ss.android.socialbase.downloader.downloader.IRetryDelayTimeCalculator
    public long calculateRetryDelayTime(int i, int i2) {
        if (i == 1) {
            return 3000L;
        }
        if (i == 2) {
            return 15000L;
        }
        if (i == 3) {
            return c.k;
        }
        if (i > 3) {
            return e.a;
        }
        return 0L;
    }
}
