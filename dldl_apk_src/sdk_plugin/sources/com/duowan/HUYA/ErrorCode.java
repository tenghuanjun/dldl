package com.duowan.HUYA;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ErrorCode {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int _EC_AUDIT_TEXT_FAIL = 924;
    public static final int _EC_NEED_VERIFY_CODE = 925;
    public static final int _EC_NO_ENOUGH_MONEY = 911;
    public static final int _EC_NO_SYNCHRONOUS = 928;
    public static final int _EC_OK = 0;
    private String __T;
    private int __value;
    private static ErrorCode[] __values = new ErrorCode[29];
    public static final int _EC_UNKNOWN = -901;
    public static final ErrorCode EC_UNKNOWN = new ErrorCode(0, _EC_UNKNOWN, "EC_UNKNOWN");
    public static final int _EC_SYSTEM_BUSY = -902;
    public static final ErrorCode EC_SYSTEM_BUSY = new ErrorCode(1, _EC_SYSTEM_BUSY, "EC_SYSTEM_BUSY");
    public static final ErrorCode EC_OK = new ErrorCode(2, 0, "EC_OK");
    public static final int _EC_UNAVAILABLE = 903;
    public static final ErrorCode EC_UNAVAILABLE = new ErrorCode(3, _EC_UNAVAILABLE, "EC_UNAVAILABLE");
    public static final int _EC_INVALID_ARGUMENT = 904;
    public static final ErrorCode EC_INVALID_ARGUMENT = new ErrorCode(4, _EC_INVALID_ARGUMENT, "EC_INVALID_ARGUMENT");
    public static final int _EC_NO_PRIVILEGE = 905;
    public static final ErrorCode EC_NO_PRIVILEGE = new ErrorCode(5, _EC_NO_PRIVILEGE, "EC_NO_PRIVILEGE");
    public static final int _EC_OVER_LIMIT = 906;
    public static final ErrorCode EC_OVER_LIMIT = new ErrorCode(6, _EC_OVER_LIMIT, "EC_OVER_LIMIT");
    public static final int _EC_OVER_COUNT_LIMIT = 907;
    public static final ErrorCode EC_OVER_COUNT_LIMIT = new ErrorCode(7, _EC_OVER_COUNT_LIMIT, "EC_OVER_COUNT_LIMIT");
    public static final int _EC_OVER_TIME_LIMIT = 908;
    public static final ErrorCode EC_OVER_TIME_LIMIT = new ErrorCode(8, _EC_OVER_TIME_LIMIT, "EC_OVER_TIME_LIMIT");
    public static final int _EC_OVER_FREQUENCY_LIMIT = 909;
    public static final ErrorCode EC_OVER_FREQUENCY_LIMIT = new ErrorCode(9, _EC_OVER_FREQUENCY_LIMIT, "EC_OVER_FREQUENCY_LIMIT");
    public static final int _EC_ALWAYS_EXISTS = 910;
    public static final ErrorCode EC_ALWAYS_EXISTS = new ErrorCode(10, _EC_ALWAYS_EXISTS, "EC_ALWAYS_EXISTS");
    public static final ErrorCode EC_NO_ENOUGH_MONEY = new ErrorCode(11, 911, "EC_NO_ENOUGH_MONEY");
    public static final int _EC_NO_ENOUGH_ITEM = 912;
    public static final ErrorCode EC_NO_ENOUGH_ITEM = new ErrorCode(12, _EC_NO_ENOUGH_ITEM, "EC_NO_ENOUGH_ITEM");
    public static final int _EC_TARGET_NOT_FOUND = 913;
    public static final ErrorCode EC_TARGET_NOT_FOUND = new ErrorCode(13, _EC_TARGET_NOT_FOUND, "EC_TARGET_NOT_FOUND");
    public static final int _EC_GOAL_NOT_COMPLATE = 914;
    public static final ErrorCode EC_GOAL_NOT_COMPLATE = new ErrorCode(14, _EC_GOAL_NOT_COMPLATE, "EC_GOAL_NOT_COMPLATE");
    public static final int _EC_MYSQL = 915;
    public static final ErrorCode EC_MYSQL = new ErrorCode(15, _EC_MYSQL, "EC_MYSQL");
    public static final int _EC_REDIS = 916;
    public static final ErrorCode EC_REDIS = new ErrorCode(16, _EC_REDIS, "EC_REDIS");
    public static final int _EC_JSON_DECODE = 917;
    public static final ErrorCode EC_JSON_DECODE = new ErrorCode(17, _EC_JSON_DECODE, "EC_JSON_DECODE");
    public static final int _EC_NO_TASK = 918;
    public static final ErrorCode EC_NO_TASK = new ErrorCode(18, _EC_NO_TASK, "EC_NO_TASK");
    public static final int _EC_HTTP = 919;
    public static final ErrorCode EC_HTTP = new ErrorCode(19, _EC_HTTP, "EC_HTTP");
    public static final int _EC_THRIFT = 920;
    public static final ErrorCode EC_THRIFT = new ErrorCode(20, _EC_THRIFT, "EC_THRIFT");
    public static final int _EC_ALREADY_GOT = 921;
    public static final ErrorCode EC_ALREADY_GOT = new ErrorCode(21, _EC_ALREADY_GOT, "EC_ALREADY_GOT");
    public static final int _EC_HUYA_LIMIT = 922;
    public static final ErrorCode EC_HUYA_LIMIT = new ErrorCode(22, _EC_HUYA_LIMIT, "EC_HUYA_LIMIT");
    public static final int _EC_ANTI_BRUSH = 923;
    public static final ErrorCode EC_ANTI_BRUSH = new ErrorCode(23, _EC_ANTI_BRUSH, "EC_ANTI_BRUSH");
    public static final ErrorCode EC_AUDIT_TEXT_FAIL = new ErrorCode(24, 924, "EC_AUDIT_TEXT_FAIL");
    public static final ErrorCode EC_NEED_VERIFY_CODE = new ErrorCode(25, 925, "EC_NEED_VERIFY_CODE");
    public static final int _EC_TARGET_DELETED = 926;
    public static final ErrorCode EC_TARGET_DELETED = new ErrorCode(26, _EC_TARGET_DELETED, "EC_TARGET_DELETED");
    public static final int _EC_NEED_BIND_PHONE = 927;
    public static final ErrorCode EC_NEED_BIND_PHONE = new ErrorCode(27, _EC_NEED_BIND_PHONE, "EC_NEED_BIND_PHONE");
    public static final ErrorCode EC_NO_SYNCHRONOUS = new ErrorCode(28, 928, "EC_NO_SYNCHRONOUS");

    public static ErrorCode convert(int i) {
        int i2 = 0;
        while (true) {
            ErrorCode[] errorCodeArr = __values;
            if (i2 >= errorCodeArr.length) {
                return null;
            }
            if (errorCodeArr[i2].value() == i) {
                return __values[i2];
            }
            i2++;
        }
    }

    public static ErrorCode convert(String str) {
        int i = 0;
        while (true) {
            ErrorCode[] errorCodeArr = __values;
            if (i >= errorCodeArr.length) {
                return null;
            }
            if (errorCodeArr[i].toString().equals(str)) {
                return __values[i];
            }
            i++;
        }
    }

    public int value() {
        return this.__value;
    }

    public String toString() {
        return this.__T;
    }

    private ErrorCode(int i, int i2, String str) {
        this.__T = new String();
        this.__T = str;
        this.__value = i2;
        __values[i] = this;
    }
}
