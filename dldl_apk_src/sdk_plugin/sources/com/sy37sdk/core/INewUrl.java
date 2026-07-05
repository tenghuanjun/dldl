package com.sy37sdk.core;

import android.content.Context;
import android.content.SharedPreferences;
import com.sqwan.common.annotation.UrlUpdate;
import com.sqwan.msdk.config.MultiSdkManager;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class INewUrl {

    @UrlUpdate(value = "auto_assign", xValue = "x_auto_assign")
    public static String AUTO_SET_ACCOUNT = null;

    @UrlUpdate(value = "resetPwd", xValue = "x_forget_password")
    public static String FORGET_PWD = null;
    public static final String KEY_S_ACTIVE = "s_activate";
    public static final String KEY_S_ART = "art";
    public static final String KEY_S_AUTO_ASSIGN = "auto_assign";
    public static final String KEY_S_BBS = "bbs";
    public static final String KEY_S_BM = "bm";
    public static final String KEY_S_BP = "bp";
    public static final String KEY_S_CARD = "card";
    public static final String KEY_S_CPWD = "cpwd";
    public static final String KEY_S_FREG = "freg";
    public static final String KEY_S_GCARD = "gcard";
    public static final String KEY_S_GWA = "gwa";
    public static final String KEY_S_GWI = "gwi";
    public static final String KEY_S_ICARD = "icard";
    public static final String KEY_S_IMSG = "imsg";
    public static final String KEY_S_IWT = "iwt";
    public static final String KEY_S_KF = "kf";
    public static final String KEY_S_LOGIN = "login";
    public static final String KEY_S_MFP = "mfp";
    public static final String KEY_S_MREG = "mreg";
    public static final String KEY_S_MREG_RES = "mreg_res";
    public static final String KEY_S_MSCODE = "mscode";
    public static final String KEY_S_ORDER_STATUS = "order_status";
    public static final String KEY_S_OSL = "osl";
    public static final String KEY_S_OTHER_LOGIN = "other_login";
    public static final String KEY_S_PAY = "pay";
    public static final String KEY_S_PFP = "pfp";
    public static final String KEY_S_PUSH = "push";
    public static final String KEY_S_REG = "reg";
    public static final String KEY_S_REPORT_DEV = "report_dev";
    public static final String KEY_S_REPORT_USER = "report_user";
    public static final String KEY_S_REST_PWD = "resetPwd";
    public static final String KEY_S_SHOP = "shop";
    public static final String KEY_S_SPRO = "spro";
    public static final String KEY_S_SPV = "spv";
    public static final String KEY_S_UAGREE = "uagree";
    public static final String KEY_TRACK = "track";

    @UrlUpdate("other_login")
    public static String OTHER_LOGIN = null;
    public static String POP_BBS_URL = null;
    public static String POP_CARD_URL = null;
    public static String POP_CHANGEACCOUNT = null;
    public static String POP_GL_URL = null;
    public static String POP_HELP_URL = null;
    public static String POP_USER_URL = null;
    private static final String SQ_URL_POP_HELP_URL = "pop_help_url";
    private static final String SQ_URL_PREFS = "sq_url_prefs";

    @UrlUpdate("track")
    public static String URL_DATA_TRACK;

    @UrlUpdate(value = "pop_ups_order_api", xValue = "x_popups_order")
    public static String URL_POPUPS_ORDER;

    @UrlUpdate("report_dev")
    public static String URL_REPORT_DEV_DURATION;

    @UrlUpdate("report_user")
    public static String URL_REPORT_USER_DURATION;
    public static HashMap<String, String> urls = new HashMap<>();

    @UrlUpdate(value = KEY_S_ACTIVE, xValue = "x_activate_s")
    public static String INIT = "https://s-api-secure." + MultiSdkManager.APP_HOST + "/sdk/active/";

    @UrlUpdate(value = "login", xValue = "x_login")
    public static String LOGIN = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/sdk/login/";

    @UrlUpdate(value = "reg", xValue = "x_register")
    public static String REG = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/sdk/reg/";

    @UrlUpdate(KEY_S_FREG)
    public static String REG_FAST = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/freg/";

    @UrlUpdate(KEY_S_IMSG)
    public static String IMSG = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/imsg/";

    @UrlUpdate(KEY_S_GWI)
    public static String GWI = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/gwi/";

    @UrlUpdate(KEY_S_ART)
    public static String ART = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/art/";

    @UrlUpdate(KEY_S_CARD)
    public static String CARD = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/card/";

    @UrlUpdate(KEY_S_GCARD)
    public static String GCARD = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/gcard/";

    @UrlUpdate(KEY_S_PUSH)
    public static String PUSH = "http://push-api." + MultiSdkManager.APP_HOST + "/push/";

    @UrlUpdate("pay")
    public static String PAY = "http://" + MultiSdkManager.APP_HOST + "/sdk/pay/";

    @UrlUpdate("order_status")
    public static String PAY_CHECK = "http://spay-api." + MultiSdkManager.APP_HOST + "/sdk/ordersearch/";

    @UrlUpdate(KEY_S_GWA)
    public static String GWA = "http://" + MultiSdkManager.APP_HOST + "/";

    @UrlUpdate(KEY_S_KF)
    public static String KEFU = "http://" + MultiSdkManager.APP_HOST + "/service/";

    @UrlUpdate(KEY_S_OSL)
    public static String OSL = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/osl/";

    @UrlUpdate(KEY_S_ICARD)
    public static String ICARD = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/icard/";

    @UrlUpdate(KEY_S_BBS)
    public static String BBS = "http://bbs." + MultiSdkManager.APP_HOST + "/";

    @UrlUpdate(KEY_S_SHOP)
    public static String SHOP = "http://" + MultiSdkManager.APP_HOST + "/shop/";

    @UrlUpdate(KEY_S_IWT)
    public static String IWT = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/iwt/";

    @UrlUpdate(KEY_S_SPRO)
    public static String SPRO = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/spro/";

    @UrlUpdate(KEY_S_CPWD)
    public static String CPWD = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/cpwd/";

    @UrlUpdate(KEY_S_PFP)
    public static String PFP = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/pfp/";

    @UrlUpdate(KEY_S_MFP)
    public static String MFP = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/mfp/";

    @UrlUpdate(KEY_S_SPV)
    public static String SPV = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/spv/";

    @UrlUpdate(KEY_S_BP)
    public static String BP = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/bp/";

    @UrlUpdate(KEY_S_BM)
    public static String BM = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/bm/";

    @UrlUpdate("mscode")
    public static String MSCODE = "http://s-api." + MultiSdkManager.APP_HOST + "/mobile/scode/";

    @UrlUpdate("mreg")
    public static String MREG = "http://s-api." + MultiSdkManager.APP_HOST + "/mobile/reg/";

    @UrlUpdate("mreg_res")
    public static String MREG_RES = "http://s-api." + MultiSdkManager.APP_HOST + "/mobile/reg_res/";

    @UrlUpdate("uagree")
    public static String USER_AGREE = "http://" + MultiSdkManager.APP_HOST + "/sdk-wrap/iframe.html?sversion=3.1.0&isrc=http://" + MultiSdkManager.APP_HOST + "/uagree/37.html";

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(MultiSdkManager.APP_HOST);
        sb.append("/sdkv1/service/retrieval/psw/index");
        FORGET_PWD = sb.toString();
        URL_DATA_TRACK = "https://track." + MultiSdkManager.APP_HOST + "/api/event/";
        AUTO_SET_ACCOUNT = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/autoassign";
        OTHER_LOGIN = "http://s-api." + MultiSdkManager.APP_HOST + "/oauth/login/";
        URL_REPORT_DEV_DURATION = "http://s-api." + MultiSdkManager.APP_HOST + "/go/sdk/reportDevDuration";
        URL_REPORT_USER_DURATION = "http://s-api." + MultiSdkManager.APP_HOST + "/go/sdk/reportUserDuration";
        URL_POPUPS_ORDER = "http://m-api-secure." + MultiSdkManager.APP_HOST + "/go/sdk/popups/order";
        POP_USER_URL = "";
        POP_CARD_URL = "";
        POP_BBS_URL = "";
        POP_GL_URL = "";
        POP_HELP_URL = "";
        POP_CHANGEACCOUNT = "";
        urls.put("login", LOGIN);
        urls.put("reg", REG);
        urls.put(KEY_S_FREG, REG_FAST);
        urls.put("pay", PAY);
        urls.put(KEY_S_IMSG, IMSG);
        urls.put(KEY_S_GWI, GWI);
        urls.put(KEY_S_ART, ART);
        urls.put(KEY_S_CARD, CARD);
        urls.put(KEY_S_GCARD, GCARD);
        urls.put(KEY_S_PUSH, PUSH);
        urls.put(KEY_S_GWA, GWA);
        urls.put(KEY_S_KF, KEFU);
        urls.put(KEY_S_OSL, OSL);
        urls.put(KEY_S_ICARD, ICARD);
        urls.put(KEY_S_BBS, BBS);
        urls.put(KEY_S_SHOP, SHOP);
        urls.put(KEY_S_IWT, IWT);
        urls.put(KEY_S_SPRO, SPRO);
        urls.put(KEY_S_CPWD, CPWD);
        urls.put(KEY_S_PFP, PFP);
        urls.put(KEY_S_MFP, MFP);
        urls.put(KEY_S_SPV, SPV);
        urls.put(KEY_S_BP, BP);
        urls.put(KEY_S_BM, BM);
        urls.put("mscode", MSCODE);
        urls.put("mreg", MREG);
        urls.put("mreg_res", MREG_RES);
        urls.put("pay_check", PAY_CHECK);
        urls.put("uagree", USER_AGREE);
        urls.put("uurl", POP_USER_URL);
        urls.put("curl", POP_CARD_URL);
        urls.put("burl", POP_BBS_URL);
        urls.put("gurl", POP_GL_URL);
        urls.put("surl", POP_HELP_URL);
        urls.put("switch", POP_CHANGEACCOUNT);
        urls.put("resetPwd", FORGET_PWD);
    }

    public static void refreshUrls() {
        INIT = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/active/";
        LOGIN = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/login/";
        REG = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/reg/";
        REG_FAST = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/freg/";
        PAY = "http://" + MultiSdkManager.APP_HOST + "/sdk/pay/";
        PAY_CHECK = "http://spay-api." + MultiSdkManager.APP_HOST + "/sdk/ordersearch/";
        IMSG = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/imsg/";
        GWI = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/gwi/";
        ART = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/art/";
        CARD = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/card/";
        GCARD = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/gcard/";
        PUSH = "http://push-api." + MultiSdkManager.APP_HOST + "/push/";
        GWA = "http://" + MultiSdkManager.APP_HOST + "/";
        KEFU = "http://" + MultiSdkManager.APP_HOST + "/service/";
        OSL = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/osl/";
        ICARD = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/icard/";
        BBS = "http://bbs." + MultiSdkManager.APP_HOST + "/";
        SHOP = "http://" + MultiSdkManager.APP_HOST + "/shop/";
        IWT = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/iwt/";
        SPRO = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/spro/";
        CPWD = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/cpwd/";
        PFP = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/pfp/";
        MFP = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/mfp/";
        SPV = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/spv/";
        BP = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/bp/";
        BM = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/bm/";
        MSCODE = "http://s-api." + MultiSdkManager.APP_HOST + "/mobile/scode/";
        MREG = "http://s-api." + MultiSdkManager.APP_HOST + "/mobile/reg/";
        MREG_RES = "http://s-api." + MultiSdkManager.APP_HOST + "/mobile/reg_res/";
        USER_AGREE = "http://" + MultiSdkManager.APP_HOST + "/sdk-wrap/iframe.html?sversion=3.1.0&isrc=http://" + MultiSdkManager.APP_HOST + "/uagree/37.html";
        StringBuilder sb = new StringBuilder();
        sb.append("https://track.");
        sb.append(MultiSdkManager.APP_HOST);
        sb.append("/api/event/");
        URL_DATA_TRACK = sb.toString();
        FORGET_PWD = "http://" + MultiSdkManager.APP_HOST + "/mt/user/account/forgetpassword/index";
        AUTO_SET_ACCOUNT = "http://s-api." + MultiSdkManager.APP_HOST + "/sdk/autoassign";
        OTHER_LOGIN = "http://s-api." + MultiSdkManager.APP_HOST + "/oauth/login/";
        URL_REPORT_DEV_DURATION = "http://s-api." + MultiSdkManager.APP_HOST + "/go/sdk/reportDevDuration";
        URL_REPORT_USER_DURATION = "http://s-api." + MultiSdkManager.APP_HOST + "/go/sdk/reportUserDuration";
    }

    public static void setUrl_POP_HELP_URL(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SQ_URL_PREFS, 0).edit();
        editorEdit.putString(SQ_URL_POP_HELP_URL, str);
        editorEdit.commit();
    }

    public static String getUrl_POP_HELP_URL(Context context) {
        return context.getSharedPreferences(SQ_URL_PREFS, 0).getString(SQ_URL_POP_HELP_URL, "");
    }
}
