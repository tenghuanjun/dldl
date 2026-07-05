package com.sqwan.order.base;

import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.track.SqTrackKey;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ReportDataBuilder {
    public final Map<String, String> map = new HashMap();

    public ReportDataBuilder payInfo(PayInfoModel payInfoModel) {
        if (payInfoModel != null) {
            put(SqTrackKey.order_amount, Float.valueOf(payInfoModel.getMoney()));
            put("order_currency", payInfoModel.getCurrencyName());
            put(SqTrackKey.cp_order_id, payInfoModel.getOrderId());
            put("product_name", payInfoModel.getProductName());
            put("role_id", payInfoModel.getRoleId());
            put("role_name", payInfoModel.getRoleName());
            put("server_id", payInfoModel.getServerId());
            put("server_name", payInfoModel.getServerName());
            put("cp_extend", payInfoModel.getExtend());
        }
        return this;
    }

    public ReportDataBuilder payExtraInfo(PayExtraInfo payExtraInfo) {
        if (payExtraInfo != null) {
            put("pay_amount", payExtraInfo.payAmount);
            put("pay_version", payExtraInfo.payVersion);
            put(SqTrackKey.is_vouchers, payExtraInfo.isVouchers);
            String str = payExtraInfo.vouchersId;
            if (TextUtils.isEmpty(str) || str.equals("[]")) {
                str = "0";
            }
            put(SqTrackKey.vouchers_id, str);
        }
        return this;
    }

    public ReportDataBuilder payCtx(PayContext payContext) {
        if (payContext == null) {
            return this;
        }
        put(SqTrackKey.pay_session, payContext.getSession());
        put("pay_start_action", payContext.getAction());
        put(SqTrackKey.pay_channels, payContext.getPayChannel());
        orderId(payContext.getMoid());
        payInfo(payContext.getPayInfo());
        payExtraInfo(payContext.getPayExtraInfo());
        payWay(payContext.getPayWay());
        return this;
    }

    public ReportDataBuilder orderId(String str) {
        if (str != null) {
            put(SqTrackKey.order_id, str);
        }
        return this;
    }

    public ReportDataBuilder payWay(PayWay payWay) {
        if (payWay != null) {
            put(SqTrackKey.pay_method, String.valueOf(payWay.type));
            put(SqTrackKey.pay_method_name, payWay.desc);
        }
        return this;
    }

    public ReportDataBuilder error(String str) {
        if (!TextUtils.isEmpty(str)) {
            put(SqTrackKey.reason_fail, str);
        }
        return this;
    }

    public ReportDataBuilder error(SqPayError sqPayError) {
        if (sqPayError != null) {
            put(SqTrackKey.fail_code, Integer.valueOf(sqPayError.code));
            put(SqTrackKey.sub_error_code, Integer.valueOf(sqPayError.originCode));
            put(SqTrackKey.reason_fail, sqPayError.msg);
            put(SqTrackKey.sub_error_msg, sqPayError.originMsg);
        }
        return this;
    }

    public ReportDataBuilder timing(String str) {
        return put("timing", str);
    }

    public ReportDataBuilder ext(String str) {
        return put("ext_data", str);
    }

    public ReportDataBuilder put(String str, Object obj) {
        if (obj != null && !TextUtils.isEmpty(str)) {
            try {
                String strValueOf = String.valueOf(obj);
                if (TextUtils.isEmpty(strValueOf)) {
                    return this;
                }
                if (this.map.containsKey(str) && !strValueOf.equals(this.map.get(str))) {
                    SQLog.w("【Pay Report】覆盖上报参数" + str + ", " + this.map.get(str) + " ==> " + strValueOf);
                }
                this.map.put(str, strValueOf);
            } catch (Exception unused) {
            }
        }
        return this;
    }

    public String toString() {
        return new JSONObject(this.map).toString();
    }
}
