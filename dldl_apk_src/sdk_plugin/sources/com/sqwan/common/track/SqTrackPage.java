package com.sqwan.common.track;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface SqTrackPage {

    public interface SqTrackViewId {
        public static final String account_login = "view05";
        public static final String account_register = "view06";
        public static final String ali_fast = "view01";
        public static final String face = "view07";
        public static final String history_account = "view11";
        public static final String modifyUserInfo = "view08";
        public static final String permission_scene = "view15";
        public static final String phone_code = "view03";
        public static final String phone_input = "view02";
        public static final String phone_pwd = "view04";
        public static final String share_confirm = "view13";
        public static final String share_preview = "view12";
        public static final String update = "view09";
        public static final String updateForce = "view10";
        public static final String wechat_login = "view14";
    }

    public interface SqTrackViewName {
        public static final String ACCOUNT_LOGIN = "账号密码登录页曝光";
        public static final String ACCOUNT_REGISTER = "账号密码注册页曝光";
        public static final String HISTORY_ACCOUNT = "历史账号登录页面";
        public static final String ali_fast = "闪验页面曝光";
        public static final String face = "人脸识别弹窗";
        public static final String modifyUserInfo = "修改个人信息曝光";
        public static final String permission_scene = "权限场景说明弹窗";
        public static final String phone_code = "手机号填写验证码页面曝光";
        public static final String phone_input = "手机号获取验证码页面曝光";
        public static final String phone_pwd = "手机号填写密码页面曝光";
        public static final String share_confirm = "分享确认界面";
        public static final String share_preview = "分享预览界面";
        public static final String update = "普更弹窗";
        public static final String updateForce = "强更弹窗";
        public static final String wechat_login = "微信登录页面曝光";
    }
}
