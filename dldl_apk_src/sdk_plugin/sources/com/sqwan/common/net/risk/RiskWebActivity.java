package com.sqwan.common.net.risk;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.plugin.standard.BaseActivity;
import com.sqwan.base.EventDispatcher;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.net.bean.WebDialogBean;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.PermissionHelper;
import com.sqwan.common.webview.SQWebViewDialog;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RiskWebActivity extends BaseActivity {
    public static final String INTENT_KEY_IN_CALLBACK_KEY = "callback_key";
    public static final String INTENT_KEY_IN_SCREEN_ORIENTATION = "screenOrientation";
    public static final String INTENT_KEY_IN_URL = "url";
    private static final Map<String, OnRetryCallback> RETRY_CALLBACK_MAP = new HashMap();
    private String mCallbackKey;

    /* JADX WARN: Multi-variable type inference failed */
    public static void startActivity(Activity activity, JSONObject jSONObject, OnRetryCallback onRetryCallback) throws Exception {
        Class cls = RiskWebActivity.class;
        String string = jSONObject.optJSONObject(FunctionRouter.KEY_DATA).getJSONObject("webview").getString("pop_up_url");
        if (TextUtils.isEmpty(string)) {
            LogUtil.i("统一弹窗的 url 为空");
            if (onRetryCallback != null) {
                onRetryCallback.onNotRetry();
                return;
            }
            return;
        }
        LogUtil.i("统一弹窗的 url：" + string);
        WebDialogBean webDialogBean = WebDialogBean.parseWebDialogBean(string);
        if (TextUtils.isEmpty(string)) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("url", string);
        boolean zIsSupportPlugin = isSupportPlugin();
        int screenOrientation = webDialogBean.getScreenOrientation();
        if (screenOrientation == 0) {
            if (!zIsSupportPlugin) {
                cls = RiskWebLandscapeActivity.class;
            }
            intent.setClass(activity, cls);
            intent.putExtra(INTENT_KEY_IN_SCREEN_ORIENTATION, "landscape");
        } else if (screenOrientation == 1) {
            if (!zIsSupportPlugin) {
                cls = RiskWebPortraitActivity.class;
            }
            intent.setClass(activity, cls);
            intent.putExtra(INTENT_KEY_IN_SCREEN_ORIENTATION, "portrait");
        } else {
            if (!zIsSupportPlugin) {
                cls = RiskWebBehindActivity.class;
            }
            intent.setClass(activity, cls);
            intent.putExtra(INTENT_KEY_IN_SCREEN_ORIENTATION, "behind");
        }
        try {
            long jUptimeMillis = SystemClock.uptimeMillis();
            addRetryCallback(String.valueOf(jUptimeMillis), onRetryCallback);
            intent.putExtra(INTENT_KEY_IN_CALLBACK_KEY, String.valueOf(jUptimeMillis));
            activity.startActivity(intent);
            LogUtil.i("通用弹窗界面跳转成功");
        } catch (Exception e) {
            LogUtil.e("通用弹窗界面跳转失败", e);
            if (onRetryCallback != null) {
                onRetryCallback.onNotRetry();
            }
        }
    }

    private static void showRiskWebDialog(Context context, final WebDialogBean webDialogBean, String str, final OnRetryCallback onRetryCallback) throws Exception {
        final SQWebViewDialog sQWebViewDialog = new SQWebViewDialog(context);
        sQWebViewDialog.setUrl(AppUtils.constructWebUrlParam(context, str));
        sQWebViewDialog.setCancelable(!webDialogBean.isFocus());
        sQWebViewDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sqwan.common.net.risk.-$$Lambda$RiskWebActivity$lC_6lNFqcgO7Uwm8_mEMNOmTs28
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                RiskWebActivity.lambda$showRiskWebDialog$0(sQWebViewDialog, onRetryCallback, webDialogBean, dialogInterface);
            }
        });
        sQWebViewDialog.show();
        LogUtil.i("通用弹窗显示了：" + str);
        HashMap map = new HashMap();
        map.put("web_url", str);
        map.put("web_path", webDialogBean.getWebPath());
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.popup_dialog_show, map);
    }

    static /* synthetic */ void lambda$showRiskWebDialog$0(SQWebViewDialog sQWebViewDialog, OnRetryCallback onRetryCallback, WebDialogBean webDialogBean, DialogInterface dialogInterface) {
        LogUtil.i("通用弹窗关闭，isUnionCloseCalled = " + sQWebViewDialog.isUnionCloseCalled() + ", isNeedUnionCloseRetry = " + sQWebViewDialog.isNeedUnionCloseRetry());
        if (onRetryCallback == null) {
            return;
        }
        if (sQWebViewDialog.isUnionCloseCalled()) {
            if (sQWebViewDialog.isNeedUnionCloseRetry()) {
                onRetryCallback.onRetry();
                return;
            } else {
                onRetryCallback.onNotRetry();
                return;
            }
        }
        if (webDialogBean.isRetry()) {
            onRetryCallback.onRetry();
        } else {
            onRetryCallback.onNotRetry();
        }
    }

    private static boolean isSupportPlugin() {
        try {
            Class<?> cls = Class.forName("com.sqwan.msdk.SQwanCore");
            boolean zBooleanValue = ((Boolean) cls.getMethod("isSupportPlugin", new Class[0]).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), new Object[0])).booleanValue();
            LogUtil.i("isSupportPlugin() 返回值：" + zBooleanValue);
            return zBooleanValue;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.e("反射 isSupportPlugin 方法失败", e);
            return false;
        }
    }

    private static synchronized void addRetryCallback(String str, OnRetryCallback onRetryCallback) {
        RETRY_CALLBACK_MAP.put(str, onRetryCallback);
    }

    private static synchronized OnRetryCallback getRetryCallback(String str) {
        return RETRY_CALLBACK_MAP.get(str);
    }

    private static synchronized void removeRetryCallback(String str) {
        RETRY_CALLBACK_MAP.remove(str);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCallbackKey = getIntent().getStringExtra(INTENT_KEY_IN_CALLBACK_KEY);
        String stringExtra = getIntent().getStringExtra("url");
        LogUtil.i("通用弹窗关闭 url 地址：" + stringExtra);
        if (TextUtils.isEmpty(stringExtra)) {
            dispatchTryResult(false);
            LogUtil.i("通用弹窗关闭 url 地址为空，直接关闭弹窗");
            return;
        }
        try {
            showRiskWebDialog(getContext(), WebDialogBean.parseWebDialogBean(stringExtra), stringExtra, new OnRetryCallback() { // from class: com.sqwan.common.net.risk.RiskWebActivity.1
                @Override // com.sqwan.common.net.risk.OnRetryCallback
                public void onRetry() {
                    RiskWebActivity.this.dispatchTryResult(true);
                }

                @Override // com.sqwan.common.net.risk.OnRetryCallback
                public void onNotRetry() {
                    RiskWebActivity.this.dispatchTryResult(false);
                }
            });
        } catch (Exception e) {
            LogUtil.e("弹出统一弹窗失败", e);
            dispatchTryResult(false);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        EventDispatcher.getInstance().dispatcherActivityResultListener(new OnActivityResultEvent(i, i2, intent));
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        PermissionHelper.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchTryResult(boolean z) {
        OnRetryCallback retryCallback = getRetryCallback(this.mCallbackKey);
        if (retryCallback == null) {
            finish();
            return;
        }
        if (z) {
            retryCallback.onRetry();
        } else {
            retryCallback.onNotRetry();
        }
        removeRetryCallback(this.mCallbackKey);
        finish();
    }
}
