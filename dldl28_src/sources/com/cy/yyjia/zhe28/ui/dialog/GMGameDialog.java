package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogGmGameBinding;
import com.cy.yyjia.zhe28.databinding.ItemGmGameBinding;
import com.cy.yyjia.zhe28.domain.GMGameBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GMGameDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B0\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012!\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\u0002\u0010\fJ\u0006\u0010\u0018\u001a\u00020\u000bR\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/GMGameDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogGmGameBinding;", "fa", "Landroidx/fragment/app/FragmentActivity;", "click", "Lkotlin/Function1;", "Lcom/cy/yyjia/zhe28/domain/GMGameBean;", "Lkotlin/ParameterName;", "name", "role", "", "(Landroidx/fragment/app/FragmentActivity;Lkotlin/jvm/functions/Function1;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/databinding/ItemGmGameBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GMGameDialog extends BaseDataBindingDialog<DialogGmGameBinding, GMGameDialog> {
    public static final int $stable = 8;
    private final BaseAdapter<GMGameBean, ItemGmGameBinding> adapter;
    private int page;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GMGameDialog(FragmentActivity fa, final Function1<? super GMGameBean, Unit> click) {
        super(fa, R.layout.dialog_gm_game);
        Intrinsics.checkNotNullParameter(fa, "fa");
        Intrinsics.checkNotNullParameter(click, "click");
        BaseAdapter<GMGameBean, ItemGmGameBinding> baseAdapter = new BaseAdapter<>(R.layout.item_gm_game, null, 2, null);
        this.adapter = baseAdapter;
        this.page = 1;
        ((DialogGmGameBinding) this.mBinding).setText("");
        ((DialogGmGameBinding) this.mBinding).rv.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.GMGameDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GMGameDialog._init_$lambda$0(click, this, baseQuickAdapter, view, i);
            }
        });
        ((DialogGmGameBinding) this.mBinding).ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.GMGameDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GMGameDialog._init_$lambda$1(this.f$0, view);
            }
        });
        ((DialogGmGameBinding) this.mBinding).tvSearch.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.GMGameDialog$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GMGameDialog._init_$lambda$2(this.f$0, view);
            }
        });
        getData();
    }

    public final BaseAdapter<GMGameBean, ItemGmGameBinding> getAdapter() {
        return this.adapter;
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(Function1 click, GMGameDialog this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(click, "$click");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        click.invoke(this$0.adapter.getItem(i));
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(GMGameDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$2(GMGameDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.page = 1;
        this$0.adapter.setNewInstance(null);
        this$0.getData();
    }

    public final void getData() {
        Repository repository = Repository.INSTANCE;
        String text = ((DialogGmGameBinding) this.mBinding).getText();
        Intrinsics.checkNotNull(text);
        repository.getGMGames(text, new Function1<PageBean<GMGameBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.GMGameDialog.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<GMGameBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<GMGameBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (GMGameDialog.this.getPage() == 1) {
                    GMGameDialog.this.getAdapter().setNewInstance(it.getList());
                } else {
                    GMGameDialog.this.getAdapter().addData(it.getList());
                }
                GMGameDialog gMGameDialog = GMGameDialog.this;
                gMGameDialog.setPage(gMGameDialog.getPage() + 1);
                gMGameDialog.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(GMGameDialog.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    GMGameDialog.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.GMGameDialog.getData.2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }
        });
    }
}
