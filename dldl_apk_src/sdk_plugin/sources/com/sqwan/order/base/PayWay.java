package com.sqwan.order.base;

import com.sq.tool.logger.SQLog;
import com.sy37sdk.order.nat.trade.NativePayWay;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayWay {
    public final String desc;
    public final int type;
    public static final PayWay UNKNOWN = new PayWay(0, "unknown");
    public static final PayWay WECHAT = new PayWay(1, NativePayWay.PAY_WAY_WECHAT);
    public static final PayWay ALI = new PayWay(2, "alipay");
    public static final PayWay WALLET = new PayWay(3, NativePayWay.PAY_WAY_WALLET);
    public static final PayWay HUA_BEI = new PayWay(4, NativePayWay.PAY_WAY_HUABEI);
    public static final PayWay LABOR = new PayWay(5, NativePayWay.PAY_WAY_LABOR);
    public static final PayWay UNION = new PayWay(8, "union");
    public static final PayWay DOU_YIN_H5 = new PayWay(9, "douyin_h5");
    private static final List<PayWay> VALUES = new ArrayList<PayWay>() { // from class: com.sqwan.order.base.PayWay.1
        {
            add(PayWay.UNKNOWN);
            add(PayWay.WECHAT);
            add(PayWay.ALI);
            add(PayWay.WALLET);
            add(PayWay.HUA_BEI);
            add(PayWay.LABOR);
            add(PayWay.UNION);
            add(PayWay.DOU_YIN_H5);
        }
    };

    public PayWay(int i, String str) {
        this.type = i;
        this.desc = str;
    }

    public String capitalize() {
        char cCharAt;
        char titleCase;
        String lowerCase = this.desc.toLowerCase(Locale.US);
        int length = lowerCase.length();
        if (length == 0 || cCharAt == (titleCase = Character.toTitleCase((cCharAt = lowerCase.charAt(0))))) {
            return lowerCase;
        }
        char[] cArr = new char[length];
        cArr[0] = titleCase;
        lowerCase.getChars(1, length, cArr, 1);
        return String.valueOf(cArr);
    }

    public String getHttpPayWay() {
        return this.desc;
    }

    public static void register(PayWay payWay) {
        if (VALUES.contains(payWay)) {
            SQLog.wt("sqsdk", "重复注册支付类型" + payWay);
            return;
        }
        SQLog.dt("sqsdk", "注册支付类型" + payWay);
        VALUES.add(payWay);
    }

    public static Collection<PayWay> values() {
        return VALUES;
    }

    public static PayWay get(String str) {
        if (str != null) {
            for (PayWay payWay : values()) {
                if (payWay.desc.equalsIgnoreCase(str)) {
                    return payWay;
                }
            }
        }
        return UNKNOWN;
    }

    public static PayWay get(int i) {
        for (PayWay payWay : values()) {
            if (payWay.type == i) {
                return payWay;
            }
        }
        return UNKNOWN;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PayWay payWay = (PayWay) obj;
        if (this.type != payWay.type) {
            return false;
        }
        return this.desc.equals(payWay.desc);
    }

    public int hashCode() {
        return (this.type * 31) + this.desc.hashCode();
    }

    public String toString() {
        return this.desc;
    }
}
