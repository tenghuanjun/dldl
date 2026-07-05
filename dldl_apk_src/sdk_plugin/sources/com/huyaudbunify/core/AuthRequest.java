package com.huyaudbunify.core;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AuthRequest {

    public interface OpCmd {
        public static final int OP_ANONYMOUS_LOGIN = 19;
        public static final int OP_CANCEL = 15;
        public static final int OP_CHECK_MOD_PWD = 16;
        public static final int OP_CHECK_REGISTER = 17;
        public static final int OP_CHECK_SMS_UP = 11;
        public static final int OP_CHECK_USER_EXIST = 18;
        public static final int OP_CREDIT_LOGIN = 2;
        public static final int OP_INVALID_REQ = -1;
        public static final int OP_LOG_REPORT = 10;
        public static final int OP_OPEN_APP_CHECK = 100;
        public static final int OP_OPEN_CREDIT_LOGIN = 102;
        public static final int OP_OPEN_PWD_LOGIN = 101;
        public static final int OP_PASSWORD_LOGIN = 1;
        public static final int OP_QRCODE_CANCEL = 22;
        public static final int OP_QRCODE_CHECK = 20;
        public static final int OP_QRCODE_CONFIRM = 21;
        public static final int OP_QRCODE_LOGIN = 14;
        public static final int OP_QUERY = 8;
        public static final int OP_QUICK_MOD_PWD = 9;
        public static final int OP_REFRESH_PIC = 7;
        public static final int OP_SEND_SMS = 4;
        public static final int OP_SMS_MOD_PWD = 6;
        public static final int OP_SMS_REG_LOGIN = 5;
        public static final int OP_THIRD_LOGIN = 13;
        public static final int OP_TICKET_LOGIN = 3;
        public static final int OP_VERIFY_SMSCODE = 12;
    }

    public interface STRATEGY {
        public static final int HWTOKEN = 4;
        public static final int JUMP_URL = 64;
        public static final int MOBTOKEN = 2;
        public static final int NONE = 0;
        public static final int PICCODE = 1;
        public static final int SLIDE = 16;
        public static final int SMSCODE = 8;
        public static final int SMS_UP = 32;
    }
}
