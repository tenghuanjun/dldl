package com.sqwan.common.web;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.util.SqResUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class WebViewToolBar extends LinearLayout {
    private View backBar;
    private View closeBar;
    private View forwardBar;
    private ImageView ivBackBar;
    private ImageView ivCloseBar;
    private ImageView ivForwardBar;
    private ImageView ivRefreshBar;
    private Context mContext;
    private WebToolBarClickListener mToolBarClickListener;
    private View refreshBar;
    private View rootView;

    public interface WebToolBarClickListener {
        void onClickBack();

        void onClickClose();

        void onClickForward();

        void onClickRefresh();
    }

    public WebViewToolBar(Context context) {
        this(context, null);
    }

    public WebViewToolBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WebViewToolBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Context context = this.mContext;
        this.rootView = View.inflate(context, SqResUtils.getIdByName("sy37_web_tool_bar", "layout", context), this);
        initView();
    }

    private void initView() {
        this.ivBackBar = (ImageView) this.rootView.findViewById(SqResUtils.getIdByName("iv_back_bar", SqTrackCommonKey.id, this.mContext));
        this.ivForwardBar = (ImageView) this.rootView.findViewById(SqResUtils.getIdByName("iv_forward_bar", SqTrackCommonKey.id, this.mContext));
        this.ivRefreshBar = (ImageView) this.rootView.findViewById(SqResUtils.getIdByName("iv_refresh_bar", SqTrackCommonKey.id, this.mContext));
        this.ivCloseBar = (ImageView) this.rootView.findViewById(SqResUtils.getIdByName("iv_close_bar", SqTrackCommonKey.id, this.mContext));
        this.backBar = this.rootView.findViewById(SqResUtils.getIdByName("web_back_bar", SqTrackCommonKey.id, this.mContext));
        this.forwardBar = this.rootView.findViewById(SqResUtils.getIdByName("web_forward_bar", SqTrackCommonKey.id, this.mContext));
        this.refreshBar = this.rootView.findViewById(SqResUtils.getIdByName("web_refresh_bar", SqTrackCommonKey.id, this.mContext));
        this.closeBar = this.rootView.findViewById(SqResUtils.getIdByName("web_close_bar", SqTrackCommonKey.id, this.mContext));
        this.backBar.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.web.WebViewToolBar.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WebViewToolBar.this.mToolBarClickListener != null) {
                    WebViewToolBar.this.mToolBarClickListener.onClickBack();
                }
            }
        });
        this.forwardBar.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.web.WebViewToolBar.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WebViewToolBar.this.mToolBarClickListener != null) {
                    WebViewToolBar.this.mToolBarClickListener.onClickForward();
                }
            }
        });
        this.refreshBar.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.web.WebViewToolBar.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WebViewToolBar.this.mToolBarClickListener != null) {
                    WebViewToolBar.this.mToolBarClickListener.onClickRefresh();
                }
            }
        });
        this.closeBar.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.web.WebViewToolBar.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (WebViewToolBar.this.mToolBarClickListener != null) {
                    WebViewToolBar.this.mToolBarClickListener.onClickClose();
                }
            }
        });
    }

    public void setBackBarSrc(int i) {
        this.ivBackBar.setImageResource(i);
    }

    public void setForwardBarSrc(int i) {
        this.ivForwardBar.setImageResource(i);
    }

    public void showAnimation() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "translationY", 200.0f, 0.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, "alpha", 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.setDuration(500L);
        animatorSet.start();
    }

    public void hideAnimation() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "translationY", 0.0f, 200.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this, "alpha", 1.0f, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.setDuration(500L);
        animatorSet.start();
    }

    public void setWebToolBarClickListener(WebToolBarClickListener webToolBarClickListener) {
        this.mToolBarClickListener = webToolBarClickListener;
    }
}
