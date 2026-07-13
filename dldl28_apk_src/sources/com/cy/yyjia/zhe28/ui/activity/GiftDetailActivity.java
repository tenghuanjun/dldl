package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityGiftDetailBinding;
import com.cy.yyjia.zhe28.domain.GiftDetailBean;
import com.cy.yyjia.zhe28.ui.dialog.ReceiveStatusDialog;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GiftDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\u000bH\u0016J\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fR\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/GiftDetailActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityGiftDetailBinding;", "()V", "id", "", "getId", "()I", "id$delegate", "Lkotlin/Lazy;", "getData", "", "init", "receive", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GiftDetailActivity extends BaseActivity<ActivityGiftDetailBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final Lazy id;

    public GiftDetailActivity() {
        super(R.layout.activity_gift_detail, 0, 2, null);
        this.id = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.GiftDetailActivity$id$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("id", 0));
            }
        });
    }

    public static final /* synthetic */ ActivityGiftDetailBinding access$getMBinding(GiftDetailActivity giftDetailActivity) {
        return giftDetailActivity.getMBinding();
    }

    public final int getId() {
        return ((Number) this.id.getValue()).intValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getData();
    }

    public final void getData() {
        Repository.INSTANCE.getGameGiftDetail(getId(), new Function1<GiftDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GiftDetailActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GiftDetailBean giftDetailBean) {
                invoke2(giftDetailBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GiftDetailBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                GiftDetailActivity.access$getMBinding(GiftDetailActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.GiftDetailActivity.getData.2
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
                GiftDetailActivity.this.netFail(it);
            }
        });
    }

    public final void receive(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        GiftDetailBean data = getMBinding().getData();
        if (data != null) {
            new ReceiveStatusDialog(getMContext()).set(1, data.getId(), data.getGameId()).show();
        }
    }
}
