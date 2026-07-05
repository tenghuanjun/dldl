package com.sqwan.afinal.download;

import android.content.Context;
import com.sqwan.common.mod.download.IDownloadMod;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.api.SQResultListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DownloadModImpl implements IDownloadMod {
    private static final long DEBOUNCE_DELAY = 2000;
    private long lastInstallTime = 0;
    private Context mContext;

    public DownloadModImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.sqwan.common.mod.download.IDownloadMod
    public void installApk(String str, SQResultListener sQResultListener) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastInstallTime < DEBOUNCE_DELAY) {
            LogUtil.w("installApk：距离上次调用时间过短，忽略本次操作");
        } else {
            this.lastInstallTime = jCurrentTimeMillis;
            SQDownloadManager.getInstance().showDialog(str);
        }
    }
}
