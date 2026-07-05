package com.sy37sdk.order.third;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.util.CheckClassUtils;
import com.sqwan.order.base.PayWay;
import com.sy37sdk.order.PayOrderModel;
import com.sy37sdk.order.third.IPayWay;
import com.sy37sdk.order.third.ali.AliPayWay;
import com.sy37sdk.order.third.douyin.DouYinPayWay;
import com.sy37sdk.order.third.union.UnionPayWay;
import com.sy37sdk.order.third.wechat.WeChatPayWay;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ThirdPayManager {
    public static final String EXTRA_ORDER_ID = "third_order_id";
    public static final String EXTRA_PAY_URL = "pay_url";
    public static final String EXTRA_REFERER = "referer";
    public static final String EXTRA_TRADE_INFO = "trade_info";
    private static final String TAG = "【Pay Third】";
    private final Map<PayWay, IPayWay> mPayWayMap = new HashMap();

    public ThirdPayManager(WebView webView) {
        put(new WeChatPayWay(webView));
        put(new AliPayWay());
        put(new DouYinPayWay(webView));
        if (isSupportUnion()) {
            put(new UnionPayWay());
        }
    }

    private void put(IPayWay iPayWay) {
        this.mPayWayMap.put(iPayWay.getName(), iPayWay);
    }

    public void init(Context context) {
        SQLog.v("【Pay Third】init " + context);
        Iterator<IPayWay> it = this.mPayWayMap.values().iterator();
        while (it.hasNext()) {
            it.next().init(context);
        }
    }

    public boolean isSupport(PayWay payWay) {
        return this.mPayWayMap.containsKey(payWay);
    }

    public boolean interceptorWithUrl(Activity activity, PayOrderModel payOrderModel, String str, Bundle bundle, IPayWay.PayWayCallback payWayCallback) {
        SQLog.v("【Pay Third】interceptorWithUrl " + str);
        for (IPayWay iPayWay : this.mPayWayMap.values()) {
            if ((iPayWay instanceof IWebPayWay) && ((IWebPayWay) iPayWay).interceptorWithUrl(activity, payOrderModel, str, bundle, payWayCallback)) {
                return true;
            }
        }
        return false;
    }

    public void pay(Activity activity, PayWay payWay, PayOrderModel payOrderModel, Bundle bundle, IPayWay.PayWayCallback payWayCallback) {
        SQLog.d("【Pay Third】触发支付, 类型=" + payWay);
        IPayWay iPayWay = this.mPayWayMap.get(payWay);
        if (iPayWay == null) {
            SQLog.e("【Pay Third】不支持类型" + payWay);
            IPayWay.UIPayWayCallback.wrap(payWayCallback).onFailed(payWay, payOrderModel, -1, "不支持该支付类型(" + payWay.type + ")");
            return;
        }
        iPayWay.pay(activity, payOrderModel, bundle, payWayCallback);
    }

    public void checkPay(PayOrderModel payOrderModel) {
        SQLog.d("【Pay Third】检查支付状态, moid=" + payOrderModel.getMoid());
        for (IPayWay iPayWay : this.mPayWayMap.values()) {
            if (iPayWay instanceof CheckablePayWay) {
                ((CheckablePayWay) iPayWay).check();
            }
        }
    }

    public void onStart(Activity activity) {
        SQLog.v("【Pay Third】onStart " + activity);
        Iterator<IPayWay> it = this.mPayWayMap.values().iterator();
        while (it.hasNext()) {
            it.next().onStart(activity);
        }
    }

    public void onResume(Activity activity) {
        SQLog.v("【Pay Third】onResume " + activity);
        Iterator<IPayWay> it = this.mPayWayMap.values().iterator();
        while (it.hasNext()) {
            it.next().onResume(activity);
        }
    }

    public void onPause(Activity activity) {
        SQLog.v("【Pay Third】onPause " + activity);
        Iterator<IPayWay> it = this.mPayWayMap.values().iterator();
        while (it.hasNext()) {
            it.next().onPause(activity);
        }
    }

    public void onStop(Activity activity) {
        SQLog.v("【Pay Third】onStop " + activity);
        Iterator<IPayWay> it = this.mPayWayMap.values().iterator();
        while (it.hasNext()) {
            it.next().onStop(activity);
        }
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        SQLog.v("【Pay Third】onActivityResult " + activity + ", requestCode=" + i);
        Iterator<IPayWay> it = this.mPayWayMap.values().iterator();
        while (it.hasNext()) {
            it.next().onActivityResult(activity, i, i2, intent);
        }
    }

    private static boolean isSupportUnion() {
        return CheckClassUtils.classExist("com.unionpay.UPPayAssistEx") && CheckClassUtils.classExist("com.unionpay.UPPayWapActivity");
    }
}
