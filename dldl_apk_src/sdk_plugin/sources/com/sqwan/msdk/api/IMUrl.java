package com.sqwan.msdk.api;

import android.os.Build;
import com.sqwan.common.annotation.UrlUpdate;
import com.sqwan.msdk.config.MultiSdkManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class IMUrl {
    public static final String KEY_ACTIVATION_CODE_CHECK = "vbcapi";
    public static final String KEY_M_ENTER = "eapi";
    public static final String KEY_M_ORDER = "oapi";
    public static final String KEY_M_PAY = "pay";
    public static final String KEY_M_SUBMIT = "lapi";
    public static final String KEY_M_VERIFY_TOKEN = "vptapi";
    public static final String KEY_PUSH = "push_url";
    public static final String KEY_REPORT_M_KEY = "report_m_dev";
    public static final String KEY_TRACK = "track";
    public static final String M_ACTIVE = "m_activate";
    public static final String POP_UPS_ACTIVE_API = "pop_ups_active_api";
    public static final String POP_UPS_ENTER_API = "pop_ups_enter_api";
    public static final String POP_UPS_RECHARGE_API = "pop_ups_recharge_api";
    public static final String MODE = Build.MODEL;
    public static final String OS = "android";
    public static final String OSVER = OS + Build.VERSION.RELEASE;

    @UrlUpdate(value = M_ACTIVE, xValue = "x_activate_m")
    public static String URL_M_INIT = "https://m-api-secure." + MultiSdkManager.APP_HOST + "/sdk/active/";

    @UrlUpdate(value = "pop_ups_active_api", xValue = "x_popups_active")
    public static String URL_M_INIT_DIALOG = "https://m-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/popups/active";

    @UrlUpdate(value = "pop_ups_enter_api", xValue = "x_popups_enter")
    public static String URL_SUBMIT_ROLE_POPUP = "https://m-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/popups/enter";

    @UrlUpdate(value = "pop_ups_recharge_api", xValue = "x_popups_recharge")
    public static String URL_PAY_POPUP = "https://m-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/popups/recharge";

    @UrlUpdate(KEY_M_VERIFY_TOKEN)
    public static String URL_M_VAREFY_TOKEN = "http://vt-api." + MultiSdkManager.APP_HOST + "/verify/ptoken/";

    @UrlUpdate(value = KEY_M_ORDER, xValue = "x_order_m")
    public static String URL_M_ORDER = "http://mpay-api-secure." + MultiSdkManager.APP_HOST + "/sdk/order/";

    @UrlUpdate(KEY_M_SUBMIT)
    public static String URL_M_SUBMIT = "http://log.api." + MultiSdkManager.APP_HOST + "/sdk/tj/";

    @UrlUpdate(KEY_PUSH)
    public static String URL_PUSH = "http://push.api." + MultiSdkManager.APP_HOST + "/sdk/push/";

    @UrlUpdate(value = KEY_ACTIVATION_CODE_CHECK, xValue = "x_vbcapi")
    public static String URL_ACTIVATION_CODE_CHECK = "http://m-api-secure." + MultiSdkManager.APP_HOST + "/sdk/vbetac/";

    @UrlUpdate(value = KEY_M_ENTER, xValue = "x_enter")
    public static String URL_M_ENTER = "http://m-api-secure." + MultiSdkManager.APP_HOST + "/sdk/enter/";

    @UrlUpdate(KEY_REPORT_M_KEY)
    public static String URL_REPORT_MDEV = "http://m-api." + MultiSdkManager.APP_HOST + "/go/sdk/reportDev";

    @UrlUpdate("pay")
    public static String URL_PAY_DEFAULT = "http://" + MultiSdkManager.APP_HOST + "/sdk/pay/";

    @Deprecated
    public static String URL_M_SPEECH = "https://pvt-api." + MultiSdkManager.APP_HOST + "/gvoice/getroomconf/";

    @Deprecated
    public static String URL_M_SPEECH_EXITROOM = "https://pvt-api." + MultiSdkManager.APP_HOST + "/gvoice/exitroom/";
    public static String URL_PAY_QUERY = "http://mpay-api." + MultiSdkManager.APP_HOST + "/payment/state/";

    @UrlUpdate("track")
    public static String URL_DATA_TRACK = "https://track." + MultiSdkManager.APP_HOST + "/api/event/";
    public static String GET_SHARE_SOURCE = "https://s-api." + MultiSdkManager.APP_HOST + "/go/sdk/share";
}
