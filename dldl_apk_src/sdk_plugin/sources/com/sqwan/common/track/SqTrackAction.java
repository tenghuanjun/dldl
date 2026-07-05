package com.sqwan.common.track;

import android.text.TextUtils;
import com.huya.component.login.report.ReportKey;
import com.sqwan.common.track.SqTrackBtn;
import com.sy37sdk.share.UrlConstant;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public enum SqTrackAction {
    SDK_CUSTOM("", ""),
    SDK_INIT("sdk_init", "调用初始化"),
    SDK_INIT_SUCC("sdk_init_succ", "sdk初始化完成"),
    LOACL_ACTIVE("sdk_local_active", "sdk本地激活"),
    LOGIN_SUCC("login_succ", "登录成功"),
    REG_SUCC("register_succ", "注册成功"),
    ROLE_ADD("role_add", "角色创建"),
    ROLE_LOGIN("role_login", "角色登录"),
    ROLE_LEVEL("role_level", "角色升级"),
    SDK_BTN_CLICK("sdk_btn_click", "点击按钮"),
    SDK_VIEW_SHOW("sdk_view_show", "页面曝光"),
    ORDER_INIT("order_init", "发起支付"),
    PAY_CLICK("pay_click", "点击确认支付"),
    PAY_SUCC("pay_succ", "支付成功"),
    INVOKE_ALI_FAST_LOGIN("invoke_ali_fast_login", "调起闪验登录"),
    ALI_FAST_LOGIN_SUCC("ali_fast_login_succ", "闪验登录成功"),
    Ali_FAST_LOGIN_FAIL("ali_fast_login_fail", "闪验登录失败"),
    SPLASH_SHOW("launch_image", "显示闪屏"),
    CALL_LOGIN("invoke_login", "调用登录服务"),
    OPEN_GAME("open_game", "打开游戏"),
    SHOW_AUTO_LOGIN_VIEW("auto_login", "显示自动登录窗"),
    CLICK_CHANGE_ACCOUNT_IN_AUTO_LOGIN_VIEW("al_change_account", "在自动登录框中点击切换账号"),
    AUTO_LOGIN_SUCCESS("auto_login_success", "自动登录成功"),
    CLICK_HELP_MENU("help", "点击登录框【帮助】"),
    CLICK_SERVER_LIST("new_server", "点击登录框【开服表】"),
    CLICK_MARQUEE("roll", "点击登录框跑马灯"),
    CLICK_FORGET_PASSWORD("ul_forget_pw", "点击【忘记密码】"),
    CLICK_EXIST_ACCOUNT("to_user_login", "点击【已有账号】"),
    CLICK_REGISTER_ACCOUNT("to_account_register", "点击【账号注册】"),
    CLICK_REGISTER_BY_PHONE("ar_to_phone_register", "点击【手机注册】"),
    ENTER_REGISTER_ONE_CLICK_VIEW("sms_register_page", "进入【一键注册】页"),
    CLICK_ENTER_NOW("sr_register", "点击【立即进入】"),
    ENTER_REGISTER_ACCOUNT_VIEW("account_register_page", "进入【账号注册】页"),
    FOCUS_REGISTER_INPUT_ACCOUNT("ar_input_account", "focus账号输入框【注册页】"),
    FOCUS_REGISTER_INPUT_PASSWORD("ar_input_pw", "focus密码输入框【注册页】"),
    CLICK_REGISTER_NOW("ar_register", "点击【立即注册】"),
    ENTER_REGISTER_BY_PHONE("phone_register_page", "进入【手机注册】页"),
    FOCUS_REGISTER_PHONE_INPUT_ACCOUNT("pr_input_pn", "focus手机号输入框【手机注册】"),
    CLICK_GET_VERIFY_CODE_BUTTON("pr_get_vc", "点击【获取验证码】按钮"),
    FOCUS_REGISTER_PHONE_INPUT_CODE("pr_input_vc", "focus验证码输入框【手机注册】"),
    CLICK_REGISTER_NOW_PHONE("pr_register", "点击【立即注册】【手机注册】"),
    ENTER_LOGIN_VIEW("user_login_page", "进入【用户登录】页"),
    FOCUS_LOGIN_INPUT_ACCOUNT("ul_input_account", "focus账号输入框【登录页】"),
    FOCUS_LOGIN_INPUT_PASSWORD("ul_input_pw", "focus密码输入框【登录页】"),
    CLICK_REGISTER_ONE_CLICK_VIEW("ul_to_register", "点击【一键注册】（仅安卓）"),
    CLICK_LOGIN_NOW("ul_login", "点击【立即登录】"),
    CLICK_SELECT_ACCOUNT_BUTTON("ul_dropdown", "点击账号下拉按钮"),
    CLICK_LOGIN_QQ("", "点击QQ登录（仅安卓）"),
    CLICK_LOGIN_WEI_CHAT("", "点击微信登录（仅安卓）"),
    SUCCESS_OF_LOGIN_QQ("", "QQ登录成功（仅安卓）"),
    SUCCESS_OF_LOGIN_WEI_CHAT("", "微信登录成功（仅安卓）"),
    SUCCESS_OF_SQ_LOGIN("account_login_success", "账号登录成功"),
    REQUEST_REGISTER_API("request_register", "请求注册接口统计"),
    SUCCESS_OF_REGISTER_ACCOUNT("register_success", "账号注册成功统计"),
    ENTER_PAY_VIEW("p", "进入支付页"),
    ENTER_GAME_COUNT_OF_DAILY("", "日启动次数（无需实时）"),
    LOGIN_SUCCESS("login_success", "完成SDK登录时（回调给CP成功时）"),
    LOGIN_FAIL(ReportKey.LOGIN_FAIL, "完成SDK登录时（回调给CP失败时）"),
    SUBMIT_CREATE_ROLE("create_role", "创建角色数据上报时刻"),
    SUBMIT_ENTER_GAME("entergame", "进服务器数据上报时刻"),
    SDK_EXIT("sdk_exit", "登出游戏成功时（显示退出框，点击确定之后）"),
    FINISH_PLOGIN("finish_plogin", "验证token接口调用时刻"),
    APP_CRASH("bugless_crash", "闪退事件上报"),
    PAY_NET_ERROR("pay_net_error", "支付页面加载超时"),
    EXTERNAL_WEB_LOAD("external_web_load", "外部调用web页面"),
    PAY_CALLBACK_SUCC("pay_callback_succ", "支付页回调成功"),
    PAY_CALLBACK_FAIL("pay_callback_fail", "支付页回调失败"),
    SHARE(UrlConstant.KEY_SHARE, "调起分享"),
    SHARE_CLICK_WECHAT("share_click_wechat", "点击【微信】分享"),
    SHARE_CLICK_MOMENTS("share_click_moments", "点击【朋友圈】分享"),
    SHARE_CLICK_QQ("share_click_qq", "点击【qq】分享"),
    SHARE_CLICK_MORE("share_click_more", "点击【更多】分享"),
    SHARE_SUCCESS_RESOURCE("share_success_resource", "请求分享物料成功"),
    SHARE_FAIL_OVERTIME("share_fail_overtime", "请求分享物料超时"),
    SHARE_FAIL_NET_ERROR("share_net_error", "请求分享物料网络错误"),
    SHARE_FAIL_RESOURCE("share_fail_resource", "后台未配置分享物料"),
    SHARE_FAIL_WAY("share_fail_way", "未配置分享平台"),
    SHARE_LOAD_IMG("share_load_img", "加载分享的图片"),
    SHARE_LOAD_IMG_SUCCESS("share_load_img_success", "加载分享图片成功"),
    CLICK_START_SHARE("click_start_share", "开始分享"),
    SHARE_CLICK_SOCIAL_WAY("share_click_social_way", "第三方分享"),
    SHARE_CLICK_SYSTEM_WAY("share_click_system_way", SqTrackBtn.SqTrackBtnExt.share_system),
    SHARE_CLICK_SOCIAL_WAY_WECHAT("share_click_social_way_wechat", "第三方分享【微信】"),
    SHARE_CLICK_SOCIAL_WAY_MOMENTS("share_click_social_way_moments", "第三方分享【朋友圈】"),
    SHARE_CLICK_SOCIAL_WAY_QQ("share_click_social_way_qq", "第三方分享【QQ】"),
    SHARE_CLICK_ORIGINAL_WAY("share_click_original_way", "用户手动分享"),
    SHARE_CLICK_ORIGINAL_WAY_WECHAT("share_click_original_way_wechat", "用户手动分享【微信】"),
    SHARE_CLICK_ORIGINAL_WAY_MOMENTS("share_click_original_way_moments", "用户手动分享【朋友圈】"),
    SHARE_CLICK_ORIGINAL_WAY_QQ("share_click_original_way_qq", "用户手动分享【QQ】"),
    SHARE_SAVE_IMG("share_save_img", "提示用户手动打开分享时保存图片"),
    SHARE_SAVE_IMG_FAIL("share_save_img_fail", "提示用户手动打开分享时保存图片失败"),
    CLICK_SHARE_OPEN_PLATFORM("click_share_open_platform", "点击弹窗，打开分享平台"),
    CLICK_SHARE_OPENWECHAT("click_share_openwechat", "点击弹窗【分享给微信好友】"),
    CLICK_SHARE_OPENQQ("click_share_openqq", "点击弹窗【分享给qq好友】"),
    SHARE_PREVIEW_SHOW("share_preview_show", "展示分享预览界面"),
    SHARE_ORIGINAL_PREVIEW_SHOW("share_original_preview_show", "展示手动分享界面"),
    SHARE_FAIL_NOT_INSTALL("share_fail_not_install", "分享失败，未安装第三方平台"),
    SHARE_VALID("share_valid", "有效进入到分享流程"),
    SHARE_FAIL_NOT_INSTALL_WECHAT("share_fail_not_install_wechat", "分享失败，未安装微信"),
    SHARE_FAIL_NOT_INSTALL_QQ("share_fail_not_install_qq", "分享失败，未安装qq"),
    SR_REGISTE_CONFIRMR_V2("sr_registe_confirmr_v2", "一键注册确认按钮"),
    SMS_SUCCESS_V2("android_sms_success_v2", "短信一键注册成功（客户端-新）"),
    SMS_SUCCESS_V1("android_sms_success_v1", "短信一键注册成功（客户端-旧）"),
    SR_REGISTER_V2("sr_register_v2", "点击【立即进入】（一键注册页，仅安卓）（新）"),
    SMS_REGISTER_PAGE_V2("sms_register_page_v2", "进入【一键注册】页（仅安卓）（新）"),
    FLOAT_BALL_SHOW("ball_show", "V2显示悬浮球"),
    FLOAT_BALL_CLICK("ball_access", "V2点击悬浮球"),
    FLOAT_BALL_DISMISS("ball_dismiss", "V2悬浮球消失(登出动作等)"),
    FLOAT_BALL_HIDE("ball_hide", "V2悬浮球拖到最下方隐藏"),
    FLOAT_MENU_SHOW("ball_entry_show", "V2悬浮球菜单显示"),
    FLOAT_MENU_CLICK("ball_entry_access", "V2悬浮球菜单点击"),
    PAY_CALLBACK_FAIL_NATIVE("pay_callback_fail_native", "支付失败"),
    PAY_CALLBACK_SUCC_NATIVE("pay_callback_succ_native", "支付成功"),
    WALLET_P_CONFIRM_NATIVE("wallet_p_confirm_native", "确认使用钱包支付"),
    WX_P_CONFIRM_NAITVE("wx_p_confirm_naitve", "确认使用微信支付"),
    ZFB_P_CONFIRM_NATIVE("zfb_p_confirm_native", "确认使用支付宝"),
    HUABEI_P_CONFIRM_NATIVE("huabei_p_confirm_native", "确认使用花呗"),
    WALLET_SUCC_NATIVE("wallet_succ_native", "钱包支付成功"),
    WX_SUCC_NATIVE("wx_succ_native", "微信支付成功"),
    ZFB_SUCC_NATIVE("zfb_succ_native", "支付宝支付成功"),
    HUABEI_SUCC_NATIVE("huabei_succ_native", "花呗支付成功"),
    MANUAL_P_NATIVE("manual_p_native", "点击人工支付"),
    WALLET_P_NATIVE("wallet_p_native", "点击【钱包支付】"),
    WX_P_NATIVE("wx_p_native", "点击【微信支付】"),
    ZFB_P_NATIVE("zfb_p_native", "点击【支付宝】"),
    HUABEI_P_NATIVE("huabei_p_native", "点击【花呗】"),
    CHOOSE_PMETHOD_PAGE_NATIVE("choose_pmethod_page_native", "进入支付页"),
    USER_REGISTER_PAGE("user_register_page", "进入【用户注册】页"),
    PHONE_REGISTER_PAGE("phone_register_page", "进入【手机注册】页"),
    USER_LOGIN_PAGE("user_login_page", "进入【用户登录】页"),
    UR_PHONE_REGISTER("ur_phone_register", "点击【手机注册】（注册页）"),
    UR_QQ_REGISTER("ur_qq_register", "点击【QQ登录】（注册页）"),
    UR_WX_REGISTER("ur_wx_register", "点击【微信登录】（注册页）"),
    UR_USER_LOGIN("ur_user_login", "点击【已有账户登录】（注册页）"),
    PR_INPUT_PN("pr_input_pn", "focus手机号输入框（手机注册页）"),
    PR_GET_VC("pr_get_vc", "点击【获取验证码】按钮（手机注册页）"),
    PR_INPUT_VC("pr_input_vc", "focus验证码输入框（手机注册页）"),
    PR_REGISTER("pr_register", "点击【立即注册】（手机注册页）"),
    PR_ELSE_REGISTER("pr_else_register", "点击【其他注册方式】(手机注册页）"),
    TO_USER_LOGIN("to_user_login", "点击【已有账号登录】（手机注册页）"),
    PR_READ_PROTOCOLS("pr_read_protocols", "查看隐私协议"),
    UL_INPUT_PN("ul_input_pn", "focus手机号码输入框（用户登录页）"),
    UL_INPUT_PW("ul_input_pw", "focus密码输入框（用户登录页）"),
    UL_LOGIN("ul_login", "点击【立即登录】（用户登录页）"),
    UL_DROPDOWN("ul_dropdown", "点击账号下拉按钮"),
    UL_VIEW_PW("ul_view_pw", "点击密码查看按钮"),
    UL_HIDE_PW("ul_hide_pw", "点击密码隐藏按钮"),
    UL_FORGET_PW("ul_forget_pw", "点击【忘记密码】"),
    UL_PHONE_REGISTER("ul_phone_register", "点击【手机注册】(登录页）"),
    UL_QQ_REGISTER("ul_qq_register", "点击【QQ登录】(登录页）"),
    UL_WX_REGISTER("ul_wx_register", "点击【微信登录】(登录页）"),
    Q_LOGIN_SUCCESS("q_login_success", "QQ登录成功"),
    WX_LOGIN_SUCCESS("wx_login_success", "微信登录成功"),
    RED_PACKAGE_FROM_FLOAT("red_package_from_float", "悬浮球点击进入"),
    RED_PACKAGE_FROM_DIALOG("red_package_from_dialog", "首次红包弹窗点击进入"),
    RED_PACKAGE_ENTER("red_package_enter", "点击红包活动"),
    WEB_JUMP_PIC_SELECT("web_jump_pic_select", "web页面跳转图片选择功能"),
    FM_TURN_ON("fm_turnon", "开始收听"),
    FM_TURN_OFF("fm_turnoff", "关闭收听"),
    SPLASH("open_img", "闪屏结束"),
    GAME_INIT("game_init", "游戏初始化开始"),
    GAME_INIT_SUCCESS("game_init_success", "游戏初始化完成"),
    PAY_SUCCESS("pay_success", "支付成功"),
    PAY_FAIL("pay_fail", "支付失败");

    private String event;
    private String name;

    SqTrackAction(String str, String str2) {
        this.event = str;
        this.name = str2;
    }

    public String getEvent() {
        return this.event;
    }

    public String getName() {
        return this.name;
    }

    public void construct(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.event = str;
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        this.name = str2;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "SqTrackAction{event='" + this.event + "', name='" + this.name + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
