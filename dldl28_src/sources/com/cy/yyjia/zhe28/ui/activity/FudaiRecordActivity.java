package com.cy.yyjia.zhe28.ui.activity;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityRvBinding;
import com.cy.yyjia.zhe28.databinding.ItemFudaiIndexBinding;
import com.cy.yyjia.zhe28.domain.FudaiIndexBean;
import com.cy.yyjia.zhe28.domain.FudaiRecordBean;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FudaiRecordActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\rH\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/FudaiRecordActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityRvBinding;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/FudaiIndexBean$List;", "Lcom/cy/yyjia/zhe28/databinding/ItemFudaiIndexBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "getData", "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FudaiRecordActivity extends BaseActivity<ActivityRvBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    public FudaiRecordActivity() {
        super(R.layout.activity_rv, 0, 2, null);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<FudaiIndexBean.List, ItemFudaiIndexBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiRecordActivity$adapter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<FudaiIndexBean.List, ItemFudaiIndexBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_fudai_index, new Function3<BaseDataBindingHolder<ItemFudaiIndexBinding>, Integer, FudaiIndexBean.List, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiRecordActivity$adapter$2.1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemFudaiIndexBinding> baseDataBindingHolder, Integer num, FudaiIndexBean.List list) {
                        invoke(baseDataBindingHolder, num.intValue(), list);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(BaseDataBindingHolder<ItemFudaiIndexBinding> h, int i, FudaiIndexBean.List list) {
                        Intrinsics.checkNotNullParameter(h, "h");
                        ItemFudaiIndexBinding itemFudaiIndexBinding = (ItemFudaiIndexBinding) h.getDataBinding();
                        if (itemFudaiIndexBinding == null) {
                            return;
                        }
                        itemFudaiIndexBinding.setWo(true);
                    }
                });
            }
        });
    }

    public final BaseAdapter<FudaiIndexBean.List, ItemFudaiIndexBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().srl.setOnRefreshListener(new OnRefreshListener() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiRecordActivity$$ExternalSyntheticLambda0
            @Override // com.scwang.smart.refresh.layout.listener.OnRefreshListener
            public final void onRefresh(RefreshLayout refreshLayout) {
                FudaiRecordActivity.init$lambda$0(this.f$0, refreshLayout);
            }
        });
        getMBinding().navigation.setTitle("我的中奖记录");
        getMBinding().rv.setAdapter(getAdapter());
        BaseAdapter.setMyEmptyView$default(getAdapter(), null, 1, null);
        getMBinding().rv.setBackgroundResource(R.drawable.default_corner_12dp);
        ViewGroup.LayoutParams layoutParams = getMBinding().srl.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
        FudaiRecordActivity fudaiRecordActivity = this;
        int iDpToPx = Util.dpToPx(fudaiRecordActivity, 12.0f);
        layoutParams2.setMargins(iDpToPx, iDpToPx, iDpToPx, iDpToPx);
        getMBinding().rv.setPadding(0, 0, 0, Util.dpToPx(fudaiRecordActivity, 16.0f));
        layoutParams2.height = -2;
        getMBinding().srl.setLayoutParams(layoutParams2);
        getMBinding().srl.setEnabled(false);
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(FudaiRecordActivity this$0, RefreshLayout it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.getAdapter().setNewInstance(null);
        this$0.getData();
    }

    public final void getData() {
        Repository.INSTANCE.getFudaiRecord(new Function1<FudaiRecordBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiRecordActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FudaiRecordBean fudaiRecordBean) {
                invoke2(fudaiRecordBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(FudaiRecordBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Iterator<FudaiIndexBean.List> it2 = it.getList().iterator();
                while (it2.hasNext()) {
                    it2.next().setUser(it.getUser());
                }
                FudaiRecordActivity.this.getAdapter().setNewInstance(it.getList());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.FudaiRecordActivity.getData.2
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
                FudaiRecordActivity.this.netFail(it);
            }
        });
    }
}
