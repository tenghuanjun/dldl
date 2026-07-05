package com.sy37sdk.order.web;

import android.text.TextUtils;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.order.base.PayInfoModel;
import com.sy37sdk.order.PayOrderModel;
import com.sy37sdk.order.third.ThirdPayManager;
import com.sy37sdk.order.web.PayWebPage;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class H5PayReporter {
    public static void trackPayNetError(String str, PayOrderModel payOrderModel, PayWebPage.OrderExtraInfo orderExtraInfo) {
        HashMap<String, String> mapCreateParams = createParams(payOrderModel);
        appendExtra(mapCreateParams, orderExtraInfo);
        mapCreateParams.put(ThirdPayManager.EXTRA_PAY_URL, str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.PAY_NET_ERROR, mapCreateParams);
    }

    public static void trackPayInit(String str, PayOrderModel payOrderModel, PayWebPage.OrderExtraInfo orderExtraInfo) {
        HashMap<String, String> mapCreateParams = createParams(payOrderModel);
        appendExtra(mapCreateParams, orderExtraInfo);
        mapCreateParams.put(ThirdPayManager.EXTRA_PAY_URL, str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.pay_init, mapCreateParams);
    }

    public static void trackPayClick(PayOrderModel payOrderModel, PayWebPage.OrderExtraInfo orderExtraInfo) {
        report(SqTrackAction2.pay_click, payOrderModel, orderExtraInfo);
    }

    public static void trackPayWechat(PayOrderModel payOrderModel, Map<String, String> map) {
        HashMap<String, String> mapCreateParams = createParams(payOrderModel);
        appendExtra(mapCreateParams, map);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.PAY_H5_WX, mapCreateParams);
    }

    public static void trackPayDouYin(PayOrderModel payOrderModel, Map<String, String> map) {
        HashMap<String, String> mapCreateParams = createParams(payOrderModel);
        appendExtra(mapCreateParams, map);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.PAY_H5_DOU_YIN, mapCreateParams);
    }

    public static void trackPayUnion(PayOrderModel payOrderModel, Map<String, String> map) {
        HashMap<String, String> mapCreateParams = createParams(payOrderModel);
        appendExtra(mapCreateParams, map);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.PAY_H5_UNION, mapCreateParams);
    }

    public static void trackPayAli(PayOrderModel payOrderModel, Map<String, String> map) {
        HashMap<String, String> mapCreateParams = createParams(payOrderModel);
        appendExtra(mapCreateParams, map);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.PAY_H5_ALI, mapCreateParams);
    }

    public static void trackJsPaySuccess(PayOrderModel payOrderModel, PayWebPage.OrderExtraInfo orderExtraInfo) {
        report(SqTrackAction2.PAY_H5_JS_SUCCESS, payOrderModel, orderExtraInfo);
    }

    public static void trackPayClose(String str, PayOrderModel payOrderModel, PayWebPage.OrderExtraInfo orderExtraInfo) {
        HashMap<String, String> mapCreateParams = createParams(payOrderModel);
        appendExtra(mapCreateParams, orderExtraInfo);
        mapCreateParams.put(ThirdPayManager.EXTRA_PAY_URL, str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.pay_close_click, mapCreateParams);
    }

    public static void trackRetryCheckSuccess(PayOrderModel payOrderModel, String str, String str2) {
        HashMap<String, String> mapCreateParams = createParams(payOrderModel);
        mapCreateParams.put("third_order_id", str);
        mapCreateParams.put(SqTrackKey.pay_method_name, str2);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.PAY_H5_RETRY_CHECK_SUCCESS, mapCreateParams);
    }

    private static void report(SqTrackAction2 sqTrackAction2, PayOrderModel payOrderModel, PayWebPage.OrderExtraInfo orderExtraInfo) {
        HashMap<String, String> mapCreateParams = createParams(payOrderModel);
        appendExtra(mapCreateParams, orderExtraInfo);
        SqTrackActionManager2.getInstance().trackAction(sqTrackAction2, mapCreateParams);
    }

    private static HashMap<String, String> createParams(PayOrderModel payOrderModel) {
        HashMap<String, String> map = new HashMap<>();
        if (payOrderModel == null) {
            return map;
        }
        map.put(SqTrackKey.pay_session, payOrderModel.getPaySession());
        map.put(SqTrackKey.order_id, payOrderModel.getMoid());
        map.put(SqTrackKey.pay_channels, "1");
        PayInfoModel payInfoModel = payOrderModel.getPayInfoModel();
        map.put(SqTrackKey.cp_order_id, payInfoModel.getOrderId());
        map.put(SqTrackKey.order_amount, payInfoModel.getMoney() + "");
        return map;
    }

    private static void appendExtra(Map<String, String> map, PayWebPage.OrderExtraInfo orderExtraInfo) {
        if (orderExtraInfo == null) {
            return;
        }
        map.put("pay_amount", orderExtraInfo.payAmount);
        map.put(SqTrackKey.pay_method, String.valueOf(orderExtraInfo.payMethod));
        map.put("pay_version", orderExtraInfo.payVersion);
        map.put(SqTrackKey.is_vouchers, orderExtraInfo.isVouchers);
        String str = orderExtraInfo.vouchersId;
        if (TextUtils.isEmpty(str) || str.equals("[]")) {
            str = "0";
        }
        map.put(SqTrackKey.vouchers_id, str);
    }

    private static void appendExtra(Map<String, String> map, Map<String, String> map2) {
        if (map2 == null || map2.isEmpty()) {
            return;
        }
        map.putAll(map2);
    }
}
