package com.cy.yyjia.zhe28.ui.fragment;

import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.domain.DealParamBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.domain.HotDealGameBean;
import com.cy.yyjia.zhe28.ui.adapter.DealAdapter;
import com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: DealIndexFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/HotDealGameBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
final class DealIndexFragment$ChildFragment1$getEmpty$1 extends Lambda implements Function1<HotDealGameBean, Unit> {
    final /* synthetic */ DealIndexFragment.ChildFragment1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DealIndexFragment$ChildFragment1$getEmpty$1(DealIndexFragment.ChildFragment1 childFragment1) {
        super(1);
        this.this$0 = childFragment1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(HotDealGameBean hotDealGameBean) {
        invoke2(hotDealGameBean);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(HotDealGameBean it) {
        Intrinsics.checkNotNullParameter(it, "it");
        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_deal_hot_game, it.getGames());
        ((RecyclerView) this.this$0.getEmpty().findViewById(R.id.rv)).setAdapter(baseAdapter);
        final DealIndexFragment.ChildFragment1 childFragment1 = this.this$0;
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$getEmpty$1$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DealIndexFragment$ChildFragment1$getEmpty$1.invoke$lambda$0(childFragment1, baseAdapter, baseQuickAdapter, view, i);
            }
        });
        DealAdapter adapter = this.this$0.getAdapter();
        View rootView = this.this$0.getEmpty().getRootView();
        Intrinsics.checkNotNullExpressionValue(rootView, "getRootView(...)");
        adapter.setEmptyView(rootView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void invoke$lambda$0(DealIndexFragment.ChildFragment1 this$0, BaseAdapter gameAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(gameAdapter, "$gameAdapter");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        DealParamBean value = this$0.getVm().getDealParam().getValue();
        Intrinsics.checkNotNull(value);
        value.setKeyword(((GameBean) gameAdapter.getItem(i)).getName());
        MutableLiveData<DealParamBean> dealParam = this$0.getVm().getDealParam();
        DealParamBean value2 = this$0.getVm().getDealParam().getValue();
        Intrinsics.checkNotNull(value2);
        dealParam.setValue(value2);
    }
}
