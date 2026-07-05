package com.sq.webview.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.sq.webview.R;
import com.sq.webview.util.WebResUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DefaultWebToolBar extends LinearLayout implements IWebToolBar {
    private View mBackBar;
    private View.OnClickListener mCloseClickListener;
    private final Context mContext;
    private View mForwardBar;
    private View mRefreshBar;
    private View mRootView;
    private boolean mShowForward;

    public DefaultWebToolBar(Context context) {
        this(context, null);
    }

    public DefaultWebToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultWebToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.DefaultWebToolBar);
        this.mShowForward = typedArrayObtainStyledAttributes.getBoolean(R.styleable.DefaultWebToolBar_show_forward, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Context context = this.mContext;
        this.mRootView = View.inflate(context, WebResUtil.getLayoutId(context, "sy37_web_view_layout_web_tool_bar"), this);
        initView();
    }

    private void initView() {
        this.mBackBar = this.mRootView.findViewById(WebResUtil.getId(this.mContext, "web_back_bar"));
        this.mForwardBar = this.mRootView.findViewById(WebResUtil.getId(this.mContext, "web_forward_bar"));
        this.mRefreshBar = this.mRootView.findViewById(WebResUtil.getId(this.mContext, "web_refresh_bar"));
        this.mForwardBar.setVisibility(this.mShowForward ? 0 : 8);
        this.mRootView.findViewById(WebResUtil.getId(this.mContext, "web_close_bar")).setOnClickListener(new View.OnClickListener() { // from class: com.sq.webview.view.-$$Lambda$DefaultWebToolBar$UMSqyy3Ob_6jf0Xswnkqev_OQgs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0$DefaultWebToolBar(view);
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$DefaultWebToolBar(View view) {
        View.OnClickListener onClickListener = this.mCloseClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @Override // com.sq.webview.view.IWebToolBar
    public void show(Context context) {
        setVisibility(0);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "translationY", 200.0f, 0.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, "alpha", 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.setDuration(500L);
        animatorSet.start();
    }

    @Override // com.sq.webview.view.IWebToolBar
    public void hide(Context context) {
        setVisibility(8);
    }

    @Override // com.sq.webview.view.IWebToolBar
    public boolean isShow() {
        return getVisibility() == 0;
    }

    @Override // com.sq.webview.view.IWebToolBar
    public View getRefreshView() {
        return this.mRefreshBar;
    }

    @Override // com.sq.webview.view.IWebToolBar
    public View getBackView() {
        return this.mBackBar;
    }

    @Override // com.sq.webview.view.IWebToolBar
    public View getForwardView() {
        return this.mForwardBar;
    }

    public void setCloseClickListener(View.OnClickListener clickListener) {
        this.mCloseClickListener = clickListener;
    }
}
