package com.sy37sdk.order.third;

import android.app.Activity;
import android.os.Bundle;
import com.sy37sdk.order.PayOrderModel;
import com.sy37sdk.order.third.IPayWay;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IWebPayWay extends IPayWay {
    boolean interceptorWithUrl(Activity activity, PayOrderModel payOrderModel, String str, Bundle bundle, IPayWay.PayWayCallback payWayCallback);
}
