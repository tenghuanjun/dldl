package com.sdk.sq.net;

import com.sqnetwork.voly.BadUrlError;
import com.sqnetwork.voly.HostError;
import com.sqnetwork.voly.NetworkError;
import com.sqnetwork.voly.NoConnectionError;
import com.sqnetwork.voly.ParseError;
import com.sqnetwork.voly.SSLError;
import com.sqnetwork.voly.ServerError;
import com.sqnetwork.voly.TimeoutError;
import com.sqnetwork.voly.VolleyError;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RequestErrorCode {
    public static final int ERROR_37_PARSE = 6040;
    public static final int ERROR_37_VERIFY = 6041;
    public static final int ERROR_CONNECTION = 6018;
    public static final int ERROR_HOST = 6002;
    public static final int ERROR_NETWORK = 6019;
    public static final int ERROR_PARSE = 6030;
    public static final int ERROR_SSL = 6011;
    public static final int ERROR_TIMEOUT = 6003;
    public static final int ERROR_UNKNOWN = 6100;
    public static final int ERROR_URL = 6001;

    public static int of(Throwable exception) {
        if (exception instanceof VolleyError) {
            VolleyError volleyError = (VolleyError) exception;
            if ((volleyError instanceof ServerError) && volleyError.networkResponse != null) {
                return volleyError.networkResponse.statusCode;
            }
            if (volleyError instanceof HostError) {
                return 6002;
            }
            if (volleyError instanceof BadUrlError) {
                return 6001;
            }
            return volleyError instanceof SSLError ? ERROR_SSL : volleyError instanceof TimeoutError ? ERROR_TIMEOUT : volleyError instanceof NoConnectionError ? ERROR_CONNECTION : volleyError instanceof NetworkError ? ERROR_NETWORK : volleyError instanceof SqParseError ? ERROR_37_PARSE : volleyError instanceof SqVerifyError ? ERROR_37_VERIFY : volleyError instanceof ParseError ? ERROR_PARSE : of(volleyError.getCause());
        }
        if (exception instanceof MalformedURLException) {
            return 6001;
        }
        if ((exception instanceof UnknownHostException) || (exception instanceof NoRouteToHostException)) {
            return 6002;
        }
        if (exception instanceof SocketTimeoutException) {
            return ERROR_TIMEOUT;
        }
        if (exception instanceof ConnectException) {
            return ERROR_CONNECTION;
        }
        if (exception instanceof SSLException) {
            return ERROR_SSL;
        }
        if (exception instanceof SocketException) {
            return ERROR_NETWORK;
        }
        return 6100;
    }
}
