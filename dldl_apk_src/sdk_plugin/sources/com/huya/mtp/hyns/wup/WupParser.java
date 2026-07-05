package com.huya.mtp.hyns.wup;

import com.huya.mtp.api.MTPApi;
import com.huya.mtp.hyns.NSException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WupParser {
    public static final String TAG = "WupErrorParser";

    public interface OnWupCodeParseListener {
        void onWupCodeParse(int i);
    }

    public static boolean parseError(NSException nSException, OnWupCodeParseListener onWupCodeParseListener) {
        for (Throwable cause = nSException; cause != null; cause = cause.getCause()) {
            if (cause instanceof WupError) {
                int i = ((WupError) cause).mCode;
                MTPApi.LOGGER.error("NetServiceWupErrorParser", "error code : " + i);
                if (onWupCodeParseListener == null) {
                    return true;
                }
                onWupCodeParseListener.onWupCodeParse(i);
                return true;
            }
        }
        return false;
    }
}
