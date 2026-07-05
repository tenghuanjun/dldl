package com.sq.tool.network;

import android.app.Activity;
import com.sdk.sq.net.RequestBuilder;
import com.sq.tool.network.SqRequest;
import com.sqwan.common.net.risk.OnRetryCallback;
import com.sqwan.common.net.risk.RiskWebActivity;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SQContextWrapper;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RiskInterceptor implements SqRequest.ResponseStateInterceptor {
    public static final int RISK_WEB_STATE_CODE = 42000;

    @Override // com.sq.tool.network.SqRequest.ResponseStateInterceptor
    public boolean onInterceptResponseState(final RequestBuilder requestBuilder, final int i, final int i2, final String str, final String str2, final SqHttpCallback<?> sqHttpCallback) {
        int method;
        Activity activity;
        if (i2 == 42000 && str2 != null && !str2.isEmpty() && (((method = requestBuilder.getMethod()) == 0 || method == 1) && (activity = SQContextWrapper.getActivity()) != null && !activity.isFinishing() && !activity.isDestroyed())) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(FunctionRouter.KEY_DATA, new JSONObject(str2));
                RiskWebActivity.startActivity(activity, jSONObject, new OnRetryCallback() { // from class: com.sq.tool.network.RiskInterceptor.1
                    @Override // com.sqwan.common.net.risk.OnRetryCallback
                    public void onRetry() {
                        try {
                            LogUtil.w("执行统一弹窗重试逻辑");
                            SqHttpClient.getInstance().enqueue(requestBuilder.build());
                        } catch (Exception e) {
                            LogUtil.e("执行统一弹窗重试逻辑异常", e);
                        }
                    }

                    @Override // com.sqwan.common.net.risk.OnRetryCallback
                    public void onNotRetry() {
                        try {
                            if (sqHttpCallback != null) {
                                sqHttpCallback.onResponseStateError(i, i2, str, str2);
                            }
                        } catch (Exception e) {
                            LogUtil.e("执行统一弹窗不重试逻辑异常", e);
                        }
                    }
                });
                return true;
            } catch (Exception e) {
                LogUtil.e("执行统一弹窗拦截逻辑失败, 不拦截", e);
            }
        }
        return false;
    }
}
