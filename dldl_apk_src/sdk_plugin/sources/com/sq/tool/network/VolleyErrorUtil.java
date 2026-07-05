package com.sq.tool.network;

import android.text.TextUtils;
import com.sdk.sq.net.RequestErrorCode;
import com.sqnetwork.voly.VolleyError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VolleyErrorUtil {
    public static String simpleErrorMsg(Throwable th) {
        if (th == null) {
            return "";
        }
        String message = th.getMessage();
        if (!TextUtils.isEmpty(message)) {
            return message;
        }
        if (th.getCause() != null && !TextUtils.isEmpty(th.getCause().getMessage())) {
            return th.getCause().getMessage();
        }
        return th.getClass().getSimpleName();
    }

    public static String errorMsg(VolleyError volleyError) {
        return errorMsg(httpStatus(volleyError), volleyError);
    }

    public static String errorMsg(int i, Exception exc) {
        if (exc == null) {
            return "";
        }
        String message = exc.getMessage();
        if (!TextUtils.isEmpty(message) && i > 0) {
            return exc.getClass().getSimpleName() + ":" + exc.getMessage() + "(" + i + ")";
        }
        if (!TextUtils.isEmpty(message)) {
            return exc.getClass().getSimpleName() + ":" + exc.getMessage();
        }
        if (i > 0) {
            return exc.getClass().getSimpleName() + "(" + i + ")";
        }
        return exc.toString();
    }

    public static int httpStatus(VolleyError volleyError) {
        if (volleyError.networkResponse != null) {
            return volleyError.networkResponse.statusCode;
        }
        return -1;
    }

    public static int errorCode(VolleyError volleyError) {
        return RequestErrorCode.of(volleyError);
    }
}
