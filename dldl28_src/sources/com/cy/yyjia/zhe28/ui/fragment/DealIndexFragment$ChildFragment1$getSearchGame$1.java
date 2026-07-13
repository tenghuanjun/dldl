package com.cy.yyjia.zhe28.ui.fragment;

import android.view.View;
import androidx.lifecycle.MutableLiveData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.domain.DealParamBean;
import com.cy.yyjia.zhe28.domain.GameBean;
import com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: DealIndexFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "Lcom/cy/yyjia/zhe28/domain/GameBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
final class DealIndexFragment$ChildFragment1$getSearchGame$1 extends Lambda implements Function1<List<GameBean>, Unit> {
    final /* synthetic */ DealIndexFragment.ChildFragment1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DealIndexFragment$ChildFragment1$getSearchGame$1(DealIndexFragment.ChildFragment1 childFragment1) {
        super(1);
        this.this$0 = childFragment1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(List<GameBean> list) {
        invoke2(list);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(List<GameBean> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(it);
        arrayList.add(new GameBean());
        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_deal_played, arrayList);
        final BaseDialog baseDialogShow = new QuickDialog(this.this$0.getMContext(), R.layout.dialog_deal_played).setAdapter(R.id.rv, baseAdapter).show();
        final DealIndexFragment.ChildFragment1 childFragment1 = this.this$0;
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.fragment.DealIndexFragment$ChildFragment1$getSearchGame$1$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DealIndexFragment$ChildFragment1$getSearchGame$1.invoke$lambda$0(baseDialogShow, baseAdapter, childFragment1, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(BaseDialog baseDialog, BaseAdapter searchAdapter, DealIndexFragment.ChildFragment1 this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(searchAdapter, "$searchAdapter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        baseDialog.dismiss();
        if (((GameBean) searchAdapter.getData().get(i)).getId() != -1) {
            DealParamBean value = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value);
            value.setKeyword(((GameBean) searchAdapter.getData().get(i)).getName());
            MutableLiveData<DealParamBean> dealParam = this$0.getVm().getDealParam();
            DealParamBean value2 = this$0.getVm().getDealParam().getValue();
            Intrinsics.checkNotNull(value2);
            dealParam.setValue(value2);
            return;
        }
        this$0.getVm().getShowKeyboard().postValue(true);
    }
}
