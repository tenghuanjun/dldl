package com.sy37sdk.account.auth.floatview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ViewUtils;
import com.sy37sdk.account.floatview.DragViewLayout;
import com.sy37sdk.account.floatview.FloatViewUtils;
import com.sy37sdk.account.floatview.FloatWindow;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AuthCountDownView extends DragViewLayout {
    public AuthClickCallback authClickCallback;
    View authHide;
    View authShow;
    View showArrow;
    TextView tvAuth;
    TextView tvAuthCd;

    interface AuthClickCallback {
        void onClick();
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    public boolean isCheckRecordPos() {
        return false;
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    public boolean isCheckShowCompelete() {
        return false;
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    public boolean isCheckStayEdge() {
        return false;
    }

    public AuthCountDownView(Context context) {
        super(context);
        View.inflate(context, SqResUtils.getLayoutId(context, "sysq_view_auth_countdown"), this);
        this.authHide = findViewById(SqResUtils.getId(context, "view_hide"));
        this.authShow = findViewById(SqResUtils.getId(context, "view_show"));
        this.tvAuth = (TextView) findViewById(SqResUtils.getId(context, "tvAuth"));
        this.tvAuthCd = (TextView) findViewById(SqResUtils.getId(context, "tvAuthCd"));
        this.showArrow = findViewById(SqResUtils.getId(context, "view_show_arrow"));
        setFilterDragClickListener(this.tvAuth, new FloatWindow.ClickListenerAdapter() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownView.1
            @Override // com.sy37sdk.account.floatview.FloatWindow.ClickListenerAdapter, com.sy37sdk.account.floatview.FloatWindow.ClickListener
            public void onClick(View view, int i, int i2) {
                if (AuthCountDownView.this.authClickCallback != null) {
                    AuthCountDownView.this.authClickCallback.onClick();
                }
            }
        });
        setFilterDragClickListener(this.authHide, new FloatWindow.ClickListenerAdapter() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownView.2
            @Override // com.sy37sdk.account.floatview.FloatWindow.ClickListenerAdapter, com.sy37sdk.account.floatview.FloatWindow.ClickListener
            public void onClick(View view, int i, int i2) {
                AuthCountDownView.this.show();
            }
        });
        setFilterDragClickListener(this.showArrow, new FloatWindow.ClickListenerAdapter() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownView.3
            @Override // com.sy37sdk.account.floatview.FloatWindow.ClickListenerAdapter, com.sy37sdk.account.floatview.FloatWindow.ClickListener
            public void onClick(View view, int i, int i2) {
                AuthCountDownView.this.hide();
            }
        });
    }

    private void hideAll() {
        ViewUtils.gone(this.authShow);
        ViewUtils.gone(this.authHide);
    }

    public void show() {
        LogUtil.i(this.TAG, "show");
        hideAll();
        ViewUtils.show(this.authShow);
        update();
    }

    public void hide() {
        LogUtil.i(this.TAG, "hide");
        hideAll();
        ViewUtils.show(this.authHide);
        update();
    }

    public void changeTime(String str) {
        LogUtil.i(this.TAG, "changeTime");
        String str2 = String.format(SqResUtils.getStringByName(getContext(), "sysq_name_auth_cd_time"), str);
        if (this.tvAuthCd.getText().toString().equals(str2)) {
            return;
        }
        LogUtil.i(this.TAG, "changeTime result:" + str2);
        this.tvAuthCd.setText(str2);
        update();
    }

    public void initView() {
        updateY(0);
        updateX(0);
        addView();
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase
    public void release() {
        LogUtil.i(this.TAG, "release");
        this.authClickCallback = null;
        super.release();
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    protected void handleStartAnimResultPos() {
        this.valueAnimator = ValueAnimator.ofInt(this.floatLayoutParams.y, 0);
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout
    protected void handleOnAnimationUpdate(ValueAnimator valueAnimator) {
        this.floatLayoutParams.y = ((Integer) valueAnimator.getAnimatedValue()).intValue();
    }

    @Override // com.sy37sdk.account.floatview.DragViewLayout, com.sy37sdk.account.floatview.CheckSystemUiViewBase, com.sy37sdk.account.floatview.SystemUiCheckUtils.onSystemUiChangedCallback
    public void onSystemUiChanged(FloatViewUtils.FloatViewConfig floatViewConfig) {
        updateX(0);
        updateY(0);
        update();
    }

    @Override // com.sy37sdk.account.floatview.CheckSystemUiViewBase, com.sy37sdk.account.floatview.ScreenOrientationHelper.ScreenOrientationChangeListener
    public void onChange(int i) {
        initLayoutParams();
        update();
    }
}
