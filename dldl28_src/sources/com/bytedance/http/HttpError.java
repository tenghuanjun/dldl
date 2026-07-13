package com.bytedance.http;

/* JADX INFO: loaded from: classes2.dex */
public interface HttpError {
    public static final int HTTP_ERROR_BIND = -10;
    public static final int HTTP_ERROR_CANCELED = -2;
    public static final int HTTP_ERROR_CONNECT_TIMEOUT = -8;
    public static final int HTTP_ERROR_GENERAL = -1;
    public static final int HTTP_ERROR_GENERAL_SECURITY = -11;
    public static final int HTTP_ERROR_IO = -3;
    public static final int HTTP_ERROR_MALFORMED_URL = -4;
    public static final int HTTP_ERROR_NO_ROUTE = -9;
    public static final int HTTP_ERROR_PERMISSION_SECURITY = -12;
    public static final int HTTP_ERROR_PROTOCOL = -5;
    public static final int HTTP_ERROR_RUNTIME_EXCEPTION = -16;
    public static final int HTTP_ERROR_SOCKET_TIMEOUT = -7;
    public static final int HTTP_ERROR_SSL_HANDSHAKE = -13;
    public static final int HTTP_ERROR_SSL_KEY_EXCEPTION = -15;
    public static final int HTTP_ERROR_SSL_PEER_UNVERIFIED = -14;
    public static final int HTTP_ERROR_UNKNOWN_HOST = -6;
    public static final int HTTP_ERROR_WS_HANDSHAKE = -19;
    public static final int HTTP_ERROR_WS_PAYLOAD_TOO_LARGE = -17;
    public static final int HTTP_ERROR_WS_QUEUE_FULL = -18;
}
