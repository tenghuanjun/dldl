package com.sy37sdk.order.nat.trade;

import android.content.Context;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.util.SDKError;
import com.sy37sdk.order.nat.NatRequestManager;
import com.sy37sdk.order.nat.bean.Coupon;
import com.sy37sdk.order.nat.bean.Order;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class OrderData {
    private static OrderData instance;
    private Context mContext;
    private NatRequestManager requestManager;

    public interface PayCallback<T> {
        void onFailure(int i, String str);

        void onSuccess(T t);
    }

    private OrderData(Context context) {
        this.mContext = context;
        this.requestManager = new NatRequestManager(context);
    }

    public static OrderData getInstance(Context context) {
        if (instance == null) {
            synchronized (OrderData.class) {
                if (instance == null) {
                    instance = new OrderData(context);
                }
            }
        }
        return instance;
    }

    public void getAvailableWays(String str, final PayCallback<String> payCallback) {
        this.requestManager.getAvailablePways(str, new SqHttpCallback<String>() { // from class: com.sy37sdk.order.nat.trade.OrderData.1
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                payCallback.onFailure(i2, str2);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str2) {
                payCallback.onSuccess(str2);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                payCallback.onFailure(i, str2);
            }
        });
    }

    public void getWalletBalance(String str, final PayCallback<Wallet> payCallback) {
        this.requestManager.getWalletBalance(str, new SqHttpCallback<String>() { // from class: com.sy37sdk.order.nat.trade.OrderData.2
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str2, String str3) {
                payCallback.onFailure(i2, str2);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str2) {
                try {
                    payCallback.onSuccess(Wallet.fromJson(str2));
                } catch (JSONException e) {
                    e.printStackTrace();
                    BuglessAction.reportCatchException(e, str2, 21);
                    payCallback.onFailure(SDKError.NET_DATA_PARSE_ERROR.code, SDKError.NET_DATA_PARSE_ERROR.message);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str2, VolleyError volleyError) {
                payCallback.onFailure(i, str2);
            }
        });
    }

    public void getCoupons(String str, float f, String str2, final PayCallback<List<Coupon>> payCallback) {
        this.requestManager.getCoupon(str, f, str2, 1, new SqHttpCallback<String>() { // from class: com.sy37sdk.order.nat.trade.OrderData.3
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str3, String str4) {
                payCallback.onFailure(i2, str3);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str3) {
                try {
                    payCallback.onSuccess(Coupon.fromJson(str3));
                } catch (JSONException e) {
                    e.printStackTrace();
                    BuglessAction.reportCatchException(e, str3, 22);
                    payCallback.onFailure(SDKError.NET_DATA_PARSE_ERROR.code, SDKError.NET_DATA_PARSE_ERROR.message);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str3, VolleyError volleyError) {
                payCallback.onFailure(i, str3);
            }
        });
    }

    public void walletPay(Order order, final PayCallback<Boolean> payCallback) {
        if (order == null) {
            payCallback.onFailure(-1, "订单异常");
        } else {
            this.requestManager.wtPay(order, new SqHttpCallback<Void>() { // from class: com.sy37sdk.order.nat.trade.OrderData.4
                @Override // com.sdk.sq.net.SqRequestCallback
                public void onResponseStateError(int i, int i2, String str, String str2) {
                    payCallback.onFailure(i2, str);
                }

                @Override // com.sq.tool.network.SqHttpCallback
                public void onSuccess(Void r2) {
                    payCallback.onSuccess(true);
                }

                @Override // com.sq.tool.network.SqHttpCallback
                public void onFailure(int i, String str, VolleyError volleyError) {
                    payCallback.onFailure(i, str);
                }
            });
        }
    }

    public void otherPay(float f, String str, String str2, boolean z, String str3, final PayCallback<PayOrder> payCallback) {
        this.requestManager.pay(f, str, str2, z, str3, new SqHttpCallback<String>() { // from class: com.sy37sdk.order.nat.trade.OrderData.5
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str4, String str5) {
                payCallback.onFailure(i2, str4);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str4) {
                try {
                    payCallback.onSuccess(PayOrder.fromJson(str4));
                } catch (JSONException e) {
                    e.printStackTrace();
                    BuglessAction.reportCatchException(e, str4, 23);
                    payCallback.onFailure(SDKError.NET_DATA_PARSE_ERROR.code, SDKError.NET_DATA_PARSE_ERROR.message);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str4, VolleyError volleyError) {
                payCallback.onFailure(i, str4);
            }
        });
    }
}
