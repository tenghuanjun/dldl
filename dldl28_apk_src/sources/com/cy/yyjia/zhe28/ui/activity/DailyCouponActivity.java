package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityDailyCouponBinding;
import com.cy.yyjia.zhe28.databinding.ItemDailyCouponBinding;
import com.cy.yyjia.zhe28.domain.DailyCouponBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.NetUtil;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: DailyCouponActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\u000bH\u0016J\u0006\u0010\r\u001a\u00020\u000bJ\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/DailyCouponActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityDailyCouponBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/DailyCouponBean$Tier;", "Lcom/cy/yyjia/zhe28/databinding/ItemDailyCouponBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "getData", "", "init", "receive", "i", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DailyCouponActivity extends BaseActivity<ActivityDailyCouponBinding> {
    public static final int $stable = 8;
    private final BaseAdapter<DailyCouponBean.Tier, ItemDailyCouponBinding> adapter;

    public static final /* synthetic */ ActivityDailyCouponBinding access$getMBinding(DailyCouponActivity dailyCouponActivity) {
        return dailyCouponActivity.getMBinding();
    }

    public DailyCouponActivity() {
        super(R.layout.activity_daily_coupon, 2);
        this.adapter = new BaseAdapter<>(R.layout.item_daily_coupon, null, 2, null);
    }

    public final BaseAdapter<DailyCouponBean.Tier, ItemDailyCouponBinding> getAdapter() {
        return this.adapter;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().rv.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyCouponActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DailyCouponActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getMBinding().btn.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyCouponActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DailyCouponActivity.init$lambda$1(this.f$0, view);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(DailyCouponActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.receive(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(DailyCouponActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.receive();
    }

    public final void receive(int i) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("log_id", String.valueOf(this.adapter.getItem(i).getLog_id()));
        NetUtil.post2$default(NetUtil.INSTANCE, "dailyRechargeCoupon/receive", linkedHashMap, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyCouponActivity.receive.1
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
                DailyCouponActivity.this.toast(it.getMsg());
                DailyCouponActivity.this.getData();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyCouponActivity.receive.2
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
                DailyCouponActivity.this.netFail(it);
            }
        }, null, 16, null);
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.DailyCouponActivity$receive$3, reason: invalid class name */
    /* JADX INFO: compiled from: DailyCouponActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/Result;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class AnonymousClass3 extends Lambda implements Function1<Result, Unit> {
        AnonymousClass3() {
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
            QuickDialog data = new QuickDialog(DailyCouponActivity.this, R.layout.dialog_daily_coupon_receive).setData(it.getMsg());
            DailyCouponBean data2 = DailyCouponActivity.access$getMBinding(DailyCouponActivity.this).getData();
            Intrinsics.checkNotNull(data2);
            data.setVariable(42, data2.getConfig().getBg_image()).setOnClickListener(R.id.iv_close, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyCouponActivity$receive$3$$ExternalSyntheticLambda0
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    baseDialog.dismiss();
                }
            }).show();
            DailyCouponActivity.this.getData();
        }
    }

    public final void receive() {
        NetUtil.post2$default(NetUtil.INSTANCE, "dailyRechargeCoupon/receiveAll", MapsKt.emptyMap(), new AnonymousClass3(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DailyCouponActivity.receive.4
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
                DailyCouponActivity.this.netFail(it);
            }
        }, null, 16, null);
    }

    public final void getData() {
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new DailyCouponActivity$getData$$inlined$get$1("dailyRechargeCoupon/index", MapsKt.emptyMap(), null, this, this), 3, null);
    }
}
