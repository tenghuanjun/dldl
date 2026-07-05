package com.sy37sdk.account.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.sqwan.common.mvp.BasePresenter;
import com.sy37sdk.account.LoginTractionManager;
import com.sy37sdk.account.view.IAutoLoginDialog;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class BaseAutoLoginPresenter extends BasePresenter<IAutoLoginDialog> implements IAutoLoginPresenter {
    protected static final long AUTO_LOGIN_WAIT_THRESHOLD = 3000;
    protected static final String TAG = "【Login AutoP】";
    protected long autoLoginShowTime;
    protected boolean isAutoLoginShow;

    public BaseAutoLoginPresenter(Context context, IAutoLoginDialog iAutoLoginDialog) {
        super(context, iAutoLoginDialog);
        this.isAutoLoginShow = false;
    }

    @Override // com.sy37sdk.account.presenter.IAutoLoginPresenter
    public void setAutoLoginShow(boolean z) {
        this.isAutoLoginShow = z;
        if (z) {
            this.autoLoginShowTime = System.currentTimeMillis();
        }
    }

    protected void loginSuccess(final Map<String, String> map) {
        if (this.mView == 0 || !this.isAutoLoginShow) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - this.autoLoginShowTime;
        if (jCurrentTimeMillis < AUTO_LOGIN_WAIT_THRESHOLD) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.sy37sdk.account.presenter.BaseAutoLoginPresenter.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!BaseAutoLoginPresenter.this.isAutoLoginShow || BaseAutoLoginPresenter.this.mView == null) {
                        return;
                    }
                    BaseAutoLoginPresenter.this.autoLoginFinish(map);
                }
            }, AUTO_LOGIN_WAIT_THRESHOLD - jCurrentTimeMillis);
        } else {
            autoLoginFinish(map);
        }
    }

    protected void loginFail(int i, String str) {
        if (this.mView != 0) {
            ((IAutoLoginDialog) this.mView).autoLoginFail(i, str);
        }
    }

    public void autoLoginFinish(Map<String, String> map) {
        LoginTractionManager.trackInvoke(map.get(LoginTractionManager.TRACK_ACCOUNT_TYPE), "4");
        LoginTractionManager.track("4", map);
    }
}
