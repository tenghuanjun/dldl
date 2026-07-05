package com.sy37sdk.order;

import com.sqwan.common.annotation.UrlUpdate;
import com.sqwan.msdk.config.MultiSdkManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class OrderUrl {
    public static final String KEY_AVAILABLE_PWAYS = "available_pways";
    public static final String KEY_GET_COUPON = "get_coupon";
    public static final String KEY_ORDER_STATUS = "order_status";
    public static final String KEY_S_PAY = "s_pay";
    public static final String KEY_WALLET_BALANCE = "wallet_balance";
    public static final String KEY_WALLET_PAY = "wallet_pay";

    @UrlUpdate(value = KEY_AVAILABLE_PWAYS, xValue = "x_available_pways")
    public static String AVAILABLE_PAY_WAYS = "http://mpay-api-secure." + MultiSdkManager.APP_HOST + "/sdk/getAvailablePways";

    @UrlUpdate(value = KEY_WALLET_BALANCE, xValue = "x_wallet_balance")
    public static String WALLET_BALANCE = "http://s-api-secure." + MultiSdkManager.APP_HOST + "/sdk/piwt/";

    @UrlUpdate(value = KEY_GET_COUPON, xValue = "x_get_coupon")
    public static String GET_COUPON = "http://spay-api-secure." + MultiSdkManager.APP_HOST + "/coupon/get";

    @UrlUpdate(value = KEY_WALLET_PAY, xValue = "x_wallet_pay")
    public static String WALLET_PAY = "http://spay-api-secure." + MultiSdkManager.APP_HOST + "/sdk/callback/wtpay/";

    @UrlUpdate(value = KEY_S_PAY, xValue = "x_order_s")
    public static String COMMON_PAY = "http://spay-api-secure." + MultiSdkManager.APP_HOST + "/sdk/order";

    @UrlUpdate(value = "order_status", xValue = "x_order_status")
    public static String PAY_CHECK = "http://spay-api-secure." + MultiSdkManager.APP_HOST + "/sdk/ordersearch/";
    public static String H5_PAY = "http://" + MultiSdkManager.APP_HOST + "/sdk/pay/";
}
