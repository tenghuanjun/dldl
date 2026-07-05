package com.sqwan.common.mod.order;

import android.app.Activity;
import android.os.Bundle;
import com.sqwan.common.mod.IModBase;
import com.sqwan.order.base.IPay;
import com.sqwan.order.base.PayContext;
import com.sqwan.order.base.PayInfoModel;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IOrderMod extends IModBase {
    void pay(Activity activity, PayContext payContext, PayInfoModel payInfoModel, Bundle bundle, IPay.PayCallback payCallback);
}
