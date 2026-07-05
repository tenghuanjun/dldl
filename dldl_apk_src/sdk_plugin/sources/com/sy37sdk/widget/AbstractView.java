package com.sy37sdk.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.route.FunctionRouter;
import com.sy37sdk.core.SQResultListener;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public abstract class AbstractView {
    public static final int STATE_RESPONSE_FAUILED = 0;
    public static final int STATE_RESPONSE_SUCCESS = 1;
    public static final int WRAP_CONTENT = -2;
    protected static final Handler mHandler = new Handler();
    public static final int match_parent = -1;
    protected long clickTime = 0;
    protected SQResultListener listener;
    private Activity mActivity;
    private LayoutInflater mInflater;
    protected View mParent;

    protected interface ResponseCallback {
        void error(int i, String str, String str2);

        void success(String str);
    }

    public boolean back() {
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    protected void onDestroy() {
    }

    protected void onFinishInflate() {
    }

    public void onReceive(Context context, Intent intent) {
    }

    protected void onResume() {
    }

    public void onViewIn() {
    }

    public void onViewOut() {
    }

    public AbstractView(Activity activity) {
        this.mActivity = activity;
        this.mInflater = LayoutInflater.from(activity);
        View parent = getParent();
        if (parent == null) {
            throw new NullPointerException("getParent() must return a non-null View.");
        }
        parent.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.mParent = parent;
        onFinishInflate();
    }

    protected View getParent() {
        return new RelativeLayout(getActivity());
    }

    public final View getContentView() {
        return this.mParent;
    }

    public View findViewById(int i) {
        return this.mParent.findViewById(i);
    }

    public View inflate(int i) {
        return this.mInflater.inflate(i, (ViewGroup) null);
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    protected synchronized boolean isQuickClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.clickTime < 400) {
            this.clickTime = jCurrentTimeMillis;
            return true;
        }
        this.clickTime = jCurrentTimeMillis;
        return false;
    }

    protected void handleResponseData(String str, String str2, int i, ResponseCallback responseCallback) throws JSONException {
        JSONObject jSONObject;
        String string = null;
        int i2 = 0;
        try {
            jSONObject = new JSONObject(str);
        } catch (Exception e) {
            e = e;
            jSONObject = null;
        }
        try {
            i2 = jSONObject.getInt("state");
            if (i2 != 1) {
                string = jSONObject.getString("msg");
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            BuglessAction.reportCatchException(e, str, i);
            responseCallback.error(203, "解析异常", str2 + "0xe");
        }
        if (i2 == 1) {
            responseCallback.success(str);
            return;
        }
        responseCallback.error(i2, string, str2);
        if (jSONObject == null || !jSONObject.has(FunctionRouter.KEY_DATA)) {
            return;
        }
        jSONObject.optJSONObject(FunctionRouter.KEY_DATA);
    }
}
