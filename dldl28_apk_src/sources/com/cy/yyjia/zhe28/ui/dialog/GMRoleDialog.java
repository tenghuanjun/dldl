package com.cy.yyjia.zhe28.ui.dialog;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogGmRolesBinding;
import com.cy.yyjia.zhe28.databinding.ItemGmRoleBinding;
import com.cy.yyjia.zhe28.domain.GMRoleBean;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GMRoleDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B0\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012!\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006¢\u0006\u0002\u0010\fJ\u0014\u0010\u0012\u001a\u00020\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/GMRoleDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogGmRolesBinding;", "fa", "Landroidx/fragment/app/FragmentActivity;", "click", "Lkotlin/Function1;", "Lcom/cy/yyjia/zhe28/domain/GMRoleBean;", "Lkotlin/ParameterName;", "name", "role", "", "(Landroidx/fragment/app/FragmentActivity;Lkotlin/jvm/functions/Function1;)V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/databinding/ItemGmRoleBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "setData", "data", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GMRoleDialog extends BaseDataBindingDialog<DialogGmRolesBinding, GMRoleDialog> {
    public static final int $stable = 8;
    private final BaseAdapter<GMRoleBean, ItemGmRoleBinding> adapter;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GMRoleDialog(FragmentActivity fa, final Function1<? super GMRoleBean, Unit> click) {
        super(fa, R.layout.dialog_gm_roles);
        Intrinsics.checkNotNullParameter(fa, "fa");
        Intrinsics.checkNotNullParameter(click, "click");
        BaseAdapter<GMRoleBean, ItemGmRoleBinding> baseAdapter = new BaseAdapter<>(R.layout.item_gm_role, null, 2, null);
        this.adapter = baseAdapter;
        ((DialogGmRolesBinding) this.mBinding).rv.setAdapter(baseAdapter);
        baseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.GMRoleDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                GMRoleDialog._init_$lambda$0(this.f$0, click, baseQuickAdapter, view, i);
            }
        });
        ((DialogGmRolesBinding) this.mBinding).ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.GMRoleDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GMRoleDialog._init_$lambda$1(this.f$0, view);
            }
        });
    }

    public final BaseAdapter<GMRoleBean, ItemGmRoleBinding> getAdapter() {
        return this.adapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(GMRoleDialog this$0, Function1 click, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(click, "$click");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        int size = this$0.adapter.getData().size();
        int i2 = 0;
        while (i2 < size) {
            this$0.adapter.getItem(i2).setSelected(i2 == i);
            i2++;
        }
        click.invoke(this$0.adapter.getItem(i));
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(GMRoleDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final GMRoleDialog setData(List<GMRoleBean> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.adapter.setNewInstance(data);
        return this;
    }
}
