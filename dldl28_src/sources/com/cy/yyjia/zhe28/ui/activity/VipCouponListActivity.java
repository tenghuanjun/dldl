package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemVipCouponBinding;
import com.cy.yyjia.zhe28.domain.CouponBean;
import com.cy.yyjia.zhe28.domain.Result;
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

/* JADX INFO: compiled from: VipCouponListActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/VipCouponListActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/CouponBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemVipCouponBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "banner", "", "", "getBanner", "()Ljava/util/List;", "type", "getType", "()I", "type$delegate", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VipCouponListActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private final List<Integer> banner;

    /* JADX INFO: renamed from: type$delegate, reason: from kotlin metadata */
    private final Lazy type;

    public VipCouponListActivity() {
        super(R.layout.activity_rv, 0, 2, null);
        this.type = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponListActivity$type$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("type", 0));
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<CouponBean, ItemVipCouponBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponListActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<CouponBean, ItemVipCouponBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_vip_coupon, null, 2, null);
            }
        });
        this.banner = CollectionsKt.listOf((Object[]) new Integer[]{Integer.valueOf(R.mipmap.banner_vip0), Integer.valueOf(R.mipmap.banner_vip1), Integer.valueOf(R.mipmap.banner_vip2), Integer.valueOf(R.mipmap.banner_vip3), Integer.valueOf(R.mipmap.banner_vip4)});
    }

    public final int getType() {
        return ((Number) this.type.getValue()).intValue();
    }

    public final BaseAdapter<CouponBean, ItemVipCouponBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final List<Integer> getBanner() {
        return this.banner;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().srl.setEnabled(false);
        VipCouponListActivity vipCouponListActivity = this;
        ImageView imageView = new ImageView(vipCouponListActivity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(Util.dpToPx(vipCouponListActivity, 12.0f), Util.dpToPx(vipCouponListActivity, 12.0f), Util.dpToPx(vipCouponListActivity, 12.0f), 0);
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
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponListActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VipCouponListActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().addChildClickViewIds(R.id.btn);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponListActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VipCouponListActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(VipCouponListActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.getAdapter().getItem(i).setSelected(!this$0.getAdapter().getItem(i).getSelected());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(final VipCouponListActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Repository.INSTANCE.receiveVipCoupon(this$0.getAdapter().getItem(i).getCouponId(), this$0.getType(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponListActivity$init$2$1
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
                this.this$0.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponListActivity$init$2$2
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

    public final void getData() {
        Repository.INSTANCE.getVipCouponList(getType(), new Function1<List<CouponBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponListActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<CouponBean> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<CouponBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                VipCouponListActivity.this.getAdapter().setNewInstance(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponListActivity.getData.2
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
                VipCouponListActivity.this.netFail(it);
            }
        });
    }
}
