package com.sq.webview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sq.webview.util.WebResUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebViewTitleBar extends RelativeLayout {
    private ImageView ivBack;
    private ImageView ivClose;
    private ImageView ivRefresh;
    private TextView tvTitle;

    public WebViewTitleBar(Context context) {
        this(context, null);
    }

    public WebViewTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebViewTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(WebResUtil.getLayoutId(context, "sy37_web_view_layout_title_bar"), (ViewGroup) this, true);
        this.ivClose = (ImageView) findViewById(WebResUtil.getId(context, "iv_close"));
        this.ivRefresh = (ImageView) findViewById(WebResUtil.getId(context, "iv_refresh"));
        this.tvTitle = (TextView) findViewById(WebResUtil.getId(context, "tv_title"));
        this.ivBack = (ImageView) findViewById(WebResUtil.getId(context, "iv_back"));
    }

    public void setTitle(String title) {
        this.tvTitle.setText(title);
    }

    public void setCloseListener(View.OnClickListener listener) {
        this.ivClose.setOnClickListener(listener);
    }

    public void setRefreshListener(View.OnClickListener listener) {
        this.ivRefresh.setOnClickListener(listener);
    }

    public void setBackListener(View.OnClickListener listener) {
        this.ivBack.setOnClickListener(listener);
    }
}
