package com.huya.live.ns.helper;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.duowan.HUYA.ErrorCode;
import com.huya.berry.sdkplayer.common.pubtext.XXBarrageParser;
import com.huya.hysignal.core.HySignalException;
import com.huya.mtp.data.exception.CacheNotFoundError;
import com.huya.mtp.data.exception.DataNetworkException;
import com.huya.mtp.data.exception.NoParserException;
import com.huya.mtp.data.exception.NoStrategyException;
import com.huya.mtp.data.exception.NoValidatorException;
import com.huya.mtp.data.exception.NullResponseException;
import com.huya.mtp.data.exception.ParseException;
import com.huya.mtp.data.exception.PermissionException;
import com.huya.mtp.data.exception.TransportException;
import com.huya.mtp.data.exception.ValidationException;
import com.huya.mtp.hyns.wup.WupError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LiveProtocolHelper {
    public static int parseRetCode(Throwable th) {
        if (th instanceof HySignalException) {
            return ((HySignalException) th).getErrorCode();
        }
        if (th instanceof TimeoutError) {
            return 600;
        }
        if (th instanceof ParseError) {
            return 601;
        }
        if (th instanceof AuthFailureError) {
            return 602;
        }
        if (th instanceof NoConnectionError) {
            return 603;
        }
        if (th instanceof NetworkError) {
            return 604;
        }
        if (th instanceof ServerError) {
            return 605;
        }
        if (th instanceof VolleyError) {
            return 606;
        }
        if (th instanceof DataNetworkException) {
            return 700;
        }
        if (th instanceof TransportException) {
            return 701;
        }
        if (th instanceof WupError) {
            return ((WupError) th).mCode;
        }
        if (th instanceof NoParserException) {
            return 801;
        }
        if (th instanceof ParseException) {
            return 802;
        }
        if (th instanceof PermissionException) {
            return XXBarrageParser.SUPER_FAST_DURATION;
        }
        if (th instanceof NullResponseException) {
            return 901;
        }
        if (th instanceof NoValidatorException) {
            return 902;
        }
        if (th instanceof ValidationException) {
            return ErrorCode._EC_UNAVAILABLE;
        }
        if (th instanceof CacheNotFoundError) {
            return ErrorCode._EC_INVALID_ARGUMENT;
        }
        if (th instanceof NoStrategyException) {
            return ErrorCode._EC_NO_PRIVILEGE;
        }
        return 999;
    }
}
