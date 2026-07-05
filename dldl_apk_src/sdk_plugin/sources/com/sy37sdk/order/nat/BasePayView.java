package com.sy37sdk.order.nat;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.mvp.ILoadView;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.view.BaseView;
import com.sy37sdk.order.view.PayConfirmDialog;
import com.sy37sdk.order.view.ui.BackPayView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class BasePayView extends BaseView implements IPageSwitchView, ILoadView, IBasePayView {
    private BackPayView backPayView;
    protected INativePayDialog nativePayDialog;
    protected View rootView;

    public abstract String getLayoutResName();

    public abstract String getTitle();

    public abstract void initEvent();

    public abstract void initView();

    protected void leftViewClicked() {
    }

    @Override // com.sy37sdk.order.nat.IPageSwitchView
    public void onBackPressed() {
    }

    @Override // com.sy37sdk.order.nat.IBasePayView
    public void onFailure(int i, String str) {
    }

    @Override // com.sy37sdk.order.nat.IPageSwitchView
    public void onPayViewWindowFocusChanged(boolean z) {
    }

    @Override // com.sy37sdk.order.nat.IPageSwitchView
    public void onSwitched(int i, int i2, Bundle bundle) {
    }

    @Override // com.sy37sdk.order.nat.IBasePayView
    public void paySuccess() {
    }

    public BasePayView(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initRootView();
        initView();
        initEvent();
    }

    private void initRootView() {
        if (this.rootView != null) {
            return;
        }
        View viewInflate = LayoutInflater.from(getContext()).inflate(getIdByName(getLayoutResName(), "layout"), (ViewGroup) null);
        this.rootView = viewInflate;
        addView(viewInflate);
        initBackTitleView();
    }

    private void initBackTitleView() {
        BackPayView backPayView = (BackPayView) this.rootView.findViewById(SqResUtils.getIdByName("pay_title_view", SqTrackCommonKey.id, getContext()));
        this.backPayView = backPayView;
        backPayView.setOnClickBackViewListener(new BackPayView.OnClickBackViewListener() { // from class: com.sy37sdk.order.nat.BasePayView.1
            @Override // com.sy37sdk.order.view.ui.BackPayView.OnClickBackViewListener
            public void clickLeft() {
                BasePayView.this.leftViewClicked();
            }

            @Override // com.sy37sdk.order.view.ui.BackPayView.OnClickBackViewListener
            public void clickRight() {
                new PayConfirmDialog.Builder(BasePayView.this.getContext()).setTitle(SqResUtils.getStringByName(BasePayView.this.getContext(), "sysq_pay_cancel_tip")).setCancelable(false).setNegativeButton(SqResUtils.getStringByName(BasePayView.this.getContext(), "sysq_pay_cancel_sure"), new View.OnClickListener() { // from class: com.sy37sdk.order.nat.BasePayView.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (BasePayView.this.nativePayDialog != null) {
                            BasePayView.this.nativePayDialog.closeAccountDialog(true);
                        }
                    }
                }).setPositiveButton(SqResUtils.getStringByName(BasePayView.this.getContext(), "sysq_pay_cancel_continue"), null).show();
            }
        });
        setLeftResDrawable("sysq_pay_ic_help");
        setRightResDrawable("sysq_pay_ic_close");
        this.backPayView.setTitle(getTitle());
    }

    protected void setLeftResDrawable(String str) {
        BackPayView backPayView = this.backPayView;
        if (backPayView != null) {
            backPayView.setLeftResDrawable(str);
        }
    }

    protected void setRightResDrawable(String str) {
        BackPayView backPayView = this.backPayView;
        if (backPayView != null) {
            backPayView.setRightResDrawable(str);
        }
    }

    protected void setLeftVisible(boolean z) {
        BackPayView backPayView = this.backPayView;
        if (backPayView != null) {
            backPayView.setLeftVisible(z);
        }
    }

    @Override // com.sqwan.common.mvp.ILoadView
    public void showLoading() {
        INativePayDialog iNativePayDialog = this.nativePayDialog;
        if (iNativePayDialog != null) {
            iNativePayDialog.showLoading();
        }
    }

    @Override // com.sqwan.common.mvp.ILoadView
    public void hideLoading() {
        INativePayDialog iNativePayDialog = this.nativePayDialog;
        if (iNativePayDialog != null) {
            iNativePayDialog.hideLoading();
        }
    }

    @Override // com.sy37sdk.order.nat.IBasePayView
    public void showToast(String str) {
        ToastUtil.showToast(getContext(), str);
    }

    protected boolean isLandscape() {
        return getContext().getResources().getConfiguration().orientation == 2;
    }
}
