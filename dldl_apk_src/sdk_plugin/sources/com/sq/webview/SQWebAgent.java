package com.sq.webview;

import android.app.Application;
import android.os.Build;
import android.webkit.WebView;
import com.sq.tools.report.event.IEventReporter;
import com.sq.tools.report.exception.IExceptionReporter;
import com.sq.webview.hooks.ConsoleWebHook;
import com.sq.webview.hooks.HttpDnsWebHook;
import com.sq.webview.hooks.LocalH5WebHook;
import com.sq.webview.hooks.MonitorWebHook;
import com.sq.webview.local.ConfigCallback;
import com.sq.webview.local.LocalH5Manager;
import com.sq.webview.net.IRequest;
import com.sq.webview.report.WebErrorReporter;
import com.sq.webview.report.WebEventReporter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SQWebAgent {
    private static SQWebAgent sSQWebAgent;
    private boolean mCollectWhiteScreenEnable;
    private IEventReporter mEventReporter;
    private IExceptionReporter mExceptionReporter;
    private Executor mExecutor;
    private List<WebHook> mGlobalWebHooks;
    private boolean mHttpDnsEnable;
    private IConfigWebView mIConfigWebView;
    private boolean mLocalH5Enable;
    private boolean mMonitorEnable;

    public interface IConfigWebView {
        void configWebView(WebView webView);
    }

    private SQWebAgent() {
        this.mGlobalWebHooks = new ArrayList();
        this.mMonitorEnable = false;
        this.mHttpDnsEnable = true;
        this.mLocalH5Enable = false;
        this.mCollectWhiteScreenEnable = false;
    }

    public static SQWebAgent createDefault(Builder builder) {
        if (sSQWebAgent == null) {
            sSQWebAgent = builder.build();
        }
        return sSQWebAgent;
    }

    public static SQWebAgent getDefault() {
        SQWebAgent sQWebAgent = sSQWebAgent;
        if (sQWebAgent != null) {
            return sQWebAgent;
        }
        throw new RuntimeException("default agent is null! Please call the method SQWebAgent.createDefault(builder) to create it ");
    }

    public WebFunctionWrapper bind(WebView webView) {
        WebHookDispatcher webHookDispatcher = new WebHookDispatcher();
        webHookDispatcher.onWebInit(webView);
        webHookDispatcher.addWebHooks(this.mGlobalWebHooks);
        defaultWebViewSetting().configWebView(webView);
        IConfigWebView iConfigWebView = this.mIConfigWebView;
        if (iConfigWebView != null) {
            iConfigWebView.configWebView(webView);
        }
        return new WebFunctionWrapper(webView, webHookDispatcher);
    }

    public void checkLocalH5Enable(String url, ConfigCallback configCallback) {
        LocalH5Manager.getInstance().getSinglePageConfig(url, configCallback);
    }

    public LocalH5ConfigBuilder localH5Builder() {
        return new LocalH5ConfigBuilder();
    }

    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.setGlobalWebHooks(this.mGlobalWebHooks);
        builder.configWebViewSetting(this.mIConfigWebView);
        builder.setEventReporter(this.mEventReporter);
        builder.setExceptionReporter(this.mExceptionReporter);
        builder.enableLocalH5(this.mLocalH5Enable);
        builder.setCollectWhiteScreenEnable(this.mCollectWhiteScreenEnable);
        if (!this.mHttpDnsEnable) {
            builder.disableHttpDns();
        }
        if (builder.mMonitorEnable) {
            builder.enableMonitor(this.mExecutor);
        } else {
            builder.disableMonitor();
        }
        return builder;
    }

    public class LocalH5ConfigBuilder {
        private String mGid;
        private String mPid;
        private IRequest mRequestProxy;
        private String mSversion;

        public LocalH5ConfigBuilder() {
        }

        public LocalH5ConfigBuilder setGid(String gid) {
            this.mGid = gid;
            return this;
        }

        public LocalH5ConfigBuilder setPid(String pid) {
            this.mPid = pid;
            return this;
        }

        public LocalH5ConfigBuilder setSversion(String sversion) {
            this.mSversion = sversion;
            return this;
        }

        public LocalH5ConfigBuilder setRequestProxy(IRequest requestProxy) {
            this.mRequestProxy = requestProxy;
            return this;
        }

        public void build(Application application) {
            LocalH5Manager.getInstance().setGid(this.mGid).setPid(this.mPid).setSversion(this.mSversion).setRequestProxy(this.mRequestProxy).setEventReporter(new WebEventReporter(SQWebAgent.this.mEventReporter)).setErrorReporter(new WebErrorReporter(SQWebAgent.this.mExceptionReporter)).init(application);
        }
    }

    public static class Builder {
        private IEventReporter mEventReporter;
        private IExceptionReporter mExceptionReporter;
        private Executor mExecutor;
        private IConfigWebView mIConfigWebView;
        private boolean mCollectWhiteScreenEnable = false;
        private final List<WebHook> mGlobalWebHooks = new ArrayList();
        private boolean mMonitorEnable = true;
        private boolean mHttpDnsEnable = true;
        private boolean mLocalH5Enable = false;

        public Builder setEventReporter(IEventReporter reporter) {
            this.mEventReporter = reporter;
            return this;
        }

        public Builder setExceptionReporter(IExceptionReporter reporter) {
            this.mExceptionReporter = reporter;
            return this;
        }

        public Builder setCollectWhiteScreenEnable(boolean enable) {
            this.mCollectWhiteScreenEnable = enable;
            return this;
        }

        public Builder setGlobalWebHooks(List<WebHook> webHooks) {
            this.mGlobalWebHooks.addAll(webHooks);
            return this;
        }

        public Builder enableMonitor(Executor executor) {
            this.mMonitorEnable = true;
            this.mExecutor = executor;
            return this;
        }

        public Builder enableMonitor() {
            this.mMonitorEnable = true;
            return this;
        }

        public Builder disableMonitor() {
            this.mMonitorEnable = false;
            return this;
        }

        public Builder disableHttpDns() {
            this.mHttpDnsEnable = false;
            return this;
        }

        public Builder enableLocalH5(boolean enable) {
            this.mLocalH5Enable = enable;
            return this;
        }

        public Builder configWebViewSetting(IConfigWebView configSetting) {
            this.mIConfigWebView = configSetting;
            return this;
        }

        private Executor defaultExecutor() {
            return new ThreadPoolExecutor(1, 1, 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SQWebAgent build() {
            WebErrorReporter webErrorReporter;
            IExceptionReporter iExceptionReporter;
            WebEventReporter webEventReporter = null;
            SQWebAgent sQWebAgent = new SQWebAgent();
            sQWebAgent.mGlobalWebHooks = this.mGlobalWebHooks;
            sQWebAgent.mMonitorEnable = this.mMonitorEnable;
            sQWebAgent.mHttpDnsEnable = this.mHttpDnsEnable;
            sQWebAgent.mLocalH5Enable = this.mLocalH5Enable;
            sQWebAgent.mCollectWhiteScreenEnable = this.mCollectWhiteScreenEnable;
            IConfigWebView iConfigWebView = this.mIConfigWebView;
            if (iConfigWebView != null) {
                sQWebAgent.mIConfigWebView = iConfigWebView;
            }
            if (this.mExecutor == null) {
                Executor executorDefaultExecutor = defaultExecutor();
                this.mExecutor = executorDefaultExecutor;
                sQWebAgent.mExecutor = executorDefaultExecutor;
            }
            if (!sQWebAgent.mMonitorEnable) {
                webErrorReporter = null;
            } else {
                if (this.mEventReporter == null || (iExceptionReporter = this.mExceptionReporter) == null) {
                    throw new NullPointerException("MonitorEnable is true ,but reporter is null !!");
                }
                webErrorReporter = new WebErrorReporter(iExceptionReporter);
                webEventReporter = new WebEventReporter(this.mEventReporter);
                MonitorWebHook monitorWebHook = new MonitorWebHook(webErrorReporter, webEventReporter, this.mExecutor);
                monitorWebHook.setCollectWhiteScreenEnable(this.mCollectWhiteScreenEnable);
                this.mGlobalWebHooks.add(monitorWebHook);
                sQWebAgent.mExceptionReporter = this.mExceptionReporter;
                sQWebAgent.mEventReporter = this.mEventReporter;
            }
            if (sQWebAgent.mLocalH5Enable) {
                this.mGlobalWebHooks.add(new LocalH5WebHook(webEventReporter, webErrorReporter));
            }
            if (sQWebAgent.mHttpDnsEnable) {
                this.mGlobalWebHooks.add(new HttpDnsWebHook(webEventReporter, webErrorReporter));
            }
            this.mGlobalWebHooks.add(new ConsoleWebHook());
            return sQWebAgent;
        }
    }

    private IConfigWebView defaultWebViewSetting() {
        return new IConfigWebView() { // from class: com.sq.webview.-$$Lambda$SQWebAgent$g_IkjNKcc80bw82sj-__XcP1q2g
            @Override // com.sq.webview.SQWebAgent.IConfigWebView
            public final void configWebView(WebView webView) {
                SQWebAgent.lambda$defaultWebViewSetting$0(webView);
            }
        };
    }

    static /* synthetic */ void lambda$defaultWebViewSetting$0(WebView webView) {
        webView.setBackgroundColor(0);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(2);
        if (Build.VERSION.SDK_INT > 21) {
            webView.getSettings().setMixedContentMode(0);
        }
    }
}
