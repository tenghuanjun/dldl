package com.sy37sdk.order.nat.pay;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.sdk.app.H5PayCallback;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.H5PayResultModel;
import com.sqwan.common.util.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class Apay {
    private static final int SDK_PAY_FLAG = 1;
    private static AliCallback callback;
    private static Apay mInstance;
    private Handler resultHandler = new ResultHandler(Looper.getMainLooper());

    public interface AliCallback {
        void onFailure(int i, String str);

        void onSuccess();
    }

    private Apay() {
    }

    public static synchronized Apay getInstance() {
        if (mInstance == null) {
            mInstance = new Apay();
        }
        return mInstance;
    }

    public void pay(final Activity activity, final String str, AliCallback aliCallback, final boolean z) {
        callback = aliCallback;
        new Thread(new Runnable() { // from class: com.sy37sdk.order.nat.pay.Apay.1
            @Override // java.lang.Runnable
            public void run() {
                Map mapPayV2 = new PayTask(activity).payV2(str, z);
                LogUtil.i("apay", mapPayV2.toString());
                Message message = new Message();
                message.what = 1;
                message.obj = mapPayV2;
                Apay.this.resultHandler.sendMessage(message);
            }
        }).start();
    }

    public boolean interceptorWithUrl(Activity activity, String str, boolean z, AliCallback aliCallback) {
        callback = aliCallback;
        return new PayTask(activity).payInterceptorWithUrl(str, z, new H5PayCallback() { // from class: com.sy37sdk.order.nat.pay.Apay.2
            public void onPayResult(H5PayResultModel h5PayResultModel) {
                String resultCode = h5PayResultModel.getResultCode();
                String returnUrl = h5PayResultModel.getReturnUrl();
                HashMap map = new HashMap();
                map.put("resultStatus", resultCode);
                map.put("returnUrl", returnUrl);
                map.put("memo", "取消支付");
                Message message = new Message();
                message.what = 1;
                message.obj = map;
                Apay.this.resultHandler.sendMessage(message);
            }
        });
    }

    static class ResultHandler extends Handler {
        public ResultHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                Result result = new Result(message.obj);
                if (Apay.callback != null) {
                    if (result.getResultStatus().equals(Result.STATUS_CODE_SUCCESS)) {
                        Apay.callback.onSuccess();
                    } else if (!TextUtils.isEmpty(result.getReturnUrl())) {
                        Apay.callback.onFailure(Result.STATUS_CODE_URL, "支付宝支付链接异常，请重试或联系客服【20008】");
                    } else {
                        Apay.callback.onFailure(-1, result.getMemo());
                    }
                }
            }
        }
    }
}
