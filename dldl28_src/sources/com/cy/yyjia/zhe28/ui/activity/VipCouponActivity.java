package com.cy.yyjia.zhe28.ui.activity;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityVipCouponBinding;
import com.cy.yyjia.zhe28.databinding.ItemVipCouponLevelBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.VipCouponIndexBean;
import com.cy.yyjia.zhe28.ui.dialog.RuleDialog;
import com.cy.yyjia.zhe28.ui.dialog.VipCouponBuyDialog;
import com.cy.yyjia.zhe28.util.NetUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: VipCouponActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/VipCouponActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityVipCouponBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/VipCouponIndexBean$Vip;", "Lcom/cy/yyjia/zhe28/databinding/ItemVipCouponLevelBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "max", "", "getMax", "()I", "setMax", "(I)V", "getData", "", "init", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VipCouponActivity extends BaseActivity<ActivityVipCouponBinding> implements View.OnClickListener {
    public static final int $stable = 8;
    private final BaseAdapter<VipCouponIndexBean.Vip, ItemVipCouponLevelBinding> adapter;
    private int max;

    public VipCouponActivity() {
        super(R.layout.activity_vip_coupon, 1);
        this.adapter = new BaseAdapter<>(R.layout.item_vip_coupon_level, null, 2, null);
    }

    public final BaseAdapter<VipCouponIndexBean.Vip, ItemVipCouponLevelBinding> getAdapter() {
        return this.adapter;
    }

    public final int getMax() {
        return this.max;
    }

    public final void setMax(int i) {
        this.max = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().rv.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VipCouponActivity.init$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(VipCouponActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Iterator<VipCouponIndexBean.Vip> it = this$0.adapter.getData().iterator();
        while (it.hasNext()) {
            it.next().setSelected(false);
        }
        this$0.adapter.getItem(i).setSelected(true);
        ActivityVipCouponBinding mBinding = this$0.getMBinding();
        VipCouponIndexBean data = this$0.getMBinding().getData();
        Intrinsics.checkNotNull(data);
        mBinding.setData(data);
    }

    public final void getData() {
        NetUtil netUtil = NetUtil.INSTANCE;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new VipCouponActivity$getData$$inlined$get$1("vip/vipBuyCoupon", MapsKt.emptyMap(), null, this, this), 3, null);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id == R.id.btn) {
            new VipCouponBuyDialog(this, new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponActivity.onClick.1
                {
                    super(1);
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String num) {
                    Intrinsics.checkNotNullParameter(num, "num");
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    linkedHashMap.put("num", num);
                    NetUtil netUtil = NetUtil.INSTANCE;
                    final VipCouponActivity vipCouponActivity = VipCouponActivity.this;
                    Function1<Result, Unit> function1 = new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponActivity.onClick.1.1
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
                            vipCouponActivity.toast(it.getMsg());
                        }
                    };
                    final VipCouponActivity vipCouponActivity2 = VipCouponActivity.this;
                    NetUtil.post2$default(netUtil, "vip/vipBuyCouponSubmit", linkedHashMap, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.VipCouponActivity.onClick.1.2
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
                            vipCouponActivity2.netFail(it);
                        }
                    }, null, 16, null);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }
            }).setVariable(55, Integer.valueOf(this.max)).show();
        } else {
            if (id != R.id.iv_rule) {
                return;
            }
            RuleDialog ruleDialog = new RuleDialog(this);
            VipCouponIndexBean data = getMBinding().getData();
            Intrinsics.checkNotNull(data);
            ruleDialog.setTextStr(data.getRule()).show();
        }
    }
}
