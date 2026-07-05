package com.sy37sdk.account.floatview;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.account.floatview.FloatViewUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
class SystemUiCheckUtils {
    private static final SystemUiCheckUtils ourInstance = new SystemUiCheckUtils();
    private List<onSystemUiChangedCallback> listener = new ArrayList();
    String TAG = getClass().getSimpleName();
    boolean hasChanged = false;

    interface onSystemUiChangedCallback {
        void onSystemUiChanged(FloatViewUtils.FloatViewConfig floatViewConfig);
    }

    public void addListener(onSystemUiChangedCallback onsystemuichangedcallback) {
        this.listener.add(onsystemuichangedcallback);
    }

    public void removeListener(onSystemUiChangedCallback onsystemuichangedcallback) {
        this.listener.remove(onsystemuichangedcallback);
    }

    static SystemUiCheckUtils getInstance() {
        return ourInstance;
    }

    private SystemUiCheckUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isUiHideNavigation(View view) {
        return (view.getSystemUiVisibility() & 2) == 2;
    }

    public void init(final Context context) {
        if (context instanceof Activity) {
            final View decorView = ((Activity) context).getWindow().getDecorView();
            final boolean zIsUiHideNavigation = isUiHideNavigation(decorView);
            LogUtil.i(this.TAG, "isUiHideNavigation:" + zIsUiHideNavigation);
            if (zIsUiHideNavigation) {
                return;
            }
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.sy37sdk.account.floatview.SystemUiCheckUtils.1
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    LogUtil.i(SystemUiCheckUtils.this.TAG, "onSystemUiVisibilityChange visibility:" + i);
                    if (zIsUiHideNavigation || SystemUiCheckUtils.this.hasChanged || !SystemUiCheckUtils.this.isUiHideNavigation(decorView)) {
                        return;
                    }
                    SystemUiCheckUtils.this.onSystemUiChanged(new FloatViewUtils.FloatViewConfig(context));
                    SystemUiCheckUtils.this.hasChanged = true;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSystemUiChanged(FloatViewUtils.FloatViewConfig floatViewConfig) {
        for (onSystemUiChangedCallback onsystemuichangedcallback : this.listener) {
            LogUtil.i(this.TAG, "onSystemUiChanged _onSystemUiChangedCallback:" + onsystemuichangedcallback);
            onsystemuichangedcallback.onSystemUiChanged(floatViewConfig);
        }
    }
}
