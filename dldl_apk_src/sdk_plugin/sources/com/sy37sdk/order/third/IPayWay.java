package com.sy37sdk.order.third;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.sqwan.order.base.PayWay;
import com.sy37sdk.order.PayOrderModel;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IPayWay {
    public static final String TAG = "【Pay Way】";

    public interface PayWayCallback {
        void onCancel(PayWay payWay, PayOrderModel payOrderModel);

        void onFailed(PayWay payWay, PayOrderModel payOrderModel, int i, String str);

        void onSuccess(PayWay payWay, PayOrderModel payOrderModel);
    }

    PayWay getName();

    void init(Context context);

    void onActivityResult(Activity activity, int i, int i2, Intent intent);

    void onPause(Activity activity);

    void onResume(Activity activity);

    void onStart(Activity activity);

    void onStop(Activity activity);

    void pay(Activity activity, PayOrderModel payOrderModel, Bundle bundle, PayWayCallback payWayCallback);

    public static class UIPayWayCallback implements PayWayCallback {
        private static final Handler sHandler = new Handler(Looper.getMainLooper());
        final PayWayCallback callback;

        public static PayWayCallback wrap(PayWayCallback payWayCallback) {
            return payWayCallback instanceof UIPayWayCallback ? payWayCallback : new UIPayWayCallback(payWayCallback);
        }

        public UIPayWayCallback(PayWayCallback payWayCallback) {
            this.callback = payWayCallback;
        }

        @Override // com.sy37sdk.order.third.IPayWay.PayWayCallback
        public void onSuccess(final PayWay payWay, final PayOrderModel payOrderModel) {
            if (this.callback != null) {
                if (isMainThread()) {
                    this.callback.onSuccess(payWay, payOrderModel);
                } else {
                    sHandler.post(new Runnable() { // from class: com.sy37sdk.order.third.-$$Lambda$IPayWay$UIPayWayCallback$7hApeg15_5dzqXVLHNy-RaSjIhA
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onSuccess$0$IPayWay$UIPayWayCallback(payWay, payOrderModel);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onSuccess$0$IPayWay$UIPayWayCallback(PayWay payWay, PayOrderModel payOrderModel) {
            this.callback.onSuccess(payWay, payOrderModel);
        }

        @Override // com.sy37sdk.order.third.IPayWay.PayWayCallback
        public void onCancel(final PayWay payWay, final PayOrderModel payOrderModel) {
            if (this.callback != null) {
                if (isMainThread()) {
                    this.callback.onCancel(payWay, payOrderModel);
                } else {
                    sHandler.post(new Runnable() { // from class: com.sy37sdk.order.third.-$$Lambda$IPayWay$UIPayWayCallback$tPysx8cFGH6tiphcGwu5AiCAfUc
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onCancel$1$IPayWay$UIPayWayCallback(payWay, payOrderModel);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onCancel$1$IPayWay$UIPayWayCallback(PayWay payWay, PayOrderModel payOrderModel) {
            this.callback.onCancel(payWay, payOrderModel);
        }

        @Override // com.sy37sdk.order.third.IPayWay.PayWayCallback
        public void onFailed(final PayWay payWay, final PayOrderModel payOrderModel, final int i, final String str) {
            if (this.callback != null) {
                if (isMainThread()) {
                    this.callback.onFailed(payWay, payOrderModel, i, str);
                } else {
                    sHandler.post(new Runnable() { // from class: com.sy37sdk.order.third.-$$Lambda$IPayWay$UIPayWayCallback$hJ1REqX3A88x1f31Q5wMNHB4IuU
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onFailed$2$IPayWay$UIPayWayCallback(payWay, payOrderModel, i, str);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onFailed$2$IPayWay$UIPayWayCallback(PayWay payWay, PayOrderModel payOrderModel, int i, String str) {
            this.callback.onFailed(payWay, payOrderModel, i, str);
        }

        private static boolean isMainThread() {
            return Looper.getMainLooper() == Looper.myLooper();
        }
    }
}
