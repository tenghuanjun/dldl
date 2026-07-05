package com.sy37sdk.order.nat;

import android.content.Context;
import android.text.TextUtils;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.order.base.PayWay;
import com.sy37sdk.order.OrderRequestManager;
import com.sy37sdk.order.OrderUrl;
import com.sy37sdk.order.nat.bean.Order;
import com.sy37sdk.order.nat.trade.NativePayWay;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class NatRequestManager {
    private final Context mContext;

    public NatRequestManager(Context context) {
        this.mContext = context;
    }

    public void getAvailablePways(String str, SqHttpCallback<String> sqHttpCallback) {
        SqRequest.of(OrderUrl.AVAILABLE_PAY_WAYS).params(OrderRequestManager.generateCommonParamsOfPay(this.mContext, str)).addParam(SqConstants.MOID, str).post(sqHttpCallback, String.class);
    }

    public void getWalletBalance(String str, SqHttpCallback<String> sqHttpCallback) {
        SqRequest.of(OrderUrl.WALLET_BALANCE).params(OrderRequestManager.generateCommonParamsOfPay(this.mContext, str)).addParam(SqConstants.MOID, str).post(sqHttpCallback, String.class);
    }

    public void getCoupon(String str, float f, String str2, int i, SqHttpCallback<String> sqHttpCallback) {
        SqRequest.of(OrderUrl.GET_COUPON).params(OrderRequestManager.generateCommonParamsOfPay(this.mContext, str)).addParam("usestatus", i + "").addParam("money", f + "").addParam(SqConstants.MOID, str).addParam(SqConstants.DSID, str2).post(sqHttpCallback, String.class);
    }

    public void pay(float f, String str, String str2, boolean z, String str3, SqHttpCallback<String> sqHttpCallback) {
        SqRequest sqRequestAddParam = SqRequest.of(OrderUrl.COMMON_PAY).params(OrderRequestManager.generateCommonParamsOfPay(this.mContext, str2)).addParam("money", f + "").addParam(SqConstants.PAYWAY, str).addParam(SqConstants.MOID, str2).addParam("payVersion", "1.0.0");
        if (z) {
            sqRequestAddParam.addParam(NativePayWay.PWAY_KEY_HUABEI, "1");
        }
        if (!TextUtils.isEmpty(str3)) {
            sqRequestAddParam.addParam("couponCode", str3);
        }
        sqRequestAddParam.post(sqHttpCallback, String.class);
    }

    public void wtPay(Order order, SqHttpCallback<Void> sqHttpCallback) {
        String moid = order.getMoid();
        SqRequest.of(OrderUrl.WALLET_PAY).params(OrderRequestManager.generateCommonParamsOfPay(this.mContext, moid)).addParam("money", order.getMoney() + "").addParam(SqConstants.MOID, moid).addParam(SqConstants.DSID, order.getDsid()).addParam(SqConstants.PAYWAY, PayWay.WALLET.getHttpPayWay()).addParam(SqConstants.DOID, order.getDoid()).addParam(SqConstants.DEXT, order.getDext()).addParam(SqConstants.DRID, order.getDrid()).addParam(SqConstants.DRNAME, order.getDrname()).addParam(SqConstants.DRLEVEL, order.getDrlevel() + "").post(sqHttpCallback, Void.class);
    }
}
