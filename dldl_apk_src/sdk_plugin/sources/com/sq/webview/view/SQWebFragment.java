package com.sq.webview.view;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sq.webview.SQBaseJSInterface;
import com.sq.webview.SQWebAgent;
import com.sq.webview.WebHook;
import com.sq.webview.util.WebLogUtil;
import com.sq.webview.util.WebResUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SQWebFragment extends Fragment {
    private SQBaseJSInterface mSQBaseJSInterface;
    private SQWebAgent mSQWebAgent;
    private SQWebView mSQWebView;
    private String mTitle;
    private String mUrl;
    private int mBackgroundColor = 0;
    private List<WebHook> mWebHooks = new ArrayList();

    public static SQWebFragment newInstance(Bundle params) {
        SQWebFragment sQWebFragment = new SQWebFragment();
        sQWebFragment.setArguments(params);
        return sQWebFragment;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.mUrl = arguments.getString("url");
        this.mTitle = arguments.getString("title");
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SQWebView sQWebView = (SQWebView) view.findViewById(WebResUtil.getId(view.getContext(), "sq_wv"));
        this.mSQWebView = sQWebView;
        sQWebView.setTitle(this.mTitle);
        SQWebAgent sQWebAgent = this.mSQWebAgent;
        if (sQWebAgent != null) {
            this.mSQWebView.setWebAgent(sQWebAgent);
            this.mSQWebView.getWebFunctionWrapper().addWebHooks(this.mWebHooks);
        }
        this.mSQWebView.setTitleCloseListener(new View.OnClickListener() { // from class: com.sq.webview.view.-$$Lambda$SQWebFragment$HAlw_VcHHal3g6WZjiNpxtQQ-b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$0$SQWebFragment(view2);
            }
        });
        if (this.mSQBaseJSInterface == null) {
            this.mSQBaseJSInterface = new SQBaseJSInterface("Tools", view.getContext());
        }
        this.mSQWebView.getRealWebView().setBackgroundColor(this.mBackgroundColor);
        this.mSQBaseJSInterface.setWebView(this.mSQWebView.getRealWebView());
        SQWebView sQWebView2 = this.mSQWebView;
        SQBaseJSInterface sQBaseJSInterface = this.mSQBaseJSInterface;
        sQWebView2.addJavascriptInterface(sQBaseJSInterface, sQBaseJSInterface.getInterfaceName());
        if (TextUtils.isEmpty(this.mUrl)) {
            return;
        }
        this.mSQWebView.loadUrl(this.mUrl);
    }

    public /* synthetic */ void lambda$onViewCreated$0$SQWebFragment(View v) {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(WebResUtil.getLayoutId(getActivity(), "sy37_web_view_layout_dialog"), container, false);
    }

    public void setSQWebAgent(SQWebAgent agent) {
        this.mSQWebAgent = agent;
    }

    public void setJSInterface(SQBaseJSInterface sqBaseJSInterface) {
        this.mSQBaseJSInterface = sqBaseJSInterface;
    }

    public void loadUrl(String url) {
        this.mUrl = url;
        if (!TextUtils.isEmpty(url)) {
            this.mSQWebView.loadUrl(this.mUrl);
        } else {
            WebLogUtil.e("SQWebFragment load url, url is empty");
        }
    }

    public void setWebViewBackground(int color) {
        this.mBackgroundColor = color;
        SQWebView sQWebView = this.mSQWebView;
        if (sQWebView != null) {
            sQWebView.getRealWebView().setBackgroundColor(color);
        }
    }

    public void addWebHook(WebHook webHook) {
        this.mWebHooks.add(webHook);
    }

    public void addWebHooks(List<WebHook> webHooks) {
        this.mWebHooks.addAll(webHooks);
    }
}
