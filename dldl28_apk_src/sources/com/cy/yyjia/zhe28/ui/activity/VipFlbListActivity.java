package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemVipFlbBinding;
import com.cy.yyjia.zhe28.domain.ActivityFlbResult;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.VipFlbBean;
import com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.cy.yyjia.zhe28.view.Navigation;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: VipFlbListActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u001aH\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0016\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u000b\u001a\u0004\b\u0017\u0010\u0013¨\u0006\u001c"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/VipFlbListActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/VipFlbBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemVipFlbBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "banner", "", "", "getBanner", "()Ljava/util/List;", "page", "getPage", "()I", "setPage", "(I)V", "type", "getType", "type$delegate", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VipFlbListActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private final List<Integer> banner;
    private int page;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    public VipFlbListActivity() {
        super(R.layout.activity_rv, 0, 2, null);
        this.banner = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.mipmap.banner_vip0), Integer.valueOf(R.mipmap.banner_vip1), Integer.valueOf(R.mipmap.banner_vip2), Integer.valueOf(R.mipmap.banner_vip3), Integer.valueOf(R.mipmap.banner_vip4)});
        this.type = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity$type$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("type", 0));
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<VipFlbBean, ItemVipFlbBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<VipFlbBean, ItemVipFlbBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_vip_flb, null, 2, null);
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityRvBinding access$getMBinding(VipFlbListActivity vipFlbListActivity) {
        return vipFlbListActivity.getMBinding();
    }

    public final List<Integer> getBanner() {
        return this.banner;
    }

    public final int getType() {
        return ((Number) this.type.getValue()).intValue();
    }

    public final BaseAdapter<VipFlbBean, ItemVipFlbBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().srl.setEnabled(false);
        VipFlbListActivity vipFlbListActivity = this;
        ImageView imageView = new ImageView(vipFlbListActivity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(Util.dpToPx(vipFlbListActivity, 12.0f), Util.dpToPx(vipFlbListActivity, 12.0f), Util.dpToPx(vipFlbListActivity, 12.0f), 0);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(this.banner.get(getType()).intValue());
        imageView.setLayoutParams(layoutParams);
        getMBinding().ll.addView(imageView, 1);
        Navigation navigation = getMBinding().navigation;
        String stringExtra = getIntent().getStringExtra("title");
        Intrinsics.checkNotNull(stringExtra);
        navigation.setTitle(stringExtra);
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VipFlbListActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(final VipFlbListActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Repository.INSTANCE.receiveVipFlb(this$0.getAdapter().getItem(i).getId(), this$0.getType(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity$init$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                invoke2(result);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Result it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.toast(it.getMsg());
                this.this$0.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity$init$1$2
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
                this.this$0.netFail(it);
            }
        });
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity$getData$2, reason: invalid class name */
    /* JADX INFO: compiled from: VipFlbListActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "result", "Lcom/cy/yyjia/zhe28/domain/ActivityFlbResult;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class AnonymousClass2 extends Lambda implements Function1<ActivityFlbResult, Unit> {
        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ActivityFlbResult activityFlbResult) {
            invoke2(activityFlbResult);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final ActivityFlbResult result) {
            Intrinsics.checkNotNullParameter(result, "result");
            VipFlbListActivity.this.getAdapter().setNewInstance(result.getConfig());
            BaseAdapter<VipFlbBean, ItemVipFlbBinding> adapter = VipFlbListActivity.this.getAdapter();
            final VipFlbListActivity vipFlbListActivity = VipFlbListActivity.this;
            adapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity$getData$2$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    VipFlbListActivity.AnonymousClass2.invoke$lambda$0(result, vipFlbListActivity, baseQuickAdapter, view, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(ActivityFlbResult result, final VipFlbListActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(result, "$result");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            Repository.INSTANCE.receiveVipFlb(result.getId(), this$0.getType(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity$getData$2$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Result result2) {
                    invoke2(result2);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Result it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    this$0.toast(it.getMsg());
                    this$0.getData();
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity$getData$2$1$2
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
                    this$0.netFail(it);
                }
            });
        }
    }

    public final void getData() {
        Repository.INSTANCE.getVipFlbList(this.page, getType(), new Function1<List<VipFlbBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<VipFlbBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<VipFlbBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VipFlbListActivity.this.getAdapter().setNewInstance(it);
            }
        }, new AnonymousClass2(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipFlbListActivity.getData.3
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
                VipFlbListActivity.access$getMBinding(VipFlbListActivity.this).srl.finishRefresh(false);
                VipFlbListActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                VipFlbListActivity.this.netFail(it);
            }
        });
    }
}
