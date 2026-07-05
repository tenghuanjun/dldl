package com.huya.mtp.hyns;

import com.huya.hysignal.core.HySignalException;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.wup.WupError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HySignalErrorParse {
    public static final String TAG = "HySignalErrorParse";

    public interface OnParse {
        void onHysignalParse(int i, int i2);

        void onWupCodeParse(int i);
    }

    public static void parseError(NSException nSException, OnParse onParse) {
        for (Throwable cause = nSException; cause != null; cause = cause.getCause()) {
            if (cause instanceof WupError) {
                int i = ((WupError) cause).mCode;
                MTPApi.LOGGER.error("NetServiceHySignalErrorParse", "error code : " + i);
                if (onParse != null) {
                    onParse.onWupCodeParse(i);
                    return;
                }
                return;
            }
            if (cause instanceof HySignalException) {
                MTPApi.LOGGER.error("NetServiceHySignalErrorParse", cause.toString());
                if (onParse != null) {
                    HySignalException hySignalException = (HySignalException) cause;
                    onParse.onHysignalParse(hySignalException.getErrorType(), hySignalException.getErrorCode());
                    return;
                }
                return;
            }
        }
    }
}
