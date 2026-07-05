package com.sqwan.common.track;

import android.text.TextUtils;
import com.huya.component.login.report.ReportKey;
import com.sqwan.liveshow.huya.SqR;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public enum SqTrackAction2 {
    sdk_expand("", ""),
    open_img("pay_fail", "闪屏结束"),
    sdk_init("sdk_init", "SDK初始化开始"),
    sdk_init_succ("sdk_init_succ", "SDK初始化完成"),
    sdk_init_fail("sdk_init_fail", "SDK初始化失败"),
    invoke_ali_fast_login("invoke_ali_fast_login", "闪验调用"),
    ali_fast_login_succ("ali_fast_login_succ", "闪验调用成功"),
    ali_fast_login_fail("ali_fast_login_fail", "闪验调用失败"),
    login_invoke("login_invoke", "登录调用"),
    login_succ("login_succ", "登录成功"),
    login_fail(ReportKey.LOGIN_FAIL, "登录失败"),
    login_wechat_succ("login_wechat_succ", "微信登录成功"),
    login_wechat_fail("login_wechat_fail", "微信登录成功失败"),
    register_succ("register_succ", "注册成功"),
    role_add("role_add", "角色创建成功"),
    role_login("role_login", "角色进服成功"),
    role_level("role_level", "角色升级成功"),
    sdk_btn_click("sdk_btn_click", "点击按钮"),
    sdk_view_show("sdk_view_show", "页面曝光"),
    ball_show("ball_show", "悬浮球曝光"),
    ball_access("ball_access", "点击悬浮球"),
    ball_entry_show("ball_entry_show", "点击悬浮球，打开二级列表"),
    ball_btn_show("ball_btn_show", "二级入口曝光"),
    ball_entry_access("ball_entry_access", "点击二级入口"),
    FLOAT_BALL_HIDE("ball_hide", "V2悬浮球拖到最下方隐藏"),
    certification("certification", "实名认证"),
    certification_succ("certification_succ", "实名认证成功"),
    certification_fail("certification_fail", "实名认证失败"),
    face_recognition("face_recognition", "人脸识别调用"),
    face_recognition_succ("face_recognition_succ", "人脸识别成功"),
    face_recognition_fail("face_recognition_fail", "人脸识别失败"),
    sdk_push_click("sdk_push_click", "弹窗点击"),
    sdk_push_show("sdk_push_show", "弹窗曝光"),
    backstage_succ("backstage_succ", "切后台（未杀进程）"),
    resume_succ("resume_succ", "切后台（未杀进程）后回到游戏"),
    LOGOUT_SUCC("logout_succ", "账号登出（手动退出）"),
    pay_invoke("pay_invoke", "支付调起"),
    pay_init("pay_init", "进入支付页"),
    pay_click("pay_click", "点击确认支付"),
    pay_close_click("pay_close_click", "点击关闭支付"),
    pay_succ("pay_succ", "支付成功"),
    pay_fail("pay_fail", "支付失败"),
    pay_cancel("pay_cancel", "取消支付"),
    role_login_throwable("role_login_throwable", "进服异常"),
    imei_empty("imei_empty", "imei为空"),
    general_update("general_update", "普更调用"),
    general_update_success("general_update_success", "普更下载完成"),
    general_update_fail("general_update_fail", "普更下载失败"),
    forced_update("forced_update", "强更调用"),
    forced_update_success("forced_update_success", "强更下载完成"),
    forced_update_fail("forced_update_fail", "强更下载失败"),
    hotter_update("hotter_update", "热更调用"),
    hotter_update_rollback("hotter_update_rollback", "插件回滚"),
    hotter_update_success("hotter_update_success", "热更插件下载完成"),
    hotter_update_fail("hotter_update_fail", "热更插件下载失败"),
    hotter_update_effect("hotter_update_effect", "热更加载生效"),
    hotter_update_rollback_effect("hotter_update_rollback_effect", "热更回滚生效"),
    HOTTER_UPDATE_CONSUMING("hotter_update_consuming", "热更耗时统计"),
    deprecated_call("deprecated_call", "过时类被调用"),
    report_after_permission("report_after_permission", "同意权限后开始媒体操作"),
    report_register("report_register", "上报注册至媒体"),
    report_purchase("report_purchase", "上报支付至媒体"),
    report_event("report_event", "上报事件至媒体"),
    report_cp_pay_event("report_cp_pay_event", "上报CP支付事件至媒体"),
    report_oaid("report_oaid", "上报oaid"),
    report_oaid_request("report_oaid_request", "上报oaid请求"),
    request_pcheck("request_pcheck", "请求政策合规"),
    request_pcheck_succ("request_pcheck_succ", "请求政策合规"),
    request_pcheck_fail("request_pcheck_fail", "请求政策不合规"),
    cp_login_succ("cp_login_succ", "回调给cp登录成功"),
    cp_login_fail("cp_login_fail", "回调给cp登录失败"),
    request_permission_result("request_permission_result", "权限申请结果"),
    DIAGNOSTIC_ASSISTANT_HOME_SHOW("diagnostic_assistant_home_show", "诊断助手显示了"),
    DIAGNOSTIC_ASSISTANT_HOME_CLOSE("diagnostic_assistant_home_close", "诊断助手关闭了"),
    DIAGNOSTIC_ASSISTANT_UPDATE_LOG_SUCCESS("diagnostic_assistant_update_log_success", "离线日志上传成功"),
    DIAGNOSTIC_ASSISTANT_UPDATE_LOG_FAIL("diagnostic_assistant_update_log_fail", "离线日志上传失败"),
    DIAGNOSTIC_ASSISTANT_LOG_RETRIEVAL_SUCCESS("diagnostic_assistant_log_retrieval_success", "日志回捞成功"),
    DIAGNOSTIC_ASSISTANT_LOG_RETRIEVAL_FAIL("diagnostic_assistant_log_retrieval_fail", "日志回捞失败"),
    PACKAGE_PARAMS("package_params", "包体参数"),
    error_report("error_report", "错误上报"),
    share_invoke("share_invoke", "分享调用"),
    share_success(SqR.string.share_success, "分享成功"),
    share_fail("share_fail", "分享失败"),
    FM_TURN_ON("fm_turnon", "开始收听"),
    FM_TURN_OFF("fm_turnoff", "关闭收听"),
    BUILTIN_LIVE_ICON("builtin_live_icon", "入口点击"),
    BUILTIN_LIVE_WATCH("builtin_live_watch", "进入直播间"),
    BUILTIN_LIVE_OFF("builtin_live_off", "退出直播间"),
    BULLET_CHAT("bullet_chat", "弹幕发言"),
    BUILTIN_LIVE_HALF_OFF("builtin_live_half_off", "退出半屏模式直播间"),
    BUILTIN_LIVE_HALF_WATCH("builtin_live_half_watch", "进入半屏模式直播间"),
    BUILTIN_LIVE_LISTENING("builtin_live_listening", "进入仅听声音模式"),
    BUILTIN_LIVE_LISTENING_OFF("builtin_live_listening_off", "退出仅听声音模式"),
    FM_COMMETNS("fm_comments", "发送评论"),
    START_URL_REQUEST("start_url_request", "发起业务请求"),
    network_status("network_status", "单域名解析第一次失败网络探测"),
    before_request_network_status("before_request_network_status", "腾讯云dns解析前网络探测"),
    LOACL_ACTIVE("sdk_local_active", "sdk本地激活"),
    WEB_JUMP_PIC_SELECT("web_jump_pic_select", "web页面跳转图片选择功能"),
    TOUCH("touch_information_37", "touch_information"),
    DEVICE_INFO_ACTIVE("device_info_active", "激活上报设备信息"),
    DEVICE_INFO_LOGIN("device_info_login", "登录上报设备信息"),
    DEVICE_INFO_LOGIN_FAIL("device_info_login_fail", "登录失败上报设备信息"),
    PAY_NET_ERROR("pay_net_error", "支付页面加载超时"),
    OAID_RESULT("oaid_result", "oaid获取结果"),
    COMMENT_SUCCESS("comment_success", "点击好评跳转成功"),
    COMMENT_CANCEL("comment_cancel", "取消好评跳转"),
    COMMENT_CLOSE("comment_close", "好评跳转弹窗关闭"),
    COMMENT_ERROR("comment_error", "好评跳转异常"),
    POLICY_ERROR("policy_error", "policy政策接口异常"),
    POLICY_SHARE_LIST_SUCCESS("policy_share_list_success", "个人信息第三方共享清单打开成功"),
    POLICY_SHARE_LIST_ERROR("policy_share_list_error", "个人信息第三方共享清单打开异常"),
    POLICY_PERSONAL_LIST_SUCCESS("policy_personal_list_success", "个人信息收集与使用清单打开成功"),
    POLICY_PERSONAL_LIST_ERROR("policy_personal_list_error", "个人信息收集与使用清单打开异常"),
    POLICY_RECORD_NUMBER_SUCCESS("policy_record_number_success", "备案号获取成功"),
    POLICY_RECORD_NUMBER_ERROR("policy_record_number_error", "备案号获取失败"),
    POLICY_SHOW_MIIT_SUCCESS("policy_show_miit_success", "工信部url打开成功"),
    POLICY_SHOW_MIIT_ERROR("policy_show_miit_error", "工信部url打开异常"),
    popup_dialog_show("popup_dialog_show", "通用弹窗曝光"),
    PAY_H5_JS_SUCCESS("pay_h5_js_success", "h5支付js支付成功"),
    PAY_H5_WX("pay_h5_wx", "微信h5支付方式"),
    PAY_H5_DOU_YIN("pay_h5_dou_yin", "抖音h5支付方式"),
    PAY_H5_ALI("pay_h5_ali", "支付宝h5支付方式"),
    PAY_H5_UNION("pay_h5_union", "云闪付h5支付方式"),
    PAY_H5_RETRY_CHECK_SUCCESS("pay_h5_retry_check_success", "h5支付重试查单支付成功"),
    SHOW_USER_AGREEMENT("show_user_agreement", "用户协议调起SDK协议弹窗"),
    SHOW_AGE_APPROPRIATE("show_age_appropriate", "适龄弹窗"),
    REQUEST_PERMISSION("request_permission", "权限申请"),
    PERMISSION_CALLBACK("permission_callback", "权限回调"),
    SWITCH_ACCOUNT_SUCCESS("switch_account_success", "切换账号成功事件"),
    BACK_TO_GAME_LOGIN("back_to_game_login", "回到登录界面"),
    DO_LOGIN("do_login", "登录接口调用"),
    CREATE_ROLE_INFO("create_role_info", "创建角色信息"),
    SUBMIT_ROLE_INFO("submit_role_info", "提交角色信息"),
    UPGRADE_ROLE_LEVEL("upgrade_role_level", "角色升级信息"),
    PAY_ORDER("pay_order", "支付接口上报"),
    SHOW_EXIT_DIALOG("show_exit_dialog", "退出接口调用"),
    QRCODE_SCAN_PAGE("qrcode_scan_page", "进入扫一扫页面"),
    QRCODE_SCAN_PERMISSION("qrcode_scan_permission", "相机权限弹窗"),
    QRCODE_SCAN_AUTH("qrcode_scan_auth", "相机权限授权"),
    QRCODE_SCAN_SUCC("qrcode_scan_succ", "扫码成功"),
    QRCODE_CONFIRM_SUCC("qrcode_confirm_succ", "扫码成功确认"),
    QRCODE_LOGIN_SUCC("qrcode_login_succ", "扫码登录成功"),
    QRCODE_LOGIN_FAIL("qrcode_login_fail", "扫码失败"),
    SDK_COMMON_ERROR("sdk_common_error", "SDK普通异常分析"),
    MULTI_ACCOUNT_SELECT_PAGE("multi_account_select_page", "账号选择弹窗曝光"),
    MULTI_ACCOUNT_LOGIN_SUCC("multi_account_login_succ", "账号确认后登录成功"),
    MULTI_ACCOUNT_LOGIN_FAIL("multi_account_login_fail", "账号确认选择后密码错误"),
    MULTI_ACCOUNT_RE_INPUT("multi_account_re_input", "重新输入密码按钮点击"),
    CHECK_ACCOUNT_LIST_SUCC("check_account_list_succ", "密码校验通过"),
    CHECK_ACCOUNT_LIST_FAIL("check_account_list_fail", "密码校验错误"),
    SDK_WEB_LOAD_FINISH("sdk_web_load_finish", "网页加载完成埋点"),
    SDK_WEB_LOAD_FAIL("sdk_web_load_fail", "网页加载失败埋点"),
    SDK_WEB_LOAD_WHITE_SCREEN_ERROR("sdk_web_load_white_screen_error", "网页加载完成但是页面是白屏埋点"),
    SDK_WEB_SSL_ERROR("sdk_web_ssl_error", "网页加载出现网站证书错误埋点"),
    SDK_WEB_HTTP_CODE_ERROR("sdk_web_http_code_error", "网页加载出现http状态码错误"),
    PERMISSION_SCENE_FORBIDDEN("permission_scene_forbidden", "拒绝场景弹窗"),
    PERMISSION_SCENE_GRANTED("permission_scene_granted", "同意场景弹窗"),
    SDK_GAME_BINDING_INVOKE("sdk_game_binding_invoke", "游戏绑定调用"),
    SDK_GAME_BINDING_SUCCESS("sdk_game_binding_success", "游戏绑定成功"),
    SDK_GAME_BINDING_FAIL("sdk_game_binding_fail", "游戏绑定失败"),
    SDK_GAME_BINDING_CANCEL("sdk_game_binding_cancel", "游戏绑定取消"),
    DLYZAPP_LOGIN_PUSH_SHOW("dlyzapp_login_push_show", "授权账号登录弹窗曝光"),
    CONFIRM_DLYZAPP_LOGIN_AUTHORIZE("confirm_dlyzapp_login_authorize", "授权账号登录弹窗点击确定授权"),
    CANCEL_DLYZAPP_LOGIN_AUTHORIZE("cancel_dlyzapp_login_authorize", "授权账号登录弹窗点击取消授权"),
    DLYZAPP_DOWNLOAD_PUSH_SHOW("dlyzapp_download_push_show", "下载弹窗展示"),
    DLYZAPP_DOWNLOAD_PUSH_START("dlyzapp_download_push_start", "开始下载"),
    DLYZAPP_DOWNLOAD_PUSH_COMPLETE("dlyzapp_download_push_complete", "完成下载"),
    DLYZAPP_DOWNLOAD_PUSH_INSTALL("dlyzapp_download_push_install", "点击安装按钮"),
    DLYZAPP_DOWNLOAD_PUSH_OPEN("dlyzapp_download_push_open", "点击打开应用按钮"),
    IS_SYSTEM_INSTALL_DLYZAPP("is_system_install_dlyzapp", "获取系统是否安装斗罗app"),
    DLYZAPP_DOWNLOAD_PUSH_CLOSE("dlyzapp_download_push_close", "关闭弹窗");

    private String event;
    private String name;

    SqTrackAction2(String str, String str2) {
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
        return "SqTrackAction2{event='" + this.event + "', name='" + this.name + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
