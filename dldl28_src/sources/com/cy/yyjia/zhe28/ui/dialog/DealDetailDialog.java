package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogDealDetailBinding;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.domain.DealDetailResult;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DealDetailDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\n\u001a\u00020\u000bJ)\u0010\f\u001a\u00020\u00002!\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u000b0\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/DealDetailDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogDealDetailBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "id", "", "(Landroidx/fragment/app/FragmentActivity;I)V", "getId", "()I", "getData", "", "share", "Lkotlin/Function1;", "Lcom/cy/yyjia/zhe28/domain/DealBean;", "Lkotlin/ParameterName;", "name", "deal", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealDetailDialog extends BaseDataBindingDialog<DialogDealDetailBinding, DealDetailDialog> {
    public static final int $stable = 0;
    private final int id;

    public final int getId() {
        return this.id;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DealDetailDialog(FragmentActivity activity, int i) {
        super(activity, R.layout.dialog_deal_detail);
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.id = i;
        getData();
    }

    public final void getData() {
        Repository.INSTANCE.getDealDetail(this.id, new Function1<DealDetailResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.DealDetailDialog.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DealDetailResult dealDetailResult) {
                invoke2(dealDetailResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DealDetailResult it) {
                Intrinsics.checkNotNullParameter(it, "it");
                ((DialogDealDetailBinding) DealDetailDialog.this.mBinding).setData(it.getDetail());
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.dialog.DealDetailDialog.getData.2
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
                DealDetailDialog.this.log(it.getLocalizedMessage());
            }
        });
    }

    public final DealDetailDialog share(final Function1<? super DealBean, Unit> share) {
        Intrinsics.checkNotNullParameter(share, "share");
        ((DialogDealDetailBinding) this.mBinding).ivShare.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.DealDetailDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DealDetailDialog.share$lambda$1(this.f$0, share, view);
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void share$lambda$1(DealDetailDialog this$0, Function1 share, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(share, "$share");
        DealBean data = ((DialogDealDetailBinding) this$0.mBinding).getData();
        if (data != null) {
            share.invoke(data);
            this$0.dismiss();
        }
    }
}
