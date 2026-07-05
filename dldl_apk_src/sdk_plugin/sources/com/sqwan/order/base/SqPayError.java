package com.sqwan.order.base;

import android.os.Parcelable;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.base.SqError;
import com.sqwan.common.constants.SqErrorCode;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SqPayError extends SqError implements Parcelable {
    public static final int MODULE_HTTP = 2;
    public static final int MODULE_INTERNAL = 1;
    public static final int MODULE_THIRD = 3;
    public static final int ERROR_SDK_UNSUPPORTED = code(1, 1);
    public static final int ERROR_SDK_PRICE = code(1, 10);
    public static final int ERROR_SDK_INVALID_PAY_URL = code(1, 11);
    public static final int ERROR_SDK_USER_ERROR = code(1, 12);
    public static final int ERROR_SDK_INVALID_PAY_TYPE = code(1, 13);
    public static final int ERROR_SDK_CONSUME_SUCCESS_AT_PAY = code(1, 14);
    public static final int ERROR_SDK_CONSUME_FAIL_AT_PAY = code(1, 15);
    public static final int ERROR_SDK_CONFIG_AGE = code(1, 16);
    public static final int ERROR_SDK_RECHARGE_TIP = code(1, 17);
    public static final int ERROR_SDK_INLINE = code(1, 18);
    public static final int ERROR_SDK_VERIFY_URL = code(1, 19);
    public static final int ERROR_SDK_DISABLE_PAY = code(1, 20);
    public static final int ERROR_SDK_AUTH = code(1, 21);
    public static final int ERROR_SDK_ADDITION = code(1, 22);
    public static final int ERROR_SDK_NATIVE = code(1, 23);
    public static final int ERROR_HTTP_AGE_CONFIG = code(2, 10);
    public static final int ERROR_HTTP_RECHARGE_LIMIT = code(2, 11);
    public static final int ERROR_HTTP_CREATE_ORDER = code(2, 12);
    public static final int ERROR_HTTP_CONFIRM_ORDER = code(2, 13);
    public static final int ERROR_HTTP_FIND_ORDER = code(2, 14);
    public static final int ERROR_HTTP_VERIFY = code(2, 15);
    public static final int ERROR_HTTP_GPP_ORDER = code(2, 16);
    public static final int ERROR_THIRD_INIT = code(3, 10);
    public static final int ERROR_THIRD_QUERY_DETAIL = code(3, 11);
    public static final int ERROR_THIRD_PURCHASE = code(3, 12);
    public static final int ERROR_THIRD_QUERY_PURCHASE = code(3, 13);
    public static final int ERROR_THIRD_CONSUME = code(3, 14);

    public SqPayError(int i, String str) {
        super(i, str);
    }

    public SqPayError(int i, String str, int i2) {
        super(i, str, i2, str);
    }

    public SqPayError(int i, String str, int i2, String str2) {
        super(i, str, i2, str2);
    }

    public SqPayError(SqError sqError) {
        super(sqError);
    }

    public static SqPayError httpRespError(int i, int i2, String str) {
        return new SqPayError(SqError.serverError(i, i2, str));
    }

    public static SqPayError httpReqError(int i, VolleyError volleyError) {
        return new SqPayError(SqError.httpError(i, volleyError));
    }

    private static int code(int i, int i2) {
        return SqErrorCode.code(3, (i * 100) + i2);
    }
}
