package com.sy37sdk.order.third;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.util.SQContextWrapper;
import com.sy37sdk.order.OrderRequestManager;
import com.sy37sdk.order.OrderTrackManager;
import com.sy37sdk.order.PayOrderModel;
import com.sy37sdk.order.web.H5PayReporter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CheckOrderManager {
    private static final int RETRY_CHECK_DELAY = 60000;
    private static final String TAG = "【Pay Check】";
    private static volatile CheckOrderManager sInstance;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private OrderRequestManager mRequestManager;

    public interface CheckOrderCallback {
        public static final int ERROR_CANCEL = -333;
        public static final int ERROR_CONTEXT_NULL = -31;
        public static final int ERROR_PARAM = -32;

        void onFailure(PayOrderModel payOrderModel, String str, int i, String str2);

        void onSuccess(PayOrderModel payOrderModel, String str);
    }

    public static CheckOrderManager getInstance() {
        if (sInstance == null) {
            synchronized (CheckOrderManager.class) {
                if (sInstance == null) {
                    sInstance = new CheckOrderManager();
                }
            }
        }
        return sInstance;
    }

    private CheckOrderManager() {
    }

    private OrderRequestManager rm() {
        OrderRequestManager orderRequestManager = this.mRequestManager;
        if (orderRequestManager != null) {
            return orderRequestManager;
        }
        Context applicationContext = SQContextWrapper.getApplicationContext();
        if (applicationContext != null) {
            this.mRequestManager = new OrderRequestManager(applicationContext);
        }
        return this.mRequestManager;
    }

    public void check(PayOrderModel payOrderModel, String str, String str2, CheckOrderCallback checkOrderCallback) {
        check(payOrderModel, str, str2, true, checkOrderCallback);
    }

    public void check(final PayOrderModel payOrderModel, final String str, final String str2, final boolean z, final CheckOrderCallback checkOrderCallback) {
        OrderRequestManager orderRequestManagerRm = rm();
        if (orderRequestManagerRm == null) {
            SQLog.e("【Pay Check】rm异常, 无法查询支付状态, moid=" + payOrderModel.getMoid() + ", " + str + "(" + str2 + ")");
            if (checkOrderCallback != null) {
                checkOrderCallback.onFailure(payOrderModel, str, -31, "无法查询支付状态");
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            SQLog.w("【Pay Check】无法查询支付状态, moid=" + payOrderModel.getMoid() + ", " + str + "(" + str2 + ")");
            if (checkOrderCallback != null) {
                checkOrderCallback.onFailure(payOrderModel, str, -32, "无法查询支付状态");
                return;
            }
            return;
        }
        SQLog.d("【Pay Check】查询支付状态, moid=" + payOrderModel.getMoid() + ", " + str + "(" + str2 + ")");
        orderRequestManagerRm.checkPay(str, str2, new SqHttpCallback<Void>() { // from class: com.sy37sdk.order.third.CheckOrderManager.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(Void r3) {
                SQLog.i(CheckOrderManager.TAG + payOrderModel.getMoid() + "已支付成功");
                CheckOrderCallback checkOrderCallback2 = checkOrderCallback;
                if (checkOrderCallback2 != null) {
                    checkOrderCallback2.onSuccess(payOrderModel, str);
                }
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str3, String str4) {
                SQLog.w(CheckOrderManager.TAG + payOrderModel.getMoid() + "未支付成功, code=" + i2 + ", msg=" + str3);
                if (z) {
                    CheckOrderManager.this.retryCheckAfterDelay(payOrderModel, str, str2);
                }
                if (i2 == -1) {
                    CheckOrderCallback checkOrderCallback2 = checkOrderCallback;
                    if (checkOrderCallback2 != null) {
                        checkOrderCallback2.onFailure(payOrderModel, str, CheckOrderCallback.ERROR_CANCEL, "订单未支付");
                        return;
                    }
                    return;
                }
                CheckOrderCallback checkOrderCallback3 = checkOrderCallback;
                if (checkOrderCallback3 != null) {
                    checkOrderCallback3.onFailure(payOrderModel, str, i2, str3);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str3, VolleyError volleyError) {
                SQLog.w(CheckOrderManager.TAG + payOrderModel.getMoid() + "查询失败, code=" + i + ", msg=" + str3);
                CheckOrderCallback checkOrderCallback2 = checkOrderCallback;
                if (checkOrderCallback2 != null) {
                    checkOrderCallback2.onFailure(payOrderModel, str, i, str3);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retryCheckAfterDelay(final PayOrderModel payOrderModel, final String str, final String str2) {
        SQLog.d("【Pay Check】等待重试查询, moid=" + payOrderModel.getMoid() + ", delay: 60000");
        this.mHandler.postDelayed(new Runnable() { // from class: com.sy37sdk.order.third.-$$Lambda$CheckOrderManager$jGJBgCzSAZ-fgdK_OvNENm4Avm4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$retryCheckAfterDelay$0$CheckOrderManager(payOrderModel, str, str2);
            }
        }, 60000L);
    }

    public /* synthetic */ void lambda$retryCheckAfterDelay$0$CheckOrderManager(final PayOrderModel payOrderModel, final String str, final String str2) {
        SQLog.w("【Pay Check】重试查询支付状态, moid=" + payOrderModel.getMoid() + ", " + str + "(" + str2 + ")");
        OrderRequestManager orderRequestManagerRm = rm();
        if (orderRequestManagerRm == null) {
            SQLog.e("【Pay Check】rm异常, 无法查询支付状态, moid=" + payOrderModel.getMoid() + ", " + str + "(" + str2 + ")");
            return;
        }
        orderRequestManagerRm.checkPay(str, str2, new SqHttpCallback<Void>() { // from class: com.sy37sdk.order.third.CheckOrderManager.2
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(Void r3) {
                SQLog.i(CheckOrderManager.TAG + payOrderModel.getMoid() + "支付成功(重试), 补充上报媒体");
                H5PayReporter.trackRetryCheckSuccess(payOrderModel, str, str2);
                OrderTrackManager.reportToMedia(payOrderModel.getPayInfoModel(), payOrderModel.getMoid(), true);
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str3, String str4) {
                SQLog.w(CheckOrderManager.TAG + payOrderModel.getMoid() + "未支付成功(重试), code=" + i2 + ", msg=" + str3);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str3, VolleyError volleyError) {
                SQLog.w(CheckOrderManager.TAG + payOrderModel.getMoid() + "查询失败(重试), code=" + i + ", msg=" + str3);
            }
        });
    }
}
