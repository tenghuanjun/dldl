package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityEventDetailBinding;
import com.cy.yyjia.zhe28.domain.GameEventDetailBean;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: EventDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eR\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/EventDetailActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityEventDetailBinding;", "()V", "newsId", "", "getNewsId", "()I", "newsId$delegate", "Lkotlin/Lazy;", "init", "", "initWv", "wv", "Landroid/webkit/WebView;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class EventDetailActivity extends BaseActivity<ActivityEventDetailBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: newsId$delegate, reason: from kotlin metadata */
    private final Lazy newsId;

    public static final /* synthetic */ ActivityEventDetailBinding access$getMBinding(EventDetailActivity eventDetailActivity) {
        return eventDetailActivity.getMBinding();
    }

    public EventDetailActivity() {
        super(R.layout.activity_event_detail, 0, 2, null);
        this.newsId = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.EventDetailActivity$newsId$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("newsId", 0));
            }
        });
    }

    private final int getNewsId() {
        return ((Number) this.newsId.getValue()).intValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        Repository.INSTANCE.getGameEventDetail(getNewsId(), new Function1<GameEventDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.EventDetailActivity.init.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GameEventDetailBean gameEventDetailBean) {
                invoke2(gameEventDetailBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GameEventDetailBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                EventDetailActivity.access$getMBinding(EventDetailActivity.this).setData(it);
                if (TextUtils.isEmpty(it.getUrl())) {
                    EventDetailActivity.access$getMBinding(EventDetailActivity.this).wv2.setVisibility(8);
                    EventDetailActivity eventDetailActivity = EventDetailActivity.this;
                    WebView wv = EventDetailActivity.access$getMBinding(eventDetailActivity).wv;
                    Intrinsics.checkNotNullExpressionValue(wv, "wv");
                    eventDetailActivity.initWv(wv);
                    EventDetailActivity.access$getMBinding(EventDetailActivity.this).wv.loadData(it.getMessage(), "", "");
                    return;
                }
                EventDetailActivity.access$getMBinding(EventDetailActivity.this).csl.setVisibility(8);
                EventDetailActivity eventDetailActivity2 = EventDetailActivity.this;
                WebView wv2 = EventDetailActivity.access$getMBinding(eventDetailActivity2).wv2;
                Intrinsics.checkNotNullExpressionValue(wv2, "wv2");
                eventDetailActivity2.initWv(wv2);
                WebView webView = EventDetailActivity.access$getMBinding(EventDetailActivity.this).wv2;
                String url = it.getUrl();
                Intrinsics.checkNotNull(url);
                webView.loadUrl(url);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.EventDetailActivity.init.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                EventDetailActivity.this.netFail(it);
            }
        });
    }

    public final void initWv(WebView wv) {
        Intrinsics.checkNotNullParameter(wv, "wv");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setCacheMode(2);
        wv.getSettings().setDomStorageEnabled(true);
        wv.setWebViewClient(new WebViewClient() { // from class: com.cy.yyjia.zhe28.ui.activity.EventDetailActivity.initWv.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                EventDetailActivity.this.log("url" + url);
                Intrinsics.checkNotNull(url);
                if (StringsKt.startsWith$default(url, "http:", false, 2, (Object) null) || StringsKt.startsWith$default(url, "https:", false, 2, (Object) null)) {
                    return false;
                }
                try {
                    EventDetailActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    return true;
                } catch (Exception e) {
                    EventDetailActivity eventDetailActivity = EventDetailActivity.this;
                    String localizedMessage = e.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    eventDetailActivity.log(localizedMessage);
                    return true;
                }
            }
        });
    }
}
