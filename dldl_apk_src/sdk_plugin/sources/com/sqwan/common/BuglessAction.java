package com.sqwan.common;

import android.content.Context;
import android.util.Log;
import com.sq.diagnostic.assistant.DiagnosticAssistant;
import com.sq.sdk.tool.util.SqLogUtil;
import com.sq.tools.manager.SensitiveInfoManager;
import com.sqwan.bugless.core.Bugless;
import com.sqwan.bugless.model.AppExtension;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.msdk.api.SQAppConfig;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BuglessAction {
    public static final int COMMON_ERROR = 999;
    public static final int FAKE_ACTIVE = 28;
    public static final int LOGIN_USERNAME_EMPTY = 24;
    public static final int M_INIT = 2;
    public static final int M_ORDER = 4;
    public static final int M_TOKEN_VERIFY = 3;
    public static final int ORDER_GET_COUPON = 22;
    public static final int ORDER_GET_PWAY = 20;
    public static final int ORDER_GET_WALLET = 21;
    public static final int PARSE_CONFIG_ERROR = 19;
    public static final int PAY_DIALOG_SHOW_FAIL = 102;
    public static final int PAY_ERROR = 124;
    public static final int PAY_NATIVE_PAGE_ERROR = 106;
    public static final int PAY_WEB_PAGE_ERROR = 103;
    public static final int S_AUTO_REG = 10;
    public static final int S_INIT = 5;
    public static final int S_LOGIN = 11;
    public static final int S_ORDER = 23;
    public static final int S_PAY_FAIL = 13;
    private static final String TAG = "【BUGLESS】";
    public static final int USER_AUTH_POLICY_DES_DIALOG_ERROR = 101;

    private static String getMsg(int i) {
        if (i == 2) {
            return "m层初始化解析过程-异常";
        }
        if (i == 3) {
            return "登录验证token过程出错";
        }
        if (i == 4) {
            return "m层下单失败";
        }
        if (i == 5) {
            return "s层初始化解析过程-异常";
        }
        if (i == 10) {
            return "自动生成账号失败";
        }
        if (i == 11) {
            return "登录失败";
        }
        if (i == 13) {
            return "调用支付返回了失败";
        }
        if (i == 124) {
            return "应用内购买异常";
        }
        switch (i) {
            case 19:
                return "登录成功之后解析配置出错";
            case 20:
                return "查询支付方式失败";
            case 21:
                return "查询钱包余额失败";
            case 22:
                return "查询代金券失败";
            case 23:
                return "s层下单失败";
            default:
                return "未知类型";
        }
    }

    public static void init(Context context) {
        SqLogUtil.d("Bugless url=http://bugless.shan-yu-tech.com/api/bugless/");
        Bugless.getInstance().setUrl("http://bugless.shan-yu-tech.com/api/bugless/");
        Bugless.getInstance().init(context, new BuglessHttpClient());
    }

    public static void setAppExtension(SQAppConfig sQAppConfig) {
        if (sQAppConfig == null) {
            Log.e("sqsdk", "【BUGLESS】config为空, 无法设置bugless参数");
            return;
        }
        AppExtension appExtension = new AppExtension();
        appExtension.setSversion(VersionUtil.sdkVersion);
        appExtension.setRefer(sQAppConfig.getRefer());
        appExtension.setGid(sQAppConfig.getGameid());
        appExtension.setPid(sQAppConfig.getPartner());
        Bugless.getInstance().setAppExtension(appExtension);
    }

    public static void reportCatchException(Exception exc, String str, int i) {
        reportCatchException(exc, getMsg(i), str, i);
    }

    public static void reportCatchException(Throwable th, String str, String str2, int i) {
        Log.d("sqsdk", "【BUGLESS】actionType：" + i + ", businessMsg：" + str + ", businessData：" + str2, th);
        if ((SQContextWrapper.getApplicationContext().getApplicationInfo().flags & 2) != 0) {
            SqLogUtil.w("【BUGLESS】debug版本, 不上报埋点数据, 直接认为上报成功 " + str);
            return;
        }
        Bugless.getInstance().reportCatchedExcaption(th, str, str2, i, SensitiveInfoManager.getInstance().isAuthCheck());
        DiagnosticAssistant.getInstance().recordBuglessActionType(th, str, str2, i);
    }
}
