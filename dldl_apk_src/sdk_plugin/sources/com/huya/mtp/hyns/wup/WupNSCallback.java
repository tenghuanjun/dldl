package com.huya.mtp.hyns.wup;

import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.NSCallback;
import com.huya.mtp.hyns.NSException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class WupNSCallback implements NSCallback {
    public static final String TAG = "WupNSCallback";

    public abstract void onError(NSException nSException, boolean z, WupError wupError);

    @Override // com.huya.mtp.hyns.NSCallback
    public final void onError(NSException nSException) {
        WupError wupError = null;
        boolean z = false;
        for (Throwable cause = nSException; cause != null; cause = cause.getCause()) {
            if (cause instanceof WupError) {
                wupError = (WupError) cause;
                z = true;
            }
        }
        MTPApi.LOGGER.debug(TAG, "isWupError: %s", Boolean.valueOf(z));
        onError(nSException, z, wupError);
    }
}
