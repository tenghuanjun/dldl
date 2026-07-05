package com.sy37sdk.account.alifast;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FastLoginConstants {

    public class BundleKey {
        public static final String CODE = "code";
        public static final String MESSAGE = "msg";

        public BundleKey() {
        }
    }

    public class Code {
        public static final String CANCEL = "5";
        public static final String FAILURE_CHECK_TOKEN_ERROR = "9";
        public static final String FAILURE_CLICK_ACCOUNT_LOGIN = "16";
        public static final String FAILURE_CLICK_BACK = "15";
        public static final String FAILURE_CLICK_OTHER_WAY = "11";
        public static final String FAILURE_CONFIG_ERROR = "3";
        public static final String FAILURE_NOT_CHECK_TOKEN = "8";
        public static final String FAILURE_NOT_CONFIG = "2";
        public static final String FAILURE_NOT_SUPPORT = "12";
        public static final String FAILURE_PARSE_ALI = "4";
        public static final String FAILURE_PARSE_ALI_FAILURE = "6";
        public static final String FAILURE_PARSE_CHECK_TOKEN = "7";
        public static final String FAILURE_PARSE_CONFIG = "1";
        public static final String FAILURE_REQUEST_CONFIG = "10";
        public static final String FAILURE_VERIFY_FAIL = "13";
        public static final String FAILURE_VERIFY_FAIL_UNKNOWN = "14";
        public static final String SUCCESS = "0";

        public Code() {
        }
    }

    public class MESSAGE {
        public static final String CANCEL = "取消阿里云闪验登录";
        public static final String FAILURE_CHECK_TOKEN_ERROR = "请求校验闪验token出错";
        public static final String FAILURE_CLICK_ACCOUNT_LOGIN = "选择账号密码登录";
        public static final String FAILURE_CLICK_BACK = "选择返回其他方式登录";
        public static final String FAILURE_CLICK_OTHER_WAY = "选择其他方式登录";
        public static final String FAILURE_CONFIG_ERROR = "请求闪验配置出错";
        public static final String FAILURE_NOT_CHECK_TOKEN = "请求校验闪验token失败";
        public static final String FAILURE_NOT_CONFIG = "闪验未配置";
        public static final String FAILURE_NOT_SUPPORT = "不支持的闪验版本";
        public static final String FAILURE_PARSE_ALI = "解析阿里云闪验失败";
        public static final String FAILURE_PARSE_ALI_FAILURE = "解析闪验登录失败败时出错";
        public static final String FAILURE_PARSE_CHECK_TOKEN = "请求校验闪验token解析出错";
        public static final String FAILURE_PARSE_CONFIG = "解析闪验配置失败";
        public static final String FAILURE_REQUEST_CONFIG = "请求闪验配置失败";
        public static final String FAILURE_VERIFY_FAIL = "校验token失败";
        public static final String FAILURE_VERIFY_FAIL_UNKNOWN = "未知错误";
        public static final String SUCCESS = "获取闪验成功";

        public MESSAGE() {
        }
    }
}
