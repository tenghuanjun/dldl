package com.sy37sdk.account.view.base.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.msdk.config.ConfigManager;
import com.sqwan.msdk.config.MultiSdkManager;
import com.sy37sdk.account.view.LoginSkinHelper;
import com.sy37sdk.account.view.base.view.BackTitleView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class BaseSwitchView extends BasePageSwitchView {
    protected BackTitleView backTitleView;
    protected View rootView;

    public abstract String getLayoutResName();

    public abstract String getTitle();

    public abstract void initEvent();

    public abstract void initView();

    public BaseSwitchView(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initRootView();
        initView();
        initEvent();
    }

    @Override // com.sy37sdk.account.view.base.view.BasePageSwitchView, com.sy37sdk.account.view.base.view.IPageSwitchView
    public void onSwitched(int i, int i2, Bundle bundle) {
        super.onSwitched(i, i2, bundle);
        this.backTitleView.showBackView(canGoBack());
    }

    @Override // com.sy37sdk.account.view.base.view.BasePageSwitchView, com.sy37sdk.account.view.base.view.IPageSwitchView
    public void onBackPressed() {
        super.onBackPressed();
        onBack();
    }

    protected void initRootView() {
        if (this.rootView != null) {
            return;
        }
        View viewInflate = LayoutInflater.from(getContext()).inflate(getIdByName(getLayoutResName(), "layout"), (ViewGroup) null);
        this.rootView = viewInflate;
        addView(viewInflate);
        initBackTitleView();
        View viewByName = getViewByName("content_layout");
        if (viewByName != null) {
            viewByName.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.account.view.base.view.BaseSwitchView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.i("点击了内容区域");
                }
            });
        }
    }

    protected void onBack() {
        if (this.loginDialog != null) {
            this.loginDialog.goBack();
        }
    }

    protected <T extends View> T getViewByName(String str) {
        return (T) this.rootView.findViewById(getIdByName(str, SqTrackCommonKey.id));
    }

    private void initBackTitleView() {
        BackTitleView backTitleView = (BackTitleView) this.rootView.findViewById(SqResUtils.getIdByName("common_title_view", SqTrackCommonKey.id, getContext()));
        this.backTitleView = backTitleView;
        if (backTitleView != null) {
            backTitleView.setOnClickTitleViewListener(new BackTitleView.OnClickTitleViewListener() { // from class: com.sy37sdk.account.view.base.view.BaseSwitchView.2
                @Override // com.sy37sdk.account.view.base.view.BackTitleView.OnClickTitleViewListener
                public void clickBack() {
                    BaseSwitchView.this.onBack();
                }

                @Override // com.sy37sdk.account.view.base.view.BackTitleView.OnClickTitleViewListener
                public void clickClose() {
                    BaseSwitchView.this.closeLoginDialog();
                    if (BaseSwitchView.this.loginDialog != null) {
                        BaseSwitchView.this.loginDialog.closeAccountDialog();
                    }
                }
            });
            if (MultiSdkManager.getInstance().isScut3() || ConfigManager.getInstance(getContext()).isSplashSDK() || ConfigManager.getInstance(getContext()).isLessFunction()) {
                this.backTitleView.showTitle();
            } else {
                this.backTitleView.showLogo();
            }
            this.backTitleView.getBackImageView().setImageResource(LoginSkinHelper.getBackIconResId(getContext()));
            this.backTitleView.getCloseImageView().setImageResource(LoginSkinHelper.getCloseIconResId(getContext()));
        }
        this.backTitleView.setTitle(getTitle());
    }

    protected void closeLoginDialog() {
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.closeLoginPage, SqTrackBtn.SqTrackBtnExt.closeLoginPage);
    }

    protected void setTitleView(String str) {
        BackTitleView backTitleView = this.backTitleView;
        if (backTitleView != null) {
            backTitleView.setTitle(str);
        }
    }

    public void showSoftInput(final View view) {
        new Handler().postDelayed(new Runnable() { // from class: com.sy37sdk.account.view.base.view.BaseSwitchView.3
            @Override // java.lang.Runnable
            public void run() {
                BaseSwitchView.this.requestFocus(view);
            }
        }, 300L);
    }

    public void hideSoftInput() {
        StatusBarUtil.hideSystemKeyBoard(getContext(), this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFocus(View view) {
        if (view == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
    }

    public boolean canGoBack() {
        return this.loginDialog.canGoBack();
    }
}
