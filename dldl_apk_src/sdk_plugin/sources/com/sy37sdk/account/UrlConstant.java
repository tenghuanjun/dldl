package com.sy37sdk.account;

import android.text.TextUtils;
import com.sqwan.base.L;
import com.sqwan.common.annotation.UrlUpdate;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.config.ConfigManager;
import com.sqwan.msdk.config.MultiSdkManager;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UrlConstant {

    @UrlUpdate(KEY_S_APPEAL_PAGE)
    public static String APPEAL_PAGE = null;

    @UrlUpdate(value = KEY_S_AGE_APPROPRIATE_COF, xValue = "x_right_age")
    public static String APPROPRIATE_AGE_PROTOCOL = null;
    private static String APP_HOST_Prefix_p = "http://message-api.";

    @UrlUpdate(value = KEY_S_REPORT_PCHECK, xValue = "x_pcheck")
    public static String CHECK_ANTI_AUTHENT = null;
    public static String FACE_VERIFY_CUSTOMER_PAGE = null;
    public static String FACE_VERIFY_HELP_PAGE = null;

    @Deprecated
    public static String FLOAT_WINDOW = null;

    @UrlUpdate(value = KEY_S_FLOAT_WINDOW_NEW, xValue = "x_float_config")
    public static String FLOAT_WINDOW_V2 = null;

    @UrlUpdate(value = "resetPwd", xValue = "x_forget_password")
    public static String FORGET_PWD = null;

    @UrlUpdate(value = "x_sdk_kf_guide_url", xValue = "x_sdk_kf_guide_url")
    public static String KEFU_GUIDE_URL = null;

    @UrlUpdate(value = "x_sdk_kf_icon_url", xValue = "x_sdk_kf_icon_url")
    public static String KEFU_ICON_URL = null;
    public static final String KEY_GAME_URL_LIST = "game_url_list";
    public static final String KEY_M_SDK_PERMISSION = "sdk_permission";
    public static final String KEY_M_USER_PROTOCOL = "m_user_protocol";
    public static final String KEY_PLATFORM_BOARD = "announcement";
    public static final String KEY_S_AGE_APPROPRIATE_COF = "right_age";
    public static final String KEY_S_APPEAL_PAGE = "phone_retrieval";
    public static final String KEY_S_AUTO_ASSIGN = "auto_assign";
    public static final String KEY_S_CFG_SHAN_YAN = "cfg_shan_yan";
    public static final String KEY_S_CHECK_SCODE = "sdk_mobile_check_code";
    public static final String KEY_S_ENTRANCE = "entrance";
    public static final String KEY_S_FETCH_INFO = "u_fetch_u_info";
    public static final String KEY_S_FLOAT_WINDOW_NEW = "s_float_window_new";
    public static final String KEY_S_GET_AVATAR_LIST = "get_pt_avatar_list";
    public static final String KEY_S_GET_FLOAT_WARNING = "get_float_warning";
    public static final String KEY_S_GET_RED_POINT = "get_red_point";
    public static final String KEY_S_GET_USER_INFO = "get_pt_user_info";
    public static final String KEY_S_LOGIN = "login";
    public static final String KEY_S_LOGIN_PWD = "mobile_login_pwd";
    public static final String KEY_S_LOGIN_SCODE = "mobile_login_scode";
    public static final String KEY_S_LOGIN_TICKET = "sdk_mobile_login_ticket";
    public static final String KEY_S_MREG = "mreg";
    public static final String KEY_S_MREG_RES = "mreg_res";
    public static final String KEY_S_MSCODE = "mscode";
    public static final String KEY_S_OTHER_LOGIN = "other_login";
    public static final String KEY_S_QUICK_LOGIN = "quick_login";
    public static final String KEY_S_REG = "reg";
    public static final String KEY_S_REPORT_DEV_DURATION = "report_dev";
    public static final String KEY_S_REPORT_LOGIN_FAIL = "sdk_login_fail";
    public static final String KEY_S_REPORT_ONLINE = "report_online";
    public static final String KEY_S_REPORT_PCHECK = "pcheck";
    public static final String KEY_S_REPORT_USER_DURATION = "report_user";
    public static final String KEY_S_REST_PWD = "resetPwd";
    public static final String KEY_S_SEND_SCODE = "mobile_send_scode";
    public static final String KEY_S_SHAN_YAN_LOGIN = "mobile_shan_yan_login";
    public static final String KEY_S_SHOW_RED_FLOAT_WINDOW = "s_show_red_float_window";
    public static final String KEY_S_UAGREE = "uagree";
    public static final String KEY_S_UPDATE_RED_POINT = "update_red_point";
    public static final String KEY_S_UPDATE_USER_INFO = "update_pt_user_info";
    public static final String KEY_S_USER_PROTOCOL = "s_user_protocol";
    public static final String KEY_S_VALIDATE_CHECK_FACE_VERIFY = "validate_check_face_verify";
    public static final String KEY_S_VALIDATE_DESCRIBE_FACE_VERIFY = "validate_describe_face_verify";
    public static final String KEY_S_VALIDATE_INIT_FACE_VERIFY = "validate_init_face_verify";
    public static final String KEY_WECHAT_LOGIN = "x_auth_wechat_oplatform";

    @UrlUpdate("other_login")
    public static String OTHER_LOGIN = null;

    @UrlUpdate(value = KEY_PLATFORM_BOARD, xValue = "x_announcement")
    public static String PLATFORM_FAULT_URL = null;
    public static final String POP_UPS_LOGIN_API = "pop_ups_login_api";

    @UrlUpdate(KEY_S_SHOW_RED_FLOAT_WINDOW)
    public static String REDPACKET_FLOATVIEW_SWITCH;
    public static String UPDATE_URL;

    @UrlUpdate(value = KEY_S_VALIDATE_INIT_FACE_VERIFY, xValue = "x_face_init")
    public static String URL_ALI_CERTIFY_FACE_ID;

    @UrlUpdate(value = KEY_S_VALIDATE_DESCRIBE_FACE_VERIFY, xValue = "x_face_verify_res")
    public static String URL_CHECK_VALIDATE_VERIFY;

    @UrlUpdate(value = KEY_S_GET_AVATAR_LIST, xValue = "x_pt_avatar_list")
    public static String URL_GET_AVATAR_LIST;
    public static String URL_GET_CONFIG_INFO;

    @UrlUpdate(value = KEY_S_GET_FLOAT_WARNING, xValue = "x_get_float_warning")
    public static String URL_GET_FLOAT_WARNING;

    @UrlUpdate(value = "x_gamehub_popup", xValue = "x_gamehub_popup")
    public static String URL_GET_POPS_UP;

    @UrlUpdate(value = KEY_S_GET_USER_INFO, xValue = "x_pt_user_info")
    public static String URL_GET_PT_USER_INFO;

    @UrlUpdate(value = KEY_S_UPDATE_RED_POINT, xValue = "x_red_point_watch")
    public static String URL_GET_RED_CALLED;

    @UrlUpdate(value = KEY_S_GET_RED_POINT, xValue = "x_red_point")
    public static String URL_GET_RED_POINT;

    @UrlUpdate(value = KEY_S_CHECK_SCODE, xValue = "x_sdk_mobile_check_code")
    public static String URL_LOGIN_CHECK_CODE;

    @UrlUpdate(value = KEY_S_QUICK_LOGIN, xValue = "x_quick_login")
    public static String URL_LOGIN_FAST;

    @UrlUpdate(value = KEY_S_LOGIN_SCODE, xValue = "x_phone_logincode")
    public static String URL_LOGIN_PHONE_CODE;

    @UrlUpdate(value = KEY_S_LOGIN_PWD, xValue = "x_phone_login_pwd")
    public static String URL_LOGIN_PHONE_PWD;

    @UrlUpdate(value = KEY_S_REPORT_LOGIN_FAIL, xValue = "x_sdk_login_fail")
    public static String URL_LOGIN_REPORT;

    @UrlUpdate(value = KEY_S_SEND_SCODE, xValue = "x_phone_code")
    public static String URL_LOGIN_SEND_CODE;

    @UrlUpdate(value = KEY_S_LOGIN_TICKET, xValue = "x_sdk_mobile_login_ticket")
    public static String URL_LOGIN_TICKET;

    @UrlUpdate(value = KEY_S_UPDATE_USER_INFO, xValue = "x_update_pt_user_info")
    public static String URL_MODIFY_PERSON_INFO;
    public static String URL_M_REPORT_RISK_TOKEN;

    @UrlUpdate(value = KEY_S_VALIDATE_CHECK_FACE_VERIFY, xValue = "x_face_if_need")
    public static String URL_NEED_ALI_CERTIFY_FACE;

    @UrlUpdate(value = "x_sdk_qrcode_login_cancel", xValue = "x_sdk_qrcode_login_cancel")
    public static String URL_QRCODE_CANCEL_AUTH;

    @UrlUpdate(value = "x_sdk_qrcode_login_confirm", xValue = "x_sdk_qrcode_login_confirm")
    public static String URL_QRCODE_CONFIRM_AUTH;

    @UrlUpdate(value = "x_sdk_qrcode_login_scan", xValue = "x_sdk_qrcode_login_scan")
    public static String URL_QRCODE_SCAN;

    @UrlUpdate(value = KEY_S_ENTRANCE, xValue = "x_entrance")
    public static String URL_REG_ENTRANCE;

    @UrlUpdate("report_dev")
    public static String URL_REPORT_DEV_DURATION;

    @UrlUpdate(value = KEY_S_REPORT_ONLINE, xValue = "x_report_online")
    public static String URL_REPORT_DEV_ONLINE;

    @UrlUpdate(value = "report_user", xValue = "x_report_user_duration")
    public static String URL_REPORT_USER_DURATION;

    @UrlUpdate(value = KEY_S_CFG_SHAN_YAN, xValue = "x_config_flash_verify_url")
    public static String URL_REQUEST_FAST_CONFIG;

    @UrlUpdate(value = "x_gamehub_sub_user_relate", xValue = "x_gamehub_sub_user_relate")
    public static String URL_USER_RELATE;

    @UrlUpdate(value = KEY_S_SHAN_YAN_LOGIN, xValue = "x_doflash_verify_url")
    public static String URL_VERIFY_FAST_TOKEN;

    @UrlUpdate(KEY_S_USER_PROTOCOL)
    public static String USER_PROTOCOL;

    @UrlUpdate(value = KEY_WECHAT_LOGIN, xValue = "x_s_auth_wx_oplatform")
    public static String WECHAT_LOGIN;

    @UrlUpdate(KEY_S_FETCH_INFO)
    public static String WX_AUTH;

    @UrlUpdate(value = "pop_ups_login_api", xValue = "x_popups_login")
    public static String URL_M_LOGIN_POPUP = "http://m-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/popups/login";

    @UrlUpdate(value = KEY_M_USER_PROTOCOL, xValue = "x_user_protocol")
    public static String USER_PROTOCOL_ACTIVE_BEFORE = "http://m-api-secure." + MultiSdkManager.APP_HOST + "/go/cfg/user_protocol";

    @UrlUpdate(KEY_M_SDK_PERMISSION)
    public static String ACTIVE_BEFORE_PERMISSION = "http://m-api." + MultiSdkManager.APP_HOST + "/go/cfg/sdk_permission";

    @UrlUpdate(KEY_GAME_URL_LIST)
    public static String GET_GAME_URL_LIST = "http://s-api." + MultiSdkManager.APP_HOST + "/api/sapi-service/v1/gamedomainnamefiling/game_url_list";

    @UrlUpdate(value = "login", xValue = "x_login")
    public static String LOGIN = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/sdk/login/";

    @UrlUpdate(value = "x_sdk_check_account_list", xValue = "x_sdk_check_account_list")
    public static String CHECK_ACCOUNT_LIST = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/check_account_list";

    @UrlUpdate(value = "reg", xValue = "x_register")
    public static String REG = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/sdk/reg/";

    @UrlUpdate("mreg_res")
    public static String MREG_RES = "http://s-api." + MultiSdkManager.APP_HOST + "/mobile/reg_res/";

    @UrlUpdate(value = "auto_assign", xValue = "x_auto_assign")
    public static String AUTO_SET_ACCOUNT = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/sdk/autoassign";

    @UrlUpdate("mscode")
    public static String MSCODE = "http://s-api." + MultiSdkManager.APP_HOST + "/mobile/scode/";

    @UrlUpdate("mreg")
    public static String MREG = "http://s-api." + MultiSdkManager.APP_HOST + "/mobile/reg/";

    @UrlUpdate("uagree")
    public static String USER_AGREE = "http://" + MultiSdkManager.APP_HOST + "/sdk-wrap/iframe.html?sversion=3.1.0&isrc=http://" + MultiSdkManager.APP_HOST + "/uagree/37.html";

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("http://s-api.");
        sb.append(MultiSdkManager.APP_HOST);
        sb.append("/go/sdk/reportDevDuration");
        URL_REPORT_DEV_DURATION = sb.toString();
        URL_REPORT_USER_DURATION = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/reportUserDuration";
        CHECK_ANTI_AUTHENT = "https://m-api-secure." + MultiSdkManager.APP_HOST + "/antiindulge/pcheck/";
        FORGET_PWD = "https://user.37.com.cn/sdkv1/service/retrieval/psw/index";
        APPEAL_PAGE = "http://" + MultiSdkManager.APP_HOST + "/service-system/accountappeal/phoneRetrieval";
        OTHER_LOGIN = "http://s-api." + MultiSdkManager.APP_HOST + "/oauth/login/";
        USER_PROTOCOL = "http://s-api." + MultiSdkManager.APP_HOST + "/go/cfg/user_protocol";
        FLOAT_WINDOW = "http://s-api." + MultiSdkManager.APP_HOST + "/go/cfg/float_window";
        FLOAT_WINDOW_V2 = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/cfg/v2/float_window";
        REDPACKET_FLOATVIEW_SWITCH = "http://s-api." + MultiSdkManager.APP_HOST + "/go/cfg/show_red_float_window";
        WX_AUTH = "http://us-api." + MultiSdkManager.APP_HOST + "/oauth/gw/fetchUinfo?";
        WECHAT_LOGIN = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/third_auth/wechat/oplatform";
        APPROPRIATE_AGE_PROTOCOL = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/cfg/age_appropriate_conf";
        URL_REPORT_DEV_ONLINE = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/reportOnline";
        URL_REQUEST_FAST_CONFIG = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/cfg/shan_yan";
        URL_VERIFY_FAST_TOKEN = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/mobile/shan_yan_login";
        URL_LOGIN_PHONE_CODE = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/mobile/login_scode";
        URL_LOGIN_SEND_CODE = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/mobile/send_scode";
        URL_LOGIN_CHECK_CODE = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/mobile/check_code";
        URL_LOGIN_TICKET = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/mobile/login_ticket";
        URL_LOGIN_PHONE_PWD = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/mobile/login_pwd";
        URL_LOGIN_FAST = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/quick_login";
        URL_REG_ENTRANCE = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/go/cfg/entrance";
        URL_LOGIN_REPORT = "http://m-api.-secure." + MultiSdkManager.APP_HOST + "/go/sdk/report/login_fail";
        URL_M_REPORT_RISK_TOKEN = "http://m-api." + MultiSdkManager.APP_HOST + "/go/sdk/report/risk";
        URL_GET_PT_USER_INFO = "https://us-api-secure." + MultiSdkManager.APP_HOST + "/userinfo/getPtUserInfo";
        URL_GET_RED_POINT = "https://message-api-secure." + MultiSdkManager.APP_HOST + "/api/message-state-api/v1/red_dot/quantity";
        URL_GET_FLOAT_WARNING = "https://message-api-secure." + MultiSdkManager.APP_HOST + "/api/message-state-api/v1/red_dot/sdk/get_warning";
        URL_GET_RED_CALLED = "https://message-api-secure." + MultiSdkManager.APP_HOST + "/api/message-state-api/v1/red_dot/watched";
        URL_GET_AVATAR_LIST = "https://us-api-secure." + MultiSdkManager.APP_HOST + "/userinfo/getAvatarList";
        URL_MODIFY_PERSON_INFO = "https://us-api-secure." + MultiSdkManager.APP_HOST + "/userinfo/setPtUserInfo";
        URL_ALI_CERTIFY_FACE_ID = "https://validate-api-secure." + MultiSdkManager.APP_HOST + "/face/init-verify";
        URL_NEED_ALI_CERTIFY_FACE = "https://validate-api-secure." + MultiSdkManager.APP_HOST + "/face/check-verify-need";
        URL_CHECK_VALIDATE_VERIFY = "https://validate-api-secure." + MultiSdkManager.APP_HOST + "/face/get-verify-res";
        PLATFORM_FAULT_URL = "https://sos-service-secure." + MultiSdkManager.APP_HOST + "/api/sos-service/v1/platform_board/get";
        StringBuilder sb2 = new StringBuilder();
        sb2.append("https://user.");
        sb2.append(ConfigManager.getInstance(L.getActivity()).isSplashSDK() ? "39ej7e.com" : MultiSdkManager.APP_HOST);
        sb2.append("/sdkv1/user-system/face-help");
        FACE_VERIFY_HELP_PAGE = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("https://user.");
        sb3.append(ConfigManager.getInstance(L.getActivity()).isSplashSDK() ? "39ej7e.com" : MultiSdkManager.APP_HOST);
        sb3.append("/sdkv1/service/home");
        FACE_VERIFY_CUSTOMER_PAGE = sb3.toString();
        KEFU_GUIDE_URL = "";
        KEFU_ICON_URL = "";
        UPDATE_URL = "https://sdk-apix-secure." + MultiSdkManager.APP_HOST + "/server-info-service/get-url";
        URL_QRCODE_SCAN = "http://s-api." + MultiSdkManager.APP_HOST + "/go/sdk/account/qrcode/scan";
        URL_QRCODE_CONFIRM_AUTH = "http://s-api." + MultiSdkManager.APP_HOST + "/go/sdk/account/qrcode/confirm";
        URL_QRCODE_CANCEL_AUTH = "http://s-api." + MultiSdkManager.APP_HOST + "/go/sdk/account/qrcode/cancel";
        URL_GET_CONFIG_INFO = "http://app-liefer.37.com.cn/app/client/config/get";
        URL_USER_RELATE = "https://gamehub-api-secure.37.com.cn/api/gamehub-api/v1/sub_user/relate";
        URL_GET_POPS_UP = "https://m-api-secure." + MultiSdkManager.APP_HOST + "/api/mapi-service/v1/gamehub/pops_up";
    }

    public static void refreshFaceUrls(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("face_verify_customer");
            if (!TextUtils.isEmpty(strOptString)) {
                FACE_VERIFY_CUSTOMER_PAGE = strOptString;
            }
            String strOptString2 = jSONObject.optString("face_verify_help");
            if (TextUtils.isEmpty(strOptString2)) {
                return;
            }
            FACE_VERIFY_HELP_PAGE = strOptString2;
        } catch (JSONException e) {
            LogUtil.e("解析页面链接失败！");
            e.printStackTrace();
        }
    }
}
