package com.sqwan.order.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IPay {

    public interface PayCallback {
        void onCancel(PayContext payContext);

        void onFailed(PayContext payContext, SqPayError sqPayError);

        void onSuccess(PayContext payContext);
    }

    void init(Context context);

    void pay(Activity activity, PayContext payContext, PayInfoModel payInfoModel, Bundle bundle, PayCallback payCallback);

    public static class UIPayCallback implements PayCallback {
        private static final Handler sHandler = new Handler(Looper.getMainLooper());
        final PayCallback callback;

        public static PayCallback wrap(PayCallback payCallback) {
            return payCallback instanceof UIPayCallback ? payCallback : new UIPayCallback(payCallback);
        }

        public UIPayCallback(PayCallback payCallback) {
            this.callback = payCallback;
        }

        @Override // com.sqwan.order.base.IPay.PayCallback
        public void onSuccess(final PayContext payContext) {
            if (this.callback != null) {
                if (isMainThread()) {
                    this.callback.onSuccess(payContext);
                } else {
                    sHandler.post(new Runnable() { // from class: com.sqwan.order.base.-$$Lambda$IPay$UIPayCallback$quJCHsqOJh-0EQn9lvxkNtp2LX0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onSuccess$0$IPay$UIPayCallback(payContext);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onSuccess$0$IPay$UIPayCallback(PayContext payContext) {
            this.callback.onSuccess(payContext);
        }

        @Override // com.sqwan.order.base.IPay.PayCallback
        public void onCancel(final PayContext payContext) {
            if (this.callback != null) {
                if (isMainThread()) {
                    this.callback.onCancel(payContext);
                } else {
                    sHandler.post(new Runnable() { // from class: com.sqwan.order.base.-$$Lambda$IPay$UIPayCallback$g_OA2lXLHV2gE2MaAPptnzTG3oc
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onCancel$1$IPay$UIPayCallback(payContext);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onCancel$1$IPay$UIPayCallback(PayContext payContext) {
            this.callback.onCancel(payContext);
        }

        @Override // com.sqwan.order.base.IPay.PayCallback
        public void onFailed(final PayContext payContext, final SqPayError sqPayError) {
            if (this.callback != null) {
                if (isMainThread()) {
                    this.callback.onFailed(payContext, sqPayError);
                } else {
                    sHandler.post(new Runnable() { // from class: com.sqwan.order.base.-$$Lambda$IPay$UIPayCallback$LqdB-Ol6TJMS0BsSkFYzzkI5DQg
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onFailed$2$IPay$UIPayCallback(payContext, sqPayError);
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onFailed$2$IPay$UIPayCallback(PayContext payContext, SqPayError sqPayError) {
            this.callback.onFailed(payContext, sqPayError);
        }

        private static boolean isMainThread() {
            return Looper.getMainLooper() == Looper.myLooper();
        }
    }
}
